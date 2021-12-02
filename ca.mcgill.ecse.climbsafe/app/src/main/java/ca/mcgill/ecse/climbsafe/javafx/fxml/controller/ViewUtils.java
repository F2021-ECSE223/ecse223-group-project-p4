package ca.mcgill.ecse.climbsafe.javafx.fxml.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewUtils {

  /**
   * Makes a popup window
   * 
   * @param title The title of the popup window
   * @param message The message of the popup window
   * @author Adam Kazma (inspired from BTMS)
   */
  public static void makePopupWindow(String title, String message) {
    Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    VBox dialogPane = new VBox();

    // create UI elements
    Text text = new Text(message);
    Button okButton = new Button("OK");
    okButton.setOnAction(a -> dialog.close());

    // display the popup window
    int innerPadding = 10; // inner padding/spacing
    int outerPadding = 100; // outer padding
    dialogPane.setSpacing(innerPadding);
    dialogPane.setAlignment(Pos.CENTER);
    dialogPane.setPadding(new Insets(innerPadding, innerPadding, innerPadding, innerPadding));
    dialogPane.getChildren().addAll(text, okButton);
    Scene dialogScene = new Scene(dialogPane, outerPadding + 5 * message.length(), outerPadding);
    dialog.setScene(dialogScene);
    dialog.setTitle(title);
    dialog.show();
  }

  /**
   * Makes a popup window with title Error
   * 
   * @param message The message of the popup window
   * @author Adam Kazma (inspired from BTMS)
   */
  public static void showError(String message) {
    makePopupWindow("Error", message);
  }

  /**
   * Makes a popup window with title Successful Operation
   * 
   * @param message The message of the popup window
   * @author Adam Kazma
   */
  public static void showSuccess(String message) {
    makePopupWindow("Successful Operation", message);
  }

  /**
   * Checks that the input string is alphabetic
   * 
   * @param name The string we want to check is alpha numeric
   * @author Adam Kazma (inspired from BTMS)
   */
  public static boolean isAlpha(String name) {
    for (Character ch : name.toCharArray()) {
      if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) {
        return false;
      }
    }
    return true;
  }
}
