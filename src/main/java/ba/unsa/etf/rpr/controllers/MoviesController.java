package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX controller for full movie list management
 *
 * @author Anida Nezovic
 */
public class MoviesController {

    public Button idCancelBtn;
    public VBox idVbox;
    public ScrollPane idScrollPane;

    /**
     * Method that closes current window when cancel button is clicked.
     *
     * @param actionEvent
     */
    public void cancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage)idCancelBtn.getScene().getWindow();
        stage.close();
    }
}
