package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AssignmentOperationsController {
  @FXML
  private Button initiateAssignment;
  @FXML
  private TableView assignmentTable;

  private String[] TOAssignmentProperties =
      {"memberEmail", "memberName", "guideEmail", "guideName", "hotelName", "startWeek", "endWeek",
          "totalCostForGuide", "totalCostForEquipment", "status", "authorizationCode",
          "refundedPercentageAmount", "climbRating", "climbComment", "climbLength"};

  // Event Listener on Button[#initiateAssignment].onAction
  @FXML
  public void initiateSeason(ActionEvent event) {
    try {
      AssignmentController.initiateAssignment();
    } catch (InvalidInputException e) {
      ViewUtils.showError(e.getMessage());
      return;
    }
  }

  public void refresh() {

    ObservableList<TOAssignment> data = FXCollections.observableArrayList();
    List<TOAssignment> assignments = ClimbSafeFeatureSet6Controller.getAssignments();

    for (TOAssignment assignment : assignments) {
      data.add(assignment);
    }

    for (int i = 0; i < assignmentTable.getColumns().size(); i++) {
      assignmentTable.getVisibleLeafColumn(i).setCellValueFactory(
          new PropertyValueFactory<TOAssignment, String>(TOAssignmentProperties[i]));
    }
    
    assignmentTable.setItems(data);



  }

}
