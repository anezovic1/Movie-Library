package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.AdminManager;
import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.controllers.AdminController;
import ba.unsa.etf.rpr.domain.Administrator;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class App {
    public static void main(String[] args) throws MovieException {
        System.out.println("               WELCOME TO TMDB!\n");
        System.out.println("You can choose the option you want from the provided list: ");

        MovieManager movieManager = new MovieManager();
        UserManager userManager = new UserManager();
        AdminManager adminManager = new AdminManager();


        System.out.println("1 - List of all movies in our database");
        System.out.println("2 - Login");
        System.out.println("3 - Sign up");
        System.out.println("4 - Login as Administrator");
        System.out.println("5 - Search for a movie");
        System.out.println("6 - Close the application");

        Scanner input = new Scanner(System.in);
        System.out.println("Enter option: ");

        int option = input.nextInt();

        if(option == 1) {
            System.out.println("\nThis is our full list of movies.\n");
            List<Movie> allMovies = FXCollections.observableList(movieManager.getAll());

            for(int i = 0; i < allMovies.size(); i++) {
                System.out.println(allMovies.get(i).getId() + ". " + allMovies.get(i).getName() + "                    ");
                System.out.println("   " + allMovies.get(i).getGenre() + "               ");
            }
        }
        else if(option == 2) {
            System.out.println("\n\nPlease enter your username and password to log in.\n");
            String username = "";
            String password = "";

            Scanner input1 = new Scanner(System.in);
            System.out.print("Enter your username: ");
            username = input1.nextLine();
            System.out.print("Enter your password: ");
            password = input1.nextLine();

            List<User> allUsers = FXCollections.observableList(userManager.getAll());

            boolean valid = false;
            String name = "";

            for(int i = 0; i < allUsers.size(); i++) {
                if(allUsers.get(i).getUsername().equals(username) && allUsers.get(i).getPassword().equals(password)) {
                    name = allUsers.get(i).getName() + " " + allUsers.get(i).getLastName();
                    valid = true;
                    break;
                }
            }

            if(valid) {
                System.out.println("\n\n    Welcome back, " + name + "!\n\n");
            }
            else {
                System.out.println("Sorry, but you are not logged in. Please sign up.");
            }
        }
        else if(option == 3) {
            System.out.println("\n\nJoin the Community!\n");
            System.out.println("\nPlease enter the required information.\n");
            String name = "";
            String lastName = "";
            String email = "";
            String username = "";
            String password = "";

            Scanner input1 = new Scanner(System.in);
            System.out.print("Enter your name: ");
            name = input1.nextLine();
            System.out.print("Enter your last name: ");
            lastName = input1.nextLine();
            System.out.print("Enter your email: ");
            email = input1.nextLine();
            System.out.print("Enter your username: ");
            username = input1.nextLine();
            System.out.print("Enter your password: ");
            password = input1.nextLine();

            boolean valid = true;

            if(name.contains(" ") || username.contains(" ") || lastName.contains(" ")) {
                valid = false;
            }

            for(int i = 0; i < name.length(); i++) {
                if(name.charAt(i) < 'A' || (name.charAt(i) > 'Z' && name.charAt(i) < 'a') || name.charAt(i) > 'z') {
                    valid = false;
                    break;
                }
            }
            for(int i = 0; i < lastName.length(); i++) {
                if(lastName.charAt(i) < 'A' || (lastName.charAt(i) > 'Z' && lastName.charAt(i) < 'a') || lastName.charAt(i) > 'z') {
                    valid = false;
                    break;
                }
            }

            String validPassword = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(validPassword);
            Matcher matcher = pattern.matcher(email);

            if(!matcher.matches()) {
                valid = false;
            }

            if(!valid) {
                System.out.println("Sorry, but you entered invalid information. Please check again.");
            }
            else {
                User newUser = new User();
                newUser.setName(name);
                newUser.setLastName(lastName);
                newUser.setUsername(username);
                newUser.setEmail(email);
                newUser.setPassword(password);
                userManager.add(newUser);

                System.out.println("You successfully signed up! Please, try to log in.");
            }
        }
        else if(option == 4) {
            System.out.println("\n\nPlease enter your username and password to log in.\n");
            String username = "";
            String password = "";

            Scanner input1 = new Scanner(System.in);
            System.out.print("Enter your username: ");
            username = input1.nextLine();
            System.out.print("Enter your password: ");
            password = input1.nextLine();

            List<Administrator> allAdmins = FXCollections.observableList(adminManager.getAll());
            boolean valid = false;

            for(int i = 0; i < allAdmins.size(); i++) {
                if(allAdmins.get(i).getUsername().equals(username) && allAdmins.get(i).getPassword().equals(password)) {
                    valid = true;
                    break;
                }
            }

            if(valid) {
                System.out.println("Welcome back administrator, " + username + "!");
                System.out.println("                   :)                   ");
            }
            else {
                System.out.println("You don't have administrator access.");
            }
        }
        else if(option == 5) {
            System.out.println("   Search for a movie in our database!   ");
            String text = "";

            Scanner input1 = new Scanner(System.in);
            System.out.print("Enter the text to search for the movie you want: ");
            text = input1.nextLine();

            System.out.println("     Results:\n");
            List<Movie> movies = FXCollections.observableList(movieManager.searchMovies(text));
            for(int i = 0; i < movies.size(); i++) {
                System.out.println(i + 1 + ". " + movies.get(i).getName());
            }
        }
        else if (option == 6) {
            System.out.println(":(");
        }

    }
}
