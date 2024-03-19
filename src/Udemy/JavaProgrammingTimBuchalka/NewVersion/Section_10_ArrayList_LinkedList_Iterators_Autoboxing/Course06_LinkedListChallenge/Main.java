package CourseCodes.NewSections.Section_10_ArrayList_LinkedList_Iterators_Autoboxing.Course06_LinkedListChallenge;
/*
Part-1
                                                LinkedList Challenge

        It's now time for a LinkedList challenge. I'm going to ask you to use LinkedList functionality, to create a list
    of places, ordered by distance from the starting point. And we want to use a ListIterator, to move, both backwards and
    forwards, through this ordered itinerary of places.

        First, create a type that has a town or place name, and a field for storing the distance from the start. Next,
    create an itinerary of places or towns to visit, much like we've been doing in the last few courses.

                            |       Town        | Distance from Sydney(in km) |
                            |     Adalaide      |           1374              |
                            |   Alice Springs   |           2771              |
                            |     Brisbane      |            917              |
                            |      Darwin       |           3972              |
                            |     Melbourne     |            877              |
                            |       Perth       |           3923              |

    But this time, instead of Strings, you'll want to create a LinkedList of your place or town type. Here we show a list
    of a few places in Australia, and their distances from Sydney. You'll create a LinkedList, ordered by the distance
    from the starting point, in this case Sydney. Sydney should be the first element in your list. You don't want to allow
    duplicate places to be in your list, for this data set.

        In addition, you'll create an interactive program with the following menu item options. The menu will have options
    to move forwards and backwards your itinerary, to list the itinerary, and print menu options and quit the program.

                            Available actions (select word or letter):
                            (F)orward
                            (B)ackward
                            (L)ist Places
                            (M)enu
                            (Q)uit

    You'll want to use a Scanner, and the nextLine method, to get input from the console. You'll use a ListIterator, to
    move forwards and backwards, through the list of places on your itinerary.
End-Part-1
*/

/*
Part-2
        The challenge said to create a type for the place or town to visit, or the itinerary item. Did you create a class
    for this? That would've been a good way to solve it, but Java does offer a much easier option, the record. I'm going
    to create a record, and put it in the Description.txt source file.
End-Part-2
*/

import java.util.LinkedList;
import java.util.Scanner;

record Place(String name, int distance) {
/*
Part-3
        This record has 2 fields, name and distance, which is distance from the starting point. And just by doing this,
    we have a constructor, accessor methods, and an overridden toString method, created for us implicitly by Java. Next,
    I want to set up a LinkedList of these records in the main method.
End-Part-3
*/

    @Override
    public String toString() {
        //return null;
        return String.format("%s (%d)", name, distance);
    }

/*
Part-8
        And choose toString. And I'll replace the null, returning a formatted String. Hopefully, you remember, the String.format
    methods takes a String with format specifiers, and we pass the values, name and distance. Re-running the code,

                    [Adelaide (1374)]

    we get a simpler String printed, this will be easier to look at when we have more places. So now, let's test to see
    if we can add a duplicate record. Going back to the main method,
End-Part-8
*/
}

