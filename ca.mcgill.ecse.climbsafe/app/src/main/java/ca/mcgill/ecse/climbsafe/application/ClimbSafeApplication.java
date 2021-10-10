/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ca.mcgill.ecse.climbsafe.application;

//Imports
import java.sql.Date;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

public class ClimbSafeApplication {
	
	private static ClimbSafe climbSafe; // The system instance
	
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new ClimbSafeApplication().getGreeting());
    }
    
    public static ClimbSafe getClimbSafe() {
        if (climbSafe == null) {
          // these attributes are default, you should set them later with the setters
          climbSafe = new ClimbSafe(new Date(0), 0, 0);
        }
        
        return climbSafe;
      }
}
