package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;

public class ClimbSafeFeatureSet3Controller {

  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {
	  
	  ClimbSafe system = ClimbSafeApplication.getClimbSafe();
	  
	  if(email.equals("admin@nmc.nt")) throw new InvalidInputException("Email cannot be admin@nmc.nt");
	  
	  if(User.hasWithEmail(email)) {
		  if(User.getWithEmail(email) instanceof Guide) {
			  throw new InvalidInputException("Email already linked to a guide account");
		  }
		  
		  if(User.getWithEmail(email) instanceof Member) {
			  throw new InvalidInputException("Email already linked to a member account");
		  }
	  }
	  
	  
	  if(email.contains(" ")) throw new InvalidInputException("Email must not contain any space.");
	  
	  if(emailIsInvalid(email)) throw new InvalidInputException("Invalid email.");
	  
	  if(email.equals("")) throw new InvalidInputException("Email cannot be empty");
	  
	  if(password.equals("")) throw new InvalidInputException("Password cannot be empty");
	  
	  if(name.equals("")) throw new InvalidInputException("Name cannot be empty");
	  
	  if(emergencyContact.equals("")) throw new InvalidInputException("Emergency Contact cannot be empty");
	  
	  system.addGuide(new Guide(email, password, name, emergencyContact, system));
  }
  
  private static boolean emailIsInvalid (String aemail) {
	  boolean b=containsDuplicateAtOrDot(aemail) || !aemail.contains("@email.com") ||
			     aemail.charAt(0)=='@';
		return !b;
  }
  
  // return true if the input email contains one @, false otherwise
  private static boolean containsDuplicateAtOrDot (String aemail) {
	  int numberOfAt=0;
	  int numberOfDot=0;
	  for (int i=0; i<aemail.length(); i++) {
		  if(aemail.charAt(i)=='@') {
			  numberOfAt++;
		  }
		  if(aemail.charAt(i)=='.') {
			  numberOfDot++;
		  }
	  }
	  return numberOfAt==1 && numberOfDot==1;
  }
  
  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {
	  
	  ClimbSafe system = ClimbSafeApplication.getClimbSafe();
	  
	  if(!Guide.hasWithEmail(email)) throw new InvalidInputException("No guides found with that email.");
	  
	  if(newPassword.equals("")) throw new InvalidInputException("Password cannot be empty.");
	  
	  if(newName.equals("")) throw new InvalidInputException("Name cannot be empty.");
	  
	  if(newEmergencyContact.equals("")) throw new InvalidInputException("Emergency contact cannot be empty.");
	  
	  ((Guide) User.getWithEmail(email)).setPassword(newPassword);
	  
	  ((Guide) User.getWithEmail(email)).setName(newName);
	  
	  ((Guide) User.getWithEmail(email)).setEmergencyContact(newEmergencyContact);
		  
	
		  
	  }
	  
	  
	  
  }

}


















