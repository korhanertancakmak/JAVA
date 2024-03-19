package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course07_CollectionsChallenge_CardGamePart2.games.poker;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course07_CollectionsChallenge_CardGamePart2.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PokerHand {

    private List<Card> hand;
    private List<Card> keepers;
    private List<Card> discards;
    private Ranking score = Ranking.NONE;
    private int playerNo;

    public PokerHand( int playerNo, List<Card> hand) {
        hand.sort(Card.sortRankReversedSuit());
        this.hand = hand;
        this.playerNo = playerNo;
        keepers = new ArrayList<>(hand.size());
        discards = new ArrayList<>(hand.size());
    }

//Part-10
/*
        I'll add some additional text to the format String and 2 specifiers, and I'll insert those before the final %s
    specifier, so Best:%-7s, and Worst:%-6s. And I'll 2 more variables in the list passed, so before the discards part.
    The first will call max on the Collections class, passing the hand, but sorting by rank only. And then I'll call min
    with the same sort. And If I run the code now,

                ---------------------------
                1. ONE PAIR         Rank:1 [K♠(11), 6♠(4), 4♥(2), 2♦(0), 2♠(0)]     Best:K♠(11)  Worst:2♦(0)  Discards:[4♥(2), 6♠(4)]
                2. NONE             Rank:0 [A♥(12), K♦(11), 10♣(8), 9♣(7), 4♣(2)]   Best:A♥(12)  Worst:4♣(2)  Discards:[4♣(2), 9♣(7), 10♣(8)]
                3. ONE PAIR         Rank:1 [A♣(12), A♦(12), J♣(9), 10♥(8), 7♦(5)]   Best:A♣(12)  Worst:7♦(5)  Discards:[7♦(5), 10♥(8)]
                4. ONE PAIR         Rank:1 [9♥(7), 8♦(6), 8♥(6), 5♦(3), 3♠(1)]      Best:9♥(7)   Worst:3♠(1)  Discards:[3♠(1), 5♦(3), 9♥(7)]
                5. ONE PAIR         Rank:1 [K♥(11), 6♦(4), 6♥(4), 5♥(3), 4♦(2)]     Best:K♥(11)  Worst:4♦(2)  Discards:[4♦(2), 5♥(3)]
                6. ONE PAIR         Rank:1 [Q♠(10), 9♦(7), 7♥(5), 7♠(5), 3♣(1)]     Best:Q♠(10)  Worst:3♣(1)  Discards:[3♣(1), 9♦(7)]
                7. NONE             Rank:0 [Q♥(10), J♥(9), 10♦(8), 4♠(2), 2♣(0)]    Best:Q♥(10)  Worst:2♣(0)  Discards:[2♣(0), 4♠(2), 10♦(8)]
                8. NONE             Rank:0 [A♠(12), J♦(9), 10♠(8), 9♠(7), 3♦(1)]    Best:A♠(12)  Worst:3♦(1)  Discards:[3♦(1), 9♠(7), 10♠(8)]

    you can see the best card and worst card listed there. In some card games, one suit may rank higher, when it becomes
    a trump suit for example, and you might need another way of determining what the best card in your hand is. Ok, I'll
    end this challenge code here. I tried to use as many of the Collections methods as possible, even in cases where maybe
    their use was questionable, but I wanted to give you a good review. I used shuffle, reverse, min and max, nCopies,
    frequency, and rotate in this code. Perhaps you found ways to use swap or index of sub list for example.
*/
//End-Part-10

    @Override
    public String toString() {
        return "%d. %-16s Rank:%d %-40s Best:%-7s Worst:%-6s %s".formatted(
                playerNo, score, score.ordinal(), hand,
                Collections.max(hand, Comparator.comparing(Card::rank)),
                Collections.min(hand, Comparator.comparing(Card::rank)),
                (discards.size() > 0) ? "Discards:" + discards : "");
    }

//Part-3
/*
        This will take an integer parameter, I'm calling faceCount, which is the frequency of a face card, for example,
    how many kings are in my hand. I'll use that to determine the ranking and score, of the hand. I'll start with a switch
    on the method argument. Case 4 means I have 4 of a kind, so I'll set the score to that rank. Next, I want to look at
    case 3, meaning I have 3 of a kind, but if I've already found 2 of a kind, then I have a full house, which is both
    3 of a kind and 2 of a kind in 1 hand. I'll check if the current score is NONE, and if it is, I'll set the score is
    3 of a kind, otherwise, I'll set it to FULL_HOUSE. Similarly, when I find 2 cards that have the same face card, I
    want to check if some other match was found. First, I'll check if the current score is NONE, which means this is the
    first pair found, so I'll set the score to ONE_PAIR. Next, I'll check if a 3 of a kind was already identified in the
    hand, and if so, I'll set the score to FULL_HOUSE. Otherwise, this means I have 2 pairs, and I'll set the score to
    that in the last else. Now that I've got a method to set score, I can set up an evalHand method.
*/
//End-Part-3

    private void setRank(int faceCount) {

        switch (faceCount) {
            case 4 -> score = Ranking.FOUR_OF_A_KIND;
            case 3 -> {
                if (score == Ranking.NONE) score = Ranking.THREE_OF_A_KIND;
                else score = Ranking.FULL_HOUSE;
            }
            case 2 -> {
                if (score == Ranking.NONE) score = Ranking.ONE_PAIR;
                else if (score == Ranking.THREE_OF_A_KIND) score = Ranking.FULL_HOUSE;
                else score = Ranking.TWO_PAIR;
            }
        }
    }

//Part-4
/*
        This is going to be a public method that takes no arguments and returns no data. I'm making it public, because
    I may want to allow clients to use it. The first thing I want to do is to create a list of Strings, I'll call it face
    list, the same size as the number of cards in a hand. And I'll use the forEach method on hand, to loop through each
    card in the hand, and populate the list with the face card attributes. Next, I'll create another List of Strings,
    and this will just be the face cards where multiples are found. Later, when I cover Sets, I'll show you a simpler way
    to remove duplicates, but for now, I'll check if this list already has the face card, using the contains method. If
    it doesn't, than I'll also use the frequency method on Collections, passing it my face list, and the current face
    value. "!duplicateFaceCards.contains(face) && Collections.frequency(faceList, face) > 1" will let me check if this
    face card has duplicates, meaning the frequency is greater than 1. If both those conditions are true, then the face
    card will get added to the duplicate Face Cards list. Now that I have a list of face cards that have duplicates, I
    can loop through this list to start ranking my hand. I'll get the index of the first face card in the series of the
    group, using the indexOf method on the face list. I'll assign that to a variable called start. The last index is the
    last face card in the series of multiple cards of the same face card value, and retrieved using lastIndexOf. I can
    call set Rank, getting the number of cards of the same face value, using simple math, so "last - start + 1". I'll
    retrieve the sublist, using the start and last indices I got. I'll add the sublist to my keepers list, or the cards
    I want to keep in my hand. Ok, that's how I'm going to evaluate and score each player's hand. Now I'll get back to
    the PokerGame, and the startPlay method.
*/
//End-Part-4

    public void evalHand() {

        List<String> faceList = new ArrayList<>(hand.size());
        hand.forEach(card -> faceList.add(card.face()));

        List<String> duplicateFaceCards = new ArrayList<>();
        faceList.forEach(face -> {
            if (!duplicateFaceCards.contains(face) && Collections.frequency(faceList, face) > 1) {
                duplicateFaceCards.add(face);
            }
        });

//Part-5
/*
        Now that I have a list of face cards that have duplicates, I can loop through this list to start ranking my hand.
    I'll get the index of the first face card in the series of the group, using the indexOf method on the face list. I'll
    assign that to a variable called start. The last index is the last face card in the series of multiple cards of the
    same face card value, and retrieved using lastIndexOf. I can call set Rank, getting the number of cards of the same
    face value, using simple math, so "last - start + 1". I'll retrieve the sublist, using the start and last indices I
    got. I'll add the sublist to my keepers list, or the cards I want to keep in my hand. Ok, that's how I'm going to
    evaluate and score each player's hand. Now I'll get back to the PokerGame, and the startPlay method.
*/
//End-Part-5

        for (String duplicate : duplicateFaceCards) {
            int start = faceList.indexOf(duplicate);
            int last = faceList.lastIndexOf(duplicate);
            setRank(last - start + 1);
            List<Card> sub = hand.subList(start, last + 1);
            keepers.addAll(sub);
        }

        pickDiscards();                                         // added after part-8
    }

//Part-8
/*
        I'll create a new method on the PokerHand, called pick Discards, again private and void with no parameters. I'll
    create a local variable called temp that will be a copy of my hand, so new Array list and pass it the hand. I'll follow
    that by calling removeAll on the list, removing anything that's in the keepers list. This might be good enough in
    some circles, but you may not want to throw away higher ranked cards, like an Ace or King, so I'm going to check for
    these scenarios. I'll set up a local variable for how many of my cards are part of a card rank, and I'll call that
    rankedCards, equal to the size of keepers. I want to reverse the order of my temporary list of remaining cards, using
    Collections.reverse, so that the lowest ranked cards are first. I'll set up a local variable index, starting at 0,
    then loop through each card in the temp list. I only want to add the card to the discards pile if I don't yet already
    have 3, and if the number of rankedCards is 3 or more, or the rank is less than 9. Otherwise, the card gets added back
    to the keepers pile. I'll make a call to this at the end of the evalHand method.
*/
//End-Part-8

    private void pickDiscards() {

        List<Card> temp = new ArrayList<>(hand);
        temp.removeAll(keepers);
        int rankedCards = keepers.size();
        Collections.reverse(temp);
        int index = 0;
        for (Card c : temp) {
            if (index++ < 3 && (rankedCards > 2 || c.rank() < 9)) discards.add(c);
            else keepers.add(c);
        }
    }

//Part-9
/*
        And running this code:

            ---------------------------
            1. ONE PAIR         Rank:1 [K♠(11), 6♠(4), 4♥(2), 2♦(0), 2♠(0)]     Discards:[4♥(2), 6♠(4)]
            2. NONE             Rank:0 [A♥(12), K♦(11), 10♣(8), 9♣(7), 4♣(2)]   Discards:[4♣(2), 9♣(7), 10♣(8)]
            3. ONE PAIR         Rank:1 [A♣(12), A♦(12), J♣(9), 10♥(8), 7♦(5)]   Discards:[7♦(5), 10♥(8)]
            4. ONE PAIR         Rank:1 [9♥(7), 8♦(6), 8♥(6), 5♦(3), 3♠(1)]      Discards:[3♠(1), 5♦(3), 9♥(7)]
            5. ONE PAIR         Rank:1 [K♥(11), 6♦(4), 6♥(4), 5♥(3), 4♦(2)]     Discards:[4♦(2), 5♥(3)]
            6. ONE PAIR         Rank:1 [Q♠(10), 9♦(7), 7♥(5), 7♠(5), 3♣(1)]     Discards:[3♣(1), 9♦(7)]
            7. NONE             Rank:0 [Q♥(10), J♥(9), 10♦(8), 4♠(2), 2♣(0)]    Discards:[2♣(0), 4♠(2), 10♦(8)]
            8. NONE             Rank:0 [A♠(12), J♦(9), 10♠(8), 9♠(7), 3♦(1)]    Discards:[3♦(1), 9♠(7), 10♠(8)]

    now you'll see the preliminary recommendation of cards to discard. Again, maybe you'd do this for the hands that are
    automated, or if you're teaching someone new how to play. For hands that are 2 pair, you'd only have one card in
    discard pile, because you'll want to keep both pairs presumably. For one pair hands, you'll have 3 cards to discard,
    but cards that are 10 or higher won't be included in the discards. Finally, I want to use the min and max methods
    on collections, to print the hand's highest and lowest cards. I could do this, by just getting the first and last
    card in my sorted hand, it's true, but let's imagine we have a different way of evaluating this, and want a different
    order. I'll go to the toString method on my PokerHand.
*/
//End-Part-9
}
