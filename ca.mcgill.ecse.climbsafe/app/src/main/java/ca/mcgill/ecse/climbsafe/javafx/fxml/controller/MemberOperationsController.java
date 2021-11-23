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
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Tab;

import javafx.scene.control.CheckBox;




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
	private ComboBox addedItemsList = new ComboBox<BookedItem>(FXCollections.observableList(system.getBookedItems()));
	@FXML
	private ComboBox addedBundlesList = new ComboBox<>(FXCollections.observableList(system.getBundleItems()));
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
	private ComboBox updateItemName = new ComboBox<BookedItem>(FXCollections.observableList(system.getBookedItems()));
	@FXML
	private ComboBox updateBundleName = new ComboBox<>(FXCollections.observableList(system.getBundleItems()));
	@FXML
	private Tab deleteMemberTab;
	@FXML
	private Button deleteMemberButton;
	@FXML
	private TextField toBedeletedMemberEmail;
	
	private List<String> bookedItemsToAdd = new ArrayList<>();
	private List<Integer> numberOfItemsToAdd = new ArrayList<>();
	
	private Integer totalWeight = 0;
	private Integer totalPricePerWeek = 0;

	
	

	private static ClimbSafe system = ClimbSafeApplication.getClimbSafe();
	
	
	// Event Listener on Button[#registerMemberButton].onAction
	@FXML
	public void registerMemberUI(ActionEvent event) {
	    
      String name = addFirstName.getText() + addLastName.getText();
      String email = addEmail.getText();
      String password = addPassword.getText();
      String emergency = addEmergencyPhone.getText();
      boolean hotel = hotelRequiredCheck.isSelected();
      boolean guide = guideRequiredCheck.isSelected();

	  
	  if (!ViewUtils.isAlpha(name) || !ViewUtils.isAlpha(email) 
          || !ViewUtils.isAlpha(password) || !ViewUtils.isAlpha(emergency)){
        ViewUtils.showError("The input must only contain letters.");
        return;
      }
      
      int numberOfWeeksWanted;
      
      try {
        numberOfWeeksWanted = Integer.parseInt(this.addNumberWeeks.getText());
      }
      catch(Exception e){
        ViewUtils.showError("The number of weeks wanted must be an integer");
        return;
      }
      
      if(name.equals("") ||  email.equals("") ||  password.equals("") ||
          emergency.equals("")) {
        
        ViewUtils.showError("The input must not be empty.");
        return;
        
      }
      
      try {
        ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergency, numberOfWeeksWanted, guide, hotel, bookedItemsToAdd, numberOfItemsToAdd);
        bookedItemsToAdd.clear();
        numberOfItemsToAdd.clear();
        
        totalWeight = 0;
        totalPricePerWeek = 0 ;
        
        showTotalWeight.setText(" 0 lb");
        showTotalPrice.setText("0 s");
        
      }
      catch(InvalidInputException e) {
        ViewUtils.showError(e.getMessage());
        return;
      }
      
      
      
	  
	}
	// Event Listener on Button[#addItemButton].onAction
	@FXML
	public void addItem(ActionEvent event) {
	  
	  int numberOfItemWanted;
	  
	  
	  try {
	    numberOfItemWanted = Integer.parseInt(this.addItemsQuantities.getText());
      }
      catch(Exception e){
        ViewUtils.showError("The number of items wanted must be an integer");
        return;
      }
	  
	  
	  bookedItemsToAdd.add((String) addedItemsList.getValue());
	  numberOfItemsToAdd.add(numberOfItemWanted);

	  
	  Equipment temp = (Equipment) BookableItem.getWithName((String) addedItemsList.getValue());
	
	  totalWeight =+ temp.getWeight() * numberOfItemWanted;
	  totalPricePerWeek =+ temp.getPricePerWeek() * numberOfItemWanted ;
	  
	  showTotalWeight.setText(String.valueOf(totalWeight) + " lb");
	  showTotalPrice.setText(String.valueOf(totalPricePerWeek) + " s");
		
	  
	}
	// Event Listener on Button[#addBundleButton].onAction
	@FXML
	public void addBundle(ActionEvent event) {
      int numberOfBundleWanted;
      
      try {
        numberOfBundleWanted = Integer.parseInt(this.addBundlesQuantities.getText());
      }
      catch(Exception e){
        ViewUtils.showError("The number of bundles wanted must be an integer");
        return;
      }
      
      bookedItemsToAdd.add((String) addedBundlesList.getValue());
      numberOfItemsToAdd.add(numberOfBundleWanted);
      
      Equipment temp = (Equipment) BookableItem.getWithName((String) addedBundlesList.getValue());
      
      totalWeight =+ temp.getWeight() * numberOfBundleWanted;
      totalPricePerWeek =+ temp.getPricePerWeek() * numberOfBundleWanted ;
      
      }
	
	
	// Event Listener on Button[#updateMemberButton].onAction
	@FXML
	public void updateMemberUI(ActionEvent event) {
	    
      String name = updateFirstName.getText() + updateLastName.getText();
      String email = memberEmail.getText();
      String password = updatePassword.getText();
      String emergency = updateEmergencyPhone.getText();
      boolean hotel = updateHotelRequiredCheck.isSelected();
      boolean guide = updateGuideRequiredCheck.isSelected();

      
      if (!ViewUtils.isAlpha(name) || !ViewUtils.isAlpha(email) 
          || !ViewUtils.isAlpha(password) || !ViewUtils.isAlpha(emergency)){
        ViewUtils.showError("The input must only contain letters.");
        return;
      }
      
      int numberOfWeeksWanted;
      
      try {
        numberOfWeeksWanted = Integer.parseInt(this.updateNumberWeeks.getText());
      }
      catch(Exception e){
        ViewUtils.showError("The number of weeks wanted must be an integer");
        return;
      }
      
      if(name.equals("") ||  email.equals("") ||  password.equals("") ||
          emergency.equals("")) {
        
        ViewUtils.showError("The input must not be empty.");
        return;
        
      }
      
      try {
        ClimbSafeFeatureSet2Controller.updateMember(email, password, name, emergency, numberOfWeeksWanted, guide, hotel, bookedItemsToAdd, numberOfItemsToAdd);
        bookedItemsToAdd.clear();
        numberOfItemsToAdd.clear();
        
        
      }
      catch(InvalidInputException e) {
        ViewUtils.showError(e.getMessage());
        return;
      }
      
	  
	}
	// Event Listener on Button[#updateAddItemButton].onAction
	@FXML
	public void updateItem(ActionEvent event) {
	    int numberOfItemWanted;
	      
	      
	      try {
	        numberOfItemWanted = Integer.parseInt(this.updateItemQuantity.getText());
	      }
	      catch(Exception e){
	        ViewUtils.showError("The number of items wanted must be an integer");
	        return;
	      }
	      
	      
	      bookedItemsToAdd.add((String) updateItemName.getValue());
	      numberOfItemsToAdd.add(numberOfItemWanted);

	      
	      Equipment temp = (Equipment) BookableItem.getWithName((String) updateItemName.getValue());
	    
	      totalWeight =+ temp.getWeight() * numberOfItemWanted;
	      totalPricePerWeek =+ temp.getPricePerWeek() * numberOfItemWanted ;
	      
	
	        
	      
	}
	// Event Listener on Button[#updateAddIBundleBuntton].onAction
	@FXML
	public void updateBundle(ActionEvent event) {
      int numberOfBundleWanted;
      
      try {
        numberOfBundleWanted = Integer.parseInt(this.updateBundleQuantity.getText());
      }
      catch(Exception e){
        ViewUtils.showError("The number of bundles wanted must be an integer");
        return;
      }
      
      bookedItemsToAdd.add((String) updateBundleName.getValue());
      numberOfItemsToAdd.add(numberOfBundleWanted);
      
      Equipment temp = (Equipment) BookableItem.getWithName((String) updateBundleName.getValue());
      
      totalWeight =+ temp.getWeight() * numberOfBundleWanted;
      totalPricePerWeek =+ temp.getPricePerWeek() * numberOfBundleWanted ;
      
	}
	
	
	
	// Event Listener on Button[#deleteMemberButton].onAction
	@FXML
	public void deleteMemberUI(ActionEvent event) {
	  
	  String email = toBedeletedMemberEmail.getText();
	  
	  if (!ViewUtils.isAlpha(email)){
        ViewUtils.showError("The input must only contain letters.");
        return;
      }
	  
      if(email.equals("")) {
        
        ViewUtils.showError("The input must not be empty.");
        return;
        
      }
    
 
        ClimbSafeFeatureSet1Controller.deleteMember(email);

	  
	}
}
