/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 59 "../../../../../ClimbSafe.ump"
public class Hotel
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Rating { OneStar, TwoStars, ThreeStars, FourStars, FiveStars }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Hotel Attributes
  private String name;
  private String address;
  private Rating rating;

  //Hotel Associations
  private ClimbSafeSystem climbSafeSystem;
  private List<Member> guests;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hotel(String aName, String aAddress, Rating aRating, ClimbSafeSystem aClimbSafeSystem)
  {
    name = aName;
    address = aAddress;
    rating = aRating;
    boolean didAddClimbSafeSystem = setClimbSafeSystem(aClimbSafeSystem);
    if (!didAddClimbSafeSystem)
    {
      throw new RuntimeException("Unable to create allHotel due to climbSafeSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    guests = new ArrayList<Member>();
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

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setRating(Rating aRating)
  {
    boolean wasSet = false;
    rating = aRating;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }

  public Rating getRating()
  {
    return rating;
  }
  /* Code from template association_GetOne */
  public ClimbSafeSystem getClimbSafeSystem()
  {
    return climbSafeSystem;
  }
  /* Code from template association_GetMany */
  public Member getGuest(int index)
  {
    Member aGuest = guests.get(index);
    return aGuest;
  }

  public List<Member> getGuests()
  {
    List<Member> newGuests = Collections.unmodifiableList(guests);
    return newGuests;
  }

  public int numberOfGuests()
  {
    int number = guests.size();
    return number;
  }

  public boolean hasGuests()
  {
    boolean has = guests.size() > 0;
    return has;
  }

  public int indexOfGuest(Member aGuest)
  {
    int index = guests.indexOf(aGuest);
    return index;
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
      existingClimbSafeSystem.removeAllHotel(this);
    }
    climbSafeSystem.addAllHotel(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGuests()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addGuest(Member aGuest)
  {
    boolean wasAdded = false;
    if (guests.contains(aGuest)) { return false; }
    Hotel existingHotel = aGuest.getHotel();
    if (existingHotel == null)
    {
      aGuest.setHotel(this);
    }
    else if (!this.equals(existingHotel))
    {
      existingHotel.removeGuest(aGuest);
      addGuest(aGuest);
    }
    else
    {
      guests.add(aGuest);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGuest(Member aGuest)
  {
    boolean wasRemoved = false;
    if (guests.contains(aGuest))
    {
      guests.remove(aGuest);
      aGuest.setHotel(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGuestAt(Member aGuest, int index)
  {  
    boolean wasAdded = false;
    if(addGuest(aGuest))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGuests()) { index = numberOfGuests() - 1; }
      guests.remove(aGuest);
      guests.add(index, aGuest);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGuestAt(Member aGuest, int index)
  {
    boolean wasAdded = false;
    if(guests.contains(aGuest))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGuests()) { index = numberOfGuests() - 1; }
      guests.remove(aGuest);
      guests.add(index, aGuest);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGuestAt(aGuest, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ClimbSafeSystem placeholderClimbSafeSystem = climbSafeSystem;
    this.climbSafeSystem = null;
    if(placeholderClimbSafeSystem != null)
    {
      placeholderClimbSafeSystem.removeAllHotel(this);
    }
    while( !guests.isEmpty() )
    {
      guests.get(0).setHotel(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "rating" + "=" + (getRating() != null ? !getRating().equals(this)  ? getRating().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafeSystem = "+(getClimbSafeSystem()!=null?Integer.toHexString(System.identityHashCode(getClimbSafeSystem())):"null");
  }
}