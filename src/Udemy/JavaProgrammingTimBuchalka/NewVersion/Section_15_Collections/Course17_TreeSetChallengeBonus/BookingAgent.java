package CourseCodes.NewSections.Section_15_Collections.Course17_TreeSetChallengeBonus;

//Part-1
/*

        The bonus was to Create a second method on the Theatre class, that lets an agent reserve a number of seats, contiguous
    within a row. The parameters should be:

        * the number of reservations being requested.
        * a range of rows (from A to C for example, for front row seats).
        * a range of seats (a number greater than or equal to 1, and less than or equal to the rows per seat).

        Let's see if we can figure out how to reserve a series of contiguous seats within a single row. Our booking agent
    will let a buyer pick the number of seats they want to reserve. The buyer will specify ranges, for their selected rows
    and their desired seat numbers. This will accommodate people who prefer to sit on aisles, or in the middle of a row,
    for example. To start, I'm going to add another method on the Theatre class,
*/
//End-Part-1
public class BookingAgent {

    public static void main(String[] args) {

        int rows = 10;
        int totalSeats = 100;
        Theatre rodgersNYC = new Theatre("Richard Rodgers", rows, totalSeats);

        rodgersNYC.printSeatMap();

        bookSeat(rodgersNYC, 'A', 3);
        bookSeat(rodgersNYC, 'A', 3);

        bookSeat(rodgersNYC, 'B', 1);
        bookSeat(rodgersNYC, 'B', 11);
        bookSeat(rodgersNYC, 'M', 1);

        bookSeats(rodgersNYC, 4, 'B', 3, 10);

//Part-11
/*
        I'll start by trying to book 4 seats, in row B only, and I don't want them to include B1 or B2, so my min will be
    3 and my max will be 10. I'll run that and see what I get.

                    Congratulations! Your reserved seats are [B003, B004, B005, B006]
                    ------------------------------------------------------------------------------------------
                    Richard Rodgers Seat Map
                    ------------------------------------------------------------------------------------------
                    A001    A002    A003(●) A004    A005    A006    A007    A008    A009    A010
                    B001(●) B002    B003(●) B004(●) B005(●) B006(●) B007    B008    B009    B010
                    C001    C002    C003    C004    C005    C006    C007    C008    C009    C010
                    D001    D002    D003    D004    D005    D006    D007    D008    D009    D010
                    E001    E002    E003    E004    E005    E006    E007    E008    E009    E010
                    F001    F002    F003    F004    F005    F006    F007    F008    F009    F010
                    G001    G002    G003    G004    G005    G006    G007    G008    G009    G010
                    H001    H002    H003    H004    H005    H006    H007    H008    H009    H010
                    I001    I002    I003    I004    I005    I006    I007    I008    I009    I010
                    J001    J002    J003    J004    J005    J006    J007    J008    J009    J010
                    ------------------------------------------------------------------------------------------

    And good news, there were four seats available, and I was able to reserve them, B003, through B006, as you can see.
    And the seat map now shows them as reserved. M
*/
//End-Part-11

        bookSeats(rodgersNYC, 6, 'B', 'C', 3, 10);

//Part-12
/*
        My next reservation, wants 6 seats, either in row B or C, and again, for whatever reason, they don't like seats
    1 and 2. Running that request,

                    Congratulations! Your reserved seats are [C003, C004, C005, C006, C007, C008]
                    ------------------------------------------------------------------------------------------
                    Richard Rodgers Seat Map
                    ------------------------------------------------------------------------------------------
                    A001    A002    A003(●) A004    A005    A006    A007    A008    A009    A010
                    B001(●) B002    B003(●) B004(●) B005(●) B006(●) B007    B008    B009    B010
                    C001    C002    C003(●) C004(●) C005(●) C006(●) C007(●) C008(●) C009    C010
                    D001    D002    D003    D004    D005    D006    D007    D008    D009    D010
                    E001    E002    E003    E004    E005    E006    E007    E008    E009    E010
                    F001    F002    F003    F004    F005    F006    F007    F008    F009    F010
                    G001    G002    G003    G004    G005    G006    G007    G008    G009    G010
                    H001    H002    H003    H004    H005    H006    H007    H008    H009    H010
                    I001    I002    I003    I004    I005    I006    I007    I008    I009    I010
                    J001    J002    J003    J004    J005    J006    J007    J008    J009    J010
                    ------------------------------------------------------------------------------------------

    I couldn't reserve seats in row B, because there weren't enough contiguous seats, so it reserved them in row C. Let's
    keep going here with some more test scenarios.
*/
//End-Part-12

        bookSeats(rodgersNYC, 4, 'B', 1, 10);

//Part-13
/*
        Let's see what happens if I ask for 4 seats, in row B, anywhere in row B, meaning seats 1 through 10.

                    Congratulations! Your reserved seats are [B007, B008, B009, B010]
                    ------------------------------------------------------------------------------------------
                    Richard Rodgers Seat Map
                    ------------------------------------------------------------------------------------------
                    A001    A002    A003(●) A004    A005    A006    A007    A008    A009    A010
                    B001(●) B002    B003(●) B004(●) B005(●) B006(●) B007(●) B008(●) B009(●) B010(●)
                    C001    C002    C003(●) C004(●) C005(●) C006(●) C007(●) C008(●) C009    C010
                    D001    D002    D003    D004    D005    D006    D007    D008    D009    D010
                    E001    E002    E003    E004    E005    E006    E007    E008    E009    E010
                    F001    F002    F003    F004    F005    F006    F007    F008    F009    F010
                    G001    G002    G003    G004    G005    G006    G007    G008    G009    G010
                    H001    H002    H003    H004    H005    H006    H007    H008    H009    H010
                    I001    I002    I003    I004    I005    I006    I007    I008    I009    I010
                    J001    J002    J003    J004    J005    J006    J007    J008    J009    J010
                    ------------------------------------------------------------------------------------------

    The good news is that this request was successful, it ignored seat B002, since it didn't have any contiguous seats,
    but found the seats B007 through B010.
*/
//End-Part-13

        bookSeats(rodgersNYC, 4, 'B', 'C', 1, 10);

//Part-14
/*
        Now, I want 4 seats, in either row B or C, any seats, so from 1 to 10. Running that,

                        Richard Rodgers Seat Map
                        ------------------------------------------------------------------------------------------
                        A001    A002    A003(●) A004    A005    A006    A007    A008    A009    A010
                        B001(●) B002    B003(●) B004(●) B005(●) B006(●) B007(●) B008(●) B009(●) B010(●)
                        C001    C002    C003(●) C004(●) C005(●) C006(●) C007(●) C008(●) C009    C010
                        D001    D002    D003    D004    D005    D006    D007    D008    D009    D010
                        E001    E002    E003    E004    E005    E006    E007    E008    E009    E010
                        F001    F002    F003    F004    F005    F006    F007    F008    F009    F010
                        G001    G002    G003    G004    G005    G006    G007    G008    G009    G010
                        H001    H002    H003    H004    H005    H006    H007    H008    H009    H010
                        I001    I002    I003    I004    I005    I006    I007    I008    I009    I010
                        J001    J002    J003    J004    J005    J006    J007    J008    J009    J010
                        ------------------------------------------------------------------------------------------
                        Sorry! No matching contiguous seats in rows: B - C

    I can see this request failed. Neither of the selected rows had four contiguous seats at this time.
*/
//End-Part-14

        bookSeats(rodgersNYC, 1, 'B', 'C', 1, 10);

//Part-15
/*
        What if I just asked for one seat next, in rows B and C? Asking for a seat this way, lets me reserve a single
    seat, but I was able to be a little less specific than if I just used the book seat method.

                        Congratulations! Your reserved seats are [B002]
                        ------------------------------------------------------------------------------------------
                        Richard Rodgers Seat Map
                        ------------------------------------------------------------------------------------------
                        A001    A002    A003(●) A004    A005    A006    A007    A008    A009    A010
                        B001(●) B002(●) B003(●) B004(●) B005(●) B006(●) B007(●) B008(●) B009(●) B010(●)
                        C001    C002    C003(●) C004(●) C005(●) C006(●) C007(●) C008(●) C009    C010
                        D001    D002    D003    D004    D005    D006    D007    D008    D009    D010
                        E001    E002    E003    E004    E005    E006    E007    E008    E009    E010
                        F001    F002    F003    F004    F005    F006    F007    F008    F009    F010
                        G001    G002    G003    G004    G005    G006    G007    G008    G009    G010
                        H001    H002    H003    H004    H005    H006    H007    H008    H009    H010
                        I001    I002    I003    I004    I005    I006    I007    I008    I009    I010
                        J001    J002    J003    J004    J005    J006    J007    J008    J009    J010
                        ------------------------------------------------------------------------------------------

    I was able to reserve the last open seat in row B, B002. Let's try one with some bad data.
*/
//End-Part-15

        bookSeats(rodgersNYC, 4, 'M', 'Z', 1, 10);

//Part-16
/*
        I'll enter rows M through Z in this request.

                    Invalid! 4 seats between M[1-10]-J[1-10] Try again: Seat must be between A001 and J010
                    Sorry! No matching contiguous seats in rows: M - Z

    And that didn't work, I got invalid, the message from my validation method on Theatre. Notice that it wanted to use
    J as the maximum row, but it didn't do the same for the start row, in this case M. It's a lot harder to know where
    to begin, if your agent doesn't get that right, so the code just flags it as invalid. Finally, I want to do one more
    test.
*/
//End-Part-16

        bookSeats(rodgersNYC, 10, 'A', 'E', 1, 10);

//Part-17
/*
        I'll try to reserve 10 seats, in any row from A, through E. Running that,

                        Congratulations! Your reserved seats are [D001, D002, D003, D004, D005, D006, D007, D008, D009, D010]
                        ------------------------------------------------------------------------------------------
                        Richard Rodgers Seat Map
                        ------------------------------------------------------------------------------------------
                        A001    A002    A003(●) A004    A005    A006    A007    A008    A009    A010
                        B001(●) B002(●) B003(●) B004(●) B005(●) B006(●) B007(●) B008(●) B009(●) B010(●)
                        C001    C002    C003(●) C004(●) C005(●) C006(●) C007(●) C008(●) C009    C010
                        D001(●) D002(●) D003(●) D004(●) D005(●) D006(●) D007(●) D008(●) D009(●) D010(●)
                        E001    E002    E003    E004    E005    E006    E007    E008    E009    E010
                        F001    F002    F003    F004    F005    F006    F007    F008    F009    F010
                        G001    G002    G003    G004    G005    G006    G007    G008    G009    G010
                        H001    H002    H003    H004    H005    H006    H007    H008    H009    H010
                        I001    I002    I003    I004    I005    I006    I007    I008    I009    I010
                        J001    J002    J003    J004    J005    J006    J007    J008    J009    J010
                        ------------------------------------------------------------------------------------------

    you can see I laid claim to the entire row D for my reservations. Too bad I couldn't boot the guy that reserved A003,
    and give my 10 guests better seats. Maybe the theatre shouldn't allow single seats in an unreserved row to start?
    Anyway, that's a rule for another day. Ok, so this challenge used some of the methods unique to the TreeSet, or more
    accurately to the NavigableSet, such as last and first, floor, ceiling and subset. In the next lecture, I'm going to
    transition to talking about a different kind of collections group, the Map, so I'll see you in that next lecture.
*/
//End-Part-17
    }

