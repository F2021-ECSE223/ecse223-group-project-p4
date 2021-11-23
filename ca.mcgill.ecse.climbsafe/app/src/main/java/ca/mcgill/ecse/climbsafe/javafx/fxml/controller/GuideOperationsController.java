package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import javafx.event.ActionEvent;

public class GuideOperationsController {
  @FXML
  private TextField guidePassword;
  @FXML
  private Button registerGuideButton;
  @FXML
  private TextField guideEmail;
  @FXML
  private TextField guideName;
  @FXML
  private TextField guideContact;
  @FXML
  private TextField newGuideName;
  @FXML
  private Button updateGuideButton;
  @FXML
  private TextField newGuidePassword;
  @FXML
  private TextField newGuideContact;
  @FXML
  private TextField updtGuideEmail;
  @FXML
  private Button deleteGuideButton;
  @FXML
  private TextField rmGuideEmail;

  // Event Listener on Button[#registerGuideButton].onAction
  @FXML
  public void registerGuideAction(ActionEvent event) {
    if (!ViewUtils.isAlpha(guideName.getText())) {
      ViewUtils.showError("The entered guide name must only contain letters.");
      return;
    }
    try {
      ClimbSafeFeatureSet3Controller.registerGuide(guideEmail.getText(), guidePassword.getText(),
          guideName.getText(), guideContact.getText());
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Button[#updateGuideButton].onAction
  @FXML
  public void updateGuideAction(ActionEvent event) {
    if (!ViewUtils.isAlpha(newGuideName.getText())) {
      ViewUtils.showError("The entered guide name must only contain letters.");
      return;
    }
    try {
      ClimbSafeFeatureSet3Controller.updateGuide(updtGuideEmail.getText(),
          newGuidePassword.getText(), newGuideName.getText(), newGuideContact.getText());
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Button[#deleteGuideButton].onAction
  @FXML
  public void deleteGuideAction(ActionEvent event) {
    try {
      ClimbSafeFeatureSet1Controller.deleteGuide(rmGuideEmail.getText());
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }
}
