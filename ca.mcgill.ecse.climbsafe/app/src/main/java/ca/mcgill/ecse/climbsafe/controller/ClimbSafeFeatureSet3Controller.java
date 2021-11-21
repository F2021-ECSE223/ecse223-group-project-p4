package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class ClimbSafeFeatureSet3Controller {


  /**
   * This method allows a guide to put his personal informations so he can register in the system.
   * The guide personal informations include his email, his password, his name, and an emergency
   * contact of his choice
   * 
   * @author Ralph Nassar
   * 
   * @param email
   * @param password
   * @param name
   * @param emergencyContact
   * @throws InvalidInputException when the following cases happen:
   * 
   *         Case 1: the input email must not contain any space
   * 
   *         Case 2: the input email cannot be linked to another guide's or member's email in the
   *         system
   * 
   *         Case 3: email, password, name, and emergency contact must not be empty
   * 
   *         Case 4: The input email must be valid
   * 
   *         Case 5: the input email must not be admin@nmc.nt
   */


  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {

    // Case 1
    if (email.contains(" "))
      throw new InvalidInputException("Email must not contain any spaces");

    // Case 2
    if (User.hasWithEmail(email)) {
      if (User.getWithEmail(email) instanceof Guide) {
        throw new InvalidInputException("Email already linked to a guide account");
      }

      if (User.getWithEmail(email) instanceof Member) {
        throw new InvalidInputException("Email already linked to a member account");
      }
    }


    // Case 3 (the four following conditions)
    if (email.isEmpty())
      throw new InvalidInputException("Email cannot be empty");

    if (password.isEmpty())
      throw new InvalidInputException("Password cannot be empty");

    if (name.isEmpty())
      throw new InvalidInputException("Name cannot be empty");

    if (emergencyContact.isEmpty())
      throw new InvalidInputException("Emergency contact cannot be empty");

    // Case 4 (the email is invalid)
    if (!ClimbSafeFeatureSet2Controller.emailIsValid(email))
      throw new InvalidInputException("Invalid email");

    // Case 5 (the email equals admin@nmc.nt
    if (email.equals("admin@nmc.nt"))
      throw new InvalidInputException("Email cannot be admin@nmc.nt");

    // Finally, if no exception is thrown, add a new guide in the system!
    ClimbSafeApplication.getClimbSafe().addGuide(
        new Guide(email, password, name, emergencyContact, ClimbSafeApplication.getClimbSafe()));

    ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
  }



  /**
   * This method update a guide's information. The guide can update his password, his name, and his
   * emergency contact, but not his email, using setPassword, setName, and setEmergencyContact.
   * 
   * @author Ralph Nassar
   * 
   * @param email the email of the current guide
   * @param newPassword the new password of the guide
   * @param newName the new name that the guide wants to set in the system
   * @param newEmergencyContact the new emergency contact of the guide
   * @throws InvalidInputException when one of the following cases happen:
   * 
   *         Case 1: if the input email is not in the system.
   * 
   *         Case 2: if the new password, the new name, or the new emergency contact is empty.
   */

  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {

    // If the input email is not in the system
    if (!Guide.hasWithEmail(email))
      throw new InvalidInputException("No guides found with that email.");

    // If the new password is empty
    if (newPassword.equals(""))
      throw new InvalidInputException("Password cannot be empty.");

    // If the new name is empty
    if (newName.equals(""))
      throw new InvalidInputException("Name cannot be empty.");

    // If the new emergency contact is empty
    if (newEmergencyContact.equals(""))
      throw new InvalidInputException("Emergency contact cannot be empty.");

    // If no error is thrown, set new personal informations

    // set the new Password linked to the input email
    ((Guide) User.getWithEmail(email)).setPassword(newPassword);

    // set the new Password linked to the input email
    ((Guide) User.getWithEmail(email)).setName(newName);

    // set the new Password linked to the input email
    ((Guide) User.getWithEmail(email)).setEmergencyContact(newEmergencyContact);

    ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());

  }


}
