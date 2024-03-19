package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course07_CollectionsChallenge_CardGamePart2.games.poker;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course07_CollectionsChallenge_CardGamePart2.Card;

import java.util.*;
import java.util.function.Consumer;

public class PokerGame {

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

    public void startPlay() {

        Collections.shuffle(deck);
        Card.printDeck(deck);

        int randomMiddle = new Random().nextInt(15, 35);
        Collections.rotate(deck, randomMiddle);
        Card.printDeck(deck);

        deal();
        System.out.println("---------------------------");

//Part-6
/*
        I want to insert a new statement after this separator line, before the forEach call on pokerHands. For fun, I'll
    set up a consumer lambda expression, a method reference for the eval hand method I just created, so PokerHand::evalHand.
    After that, I want to change what I have in the call to the forEach method on the next line, to use the checkHand
    lambda, chaining andThen to that with the method reference to print the hand.

                                    pokerHands.forEach(System.out::println);
                                                        to
                                    pokerHands.forEach(checkHand.andThen(System.out::println));

    In a single statement, I can evaluate the hand and print it. Now, running this code:

                    ---------------------------
                    Current Deck
                    4♦(2) Q♥(10) 8♠(6) 3♦(1) 6♦(4) 8♣(6) K♣(11) 7♣(5) Q♠(10) 6♠(4) 10♣(8) 3♥(1) 10♥(8)
                    J♦(9) A♦(12) 9♠(7) 4♠(2) 4♥(2) K♠(11) 5♥(3) 8♦(6) 4♣(2) J♥(9) 9♣(7) 2♠(0) 7♠(5)
                    7♦(5) 5♣(3) Q♦(10) 6♥(4) 10♠(8) 2♦(0) 5♠(3) 5♦(3) 9♦(7) Q♣(10) 10♦(8) 2♥(0) 6♣(4)
                    A♥(12) 8♥(6) J♠(9) K♥(11) 3♣(1) J♣(9) 9♥(7) 7♥(5) A♠(12) 3♠(1) A♣(12) K♦(11) 2♣(0)
                    ---------------------------
                    1. ONE PAIR         Rank:1 [Q♠(10), 10♥(8), 6♦(4), 4♦(2), 4♠(2)]
                    2. NONE             Rank:0 [Q♥(10), J♦(9), 8♣(6), 6♠(4), 4♥(2)]
                    3. ONE PAIR         Rank:1 [A♦(12), K♣(11), K♠(11), 10♣(8), 8♠(6)]
                    4. ONE PAIR         Rank:1 [9♠(7), 7♣(5), 5♥(3), 3♦(1), 3♥(1)]

    You can see the Player's hands. You might have to run it a couple of times to see some interesting scores, but hopefully
    you can see 1 pair, and 2 pair, and if i keep running it, I might get to see a 3 of a kind or a full house. Going to
    GameController Class,
*/
//End-Part-6

        Consumer<PokerHand> checkHand = PokerHand::evalHand;
        //pokerHands.forEach(System.out::println);
        pokerHands.forEach(checkHand.andThen(System.out::println));

//Part-1
/*
        We're about halfway through building my card game, a poker game called five card draw. I've dealt the hands, but
    I haven't done anything with them. Before I do the evaluation of the cards in a poker hand, I'll create a new List
    for the remaining cards, the ones not dealt. I'll do this in my startPlay method, in PokerGame. First I'll create a
    couple of local variables. cardsDealt is just the player count, times the cards in hand fields. Cards remaining is the
    full deck size, minus the cards dealt.
*/
//End-Part-1

        int cardsDealt = playerCount * cardsInHand;
        int cardsRemaining = deck.size() - cardsDealt;

//Part-2
/*
        I'm going to use nCopies on the Collections class to create a new List. This is not the easiest way to do this,
    but I do want to just revisit this method for a minute. I'll instantiate a new ArrayList. Passing a sublist of deck
    to the constructor is probably the most straightforward way to do this. But instead, I want to make a call to the
    nCopies method on Collections, passing it the number of cards remaining as the first argument, and null as the second
    argument. This basically fills my list with the number of elements specified and null references. Why would I want
    to do this? Well, remember, if we just create an array list with no arguments, or a capacity only, that means we've
    only got a zero element list, so we couldn't use the fill method on collections, or the replaceAll method on list to
    quickly populate our list with elements based on some function. I'll use replaceAll here next to show you what I mean.
    It gets the current index in the remainingCards list, then adds cardsDealt to that to pick the card from the full deck.
    I'll add a lambda expression that will loop through all the elements in my list (which now consists of 32 null
    references). And print remaining cards. You might ask why I'd do something so convoluted. Let me insert another
    statement, where I simply instantiate remaining cards to a new array list with a capacity of the remaining cards
    number. And I'll comment out the next statement with respect to this statement. If I run that:

                ---------------------------
                Remaining Cards

    I don't get an exception, but I don't get remaining cards printed either, and that's because I'm trying to use replaceAll
    on an empty list. If you plan to use replaceAll with a function that would make your code simpler, then this option,
    using nCopies to fill the list with nulls, is one way to prevent the empty list scenario I just showed you. Let me
    revert those last 2 changes. I'll comment

                                remainingCards = new ArrayList<>(cardsRemaining);

    and uncomment

                                remainingCards = new ArrayList<>(Collections.nCopies(cardsRemaining, null));

    Running that code with 4 players,

                ---------------------------
                Remaining Cards
                K♣(11) 8♠(6) 3♣(1) 5♦(3) 4♦(2) 6♦(4) 10♦(8) 10♣(8) 6♠(4) J♦(9) 9♠(7) Q♥(10) K♦(11) A♦(12) A♠(12) 9♥(7)
                9♣(7) 6♥(4) 4♣(2) 4♥(2) 10♥(8) 5♥(3) Q♦(10) J♠(9) 7♥(5) 2♣(0) A♣(12) K♠(11) 6♣(4) J♥(9) 5♣(3) A♥(12)

    I get 32 cards as the remaining cards, because I used 20 cards for my 4 players, it's true, I could have just passed
    the sublist of deck to the array list constructor, but since one of my objectives was to use as many methods on
    Collections, I thought I'd revisit that particular one. Ok, so lastly, I'm going to write the code that can evaluate
    each player's hand. In a real game, you maybe would only do this, if you had some automated competitors, or if you're
    teaching someone how to play. I'm going to evaluate only half of the valid hand ranks, the ones associated with duplicate
    face cards in a hand, for simplicity. Ok, so getting back to the PokerHand, I want to create a method called setRank.
*/
//End-Part-2

        //remainingCards = new ArrayList<>(cardsRemaining);
        remainingCards = new ArrayList<>(Collections.nCopies(cardsRemaining, null));
        remainingCards.replaceAll(c -> deck.get(cardsDealt + remainingCards.indexOf(c)));
        Card.printDeck(remainingCards, "Remaining Cards", 2);
    }

    private void deal() {

        Card[][] hands = new Card[playerCount][cardsInHand];
        for (int deckIndex = 0, i = 0; i < cardsInHand; i++) {
            for (int j = 0; j < playerCount; j++) {
                hands[j][i] = deck.get(deckIndex++);
            }
        }

        int playerNo = 1;
        for (Card[] hand : hands) {
            pokerHands.add(new PokerHand(playerNo++, Arrays.asList(hand)));
        }

    }


}
