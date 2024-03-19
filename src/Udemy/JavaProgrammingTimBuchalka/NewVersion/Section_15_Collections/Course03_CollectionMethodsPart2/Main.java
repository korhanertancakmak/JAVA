package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course03_CollectionMethodsPart2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Part-1
/*
                                            What's a Collection Class?

        In this lecture, I'm going to be covering the java.util.Collections class. It's important to understand that the
    Collections class is not the Collections Framework. The framework contains many interfaces and implemented classes,
    as well as helper classes, which this Collections class is just one example. At one time, Java had interfaces, but
    no support for static or default methods on them, so useful methods were packaged in these helper classes. Some of
    these methods have since been implemented on the interfaces themselves, but there's still some functionality on the
    Collections class you might find useful. I'll examine some of these in code, and compare them to the methods on the
    interfaces now available.

        In the last lecture, I created some code I'll be using in this lecture, and a couple of the ones that follow. I'll
    briefly discuss it here, for those of you who decided to skip that walk through.

            _________________________________________________________________
            | Card                                                          |
            |_______________________________________________________________|
            | private Suit suit;                                            |       _________________________________
            | private String face;                                          |       |<<Enumeration>>                |
            | private int rank;                                             |       | Suit                          |
            |_______________________________________________________________|       |_______________________________|
            |  "public static methods"                                      |       | CLUB, DIAMOND, HEART, SPADE   |
            |  Card getNumericCard(Suit suit, int number)                   |       |_______________________________|
            |  Card getFaceCard(Suit suit, char abbrev)                     |       | "public instance methods"     |
            |  List<Card> getStandardDeck()                                 |       | char getImage()               |
            |  void printDeck(List<Card> deck)                              |       |_______________________________|
            |  void printDeck(List<Card> deck, String description, int rows)|                           CLUB = 9827
            |  "public instance methods"                                    |                        DIAMOND = 9830
            |  String toString()                                            |                          HEART = 9829
            |_______________________________________________________________|                          SPADE = 9824

    This code has Card, a record, with a nested enum, named Suit, declared in it. Suit is either a Club, Diamond, Heart
    or Spade, and this enum has a helper function, getImage, that returns a printable character value for that suit. I
    overrode the toString method on Card, to print that character along with what I call the face, or face value of the
    card, so 2 through 10, or Jack, Queen, King, or Ace. Each card will also have a rank, starting with 0 for the lowest
    card. In a standard deck, the lowest card, a two, is 0, because an Ace is usually the highest value card, though an
    ace can represent a one sometimes. The Card has static helper functions I'll be using in the code in this lecture, one
    to get an instance of a numeric card, one to get an instance of a face card. In addition, I can get a list of cards,
    that represents a standard deck of cards. And there's functions to print the deck of cards. And there's functions to
    print the deck of cards.

        Ok, so getting back to the Main class in the collectionsMethods, you can see I have 2 lines of code from the last
    lecture. I have a list of cards, I'm calling "deck", and I get that by calling a static method, called getStandardDeck
    on the Card class, then I simply call the printDeck, another static method on Card, to print the cards in my deck.
    Running this code,

            ---------------------------
            Current Deck
            2♣(0) 3♣(1) 4♣(2) 5♣(3) 6♣(4) 7♣(5) 8♣(6) 9♣(7) 10♣(8) J♣(9) Q♣(10) K♣(11) A♣(12)
            2♦(0) 3♦(1) 4♦(2) 5♦(3) 6♦(4) 7♦(5) 8♦(6) 9♦(7) 10♦(8) J♦(9) Q♦(10) K♦(11) A♦(12)
            2♥(0) 3♥(1) 4♥(2) 5♥(3) 6♥(4) 7♥(5) 8♥(6) 9♥(7) 10♥(8) J♥(9) Q♥(10) K♥(11) A♥(12)
            2♠(0) 3♠(1) 4♠(2) 5♠(3) 6♠(4) 7♠(5) 8♠(6) 9♠(7) 10♠(8) J♠(9) Q♠(10) K♠(11) A♠(12)

    gives me 52 cards, in 4 suits, starting with 2 through 10, then Jack, Queen, King and Ace. 2 had the lowest rank, the
    number in parentheses, so 0, and Ace has the highest rank, so 12. This set up will give us lots of opportunity, to
    test out the many methods on java.util.Collections. I want to start out by exploring some of the methods on collections
    for populating a list.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

//Part-2
/*
        I want to start out by exploring some of the methods on collections for populating a list. Before I start, I'll
    first use a method I've shown you before, on the Arrays helper class, called fill, to fill an array. I'm going to
    create an array of Cards, just for one suit, to start out, so I'll create an empty array of 13 Cards. I'll generate
    an ace of Hearts card, using the static getFaceCard method, The thing to note here is that Suit is a nested enum
    inside of Card, so for me to access it from this Main class, I have to use the Card type as a qualifier to the enum
    type name, Suit. Next, I'll call the fill method on the arrays class, passing the ace of hearts card. And I'll print
    that out, passing my array as a list backed by an array. I'll call the overloaded version of printDeck, so I can pass
    a description, and I just want that printed out in a single row. Running this code,

            ---------------------------
            Aces of Hearts
            A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12)

    you can see the output. I have an array of 13 cards, all filled with the same kind of card, an ace of hearts, and aces
    have a rank of 12. I'm reviewing this method on arrays, because I wanted to also show you that the Collections class
    has a fill method that seems to be very similar, so I want to look at that in comparison.
*/
//End-Part-2

        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART, 'A');
        Arrays.fill(cardArray, aceOfHearts);
        Card.printDeck(Arrays.asList(cardArray), "Aces of Hearts", 1);

