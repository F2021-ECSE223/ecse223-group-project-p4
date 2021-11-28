package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ClimbingPathOperationsController {

    @FXML
    private Tab DeletePathTab;

    @FXML
    private Tab UpdatePathTab;

    @FXML
    private TextField addBundleName;

    @FXML
    private ComboBox<?> addDifficultyCombo;

    @FXML
    private Button addNewPath;

    @FXML
    private Tab addPathTab;

    @FXML
    private Button deleteExistingPath;

    @FXML
    private ListView<?> newDiffList;

    @FXML
    private ComboBox<?> newDifficultyCombo;

    @FXML
    private ListView<?> newDistList;

    @FXML
    private TextField newPathDistance;

    @FXML
    private ListView<?> newPathList;

    @FXML
    private TextField oldPathName;

    @FXML
    private ListView<?> pathDiffList;

    @FXML
    private ListView<?> pathDistList;

    @FXML
    private ListView<?> pathList;

    @FXML
    private ListView<?> rmDiffList;

    @FXML
    private ListView<?> rmDistList;

    @FXML
    private ListView<?> rmPathList;

    @FXML
    private Button updateExistingPath;

    @FXML
    private TextField updatedPathDistance;

    @FXML
    private TextField updatedPathName;

    @FXML
    void AddPath(ActionEvent event) {

    }

    @FXML
    void RefreshSystemPaths1(ActionEvent event) {

    }

    @FXML
    void RefreshSystemPaths2(ActionEvent event) {

    }

    @FXML
    void RefreshSystemPaths3(ActionEvent event) {

    }

    @FXML
    void UpdatePath(ActionEvent event) {

    }

    @FXML
    void deletePath(ActionEvent event) {

    }

    @FXML
    void selectedEq(MouseEvent event) {

    }

}
