package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.business.WatchlistManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.Row;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.domain.Watchlist;
import ba.unsa.etf.rpr.exceptions.MovieException;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX controller for user management
 *
 * @author Anida Nezovic
 */
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
    public ListView<String> listView;
    public TableView<Row> watchlistsTable;
    public TableColumn<Row, String> watchlistColumn;
    public TableColumn<Row, String> moviesColumn;
    private int loggedUserId;

    /**
     * Class constructor.
     */
    public UserController() {
    }

    /**
     * Class constructor specifying id of the user that is currently logged in.
     */
    public UserController(int loggedUserId) {
        this.loggedUserId = loggedUserId;
    }

    /**
     * This method updates users table view of watchlists.
     */
    public void showWatchlists() throws MovieException {
        List<Watchlist> allWatchlists = FXCollections.observableList(watchlistManager.getAll());
        List<String> namesOfAllWatchlists = new ArrayList<>();
        List<String> moviesOfWatchlist = new ArrayList<>();

        for(int i = 0; i < allWatchlists.size(); i++) {
            if(allWatchlists.get(i).getUserId() == this.loggedUserId) {
                namesOfAllWatchlists.add(allWatchlists.get(i).getName()); //   free time, my favourite
                moviesOfWatchlist.add(allWatchlists.get(i).getMovies());  //  "1,4,7", "1,4"
            }
        }


        Map<String, StringBuilder> movies = new HashMap<>();
        List<Movie> allMovies = FXCollections.observableList(movieManager.getAll());

        for(int i = 0; i < moviesOfWatchlist.size(); i++) {
            StringBuilder namesOfAllMovies = new StringBuilder("");
            String[] splittedMovies = moviesOfWatchlist.get(i).split(",");

            for(int j = 0; j < splittedMovies.length; j++) {
                for(int k = 0; k < allMovies.size(); k++) {
                    if(allMovies.get(k).getId() == Integer.parseInt(splittedMovies[j])) {
                        if(j != splittedMovies.length - 1) {
                            namesOfAllMovies.append(allMovies.get(k).getName() + ", ");
                        }
                        else {
                            namesOfAllMovies.append(allMovies.get(k).getName());
                        }
                    }
                }
            }

            movies.put(namesOfAllWatchlists.get(i), namesOfAllMovies);
        }

        this.watchlistColumn.setCellValueFactory(new PropertyValueFactory<Row, String>("header"));
        this.moviesColumn.setCellValueFactory(new PropertyValueFactory<Row, String>("value"));

        List<Row> rowList = new ArrayList<>();
        for (Map.Entry<String, StringBuilder> entry : movies.entrySet()) {
            rowList.add(new Row(entry.getKey(), entry.getValue().toString()));
        }
        this.watchlistsTable.setItems(FXCollections.observableList(rowList));
        //this.watchlistsTable.refresh();
    }

    /**
     * This method sets value of the label. Value is the full name of the current user.
     */
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

        showWatchlists();
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

        showWatchlists();
    }

    /**
     * Method that closes current window when cancel button is clicked.
     *
     * @param actionEvent
     */
    public void cancelBtnClick(ActionEvent actionEvent) {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }
}
