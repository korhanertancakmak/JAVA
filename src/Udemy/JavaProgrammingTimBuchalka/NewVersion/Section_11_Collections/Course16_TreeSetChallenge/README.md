# [TreeSet Challenge]()
<div align="justify">

I've created instead of **Main**, 
I'm going to call my starting class, 
_BookingAgent_. 
Before I do anything here,
I'll create the **Theatre** class.
I'll add my inner nested class here, 
the **Seat** class. 

```java  
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
}
```

This class has two fields, 
a String I'll call _SeatNum_.
And a boolean, _reserved_. 
I'll add a constructor, 
selecting just seatNum as the field. 
I want to change this constructor though, 
as I stated on the challenge info, 
so that one argument will be a character, 
for the row, and the other 
is the seat number in that row, so an int. 
I'll change the assignment to a formatted String,
which will take the row character, and the seatNumber,
prefixing it with leading zeros up to three digits,
and I'll make all of that upper case.
Ok, that's my constructor.
Next, I want to override the _toString_ method,
and return _seatNum_ there. 
Finally, I want to implement Comparable on this **Seat** class. 
It's important to remember 
to pass a type argument to **Comparable**, 
in this case **Seat**, 
so that when we go to override its abstract method, 
it generates it using the **Seat** type. 
This is because I want to use it with a **TreeSet**, 
without being forced to specify a comparator 
any time I want to use methods, 
or create a new **TreeSet** of seats.
IntelliJ flags that as a problem, as you'd expect. 
I'll hover over that, and implement the _compareTo_ method, 
and since I don't want to return zero, I'll change that. 
I want to return _seatNum.compareTo_, 
and pass that _o.seatNum_. 
That's all I really need in this little nested class.
Next, I want to set up the fields in the **theatre** class.

```java  
private String theatreName;
private int seatsPerRow;
private NavigableSet<Seat> seats;
```

These were _theatreName_, a string.
_seatsPerRow_, an int. 
And a **NavigableSet**, with type argument, **Seat**, 
called _seats_. 
This is my collection for the theatre's seats. 
So why did I use **NavigableSet**, 
and not **SortedSet** or **TreeSet**?
First of all, when using 
a class in the **collections** framework, 
you'll use the interface type as your reference type,
so you don't want to use **TreeSet**. 
I'm planning on using methods specifically 
declared on **NavigableSet**, 
that aren't on **SortedSet**, such as the floor 
and _ceiling_ methods, so that's why 
I'm going with this type. 
Next, I want a constructor for my **Theatre** class.

```java  
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
```

I'll generate this, selecting _theatreMame_ 
and _seatsPerRow_. 
I want to change this constructor,
so first I'll change _seatsPerRow_ to just _rows_, 
and I'll add an argument, an int, for _totalSeats_. 
I'll change the assignment, and make _seatsPerRow_ 
equal to _totalSeats_ divided by _rows_. 
I'm going to set up the _totalSeats_ in this constructor. 
I'll first initialize _seats_ to a new **TreeSet**. 
I'll loop from zero to one less than the _totalSeats_.
I'll calculate the row character. 
This is the current index, divided by the _seatsPerRow_, 
added to the integer value for a capital _A_. 
This means the first row's seats will be labeled _A_, 
the second row's seats will be labeled _B_ and so on. 
My seat num can be derived using the remainder operator, 
so _i_, mod _seatsPerRow_, and I don't want any zero seat numbers, 
so I want to add 1 to each of these. 
I'll add a new seat, passing it the row character, 
and the _seatInRow_, adding that to the _seats_ field. 
After this, I'll add the printSeatMap method:

```java  
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
```

