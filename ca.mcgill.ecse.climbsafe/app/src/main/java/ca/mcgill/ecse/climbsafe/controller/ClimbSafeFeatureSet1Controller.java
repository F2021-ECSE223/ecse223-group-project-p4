package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;

//My imports
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;

public class ClimbSafeFeatureSet1Controller {
	

	private static ClimbSafe climbSafe; // The system instance

  
	
	public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
	  
	
	  
	  checkValidDate(startDate);			//Check if the input date is valid using the private helper method
	  	  
	  
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

 
    private static void checkValidDate(Date startDate) throws InvalidInputException{
	 
	  int [] monthWith30Days = {3,5,8,10};					//These are the months with 30 days in them
	  
	  
	  if(startDate.getYear() < 2021) throw new InvalidInputException("Invalide date"); //If year before 2021
	  
	  if(startDate.getDate() < 1 || startDate.getDate() > 31) throw new InvalidInputException("Invalide date"); //If date is less than 0 or more than 31

	  if(startDate.getMonth() < 0 || startDate.getMonth() > 11 ) throw new InvalidInputException("Invalide date"); //If month is less than 0 or more than 11 (Date class takes it like this)
	
	  
	  //Checks for month that has 30 days in it has 31 days in it
	  for(int i=0; i < monthWith30Days.length; i++) {
		  
		  if(startDate.getMonth() == monthWith30Days[i]) {
			 
			  if(startDate.getDate() == 31) 
				  throw new InvalidInputException("Invalide date");
		  }
	  }
	  
	 
	  if(startDate.getMonth() == 1) {		//Checks if the Month is February
		  
		  
		  if(startDate.getDate() > 29) 			//Check if there's more than 29 days
			  throw new InvalidInputException("Invalide date");			

		  
		  
		  if(startDate.getDate() == 28 && startDate.getYear() % 4 == 0 ) //Check if leap year and has only 28 days  
			  throw new InvalidInputException("Invalide date"); 	

	  }
  }
  
  
  
  
  
  
  
  
  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {}

}
