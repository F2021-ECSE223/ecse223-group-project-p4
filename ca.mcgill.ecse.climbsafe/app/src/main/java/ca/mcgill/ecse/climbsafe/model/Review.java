/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;

// line 3 "../../../../../ExtraFeatures.ump"
public class Review implements Serializable
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Rating { VeryPoor, Poor, Neutral, Good, VeryGood }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Review Attributes
  private Rating rating;
  private String comment;

  //Review Associations
  private Member member;
  private Assignment assignment;
  private ClimbSafe climbSafe;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetMember;
  private boolean canSetAssignment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Review(Rating aRating, String aComment, Member aMember, Assignment aAssignment, ClimbSafe aClimbSafe)
  {
    cachedHashCode = -1;
    canSetMember = true;
    canSetAssignment = true;
    rating = aRating;
    comment = aComment;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create review due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAssignment = setAssignment(aAssignment);
    if (!didAddAssignment)
    {
      throw new RuntimeException("Unable to create review due to assignment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create review due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRating(Rating aRating)
  {
    boolean wasSet = false;
    rating = aRating;
    wasSet = true;
    return wasSet;
  }

  public boolean setComment(String aComment)
  {
    boolean wasSet = false;
    comment = aComment;
    wasSet = true;
    return wasSet;
  }

  public Rating getRating()
  {
    return rating;
  }

  public String getComment()
  {
    return comment;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public Assignment getAssignment()
  {
    return assignment;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (!canSetMember) { return false; }
    if (aNewMember == null)
    {
      //Unable to setMember to null, as review must always be associated to a member
      return wasSet;
    }
    
    Review existingReview = aNewMember.getReview();
    if (existingReview != null && !equals(existingReview))
    {
      //Unable to setMember, the current member already has a review, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Member anOldMember = member;
    member = aNewMember;
    member.setReview(this);

    if (anOldMember != null)
    {
      anOldMember.setReview(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setAssignment(Assignment aNewAssignment)
  {
    boolean wasSet = false;
    if (!canSetAssignment) { return false; }
    if (aNewAssignment == null)
    {
      //Unable to setAssignment to null, as review must always be associated to a assignment
      return wasSet;
    }
    
    Review existingReview = aNewAssignment.getReview();
    if (existingReview != null && !equals(existingReview))
    {
      //Unable to setAssignment, the current assignment already has a review, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Assignment anOldAssignment = assignment;
    assignment = aNewAssignment;
    assignment.setReview(this);

    if (anOldAssignment != null)
    {
      anOldAssignment.setReview(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToManyAssociationClass */
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
      existingClimbSafe.removeReview(this);
    }
    if (!climbSafe.addReview(this))
    {
      climbSafe = existingClimbSafe;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    Review compareTo = (Review)obj;
  
    if (getMember() == null && compareTo.getMember() != null)
    {
      return false;
    }
    else if (getMember() != null && !getMember().equals(compareTo.getMember()))
    {
      return false;
    }

    if (getAssignment() == null && compareTo.getAssignment() != null)
    {
      return false;
    }
    else if (getAssignment() != null && !getAssignment().equals(compareTo.getAssignment()))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (getMember() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getMember().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getAssignment() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getAssignment().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetMember = false;
    canSetAssignment = false;
    return cachedHashCode;
  }

  public void delete()
  {
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.setReview(null);
    }
    Assignment existingAssignment = assignment;
    assignment = null;
    if (existingAssignment != null)
    {
      existingAssignment.setReview(null);
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeReview(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "comment" + ":" + getComment()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "rating" + "=" + (getRating() != null ? !getRating().equals(this)  ? getRating().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignment = "+(getAssignment()!=null?Integer.toHexString(System.identityHashCode(getAssignment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 15 ../../../../../ExtraFeatures.ump
  private static final long serialVersionUID = 14L;
  
}