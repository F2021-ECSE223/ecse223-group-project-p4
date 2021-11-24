package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Tab;

import javafx.scene.control.CheckBox;



public class MemberOperationsController {

  private static ClimbSafe system = ClimbSafeApplication.getClimbSafe(); // The system instance

  @FXML
  private Tab registerMemberTab;
  @FXML
  private TextField addEmail;
  @FXML
  private Button registerMemberButton;
  @FXML
  private TextField addFirstName;
  @FXML
  private TextField addLastName;
  @FXML
  private TextField addPassword;
  @FXML
  private TextField addEmergencyPhone;
  @FXML
  private TextField addNumberWeeks;
  @FXML
  private CheckBox guideRequiredCheck;
  @FXML
  private CheckBox hotelRequiredCheck;
  @FXML
  private Button addItemButton;
  @FXML
  private Button addBundleButton;
  @FXML
  private TextField addItemsQuantities;
  @FXML
  private TextField addBundlesQuantities;
  @FXML
  private ComboBox<Equipment> addedItemsList =
      new ComboBox<Equipment>(FXCollections.observableList(system.getEquipment())); // Get all
                                                                                    // available
                                                                                    // equipment
                                                                                    // from system
  @FXML
  private ComboBox<EquipmentBundle> addedBundlesList =
      new ComboBox<EquipmentBundle>(FXCollections.observableList(system.getBundles())); // Get all
                                                                                        // available
                                                                                        // equipmentBundle
                                                                                        // from
                                                                                        // system
  @FXML
  private Label showTotalWeight;
  @FXML
  private Label showTotalPrice;
  @FXML
  private Tab updateMemberTab;
  @FXML
  private TextField updatePassword;
  @FXML
  private Button updateMemberButton;
  @FXML
  private TextField updateLastName;
  @FXML
  private TextField memberEmail;
  @FXML
  private TextField updateFirstName;
  @FXML
  private TextField updateEmergencyPhone;
  @FXML
  private TextField updateNumberWeeks;
  @FXML
  private TextField updateItemQuantity;
  @FXML
  private CheckBox updateHotelRequiredCheck;
  @FXML
  private CheckBox updateGuideRequiredCheck;
  @FXML
  private Button updateAddItemButton;
  @FXML
  private Button updateAddIBundleBuntton;
  @FXML
  private TextField updateBundleQuantity;
  @FXML
  private ComboBox<Equipment> updateItemName =
      new ComboBox<Equipment>(FXCollections.observableList(system.getEquipment())); // Get all
                                                                                    // available
                                                                                    // equipment
                                                                                    // from system
  @FXML
  private ComboBox<EquipmentBundle> updateBundleName =
      new ComboBox<EquipmentBundle>(FXCollections.observableList(system.getBundles())); // Get all
                                                                                        // available
                                                                                        // equipmentBundle
                                                                                        // from
                                                                                        // system
  @FXML
  private Tab deleteMemberTab;
  @FXML
  private Button deleteMemberButton;
  @FXML
  private TextField toBedeletedMemberEmail;

  // Initialize lists for the member adding his items for the climb

  // For Registering
  private List<String> bookedItemsToAdd = new ArrayList<>();
  private List<Integer> numberOfItemsToAdd = new ArrayList<>();
  private List<BookableItem> allBookedItemsList = new ArrayList<>();

  // For Updating
  private List<String> bookedItemsToUpdate = new ArrayList<>();
  private List<Integer> numberOfItemsToUpdate = new ArrayList<>();
  private List<BookableItem> updateAllBookedItemsList = new ArrayList<>();



  // Integer for various calculations
  private Integer totalWeight = 0;
  private Integer totalPrice = 0;

  private Integer totalPriceForUpdate = 0;
  private Integer totalWeightForUpdate = 0;



