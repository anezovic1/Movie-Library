package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.business.WatchlistManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.domain.Watchlist;
import ba.unsa.etf.rpr.exceptions.MovieException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class UserController {
    public Label userNameLabel;
    public Button idLogoutBtn;
    public Button cancelBtn;
    public TextField watchlistName;
    public TextField listOfMoviesId;
    private UserManager userManager = new UserManager();
    private WatchlistManager watchlistManager = new WatchlistManager();
    private final MovieManager movieManager = new MovieManager();
    public ListView listView;

    private int loggedUserId;

    public UserController() {
    }

    public UserController(int loggedUserId) {
        this.loggedUserId = loggedUserId;
    }

    @FXML
    public void initialize() throws MovieException {
        List<User> allUsers = FXCollections.observableList(userManager.getAll());

        String nameAndlastName = "";

        for(int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getId() == loggedUserId) {
                nameAndlastName = allUsers.get(i).getName() + " " + allUsers.get(i).getLastName();
                break;
            }
        }

        this.userNameLabel.setText(nameAndlastName);
    }

    public void logoutBtnClick(ActionEvent actionEvent) {
        Stage stage = (Stage)idLogoutBtn.getScene().getWindow();
        stage.close();
    }

    public void createWatchlistClick(ActionEvent actionEvent) throws IOException, MovieException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/create_watchlist.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        System.out.println("OVO JE ID TRENUTNOG USERRA: " + loggedUserId);

        this.listView.getItems().clear();

        List<Movie> allMovies = FXCollections.observableList(movieManager.getAll());
        List<String> namesOfAllMovies = new ArrayList<>();
        for(int i = 0; i < allMovies.size(); i++) {
            namesOfAllMovies.add(allMovies.get(i).getId() + ". " + allMovies.get(i).getName());
        }

        this.listView.getItems().addAll(namesOfAllMovies);

        stage.setTitle("Creating watchlist");
        stage.getIcons().add(new Image("/img/footer.png"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    public void createBtnClick(ActionEvent actionEvent) throws MovieException, IOException {
        Watchlist watchlist = new Watchlist();

        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();
        LogInController logInController = loader.getController();

        List<User> allUsers = FXCollections.observableList(userManager.getAll());
        int idUser = 0;
        for(int i = 0; i < allUsers.size(); i++) {
            if(logInController.getUserName().equals(allUsers.get(i).getName() + " " + allUsers.get(i).getLastName())) {
                idUser = allUsers.get(i).getId();
                break;
            }
        }*/

        /*watchlist.setName(watchlistName.getText());
        watchlist.setUserId(loggedUserId);
        watchlist.setMovies(listOfMoviesId.getText());
        watchlistManager.add(watchlist);*/
    }

    public void cancelBtnClick(ActionEvent actionEvent) {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }
}
