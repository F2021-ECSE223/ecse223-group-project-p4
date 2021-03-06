package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet7Controller;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class HotelOperationsController {
  @FXML
  private Button buttonAddHotel;
  @FXML
  private TextField nameAddHotel;
  @FXML
  private TextField addressAddHotel;
  @FXML
  private ComboBox<String> addRatingCombo = new ComboBox<String>();
  @FXML
  private ComboBox<String> updateRatingCombo = new ComboBox<String>();
  @FXML
  private Button buttonUpdateHotel;
  @FXML
  private TextField newNameUpdateHotel;
  @FXML
  private TextField oldNameUpdateHotel;
  @FXML
  private TextField newAddressUpdateHotel;
  @FXML
  private Button buttonDeleteHotel;

  @FXML
  private ListView<Label> HotelList = new ListView<Label>();
  @FXML
  private ListView<Label> RatingList = new ListView<Label>();
  @FXML
  private ListView<Label> HotelList2 = new ListView<Label>();
  @FXML
  private ListView<Label> RatingList2 = new ListView<Label>();
  @FXML
  private ListView<Label> rmHotelList = new ListView<Label>();
  @FXML
  private ListView<Label> rmRatingList = new ListView<Label>();



  ObservableList<String> ratingList = FXCollections.observableArrayList("One star", "Two star",
      "Three star", "Four star", "Five star");

  /**
   * This private method will initialize the hotel UI. It will first reset the combo boxes to "One
   * star" in the add hotel and update hotel tabs. Also, this method will refresh the current hotel
   * and rating lists using refreshHotels method explained below
   * 
   * @author Ralph Nassar
   * 
   */

  private void initialize() {
    addRatingCombo.setValue("One star");
    addRatingCombo.setItems(ratingList);
    updateRatingCombo.setValue("One star");
    updateRatingCombo.setItems(ratingList);

    refreshHotels(HotelList, RatingList); // Refreshing the lists in add hotel tab
    refreshHotels(HotelList2, RatingList2); // Refreshing the lists in update hotel tab
    refreshHotels(rmHotelList, rmRatingList); // Refreshing the lists in delete hotel tab


  }

  // Event Listener on Tab[#AddHotelTab].onSelectionChanged
  @FXML
  /**
   * Refreshes the UI page in the add hotel tab using the initialize() method.
   * 
   * @author Ralph Nassar
   * @param event
   */

  public void refreshAddTab(Event event) {
    initialize();
  }

  // Event Listener on Tab[#UpdateHotelTab].onSelectionChanged
  @FXML
  /**
   * Refreshes the UI page in the update hotel tab using the initialize() method.
   * 
   * @author Ralph Nassar
   * @param event
   */

  public void refreshUpdateTab(Event event) {
    initialize();
  }

  // Event Listener on Tab[#DeleteHotelTab].onSelectionChanged
  @FXML
  /**
   * Refreshes the UI page in the delete hotel tab using the initialize() method.
   * 
   * @author Ralph Nassar
   * @param event
   */
  public void refreshDeleteTab(Event event) {
    initialize();
  }

  // Event Listener on Button[#buttonAddHotel].onAction
  @FXML
  /**
   * A method executed when the button add is pressed in the add hotel tab. There are 4 cases:
   * 
   * Case 1: the text field to enter the name is empty, a popup that asks to enter the name of the
   * hotel appears. Nothing is saved.
   * 
   * Case 2: the text field to enter the address of the hotel is empty, a popup that asks to enter
   * the address of the hotel appears. Nothing is saved.
   * 
   * Case 3: the method addHotel in the hotel controller throws an exception if the input hotel
   * already exists in the system (the name of the hotel). If the input hotel already exists, a
   * popup that tells that the hotel already exists in the system appears. Nothing is saved.
   * 
   * Case 4: if none of the three cases above happen, the method addHotel in the controller of the
   * hotel will not throw any exception and will save all informations entered in the page. Also, a
   * popup will show the success, the text fields are cleared, the combo box is reset to one star
   * and we use the initialize() method to update the hotel and rating lists.
   * 
   * @author Ralph Nassar
   * @param event
   */

  public void actionAddHotel(ActionEvent event) {

    if (nameAddHotel.getText().isBlank()) {
      ViewUtils.showError("Please enter the name of the hotel.");
      return;
    }

    if (addressAddHotel.getText().isBlank()) {
      ViewUtils.showError("Please enter the name of the hotel.");
      return;
    }

    try {
      ClimbSafeFeatureSet7Controller.addHotel(nameAddHotel.getText(), addressAddHotel.getText(),
          ratingInt(addRatingCombo.getValue().toString()));
    } catch (Exception e) {
      ViewUtils.showError("Hotel already exists in the system.");

      return;
    }

    ViewUtils.showSuccess("The hotel was successfully added.");
    nameAddHotel.clear();
    addressAddHotel.clear();
    addRatingCombo.setValue("One star");
    initialize();
  }

  // Event Listener on Button[#buttonUpdateHotel].onAction
  @FXML
  /**
   * A method executed when the button update is pressed in the update hotel tab. There are 5 cases:
   * 
   * Case 1: the text field to enter the old name is empty, a popup that asks to enter the old name
   * of the hotel appears. Nothing is saved.
   * 
   * Case 2: the text field to enter the new name of the hotel is empty, a popup that asks to enter
   * the new name of the hotel appears. Nothing is saved.
   * 
   * Case 3: the text field to enter the new address of the hotel is empty, a popup that asks to
   * enter the new address of the hotel appears. Nothing is saved.
   * 
   * Case 4: the method updateHotel in the hotel controller throws an exception if the input old
   * hotel does not exist in the system (the old name of the hotel). If the input old hotel does not
   * exist, a popup that tells that the hotel does not exist in the system appears. Nothing is
   * saved.
   * 
   * Case 5: if none of the three cases above happen, the method updateHotel in the controller of
   * the hotel will not throw any exception and will save all informations entered in the page.
   * Also, a popup will show the success, the text fields are cleared, the combo box is reset to one
   * star and we use the initialize() method to update the hotel and rating lists.
   * 
   * @author Ralph Nassar
   * @param event
   */

  public void actionUpdateHotel(ActionEvent event) {

    if (oldNameUpdateHotel.getText().isBlank()) {
      ViewUtils.showError("Please enter the name of the old hotel.");
      return;
    }

    if (newNameUpdateHotel.getText().isBlank()) {
      ViewUtils.showError("Please enter the name of the new hotel.");
      return;
    }

    if (newAddressUpdateHotel.getText().isBlank()) {
      ViewUtils.showError("Please enter the address of the new hotel.");
      return;
    }

    try {
      ClimbSafeFeatureSet7Controller.updateHotel(oldNameUpdateHotel.getText(),
          newNameUpdateHotel.getText(), newAddressUpdateHotel.getText(),
          ratingInt(updateRatingCombo.getValue().toString()));
    } catch (Exception e) {
      ViewUtils.showError(e.getMessage());
      return;
    }

    ViewUtils.showSuccess("The hotel was successfully updated.");
    oldNameUpdateHotel.clear();
    newNameUpdateHotel.clear();
    newAddressUpdateHotel.clear();
    updateRatingCombo.setValue("One star");

    initialize();
  }

  // Event Listener on Button[#buttonDeleteHotel].onAction
  @FXML

  /**
   * A method executed when the button delete is pressed in the delete hotel tab. Then, the lists
   * are refreshed.
   * 
   * @author Ralph Nassar
   * @param event
   */

  public void actionDeleteHotel(ActionEvent event) {

    // Choose a hotel from the list to be deleted

    String toBeDeleted = rmHotelList.getSelectionModel().getSelectedItem().getText();

    ClimbSafeFeatureSet1Controller.deleteHotel(toBeDeleted); // delete the chosen hotel
    refreshHotels(rmHotelList, rmRatingList); // refresh both lists
    ViewUtils.showSuccess("The hotel was successfully deleted."); // message that show the success
    rmHotelList.getSelectionModel().clearSelection();

    initialize();
  }

  /**
   * Private method that takes as input the string that will correspond in the methods above to the
   * rating of a hotel. Returns 1 if "One Star", 2 if "Two star"...
   * 
   * @author Ralph Nassar
   * @param m
   */
  private int ratingInt(String m) {

    switch (m) {
      case ("One star"):
        return 1;
      case ("Two star"):
        return 2;
      case ("Three star"):
        return 3;
      case ("Four star"):
        return 4;
      case ("Five star"):
        return 5;
      default:
        return -1;
    }
  }

  // Event Listener on ListView[#rmHotelList].onMouseClicked
  @FXML
  /**
   * A method that select the hotel, depending on the position of the cursor.
   * 
   * @author Ralph Nassar
   * @param event
   */
  public void selectedHotel(MouseEvent event) {

    rmRatingList.getSelectionModel().select(rmHotelList.getSelectionModel().getSelectedIndex());
  }

  // Event Listener on ListView[#rmHotelList].onMouseClicked
  @FXML
  /**
   * A method that select the rating, depending on the position of the cursor.
   * 
   * @author Ralph Nassar
   * @param event
   */
  public void selectedRating(MouseEvent event) {

    rmHotelList.getSelectionModel().select(rmRatingList.getSelectionModel().getSelectedIndex());
  }

  /**
   * This method will refresh the two lists, the hotel list and the rating list. After clearing both
   * lists, we will iterate through the new hotels added (or the remaining one if deleted) So we
   * will finally get a new hotel list and a new rating list, after operations are done.
   * 
   * @author Ralph Nassar
   * 
   * @param hotelList: the old list of hotels
   * @param ratingList: the old list of ratings
   */
  private void refreshHotels(ListView<Label> hotelList, ListView<Label> ratingList) {

    hotelList.getItems().clear();
    ratingList.getItems().clear();

    for (Hotel hotel : ClimbSafeApplication.getClimbSafe().getHotels()) {
      hotelList.getItems().add(new Label(hotel.getName()));
      ratingList.getItems().add(new Label(hotel.getRating().toString()));

    }

  }
}

