package ca.mcgill.ecse.climbsafe.persistence;

import java.sql.Date;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

public class ClimbSafePersistence {

  // Name of the file we're saving to
  private static String filename = "data.climbsafe";

  /**
   * Setter for the filename static variable
   * 
   * @author Wassim Jabbour
   * @param filename What we want to set filename to
   */
  public static void setFilename(String filename) {
    ClimbSafePersistence.filename = filename;
  }

  /**
   * Saves the state of the system in memory (Doesn't take arguments)
   * 
   * @author Wassim Jabbour
   * 
   */
  public static void save() {
    PersistenceObjectStream.setFilename(filename);
    save(ClimbSafeApplication.getClimbSafe());
  }

  /**
   * Saves the state of the system passed as input in memory
   * 
   * @author Wassim Jabbour
   * @param climbsafe The system we want to save in memory
   */
  public static void save(ClimbSafe climbsafe) {
    PersistenceObjectStream.setFilename(filename);
    PersistenceObjectStream.serialize(climbsafe);
  }

  /**
   * Loads the system saved in memory
   * 
   * @author Wassim Jabbour
   * @return Returns the instance of the system saved in memory
   */
  public static ClimbSafe load() {

    PersistenceObjectStream.setFilename(filename);
    var climbSafe = (ClimbSafe) PersistenceObjectStream.deserialize();

    // model cannot be loaded - create empty climbsafe
    if (climbSafe == null) {
      climbSafe = new ClimbSafe(new Date(0), 0, 0);
    } else {
      climbSafe.reinitialize();
    }
    return climbSafe;
  }


}
