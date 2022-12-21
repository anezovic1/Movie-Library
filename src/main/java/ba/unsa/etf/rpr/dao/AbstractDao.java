package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.domain.Watchlist;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.sql.*;
import java.util.*;

/**
 * abstract class that implements core DAO CRUD methods for every entity
 * @author anida
 */
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

    public Connection getConnecetion() {
        return connecetion;
    }

    public abstract T row2object(ResultSet rs) throws MovieException;
    public abstract Map<String, Object> object2row(T object);
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

    public List<T> getAll() throws MovieException {
        String query = "SELECT * FROM " + this.tableName;
        List<T> objects = new ArrayList<>();
        try {
            PreparedStatement stmt = getConnecetion().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                T object = row2object(rs);
                objects.add(object);
            }
            rs.close();
            return objects;
        } catch (SQLException e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

    public void delete(int id) throws MovieException {
        String query = "DELETE FROM " + this.tableName + "WHERE id = ?";
        try {
            PreparedStatement stmt = getConnecetion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch(SQLException e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

    /**
     * Accepts KV storage of column names and return CSV of columns and question marks for insert statement
     * Example: (id, name, date) ?,?,?
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for(Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if(entry.getKey().equals("id")) continue;
            columns.append(entry.getKey());
            questions.append("?");
            if(row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }

        return new AbstractMap.SimpleEntry<String, String>(columns.toString(), questions.toString());
    }

    /**
     * Prepare columns for update statement id=?, name=?, ...
     * @param row
     */
    private String prepareUpdateParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for(Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if(entry.getKey().equals("id")) continue;
            columns.append(entry.getKey())
                    .append("= ?");
            if(row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }
    public T add(T item) throws MovieException {
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ")
                .append(tableName)
                .append(" (").append(columns.getKey()).append(") ")
                .append("VALUES (").append(columns.getValue()).append(")");

        try {
            PreparedStatement stmt = getConnecetion().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);

            int counter = 1;
            for(Map.Entry<String, Object> entry: row.entrySet()) {
                if(entry.getKey().equals("id")) continue;;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        } catch(SQLException e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

    public T update(T item) throws MovieException {
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(this.tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try {
            PreparedStatement stmt = getConnecetion().prepareStatement(builder.toString());
            int counter = 1;
            for(Map.Entry<String, Object> entry: row.entrySet()) {
                if(entry.getKey().equals("id")) continue;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter + 1, item.getId());
            stmt.executeUpdate();
            return item;
        } catch(SQLException e) {
            throw new MovieException(e.getMessage(), e);
        }
    }
}
