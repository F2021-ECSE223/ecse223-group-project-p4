package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import java.lang.reflect.Array;
import java.util.List;
import ca.mcgill.ecse.climbsafe.controller.ExtraFeaturesController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

public class ReviewOperationsController {
  @FXML
  private Button submitTripReview;
  @FXML
  private TextField memberEmailTripReview;
  @FXML
  private TextField commentTripReview;


  private String[] rating = {"VeryPoor", "Poor", "Neutral", "Good", "VeryGood"};

  @FXML
  private ComboBox<String> ratingTripReview;


  public void initialize() {
    ratingTripReview.getItems().clear();
    for (int i = 0; i < 5; i++) {
      ratingTripReview.getItems().add(rating[i]);
    }
  }

  // Event Listener on Button[#submitTripReview].onAction
  @FXML
  public void submitReviewAction(ActionEvent event) {

    ratingTripReview.setItems(FXCollections.observableArrayList(rating));

    if (memberEmailTripReview.getText().isBlank()) {
      ViewUtils.showError("Please enter an email address.");
      return;
    }

    if (ratingTripReview.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please select a rating.");
      return;
    }
    try {
      ExtraFeaturesController.rateClimb(memberEmailTripReview.getText(),
          ratingTripReview.getValue(), commentTripReview.getText());
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
      return;
    }

    ViewUtils.showSuccess("Your review was successfully submitted.");
  }
}
