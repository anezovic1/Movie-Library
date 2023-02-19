package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

public class UserManagerTest {
    private UserManager userManager;
    private User user;
    private UserDaoSQLImpl userDaoSQLMock;
    private List<User> categories;

    @Test
    void validateUserName() throws MovieException {
        /*String correctName = "Neko";

        try {
            Mockito.doCallRealMethod().when(userManager).validateUserName(correctName);
        } catch (MovieException e) {
            //Test will fall if method validateCategoryName(name) throws an exception for correct parameter
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectName = "a+";

        Assertions.assertEquals(userManager.validateUserName(incorrectName), "User name is invalid!");*/
    }
}
