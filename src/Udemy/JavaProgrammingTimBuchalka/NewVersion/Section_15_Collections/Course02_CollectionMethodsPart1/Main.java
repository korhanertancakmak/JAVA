package CourseCodes.NewSections.Section_15_Collections.Course02_CollectionMethodsPart1;

import java.util.List;

//Part-1
/*
                                                    The Setup

        I want to start doing something a little bit different, as we proceed further into the course, and the code gets
    naturally more complex. In some instances, it takes me a while to set up the code, before I have a starting basis for
    the new content I want to teach. Moving forward, I'll try to do this setup work, in a separate lecture, when it makes
    sense to do so. "I'll introduce this code as an optional challenge", for those who really like the independent work.
    If you feel like you need extra time with some of the basics, definitely take the challenge, and follow along with me
    in this lecture, as I set up my own version of the code. If you feel like you don't need this review, and aren't
    interested in the challenge, then feel free to skip this lecture.

        "I'll review this code in much briefer detail, at the start of the lecture where I introduce it", and the source
    will be available as a download for you, in the resources section of that lecture. I'll also try to mention any specifics
    in the code in this lecture, that might be a bit new(and will be covered shortly) or is a variant of something we've
    used previously. For example, in the code I'll be reviewing in this lecture. My code will include the use of a record,
    a nested enum on that record, and the use of List functions you've already been exposed to. I'll be using ascii codes
    to display the suits of the cards.

        In this lecture, I want to set up a Card class, which will be used to create a deck of playing cards. I'll be using
    these cards, and decks of cards, to demonstrate many of the methods on java.util.Collections. The Card will have three
    fields:

            - a Suit, meaning Club, Diamond, Heart, or Spade.
            - a face field, which will be a String, containing either the number of the card, or the face value of the
              card, Jack, Queen, King or Ace.
            - a rank, an integer.

    The Card should override the toString method, and print the card with the face value (abbreviated, if a face card),
    the ascii character of the suit, and the rank in parentheses. I'm including the ascii characters that will print out
    each suit as a printable character.

            CLUB  = 9827
          DIAMOND = 9830
            HEART = 9829
            SPADE = 9824

    The card should have the following public static methods to assist anyone using this class:

        - getNumericCard which should return an instance of a Card, based on the suit and number passed to it.
        - getFaceCard which should return an instance of a Card, based on the suit and abbreviation (J, Q, K, A) passed
          to it.
        - getStandardDeck which should return a list of cards you'd find in a standard deck. See the previous notes for
          a full set of cards.
        - printDeck, which should take a description, a list of card, and a row count. This method will print the cards
          out in the number of rows passed.
        - Finally, the card should have an overloaded printDeck method, that will print "Current Deck" as the description,
          and use 4, as the number of rows to be printed.

        If you want to take this as a challenge, go ahead and try that out on your own. If you feel like you don't want
    to go through this setup, or need the additional walk through, don't worry. "I'll introduce it with a brief overview
    in the next lecture, and it will be in the resources section of that lecture, as well as this one".

        This chart shows my own plan, or the class diagram I'll be coding towards. I'm going to create a Card class. In
    my case I'm just going to make it a record, because once a card is created, there's no sense in allowing anyone to
    change it.

            _________________________________________________________________
            | Card                                                          |
            |_______________________________________________________________|
            | private Suit suit;                                            |
            | private String face;                                          |
            | private int rank;                                             |       _________________________________
            |_______________________________________________________________|       |<<Enumeration>>                |
            |  "public static methods"                                      |       | Suit                          |
            |  Card getNumericCard(Suit suit, int number)                   |       |_______________________________|
            |  Card getFaceCard(Suit suit, char abbrev)                     |       | CLUB, DIAMOND, HEART, SPADE   |
            |  List<Card> getStandardDeck()                                 |       |_______________________________|
            |  void printDeck(List<Card> deck)                              |       | "public instance methods"     |
            |  void printDeck(List<Card> deck, String description, int rows)|       | char getImage()               |
            |  "public instance methods"                                    |       |_______________________________|
            |  String toString()                                            |
            |_______________________________________________________________|

    Using a record gives me built in immutability, if all my attributes are simple data types, like primitives and Strings.
    Maybe you decided to create a Deck class, to contain your cards, and that's a good idea too. For the examples ahead,
    I'm just going to use List as my Deck container.
*/
//End-Part-1
public class Main {

    public static void main(String[] args) {

//Part-9
/*
        Simply by calling the getStandardDeck method on the Card class, assigning it to a variable, and then calling
    printDeck on the Card class as well. Running this code:

        ---------------------------
        Current Deck
        2♣(0) 3♣(1) 4♣(2) 5♣(3) 6♣(4) 7♣(5) 8♣(6) 9♣(7) 10♣(8) J♣(9) Q♣(10) K♣(11) A♣(12)
        2♦(0) 3♦(1) 4♦(2) 5♦(3) 6♦(4) 7♦(5) 8♦(6) 9♦(7) 10♦(8) J♦(9) Q♦(10) K♦(11) A♦(12)
        2♥(0) 3♥(1) 4♥(2) 5♥(3) 6♥(4) 7♥(5) 8♥(6) 9♥(7) 10♥(8) J♥(9) Q♥(10) K♥(11) A♥(12)
        2♠(0) 3♠(1) 4♠(2) 5♠(3) 6♠(4) 7♠(5) 8♠(6) 9♠(7) 10♠(8) J♠(9) Q♠(10) K♠(11) A♠(12)

    you can see why I wanted to print my deck by rows, it's easier to see all the cards, and when I create a standard deck,
    they're ordered by suit and rank as you can see. Ok, so that's the setup. This code will be used in the next few lectures,
    where I'm going to cover a new class I haven't yet talked about, and that's java.util.Collections, so I'll see you
    in the next lecture.
*/
//End-Part-9

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);
    }
}
