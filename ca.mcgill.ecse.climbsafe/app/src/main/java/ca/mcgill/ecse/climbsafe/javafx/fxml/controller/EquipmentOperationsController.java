package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class EquipmentOperationsController {
  @FXML
  private Tab EqTab1;
  @FXML
  private TextField addEqWeight;
  @FXML
  private Button addEquipmentButton;
  @FXML
  private TextField addEqName;
  @FXML
  private TextField addEqPrice;
  @FXML
  private ListView<Label> SystemEquipment = new ListView<Label>();
  @FXML
  private Tab EqTab2;
  @FXML
  private TextField updtEqNewWeight;
  @FXML
  private Button updateEquipmentButton;
  @FXML
  private TextField updtEqNewName;
  @FXML
  private TextField updtEqNewPrice;
  @FXML
  private ListView<Label> SystemEquipment1 = new ListView<Label>();
  @FXML
  private TextField updtEqName;
  @FXML
  private Tab EqTab3;
  @FXML
  private Button deleteEquipmentButton;
  @FXML
  private ListView<Label> equipmentList = new ListView<Label>();

  // Event Listener on Tab[#EqTab1].onSelectionChanged
  @FXML
  /**
   * Refreshes the ListView present on the "Add Equipment" tab.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void initialize() {
    refreshEquipmentList(SystemEquipment);
  }

  public void RefreshSystemEquipment(Event event) {
    refreshEquipmentList(SystemEquipment);
  }

  // Event Listener on Button[#addEquipmentButton].onAction
  @FXML
  /**
   * Parses and collects User input in the add Equipment Tab, and passes it while calling a
   * controller method (addEquipment).
   * 
   * @param event
   * @author Adam Kazma
   */
  public void addEquipmentAction(ActionEvent event) {
    // Retrieving user input for equipment name
    String name = addEqName.getText();
    int weight = 0;
    int price = 0;
    // Checking that input for equipment name is alphabetic, if not throw an error.
    if (!ViewUtils.isAlpha(name)) {
      ViewUtils.showError("Equipment Name must only contain letters.");
      return;
    }
    // Checking that input for equipment weight is an integer, if not throw an error.
    try {
      weight = Integer.parseInt(addEqWeight.getText());
    } catch (Exception e) {
      ViewUtils.showError("Equipment Weight must be a number.");
      return;
    }
    // Checking that input for equipment price is an integer, if not throw an error.
    try {
      price = Integer.parseInt(addEqPrice.getText());
    } catch (Exception e) {
      ViewUtils.showError("Equipment Price must be a number.");
      return;
    }
    // Finally, calling the controller method with given user input, while showing any exception
    // thrown.
    try {
      ClimbSafeFeatureSet4Controller.addEquipment(name, weight, price);
      refreshEquipmentList(SystemEquipment);
      ViewUtils.showSuccess("Successfully added equipment \"" + name + "\" to the system.");
      // Clear input fields.
      addEqName.clear();
      addEqWeight.clear();
      addEqPrice.clear();
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Tab[#EqTab2].onSelectionChanged
  @FXML
  /**
   * Refreshes the ListView present on the "Update Equipment" tab.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void RefreshSystemEquipment2(Event event) {
    refreshEquipmentList(SystemEquipment1);
  }

  // Event Listener on Button[#updateEquipmentButton].onAction
  @FXML
  /**
   * Parses and collects user input in the "Update Equipment" tab. Then, the updateEquipment()
   * controller method is called, with user input passed as parameters.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void updateEquipmentAction(ActionEvent event) {
    String oldName = updtEqName.getText();
    String newName = updtEqNewName.getText();
    int weight = 0;
    int price = 0;
    // Checking if the entered old equipment name is alphabetic. If not, throw an error.
    if (!ViewUtils.isAlpha(oldName)) {
      ViewUtils.showError("Old Equipment Name must only contain letters.");
      return;
    }
    // Checking if the entered new equipment name is alphabetic. If not, throw an error.
    if (!ViewUtils.isAlpha(newName)) {
      ViewUtils.showError("New Equipment Name must only contain letters.");
      return;
    }
    try {
      // If the entered value into the weight field is not an Integer, we throw an error.
      weight = Integer.parseInt(updtEqNewWeight.getText());
    } catch (Exception e) {
      ViewUtils.showError("New Equipment Weight must be a number.");
      return;
    }
    try {
      // If the entered value into the price field is not an Integer, we throw an error.
      price = Integer.parseInt(updtEqNewPrice.getText());
    } catch (Exception e) {
      ViewUtils.showError("New Equipment Price must be a number.");
      return;
    }
    try {
      // We try calling the controller method, and pass the user input as parameters.
      ClimbSafeFeatureSet4Controller.updateEquipment(oldName, newName, weight, price);
      refreshEquipmentList(SystemEquipment1);
      ViewUtils
          .showSuccess("Successfully updated equipment \"" + oldName + "\" to the new equipment \""
              + newName + "\" with a weight of " + Integer.toString(weight) + " lbs and a price of "
              + Integer.toString(price) + " $/Week.");
      // Clear input fields after successful action.
      updtEqName.clear();
      updtEqNewName.clear();
      updtEqNewWeight.clear();
      updtEqNewPrice.clear();
    } catch (Exception e) {
      // Show thrown exception in a dialog box, if any.
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Tab[#EqTab3].onSelectionChanged
  @FXML
  /**
   * Refreshes the ListView present on the "Delete Equipment" tab.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void RefreshSystemEquipment3(Event event) {
    refreshEquipmentList(equipmentList);
  }

  // Event Listener on Button[#deleteEquipmentButton].onAction
  /**
   * Calls controller method "deleteEquipment()", passes the name of the equipment to be deleted.
   * 
   * @param event
   * @author Adam Kazma
   */
  @FXML
  public void deleteEquipmentAction(ActionEvent event) {
    // First, check if the user actually selected an equipment from the list in order to delete it.
    // If not, throw an error.
    String toBeDeleted = equipmentList.getSelectionModel().getSelectedItem().getText();
    if (toBeDeleted == null) {
      ViewUtils.showError("Please select an Equipment from the list.");
      return;
    }
    // Next, we split the selected Label's text to only get the equipment name.
    toBeDeleted = toBeDeleted.split(", ")[0];
    try {
      // We try calling the controller method, pass the required parameter.
      ClimbSafeFeatureSet6Controller.deleteEquipment(toBeDeleted);
      refreshEquipmentList(equipmentList);
      // Show successful action dialog.
      ViewUtils.showSuccess("Successfully deleted equipment \"" + toBeDeleted + "\".");
      // We clear the input fields after a successful action.
      equipmentList.getSelectionModel().clearSelection();
    } catch (Exception e) {
      // We show any thrown exception.
      ViewUtils.showError(e.getMessage());
    }
  }

  /**
   * Helper method, which refreshes a given ListView element to list all equipment in the system.
   * 
   * @param eqList
   * @author Adam Kazma
   */
  private void refreshEquipmentList(ListView<Label> eqList) {
    // First, clear ListView of old items.
    eqList.getItems().clear();
    // Next, loop over all equipment in the system. For each equipment found, attach its name,
    // weight and price as a String to a Label, and then add the Label to the ListView
    for (Equipment eqItem : ClimbSafeApplication.getClimbSafe().getEquipment()) {
      eqList.getItems().add(new Label(eqItem.getName() + ", " + Integer.toString(eqItem.getWeight())
          + " lbs, " + Integer.toString(eqItem.getPricePerWeek()) + "$"));
    }
    // Finally, refresh the visual ListView after the containing items are modified.
    eqList.refresh();
  }
}
