package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course20_MapsViewCollections;

import java.util.*;

//Part-1
/*

                                        The Purpose of view Collections



               ___________________________________
               | <<Interface>>                   |
               | Map<K,V>                        |
               |_________________________________|
               |keySet(): Set<K>                 |          ___________________________________
               |entrySet(): Set<Map.Entry<K,V>>  |          | <<Interface>>                   |
               |values(): Collection<V>          |          | Map.Entry<K,V>                  |
               |_________________________________|          |_________________________________|
                                ↑                                            ↑
                            implements                                   implements
               _________________↑_________________          _________________↑_________________
               | HashMap<K,V>                    |          | HashMap.Node<K,V>               |
               |_________________________________|<>--------|_________________________________|
               | table: Node[]                   |          |  key: K                         |
               |_________________________________|          |  value: V                       |
                                                            |_________________________________|

        On this diagram, I'm showing you a high level overview of the structure of the HashMap class. First, it's important
    to know that there's a static nested interface on the Map interface, and it's name is Entry. Concrete classes, implement
    both the Map interface, and the Map.Entry interface. The HashMap implements Map, and has a static nested class, Node,
    that implements the Map.Entry interface. The HashMap maintains an array of these Nodes, in a field called table, whose
    size is managed by Java, and whose indices are determined by hashing functions. For this reason, a HashMap is not
    ordered.

                                            The Map's view Collections

                                    ___________________________________
                                    | <<Interface>>                   |
                                    | Map<K,V>                        |
                                    |_________________________________|
                                    |keySet(): Set<K>                 |
                                    |entrySet(): Set<Map.Entry<K,V>>  |
                                    |values(): Collection<V>          |
                                    |_________________________________|

        I want to focus now on the three view collections we can get from the map, which are the keySet, the entrySet,
    and the values. We know a map has keys, and these can't contain duplicates. These keys can be retrieved as a Set view,
    by calling the keySet method on any map object. Each key value pair is stored as instance of an Entry, and the combination
    of the key and value will be unique, because the key is unique.

        A Set view of these entries, or nodes in the case of the HashMap, can be retrieved from the method entrySet. Finally,
    values are stored, and referenced by the key, and values can have duplicates, meaning multiple keys could reference
    a single value. You can get a Collection view of these, from the values method, on a map instance. Let's look at these
    views in some code. Getting back to the SetsAndMaps project, I want to create a new class called MapViewsMain, with
    a main method.
*/
//End-Part-1

public class MapViewsMain {

    public static void main(String[] args) {

//Part-2
/*
        I'm going to create a new map, with String as the key type, and Contact as the value type. I'll set that to a new
    HashMap. I'll follow that with a call to get Data on ContactData, passing it the phone type, and chain a for each to
    that. In the lambda expression, I'll make a call to the put method on contacts, pass the contact name as the key, and
    the contact object, as the value. I'll copy that statement, pasting it, changing the type from phone to email. Next,
    I want to get a view of my keys, and I can do that with the keySet method on a map. I'll create a local variable with
    a type of Set, and type argument String. I'll call it keysView, and assign that to contacts.keySet. After that, I'll
    just print that variable out. Since the method keySet returns a set and not a map, I need only one type argument in
    the declaration, and that's the type of the key, a string in this case. If I run this,

            [Lucy Van Pelt, Linus Van Pelt, Minnie Mouse, Maid Marion, Charlie Brown, Robin Hood, Daffy Duck, Mickey Mouse]

    you can see the keys, my contact names printed, in no particular order there. If I had used a constructor, for example,
    perhaps a TreeSet, I would not actually get a view at that point, but a copy of the keys. Let me do that next.
*/
//End-Part-2

        Map<String, Contact> contacts = new HashMap<>();
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));

        Set<String> keysView = contacts.keySet();
        System.out.println(keysView);

