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
     * Setter for the id. At the moment we don't need this method
     * because id is autoincremented.
     *
     * @param id the id to set
     */
    @Override
    public void setId(int id) {

    }

    /**
     * Getter for Id
     *
     * @return int
     */
    @Override
    public int getId() {
        return 0;
    }

    /**
     * Getter for username
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method used for printing admins id, username and password.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /**
     * Method used for comparing Administrator instances.
     *
     * @return boolean
     */
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
