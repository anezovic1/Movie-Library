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

    public WatchlistDaoSQLImpl() {
        super("watchlists");
    }

    @Override
    public Watchlist row2object(ResultSet rs) throws MovieException {
        try {
            Watchlist watchlist = new Watchlist();
            watchlist.setId(rs.getInt("id"));
            watchlist.setName(rs.getString("name"));
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
        return row;
    }
}
