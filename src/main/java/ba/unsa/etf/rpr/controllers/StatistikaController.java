package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.business.WatchlistManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.Watchlist;
import ba.unsa.etf.rpr.exceptions.MovieException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.List;

public class StatistikaController {

    public TableView tableView;
    private WatchlistManager watchlistManager = new WatchlistManager();
    private MovieManager movieManager = new MovieManager();

    public void initialize() throws MovieException {
        List<Watchlist> allWatchlists = FXCollections.observableList(watchlistManager.getAll());

        TableColumn tblMovie = new TableColumn();
        tblMovie.setMinWidth(50);
        tblMovie.setText("naziv filma");
        tblMovie.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
        tableView.getColumns().add(tblMovie);

        List<Movie> allMovies = FXCollections.observableList(movieManager.getAll());

        for(int i = 0; i < allWatchlists.size(); i++) {
            TableColumn tbl = new TableColumn();
            tbl.setText(allWatchlists.get(i).getName());
            tbl.setMinWidth(50);
            tbl.setUserData(allWatchlists.get(i));
            tbl.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                @Override
                public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                   Watchlist watchlist = (Watchlist) cellDataFeatures.getTableColumn().getUserData();
                    Movie movie = (Movie) cellDataFeatures.getValue();

                    System.out.println(watchlist);
                    System.out.println(movie);
                    String znak = "o";
                    if(watchlist.getMovies().contains(String.valueOf(movie.getId()))) {
                        znak = "+";
                    }
                    return new SimpleStringProperty(znak);
                }
            });
            tableView.getColumns().add(tbl);
        }

        tableView.setItems(FXCollections.observableList(allMovies));
        tableView.refresh();
    }



}
