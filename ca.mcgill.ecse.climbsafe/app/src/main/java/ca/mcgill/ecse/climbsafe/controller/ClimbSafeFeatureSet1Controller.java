package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.util.List;

//My imports
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;

public class ClimbSafeFeatureSet1Controller {
	

	private static ClimbSafe climbSafe; // The system instance

  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
	  
	
	  
	  checkValidDate(startDate);
	  	  
	  if(nrWeeks <= 0) throw new InvalidInputException("The price of guide per week must be greater than or equal to zero");
	  if(priceOfGuidePerWeek <= 0) throw new InvalidInputException("The price of guide per week must be greater than or equal to zero");


	  climbSafe = ClimbSafeApplication.getClimbSafe();
	  
	  climbSafe.setNrWeeks(nrWeeks);
	  climbSafe.setStartDate(startDate);
	  climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
	  
  }

  
  
  
  
  
  public static void deleteMember(String email) {
	  //check if member exists or no 
	  //check if instance of guide
	  //check if email is valid
	  
	  
	  
  }

  public static void deleteGuide(String email) {
	  
	  
	  
  }

 
  //TODO isValidEmail() 
  
    private static void checkValidDate(Date startDate) throws InvalidInputException{
	 
	  int [] monthWith30Days = {3,5,8,10};
	  
	  
	  if(startDate.getYear() < 2021) throw new InvalidInputException("Invalide date"); //If year before 2021
	  
	  if(startDate.getDate() < 1 || startDate.getDate() > 31) throw new InvalidInputException("Invalide date"); //If date is less than 0 or more than 31

	  if(startDate.getMonth() < 0 || startDate.getMonth() > 11 ) throw new InvalidInputException("Invalide date"); //If month is less than 0 or more than 11 (Date class takes it like this)
	
	  
	  //Checks for month with 30 days has 31 days in it
	  for(int i=0; i < monthWith30Days.length; i++) {
		  if(startDate.getMonth() == monthWith30Days[i]) {
			  if(startDate.getDate() == 30) throw new InvalidInputException("Invalide date");
		  }
	  }
	  
	  //Checks for February
	  if(startDate.getMonth() == 1) {
		  
		  if(startDate.getDate() > 29) throw new InvalidInputException("Invalide date"); // If has more than 29 days

		  if(startDate.getDate() == 28 && startDate.getYear() % 4 == 0 ) throw new InvalidInputException("Invalide date"); //If leap year and has 28 days error 

	  }
  }
  
  
  
  
  
  
  
  
  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {}

}
