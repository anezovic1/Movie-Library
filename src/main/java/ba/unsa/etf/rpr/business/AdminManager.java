package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Administrator;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.util.List;

/**
 * Business Logic Layer for management of Admins
 *
 * @author Anida Nezovic
 */
public class AdminManager {
    public List<Administrator> getAll() throws MovieException {
        return DaoFactory.adminDao().getAll();
    }

    public void delete(int id) throws MovieException {
        DaoFactory.adminDao().delete(id);
    }

    public Administrator getById(int adminID) throws MovieException {
        return DaoFactory.adminDao().getById(adminID);
    }

    public void update(Administrator admin) throws MovieException {
        DaoFactory.adminDao().update(admin);
    }

    public Administrator add(Administrator admin) throws MovieException {
        return DaoFactory.adminDao().add(admin);
    }
}
