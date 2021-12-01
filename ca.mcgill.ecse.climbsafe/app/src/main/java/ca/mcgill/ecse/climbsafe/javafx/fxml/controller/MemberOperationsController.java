package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.ClimbingPath;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Tab;

import javafx.scene.control.CheckBox;

import javafx.event.Event;

public class MemberOperationsController {
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
  private ComboBox<String> addedItemsList = new ComboBox<String>();
  @FXML
  private ComboBox<String> addedBundlesList = new ComboBox<String>();
  @FXML
  private ListView<Label> listOfItemsChosen = new ListView<Label>();
  @FXML
  private ListView<Label> listOfNumberOfItemsChosen = new ListView<Label>();
  @FXML
  private Button removeItems;
  @FXML
  private Button addPathButton;
  @FXML
  private ListView<Label> listOfClimbingPaths = new ListView<Label>();
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
  private ComboBox<String> updateItemName = new ComboBox<String>();
  @FXML
  private ComboBox<String> updateBundleName = new ComboBox<String>();
  @FXML
  private ListView<Label> listOfClimbingPathsUpdate = new ListView<Label>();
  @FXML
  private Button updatePathButton;
  @FXML
  private Button removeItemsUpdate;
  @FXML
  private ListView<Label> listOfItemsChosenUpdate;
  @FXML
  private ListView<Label> listOfINumberOftemsChosenUpdate = new ListView<Label>();
  @FXML
  private Label chosenPath;
  @FXML
  private Label chosenPathUpdate;
  @FXML
  private Tab deleteMemberTab;
  @FXML
  private Button deleteMemberButton;
  @FXML
  private TextField toBedeletedMemberEmail;

  private static ClimbSafe system = ClimbSafeApplication.getClimbSafe(); // The system instance


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

  private Integer pathIndexRegister = null;
  private Integer pathIndexUpdate = null;



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

    String name = addFirstName.getText() + " " + addLastName.getText();
    String email = addEmail.getText();
    String password = addPassword.getText();
    String emergency = addEmergencyPhone.getText();


    boolean hotel = hotelRequiredCheck.isSelected();
    boolean guide = guideRequiredCheck.isSelected();


    if (pathIndexRegister == null) {
      ViewUtils.showError("Please select a climbing path.");
      return;
    }

