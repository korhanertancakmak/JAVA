package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course16_TreeSetChallenge;

import java.util.NavigableSet;
import java.util.TreeSet;

//Part-3
/*
        I'll add my inner nested class here, the Seat class. This class has two fields, a String I'll call SeatNum. And
    a boolean, reserved. I'll add a constructor, selecting just seatNum as the field. I want to change this constructor
    though, as I stated on the challenge info, so that one argument will be a character, for the row, and the other is
    the seat number in that row, so an int. I'll change the assignment to a formatted String, which will take the row
    character, and the seatNumber, prefixing it with leading zeros up to 3 digits, and I'll make all of that upper case.
    Ok, that's my constructor.
*/
//End-Part-3

public class Theatre {

    class Seat implements Comparable<Seat> {

        private String seatNum;
        private boolean reserved;

        public Seat(char rowChar, int seatNo) {
            this.seatNum = "%c%03d".formatted(rowChar, seatNo).toUpperCase();
        }

//Part-4
/*
        Next, I want to override the toString method, and just return seatNum there. Finally, I want to implement Comparable
    on this Seat class. It's important to remember to pass a type argument to Comparable, in this case Seat, so that when
    we go to override its abstract method, it generates it using the Seat type. This is because I want to use it with a
    TreeSet, without being forced to specify a comparator any time I want to use methods, or create a new TreeSet of seats.
    IntelliJ flags that as a problem, as you'd expect. I'll hover over that, and implement the compareTo method, and since
    I don't want to return zero, I'll change that. I want to return seatNum.compareTo, and pass that o.seatNum. That's
    all I really need on this little nested class. Next, I want to set up the fields, on the theatre class.
*/
//End-Part-4

        @Override
        public String toString() {
            return seatNum;
        }

        @Override
        public int compareTo(Seat o) {
            return seatNum.compareTo(o.seatNum);
        }
    }

//Part-5
/*
        These were theatre name, a string. seats Per Row, an int. And a Navigable Set, with type argument, seat, called
    seats. This is my collection for the theatre's seats. So why did I use NavigableSet, and not SortedSet or TreeSet?
    First of all, when using a class in the collections framework, you'll use the interface type as your reference type,
    so you don't want to use TreeSet. I'm planning on using methods specifically declared on Navigable Set, that aren't
    on Sorted Set, such as the floor and ceiling methods, so that's why I'm going with this type. Next, I want a constructor
    for my Theatre class.
*/
//End-Part-5

    private String theatreName;
    private int seatsPerRow;
    private NavigableSet<Seat> seats;
    //private Set<Seat> seats;

//Part-6
/*
         I'll generate this, selecting theatreMame and seats per row. I want to change this constructor, so first I'll
    change seats Per Row to just rows, and I'll add an additional argument, an int, for total Seats. I'll change the
    assignment, and make seats per row equal to totalSeats divided by rows. Here, I'm just going to calculate the seats
    per row, so I'll divide total seats by the rows, passed to this constructor. I'm going to set up the theatre seats
    in this constructor. I'll first initialize seats, to a new TreeSet. I'll loop from 0 to one less than the totalSeats.
    I'll calculate the row character. This is the current index, divided by the seats per row, added to the integer value
    for a capital A. This means the first row's seats will be labeled A, the second row's seats will be labeled B and so
    on. My seat num can be derived using the remainder operator, so i, mod, seatsPerRow, and I don't want any 0 seat
    numbers, so I want to add 1 to each of these. I'll add a new seat, passing it the row character, and the seat in row,
    adding that to the seats field. After this, I'll add the printSeatMap method,
*/
//End-Part-6

