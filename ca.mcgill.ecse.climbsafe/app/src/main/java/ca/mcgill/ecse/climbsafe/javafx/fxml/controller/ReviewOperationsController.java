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
  /**
   * A method executed when the button submit is pressed in the review trip page. There are 4 cases:
   * 
   * Case 1: the text field to enter the email is empty, a popup that asks to enter the email of the
   * member appears. Nothing is saved.
   * 
   * Case 2: the user does not select a rating, a popup that asks to enter the rating appears.
   * Nothing is saved.
   * 
   * Case 3: the method rateClimb in the extra features controller throws an exception if the input
   * email does not exist in the system (the email of the member). If the input email does not
   * exist, the exception is catched and a popup that tells that the email does not exist in the
   * system appears. Nothing is saved.
   * 
   * Case 4: if none of the three cases above happen, the method rateClimb in the extra features
   * controller will not throw any exception and will save all informations entered in the page.
   * Also, a popup will show the success.
   * 
   * @author Ralph Nassar
   * @param event
   */
  public void submitReviewAction(ActionEvent event) {

    ratingTripReview.setItems(FXCollections.observableArrayList(rating));

    if (memberEmailTripReview.getText().isBlank()) {  // if no email is entered
      ViewUtils.showError("Please enter an email address."); // popup
    }

    if (ratingTripReview.getSelectionModel().isEmpty()) { // if no rating is selected
      ViewUtils.showError("Please select a rating."); // popup
      return;
    }
    try {
      ExtraFeaturesController.rateClimb(memberEmailTripReview.getText(),
          ratingTripReview.getValue(), commentTripReview.getText());
    } catch (Exception e) {  // email was not found in the system
      ViewUtils.showError(e.getMessage());  // popup
      return;
    }

    ViewUtils.showSuccess("Your review was successfully submitted.");  // popup showing the success
  }
}
