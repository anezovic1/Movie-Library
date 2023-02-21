package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDaoTest {
    private UserManager userManager = new UserManager();
    private User user;
    private UserDaoSQLImpl userDaoSQLMock;
    private List<User> users;

    @Test
    void validateUser() throws MovieException {
        String correctName = "Harry";
        String correctLasName = "Potter";
        String correctEmail = "harry123@gmail.com";
        String incorrectName = " ";
        String incorrectLastName = "Pot+ter";
        String incorrectEmail = "email.com";
        assertThrows(MovieException.class, () -> userManager.validateUser(incorrectName, correctLasName, correctEmail));
        assertThrows(MovieException.class, () -> userManager.validateUser(correctName, incorrectLastName, correctEmail));
        assertThrows(MovieException.class, () -> userManager.validateUser(correctName, correctLasName, incorrectEmail));

    }

}
