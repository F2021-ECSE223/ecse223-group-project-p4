package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class ClimbSafeFeatureSet2Controller {
  /**
   * This method checks for the validity of all the input data entered by the User upon
   * registration. If it is valid, a Member object is created, added to the system and all required
   * equipment is booked in the system.
   * 
   * @author Adam Kazma
   * @param email - The new member's e-mail address
   * @param password - The new member's password
   * @param name - The new member's full Name
   * @param emergencyContact - The emergencyContact information submitted by the new member
   * @param nrWeeks - The number of climbing weeks desired by the new member
   * @param guideRequired - Whether the new member requires a guide or not
   * @param hotelRequired - Whether the new member requires a hotel or not
   * @param itemNames - List of names of the equipment items or bundles chosen by the new member
   * @param itemQuantities - List of quantities of items whose name is in the previous parameter
   * @throws InvalidInputException If any information entered by the user invalidates registration
   * 
   */
  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {

    ClimbSafe system = ClimbSafeApplication.getClimbSafe();

    // Edge Cases
    if (User.hasWithEmail(email)) {
      if (User.getWithEmail(email) instanceof Member) {
        throw new InvalidInputException("A member with this email already exists");
      }

      if (User.getWithEmail(email) instanceof Guide) {
        throw new InvalidInputException("A guide with this email already exists");
      }
    }

    if (email.contains(" "))
      throw new InvalidInputException("The email must not contain any spaces.");

    if (!emailIsValid(email))
      throw new InvalidInputException("Invalid email.");

    if (password.equals(""))
      throw new InvalidInputException("The password cannot be empty");

    if (name.equals(""))
      throw new InvalidInputException("The name cannot be empty");

    if (emergencyContact.equals(""))
      throw new InvalidInputException("The emergency contact cannot be empty");

    if (nrWeeks <= 0 || nrWeeks > system.getNrWeeks()) {
      throw new InvalidInputException(
          "The number of weeks must be greater than zero and less than or equal"
              + " to the number of climbing weeks in the climbing season");
    }

    if (email.equals("admin@nmc.nt"))
      throw new InvalidInputException("The email entered is not allowed for members");

    for (String itemLabel : itemNames) {
      if (!BookableItem.hasWithName(itemLabel))
        throw new InvalidInputException("Requested item not found");
    }

    // Creating a new member
    Member memb = system.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired,
        hotelRequired);

    // Adding the new member to the system
    system.addMember(memb);

    // Adding booked items for this member
    for (int a = 0; a < itemNames.size(); a++) {
      system.addBookedItem(new BookedItem(itemQuantities.get(a),system, memb,
          BookableItem.getWithName(itemNames.get(a))));
    }
    
    ClimbSafePersistence.save(system);
  }

  /**
   * This method checks for the validity of the updated data entered by the Member before
   * overwriting his password, name, number of weeks, hotel requirement and guide requirement. The
   * previously booked equipment items are cleared from the system and the new ones are booked
   * instead.
   * 
   * @param email - Member's e-mail address, that is used to identify him as it is not changed.
   * @param newPassword - The new password desired by the member, which will replace the old one.
   * @param newName - The Member's new name.
   * @param newEmergencyContact - The member's new emergency contact information.
   * @param newNrWeeks - The new number of weeks desired.
   * @param newGuideRequired - Update to the guide requirement.
   * @param newHotelRequired - Update to the hotel requirement.
   * @param newItemNames - New names of the items required by the Member, which will overwrite the
   *        old ones.
   * @param newItemQuantities - New quantities for the new items (whose name is in the previous
   *        parameter).
   * @throws InvalidInputException If any data entered is invalid.
   * @author Adam Kazma
   */
  public static void updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
      throws InvalidInputException {
    ClimbSafe system = ClimbSafeApplication.getClimbSafe();
    // Edge Cases
    if (newPassword.equals(""))
      throw new InvalidInputException("The password cannot be empty");

    if (newName.equals(""))
      throw new InvalidInputException("The name cannot be empty");

    if (newEmergencyContact.equals(""))
      throw new InvalidInputException("The emergency contact cannot be empty");

    if (newNrWeeks <= 0 || newNrWeeks > system.getNrWeeks()) {
      throw new InvalidInputException(
          "The number of weeks must be greater than zero and less than or equal"
              + " to the number of climbing weeks in the climbing season");
    }
    if (Member.getWithEmail(email) == null || !(Member.getWithEmail(email) instanceof Member)) {
      throw new InvalidInputException("Member not found");
    }
    if (email.equals("admin@nmc.nt"))
      throw new InvalidInputException("The email entered is not allowed for members");

    for (String itemLabel : newItemNames) {
      if (!BookableItem.hasWithName(itemLabel))
        throw new InvalidInputException("Requested item not found");
    }
    // Clear previously booked items for this member
    for (int i = 0; i < ((Member) Member.getWithEmail(email)).getBookedItems().size(); i++) {
      system.getBookedItem(i).delete();
      ((Member) Member.getWithEmail(email)).getBookedItem(i).delete();
    }
    // Enter new booked items for this member
    for (int a = 0; a < newItemNames.size(); a++) {
      ((Member) Member.getWithEmail(email)).addBookedItem(
          system.addBookedItem(newItemQuantities.get(a), ((Member) Member.getWithEmail(email)),
              BookableItem.getWithName(newItemNames.get(a))));
    }
    // Update member details
    Member.getWithEmail(email).setPassword(newPassword);
    ((NamedUser) Member.getWithEmail(email)).setName(newName);
    ((NamedUser) Member.getWithEmail(email)).setEmergencyContact(newEmergencyContact);
    ((Member) Member.getWithEmail(email)).setNrWeeks(newNrWeeks);
    ((Member) Member.getWithEmail(email)).setGuideRequired(newGuideRequired);
    ((Member) Member.getWithEmail(email)).setHotelRequired(newHotelRequired);
    
    ClimbSafePersistence.save(system);
  }

  // Helper method
  /**
   * 
   * This method checks the validity of the email by checking the following conditions:
   * <ul>
   * <li>The email contains exactly one '@' character that is not at the beginning.</li>
   * <li>The email contains exactly one dot '.' after the '@' character, which is not at the very
   * end of the string.</li>
   * <li>There is at least one character between the '@' character and each '.' dot character.</li>
   * <li>The email isn't an empty string.</li>
   * </ul>
   * 
   * @param email - The email address to be checked
   * @author Adam Kazma, Ralph Nassar
   * @return True (If email is valid), False (If any of the conditions are not met)
   */
  static boolean emailIsValid(String email) {
    if (email.charAt(email.length() - 1) == '.') {
      return false;
    }
    int atNum = 0;
    int atIndex = email.length() - 1;
    int dotNumAfterAt = 0;
    char[] charArr = email.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      if (charArr[i] == '@') {
        atNum += 1;
        atIndex = i;
        if (i == 0)
          return false;
        if (i < charArr.length - 1) {
          if (charArr[i + 1] == '.')
            return false;
        }
        if (i > 0) {
          if (charArr[i - 1] == '.')
            return false;
        }
      }
      if (charArr[i] == '.') {
        if (i > atIndex)
          dotNumAfterAt += 1;
        if (i < charArr.length - 1) {
          if (charArr[i + 1] == '.')
            return false;
        }
        if (i > 0) {
          if (charArr[i - 1] == '.')
            return false;
        }
      }
    }
    if (atNum != 1)
      return false;
    if (dotNumAfterAt < 1)
      return false;
    return true;
  }
}