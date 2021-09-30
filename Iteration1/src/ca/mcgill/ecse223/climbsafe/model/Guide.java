/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 28 "../../../../../ClimbSafe.ump"
public class Guide extends Climber
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Guide Associations
  private List<MountainClimb> climbs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Guide(String aEmail, String aPassword, ClimbSafeSystem aClimbSafeSystem, String aName, EmergencyContact aContact)
  {
    super(aEmail, aPassword, aClimbSafeSystem, aName, aContact);
    climbs = new ArrayList<MountainClimb>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public MountainClimb getClimb(int index)
  {
    MountainClimb aClimb = climbs.get(index);
    return aClimb;
  }

  public List<MountainClimb> getClimbs()
  {
    List<MountainClimb> newClimbs = Collections.unmodifiableList(climbs);
    return newClimbs;
  }

  public int numberOfClimbs()
  {
    int number = climbs.size();
    return number;
  }

  public boolean hasClimbs()
  {
    boolean has = climbs.size() > 0;
    return has;
  }

  public int indexOfClimb(MountainClimb aClimb)
  {
    int index = climbs.indexOf(aClimb);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbs()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addClimb(MountainClimb aClimb)
  {
    boolean wasAdded = false;
    if (climbs.contains(aClimb)) { return false; }
    Guide existingGuide = aClimb.getGuide();
    if (existingGuide == null)
    {
      aClimb.setGuide(this);
    }
    else if (!this.equals(existingGuide))
    {
      existingGuide.removeClimb(aClimb);
      addClimb(aClimb);
    }
    else
    {
      climbs.add(aClimb);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeClimb(MountainClimb aClimb)
  {
    boolean wasRemoved = false;
    if (climbs.contains(aClimb))
    {
      climbs.remove(aClimb);
      aClimb.setGuide(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClimbAt(MountainClimb aClimb, int index)
  {  
    boolean wasAdded = false;
    if(addClimb(aClimb))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbs()) { index = numberOfClimbs() - 1; }
      climbs.remove(aClimb);
      climbs.add(index, aClimb);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClimbAt(MountainClimb aClimb, int index)
  {
    boolean wasAdded = false;
    if(climbs.contains(aClimb))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbs()) { index = numberOfClimbs() - 1; }
      climbs.remove(aClimb);
      climbs.add(index, aClimb);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClimbAt(aClimb, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while( !climbs.isEmpty() )
    {
      climbs.get(0).setGuide(null);
    }
    super.delete();
  }

}