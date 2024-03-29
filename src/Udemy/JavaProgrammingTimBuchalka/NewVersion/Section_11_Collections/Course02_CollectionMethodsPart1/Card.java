package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course02_CollectionMethodsPart1;

import java.util.ArrayList;
import java.util.List;

//Part-2
/*
        First, I'll create a new record in the same package, and call that Card. I'll add my fields in the parentheses
    there, first a field with a type of Suit. That type is going to be an enum I'll create in just a minute, and that
    field will be named suit in lowercase. I'll follow that with a string field called face, and an int named rank.
*/
//End-Part-2

public record Card(Suit suit, String face, int rank) {

//Part-3
/*
        I'm going to insert my enum Suit as a nested type in this record. I'll make it public because other classes, not
    in my package, might use it later down the road. It'll have 4 constants, each representing a suit, so CLUB, DIAMOND,
    HEART, and SPADE. For the standard deck, there's no real difference in ranking for these suits, so my ordinal value
    in this enum isn't really important. In other words, it doesn't matter how I list my constants in this enum, so I'm
    just choosing alphabetical order. I've added a semi colon after the last constant, because I'm going to include an
    additional method on this enum. Next, I want to add a public method, get Image, that just returns the ascii character
    to print for each suit, which I gave you on challenge info at the start of this lecture. I'm just going to set up a
    char array of those values in the same order as my constants, then index that array by the ordinal value. That completes
    the enum.
*/
//End-Part-3

    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;

        public char getImage() {
            return (new char[]{9827, 9830, 9829, 9824})[this.ordinal()];
        }
    }

//Part-4
/*
        For the card record itself, I'll first override the toString method, so I'll insert an override. I'll set up a
    local variable, index, which in all cases will be 1, unless my card is a 10, which is the only case where I want 2
    digits to be retrieved. I'll retrieve either the first number or letter from the face field of the card, or just 10
    if it's 10. I return a formatted string which is the number or face card abbreviation, the suit character, which I
    can get from the get Image method I created, and then the rank of the card. Now, I want a bunch of public static
    methods on this class, to help anyone building a deck, or printing out a list of cards.
*/
//End-Part-4

    @Override
    public String toString() {

        int index = face.equals("10") ? 2 : 1;
        String faceString = face.substring(0, index);
        return "%s%c(%d)".formatted(faceString, suit.getImage(), rank);
    }

//Part-5
/*
        I'll start with the method to return a standard numeric card, so it'll be public static, and the return type is
    Card. I'm calling it getNumericCard, and passing it a specific suit and a cardNumber, so this will be a number between
    2 and 10, for a standard deck. I want to make sure the card number passed is really a number between 2 and 10. If it
    is, I'll return a new card instance with the suit passed, the cardNumber as a string, and a generated rank. I want
    my lowest card to have a rank of zero, and since my lowest card is a 2, I'll subtract 2 here. Finally, if this method
    was called with some other number, I'll print there was a problem. And I'll return null in that case.
*/
//End-Part-5

    public static Card getNumericCard(Suit suit, int cardNumber) {

        if (cardNumber > 1 && cardNumber < 11) {
            return new Card(suit, String.valueOf(cardNumber), cardNumber - 2);
        }
        System.out.println("Invalid Numeric card selected");
        return null;
    }

//Part-6
/*
        The next method, getFaceCard, is going to be very similar, but instead of taking a card number, it will take a
    char, the abbreviation for the face card, so J for Jack, Q for Queen, K for King, and A for ace. I'll validate that
    the character passed is one of those four, by calling the indexOf method, on a string that has those 4 characters. Now,
    this card isn't checking for the proper case, and you could add that, if you wanted your own code to be more robust.
    In general, the mechanism for creating a card will be in the context of creating a deck, so I'm not going to be too
    worried about bad data passed in here. If the index is greater than -1, it's one of the four valid letters. And I'll
    return a new card instance with the suit specified, the abbreviation passed as a string, and again a generated ranking,
    such that a Jack gets the rank of 9, and an Ace gets the highest rank of 12. If the code falls through to this point,
    an invalid character was passed. So I'll return null.
*/
//End-Part-6

    public static Card getFaceCard(Suit suit, char abbrev) {

        int charIndex = "JQKA".indexOf(abbrev);
        if (charIndex > -1) {
            return new Card(suit, "" + abbrev, charIndex + 9);
        }
        System.out.println("Invalid Face card selected");
        return null;
    }

