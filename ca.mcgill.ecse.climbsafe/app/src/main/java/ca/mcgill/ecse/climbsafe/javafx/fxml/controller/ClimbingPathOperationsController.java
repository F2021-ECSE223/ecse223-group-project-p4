package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ExtraFeaturesController;
import ca.mcgill.ecse.climbsafe.model.ClimbingPath;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

import javafx.scene.input.MouseEvent;

import javafx.event.Event;

public class ClimbingPathOperationsController {
	ObservableList<String> difficultyLevelList = FXCollections.observableArrayList("Easy", "Moderate", "Hard"); 
    
	@FXML
	private Tab addPathTab;
	@FXML
	private Button addNewPath;
	@FXML
	private TextField newPathDistance;
	@FXML
	private TextField addPathName;
	@FXML
	private ComboBox<String> addDifficultyCombo;
	@FXML
	private ListView<Label>pathList;
	@FXML
	private ListView<Label> distList;
	@FXML
	private ListView<Label> diffList;
	@FXML
	private Tab UpdatePathTab;
	@FXML
	private Button updateExistingPath;
	@FXML
	private TextField updatedPathDistance;
	@FXML
	private TextField updatedPathName;
	@FXML
	private TextField oldPathName;
	@FXML
	private ComboBox<String> newDifficultyCombo;
	@FXML
	private ListView<Label> newPathList;
	@FXML
	private ListView<Label>newDistList;
	@FXML
	private ListView<Label> newDiffList;
	@FXML
	private Tab DeletePathTab;
	@FXML
	private Button deleteExistingPath;
	@FXML
	private ListView <Label>rmPathList;
	@FXML
	private ListView <Label> rmDistList;
	@FXML
	private ListView <Label> rmDiffList;
	/**
	 * Sets the options for the difficulty and displays the available paths in the system on all relevant pages 
	 * 
	 * @author Tinetendo Makata
	 */
	@FXML
	private void initialize() {
	  //Displays all the existing paths 
	  refreshPaths(rmPathList, rmDistList, rmDiffList);
	  refreshPaths(newPathList, newDistList, newDiffList);
	  refreshPaths(pathList, distList, diffList);
	  
	  addDifficultyCombo.setValue("Easy");
	  addDifficultyCombo.setItems(difficultyLevelList);
	  
	  newDifficultyCombo.setValue("Easy");
	  newDifficultyCombo.setItems(difficultyLevelList);
	  

	}

	// Event Listener on Tab[#addPathTab].onSelectionChanged
	@FXML
	public void RefreshSystemPaths1(Event event) {
	initialize();
	}
	// Event Listener on Button[#addNewPath].onAction
	@FXML
	  public void AddPath(ActionEvent event) {


	    if (!ViewUtils.isAlpha(addPathName.getText()))
	         {
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
	      ExtraFeaturesController.addClimbingPath(addPathName.getText(),
	          Integer.parseInt(newPathDistance.getText()), addDifficultyCombo.getValue().toString());
	      ViewUtils.showSuccess("Successfully added bundle \"" + addPathName.getText()+"\"with a distance of "
	          + Integer.parseInt(newPathDistance.getText())+"\" and difficulty of "+ addDifficultyCombo.getValue().toString()+"to the system");
	      //Clear input fields 
	      addPathName.clear();
	      newPathDistance.clear();
	      addDifficultyCombo.getSelectionModel().clearSelection();
	    } catch (Exception e) {
	      ViewUtils.showError(e.getMessage());
	    }
	    
	  }

