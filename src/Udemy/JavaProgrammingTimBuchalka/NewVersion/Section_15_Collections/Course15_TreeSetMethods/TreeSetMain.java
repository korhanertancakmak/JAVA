package CourseCodes.NewSections.Section_15_Collections.Course15_TreeSetMethods;

import java.util.*;

public class TreeSetMain {

    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

//        NavigableSet<Contact> sorted = new TreeSet<>(phones);
        Comparator<Contact> mySort = Comparator.comparing(Contact::getName);
        NavigableSet<Contact> sorted = new TreeSet<>(mySort);
        sorted.addAll(phones);
        sorted.forEach(System.out::println);

        NavigableSet<String> justNames = new TreeSet<>();
        phones.forEach(c -> justNames.add(c.getName()));
        System.out.println(justNames);

        NavigableSet<Contact> fullSet = new TreeSet<>(sorted);
        fullSet.addAll(emails);
        fullSet.forEach(System.out::println);

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.sort(sorted.comparator());
        System.out.println("--------------------------");
        fullList.forEach(System.out::println);

        Contact min = Collections.min(fullSet, fullSet.comparator());
        Contact max = Collections.max(fullSet, fullSet.comparator());

        Contact first = fullSet.first();
        Contact last = fullSet.last();

        System.out.println("--------------------------");
        System.out.printf("min = %s, first=%s %n", min.getName(), first.getName());
        System.out.printf("max = %s, last=%s %n", max.getName(), last.getName());
        System.out.println("--------------------------");

        NavigableSet<Contact> copiedSet = new TreeSet<>(fullSet);
        System.out.println("First element = " + copiedSet.pollFirst());
        System.out.println("Last element = " + copiedSet.pollLast());
        copiedSet.forEach(System.out::println);
        System.out.println("--------------------------");

//Part-1
/*
        Previously, we looked at different ways to instantiate a Tree Set, and confirmed that the elements were ordered
    when printed out. I covered the first and last methods, as well as the poll first and poll last methods. In this lecture,
    I want to cover additional functionality unique to this kind of set. The next set of methods I'll look at, identify
    the closest match in a set, to the value you pass to the method. First, I'll set up a couple of individual contacts,
    one for Daffy Duck who is a contact in my set. And one for Daisy Duck who isn't. I'll also include two others, who
    are also not in the set, snoopy who would be last, if inserted in the tree set. And Archie who would be first, if
    inserted. Ok, so now I have all the test cases I need to test the navigation methods, higher, lower, ceiling and floor.
*/
//End-Part-1

        Contact daffy = new Contact("Daffy Duck");
        Contact daisy = new Contact("Daisy Duck");
        Contact snoopy = new Contact("Snoopy");
        Contact archie = new Contact("Archie");

//Part-2
/*
        I'm going to loop through a list of contacts, which include daffy, daisy, the contact I got back from the last
    method, meaning the last contact in the set, and snoopy. I want to execute ceiling on each of those, and print out
    what comes back from that method. After that, I'll execute the higher method, passing each of the contacts, and print
    that, so we can look at the results of ceiling and higher, using the same contact. I'll end that by printing a separator
    line. Running this code,

                    --------------------------
                    ceiling(Daffy Duck)=Daffy Duck: [daffy@google.com] []
                    higher(Daffy Duck)=Linus Van Pelt: [lvpelt2015@gmail.com] []
                    ceiling(Daisy Duck)=Linus Van Pelt: [lvpelt2015@gmail.com] []
                    higher(Daisy Duck)=Linus Van Pelt: [lvpelt2015@gmail.com] []
                    ceiling(Robin Hood)=Robin Hood: [] [(564) 789-3000]
                    higher(Robin Hood)=null
                    ceiling(Snoopy)=null
                    higher(Snoopy)=null

    you can see the results for each different argument. Daffy is in the tree set, so ceiling returns Daffy, because you
    can think of ceiling as returning the element, that is either greater than or equal to the element passed. But the
    higher method never returns the value that's equal to it in a set, it always returns the next greater element, so I
    get Linus there. But for Daisy, because she's not in the set at all, higher and ceiling both return the same result,
    Linus. Now look what happens if we pass the last element. Again, ceiling will return the element that equals the value
    passed, so that's Robin Hood. Higher returns a null. With Snoopy, both ceiling and higher return null, because Snoopy
    isn't in the set, and there aren't any elements greater than snoopy.
*/
//End-Part-2

