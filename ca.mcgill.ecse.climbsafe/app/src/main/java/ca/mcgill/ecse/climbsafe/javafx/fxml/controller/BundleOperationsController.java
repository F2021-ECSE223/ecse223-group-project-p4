package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import java.util.*;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

import javafx.scene.input.MouseEvent;

import javafx.event.Event;

public class BundleOperationsController {
  @FXML
  private Tab BundleTab1;
  @FXML
  private Button addBundleButton;
  @FXML
  private TextField addBundleName;
  @FXML
  private TextField addBundleDiscount;
  @FXML
  private Button addItemButton;
  @FXML
  private TextField addItemQuantities;
  @FXML
  private ComboBox<String> addItemCombo;
  @FXML
  private Button rmItemButton;
  @FXML
  private ListView<Label> bundleEqList;
  @FXML
  private ListView<Label> bundleEqQtList;
  @FXML
  private Tab BundleTab2;
  @FXML
  private Button updateBundleButton;
  @FXML
  private TextField newBundleName;
  @FXML
  private TextField newBundleDiscount;
  @FXML
  private TextField oldBundleName;
  @FXML
  private Button addItemButton2;
  @FXML
  private TextField newItemQuantity;
  @FXML
  private ComboBox<String> newItemCombo;
  @FXML
  private Button rmItemButton2;
  @FXML
  private ListView<Label> newEqList;
  @FXML
  private ListView<Label> newQtList;
  @FXML
  private Tab BundleTab3;
  @FXML
  private Button deleteBundleButton;
  @FXML
  private ListView<Label> rmDiscountList;
  @FXML
  private ListView<Label> rmBundleList;
  private ArrayList<String> equipmentToBeAdded = new ArrayList<String>();
  private ArrayList<String> quantitiesToBeAdded = new ArrayList<String>();
  private ArrayList<String> equipmentToBeUpdated = new ArrayList<String>();
  private ArrayList<String> quantitiesToBeUpdated = new ArrayList<String>();

  @FXML
  public void initialize() {
    List<String> equipment = getEquipment();
    if (equipment.size() == 0) {
      addItemCombo.getItems().clear();
      newItemCombo.getItems().clear();
    } else {
      addItemCombo.setItems(FXCollections.observableList(equipment));
      newItemCombo.setItems(FXCollections.observableList(equipment));
    }
  }

  // Event Listener on Tab[#BundleTab1].onSelectionChanged
  @FXML
  public void RefreshSystemBundles(Event event) {
    initialize();
  }

