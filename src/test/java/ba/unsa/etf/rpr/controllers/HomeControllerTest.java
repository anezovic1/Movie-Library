package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
public class HomeControllerTest {
    @Start
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        stage.setTitle("Welcome to TMDB!");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
    }

    @Test
    void moviesBtnClickTest(FxRobot robot) {
        Button moviesButton = robot.lookup("#moviesBtn").queryAs(Button.class);
        assertNotNull(moviesButton);
        robot.clickOn("#moviesBtn");

        robot.lookup("#fullMovieList").tryQuery().isPresent();
        Label movies = robot.lookup("#fullMovieList").queryAs(Label.class);
        assertNotNull(movies);
    }

    @Test
    void loginBtnClickTest(FxRobot robot) {
        Button loginButton = robot.lookup("#loginBtn").queryAs(Button.class);
        assertNotNull(loginButton);
        robot.clickOn("#loginBtn");

        robot.lookup("#username").tryQuery().isPresent();
        Label username = robot.lookup("#username").queryAs(Label.class);
        assertNotNull(username);
    }

}