//Part-3
/*
        I'll create another variable, copyOfKeys, and set that to a new Tree Set. And I'll print this variable out. Here,
    I'm doing something similar, except I'm passing the result of calling key set to a TreeSet constructor. Running that,

                [Charlie Brown, Daffy Duck, Linus Van Pelt, Lucy Van Pelt, Maid Marion, Mickey Mouse, Minnie Mouse, Robin Hood]

    my keys are now in alphabetical order, as you can see.
*/
//End-Part-3

        Set<String> copyOfKeys = new TreeSet<>(contacts.keySet());
        System.out.println(copyOfKeys);

//Part-4
/*
        I can use the containsKey method on the map, to see if the element exists by key. I'll call that in an if statement,
    and look for the key name, Linus Van Pelt. If contacts have a key matching that, I'll print that linus and I go way
    back, meaning I've known him a long time. You can see that's a lot easier than previous attempts I made, where I created
    a contact instance with a matching name, to try to find a match. What's neat though is, I can use this key set view,
    to remove elements from the map. I'll call the remove method on the keys view, passing it Daffy Duck. Then I'll print
    out the keys view. And I'll also print out my contacts set. Running this code,

                Linus and I go way back, so of course I have info
                [Lucy Van Pelt, Linus Van Pelt, Minnie Mouse, Maid Marion, Charlie Brown, Robin Hood, Mickey Mouse]
                Lucy Van Pelt: [] [(564) 208-6852]
                Linus Van Pelt: [lvpelt2015@gmail.com] []
                Minnie Mouse: [minnie@verizon.net] []
                Maid Marion: [] [(123) 456-7890]
                Charlie Brown: [] [(333) 444-5555]
                Robin Hood: [rhood@gmail.com] []
                Mickey Mouse: [micky1@aws.com] []

    notice that Daffy Duck's not in the key set, which should be no surprise. What might be a surprise though, is that
    this removed the key and value pair in the map as well, and the Daffy Duck contact isn't printed out. Let me do the
    same thing with my copy of the key set.
*/
//End-Part-4

        if (contacts.containsKey("Linus Van Pelt")) {
            System.out.println("Linus and I go way back, so of course I have info");
        }

        keysView.remove("Daffy Duck");
        System.out.println(keysView);
        contacts.forEach((k, v) -> System.out.println(v));

//Part-5
/*
        I'll copy those last three statements, and paste that copy directly below them, and I'll change keys view to copy
    of keys. I'll also change the contact I want removed to be Linus Van Pelt for this test, since I already removed Daffy
    from my contacts map. Running this code,

                    [Charlie Brown, Daffy Duck, Lucy Van Pelt, Maid Marion, Mickey Mouse, Minnie Mouse, Robin Hood]
                    Lucy Van Pelt: [] [(564) 208-6852]
                    Linus Van Pelt: [lvpelt2015@gmail.com] []
                    Minnie Mouse: [minnie@verizon.net] []
                    Maid Marion: [] [(123) 456-7890]
                    Charlie Brown: [] [(333) 444-5555]
                    Robin Hood: [rhood@gmail.com] []
                    Mickey Mouse: [micky1@aws.com] []

    it's true Linus is removed from copy of Keys. Since this is a copy though, it had no effect on the original map, so
    Linus is still in that map. If your goal is to use the key set to manage and manipulate the underlying map data, make
    sure you don't make the simple mistake of passing your key set to a new Set's constructor. Let's say, that next, I
    want to purge some old contacts, like Lucy, Minnie and Maid Marion. I can use removeAll on my key set with these three
    contacts, or alternatively I can use the remain all method, and name the contacts I want to keep. I'll go with the
    second approach.
*/
//End-Part-5

        copyOfKeys.remove("Linus Van Pelt");
        System.out.println(copyOfKeys);
        contacts.forEach((k, v) -> System.out.println(v));

