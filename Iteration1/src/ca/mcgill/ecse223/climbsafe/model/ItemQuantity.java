/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;

// line 78 "../../../../../ClimbSafe.ump"
public class ItemQuantity
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ItemQuantity Attributes
  private int desiredAmountOfItems;

  //ItemQuantity Associations
  private Member itemPicker;
  private EquipmentItem chosenItem;
  private ClimbSafeSystem climbSafeSystem;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetItemPicker;
  private boolean canSetChosenItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ItemQuantity(int aDesiredAmountOfItems, Member aItemPicker, EquipmentItem aChosenItem, ClimbSafeSystem aClimbSafeSystem)
  {
    cachedHashCode = -1;
    canSetItemPicker = true;
    canSetChosenItem = true;
    desiredAmountOfItems = aDesiredAmountOfItems;
    boolean didAddItemPicker = setItemPicker(aItemPicker);
    if (!didAddItemPicker)
    {
      throw new RuntimeException("Unable to create itemQuantity due to itemPicker. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddChosenItem = setChosenItem(aChosenItem);
    if (!didAddChosenItem)
    {
      throw new RuntimeException("Unable to create itemQuantity due to chosenItem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafeSystem = setClimbSafeSystem(aClimbSafeSystem);
    if (!didAddClimbSafeSystem)
    {
      throw new RuntimeException("Unable to create itemQuantity due to climbSafeSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDesiredAmountOfItems(int aDesiredAmountOfItems)
  {
    boolean wasSet = false;
    desiredAmountOfItems = aDesiredAmountOfItems;
    wasSet = true;
    return wasSet;
  }

  public int getDesiredAmountOfItems()
  {
    return desiredAmountOfItems;
  }
  /* Code from template association_GetOne */
  public Member getItemPicker()
  {
    return itemPicker;
  }
  /* Code from template association_GetOne */
  public EquipmentItem getChosenItem()
  {
    return chosenItem;
  }
  /* Code from template association_GetOne */
  public ClimbSafeSystem getClimbSafeSystem()
  {
    return climbSafeSystem;
  }
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setItemPicker(Member aItemPicker)
  {
    boolean wasSet = false;
    if (!canSetItemPicker) { return false; }
    if (aItemPicker == null)
    {
      return wasSet;
    }

    Member existingItemPicker = itemPicker;
    itemPicker = aItemPicker;
    if (existingItemPicker != null && !existingItemPicker.equals(aItemPicker))
    {
      existingItemPicker.removeItemQuantity(this);
    }
    if (!itemPicker.addItemQuantity(this))
    {
      itemPicker = existingItemPicker;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setChosenItem(EquipmentItem aChosenItem)
  {
    boolean wasSet = false;
    if (!canSetChosenItem) { return false; }
    if (aChosenItem == null)
    {
      return wasSet;
    }

    EquipmentItem existingChosenItem = chosenItem;
    chosenItem = aChosenItem;
    if (existingChosenItem != null && !existingChosenItem.equals(aChosenItem))
    {
      existingChosenItem.removeItemQuantity(this);
    }
    if (!chosenItem.addItemQuantity(this))
    {
      chosenItem = existingChosenItem;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToManyAssociationClass */
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
      existingClimbSafeSystem.removeItemQuantity(this);
    }
    if (!climbSafeSystem.addItemQuantity(this))
    {
      climbSafeSystem = existingClimbSafeSystem;
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

    ItemQuantity compareTo = (ItemQuantity)obj;
  
    if (getItemPicker() == null && compareTo.getItemPicker() != null)
    {
      return false;
    }
    else if (getItemPicker() != null && !getItemPicker().equals(compareTo.getItemPicker()))
    {
      return false;
    }

    if (getChosenItem() == null && compareTo.getChosenItem() != null)
    {
      return false;
    }
    else if (getChosenItem() != null && !getChosenItem().equals(compareTo.getChosenItem()))
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
    if (getItemPicker() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getItemPicker().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getChosenItem() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getChosenItem().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetItemPicker = false;
    canSetChosenItem = false;
    return cachedHashCode;
  }

  public void delete()
  {
    Member placeholderItemPicker = itemPicker;
    this.itemPicker = null;
    if(placeholderItemPicker != null)
    {
      placeholderItemPicker.removeItemQuantity(this);
    }
    EquipmentItem placeholderChosenItem = chosenItem;
    this.chosenItem = null;
    if(placeholderChosenItem != null)
    {
      placeholderChosenItem.removeItemQuantity(this);
    }
    ClimbSafeSystem placeholderClimbSafeSystem = climbSafeSystem;
    this.climbSafeSystem = null;
    if(placeholderClimbSafeSystem != null)
    {
      placeholderClimbSafeSystem.removeItemQuantity(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "desiredAmountOfItems" + ":" + getDesiredAmountOfItems()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "itemPicker = "+(getItemPicker()!=null?Integer.toHexString(System.identityHashCode(getItemPicker())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "chosenItem = "+(getChosenItem()!=null?Integer.toHexString(System.identityHashCode(getChosenItem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafeSystem = "+(getClimbSafeSystem()!=null?Integer.toHexString(System.identityHashCode(getClimbSafeSystem())):"null");
  }
}