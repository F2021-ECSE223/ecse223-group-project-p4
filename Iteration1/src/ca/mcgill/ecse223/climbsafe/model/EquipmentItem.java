/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 47 "../../../../../ClimbSafe.ump"
public class EquipmentItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EquipmentItem Attributes
  private double weight;
  private int pricePerWeek;
  private String name;

  //EquipmentItem Associations
  private List<ItemQuantity> itemQuantities;
  private ClimbSafeSystem climbSafeSystem;
  private List<Bundle> bundles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EquipmentItem(double aWeight, int aPricePerWeek, String aName, ClimbSafeSystem aClimbSafeSystem)
  {
    weight = aWeight;
    pricePerWeek = aPricePerWeek;
    name = aName;
    itemQuantities = new ArrayList<ItemQuantity>();
    boolean didAddClimbSafeSystem = setClimbSafeSystem(aClimbSafeSystem);
    if (!didAddClimbSafeSystem)
    {
      throw new RuntimeException("Unable to create allItem due to climbSafeSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bundles = new ArrayList<Bundle>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeight(double aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
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

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public double getWeight()
  {
    return weight;
  }

  public int getPricePerWeek()
  {
    return pricePerWeek;
  }

  public String getName()
  {
    return name;
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
  public ClimbSafeSystem getClimbSafeSystem()
  {
    return climbSafeSystem;
  }
  /* Code from template association_GetMany */
  public Bundle getBundle(int index)
  {
    Bundle aBundle = bundles.get(index);
    return aBundle;
  }

  public List<Bundle> getBundles()
  {
    List<Bundle> newBundles = Collections.unmodifiableList(bundles);
    return newBundles;
  }

  public int numberOfBundles()
  {
    int number = bundles.size();
    return number;
  }

  public boolean hasBundles()
  {
    boolean has = bundles.size() > 0;
    return has;
  }

  public int indexOfBundle(Bundle aBundle)
  {
    int index = bundles.indexOf(aBundle);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItemQuantities()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ItemQuantity addItemQuantity(int aDesiredAmountOfItems, Member aItemPicker, ClimbSafeSystem aClimbSafeSystem)
  {
    return new ItemQuantity(aDesiredAmountOfItems, aItemPicker, this, aClimbSafeSystem);
  }

  public boolean addItemQuantity(ItemQuantity aItemQuantity)
  {
    boolean wasAdded = false;
    if (itemQuantities.contains(aItemQuantity)) { return false; }
    EquipmentItem existingChosenItem = aItemQuantity.getChosenItem();
    boolean isNewChosenItem = existingChosenItem != null && !this.equals(existingChosenItem);
    if (isNewChosenItem)
    {
      aItemQuantity.setChosenItem(this);
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
    //Unable to remove aItemQuantity, as it must always have a chosenItem
    if (!this.equals(aItemQuantity.getChosenItem()))
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
      existingClimbSafeSystem.removeAllItem(this);
    }
    climbSafeSystem.addAllItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBundle(Bundle aBundle)
  {
    boolean wasAdded = false;
    if (bundles.contains(aBundle)) { return false; }
    bundles.add(aBundle);
    if (aBundle.indexOfItem(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBundle.addItem(this);
      if (!wasAdded)
      {
        bundles.remove(aBundle);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBundle(Bundle aBundle)
  {
    boolean wasRemoved = false;
    if (!bundles.contains(aBundle))
    {
      return wasRemoved;
    }

    int oldIndex = bundles.indexOf(aBundle);
    bundles.remove(oldIndex);
    if (aBundle.indexOfItem(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBundle.removeItem(this);
      if (!wasRemoved)
      {
        bundles.add(oldIndex,aBundle);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundleAt(Bundle aBundle, int index)
  {  
    boolean wasAdded = false;
    if(addBundle(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundleAt(Bundle aBundle, int index)
  {
    boolean wasAdded = false;
    if(bundles.contains(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundleAt(aBundle, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=itemQuantities.size(); i > 0; i--)
    {
      ItemQuantity aItemQuantity = itemQuantities.get(i - 1);
      aItemQuantity.delete();
    }
    ClimbSafeSystem placeholderClimbSafeSystem = climbSafeSystem;
    this.climbSafeSystem = null;
    if(placeholderClimbSafeSystem != null)
    {
      placeholderClimbSafeSystem.removeAllItem(this);
    }
    ArrayList<Bundle> copyOfBundles = new ArrayList<Bundle>(bundles);
    bundles.clear();
    for(Bundle aBundle : copyOfBundles)
    {
      if (aBundle.numberOfItems() <= Bundle.minimumNumberOfItems())
      {
        aBundle.delete();
      }
      else
      {
        aBundle.removeItem(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "weight" + ":" + getWeight()+ "," +
            "pricePerWeek" + ":" + getPricePerWeek()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafeSystem = "+(getClimbSafeSystem()!=null?Integer.toHexString(System.identityHashCode(getClimbSafeSystem())):"null");
  }
}