//Part-3
/*
        This method takes a list, and an element. I'll create a new array list of cards, just calling it cards, and specify
    that it can hold 52 cards, which is the number of cards in a standard deck. I'll next call fill on the Collections
    class this time, and pass it my cards list, as well as the aceOfHearts card. I'll print the list, simply by passing
    it to System.out.println this time. And I'll print the size of the list. This method, fill, looks a lot like Arrays.fill,
    so you'd expect to get an array of 52 cards back, all aces of hearts. Running this code though,

                []
                cards.size() = 0

    shows that's not what really happens. Instead, the list is empty, and the size of the list is zero. When I initialize
    the ArrayList, passing it 52, it just sets the capacity to 52. It doesn't populate the list with elements. Unlike an
    array, the list isn't populated with 52 null references for example. Using fill here, on the Collections class, would
    fill the list (if it's size were greater than zero), meaning it would replace every element with the element passed
    to the method. The Collections.fill method doesn't actually add elements, so if my list is empty, it stays empty.
    How can I fill this list with some default value? The Collections class offers us some alternatives.
*/
//End-Part-3

        List<Card> cards = new ArrayList<>(52);
        Collections.fill(cards, aceOfHearts);
        System.out.println(cards);
        System.out.println("cards.size() = " + cards.size());

//Part-4
/*
        One of these methods is called nCopies which creates a new list with the number of elements you specify as the
    first argument, filling it with the element you pass as the second argument. First I'll set up another local variable,
    and call it acesOfHearts, and assign that to the call to Collections.nCopies, passing 13, and the card I create earlier,
    aceOfHearts. And I'll use printDeck, so I get a line separator and a header, but I'll print these cards in a single
    row.

                ---------------------------
                Aces of Hearts
                A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12)

    Like the array.fill example, I now have 13 aces of hearts in this new list. I'm going to copy those last last 2 lines,
    and paste a copy directly below that.
*/
//End-Part-4

        List<Card> acesOfHearts = Collections.nCopies(13, aceOfHearts);
        Card.printDeck(acesOfHearts, "Aces of Hearts", 1);

//Part-5
/*
        Before I change anything, I want to first add a new card, before these 2 statements. I want to create a king of
    clubs, so I'll call get face card, passing it Card.suit.CLUB, and the character K. Now, I'll change the list name
    from aces of hearts, to king of clubs on the first line. I'll change the card passed to nCopies on the second statement
    from aceOfHearts to kingOfClubs. Finally I'll change the call to print deck, again replacing acesOfHearts with kingsOfClubs,
    and also specifying King of Clubs as the descriptive header. Ok, running that,

                ---------------------------
                Kings of Clubs
                K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11)

    I now have a second list with just king of clubs. Next, I want to populate my full deck with some of these. Like List,
    Collections includes an addAll method.
*/
//End-Part-5

        Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUB, 'K');
        List<Card> kingsOfClubs = Collections.nCopies(13, kingOfClubs);
        Card.printDeck(kingsOfClubs, "Kings of Clubs", 1);

