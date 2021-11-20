/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;
import java.util.*;

// line 19 "../../../../../ExtraFeatures.ump"
public class ClimbingPath implements Serializable
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Difficulty { Easy, Moderate, Hard }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, ClimbingPath> climbingpathsByLocation = new HashMap<String, ClimbingPath>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbingPath Attributes
  private String location;
  private int length;
  private Difficulty difficulty;

  //ClimbingPath Associations
  private List<Assignment> assignments;
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbingPath(String aLocation, int aLength, Difficulty aDifficulty, ClimbSafe aClimbSafe)
  {
    length = aLength;
    difficulty = aDifficulty;
    if (!setLocation(aLocation))
    {
      throw new RuntimeException("Cannot create due to duplicate location. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    assignments = new ArrayList<Assignment>();
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create climbingPath due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLocation(String aLocation)
  {
    boolean wasSet = false;
    String anOldLocation = getLocation();
    if (anOldLocation != null && anOldLocation.equals(aLocation)) {
      return true;
    }
    if (hasWithLocation(aLocation)) {
      return wasSet;
    }
    location = aLocation;
    wasSet = true;
    if (anOldLocation != null) {
      climbingpathsByLocation.remove(anOldLocation);
    }
    climbingpathsByLocation.put(aLocation, this);
    return wasSet;
  }

  public boolean setLength(int aLength)
  {
    boolean wasSet = false;
    length = aLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setDifficulty(Difficulty aDifficulty)
  {
    boolean wasSet = false;
    difficulty = aDifficulty;
    wasSet = true;
    return wasSet;
  }

  public String getLocation()
  {
    return location;
  }
  /* Code from template attribute_GetUnique */
  public static ClimbingPath getWithLocation(String aLocation)
  {
    return climbingpathsByLocation.get(aLocation);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithLocation(String aLocation)
  {
    return getWithLocation(aLocation) != null;
  }

  /**
   * In kilometers
   */
  public int getLength()
  {
    return length;
  }

  public Difficulty getDifficulty()
  {
    return difficulty;
  }
  /* Code from template association_GetMany */
  public Assignment getAssignment(int index)
  {
    Assignment aAssignment = assignments.get(index);
    return aAssignment;
  }

  /**
   * 0..1 to avoid changing the constructor of assignment which would mess up the step defintions
   */
  public List<Assignment> getAssignments()
  {
    List<Assignment> newAssignments = Collections.unmodifiableList(assignments);
    return newAssignments;
  }

  public int numberOfAssignments()
  {
    int number = assignments.size();
    return number;
  }

  public boolean hasAssignments()
  {
    boolean has = assignments.size() > 0;
    return has;
  }

  public int indexOfAssignment(Assignment aAssignment)
  {
    int index = assignments.indexOf(aAssignment);
    return index;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    ClimbingPath existingClimbingPath = aAssignment.getClimbingPath();
    if (existingClimbingPath == null)
    {
      aAssignment.setClimbingPath(this);
    }
    else if (!this.equals(existingClimbingPath))
    {
      existingClimbingPath.removeAssignment(aAssignment);
      addAssignment(aAssignment);
    }
    else
    {
      assignments.add(aAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignment(Assignment aAssignment)
  {
    boolean wasRemoved = false;
    if (assignments.contains(aAssignment))
    {
      assignments.remove(aAssignment);
      aAssignment.setClimbingPath(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignmentAt(Assignment aAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addAssignment(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignmentAt(Assignment aAssignment, int index)
  {
    boolean wasAdded = false;
    if(assignments.contains(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignmentAt(aAssignment, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setClimbSafe(ClimbSafe aClimbSafe)
  {
    boolean wasSet = false;
    if (aClimbSafe == null)
    {
      return wasSet;
    }

    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = aClimbSafe;
    if (existingClimbSafe != null && !existingClimbSafe.equals(aClimbSafe))
    {
      existingClimbSafe.removeClimbingPath(this);
    }
    climbSafe.addClimbingPath(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    climbingpathsByLocation.remove(getLocation());
    while( !assignments.isEmpty() )
    {
      assignments.get(0).setClimbingPath(null);
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeClimbingPath(this);
    }
  }

  // line 36 "../../../../../ExtraFeatures.ump"
   public static  void reinitializeUniquePath(List<ClimbingPath> climbingPaths){
    climbingpathsByLocation = new HashMap<String, ClimbingPath>();

    for (ClimbingPath climbingPath : climbingPaths) {
      climbingpathsByLocation.put(climbingPath.getLocation(), climbingPath);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "location" + ":" + getLocation()+ "," +
            "length" + ":" + getLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "difficulty" + "=" + (getDifficulty() != null ? !getDifficulty().equals(this)  ? getDifficulty().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 32 "../../../../../ExtraFeatures.ump"
  private static final long serialVersionUID = 15L ;

  
}