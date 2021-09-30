/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 32 "../../../../../ClimbSafe.ump"
public class Member extends Climber
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private int totalNumberOfWeeksDesired;
  private boolean wantsGuide;
  private boolean wantsHotel;
  private int totalCost;
  private int totalWeight;

  //Member Associations
  private List<BundleQuantity> bundleQuantities;
  private List<ItemQuantity> itemQuantities;
  private Hotel hotel;
  private MountainClimb climb;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(String aEmail, String aPassword, ClimbSafeSystem aClimbSafeSystem, String aName, EmergencyContact aContact, int aTotalNumberOfWeeksDesired, boolean aWantsGuide, boolean aWantsHotel, int aTotalCost, int aTotalWeight)
  {
    super(aEmail, aPassword, aClimbSafeSystem, aName, aContact);
    totalNumberOfWeeksDesired = aTotalNumberOfWeeksDesired;
    wantsGuide = aWantsGuide;
    wantsHotel = aWantsHotel;
    totalCost = aTotalCost;
    totalWeight = aTotalWeight;
    bundleQuantities = new ArrayList<BundleQuantity>();
    itemQuantities = new ArrayList<ItemQuantity>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTotalNumberOfWeeksDesired(int aTotalNumberOfWeeksDesired)
  {
    boolean wasSet = false;
    totalNumberOfWeeksDesired = aTotalNumberOfWeeksDesired;
    wasSet = true;
    return wasSet;
  }

  public boolean setWantsGuide(boolean aWantsGuide)
  {
    boolean wasSet = false;
    wantsGuide = aWantsGuide;
    wasSet = true;
    return wasSet;
  }

  public boolean setWantsHotel(boolean aWantsHotel)
  {
    boolean wasSet = false;
    wantsHotel = aWantsHotel;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalCost(int aTotalCost)
  {
    boolean wasSet = false;
    totalCost = aTotalCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalWeight(int aTotalWeight)
  {
    boolean wasSet = false;
    totalWeight = aTotalWeight;
    wasSet = true;
    return wasSet;
  }

  /**
   * Saves user input in memory before the admin assigns him to a mountain climb of that length
   */
  public int getTotalNumberOfWeeksDesired()
  {
    return totalNumberOfWeeksDesired;
  }

  /**
   * Saves user input in memory before the admin assigns him to a mountain climb
   */
  public boolean getWantsGuide()
  {
    return wantsGuide;
  }

  /**
   * Saves user input in memory before the admin assigns him to a hotel
   */
  public boolean getWantsHotel()
  {
    return wantsHotel;
  }

  /**
   * This is a derived attribute, sums up the total cost of the trip (multiplies the prices per week by the number of weeks)
   */
  public int getTotalCost()
  {
    return totalCost;
  }

  /**
   * This is a derived attribute, sums up the total weight of the chosen equipment (to know if it can be carried in the mountains)
   */
  public int getTotalWeight()
  {
    return totalWeight;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isWantsGuide()
  {
    return wantsGuide;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isWantsHotel()
  {
    return wantsHotel;
  }
  /* Code from template association_GetMany */
  public BundleQuantity getBundleQuantity(int index)
  {
    BundleQuantity aBundleQuantity = bundleQuantities.get(index);
    return aBundleQuantity;
  }

  public List<BundleQuantity> getBundleQuantities()
  {
    List<BundleQuantity> newBundleQuantities = Collections.unmodifiableList(bundleQuantities);
    return newBundleQuantities;
  }

  public int numberOfBundleQuantities()
  {
    int number = bundleQuantities.size();
    return number;
  }

  public boolean hasBundleQuantities()
  {
    boolean has = bundleQuantities.size() > 0;
    return has;
  }

  public int indexOfBundleQuantity(BundleQuantity aBundleQuantity)
  {
    int index = bundleQuantities.indexOf(aBundleQuantity);
    return index;
  }
  /* Code from template association_GetMany */
  public ItemQuantity getItemQuantity(int index)
  {
    ItemQuantity aItemQuantity = itemQuantities.get(index);
    return aItemQuantity;
  }

  public List<ItemQuantity> getItemQuantities()
  {
    List<ItemQuantity> newItemQuantities = Collections.unmodifiableList(itemQuantities);
    return newItemQuantities;
  }

  public int numberOfItemQuantities()
  {
    int number = itemQuantities.size();
    return number;
  }

  public boolean hasItemQuantities()
  {
    boolean has = itemQuantities.size() > 0;
    return has;
  }

  public int indexOfItemQuantity(ItemQuantity aItemQuantity)
  {
    int index = itemQuantities.indexOf(aItemQuantity);
    return index;
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
  public MountainClimb getClimb()
  {
    return climb;
  }

  public boolean hasClimb()
  {
    boolean has = climb != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundleQuantities()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BundleQuantity addBundleQuantity(int aDesiredAmountOfBundles, Bundle aChosenBundle, ClimbSafeSystem aClimbSafeSystem)
  {
    return new BundleQuantity(aDesiredAmountOfBundles, this, aChosenBundle, aClimbSafeSystem);
  }

  public boolean addBundleQuantity(BundleQuantity aBundleQuantity)
  {
    boolean wasAdded = false;
    if (bundleQuantities.contains(aBundleQuantity)) { return false; }
    Member existingBundlePicker = aBundleQuantity.getBundlePicker();
    boolean isNewBundlePicker = existingBundlePicker != null && !this.equals(existingBundlePicker);
    if (isNewBundlePicker)
    {
      aBundleQuantity.setBundlePicker(this);
    }
    else
    {
      bundleQuantities.add(aBundleQuantity);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBundleQuantity(BundleQuantity aBundleQuantity)
  {
    boolean wasRemoved = false;
    //Unable to remove aBundleQuantity, as it must always have a bundlePicker
    if (!this.equals(aBundleQuantity.getBundlePicker()))
    {
      bundleQuantities.remove(aBundleQuantity);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundleQuantityAt(BundleQuantity aBundleQuantity, int index)
  {  
    boolean wasAdded = false;
    if(addBundleQuantity(aBundleQuantity))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundleQuantities()) { index = numberOfBundleQuantities() - 1; }
      bundleQuantities.remove(aBundleQuantity);
      bundleQuantities.add(index, aBundleQuantity);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundleQuantityAt(BundleQuantity aBundleQuantity, int index)
  {
    boolean wasAdded = false;
    if(bundleQuantities.contains(aBundleQuantity))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundleQuantities()) { index = numberOfBundleQuantities() - 1; }
      bundleQuantities.remove(aBundleQuantity);
      bundleQuantities.add(index, aBundleQuantity);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundleQuantityAt(aBundleQuantity, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItemQuantities()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ItemQuantity addItemQuantity(int aDesiredAmountOfItems, EquipmentItem aChosenItem, ClimbSafeSystem aClimbSafeSystem)
  {
    return new ItemQuantity(aDesiredAmountOfItems, this, aChosenItem, aClimbSafeSystem);
  }

  public boolean addItemQuantity(ItemQuantity aItemQuantity)
  {
    boolean wasAdded = false;
    if (itemQuantities.contains(aItemQuantity)) { return false; }
    Member existingItemPicker = aItemQuantity.getItemPicker();
    boolean isNewItemPicker = existingItemPicker != null && !this.equals(existingItemPicker);
    if (isNewItemPicker)
    {
      aItemQuantity.setItemPicker(this);
    }
    else
    {
      itemQuantities.add(aItemQuantity);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItemQuantity(ItemQuantity aItemQuantity)
  {
    boolean wasRemoved = false;
    //Unable to remove aItemQuantity, as it must always have a itemPicker
    if (!this.equals(aItemQuantity.getItemPicker()))
    {
      itemQuantities.remove(aItemQuantity);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemQuantityAt(ItemQuantity aItemQuantity, int index)
  {  
    boolean wasAdded = false;
    if(addItemQuantity(aItemQuantity))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItemQuantities()) { index = numberOfItemQuantities() - 1; }
      itemQuantities.remove(aItemQuantity);
      itemQuantities.add(index, aItemQuantity);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemQuantityAt(ItemQuantity aItemQuantity, int index)
  {
    boolean wasAdded = false;
    if(itemQuantities.contains(aItemQuantity))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItemQuantities()) { index = numberOfItemQuantities() - 1; }
      itemQuantities.remove(aItemQuantity);
      itemQuantities.add(index, aItemQuantity);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemQuantityAt(aItemQuantity, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeGuest(this);
    }
    if (aHotel != null)
    {
      aHotel.addGuest(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setClimb(MountainClimb aNewClimb)
  {
    boolean wasSet = false;
    if (climb != null && !climb.equals(aNewClimb) && equals(climb.getMember()))
    {
      //Unable to setClimb, as existing climb would become an orphan
      return wasSet;
    }

    climb = aNewClimb;
    Member anOldMember = aNewClimb != null ? aNewClimb.getMember() : null;

    if (!this.equals(anOldMember))
    {
      if (anOldMember != null)
      {
        anOldMember.climb = null;
      }
      if (climb != null)
      {
        climb.setMember(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=bundleQuantities.size(); i > 0; i--)
    {
      BundleQuantity aBundleQuantity = bundleQuantities.get(i - 1);
      aBundleQuantity.delete();
    }
    for(int i=itemQuantities.size(); i > 0; i--)
    {
      ItemQuantity aItemQuantity = itemQuantities.get(i - 1);
      aItemQuantity.delete();
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeGuest(this);
    }
    MountainClimb existingClimb = climb;
    climb = null;
    if (existingClimb != null)
    {
      existingClimb.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "totalNumberOfWeeksDesired" + ":" + getTotalNumberOfWeeksDesired()+ "," +
            "wantsGuide" + ":" + getWantsGuide()+ "," +
            "wantsHotel" + ":" + getWantsHotel()+ "," +
            "totalCost" + ":" + getTotalCost()+ "," +
            "totalWeight" + ":" + getTotalWeight()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climb = "+(getClimb()!=null?Integer.toHexString(System.identityHashCode(getClimb())):"null");
  }
}