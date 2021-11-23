package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import ca.mcgill.ecse.climbsafe.controller.AssignmentController;


public class TripOperationsController {
  @FXML
  private Button startTripButton;
  @FXML
  private Button finishTripButton;
  @FXML
  private Button cancelTripButton;
  @FXML
  private TextField memberEmailTextField;
  @FXML
  private TextField weeknrTextField;

  // Event Listener on Button[#startTripButton].onAction
  @FXML
  public void startTrips(ActionEvent event) {

    int nrWeeks = -1;
    try {
      nrWeeks = Integer.parseInt(weeknrTextField.getText());

    } catch (Exception e) {
      ViewUtils.showError("The number of weeks must be an integer.");
      return;
    }

    try {
      AssignmentController.startTrips(nrWeeks);
      ViewUtils.showSuccess("All trips for week " + nrWeeks + " have started.");

    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
    weeknrTextField.clear();
  }

  // Event Listener on Button[#finishTripButton].onAction
  @FXML
  public void finishTrip(ActionEvent event) {
    try {
      AssignmentController.finishTrip(memberEmailTextField.getText());
      ViewUtils.showSuccess(
          "The trip was successfully finished for member " + memberEmailTextField.getText());

    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
    memberEmailTextField.clear();
  }

  // Event Listener on Button[#cancelTripButton].onAction
  @FXML
  public void cancelTrip(ActionEvent event) {
    try {
      AssignmentController.cancelTrip(memberEmailTextField.getText());
      ViewUtils.showSuccess(
          "The trip was successfully cancelled for member " + memberEmailTextField.getText());

    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
    memberEmailTextField.clear();
  }
}
