package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course08_HashCodes_Sets_Maps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        String aText = "Hello";
        String bText = "Hello";
        String cText = String.join("l","He","lo");
        String dText = "He".concat("llo");
        String eText = "hello";

        List<String> hellos = Arrays.asList(aText, bText, cText, dText, eText);
        hellos.forEach(s -> System.out.println(s + ": " + s.hashCode()));

        Set<String> mySet = new HashSet<>(hellos);
        System.out.println("mySet = " + mySet);
        System.out.println("# of elements = " + mySet.size());

//Part-6
/*

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
