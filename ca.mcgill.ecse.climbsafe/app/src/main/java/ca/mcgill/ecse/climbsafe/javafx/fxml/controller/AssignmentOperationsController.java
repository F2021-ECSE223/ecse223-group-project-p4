package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import java.util.List;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

public class AssignmentOperationsController {
	@FXML
	private Button initiateAssignment;
	@FXML
	private TableView assignmentTable;

	// Event Listener on Button[#initiateAssignment].onAction
	@FXML
	public void initiateSeason(ActionEvent event) {
	  try {
	    AssignmentController.initiateAssignment();
	  }
	  catch(InvalidInputException e) {
        ViewUtils.showError(e.getMessage());
        return;
      }
	}
	
	public void refreshTable() {
	    
	  List<TOAssignment> assignments = ClimbSafeFeatureSet6Controller.getAssignments();
	  
	  //TODO continue refresh table method
	}  
}
