package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import java.util.List;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.ExtraFeaturesController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class AssignmentOperationsController {
  @FXML
  private Button initiateAssignment;
  @FXML
  private TableView assignmentTable;
  @FXML
  private TableColumn memberNameCol;
  @FXML
  private TableColumn memberEmailCol;
  @FXML
  private TableColumn guideNameCol;
  @FXML
  private TableColumn guideEmailCol;
  @FXML
  private TableColumn hotelNameCol;
  @FXML
  private TableColumn startWeekCol;
  @FXML
  private TableColumn endWeekCol;
  @FXML
  private TableColumn guideCostCol;
  @FXML
  private TableColumn equipmentCostCol;
  @FXML
  private TableColumn statusCol;
  @FXML
  private TableColumn authorizationCodeCol;
  @FXML
  private TableColumn refundCol;
  @FXML
  private TableColumn ratingCol;
  @FXML
  private TableColumn commentCol;
  @FXML
  private TableColumn climbLocationCol;
  @FXML
  private TableColumn climbLengthCol;


  // Event Listener on Button[#initiateAssignment].onAction
  @FXML
  /**
   * Initiates the assignments for all members
   * 
   * @param event The event that is triggered when the initiate button is pressed
   * @author Wassim Jabbour
   */
  public void initiateAssignments(ActionEvent event) {

    // Initiates the assignments and catches any errors
    try {
      AssignmentController.initiateAssignment();
    } catch (InvalidInputException e) {
      ViewUtils.showError(e.getMessage());
      // In case the assignments were already initiated, we don't want to reassign hotels so we
      // return
      if (e.getMessage().equals("Assignments were already initiated for the current season"))
        return;
      // If the assignments were partial we still want to refresh
      ExtraFeaturesController.setHotelsAndClimbingPaths();
      initialize();
      return;
    }

    ExtraFeaturesController.setHotelsAndClimbingPaths();
    initialize();
    ViewUtils.showSuccess("Assignments initiated successfully.");
  }

  @SuppressWarnings("unchecked")
  /**
   * Refreshes the table, called every time the tab is pressed or when the season is initiated
   * 
   * @author Wassim Jabbour
   */
  public void initialize() {

    List<TOAssignment> assignments = ClimbSafeFeatureSet6Controller.getAssignments();

    memberNameCol.setCellValueFactory(new PropertyValueFactory<TOAssignment, String>("memberName"));
    memberEmailCol
        .setCellValueFactory(new PropertyValueFactory<TOAssignment, String>("memberEmail"));
    guideNameCol.setCellValueFactory(new PropertyValueFactory<TOAssignment, String>("guideName"));
    guideEmailCol.setCellValueFactory(new PropertyValueFactory<TOAssignment, String>("guideEmail"));
    hotelNameCol.setCellValueFactory(new PropertyValueFactory<TOAssignment, String>("hotelName"));
    startWeekCol.setCellValueFactory(new PropertyValueFactory<TOAssignment, Integer>("startWeek"));
    endWeekCol.setCellValueFactory(new PropertyValueFactory<TOAssignment, Integer>("endWeek"));
    guideCostCol
        .setCellValueFactory(new PropertyValueFactory<TOAssignment, Integer>("totalCostForGuide"));
    equipmentCostCol.setCellValueFactory(
        new PropertyValueFactory<TOAssignment, Integer>("totalCostForEquipment"));
    statusCol.setCellValueFactory(new PropertyValueFactory<TOAssignment, String>("status"));
    authorizationCodeCol
        .setCellValueFactory(new PropertyValueFactory<TOAssignment, String>("authorizationCode"));
    refundCol.setCellValueFactory(
        new PropertyValueFactory<TOAssignment, Integer>("refundedPercentageAmount"));
    ratingCol.setCellValueFactory(new PropertyValueFactory<TOAssignment, String>("climbRating"));
    commentCol.setCellValueFactory(new PropertyValueFactory<TOAssignment, String>("climbComment"));
    climbLocationCol
        .setCellValueFactory(new PropertyValueFactory<TOAssignment, String>("climbLocation"));
    climbLengthCol
        .setCellValueFactory(new PropertyValueFactory<TOAssignment, Integer>("climbLength"));

    assignmentTable.getItems().setAll(assignments);

  }
}
