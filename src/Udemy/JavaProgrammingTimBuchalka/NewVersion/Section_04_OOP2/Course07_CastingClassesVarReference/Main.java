package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_04_OOP2.Course07_CastingClassesVarReference;

public class Main {
    public static void main(String[] args) {

        Movie movie = Movie.getMovie("A", "Jaws");
        movie.watchMovie();

        //Adventure jaws = Movie.getMovie("A", "Jaws");

        //Adventure jaws = (Adventure) Movie.getMovie("A", "Jaws");
        //jaws.watchMovie();

        //Adventure jaws = (Adventure) Movie.getMovie("C", "Jaws");
        //jaws.watchMovie();

        Adventure jaws = (Adventure) Movie.getMovie("A", "Jaws");
        jaws.watchMovie();

        Object comedy = Movie.getMovie("C", "Airplane");
        //comedy.watchMovie();
        //comedy.watchComedy();

        //Movie comedyMovie = (Movie) comedy;
        //comedyMovie.watchComedy();

        Comedy comedyMovie = (Comedy) comedy;
        comedyMovie.watchComedy();

        var airplane = Movie.getMovie("C", "Airplane");
        airplane.watchMovie();

        var plane = new Comedy("Airplane");
        plane.watchComedy();
    }
}
