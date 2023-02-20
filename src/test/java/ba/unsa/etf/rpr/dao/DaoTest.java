package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.business.AdminManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Administrator;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class DaoTest {
    private UserManager userManager = new UserManager();
    private AdminManager adminManager = new AdminManager();

    @BeforeEach
    public void initializeObjectsWeNeed() {
        userManager = Mockito.mock(UserManager.class);
        adminManager = Mockito.mock(AdminManager.class);
    }

    @Test
    void addNewUser() throws MovieException {
        User newUser = new User(1, "Neko", "Drugic", "nekodrugic@gmail.com","neko_drugic123", "nekoneko");
        userManager.add(newUser);
        Mockito.verify(userManager).add(newUser);
    }

    @Test
    void addNewAdmin() throws MovieException {
        Administrator newAdmin = new Administrator("admin", "admin");
        adminManager.add(newAdmin);
        Mockito.verify(adminManager).add(newAdmin);
    }
}
