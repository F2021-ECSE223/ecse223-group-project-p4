package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.fxml.FXML;

import javafx.event.Event;

public class MainPageController {

    @FXML
    private BundleOperationsController bundleTabController ;
	// Event Listener on Tab.onSelectionChanged
	@FXML
	public void refreshBundleTab(Event event) {
		bundleTabController.initialize();
	}
}
