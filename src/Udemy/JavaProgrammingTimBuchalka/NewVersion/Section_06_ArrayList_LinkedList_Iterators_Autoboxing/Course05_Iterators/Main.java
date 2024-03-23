package CourseCodes.NewSections.Section_10_ArrayList_LinkedList_Iterators_Autoboxing.Course05_Iterators;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

/*
Part-1
                                                    Iterators

        So far, we've mainly used for loops to traverse, or step through elements, in an array or list. We can use the
    traditional for loop and an index, to index into a list. We can use the enhanced for loop and a collection, to step
    through the elements, one at a time. But Java provides other means to traverse lists. Two alternatives are the Iterator,
    and the ListIterator.

        If you're familiar with databases, you might be familiar with a database cursor, which is a mechanism that enables
    traversal, over records in a database. An iterator can be thought of as similar to a database cursor. The kind of cursor
    we're referring to here, can be described as an object, that allows traversal over records in a collection.

        The Iterator is pretty simple. When you get an instance of an iterator, you can call the "next" method, to get the
    next element in the list. You can use the "hasNext" method, to check if any elements remain to be processed. In the code,
    you can see a while loop, which uses the iterators "hasNext" method, to determine if it should continue looping. In the
    loop, the "next" method is called, and its value assigned to a local variable, and the local variable printed out. This
    would just print each element in a list, but do it through the iterator object.

                                        Cursor Position At Start      <<<<<<<<------------------------
         next() ------->>>|                  Alice Springs                  |                        ↑
                                   Cursor Position after first next() <<<<<<<<-----------------------↑
         next() ------->>>|                    Adelaide                     |                        ↑
                                   Cursor Position after second next()                      Iterator (Cursor)
         next() ------->>>|                  Alice Springs                  |                        ↓
                                   Cursor Position after third next() <<<<<<<<-----------------------↓
         next() ------->>>|                  Alice Springs                  |                        ↓
                                   Cursor Position after last next()  <<<<<<<<------------------------
                                            hasNext() = false

        This diagram shows visually how an Iterator works, using the PlacesToVisit List. When an iterator is created, its
    cursor position is pointed at a position "before" the first element. The first call to the "next" method gets the first
    element, and moves the cursor position, to be between the first and second elements. Subsequent calls to the "next"
    method moves the position of the iterator through the list, as shown, until there are "no elements left", meaning
    hasNext = "false". At this point, the iterator or cursor position is below the last element. We looked at it briefly
    in code in the last course, but I want to explore this type with you a bit more.
End-Part-1
*/

        var placesToVisit = new LinkedList<String>();
        placesToVisit.add("Sydney");
        placesToVisit.add(0,"Canberra");
        addMoreElements(placesToVisit);

/*
Part-2
        Copying and pasting the code parts from the last course, which we need for this one. I'll go on with a new method
    in our Main class, called testIterator.
End-Part-2
*/

        testIterator(placesToVisit);