public class Main {
    public static void main(String[] args) {

        LinkedList<Place> placesToVisit = new LinkedList<>();
/*
Part-4
        With this code, we're saying that LinkedList, is going to contain a list of our Place records. We could use any
    of the add methods on LinkedList, but I want to create my own method, called addPlace. This code is going to let us
    add the place, in order of the distance from the starting place, and also check for duplicates. This will be a private
    method, taking a LinkedList, and the Place record that should get added. This method will return void, and be called
    add place. I'll add those 2 arguments.
End-Part-4
*/

        Place adelaide = new Place("Adelaide", 1374);
        addPlace(placesToVisit, adelaide);
        //System.out.println(placesToVisit);

/*
Part-7
        Here, we create a new Place record with name "Adelaide", and the distance as "1374", which was in the table of data
    on the challenge's info. Then we call addPlace, passing the LinkedList and this new record. When I run this,

                    [Place[name=Adelaide, distance=1374]]

    we see our list now includes the Adelaide record. This is the default string representation for a record, and includes
    the class name, and the record field names and values. I want to simplify this, so I'll add a couple of lines between
    the brackets, in the record body. Then I'll generate an overridden method. I'll use "control + o" to bring up the options.
End-Part-7
*/

        //addPlace(placesToVisit, new Place("Adelaide", 1374));
        addPlace(placesToVisit, new Place("adelaide", 1374));

/*
Part-9
        Commenting the println line above, here, we create another record, with the exact same data as the previous record,
    and we just create that in the second call to addPlace. Running the code,

                    Found duplicate: Adelaide (1374)
                    [Adelaide (1374)]

    we see that our code recognized this as a duplicate record, and didn't add it. This works because Java includes implicit
    code for record, which tests the equality of all it's fields. If all the record's field values match another record's
    field values, Java recognizes that the records are equal. This means we can use the "contains" method, to find a match,
    for records, so that's kind of another nice feature with using records. Now, I'm going to change the second record,
    to have Adelaide, all in lowercase.

         addPlace(placesToVisit, new Place("Adelaide", 1374)); to addPlace(placesToVisit, new Place("adelaide", 1374));

    And running this,

                    [Adelaide (1374), adelaide (1374)]

    we get 2 Adelaide records, 1 uppercase and 1 lowercase. This isn't the behavior we want, so I want to write my own test,
    looping through every element in the list. For me, a match will be a match when the place names are the same, ignoring
    the case, and I'm not going to compare the distance fields. Going to addPlace,
End-Part-9
*/

        addPlace(placesToVisit, new Place("Brisbane", 917));
        addPlace(placesToVisit, new Place("Perth", 3923));
        addPlace(placesToVisit, new Place("Alice Springs", 2771));
        addPlace(placesToVisit, new Place("Darwin", 3972));
        addPlace(placesToVisit, new Place("Melbourne", 877));

/*
Part-12
        Ok, that's our test. I'll run this code now,

                    Found duplicate: adelaide (1374)
                    [Melbourne (877), Brisbane (917), Adelaide (1374), Alice Springs (2771), Perth (3923), Darwin (3972)]

    And we get that printed out, and we can see that our itinerary is ordered, by distance to Sydney, from closest to
    furthest away. And we add Sydney as the first place in our list, with the addFirst method.

        Now, we can create the interactive part of our program, with the menu items. We'll use a Scanner, to get input
    from the user, and we'll use a ListIterator, to move back and forth, through the itinerary. First, we want a method,
    to print those menu options. I'll add a method called printMenu, make it private and static.
End-Part-12
*/

        placesToVisit.addFirst(new Place("Sydney", 0));
        System.out.println(placesToVisit);

        var iterator = placesToVisit.listIterator();
        Scanner scanner = new Scanner(System.in);
        boolean quitLoop = false;
        boolean forward = true;

/*
Part-14
        In this code, we set up the iterator variable, using the "var" keyword, and calling the listIterator method on our
    LinkedList. Next, we create a scanner variable, which we've done quite a few times, so this should be familiar to you.
    And we set up a couple of boolean variables, which I'll explain shortly. Next, I'll print the menu, and start the while
    loop.
End-Part-14
*/

        printMenu();

        while (!quitLoop) {
            if(!iterator.hasPrevious()) {
                System.out.println("Originating : " + iterator.next());
                forward = true;
            }

            if(!iterator.hasNext()) {
                System.out.println("Final : " + iterator.previous());
                forward = false;
            }

            System.out.println("Enter Value: ");
            String menuItem = scanner.nextLine().toUpperCase().substring(0, 1);

            switch (menuItem) {
                case "F" :
                    System.out.println("User wants to go forward");
                    if (!forward) {                 // Reversing Direction
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next();        // Adjust position forward
                        }
                    }

                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                    break;

                case "B" :
                    System.out.println("User wants to go backwards");
                    if (forward) {                 // Reversing Direction
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous();        // Adjust position backwards
                        }
                    }

                    if (iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    }
                    break;

                case "M" :
                    printMenu();
                    break;

                case "L" :
                    System.out.println(placesToVisit);
                    break;

                default :
                    quitLoop = true;
                    break;
            }
        }

