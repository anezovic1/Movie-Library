package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {
    public TextField fieldUsername;
    public PasswordField fieldPassword;
    public Button cancelBtn;

    @FXML
    public void initialize() {
        fieldUsername.getStyleClass().removeAll("fieldNotCorrect");
        fieldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(fieldUsername.getText().trim().isEmpty()) {
                    fieldUsername.getStyleClass().removeAll("fieldCorrect");
                    fieldUsername.getStyleClass().add("fieldNotCorrect");
                }
                else {
                    fieldUsername.getStyleClass().removeAll("fieldNotCorrect");
                    fieldUsername.getStyleClass().add("fieldCorrect");
                }
            }
        });
    }
    public void loginButtonClick(ActionEvent actionEvent) {

    }

    public void cancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }
}
