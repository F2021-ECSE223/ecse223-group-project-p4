package ca.mcgill.ecse.climbsafe.controller;


import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.model.*;

public class AssignmentController {

  /**
   * @author Adam Kazma, Ralph Nassar
   * @throws InvalidInputException
   */
  public static void initiateAssignment() throws InvalidInputException {
    // If banned throw exception
  }

  /**
   * @author Tinetendo Makata
   * @param memberEmail
   * @param authorizationCode
   * @throws InvalidInputException
   */
  public static void payForTrip(String memberEmail, String authorizationCode)
      throws InvalidInputException {
    // If banned throw exception
  }

  /**
   * @author Wassim Jabbour
   * @param weekNr
   * @throws InvalidInputException
   */
  public static void startTrips(int weekNr) throws InvalidInputException {

    for (Assignment assignment : ClimbSafeApplication.getClimbSafe().getAssignments()) {
      if (assignment.getStartWeek() == weekNr) {
        try {
          assignment.start();
        } catch (RuntimeException e) {
          throw new InvalidInputException(e.getMessage());
        }
      }
    }

  }

  /**
   * @author Matthieu Hakim
   * @param memberEmail
   * @throws InvalidInputException
   */
  public static void finishTrip(String memberEmail) throws InvalidInputException {

    Member member = (Member) Member.getWithEmail(memberEmail);

    if (member.getMemberStateFullName().equals("Banned")) {

      throw new InvalidInputException("Cannot finish the trip due to a ban");
    } else {

      try {
        member.getAssignment().finish();
      } catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }


    }

  }

  /**
   * @author Karl Rouhana
   * @param memberEmail
   * @throws InvalidInputException
   */
  public static void cancelTrip(String memberEmail) throws InvalidInputException {
    Member member = (Member) Member.getWithEmail(memberEmail);

    if (member.getMemberStateFullName().equals("Banned")) { // Check if the member is banned

      throw new InvalidInputException("Cannot cancel the trip due to a ban"); // Throw exception
                                                                              // that the member is
                                                                              // banned from the
                                                                              // system
    } else {

      try {
        member.getAssignment().cancel(); // Try to cancel the assignment
      } catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage()); // Raise error if it doesn't work
      }


    }
  }

}
