package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.AdminManager;
import ba.unsa.etf.rpr.domain.Administrator;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminController {

    AdminManager adminManager = new AdminManager();
    public Button loginBtn;
    public Button cancelBtn;
    public TextField fieldUsername;
    public Label adminNameLabel;
    public PasswordField fieldPassword;

    public void cancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void loginClick(ActionEvent actionEvent) throws IOException, MovieException {
        /*List<Administrator> allAdmins = FXCollections.observableList(adminManager.getAll());
        boolean valid = false;
        String nameAndlastName = "";

        for(int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getUsername().equals(fieldUsername.getText()) && allUsers.get(i).getPassword().equals(fieldPassword.getText())) {
                nameAndlastName = allUsers.get(i).getName() + " " + allUsers.get(i).getLastName();
                valid = true;
                break;
            }
        }*/
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin_login.fxml"));
        Parent root = loader.load();

        AdminController adminController = loader.getController();
        adminController.adminNameLabel.setText(fieldUsername.getText());

        adminNameLabel.setText(fieldUsername.getText());
        stage.setTitle("Admin");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }
}
