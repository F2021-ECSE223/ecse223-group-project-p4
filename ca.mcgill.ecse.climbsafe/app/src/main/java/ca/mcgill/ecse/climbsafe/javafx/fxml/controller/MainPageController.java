package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.event.Event;
import javafx.fxml.FXML;

public class MainPageController {
  @FXML
  private BundleOperationsController bundleTabController;
  @FXML
  private MemberOperationsController memberTabController;
  @FXML
  private AssignmentOperationsController assignmentTabController;
  @FXML
  private ReviewOperationsController reviewTabController;
  @FXML
  private GuideOperationsController guideTabController;
  @FXML
  private EquipmentOperationsController equipmentTabController;

  @FXML
  /**
   * To refresh the member tab when pressed
   * 
   * @param event The action of pressing the tab
   * @author Wassim Jabbour
   */
  public void refreshMemberTab(Event event) {
    memberTabController.initialize();
  }

  /**
   * To refresh the equipment tab when pressed
   * 
   * @param event The action of pressing the tab
   * @author Wassim Jabbour
   */
  @FXML
  public void refreshEquipmentTab(Event event) {
    equipmentTabController.initialize();
  }

  /**
   * To refresh the bundle tab when pressed
   * 
   * @param event The action of pressing the tab
   * @author Wassim Jabbour
   */
  @FXML
  public void refreshBundleTab(Event event) {
    bundleTabController.initialize();
  }

  /**
   * To refresh the member tab when pressed
   * 
   * @param event
   * @author Wassim Jabbour
   */
  @FXML
  public void refreshAssignmentsTab(Event event) {
    assignmentTabController.initialize();
  }

  /**
   * To refresh the review tab when pressed
   * 
   * @param event The action of pressing the tab
   * @author Wassim Jabbour
   */
  @FXML
  public void refreshReviewTab(Event event) {
    reviewTabController.initialize();
  }

  /**
   * To refresh the guide tab when pressed
   * 
   * @param event The action of pressing the tab
   * @author Wassim Jabbour
   */
  @FXML
  public void refreshGuideTab(Event event) {
    guideTabController.initialize();
  }
}
