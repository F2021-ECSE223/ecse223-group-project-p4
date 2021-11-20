/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;
import java.util.*;

// line 42 "../../../../../ExtraFeatures.ump"
public class ClimbingGroup implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbingGroup Associations
  private List<Assignment> assignments;
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbingGroup(ClimbSafe aClimbSafe, Assignment... allAssignments)
  {
    assignments = new ArrayList<Assignment>();
    boolean didAddAssignments = setAssignments(allAssignments);
    if (!didAddAssignments)
    {
      throw new RuntimeException("Unable to create ClimbingGroup, must have at least 2 assignments. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create climbingGroup due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Assignment getAssignment(int index)
  {
    Assignment aAssignment = assignments.get(index);
    return aAssignment;
  }

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
    return 2;
  }
  /* Code from template association_AddMNToOptionalOne */
  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    ClimbingGroup existingClimbingGroup = aAssignment.getClimbingGroup();
    if (existingClimbingGroup != null && existingClimbingGroup.numberOfAssignments() <= minimumNumberOfAssignments())
    {
      return wasAdded;
    }
    else if (existingClimbingGroup != null)
    {
      existingClimbingGroup.assignments.remove(aAssignment);
    }
    assignments.add(aAssignment);
    setClimbingGroup(aAssignment,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignment(Assignment aAssignment)
  {
    boolean wasRemoved = false;
    if (assignments.contains(aAssignment) && numberOfAssignments() > minimumNumberOfAssignments())
    {
      assignments.remove(aAssignment);
      setClimbingGroup(aAssignment,null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToOptionalOne */
  public boolean setAssignments(Assignment... newAssignments)
  {
    boolean wasSet = false;
    if (newAssignments.length < minimumNumberOfAssignments())
    {
      return wasSet;
    }

    ArrayList<Assignment> checkNewAssignments = new ArrayList<Assignment>();
    HashMap<ClimbingGroup,Integer> climbingGroupToNewAssignments = new HashMap<ClimbingGroup,Integer>();
    for (Assignment aAssignment : newAssignments)
    {
      if (checkNewAssignments.contains(aAssignment))
      {
        return wasSet;
      }
      else if (aAssignment.getClimbingGroup() != null && !this.equals(aAssignment.getClimbingGroup()))
      {
        ClimbingGroup existingClimbingGroup = aAssignment.getClimbingGroup();
        if (!climbingGroupToNewAssignments.containsKey(existingClimbingGroup))
        {
          climbingGroupToNewAssignments.put(existingClimbingGroup, Integer.valueOf(existingClimbingGroup.numberOfAssignments()));
        }
        Integer currentCount = climbingGroupToNewAssignments.get(existingClimbingGroup);
        int nextCount = currentCount - 1;
        if (nextCount < 2)
        {
          return wasSet;
        }
        climbingGroupToNewAssignments.put(existingClimbingGroup, Integer.valueOf(nextCount));
      }
      checkNewAssignments.add(aAssignment);
    }

    assignments.removeAll(checkNewAssignments);

    for (Assignment orphan : assignments)
    {
      setClimbingGroup(orphan, null);
    }
    assignments.clear();
    for (Assignment aAssignment : newAssignments)
    {
      if (aAssignment.getClimbingGroup() != null)
      {
        aAssignment.getClimbingGroup().assignments.remove(aAssignment);
      }
      setClimbingGroup(aAssignment, this);
      assignments.add(aAssignment);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setClimbingGroup(Assignment aAssignment, ClimbingGroup aClimbingGroup)
  {
    try
    {
      java.lang.reflect.Field mentorField = aAssignment.getClass().getDeclaredField("climbingGroup");
      mentorField.setAccessible(true);
      mentorField.set(aAssignment, aClimbingGroup);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aClimbingGroup to aAssignment", e);
    }
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
      existingClimbSafe.removeClimbingGroup(this);
    }
    climbSafe.addClimbingGroup(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(Assignment aAssignment : assignments)
    {
      setClimbingGroup(aAssignment,null);
    }
    assignments.clear();
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeClimbingGroup(this);
    }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 48 "../../../../../ExtraFeatures.ump"
  private static final long serialVersionUID = 16L ;

  
}