So I can test if my _theatre_ seats are correctly numbered. 
I'll make this public, 
because my booking agent should be able to call it. 
I'll set up a local variable, a separator line, 90 dashes. 
I'll print a formatted String. 
This looks complex, but I'm simply printing a separator line, 
then the theatre name, and then the separator line again.
Notice the use of dollar signs in these specifiers. 
If I specify where to find the passed parameter,
starting at 1, I can use a parameter in multiple places, 
which is what I'm doing here. 
I'll finish with yet another dashed line. 
I haven't really done anything here yet. 
I still have to add the code to print the seats. 
I'll start with a local variable I'll call index, 
initializing that to zero. 
I'll loop through the seats. 
And I'm going to print another formatted String.
This will be the seat number, followed by an indicator, 
a large bullet character, if the seat is reserved.
I'm using the unicode character, with the _\u_ escape here, 
but if you want to take IntelliJ's suggestion to replace
it with the actual character, go ahead, 
or use any character that you like there, 
it's just a way of seeing what's reserved. 
Finally, I want to include a new line character. 
If I reach the end of a row of seats, 
so I'll use the remainder operator to check that. 
Ok, that's it for that method. 
Now, I'll go back to my BookingAgent class, 
and create my first theatre.

```java  
public class BookingAgent {

    public static void main(String[] args) {

        int rows = 10;
        int totalSeats = 100;
        Theatre rodgersNYC = new Theatre("Richard Rodgers", rows, totalSeats);

        rodgersNYC.printSeatMap();
    }
}
```

I'll set up two local variables, an int, rows, equal 10, 
and totalSeats = 100. 
Doing the math, my rows should all have 10 seats in them.
There's a theatre in new york city, called _Richard Rodgers_, 
so I'll name my local variable _rodgersNYC_, 
and pass it the name, _Richard Rodgers_, 
and my rows and total seats. 
Then I'll call _printSeatMap_ on that variable. 
Running that code:

```java  
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
```
        
I get a well-formatted seat map of this _theatre_. 
This really isn't what this theatre's seat map looks like, 
but we're keeping it simple. 
I have rows _A_, through _J_, and in each row, seats 1 through 10. 
Nothing's reserved yet. 
I have done little with the **TreeSet** up to this point, 
except use it as storage, and print it out. 
I could actually change the type to a **LinkedHashSet**.
Let me show you that. 
I'll go back to the Theatre constructor:

```java  
public Theatre(String theatreName, int rows, int totalSeats) {
    this.theatreName = theatreName;
    this.seatsPerRow = totalSeats / rows;

    //seats = new TreeSet<>();
    seats = new LinkedHashSet<>();
    for (int i = 0; i < totalSeats; i++) {
        char rowChar = (char) (i / seatsPerRow + (int) 'A');
        int seatInRow = i % seatsPerRow + 1;
        seats.add(new Seat(rowChar, seatInRow));
    }
}
```

And change the type to a LinkedHashSet. 
This gives me an error though, and that's 
because I declared seats as a **NavigableSet**, and _LinkedHashSet_, 
doesn't implement that interface. 

```java  
private String theatreName;
private int seatsPerRow;
//private NavigableSet<Seat> seats;
private Set<Seat> seats;
```

I'll change that to Set here, for just a minute. 
Ok, so that compiles and I can run it.

```java  
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
```

My printed map looks exactly the same. 
That's because I added all my seats in this order, 
so they're in insertion order, in the order 
I'd want to see them printed. 

```java  
public Theatre(String theatreName, int rows, int totalSeats) {
    this.theatreName = theatreName;
    this.seatsPerRow = totalSeats / rows;

    //seats = new TreeSet<>();
    //seats = new LinkedHashSet<>();
    seats = new HashSet<>();
    for (int i = 0; i < totalSeats; i++) {
        char rowChar = (char) (i / seatsPerRow + (int) 'A');
        int seatInRow = i % seatsPerRow + 1;
        seats.add(new Seat(rowChar, seatInRow));
    }
}
```

If I change _LinkedHashSet_ to just _HashSet_, 
If I run my code like that:

```java  
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
```

My printed map isn't in any particular order, 
and this order isn't guaranteed. 
That's just a reminder of the three types of Sets you can use. 

```java  
private String theatreName;
private int seatsPerRow;
private NavigableSet<Seat> seats;
//private Set<Seat> seats;
```

