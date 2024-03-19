package CourseCodes.NewSections.Section_15_Collections.Course01_CollectionsFramework;

//Part-1
/*
                                                    Collections

        The List and Iterator interfaces, and the ArrayList and LinkedList classes belong to this framework. In this
    section, I'll revisit these types a little bit, to demonstrate how they fit into the big picture of this series of
    interfaces and classes. I'll also cover some other very important types of collection objects, such as sets and maps.
    By the end of this section, you'll have a few more classes in your tool box, for groups of many objects. Let’s get
    started.

        A "collection" is just an object that represents a group of objects. In general, the group of objects have some
    relationship to each other. Computer science has common names, and an expected set of behavior, for different types
    of collection objects. Collection objects, in various languages, include arrays, lists, vectors, sets, queues, tables,
    dictionaries, and maps(not geographical maps, but data maps). These are differentiated by the way they store the objects
    in memory, how objects are retrieved and ordered, and whether nulls and duplicate entries are permitted. Oracle's Java
    documentation describes it's collections framework as: "A unified architecture for representing and manipulating
    collections, enabling collections to be manipulated independently of implementation details.” That's a mouthful, but
    the term manipulated independently of implementation details should give you a hint that its based on interfaces. If
    you're interested in reading the Oracle documentation, they have a good overview at the following link

                    https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html

    This was written for JDK-8, but it still applies. Strictly speaking, arrays and the array utilities in the java.util.
    Arrays class are not considered part of this framework. All collection objects implement the Collection interface,
    with the exception of maps, and I'll explain why in this section.

                                                The Collection Interface

        The Collection interface is the root of the collection hierarchy. Like most roots in software hierarchies, it's
    an abstract representation of the behavior you'd need, for managing a group of objects. This type is typically used
    to "pass "collections" around, and manipulate them where "maximum generality" is desired. Remember, the interface
    let's us describe objects by what they can do, rather than what they really look like, or how they're ultimately
    constructed. If you look at the methods on this interface, you can see the basic operations a collection of any shape,
    or type, would need to support.

                                          _______________________________
                                          | <<Interface>>               |
                                          | Collection [Base Interface] |
                                          |_____________________________|
                                          |  add(E e)                   |
                                          |  addAll(Collection)         |
                                          |  clear()                    |
                                          |  contains(Object o)         |
                                          |  containsAll(Collection)    |
                                          |  iterator()                 |
                                          |  remove(Object o)           |
                                          |  removeAll(Collection)      |
                                          |  removeIf(Predicate)        |
                                          |  retainAll(Collection)      |
                                          |_____________________________|

    We've already looked at every one of these operations, in our study of ArrayLists and LinkedLists. When managing a
    group, you'll be adding and removing elements, checking if an element is in the group, and iterating through the elements.
    There are some others, but these are the ones that describe nearly everything you'd want to do to manage a group.
    Java uses the term "Element" for a member of the group being managed. Let's jump into IntelliJ and see what we can do
    with this interface.
*/
//End-Part-1


import java.util.*;

public class Main {

