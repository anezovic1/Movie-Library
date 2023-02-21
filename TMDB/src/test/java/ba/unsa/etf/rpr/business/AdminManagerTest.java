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
    private Administrator newAdmin = new Administrator("admin", "admin");
    @BeforeEach
    public void initializeObjectsWeNeed() {
        adminManager = Mockito.mock(AdminManager.class);
    }

    @Test
    void addNewAdmin() throws MovieException {
        adminManager.add(newAdmin);
        Mockito.verify(adminManager).add(newAdmin);
    }

    @Test
    void deleteUser() throws MovieException {
        adminManager.delete(2);
        Mockito.verify(adminManager).delete(2);
    }

    @Test
    void updateUser() throws MovieException {
        System.out.println(newAdmin.getPassword());
        newAdmin.setPassword("novi admin");
        adminManager.update(newAdmin);
        System.out.println(newAdmin.getPassword());
        Mockito.verify(adminManager).update(newAdmin);
    }

}
