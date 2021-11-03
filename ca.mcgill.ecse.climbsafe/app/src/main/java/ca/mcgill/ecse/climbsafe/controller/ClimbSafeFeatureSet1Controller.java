package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.util.List;

// My imports
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;


public class ClimbSafeFeatureSet1Controller {


  private static ClimbSafe system; // The system instance

  /**
   * The setup method will checks if its input has any error with the way they were given. If
   * there's an error with the given input, it raises an InvalidInputException and if not, it will
   * add them to the system just fine.
   * 
   * @author Karl Rouhana
   * @param startDate - the input start date
   * @param nrWeeks - the input number of weeks
   * @param priceOfGuidePerWeek - the input price of guide per week
   * @throws InvalidInputException - throws invalid input exception if there's an error
   * 
   */

  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {


    if (!(nrWeeks > 0)) // Checks if the number of climbing weeks is greater than or equal to zero
      throw new InvalidInputException(
          "The number of climbing weeks must be greater than or equal to zero");


    if (!(priceOfGuidePerWeek > 0)) // Checks if the price of guide per week is greater than or
                                    // equal to zero
      throw new InvalidInputException(
          "The price of guide per week must be greater than or equal to zero");


    system = ClimbSafeApplication.getClimbSafe(); // Get the current system


    // If everything is fine, set the start date, Number of weeks and the Price of the guide per
    // week to the system

    system.setStartDate(startDate);
    system.setNrWeeks(nrWeeks);
    system.setPriceOfGuidePerWeek(priceOfGuidePerWeek);


  }



  /**
   * The deleteMember method will delete a member if and only if the given email is associated with
   * a User, and that User is a Member. If it's a Guide or the email does not exist in the system,
   * don't do anything.
   * 
   * @author Karl Rouhana
   * @param email - the input email
   * 
   */



  public static void deleteMember(String email) {


    User toBeDeleted = User.getWithEmail(email); // Return the User with the associated email

    if (toBeDeleted != null && toBeDeleted instanceof Member) // If the user exists and is a member
      toBeDeleted.delete(); // Delete the member
  }


  /**
   * The deleteGuide method will delete a guide if and only if the given email is associated with a
   * User, and that User is a guide. If it's a Member or the email does not exist in the system,
   * don't do anything.
   * 
   * @author Karl Rouhana
   * @param email - the input email
   * 
   */


  public static void deleteGuide(String email) {


    User toBeDeleted = User.getWithEmail(email); // Return the User with the associated email

    if (toBeDeleted != null && toBeDeleted instanceof Guide) // If the user exists and is a Guide
      toBeDeleted.delete(); // Delete the guide

  }



  // We did not implement this method since we have 6 members on the team
  public static void deleteHotel(String name) {}

}
