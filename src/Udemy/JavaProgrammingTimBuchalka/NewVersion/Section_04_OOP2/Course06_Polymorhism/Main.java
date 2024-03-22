package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_04_OOP2.Course06_Polymorhism;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        Movie theMovie = new Adventure("Star Wars");
//        theMovie.watchMovie();
//        Movie theSecondMovie = Movie.getMovie("Science", "Star Wars");
//        theSecondMovie.watchMovie();

        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Type (A for Adventure, C for Comedy, "+
                    "S for Science Fiction, or Q to quit): ");
            String type = s.nextLine();
            if ("Qq".contains(type)) {
                break;
            }
            System.out.print("Enter Movie Title: ");
            String title = s.nextLine();
            Movie movie = Movie.getMovie(type, title);
            movie.watchMovie();
        }
    }
}
