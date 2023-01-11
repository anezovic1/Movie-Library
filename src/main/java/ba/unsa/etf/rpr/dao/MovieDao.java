package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.util.List;

/**
 * Dao interface for Movie domain bean
 * @author anida
 */
public interface MovieDao extends Dao<Movie> {
    List<Movie> searchByText(String text) throws MovieException;
}