//Part-6
/*
        It's first argument is the list I want to add elements to. The second argument is for the elements to be added,
    but unlike list's addAll method, this is a variable argument of elements to be added. The difference is that List's
    addAll method takes a collection of elements to be added. Let me show you this method. Remember, my cards list is
    still empty with an initial capacity of 52 cards. I'll call Collections.addAll, passing it my cards list, and I'll
    just simply pass my card array, of acesOfHearths, to that. And then I'll print out the cards deck. Running this code,

            ---------------------------
            Card Collection with Aces added
            A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12)

    you can see that my cards list has the same cards now as the aces of hearts list. For good measure, what do you think
    happens, If I just execute addAll on cards, passing it this array, Let me add that code, right after the Collections.addAll
    method. "cards.addAll(cardArray)" code doesn't compile, because you can't pass an array to the addAll method on List.
    You can pass a collection, or another list, which is why you'll often see me using List.Of, or Array.asList, as part
    of that method. I'll comment that last line.
*/
//End-Part-6

        Collections.addAll(cards, cardArray);
        Collections.addAll(cards, cardArray);                           // added in Part-7
        //cards.addAll(cardArray);
        Card.printDeck(cards, "Card Collection with Aces added", 2);        // rows changed 1 to 2 in Part-7

//Part-7
/*
        Next I want to examine the copy method on java.util.Collections. First I'll comment last 2 statements. I'll next
    make a call to Collections.copy, passing it my now empty cards list, and the kingsOfClubs list, which had 13 king of
    clubs cards in it. And then I'll print that out by calling print deck. The copy method on collections takes 2 arguments,
    the first is the destination of the copied elements, and here I'm passing my cards list, which I want to be my full
    deck. The second argument is the elements to be copied, and I'm passing the kings of clubs list. You'd expect this to
    copy the elements in kings of clubs to cards, so let me run this.

            Exception in thread in "main" java.lang.IndexOutOfBounds Exception Create breakpoint: Source does not fit in dest

    You can see I've got an error, Source does not fit in dest. This is similar to the problem I had earlier with the fill
    method on Collections. My card list is empty. Remember I commented out the addAll method, so nothing has filled or
    populated, or added elements yet, to my card deck, so it's size is still 0. You can't use this copy method if the
    number of elements in your current list is less than the number of elements in the source list (cards = 0, kingsOfClubs = 13).
    I'll uncomment out those 2 lines of code above, so cards will have 13 aces of hearts in it, before I use the copy
    method. And now running that code,

            ---------------------------
            Card Collection with Kings copied
            K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11)

    you can see my aces in the card list, were replaced with kings. What happens if my destination has more elements than
    the source? Let's add some more cards. I'm just going to copy Collections.addAll statement, pasting a copy right below
    it, so that we get 26 aces added. I'm also going to change the call to print deck, to print on 2 rows,

            Card.printDeck(cards, "Card Collection with Aces added", 1);
            Card.printDeck(cards, "Card Collection with Kings copied", 1);
                                      to
            Card.printDeck(cards, "Card Collection with Aces added", 2);
            Card.printDeck(cards, "Card Collection with Kings copied", 2);

    so it's easier to read the output. And running this,

            ---------------------------
            Card Collection with Aces added
            A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12)
            A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12)
            ---------------------------
            Card Collection with Kings copied
            K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11)
            A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12)

    you can see it only copied over 13 elements, replacing the first 13 aces with kings. What I want you to understand
    here, is that this method copies elements from one list to another, it doesn't return a copy of your list. Elements
    are being assigned to the existing destination list, vs. a copy of the list being made. If you want a full list copy,
    you'd use the List.copy method, so let me revisit that method real quick for you here.
*/
//End-Part-7

        Collections.copy(cards, kingsOfClubs);
        Card.printDeck(cards, "Card Collection with Kings copied", 2);

//Part-8
/*
        I'll reassign cards to the result of List.copyOf, passing it my kingsOfClubs list. And I'll print my cards list
    in a single row.

                ---------------------------
                List Copy of Kings
                K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11)

    This gives us the result, which is the same as the list kingsOfClubs. This method is a true copy, though it's important
    to remember that this method returns an unmodifiable list. If you needed a modifiable copy of the list, you'd make a
    call to the copy of method, and pass it to a List constructor, or use addAll for example. Ok, In the next lecture,
    I'll continue with the discussion of functionality supported on this Collections class.
*/
//End-Part-8

        cards = List.copyOf(kingsOfClubs);
        Card.printDeck(cards, "List Copy of Kings", 1);

    }
}
