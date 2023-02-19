package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.util.List;

public class UserManager {

    public void validateUserName(String name) throws MovieException{
        if(name.contains(" ") || name == null) {
            throw new MovieException("User name is invalid!");
        }

        for(int i = 0; i < name.length(); i++) {
            if(name.charAt(i) < 'A' || (name.charAt(i) > 'Z' && name.charAt(i) < 'a') || name.charAt(i) > 'z') {
                throw new MovieException("User name is invalid!");
            }
        }
    }
    public List<User> getAll() throws MovieException {
        return DaoFactory.userDao().getAll();
    }

    public void delete(int userID) throws MovieException {
        DaoFactory.userDao().delete(userID);
    }

    public User getById(int userID) throws MovieException {
        return DaoFactory.userDao().getById(userID);
    }

    public void update(User user) throws MovieException {
        DaoFactory.userDao().update(user);
    }

    public User add(User user) throws MovieException {
        return DaoFactory.userDao().add(user);
    }
}