    public Theatre(String theatreName, int rows, int totalSeats) {
        this.theatreName = theatreName;
        this.seatsPerRow = totalSeats / rows;

//Part-9
/*
        and change the type to a LinkedHashSet. This gives me an error though, and that's because I declared seats as a
    NavigableSet, and linked hash set, doesn't actually implement that interface. I'll change that to Set here, for just
    a minute, on line 65. Ok, so that compiles and I can run it.

            PRİNTED THE SAME MAP

    My printed map looks exactly the same. That's because I added all my seats in this order, so they're in insertion order,
    in the order I'd want to see them printed. If I now changed LinkedHashSet to just HashSet, If I run my code like that,

            ------------------------------------------------------------------------------------------
            Richard Rodgers Seat Map
            ------------------------------------------------------------------------------------------
            B007    B004    A002    F007    E002    G004    A010    H008    D005    E005
            A006    F009    A004    D002    G006    I005    A009    F005    H010    J001
            D004    H005    J006    B001    I002    J009    E008    B006    D006    F004
            A007    I008    J003    B003    B005    C002    D010    G008    A008    I006
            J005    C008    C010    G005    D009    G007    E004    J010    C003    C001
            B002    E010    J004    G002    I009    A003    C009    G001    I003    D003
            H003    H004    F008    G003    F006    I010    F002    D001    C007    H002
            B008    C006    B010    I007    C005    A005    E003    H007    E006    E007
            F010    E001    J002    A001    B009    H009    D007    E009    J008    J007
            D008    I004    G010    C004    G009    H006    F003    F001    H001    I001
            ------------------------------------------------------------------------------------------

    my printed map isn't in any particular order, and this order isn't guaranteed. That's just a reminder of the three
    types of Sets you can use. I'm going to revert those last three changes so that I'm using NavigableSet as the reference
    type, and creating a new instance of Tree Set. Ok, so Now, let's actually start doing something a bit more interesting.
*/
//End-Part-9

        seats = new TreeSet<>();
        //seats = new LinkedHashSet<>();
        //seats = new HashSet<>();
        for (int i = 0; i < totalSeats; i++) {
            char rowChar = (char) (i / seatsPerRow + (int) 'A');
            int seatInRow = i % seatsPerRow + 1;
            seats.add(new Seat(rowChar, seatInRow));
        }
    }

//Part-7
/*
        So I can test if my theatre seats are correctly numbered. I'll make this public, because my booking agent should
    be able to call it. I'll set up a local variable, a separator line, 90 dashes. I'll print a formatted String. This
    looks complex, but I'm simply printing a separator line, then the theatre name, and then the separator line again.
    Notice the use of dollar signs in these specifiers. If I specify where to find the passed parameter, starting at 1,
    I can use a parameter in multiple places, which is what I'm doing here. I'll finish with yet another dashed line. I
    haven't really done anything here yet. I still have to add the code to print the seats. I'll start with a local variable
    I'll call index, initializing that to zero. I'll loop through the seats. And I'm going to print another formatted
    String. This will be the seat number, followed by an indicate, a large bullet character, if the seat is reserved.
    I'm using the unicode character, with the "\ u" escape here, but if you want to take IntelliJ's suggestion to replace
    it with the actual character, go ahead, or use any character that you like there, it's just a way of seeing what's
    reserved. Finally, I want to include a new line character. If I reach the end of a row of seats, so I'll use the
    remainder operator to check that. Ok, that's it for that method. Now, I'll go back to my BookingAgent class, and
    create my first theatre.
*/
//End-Part-7

    public void printSeatMap() {

        String separatorLine = "-".repeat(90);
        System.out.printf("%1$s%n%2$s Seat Map%n%1$s%n", separatorLine, theatreName);

        int index = 0;
        for (Seat s : seats) {
            System.out.printf("%-8s%s",
                    s.seatNum + ((s.reserved) ? "(●)" : ""),
                    ((index++ + 1) % seatsPerRow == 0) ? "\n" : "");
        }
        System.out.println(separatorLine);
    }

//Part-10
/*
        First I want to add the reserve seat method, for a single seat reservation. I'll make this public, because it's
    a method clients should have access to, and it will return a String, if the seat was found and reserved, otherwise
    null. It will take a char, representing the desired row of the seat, and the seat number, within that row, so an int.
    I'll set up a local variable, that's a Seat, and create a new seat using the requested row and seat number. I'm going
    to call the floor method on seats, passing it my requested seat variable. I've said before that sets don't have a get
    method, and that's still true for a TreeSet. I could loop through all the set elements, to find my seat, or I can take
    advantage of one of Navigable Set's closest match methods, which is what I'm doing here. If I used the lower method,
    I'd get the seat that was lower than the seat passed, but floor gives me a matched seat, if it exists, or the seat
    lower than that. It returns null if there aren't any seats lower than this seat number in the set. I'll check if the
    value coming back is null, or if the seat that is returned from the lower method has a different seat number than
    the requested one. If that's the case, I'll print there's no such seat, and print the seat information. I'll also
    print the seat range, using Navigable Set's first and last methods to do that. I'll return null from the method.
    I'll add the code to return a reserved seat next, which is the else condition. Inside this else, I want a nested if
    then else condition. This time I'll check to see if the seat's still unreserved. If that's the case, I'll set reserved
    to true, and return this seat's seat number from the method. Otherwise, if the seat's already reserved, I'll print
    that out. Getting back to my bookingAgent class,
*/
//End-Part-10

    public String reserveSeat(char row, int seat) {

//Part-14
/*
        To show you that, I'll go back to my reserve seat method, and simply replace the call to floor, with a call to
    ceiling. Now, if I rerun the code, I get all the same results. In this case, ceiling and floor both work, because
    I'm really interested in an exact match, and not the closest match. I could have used the contains method, to check
    if seats contained my requested seat, but then I'd still have to retrieve the match, and ceiling and floor save me
    the trouble of doing that. Ok, that ends this part of the challenge, which used a TreeSet to maintain an ordered set
    of theatre seats, and used the floor and ceiling methods, as well as first and last. In the next lecture, I'll cover
    the bonus challenge, which I hope you tried and had some fun with. The bonus method will allow your BookingAgent, to
    reserve multiple, contiguous seats in a single row, based on certain criteria passed.
*/
//End-Part-14

        Seat requestedSeat = new Seat(row, seat);
        //Seat requested = seats.floor(requestedSeat);
        Seat requested = seats.ceiling(requestedSeat);

        if (requested == null || !requested.seatNum.equals(requestedSeat.seatNum)) {
            System.out.print("--> No such seat: " + requestedSeat);
            System.out.printf(": Seat must be between %s and %s%n",
                    seats.first().seatNum, seats.last().seatNum);
        } else {
            if (!requested.reserved) {
                requested.reserved = true;
                return requested.seatNum;
            } else {
                System.out.println("Seat's already reserved.");
            }
        }
        return null;
    }

}
