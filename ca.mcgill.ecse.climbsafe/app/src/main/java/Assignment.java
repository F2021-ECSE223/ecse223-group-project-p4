/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 1 "AssignmentProcess.ump"
public class Assignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private String authorizationCode;
  private int refundPercentage;

  //Assignment State Machines
  public enum AssignmentState { Assigned, Paid, Started, Finished, Cancelled }
  private AssignmentState assignmentState;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment()
  {
    authorizationCode = null;
    refundPercentage = 0;
    setAssignmentState(AssignmentState.Assigned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAuthorizationCode(String aAuthorizationCode)
  {
    boolean wasSet = false;
    authorizationCode = aAuthorizationCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setRefundPercentage(int aRefundPercentage)
  {
    boolean wasSet = false;
    refundPercentage = aRefundPercentage;
    wasSet = true;
    return wasSet;
  }

  public String getAuthorizationCode()
  {
    return authorizationCode;
  }

  public int getRefundPercentage()
  {
    return refundPercentage;
  }

  public String getAssignmentStateFullName()
  {
    String answer = assignmentState.toString();
    return answer;
  }

  public AssignmentState getAssignmentState()
  {
    return assignmentState;
  }

  public boolean pay(String authorizationCode)
  {
    boolean wasEventProcessed = false;
    
    AssignmentState aAssignmentState = assignmentState;
    switch (aAssignmentState)
    {
      case Assigned:
        if (isValidCode(getAuthorizationCode()))
        {
        // line 9 "AssignmentProcess.ump"
          setAuthorizationCode(authorizationCode.trim());
          setAssignmentState(AssignmentState.Paid);
          wasEventProcessed = true;
          break;
        }
        if (!(isValidCode(getAuthorizationCode())))
        {
        // line 10 "AssignmentProcess.ump"
          throwException("Invalid authorization code");
          setAssignmentState(AssignmentState.Assigned);
          wasEventProcessed = true;
          break;
        }
        break;
      case Paid:
        // line 21 "AssignmentProcess.ump"
        throwException("Trip has already been paid for");
        setAssignmentState(AssignmentState.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        // line 27 "AssignmentProcess.ump"
        throwException("Trip has already been paid for");
        setAssignmentState(AssignmentState.Started);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 32 "AssignmentProcess.ump"
        throwException("Cannot pay for a trip which has finished");
        setAssignmentState(AssignmentState.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 38 "AssignmentProcess.ump"
        throwException("Cannot pay for a trip which has been cancelled");
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    AssignmentState aAssignmentState = assignmentState;
    switch (aAssignmentState)
    {
      case Assigned:
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 19 "AssignmentProcess.ump"
        setRefundPercentage(50);
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        // line 26 "AssignmentProcess.ump"
        setRefundPercentage(10);
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 34 "AssignmentProcess.ump"
        throwException("Cannot cancel a trip which has finished");
        setAssignmentState(AssignmentState.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean start()
  {
    boolean wasEventProcessed = false;
    
    AssignmentState aAssignmentState = assignmentState;
    switch (aAssignmentState)
    {
      case Assigned:
        // line 14 "AssignmentProcess.ump"
        banMember();
        setAssignmentState(AssignmentState.Assigned);
        wasEventProcessed = true;
        break;
      case Paid:
        setAssignmentState(AssignmentState.Started);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 33 "AssignmentProcess.ump"
        throwException("Cannot start a trip which has finished");
        setAssignmentState(AssignmentState.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 39 "AssignmentProcess.ump"
        throwException("Cannot start a trip which has been cancelled");
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finish()
  {
    boolean wasEventProcessed = false;
    
    AssignmentState aAssignmentState = assignmentState;
    switch (aAssignmentState)
    {
      case Assigned:
        // line 15 "AssignmentProcess.ump"
        throwException("Cannot finish a trip which has not started");
        setAssignmentState(AssignmentState.Assigned);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 20 "AssignmentProcess.ump"
        throwException("Cannot finish a trip which has not started");
        setAssignmentState(AssignmentState.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        setAssignmentState(AssignmentState.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 40 "AssignmentProcess.ump"
        throwException("Cannot finish a trip which has been cancelled");
        setAssignmentState(AssignmentState.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setAssignmentState(AssignmentState aAssignmentState)
  {
    assignmentState = aAssignmentState;
  }

  public void delete()
  {}

  // line 45 "AssignmentProcess.ump"
   private boolean isValidCode(String authorizationCode){
    return !authorizationCode.trim().isEmpty();
  }

  // line 49 "AssignmentProcess.ump"
   private void throwException(String error){
    throw new RuntimeException(error);
  }

  // line 53 "AssignmentProcess.ump"
   private void banMember(){
    getMember().ban();
  }


  public String toString()
  {
    return super.toString() + "["+
            "authorizationCode" + ":" + getAuthorizationCode()+ "," +
            "refundPercentage" + ":" + getRefundPercentage()+ "]";
  }
}