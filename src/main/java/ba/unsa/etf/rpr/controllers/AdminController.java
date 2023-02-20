package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.AdminManager;
import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.business.WatchlistManager;
import ba.unsa.etf.rpr.domain.Administrator;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.domain.Watchlist;
import ba.unsa.etf.rpr.exceptions.MovieException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX controller for admin management
 *
 * @author Anida Nezovic
 */
public class AdminController {

    public ListView<String> listView;
    public TextField idUser;
    public Button deleteUserBtn;
    public Button deleteMovieBtn;
    public TextField idMovie;
    public TextField movieRating;
    public TextField movieDuration;
    public TextField movieGenre;
    public TextField movieName;
    public Button addBtn;
    public Button logoutBtn;
    AdminManager adminManager = new AdminManager();
    MovieManager movieManager = new MovieManager();
    UserManager userManager = new UserManager();
    WatchlistManager watchlistManager = new WatchlistManager();
    public Button loginBtn;
    public Button cancelBtn;
    public TextField fieldUsername;
    public Label adminNameLabel;
    public PasswordField fieldPassword;

    /**
     * Method that closes current window when cancel button is clicked.
     *
     * @param actionEvent
     */
    public void cancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * Method by which the admin logs in. If entered information is invalid an alert pops up.
     *
     * @param actionEvent
     */
    public void loginClick(ActionEvent actionEvent) throws IOException, MovieException {
        List<Administrator> allAdmins = FXCollections.observableList(adminManager.getAll());
        boolean valid = false;

        for(int i = 0; i < allAdmins.size(); i++) {
            System.out.println(allAdmins.get(i).getUsername() + " " + allAdmins.get(i).getPassword());
            if(allAdmins.get(i).getUsername().equals(fieldUsername.getText()) && allAdmins.get(i).getPassword().equals(fieldPassword.getText())) {
                valid = true;
                break;
            }
        }

        Stage stage1 = (Stage)loginBtn.getScene().getWindow();
        stage1.close();

        if(valid) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin_login.fxml"));
            Parent root = loader.load();

            AdminController adminController = loader.getController();
            adminController.adminNameLabel.setText(fieldUsername.getText());

            stage.setTitle("Admin");
            stage.getIcons().add(new Image("/img/footer.png"));
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        }
        else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error alert");
            alert.setHeaderText("Invalid information!");
            alert.setContentText("You don't have administrator access.");

            alert.show();
        }
    }

    /**
     * This method is called when admin clicks on 'movies' button. It shows all movies that are in database.
     *
     * @param actionEvent
     */
    public void moviesBtnClick(ActionEvent actionEvent) throws MovieException {
        listView.getItems().clear();
        List<Movie> allMovies = FXCollections.observableList(movieManager.getAll());
        List<String> namesOfAllMovies = new ArrayList<>();

        for(int i = 0; i < allMovies.size(); i++) {
            namesOfAllMovies.add(allMovies.get(i).getId() + ". " + allMovies.get(i).getName());
        }
        listView.getItems().addAll(namesOfAllMovies);
    }

    /**
     * This method is called when admin clicks on 'users' button. It shows all users that are in database.
     *
     * @param actionEvent
     */
    public void usersBtnClick(ActionEvent actionEvent) throws MovieException {
        listView.getItems().clear();
        List<User> allUsers = FXCollections.observableList(userManager.getAll());
        List<String> namesOfAllUsers = new ArrayList<>();

        for(int i = 0; i < allUsers.size(); i++) {
            namesOfAllUsers.add(allUsers.get(i).getId() + ". " + allUsers.get(i).getName() + " " + allUsers.get(i).getLastName());
        }

        listView.getItems().addAll(namesOfAllUsers);
    }

    /**
     * This method opens a new window in which the admin can delete specific user.
     *
     * @param actionEvent
     */
    public void deleteUser(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/delete_user.fxml"));
        Parent root = loader.load();
        stage.setTitle("Delete user");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * This method opens a new window in which the admin can delete specific movie.
     *
     * @param actionEvent
     */
    public void deleteMovie(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/delete_movie.fxml"));
        Parent root = loader.load();
        stage.setTitle("Delete movie");
        stage.getIcons().add(new Image("/img/footer.png"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * This method opens a new window in which the admin can add a new movie.
     *
     * @param actionEvent
     */
    public void addMovie(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add.fxml"));
        Parent root = loader.load();
        stage.setTitle("Add movie");
        stage.getIcons().add(new Image("/img/footer.png"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * This method really does delete a user. It communicates directly with database.
     *
     * @param actionEvent
     */
    public void deleteUserOKClick(ActionEvent actionEvent) throws MovieException {
        List<User> allUsers = FXCollections.observableList(userManager.getAll());
        int id = Integer.parseInt(idUser.getText());

        Stage stage = (Stage)deleteUserBtn.getScene().getWindow();
        stage.close();
        if(id < 0 || id > allUsers.size()) {
            System.out.println("User is not deleted!" + id);
        }
        else {
            List<Watchlist> allWatchlists = FXCollections.observableList(watchlistManager.getAll());
            List<Integer> idWatchlists = new ArrayList<>();

            for(int i = 0; i < allWatchlists.size(); i++) {
                if(allWatchlists.get(i).getUserId() == id) {
                    idWatchlists.add(allWatchlists.get(i).getId());
                }
            }

            for(int i = 0; i < idWatchlists.size(); i++) {
                watchlistManager.delete(idWatchlists.get(i));
            }

            userManager.delete(id);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "You successfully deleted the user!", ButtonType.OK);
            a.show();
        }
    }

    public void deleteMovieOKClick(ActionEvent actionEvent) throws MovieException {
        List<Movie> allMovies = FXCollections.observableList(movieManager.getAll());
        int id = Integer.parseInt(idMovie.getText());

        Stage stage = (Stage)deleteMovieBtn.getScene().getWindow();
        stage.close();

        if(id < 0 || id > allMovies.size()) {
            System.out.println("Movie is not deleted!" + id);
        }
        else {
            List<Watchlist> allWatchlists = FXCollections.observableList(watchlistManager.getAll());
            List<Integer> idWatchlists = new ArrayList<>();

            for(int i = 0; i < allWatchlists.size(); i++) {
                String[] idMovies = allWatchlists.get(i).getMovies().split(",");

                for(int j = 0; j < idMovies.length; j++) {
                    if(id == Integer.parseInt(idMovies[j])) {
                        idWatchlists.add(allWatchlists.get(i).getId());
                    }
                }
            }

            for(int i = 0; i < idWatchlists.size(); i++) {
                watchlistManager.delete(idWatchlists.get(i));
            }

            movieManager.delete(id);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "You successfully deleted the movie!", ButtonType.OK);
            a.show();
        }
    }

    public void addMovieOKClick(ActionEvent actionEvent) throws MovieException {
        Movie newMovie = new Movie();
        newMovie.setName(movieName.getText());
        newMovie.setGenre(movieGenre.getText());
        newMovie.setDuration(movieDuration.getText());
        newMovie.setRating(movieRating.getText());
        movieManager.add(newMovie);

        Stage stage = (Stage)addBtn.getScene().getWindow();
        stage.close();

        Alert a = new Alert(Alert.AlertType.INFORMATION, "You successfully added the movie!", ButtonType.OK);
        a.show();
    }

    public void logoutBtnClick(ActionEvent actionEvent) {
        Stage stage = (Stage)logoutBtn.getScene().getWindow();
        stage.close();
    }
}