I'm going to revert those last three changes 
so that I'm using NavigableSet as the reference type:

```java  
public Theatre(String theatreName, int rows, int totalSeats) {
    this.theatreName = theatreName;
    this.seatsPerRow = totalSeats / rows;

    seats = new TreeSet<>();
    //seats = new LinkedHashSet<>();
    //seats = new HashSet<>();
    for (int i = 0; i < totalSeats; i++) {
        char rowChar = (char) (i / seatsPerRow + (int) 'A');
        int seatInRow = i % seatsPerRow + 1;
        seats.add(new Seat(rowChar, seatInRow));
    }
}
```

And creating a new instance of _TreeSet_. 
Ok, so now, let's actually start doing something a bit more interesting.

```java  
public String reserveSeat(char row, int seat) {
    Seat requestedSeat = new Seat(row, seat);
    Seat requested = seats.floor(requestedSeat);

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
```

First, I want to add the _reserveSeat_ method 
for a single seat reservation. 
I'll make this public 
because it's a method clients should have access to, 
and it will return a String if the seat was found and _reserved_, 
otherwise _null_. 
It will take a char, representing the desired row of the seat, 
and the seat number, within that row, so an int.
I'll set up a local variable, that's a **Seat**, 
and create a new seat using the requested row and seat number. 
I'm going to call the _floor_ method on seats, 
passing it my requested seat variable. 
I've said before that sets don't have a get method, 
and that's still true for a **TreeSet**. 
I could loop through all the set elements, to find my seat, 
or I can take advantage of one of **NavigableSet**'s 
closest match methods, which is what I'm doing here. 
If I used the lower method, 
I'd get the seat that was lower than the seat passed, 
but _floor_ gives me a matched seat if it exists, 
or the seat lower than that. 
It returns _null_ if there aren't any seats lower than 
this seat number in the set. 
I'll check if the value coming back is null, 
or if the seat returned from the lower method 
has a different seat number than the requested one. 
If that's the case, I'll print there's no such seat, 
and print the seat information.
I'll also print the seat range, using _NavigableSet_'s 
_first_ and _last_ methods to do that. 
I'll return _null_ from the method. 
I'll add the code to return a reserved seat next, 
which is the else condition. 
Inside this else, I want a nested if then else condition. 
This time I'll check to see if the seat's still unreserved. 
If that's the case, I'll set reserved to true, 
and return this seat's seat number from the method. 
Otherwise, if the seat's already reserved, I'll print that out. 
Getting back to my bookingAgent class,

```java  
private static void bookSeat(Theatre theatre, char row, int seatNo) {

    String seat = theatre.reserveSeat(row, seatNo);
    if (seat != null) {
        System.out.println("Congratulations! Your reserved seat is " + seat);
        theatre.printSeatMap();
    } else {
        System.out.println("Sorry! Unable to reserve " + row + seatNo);
    }
}
```

I'm going to add a private static method there. 
I want to call this _bookSeat_, that will take a theatre, 
a row character, and a seat integer. 
I'll call _reserveSeat_ on theatre, 
and assign that to a local variable, a String. 
If the seat's not _null_, I'll print _Congratulations, Your reserved seat is_, 
and print the seat information. 
And after that, I'll print the seat map again. 
Otherwise, I'll print _Sorry, unable to reserve_ 
and include the seat number requested. 
I'll call that from my main method:

```java  
bookSeat(rodgersNYC, 'A', 3);
bookSeat(rodgersNYC, 'A', 3);
```

so I'll call _bookSeat_, passing my _rodgersNYC_ theatre, 
the row, the character _A_ in single quotes, and _3_ 
for the third seat in row _A_. 
Now, I'll run that:

```java  
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
```
                
And I can see a message saying _congratulations, your reserved seat is A003_, 
and the seat map now includes a mark after that seat, which means it's reserved. 
If I copy and paste that last statement. 
And run that again, I get the messages, _Seat's already reserved_. 
Then _Sorry, unable to reserve A3_.

