package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.util.List;

public class MovieManager {

    public List<Movie> getAll() throws MovieException {
        return DaoFactory.movieDao().getAll();
    }

    public List<Movie> searchMovies(String text) throws MovieException {
        return DaoFactory.movieDao().searchByText(text);
    }

    public void delete(int id) throws MovieException {
        DaoFactory.movieDao().delete(id);
    }

    public Movie getById(int movieID) throws MovieException {
        return DaoFactory.movieDao().getById(movieID);
    }

    public void update(Movie movie) throws MovieException {
        DaoFactory.movieDao().update(movie);
    }

    public Movie add(Movie movie) throws MovieException {
        return DaoFactory.movieDao().add(movie);
    }
}
