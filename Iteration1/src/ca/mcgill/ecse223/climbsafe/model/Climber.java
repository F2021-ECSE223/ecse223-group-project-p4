/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 16 "../../../../../ClimbSafe.ump"
public abstract class Climber extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Climber Attributes
  private String name;

  //Climber Associations
  private EmergencyContact contact;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Climber(String aEmail, String aPassword, ClimbSafeSystem aClimbSafeSystem, String aName, EmergencyContact aContact)
  {
    super(aEmail, aPassword, aClimbSafeSystem);
    name = aName;
    boolean didAddContact = setContact(aContact);
    if (!didAddContact)
    {
      throw new RuntimeException("Unable to create climber due to contact. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetOne */
  public EmergencyContact getContact()
  {
    return contact;
  }
  /* Code from template association_SetOneToMany */
  public boolean setContact(EmergencyContact aContact)
  {
    boolean wasSet = false;
    if (aContact == null)
    {
      return wasSet;
    }

    EmergencyContact existingContact = contact;
    contact = aContact;
    if (existingContact != null && !existingContact.equals(aContact))
    {
      existingContact.removeClimber(this);
    }
    contact.addClimber(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    EmergencyContact placeholderContact = contact;
    this.contact = null;
    if(placeholderContact != null)
    {
      placeholderContact.removeClimber(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "contact = "+(getContact()!=null?Integer.toHexString(System.identityHashCode(getContact())):"null");
  }
}