//Part-6
/*
        Again I'll copy the three statements of keysView, and paste them below. I'll remove and replace that first line,
    and call keysView.retainAll. I'll pass a list of strings, so List.of, then my list of 4 records that I want to keep,
    Linus, Charlie Brown, Robin Hood and Mickey. This code runs and compiles successfully,

                    [Linus Van Pelt, Charlie Brown, Robin Hood, Mickey Mouse]
                    Linus Van Pelt: [lvpelt2015@gmail.com] []
                    Charlie Brown: [] [(333) 444-5555]
                    Robin Hood: [rhood@gmail.com] []
                    Mickey Mouse: [micky1@aws.com] []

    and I've got four names in my key set, and 4 contacts in my map. Ok, so that's pretty convenient, to be able to just
    perform functions on your set of keys, and have those operations be propagated to the map.

                            Functionality available to set returned from keySet()

        The set returned from the keySet method, is backed by the map. This means changes to the map are reflected in the
    set, and vice-versa. The set supports element removal, which removes the corresponding mapping from the map. You can
    use the methods remove, removeAll, retainAll, and clear operations. It does not support the add or addAll operations.
    Let's continue with this a bit longer.
*/
//End-Part-6

        keysView.retainAll(List.of("Linus Van Pelt","Charlie Brown", "Robin Hood", "Mickey Mouse"));
        System.out.println(keysView);
        contacts.forEach((k, v) -> System.out.println(v));

//Part-7
/*
        First, I'll execute clear on the keysView. I'll print my contacts set again. And running this,

                    {}

    I hope you can see that I've cleared the contacts map of elements, and a pair of empty brackets are printed out for
    my contacts map. Now, I want to try to start adding contacts back in, using the keysView.
*/
//End-Part-7

        keysView.clear();
        System.out.println(contacts);

//Part-8
/*
        I'll add Daffy Duck to the keys View set, and I'll again print out the contacts. This code compiles, but now if
    I run it,

                    Exception in thread "main" java.lang.UnsupportedOperationException

    I get an exception, an UnsupportedOperation Exception As I stated before, add operations can't be used on the keys
    view. If you think about that, it's kind of logical, because adding just a key is only part of the data needed. You
    don't have a way, if you do this, to say what the keyed value would be. Let me comment those two statements.
*/
//End-Part-8

        //keysView.add("Daffy Duck");
        //System.out.println(contacts);

//Part-9
/*
        Next, I'll add my contacts back, as I did before. I'll make a call to contact data dot get data, passing it the
    email type. I'll chain the for each method to that, adding each of these contacts to my set. I'll repeat that, but
    pass the phone type. And then I'll immediately print out the keysView set, not my map. Running this code,

                [Linus Van Pelt, Lucy Van Pelt, Minnie Mouse, Maid Marion, Robin Hood, Daffy Duck, Charlie Brown, Mickey Mouse]

    you can see all my newly added contacts, are in the keysView set. I didn't have to execute the keySet method again,
    to get the refreshed data. My view was refreshed automatically. This is a pretty powerful and easy way, to manipulate
    elements in the map.
*/
//End-Part-9

        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        System.out.println(keysView);

//Part-10
/*
        Now, let's consider the next view. I'll call values on my contacts map and assign to that to a variable, called
    values, with var as the reference type. And I'll call for each, on the result of that, printing out each element. The
    first thing I want you to see is that IntelliJ is telling me, with its hints, that I'm getting back a collection of
    Contacts there. And running that code,

                    Linus Van Pelt: [lvpelt2015@gmail.com] []
                    Lucy Van Pelt: [] [(564) 208-6852]
                    Minnie Mouse: [] [(456) 780-5666]
                    Maid Marion: [] [(123) 456-7890]
                    Robin Hood: [] [(789) 902-8222]
                    Daffy Duck: [daffy@google.com] []
                    Charlie Brown: [] [(333) 444-5555]
                    Mickey Mouse: [] [(999) 888-7777]

    I get all my contacts printed out.
*/
//End-Part-10

        var values = contacts.values();
        values.forEach(System.out::println);

