package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import java.sql.Date;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import javafx.event.ActionEvent;

import javafx.scene.control.DatePicker;

public class SetupNMCController {
  @FXML
  private Button initiateSeason;
  @FXML
  private TextField pricePerGuide;
  @FXML
  private DatePicker startDate;
  @FXML
  private TextField nrWeeks;

  // Event Listener on Button[#initiateSeason].onAction
  @FXML
  /**
   * Initiates the climbing season using the inputs on the page
   * @param event The event that is called the the initiate season button is pressed
   * @author Wassim Jabbour
   */
  public void initiateSeason(ActionEvent event) {

    int pricePerGuide;
    Date startDate;
    int nrWeeks;

    // Checking the start date was inputted
    if (this.startDate.getValue() == null) {
      ViewUtils.showError("Please choose a date");
      return;
    }

    // Checking the price per guide is an integer
    try {
      pricePerGuide = Integer.parseInt(this.pricePerGuide.getText());
    } catch (Exception e) {
      ViewUtils.showError("The price per guide must be an integer");
      return;
    }

    // Checking the date is in the future
    startDate = Date.valueOf(this.startDate.getValue());
    Date currentDate = Date.valueOf(java.time.LocalDate.now());

    if (startDate.compareTo(currentDate) < 0) {
      ViewUtils.showError("The start date must come after the current date");
      return;
    }

    // Checking the number of weeks is an integer
    try {
      nrWeeks = Integer.parseInt(this.nrWeeks.getText());
    } catch (Exception e) {
      ViewUtils.showError("The number of weeks must be an integer");
      return;
    }

    // Setting up the system and throwing any errors 
    try {
      ClimbSafeFeatureSet1Controller.setup(startDate, nrWeeks, pricePerGuide);
    } catch (InvalidInputException e) {
      ViewUtils.showError(e.getMessage());
      return;
    }

    // Throwing a success message and clearing all fields
    ViewUtils.showSuccess("The NMC system was setup successfully");
    this.startDate.getEditor().clear();
    this.nrWeeks.clear();
    this.pricePerGuide.clear();
  }
}
