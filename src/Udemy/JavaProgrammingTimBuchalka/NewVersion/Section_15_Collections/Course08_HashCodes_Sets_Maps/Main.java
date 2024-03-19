package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course08_HashCodes_Sets_Maps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Part-1
/*
                                Understanding the Importance of the Hash Code

        In upcoming lectures, I'll be discussing 2 classes, HashSet and HashMap, which are based on the hash codes of
    objects. This can be a confusing topic for new programmers, so I want to spend some extra time explaining it. In a
    previous lecture, I showed you an image of an abstract set, which showed chaotically placed elements. Since sets are
    unique because they don't support duplicates, adding an element always incurs the cost of first checking for a match.
    If your set is large or very large, this becomes a costly operation, O(n), or linear time, if you remember the Big O
    notations I covered previously. A mechanism to reduce this cost, is introduced by something called hashing. If we
    created two buckets of elements, and the element could consistently identify which bucket it was stored in, then the
    lookup could be reduced by half. If we created four buckets, we could reduce the cost by a quarter. A hashed collection
    will optimally create a limited set of buckets, to provide an even distribution of the objects across the buckets in
    a full set. A hash code can be any valid integer, so it could be one of 4.2 billion valid numbers. If your collection
    only contains 100,000 elements, you don't want to back it with a storage mechanism of 4 billion possible placeholders.
    And you don't want to have to iterate through 100,000 elements one at a time to find a match or a duplicate. A hashing
    mechanism will take an integer hash code, and a capacity declaration which specifies the number of buckets to distribute
    the objects over. It then translates the range of hash codes into a range of bucket identifiers. Hashed implementations
    use a combination of the hash code and other means, to provide the most efficient bucketing system, to achieve this
    desired uniform distribution of the objects.

                                    Hashing Starts With Understanding Equality

        To understand hashing in Java, I think it helps to first understand the equality of objects. I've touched on this
    in previous lectures, but now I want to be sure you thoroughly understand this subject, because it matters when dealing
    with any hashed collections. There are two methods on java.util.Object, that all objects inherit.

            Testing for equality                                The hashcode method
            public boolean equals(Object obj)                   public int hashCode()

    These are "equals", and "hashCode", and I show the method signatures from Object here. The implementation of equals
    on Object is shown here. It simply returns this == obj.

            public boolean equals(Object obj) {
                return (this == obj);
            }

    Do you remember what "==" means for Objects? It means two variables have the "same reference to a single object in
    memory." Because both references are pointing to the same object, then this is obviously a good equality test. Objects
    can be considered equal in other instances as well, if their attribute values are equal, for example. The String class
    overrides this method, so that it compares all the characters in each String, to confirm that two Strings are equal.
    Let's review this in some code, as the jumping off point for understanding the hash code.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

//Part-2
/*
        I'll set up a series of String local variables. I'll set a-text and b-text to the same string literal, so "Hello"
    in double quotes. I have c-text and that's assigned the result of calling String.join with "l", as the first argument,
    and "He", and "lo", as the next 2 arguments. d-text is the result of concatenating "llo", to the string literal "He".
    Finally e-text is set to the string literal, hello, but all lowercase this time. Now, I'll create a list out of these
    strings, so I can process them more easily.
*/
//End-Part-2

        String aText = "Hello";
        String bText = "Hello";
        String cText = String.join("l","He","lo");
        String dText = "He".concat("llo");
        String eText = "hello";

//Part-3
/*
        I'll call asList on the Arrays class, passing it my list of Strings. Remember this takes a variable arguments list,
    so I can just list all my strings there. Next, I'll print each of these Strings out, along with their hashCodes.
    Running this code:

                Hello: 69609650
                Hello: 69609650
                Hello: 69609650
                Hello: 69609650
                hello: 99162322

    I've got 5 strings printed, 4 have the value Hello, with a capital H, and one is hello with a lower case h. Notice
    that all 4 of the Strings with a capital h, return the exact same hash code, but the last one is different. Java doesn't
    care if these are different objects in memory, when it tests the equality of Strings, using the equals method. It just
    cares that the characters match, in one instance, compared to another instance.
*/
//End-Part-3

        List<String> hellos = Arrays.asList(aText, bText, cText, dText, eText);
        hellos.forEach(s -> System.out.println(s + ": " + s.hashCode()));

//Part-4
/*
        Now, let's create a Set of strings. I do that by declaring Set as the type, with String as the type argument. I'll
    call it my set and assign it a new HashSet, and I'll pass my hellos list to the hash set constructor. A hash set is
    a class that implements the Set interface, and tracks duplicates by their hash code. Most collections allow the creation
    of another collection type, by passing a different collection to the constructor, as I'm doing here, passing a list
    to a set, but a Set's constructor allows any instance that implements Collection to be passed to it. Ok, now, I'll
    print out my set, and like a list, I can pass that to a System.out.println statement. I'll also print out the size
    of my set. Running this code:

                mySet = [Hello, hello]
                # of elements = 2

    I get 2 elements in my set, Hello with a capital h, and hello with a lower case h. You can see the number of elements
    is 2, even though the list I passed to the constructor had 5 references. Let's loop through the elements and see
    which String references are really in this set.
*/
//End-Part-4

        Set<String> mySet = new HashSet<>(hellos);
        System.out.println("mySet = " + mySet);
        System.out.println("# of elements = " + mySet.size());

