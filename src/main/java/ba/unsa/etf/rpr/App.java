package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MovieException;
import javafx.collections.FXCollections;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws MovieException {
        System.out.println("               WELCOME TO TMDB!\n");
        System.out.println("You can choose the option you want from the provided list: ");

        MovieManager movieManager = new MovieManager();
        UserManager userManager = new UserManager();


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
        else if (option == 6) {
            System.out.println(":(");
        }



    }
}
