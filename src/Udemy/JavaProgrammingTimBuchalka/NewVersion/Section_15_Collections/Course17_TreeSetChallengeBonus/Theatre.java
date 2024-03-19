package CourseCodes.NewSections.Section_15_Collections.Course17_TreeSetChallengeBonus;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class Theatre {

    class Seat implements Comparable<Seat> {

        private String seatNum;
        private boolean reserved;

        public Seat(char rowChar, int seatNo) {
            this.seatNum = "%c%03d".formatted(rowChar, seatNo).toUpperCase();
        }

        @Override
        public String toString() {
            return seatNum;
        }

        @Override
        public int compareTo(Seat o) {
            return seatNum.compareTo(o.seatNum);
        }
    }

    private String theatreName;
    private int seatsPerRow;
    private NavigableSet<Seat> seats;

    public Theatre(String theatreName, int rows, int totalSeats) {
        this.theatreName = theatreName;
        this.seatsPerRow = totalSeats / rows;

        seats = new TreeSet<>();
        for (int i = 0; i < totalSeats; i++) {
            char rowChar = (char) (i / seatsPerRow + (int) 'A');
            int seatInRow = i % seatsPerRow + 1;
            seats.add(new Seat(rowChar, seatInRow));
        }
    }

    public void printSeatMap() {

        String separatorLine = "-".repeat(90);
        System.out.printf("%1$s%n%2$s Seat Map%n%1$s%n", separatorLine,
                theatreName);

        int index = 0;
        for (Seat s : seats) {
            System.out.printf("%-8s%s",
                    s.seatNum + ((s.reserved) ? "(\u25CF)" : ""),
                    ((index++ + 1) % seatsPerRow == 0) ? "\n" : "");
        }
        System.out.println(separatorLine);
    }

    public String reserveSeat(char row, int seat) {

        Seat requestedSeat = new Seat(row, seat);
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

//Part-3
/*
        I'll insert this method just above the reserveSeats method I've been working on. It will have similar arguments,
    so count, a first and last character, for the row range, and a minimum, and maximum integer, for the seat range, and
    it will return a boolean. The first thing I want to do is set up a boolean result variable, and assign it the result
    of a logical or statement, with multiple conditions. I want to return the result from this method. In this code, I
    first want to make sure the min is greater than zero, because my seat numbers in any row, always start with 1. Next,
    I want to make sure the users aren't trying to get more seats than I have in a single row.This method is only going
    to return seats from a single row. Another good method for Theatre might be to reserve a block, that includes multiple
    rows, but that's for another day. Lastly I just want to make sure that the difference of max and min, is greater than
    the number of seats they want. In addition to these three validation checks, I also want to confirm that the first
    row they picked is really in my available seats.

        I'm going to set up another conditional logical statement, this time
    using an and operator. If the result is false, because one of the first three conditions is met, it won't test the
    second condition. That's what the two ampersands mean, if the result can be determined by the first part, if that part
    is false in an and statement, then it won't execute the second expression. This is code, especially since my expression
    on the right, is calling the contains method on a set, and that could get costly.

        The second condition executes the contains method, to see if the seat (and I'm creating a new seat here with the
    first row, and the minimum seat number) is really a valid seat. If my result from any of these conditions is false,
    I want to print out some information for the booking agent, to that effect. This is a more complex formatted string,
    because I want you to get used to seeing a formatted string that uses argument indexes and dollar signs. I've shown
    this to you before, but this was another good opportunity to use it. It saves me the trouble of having to pass min
    and max as parameters, multiple times. I'll also print something similar to what I printed in the reserve seat method,
    so the valid range, using seats.first, and seats.last to do that. Now I can call this method from my reserve seats
    method below it.
*/
//End-Part-3

    private boolean validate(int count, char first, char last, int min, int max) {

        boolean result = (min > 0 || seatsPerRow >= count || (max - min + 1) >= count);

//Part-4
/*
        I'm going to set up another conditional logical statement, this time using an "and" operator. If the result is
    false, because one of the first three conditions is met, it won't test the second condition. That's what the two ampersands
    mean, if the result can be determined by the first part, if that part is false in an and statement, then it won't
    execute the second expression. This is code, especially since my expression on the right, is calling the contains
    method on a set, and that could get costly. The second condition executes the contains method, to see if the seat
    (and I'm creating a new seat here with the first row, and the minimum seat number) is really a valid seat.
*/
//End-Part-4

        result = result && seats.contains(new Seat(first, min));

//Part-5
/*
        If my result from any of these conditions is false, I want to print out some information for the booking agent,
    to that effect. This is a more complex formatted string, because I want you to get used to seeing a formatted string
    that uses argument indexes and dollar signs. I've shown this to you before, but this was another good opportunity to
    use it. It saves me the trouble of having to pass min and max as parameters, multiple times. I'll also print something
    similar to what I printed in the reserve seat method, so the valid range, using seats.first, and seats.last to do that.
    Now I can call this method from my reserve seats method below it.
*/
//End-Part-5

        if (!result) {
            System.out.printf("Invalid! %1$d seats between " + "%2$c[%3$d-%4$d]-%5$c[%3$d-%4$d] Try again",
                    count, first, min, max, last);
            System.out.printf(": Seat must be between %s and %s%n",
                    seats.first().seatNum, seats.last().seatNum);
        }

        return result;
    }

//Part-2
/*
        I'll call reserve seats, again public, this time it will return a set of seats if the reservation was successful,
    or null if not. It will take the number of seats requested, the minimum row requested as a char, and the maximum row
    requested, another char. Then I'll have the minimum seat requested in that row, and the maximum requested seat in the
    same row. The first thing I'll do is, get the last valid seat in the theatre, getting the row identifier, the first
    character, from the seat num. And if the max character that was passed to this method is actually larger than that,
    I'll just ignore it and use this character, so if the user enters Z for example, and my rows only go through the letter
    M, I'll only look through M. I could do the same for the minimum row, but I want to include other validation, that
    should eliminate that scenario in any case. I'm going to do this with a validation method, which I'll get coded before
    I complete my reserveSeats method.
*/
//End-Part-2

    public Set<Seat> reserveSeats(int count,  char minRow, char maxRow, int minSeat, int maxSeat) {

        char lastValid = seats.last().seatNum.charAt(0);
        maxRow = (maxRow < lastValid) ? maxRow : lastValid;

//Part-6
/*
        I'll include an if statement, and test the negated result, of the validate method, with the arguments passed to
    this method. If I get false back, I want to just get out of the method here and pass back null. Otherwise, I'll set
    a new local variable, another Navigable set, called selected, and for the moment, I want that to be null. Ok, so I've
    got validation, but now I need to figure out how to process this data.
*/
//End-Part-6

        if (!validate(count, minRow, maxRow, minSeat, maxSeat)) {
            return null;
        }

        NavigableSet<Seat> selected = null;

//Part-7
/*
        I'm going to loop through each desired row, so that will be a char variable, starting with the minRow that was
    passed, going up to and including the maxRow. MaxRow was either what the user passed, or the last row in the theatre,
    whichever was less. The first thing I want to do is get a subset from seats, which will be the seats in the row of
    the current row I'm processing. My minSeat will be a new seat with the current letter, and the minimum seat selected.
    This could be 1, but might be something else, if they didn't want an aisle seat, for example. I'm going to use the
    overloaded version of subSet, and make the inclusive flags true, meaning I want to include the seats I'm specifying
    here. The last seat is a seat in the same row, but determined by the max seat entered by the user. Once I've got my
    subset of seats, I want to loop through them, but first I'll set up a couple of local variables.
*/
//End-Part-7

        for (char letter = minRow; letter <= maxRow; letter++) {

            NavigableSet<Seat> contiguous = seats.subSet(
                    new Seat(letter, minSeat), true,
                    new Seat(letter, maxSeat), true);

//Part-8
/*
        I'm going to use an enhanced for loop this time, but I still want an indexed variable, that will increment each
    time I find a good seat in the row, so I'll start with int, index equals zero. I want a variable for my first seat
    in the reserved set of seats. I'm going to set this to null to start with. It will get populated with the first
    unreserved seat. I'll set up my enhanced for loop, naming the element current, and I'll go through the elements in
    my contiguous set (the subset of seats in this row, that match the min and max criteria). I'm going to check each
    seat in my subset, and try to find the first unreserved seat. If a seat is reserved, I'll reset the index back to
    zero, And I'll call continue, which will just go up and check the next set in this subset. Once I find an unreserved
    seat, I want to set my first variable to it, if my index is zero, meaning this is the first unreserved seat I've found.
    I'll increment my index variable, with a prefix increment, so the ++ is to the left of the variable. This means it
    gets incremented before I compare it to count, which I do here next. If these are equal, I know I've found enough
    contiguous seats to meet the requirement. In that case, I'll use subset again, this time on the contiguous set to get
    all the matching seats, and assign that to my selected seats variable. I'll break out of the enhanced for loop, since
    I found all the seats I need. In addition, I need to break out of the outer loop, since I'm done with the work at that
    point. I'll do this with a conditional statement, after the enhanced for loop, that breaks if selected isn't null.
    Later, I'll cover how to do this with loop labels, but since this is a less commonly used feature, I'll include that
    conversation at a later date, when I cover odds and ends.
*/
//End-Part-8

            int index = 0;
            Seat first = null;
            for (Seat current : contiguous) {
                if (current.reserved) {
                    index = 0;
                    continue;
                }
                first = (index == 0) ? current : first;
                if (++index == count) {
                    selected = contiguous.subSet(first, true,
                            current, true);
                    break;
                }
            }
            if (selected != null) {
                break;
            }
        }

//Part-9
/*
        After processing, I want to set up a result set, which I'll initially set to null. If my selected set isn't null,
    this means I found good seats. I can loop through those, and set each seat's reserved field to true. Finally, I want
    a copy of the set, so I'll create a new TreeSet and pass it the selected set. Now, this was a pretty long method,
    with quite a bit going on. If you don't quite get it, let me encourage you to set breakpoints, and use the IntelliJ
    debugging option to run this code. Step through each execution, and study how the local variables are changing, as
    you go through the iterations of each loop. Right now, I want to get back to my BookingAgent class,
*/
//End-Part-9

        Set<Seat> reservedSeats = null;
        if (selected != null) {
            selected.forEach(s -> s.reserved = true);
            reservedSeats = new TreeSet<>(selected);
        }
        return reservedSeats;
    }
}
