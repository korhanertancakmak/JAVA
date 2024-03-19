package CourseCodes.NewSections.Section_15_Collections.Course14_LinkedHashSetAndTreeSet;

import java.util.*;

public class TreeSetMain {

    public static void main(String[] args) {

//Part-3
/*
        I want a main method on this class, so I'll type in the short cut "psvm". Next, I want to get the phone and email
    lists from the ContactData class. I'll set up two local variables, both lists. The phones variable will get assigned
    the result of calling get data on ContactData, passing phone as the type. Emails get set the same way, but this time
    I pass email to the getData method as the type. If I want the additional functionality of the SortedSet or NavigableSet,
    then I need to declare my Set as one of these types. I'm going to start out using the NavigableSet as my reference
    type, the most specific of the interfaces, because I want to cover all the methods unique to both the SortedSet and
    NavigableSet. I'll call this variable sorted, and assign a new TreeSet, passing it my phones list. And I'll print the
    elements in that set out. This code compiles, but if I run it, I get a familiar error, that Contact cannot be cast
    to class Comparable. This exception confirms what I stated before, that elements must implement Comparable. I can get
    around this requirement, if I pass a Comparator to the constructor, defining the sort, which I'll do next. Now, the
    constructor that takes a Comparator, is a single argument constructor, so I'll create that, then add my phone contacts.
    I'll comment out that first statement. Right below that, and before the for each statement, I'll add a couple of lines.
    First I'll create a comparator called my sort. I'll set that to the result of calling the comparing method on comparator,
    using the method reference contact::getMame. I'll again create a variable, with the type Navigable Set, type argument
    Contact, name is sorted, and assign a new Tree set to that, but this time, I'm passing the mySort variable to it. I'll
    next use the add all method, to add the phone contacts. Ok, let me run that,

                Charlie Brown: [] [(333) 444-5555]
                Lucy Van Pelt: [] [(564) 208-6852]
                Maid Marion: [] [(123) 456-7890]
                Mickey Mouse: [] [(999) 888-7777]
                Minnie Mouse: [] [(456) 780-5666]
                Robin Hood: [] [(564) 789-3000]

    and now you can see my phone contacts, in name order, and without duplicates. Since we know Strings have a natural sort
    order, I'll create a TreeSet of those next. I'm using the no args constructor in this case. And I'll loop through my
    phone contacts, adding each name of the contact to my set of strings. And I can print that out. Running this code works,

                [Charlie Brown, Lucy Van Pelt, Maid Marion, Mickey Mouse, Minnie Mouse, Robin Hood]

    and my set of just names is sorted. I can also pass in a sorted set to the TreeSet constructor, and since I already
    have one set up, sorted, I'll pass that.
*/
//End-Part-3

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

//        NavigableSet<Contact> sorted = new TreeSet<>(phones);
        Comparator<Contact> mySort = Comparator.comparing(Contact::getName);
        NavigableSet<Contact> sorted = new TreeSet<>(mySort);
        sorted.addAll(phones);
        sorted.forEach(System.out::println);

//Part-4
/*
        Here, I'll create a new set, another NavigableSet, called full set, and assign that a new TreeSet, passing my
    sorted set to the constructor. I'll add the emails list, passing them to the add all method on this set. And I'll
    print the elements out in the full set. This compiles and runs,

                Charlie Brown: [] [(333) 444-5555]
                Daffy Duck: [daffy@google.com] []
                Linus Van Pelt: [lvpelt2015@gmail.com] []
                Lucy Van Pelt: [] [(564) 208-6852]
                Maid Marion: [] [(123) 456-7890]
                Mickey Mouse: [] [(999) 888-7777]
                Minnie Mouse: [] [(456) 780-5666]
                Robin Hood: [] [(564) 789-3000]

    and shows that I now have a combined list of contacts, in alphabetical order. In this case, the sort was determined
    by the sort mechanism of the TreeSet passed to the constructor. In fact, there's a method on the SortedSet interface,
    that returns the comparator used in the set.
*/
//End-Part-4

        NavigableSet<String> justNames = new TreeSet<>();
        phones.forEach(c -> justNames.add(c.getName()));
        System.out.println(justNames);

        NavigableSet<Contact> fullSet = new TreeSet<>(sorted);
        fullSet.addAll(emails);
        fullSet.forEach(System.out::println);

//Part-5
/*
        I'll create a List, full List, assigning that a new array list passing it my phones list. I'll add the emails to
    the full list, using add all. Now, I want to sort the same way the tree set is sorted, by passing the result of calling
    the comparator method on the sorted set. I'll print a separator line. And then I'll print my combined list. And running
    this code,

                        --------------------------
                        Charlie Brown: [] [(333) 444-5555]
                        Daffy Duck: [daffy@google.com] []
                        Linus Van Pelt: [lvpelt2015@gmail.com] []
                        Lucy Van Pelt: [] [(564) 208-6852]
                        Maid Marion: [] [(123) 456-7890]
                        Mickey Mouse: [] [(999) 888-7777]
                        Mickey Mouse: [] [(124) 748-9758]
                        Mickey Mouse: [] [(999) 888-7777]
                        Mickey Mouse: [mckmouse@gmail.com] []
                        Mickey Mouse: [micky1@aws.com] []
                        Minnie Mouse: [] [(456) 780-5666]
                        Minnie Mouse: [minnie@verizon.net] []
                        Robin Hood: [] [(564) 789-3000]
                        Robin Hood: [] [(789) 902-8222]
                        Robin Hood: [rhood@gmail.com] []

    notice that this list, contains duplicates, but I was able to sort in the same way as the set, alphabetically by the
    name. Next, I want to show you the first, last, poll first and poll last methods on this class. But before I do that,
    I also want to get the minimum and maximum values of this set.
*/
//End-Part-5

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.sort(sorted.comparator());
        System.out.println("--------------------------");
        fullList.forEach(System.out::println);

//Part-6
/*
        You may remember there is a min, and max method on java.util.Collections, so I'll try to use those first, passing
    the fullSet to those methods. First, I'll call Collections dot min, with fullSet. Then I'll do the same for max. You
    can see this code doesn't compile, when I try to use this version of the min and max methods, because I still haven't
    implemented Comparable on Contact. But there are overloaded versions of these methods, that let me pass a comparator,
    and I can actually get this from the set itself, as I showed you earlier. I'll add fullSet dot comparator in both of
    those method calls. This code compiles and runs, and I'll print the results in just a minute. But actually the SortedSet
    interface includes first and last methods, and I'll demonstrate them next.
*/
//End-Part-6

        Contact min = Collections.min(fullSet, fullSet.comparator());
        Contact max = Collections.max(fullSet, fullSet.comparator());

//Part-7
/*
        I'll create two variables, both Contacts, and I'll set first to the result of calling the first method on full set.
    I'll call last on full set and assign that to my variable called last. These methods give me the same results as the
    methods on Collections, and are the preferred way to get this data, but I did want you to see the alternative, which
    you might again see in legacy code. I'll print these out next, printing the name of the min value, then the name of
    the first variable. And I'll do that for the max, and last variables too. And I'll include another line of dashes.
    Running this code,

                --------------------------
                min = Charlie Brown, first=Charlie Brown
                max = Robin Hood, last=Robin Hood
                --------------------------
                First element = Charlie Brown: [] [(333) 444-5555]
                Last element = Robin Hood: [] [(564) 789-3000]

    you can see both elements are the same, meaning min and first get the same element, and max and last also get the same
    element. In addition to first and last methods, there's also the pollFirst, and pollLast methods.
*/
//End-Part-7

        Contact first = fullSet.first();
        Contact last = fullSet.last();

        System.out.println("--------------------------");
        System.out.printf("min = %s, first=%s %n", min.getName(), first.getName());
        System.out.printf("max = %s, last=%s %n", max.getName(), last.getName());
        System.out.println("--------------------------");

//Part-8
/*
        These remove the first or last sorted element from the set, and the methods return the removed element. To test
    this, I first want to create a copy of the set, so I'll create a new NavigableSet variable, called copied set, setting
    that to a new Tree set with the full set passed to the constructor. I'll print out what I get back, from executing
    poll First, and do the same for pollLast. Then I'll print all the set elements, and a separator line. Running this
    code,

                First element = Charlie Brown: [] [(333) 444-5555]
                Last element = Robin Hood: [] [(564) 789-3000]
                Daffy Duck: [daffy@google.com] []
                Linus Van Pelt: [lvpelt2015@gmail.com] []
                Lucy Van Pelt: [] [(564) 208-6852]
                Maid Marion: [] [(123) 456-7890]
                Mickey Mouse: [] [(999) 888-7777]
                Minnie Mouse: [] [(456) 780-5666]
                --------------------------

    you can see I get the same result as I did with the first and last methods, but with one very important difference.
    The elements are first removed from the set, which you can see here, when I print out the elements in the copied set.
    Charlie Brown and Robin Hood are no longer in the set. Ok, this feels like a good place to end this lecture. I've
    introduced you to the Tree Set, which implements both the Sorted Set and the Navigable Set. This set has first and
    last methods which retrieve the first sorted element, and the last sorted element. The pollFirst and pollLast methods
    do the same thing, but in addition, they remove the element from the set. In the next lecture, I'll be covering
    additional methods unique to the navigable set as well as a few on the sorted set interface.
*/
//End-Part-8

        NavigableSet<Contact> copiedSet = new TreeSet<>(fullSet);
        System.out.println("First element = " + copiedSet.pollFirst());
        System.out.println("Last element = " + copiedSet.pollLast());
        copiedSet.forEach(System.out::println);
        System.out.println("--------------------------");

    }
}
