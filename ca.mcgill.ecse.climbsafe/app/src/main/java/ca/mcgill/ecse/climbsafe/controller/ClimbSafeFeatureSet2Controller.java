package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.model.*;

public class ClimbSafeFeatureSet2Controller {

  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
	  ClimbSafe system = ClimbSafeApplication.getClimbSafe();
	  //Edge Cases
	  if(User.hasWithEmail(email)) {
		  if(User.getWithEmail(email) instanceof Member) {
			  throw new InvalidInputException("A member with this email already exists");
		  }
		  
		  if(User.getWithEmail(email) instanceof Guide) {
			  throw new InvalidInputException("A guide with this email already exists");
		  }
	  } 
	  if(email.contains(" ")) throw new InvalidInputException("The email must not contain any spaces.");
	  
	  if(!emailIsValid(email)) throw new InvalidInputException("Invalid email.");
	  
	  if(password.equals("")) throw new InvalidInputException("The password cannot be empty");
	  
	  if(name.equals("")) throw new InvalidInputException("The name cannot be empty");
	  
	  if(emergencyContact.equals("")) throw new InvalidInputException("The emergency contact cannot be empty");
	  
	  if(nrWeeks <= 0 || nrWeeks > system.getNrWeeks()) {
		  throw new InvalidInputException("The number of weeks must be greater than zero and less than or equal" + 
	     " to the number of climbing weeks in the climbing season");
	  }
	  if(email.equals("admin@nmc.nt")) throw new InvalidInputException("The email entered is not allowed for members");
	  
	  for(String itemLabel : itemNames) {
		  if(!BookableItem.hasWithName(itemLabel)) throw new InvalidInputException("Requested item not found");
	  }
	  //Creating a new member
	  Member memb = system.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired);
	  //Adding the new member to the system
	  system.addMember(memb);
	  //Adding booked items for this member
	  for(int a = 0; a < itemNames.size(); a++) {
		  system.addBookedItem(system.addBookedItem(itemQuantities.get(a), memb, BookableItem.getWithName(itemNames.get(a))));
	  }
  }
	public static void updateMember(String email, String newPassword, String newName,
	      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
	      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
	      throws InvalidInputException {
		ClimbSafe system = ClimbSafeApplication.getClimbSafe();
		//Edge Cases
		if(newPassword.equals("")) throw new InvalidInputException("The password cannot be empty");
		  
		  if(newName.equals("")) throw new InvalidInputException("The name cannot be empty");
		  
		  if(newEmergencyContact.equals("")) throw new InvalidInputException("The emergency contact cannot be empty");
		  
		  if(newNrWeeks <= 0 || newNrWeeks > system.getNrWeeks()) {
			  throw new InvalidInputException("The number of weeks must be greater than zero and less than or equal" + 
		     " to the number of climbing weeks in the climbing season");
		  }
		  if(email.equals("admin@nmc.nt")) throw new InvalidInputException("The email entered is not allowed for members");
		  
		  for(String itemLabel : newItemNames) {
			  if(!BookableItem.hasWithName(itemLabel)) throw new InvalidInputException("Requested item not found");
		  }
		  //Update member details
		  Member.getWithEmail(email).setPassword(newPassword);
		  ((NamedUser)Member.getWithEmail(email)).setName(newName);
		  ((NamedUser)Member.getWithEmail(email)).setEmergencyContact(newEmergencyContact);
		  ((Member)Member.getWithEmail(email)).setNrWeeks(newNrWeeks);
		  ((Member)Member.getWithEmail(email)).setGuideRequired(newGuideRequired);
		  ((Member)Member.getWithEmail(email)).setHotelRequired(newHotelRequired);
		  //Clear previously booked items for this member
		  for(BookedItem bkdItem : system.getBookedItems()) {
			  if(bkdItem.getMember() == Member.getWithEmail(email)) bkdItem.delete();
		  }
		  //Enter new booked items for this member
		  for(int a = 0; a < newItemNames.size(); a++) {
			  system.addBookedItem(system.addBookedItem(newItemQuantities.get(a), ((Member)Member.getWithEmail(email)), BookableItem.getWithName(newItemNames.get(a))));
		  }
	}
	//Helper method
	private static boolean emailIsValid(String email) {
		int atNum = 0;
		int atIndex = 0;
		int dotNumAfterAt = 0;
		char[] charArr = email.toCharArray();
		for(int i = 0; i<charArr.length; i++) {
			if(charArr[i] == '@') {
				atNum += 1;
				atIndex = i;
				if(i == 0) return false;
				if(i < charArr.length - 1) {
					if(charArr[i+1] == '.') return false;
				}
				if(i > 0) {
					if(charArr[i-1] == '.') return false;
				}
			}
			if(atNum != 1) return false;
			if(charArr[i] == '.') {
				if(i > atIndex) dotNumAfterAt += 1;
				if(i < charArr.length - 1) {
					if(charArr[i+1] == '.') return false;
				}
				if(i > 0) {
					if(charArr[i-1] == '.') return false;
				}
			}
			if(dotNumAfterAt != 1) return false;
		}		
		return true;
	}
}