/*
Part-15
        Here, we use of the boolean variables, the quitLoop, to determine whether we keep looping or not. And next, we get
    from the console, with scanner.nextLine(). We force the input to be uppercase, then get the first character, with sub
    string method, chaining all these methods together. This means the user can enter one letter or the whole word, in all
    upper case or mixed case, and our code still figure out what they want to do.

        Next, I want to use a traditional switch statement, to process each menu item. I'm going to start with the default
    label. Add a switch for menu item. So this code sets quitLoop to true, which will quit out of the loop, next time it's
    checked at the top of the loop. So we've set up other cases, and we can test that, making sure we can test each option.
    And that looks good. So now, I want to print out the position of the first place, at the start of the loop. Can we
    move backwards?

                    if(!iterator.hasPrevious()) {
                        System.out.println("Originating : " + iterator.next());
                        forward = true;
                    }

    Here, we check if the iterator.hasPrevious method returns false, this means we're at the start of the list. We print
    out the first element, the originating place, using the next method. And we set the forward variable to true, because
    we can only go forward, when this is the case.

                    if(!iterator.hasNext()) {
                        System.out.println("Final : " + iterator.previous());
                        forward = false;
                    }

    Now for this code, if iterator.hasNext is false, this means, we're at the end of the list. So we print out the element,
    just up from the cursor, using the previous method. This is the final element in our list, or final place in our itinerary.
    We set forward to false, because the only direction we can go in, from this point, is backwards, so forward is false
    in this case. We'll test this shortly. Now I have to add code to walk through the list, backwards and forwards, one
    place at a time. And hopefully, you remember how to do this, using the hasNext, and next methods for moving forward.
    Can we go forwards?

                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }

    When the user enters F, or forward, we'll just get the next element in the list, when there is a next element. And also
    User wants to go backwards,

                    if (iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    }

    With this code, when the user presses B, we get the previous element, after we confirm there is a previous element,
    using hasPrevious, and we print that element out. Ok, now test this out.

                    if (!forward) {                 // Reversing Direction
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next();        // Adjust position forward
                        }
                    }

    We know the forward flag is set to true, when we start at the top of the cursor. And we set the forward flag to false,
    if we're at the end of the cursor. If forward is false, this means we're moving backwards. In this code, we set the
    forward flag to true if it's false, because the user pressed F, or entered forward. With the next if statement, we
    check to see if there really is a next element. If there is, we want to adjust the cursor, moving it forward, 1 extra
    spot. All of this code is for when we reverse directions from backwards to forwards. Also, we have to do the same from
    forwards to backwards.

        If the forward flag was true, this means we're reversing our direction and moving backwards, so we set the forward
    flag to false. And we need to adjust our cursor, with an extra call to the previous method, first testing if hasPrevious
    is true.
End-Part-15
*/

    }

    private static void addPlace(LinkedList<Place> list, Place place) {

        if (list.contains(place)) {
            System.out.println("Found duplicate: " + place);
            return;
        }
/*
Part-6
        Hopefully you've remembered that Lists have a method, called contains. This checks for a matching element, so if
    this method finds a match, we'll just return from this method, meaning the item won't get added because it's already
    there. Let's see what happens when we call this method, from the main method.
End-Part-6
*/

        for (Place p : list) {
            if (p.name().equalsIgnoreCase(place.name())) {
                System.out.println("Found duplicate: " + place);
                return;
            }
        }

/*
Part-10
        Here, we've got an enhanced for loop, with a local variable p, which is a Place record. We compare each record in
    the list, with the one we want added, comparing the name field values on each record. The implicit accessor is not
    getName but just name on records. And we print "Found duplicate:", and return from the method if we find a match. If
    I run this,

                    Found duplicate: adelaide (1374)
                    [Adelaide (1374)]

    we get Found duplicate, adelaide 1374, and the list still only has 1 record. So that's good. Now we handled duplicates,
    I want this method to add each new place, in order by the distance to Sydney. We want the closest place to be first,
    and the farthest place to be last. I'm going to use a simple enhanced for loop for this.
End-Part-10
*/

        int matchedIndex = 0;
        for (var listPlace : list) {
            if (place.distance() < listPlace.distance()) {
                list.add(matchedIndex, place);
                return;
            }
            matchedIndex++;
        }

/*
Part-11
        First we create an index variable, which keeps track of what element is being processed, what index position, and
    we know we want to start at 0. In the loop, we increment the matchedIndex variable, in each iteration. Now, I'll add
    the code, to compare distance between the record we're adding, and the record we process in the loop. Add the comparison.

                    if (place.distance() < listPlace.distance()) {
                        list.add(matchedIndex, place);
                        return;
                    }

    we use the accessor method, distance, and compare that to the loop variable, listPlace.distance. If the new record is
    closer to Sydney, we'll add it, using the add method that takes an index, and pass it the matchedIndex. Then we'll
    return from this method, once we've added the element. If we get to the end of the loop, we know we still need to add
    the element, so we'll add it to the end of the list. Now, we could have used a ListIterator, and it could be argued,
    that might've been a bit more efficient. This is because an indexed add, for a LinkedList, has to loop through the
    elements again, to find that index, and place that element. But when you're dealing with just a few elements, as we
    are here, sometimes it's preferable to use code that's just simpler to use and read, like this. Let's get back to the
    main method, and add all of our places. I'll add these in no particular order.
End-Part-11
*/

        list.add(place);
/*
Part-5
        This code right now, just calls the add method, but we don't want to add the place, if it's already there. Let's
    add the code that checks for that. Does list contain the place?
End-Part-5
*/
    }

    private static void printMenu() {

        System.out.println("""
                Available actions (select word or letter):
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit""");

/*
Part-13
        This code is demonstrating, that we can pass a text block, directly to println statement. What's nice about using
    a text block, is we can actually just copy that data we showed you, and paste it, just the way it was on the challenge's
    info. You've seen me do something similar before. Remember, we have to keep the first """ on a separate line from the
    rest of the text block. For the last """, we don't have to do that, though we're doing that here. Ok, so this is our
    printMenu method. Now, let's just go back to the main method, and I'll set up the Scanner, and a few other local variables.
End-Part-13
*/
    }
}
