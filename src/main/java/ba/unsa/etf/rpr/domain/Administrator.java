package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * This is JavaBean Class for admin.
 *
 * @author Anida Nezovic
 */
public class Administrator implements Idable {
    private int id;
    private String username;
    private String password;

    /**
     * Class constructor.
     */
    public Administrator() {

    }

    /**
     * Class constructor specifying admins username and password.
     */
    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Sets the id. At the moment we don't need this method
     * because id is autoincremented.
     *
     * @param id the id to set
     */
    @Override
    public void setId(int id) {

    }

    @Override
    public int getId() {
        return 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
