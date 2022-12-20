package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.sql.*;
import java.util.Properties;

public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private Connection connecetion;
    private String tableName;

    public AbstractDao(String tableName) {
        try {
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            this.connecetion = DriverManager.getConnection(p.getProperty("db.connection_string"), p.getProperty("db.username"), p.getProperty("db.password"));
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

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