	// Event Listener on Tab[#UpdatePathTab].onSelectionChanged
	@FXML
	public void RefreshSystemPaths2(Event event) {
	initialize();
	}
/**
 *Called when the "Update" button is pressed, this method calls the controller method 
 *updateClimbingPath() and passes user input taken form the Update Path Tab as parameters. 
 *
 * @param event
 * @author Tinetendo Makata
 */
	// Event Listener on Button[#updateExistingPath].onAction
	@FXML
	  public void UpdatePath(ActionEvent event) {

	    if (!ViewUtils.isAlpha(oldPathName.getText())
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
	          Integer.parseInt(updatedPathDistance.getText()), newDifficultyCombo.getValue().toString());
	      ViewUtils.showSuccess("Successfully updated your bundle.");
	      updatedPathName.clear();
	      oldPathName.clear();
	      updatedPathDistance.clear();
	      newDifficultyCombo.getSelectionModel().clearSelection();
	      
	    } catch (Exception e) {
	      ViewUtils.showError(e.getMessage());
	    }
	  }
	// Event Listener on Tab[#DeletePathTab].onSelectionChanged
	@FXML
	public void RefreshSystemPaths3(Event event) {
		initialize();
	}
	// Event Listener on Button[#deleteExistingPath].onAction
	@FXML
	/**
	 * This method call the deleteClimbingPath() controller method 
	 * and removes the path selected by the user in the system.
	 * 
	 * @param event
	 * @author Tinetendo Makata
	 */
	public void deletePath(ActionEvent event) {
		
	  if (rmPathList.getSelectionModel().getSelectedItem()==null) {
	    ViewUtils.showError("Please select a path to be deleted.");
	    return;
	  }try {
	    String toBeDeleted = rmPathList.getSelectionModel().getSelectedItem().getText();
	    System.out.println(toBeDeleted);
	    ExtraFeaturesController.deleteClimbingPath(toBeDeleted);
	    refreshPaths(rmPathList, rmDistList, rmDiffList);
	    ViewUtils.showSuccess("Successfully deleted path \"" + toBeDeleted+"\" from the system.");
	    rmPathList.getSelectionModel().clearSelection();
	  }catch(Exception e) {
	    ViewUtils.showError(e.getMessage());
	  }
	}
	// Event Listener on ListView[#rmPathList].onMouseClicked
	@FXML
	/**
	 *Selects the corresponding difficulty and distance when a path is selected in the view list 
	 * @param event
	 * @author Tinetendo Makata 
	 */
	public void selectedPath(MouseEvent event) {
		
	  rmDiffList.getSelectionModel().select(rmPathList.getSelectionModel().getSelectedIndex());
	  rmDistList.getSelectionModel().select(rmPathList.getSelectionModel().getSelectedIndex());
	}
	// Event Listener on ListView[#rmDistList].onMouseClicked
	@FXML
	   /**
     *Selects the corresponding difficulty and path when a distance is selected in the view list 
     * @param event
     * @author Tinetendo Makata 
     */
	public void selectedDistance(MouseEvent event) {
	
	  rmPathList.getSelectionModel().select(rmDistList.getSelectionModel().getSelectedIndex());
	  rmDiffList.getSelectionModel().select(rmDistList.getSelectionModel().getSelectedIndex());
	}
	// Event Listener on ListView[#rmDiffList].onMouseClicked
	@FXML
	   /**
     *Selects the corresponding path and distance when a difficulty is selected in the view list 
     * @param event
     * @author Tinetendo Makata 
     */
	public void selectedDifficulty(MouseEvent event) {
		
	  rmPathList.getSelectionModel().select(rmDiffList.getSelectionModel().getSelectedIndex());
	  rmDistList.getSelectionModel().select(rmDiffList.getSelectionModel().getSelectedIndex());
	     
	}
	
	/**
	 * Helper method, refreshes the inputed ListViews of path, distance and difficulty to display the available paths in the system.
	 * @param pathList
	 * @param distList
	 * @param diffList
	 * @author Tinetendo Makata
	 */
	
	private void refreshPaths(ListView<Label> pathList, ListView<Label> distList, ListView<Label> diffList ) {
	  pathList.getItems().clear();
	  distList.getItems().clear();
	  diffList.getItems().clear();
	  
	  for (ClimbingPath path : ClimbSafeApplication.getClimbSafe().getClimbingPaths()) {
	    pathList.getItems().add(new Label(path.getLocation()));
	    distList.getItems().add(new Label(Integer.toString(path.getLength())));
	    diffList.getItems().add(new Label(path.getDifficulty().toString()));
	  }
	}
}
 