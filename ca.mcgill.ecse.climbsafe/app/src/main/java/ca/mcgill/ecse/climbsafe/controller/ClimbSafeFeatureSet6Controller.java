package ca.mcgill.ecse.climbsafe.controller;

// Default imports
import java.util.List;

// My imports
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;
import java.util.ArrayList;

public class ClimbSafeFeatureSet6Controller {

  public static void deleteEquipment(String name) throws InvalidInputException {
	  
	  
	  // Putting the system we're working on in a local variable
	  ClimbSafe system = ClimbSafeApplication.getClimbSafe();
	  
	  
	  // Checking if the equipment with the indicated name is part of a bundle
	  boolean foundEquipment = false;
	 
	  equipmentSearch:
	  for(EquipmentBundle bundle : system.getBundles()) {
		  for(BundleItem association : bundle.getBundleItems()) {
			  if(association.getEquipment().getName().equals(name)){
				  foundEquipment = true;
				  break equipmentSearch;
			  }
		  }
	  }
	  
	  if(foundEquipment) {
		  throw(new InvalidInputException("The piece of equipment is in a bundle and cannot be deleted"));
	  }
	  
	  
	  
	  // Retrieving the item to delete from the system
	  BookableItem toDelete = Equipment.getWithName(name);
	  
	  // Checking that it is actually of type equipment
	  if(toDelete == null) {
		  return; //If the piece of equipment does not exist in the system
	  }
	  
	  // Deleting the piece of equipment
	  toDelete.delete();
  }

  public static void deleteEquipmentBundle(String name) {
	  
	  // Putting the system we're working on in a local variable
	  ClimbSafe system = ClimbSafeApplication.getClimbSafe();
	  
	  // Retrieving the bundle to delete from the system
	  BookableItem toDelete = EquipmentBundle.getWithName(name);
	  
	  // If the bundle does not exist in the system
	  if(toDelete == null) {
		  return;
	  }
	  
	  // If the bundle exists in the system
	  toDelete.delete();
	  
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
			 guideEmail = "";
			 guideName = "";
		 }
		 
		 // Extracting the hotel name
		 String hotelName;
		 if(assignment.getHotel() != null) hotelName = assignment.getHotel().getName();
		 else hotelName = "";
		 
		 // Extracting the assignment start week
		 int startWeek = assignment.getStartWeek();
		 
		 // Extracting the assignment end week
		 int endWeek = assignment.getEndWeek();
		 
		 // Calculating the total cost for the guide
		 int totalCostForGuide;
		 if(guideEmail != "") totalCostForGuide = (endWeek - startWeek + 1) * system.getPriceOfGuidePerWeek();
		 else totalCostForGuide = 0;
		 
		 // Calculating the total cost of the booked equipment using a helper method
		 int totalCostForEquipment = getTotalCostOfEquipment(assignment, startWeek, endWeek, guideName != "");
		
		 // Adding the corresponding TOAssignment to the list we will be returning
		 TOassignments.add(new TOAssignment(memberEmail, memberName, guideEmail, guideName, hotelName, startWeek, endWeek, totalCostForGuide, totalCostForEquipment)); 
	  }
	  
	  return TOassignments; // Returning a list of TOAssignments
  }
  
  //Helper method that computes the total price of the equipment booked by the member of the assignment over the duration of the trip
  private static int getTotalCostOfEquipment(Assignment assignment, int startWeek, int endWeek, boolean hasGuide) {
	  
	// Putting the system we're working on in a local variable
	ClimbSafe system = ClimbSafeApplication.getClimbSafe();
	
	// Variable that keeps track of the total cost throughout the method
	int totalPrice = 0;
	
	// Looping over all the booked items of the member
	for(BookedItem bookedItem : assignment.getMember().getBookedItems()) {
		
		// If it is an equipment item
		if(bookedItem.getItem() instanceof Equipment) {
			Equipment item = (Equipment) bookedItem.getItem(); // Extracting the equipment item by downcasting
			totalPrice += bookedItem.getQuantity() * item.getPricePerWeek() * (endWeek - startWeek + 1); // Multiplying by the number of weeks and the quantity
		}
		
		// If it is an equipment bundle
		else {
			int bundlePricePerWeek = 0;
			EquipmentBundle bundle = (EquipmentBundle) bookedItem.getItem();  // Extracting the equipment bundle by downcasting
			
			// Looping over all the items in the bundle
			for(BundleItem bundleItem : bundle.getBundleItems()) {
				int itemPricePerWeek = bundleItem.getEquipment().getPricePerWeek() * bundleItem.getQuantity(); 
				if(hasGuide) itemPricePerWeek = (int) Math.round(itemPricePerWeek*(100-bundle.getDiscount())/100.0); // Applying the discount if a guide was hired
				bundlePricePerWeek += itemPricePerWeek;
			}
			
			totalPrice += bookedItem.getQuantity() * bundlePricePerWeek * (endWeek - startWeek + 1); // Multiplying by the number of weeks and the quantity
		}
	}
	
	return totalPrice; // Returning the total price over the trip
  }
  	
}
