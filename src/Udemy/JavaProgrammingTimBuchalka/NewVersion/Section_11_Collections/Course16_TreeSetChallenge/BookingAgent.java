package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course16_TreeSetChallenge;

//Part-1
/*
                                         The TreeSet Challenge(Theatre Seating)

        In this challenge, you'll be creating a Theatre class, that has a set of seats. The Seat class should be a nested
    class on the Theatre class. A Seat should be constructed with a row character and an integer, that represents the
    seat number within the row. Each Seat should have a string, a seat number, in the format 'A005', where A is the row
    number, and 005 is the seat number within the row, It should be zero padded up to three digits. Seat should also have
    a field, a boolean, indicating if the seat is reserved or not.

        The theatre class should have three fields, theatre name, an integer for seats in row, how many seats are in a
    single row in other words, and a field for the seats themselves. This last field should be a TreeSet. A Theatre instance
    should be constructed with the theatre name, the number of rows in the theatre, and the number of seats total in the
    theatre. For simplicity, assume there are a uniform number of seats in every row, and the number of rows should never
    exceed 26, so the rows will be labeled A through Z.

        You should create the seats, and number them, as part of the initialization of a theatre class. The theatre class
    should also have a printSeatMap method, that prints each seat, with each row printed on a separate line. You should
    allow a booking agent to reserve a single seat, and the printed seat map should show which seats are reserved. If you
    want an extra challenge, create a second method on theatre, that lets an agent specify.

    * the number of reservations being requested.
    * a range of rows (from A to C for example, for front row seats).
    * a range of seats (a number greater than or equal to 1, and less than or equal to the rows per seat).

    For example, if there are 10 seats in each row, you could assume the aisle seats are 1 and 9, and maybe your buyer isn't
    interested in an aisle seat, so they should be able to specify 2 through 9 as the range of seat numbers. The seats
    that get reserved, should be contiguous within a row.
*/
//End-Part-1

public class BookingAgent {

