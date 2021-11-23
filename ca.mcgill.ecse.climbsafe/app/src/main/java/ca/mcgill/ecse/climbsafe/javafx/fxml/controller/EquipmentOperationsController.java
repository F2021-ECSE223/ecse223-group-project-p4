package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.control.Tab;

import javafx.event.Event;

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
  private ListView<Label> SystemEquipment;
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
  private ListView<Label> SystemEquipment1;
  @FXML
  private TextField updtEqName;
  @FXML
  private Tab EqTab3;
  @FXML
  private Button deleteEquipmentButton;
  @FXML
  private TextField rmEquipmentName;
  @FXML
  private ListView<Label> equipmentList;

  // Event Listener on Tab[#EqTab1].onSelectionChanged
  @FXML
  public void RefreshSystemEquipment(Event event) {
    refreshEquipmentList(SystemEquipment);
  }

  // Event Listener on Button[#addEquipmentButton].onAction
  @FXML
  public void addEquipmentAction(ActionEvent event) {
    String name = addEqName.getText();
    int weight = 0;
    int price = 0;
    if (!ViewUtils.isAlpha(name)) {
      ViewUtils.showError("Equipment Name must only contain letters.");
      return;
    }
    try {
      weight = Integer.parseInt(addEqWeight.getText());
    } catch (Exception e) {
      ViewUtils.showError("Equipment Weight must be a number.");
      return;
    }
    try {
      price = Integer.parseInt(addEqPrice.getText());
    } catch (Exception e) {
      ViewUtils.showError("Equipment Price must be a number.");
      return;
    }
    try {
      ClimbSafeFeatureSet4Controller.addEquipment(name, weight, price);
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Tab[#EqTab2].onSelectionChanged
  @FXML
  public void RefreshSystemEquipment2(Event event) {
    refreshEquipmentList(SystemEquipment1);
  }

  // Event Listener on Button[#updateEquipmentButton].onAction
  @FXML
  public void updateEquipmentAction(ActionEvent event) {
    String oldName = updtEqName.getText();
    String newName = updtEqNewName.getText();
    int weight = 0;
    int price = 0;
    if (!ViewUtils.isAlpha(oldName)) {
      ViewUtils.showError("Old Equipment Name must only contain letters.");
      return;
    }
    if (!ViewUtils.isAlpha(newName)) {
      ViewUtils.showError("New Equipment Name must only contain letters.");
      return;
    }
    try {
      weight = Integer.parseInt(updtEqNewWeight.getText());
    } catch (Exception e) {
      ViewUtils.showError("New Equipment Weight must be a number.");
      return;
    }
    try {
      price = Integer.parseInt(updtEqNewPrice.getText());
    } catch (Exception e) {
      ViewUtils.showError("New Equipment Price must be a number.");
      return;
    }
    try {
      ClimbSafeFeatureSet4Controller.updateEquipment(oldName, newName, weight, price);
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Tab[#EqTab3].onSelectionChanged
  @FXML
  public void RefreshSystemEquipment3(Event event) {
    refreshEquipmentList(equipmentList);
  }

  // Event Listener on Button[#deleteEquipmentButton].onAction
  @FXML
  public void deleteEquipmentAction(ActionEvent event) {
    String toBeDeleted = rmEquipmentName.getText();
    if (!ViewUtils.isAlpha(toBeDeleted)) {
      ViewUtils.showError("Equipment Name must obly contain letters.");
      return;
    }
    try {
      ClimbSafeFeatureSet6Controller.deleteEquipment(toBeDeleted);
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  private void refreshEquipmentList(ListView<Label> eqList) {
    eqList.setItems(null);
    for (BookableItem eqItem : ClimbSafeApplication.getClimbSafe().getEquipment()) {
      eqList.getItems().add(new Label(eqItem.getName()));
    }
    eqList.refresh();
  }
}
