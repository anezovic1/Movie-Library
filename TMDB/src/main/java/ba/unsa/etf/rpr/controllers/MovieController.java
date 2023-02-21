package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX controller for movies search results management
 *
 * @author Anida Nezovic
 */
public class MovieController {
    public Label idSearchLabel;
    public TableView moviesTable;
    public TableColumn<Movie, String> genreColumn;
    public TableColumn<Movie, String> titleColumn;
    public Button cancelBtn;

    /**
     * Method that opens up a window for logging in.
     * Same as in HomeController.
     *
     * @param actionEvent
     */
    public void loginButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage.setTitle("Log in");
        stage.getIcons().add(new Image("/img/footer.png"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Method that opens up a window for signing up.
     * Same as in HomeController.
     *
     * @param actionEvent
     */
    public void signUpButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/signup.fxml"));
        stage.setTitle("Sign up");
        stage.getIcons().add(new Image("/img/footer.png"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Method that closes current window when cancel button is clicked.
     *
     * @param actionEvent
     */
    public void cancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }
}
