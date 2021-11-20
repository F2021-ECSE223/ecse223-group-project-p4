/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;

// line 108 "../../../../../ClimbSafePersistence.ump"
// line 1 "../../../../../AssignmentProcess.ump"
// line 95 "../../../../../ClimbSafe.ump"
public class Assignment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private String authorizationCode;
  private int refundPercentage;
  private int startWeek;
  private int endWeek;

  //Assignment State Machines
  public enum AssignmentState { Assigned, Paid, Started, Finished, Cancelled, Banned }
  private AssignmentState assignmentState;

  //Assignment Associations
  private Member member;
  private Guide guide;
  private Hotel hotel;
  private ClimbingPath climbingPath;
  private ClimbingGroup climbingGroup;
  private ClimbSafe climbSafe;
  private Review review;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(int aStartWeek, int aEndWeek, Member aMember, ClimbSafe aClimbSafe)
  {
    authorizationCode = null;
    refundPercentage = 0;
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create assignment due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create assignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setAssignmentState(AssignmentState.Assigned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAuthorizationCode(String aAuthorizationCode)
  {
    boolean wasSet = false;
    authorizationCode = aAuthorizationCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setRefundPercentage(int aRefundPercentage)
  {
    boolean wasSet = false;
    refundPercentage = aRefundPercentage;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(int aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  public String getAuthorizationCode()
  {
    return authorizationCode;
  }

  public int getRefundPercentage()
  {
    return refundPercentage;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
  }

  public String getAssignmentStateFullName()
  {
    String answer = assignmentState.toString();
    return answer;
  }

  public AssignmentState getAssignmentState()
  {
    return assignmentState;
  }

  public boolean pay(String authCode)
  {
    boolean wasEventProcessed = false;
    
    AssignmentState aAssignmentState = assignmentState;
    switch (aAssignmentState)
    {
      case Assigned:
        if (isValidCode(authCode))
        {
        // line 9 "../../../../../AssignmentProcess.ump"
          setAuthorizationCode(authCode.trim());
          setAssignmentState(AssignmentState.Paid);
          wasEventProcessed = true;
          break;
        }
        if (!(isValidCode(authCode)))
        {
        // line 10 "../../../../../AssignmentProcess.ump"
          throwException("Invalid authorization code");
          setAssignmentState(AssignmentState.Assigned);
          wasEventProcessed = true;
          break;
        }
        break;
      case Paid:
        // line 20 "../../../../../AssignmentProcess.ump"
        throwException("Trip has already been paid for");
        setAssignmentState(AssignmentState.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        // line 30 "../../../../../AssignmentProcess.ump"
        throwException("Trip has already been paid for");
        setAssignmentState(AssignmentState.Started);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 40 "../../../../../AssignmentProcess.ump"
        throwException("Cannot pay for a trip which has finished");
        setAssignmentState(AssignmentState.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 50 "../../../../../AssignmentProcess.ump"
        throwException("Cannot pay for a trip which has been cancelled");
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 60 "../../../../../AssignmentProcess.ump"
        throwException("Cannot pay for the trip due to a ban");
        setAssignmentState(AssignmentState.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean start()
  {
    boolean wasEventProcessed = false;
    
    AssignmentState aAssignmentState = assignmentState;
    switch (aAssignmentState)
    {
      case Assigned:
        setAssignmentState(AssignmentState.Banned);
        wasEventProcessed = true;
        break;
      case Paid:
        setAssignmentState(AssignmentState.Started);
        wasEventProcessed = true;
        break;
      case Started:
        // line 32 "../../../../../AssignmentProcess.ump"
        throwException("Cannot start a trip which has started");
        setAssignmentState(AssignmentState.Started);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 42 "../../../../../AssignmentProcess.ump"
        throwException("Cannot start a trip which has finished");
        setAssignmentState(AssignmentState.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 52 "../../../../../AssignmentProcess.ump"
        throwException("Cannot start a trip which has been cancelled");
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 62 "../../../../../AssignmentProcess.ump"
        throwException("Cannot start the trip due to a ban");
        setAssignmentState(AssignmentState.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finish()
  {
    boolean wasEventProcessed = false;
    
    AssignmentState aAssignmentState = assignmentState;
    switch (aAssignmentState)
    {
      case Assigned:
        // line 14 "../../../../../AssignmentProcess.ump"
        throwException("Cannot finish a trip which has not started");
        setAssignmentState(AssignmentState.Assigned);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 24 "../../../../../AssignmentProcess.ump"
        throwException("Cannot finish a trip which has not started");
        setAssignmentState(AssignmentState.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        setAssignmentState(AssignmentState.Finished);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 44 "../../../../../AssignmentProcess.ump"
        throwException("Cannot finish a trip which has finished");
        setAssignmentState(AssignmentState.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 54 "../../../../../AssignmentProcess.ump"
        throwException("Cannot finish a trip which has been cancelled");
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 66 "../../../../../AssignmentProcess.ump"
        throwException("Cannot finish the trip due to a ban");
        setAssignmentState(AssignmentState.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    AssignmentState aAssignmentState = assignmentState;
    switch (aAssignmentState)
    {
      case Assigned:
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 26 "../../../../../AssignmentProcess.ump"
        setRefundPercentage(50);
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        // line 36 "../../../../../AssignmentProcess.ump"
        setRefundPercentage(10);
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 46 "../../../../../AssignmentProcess.ump"
        throwException("Cannot cancel a trip which has finished");
        setAssignmentState(AssignmentState.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 56 "../../../../../AssignmentProcess.ump"
        throwException("Cannot cancel a trip which has been cancelled");
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 64 "../../../../../AssignmentProcess.ump"
        throwException("Cannot cancel the trip due to a ban");
        setAssignmentState(AssignmentState.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setAssignmentState(AssignmentState aAssignmentState)
  {
    assignmentState = aAssignmentState;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
  }

  public boolean hasGuide()
  {
    boolean has = guide != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }

  public boolean hasHotel()
  {
    boolean has = hotel != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ClimbingPath getClimbingPath()
  {
    return climbingPath;
  }

  public boolean hasClimbingPath()
  {
    boolean has = climbingPath != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ClimbingGroup getClimbingGroup()
  {
    return climbingGroup;
  }

  public boolean hasClimbingGroup()
  {
    boolean has = climbingGroup != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_GetOne */
  public Review getReview()
  {
    return review;
  }

  public boolean hasReview()
  {
    boolean has = review != null;
    return has;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (aNewMember == null)
    {
      //Unable to setMember to null, as assignment must always be associated to a member
      return wasSet;
    }
    
    Assignment existingAssignment = aNewMember.getAssignment();
    if (existingAssignment != null && !equals(existingAssignment))
    {
      //Unable to setMember, the current member already has a assignment, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Member anOldMember = member;
    member = aNewMember;
    member.setAssignment(this);

    if (anOldMember != null)
    {
      anOldMember.setAssignment(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setGuide(Guide aGuide)
  {
    boolean wasSet = false;
    Guide existingGuide = guide;
    guide = aGuide;
    if (existingGuide != null && !existingGuide.equals(aGuide))
    {
      existingGuide.removeAssignment(this);
    }
    if (aGuide != null)
    {
      aGuide.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeAssignment(this);
    }
    if (aHotel != null)
    {
      aHotel.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setClimbingPath(ClimbingPath aClimbingPath)
  {
    boolean wasSet = false;
    ClimbingPath existingClimbingPath = climbingPath;
    climbingPath = aClimbingPath;
    if (existingClimbingPath != null && !existingClimbingPath.equals(aClimbingPath))
    {
      existingClimbingPath.removeAssignment(this);
    }
    if (aClimbingPath != null)
    {
      aClimbingPath.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMandatoryMany */
  public boolean setClimbingGroup(ClimbingGroup aClimbingGroup)
  {
    //
    // This source of this source generation is association_SetOptionalOneToMandatoryMany.jet
    // This set file assumes the generation of a maximumNumberOfXXX method does not exist because 
    // it's not required (No upper bound)
    //   
    boolean wasSet = false;
    ClimbingGroup existingClimbingGroup = climbingGroup;

    if (existingClimbingGroup == null)
    {
      if (aClimbingGroup != null)
      {
        if (aClimbingGroup.addAssignment(this))
        {
          existingClimbingGroup = aClimbingGroup;
          wasSet = true;
        }
      }
    } 
    else if (existingClimbingGroup != null)
    {
      if (aClimbingGroup == null)
      {
        if (existingClimbingGroup.minimumNumberOfAssignments() < existingClimbingGroup.numberOfAssignments())
        {
          existingClimbingGroup.removeAssignment(this);
          existingClimbingGroup = aClimbingGroup;  // aClimbingGroup == null
          wasSet = true;
        }
      } 
      else
      {
        if (existingClimbingGroup.minimumNumberOfAssignments() < existingClimbingGroup.numberOfAssignments())
        {
          existingClimbingGroup.removeAssignment(this);
          aClimbingGroup.addAssignment(this);
          existingClimbingGroup = aClimbingGroup;
          wasSet = true;
        }
      }
    }
    if (wasSet)
    {
      climbingGroup = existingClimbingGroup;
    }
    return wasSet;
  }
    /* Code from template association_SetOneToMany */
  public boolean setClimbSafe(ClimbSafe aClimbSafe)
  {
    boolean wasSet = false;
    if (aClimbSafe == null)
    {
      return wasSet;
    }

    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = aClimbSafe;
    if (existingClimbSafe != null && !existingClimbSafe.equals(aClimbSafe))
    {
      existingClimbSafe.removeAssignment(this);
    }
    climbSafe.addAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setReview(Review aNewReview)
  {
    boolean wasSet = false;
    if (review != null && !review.equals(aNewReview) && equals(review.getAssignment()))
    {
      //Unable to setReview, as existing review would become an orphan
      return wasSet;
    }

    review = aNewReview;
    Assignment anOldAssignment = aNewReview != null ? aNewReview.getAssignment() : null;

    if (!this.equals(anOldAssignment))
    {
      if (anOldAssignment != null)
      {
        anOldAssignment.review = null;
      }
      if (review != null)
      {
        review.setAssignment(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.setAssignment(null);
    }
    if (guide != null)
    {
      Guide placeholderGuide = guide;
      this.guide = null;
      placeholderGuide.removeAssignment(this);
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeAssignment(this);
    }
    if (climbingPath != null)
    {
      ClimbingPath placeholderClimbingPath = climbingPath;
      this.climbingPath = null;
      placeholderClimbingPath.removeAssignment(this);
    }
    if (climbingGroup != null)
    {
      if (climbingGroup.numberOfAssignments() <= 2)
      {
        climbingGroup.delete();
      }
      else
      {
        ClimbingGroup placeholderClimbingGroup = climbingGroup;
        this.climbingGroup = null;
        placeholderClimbingGroup.removeAssignment(this);
      }
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeAssignment(this);
    }
    Review existingReview = review;
    review = null;
    if (existingReview != null)
    {
      existingReview.delete();
    }
  }

  // line 71 "../../../../../AssignmentProcess.ump"
   private boolean isValidCode(String authCode){
    return !(authCode == null || authCode.trim().isEmpty());
  }

  // line 75 "../../../../../AssignmentProcess.ump"
   private void throwException(String error){
    throw new RuntimeException(error);
  }

  // line 79 "../../../../../AssignmentProcess.ump"
   public void setState(AssignmentState state){
    setAssignmentState(state);
  }


  public String toString()
  {
    return super.toString() + "["+
            "authorizationCode" + ":" + getAuthorizationCode()+ "," +
            "refundPercentage" + ":" + getRefundPercentage()+ "," +
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbingPath = "+(getClimbingPath()!=null?Integer.toHexString(System.identityHashCode(getClimbingPath())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbingGroup = "+(getClimbingGroup()!=null?Integer.toHexString(System.identityHashCode(getClimbingGroup())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "review = "+(getReview()!=null?Integer.toHexString(System.identityHashCode(getReview())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 111 "../../../../../ClimbSafePersistence.ump"
  private static final long serialVersionUID = 11L ;

  
}