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
        user.setUsername("neko123");

        assertEquals(2, user.getId());
        assertEquals("Neko", user.getName());
        assertEquals("Nekic", user.getLastName());
        assertEquals("neko@gmail.com", user.getEmail());
        assertEquals("nekooo", user.getPassword());
        assertEquals("neko123", user.getUsername());
    }

    @Test
    public void testAdminSetterGetter() {
        Administrator admin = new Administrator();
        admin.setUsername("anida");
        admin.setPassword("RPR2023");

        assertEquals("RPR2023", admin.getPassword());
        assertEquals("anida", admin.getUsername());
    }

    @Test
    public void testMovieSetterGetter() {
        Movie movie = new Movie();
        movie.setName("Inception");
        movie.setGenre("action");
        movie.setDuration("2h 38m");
        movie.setRating("8.8");

        assertEquals("Inception", movie.getName());
        assertEquals("action", movie.getGenre());
        assertEquals("2h 38m", movie.getDuration());
        assertEquals("8.8", movie.getRating());
    }

}
