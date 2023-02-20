package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Administrator;
import ba.unsa.etf.rpr.exceptions.MovieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminManagerTest {
    private AdminManager adminManager = new AdminManager();

    @BeforeEach
    public void initializeObjectsWeNeed() {
        adminManager = Mockito.mock(AdminManager.class);
    }

    @Test
    void addNewAdmin() throws MovieException {
        Administrator newAdmin = new Administrator("admin", "admin");
        adminManager.add(newAdmin);
        Mockito.verify(adminManager).add(newAdmin);
    }

}
