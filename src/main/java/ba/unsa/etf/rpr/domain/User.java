package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * This is JavaBean Class for the user.
 *
 * @author Anida Nezovic
 */
public class User implements Idable {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String username;
    private String password;

    /**
     * Class constructor.
     */
    public User() {
    }

    /**
     * Class constructor specifying name, last name, email, username, and password for all users.
     */
    public User(String name, String lastName, String email, String username, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
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
     * Getter for last name
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for last name
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for email
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, email, username, password);
    }
}