    private static void bookSeat(Theatre theatre, char row, int seatNo) {

        String seat = theatre.reserveSeat(row, seatNo);
        if (seat != null) {
            System.out.println("Congratulations! Your reserved seat is " + seat);
            theatre.printSeatMap();
        } else {
            System.out.println("Sorry! Unable to reserve " + row + seatNo);
        }
    }

//Part-10
/*
        And add another method there as well, called bookSeats. This is going to be similar to the bookSeat method, but
    will have more parameters, so again private static void, named bookSeats. It takes a theatre, then a number of tickets,
    an int. After that, I want a range, so a min row and a max row, both chars. That's followed by a range of seat numbers,
    so a minimum seat, and a max seat, integers there. I'll have a local variable, and I'll just use var for simplicity,
    and assign it the result of calling reserve seats on the theatre passed, using the other arguments passed. I want to
    again see if I'm getting data back, so I'll check if seats is not null, and print congratulations, and the seats that
    were reserved. Otherwise I'll print sorry, that no contiguous seats could be found to reserve. I also want an overloaded
    version of this method, so if a user only wants to look in a single row, they can just pass a single row. I'll insert
    this above the current method. It has the same signature, except I won't have the char maxRow. That's the only difference.
    And I'll just execute the overloaded version, passing minRow in both them inRow and maxRow parameters to that method.
    Now, I'm ready to give this a go, so I'll scroll up to my main method.
*/
//End-Part-10

    private static void bookSeats(Theatre theatre, int tickets, char minRow, int minSeat, int maxSeat ) {

        bookSeats(theatre, tickets, minRow, minRow, minSeat, maxSeat);
    }

    private static void bookSeats(Theatre theatre, int tickets,
                                  char minRow, char maxRow,
                                  int minSeat, int maxSeat) {

        var seats =
                theatre.reserveSeats(tickets, minRow, maxRow, minSeat, maxSeat);
        if (seats != null) {
            System.out.println("Congratulations! Your reserved seats are " + seats);
            theatre.printSeatMap();
        } else {
            System.out.println("Sorry! No matching contiguous seats in rows: "
                    + minRow + " - " + maxRow);
        }
    }
}
