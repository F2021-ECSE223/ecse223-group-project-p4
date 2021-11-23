package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import ca.mcgill.ecse.climbsafe.controller.AssignmentController;

public class TripPaymentController {
  @FXML
  private Button payButtton;
  @FXML
  private TextField authCodeTextField;
  @FXML
  private TextField memberEmailTextField;

  // Event Listener on Button[#payButtton].onAction
  @FXML
  public void payForTrip(ActionEvent event) {

    try {
      AssignmentController.payForTrip(memberEmailTextField.getText(), authCodeTextField.getText());

      ViewUtils
          .showSuccess("Payment successfully processed for member " + memberEmailTextField.getText()
              + '\n' + "Authorization code: " + authCodeTextField.getText());

    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }

    authCodeTextField.clear();
    memberEmailTextField.clear();

  }
}
