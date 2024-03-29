package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course18_MapInterfaceAndFunctionality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Part-1
/*
                                           The Map Interface and Functionality

        I'm going to leave the collection side of the java collections framework for now, and look at the Map interface,
    and the Java classes that implement it. The map interface is part of the collections framework, even though it doesn't
    derive from, or implement, the Collection interface.

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

    From this diagram above, you can obviously see the Map is out here on its own. A map in the collections framework
    is another data structure. Although it's still a grouping of elements, it's different, because elements are stored
    with keyed references.

            Collection Interface                                    Map Interface
            interface Collection<E> extends Iterable<E>             interface Map<K, V>

    This means a Map requires two type arguments, as you can see above, where I'm showing the root interface, Collection,
    compared to the Map interface. The Map has K for it's key type, and V for the value type. As with any generic classes,
    the only restriction on these types is, they must be reference types, and not primitives. The map interface replaces
    the now obsolete dictionary abstract class. And like the class that it replaces, it maps one key to one value. A
    language dictionary is a classic example of a map, with the keys being the words in the dictionaries, and the values
    would be the definitions of the words. Now unfortunately, the analogy falls down a bit with the English language. And
    the reason for that is, many English words have the same meanings. So the word "put", for example, has 4 definitions,
    2 as a verb, and 2 as a noun. A Java Map can't contain duplicate keys, so I couldn't have four keys, all named "put",
    in my map. Each key can only map to a single value, so I couldn't reference 4 different string descriptions for the
    key "put", without aggregating the descriptions into a collection of some sort. In the next few lectures, I'll be looking
    at 3 of the Java classes that implement the map interface, the "HashMap", the "LinkedHashMap", and the "TreeMap". The
    HashMap is unordered, the LinkedHashMap is ordered by insertion order, and the TreeMap is a sorted map. I want to continue
    with the contact merge example I used in the Set discussion, so I've got my SetsAndMaps Project pulled up. Remember
    this has a Contact class that represents either a phone or email contact, or both. A Map is a much easier data structure
    to work with, if you're doing updates, to a keyed item.
*/
//End-Part-1

public class MapMain {

    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
        System.out.println("-----------------------------");

//Part-2
/*
        I'll create a new Class, called MapMain, and I'll create a main method in that. First, I want to get a list of
    phone and email contacts, creating a combined list. I've done this before, soI'll set this up real quick. I'm going
    to have two lists, one for phones, getting that from get data on the ContactData class, first passing phone for the
    type. For the emails list, I'll do the same thing, but pass the type email. I'll create a full list, assigning that
    a new instance of an ArrayList, and passing that phones. Next I'll add the emails list. And I'll print those elements
    out with a separator line.

                    Charlie Brown: [] [(333) 444-5555]
                    Maid Marion: [] [(123) 456-7890]
                    Mickey Mouse: [] [(999) 888-7777]
                    Mickey Mouse: [] [(124) 748-9758]
                    Minnie Mouse: [] [(456) 780-5666]
                    Robin Hood: [] [(564) 789-3000]
                    Robin Hood: [] [(789) 902-8222]
                    Lucy Van Pelt: [] [(564) 208-6852]
                    Mickey Mouse: [] [(999) 888-7777]
                    Mickey Mouse: [mckmouse@gmail.com] []
                    Mickey Mouse: [micky1@aws.com] []
                    Minnie Mouse: [minnie@verizon.net] []
                    Robin Hood: [rhood@gmail.com] []
                    Linus Van Pelt: [lvpelt2015@gmail.com] []
                    Daffy Duck: [daffy@google.com] []
                    -----------------------------

    And I get a list, with duplicate records, one for each phone number and email record, each individual record in other
    words. Records in a list, are by default in insertion order, and include all duplicates. Now, I want to create my
    first map, and I'm going to start with a hash map. I have to specify two type arguments when I do this, the first
    type is the type of the key, and the second is the type of the value, or collection element.
*/
//End-Part-2

        Map<String, Contact> contacts = new HashMap<>();

