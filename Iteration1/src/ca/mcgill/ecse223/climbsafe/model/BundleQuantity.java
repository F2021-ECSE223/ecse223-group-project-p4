/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;

// line 72 "../../../../../ClimbSafe.ump"
public class BundleQuantity
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BundleQuantity Attributes
  private int desiredAmountOfBundles;

  //BundleQuantity Associations
  private Member bundlePicker;
  private Bundle chosenBundle;
  private ClimbSafeSystem climbSafeSystem;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetBundlePicker;
  private boolean canSetChosenBundle;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BundleQuantity(int aDesiredAmountOfBundles, Member aBundlePicker, Bundle aChosenBundle, ClimbSafeSystem aClimbSafeSystem)
  {
    cachedHashCode = -1;
    canSetBundlePicker = true;
    canSetChosenBundle = true;
    desiredAmountOfBundles = aDesiredAmountOfBundles;
    boolean didAddBundlePicker = setBundlePicker(aBundlePicker);
    if (!didAddBundlePicker)
    {
      throw new RuntimeException("Unable to create bundleQuantity due to bundlePicker. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddChosenBundle = setChosenBundle(aChosenBundle);
    if (!didAddChosenBundle)
    {
      throw new RuntimeException("Unable to create bundleQuantity due to chosenBundle. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafeSystem = setClimbSafeSystem(aClimbSafeSystem);
    if (!didAddClimbSafeSystem)
    {
      throw new RuntimeException("Unable to create bundleQuantity due to climbSafeSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDesiredAmountOfBundles(int aDesiredAmountOfBundles)
  {
    boolean wasSet = false;
    desiredAmountOfBundles = aDesiredAmountOfBundles;
    wasSet = true;
    return wasSet;
  }

  public int getDesiredAmountOfBundles()
  {
    return desiredAmountOfBundles;
  }
  /* Code from template association_GetOne */
  public Member getBundlePicker()
  {
    return bundlePicker;
  }
  /* Code from template association_GetOne */
  public Bundle getChosenBundle()
  {
    return chosenBundle;
  }
  /* Code from template association_GetOne */
  public ClimbSafeSystem getClimbSafeSystem()
  {
    return climbSafeSystem;
  }
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setBundlePicker(Member aBundlePicker)
  {
    boolean wasSet = false;
    if (!canSetBundlePicker) { return false; }
    if (aBundlePicker == null)
    {
      return wasSet;
    }

    Member existingBundlePicker = bundlePicker;
    bundlePicker = aBundlePicker;
    if (existingBundlePicker != null && !existingBundlePicker.equals(aBundlePicker))
    {
      existingBundlePicker.removeBundleQuantity(this);
    }
    if (!bundlePicker.addBundleQuantity(this))
    {
      bundlePicker = existingBundlePicker;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setChosenBundle(Bundle aChosenBundle)
  {
    boolean wasSet = false;
    if (!canSetChosenBundle) { return false; }
    if (aChosenBundle == null)
    {
      return wasSet;
    }

    Bundle existingChosenBundle = chosenBundle;
    chosenBundle = aChosenBundle;
    if (existingChosenBundle != null && !existingChosenBundle.equals(aChosenBundle))
    {
      existingChosenBundle.removeBundleQuantity(this);
    }
    if (!chosenBundle.addBundleQuantity(this))
    {
      chosenBundle = existingChosenBundle;
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
      existingClimbSafeSystem.removeBundleQuantity(this);
    }
    if (!climbSafeSystem.addBundleQuantity(this))
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

    BundleQuantity compareTo = (BundleQuantity)obj;
  
    if (getBundlePicker() == null && compareTo.getBundlePicker() != null)
    {
      return false;
    }
    else if (getBundlePicker() != null && !getBundlePicker().equals(compareTo.getBundlePicker()))
    {
      return false;
    }

    if (getChosenBundle() == null && compareTo.getChosenBundle() != null)
    {
      return false;
    }
    else if (getChosenBundle() != null && !getChosenBundle().equals(compareTo.getChosenBundle()))
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
    if (getBundlePicker() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getBundlePicker().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getChosenBundle() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getChosenBundle().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetBundlePicker = false;
    canSetChosenBundle = false;
    return cachedHashCode;
  }

  public void delete()
  {
    Member placeholderBundlePicker = bundlePicker;
    this.bundlePicker = null;
    if(placeholderBundlePicker != null)
    {
      placeholderBundlePicker.removeBundleQuantity(this);
    }
    Bundle placeholderChosenBundle = chosenBundle;
    this.chosenBundle = null;
    if(placeholderChosenBundle != null)
    {
      placeholderChosenBundle.removeBundleQuantity(this);
    }
    ClimbSafeSystem placeholderClimbSafeSystem = climbSafeSystem;
    this.climbSafeSystem = null;
    if(placeholderClimbSafeSystem != null)
    {
      placeholderClimbSafeSystem.removeBundleQuantity(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "desiredAmountOfBundles" + ":" + getDesiredAmountOfBundles()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bundlePicker = "+(getBundlePicker()!=null?Integer.toHexString(System.identityHashCode(getBundlePicker())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "chosenBundle = "+(getChosenBundle()!=null?Integer.toHexString(System.identityHashCode(getChosenBundle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafeSystem = "+(getClimbSafeSystem()!=null?Integer.toHexString(System.identityHashCode(getClimbSafeSystem())):"null");
  }
}