    public static void main(String[] args) {

//Part-2
/*
        I've created instead of Main, I'm going to call my starting class, BookingAgent. I'll add a main method to that
    with the "psvm" short cut. Before I do anything with this, I'll create the Theatre class.
*/
//End-Part-2

        int rows = 10;
        int totalSeats = 100;
        Theatre rodgersNYC = new Theatre("Richard Rodgers", rows, totalSeats);

        rodgersNYC.printSeatMap();

//Part-8
/*
        I'll set up two local variables, an int, rows, equal 10, and totalSeats = 100. Doing the math, my rows should all
    have 10 seats in them. There's a theatre in new york city, called Richard Rodgers, so I'll name my local variable
    rodgersNYC, and pass it the nameRichard Rodgers, and my rows and total seats. Then I'll call printSeatMap on that
    variable. Running that code,

        ------------------------------------------------------------------------------------------
        Richard Rodgers Seat Map
        ------------------------------------------------------------------------------------------
        A001    A002    A003    A004    A005    A006    A007    A008    A009    A010
        B001    B002    B003    B004    B005    B006    B007    B008    B009    B010
        C001    C002    C003    C004    C005    C006    C007    C008    C009    C010
        D001    D002    D003    D004    D005    D006    D007    D008    D009    D010
        E001    E002    E003    E004    E005    E006    E007    E008    E009    E010
        F001    F002    F003    F004    F005    F006    F007    F008    F009    F010
        G001    G002    G003    G004    G005    G006    G007    G008    G009    G010
        H001    H002    H003    H004    H005    H006    H007    H008    H009    H010
        I001    I002    I003    I004    I005    I006    I007    I008    I009    I010
        J001    J002    J003    J004    J005    J006    J007    J008    J009    J010

    I get a well formatted seat map of this theatre. This really isn't what this theatre's seat map looks like, but we're
    keeping it simple. I have rows A, through J, and in each row, seats 1 through 10. Nothing's reserved yet. I haven't
    done much with the TreeSet up to this point, except use it as storage, and print it out. I could actually change the
    type to a LinkedHashSet. Let me show you that. I'll go back to the Theatre constructor,
*/
//End-Part-8

        bookSeat(rodgersNYC, 'A', 3);
        bookSeat(rodgersNYC, 'A', 3);

//Part-12
/*
        so I'll call book Seat, passing my rodgers NYC theatre, the row, the character A in single quotes, and 3 for the
    third seat in row A. Now, I'll run that,

                ------------------------------------------------------------------------------------------
                Congratulations! Your reserved seat is A003
                ------------------------------------------------------------------------------------------
                Richard Rodgers Seat Map
                ------------------------------------------------------------------------------------------
                A001    A002    A003(●) A004    A005    A006    A007    A008    A009    A010
                B001    B002    B003    B004    B005    B006    B007    B008    B009    B010
                C001    C002    C003    C004    C005    C006    C007    C008    C009    C010
                D001    D002    D003    D004    D005    D006    D007    D008    D009    D010
                E001    E002    E003    E004    E005    E006    E007    E008    E009    E010
                F001    F002    F003    F004    F005    F006    F007    F008    F009    F010
                G001    G002    G003    G004    G005    G006    G007    G008    G009    G010
                H001    H002    H003    H004    H005    H006    H007    H008    H009    H010
                I001    I002    I003    I004    I005    I006    I007    I008    I009    I010
                J001    J002    J003    J004    J005    J006    J007    J008    J009    J010
                ------------------------------------------------------------------------------------------
                Seat's already reserved.
                Sorry! Unable to reserve A3

    and I can see a message saying congratulations, your reserved seat is A003, and the seat map now includes a mark after
    that seat, which means it's reserved. If I copy and paste that last statement. And run that again, I get the messages,
    Seat's already reserved. Then Sorry, unable to reserve A, zero zero 3.
*/
//End-Part-12

        bookSeat(rodgersNYC, 'B', 1);
        bookSeat(rodgersNYC, 'B', 11);
        bookSeat(rodgersNYC, 'M', 1);

//Part-13
/*
        I'll call this again, but this time, I want to pass B as the row, and one as the seat number. Running that,

                Congratulations! Your reserved seat is B001
                ------------------------------------------------------------------------------------------
                Richard Rodgers Seat Map
                ------------------------------------------------------------------------------------------
                A001    A002    A003(●) A004    A005    A006    A007    A008    A009    A010
                B001(●) B002    B003    B004    B005    B006    B007    B008    B009    B010
                C001    C002    C003    C004    C005    C006    C007    C008    C009    C010
                D001    D002    D003    D004    D005    D006    D007    D008    D009    D010
                E001    E002    E003    E004    E005    E006    E007    E008    E009    E010
                F001    F002    F003    F004    F005    F006    F007    F008    F009    F010
                G001    G002    G003    G004    G005    G006    G007    G008    G009    G010
                H001    H002    H003    H004    H005    H006    H007    H008    H009    H010
                I001    I002    I003    I004    I005    I006    I007    I008    I009    I010
                J001    J002    J003    J004    J005    J006    J007    J008    J009    J010
                ------------------------------------------------------------------------------------------
                --> No such seat: B011: Seat must be between A001 and J010
                Sorry! Unable to reserve B11
                --> No such seat: M001: Seat must be between A001 and J010
                Sorry! Unable to reserve M1

    I was able to reserve b, zero zero 1, and you can see that reflecting in the printed seat map. I'll copy that last
    statement and paste a copy right below it. Let me try b eleven this time, and I'll paste another copy, and maybe try
    M1 here. Running this, you can see that I got the message, no such seat, in both cases, and that the message prints
    out the first seat and the last seat there. Ok, so that's one way to reserve a single seat, by a requested row and
    seat in row. Also you could use ceiling instead of floor, and actually that works just the same.
*/
//End-Part-13
    }

//Part-11
/*
        I'm going to add a private static method there. I want to call this bookSeat, that will take a theatre, a row
    character, and a seat integer. I'll call reserveSeat on theatre, and assign that to a local variable, a String. If
    the seat's not null, I'll print Congratulations, Your reserved seat is, and print the seat information. And after
    that, I'll print the seat map again. Otherwise, I'll print Sorry, unable to reserve and include the seat number
    requested. I'll call that from my main method,
*/
//End-Part-11

    private static void bookSeat(Theatre theatre, char row, int seatNo) {

        String seat = theatre.reserveSeat(row, seatNo);
        if (seat != null) {
            System.out.println("Congratulations! Your reserved seat is " + seat);
            theatre.printSeatMap();
        } else {
            System.out.println("Sorry! Unable to reserve " + row + seatNo);
        }
    }
}
