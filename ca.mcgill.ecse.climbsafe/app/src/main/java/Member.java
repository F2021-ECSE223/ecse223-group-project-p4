/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 57 "AssignmentProcess.ump"
public class Member
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member State Machines
  public enum MemberState { Authorized, Banned }
  private MemberState memberState;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member()
  {
    setMemberState(MemberState.Authorized);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getMemberStateFullName()
  {
    String answer = memberState.toString();
    return answer;
  }

  public MemberState getMemberState()
  {
    return memberState;
  }

  public boolean ban()
  {
    boolean wasEventProcessed = false;
    
    MemberState aMemberState = memberState;
    switch (aMemberState)
    {
      case Authorized:
        setMemberState(MemberState.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setMemberState(MemberState aMemberState)
  {
    memberState = aMemberState;
  }

  public void delete()
  {}

}