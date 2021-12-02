package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ExtraFeaturesController;
import ca.mcgill.ecse.climbsafe.model.ClimbingPath;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ClimbingPathOperationsController {
  //Contains the options for the path difficulties 
  ObservableList<String> difficultyLevelList =
      FXCollections.observableArrayList("Easy", "Moderate", "Hard");

  @FXML
  private Tab addPathTab;
  @FXML
  private Button addNewPath;
  @FXML
  private TextField newPathDistance;
  @FXML
  private TextField addPathName;
  @FXML
  private ComboBox<String> addDifficultyCombo = new ComboBox<String>();
  @FXML
  private ListView<Label> pathList = new ListView<Label>();
  @FXML
  private ListView<Label> distList = new ListView<Label>();
  @FXML
  private ListView<Label> diffList = new ListView<Label>();
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
  private ComboBox<String> newDifficultyCombo = new ComboBox<String>();
  @FXML
  private ListView<Label> newPathList = new ListView<Label>();
  @FXML
  private ListView<Label> newDistList = new ListView<Label>();
  @FXML
  private ListView<Label> newDiffList = new ListView<Label>();
  @FXML
  private Tab DeletePathTab;
  @FXML
  private Button deleteExistingPath;
  @FXML
  private ListView<Label> rmPathList = new ListView<Label>();
  @FXML
  private ListView<Label> rmDistList = new ListView<Label>();
  @FXML
  private ListView<Label> rmDiffList = new ListView<Label>();

  /**
   * Sets the options for the difficulty and displays the available paths in the system on all
   * relevant pages
   * 
   * @author Tinetendo Makata
   */
  @FXML
  private void initialize() {
    // Displays all the existing paths
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
  /**
   * This initializes all the fields of the add path tab.
   * @param event  refers to whenever the DeletePath tab is accessed 
   * @author Tinetendo Makata 
   */
  public void RefreshSystemPaths1(Event event) {
    initialize();
  }

  // Event Listener on Button[#addNewPath].onAction
  @FXML
  /**
   * This method adds a path to the ClimbSafe system.
   * @param event refers to when the add path button is clicked 
   * @author Tinetendo Makata 
   */
  public void AddPath(ActionEvent event) {


    if (!ViewUtils.isAlpha(addPathName.getText())) {
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
      ExtraFeaturesController.addClimbingPath(addPathName.getText().trim(),
          Integer.parseInt(newPathDistance.getText()), addDifficultyCombo.getValue().toString());
      ViewUtils.showSuccess("Successfully added path \"" + addPathName.getText()
          + " \"with a distance of " + Integer.parseInt(newPathDistance.getText())
          + "\" and difficulty of " + addDifficultyCombo.getValue().toString() + " to the system");
      // Clears input fields
      addPathName.clear();
      newPathDistance.clear();
      addDifficultyCombo.getSelectionModel().clearSelection();
      initialize();
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }

  }

  // Event Listener on Tab[#UpdatePathTab].onSelectionChanged
  @FXML
  /**
   * This  initializes all the fields of the update path tab.
   * @param event  refers to whenever the DeletePath tab is accessed.
   * @author Tinetendo Makata 
   */
  public void RefreshSystemPaths2(Event event) {
    initialize();
  }

  /**
   * Called when the "Update" button is pressed, this method calls the controller method
   * updateClimbingPath() and passes user input taken form the Update Path Tab as parameters.
   *
   * @param event refers to when the update button is clicked.
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
      ExtraFeaturesController.updateClimbingPath(oldPathName.getText().trim(),
          updatedPathName.getText(), Integer.parseInt(updatedPathDistance.getText()),
          newDifficultyCombo.getValue().toString());
      ViewUtils.showSuccess("Successfully updated your path.");
      updatedPathName.clear();
      oldPathName.clear();
      updatedPathDistance.clear();
      newDifficultyCombo.getSelectionModel().clearSelection();
      initialize();

    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Tab[#DeletePathTab].onSelectionChanged
  @FXML
  /**
   * This  initializes all the fields of the delete  path tab.
   * @param event  refers to whenever the DeletePath tab is accessed 
   * @author Tinetendo Makata 
   */
  public void RefreshSystemPaths3(Event event) {
    initialize();
  }

  // Event Listener on Button[#deleteExistingPath].onAction
  @FXML
  /**
   * This method call the deleteClimbingPath() controller method and removes the path selected by
   * the user in the system.
   * 
   * @param event click the remove path button 
   * @author Tinetendo Makata
   */
  public void deletePath(ActionEvent event) {

    if (rmPathList.getSelectionModel().getSelectedItem() == null) {
      ViewUtils.showError("Please select a path to be deleted.");
      return;
    }
    try {
      String toBeDeleted = rmPathList.getSelectionModel().getSelectedItem().getText();

      ExtraFeaturesController.deleteClimbingPath(toBeDeleted);
      refreshPaths(rmPathList, rmDistList, rmDiffList);
      ViewUtils.showSuccess("Successfully deleted path \"" + toBeDeleted + "\" from the system.");
      rmPathList.getSelectionModel().clearSelection();
      initialize();
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on ListView[#rmPathList].onMouseClicked
  @FXML
  /**
   * Selects the corresponding difficulty and distance when a path is selected in the view list
   * 
   * @param event click of the path name in the path ListView 
   * @author Tinetendo Makata
   */
  public void selectedPath(MouseEvent event) {

    rmDiffList.getSelectionModel().select(rmPathList.getSelectionModel().getSelectedIndex());
    rmDistList.getSelectionModel().select(rmPathList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on ListView[#rmDistList].onMouseClicked
  @FXML
  /**
   * Selects the corresponding difficulty and path when a distance is selected in the view list
   * 
   * @param event click of distance in the remove path ListView 
   * @author Tinetendo Makata
   */
  public void selectedDistance(MouseEvent event) {

    rmPathList.getSelectionModel().select(rmDistList.getSelectionModel().getSelectedIndex());
    rmDiffList.getSelectionModel().select(rmDistList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on ListView[#rmDiffList].onMouseClicked
  @FXML
  /**
   * Selects the corresponding path and distance when a difficulty is selected in the view list
   * 
   * @param event click of a difficulty in the remove path listView 
   * @author Tinetendo Makata
   */
  public void selectedDifficulty(MouseEvent event) {

    rmPathList.getSelectionModel().select(rmDiffList.getSelectionModel().getSelectedIndex());
    rmDistList.getSelectionModel().select(rmDiffList.getSelectionModel().getSelectedIndex());

  }

  /**
   * Helper method, refreshes the inputed ListViews of path, distance and difficulty to display the
   * available paths in the system.
   * 
   * @param pathList List of paths in the system 
   * @param distList List of path distances 
   * @param diffList List of path difficulties
   * @author Tinetendo Makata
   */
  private void refreshPaths(ListView<Label> pathList, ListView<Label> distList,
      ListView<Label> diffList) {
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
