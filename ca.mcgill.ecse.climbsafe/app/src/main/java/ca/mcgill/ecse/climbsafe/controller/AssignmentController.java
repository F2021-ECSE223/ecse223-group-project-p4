package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class AssignmentController {

  /**
   * @author Adam Kazma, Ralph Nassar
   * @throws InvalidInputException
   */
  public static void initiateAssignment() throws InvalidInputException {

    // Extracting the system instance into a variable
    ClimbSafe system = ClimbSafeApplication.getClimbSafe();

    // If the assignments were already initialized, throw an exception
    if (system.getAssignments().size() > 0)
      throw new InvalidInputException("Assignments were already initiated for the current season");

    // Else initiate all assignments
    if (system.getGuides().size() == 0) {

      for (Member member : system.getMembers()) {
        if (!member.getGuideRequired()) {
          system.addAssignment(new Assignment(1, member.getNrWeeks(), member, system));
        }
      }
    }

    else {
      for (Guide guide : system.getGuides()) {
        int weeksTaken = 0;
        for (Member member : system.getMembers()) {
          if (member.getAssignment() == null) {
            if (member.getGuideRequired()) {
              if (member.getNrWeeks() <= system.getNrWeeks() - weeksTaken) {
                Assignment assignment = new Assignment(weeksTaken + 1,
                    weeksTaken + member.getNrWeeks(), member, system);
                assignment.setGuide(guide);
                system.addAssignment(assignment);
                weeksTaken += member.getNrWeeks();
              } else {
                if (system.getGuides().indexOf(guide) == system.getGuides().size() - 1) {
                  throw new InvalidInputException(
                      "Assignments could not be completed for all members");
                }
              }
            } else {
              system.addAssignment(new Assignment(1, member.getNrWeeks(), member, system));
            }
          }
        }
      }
    }

    ClimbSafePersistence.save(system);
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

  /**
   * @author Wassim Jabbour
   * @param weekNr
   * @throws InvalidInputException
   */
  public static void startTrips(int weekNr) throws InvalidInputException {


    if (weekNr < 1) {
      throw new InvalidInputException("Week number must be at least 1");
    }
    if (weekNr > ClimbSafeApplication.getClimbSafe().getNrWeeks()) {
      throw new InvalidInputException(
          "Week number cannot be bigger than the number of weeks in the season");

    }

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
      member.getAssignment().finish(); // Try to finish assignment
      ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe()); // Save in persistence
    } catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage()); // Raise error if cannot finsih assignment
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
