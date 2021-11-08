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

    Member member = (Member) User.getWithEmail(memberEmail);

    if (member == null)
      throw new InvalidInputException(
          "Member with email address " + memberEmail + " does not exist");

    if (member.getMemberStateFullName().equals("Banned")) {

      throw new InvalidInputException("Cannot pay for the trip due to a ban");
    } else {

      try {
        member.getAssignment().pay(authorizationCode);
        ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
      } catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }


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

        if (assignment.getMember().getMemberStateFullName().equals("Banned")) {
          throw new InvalidInputException("Cannot start the trip due to a ban");
        } else {
          try {
            assignment.start();
            ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
          } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
          }
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


    if (member == null || !(member instanceof Member)) {
      throw new InvalidInputException(
          "Member with email address " + memberEmail + " does not exist");
    }

    if (member.getMemberStateFullName().equals("Banned")) {

      throw new InvalidInputException("Cannot finish the trip due to a ban");
    } else {

      try {
        member.getAssignment().finish();
        ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
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

    // Checks if the member exists
    if (member == null)
      throw new InvalidInputException(
          "Member with email address " + memberEmail + " does not exist");
    if (member.getMemberStateFullName().equals("Banned")) { // Check if the member is banned

      // Throw exception that the member is banned from the system
      throw new InvalidInputException("Cannot cancel the trip due to a ban");
    } else {

      try {
        member.getAssignment().cancel(); // Try to cancel the assignment
        ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
      } catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage()); // Raise error if it doesn't work
      }


    }

  }
}
