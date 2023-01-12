package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Movie;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MovieController {
    public Label idSearchLabel;
    public TableView moviesTable;
    public TableColumn<Movie, String> genreColumn;
    public TableColumn<Movie, String> titleColumn;
}
