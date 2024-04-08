package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_Streams.Course07_TerminalOperations;

//Part-4
/*
        I'll generate a custom constructor next, and this will just have two fields, rowMarker and seatNumber. Instead
    of passing false as the isReserved value, I want to pass a randomly generated boolean. I'll replace false with new
    Random(), and call nextBoolean on that. Getting back to the main method,
*/
//End-Part-4
import java.util.Random;
public record Seat(char rowMarker, int seatNumber, boolean isReserved) {

    public Seat(char rowMarker, int seatNumber) {
        this(rowMarker, seatNumber, new Random().nextBoolean());
        //this(rowMarker, seatNumber, false);
    }
}
