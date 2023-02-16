package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        primaryStage.setTitle("Welcome to TMDB!");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/img/footer.png"));
        //primaryStage.setMinHeight(490);
        //primaryStage.setMinWidth(740);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

/*public class App {
    public static void main() {

    }
}*/
