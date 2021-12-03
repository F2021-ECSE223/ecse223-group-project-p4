package ca.mcgill.ecse.climbsafe.features;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CommonStepDefinitions {
  /**
   * Method used to delete the current climbsafe system instance before the next test. This is
   * effective for all scenarios in all feature files
   */

  @After
  public void tearDown() {
    ClimbSafeApplication.getClimbSafe().delete();
    ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
  }

  /**
   * Since the demo file creator modifies the system, so the first test comes out as wrong
   * 
   * @author Wassim Jabbour
   */
  @Before
  public void setupAll() {
    ClimbSafeApplication.getClimbSafe().delete();
    ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
  }

}