```java  
bookSeat(rodgersNYC, 'B', 1);
bookSeat(rodgersNYC, 'B', 11);
bookSeat(rodgersNYC, 'M', 1);
```

I'll call this again, but this time, 
I want to pass _B1_ as the seat. 
Running that:

```java  
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
```
                
I was able to reserve _B1_, and you can see that reflecting 
in the printed seat map. 
I'll copy that last statement and paste a copy right below it.
Let me try _B11_ this time, and I'll paste another copy, 
and maybe try _M1_ here. 
Running this, you can see that I got the message, 
no such seat, in both cases, 
and that the message prints out the first seat 
and the last seat there. 
Ok, so that's one way to reserve a single seat, 
by a requested row and seat in row. 
Also, you could use _ceiling_ instead of _floor_, 
and actually that works just the same.

```java  
public String reserveSeat(char row, int seat) {
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
```

To show you that, I'll go back to my _reserveSeat_ method, 
and simply replace the call to _floor_ with a call to _ceiling_. 
Now, if I rerun the code, I get all the same results. 
In this case, _ceiling_ and _floor_ both work, 
because I'm really interested in an exact match, 
and not the closest match. 
I could have used the contains method, to check 
if seats contained my requested seat, 
but then I'd still have to retrieve the match, 
and _ceiling_ and _floor_ save me the trouble of doing that.
Ok, that ends this part of the challenge, 
which used a **TreeSet** to maintain an ordered set of theatre seats, 
and used the _floor_ and _ceiling_ methods, 
as well as _first_ and _last_. 

Let's see if we can figure out 
how to reserve a series of contiguous seats within a single row. 
Our **BookingAgent** will let a buyer pick the number of seats they want to reserve. 
The buyer will specify ranges for their selected rows and their desired seat numbers. 
This will accommodate people who prefer to sit on aisles, 
or in the middle of a row, for example. 
To start, I'm going to add another method to the **Theatre** class:

```java  
public Set<Seat> reserveSeats(int count,  char minRow, char maxRow, int minSeat, int maxSeat) {

    char lastValid = seats.last().seatNum.charAt(0);
    maxRow = (maxRow < lastValid) ? maxRow : lastValid;
    
    return reservedSeats;
}
```

I'll call _reserveSeats_, again public, 
this time it will return a set of seats 
if the reservation was successful, or _null_ if not. 
It will take the number of seats requested, 
the minimum row requested as a char, 
and the maximum row requested, another char. 
Then I'll have the minimum seat requested in that row, 
and the maximum requested seat in the same row. 
The first thing I'll do is, get the _lastValid_ seat in the theatre, 
getting the row identifier, the first character, from the seat num. 
And if the max character that was passed 
to this method is actually larger than that,
I'll just ignore it and use this character, 
so if the user enters _Z_ for example, 
and my rows only go through the letter _M_, 
I'll only look through _M_. 
I could do the same for the minimum row,
but I want to include other validation, 
that should eliminate that scenario in any case. 
I'm going to do this with a validation method, 
which I'll get coded before I complete my reserveSeats method.

```java  
private boolean validate(int count, char first, char last, int min, int max) {

    boolean result = (min > 0 || seatsPerRow >= count || (max - min + 1) >= count);
    result = result && seats.contains(new Seat(first, min));

    if (!result) {
        System.out.printf("Invalid! %1$d seats between " + "%2$c[%3$d-%4$d]-%5$c[%3$d-%4$d] Try again",
                count, first, min, max, last);
        System.out.printf(": Seat must be between %s and %s%n",
                seats.first().seatNum, seats.last().seatNum);
    }

    return result;
}
```

