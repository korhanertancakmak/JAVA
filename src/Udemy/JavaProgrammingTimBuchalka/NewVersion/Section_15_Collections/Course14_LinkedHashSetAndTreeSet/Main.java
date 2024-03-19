package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course14_LinkedHashSetAndTreeSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Part-1
/*
                                            Ordered Sets - The LinkedHashSet

        In the last lecture, we reviewed the HashSet, and I've said before, that ordering is unspecified and generally
    chaotic. If you need an ordered set, you'll want to consider either the "LinkedHashSet" or the "TreeSet". A LinkedHashSet
    maintains the insertion order of the elements. The TreeSet is a sorted collection, sorted by the natural order of the
    elements, or by specifying the sort during the creation of the set. The LinkedHashSet "extends the HashSet" class. It
    maintains relationships between elements with the use of a doubly linked list between entries. The "iteration order"
    is therefore the same as the "insertion order" of the elements, meaning the order is "predictable". All the methods
    for the LinkedHashSet are the same as those for the HashSet. Like HashSet, it provides constant-time performance, O(1),
    for the add, contains and remove operations.But like the HashSet, this assumes an efficient hashing function.

                                                        TreeSet

        A TreeSet's class, uses a data structure that's a derivative of what's called a binary search tree, or Btree for
    short, which is based on the concept and efficiencies of the binary search. I've discussed the binarySearch method on
    the List, as well as the java.util.Collections class, and shown that this type of search is very fast, if the elements
    are sorted. This search iteratively tests the mid range of a group of elements to be searched, to quickly find its
    element, in a collection.

                                                        Maid Marion
                                                      /              \
          Left Node Contains: Elements < Maid Marion /                \ Right Node Contains: Elements > Maid Marion
                                                    /                  \
                                                   /                    \
                                                  /                      \
                                     Linus Van Pelt                                Minnie Mouse
                                    /               \                            /               \
          Left Node Contains:      /          Right Node Contains:      Left Node Contains:      Right Node Contains:
          Elements < Linus Van Pelt        Elements > Linus Van Pelt    Elements < Minnie Mouse   Elements > Minnie Mouse
                                 /                     \                     /                      \
                                /                       \                   /                        \
                      Charlie Brown             Lucy Van Pelt           Mickey Mouse            Robin Hood
                    /               \         /               \       /               \       /               \

        As elements are added to a TreeSet, they're organized in the form of a tree, where the top of the tree represents
    that mid point of the elements. This tree shows a conceptual example, using some of the character contacts from my
    last samples of code. Further binary divisions become nodes under that. The "left" node and its children are elements
    that are "less than" the parent node. The "right" node and its children are elements that are "greater than" the parent
    node. Instead of looking through all the elements in the collection to locate a match, this allows the tree to be
    quickly traversed, each node a simple decision point. Java's internal implementation uses a balanced tree data structure,
    called the red black tree. This class isn't about software engineering, but if this topic interests you, let me encourage
    you to explore software data structures. The main point is the tree remains balanced as elements are added.

                                                   TreeSet O Notation

        You'll remember that O(1) is constant time, meaning the time or cost of an operation doesn't change, regardless
    of how many elements are processed. O(n) is linear time, meaning it grows in line with the way the collection grows.
    Another notation, is O(log(n)), which means the cost falls somewhere in between constant and linear time. The TreeSet
    promises O(log(n)) for the add, remove, and contains operations, compared to the HashSet which has constant time O(1)
    for those same operations.

                                            The TreeSet interface hierarchy

        ________________________________     __________________     __________________     __________________
        | <<Interface>>                |     | <<Interface>>  |     | <<Interface>>  |     | <<Interface>>  |
        | NavigableSet                 |---->| SortedSet      |---->| Set            |---->| Collection     |
        |______________________________|     |________________|     |________________|     |________________|
        | Implementations: TreeSet     |     |________________|     |________________|     |________________|
        |______________________________|


        A TreeSet can be declared or passed to arguments typed with any of the interfaces shown on above. This class is
    sorted, and implements the SortedSet interface, which has such methods as first, last, headSet and tailSet, and comparator.
    This set also implements the NavigableSet Interface, so it has methods such as ceiling, floor, higher, lower, descendingSet
    and others. The TreeSet gives us a lot more functionality, but at a higher cost, than a LinkedHashSet, or a HashSet.
    You don't really have to understand the underlying data structure to understand two important points.

                               The TreeSet relies on Comparable or Comparator methods

        Elements which implement Comparable (said to have a natural order sort, like Strings and numbers) can be elements
    of a TreeSet. If your elements don't implement Comparable, you must pass a Comparator to the constructor.

        Let's jump into code, and start exploring the TreeSet class, and how to use it. I won't really spend much time
    on the LinkedHashSet, since it doesn't differ significantly, as far as functionality, from the HashSet. Just know that
    the LinkedHashSet provides a predictable iterable order, and this incurs slightly more cost than a HashSet, because
    of the doubly linked list structure that supports it. Ok, so I'm using the SetsAndMaps project. I want to create a
    new class, apart from the Main class, to keep the TreeSet code separate, so I'll create a TreeSetMain class.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        List<Contact> emails =  ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Phone List", phones);
        printData("Email List", emails);

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);
        printData("Phone Contacts", phoneContacts);
        printData("Email Contacts", emailContacts);

        int index = emails.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmail("Sherwood Forest");
        robinHood.addEmail("Sherwood Forest");
        robinHood.replaceEmailIfExists("RHood@sherwoodforest.com", "RHood@sherwoodforest.org");
        System.out.println(robinHood);

        Set<Contact> unionAB = new HashSet<>();
        unionAB.addAll(emailContacts);
        unionAB.addAll(phoneContacts);
        printData("(A ∪ B) Union of emails (A) with phones (B)", unionAB);

        Set<Contact> intersectAB = new HashSet<>(emailContacts);
        intersectAB.retainAll(phoneContacts);
        printData("(A ∩ B) Intersect emails (A) and phones (B)", intersectAB);

        Set<Contact> intersectBA = new HashSet<>(phoneContacts);
        intersectBA.retainAll(emailContacts);
        printData("(B ∩ A) Intersect phones (B) and emails (A)", intersectBA);

        Set<Contact> AMinusB = new HashSet<>(emailContacts);
        AMinusB.removeAll(phoneContacts);
        printData("(A - B) emails (A) - phones (B)", AMinusB);

        Set<Contact> BMinusA = new HashSet<>(phoneContacts);
        BMinusA.removeAll(emailContacts);
        printData("(B - A) phones (B) - emails (A)", BMinusA);

        Set<Contact> symmetricDiff = new HashSet<>(AMinusB);
        symmetricDiff.addAll(BMinusA);
        printData("Symmetric Difference: phones and emails", symmetricDiff);

        Set<Contact> symmetricDiff2 = new HashSet<>(unionAB);
        symmetricDiff2.removeAll(intersectAB);
        printData("Symmetric Difference: phones and emails", symmetricDiff2);

    }

    public static void printData(String header, Collection<Contact> contacts) {

        System.out.println("----------------------------------------------");
        System.out.println(header);
        System.out.println("----------------------------------------------");
        contacts.forEach(System.out::println);
    }
}
