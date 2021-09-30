/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;

// line 67 "../../../../../ClimbSafe.ump"
public class MountainClimb
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MountainClimb Attributes
  private int startWeek;
  private int endWeek;

  //MountainClimb Associations
  private ClimbSafeSystem climbSafeSystem;
  private Member member;
  private Guide guide;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MountainClimb(int aStartWeek, int aEndWeek, ClimbSafeSystem aClimbSafeSystem, Member aMember)
  {
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    boolean didAddClimbSafeSystem = setClimbSafeSystem(aClimbSafeSystem);
    if (!didAddClimbSafeSystem)
    {
      throw new RuntimeException("Unable to create climb due to climbSafeSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create climb due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
  }
  /* Code from template association_GetOne */
  public ClimbSafeSystem getClimbSafeSystem()
  {
    return climbSafeSystem;
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
  /* Code from template association_SetOneToMany */
  public boolean setClimbSafeSystem(ClimbSafeSystem aClimbSafeSystem)
  {
    boolean wasSet = false;
    if (aClimbSafeSystem == null)
    {
      return wasSet;
    }

    ClimbSafeSystem existingClimbSafeSystem = climbSafeSystem;
    climbSafeSystem = aClimbSafeSystem;
    if (existingClimbSafeSystem != null && !existingClimbSafeSystem.equals(aClimbSafeSystem))
    {
      existingClimbSafeSystem.removeClimb(this);
    }
    climbSafeSystem.addClimb(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (aNewMember == null)
    {
      //Unable to setMember to null, as climb must always be associated to a member
      return wasSet;
    }
    
    MountainClimb existingClimb = aNewMember.getClimb();
    if (existingClimb != null && !equals(existingClimb))
    {
      //Unable to setMember, the current member already has a climb, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Member anOldMember = member;
    member = aNewMember;
    member.setClimb(this);

    if (anOldMember != null)
    {
      anOldMember.setClimb(null);
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
      existingGuide.removeClimb(this);
    }
    if (aGuide != null)
    {
      aGuide.addClimb(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ClimbSafeSystem placeholderClimbSafeSystem = climbSafeSystem;
    this.climbSafeSystem = null;
    if(placeholderClimbSafeSystem != null)
    {
      placeholderClimbSafeSystem.removeClimb(this);
    }
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.setClimb(null);
    }
    if (guide != null)
    {
      Guide placeholderGuide = guide;
      this.guide = null;
      placeholderGuide.removeClimb(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafeSystem = "+(getClimbSafeSystem()!=null?Integer.toHexString(System.identityHashCode(getClimbSafeSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null");
  }
}