package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import ca.mcgill.ecse.climbsafe.controller.ExtraFeaturesController;
import javafx.event.ActionEvent;

public class ClimbingPathOperationsController {
  @FXML
  private Button addNewPath;
  @FXML
  private TextField newPathName;
  @FXML
  private TextField newPathDifficulty;
  @FXML
  private TextField newPathDistance;
  @FXML
  private Button updateExistingPath;
  @FXML
  private TextField updatedPathDifficulty;
  @FXML
  private TextField updatedPathDistance;
  @FXML
  private TextField updatedPathName;
  @FXML
  private TextField oldPathName;
  @FXML
  private Button deleteExistingPath;
  @FXML
  private TextField pathToDelete;

  // Event Listener on Button[#addNewPath].onAction
  @FXML
  public void AddPath(ActionEvent event) {


    if (!ViewUtils.isAlpha(newPathName.getText())
        || !ViewUtils.isAlpha(newPathDifficulty.getText())) {
      ViewUtils.showError("The input must only contain letters.");
      return;
    }
    try {
      Integer.parseInt(newPathDistance.getText());
    } catch (Exception e) {
      ViewUtils.showError("The path distance can only be a number.");
      return;
    }

    try {
      ExtraFeaturesController.addClimbingPath(newPathName.getText(),
          Integer.parseInt(newPathDistance.getText()), newPathDifficulty.getText());
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Button[#updateExistingPath].onAction
  @FXML
  public void UpdatePath(ActionEvent event) {

    if (!ViewUtils.isAlpha(oldPathName.getText())
        || !ViewUtils.isAlpha(updatedPathDifficulty.getText())
        || !ViewUtils.isAlpha(updatedPathName.getText())) {
      ViewUtils.showError("The input must only contain letters.");
      return;
    }
    try {
      Integer.parseInt(updatedPathDistance.getText());
    } catch (Exception e) {
      ViewUtils.showError("The path distance can only be a number.");
      return;
    }
    try {
      ExtraFeaturesController.updateClimbingPath(oldPathName.getText(), updatedPathName.getText(),
          Integer.parseInt(updatedPathDistance.getText()), updatedPathDifficulty.getText());
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Button[#deleteExistingPath].onAction
  @FXML
  public void deletePath(ActionEvent event) {

    if (!ViewUtils.isAlpha(pathToDelete.getText())) {
      ViewUtils.showError("The input must only contain letters.");
      return;
    }
    try {
      ExtraFeaturesController.deleteClimbingPath(pathToDelete.getText());
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }

  }
}
