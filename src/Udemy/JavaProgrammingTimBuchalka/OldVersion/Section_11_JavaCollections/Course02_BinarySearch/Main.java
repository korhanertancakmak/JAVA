package Udemy.JavaProgrammingTimBuchalka.OldVersion.Section_11_JavaCollections.Course02_BinarySearch;

public class Main {

    public static void main(String[] args) {
	    Theatre theatre = new Theatre("Olympian", 8, 12);
//        theatre.getSeats();
        if(theatre.reserveSeat("B13")) {
            System.out.println("Please pay");
        } else {
            System.out.println("Sorry, seat is taken");
        }
//        if(theatre.reserveSeat("H11")) {
//            System.out.println("Please pay");
//        } else {
//            System.out.println("Sorry, seat is taken");
//        }
    }
}
