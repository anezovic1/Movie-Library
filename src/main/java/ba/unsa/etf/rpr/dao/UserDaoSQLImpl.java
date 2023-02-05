package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 * @author anida
 */
public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {
    public UserDaoSQLImpl() {
        super("users");
    }

    @Override
    public User row2object(ResultSet rs) throws MovieException {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setUsername(rs.getString("username"));
            return user;
        } catch(SQLException e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(User object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("last_name", object.getLastName());
        row.put("email", object.getEmail());
        row.put("password", object.getPassword());
        row.put("username", object.getUsername());
        return row;
    }
}
