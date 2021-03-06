/* PLEASE DO NOT EDIT THIS CODE */
/* This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language! */

package ca.mcgill.ecse.climbsafe.controller;

/**
 * Modified to encapsulate extra features Author : Wassim Jabbour
 */
// line 5 "../../../../../ClimbSafeTransferObjects.ump"
public class TOAssignment {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // TOAssignment Attributes
  private String memberEmail;
  private String memberName;
  private String guideEmail;
  private String guideName;
  private String hotelName;
  private int startWeek;
  private int endWeek;
  private int totalCostForGuide;
  private int totalCostForEquipment;
  private String status;
  private String authorizationCode;
  private int refundedPercentageAmount;
  private String climbRating;
  private String climbComment;
  private String climbLocation;
  private int climbLength;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public TOAssignment(String aMemberEmail, String aMemberName, String aGuideEmail,
      String aGuideName, String aHotelName, int aStartWeek, int aEndWeek, int aTotalCostForGuide,
      int aTotalCostForEquipment, String aStatus, String aAuthorizationCode,
      int aRefundedPercentageAmount, String aClimbRating, String aClimbComment,
      String aClimbLocation, int aClimbLength) {
    memberEmail = aMemberEmail;
    memberName = aMemberName;
    guideEmail = aGuideEmail;
    guideName = aGuideName;
    hotelName = aHotelName;
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    totalCostForGuide = aTotalCostForGuide;
    totalCostForEquipment = aTotalCostForEquipment;
    status = aStatus;
    authorizationCode = aAuthorizationCode;
    refundedPercentageAmount = aRefundedPercentageAmount;
    climbRating = aClimbRating;
    climbComment = aClimbComment;
    climbLocation = aClimbLocation;
    climbLength = aClimbLength;
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setMemberEmail(String aMemberEmail) {
    boolean wasSet = false;
    memberEmail = aMemberEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setMemberName(String aMemberName) {
    boolean wasSet = false;
    memberName = aMemberName;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideEmail(String aGuideEmail) {
    boolean wasSet = false;
    guideEmail = aGuideEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideName(String aGuideName) {
    boolean wasSet = false;
    guideName = aGuideName;
    wasSet = true;
    return wasSet;
  }

  public boolean setHotelName(String aHotelName) {
    boolean wasSet = false;
    hotelName = aHotelName;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartWeek(int aStartWeek) {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(int aEndWeek) {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalCostForGuide(int aTotalCostForGuide) {
    boolean wasSet = false;
    totalCostForGuide = aTotalCostForGuide;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalCostForEquipment(int aTotalCostForEquipment) {
    boolean wasSet = false;
    totalCostForEquipment = aTotalCostForEquipment;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatus(String aStatus) {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setAuthorizationCode(String aAuthorizationCode) {
    boolean wasSet = false;
    authorizationCode = aAuthorizationCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setRefundedPercentageAmount(int aRefundedPercentageAmount) {
    boolean wasSet = false;
    refundedPercentageAmount = aRefundedPercentageAmount;
    wasSet = true;
    return wasSet;
  }

  public boolean setClimbRating(String aClimbRating) {
    boolean wasSet = false;
    climbRating = aClimbRating;
    wasSet = true;
    return wasSet;
  }

  public boolean setClimbComment(String aClimbComment) {
    boolean wasSet = false;
    climbComment = aClimbComment;
    wasSet = true;
    return wasSet;
  }

  public boolean setClimbLocation(String aClimbLocation) {
    boolean wasSet = false;
    climbLocation = aClimbLocation;
    wasSet = true;
    return wasSet;
  }

  public boolean setClimbLength(int aClimbLength) {
    boolean wasSet = false;
    climbLength = aClimbLength;
    wasSet = true;
    return wasSet;
  }

  public String getMemberEmail() {
    return memberEmail;
  }

  public String getMemberName() {
    return memberName;
  }

  public String getGuideEmail() {
    return guideEmail;
  }

  public String getGuideName() {
    return guideName;
  }

  public String getHotelName() {
    return hotelName;
  }

  public int getStartWeek() {
    return startWeek;
  }

  public int getEndWeek() {
    return endWeek;
  }

  public int getTotalCostForGuide() {
    return totalCostForGuide;
  }

  public int getTotalCostForEquipment() {
    return totalCostForEquipment;
  }

  public String getStatus() {
    return status;
  }

  public String getAuthorizationCode() {
    return authorizationCode;
  }

  public int getRefundedPercentageAmount() {
    return refundedPercentageAmount;
  }

  public String getClimbRating() {
    return climbRating;
  }

  public String getClimbComment() {
    return climbComment;
  }

  public String getClimbLocation() {
    return climbLocation;
  }

  public int getClimbLength() {
    return climbLength;
  }

  public void delete() {}


  public String toString() {
    return super.toString() + "[" + "memberEmail" + ":" + getMemberEmail() + "," + "memberName"
        + ":" + getMemberName() + "," + "guideEmail" + ":" + getGuideEmail() + "," + "guideName"
        + ":" + getGuideName() + "," + "hotelName" + ":" + getHotelName() + "," + "startWeek" + ":"
        + getStartWeek() + "," + "endWeek" + ":" + getEndWeek() + "," + "totalCostForGuide" + ":"
        + getTotalCostForGuide() + "," + "totalCostForEquipment" + ":" + getTotalCostForEquipment()
        + "," + "status" + ":" + getStatus() + "," + "authorizationCode" + ":"
        + getAuthorizationCode() + "," + "refundedPercentageAmount" + ":"
        + getRefundedPercentageAmount() + "," + "climbRating" + ":" + getClimbRating() + ","
        + "climbComment" + ":" + getClimbComment() + "," + "climbLocation" + ":"
        + getClimbLocation() + "," + "climbLength" + ":" + getClimbLength() + "]";
  }
}
