package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DaoTest {
    private UserManager userManager = new UserManager();
    private User user;
    private UserDaoSQLImpl userDaoSQLMock;
    private List<User> users;


//    @BeforeEach
//    public void initializeObjectsWeNeed() {
//        userManager = Mockito.mock(UserManager.class);
//        user = new User();
//        user.setName("Neko");
//        user.setLastName("Drugic");
//
//        userDaoSQLMock = Mockito.mock(UserDaoSQLImpl.class);
//        users = new ArrayList<>();
//        users.addAll(Arrays.asList(
//                new User("User1", "Prezime1", "u1@gmail.com", "u1", "u1"),
//                new User("User2", "Prezime2", "u2@gmail.com", "u2", "u2"),
//                new User("User3", "Prezime3", "u3@gmail.com", "u3", "u3"),
//                new User("User4", "Prezime4", "u4@gmail.com", "u4", "u4")));
//    }


    @Test
    void validateCategoryName() throws MovieException {
        String correctName = "Harry";
        String correctLasName = "Potter";
        String correctEmail = "harry123@gmail.com";

//        try {
//            Mockito.doCallRealMethod().when(userManager).validateUser(correctName, correctLasName, correctEmail);
//        } catch (MovieException e) {
//            //Test will fall if method validateCategoryName(name) throws an exception for correct parameter
//            e.printStackTrace();
//            assertTrue(false);
//        }

        String incorrectName = " ";
        String incorrectLastName = "Pot+ter";
        String incorrectEmail = "email.com";
        assertThrows(MovieException.class, () -> userManager.validateUser(incorrectName, correctLasName, correctEmail));
        assertThrows(MovieException.class, () -> userManager.validateUser(correctName, incorrectLastName, correctEmail));
        assertThrows(MovieException.class, () -> userManager.validateUser(correctName, correctLasName, incorrectEmail));

    }

}
