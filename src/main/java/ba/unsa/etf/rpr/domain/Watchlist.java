package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * holds list of all movies that user selected
 * @author anida
 */
public class Watchlist implements Idable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Watchlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Watchlist watchlist = (Watchlist) o;
        return id == watchlist.id && Objects.equals(name, watchlist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