  // Event Listener on Button[#addBundleButton].onAction
  @FXML
  public void addBundleAction(ActionEvent event) {
    String name = addBundleName.getText();
    if (!ViewUtils.isAlpha(name)) {
      ViewUtils.showError("Bundle name can only contain letters.");
      return;
    }
    int discount = 0;
    try {
      discount = Integer.parseInt(addBundleDiscount.getText());
    } catch (Exception e) {
      ViewUtils.showError("Discount can only be an integer.");
      return;
    }

    try {
      ClimbSafeFeatureSet5Controller.addEquipmentBundle(name, discount, equipmentToBeAdded,
          stringListToInteger(quantitiesToBeAdded));
      ViewUtils.showSuccess("Successfully added bundle \"" + name + "\" with a discount of \""
          + Integer.toString(discount) + "\" to the system.");
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Button[#addItemButton].onAction
  @FXML
  public void addItem(ActionEvent event) {
    if (addItemCombo.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please Select an Equipment from the List.");
      return;
    }
    int quantity = 0;
    try {
      quantity = Integer.parseInt(addItemQuantities.getText());
    } catch (Exception e) {
      ViewUtils.showError("Equipment Quantity must be a number.");
      return;
    }
    equipmentToBeAdded.add(addItemCombo.getValue());
    quantitiesToBeAdded.add(Integer.toString(quantity));

    refreshListView(bundleEqList, equipmentToBeAdded);
    refreshListView(bundleEqQtList, quantitiesToBeAdded);
  }

  // Event Listener on Button[#rmItemButton].onAction
  @FXML
  public void rmItem(ActionEvent event) {
    if (bundleEqList.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please select an equipment item to delete.");
      return;
    }
    equipmentToBeAdded.remove(bundleEqList.getSelectionModel().getSelectedIndex());
    quantitiesToBeAdded.remove(bundleEqList.getSelectionModel().getSelectedIndex());
    refreshListView(bundleEqList, equipmentToBeAdded);
    refreshListView(bundleEqQtList, quantitiesToBeAdded);
  }

  // Event Listener on ListView[#bundleEqList].onMouseClicked
  @FXML
  public void selectedEq(MouseEvent event) {
    bundleEqQtList.getSelectionModel().select(bundleEqList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on ListView[#bundleEqQtList].onMouseClicked
  @FXML
  public void selectedQt(MouseEvent event) {
    bundleEqList.getSelectionModel().select(bundleEqQtList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on Tab[#BundleTab2].onSelectionChanged
  @FXML
  public void RefreshSystemBundles2(Event event) {
    initialize();
  }

  // Event Listener on Button[#updateBundleButton].onAction
  @FXML
  public void updateBundleAction(ActionEvent event) {
    String oldName = oldBundleName.getText();
    if (!ViewUtils.isAlpha(oldName)) {
      ViewUtils.showError("Old bundle name can only contain letters.");
      return;
    }
    String name = newBundleName.getText();
    if (!ViewUtils.isAlpha(name)) {
      ViewUtils.showError("New bundle name can only contain letters.");
      return;
    }
    int discount = 0;
    try {
      discount = Integer.parseInt(newBundleDiscount.getText());
    } catch (Exception e) {
      ViewUtils.showError("new Discount can only be an integer.");
      return;
    }

    try {
      ClimbSafeFeatureSet5Controller.updateEquipmentBundle(oldName, name, discount,
          equipmentToBeUpdated, stringListToInteger(quantitiesToBeUpdated));
      ViewUtils.showSuccess("Successfully updated bundle \"" + oldName + "\" to the new bundle \""
          + name + "\" with a discount of \"" + Integer.toString(discount) + "\" to the system.");
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Button[#addItemButton2].onAction
  @FXML
  public void addItem2(ActionEvent event) {
    if (newItemCombo.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please Select an Equipment from the List.");
      return;
    }
    int quantity = 0;
    try {
      quantity = Integer.parseInt(newItemQuantity.getText());
    } catch (Exception e) {
      ViewUtils.showError("Equipment Quantity must be a number.");
      return;
    }
    equipmentToBeUpdated.add(newItemCombo.getValue());
    quantitiesToBeUpdated.add(Integer.toString(quantity));

    refreshListView(newEqList, equipmentToBeUpdated);
    refreshListView(newQtList, quantitiesToBeUpdated);
  }

  // Event Listener on Button[#rmItemButton2].onAction
  @FXML
  public void rmIntem2(ActionEvent event) {
    if (newEqList.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please select an equipment item to delete.");
      return;
    }
    equipmentToBeUpdated.remove(newEqList.getSelectionModel().getSelectedIndex());
    quantitiesToBeUpdated.remove(newEqList.getSelectionModel().getSelectedIndex());
    refreshListView(newEqList, equipmentToBeUpdated);
    refreshListView(newQtList, quantitiesToBeUpdated);
  }

  // Event Listener on ListView[#newEqList].onMouseClicked
  @FXML
  public void newEqSelected(MouseEvent event) {
    newQtList.getSelectionModel().select(newEqList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on ListView[#newQtList].onMouseClicked
  @FXML
  public void newQtSelected(MouseEvent event) {
    newEqList.getSelectionModel().select(newQtList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on Tab[#BundleTab3].onSelectionChanged
  @FXML
  public void RefreshSystemBundles3(Event event) {
    refreshBundles(rmBundleList, rmDiscountList);
  }

  // Event Listener on Button[#deleteBundleButton].onAction
  @FXML
  public void deleteBundleAction(ActionEvent event) {
    if (rmBundleList.getSelectionModel().getSelectedItem() == null) {
      ViewUtils.showError("Please Select a Bundle to delete.");
      return;
    }
    try {
      String toBeDeleted = rmBundleList.getSelectionModel().getSelectedItem().getText();
      ClimbSafeFeatureSet6Controller.deleteEquipmentBundle(toBeDeleted);
      refreshBundles(rmBundleList, rmDiscountList);
      ViewUtils.showSuccess("Successfully deleted bundle \"" + toBeDeleted + "\" from the system.");

    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on ListView[#rmDiscountList].onMouseClicked
  @FXML
  public void rmDiscountSelected(MouseEvent event) {
    rmBundleList.getSelectionModel().select(rmDiscountList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on ListView[#rmBundleList].onMouseClicked
  @FXML
  public void rmBundleSelected(MouseEvent event) {
    rmDiscountList.getSelectionModel().select(rmBundleList.getSelectionModel().getSelectedIndex());
  }

  private void refreshBundles(ListView<Label> bundleList, ListView<Label> discountList) {
    bundleList.getItems().clear();
    discountList.getItems().clear();
    for (EquipmentBundle bundle : ClimbSafeApplication.getClimbSafe().getBundles()) {
      bundleList.getItems().add(new Label(bundle.getName()));
      discountList.getItems().add(new Label(Integer.toString(bundle.getDiscount())));
    }
    rmBundleList.refresh();
    rmDiscountList.refresh();
  }

  private List<String> getEquipment() {
    List<String> list = new ArrayList<String>();
    for (Equipment equipment : ClimbSafeApplication.getClimbSafe().getEquipment()) {
      list.add(equipment.getName());
    }
    return list;
  }

  private void refreshListView(ListView<Label> listView, List<String> data) {
    listView.getItems().clear();
    for (String string : data) {
      listView.getItems().add(new Label(string));
    }
    listView.refresh();
  }

  private List<Integer> stringListToInteger(List<String> list) {
    List<Integer> ints = new ArrayList<Integer>();
    for (String s : list) {
      ints.add(Integer.parseInt(s));
    }
    return ints;
  }
}
