package ca.mcgill.ecse.climbsafe.controller;


import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class AssignmentController {

  /**
   * @author Adam Kazma, Ralph Nassar
   * @throws InvalidInputException
   */
  public static void initiateAssignment() throws InvalidInputException {

    for (Guide guide : ClimbSafeApplication.getClimbSafe().getGuides()) {
      int weeksTaken = 0;
      for (Member member : ClimbSafeApplication.getClimbSafe().getMembers()) {
        if (member.getAssignment() == null) {
          if (member.getGuideRequired()) {
            if (member.getNrWeeks() <= ClimbSafeApplication.getClimbSafe().getNrWeeks()
                - weeksTaken) {
              Assignment assignment = new Assignment(weeksTaken + 1,
                  weeksTaken + member.getNrWeeks(), member, ClimbSafeApplication.getClimbSafe());
              assignment.setGuide(guide);
              ClimbSafeApplication.getClimbSafe().addAssignment(assignment);
              weeksTaken += member.getNrWeeks();
            } else {
              if (ClimbSafeApplication.getClimbSafe().getGuides()
                  .indexOf(guide) == ClimbSafeApplication.getClimbSafe().getGuides().size() - 1) {
                throw new InvalidInputException(
                    "Assignments could not be completed for all members");
              }
            }
          } else {
            ClimbSafeApplication.getClimbSafe().addAssignment(new Assignment(1, member.getNrWeeks(),
                member, ClimbSafeApplication.getClimbSafe()));
          }
        }
      }

    }
    ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
  }

  /**
   * @author Tinetendo Makata
   * @param memberEmail
   * @param authorizationCode
   * @throws InvalidInputException
   */
  public static void payForTrip(String memberEmail, String authorizationCode)
      throws InvalidInputException {


    Member member = checkIfMemberExists(memberEmail);

    try {
      member.getAssignment().pay(authorizationCode);
      ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
    } catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }

  }
  // If banned throw exception

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
          ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
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

    Member member = checkIfMemberExists(memberEmail);

    try {
      member.getAssignment().finish();
      ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
    } catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }

  }

  /**
   * @author Karl Rouhana
   * @param memberEmail
   * @throws InvalidInputException
   */
  public static void cancelTrip(String memberEmail) throws InvalidInputException {

    Member member = checkIfMemberExists(memberEmail);

    try {
      member.getAssignment().cancel(); // Try to cancel the assignment
      ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
    } catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage()); // Raise error if it doesn't work
    }

  }

  /**
   * This private helper method checks if the member with the input email exists, if not thrown an
   * exception
   * 
   * @author Karl Rouhana - Matthieu Hakim - Tinetendo Makata
   * @param memberEmail - The member's email
   * @return Member Returns the member with the passed email address
   * @throws InvalidInputException Thrown if no member with the given email exist
   */

  private static Member checkIfMemberExists(String memberEmail) throws InvalidInputException {

    Member member = (Member) Member.getWithEmail(memberEmail);

    if (member == null)
      throw new InvalidInputException(
          "Member with email address " + memberEmail + " does not exist");

    return member;


  }
}
