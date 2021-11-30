package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.event.Event;

public class MainPageController {
  @FXML
  private BundleOperationsController bundleTabController;
  @FXML
  private MemberOperationsController memberTabController;
  @FXML
  private AssignmentOperationsController assignmentTabController;
  @FXML
  private ReviewOperationsController reviewTabController;

  // Event Listener on Tab.onSelectionChanged
  @FXML
  public void refreshMemberTab(Event event) {
    memberTabController.initialize();
  }

  // Event Listener on Tab.onSelectionChanged
  @FXML
  public void refreshBundleTab(Event event) {
    bundleTabController.initialize();
  }

  // Event Listener on Tab.onSelectionChanged
  @FXML
  public void refreshAssignmentsTab(Event event) {
    assignmentTabController.initialize();
  }

  // Event Listener on Tab.onSelectionChanged
  @FXML
  public void refreshReviewTab(Event event) {
    reviewTabController.initialize();
  }
}