    // Check if information entered is alphanumeric
    if (!ViewUtils.isAlpha(name)) {
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
    if (addFirstName.getText().equals("") || addLastName.getText().equals("") || email.equals("")
        || password.equals("") || emergency.equals("")) {

      ViewUtils.showError("The input fields must not be empty.");
      return;

    }


    try {

      // Compute the total price and weight of the item chosen by the member and if the member
      // desires a guide
      totalPrice =
          computeTotalCost(numberOfWeeksWanted, guide, allBookedItemsList, numberOfItemsToAdd);
      totalWeight = computeTotalWeight(allBookedItemsList, numberOfItemsToAdd);

      ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergency,
          numberOfWeeksWanted, guide, hotel, bookedItemsToAdd, numberOfItemsToAdd); // Try to
                                                                                    // register
                                                                                    // member


      clearFieldsInRegister();

      if (!guide) {
        // Output the price and weight without taking into account the guide since the member does
        // not want it
        ViewUtils.showSuccess("Registration successfully processed for member " + name + "." + '\n'
            + "Total Price of equipment is " + totalPrice + " $ and the the total weight is "
            + totalWeight + " lb.");


      } else {
        // Output the price and weight taking into account the guide since the member wants it

        int totalCostForGuide = system.getPriceOfGuidePerWeek() * numberOfWeeksWanted;


        ViewUtils.showSuccess("Registration successfully processed for member " + name + "." + '\n'
            + "Total Price of equipment is " + totalPrice + " $, total price for the guide is "
            + totalCostForGuide + " $ and the the total weight is " + totalWeight + " lb.");

      }

      if (pathIndexRegister != null) {
        ClimbingPath path = system.getClimbingPaths().get(pathIndexRegister);
        ((Member) Member.getWithEmail(email)).setSelectedClimbingLocation(path.getLocation());

      }



      // Clear the temporary lists for the next customer
      bookedItemsToAdd.clear();
      numberOfItemsToAdd.clear();
      allBookedItemsList.clear();

      refreshListViewString(listOfItemsChosen, bookedItemsToAdd);

      refreshListViewInteger(listOfNumberOfItemsChosen, numberOfItemsToAdd);

      // Clear the price and weight
      totalPrice = 0;
      totalWeight = 0;
      pathIndexRegister = null;



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


    BookableItem item = BookableItem.getWithName(addedItemsList.getValue()); // Get the name of the
                                                                             // item chosen

    if (numberOfItemWanted <= 0) {
      ViewUtils.showError("The number of items wanted must be greater than 0.");
      return;

    }

    if (!(item == null)) { // Check thats the member chose an item.

      bookedItemsToAdd.add(item.getName()); // Add the name of the item to the list

      allBookedItemsList.add((Equipment) item); // Add the equipment to the
                                                // list
      numberOfItemsToAdd.add(numberOfItemWanted); // Add the number of equipment requested by the
                                                  // member


      refreshListViewString(listOfItemsChosen, bookedItemsToAdd);

      refreshListViewInteger(listOfNumberOfItemsChosen, numberOfItemsToAdd);


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

    BookableItem bundle = BookableItem.getWithName(addedBundlesList.getValue()); // Get the name of
                                                                                 // the item chosen

    if (!(bundle == null)) { // Check thats the member chose a bundle.

      bookedItemsToAdd.add(bundle.getName()); // Add the name of the item to the list

      allBookedItemsList.add((EquipmentBundle) bundle); // Add the equipment
                                                        // bundle to the list

      numberOfItemsToAdd.add(numberOfBundleWanted); // Add the number of equipment requested by the
                                                    // member


      refreshListViewString(listOfItemsChosen, bookedItemsToAdd);

      refreshListViewInteger(listOfNumberOfItemsChosen, numberOfItemsToAdd);

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
    String name = updateFirstName.getText() + " " + updateLastName.getText();
    String email = memberEmail.getText();
    String password = updatePassword.getText();
    String emergency = updateEmergencyPhone.getText();


    boolean hotel = updateHotelRequiredCheck.isSelected();
    boolean guide = updateGuideRequiredCheck.isSelected();


    if (pathIndexUpdate == null) {
      ViewUtils.showError("Please select a climbing path.");
      return;

    }


    // Check if information entered is alphanumeric
    if (!ViewUtils.isAlpha(name)) {
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
    if (updateFirstName.getText().equals("") || updateLastName.getText().equals("")
        || email.equals("") || password.equals("") || emergency.equals("")) {

      ViewUtils.showError("The input fields must not be empty.");
      return;

    }

    try {
      ClimbSafeFeatureSet2Controller.updateMember(email, password, name, emergency,
          numberOfWeeksWanted, guide, hotel, bookedItemsToUpdate, numberOfItemsToUpdate); // Try to
                                                                                          // Update
      // member

      // Compute the total price and weight of the item chosen by the member and if the member
      // desires a guide
      totalPriceForUpdate = computeTotalCost(numberOfWeeksWanted, guide, updateAllBookedItemsList,
          numberOfItemsToUpdate);
      totalWeightForUpdate = computeTotalWeight(updateAllBookedItemsList, numberOfItemsToUpdate);

      clearFieldsInUpdate();



      if (!guide)
        // Output the price and weight without taking into account the guide since the member does
        // not want it
        ViewUtils.showSuccess("Update successfully processed for member " + name + "." + '\n'
            + "Total Price of equipment is " + totalPriceForUpdate
            + " $ and the the total weight is " + totalWeightForUpdate + " lb.");

      else {

        int totalCostForGuide = system.getPriceOfGuidePerWeek() * numberOfWeeksWanted;

        // Output the price and weight taking into account the guide since the member wants it
        ViewUtils.showSuccess("Update successfully processed for member " + name + '\n'
            + "Total Price of equipment is: " + totalPriceForUpdate
            + " $, total price of the guide is: " + totalCostForGuide
            + " $ and the the total weight is " + totalWeightForUpdate + " lb.");

        int totalPriceWithGuide = totalCostForGuide + totalPriceForUpdate;

      }



      if (pathIndexUpdate != null) {
        ClimbingPath path = system.getClimbingPaths().get(pathIndexUpdate);
        ((Member) Member.getWithEmail(email)).setSelectedClimbingLocation(path.getLocation());

      }

      // Set the price and weight on the screen


      // Clear the temporary lists for the next customer
      bookedItemsToUpdate.clear();
      numberOfItemsToUpdate.clear();
      updateAllBookedItemsList.clear();

      refreshListViewString(listOfItemsChosenUpdate, bookedItemsToUpdate);

      refreshListViewInteger(listOfINumberOftemsChosenUpdate, numberOfItemsToUpdate);



      // Clear the price and weight
      totalPriceForUpdate = 0;
      totalWeightForUpdate = 0;
      pathIndexUpdate = null;



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


    BookableItem item = BookableItem.getWithName(updateItemName.getValue());

    if (numberOfItemWanted <= 0) {
      ViewUtils.showError("The number of items wanted must be greater than 0.");
      return;

    }

    if (!(item == null)) { // Check thats the member chose an item.

      bookedItemsToUpdate.add(item.getName()); // Add the name of the item to the list

      numberOfItemsToUpdate.add(numberOfItemWanted); // Add the number of equipment requested by the
                                                     // member

      updateAllBookedItemsList.add((Equipment) item); // Add the equipment to
                                                      // the list


      refreshListViewString(listOfItemsChosenUpdate, bookedItemsToUpdate);

      refreshListViewInteger(listOfINumberOftemsChosenUpdate, numberOfItemsToUpdate);


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

    // Get the bundle item from the selected from the comboBox
    BookableItem bundle = BookableItem.getWithName(updateBundleName.getValue());

    // Check thats the member chose a bundle.
    if (!(bundle == null)) {

      // Add the name of the bundle to the list
      bookedItemsToUpdate.add(bundle.getName());

      // Add the number of equipment requested by the member
      numberOfItemsToUpdate.add(numberOfBundleWanted);

      // Add the equipment bundle to the list
      updateAllBookedItemsList.add((EquipmentBundle) bundle);

      // Refresh the list to show the selected bundles and quantities
      refreshListViewString(listOfItemsChosenUpdate, bookedItemsToUpdate);
      refreshListViewInteger(listOfINumberOftemsChosenUpdate, numberOfItemsToUpdate);
    }

    // The member clicked on add without selecting any bundle
    else {
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

    // Check if information entered is not empty
    if (email.equals("")) {

      ViewUtils.showError("The input field must not be empty.");
      return;

    }

    // Delete the member with the select email
    User toBeDeleted = User.getWithEmail(email); // Return the User with the associated email

    if (toBeDeleted != null && toBeDeleted instanceof Member) { // If the user exists and is a
                                                                // member
      ClimbSafeFeatureSet1Controller.deleteMember(email); // Delete the member
      ViewUtils.showSuccess("Successfully deleted the member " + email);
      clearFieldsInDelete();
    } else {
      ViewUtils.showError("The member does not exist.");
      return;
    }
  }


  // Event Listener on Button[#removeItems].onAction
  @FXML
  /**
   * 
   * @param event
   */
  public void removeItemsFromChosen(ActionEvent event) {

    if (listOfItemsChosen.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please select an item to delete.");
      return;
    }
    bookedItemsToAdd.remove(listOfItemsChosen.getSelectionModel().getSelectedIndex());
    numberOfItemsToAdd.remove(listOfNumberOfItemsChosen.getSelectionModel().getSelectedIndex());
    refreshListViewString(listOfItemsChosen, bookedItemsToAdd);
    refreshListViewInteger(listOfNumberOfItemsChosen, numberOfItemsToAdd);
  }

  // Event Listener on Button[#removeItemsUpdate].onAction
  @FXML
  /**
   * 
   * @param event
   */
  public void removeItemsFromChosenUpdate(ActionEvent event) {
    if (listOfItemsChosenUpdate.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please select an item to delete.");
      return;
    }
    bookedItemsToUpdate.remove(listOfItemsChosenUpdate.getSelectionModel().getSelectedIndex());
    numberOfItemsToUpdate
        .remove(listOfINumberOftemsChosenUpdate.getSelectionModel().getSelectedIndex());
    refreshListViewString(listOfItemsChosenUpdate, bookedItemsToUpdate);
    refreshListViewInteger(listOfINumberOftemsChosenUpdate, numberOfItemsToUpdate);
  }

  // Event Listener on ListView[#listOfItemsChosen].onMouseClicked
  @FXML
  /**
   * 
   * @param event
   */
  public void selectItem(MouseEvent event) {
    listOfNumberOfItemsChosen.getSelectionModel()
        .select(listOfItemsChosen.getSelectionModel().getSelectedIndex());

  }

  // Event Listener on ListView[#listOfNumberOfItemsChosen].onMouseClicked
  @FXML
  /**
   * 
   * @param event
   */
  public void selectQuantity(MouseEvent event) {
    listOfItemsChosen.getSelectionModel()
        .select(listOfNumberOfItemsChosen.getSelectionModel().getSelectedIndex());

  }

  /**
   * 
   * @param listView
   * @param names
   */
  private void refreshListViewString(ListView<Label> listView, List<String> names) {
    listView.getItems().clear();
    for (String string : names) {
      listView.getItems().add(new Label(string));
    }
    listView.refresh();
  }

  /**
   * 
   * @param listView
   * @param quantities
   */
  private void refreshListViewInteger(ListView<Label> listView, List<Integer> quantities) {
    listView.getItems().clear();
    for (Integer ints : quantities) {
      listView.getItems().add(new Label(String.valueOf(ints)));
    }
    listView.refresh();
  }


  /**
   * 
   * @return
   */
  public List<String> getPathName() {

    List<String> climbingPathNames = new ArrayList();

    climbingPathNames.add("No paths in system.");

    List<ClimbingPath> allPaths = system.getClimbingPaths();

    String name = "";

    if (allPaths.size() > 0) {
      climbingPathNames.clear();

      for (ClimbingPath path : allPaths) {

        name = path.getLocation() + ", " + path.getDifficulty() + ", " + path.getLength() + " Km.";
        climbingPathNames.add(name);

      }
    }

    return climbingPathNames;
  }


  /**
   * 
   * @param box
   * @param prompt
   */
  private void setPromptText(ComboBox<String> box, String prompt) {
    box.setPromptText(prompt);
  }

  /**
   * 
   */
  public void initialize() {

    // Get the name of items and bundles from the system
    List<String> itemsList = getNameOfItems();
    List<String> bundlesList = getNameOfBundles();

    // Refresh the list of path
    refreshListViewString(listOfClimbingPaths, getPathName());
    refreshListViewString(listOfClimbingPathsUpdate, getPathName());

    // If there's items in the system, set them to the comboBoxes and set the prompt text
    // appropriately
    if (itemsList.size() > 0) {

      addedItemsList.setItems(FXCollections.observableList(itemsList));
      updateItemName.setItems(FXCollections.observableList(itemsList));

      setPromptText(addedItemsList, "Available items");
      setPromptText(updateItemName, "Available items");
    }

    // If no items in system, clear the names and set the prompt text appropriately
    else {

      try {
        // Clear the names of the items in the boxes
        addedItemsList.getItems().clear();
        updateItemName.getItems().clear();

        setPromptText(addedItemsList, "No items in system");
        setPromptText(updateItemName, "No items in system");

      }
      // Catch null pointer exception if initial has no items because cannot clear a empty list
      catch (Exception e) {
        setPromptText(addedItemsList, "No items in system");
        setPromptText(updateItemName, "No items in system");
      }
    }


    // If there's bundle in the system, set them to the comboBoxes and set the prompt text
    // appropriately
    if (bundlesList.size() > 0) {
      addedBundlesList.setItems(FXCollections.observableList(bundlesList));
      updateBundleName.setItems(FXCollections.observableList(bundlesList));

      setPromptText(addedBundlesList, "Available items");
      setPromptText(updateBundleName, "Available items");

    }
    // If no bundles in system, clear the names and set the prompt text appropriately
    else {

      try {
        // Clear the names of the bundles in the boxes
        addedBundlesList.getItems().clear();
        updateBundleName.getItems().clear();

        setPromptText(addedBundlesList, "No items in system");
        setPromptText(updateBundleName, "No items in system");

      }
      // Catch null pointer exception if initial has no items because cannot clear a empty list
      catch (Exception e) {
        setPromptText(addedBundlesList, "No items in system");
        setPromptText(updateBundleName, "No items in system");
      }
    }
  }

  /**
   * 
   * @param event
   */
  public void RefreshMemberRegister(Event event) {
    initialize();

  }

  /**
   * 
   * @param event
   */
  public void RefreshMemberUpdate(Event event) {
    initialize();
  }

  /**
   * 
   * @param event
   */
  public void RefreshMemberDelete(Event event) {
    initialize();
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

    // Returning the total price over the trip
    return totalPrice;


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
    // Returning the total weight over the trip
    return totalWeight;

  }



  /**
   * 
   * @return
   */
  private List<String> getNameOfItems() {

    system = ClimbSafeApplication.getClimbSafe();

    List<String> listOfNames = new ArrayList<>();

    for (Equipment item : system.getEquipment()) {

      listOfNames.add(item.getName());

    }

    return listOfNames;
  }

  /**
   * 
   * @return
   */
  private List<String> getNameOfBundles() {

    system = ClimbSafeApplication.getClimbSafe();

    List<String> listOfNames = new ArrayList<>();

    for (EquipmentBundle bundle : system.getBundles()) {

      listOfNames.add(bundle.getName());

    }
    return listOfNames;
  }


  // Event Listener on ListView[#listOfClimbingPaths].onMouseClicked
  @FXML
  /**
   * 
   * @param event
   */
  public void selectPath(MouseEvent event) {
    listOfClimbingPaths.getSelectionModel()
        .select(listOfClimbingPaths.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on ListView[#listOfClimbingPathsUpdate].onMouseClicked
  @FXML
  /**
   * 
   * @param event
   */
  public void selectPathUpdate(MouseEvent event) {
    listOfClimbingPathsUpdate.getSelectionModel()
        .select(listOfClimbingPathsUpdate.getSelectionModel().getSelectedIndex());;

  }

  // Event Listener on Button[#addPathButton].onAction
  @FXML
  /**
   * Choose a path for the user in the register tab
   * 
   * @param event
   */
  public void addPath(ActionEvent event) {

    // Check if the user selected a path
    if (listOfClimbingPaths.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please select a path to climb.");
      return;
    }

    // Check if there's no path in system and "wait for the admin to update this"
    if (listOfClimbingPaths.getSelectionModel().getSelectedItem().getText()
        .equals("No paths in system.")) {
      ViewUtils.showError("Please select a path to climb.");
      return;
    }

    // Get the index of the path chosen to add it later to the member
    pathIndexRegister = listOfClimbingPaths.getSelectionModel().getSelectedIndex();

    // Set the path chosen on the screen
    chosenPath.setText(listOfClimbingPaths.getSelectionModel().getSelectedItem().getText());

  }

  // Event Listener on Button[#updatePathButton].onAction
  @FXML
  /**
   * Choose a path for the user in the update tab
   * 
   * @param event
   */
  public void updatePath(ActionEvent event) {

    // Check if the user selected a path
    if (listOfClimbingPathsUpdate.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please select a path to climb.");
      return;
    }

    // Check if there's no path in system and "wait for the admin to update this"
    if (listOfClimbingPathsUpdate.getSelectionModel().getSelectedItem().getText()
        .equals("No paths in system.")) {

      ViewUtils.showError("Please select a path to climb.");
      return;
    }

    // Get the index of the path chosen to add it later to the member
    pathIndexUpdate = listOfClimbingPathsUpdate.getSelectionModel().getSelectedIndex();

    // Set the path chosen on the screen
    chosenPathUpdate
        .setText(listOfClimbingPathsUpdate.getSelectionModel().getSelectedItem().getText());

  }

  // Event Listener on ListView[#listOfItemsChosenUpdate].onMouseClicked
  @FXML
  /**
   * Select the items from the list in update and consequently select the quantity attached
   * 
   * @param event
   */
  public void selectItemUpdate(MouseEvent event) {
    listOfINumberOftemsChosenUpdate.getSelectionModel()
        .select(listOfItemsChosenUpdate.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on ListView[#listOfINumberOftemsChosenUpdate].onMouseClicked
  @FXML
  /**
   * Select the quantities from the list in update and consequently select the item attached
   * 
   * @param event
   */
  public void selectQuantitiesUpdate(MouseEvent event) {
    listOfItemsChosenUpdate.getSelectionModel()
        .select(listOfINumberOftemsChosenUpdate.getSelectionModel().getSelectedIndex());
  }


  /**
   * Clear all fields in the register Tab
   * 
   * @author Karl Rouhana
   */
  private void clearFieldsInRegister() {
    addFirstName.clear();
    addLastName.clear();
    addEmail.clear();
    addPassword.clear();
    addEmergencyPhone.clear();
    hotelRequiredCheck.disarm();
    guideRequiredCheck.disarm();
    chosenPath.setText("");
    addNumberWeeks.setText("");
    addItemsQuantities.setText("");
    addBundlesQuantities.setText("");
  }

  /**
   * Clear all fields in the update Tab
   * 
   * @author Karl Rouhana
   */
  private void clearFieldsInUpdate() {
    updateFirstName.clear();
    updateLastName.clear();
    memberEmail.clear();
    updatePassword.clear();
    updateEmergencyPhone.clear();
    updateHotelRequiredCheck.disarm();
    updateGuideRequiredCheck.disarm();
    chosenPathUpdate.setText("");
    updateNumberWeeks.setText("");
    updateBundleQuantity.setText("");
    updateItemQuantity.setText("");
  }

  /**
   * Clear all fields in the delete Tab
   * 
   * @author Karl Rouhana
   */
  private void clearFieldsInDelete() {
    toBedeletedMemberEmail.clear();
  }


}
