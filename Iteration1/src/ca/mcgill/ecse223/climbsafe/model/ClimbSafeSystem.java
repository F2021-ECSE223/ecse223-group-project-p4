/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.sql.Date;
import java.util.*;

// line 3 "../../../../../ClimbSafe.ump"
public class ClimbSafeSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbSafeSystem Attributes
  private Date seasonStart;
  private Date seasonEnd;
  private int guidePricePerWeek;
  private int bundlePercentageDiscount;

  //ClimbSafeSystem Associations
  private List<EquipmentItem> allItems;
  private List<Bundle> allBundles;
  private List<Hotel> allHotels;
  private List<MountainClimb> climbs;
  private List<EmergencyContact> emergencyContacts;
  private List<User> users;
  private List<ItemQuantity> itemQuantities;
  private List<BundleQuantity> bundleQuantities;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbSafeSystem(Date aSeasonStart, Date aSeasonEnd, int aGuidePricePerWeek, int aBundlePercentageDiscount)
  {
    seasonStart = aSeasonStart;
    seasonEnd = aSeasonEnd;
    guidePricePerWeek = aGuidePricePerWeek;
    bundlePercentageDiscount = aBundlePercentageDiscount;
    allItems = new ArrayList<EquipmentItem>();
    allBundles = new ArrayList<Bundle>();
    allHotels = new ArrayList<Hotel>();
    climbs = new ArrayList<MountainClimb>();
    emergencyContacts = new ArrayList<EmergencyContact>();
    users = new ArrayList<User>();
    itemQuantities = new ArrayList<ItemQuantity>();
    bundleQuantities = new ArrayList<BundleQuantity>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSeasonStart(Date aSeasonStart)
  {
    boolean wasSet = false;
    seasonStart = aSeasonStart;
    wasSet = true;
    return wasSet;
  }

  public boolean setSeasonEnd(Date aSeasonEnd)
  {
    boolean wasSet = false;
    seasonEnd = aSeasonEnd;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuidePricePerWeek(int aGuidePricePerWeek)
  {
    boolean wasSet = false;
    guidePricePerWeek = aGuidePricePerWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setBundlePercentageDiscount(int aBundlePercentageDiscount)
  {
    boolean wasSet = false;
    bundlePercentageDiscount = aBundlePercentageDiscount;
    wasSet = true;
    return wasSet;
  }

  public Date getSeasonStart()
  {
    return seasonStart;
  }

  public Date getSeasonEnd()
  {
    return seasonEnd;
  }

  public int getGuidePricePerWeek()
  {
    return guidePricePerWeek;
  }

  /**
   * If a guide is hired
   */
  public int getBundlePercentageDiscount()
  {
    return bundlePercentageDiscount;
  }
  /* Code from template association_GetMany */
  public EquipmentItem getAllItem(int index)
  {
    EquipmentItem aAllItem = allItems.get(index);
    return aAllItem;
  }

  public List<EquipmentItem> getAllItems()
  {
    List<EquipmentItem> newAllItems = Collections.unmodifiableList(allItems);
    return newAllItems;
  }

  public int numberOfAllItems()
  {
    int number = allItems.size();
    return number;
  }

  public boolean hasAllItems()
  {
    boolean has = allItems.size() > 0;
    return has;
  }

  public int indexOfAllItem(EquipmentItem aAllItem)
  {
    int index = allItems.indexOf(aAllItem);
    return index;
  }
  /* Code from template association_GetMany */
  public Bundle getAllBundle(int index)
  {
    Bundle aAllBundle = allBundles.get(index);
    return aAllBundle;
  }

  public List<Bundle> getAllBundles()
  {
    List<Bundle> newAllBundles = Collections.unmodifiableList(allBundles);
    return newAllBundles;
  }

  public int numberOfAllBundles()
  {
    int number = allBundles.size();
    return number;
  }

  public boolean hasAllBundles()
  {
    boolean has = allBundles.size() > 0;
    return has;
  }

  public int indexOfAllBundle(Bundle aAllBundle)
  {
    int index = allBundles.indexOf(aAllBundle);
    return index;
  }
  /* Code from template association_GetMany */
  public Hotel getAllHotel(int index)
  {
    Hotel aAllHotel = allHotels.get(index);
    return aAllHotel;
  }

  public List<Hotel> getAllHotels()
  {
    List<Hotel> newAllHotels = Collections.unmodifiableList(allHotels);
    return newAllHotels;
  }

  public int numberOfAllHotels()
  {
    int number = allHotels.size();
    return number;
  }

  public boolean hasAllHotels()
  {
    boolean has = allHotels.size() > 0;
    return has;
  }

  public int indexOfAllHotel(Hotel aAllHotel)
  {
    int index = allHotels.indexOf(aAllHotel);
    return index;
  }
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
  /* Code from template association_GetMany */
  public EmergencyContact getEmergencyContact(int index)
  {
    EmergencyContact aEmergencyContact = emergencyContacts.get(index);
    return aEmergencyContact;
  }

  public List<EmergencyContact> getEmergencyContacts()
  {
    List<EmergencyContact> newEmergencyContacts = Collections.unmodifiableList(emergencyContacts);
    return newEmergencyContacts;
  }

  public int numberOfEmergencyContacts()
  {
    int number = emergencyContacts.size();
    return number;
  }

  public boolean hasEmergencyContacts()
  {
    boolean has = emergencyContacts.size() > 0;
    return has;
  }

  public int indexOfEmergencyContact(EmergencyContact aEmergencyContact)
  {
    int index = emergencyContacts.indexOf(aEmergencyContact);
    return index;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAllItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public EquipmentItem addAllItem(double aWeight, int aPricePerWeek, String aName)
  {
    return new EquipmentItem(aWeight, aPricePerWeek, aName, this);
  }

  public boolean addAllItem(EquipmentItem aAllItem)
  {
    boolean wasAdded = false;
    if (allItems.contains(aAllItem)) { return false; }
    ClimbSafeSystem existingClimbSafeSystem = aAllItem.getClimbSafeSystem();
    boolean isNewClimbSafeSystem = existingClimbSafeSystem != null && !this.equals(existingClimbSafeSystem);
    if (isNewClimbSafeSystem)
    {
      aAllItem.setClimbSafeSystem(this);
    }
    else
    {
      allItems.add(aAllItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAllItem(EquipmentItem aAllItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aAllItem, as it must always have a climbSafeSystem
    if (!this.equals(aAllItem.getClimbSafeSystem()))
    {
      allItems.remove(aAllItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAllItemAt(EquipmentItem aAllItem, int index)
  {  
    boolean wasAdded = false;
    if(addAllItem(aAllItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllItems()) { index = numberOfAllItems() - 1; }
      allItems.remove(aAllItem);
      allItems.add(index, aAllItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAllItemAt(EquipmentItem aAllItem, int index)
  {
    boolean wasAdded = false;
    if(allItems.contains(aAllItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllItems()) { index = numberOfAllItems() - 1; }
      allItems.remove(aAllItem);
      allItems.add(index, aAllItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAllItemAt(aAllItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAllBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Bundle addAllBundle(String aName, int aPricePerWeek, int aWeight, EquipmentItem... allItems)
  {
    return new Bundle(aName, aPricePerWeek, aWeight, this, allItems);
  }

  public boolean addAllBundle(Bundle aAllBundle)
  {
    boolean wasAdded = false;
    if (allBundles.contains(aAllBundle)) { return false; }
    ClimbSafeSystem existingClimbSafeSystem = aAllBundle.getClimbSafeSystem();
    boolean isNewClimbSafeSystem = existingClimbSafeSystem != null && !this.equals(existingClimbSafeSystem);
    if (isNewClimbSafeSystem)
    {
      aAllBundle.setClimbSafeSystem(this);
    }
    else
    {
      allBundles.add(aAllBundle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAllBundle(Bundle aAllBundle)
  {
    boolean wasRemoved = false;
    //Unable to remove aAllBundle, as it must always have a climbSafeSystem
    if (!this.equals(aAllBundle.getClimbSafeSystem()))
    {
      allBundles.remove(aAllBundle);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAllBundleAt(Bundle aAllBundle, int index)
  {  
    boolean wasAdded = false;
    if(addAllBundle(aAllBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllBundles()) { index = numberOfAllBundles() - 1; }
      allBundles.remove(aAllBundle);
      allBundles.add(index, aAllBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAllBundleAt(Bundle aAllBundle, int index)
  {
    boolean wasAdded = false;
    if(allBundles.contains(aAllBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllBundles()) { index = numberOfAllBundles() - 1; }
      allBundles.remove(aAllBundle);
      allBundles.add(index, aAllBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAllBundleAt(aAllBundle, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAllHotels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Hotel addAllHotel(String aName, String aAddress, Hotel.Rating aRating)
  {
    return new Hotel(aName, aAddress, aRating, this);
  }

  public boolean addAllHotel(Hotel aAllHotel)
  {
    boolean wasAdded = false;
    if (allHotels.contains(aAllHotel)) { return false; }
    ClimbSafeSystem existingClimbSafeSystem = aAllHotel.getClimbSafeSystem();
    boolean isNewClimbSafeSystem = existingClimbSafeSystem != null && !this.equals(existingClimbSafeSystem);
    if (isNewClimbSafeSystem)
    {
      aAllHotel.setClimbSafeSystem(this);
    }
    else
    {
      allHotels.add(aAllHotel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAllHotel(Hotel aAllHotel)
  {
    boolean wasRemoved = false;
    //Unable to remove aAllHotel, as it must always have a climbSafeSystem
    if (!this.equals(aAllHotel.getClimbSafeSystem()))
    {
      allHotels.remove(aAllHotel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAllHotelAt(Hotel aAllHotel, int index)
  {  
    boolean wasAdded = false;
    if(addAllHotel(aAllHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllHotels()) { index = numberOfAllHotels() - 1; }
      allHotels.remove(aAllHotel);
      allHotels.add(index, aAllHotel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAllHotelAt(Hotel aAllHotel, int index)
  {
    boolean wasAdded = false;
    if(allHotels.contains(aAllHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllHotels()) { index = numberOfAllHotels() - 1; }
      allHotels.remove(aAllHotel);
      allHotels.add(index, aAllHotel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAllHotelAt(aAllHotel, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbs()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MountainClimb addClimb(int aStartWeek, int aEndWeek, Member aMember)
  {
    return new MountainClimb(aStartWeek, aEndWeek, this, aMember);
  }

  public boolean addClimb(MountainClimb aClimb)
  {
    boolean wasAdded = false;
    if (climbs.contains(aClimb)) { return false; }
    ClimbSafeSystem existingClimbSafeSystem = aClimb.getClimbSafeSystem();
    boolean isNewClimbSafeSystem = existingClimbSafeSystem != null && !this.equals(existingClimbSafeSystem);
    if (isNewClimbSafeSystem)
    {
      aClimb.setClimbSafeSystem(this);
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
    //Unable to remove aClimb, as it must always have a climbSafeSystem
    if (!this.equals(aClimb.getClimbSafeSystem()))
    {
      climbs.remove(aClimb);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEmergencyContacts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public EmergencyContact addEmergencyContact(String aName, int aNumber, String aEmail)
  {
    return new EmergencyContact(aName, aNumber, aEmail, this);
  }

  public boolean addEmergencyContact(EmergencyContact aEmergencyContact)
  {
    boolean wasAdded = false;
    if (emergencyContacts.contains(aEmergencyContact)) { return false; }
    ClimbSafeSystem existingClimbSafeSystem = aEmergencyContact.getClimbSafeSystem();
    boolean isNewClimbSafeSystem = existingClimbSafeSystem != null && !this.equals(existingClimbSafeSystem);
    if (isNewClimbSafeSystem)
    {
      aEmergencyContact.setClimbSafeSystem(this);
    }
    else
    {
      emergencyContacts.add(aEmergencyContact);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmergencyContact(EmergencyContact aEmergencyContact)
  {
    boolean wasRemoved = false;
    //Unable to remove aEmergencyContact, as it must always have a climbSafeSystem
    if (!this.equals(aEmergencyContact.getClimbSafeSystem()))
    {
      emergencyContacts.remove(aEmergencyContact);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEmergencyContactAt(EmergencyContact aEmergencyContact, int index)
  {  
    boolean wasAdded = false;
    if(addEmergencyContact(aEmergencyContact))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmergencyContacts()) { index = numberOfEmergencyContacts() - 1; }
      emergencyContacts.remove(aEmergencyContact);
      emergencyContacts.add(index, aEmergencyContact);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmergencyContactAt(EmergencyContact aEmergencyContact, int index)
  {
    boolean wasAdded = false;
    if(emergencyContacts.contains(aEmergencyContact))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmergencyContacts()) { index = numberOfEmergencyContacts() - 1; }
      emergencyContacts.remove(aEmergencyContact);
      emergencyContacts.add(index, aEmergencyContact);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmergencyContactAt(aEmergencyContact, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    ClimbSafeSystem existingClimbSafeSystem = aUser.getClimbSafeSystem();
    boolean isNewClimbSafeSystem = existingClimbSafeSystem != null && !this.equals(existingClimbSafeSystem);
    if (isNewClimbSafeSystem)
    {
      aUser.setClimbSafeSystem(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a climbSafeSystem
    if (!this.equals(aUser.getClimbSafeSystem()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItemQuantities()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ItemQuantity addItemQuantity(int aDesiredAmountOfItems, Member aItemPicker, EquipmentItem aChosenItem)
  {
    return new ItemQuantity(aDesiredAmountOfItems, aItemPicker, aChosenItem, this);
  }

  public boolean addItemQuantity(ItemQuantity aItemQuantity)
  {
    boolean wasAdded = false;
    if (itemQuantities.contains(aItemQuantity)) { return false; }
    ClimbSafeSystem existingClimbSafeSystem = aItemQuantity.getClimbSafeSystem();
    boolean isNewClimbSafeSystem = existingClimbSafeSystem != null && !this.equals(existingClimbSafeSystem);
    if (isNewClimbSafeSystem)
    {
      aItemQuantity.setClimbSafeSystem(this);
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
    //Unable to remove aItemQuantity, as it must always have a climbSafeSystem
    if (!this.equals(aItemQuantity.getClimbSafeSystem()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundleQuantities()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BundleQuantity addBundleQuantity(int aDesiredAmountOfBundles, Member aBundlePicker, Bundle aChosenBundle)
  {
    return new BundleQuantity(aDesiredAmountOfBundles, aBundlePicker, aChosenBundle, this);
  }

  public boolean addBundleQuantity(BundleQuantity aBundleQuantity)
  {
    boolean wasAdded = false;
    if (bundleQuantities.contains(aBundleQuantity)) { return false; }
    ClimbSafeSystem existingClimbSafeSystem = aBundleQuantity.getClimbSafeSystem();
    boolean isNewClimbSafeSystem = existingClimbSafeSystem != null && !this.equals(existingClimbSafeSystem);
    if (isNewClimbSafeSystem)
    {
      aBundleQuantity.setClimbSafeSystem(this);
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
    //Unable to remove aBundleQuantity, as it must always have a climbSafeSystem
    if (!this.equals(aBundleQuantity.getClimbSafeSystem()))
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

  public void delete()
  {
    while (allItems.size() > 0)
    {
      EquipmentItem aAllItem = allItems.get(allItems.size() - 1);
      aAllItem.delete();
      allItems.remove(aAllItem);
    }
    
    while (allBundles.size() > 0)
    {
      Bundle aAllBundle = allBundles.get(allBundles.size() - 1);
      aAllBundle.delete();
      allBundles.remove(aAllBundle);
    }
    
    while (allHotels.size() > 0)
    {
      Hotel aAllHotel = allHotels.get(allHotels.size() - 1);
      aAllHotel.delete();
      allHotels.remove(aAllHotel);
    }
    
    while (climbs.size() > 0)
    {
      MountainClimb aClimb = climbs.get(climbs.size() - 1);
      aClimb.delete();
      climbs.remove(aClimb);
    }
    
    while (emergencyContacts.size() > 0)
    {
      EmergencyContact aEmergencyContact = emergencyContacts.get(emergencyContacts.size() - 1);
      aEmergencyContact.delete();
      emergencyContacts.remove(aEmergencyContact);
    }
    
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (itemQuantities.size() > 0)
    {
      ItemQuantity aItemQuantity = itemQuantities.get(itemQuantities.size() - 1);
      aItemQuantity.delete();
      itemQuantities.remove(aItemQuantity);
    }
    
    while (bundleQuantities.size() > 0)
    {
      BundleQuantity aBundleQuantity = bundleQuantities.get(bundleQuantities.size() - 1);
      aBundleQuantity.delete();
      bundleQuantities.remove(aBundleQuantity);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "guidePricePerWeek" + ":" + getGuidePricePerWeek()+ "," +
            "bundlePercentageDiscount" + ":" + getBundlePercentageDiscount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "seasonStart" + "=" + (getSeasonStart() != null ? !getSeasonStart().equals(this)  ? getSeasonStart().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "seasonEnd" + "=" + (getSeasonEnd() != null ? !getSeasonEnd().equals(this)  ? getSeasonEnd().toString().replaceAll("  ","    ") : "this" : "null");
  }
}