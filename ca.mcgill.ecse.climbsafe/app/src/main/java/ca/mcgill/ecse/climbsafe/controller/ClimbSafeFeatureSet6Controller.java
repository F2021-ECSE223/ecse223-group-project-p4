package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class ClimbSafeFeatureSet6Controller {

  /**
   * Deletes a piece of equipment with a given name from the system (If it exists)
   * 
   * @author Wassim Jabbour
   * @param name The name of the piece of equipment to delete from the system
   * @throws InvalidInputException It throws an invalid input exception if the piece of equipment
   *         with the given name is part of a bundle
   */
  public static void deleteEquipment(String name) throws InvalidInputException {

    // Retrieving the item to delete from the system
    BookableItem toDelete = BookableItem.getWithName(name);

    // Checking that it is actually of type equipment
    if (toDelete != null && toDelete instanceof Equipment equipmentItem) {

      // Checking that the equipment with the indicated name is not part of a bundle
      if (equipmentItem.getBundleItems().size() != 0)
        throw (new InvalidInputException(
            "The piece of equipment is in a bundle and cannot be deleted"));

      // Deleting the piece of equipment
      toDelete.delete();
    }

    // Saving the system
    ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
  }

  /**
   * Deletes an equipment bundle with a given name from the system (If it exists)
   * 
   * @author Wassim Jabbour
   * @param name The name of the equipment bundle to delete from the system
   */
  public static void deleteEquipmentBundle(String name) {

    // Retrieving the bundle to delete from the system
    var toDelete = BookableItem.getWithName(name);

    // Delete the bundle if it exists in the system
    if (toDelete != null && toDelete instanceof EquipmentBundle)
      toDelete.delete();

    // Saving the system
    ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
  }

  /**
   * Returns all the assignments a system has in the form of transfer objects
   * 
   * @author Wassim Jabbour
   */
  public static List<TOAssignment> getAssignments() {

    // Putting the system we're working on in a local variable
    ClimbSafe system = ClimbSafeApplication.getClimbSafe();

    // Retrieving the list of all the assignments of the system
    List<Assignment> assignments = system.getAssignments();

    // Copying all the elements of the list to a new list of TOAssignments
    List<TOAssignment> TOassignments = new ArrayList<>();

    for (Assignment assignment : assignments) {

      // Extracting the member email
      String memberEmail = assignment.getMember().getEmail();

      // Extracting the member name
      String memberName = assignment.getMember().getName();

      // Extracting the guide name and email
      String guideEmail = null, guideName = null;
      if (assignment.getGuide() != null) {
        guideEmail = assignment.getGuide().getEmail();
        guideName = assignment.getGuide().getName();
      }

      // Extracting the hotel name
      String hotelName = null;
      if (assignment.getHotel() != null)
        hotelName = assignment.getHotel().getName();

      // Extracting the assignment start week
      int startWeek = assignment.getStartWeek();

      // Extracting the assignment end week
      int endWeek = assignment.getEndWeek();

      // Calculating the total cost for the guide
      int totalCostForGuide = 0;
      if (guideEmail != null)
        totalCostForGuide = (endWeek - startWeek + 1) * system.getPriceOfGuidePerWeek();

      // Calculating the total cost of the booked equipment using a helper method
      int totalCostForEquipment = getTotalCostOfEquipment(assignment);

      // Extracting the assignment state
      String status = assignment.getAssignmentStateFullName();

      // Extracting the authorization code
      String authorizationCode = assignment.getAuthorizationCode();

      // Extracting the refund percentage
      int refund = assignment.getRefundPercentage();

      // Extracting the climb comment and rating
      String rating = null, comment = null;
      if (assignment.hasReview()) {
        rating = assignment.getReview().getRating().name();
        comment = assignment.getReview().getComment();
      }

      // Extracting the climb length and location
      int length = 0;
      String location = null;
      if (assignment.hasClimbingPath()) {
        length = assignment.getClimbingPath().getLength();
        location = assignment.getClimbingPath().getLocation();
      }


      // Adding the corresponding TOAssignment to the list we will be returning
      TOassignments.add(new TOAssignment(memberEmail, memberName, guideEmail, guideName, hotelName,
          startWeek, endWeek, totalCostForGuide, totalCostForEquipment, status, authorizationCode,
          refund, rating, comment, location, length));
    }

    return TOassignments; // Returning a list of TOAssignments
  }

  /**
   * Helper method that computes the total price of the equipment booked by a member. It accounts
   * for the length of the trip and the number of booked items. In the case of booking a bundle, it
   * also applies a discount if a guide was requested
   * 
   * @author Wassim Jabbour
   * @param assignment The assignment we want to compute the total cost of the equipment for
   */
  private static int getTotalCostOfEquipment(Assignment assignment) {

    // Extracting the attributes of the assignment
    int startWeek = assignment.getStartWeek();
    int endWeek = assignment.getEndWeek();
    boolean hasGuide = (assignment.getGuide() != null);

    // Variable that keeps track of the total cost throughout the method
    int totalPrice = 0;

    // Looping over all the booked items of the member
    for (var bookedItem : assignment.getMember().getBookedItems()) {

      // If it is an equipment item
      if (bookedItem.getItem()instanceof Equipment item)
        // Multiplying by the number of weeks and the quantity
        totalPrice += bookedItem.getQuantity() * item.getPricePerWeek() * (endWeek - startWeek + 1);

      // If it is an equipment bundle
      else {
        int bundlePricePerWeek = 0;
        EquipmentBundle bundle = (EquipmentBundle) bookedItem.getItem();

        // Looping over all the items in the bundle
        for (BundleItem bundleItem : bundle.getBundleItems()) {
          int itemPricePerWeek =
              bundleItem.getEquipment().getPricePerWeek() * bundleItem.getQuantity();
          bundlePricePerWeek += itemPricePerWeek;
        }

        // Applying the discount if a guide was hired
        if (hasGuide)
          bundlePricePerWeek =
              (int) Math.round(bundlePricePerWeek * (100 - bundle.getDiscount()) / 100.0);

        // Adding the price of this bundle by multiplying by the quantity and the number of weeks
        totalPrice += bookedItem.getQuantity() * bundlePricePerWeek * (endWeek - startWeek + 1);
      }
    }

    return totalPrice; // Returning the total price over the trip
  }

}
