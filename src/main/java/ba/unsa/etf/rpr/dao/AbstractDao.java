package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private Connection connecetion;
    private String tableName;

    public abstract T row2object(ResultSet rs) throws MovieException;
    public T getById(int id) throws MovieException {
        String query = "SELECT * FROM " + this.tableName + " WHERE id = ?";

        try {
            PreparedStatement stmt = this.connecetion.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                T result = row2object(rs);
                rs.close();
                return result;
            }
            else {
                throw new MovieException("Object not found");
            }
        } catch (SQLException e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

}