        for (Contact c : List.of(daffy, daisy, last, snoopy)) {
            System.out.printf("ceiling(%s)=%s%n", c.getName(), fullSet.ceiling(c));
            System.out.printf("higher(%s)=%s%n", c.getName(), fullSet.higher(c));
        }
        System.out.println("--------------------------");

//Part-3
/*
         I'll repeat these tests for the floor and lower methods next. I'll copy that last for loop, and following separator
    line. I want to still test daffy and daisy, but now I want to test first, rather than last, and I want to use Archie
    instead of snoopy. Inside the loop, I'll change ceiling to floor in both instances, and on the next line, I'll change
    higher to lower, in two instances. And running that,

            --------------------------
            floor(Daffy Duck)=Daffy Duck: [daffy@google.com] []
            lower(Daffy Duck)=Charlie Brown: [] [(333) 444-5555]
            floor(Daisy Duck)=Daffy Duck: [daffy@google.com] []
            lower(Daisy Duck)=Daffy Duck: [daffy@google.com] []
            floor(Charlie Brown)=Charlie Brown: [] [(333) 444-5555]
            lower(Charlie Brown)=null
            floor(Archie)=null
            lower(Archie)=null

    you can see that floor is similar to ceiling. It returns the element that's equal to the argument passed, if that
    element's in the set. Here, I get Daffy back for the first call to floor, but the lower method returns the element
    just lower than the element passed, so I get Charlie Brown. I get the same results, when I run the methods for Daisy
    Duck, getting Daffy Duck back in both cases. Daffy's the element that's just lower than Daisy would be, if she were
    in the set. Notice that when I pass in the first element (Charlie Brown), floor gives that element back, but I get
    null from the lower method. When I pass Archie, I get null back from both methods, because there is no element less
    than Archie in the set. Let me show you these methods on a table.
*/
//End-Part-3

        for (Contact c : List.of(daffy, daisy, first, archie)) {
            System.out.printf("floor(%s)=%s%n", c.getName(), fullSet.floor(c));
            System.out.printf("lower(%s)=%s%n", c.getName(), fullSet.lower(c));
        }
        System.out.println("--------------------------");

//Part-4
/*

    ________________________________________________________________________________________________________________
    |                 |                                 Result From Methods                                        |
    | Element passed  |____________________________________________________________________________________________|
    | as the argument |   floor(E) (<=)  |  lower(E) (<)          |   ceiling(E) (>=) |   higher(E) (>)            |
    |_________________|__________________|________________________|___________________|____________________________|
    |                 |                  | Next Element < Element |                   |  Next Element > Element    |
    |    In Set       | Matched Element  | or null if none found  | Matched Element   |  or null if none found     |
    |_________________|__________________|________________________|___________________|____________________________|

        All the methods shown on this table take an element as an argument, and return an element in the set, the closest
    match to the element passed. lower returns an element immediately from the set that is less than the method argument,
    or null if the argument is the minimum element in the set. floor returns elements respectively less than, or less than
    or equal, to the method argument. higher returns an element that is immediately greater than the method argument in
    the set, or null if the argument is the maximum element in the set. Ceiling returns an element in the set, that is
    greater than or equal to the method argument. The lower and higher methods will never return the same element as the
    method argument. Ok, so I've shown you the closest matches methods. I want you to think back to some code in some of
    the challenges we've done in this section, and how you might use methods like this. Perhaps, in the card game, knowing
    what the next card in a sorted hand would be, in a player's hand, would make it easier to look for straights and
    flushes, as an example.
*/
//End-Part-4

        NavigableSet<Contact> descendingSet = fullSet.descendingSet();
        descendingSet.forEach(System.out::println);
        System.out.println("--------------------------");

//Part-5
/*
        Now, let's look at a few other methods, that return different sets. All of these sets are backed by the original
    set used to create them. First, I can get a descending set, by a method of that name on the tree set. I'll set up a
    local variable, setting it to the result of calling descending set, on full set. I'll use for each, to print all the
    elements, and include a separator line. Running that code,

            --------------------------
            Robin Hood: [] [(564) 789-3000]
            Minnie Mouse: [] [(456) 780-5666]
            Mickey Mouse: [] [(999) 888-7777]
            Maid Marion: [] [(123) 456-7890]
            Lucy Van Pelt: [] [(564) 208-6852]
            Linus Van Pelt: [lvpelt2015@gmail.com] []
            Daffy Duck: [daffy@google.com] []
            Charlie Brown: [] [(333) 444-5555]

    you can see the set printed out in descending sorted order. The set that's returned is backed by the original set,
    so any changes to the fullSet, will be reflected in this set, and vice versa. Let's just confirm that by making a
    change to this descending set.
*/
//End-Part-5

