package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Administrator;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

public class UserManagerTest {
    private UserManager userManager = new UserManager();

    @BeforeEach
    public void initializeObjectsWeNeed() {
        userManager = Mockito.mock(UserManager.class);
    }

    @Test
    void addNewUser() throws MovieException {
        User newUser = new User("Neko", "Drugic", "nekodrugic@gmail.com","neko_drugic123", "nekoneko");
        userManager.add(newUser);
        Mockito.verify(userManager).add(newUser);
    }

    @Test
    void deleteUser() throws MovieException {
        userManager.delete(5);
        Mockito.verify(userManager).delete(5);
    }

}
