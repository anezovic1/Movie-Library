package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * This is JavaBean Class for the watchlist. It holds information about all movies user selected.
 *
 * @author Anida Nezovic
 */
public class Watchlist implements Idable {
    private int id;
    private String name;
    private int userId;
    private String movies;

    /**
     * Getter for movies. It contains movie ids that are separated by a comma.
     *
     * @return String
     */
    public String getMovies() {
        return movies;
    }

    /**
     * Setter for movies
     *
     * @param movies
     */
    public void setMovies(String movies) {
        this.movies = movies;
    }

    /**
     * Getter for user id
     *
     * @return int
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter for user id
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter for Id
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the id. At the moment we don't need this method
     * because id is autoincremented.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Watchlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", movies='" + movies + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Watchlist watchlist = (Watchlist) o;
        return id == watchlist.id && userId == watchlist.userId && Objects.equals(name, watchlist.name) && Objects.equals(movies, watchlist.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userId, movies);
    }
}
