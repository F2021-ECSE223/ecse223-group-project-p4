package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.User;
import javafx.event.ActionEvent;
import javafx.event.Event;

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
  @FXML
  private ListView<Label> guideList;
  @FXML
  private ListView<Label> guideEmailList;


  /**
   * Refresh everything that appears on the UI
   * 
   * @author Karl Rouhana
   */
  public void initialize() {
    // Clear the guide lists
    guideList.getItems().clear();
    guideEmailList.getItems().clear();

    // Add each guide to the list with his email and name
    for (Guide guide : ClimbSafeApplication.getClimbSafe().getGuides()) {
      guideList.getItems().add(new Label(guide.getName()));
      guideEmailList.getItems().add(new Label(guide.getEmail()));
    }
  }

  @FXML
  /**
   * Refresh the table of guides
   * 
   * @param event
   * @author Karl Rouhana
   */
  public void refreshGuides(Event event) {
    initialize();
  }

  // Event Listener on Button[#registerGuideButton].onAction
  @FXML
  /**
   * This method will register the info of a guide by calling the controller when the user presses
   * the button
   * 
   * @param event
   * @author Karl Rouhana
   */
  public void registerGuideAction(ActionEvent event) {

    // Get the required parameters from the UI
    String name = guideFirstName.getText() + " " + guideLastName.getText();
    String email = guideEmail.getText();
    String password = guidePassword.getText();
    String emergency = guideContact.getText();

    // Check if information entered is alphanumeric
    if (!ViewUtils.isAlpha(name)) {
      ViewUtils.showError("The input must only contain letters.");
      return;
    }

    // Check if information entered is not empty
    if (guideFirstName.getText().equals("") || guideLastName.getText().equals("")
        || email.equals("") || password.equals("") || emergency.equals("")) {

      ViewUtils.showError("The input must not be empty.");
      return;

    }

    // Try to register the guide in the system
    try {
      ClimbSafeFeatureSet3Controller.registerGuide(email, password, name, emergency);
      clearFieldsInRegister();
      ViewUtils.showSuccess("The guide with email " + email + " was successfully registering.");
      initialize();

    } catch (Exception e) { // catch and output the error
      ViewUtils.showError(e.getMessage());
    }

  }

  // Event Listener on Button[#updateGuideButton].onAction
  @FXML
  /**
   * This method will update the info of a guide by calling the controller when the user presses the
   * button
   * 
   * @param event
   * @author Karl Rouhana
   */
  public void updateGuideAction(ActionEvent event) {

    // Get the required parameters from the UI
    String name = updateGuideFirstName.getText() + " " + updateGuideLastName.getText();
    String email = updtateGuideEmail.getText();
    String password = newGuidePassword.getText();
    String emergency = newGuideContact.getText();

    // Check if information entered is alphanumeric
    if (!ViewUtils.isAlpha(name)) {
      ViewUtils.showError("The input must only contain letters.");
      return;
    }

    // Check if information entered is not empty
    if (updateGuideFirstName.getText().equals("") || updateGuideLastName.getText().equals("")
        || email.equals("") || password.equals("") || emergency.equals("")) {

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
   * This method will delete the guide by calling the controller when the user presses the button
   * 
   * @param event
   * @author Karl Rouhana
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

    // Return the User with the associated email
    User toBeDeleted = User.getWithEmail(email);

    // If the user exists and is a guide
    if (toBeDeleted != null && toBeDeleted instanceof Guide) {

      // Delete the guide
      ClimbSafeFeatureSet1Controller.deleteGuide(email);

      // Output that the guide has been deleted
      ViewUtils.showSuccess("Successfully deleted the guide with email " + email);
      clearFieldsInDelete();
    }
    // There's no guide with the input email
    else {
      ViewUtils.showError("The guide with email " + email + " does not exist.");
      return;
    }

  }

  /**
   * Private helper method that Clear all fields in the register Tab
   * 
   * @author Karl Rouhana
   */
  private void clearFieldsInRegister() {
    guidePassword.clear();
    guideEmail.clear();
    guideFirstName.clear();
    guideContact.clear();
    guideLastName.clear();
  }

  /**
   * Private helper method that Clear all fields in the update Tab
   * 
   * @author Karl Rouhana
   */
  private void clearFieldsInUpdate() {
    newGuidePassword.clear();
    newGuideContact.clear();
    updtateGuideEmail.clear();
    updateGuideFirstName.clear();
    updateGuideLastName.clear();
  }

  /**
   * Private helper method that Clear all fields in the delete Tab
   * 
   * @author Karl Rouhana
   */
  private void clearFieldsInDelete() {
    rmGuideEmail.clear();
  }



}