//Part-11
/*
        Once again, I'll use the retain all method, but this time using it on the values collection, passing it the emails
    retrieved from Contact Data dot get Data. I'll print the keys, using my keys view, and after that, I'll print my map's
    keys and values. Running that code,

                [Linus Van Pelt, Minnie Mouse, Robin Hood, Daffy Duck, Mickey Mouse]
                Linus Van Pelt: [lvpelt2015@gmail.com] []
                Minnie Mouse: [] [(456) 780-5666]
                Robin Hood: [] [(789) 902-8222]
                Daffy Duck: [daffy@google.com] []
                Mickey Mouse: [] [(999) 888-7777]

    notice that the keys view and the map, have the same contacts as the email contacts, even though the contact's attributes
    (phone in some cases), may not match those in the email list. I was able to change my mapped data, by using a values
    view, which was then also reflected in the keys view. I'm going to add a method to the contact class,
*/
//End-Part-11

        values.retainAll(ContactData.getData("email"));
        System.out.println(keysView);
        contacts.forEach((k, v) -> System.out.println(v));

//Part-13
/*
        I'll first add a separator line. I'll create a new ArrayList, and pass it values. I'll sort the list, using my new
    method, with the helper methods on Comparator to do that. And I'll print out the elements in my list, including a call
    to the new method name, and then printing the contact info. Running this code,

                ------------------
                Duck, Daffy: Daffy Duck: [daffy@google.com] []
                Hood, Robin: Robin Hood: [] [(789) 902-8222]
                Mouse, Mickey: Mickey Mouse: [] [(999) 888-7777]
                Mouse, Minnie: Minnie Mouse: [] [(456) 780-5666]
                Van Pelt, Linus: Linus Van Pelt: [lvpelt2015@gmail.com] []

    you can see I have a nice printed list, alphabetical by last name. One thing I haven't demonstrated yet, is adding a
    duplicate contact, but under a different key name, so let me do that with the first element in my list here.
*/
//End-Part-13

        System.out.println("------------------");
        List<Contact> list = new ArrayList<>(values);
        list.sort(Comparator.comparing(Contact::getNameLastFirst));
        list.forEach(c -> System.out.println(c.getNameLastFirst() + ": " + c));

//Part-14
/*
        I'll print a separator line, then set a local variable to the first element in my list. I'll call put on my contacts
    map, but instead of using name for the key, I'm going to use my new method, get name last first, and I'll pass the my
    local variable as the value. Now, I'll print out the values collection view, using for each. And after that, I'll
    print the keys in my keys view. Running that,

                ------------------
                Linus Van Pelt: [lvpelt2015@gmail.com] []
                Daffy Duck: [daffy@google.com] []
                Minnie Mouse: [] [(456) 780-5666]
                Robin Hood: [] [(789) 902-8222]
                Daffy Duck: [daffy@google.com] []
                Mickey Mouse: [] [(999) 888-7777]
                Linus Van Pelt
                Duck, Daffy
                Minnie Mouse
                Robin Hood
                Daffy Duck
                Mickey Mouse

    I hope you can see there's two Daffy Duck contacts but the keys are unique, because Duck comma Daffy is different from
    Daffy Duck. Now, I can also pass my values collection to a HashSet, which also takes a Collection as an argument to
    the constructor.
*/
//End-Part-14

        System.out.println("------------------");
        Contact first = list.get(0);
        contacts.put(first.getNameLastFirst(), first);
        values.forEach(System.out::println);
        keysView.forEach(System.out::println);

