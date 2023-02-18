package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exceptions.MovieException;
import javafx.collections.FXCollections;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws MovieException {
        System.out.println("               WELCOME TO TMDB!\n");
        System.out.println("You can choose the option you want from the provided list: ");

        System.out.println("1 - List of all movies in our database");
        System.out.println("2 - Login");
        System.out.println("3 - Sign up");
        System.out.println("4 - Login as Administrator");
        System.out.println("5 - Search for a movie");
        System.out.println("6 - Close the application");

        Scanner input = new Scanner(System.in);
        System.out.println("Enter option: ");

        int option = input.nextInt();

        MovieManager movieManager = new MovieManager();

        if(option == 1) {
            System.out.println("\nThis is our full list of movies.\n");
            List<Movie> allMovies = FXCollections.observableList(movieManager.getAll());

            for(int i = 0; i < allMovies.size(); i++) {
                System.out.println(allMovies.get(i).getId() + ". " + allMovies.get(i).getName() + "                    ");
                System.out.println("   " + allMovies.get(i).getGenre() + "               ");
            }
        }

    }
}
