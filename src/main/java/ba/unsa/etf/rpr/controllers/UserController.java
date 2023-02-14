package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UserController {
    public Label userNameLabel;
    public Button idLogoutBtn;

    public void logoutBtnClick(ActionEvent actionEvent) {
        Stage stage = (Stage)idLogoutBtn.getScene().getWindow();
        stage.close();
    }
}
