package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_Streams.Course06_IntermediateOperationsPart2;

import java.util.Comparator;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        System.out.println();
        int maxSeats = 100;
        int seatsInRow = 10;
        var stream = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                        .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1));

        //stream.forEach(System.out::println);


        var stream1 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                //.map(Seat::toString);
                //.map(Seat::price)
                .mapToDouble(Seat::price)
                //.map("%.2f"::formatted);
                .mapToObj("%.2f"::formatted);

        //stream1.forEach(System.out::println);


        var stream2 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                //.mapToDouble(Seat::price)
                //.boxed()
                //.map("%.2f"::formatted);
                .sorted();

        //stream2.forEach(System.out::println);

        var stream3 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .sorted(Comparator.comparing(Seat::price).thenComparing(Seat::toString));

        //stream3.forEach(System.out::println);


        var stream4 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .sorted(Comparator.comparing(Seat::price).thenComparing(Seat::toString))
                .skip(5)
                .limit(10)
                .peek(s -> System.out.println("--> " + s));

        stream4.forEach(System.out::println);
    }
}