//Part-5
/*
        I'll loop through the elements in the set, using an enhanced for loop, printing the set value. I'll loop through
    the string in the hellos list, using a traditional for loop. I'm going to compare the String in the set with the
    String in the list, and I want to use equals sign here, because I'm really interested in whether these instances are
    the same object in memory. If they're the same reference, I'll print out the index of the element in the list. I'll
    print a new line after set element. And running that code:

            ---(same)
            Hello: 0, 1,
            hello: 4,

    I get 3 matches. The first set element, "Hello", with an uppercase H, is the same reference as the first 2 list elements,
    at index 0 and 1. The "hello" element with lowercase is the last element in the Strings list. The hash set will only
    add new references to its collection if it doesn't find a match in its collection, first using the hashCode and then
    the object's equals method. It uses the hashCode to create a bucket identifier to store the new reference. I'll talk
    about the underlying mechanism of the hash set a bit later.
*/
//End-Part-5

//Part-6
/*
        Our code set up five String reference variables, but two of these, referenced the same string object in memory,
    as shown here with aText and bText pointing to the same string instance.

        Reference Variables               Objects In Memory                                                              Hash Collection
            ___________                 ______________________
            |  aText  |                 |    "Hello"         | An indexed bucket is created as
            |  bText  |>>>>>>>>>>>>>>>>>|hashcode:69609650   |>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  Bucket-1
            |---------|                 |--------------------| an algorithm dependent on the hash code

            ___________                      "Hello"
            |  cText  |>>>>>>>>>>>>>>>>> hashcode:69609650
            |---------|                                        If hash codes are different, then unique elements are
                                                               distributed across algorithmically identified buckets.
            ___________                      "Hello"
            |  dText  |>>>>>>>>>>>>>>>>> hashcode:69609650
            |---------|
                                        ______________________
            ___________                 |    "hello"         | If hash codes are the same, the equals method is used to
            |  eText  |>>>>>>>>>>>>>>>>>|hashcode:69609650   | >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  Bucket-2
            |---------|                 |--------------------| determine if elements in the bucket are equal or duplicates.

    When we passed our list of five strings to the HashSet, it added only unique instances to its collection. It locates
    elements to match, by first deriving which bucket to look through, based on the hash code. It then compares those
    elements to the next element to be added, with other elements in that bucket, using the equals method. Again, we'll
    talk about how Java implements the HashSet in a bit. For now, I want you to understand how equality, and the hashCode
    go hand in hand, when using hashed collections. Let's get back to the code and explore this a bit more.
*/
//End-Part-6

        for (String setValue : mySet) {
            System.out.print(setValue + ": ");
            for (int i = 0; i < hellos.size(); i++) {
                if (setValue == hellos.get(i)) {
                    System.out.print(i + ", ");
                }
            }
            System.out.println(" ");
        }

//Part-9
/*
        An ace of hearts, a king of clubs, and a queen of spades. I'll again create a list of these, passing the list of
    cards to the asList method. I'll print the hashCode for each. Running that code:

                ---(same)
                Ace of Hearts: 2065951873
                King of Clubs: 793589513
                Queen of Spades: 1313922862

    you can see I get unique hash codes for each. Let's create a set of cards and add the cards one at a time.
*/
//End-Part-9

        PlayingCard aceHearts = new PlayingCard("Hearts", "Ace");
        PlayingCard kingClubs = new PlayingCard("Clubs", "King");
        PlayingCard queenSpades = new PlayingCard("Spades", "Queen");
        List<PlayingCard> cards = Arrays.asList(aceHearts, kingClubs, queenSpades);
        cards.forEach(s -> System.out.println(s + ": " + s.hashCode()));

//Part-10
/*
        I'll call the Set of cards deck, and make it a HashSet. Loop through the cards list. Add these cards one at a time.
    The add method on any collection returns true if the element is successfully added, and false if not. Because sets
    don't allow duplicates, it becomes more important to check if the element you expect to be added, really was added.
    In "if (!deck.add(c))", I check if i get false back, and if so, I'll print that a duplicate was found. I'm also printing
    the entire set after this loop to see what's been added to the set. Running this code:

                ---(same)
                [Ace of Hearts, King of Clubs, Queen of Spades]

    we see that all 3 cards were added to the set. The elements are not guaranteed to be in any order, so you may not see
    the elements printed in the same order as mine, or in the order you added the elements. Ok, next I want to go back
    to the PlayingCard class, and override both the "hashCode" and the "equals" method.
*/
//End-Part-10

        Set<PlayingCard> deck = new HashSet<>();
        for (PlayingCard c : cards) {
            if (!deck.add(c)) {
                System.out.println("Found a duplicate for " + c);
            }
        }
        System.out.println(deck);

    }
}
