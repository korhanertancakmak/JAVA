package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course06_CollectionsChallenge_CardGamePart1.games.poker;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course06_CollectionsChallenge_CardGamePart1.Card;
import java.util.*;

public class PokerGame {

    private final List<Card> deck = Card.getStandardDeck();
    private final int playerCount;
    private final int cardsInHand;
    private final List<PokerHand> pokerHands;
    private List<Card> remainingCards;

    public PokerGame(int playerCount, int cardsInHand) {
        this.playerCount = playerCount;
        this.cardsInHand = cardsInHand;
        pokerHands = new ArrayList<>(cardsInHand);
    }

    public void startPlay() {

        Collections.shuffle(deck);
        Card.printDeck(deck);
        //Collections.rotate(deck, 26);
        //Card.printDeck(deck);

        int randomMiddle = new Random().nextInt(15, 35);
        Collections.rotate(deck, randomMiddle);
        Card.printDeck(deck);

        deal();
        System.out.println("---------------------------");
        pokerHands.forEach(System.out::println);

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
