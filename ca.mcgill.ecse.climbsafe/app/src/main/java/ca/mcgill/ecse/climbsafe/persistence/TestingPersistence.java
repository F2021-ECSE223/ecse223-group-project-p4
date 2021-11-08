package ca.mcgill.ecse.climbsafe.persistence;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;

public class TestingPersistence {

  public static void main(String[] args) {
    
    ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
    
    try {
      ClimbSafeFeatureSet3Controller.registerGuide("wassim.jabbour@mail.mcgill.ca", "pass", "wassim", "342423423");
    } catch (InvalidInputException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    climbSafe.delete();
    
    climbSafe = ClimbSafeApplication.getClimbSafe();
    
    System.out.println(User.hasWithEmail("wassim.jabbour@mail.mcgill.ca"));
    
  }
}
