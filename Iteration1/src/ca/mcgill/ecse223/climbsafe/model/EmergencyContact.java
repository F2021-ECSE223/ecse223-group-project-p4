/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 41 "../../../../../ClimbSafe.ump"
public class EmergencyContact
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EmergencyContact Attributes
  private String name;
  private int number;
  private String email;

  //EmergencyContact Associations
  private List<Climber> climbers;
  private ClimbSafeSystem climbSafeSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EmergencyContact(String aName, int aNumber, String aEmail, ClimbSafeSystem aClimbSafeSystem)
  {
    name = aName;
    number = aNumber;
    email = aEmail;
    climbers = new ArrayList<Climber>();
    boolean didAddClimbSafeSystem = setClimbSafeSystem(aClimbSafeSystem);
    if (!didAddClimbSafeSystem)
    {
      throw new RuntimeException("Unable to create emergencyContact due to climbSafeSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getNumber()
  {
    return number;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template association_GetMany */
  public Climber getClimber(int index)
  {
    Climber aClimber = climbers.get(index);
    return aClimber;
  }

  public List<Climber> getClimbers()
  {
    List<Climber> newClimbers = Collections.unmodifiableList(climbers);
    return newClimbers;
  }

  public int numberOfClimbers()
  {
    int number = climbers.size();
    return number;
  }

  public boolean hasClimbers()
  {
    boolean has = climbers.size() > 0;
    return has;
  }

  public int indexOfClimber(Climber aClimber)
  {
    int index = climbers.indexOf(aClimber);
    return index;
  }
  /* Code from template association_GetOne */
  public ClimbSafeSystem getClimbSafeSystem()
  {
    return climbSafeSystem;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addClimber(Climber aClimber)
  {
    boolean wasAdded = false;
    if (climbers.contains(aClimber)) { return false; }
    EmergencyContact existingContact = aClimber.getContact();
    boolean isNewContact = existingContact != null && !this.equals(existingContact);
    if (isNewContact)
    {
      aClimber.setContact(this);
    }
    else
    {
      climbers.add(aClimber);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeClimber(Climber aClimber)
  {
    boolean wasRemoved = false;
    //Unable to remove aClimber, as it must always have a contact
    if (!this.equals(aClimber.getContact()))
    {
      climbers.remove(aClimber);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClimberAt(Climber aClimber, int index)
  {  
    boolean wasAdded = false;
    if(addClimber(aClimber))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbers()) { index = numberOfClimbers() - 1; }
      climbers.remove(aClimber);
      climbers.add(index, aClimber);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClimberAt(Climber aClimber, int index)
  {
    boolean wasAdded = false;
    if(climbers.contains(aClimber))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbers()) { index = numberOfClimbers() - 1; }
      climbers.remove(aClimber);
      climbers.add(index, aClimber);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClimberAt(aClimber, index);
    }
    return wasAdded;
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
      existingClimbSafeSystem.removeEmergencyContact(this);
    }
    climbSafeSystem.addEmergencyContact(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=climbers.size(); i > 0; i--)
    {
      Climber aClimber = climbers.get(i - 1);
      aClimber.delete();
    }
    ClimbSafeSystem placeholderClimbSafeSystem = climbSafeSystem;
    this.climbSafeSystem = null;
    if(placeholderClimbSafeSystem != null)
    {
      placeholderClimbSafeSystem.removeEmergencyContact(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "number" + ":" + getNumber()+ "," +
            "email" + ":" + getEmail()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafeSystem = "+(getClimbSafeSystem()!=null?Integer.toHexString(System.identityHashCode(getClimbSafeSystem())):"null");
  }
}