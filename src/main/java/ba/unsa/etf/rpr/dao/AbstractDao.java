package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.domain.Watchlist;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements core DAO CRUD methods for every entity.
 * @author Anida Nezovic
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private static Connection connection;
    private String tableName;

    /**
     * Class constructor specifying name of the specific table.
     *
     * @param tableName
     */
    public AbstractDao(String tableName) {
        this.tableName = tableName;
        createConnection();
    }

    /**
     * Method that creates connection to database.
     */
    private static void createConnection() {
        if(AbstractDao.connection == null) {
            try {
                Properties properties = new Properties();
                properties.load(ClassLoader.getSystemResource("application.properties").openStream());
                String url = properties.getProperty("db.connection_string");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");
                AbstractDao.connection = DriverManager.getConnection(url, username, password);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            finally {
                Runtime.getRuntime().addShutdownHook(new Thread(){
                    @Override
                    public void run(){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    /**
     * Getter for connection
     *
     * @return Connection
     */
    public static Connection getConnecetion() {
        return AbstractDao.connection;
    }

    /**
     * Method for mapping ResultSet into Object
     *
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws MovieException in case of error with db
     */
    public abstract T row2object(ResultSet rs) throws MovieException;

    /**
     * Method for mapping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    public abstract Map<String, Object> object2row(T object);

    /**
     * Method for defining query for getting an item by id.
     *
     * @param id
     */
    public T getById(int id) throws MovieException {
        return executeQueryUnique("SELECT * FROM " + this.tableName + " WHERE id = ?", new Object[]{id});
    }

    /**
     * Method for defining query that selects all items from specific table.
     *
     * @return List<T>
     */
    public List<T> getAll() throws MovieException {
        return executeQuery("SELECT * FROM "+ tableName, null);
    }

    /**
     * Method for defining query that deletes item by id.
     *
     * @param id
     */
    public void delete(int id) throws MovieException {
        String query = "DELETE FROM " + tableName + " WHERE id = ?";
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

    /**
     * Method for defining query that adds item to database.
     *
     * @param item
     */
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

    /**
     * Method for defining query that updates item to database.
     *
     * @param item
     */
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

    /**
     * Utility method for executing any kind of query
     * @param query - SQL query
     * @param params - params for query
     * @return List of objects from database
     * @throws MovieException in case of error with db
     */
    public List<T> executeQuery(String query, Object[] params) throws MovieException {
        try {
            PreparedStatement stmt = getConnecetion().prepareStatement(query);
            if(params != null) {
                for(int i = 1; i <= params.length; i++) {
                    stmt.setObject(i, params[i - 1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while(rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        }
        catch(SQLException e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

    /**
     * Utility for query execution that always return single record
     * @param query - query that returns single record
     * @param params - list of params for sql query
     * @return Object
     * @throws MovieException in case when object is not found
     */
    public T executeQueryUnique(String query, Object[] params) throws MovieException {
        List<T> result = executeQuery(query, params);
        if(result != null && result.size() == 1) {
            return result.get(0);
        }
        else {
            throw new MovieException("Object not found!");
        }
    }
}
