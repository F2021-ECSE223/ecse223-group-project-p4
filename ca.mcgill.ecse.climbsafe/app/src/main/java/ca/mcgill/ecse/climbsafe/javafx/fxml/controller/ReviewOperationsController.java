package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

public class ReviewOperationsController {
	@FXML
	private Button submitTripReview;
	@FXML
	private TextField memberEmailTripReview;
	@FXML
	private TextField commentTripReview;
	@FXML
	private ComboBox ratingTripReview;

	// Event Listener on Button[#submitTripReview].onAction
	@FXML
	public void submitReviewAction(ActionEvent event) {
		// TODO Autogenerated
	}
}