/*
Part-5
        And now add the call to testIterator, passing it placesToVisit. And if I run that,

                    Alice Springs
                    Brisbane
                    Darwin
                    Canberra
                    Sydney
                    Hobart
                    Melbourne
                    Toowoomba
                    [Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]

    we first get our places printed out one by one, and then the full list. One of the benefits of an iterator, is that
    you can use it to modify the list, while you're iterating through it. Let's say we wanted to remove all instances of
    Brisbane from our list, and our list could contain duplicates. I'll add that code to the test Iterator method,
End-Part-5
*/
    }

    private static void addMoreElements(LinkedList<String> list) {
        list.addFirst("Darwin");
        list.addLast("Hobart");
        // Queue methods
        list.offer("Melbourne");
        list.offerFirst("Brisbane");
        list.offerLast("Toowoomba");
        list.push("Alice Springs");
    }

    private static void testIterator(LinkedList<String> list) {

/*
Part-3
        I'll create a variable, containing our list iterator. And loop through it using hasNext. And loop through it using
    hasNext. Let's print out the element, returned by the next method. And then print out the list.
End-Part-3
*/
        var iterator = list.listIterator();                 // changed at Part-6 from "var iterator = list.iterator();"
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//        System.out.println(list);

/*
Part-4
        Here, I have created an iterator variable, assigning it the result of the list.iterator method. I have a while
    loop, that checks the iterator.hasNext method, which returns true, if the cursor position is before another item to
    process. And I'm calling iterator.next, and passing the result directly to the println statement, so these will print
    every element in the list. Back to the main method,
End-Part-4
*/

        while (iterator.hasNext()) {
            if (iterator.next().equals("Brisbane")) {
                //iterator.remove();                   at the end of part-6, marked as comment
                //list.remove();
                iterator.add("Lake Wivenhoe");
            }
        }

/*
Part-6
        Here, we just have an if condition, that checks if the value we get back from the next method equals Brisbane. If
    It does, we execute iterator.remove. Running that,

                [Alice Springs, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]

    you can see this works, and Brisbane was removed from the list. But what If I tried to remove Brisbane directly from
    the list at this point. I'll change iterator.remove to list.remove. If I run this,

                Exception in thread "main" java.util.ConcurrentModificationException

    we get a ConcurrentModificationException. You'd get this same error, if you tried to do something similar in an enhanced
    for loop. The iterator provides a safe way to remove elements, while still iterating through the list, so it's important,
    to make sure you're calling remove on the iterator object, and not the list object. I'll revert that last change. This
    type of iterator only allows us to move forward through the elements. This means we can only call the next method on
    this iterator instance. And the only method available for mutating elements in this iterator, is the remove method,
    which I just showed you. There is another iterator, the ListIterator, that gives us additional functionality.

                                                Iterator vs ListIterator

        An Iterator is forwards only, and only supports the "remove" method. A ListIterator can be used to go both forwards
    and backwards, and in addition to the "remove" method, it also supports the "add" and "set" methods. I'll make a minor
    change to our code. Instead of calling the iterator method, I'll change that to call the listIterator method on list,
    which gives us a ListIterator.

                var iterator = list.iterator();      to       var iterator = list.listiterator();

    And running this,

                [Alice Springs, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]

    we get the exact same results. But now, I can change remove to add, adding a new place to visit after Brisbane. Instead
    of removing Brisbane, this code "iterator.add("Lake Wivenhoe");" adds "Lake Wivenhoe", to our places to visit, after
    Brisbane. Again, it's really important to note, we're using the add method on the ListIterator, and NOT on the list
    itself. And If we run it,

                [Alice Springs, Brisbane, Lake Wivenhoe, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]

    we can confirm that "Lake Wivenhoe" was added to the list, immediately following Brisbane. Now, after the while loop,
    what happens if I want to loop through the elements again? I'll add another while loop, just after the first, and
    loop through the elements again, just printing them out. We'll create a while loop, based on our iterators, hasNext
    method.
End-Part-6
*/

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(list);

/*
Part-7
        If I run this code,

                [Alice Springs, Brisbane, Lake Wivenhoe, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]

    we don't get an error, but we don't get any places printed out individually. And that's because hasNext, after that
    first while loop, is false, and the second loop never executes. If we wanted to loop through this iterator again, we
    couldn't use this same iterator instance, to move forward anymore, not in its current state. And we can't simply reset
    it to the beginning. We could get a new instance, by calling the listIterator method again, or we could move backwards.
    I'll try moving backwards here, so I'll change hasNext to hasPrevious in the while condition.
End-Part-7
*/

        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
        System.out.println(list);

/*
Part-8
        In this code, we're actually iterating backwards through the list, and printing each town. And If I run this,

                Toowoomba
                Melbourne
                Hobart
                Sydney
                Canberra
                Darwin
                Lake Wivenhoe
                Brisbane
                Alice Springs
                [Alice Springs, Brisbane, Lake Wivenhoe, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]

    you can see in the output, Toowoomba is printed first, and Alice Springs last. After running this, we're at the top
    of the cursor again, at the start of the list. Another thing we can do, is call the listIterator method, and pass the
    cursor position we want to start at. I demonstrated this in the last course, but I'll show it here again. I'll do this,
    creating a new iterator after the last statement.
    It's really important to understand that the positions of the cursor of iterator, are between the elements.
End-Part-8
*/

        var iterator2 = list.listIterator(3);
        //System.out.println(iterator2.next());
        System.out.println(iterator2.previous());

/*
Part-9
        This is a way to get an iterator, with the cursor positioned in some other place, other than the default, which
    is prior to the first element. We've specified position 3, so the cursor is placed between the elements at index 2
    and index 3. Running this,

                Toowoomba
                Melbourne
                Hobart
                Sydney
                Canberra
                Darwin
                Lake Wivenhoe
                Brisbane
                Alice Springs
                [Alice Springs, Brisbane, Lake Wivenhoe, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
                Darwin

    we get Darwin printed out. Let's change next to previous. And running that,

                ... (same)
                Lake Wivenhoe

    we get Lake Wivenhoe. It's really important to understand that the positions of the cursor of iterator, are between
    the elements.

                                          Iterator (Cursor) Positions
                                  _____________________________________________
                                  ↓                     ↓                     ↓
                                | 0 |                 | 1 |                 | 2 |
                                      |Alice Springs|        |Brisbane|             |Darwin|
                                           | 0 |                | 1 |                 | 2 |
                                             ↑                    ↑                     ↑
                                             ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
                                                        Element Positions

    When the iterator is at position 0, or the start, it's not pointing at element 0. The code shows an iterator for this
    list. We get Alice Springs when we first call iterator.next, and that moves the cursor of iterator to cursor position
    1, meaning after Alice Springs. Another call to iterator next returns Brisbane, and moves the cursor to cursor position
    2 or between Brisbane and Darwin. But if we decide to reverse positions, and call previous here, we get Brisbane,
    because the cursor position was 2 when we made this call. Traversing both backwards and forwards through a collection,
    using a listIterator, is a little tricky because of this. But if you remember that the cursor is always between elements,
    then you'll be able to keep it straight.
End-Part-9
*/
    }
}