I'll insert this method just above the _reserveSeats_ method 
I've been working on.
It will have similar arguments, so count, 
a _first_ and _last_ character, for the row range, 
and a _minimum_, and _maximum_ integer, for the seat range, 
and it will return a boolean. 
The first thing I want to do is set up a boolean _result_ variable, 
and assign it the result of a logical or statement, 
with multiple conditions. 
I want to return the _result_ from this method. 
In this code, I first want to make sure the _min_ is greater than zero, 
because my seat numbers in any row always start with 1. 
Next, I want to make sure the users aren't trying to get more seats 
than I have in a single row.
This method is only going to return seats from a single row. 
Another good method for **Theatre** might be to reserve a block 
that includes multiple rows, but that's for another day. 
Lastly, I just want to make sure that the difference of _max_ and _min_ 
is greater than the number of seats they want. 
In addition to these three validation checks, 
I also want to confirm that the first row 
they picked is really in my available seats.
I'm going to set up another conditional logical statement, 
this time using an and operator. 
If the _result_ is false, because one of the first three conditions is met, 
it won't test the second condition. 
That's what the two ampersands mean, 
if the _result_ can be determined by the first part, 
if that part is false in an _and_ statement, 
then it won't execute the second expression. 
This is code, especially since my expression on the right 
is calling the contains method on a set, 
and that could get costly.
The second condition executes the _contains_ method, 
to see if the seat (and I'm creating a new seat here 
with the first row, and the minimum seat number) 
is really a valid seat. 
If my _result_ from any of these conditions is false, 
I want to print out some information for the booking agent, to that effect. 
This is a more complex formatted string, 
because I want you to get used to seeing a formatted string 
that uses argument indexes and dollar signs. 
I've shown this to you before, 
but this was another good opportunity to use it. 
It saves me the trouble of having to pass _min_ and _max_ as parameters, 
multiple times. 
I'll also print something similar to what I printed in the reserve seat method,
so the valid range, using _seats.first_ and _seats.last_ to do that. 
Now I can call this method from my _reserveSeats_ method below it.

```java  
public Set<Seat> reserveSeats(int count,  char minRow, char maxRow, int minSeat, int maxSeat) {

    char lastValid = seats.last().seatNum.charAt(0);
    maxRow = (maxRow < lastValid) ? maxRow : lastValid;

    if (!validate(count, minRow, maxRow, minSeat, maxSeat)) {
        return null;
    }

    NavigableSet<Seat> selected = null;
    
    return reservedSeats;
}
```

I'll include an if statement, 
and test the negated result of the _validate_ method, 
with the arguments passed to this method. 
If I get false back, I want to just get out of the method here 
and pass back null. 
Otherwise, I'll set a new local variable, another _NavigableSet_, 
called selected, and for the moment, I want that to be null.
Ok, so I've got validation, but now I need to figure out 
how to process this data.

```java  
public Set<Seat> reserveSeats(int count,  char minRow, char maxRow, int minSeat, int maxSeat) {

    char lastValid = seats.last().seatNum.charAt(0);
    maxRow = (maxRow < lastValid) ? maxRow : lastValid;

    if (!validate(count, minRow, maxRow, minSeat, maxSeat)) {
        return null;
    }

    NavigableSet<Seat> selected = null;

    for (char letter = minRow; letter <= maxRow; letter++) {

        NavigableSet<Seat> contiguous = seats.subSet(
                new Seat(letter, minSeat), true,
                new Seat(letter, maxSeat), true);
        
    }
    return reservedSeats;
}
```

I'm going to loop through each desired row, 
so that will be a char variable, starting with the minRow 
that was passed, going up to and including the maxRow. 
MaxRow was either what the user passed, 
or the last row in the _theatre_, whichever was less. 
The first thing I want to do is get a subset of seats, 
which will be the seats in the row of the current row I'm processing. 
My minSeat will be a new seat with the current letter, 
and the minimum seat selected.
This could be one, but might be something else, 
if they didn't want an aisle seat, for example. 
I'm going to use the overloaded version of subSet, 
and make the inclusive flags true, 
meaning I want to include the seats I'm specifying here. 
The last seat is a seat in the same row, 
but determined by the max seat entered by the user. 
Once I've got my subset of seats, I want to loop through them, 
but first I'll set up a couple of local variables.