        for (Contact contact : fullList) {
            contacts.put(contact.getName(), contact);
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

//Part-3
/*
        I'll use Map as my reference type, and my key is going to be the contact name so string, and the value is going
    to be a Contact record. I'll assign a new instance of the HashMap to that, with nothing passed to the constructor.
    Unlike the classes with Collection at their root, Map implementations don't have an add all method, and I can't simply
    pass collection types to the constructor, only other map types. To add my Contacts, I'm going to loop through the full
    list, and use the put method on my hash map. The put method takes a key and a value, and inserts what's called an Entry
    into the map. I'll pass the name, using get name on Contact, as the key, and my current contact as the value. Now I
    want to print out my elements in the map. There are multiple ways to do that, but I'll start with the for each method
    on the map itself, on contacts, and pass it a lambda expression. This lambda expression requires two arguments, one
    for the key, and one for the value, and I'll call those k and v for simplicity, and to save a little space. My expression
    will just print the key and the value here. Running this class's main method,

                    key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                    key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                    key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] []
                    key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                    key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                    key=Robin Hood, value= Robin Hood: [rhood@gmail.com] []
                    key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
                    key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com] []

    you can see the keys are the contact names, and the values, are the contact records. These elements aren't in any
    particular order. Also notice that there's no duplicates, of either key or value. Keys must be unique, but also, I
    want you to see that Mickey's contact, has an email, and not a phone number. Why is that, when the ArrayList, fullList,
    listed Mickey records with the phone numbers first? Wouldn't this mean that the first Mickey record, with phone number
    999, 888, 7777, would have been added first, and other records ignored? You'll remember that when we called add, on
    a set, it would return true or false, false if the element wasn't added, because it was already in the set.
    The map's put method is different than the set's add method, because the put method always puts the element in the
    map. If the key is not in the map, the key and value are added. If the key is in the map, the value is replaced, and
    the previous value is returned from the put method. For a map, this means the last element in your list, is the one
    that ends up in your map, which is what happened here. There are other methods for adding elements to the map, and
    I'll discuss each of these in turn in a bit.
*/
//End-Part-3

        System.out.println("-----------------------------");
        System.out.println(contacts.get("Charlie Brown"));
        System.out.println(contacts.get("Chuck Brown"));

//Part-4
/*
        But before that, something about a map that makes it so nice is, I can simply use the key value to look up my
    contact. I'll do that next, so first I'll print a separation line. Then I'll print the result of the get method, and
    for a map, I pass the key to that, so I'll pass the string Charlie Brown. Running that,

                        -----------------------------
                        Charlie Brown: [] [(333) 444-5555]

    you can see the get method successfully retrieved my Charlie Brown contact record. If Charlie Brown is not a key in
    the map, the get method would return a null. JDK8 introduced a method called getOrDefault, which will replace that
    null value with a default value. Let's look at that for a moment. First I'll use the get method and pass it Chuck
    Brown, passing that directly to a System.out.println method. And you can see, when I run that,

                        null

    it just prints a null. There may be some cases, where you're chaining methods, for example, where you really don't
    want a null back, because it will cause a null reference exception to occur.
*/
//End-Part-4

        Contact defaultContact = new Contact("Chuck Brown");
        System.out.println(contacts.getOrDefault("Chuck Brown", defaultContact));

//Part-5
/*
        I'll create a default contact for Chuck Brown, by just passing his name to the Contact constructor, Here, I'll use
    the get or default method, with the Chuck Brown key, but also pass it the defaultContact. If I run that code,

                    Chuck Brown: [] []

    you can see that gives me a Contact record back, for Chuck Brown with no other data. It's important to remember,
    though that Chuck Brown never gets added to the map, that contact is just there, the defaultContact, as a convenience.
*/
//End-Part-5

        System.out.println("-----------------------------");
        contacts.clear();
        for (Contact contact : fullList) {
            Contact duplicate = contacts.put(contact.getName(), contact);
            if (duplicate != null) {
                //System.out.println("duplicate = " + duplicate);
                //System.out.println("current = " + contact);
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

//Part-6
/*
        The put method will also return a null if the element isn't found in the map, or the element if it is. I'll add
    another separator line and now, I'll clear the contacts map of elements. I'll again loop through the contacts. This
    time I'll assign the outcome of the put method to a local variable. If that's not null, I'll print the value that
    came back, as well as the current value, which is the value that always gets put in the map. In this code, I can
    recognize a duplicate, if something other than null is returned from the put method. Running this code,

                    -----------------------------
                    duplicate = Mickey Mouse: [] [(999) 888-7777]
                    current = Mickey Mouse: [] [(124) 748-9758]
                    duplicate = Robin Hood: [] [(564) 789-3000]
                    current = Robin Hood: [] [(789) 902-8222]
                    duplicate = Mickey Mouse: [] [(124) 748-9758]
                    current = Mickey Mouse: [] [(999) 888-7777]
                    duplicate = Mickey Mouse: [] [(999) 888-7777]
                    current = Mickey Mouse: [mckmouse@gmail.com] []
                    duplicate = Mickey Mouse: [mckmouse@gmail.com] []
                    current = Mickey Mouse: [micky1@aws.com] []
                    duplicate = Minnie Mouse: [] [(456) 780-5666]
                    current = Minnie Mouse: [minnie@verizon.net] []
                    duplicate = Robin Hood: [] [(789) 902-8222]
                    current = Robin Hood: [rhood@gmail.com] []

    prints duplicate records by name, as well as the record that I'm iterating over, the current contact. I can use this
    bit of information to my advantage, especially since my objective is to merge my contacts. First, I'll comment out
    those two print statements in this loop

                                System.out.println("duplicate = " + duplicate);
                                System.out.println("current = " + contact);
                                                    to
                                contacts.put(contact.getName(), contact.mergeContactData(duplicate));

    I'll call my mergeContactData method when this situation occurs, and then put the new contact that comes back from
    that, in the map. I'll make another call to the put method on contacts, using the same key, and then pass a call to
    mergeContactData as the expression for the second argument. If I pass that the duplicate entry, I'll get a new contact
    with the current and duplicate data merged. I want to copy that forEach statement that prints the whole map out, and
    paste it after this loop. Can you guess what the results of this will be now? If I run that,

                    -----------------------------
                    key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                    key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                    key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                    key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                    key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                    key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                    key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
                    key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

    you can see I have a distinct listing of my contacts, with merged emails, and phone numbers. You can see Mickey Mouse
    has two distinct emails, and two distinct phone numbers. Merging my contacts was quick work, using a hash map. Now,
    let's just say I didn't really want to merge these elements, but I also don't want each additional matching record,
    to replace the initial entry either. In other words, I don't want to replace the value, every time I do a put.
*/
//End-Part-6

        System.out.println("-----------------------------");
        contacts.clear();

        for (Contact contact : fullList) {
            contacts.putIfAbsent(contact.getName(), contact);
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

//Part-7
/*
        First, I'll add two statements, a separation line and a call to clear the map again. I'm going to copy that first
    for loop, and paste that at the end of this main method. And instead of put, I'll use another method, called putIfAbsent,
    which is a default method on the Map interface. I'll run this real quick, then talk about the differences between put,
    and putIfAbsent.

                    -----------------------------
                    key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                    key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                    key=Minnie Mouse, value= Minnie Mouse: [] [(456) 780-5666]
                    key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                    key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                    key=Robin Hood, value= Robin Hood: [] [(564) 789-3000]
                    key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
                    key=Mickey Mouse, value= Mickey Mouse: [] [(999) 888-7777]

    Looking at the results of my map, you can see that Mickey's record, reflects the first record I had in my full list,
    meaning this record has the phone number 999, 888, 7777. The put if absent method won't put an updated value in the
    map, it just ignores the element, if it already finds something in the map for the key. Again, this method returns
    an element, if one is already in the map for the key, but the method doesn't replace it with the current element. It
    returns null if this is the first time an entry is being added to the map for that key. And now, If I wanted to merge
    contacts in this case, I could do something similar, so I'll copy the statements above, and paste them below.
*/
//End-Part-7

        System.out.println("-----------------------------");
        contacts.clear();

        for (Contact contact : fullList) {
            Contact duplicate = contacts.putIfAbsent(contact.getName(), contact);
            if (duplicate != null) {
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

//Part-8
/*
        In this loop, I want to assign what comes back from put If Absent, to a local variable, and again I'll call that
    duplicate. I'll add an if statement, checking if duplicate isn't null, and if it isn't, I'll now make a call to put
    here. I'll put my merged contact, again merging the current contact, with the duplicate variable. And running that
    code,

                -----------------------------
                key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
                key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

    I again get my contacts, nicely merged. There's still another method, introduced in JDK8, that I can use, rather than
    doing this kind of check, with put or putIfAbsent. That's the merge method. It also takes a key, and a value, but
    the third parameter is a BiFunction interface, meaning it's a target for a lambda expression that takes two parameters,
    and returns a result.
*/
//End-Part-8

        System.out.println("-----------------------------");
        contacts.clear();
        fullList.forEach(contact -> contacts.merge(contact.getName(), contact,
                //(previous, current) -> {
                    //System.out.println("prev: " + previous + " : current" + current);
                    //Contact merged = previous.mergeContactData(current);
                    //System.out.println("merged: " + merged);
                    //return merged;
                    //}
                Contact::mergeContactData
                ));
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

//Part-9
/*
        I'll print yet another separator line, and clear my contacts map. Now, I'll loop through my full list, but this
    time I'm going to do it with the for Each method. And that takes a lambda expression, and in there, I'm just going
    to call merge on the contacts map, passing the current contacts name, then the current contact. I need to follow that
    with another lambda expression. I'll leave a place holder here, for a minute. Finally I'll print the elements in the
    contacts map after this call. Again, this next lambda is a Function Interface, and in this case my two types and result
    are all going to be the same type, Contact. I need to set up two parameters in my lambda, and I'm going to call them
    previous and current, and now I'll set up a multi-line lambda, so an opening bracket. I want to print out the previous
    and the current contacts. I'll call merge contact data, to merge the data on my two contacts. I'll print the merged
    contact, and return it from this nested lambda expression. This looks like a lot, but bear with me a minute here.
    There's a reason I'm using the forEach method, and not the enhanced for loop, which might have been a little bit easier
    to see. Running this code,

                    -----------------------------
                    prev: Mickey Mouse: [] [(999) 888-7777] : currentMickey Mouse: [] [(124) 748-9758]
                    merged: Mickey Mouse: [] [(124) 748-9758, (999) 888-7777]
                    prev: Robin Hood: [] [(564) 789-3000] : currentRobin Hood: [] [(789) 902-8222]
                    merged: Robin Hood: [] [(789) 902-8222, (564) 789-3000]
                    prev: Mickey Mouse: [] [(124) 748-9758, (999) 888-7777] : currentMickey Mouse: [] [(999) 888-7777]
                    merged: Mickey Mouse: [] [(124) 748-9758, (999) 888-7777]
                    prev: Mickey Mouse: [] [(124) 748-9758, (999) 888-7777] : currentMickey Mouse: [mckmouse@gmail.com] []
                    merged: Mickey Mouse: [mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
                    prev: Mickey Mouse: [mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777] : currentMickey Mouse: [micky1@aws.com] []
                    merged: Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
                    prev: Minnie Mouse: [] [(456) 780-5666] : currentMinnie Mouse: [minnie@verizon.net] []
                    merged: Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                    prev: Robin Hood: [] [(789) 902-8222, (564) 789-3000] : currentRobin Hood: [rhood@gmail.com] []
                    merged: Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                    key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                    key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                    key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                    key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                    key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
                    key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                    key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                    key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

    you can now see the progression of the merge happening, with both the previous, current and merged contact printed out.
    If I just look at Mickey Mouse's progression, I can see that the map started out with the 999 phone contact, then
    it merged that with the 124 number, and it continues to merge any elements where the key already exists in the map.
    Then I've got the full map printed after that, and all my elements are again nicely merged. Ok, so let's clean this
    code up a bit, and get rid of the print statements in my lambda expression. I want to collapse this multi-line lambda
    into a single line lambda expression. Next, I'll remove the opening bracket, and the new line.

                                (previous, current) -> {
                                    System.out.println("prev: " + previous + " : current" + current);
                                    Contact merged = previous.mergeContactData(current);
                                    System.out.println("merged: " + merged);
                                    return merged;
                                }
                                                    to
                                (previous, current) -> previous.mergeContactData(current);

    I want to remove Contact merged=, and the ending semi-colon. After that, I'll remove the return statement altogether,
    and the closing bracket. And running this,

                    key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                    key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                    key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                    key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                    key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
                    key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                    key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                    key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

    the result is the same, but IntelliJ is telling me I can even make this more succinct. You can see that previous dot
    merge contact data is highlighted. I'll hover over that, and there it says I can replace this entire lambda expression,
    with a method reference, so I will.

                                (previous, current) -> previous.mergeContactData(current);
                                                    to
                                        Contact::mergeContactData

    This gives us the exact same results. That's a neat little bit of code, that lets me merge a bunch of contacts from
    an ArrayList, into a HashMap, with a single statement. Who doesn't love one statement of code, that does all the work
    for us. I'm going to end this lecture here. In the next lecture, I'll cover additional default methods on the map, as
    well as others implemented on the HashMap class itself.
*/
//End-Part-9

    }
}
