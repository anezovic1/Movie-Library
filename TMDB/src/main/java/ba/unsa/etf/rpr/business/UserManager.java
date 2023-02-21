package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Business Logic Layer for management of Users
 *
 * @author Anida Nezovic
 */
public class UserManager {

    /**
     * Method that does the validation of users personal information.
     *
     * @param name
     * @param lastName
     * @param email
     */
    public void validateUser(String name, String lastName, String email) throws MovieException {
        if(name.contains(" ") || name == null || lastName.contains(" ") || lastName == null) {
            throw new MovieException("User name is invalid!");
        }

        for(int i = 0; i < name.length(); i++) {
            if(name.charAt(i) < 'A' || (name.charAt(i) > 'Z' && name.charAt(i) < 'a') || name.charAt(i) > 'z') {
                throw new MovieException("User name is invalid!");
            }
        }

        for(int i = 0; i < lastName.length(); i++) {
            if(lastName.charAt(i) < 'A' || (lastName.charAt(i) > 'Z' && lastName.charAt(i) < 'a') || lastName.charAt(i) > 'z') {
                throw new MovieException("User name is invalid!");
            }
        }

        String validPassword = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(validPassword);
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches()) {
            throw new MovieException("User name is invalid!");
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
