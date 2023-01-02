package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInController {
    public TextField fieldUsername;
    public PasswordField fieldPassword;

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
}