    public static void main(String[] args) {

//Part-2
/*
        First, I'll create an empty array list of type String and assign it to a list local variable. I'll then create
    an array of Strings, and I'll use an array initializer to assign the names. I'll add these strings, via a call to
    addAll, nesting a call to Arrays.asList. Print the list. This should be familiar ground for you. Running that:

            [Anna, Bob, Carol, David, Edna]

    I get all the names printed out.
*/
//End-Part-2

        //List<String> list = new ArrayList<>();

//Part-5
/*
        I can actually abstract my variable type further, to Collection. I can still add elements, and check if an element
    is in the collection. And all of my code compiles and runs, as before.
*/
//End-Part-5

        //Collection<String> list = new ArrayList<>();

//Part-6
/*
        Ok, this is nothing new or exciting, it's just how interfaces work. Now, let's change ArrayList to say TreeSet in
    that first statement. TreeSet is a different implementation, meaning it implements the Collection interface, and don't
    worry, I'll be talking about this class in detail coming up. Right now, I just want to swap out my collection type
    for another, that is significantly different from an ArrayList. And this code still runs, and the output is the same.
*/
//End-Part-6

        //Collection<String> list = new TreeSet<>();

//Part-7
/*
        Now, let's try a different implementation, HashSet. This compiles and runs too,

                [Edna, Bob, David, Carol, Anna]
                [Gary, Edna, Bob, George, Grace, David, Fred, Carol, Anna]
                Gary is in the list? true
                [Edna, Bob, David, Fred, Carol, Anna]
                Gary is in the list? false

    but the output is different, isn't it? The elements aren't ordered. Not all collections are implicitly ordered, by
    the way you add the elements, the insertion order in other words, like lists are. What if I wanted to sort this? Can
    I just call the sort method on list, as we did before? I'll add that statement, calling sort on my list variable
    right below the last println. But this doesn't compile, and if I hover over that, the error says, "cannot resolve
    'sort' in Collection". The Collection interface doesn't have a sort method. The List interface does, but the Collection
    interface is more abstracted than a list. Another thing to know about the Collection interface is that there aren't
    any direct implementations of this top level interface. Other interfaces are derived from it, and implementations
    (or concrete classes) implement the derived interfaces, like List and Set. Let's look at this on chart for an interface
    we know pretty well by now, List.

                                            _______________________________
                                            | <<Interface>>               |
                                            | Collection [Base Interface] |
                                            |_____________________________|
                                            |_____________________________|
                                                           ↑
                                       ____________________↑_____________________
                                       | <<Interface>>                          |
                                       | List    [Derived Interface]            |
                                       |________________________________________|
                                       |  add(int index, E element)             |
                                       |  get(int index)                        |
                                       |  indexOf(Object o)                     |
                                       |  lastIndexOf(Object o)                 |
                                       |  ListIterator<E> listIterator()        |
                                       |  of()                                  |
                                       |  remove(int index)                     |
                                       |  set(int index, E element)             |
                                       |  sort(Comparator)                      |
                                       |  subList(int fromIndex, int toIndex)   |
                                       |________________________________________|

    This chart shows the List interface extending Collection. For simplicity, I'm not showing the Collection methods that
    I showed on the previous chart. I'm only showing additional methods specifically declared on the List interface. We
    covered most of these methods, but I wanted you to see here, that most of these are dealing with an index. A list can
    be either indexed, as an ArrayList, or not, like a LinkedList, but a LinkedList is implemented to support all of these
    methods as well. Derived interfaces may have specific ways to add, remove, get, and sort elements for their specific
    type of collection, in addition to those defined on the Collection Interface itself. Now, let's look at the big picture
    of interfaces, and some specific implementations.

        This chart below shows the interface hierarchy. It's also showing the implementations or concrete classes, that
    implement these interfaces, in bottom parts. Notice that Map does not extend Collection, although still part of this
    framework. Maps are uniquely different, which I'll be explaining when I cover Maps in this section. You can see that
    LinkedList implements both List and Deque(double ended queue), and I discussed this in detail when I covered LinkedLists.

                                    __________________                                      __________________
                                    | <<Interface>>  |                                      | <<Interface>>  |
                                    | Collection     |                                      | Map            |
                                    |________________|                                      |________________|
                    ------------------------↑------------------------                       |Implementations:|
            ________↑_________      ________↑_________      ________↑_________              |HashMap         |
            | <<Interface>>  |      | <<Interface>>  |      | <<Interface>>  |              |LinkedHashMap   |
            | List           |      | Set            |      | Queue          |              |________________|
            |________________|      |________________|      |________________|                      ↑
            |Implementations:|      |Implementations:|              ↑                       ________↑_________
            |LinkedList      |      |HashSet         |              ↑                       | <<Interface>>  |
            |ArrayList       |      |LinkedHashSet   |              ↑                       | SortedMap      |
            |________________|      |________________|      ________↑_________              |________________|
                                            ↑               | <<Interface>>  |              |Implementations:|
                                    ________↑_________      | Deque          |              |TreeMap         |
                                    | <<Interface>>  |      |________________|              |________________|
                                    | SortedSet      |      |Implementations:|
                                    |________________|      |ArrayDeque      |
                                    |Implementations:|      |LinkedList      |
                                    |TreeSet         |      |________________|
                                    |________________|

        A List is an ordered collection (also known as a sequence). These can be sequenced in memory like an ArrayList,
    or maintain links to the next and previous values, as a LinkedList.

                                                  The Queue

        A Queue is a collection designed for holding elements prior to processing, in other words the processing order
    matters, so the first and last positions, or the head and tail, are prioritized. Most often these may be implemented
    as First In, First Out (FIFO), but can be implemented like a Stack, as Last In First Out (LIFO) which we've discussed.
    Remember a Deque supports both.

                                                   The Set

        A Set is a collection conceptually based off of a mathematical set. Importantly, it contains no duplicate elements,
    and isn't naturally sequenced or ordered. You can think of a set as a kind of penned in chaotic grouping of objects.
    Java has three implementations, which I'll be reviewing in this section of the course in detail, the HashSet, the
    TreeSet and the LinkedHashSet. These are distinguished by the underlying way they store the elements in the set. A
    Sorted Set is a set that provides a total ordering of the elements.

                                                   The Map

        A Map is a collection that stores key and value pairs. The keys are a set, and the values are a separate collection,
    where the key keeps a reference to a value. Keys need to be unique, but values don't. Elements in a tree are stored
    in a key value Node, also called an Entry. In the videos coming up, we'll be looking at Set and Map, and how they
    resemble and differ from List. But before that, I want to talk about polymorphic algorithms.

                                        What's a polymorphic algorithm?

        Oracle's documentation describes a polymorphic algorithm as a piece of reusable functionality. At one time, most
    of these methods were provided to us, as static methods, on a class called java.util.Collections. Since JDK-8, and
    the advent of multiple interface enhancements, some of these methods are now on the interfaces themselves, as default
    or static methods. But not all, so I'll be discussing this class, and what it has to offer, in comparison to what's
    available on each collection class. It's also important to understand that legacy code will be using this class for
    some operations, that can be done from the class itself. I'll comment out that last line of code so that the class
    still compiles. Next, I'll be talking about this helper class and its methods. There are some fun polymorphic algorithms
    on there I haven't covered, so I'll see you in that next lecture.
*/
//End-Part-7

        Collection<String> list = new HashSet<>();

        String[] names = {"Anna", "Bob", "Carol", "David", "Edna"};
        list.addAll(Arrays.asList(names));
        System.out.println(list);

//Part-3
/*
        I'll add a couple more statements, again, just using methods I showed you, that are defined on the Collection
    interface. Let's add another name, Fred. And 3 more, via call to addAll, and as list as we did earlier. Print what
    we have. Running that code:

            [Anna, Bob, Carol, David, Edna]
            [Anna, Bob, Carol, David, Edna, Fred, George, Gary, Grace]
            Gary is in the list? true
*/
//End-Part-3

        list.add("Fred");
        list.addAll(Arrays.asList("George", "Gary", "Grace"));
        System.out.println(list);
        System.out.println("Gary is in the list? " + list.contains("Gary"));

//Part-4
/*
        I'll add a removeIf statement for good measure with a lambda expression, which will remove any names from the list
    that start with G, checking if charAt(0) = G. And I'll copy the 2 println statements from above, to below this statement.
    Running that:

            ---(same)
            [Anna, Bob, Carol, David, Edna, Fred]
            Gary is in the list? false

    These are all methods, defined on the Collection interface, but executed on a specific implementation, the ArrayList,
    but assigned to a List variable.
*/
//End-Part-4

        list.removeIf(s -> s.charAt(0) == 'G');
        System.out.println(list);
        System.out.println("Gary is in the list? " + list.contains("Gary"));
//        list.sort();
    }
}