```java  
public Set<Seat> reserveSeats(int count,  char minRow, char maxRow, int minSeat, int maxSeat) {

    char lastValid = seats.last().seatNum.charAt(0);
    maxRow = (maxRow < lastValid) ? maxRow : lastValid;

    if (!validate(count, minRow, maxRow, minSeat, maxSeat)) {
        return null;
    }

    NavigableSet<Seat> selected = null;

    for (char letter = minRow; letter <= maxRow; letter++) {

        NavigableSet<Seat> contiguous = seats.subSet(
                new Seat(letter, minSeat), true,
                new Seat(letter, maxSeat), true);

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
    return reservedSeats;
}
```

I'm going to use an enhanced for loop this time, 
but I still want an indexed variable 
that will increment each time I find a good seat in the row, 
so I'll start with int, index equals zero. 
I want a variable for my first seat in the reserved set of seats. 
I'm going to set this to null to start with. 
It will get populated with the first
unreserved seat. 
I'll set up my enhanced for loop, naming the element current, 
and I'll go through the elements in my contiguous set 
(the subset of seats in this row, 
that match the min and max criteria). 
I'm going to check each seat in my subset, 
and try to find the first unreserved seat. 
If a seat is reserved, I'll reset the index back to zero. 
And I'll call _continue_, which will just go up 
and check the next set in this subset. 
Once I find an unreserved seat, 
I want to set my first variable to it 
if my index is zero, meaning this is the first unreserved seat 
I've found.
I'll increment my index variable, with a prefix increment, 
so the ++ is to the left of the variable. 
This means it gets incremented before I compare it to count, 
which I do here next. 
If these are equal, I know I've found enough contiguous seats 
to meet the requirement.
In that case, I'll use subset again, 
this time on the contiguous set to get all the matching seats, 
and assign that to my selected seats variable. 
I'll break out of the enhanced for loop, 
since I found all the seats I need. 
In addition, I need to break out of the outer loop, 
since I'm done with the work at that point. 
I'll do this with a conditional statement, 
after the enhanced for loop, that breaks 
if selected isn't null.
Later I'll cover how to do this with loop labels, 
but since this is a less commonly used feature, 
I'll include that conversation at a later date, 
when I cover odds and ends.

```java  
public Set<Seat> reserveSeats(int count,  char minRow, char maxRow, int minSeat, int maxSeat) {

    char lastValid = seats.last().seatNum.charAt(0);
    maxRow = (maxRow < lastValid) ? maxRow : lastValid;

    if (!validate(count, minRow, maxRow, minSeat, maxSeat)) {
        return null;
    }

    NavigableSet<Seat> selected = null;

    for (char letter = minRow; letter <= maxRow; letter++) {

        NavigableSet<Seat> contiguous = seats.subSet(
                new Seat(letter, minSeat), true,
                new Seat(letter, maxSeat), true);

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

    Set<Seat> reservedSeats = null;
    if (selected != null) {
        selected.forEach(s -> s.reserved = true);
        reservedSeats = new TreeSet<>(selected);
    }
    return reservedSeats;
}
```

After processing, I want to set up a result set, 
which I'll initially set to null. 
If my selected set isn't null, this means I found good seats. 
I can loop through those, and set each seat's reserved field to true. 
Finally, I want a copy of the set, so I'll create a new **TreeSet** 
and pass it the selected set. 
Now, this was a pretty long method, with quite a bit going on. 
If you don't quite get it, let me encourage you to set breakpoints, 
and use the IntelliJ debugging option to run this code. 
Step through each execution, and study how the local variables are changing 
as you go through the iterations of each loop.
Right now, I want to get back to my **BookingAgent** class:

```java  
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
```

