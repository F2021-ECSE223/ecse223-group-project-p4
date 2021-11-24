package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet7Controller;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import javafx.event.ActionEvent;

public class HotelOperationsController {
	@FXML
	private Button buttonAddHotel;
	@FXML
	private TextField nameAddHotel;
	@FXML
	private TextField addressAddHotel;
	@FXML
	private TextField ratingAddHotel;
	@FXML
	private Button buttonUpdateHotel;
	@FXML
	private TextField newNameUpdateHotel;
	@FXML
	private TextField oldNameUpdateHotel;
	@FXML
	private TextField newAddressUpdateHotel;
	@FXML
	private TextField newRatingUpdateHotel;
	@FXML
	private Button buttonDeleteHotel;
	@FXML
	private TextField nameDeleteHotel;

	// Event Listener on Button[#buttonAddHotel].onAction
	@FXML
	public void actionAddHotel(ActionEvent event) {
		// TODO Autogenerated
	  
	  if (nameAddHotel.getText().isBlank()) {
        ViewUtils.showError("Please enter the name of the hotel.");
        return;
      }
      
      if(addressAddHotel.getText().isBlank()) {
        ViewUtils.showError("Please enter the name of the hotel.");
        return;
      }
      
      if(ratingAddHotel.getText().isBlank()) {
        ViewUtils.showError("Please enter the rating of the hotel.");
        return;
      }
      int rating;
      
      try {
        rating=Integer.parseInt(ratingAddHotel.getText());
      } catch (Exception e) {
        ViewUtils.showError("The rating of an hotel must be an integer from 1 to 5.");
        return;
      }
      try {
        ClimbSafeFeatureSet7Controller.addHotel(nameAddHotel.getText(), addressAddHotel.getText(), rating);
      } catch (Exception e) {
        ViewUtils.showError("Hotel already exists in the system.");
        return;
      }
      
      ViewUtils.showSuccess("The hotel was successfully added.");
	}
	// Event Listener on Button[#buttonUpdateHotel].onAction
	@FXML
	public void actionUpdateHotel(ActionEvent event) {
		// TODO Autogenerated
	  
	  if(oldNameUpdateHotel.getText().isBlank()) {
	    ViewUtils.showError("Please enter the name of the old hotel.");
	    return;
	  }
	  
	  if(newNameUpdateHotel.getText().isBlank()) {
        ViewUtils.showError("Please enter the name of the new hotel.");
        return;
	  }
	  
	  if(newAddressUpdateHotel.getText().isBlank()) {
        ViewUtils.showError("Please enter the address of the new hotel.");
        return;
      }
	  
	  if(newRatingUpdateHotel.getText().isBlank()) {
        ViewUtils.showError("Please enter the rating of the new hotel.");
        return;
      }
	  
	  int newRating;
      
      try {
        newRating=Integer.parseInt(newRatingUpdateHotel.getText().trim());
      } catch (Exception e) {
        ViewUtils.showError("The rating of an hotel must be an integer from 1 to 5.");
        return;
      }
      
      try {
        ClimbSafeFeatureSet7Controller.updateHotel(oldNameUpdateHotel.getText(), newNameUpdateHotel.getText(), newAddressUpdateHotel.getText(), newRating);
      } catch (Exception e) {
        ViewUtils.showError("Hotel already exists in the system.");
        return;
      }
      
      ViewUtils.showSuccess("The hotel was successfully added.");
	}
	// Event Listener on Button[#buttonDeleteHotel].onAction
	@FXML
	public void actionDeleteHotel(ActionEvent event) {
		// TODO Autogenerated
	  if(nameDeleteHotel.getText().isBlank()) {
	    ViewUtils.showError("Please enter the name of the hotel to be deleted.");
	    return;
	  }
	  
	  Hotel toBeDeleted=Hotel.getWithName(nameDeleteHotel.getText());
	  if (toBeDeleted==null) {
	    ViewUtils.showError("This hotel does not exist in the system.");
	    return;
	  }
	  
	  ClimbSafeFeatureSet1Controller.deleteHotel(nameDeleteHotel.getText());
	  ViewUtils.showSuccess("The hotel was successfully deleted.");;
	}
}