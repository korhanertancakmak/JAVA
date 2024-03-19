package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Streams.Course06_IntermediateOperationsPart2;

//Part-2
/*
        You may remember in previous lectures, I created collections of Theatre Seats. I want to do something similar to
    demonstrate some of the stream operations. I'll create a new Java class, a record, and just call that Seat. It will
    have the fields rowMarker, seatNumber, and a price of the seat. RowMarker will be a letter, seatNumber will be an int,
    and price will be a double. I'll generate a custom constructor, and for that, I just want the first two fields,
    rowMarker, and seatNumber. I'll change this code. First, I want to insert the seatNumber, before the zero. Next I'll
    replace 0 with a ternary operator, to set up a pricing rule for seats. All seats will be $75, but I'll have the outer
    seats, seats 1 and 2, and seats 9 and 10 be discounted, after row C. I'll check that rowMarker is greater than a literal
    C character. I'll use a conditional and, and next check the seat number values. That's it for the constructor.
*/
//End-Part-2

public record Seat(char rowMarker, int seatNumber, double price) {

    public Seat(char rowMarker, int seatNumber) {
        this(rowMarker, seatNumber, rowMarker > 'C' && (seatNumber <= 2 || seatNumber >= 9) ? 50 : 75);
    }

//Part-3
/*
        Next, I want a toString method, and I'll generate that with ALT INSERT, and Select None for fields. I'm going to
    return a formatted String. The seat number will be in the format, of B001, for example, so I'll print the row marker
    with a character specifier. That'll be followed by the seat number with an integer specifier, with a width of 3, but
    I want leading zeros, so I make that zero 3. Finally the price has a float identifier. I'll ignore any decimal value
    when this is printed, so I make that .0f. Ok, that's a pretty simple record. This pricing mechanism is a bit flawed,
    since my seat numbers may not always be from 1 to 10 under some circumstances, but it's good enough for this test.
    Getting back to the Main class and the main method,
*/
//End-Part-3

    @Override
    public String toString() {
        return "%c%03d %.0f".formatted(rowMarker, seatNumber, price);
    }
}
