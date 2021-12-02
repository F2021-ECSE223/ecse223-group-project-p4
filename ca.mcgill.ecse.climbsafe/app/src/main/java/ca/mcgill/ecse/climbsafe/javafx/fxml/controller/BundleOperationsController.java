package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import javafx.collections.FXCollections;
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
  private ComboBox<String> addItemCombo = new ComboBox<String>();
  @FXML
  private Button rmItemButton;
  @FXML
  private ListView<Label> bundleEqList = new ListView<Label>();
  @FXML
  private ListView<Label> bundleEqQtList = new ListView<Label>();
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
  private ComboBox<String> newItemCombo = new ComboBox<String>();
  @FXML
  private Button rmItemButton2;
  @FXML
  private ListView<Label> newEqList = new ListView<Label>();
  @FXML
  private ListView<Label> newQtList = new ListView<Label>();
  @FXML
  private Tab BundleTab3;
  @FXML
  private Button deleteBundleButton;
  @FXML
  private ListView<Label> rmDiscountList = new ListView<Label>();
  @FXML
  private ListView<Label> rmBundleList = new ListView<Label>();
  // Temporary lists of equipment items
  private ArrayList<String> equipmentToBeAdded = new ArrayList<String>();
  private ArrayList<String> quantitiesToBeAdded = new ArrayList<String>();
  private ArrayList<String> equipmentToBeUpdated = new ArrayList<String>();
  private ArrayList<String> quantitiesToBeUpdated = new ArrayList<String>();

  @FXML
  /**
   * Refreshes the Combo Boxes (Dropdown Menus) by retrieving all system equipment items and listing
   * them.
   * 
   * @author Adam Kazma
   */
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
  /**
   * Refreshes Combo Box on Add Bundle tab.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void RefreshSystemBundles(Event event) {
    initialize();
  }

  // Event Listener on Button[#addBundleButton].onAction
  @FXML
  /**
   * Called when the "Add Bundle" button is pressed, this method calls the controller method
   * addEquipmentBundle() and passes user input given on the Add Bundle Tab as parameters.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void addBundleAction(ActionEvent event) {
    String name = addBundleName.getText();
    // Check if given bundle name is alphabetic, otherwise throw an error.
    if (!ViewUtils.isAlpha(name)) {
      ViewUtils.showError("Bundle name can only contain letters.");
      return;
    }
    int discount = 0;
    // Check if the given bundle discount is an integer, otherwise throw an error.
    try {
      discount = Integer.parseInt(addBundleDiscount.getText());
    } catch (Exception e) {
      ViewUtils.showError("Discount can only be an integer.");
      return;
    }
    // Calling controller method, and passing the user input as parameters. If any exception is
    // thrown, display it in a dialog box.
    try {
      ClimbSafeFeatureSet5Controller.addEquipmentBundle(name, discount, equipmentToBeAdded,
          stringListToInteger(quantitiesToBeAdded));
      ViewUtils.showSuccess("Successfully added bundle \"" + name + "\" with a discount of \""
          + Integer.toString(discount) + "\" to the system.");
      // Clear input fields after a successful action.
      addBundleName.clear();
      addItemCombo.getSelectionModel().clearSelection();
      addItemQuantities.clear();
      addBundleDiscount.clear();
      bundleEqList.getItems().clear();
      bundleEqList.getSelectionModel().clearSelection();
      bundleEqQtList.getItems().clear();
      bundleEqQtList.getSelectionModel().clearSelection();
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Button[#addItemButton].onAction
  @FXML
  /**
   * Called when the "Add" button is pressed (Add Bundle tab), this method adds the selected
   * equipment item in the dropdown menu (combobox) and the quantity from the field next to it to
   * two temporary lists, that will be used later to create the bundle.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void addItem(ActionEvent event) {
    // If no equipment is selected in ComboBox, throw an error.
    if (addItemCombo.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please Select an Equipment from the List.");
      return;
    }
    int quantity = 0;
    // Check if the given equipment quantity is an integer, if not throw an error.
    try {
      quantity = Integer.parseInt(addItemQuantities.getText());
    } catch (Exception e) {
      ViewUtils.showError("Equipment Quantity must be a number.");
      return;
    }
    // Checking that the selected equipment has not been already added to the temporary list.
    String toBeAdded = addItemCombo.getValue();
    for (String equipment : equipmentToBeAdded) {
      if (equipment.equals(toBeAdded)) {
        ViewUtils.showError("You already selected this equipment.");
        return;
      }
    }
    // Add equipment and corresponding quantity to two lists.
    equipmentToBeAdded.add(toBeAdded);
    quantitiesToBeAdded.add(Integer.toString(quantity));

    // Refresh ListViews displaying the two temporary lists.
    refreshListView(bundleEqList, equipmentToBeAdded);
    refreshListView(bundleEqQtList, quantitiesToBeAdded);
  }

  // Event Listener on Button[#rmItemButton].onAction
  @FXML
  /**
   * Called when the "Remove Item" button is pressed (Add Bundle tab), this method removes the
   * selected item in the ListViews (corresponding to an equipment name and quantity) from the two
   * temporary lists.
   * 
   * @param event
   * @author Adam Kazma
   */
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
  /**
   * Selects matching quantity from the second ListView in the Add Bundle tab, if an Equipment is
   * selected from the first ListView.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void selectedEq(MouseEvent event) {
    bundleEqQtList.getSelectionModel().select(bundleEqList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on ListView[#bundleEqQtList].onMouseClicked
  @FXML
  /**
   * Selects matching Equipment from the first ListView in the Add Bundle tab, if a Quantity is
   * selected from the second ListView.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void selectedQt(MouseEvent event) {
    bundleEqList.getSelectionModel().select(bundleEqQtList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on Tab[#BundleTab2].onSelectionChanged
  @FXML
  /**
   * Refreshes Combo Box on Update Bundle tab.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void RefreshSystemBundles2(Event event) {
    initialize();
  }

  // Event Listener on Button[#updateBundleButton].onAction
  @FXML
  /**
   * This method calls the updateEquipmentBundle() controller method, and passes user input as
   * parameters.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void updateBundleAction(ActionEvent event) {
    String oldName = oldBundleName.getText();
    // Check that old Bundle name is alphabetic. If not, throw an error.
    if (!ViewUtils.isAlpha(oldName)) {
      ViewUtils.showError("Old bundle name can only contain letters.");
      return;
    }
    String name = newBundleName.getText();
    // Check that new Bundle name is alphabetic. If not, throw an error.
    if (!ViewUtils.isAlpha(name)) {
      ViewUtils.showError("New bundle name can only contain letters.");
      return;
    }
    int discount = 0;
    // Check that the given value in the discount field is and integer. If not, throw an error.
    try {
      discount = Integer.parseInt(newBundleDiscount.getText());
    } catch (Exception e) {
      ViewUtils.showError("new Discount can only be an integer.");
      return;
    }
    // Call the controller method, while passing parameters. If any exception is thrown, display it
    // in a dialog box.
    try {
      ClimbSafeFeatureSet5Controller.updateEquipmentBundle(oldName, name, discount,
          equipmentToBeUpdated, stringListToInteger(quantitiesToBeUpdated));
      ViewUtils.showSuccess("Successfully updated bundle \"" + oldName + "\" to the new bundle \""
          + name + "\" with a discount of \"" + Integer.toString(discount) + "\" to the system.");
      // Clear input fields if action is successful.
      oldBundleName.clear();
      newBundleName.clear();
      newItemCombo.getSelectionModel().clearSelection();
      newItemQuantity.clear();
      newBundleDiscount.clear();
      newEqList.getItems().clear();
      newEqList.getSelectionModel().clearSelection();
      newQtList.getItems().clear();
      newQtList.getSelectionModel().clearSelection();
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on Button[#addItemButton2].onAction
  @FXML
  /**
   * Called when the "Add" button is pressed (Update Bundle tab), this method adds the selected
   * equipment item in the dropdown menu (combobox) and the quantity from the field next to it to
   * two temporary lists, that will be used later to create the bundle.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void addItem2(ActionEvent event) {
    // Check if an equipment is selected from Combo Box, if not throw an error.
    if (newItemCombo.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please Select an Equipment from the List.");
      return;
    }
    int quantity = 0;
    // Check if value of quantity field is an integer, if not throw an error.
    try {
      quantity = Integer.parseInt(newItemQuantity.getText());
    } catch (Exception e) {
      ViewUtils.showError("Equipment Quantity must be a number.");
      return;
    }
    // Check that the temporary list does not already contain selected equipment.
    String toBeUpdated = newItemCombo.getValue();
    for (String equipment : equipmentToBeUpdated) {
      if (equipment.equals(toBeUpdated)) {
        ViewUtils.showError("You already selected this equipment.");
        return;
      }
    }
    // Add equipment & quantity to temporary lists.
    equipmentToBeUpdated.add(toBeUpdated);
    quantitiesToBeUpdated.add(Integer.toString(quantity));
    // Refresh ListViews.
    refreshListView(newEqList, equipmentToBeUpdated);
    refreshListView(newQtList, quantitiesToBeUpdated);
  }

  // Event Listener on Button[#rmItemButton2].onAction
  @FXML
  /**
   * Called when the "Remove Item" button is pressed (Update Bundle tab), this method removes the
   * selected item in the ListViews (corresponding to an equipment name and quantity) from the two
   * temporary lists.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void rmIntem2(ActionEvent event) {
    // Check if user selected a Bundle from the ListView, if not throw an error.
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
  /**
   * Selects matching quantity from the second ListView in the Update Bundle tab, if an Equipment is
   * selected from the first ListView.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void newEqSelected(MouseEvent event) {
    newQtList.getSelectionModel().select(newEqList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on ListView[#newQtList].onMouseClicked
  @FXML
  /**
   * Selects matching Equipment from the first ListView in the Update Bundle tab, if a Quantity is
   * selected from the second ListView.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void newQtSelected(MouseEvent event) {
    newEqList.getSelectionModel().select(newQtList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on Tab[#BundleTab3].onSelectionChanged
  @FXML
  /**
   * This method refreshes the ListViews on the Delete Bundle tab, by retrieving the system bundles
   * and their respective discounts, and listing them.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void RefreshSystemBundles3(Event event) {
    refreshBundles(rmBundleList, rmDiscountList);
  }

  // Event Listener on Button[#deleteBundleButton].onAction
  @FXML
  /**
   * This method calls the deleteEquipmentBundle() controller method, with the name of the bundle
   * selected by the user passed as a parameter.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void deleteBundleAction(ActionEvent event) {
    if (rmBundleList.getSelectionModel().getSelectedItem() == null) {
      // If no bundle is selected from ListView, throw an error.
      ViewUtils.showError("Please Select a Bundle to delete.");
      return;
    }
    try {
      String toBeDeleted = rmBundleList.getSelectionModel().getSelectedItem().getText();
      ClimbSafeFeatureSet6Controller.deleteEquipmentBundle(toBeDeleted);
      refreshBundles(rmBundleList, rmDiscountList);
      ViewUtils.showSuccess("Successfully deleted bundle \"" + toBeDeleted + "\" from the system.");
      rmBundleList.getSelectionModel().clearSelection();

    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
    }
  }

  // Event Listener on ListView[#rmDiscountList].onMouseClicked
  @FXML
  /**
   * Selects matching Bundle from the first ListView in the Delete Bundle tab, if a Discount is
   * selected from the second ListView.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void rmDiscountSelected(MouseEvent event) {
    rmBundleList.getSelectionModel().select(rmDiscountList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on ListView[#rmBundleList].onMouseClicked
  @FXML
  /**
   * Selects matching Discount from the second ListView in the Delete Bundle tab, if a Bundle is
   * selected from the first ListView.
   * 
   * @param event
   * @author Adam Kazma
   */
  public void rmBundleSelected(MouseEvent event) {
    rmDiscountList.getSelectionModel().select(rmBundleList.getSelectionModel().getSelectedIndex());
  }

  /**
   * Helper method, refreshes the two given ListViews of bundles and discounts to diplay the names
   * and discounts of the bundles currently in the system.
   * 
   * @param bundleList
   * @param discountList
   * @author Adam Kazma
   */
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

  /**
   * Helper Method to retrieve system equipment items.
   * 
   * @return List of names of the equipment items in the system.
   * @author Adam Kazma
   */
  private List<String> getEquipment() {
    List<String> list = new ArrayList<String>();
    for (Equipment equipment : ClimbSafeApplication.getClimbSafe().getEquipment()) {
      list.add(equipment.getName());
    }
    return list;
  }

  /**
   * Helper Method refreshes the given ListView to display the given data.
   * 
   * @param listView
   * @param data
   * @author Adam Kazma
   */
  private void refreshListView(ListView<Label> listView, List<String> data) {
    listView.getItems().clear();
    for (String string : data) {
      listView.getItems().add(new Label(string));
    }
    listView.refresh();
  }

  /**
   * Helper Method to convert a list of strings to a list of integers.
   * 
   * @param list
   * @return List of Integers corresponding to the given List of Strings.
   * @author Adam Kazma
   */
  private List<Integer> stringListToInteger(List<String> list) {
    List<Integer> ints = new ArrayList<Integer>();
    for (String s : list) {
      ints.add(Integer.parseInt(s));
    }
    return ints;
  }
}
