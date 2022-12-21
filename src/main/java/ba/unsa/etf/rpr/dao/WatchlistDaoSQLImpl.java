package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Watchlist;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WatchlistDaoSQLImpl implements WatchlistDao {

    private Connection connection;

    public WatchlistDaoSQLImpl() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/my_database", "my_username", "my_password");
            /* I don't want to push information about my database. */

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Watchlist getById(int id)  {
        String query = "SELECT * FROM watchlists WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                Watchlist watchlist = new Watchlist();
                watchlist.setId(rs.getInt("id"));
                watchlist.setName(rs.getString("name"));
                rs.close();
                return watchlist;
            }
            else {
                return null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Watchlist add(Watchlist item) {
        String query = "INSERT INTO watchlists(name) VALUES(?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Watchlist update(Watchlist item) {
        String query = "UPDATE watchlists SET name = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.getId());
            stmt.executeUpdate();
            return item;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM watchlists WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Watchlist> getAll() {
        String query = "SELECT * FROM watchlists";
        List<Watchlist> watchlists = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Watchlist watchlist = new Watchlist();
                watchlist.setId(rs.getInt("id"));
                watchlist.setName(rs.getString("name"));
                watchlists.add(watchlist);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return watchlists;
    }
}
