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
  private TextField guideFirstName;
  @FXML
  private TextField guideContact;
  @FXML
  private TextField guideLastName;
  @FXML
  private Button updateGuideButton;
  @FXML
  private TextField newGuidePassword;
  @FXML
  private TextField newGuideContact;
  @FXML
  private TextField updtateGuideEmail;
  @FXML
  private TextField updateGuideFirstName;
  @FXML
  private TextField updateGuideLastName;
  @FXML
  private Button deleteGuideButton;
  @FXML
  private TextField rmGuideEmail;



  // Event Listener on Button[#registerGuideButton].onAction
  @FXML
  /**
   * 
   * @param event
   */
  public void registerGuideAction(ActionEvent event) {

    // Get the required parameters from the UI
    String name = guideFirstName.getText() + guideLastName.getText();
    String email = guideEmail.getText();
    String password = guidePassword.getText();
    String emergency = guideContact.getText();

    // Check if information entered is alphanumeric
    if (!ViewUtils.isAlpha(name)) {
      ViewUtils.showError("The input must only contain letters.");
      return;
    }

    // Check if information entered is not empty
    if (name.equals("") || email.equals("") || password.equals("") || emergency.equals("")) {

      ViewUtils.showError("The input must not be empty.");
      return;

    }

    // Try to register the guide in the system
    try {
      ClimbSafeFeatureSet3Controller.registerGuide(email, password, name, emergency);
      clearFieldsInRegister();
      ViewUtils.showSuccess("The guide with email " + email + " was successfully registering.");

    } catch (Exception e) { // catch and output the error
      ViewUtils.showError(e.getMessage());
    }

  }

  // Event Listener on Button[#updateGuideButton].onAction
  @FXML
  /**
   * 
   * @param event
   */
  public void updateGuideAction(ActionEvent event) {

    // Get the required parameters from the UI
    String name = updateGuideFirstName.getText() + updateGuideLastName.getText();
    String email = updtateGuideEmail.getText();
    String password = newGuidePassword.getText();
    String emergency = newGuideContact.getText();

    // Check if information entered is alphanumeric
    if (!ViewUtils.isAlpha(name)) {
      ViewUtils.showError("The input must only contain letters.");
      return;
    }

    // Check if information entered is not empty
    if (name.equals("") || email.equals("") || password.equals("") || emergency.equals("")) {

      ViewUtils.showError("The input must not be empty.");
      return;

    }
    // Try to update the guide in the system
    try {
      ClimbSafeFeatureSet3Controller.updateGuide(email, password, name, emergency);
      clearFieldsInUpdate();
      ViewUtils.showSuccess("The guide with email " + email + " was successfully updated.");

    } catch (Exception e) { // catch and output the error
      ViewUtils.showError(e.getMessage());
    }

  }


  /**
   * 
   * @param event
   */
  // Event Listener on Button[#deleteGuideButton].onAction
  @FXML
  public void deleteGuideAction(ActionEvent event) {
    // Get the required parameters from the UI
    String email = rmGuideEmail.getText();


    // Check if information entered is not empty
    if (email.equals("")) {

      ViewUtils.showError("The input must not be empty.");
      return;

    }

    // Delete the Guide with the select email
    ClimbSafeFeatureSet1Controller.deleteGuide(email);
   
    //Show that the guide with the email was successfully deleted
    ViewUtils.showSuccess("The guide with email " + email + " was successfully deleted.");
    
    clearFieldsInDelete();

  }
  
  
  /**
   * 
   */
  private void clearFieldsInRegister() {
    guidePassword.clear();
    guideEmail.clear();
    guideFirstName.clear();
    guideContact.clear();
    guideLastName.clear();
  }

  /**
   * 
   * 
   */
  private void clearFieldsInUpdate() {
    newGuidePassword.clear();
    newGuideContact.clear();
    updtateGuideEmail.clear();
    updateGuideFirstName.clear();
    updateGuideLastName.clear();
  }

  /**
   * 
   */
  private void clearFieldsInDelete() {
    rmGuideEmail.clear();
  }

  
  
  
}
