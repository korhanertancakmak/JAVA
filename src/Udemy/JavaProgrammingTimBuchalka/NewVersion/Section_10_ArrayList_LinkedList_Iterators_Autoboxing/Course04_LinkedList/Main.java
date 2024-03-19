package CourseCodes.NewSections.Section_10_ArrayList_LinkedList_Iterators_Autoboxing.Course04_LinkedList;

import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {

/*
Part-1
                                                LinkedList

        I've just talked a lot about how the LinkedList, and the ArrayList, are different under the covers. An ArrayList
    is implemented on top of an array, but a LinkedList is a doubly linked list. Both implement all of List's methods, but
    the LinkedList also implements the Queue and Stack methods as well.

                            A Queue is a First-In, First-Out (FIFO) Data Collection

        When you think of a queue, you might think of standing in line. When you get in a line or a queue, you expect that
    you'll be processed, in relationship to the first person in line. We call this a First-in First-out, or FIFO data
    collection.

                         |   POLL    |
                               ↑     (REMOVES FROM HEAD)
                               ↑
     (Queue Starts here) |   First   |      |  Element   |      |  Element   |      |   Last   |
                         |  Element  |      |            |      |            |      |  Element |    (End of Queue)
                                                                                          ↑
                                                                                          ↑     (INSERTS AT TAIL)
                                                                                    |   OFFER    |

    If you want to remove an item, you poll the queue, getting the first element or person in the line. If you want to add
    an item, you offer it onto the queue, sending it to the back of the line. Single-ended queues always process elements
    from the start of the queue. A double-ended queue allows access to both the start and end of the queue. A LinkedList
    can be used as a double ended queue.

                            A Stack is a Last-In, First-Out (LIFO) Data Collection

        When you think of a stack, you can think of a vertical pile of elements, one on top of another, as we show on this
    diagram.

                              INSERTS AT TOP                     REMOVES FROM TOP
               |   PUSH    |      →→→→          |   Element   |      →→→→          |   POP    |
                                OF STACK        |   Element   |    OF STACK
                                                |   Element   |
                                                |   Element   |

    When you add an item, you push it onto the stack. If you want to get an item, you'll take the top item, or pop it from
    the stack. We call this a Last-In First-out, or LIFO data collection. A LinkedList can be used as a stack as well.
    Let's start using this new class in some code.
End-Part-1
*/

        LinkedList<String> placesToVisit = new LinkedList<>();

/*
Part-2
        The first thing I'll do is create a LinkedList variable called placesToVisit. Like an ArrayList, we include the
    type parameter, String, in <>, in the declaration. And we use the <> operator on the right side of the assignment
    operator. This is one way to declare a LinkedList. I want to take an extra minute here to talk to you about Lists,
    and the "var" keyword. Now I'll use the "var" keyword instead to set up this LinkedList.
End-Part-2
*/
        var placesToVisit2 = new LinkedList<String>();
/*
Part-3
        Creating new LinkedList of strings and assigning to our placesToVisit, what I wanted to show you here is that we
    can use "var" keyword for type LinkedList(or any type list or collection). But when we do, we have to specify the type
    parameter on the right side of the assignment operator in <>. We can't just put a diamond operator there as we did on
    the line above. Using the <> operator without a type in this statement doesn't give Java enough information to infer
    the type. So in this case, the type isn't optional on the right side of the assignment. Now, adding records to a
    LinkedList is a lot like adding records to an ArrayList, so let's add a few places we might want to visit in Australia.
    First I'll add Sydney, then I'll add Canberra at index 0.
End-Part-3
*/

        placesToVisit2.add("Sydney");
        placesToVisit2.add(0,"Canberra");
        System.out.println(placesToVisit2);

/*
Part-4
        Like an ArrayList, we can use the "add" method, passing it just an element, which appends the element, to the end
    of the list. We next use the overwritten method for add, which takes an index, again like the ArrayList. This inserts
    "Canberra" at position 0 of the list. Running this code,

                [Canberra, Sydney]

    We confirm that our list has "Canberra" first, then "Sydney". Now because the LinkedList implements methods on the
    deck interface, we have several other functions, to add elements. I'm going to create a method called addMoreElements,
    to demonstrate some of these. I'll make the return type void, and add a parameter of a LinkedList of strings.
End-Part-4
*/

        addMoreElements(placesToVisit2);
        System.out.println(placesToVisit2);

/*
Part-6
        Running this code,

                [Canberra, Sydney]
                [Darwin, Canberra, Sydney, Hobart]

    we can confirm that "Darwin" was added at the start and how about at the end of the list. In addition to these methods,
    LinkedList has a method called offer, which queue language if you will, for adding an element to the end of the queue.
    Let's add that to the addMoreElements method.
End-Part-6
*/

        //removeElements(placesToVisit2);
        //System.out.println(placesToVisit2);

/*
Part-12
        Running that,

                [Canberra, Sydney]
                [Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
                [Alice Springs, Darwin, Canberra, Hobart, Melbourne, Toowoomba]

    we can confirm that we removed "Brisbane" and "Sydney". LinkedList has additional methods to remove elements, that
    aren't on ArrayList. One of these is a no argument remove method. Let's add that to removeElements method.
End-Part-12
*/

        //gettingElements(placesToVisit2);

/*
Part-19
        First, we'll comment out the call to the removeElements method. And running this code,

                [Canberra, Sydney]
                [Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
                Retrieved Element = Sydney

    we can see we retrieved the element at index 4 or the 5th City in our list, Sydney. I've said this as a method on both
    ArrayList and LinkedList, however on the ArrayList the Big O Notation is O(1) at its worst, whereas for a LinkedList,
    the worst case is O(n). It's actually not as bad as that on a LinkedList. Since it's a double-ended queue, Java will
    decide where to start searching. The retrieval will start moving from one link to the next, either from the start or
    the end of the list, whichever is closer to the specified index. So it would never traverse the entire number of elements.
    In addition to get method, the LinedList has the getFirst, and getLast methods. I'll print out the first and last
    elements using getFirst and getLast. Going back to the gettingElements method,
End-Part-19
*/

        //printItinerary(placesToVisit2);
        //printItinerary2(placesToVisit2);        // after part-27
        printItinerary3(placesToVisit2);          // after part-28

/*
Part-25
        I'll first comment out the call to the gettingElements method. Running this code, we should see our list of places
    and then that the trip starts at Alice Springs and ends at Toowoomba. Now let's include a loop to print the places in
    between, and we'll want to include an entry for each item, except the starting and ending points. Every line item will
    include where we started and where we ended up. Going back to printItenirary method,
End-Part-25
*/
    }

    private static void addMoreElements(LinkedList<String> list) {
/*
Part-5
        I'll then add "Darwin" at the start of the list. And then "Hobart" at the end. These methods that are on the LinkedList
    class, but not on an ArrayList. The first statement adds "Darwin", inserting it at the start of the list. The next
    statement adds "Hobart", appending it to the end of the list. Let's call the addMoreElements from the main method and
    print out the list.
End-Part-5
*/
        list.addFirst("Darwin");
        list.addLast("Hobart");
        // Queue methods
        list.offer("Melbourne");
/*
Part-7
        I'll call offer passing the string literal "Melbourne". And running that,

                [Canberra, Sydney]
                [Darwin, Canberra, Sydney, Hobart, Melbourne]

    we see "Melbourne" now at the end of the list or queue. The "offerLast" last method does the same thing as the offer
    method. We also have an "offerFirst" method that does what the addFirst method does, so let's add these next.
End-Part-7
*/

        list.offerFirst("Brisbane");
        list.offerLast("Toowoomba");

/*
Part-8
        Running this,

                [Canberra, Sydney]
                [Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]

    we see "Brisbane" as our first element and "Toowoomba" as the last. Continuing on, there is one more method for adding
    an element. If you're familiar with stack language you might know that when you want to add something to a stack, you
    push it onto the stack. We also have a "push" method. The top of the stack is the first element in the LinkedList, and
    if you're using LinkedList as a stack, pushing will insert the element at the start. I'll add that method,
End-Part-8
*/

        // Stack Methods
        list.push("Alice Springs");

/*
Part-9
        Running that,

                [Canberra, Sydney]
                [Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]

    we see that the push method placed "Alice Springs" at the head, or beginning of the list. So you can see the LinkedList
    can be used as different types of data collections a list, a queue, a double-ended queue, and a stack. Like the many
    ways to add an element, there are also many ways to remove an item from a LinkedList. We'll put these in a separate
    method called removeElements.
End-Part-9
*/
    }

    private static void removeElements(LinkedList<String> list) {
/*
Part-10
        I'll remove the element at index 4. And remove "Brisbane".
End-Part-10
*/
        list.remove(4);
        list.remove("Brisbane");

/*
Part-11
        Here, we're simply calling the remove methods that we also saw on the ArrayList. The first statement removes the
    5th element in the list which will be Sydney and the next removes "Brisbane" from the list. Let's add a call to the
    removeElements method in the main method, and print the list.
End-Part-11
*/

        System.out.println(list);
        String s1 = list.remove();  // removes first element
        System.out.println(s1 + " was removed");

/*
Part-13
        So, we call remove again, but we aren't passing any arguments to this version of the method. This method removes
    the first element in the list, and returns the removed element. We assign that to a variable S1 and print that out.

                [Canberra, Sydney]
                [Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
                [Alice Springs, Darwin, Canberra, Hobart, Melbourne, Toowoomba]
                Alice Springs was removed
                [Darwin, Canberra, Hobart, Melbourne, Toowoomba]

    And we can confirm that "Alice Springs" was removed from the list, which was the first item in our list.
End-Part-13
*/

        String s2 = list.removeFirst();  // removes first element
        System.out.println(s2 + " was removed");

/*
Part-14
        Running this,

                [Canberra, Sydney]
                [Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
                [Alice Springs, Darwin, Canberra, Hobart, Melbourne, Toowoomba]
                Alice Springs was removed
                Darwin was removed
                [Canberra, Hobart, Melbourne, Toowoomba]

    we confirm that removeFirst, is the same as calling remove, but might be a bit clearer to anyone reading our code. And
    there's also a removeLast method.
End-Part-14
*/

        String s3 = list.removeLast();  // removes last element
        System.out.println(s3 + " was removed");

/*
Part-15
        Running this,

                [Canberra, Sydney]
                [Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
                [Alice Springs, Darwin, Canberra, Hobart, Melbourne, Toowoomba]
                Alice Springs was removed
                Darwin was removed
                Toowoomba was removed
                [Canberra, Hobart, Melbourne]

    we see "Toowoomba", the last element, gets removed this time. And like the additional queue methods offer, offerFirst
    and offerLast for adding an element, we have the poll, pollFirst and pollLast methods to remove an element. Let's add
    these, starting with Poll.
End-Part-15
*/

        // Queue/Deque poll methods
        String p1 = list.poll();             // removes first element
        System.out.println(p1 + " was removed");

        String p2 = list.pollFirst();        // removes first element
        System.out.println(p2 + " was removed");

        String p3 = list.pollLast();         // removes last element
        System.out.println(p3 + " was removed");

/*
Part-16
        First, we create a local variable, a String named P1 and assign it the result of the poll method, which removes
    the first element from the list. And we simply print that variable out. And I added the rest of poll methods. Running
    this code,

                .... (same)
                Canberra was removed
                Hobart was removed
                Melbourne was removed
                []

    we see "Canberra" was removed first then "Hobart" and finally, "Melbourne" which leaves our list empty. Let's add a
    couple of elements back to our list before we test one more method. Let's use the push method here another stack method,
    that pushes the item to the top of the stack, the start of the list. You can imagine, if you move that push, it's
    pushing all the elements back in the pile, or downwards in the stack. I'll add Sydney, Brisbane, and Canberra back
    into the list.
End-Part-16
*/

        list.push("Sydney");
        list.push("Brisbane");
        list.push("Canberra");
        System.out.println(list);

        String p4 = list.pop();         // removes first element
        System.out.println(p4 + " was removed");

/*
Part-17
        We'll test the stack method for removing an element, which is the pop method. I'll remove the first element and
    assign it to P4 then I'll print it out. So let's run this code.

                .... (same)
                [Canberra, Brisbane, Sydney]
                Canberra was removed
                [Brisbane, Sydney]

    And we can confirm "Canberra" gets removed using the pop method. Using the push methods, it was the last element in,
    and with pop, it was the first element out.

        Now, I want to review the different ways, to retrieve an element from a LinkedList. I'll add another method to
    this class. I'll call it getting elements static return type void,
End-Part-17
*/
    }

    private static void gettingElements(LinkedList<String> list) {

        System.out.println("Retrieved Element = " + list.get(4));

/*
Part-18
        This method simply retrieves the 5th element in the list, and prints that out. Let's add a call to this method in
    the main method.
End-Part-18
*/

        System.out.println("First element = " + list.getFirst());
        System.out.println("Last element = " + list.getLast());

/*
Part-20
        Here, we're calling each method, and printing the result in the println statement. So no surprises here when I
    run it,

                [Canberra, Sydney]
                [Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
                Retrieved Element = Sydney
                First element = Alice Springs
                Last element = Toowoomba

    we get "Alice Springs" from the getFirst method and "Toowoomba" from the getLast method. We can also use the methods
    indexOf and lastIndexOf, to see if an element is in a list, as we did with the ArrayList.
End-Part-20
*/

        System.out.println("Darwin is at position: " + list.indexOf("Darwin"));
        System.out.println("Melbourne is at posiiton = " +
                list.lastIndexOf("Melbourne"));

/*
Part-21
        The Big O Notation is the same for these methods, as it is for the ArrayList. The worst case is O(n) for both, if
    the element retrieved is the element of the last position to be checked. Running this code tells us the positions of
    these towns in our list.

                [Canberra, Sydney]
                [Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
                Retrieved Element = Sydney
                First element = Alice Springs
                Last element = Toowoomba
                Darwin is at position: 2
                Melbourne is at posiiton = 6

    So "Darwin" is at position 2, and "Melbourne" is at 6. As you'd expect, LinkedList has additional methods for retrieving
    an element. The first is appropriately named element, which is a Queue method.
End-Part-21
*/

        // Queue retrieval method
        System.out.println("Element from element() = " + list.element());

/*
Part-22
        This "element" method takes no arguments. So what element does it return? Remember I've said it's a queue method
    and a queue is first in first out. So this should indicate that an element gets the first item out of the list. Running
    this,

                [Canberra, Sydney]
                [Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
                Retrieved Element = Sydney
                First element = Alice Springs
                Last element = Toowoomba
                Darwin is at position: 2
                Melbourne is at posiiton = 6
                Element from element() = Alice Springs

    we can confirm that because we get "Alice Springs". Now let's add the stack retrieval methods which are peek, and similar
    methods like peekFirst and peekLast.
End-Part-22
*/

        // Stack retrieval method
        System.out.println("Element from peek() = " + list.peek());
        System.out.println("Element from peekFirst() = " + list.peekFirst());
        System.out.println("Element from peekLast() = " + list.peekLast());

/*
Part-23
        Running this,

                .... (same)
                Element from peek() = Alice Springs
                Element from peekFirst() = Alice Springs
                Element from peekLast() = Toowoomba

    we can see that peek and peekFirst, get the first element of the list, and peekLast gets the last element. Now, let's
    switch gears and talk about traversing and manipulating the elements in the list. First, let's use a simple for loop.
    We'll say this list of towns is our itinerary, and we plan to travel to each list in the order we've defined here. I'll
    call the method printitinerary and pass it a LinkedList of string.
End-Part-23
*/
    }

    public static void printItinerary(LinkedList<String> list) {

        //System.out.println("Trip starts at " + list.getFirst());
        //System.out.println("Trip ends at " + list.getLast());

/*
Part-24
        This method uses the getFirst and getLast methods to print out the starting and ending points of our trip. And
    I'll add a call to this from the main method.
End-Part-24
*/

        System.out.println("Trip starts at " + list.getFirst());
        for (int i = 1; i < list.size(); i++) {
            System.out.println("--> From: " + list.get(i - 1) + " to " + list.get(i));
        }
        System.out.println("Trip ends at " + list.getLast());

/*
Part-26
        So this for loop, starts at i = 1, because we don't want to include an entry for the first time we started in, since
    we've already printed that out. And then I'll print the item we started at using a minus one as the index to get that
    down and then get the town at the current index. Running this code,

                    Trip starts at Alice Springs
                    --> From: Alice Springs to Brisbane
                    --> From: Brisbane to Darwin
                    --> From: Darwin to Canberra
                    --> From: Canberra to Sydney
                    --> From: Sydney to Hobart
                    --> From: Hobart to Melbourne
                    --> From: Melbourne to Toowoomba
                    Trip ends at Toowoomba

    We get a decent itinerary. I've said that indexing into a LinkedList isn't the most efficient way to access elements.
    In the for loop, we're accessing 2 elements by index for each iteration of the loop. Let's try another method this
    time using the for each loop. I'm going to copy this method and rename a new method as printItinerary2,
End-Part-26
*/
    }

    public static void printItinerary2(LinkedList<String> list) {

        System.out.println("Trip starts at " + list.getFirst());
        String previousTown = list.getFirst();
        for (String town :list) {
            System.out.println("--> From: " + previousTown + " to " + town);
            previousTown = town;
        }
        System.out.println("Trip ends at " + list.getLast());
/*
Part-27
        So first, we set up the previousTown, to be the first place in our list. Then, we set up the for each loop, using
    a String variable named town, and the second component of this declaration is the list then we'll print out the previous
    town and the current town. Next, we set previousTown to the current iterations town variable, and that's it. I'll call
    this method now in the main method instead of printitinerary. I'll make that printItinerary2. And running that,

                    Trip starts at Alice Springs
                    --> From: Alice Springs to Alice Springs
                    --> From: Alice Springs to Brisbane
                    --> From: Brisbane to Darwin
                    --> From: Darwin to Canberra
                    --> From: Canberra to Sydney
                    --> From: Sydney to Hobart
                    --> From: Hobart to Melbourne
                    --> From: Melbourne to Toowoomba
                    Trip ends at Toowoomba

    So this is a bit more efficient, but it also prints out from Alice Springs, to Alice Springs, on that second line.
    Using this loop limits us, to looping through the elements, one at a time, from the very first to the last. The traditional
    for loop is much more flexible if we don't want to loop through every element one at a time. But now, let's look at
    another way to do this without using either of the for loops. I'll copy and paste this method and rename it printItenirary3,
End-Part-27
*/
    }

    public static void printItinerary3(LinkedList<String> list) {

        System.out.println("Trip starts at " + list.getFirst());
        String previousTown = list.getFirst();
        ListIterator<String> iterator = list.listIterator(1);
        while (iterator.hasNext()) {
            var town = iterator.next();
            System.out.println("--> From: " + previousTown + " to " + town);
            previousTown = town;
        }
        System.out.println("Trip ends at " + list.getLast());

/*
Part-28
        Here, I've made 3 changes. I've set up a local variable with a type of list iterator with type string. And I've
    named it iterator, and assigned it to a method on LinkedList, listIterator. After this, I change the for loop to a
    while loop. And we're using a method on ListIterator called hasNext(), which will return true, if there are more elements
    to process. After this, we have a local loop variable which we call in town again. And we're setting this to the result
    of another method on the ListIterator, called next(). And instaed of calling print itinerary2, I'll call printItinerary3.
    And running this,

                    Trip starts at Alice Springs
                    --> From: Alice Springs to Alice Springs
                    --> From: Alice Springs to Brisbane
                    --> From: Brisbane to Darwin
                    --> From: Darwin to Canberra
                    --> From: Canberra to Sydney
                    --> From: Sydney to Hobart
                    --> From: Hobart to Melbourne
                    --> From: Melbourne to Toowoomba
                    Trip ends at Toowoomba

    we get the same output as before including the problematic first line that we don't really want. Let's make one change
    to this printItinerary3 method. When we make that call to the method list iterator on the linked list, we'll call an
    overloaded version, passing the index of the first element we want to process,

                    ListIterator<String> iterator = list.listIterator();
                                             ↓
                    ListIterator<String> iterator = list.listIterator(1);

    and now we'll run this again,

                    Trip starts at Alice Springs
                    --> From: Alice Springs to Brisbane
                    --> From: Brisbane to Darwin
                    --> From: Darwin to Canberra
                    --> From: Canberra to Sydney
                    --> From: Sydney to Hobart
                    --> From: Hobart to Melbourne
                    --> From: Melbourne to Toowoomba
                    Trip ends at Toowoomba

    And you'll notice that, we don't have that problematic first line because this particular method gets an iterator, that
    isn't set before the first element but is set before the second element in between index 0 and 1. By now, you must be
    asking what's an iterator or what's a ListIterator to be more specific.
End-Part-28
*/
    }
}
