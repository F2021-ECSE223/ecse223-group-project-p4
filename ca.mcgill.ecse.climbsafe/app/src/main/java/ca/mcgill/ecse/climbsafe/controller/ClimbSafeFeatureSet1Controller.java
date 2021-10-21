package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.util.List;

//My imports
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;

public class ClimbSafeFeatureSet1Controller {
	

	private static ClimbSafe climbSafe; // The system instance

  /**
   * @author karl rouhana
   * @param startDate - the input start date
   * @param nrWeeks - the input number of weeks
   * @param priceOfGuidePerWeek - the input price of guide per week
   * @throws InvalidInputException - throws invalid input exception if there's an error
   * 
   * The setup method will checks if its input has any error with the way they were given. 
   * If there's an error with the given input, it raises an InvalidInputException 
   * and if not, it will add them to the system just fine.

   * 
   */
	
	public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
	  
	
	  
	  checkValidDate(startDate);			//Check if the input date is valid using a private helper method
	  	  
	  
	  if(nrWeeks <= 0) 						//Checks if the number of climbing weeks is greater than or equal to zero
		  
		  throw new InvalidInputException("The number of climbing weeks must be greater than or equal to zero");		
	  
	  
	  if(priceOfGuidePerWeek <= 0) 			//Checks if the price of guide per week is greater than or equal to zero
		  
		  throw new InvalidInputException("The price of guide per week must be greater than or equal to zero");


	  climbSafe = ClimbSafeApplication.getClimbSafe();			//Get the current system
	  
	  
	  //If everything is fine, set the start date, Number of weeks and the Price of the guide per week to the system
	  
	  climbSafe.setStartDate(startDate);
	  climbSafe.setNrWeeks(nrWeeks);
	  climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
	  
  }

  
  
  /**
   * @author karl rouhana
   * @param email - the input email
   * 
   * The deleteMember method will delete a member if and only if the given email
   * is associated with a User, and that User is a Member.
   * If it's a Guide or the email does not exist in the system, 
   * don't do anything.
   */
  
	
  
  public static void deleteMember(String email) {
	  //check if member exists or no 
	  //check if instance of guide
	  //check if email is valid
	  //remove assignment with guide
	 

	  if(User.hasWithEmail(email)) { 					//Checks if a User exists with the input email
			
		  if(User.getWithEmail(email) instanceof Guide) {			//If a user exists with the email, check if that user is a guide
			 
			  return;												//If it's a guide, don't do anything and leave the guide in the system
		  }
		
		  
		  else {											//Else, the user is an instance of member, hence it's a member
	
			  
			  climbSafe = ClimbSafeApplication.getClimbSafe();			//Get the current system  
			
			  climbSafe.removeMember( (Member) Member.getWithEmail(email));	//Remove the member with the input email from the system
			
		
		}
	
		
	}
	  else {				//Else, the email does not exist in the system (no guides nor member)
		  return;			//Do nothing
	  }
	
	  
	  
	  
	  
	  
	  
	  
	  
  }
  
  
  /**
   * @author karl rouhana
   * @param email - the input email
   * 
   * The deleteGuide method will delete a guide if and only if the given email
   * is associated with a User, and that User is a guide.
   * If it's a Member or the email does not exist in the system, 
   * don't do anything.
   */


  public static void deleteGuide(String email) {
	 	 

	  
	  if(User.hasWithEmail(email)) { 					//Checks if a User exists with the input email
		
		  if(User.getWithEmail(email) instanceof Member) {		//If a user exists with the email, check if that user is a member
			 
			  return;											//If it's a member, don't do anything and leave the member in the system
		  }
		
		  
		  else {												//Else, the user is an instance of guide, hence it's a guide
				
			  climbSafe = ClimbSafeApplication.getClimbSafe();		//Get the current system  
				
				climbSafe.removeGuide( (Guide) Guide.getWithEmail(email));		//Remove the guide with the input email from the system
		}
	
		
	}
	  else {				//Else, the email does not exist in the system (no guides nor member)
		  					
		  return;			//Do nothing
	  }
	
	  
	  
	  
	  
  }

 
  
  /**
   * @author karl rouhana
   * @param date - input date
   * @throws InvalidInputException - throws invalid input exception if there's an error
   * 
   * The checkValidDate method is a helper method that will check if the given Start Date is valid.
   * 
   * 
   */
  
  
    private static void checkValidDate(Date date) throws InvalidInputException{
	 
	  int [] monthWith30Days = {3,5,8,10};					//These are the months with 30 days in them
	  
	  
	  if(date.getYear() < 2021) throw new InvalidInputException("Invalide date"); //If year before 2021
	  
	  if(date.getDate() < 1 || date.getDate() > 31) throw new InvalidInputException("Invalide date"); //If date is less than 0 or more than 31

	  if(date.getMonth() < 0 || date.getMonth() > 11 ) throw new InvalidInputException("Invalide date"); //If month is less than 0 or more than 11 (Date class takes it like this)
	
	  
	  //Checks for month that has 30 days in it has 31 days in it
	  for(int i=0; i < monthWith30Days.length; i++) {
		  
		  if(date.getMonth() == monthWith30Days[i]) {
			 
			  if(date.getDate() == 31) 
				  throw new InvalidInputException("Invalide date");
		  }
	  }
	  
	 
	  if(date.getMonth() == 1) {		//Checks if the Month is February
		  
		  
		  if(date.getDate() > 29) 			//Check if there's more than 29 days
			  throw new InvalidInputException("Invalide date");			

		  
		  
		  if(date.getDate() == 28 && date.getYear() % 4 == 0 ) //Check if leap year and has only 28 days  
			  throw new InvalidInputException("Invalide date"); 	

	  }
  }
  
  
  
  
  
  
  
  
  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {}

}
