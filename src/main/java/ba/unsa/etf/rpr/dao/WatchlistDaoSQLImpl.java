package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Watchlist;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of the DAO
 * @author anida
 */
public class WatchlistDaoSQLImpl extends AbstractDao<Watchlist> implements WatchlistDao {

    private static WatchlistDaoSQLImpl instance = null;
    public WatchlistDaoSQLImpl() {
        super("watchlists");
    }

    public static WatchlistDaoSQLImpl getInstance() {
        if(instance == null) {
            instance = new WatchlistDaoSQLImpl();
        }
        return instance;
    }

    public static void removeInstance() {
        if(instance != null) {
            instance = null;
        }
    }
    @Override
    public Watchlist row2object(ResultSet rs) throws MovieException {
        try {
            Watchlist watchlist = new Watchlist();
            watchlist.setId(rs.getInt("id"));
            watchlist.setName(rs.getString("name"));
            watchlist.setUserId(rs.getInt("user_id"));
            watchlist.setMovies(rs.getString("movies"));
            return watchlist;
        } catch(SQLException e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Watchlist object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("user_id", object.getUserId());
        row.put("movies", object.getMovies());
        return row;
    }
}
