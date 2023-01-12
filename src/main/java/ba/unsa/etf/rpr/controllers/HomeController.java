package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exceptions.MovieException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {

    private final MovieManager movieManager = new MovieManager();
    public TextField idSearchItem;

    public void loginButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage.setTitle("Log in");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    public void signUpButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/signup.fxml"));
        stage.setTitle("Sign up");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }


    public void searchBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/movie.fxml"));
        Parent root = loader.load();
        MovieController movieController = loader.getController();
        movieController.idSearchLabel.setText(movieController.idSearchLabel.getText() + " \"" + idSearchItem.getText() + "\"");

        movieController.genreColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        movieController.titleColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));

        try {
            movieController.moviesTable.setItems(FXCollections.observableList(movieManager.getAll()));
            movieController.moviesTable.refresh();

            movieController.moviesTable.setItems(FXCollections.observableList(movieManager.searchMovies(idSearchItem.getText())));
            movieController.moviesTable.refresh();
        } catch (MovieException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }

        stage.setTitle("Movies");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    public void moviesBtnClick(ActionEvent actionEvent) {
    }
}
