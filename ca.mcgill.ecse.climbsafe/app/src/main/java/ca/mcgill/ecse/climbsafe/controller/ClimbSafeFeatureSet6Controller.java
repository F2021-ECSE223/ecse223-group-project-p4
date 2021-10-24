package ca.mcgill.ecse.climbsafe.controller;

// Default imports
import java.util.List;

// My imports
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;
import java.util.ArrayList;

public class ClimbSafeFeatureSet6Controller {

  public static void deleteEquipment(String name) throws InvalidInputException {
	  
	  // Retrieving the item to delete from the system
      BookableItem toDelete = BookableItem.getWithName(name);
      
      // Checking that it is actually of type equipment
      if(toDelete != null && toDelete instanceof Equipment equipmentItem) {
        
        // Checking that the equipment with the indicated name is not part of a bundle
        if(equipmentItem.getBundleItems().size() != 0)
          throw(new InvalidInputException("The piece of equipment is in a bundle and cannot be deleted"));
        
        // Deleting the piece of equipment
        toDelete.delete();
      }
  }

  public static void deleteEquipmentBundle(String name) {
	  
	  // Retrieving the bundle to delete from the system
	  var toDelete = BookableItem.getWithName(name);
	  
	  // Delete the bundle if it exists in the system
	  if(toDelete != null && toDelete instanceof EquipmentBundle) toDelete.delete();
	  
  }

  public static List<TOAssignment> getAssignments() {
	  
	  // Putting the system we're working on in a local variable
	  ClimbSafe system = ClimbSafeApplication.getClimbSafe();
	  
	  // Retrieving the list of all the assignments of the system
	  List<Assignment> assignments = system.getAssignments();
	  
	  // Copying all the elements of the list to a new list of TOAssignments
	  List<TOAssignment> TOassignments = new ArrayList<>();
	  
	  for(Assignment assignment : assignments) {
		  
		 // Extracting the member email
		 String memberEmail = assignment.getMember().getEmail();
		 
		 // Extracting the member name
		 String memberName = assignment.getMember().getName();
		 
		 // Extracting the guide name and email
		 String guideEmail, guideName;
		 if(assignment.getGuide() != null) {
			 guideEmail = assignment.getGuide().getEmail();
			 guideName = assignment.getGuide().getName();
		 }
		 else {
			 guideEmail = null;
			 guideName = null;
		 }
		 
		 // Extracting the hotel name
		 String hotelName;
		 if(assignment.getHotel() != null) hotelName = assignment.getHotel().getName();
		 else hotelName = null;
		 
		 // Extracting the assignment start week
		 int startWeek = assignment.getStartWeek();
		 
		 // Extracting the assignment end week
		 int endWeek = assignment.getEndWeek();
		 
		 // Calculating the total cost for the guide
		 int totalCostForGuide;
		 if(guideEmail != null) totalCostForGuide = (endWeek - startWeek + 1) * system.getPriceOfGuidePerWeek();
		 else totalCostForGuide = 0;
		 
		 // Calculating the total cost of the booked equipment using a helper method
		 int totalCostForEquipment = getTotalCostOfEquipment(assignment, startWeek, endWeek, guideName != null);
		
		 // Adding the corresponding TOAssignment to the list we will be returning
		 TOassignments.add(new TOAssignment(memberEmail, memberName, guideEmail, guideName, hotelName, startWeek, endWeek, totalCostForGuide, totalCostForEquipment)); 
	  }
	  
	  return TOassignments; // Returning a list of TOAssignments
  }
  
  //Helper method that computes the total price of the equipment booked by the member of the assignment over the duration of the trip
  private static int getTotalCostOfEquipment(Assignment assignment, int startWeek, int endWeek, boolean hasGuide) {
	
	// Variable that keeps track of the total cost throughout the method
	int totalPrice = 0;
	
	// Looping over all the booked items of the member
	for(var bookedItem : assignment.getMember().getBookedItems()) {
		
		// If it is an equipment item
		if(bookedItem.getItem() instanceof Equipment item)
			totalPrice += bookedItem.getQuantity() * item.getPricePerWeek() * (endWeek - startWeek + 1); // Multiplying by the number of weeks and the quantity
		
		
		// If it is an equipment bundle
		else {
			int bundlePricePerWeek = 0;
			EquipmentBundle bundle = (EquipmentBundle) bookedItem.getItem();  // Extracting the equipment bundle by downcasting
			
			// Looping over all the items in the bundle
			for(BundleItem bundleItem : bundle.getBundleItems()) {
				int itemPricePerWeek = bundleItem.getEquipment().getPricePerWeek() * bundleItem.getQuantity(); 
				bundlePricePerWeek += itemPricePerWeek;
			}
			
			// Applying the discount if a guide was hired
			if(hasGuide) bundlePricePerWeek = (int) Math.round(bundlePricePerWeek*(100-bundle.getDiscount())/100.0);
			
			// Adding the price of this bundle by multiplying by the quantity and the number of weeks
			totalPrice += bookedItem.getQuantity() * bundlePricePerWeek * (endWeek - startWeek + 1); // Multiplying by the number of weeks and the quantity
		}
	}
	
	return totalPrice; // Returning the total price over the trip
  }
  	
}
