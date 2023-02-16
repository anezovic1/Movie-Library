package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exceptions.MovieException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.io.IOException;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {

    private final MovieManager movieManager = new MovieManager();
    public TextField idSearchItem;
    public Button moviesBtn;

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
        stage.getIcons().add(new Image("/img/footer.png"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    public void moviesBtnClick(ActionEvent actionEvent) throws IOException, MovieException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/movies.fxml"));
        Parent root = loader.load();
        MoviesController moviesController = loader.getController();

        List<Movie> allMovies = FXCollections.observableList(movieManager.getAll()); //uzela sve filmove iz baze

        HBox hbox = new HBox();

        for(int i = 0; i < allMovies.size(); i++) {
            Label newLabel = new Label();
            ImageView moviePoster = new ImageView();
            moviePoster.setImage(new Image("/img/" + allMovies.get(i).getId() + ".jpg"));
            moviePoster.setFitHeight(200);
            moviePoster.setFitWidth(150);
            newLabel.setText(allMovies.get(i).getName());
            newLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
            newLabel.setStyle("-fx-text-fill: #032541;");

            VBox singleMovie = new VBox();
            singleMovie.getChildren().add(moviePoster);
            singleMovie.getChildren().add(newLabel);
            singleMovie.setSpacing(10);
            singleMovie.setAlignment(Pos.CENTER);

            hbox.getChildren().add(singleMovie);
        }

        hbox.setSpacing(40);
        hbox.setStyle("-fx-background-color: #d0e8fd;");
        moviesController.idScrollPane.setContent(hbox);
        moviesController.idScrollPane.setPadding(new Insets(36.5));

        stage.setTitle("Movies in database");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    public void loginAdminClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
        stage.setTitle("Admin");
        stage.getIcons().add(new Image("/img/footer.png"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }
}
