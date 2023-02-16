package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 * @author anida
 */
public class MovieDaoSQLImpl extends AbstractDao<Movie> implements MovieDao {

    private static MovieDaoSQLImpl instance = null;
    public MovieDaoSQLImpl() {
        super("movies");
    }

    public static MovieDaoSQLImpl getInstance() {
        if(instance == null) {
            instance = new MovieDaoSQLImpl();
        }
        return instance;
    }
    @Override
    public Movie row2object(ResultSet rs) throws MovieException {
        try {
            Movie movie = new Movie();
            movie.setId(rs.getInt("id"));
            movie.setName(rs.getString("name"));
            movie.setDuration(rs.getString("duration"));
            movie.setGenre(rs.getString("genre"));
            movie.setRating(rs.getString("rating"));
            return movie;
        } catch(SQLException e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Movie object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("duration", object.getDuration());
        row.put("rating", object.getRating());
        row.put("genre", object.getGenre());
        return row;
    }

    @Override
    public List<Movie> searchByText(String text) throws MovieException {
        return executeQuery("SELECT * FROM movies WHERE name LIKE concat('%', ?, '%')", new Object[]{text});
    }
}
