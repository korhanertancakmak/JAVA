package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course06_CollectionsChallenge_CardGamePart1.games.poker;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course06_CollectionsChallenge_CardGamePart1.Card;
import java.util.*;

public class PokerGame {

//Part-8
/*
        This game will have a deck of cards, which I'll initialize to a standard deck, using the static method on Card
    to do that. Each poker game has a player count, the number of players playing, as well as a field called cards in
    hand, because different poker games are played with a different number of cards. Maybe another variable option would
    be whether the game is draw or stud as an example. I'll leave that for another day, and assume the poker games will
    always be played with draw. I also want to maintain a list of the player's hands, and another list that will be the
    deck of remaining cards. I'll generate a constructor for this with the playerCount and cards in hand there. I'll initiate
    my poker Hands list in this constructor as well, by creating a new array list, setting the capacity to cards in hand.
*/
//End-Part-8

    private final List<Card> deck = Card.getStandardDeck();
    private int playerCount;
    private int cardsInHand;
    private List<PokerHand> pokerHands;
    private List<Card> remainingCards;

    public PokerGame(int playerCount, int cardsInHand) {
        this.playerCount = playerCount;
        this.cardsInHand = cardsInHand;
        pokerHands = new ArrayList<>(cardsInHand);
    }

//Part-9
/*
        I want a method on this game, which I'll call start play. It takes no parameters and returns no data, so just
    public void. I'll shuffle the deck here, and print that out.

                                                Collections.shuffle(deck);
                                                Card.printDeck(deck);

    Now, let's say I want to provide a way to cut the deck next. When you cut the deck, you split the deck, often near
    the middle, and put the top part that you cut, under the lower part. I'll call collections.rotate, passing the deck,
    and 26, the exact center of the deck, and I'll again print the deck.

                                                Collections.rotate(deck, 26);
                                                Card.printDeck(deck);

    Let's run and test this, from the GameController's main method.
*/
//End-Part-9

