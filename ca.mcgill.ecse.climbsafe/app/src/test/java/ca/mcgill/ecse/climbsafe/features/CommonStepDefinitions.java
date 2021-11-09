package ca.mcgill.ecse.climbsafe.features;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import io.cucumber.java.After;

public class CommonStepDefinitions {
  /**
   * Method used to delete the current climbsafe system instance before the next test. This is effective
   * for all scenarios in all feature files
   */
  @After
  public void tearDown() {
    ClimbSafeApplication.getClimbSafe().delete();
    ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe()); // To empty the saved persistence layer before the next test
  }

}
