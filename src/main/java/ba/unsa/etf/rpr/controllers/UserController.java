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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class UserController {
    public Label userNameLabel;
    public Button idLogoutBtn;
    public Button cancelBtn;
    public Button createBtn;
    public TextField watchlistName;
    public TextField listOfMoviesId;
    private UserManager userManager = new UserManager();
    private WatchlistManager watchlistManager = new WatchlistManager();
    private final MovieManager movieManager = new MovieManager();
    public ListView listView;

    public TableView watchlistsTable;

    public TableColumn watchlistColumn;

    public TableColumn moviesColumn;

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

        List<Watchlist> allWatchlists = FXCollections.observableList(watchlistManager.getAll());
        List<String> namesOfAllWatchlists = new ArrayList<>();
        List<String> moviesOfWatchlist = new ArrayList<>();

        for(int i = 0; i < allWatchlists.size(); i++) {
            if(allWatchlists.get(i).getUserId() == this.loggedUserId) {
                namesOfAllWatchlists.add(allWatchlists.get(i).getName()); //   free time, my favourite
                moviesOfWatchlist.add(allWatchlists.get(i).getMovies());  //  "1,4,7", "1,4"
            }
        }

        Map<String, List<String>> movies = new HashMap<>();

        List<Movie> allMovies = FXCollections.observableList(movieManager.getAll());

        for(int i = 0; i < moviesOfWatchlist.size(); i++) {
            List<String> namesOfAllMovies = new ArrayList<>();
            String[] splittedMovies = moviesOfWatchlist.get(i).split(",");

            for(int j = 0; j < splittedMovies.length; j++) {
                for(int k = 0; k < allMovies.size(); k++) {
                    if(allMovies.get(k).getId() == Integer.parseInt(splittedMovies[j])) {
                        namesOfAllMovies.add(allMovies.get(k).getName());
                    }

                }
            }

            movies.put(moviesOfWatchlist.get(i), namesOfAllMovies);
        }

        for (Map.Entry<String, List<String>> entry : movies.entrySet()) {
            System.out.println("ovo je: " + entry.getKey() + " " + entry.getValue());
            this.watchlistsTable.getColumns().addAll(entry.getKey(), entry.getValue());
        }
        this.watchlistsTable.refresh();
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

        watchlist.setName(watchlistName.getText());
        watchlist.setUserId(loggedUserId);
        watchlist.setMovies(listOfMoviesId.getText());
        watchlistManager.add(watchlist);

        Stage stage1 = (Stage)createBtn.getScene().getWindow();
        stage1.close();

        Alert a = new Alert(Alert.AlertType.INFORMATION, "You successfully created your watchlist!", ButtonType.OK);
        a.show();
    }

    public void cancelBtnClick(ActionEvent actionEvent) {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }
}
