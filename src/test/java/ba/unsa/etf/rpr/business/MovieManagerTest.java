package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MovieManagerTest {
    private MovieManager movieManager = new MovieManager();
    private Movie newMovie = new Movie("Inception", "action", "2h 38m", "8.8");

    @BeforeEach
    public void initializeObjectsWeNeed() {
        movieManager = Mockito.mock(MovieManager.class);
    }

    @Test
    void addNewMovie() throws MovieException {
        movieManager.add(newMovie);
        Mockito.verify(movieManager).add(newMovie);
    }

    @Test
    void deleteMovie() throws MovieException {
        movieManager.delete(1);
        Mockito.verify(movieManager).delete(1);
    }

    @Test
    void updateMovie() throws MovieException {
        System.out.println(newMovie.getRating());
        newMovie.setRating("9.0");
        movieManager.update(newMovie);
        System.out.println(newMovie.getRating());
        Mockito.verify(movieManager).update(newMovie);
    }
}
