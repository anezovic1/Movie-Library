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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LogInController {

    public Button loginBtn;
    UserManager userManager = new UserManager();
    public TextField fieldUsername;
    public PasswordField fieldPassword;
    public Button cancelBtn;

    @FXML
    public void initialize() {
        fieldUsername.getStyleClass().removeAll("fieldNotCorrect");
        fieldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
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


    public void loginButtonClick(ActionEvent actionEvent) throws MovieException, IOException {
        List<User> allUsers = FXCollections.observableList(userManager.getAll());
        boolean valid = false;
        int userId = 0;

        for(int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getUsername().equals(fieldUsername.getText()) && allUsers.get(i).getPassword().equals(fieldPassword.getText())) {
                userId = allUsers.get(i).getId();
                valid = true;
                break;
            }
        }

        Stage stage1 = (Stage)loginBtn.getScene().getWindow();
        stage1.close();

        if(valid) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.fxml"));
            UserController userController = new UserController(userId);
            loader.setController(userController);
            Parent root = loader.load();

            stage.setTitle("You are successfully logged in!");
            stage.getIcons().add(new Image("/img/footer.png"));
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog Box");
            alert.setHeaderText("Please sing up first!");
            alert.setContentText("Go to our home page and sing up.");
            alert.show();
        }
    }

    public void cancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }
}