  // Event Listener on Button[#registerMemberButton].onAction
  @FXML
  /**
   * This method will register the info of a member by calling the controller when the user presses
   * the button
   * 
   * @param event - press of the register button
   */
  public void registerMemberUI(ActionEvent event) {

    // Get the required parameters from the UI

    String name = addFirstName.getText() + addLastName.getText();
    String email = addEmail.getText();
    String password = addPassword.getText();
    String emergency = addEmergencyPhone.getText();
    boolean hotel = hotelRequiredCheck.isSelected();
    boolean guide = guideRequiredCheck.isSelected();


    // Check if information entered is alphanumeric
    if (!ViewUtils.isAlpha(name) || !ViewUtils.isAlpha(email) || !ViewUtils.isAlpha(password)
        || !ViewUtils.isAlpha(emergency)) {
      ViewUtils.showError("The input must only contain letters.");
      return;
    }

    int numberOfWeeksWanted;

    // Check if information entered is an integer
    try {
      numberOfWeeksWanted = Integer.parseInt(this.addNumberWeeks.getText());
    } catch (Exception e) {
      ViewUtils.showError("The number of weeks wanted must be an integer");
      return;
    }



    // Check if information entered is not empty
    if (name.equals("") || email.equals("") || password.equals("") || emergency.equals("")) {

      ViewUtils.showError("The input must not be empty.");
      return;

    }


    try {
      ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergency,
          numberOfWeeksWanted, guide, hotel, bookedItemsToAdd, numberOfItemsToAdd); // Try to
                                                                                    // register
                                                                                    // member

      // Compute the total price and weight of the item chosen by the member and if the member
      // desires a guide
      totalPrice =
          computeTotalCost(numberOfWeeksWanted, guide, allBookedItemsList, numberOfItemsToAdd);
      totalWeight = computeTotalWeight(allBookedItemsList, numberOfItemsToAdd);


      if (!guide)
        // Output the price and weight without taking into account the guide since the member does
        // not want it
        ViewUtils.showSuccess("Registration successfully processed for member " + name + '\n'
            + "Total Price of equipment is " + totalPrice + " s and the the total weight is "
            + totalWeight + " lb.");

      else {
        // Output the price and weight taking into account the guide since the member wants it

        int totalCostForGuide = system.getPriceOfGuidePerWeek() * numberOfWeeksWanted;


        ViewUtils.showSuccess("Registration successfully processed for member " + name + '\n'
            + "Total Price of equipment is: " + totalPrice + " s, Total price of the guide is: "
            + totalCostForGuide + " s and the the total weight is " + totalWeight + " lb.");

      }
      // Set the price and weight on the screen
      showTotalWeight.setText(totalPrice + " lb");
      showTotalPrice.setText(totalWeight + " s");

      // Clear the temporary lists for the next customer
      bookedItemsToAdd.clear();
      numberOfItemsToAdd.clear();
      // Clear the price and weight
      totalPrice = 0;
      totalWeight = 0;

      // Catch and output the error if there's one
    } catch (InvalidInputException e) {
      ViewUtils.showError(e.getMessage());
      return;
    }



  }

  // Event Listener on Button[#addItemButton].onAction
  @FXML
  /**
   * This method will add the item to a member by adding it to a temp list when the user presses the
   * button
   * 
   * @param event - press of the add button
   */
  public void addItem(ActionEvent event) {

    int numberOfItemWanted;

    // Check if information entered is an integer
    try {
      numberOfItemWanted = Integer.parseInt(this.addItemsQuantities.getText());
    } catch (Exception e) {
      ViewUtils.showError("The number of items wanted must be an integer");
      return;
    }


    String nameOfItem = addedItemsList.getValue().getName(); // Get the name of the item chosen

    if (!nameOfItem.equals("")) { // Check thats the member chose an item.

      bookedItemsToAdd.add(nameOfItem); // Add the name of the item to the list

      allBookedItemsList.add((Equipment) addedItemsList.getValue()); // Add the equipment to the
                                                                     // list
      numberOfItemsToAdd.add(numberOfItemWanted); // Add the number of equipment requested by the
                                                  // member

    } else { // The member clicked on add without selecting any item
      ViewUtils.showError("You have to select an item to add");
      return;

    }

  }


  // Event Listener on Button[#addBundleButton].onAction
  @FXML
  /**
   * This method will add the bundle to a member by adding it to a temp list when the user presses
   * the button
   * 
   * @param event - press of the add button
   */
  public void addBundle(ActionEvent event) {
    int numberOfBundleWanted;

    // Check if information entered is an integer
    try {
      numberOfBundleWanted = Integer.parseInt(this.addBundlesQuantities.getText());
    } catch (Exception e) {
      ViewUtils.showError("The number of bundles wanted must be an integer");
      return;
    }

    String nameOfBundle = addedBundlesList.getValue().getName(); // Get the name of the item chosen

    if (!nameOfBundle.equals("")) { // Check thats the member chose a bundle.

      bookedItemsToAdd.add(nameOfBundle); // Add the name of the item to the list

      allBookedItemsList.add((EquipmentBundle) addedBundlesList.getValue()); // Add the equipment
                                                                             // bundle to the list

      numberOfItemsToAdd.add(numberOfBundleWanted); // Add the number of equipment requested by the
                                                    // member

    }

    else { // The member clicked on add without selecting any bundle
      ViewUtils.showError("You have to select a bundle to add");
      return;

    }

  }



  // Event Listener on Button[#updateMemberButton].onAction
  @FXML
  /**
   * This method will update the info of a member by calling the controller when the user presses
   * the button
   * 
   * @param event - press of the update button
   */
  public void updateMemberUI(ActionEvent event) {

    // Get the required parameters from the UI
    String name = updateFirstName.getText() + updateLastName.getText();
    String email = memberEmail.getText();
    String password = updatePassword.getText();
    String emergency = updateEmergencyPhone.getText();
    boolean hotel = updateHotelRequiredCheck.isSelected();
    boolean guide = updateGuideRequiredCheck.isSelected();

    // Check if information entered is alphanumeric
    if (!ViewUtils.isAlpha(name) || !ViewUtils.isAlpha(email) || !ViewUtils.isAlpha(password)
        || !ViewUtils.isAlpha(emergency)) {
      ViewUtils.showError("The input must only contain letters.");
      return;
    }

    int numberOfWeeksWanted;

    // Check if information entered is an integer
    try {
      numberOfWeeksWanted = Integer.parseInt(this.updateNumberWeeks.getText());
    } catch (Exception e) {
      ViewUtils.showError("The number of weeks wanted must be an integer");
      return;
    }

    // Check if information entered is not empty
    if (name.equals("") || email.equals("") || password.equals("") || emergency.equals("")) {

      ViewUtils.showError("The input must not be empty.");
      return;

    }

    try {
      ClimbSafeFeatureSet2Controller.updateMember(email, password, name, emergency,
          numberOfWeeksWanted, guide, hotel, bookedItemsToAdd, numberOfItemsToAdd); // Try to Update
                                                                                    // member

      // Compute the total price and weight of the item chosen by the member and if the member
      // desires a guide
      totalPriceForUpdate = computeTotalCost(numberOfWeeksWanted, guide, updateAllBookedItemsList,
          numberOfItemsToUpdate);
      totalWeightForUpdate = computeTotalWeight(updateAllBookedItemsList, numberOfItemsToUpdate);


      if (!guide)
        // Output the price and weight without taking into account the guide since the member does
        // not want it
        ViewUtils.showSuccess("Update successfully processed for member " + name + '\n'
            + "Total Price of equipment is " + totalPriceForUpdate
            + " s and the the total weight is " + totalWeightForUpdate + " lb.");

      else {

        int totalCostForGuide = system.getPriceOfGuidePerWeek() * numberOfWeeksWanted;

        // Output the price and weight taking into account the guide since the member wants it
        ViewUtils.showSuccess("Update successfully processed for member " + name + '\n'
            + "Total Price of equipment is: " + totalPriceForUpdate
            + " s, Total price of the guide is: " + totalCostForGuide
            + " s and the the total weight is " + totalWeightForUpdate + " lb.");

      }


      // Clear the temporary lists for the next customer
      bookedItemsToUpdate.clear();
      numberOfItemsToUpdate.clear();

      // Clear the price and weight
      totalPriceForUpdate = 0;
      totalWeightForUpdate = 0;

      // Catch and output the error if there's one
    } catch (InvalidInputException e) {
      ViewUtils.showError(e.getMessage());
      return;
    }


  }

  // Event Listener on Button[#updateAddItemButton].onAction
  @FXML
  /**
   * This method will update the items booked to a member by adding them to a temp list when the
   * user presses the button
   * 
   * @param event - press of the update button
   */
  public void updateItem(ActionEvent event) {
    int numberOfItemWanted;


    // Check if information entered is an integer
    try {
      numberOfItemWanted = Integer.parseInt(this.updateItemQuantity.getText());
    } catch (Exception e) {
      ViewUtils.showError("The number of items wanted must be an integer");
      return;
    }


    String nameOfItem = updateItemName.getValue().getName();

    if (!nameOfItem.equals("")) { // Check thats the member chose an item.

      bookedItemsToUpdate.add(nameOfItem); // Add the name of the item to the list

      numberOfItemsToUpdate.add(numberOfItemWanted); // Add the number of equipment requested by the
                                                     // member

      updateAllBookedItemsList.add((Equipment) updateItemName.getValue()); // Add the equipment to
                                                                           // the list

    }

    else { // The member clicked on add without selecting any item
      ViewUtils.showError("You have to select an item to add");
      return;

    }

  }

  // Event Listener on Button[#updateAddIBundleBuntton].onAction
  @FXML
  /**
   * This method will update the bundles booked to a member by adding to a temp list when the user
   * presses the button
   * 
   * @param event - press of the update button
   */
  public void updateBundle(ActionEvent event) {
    int numberOfBundleWanted;

    // Check if information entered is an integer
    try {
      numberOfBundleWanted = Integer.parseInt(updateBundleQuantity.getText());
    } catch (Exception e) {
      ViewUtils.showError("The number of bundles wanted must be an integer");
      return;
    }

    String nameOfBundle = updateBundleName.getValue().getName();


    if (!nameOfBundle.equals("")) { // Check thats the member chose a bundle.

      bookedItemsToUpdate.add(nameOfBundle); // Add the name of the bundle to the list

      numberOfItemsToUpdate.add(numberOfBundleWanted); // Add the number of equipment requested by
                                                       // the member

      updateAllBookedItemsList.add((EquipmentBundle) updateBundleName.getValue()); // Add the
                                                                                   // equipment
                                                                                   // bundle to the
                                                                                   // list
    }

    else { // The member clicked on add without selecting any bundle
      ViewUtils.showError("You have to select a bundle to add");
      return;

    }

  }



  // Event Listener on Button[#deleteMemberButton].onAction
  @FXML
  /**
   * This method will delete the member by calling the controller when the user presses the button
   * 
   * @param event - press of the delete button
   */
  public void deleteMemberUI(ActionEvent event) {

    // Get the required parameters from the UI
    String email = toBedeletedMemberEmail.getText();

    // Check if information entered is alphanumeric
    if (!ViewUtils.isAlpha(email)) {
      ViewUtils.showError("The input must only contain letters.");
      return;
    }

    // Check if information entered is not empty
    if (email.equals("")) {

      ViewUtils.showError("The input must not be empty.");
      return;

    }


    // Delete the member with the select email
    ClimbSafeFeatureSet1Controller.deleteMember(email);


  }


  /**
   * Helper method that computes the total price of the equipment booked by a member.
   * 
   * @param totalNumberOfWeeks - number of weeks requested from the UI
   * @param hasGuide - checks if the member requested a guide from the UI
   * @param listOfBookableItem - the list of item requested from the UI
   * @param listOfNumberOfItems - the list of number of bookableItems requested from the UI
   * @return totalPrice - the total price for the members' trip
   * @author Karl Rouhana
   */
  private int computeTotalCost(int totalNumberOfWeeks, boolean hasGuide,
      List<BookableItem> listOfBookableItem, List<Integer> listOfNumberOfItems) {


    int i = 0;
    for (var bookedItem : listOfBookableItem) {

      // If it is an equipment item
      if (bookedItem instanceof Equipment item)
        // Multiplying by the number of weeks and the quantity
        totalPrice += listOfNumberOfItems.get(i) * item.getPricePerWeek() * totalNumberOfWeeks;

      // If it is an equipment bundle
      else {
        int bundlePricePerWeek = 0;
        EquipmentBundle bundle = (EquipmentBundle) bookedItem;

        // Looping over all the items in the bundle
        for (BundleItem bundleItem : bundle.getBundleItems()) {
          int itemPricePerWeek =
              bundleItem.getEquipment().getPricePerWeek() * listOfNumberOfItems.get(i);
          bundlePricePerWeek += itemPricePerWeek;
        }

        // Applying the discount if a guide was hired
        if (hasGuide)
          bundlePricePerWeek =
              (int) Math.round(bundlePricePerWeek * (100 - bundle.getDiscount()) / 100.0);

        // Adding the price of this bundle by multiplying by the quantity and the number of weeks
        totalPrice += listOfNumberOfItems.get(i) * bundlePricePerWeek * totalNumberOfWeeks;
      }

      i++;

    }


    return totalPrice; // Returning the total price over the trip


  }

  /**
   * Helper method that computes the total weight of the equipment booked by a member.
   * 
   * @param listOfBookableItem - the list of item requested from the UI
   * @param listOfNumberOfItems - the list of number of bookableItems requested from the UI
   * @return totalWeight - the total weight for the members' trip
   * @author Karl Rouhana
   */
  private int computeTotalWeight(List<BookableItem> listOfBookableItem,
      List<Integer> listOfNumberOfItems) {

    int i = 0;
    for (var bookedItem : listOfBookableItem) {

      // If it is an equipment item
      if (bookedItem instanceof Equipment item)

        totalWeight += listOfNumberOfItems.get(i) * item.getWeight();

      // If it is an equipment bundle
      else {
        int bundleWeight = 0;
        EquipmentBundle bundle = (EquipmentBundle) bookedItem;

        // Looping over all the items in the bundle
        for (BundleItem bundleItem : bundle.getBundleItems()) {
          int itemWeightInBundle = bundleItem.getEquipment().getWeight() * bundleItem.getQuantity();
          bundleWeight += itemWeightInBundle;
        }

        totalWeight += listOfNumberOfItems.get(i) * bundleWeight;
      }

      i++;

    }
    return totalWeight;

  }



}
