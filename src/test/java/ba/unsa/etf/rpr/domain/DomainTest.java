package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainTest {

    @Test
    public void testUserSetterGetter() {
        User user = new User();
        user.setId(2);
        user.setName("Neko");
        user.setLastName("Nekic");
        user.setEmail("neko@gmail.com");
        user.setPassword("nekooo");

        assertEquals(2, user.getId());
        assertEquals("Neko", user.getName());
        assertEquals("Nekic", user.getLastName());
        assertEquals("neko@gmail.com", user.getEmail());
        assertEquals("nekooo", user.getPassword());
    }

}