//Part-15
/*
        I'll set up another local variable, set, a HashSet, again with a type argument of Contact. I'll set
    that equal to a new Hash set, passing it values. Then I'll print the values in this set. I'll also test the size of
    this set, and the size of my contacts map's key set. If the size of my set is less than the key set, than I can assume
    I have duplicate values, in the values collection, and I'll print that out. Running this,

                    Linus Van Pelt: [lvpelt2015@gmail.com] []
                    Robin Hood: [] [(789) 902-8222]
                    Mickey Mouse: [] [(999) 888-7777]
                    Daffy Duck: [daffy@google.com] []
                    Minnie Mouse: [] [(456) 780-5666]
                    Duplicate Values are in my Map

    I have 5 elements printed now, from my set, and I see that my statement got printed, Duplicate Values are in my map.
    Ok, so I've covered two of the three view collections. The final view collection is the entry set. I showed you on
    the earlier, that key value pairs are stored in instances that implement Map.Entry. In the HashMap, this is the Node.
    I can get a set of these Nodes, and examine them. I'll do this here, to look for keys that aren't in sync with their
    contact name.
*/
//End-Part-15

        HashSet<Contact> set = new HashSet<>(values);
        set.forEach(System.out::println);
        if (set.size() < contacts.keySet().size()) {
            System.out.println("Duplicate Values are in my Map");
        }

//Part-16
/*
        I'll set a local variable, using type inference, so var, and call that node set, making that equal to contacts.entrySet.
    I'll use an enhanced for loop, to loop through what I get back. Again, I'll just use var for the looped element, and
    call that node. On any node, or Map dot Entry instance, there's a getKey and a getValue method, which return the key
    value pair of this node. I'll compare the key with the value's name field next. If they're not equal, I'll print the
    key doesn't match the name, and then print both the key and the value. If you have IntelliJ's hints turned on, you'll
    see that nodeSet's type is a Set of Map.Entry, and the key type is String, the value type is Contact. I'll run this
    code,

                    java.util.HashMap$EntrySet
                    java.util.HashMap$EntrySet
                    java.util.HashMap$Node
                    Key doesn't match name: Duck, Daffy: Daffy Duck: [daffy@google.com] []

    and the result confirms what I already knew, that I have a key value pair, where the key doesn't match up with the
    contact name.
*/
//End-Part-16

        var nodeSet = contacts.entrySet();
        for (var node : nodeSet) {
            System.out.println(nodeSet.getClass().getName());
            if (!node.getKey().equals(node.getValue().getName())) {
                System.out.println(node.getClass().getName());
                System.out.println("Key doesn't match name: " + node.getKey() + ": " + node.getValue());
            }
        }

//Part-17
/*
        I'll change this code, for my more curious students, and add two statements to print out the class name,
    first right after the nodeSet. Then, I'll also include that for the node, in the if statement. Running that code,

                java.util.HashMap$EntrySet
                java.util.HashMap$EntrySet
                java.util.HashMap$Node
                Key doesn't match name: Duck, Daffy: Daffy Duck: [daffy@google.com] []
                java.util.HashMap$EntrySet
                java.util.HashMap$EntrySet
                java.util.HashMap$EntrySet
                java.util.HashMap$EntrySet

    we'll see what these instances really are. You can see both are nested types on the HashMap class itself. If I go up
    to my HashMap instantiation, and control click that class, that opens the source code for HashMap. I'll look for Class
    EntrySet first, using Control F. That will take me to the nested class declaration. This is an inner class, declared
    as final. I'll explain why you'd want to use final as a modifier, for some classes, in a section coming up. Here it
    prevents classes from extending this class, including classes that extend the HashMap. You can see this class is a
    Set, it extends an Abstract Set, and it's type is a Map.Entry. Let's look for Node next, so I'll change EntrySet
    to Node in the prompt up there. And that will take me to that class. This one is a static class, and implements the
    Map.Entry interface. I'm sure I've said this before, but you can learn a lot, if you take a little time to explore
    the code in Java's class libraries. At this point, I've covered the major functionality on the Map interface, using
    the HashMap to do it. In the next lecture, I'll give you a challenge.
*/
//End-Part-17

    }
}