//Part-7
/*
        These 2 methods can be called by our client, but in most normal circumstances, they'll be called by the next method,
    which returns an array list of cards, the standard deck of 52 cards, every card unique by suit and face card value.
    Not all decks are standard. If any of you have played pinochle, you'll know there are other variations. This method
    again will be public and static, it will return a List, and I'm going to specifically make a list of Cards, and the
    name will be getStandardDeck, with no parameters. I'll first set up a local variable, again List, type argument Card,
    and I'm calling that deck, and assigning a new ArrayList with a capacity of 52, since I know this is the size of my
    deck. At the end of this method I'll return this deck. What I need to do between those 2 statements, is add the 52
    cards. I'll loop through my enum values, my suits, using the values method on enum, and a for loop. For each suit,
    I'm going to create a set of numeric cards, then a set of face cards. My first nested loop will loop from 2 to 10.
    And I'll add the card I get back from calling the getNumericCard method, passing it the current suit and the loop
    variable, i. My next nested loop will loop through the 4 characters that represent my face cards, J, Q, K and A. I'll
    add the card, calling getFaceCard to get an instance. That method will give me a deck of 52 cards, in 4 suits, first
    the cards 2 through 10, ranked from 0 to 8, then the face cards, Jack through Ace, ranked from 9 to 13. I want to
    include a couple of overloaded printDeck methods next.
*/
//End-Part-7

    public static List<Card> getStandardDeck() {

        List<Card> deck = new ArrayList<>(52);
        for (Suit suit : Suit.values()) {
            for (int i = 2; i <= 10; i++) {
                deck.add(getNumericCard(suit, i));
            }
            for (char c : new char[]{'J', 'Q', 'K', 'A'}) {
                deck.add(getFaceCard(suit, c));
            }
        }
        return deck;
    }

//Part-8
/*
        These are public static void, named printDeck and take a List, with a type argument of Card. But the first one
    also takes a description, and the number for rows desired to use to print out the deck. I'll always print a separator
    line of dashes to help see different outputs. If the description passed isn't null. I'll print that out on its own
    line. I want a local variable, cards in row, which is the number of cards to be printed on any one row, which is the
    deck size divided by rows, If I passed 4 as rows, and my deck was a standard one, I'd get 13 cards in four rows this
    way. I loop from 0 to 1 less than the rows. The starting index will be i, times the cards in the row, so for the first
    iteration this will be 0, for the second (in a standard deck), it'll be 13, then 26 and so on. The ending index is
    just the starting index plus the cards in the row. Next, I want to use the start and end index to get a sublist of
    my deck, then I chain the forEach method to the sublist, printing each card on the same line, separated by a space.
    After each row of cards, I want a line break. The overloaded version of this method will only have a list of card,
    and will call this method with some defaults. So I'm just going to call the overloaded version, with CurrentDeck as
    the header, and in 4 rows. And actually, I'll move this overloaded version of the print deck method above the other
    one by cutting and pasting it. So that's my set up. I'll test that this code works, in the Main class,
*/
//End-Part-8

    public static void printDeck(List<Card> deck) {
        printDeck(deck, "Current Deck", 4);
    }

    public static void printDeck(List<Card> deck, String description, int rows) {

        System.out.println("---------------------------");
        if (description != null) {
            System.out.println(description);
        }
        int cardsInRow = deck.size() / rows;
        for (int i = 0; i < rows; i++) {
            int startIndex = i * cardsInRow;
            int endIndex = startIndex + cardsInRow;
            deck.subList(startIndex, endIndex).forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
    }

}
