package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course02_CollectionMethodsPart1;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);
    }
}