        Contact lastContact = descendingSet.pollLast();
        System.out.println("Removed " + lastContact);
        descendingSet.forEach(System.out::println);
        System.out.println("--------------------------");
        fullSet.forEach(System.out::println);
        System.out.println("--------------------------");

//Part-6
/*
        I'll use poll last on the descending set and assign it to a local variable, a Contact named last contact. I'll
    print out the element that got removed, the last contact. Then I'll printout the descending Set, and a separator line.
    After that, I'll print the original set, full set, and another separator line. Running this,

            --------------------------
            Removed Charlie Brown: [] [(333) 444-5555]
            Robin Hood: [] [(564) 789-3000]
            Minnie Mouse: [] [(456) 780-5666]
            Mickey Mouse: [] [(999) 888-7777]
            Maid Marion: [] [(123) 456-7890]
            Lucy Van Pelt: [] [(564) 208-6852]
            Linus Van Pelt: [lvpelt2015@gmail.com] []
            Daffy Duck: [daffy@google.com] []
            --------------------------
            Daffy Duck: [daffy@google.com] []
            Linus Van Pelt: [lvpelt2015@gmail.com] []
            Lucy Van Pelt: [] [(564) 208-6852]
            Maid Marion: [] [(123) 456-7890]
            Mickey Mouse: [] [(999) 888-7777]
            Minnie Mouse: [] [(456) 780-5666]
            Robin Hood: [] [(564) 789-3000]
            --------------------------

    you can see that Charlie Brown was removed and returned from the poll last method After that, the descending list is
    printed, and now it goes from Robin Hood to Daffy Duck, Charlie isn't listed. The output from printing the full set,
    which is still ordered alphabetically, doesn't have Charlie Brown either. Whatever you do to the descending set, will
    be reflected in the set that backs it, in my case the full set. We can also get sub sets from the head, or beginning,
    of the sorted set, or the tail or end of the sorted set.
*/
//End-Part-6

        Contact marion = new Contact("Maid Marion");
        //var headSet = fullSet.headSet(marion);
        var headSet = fullSet.headSet(marion, true);
        headSet.forEach(System.out::println);
        System.out.println("--------------------------");

        //var tailSet = fullSet.tailSet(marion);
        var tailSet = fullSet.tailSet(marion, false);
        tailSet.forEach(System.out::println);
        System.out.println("--------------------------");

//Part-7
/*
        To test this, I'll create another contact variable, named Marion, and assign that a new instance of a Contact,
    with the name of Maid Marion. These methods take an element of the type in the set, so I'll pass the Marion contact
    to the headset method on full set. I'll return the result to a local variable named head set, I'm just using var here
    for simplicity. I'll call the forEach method on that subset. I'll include a separator line after that. Now, I want to
    copy and paste those last 3 statements. I'll change head set to tail set in both cases on the first statement. Again
    on the second statement, I'll change headset to tail set. Running this code,

            --------------------------
            Daffy Duck: [daffy@google.com] []
            Linus Van Pelt: [lvpelt2015@gmail.com] []
            Lucy Van Pelt: [] [(564) 208-6852]
            --------------------------
            Maid Marion: [] [(123) 456-7890]
            Mickey Mouse: [] [(999) 888-7777]
            Minnie Mouse: [] [(456) 780-5666]
            Robin Hood: [] [(564) 789-3000]
            --------------------------

    you can see the results of these two methods. The first, the headSet, when passed the Maid Marion contact, returned
    all the elements less than Maid Marion. The second the tailSet, when passed the same contact, returned all the elements
    after Maid Marion, but also included the maid Marion record. Head Set is exclusive by default, meaning it will exclude
    the element passed. But tail set is inclusive by default, meaning it will include the element I can change the default
    behavior, by using the overloaded versions, and passing a boolean value to each, to determine if the element passed,
    should be included. I'll edit the statement that executes headset, and I'll pass true as a second argument. For the
    tail set statement, I'll pass false as the second argument And rerunning that,

            --------------------------
            Daffy Duck: [daffy@google.com] []
            Linus Van Pelt: [lvpelt2015@gmail.com] []
            Lucy Van Pelt: [] [(564) 208-6852]
            Maid Marion: [] [(123) 456-7890]
            --------------------------
            Mickey Mouse: [] [(999) 888-7777]
            Minnie Mouse: [] [(456) 780-5666]
            Robin Hood: [] [(564) 789-3000]
            --------------------------

    I've now got Maid Marion in the headset, but she's no longer in the tail set, so these second arguments, can override
    the default behavior. You can chain these methods together, to get a subset in the middle, but it's far easier to just
    use the subSet method on NavigableSet, which I'll do next.
*/
//End-Part-7

