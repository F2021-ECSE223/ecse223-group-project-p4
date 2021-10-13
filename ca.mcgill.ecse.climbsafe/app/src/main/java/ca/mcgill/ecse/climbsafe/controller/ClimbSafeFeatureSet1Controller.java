package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;

//import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

public class ClimbSafeFeatureSet1Controller {
	

	private static ClimbSafe climbSafe; // The system instance

  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
	  
	 
	  
	  
	  
	  // climbSafe = ClimbSafeApplication.getClimbSafe();
	  
	  
	  climbSafe.setNrWeeks(nrWeeks);
	  climbSafe.setStartDate(startDate);
	  climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
	  
	  
	  
  }

  
  
  
  
  
  public static void deleteMember(String email) {}

  public static void deleteGuide(String email) {}

 
  //TODO isValidEmail() 
  
  //TODO 
  private boolean checkValidDate(Date startDate) {
	  //TODO make it return error
	  
	  
	  return false;
  }
  
  
  
  
  
  
  
  
  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {}

}
