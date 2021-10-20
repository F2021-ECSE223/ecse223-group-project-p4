package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;

public class ClimbSafeFeatureSet3Controller {


	/**
	 * This method allows a guide to put his personal informations
	 * so he can register in the system. The guide personal informations
	 * include his email, his password, his name, and an emergency contact
	 * of his choice
	 * 
	 * The method throws an invalid input exception when the following cases happen:
	 * 
	 * Case 1: the email is "admin@nmc.nt", a guide email cannot be the
	 * admin email
	 * 
	 * Case 2: the input email cannot be linked to another guide's or
	 * member's email in the system
	 * 
	 * Case 3: the input email must not contain any space
	 * 
	 * Case 4: The input email must be valid
	 * 
	 * Case 5: email, password, name, and emergency contact must not be
	 * empty
	 * 
	 * Finally, if there is no exception, a new guide with the input
	 * information is added in the system
	 * 
	 * @param email
	 * @param password
	 * @param name
	 * @param emergencyContact
	 * @throws InvalidInputException
	 */
	
	
  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {
	  
	  ClimbSafe system = ClimbSafeApplication.getClimbSafe();
	  
	  // Case 1
	  if(email.equals("admin@nmc.nt")) throw new InvalidInputException("Email cannot be admin@nmc.nt");
	  
	  // Case 2
	  if(User.hasWithEmail(email)) {
		  if(User.getWithEmail(email) instanceof Guide) {
			  throw new InvalidInputException("Email already linked to a guide account");
		  }
		  
		  if(User.getWithEmail(email) instanceof Member) {
			  throw new InvalidInputException("Email already linked to a member account");
		  }
	  }
	  
	  // Case 3
	  if(email.contains(" ")) throw new InvalidInputException("Email must not contain any space.");
	  
	  // Case 4
	  if(emailIsInvalid(email)) throw new InvalidInputException("Invalid email.");
	  
	  // Case 5 (the three following conditions)
	  if(email.equals("")) throw new InvalidInputException("Email cannot be empty");
	  
	  if(password.equals("")) throw new InvalidInputException("Password cannot be empty");
	  
	  if(name.equals("")) throw new InvalidInputException("Name cannot be empty");
	  
	  if(emergencyContact.equals("")) throw new InvalidInputException("Emergency Contact cannot be empty");
	  
	  // Finally, if no exception is thrown, add a new guide in the system.
	  system.addGuide(new Guide(email, password, name, emergencyContact, system));
  }
  
  /**
   * Helper method that check whether an email is invalid or not:
   * 
   * Case 1: An email is invalid when it does not contain exactly one '@' or 
   * exactly one '.'.
   * 
   * Case 2: An email is invalid when the email does not contain the substring
   * "@email.com".
   * 
   * Case 3: an email is invalid when the substring "@email.com" is not at the
   * end of the whole string.
   * 
   * Case 4: an email is invalid if there is no substring before the character
   * '@', i.e. if the character '@' is the first one.
   * 
   * If either one of these 4 cases above happen, the email is invalid
   * 
   * 
   * @param aemail
   * @return true if at least one of the 4 conditions is true,
   * false otherwise
   */
  
  
  private static boolean emailIsInvalid (String aemail) {
	  return containsDuplicateAtOrDot(aemail) || !aemail.contains("@email.com") ||
			    aemail.charAt(aemail.length()-4)!='.' ||  aemail.charAt(0)=='@';
  }
  
  /** 
   * Helper method that checks if the input email contains more
   * than one '@' or '.'
   * 
   * @param aemail
   * @return true if the input email contains exactly one '@' and
   * exactly one '.', false otherwise
   */
  private static boolean containsDuplicateAtOrDot (String aemail) {
	  int numberOfAt=0;
	  int numberOfDot=0;
	  
	  // Iterate through the characters of the string
	  for (int i=0; i<aemail.length(); i++) {
		  // Incrementing the number of '@' in the email
		  if(aemail.charAt(i)=='@') {
			  numberOfAt++;
		  }
		// Incrementing the number of '.' in the email
		  if(aemail.charAt(i)=='.') {
			  numberOfDot++;
		  }
	  }
	  // And finally, return true if numbers of '.' and '@' are exactly one each
	  return numberOfAt==1 && numberOfDot==1;
  }
  
  
  /**
   * This method update a guide's information. The guide can update his
   * password, his name, and his emergency contact, but not his email.
   * 
   * The method throws an invalid input exception when the following cases happen:
   * 
   * Case 1: if the input email is not in the system.
   * 
   * Case 2: if the new password, the new name, or the new emergency contact is empty.
   * 
   * Finally, if there is no exceptions, password, name, and emergency contact are
   * updated using setPassword, setName, and setEmergencyContact.
   * 
   * @param email
   * @param newPassword
   * @param newName
   * @param newEmergencyContact
   * @throws InvalidInputException
   */
  
  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {
	  
	  // If the input email is not in the system
	  if(!Guide.hasWithEmail(email)) throw new InvalidInputException("No guides found with that email.");
	  
	  // If the new password is empty
	  if(newPassword.equals("")) throw new InvalidInputException("Password cannot be empty.");
	  
	  // If the new name is empty
	  if(newName.equals("")) throw new InvalidInputException("Name cannot be empty.");
	  
	  // If the new emergency contact is empty
	  if(newEmergencyContact.equals("")) throw new InvalidInputException("Emergency contact cannot be empty.");
	  
	  // set the new Password linked to the input email
	  ((Guide) User.getWithEmail(email)).setPassword(newPassword);
	  
	  // set the new Password linked to the input email
	  ((Guide) User.getWithEmail(email)).setName(newName);
	  
	  // set the new Password linked to the input email
	  ((Guide) User.getWithEmail(email)).setEmergencyContact(newEmergencyContact);
		  
  }


