        Contact linus = new Contact("Linus Van Pelt");
        //var subset = fullSet.subSet(linus, marion);
        var subset = fullSet.subSet(linus, false, marion, true);
        subset.forEach(System.out::println);

//Part-8
/*
        I'll create a new contact named Linus, with a name of Linus Van Pelt. And I'll execute the subset method on my full
    set, passing first Linus, and second Marion. I'll assign that to a variable, using var as my type, and subset as the
    name. I'll call the for each method to print each element in the sub set. And running that,

            Linus Van Pelt: [lvpelt2015@gmail.com] []
            Lucy Van Pelt: [] [(564) 208-6852]

    now I just get Linus and Lucy, so this subSet method includes the first contact, Linus, and excludes the second contact,
    Marion. Like headSet and tailSet, there's an overloaded version, so I can specify the inclusive flag for both elements
    passed. I'll add false as the second argument, which should exclude Linus, and true in the fourth argument which should
    include Marion in the sub set. And running the code again,

            Lucy Van Pelt: [] [(564) 208-6852]
            Maid Marion: [] [(123) 456-7890]

    I've now excluded Linus this time, but I've got Maid Marion. Let me show you these methods on a table, and review them.

    __________________________________________________________________________________________________________________________________________
    |  sub set methods                              |           Inclusive          |                    Description                          |
    |_______________________________________________|______________________________|_________________________________________________________|
    |  headSet(E toElement)                         | toElement is exclusive       | returns all elements less than the passed toElement     |
    |  headSet(E toElement, boolean inclusive)      | if not specified             | (unless inclusive is specifically included).            |
    |_______________________________________________|______________________________|_________________________________________________________|
    |  tailSet(E fromElement)                       | fromElement is inclusive     | returns all elements greater than or equal to the       |
    |  tailSet(E toElement, boolean inclusive)      | if not specified             | fromElement(unless inclusive is specifically included). |
    |_______________________________________________|______________________________|_________________________________________________________|
    |  subSet(E fromElement, E toElement)           | fromElement is inclusive     |                                                         |
    |  subSet(E fromElement, boolean fromInclusive, | if not specified, toElement  | returns elements greater than or equal to fromElement   |
    |         E toElement, boolean toInclusive)     | is exclusive if not specified| and less than toElement.                                |
    |_______________________________________________|______________________________|_________________________________________________________|


        All three methods, headSet, tailSet and subSet return a subset of elements, backed by the original set. The headSet
    method will return all elements at the head (or the elements that are less than) the argument passed to it. It won't
    include the element passed, unless you use the overloaded version, with true as your second argument. The tailSet
    method will return all elements from the passed element to the tail (or all elements greater than or equal) to the
    argument passed. It is inclusive of the element passed, if it's in the set. The subset takes two arguments, a from
    Element and a two Element, and returns a subset that includes the from Element, and all records less than the two
    Element. This method also has an overloaded version to specify different inclusion flags, than the default.

                                    When would you use a TreeSet?

        The TreeSet does offer many advantages, in terms of built-in functionality over the other two Set implementations,
    but it does come at a higher cost. If your number of elements is not large, or you want a collection that's sorted,
    and continuously re-sorted as you add and remove elements, and that shouldn't contain duplicate elements, theTreeSet
    is a good alternative to the ArrayList. In upcoming lectures, I'm going to introduce you to the Map and it's implemented
    classes. But before that, I have a TreeSet challenge for you.
*/
//End-Part-8

    }
}
