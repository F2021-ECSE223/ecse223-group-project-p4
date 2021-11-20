package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.model.ClimbingPath.Difficulty;
import ca.mcgill.ecse.climbsafe.model.Review.Rating;

public class ExtraFeaturesController {

  public static void rateClimb(String memberEmail, String rating, String comment)
      throws InvalidInputException {

    Member member = (Member) checkMemberExists(memberEmail);

    member.setReview(new Review(stringToRating(rating), comment, member, member.getAssignment(),
        ClimbSafeApplication.getClimbSafe()));
  }

  public static void setClimbingPath(String memberEmail, String location)
      throws InvalidInputException {

    Member member = (Member) checkMemberExists(memberEmail);
    
    ClimbingPath climbingPath = checkClimbingPathExists(location);
    
    member.getAssignment().setClimbingPath(climbingPath);
  }

  private static Difficulty stringToDifficulty(String difficulty) {
    
  }
  private static Rating stringToRating(String rating) throws InvalidInputException {
    switch (rating) {

      case ("VeryPoor"):
        return Rating.VeryPoor;

      case ("Poor"):
        return Rating.Poor;

      case ("Neutral"):
        return Rating.Neutral;

      case ("Good"):
        return Rating.Good;
      case ("VeryGood"):
        return Rating.VeryGood;

      default:
        throw new InvalidInputException("Invalid Rating: " + rating);
    }
  }
  
  private static User checkMemberExists(String memberEmail) throws InvalidInputException {
    
    User member = User.getWithEmail(memberEmail);

    if (member == null || member instanceof Guide)
      throw new InvalidInputException(
          "Member with email address " + memberEmail + " does not exist");
    
    return member;
  }
  
  private static ClimbingPath checkClimbingPathExists(String location) throws InvalidInputException {
    
    ClimbingPath climbingPath = ClimbingPath.getWithLocation(location);
    
    if(climbingPath == null)
      throw new InvalidInputException(
          "Climbing path with location " + location + " does not exist");
    
    return climbingPath;
  }



}
