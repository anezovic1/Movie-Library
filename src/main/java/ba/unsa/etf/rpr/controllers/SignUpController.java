package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {
    public Button cancelBtn;
    public PasswordField idPassword;
    public TextField idEmail;
    public TextField idUsername;
    public TextField idLastName;
    public TextField idName;

    @FXML
    public void initialize() {
        idUsername.getStyleClass().removeAll("fieldNotCorrect");
        idUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(idUsername.getText().trim().isEmpty()) {
                    idUsername.getStyleClass().removeAll("fieldCorrect");
                    idUsername.getStyleClass().add("fieldNotCorrect");
                }
                else {
                    idUsername.getStyleClass().removeAll("fieldNotCorrect");
                    idUsername.getStyleClass().add("fieldCorrect");
                }
            }
        });

        idName.getStyleClass().removeAll("fieldNotCorrect");
        idName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(idName.getText().trim().isEmpty()) {
                    idName.getStyleClass().removeAll("fieldCorrect");
                    idName.getStyleClass().add("fieldNotCorrect");
                }
                else {
                    idName.getStyleClass().removeAll("fieldNotCorrect");
                    idName.getStyleClass().add("fieldCorrect");
                }
            }
        });

        idLastName.getStyleClass().removeAll("fieldNotCorrect");
        idLastName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(idLastName.getText().trim().isEmpty()) {
                    idLastName.getStyleClass().removeAll("fieldCorrect");
                    idLastName.getStyleClass().add("fieldNotCorrect");
                }
                else {
                    idLastName.getStyleClass().removeAll("fieldNotCorrect");
                    idLastName.getStyleClass().add("fieldCorrect");
                }
            }
        });


    }

    public void cancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void singupClick(ActionEvent actionEvent) {

    }
}
