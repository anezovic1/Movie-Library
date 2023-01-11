package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Administrator;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class AdministratorDaoSQLImpl extends AbstractDao<Administrator> implements AdministratorDao{
    public AdministratorDaoSQLImpl() {
        super("users");
    }

    @Override
    public Administrator row2object(ResultSet rs) throws MovieException {
        try {
            Administrator admin = new Administrator();
            admin.setId(rs.getInt("id"));
            admin.setPassword(rs.getString("password"));
            admin.setUsername(rs.getString("username"));
            return admin;
        } catch(SQLException e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Administrator object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("password", object.getPassword());
        row.put("username", object.getUsername());
        return row;
    }
}
