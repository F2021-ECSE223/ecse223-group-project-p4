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
import ca.mcgill.ecse.climbsafe.controller.ExtraFeaturesController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.ClimbingPath;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
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
	private ComboBox<String> addedItemsList;
	@FXML
	private ComboBox<String> addedBundlesList;
	@FXML
	private Label showTotalWeight;
	@FXML
	private Label showTotalPrice;
	@FXML
	private ListView<Label> listOfItemsChosen;
	@FXML
	private ListView<Label> listOfNumberOfItemsChosen;
	@FXML
	private Button removeItems;
	@FXML
	private Button addPathButton;
	@FXML
	private ListView<Label> listOfClimbingPaths;
	@FXML
	private Label registrationSucessfulMessage;
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
	private ComboBox<String> updateItemName;
	@FXML
	private ComboBox<String> updateBundleName;
	@FXML
	private ListView<Label> listOfClimbingPathsUpdate;
	@FXML
	private Button updatePathButton;
	@FXML
	private Button removeItemsUpdate;
	@FXML
	private ListView<Label> listOfItemsChosenUpdate;
	@FXML
	private ListView<Label> listOfINumberOftemsChosenUpdate;
	@FXML
	private Label registrationSucessfulMessage1;
	@FXML
	private Label showTotalPrice1;
	@FXML
	private Label showTotalWeight1;
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
    
    private int pathIndexRegister ;
    private int pathIndexUpdate ;

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
    private void clearFieldsInUpdate() {
      updateFirstName.clear(); 
      updateLastName.clear(); 
      memberEmail.clear();
      updatePassword.clear();
      updateEmergencyPhone.clear();
      updateHotelRequiredCheck.disarm();
      updateGuideRequiredCheck.disarm();
      chosenPathUpdate.setText("");
    }
    private void clearFieldsInDelete() {
      toBedeletedMemberEmail.clear();
    }
    

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
      
      ClimbingPath location = system.getClimbingPaths().get(pathIndexRegister);
      
      boolean hotel = hotelRequiredCheck.isSelected();
      boolean guide = guideRequiredCheck.isSelected();


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

        registrationSucessfulMessage.setText("Registration successfully processed for member " + name);
        clearFieldsInRegister();

        if (!guide) {
          // Output the price and weight without taking into account the guide since the member does
          // not want it
          ViewUtils.showSuccess("Registration successfully processed for member " + name +"."+ '\n'
              + "Total Price of equipment is " + totalPrice + " s and the the total weight is "
              + totalWeight + " lb.");

        // Set the price and weight on the screen
        showTotalWeight.setText(totalWeight + " lb");
        showTotalPrice.setText(totalPrice  + " s");
        }
        else {
          // Output the price and weight taking into account the guide since the member wants it

          int totalCostForGuide = system.getPriceOfGuidePerWeek() * numberOfWeeksWanted;

         int totalPriceWithGuide = totalCostForGuide + totalPrice;

          ViewUtils.showSuccess("Registration successfully processed for member " + name +"."+ '\n'
              + "Total Price of equipment is " + totalPrice + " s, total price for the guide is "
              + totalCostForGuide + " s and the the total weight is " + totalWeight + " lb.");

          // Set the price and weight on the screen
          showTotalPrice.setText( totalPriceWithGuide+ " s");
          showTotalWeight.setText(totalWeight + " lb");
        }


        // Clear the temporary lists for the next customer
        bookedItemsToAdd.clear();
        numberOfItemsToAdd.clear();
        // Clear the price and weight
        totalPrice = 0;
        totalWeight = 0;
        pathIndexRegister = 0;
        
    
         ExtraFeaturesController.setClimbingPath(email, location.getLocation());
        
        
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


      BookableItem item = BookableItem.getWithName(addedItemsList.getValue()); // Get the name of the item chosen

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

      BookableItem bundle = BookableItem.getWithName(addedBundlesList.getValue()); // Get the name of the item chosen

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
      String name = updateFirstName.getText() + " " +  updateLastName.getText();
      String email = memberEmail.getText();
      String password = updatePassword.getText();
      String emergency = updateEmergencyPhone.getText();
      ClimbingPath location =  system.getClimbingPaths().get(pathIndexUpdate);
      boolean hotel = updateHotelRequiredCheck.isSelected();
      boolean guide = updateGuideRequiredCheck.isSelected();

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

        registrationSucessfulMessage1.setText("Update successfully processed for member " + name);
        clearFieldsInUpdate();


        if (!guide)
          // Output the price and weight without taking into account the guide since the member does
          // not want it
          ViewUtils.showSuccess("Update successfully processed for member " + name + "." + '\n'
              + "Total Price of equipment is " + totalPriceForUpdate
              + " s and the the total weight is " + totalWeightForUpdate + " lb.");

        else {

          int totalCostForGuide = system.getPriceOfGuidePerWeek() * numberOfWeeksWanted;

          // Output the price and weight taking into account the guide since the member wants it
          ViewUtils.showSuccess("Update successfully processed for member " + name + '\n'
              + "Total Price of equipment is: " + totalPriceForUpdate
              + " s, total price of the guide is: " + totalCostForGuide
              + " s and the the total weight is " + totalWeightForUpdate + " lb.");

          int totalPriceWithGuide = totalCostForGuide + totalPriceForUpdate;
          showTotalPrice1.setText( totalWeightForUpdate+ " lb");
          showTotalWeight1.setText( totalPriceWithGuide+ " s");
        }

        // Set the price and weight on the screen

        
        // Clear the temporary lists for the next customer
        bookedItemsToUpdate.clear();
        numberOfItemsToUpdate.clear();

        // Clear the price and weight
        totalPriceForUpdate = 0;
        totalWeightForUpdate = 0;
        
        if(location != null)
        ExtraFeaturesController.setClimbingPath(email, location.getLocation());


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

      BookableItem bundle = BookableItem.getWithName(updateBundleName.getValue());


      if (!(bundle == null)) { // Check thats the member chose a bundle.

        bookedItemsToUpdate.add(bundle.getName()); // Add the name of the bundle to the list

        numberOfItemsToUpdate.add(numberOfBundleWanted); // Add the number of equipment requested by
                                                         // the member

        updateAllBookedItemsList.add((EquipmentBundle) bundle); // Add the
                                                                                     // equipment
                                                                                     // bundle to the
                                                                                     // list
        refreshListViewString(listOfItemsChosenUpdate, bookedItemsToUpdate);
        
        refreshListViewInteger(listOfINumberOftemsChosenUpdate, numberOfItemsToUpdate);
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



      // Check if information entered is not empty
      if (email.equals("")) {

        ViewUtils.showError("The input must not be empty.");
        return;

      }


      // Delete the member with the select email
      ClimbSafeFeatureSet1Controller.deleteMember(email);
      clearFieldsInDelete();

      ViewUtils.showSuccess("The account with email "+email+" was successfully deleted.");

    }


     // Event Listener on Button[#removeItems].onAction
  @FXML
  public void removeItemsFromChosen(ActionEvent event) {
    
    if(listOfItemsChosen.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please select an item to delete.");
      return;
    }
    bookedItemsToAdd.remove(listOfItemsChosen.getSelectionModel().getSelectedIndex());
    numberOfItemsToAdd.remove(listOfNumberOfItemsChosen.getSelectionModel().getSelectedIndex());
    refreshListViewString(listOfItemsChosen, bookedItemsToAdd);
    refreshListViewInteger(listOfNumberOfItemsChosen, numberOfItemsToAdd);
  }
  
  @FXML
  public void removeItemsFromChosenUpdate(ActionEvent event) {
    if(listOfItemsChosenUpdate.getSelectionModel().isEmpty()) {
      ViewUtils.showError("Please select an item to delete.");
      return;
    }
    bookedItemsToUpdate.remove(listOfItemsChosenUpdate.getSelectionModel().getSelectedIndex());
    numberOfItemsToUpdate.remove(listOfINumberOftemsChosenUpdate.getSelectionModel().getSelectedIndex());
    refreshListViewString(listOfItemsChosenUpdate, bookedItemsToUpdate);
    refreshListViewInteger(listOfINumberOftemsChosenUpdate, numberOfItemsToUpdate);
  }

  // Event Listener on ListView[#listOfItemsChosen].onMouseClicked
  @FXML
 
  public void selectItem(MouseEvent event) {
    listOfNumberOfItemsChosen.getSelectionModel().select(listOfItemsChosen.getSelectionModel().getSelectedIndex());

  }

  // Event Listener on ListView[#listOfNumberOfItemsChosen].onMouseClicked
  @FXML
  public void selectQuantity(MouseEvent event) {
    listOfItemsChosen.getSelectionModel().select(listOfNumberOfItemsChosen.getSelectionModel().getSelectedIndex());

  }

  
  private void refreshListViewString(ListView<Label> listView, List<String> names) {
    listView.getItems().clear();
    for (String string : names) {
      listView.getItems().add(new Label(string));      
    }
    listView.refresh();
  }
  
  private void refreshListViewInteger(ListView<Label> listView, List<Integer> quantities) {
    listView.getItems().clear();
    for (Integer ints : quantities) {
      listView.getItems().add(new Label(String.valueOf(ints)));      
    }
    listView.refresh();
  }
  
  
  

  public List<String> getPathName() {
    
    List<String> climbingPathNames = new ArrayList();
    
    climbingPathNames.add("No paths in system.");
    
    List<ClimbingPath> allPaths = system.getClimbingPaths();
    
    String name = "";
    
    if(allPaths.size() > 0) {
      climbingPathNames.clear();
      
      for(ClimbingPath path : allPaths) {
       
        name = path.getLocation() + ", "+path.getDifficulty()+", "+path.getLength()+" Km.";
        climbingPathNames.add(name);
        
      }
  }
  
    return climbingPathNames;
  }
  
  
  public void initialize() {
    
    List<String> itemsList = getNameOfItems();
    List<String> bundlesList = getNameOfBundles();
    
    refreshListViewString(listOfClimbingPaths, getPathName());
    
    refreshListViewString(listOfClimbingPathsUpdate, getPathName());

    registrationSucessfulMessage1.setText("");
    registrationSucessfulMessage.setText("");


    
    if(itemsList.size() > 0) {

      addedItemsList.setItems(FXCollections.observableList(itemsList));
      updateItemName.setItems(FXCollections.observableList(itemsList));
      addedItemsList.setPromptText("Available items");
      updateItemName.setPromptText("Available items");

    }
    
    else {
      try {
      addedItemsList.getItems().clear();
      updateItemName.getItems().clear();
      addedItemsList.setPromptText("No items in system");
      updateItemName.setPromptText("No items in system");
      }catch(Exception e) {
      addedItemsList.setPromptText("No items in system");
      updateItemName.setPromptText("No items in system");
      }
      }
    
    if(bundlesList.size() > 0) {
      addedBundlesList.setItems(FXCollections.observableList(bundlesList));
      updateBundleName.setItems(FXCollections.observableList(bundlesList));
      addedBundlesList.setPromptText("Available items");
      updateBundleName.setPromptText("Available items");
    }
    
    else {
  
      try {
        addedBundlesList.getItems().clear();
        updateBundleName.getItems().clear();
        addedBundlesList.setPromptText("No items in system");
        updateBundleName.setPromptText("No items in system");
      }catch(Exception e) {
        addedBundlesList.setPromptText("No items in system");
        updateBundleName.setPromptText("No items in system");
      }

    }
      
  }
  
  public void RefreshMemberRegister(Event event) {
   
    
    initialize();
    
}
  public void RefreshMemberUpdate(Event event) {
 
    initialize();

}
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


  
  private List<String> getNameOfItems(){
   
    system = ClimbSafeApplication.getClimbSafe();
    
    List<String> listOfNames = new ArrayList<>();
          
      for(Equipment item : system.getEquipment()) {
        
        listOfNames.add(item.getName());
        
      }
    
    return listOfNames;
  }
  
  private List<String> getNameOfBundles(){
    
    system = ClimbSafeApplication.getClimbSafe();
    
    List<String> listOfNames = new ArrayList<>();
    
    for(EquipmentBundle bundle : system.getBundles()) {
      
      listOfNames.add(bundle.getName());
      
    }
    return listOfNames;
  }
  

	   // Event Listener on ListView[#listOfClimbingPaths].onMouseClicked
    @FXML
    public void selectPath(MouseEvent event) {
      listOfClimbingPaths.getSelectionModel().select(listOfClimbingPaths.getSelectionModel().getSelectedIndex());
    }
	
	   // Event Listener on ListView[#listOfClimbingPathsUpdate].onMouseClicked
    @FXML
    public void selectPathUpdate(MouseEvent event) {
      listOfClimbingPathsUpdate.getSelectionModel().select(listOfClimbingPathsUpdate.getSelectionModel().getSelectedIndex());;

    }
    // Event Listener on Button[#addPathButton].onAction
    @FXML
    public void addPath(ActionEvent event) {
      if(listOfClimbingPaths.getSelectionModel().isEmpty()) {
        ViewUtils.showError("Please select a path to climb.");
        return;
      }
      
      if(listOfClimbingPaths.getSelectionModel().getSelectedItem().getText().equals("No paths in system.")) {
        ViewUtils.showError("Please select a path to climb.");
        return;
      }
      
      pathIndexRegister = listOfClimbingPaths.getSelectionModel().getSelectedIndex();
      
      
      
      chosenPath.setText(listOfClimbingPaths.getSelectionModel().getSelectedItem().getText());

    }
    
    // Event Listener on Button[#updatePathButton].onAction
    @FXML
    public void updatePath(ActionEvent event) {
      if(listOfClimbingPathsUpdate.getSelectionModel().isEmpty()) {
        ViewUtils.showError("Please select a path to climb.");
        return;
      }
      
      if(listOfClimbingPathsUpdate.getSelectionModel().getSelectedItem().getText().equals("No paths in system.")) {
        ViewUtils.showError("Please select a path to climb.");
        return;
      }

      pathIndexUpdate = listOfClimbingPathsUpdate.getSelectionModel().getSelectedIndex();
      
      chosenPathUpdate.setText(listOfClimbingPathsUpdate.getSelectionModel().getSelectedItem().getText());

    }
    // Event Listener on Button[#removeItemsUpdate].onAction

    // Event Listener on ListView[#listOfItemsChosenUpdate].onMouseClicked
    @FXML
    public void selectItemUpdate(MouseEvent event) {
      listOfINumberOftemsChosenUpdate.getSelectionModel().select(listOfItemsChosenUpdate.getSelectionModel().getSelectedIndex());
    }
    // Event Listener on ListView[#listOfINumberOftemsChosenUpdate].onMouseClicked
    @FXML
    public void selectQuantitiesUpdate(MouseEvent event) {
      listOfItemsChosenUpdate.getSelectionModel().select(listOfINumberOftemsChosenUpdate.getSelectionModel().getSelectedIndex());
    }

    
}
