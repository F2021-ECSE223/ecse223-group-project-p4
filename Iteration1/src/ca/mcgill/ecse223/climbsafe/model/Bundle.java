/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 53 "../../../../../ClimbSafe.ump"
public class Bundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bundle Attributes
  private String name;
  private int pricePerWeek;
  private int weight;

  //Bundle Associations
  private List<BundleQuantity> bundleQuantities;
  private ClimbSafeSystem climbSafeSystem;
  private List<EquipmentItem> items;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bundle(String aName, int aPricePerWeek, int aWeight, ClimbSafeSystem aClimbSafeSystem, EquipmentItem... allItems)
  {
    name = aName;
    pricePerWeek = aPricePerWeek;
    weight = aWeight;
    bundleQuantities = new ArrayList<BundleQuantity>();
    boolean didAddClimbSafeSystem = setClimbSafeSystem(aClimbSafeSystem);
    if (!didAddClimbSafeSystem)
    {
      throw new RuntimeException("Unable to create allBundle due to climbSafeSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    items = new ArrayList<EquipmentItem>();
    boolean didAddItems = setItems(allItems);
    if (!didAddItems)
    {
      throw new RuntimeException("Unable to create Bundle, must have at least 2 items. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setPricePerWeek(int aPricePerWeek)
  {
    boolean wasSet = false;
    pricePerWeek = aPricePerWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeight(int aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  /**
   * This is a derived attribute, sums up the total price per week of the items in the bundle
   */
  public int getPricePerWeek()
  {
    return pricePerWeek;
  }

  /**
   * This is a derived attribute, sums up the total weight of the chosen equipment
   */
  public int getWeight()
  {
    return weight;
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
  /* Code from template association_GetOne */
  public ClimbSafeSystem getClimbSafeSystem()
  {
    return climbSafeSystem;
  }
  /* Code from template association_GetMany */
  public EquipmentItem getItem(int index)
  {
    EquipmentItem aItem = items.get(index);
    return aItem;
  }

  public List<EquipmentItem> getItems()
  {
    List<EquipmentItem> newItems = Collections.unmodifiableList(items);
    return newItems;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  public int indexOfItem(EquipmentItem aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundleQuantities()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BundleQuantity addBundleQuantity(int aDesiredAmountOfBundles, Member aBundlePicker, ClimbSafeSystem aClimbSafeSystem)
  {
    return new BundleQuantity(aDesiredAmountOfBundles, aBundlePicker, this, aClimbSafeSystem);
  }

  public boolean addBundleQuantity(BundleQuantity aBundleQuantity)
  {
    boolean wasAdded = false;
    if (bundleQuantities.contains(aBundleQuantity)) { return false; }
    Bundle existingChosenBundle = aBundleQuantity.getChosenBundle();
    boolean isNewChosenBundle = existingChosenBundle != null && !this.equals(existingChosenBundle);
    if (isNewChosenBundle)
    {
      aBundleQuantity.setChosenBundle(this);
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
    //Unable to remove aBundleQuantity, as it must always have a chosenBundle
    if (!this.equals(aBundleQuantity.getChosenBundle()))
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
      existingClimbSafeSystem.removeAllBundle(this);
    }
    climbSafeSystem.addAllBundle(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfItemsValid()
  {
    boolean isValid = numberOfItems() >= minimumNumberOfItems();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addItem(EquipmentItem aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    items.add(aItem);
    if (aItem.indexOfBundle(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aItem.addBundle(this);
      if (!wasAdded)
      {
        items.remove(aItem);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeItem(EquipmentItem aItem)
  {
    boolean wasRemoved = false;
    if (!items.contains(aItem))
    {
      return wasRemoved;
    }

    if (numberOfItems() <= minimumNumberOfItems())
    {
      return wasRemoved;
    }

    int oldIndex = items.indexOf(aItem);
    items.remove(oldIndex);
    if (aItem.indexOfBundle(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aItem.removeBundle(this);
      if (!wasRemoved)
      {
        items.add(oldIndex,aItem);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setItems(EquipmentItem... newItems)
  {
    boolean wasSet = false;
    ArrayList<EquipmentItem> verifiedItems = new ArrayList<EquipmentItem>();
    for (EquipmentItem aItem : newItems)
    {
      if (verifiedItems.contains(aItem))
      {
        continue;
      }
      verifiedItems.add(aItem);
    }

    if (verifiedItems.size() != newItems.length || verifiedItems.size() < minimumNumberOfItems())
    {
      return wasSet;
    }

    ArrayList<EquipmentItem> oldItems = new ArrayList<EquipmentItem>(items);
    items.clear();
    for (EquipmentItem aNewItem : verifiedItems)
    {
      items.add(aNewItem);
      if (oldItems.contains(aNewItem))
      {
        oldItems.remove(aNewItem);
      }
      else
      {
        aNewItem.addBundle(this);
      }
    }

    for (EquipmentItem anOldItem : oldItems)
    {
      anOldItem.removeBundle(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemAt(EquipmentItem aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(EquipmentItem aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=bundleQuantities.size(); i > 0; i--)
    {
      BundleQuantity aBundleQuantity = bundleQuantities.get(i - 1);
      aBundleQuantity.delete();
    }
    ClimbSafeSystem placeholderClimbSafeSystem = climbSafeSystem;
    this.climbSafeSystem = null;
    if(placeholderClimbSafeSystem != null)
    {
      placeholderClimbSafeSystem.removeAllBundle(this);
    }
    ArrayList<EquipmentItem> copyOfItems = new ArrayList<EquipmentItem>(items);
    items.clear();
    for(EquipmentItem aItem : copyOfItems)
    {
      aItem.removeBundle(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "pricePerWeek" + ":" + getPricePerWeek()+ "," +
            "weight" + ":" + getWeight()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafeSystem = "+(getClimbSafeSystem()!=null?Integer.toHexString(System.identityHashCode(getClimbSafeSystem())):"null");
  }
}