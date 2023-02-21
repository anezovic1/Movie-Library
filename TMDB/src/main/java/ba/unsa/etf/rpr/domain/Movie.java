package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * This is JavaBean Class for the movie.
 *
 * @author Anida Nezovic
 */
public class Movie implements Idable {
    private int id;
    private String name;
    private String genre;
    private String duration;
    private String rating;

    /**
     * Class constructor.
     */
    public Movie() {
    }

    /**
     * Class constructor specifying movie name, movie genre, movie duration and movie rating.
     */
    public Movie(String name, String genre, String duration, String rating) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
    }

    /**
     * Class constructor specifying movie name.
     */
    public Movie(String name) {
        this.name = name;
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

    /**
     * Getter for genre
     *
     * @return String
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Setter for genre
     *
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Getter for duration
     *
     * @return String
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Setter for duration
     *
     * @param duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Getter for rating
     *
     * @return String
     */
    public String getRating() {
        return rating;
    }

    /**
     * Setter for rating
     *
     * @param rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", duration='" + duration + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && Objects.equals(name, movie.name) && Objects.equals(genre, movie.genre) && Objects.equals(duration, movie.duration) && Objects.equals(rating, movie.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre, duration, rating);
    }
}