    public void startPlay() {

        Collections.shuffle(deck);
        Card.printDeck(deck);
        //Collections.rotate(deck, 26);
        //Card.printDeck(deck);

//Part-11
/*
        I'll generate a random int, let's just say somewhere between the 15th or 35th card in the deck. I'll insert a statement
    that calls nextInt on a new Random instance with 15 and 35 passed to that method, assigning the result, to a local
    variable called randomMiddle. I'll replace 26 in the rotate call with the value in randomMiddle. If I run this code
    from the main method again:

            ---------------------------
            Current Deck
            7♦(5) 8♣(6) 9♥(7) 10♣(8) 9♦(7) 2♥(0) K♣(11) J♦(9) 9♠(7) 4♥(2) 8♠(6) 6♣(4) 5♦(3)
            K♦(11) 3♣(1) J♣(9) 8♦(6) 7♣(5) K♥(11) A♥(12) 3♠(1) 6♦(4) 6♥(4) 6♠(4) Q♥(10) 3♥(1)
            4♦(2) J♠(9) 4♣(2) A♠(12) 10♦(8) Q♠(10) A♦(12) 10♠(8) A♣(12) Q♣(10) 2♣(0) 5♥(3) Q♦(10)
            5♠(3) 10♥(8) 7♠(5) 8♥(6) 2♠(0) 5♣(3) 3♦(1) 2♦(0) K♠(11) 4♠(2) J♥(9) 7♥(5) 9♣(7)
            ---------------------------
            Current Deck
            3♥(1) 4♦(2) J♠(9) 4♣(2) A♠(12) 10♦(8) Q♠(10) A♦(12) 10♠(8) A♣(12) Q♣(10) 2♣(0) 5♥(3)
            Q♦(10) 5♠(3) 10♥(8) 7♠(5) 8♥(6) 2♠(0) 5♣(3) 3♦(1) 2♦(0) K♠(11) 4♠(2) J♥(9) 7♥(5)
            9♣(7) 7♦(5) 8♣(6) 9♥(7) 10♣(8) 9♦(7) 2♥(0) K♣(11) J♦(9) 9♠(7) 4♥(2) 8♠(6) 6♣(4)
            5♦(3) K♦(11) 3♣(1) J♣(9) 8♦(6) 7♣(5) K♥(11) A♥(12) 3♠(1) 6♦(4) 6♥(4) 6♠(4) Q♥(10)

    it's a bit harder to see where the cards were rotated, but it works in the same way.
*/
//End-Part-11

        int randomMiddle = new Random().nextInt(15, 35);
        Collections.rotate(deck, randomMiddle);
        Card.printDeck(deck);

//Part-14
/*
        Call the deal method. Print each poker hand. Running this code now,

                ---------------------------
                1. NONE             Rank:0 [A♠(12), 10♠(8), 7♠(5), 5♥(3), 3♥(1)]
                2. NONE             Rank:0 [A♣(12), Q♦(10), 10♦(8), 8♥(6), 4♦(2)]
                3. NONE             Rank:0 [Q♣(10), Q♠(10), J♠(9), 5♠(3), 2♠(0)]
                4. NONE             Rank:0 [A♦(12), 10♥(8), 5♣(3), 4♣(2), 2♣(0)]

    you can see the cards in each player's hands. The rank is zero for all hands, because I still have to write the code
    to evaluate the cards. It's a little hard to confirm whether I've dealt these correctly, so for just a minute, I'll
    comment out the sort in the poker hand constructor. And I'll run that again.

                ---------------------------
                Current Deck
                4♦(2) 5♥(3) 2♠(0) K♥(11) 8♠(6) 8♥(6) 9♦(7) 5♠(3) Q♦(10) J♥(9) 10♥(8) 9♣(7) J♣(9)
                K♣(11) 6♠(4) J♠(9) 3♠(1) Q♣(10) 2♦(0) 6♦(4) 8♣(6) 6♣(4) 3♣(1) 9♥(7) 3♦(1) K♠(11)
                2♥(0) 3♥(1) 4♠(2) 2♣(0) 4♣(2) 7♣(5) A♠(12) 10♠(8) 10♣(8) 4♥(2) Q♥(10) 5♣(3) 9♠(7)
                J♦(9) 7♦(5) 8♦(6) 7♠(5) A♥(12) 7♥(5) A♦(12) 10♦(8) K♦(11) Q♠(10) 6♥(4) A♣(12) 5♦(3)
                ---------------------------
                Current Deck
                3♦(1) K♠(11) 2♥(0) 3♥(1) 4♠(2) 2♣(0) 4♣(2) 7♣(5) A♠(12) 10♠(8) 10♣(8) 4♥(2) Q♥(10)
                5♣(3) 9♠(7) J♦(9) 7♦(5) 8♦(6) 7♠(5) A♥(12) 7♥(5) A♦(12) 10♦(8) K♦(11) Q♠(10) 6♥(4)
                A♣(12) 5♦(3) 4♦(2) 5♥(3) 2♠(0) K♥(11) 8♠(6) 8♥(6) 9♦(7) 5♠(3) Q♦(10) J♥(9) 10♥(8)
                9♣(7) J♣(9) K♣(11) 6♠(4) J♠(9) 3♠(1) Q♣(10) 2♦(0) 6♦(4) 8♣(6) 6♣(4) 3♣(1) 9♥(7)
                ---------------------------
                1. NONE             Rank:0 [3♦(1), 4♠(2), A♠(12), Q♥(10), 7♦(5)]
                2. NONE             Rank:0 [K♠(11), 2♣(0), 10♠(8), 5♣(3), 8♦(6)]
                3. NONE             Rank:0 [2♥(0), 4♣(2), 10♣(8), 9♠(7), 7♠(5)]
                4. NONE             Rank:0 [3♥(1), 7♣(5), 4♥(2), J♦(9), A♥(12)]

    Now, it's a little easier to see that my first player's hand starts with the first card, then it has the 5th card in
    the deck, because the other 3 cards went to the other player's hands, and then it has the 9th card and so on. But I
    want to keep my hand sorted, so I'll revert that last change I made to the pokerHand. Since it's getting long, I'll
    end it here with my poker hands dealt out. In the next lecture, I'll set up another list for the remaining cards, and
    I'll evaluate each poker hand's cards.
*/
//End-Part-14

        deal();
        System.out.println("---------------------------");
        pokerHands.forEach(System.out::println);

    }

//Part-12
/*
        Now I need to deal my deck. I've said I want to deal these one card at a time. I'm going to create a new private
    method, again void, called deal with no parameters. I'll first set up a 2D array of Cards, so one array of cards for
    each player. Each player's array will contain the set of cards in hand. To deal the cards one at a time, I'll cycle
    through the deck, one card at a time, for each card in a hand. Then I'll loop through each player, assigning each card
    in each player's hand a unique card off the deck, using the deckIndex variable, to keep track of the place in the full
    deck. I want to create my poker hands from this data.
*/
//End-Part-12

    private void deal() {

        Card[][] hands = new Card[playerCount][cardsInHand];
        for (int deckIndex = 0, i = 0; i < cardsInHand; i++) {
            for (int j = 0; j < playerCount; j++) {
                hands[j][i] = deck.get(deckIndex++);
            }
        }

//Part-13
/*
        So first, I want a variable for the player number. I'll loop through this 2D array, and instantiate a new Poker
    Hand for each player, passing it each player's array of cards, using Arrays.asList in the constructor. Back in the
    startPlay method.
*/
//End-Part-13

        int playerNo = 1;
        for (Card[] hand : hands) {
            pokerHands.add(new PokerHand(playerNo++, Arrays.asList(hand)));
        }

    }


}
