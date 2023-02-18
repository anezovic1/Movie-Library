package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SignUpController {
    private final UserManager userManager = new UserManager();
    public Button cancelBtn;
    public PasswordField idPassword;
    public TextField idEmail;
    public TextField idUsername;
    public TextField idLastName;
    public TextField idName;
    public Button signupBtn;

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

    public void singupClick(ActionEvent actionEvent) throws MovieException, IOException {
        boolean valid = true;

        if(idName.getText().contains(" ") || idUsername.getText().contains(" ") || idLastName.getText().contains(" ")) {
            valid = false;
        }
        if(idName.getText() == null || idUsername.getText() == null || idLastName.getText() == null || idEmail.getText() == null || idPassword.getText() == null) {
            valid = false;
        }
        for(int i = 0; i < idName.getText().length(); i++) {
            if(idName.getText().charAt(i) < 'A' || (idName.getText().charAt(i) > 'Z' && idName.getText().charAt(i) < 'a') || idName.getText().charAt(i) > 'z') {
                valid = false;
                break;
            }
        }
        for(int i = 0; i < idLastName.getText().length(); i++) {
            if(idLastName.getText().charAt(i) < 'A' || (idLastName.getText().charAt(i) > 'Z' && idLastName.getText().charAt(i) < 'a') || idLastName.getText().charAt(i) > 'z') {
                valid = false;
                break;
            }
        }

        String validPassword = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(validPassword);
        Matcher matcher = pattern.matcher(idEmail.getText());

        if(!matcher.matches()) {
            valid = false;
        }

        if(!valid) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error alert");
            alert.setHeaderText("Can not add user");
            alert.setContentText("The information you entered is not valid! Please try again.");

            alert.show();
        }
        else {
            User newUser = new User();
            newUser.setName(idName.getText());
            newUser.setLastName(idLastName.getText());
            newUser.setUsername(idUsername.getText());
            newUser.setEmail(idEmail.getText());
            newUser.setPassword(idPassword.getText());
            userManager.add(newUser);

            Stage stage1 = (Stage)signupBtn.getScene().getWindow();
            stage1.close();

            Alert a = new Alert(Alert.AlertType.INFORMATION, "You successfully signed up! Please, try to log in.", ButtonType.OK);
            a.show();
        }
    }
}