And add another method there as well, called _bookSeats_. 
This is going to be similar to the _bookSeat_ method, 
but will have more parameters, so again private static void, 
named _bookSeats_. 
It takes a theatre, then a number of tickets, an int. 
After that, I want a range, so a min row and a max row, both chars. 
That's followed by a range of seat numbers, so a minimum seat, 
and a max seat, integers there. 
I'll have a local variable, and I'll just use var for simplicity,
and assign it the result of calling reserve seats on the theatre passed, 
using the other arguments passed.
I want to again see if I'm getting data back, so I'll check if seats are not null, 
and print _congratulations, and the seats that were reserved_. 
Otherwise, I'll print _sorry that no contiguous seats could be found to reserve_. 
I also want an overloaded version of this method, 
so if a user only wants to look in a single row, 
they can just pass a single row. 
I'll insert this above the current method. 
It has the same signature, except I won't have the char maxRow. 
That's the only difference.
And I'll just execute the overloaded version, 
passing minRow in both them inRow and maxRow parameters to that method.
Now, I'm ready to give this a go, 
so I'll scroll up to my main method.

```java  
bookSeats(rodgersNYC, 4, 'B', 3, 10);
```

I'll start by trying to book four seats, in row _B_ only, 
and I don't want them to include _B1_ or _B2_, 
so my min will be _3_ and my max will be _10_. 
I'll run that and see what I get.

```java  
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
```

And good news, there were four seats available, 
and I was able to reserve them, _B003_, through _B006_, 
as you can see. 
And the seat map now shows them as reserved.

```java  
bookSeats(rodgersNYC, 6, 'B', 'C', 3, 10);
```

My next reservation wants _6_ seats, either in row _B_ or _C_, and again, 
for whatever reason, they don't like seats _1_ and _2_. 
Running that request:

```java  
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
```

I couldn't reserve seats in row B, because there weren't enough contiguous seats, 
so it reserved them in row _C_. 
Let's keep going here with some more test scenarios.

```java  
bookSeats(rodgersNYC, 4, 'B', 1, 10);
```

Let's see what happens if I ask for four seats, in row _B_,
anywhere in row _B,_ meaning seats 1 through 10.

```java  
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
```

The good news is that this request was successful, 
it ignored seat _B002_, since it didn't have any contiguous seats,
but found the seats _B007_ through _B010_.

```java  
bookSeats(rodgersNYC, 4, 'B', 'C', 1, 10);
```

Now, I want 4 seats, in either row _B_ or _C_, any seats, 
so from 1 to 10. 
Running that:

```java  
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
```
                        
I can see this request failed. 
Neither of the selected rows had four contiguous seats at this time.

```java  
bookSeats(rodgersNYC, 1, 'B', 'C', 1, 10);
```

What if I just asked for one seat next, in rows _B_ and _C_? 
Asking for a seat this way, lets me reserve a single seat, 
but I was able to be a little less specific than 
if I just used the book seat method.

```java  
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
```

I was able to reserve the last open seat in row B, _B002_. 
Let's try one with some bad data.

```java  
bookSeats(rodgersNYC, 4, 'M', 'Z', 1, 10);
```

I'll enter rows M through Z in this request.

```java  
Invalid! 4 seats between M[1-10]-J[1-10] Try again: Seat must be between A001 and J010
Sorry! No matching contiguous seats in rows: M - Z
```
                    
And that didn't work I got invalid, 
the message from my validation method on **Theatre**. 
Notice that it wanted to use _J_ as the maximum row, 
but it didn't do the same for the start row, in this case _M_. 
It's a lot harder to know where to begin, 
if your agent doesn't get that right, 
so the code just flags it as invalid.
Finally, I want to do one more test.

```java  
bookSeats(rodgersNYC, 10, 'A', 'E', 1, 10);
```

I'll try to reserve 10 seats, in any row from _A_, through _E_. 
Running that,

```java  
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
```
                        
You can see I laid claim to the entire row _D_ for my reservations. 
Too bad I couldn't boot the guy that reserved _A003_, 
and give my 10 guests better seats. 
Maybe the theatre shouldn't allow single seats in an unreserved row to start?
Anyway, that's a rule for another day. 
Ok, so this challenge used some methods unique to the **TreeSet**, 
or more accurately to the **NavigableSet**, 
such as _last_, _first_, _floor_, _ceiling_ and _subset_. 
</div>
