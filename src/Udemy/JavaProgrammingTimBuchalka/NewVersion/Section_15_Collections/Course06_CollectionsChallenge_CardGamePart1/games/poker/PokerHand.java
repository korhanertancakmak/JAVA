package CourseCodes.NewSections.Section_15_Collections.Course06_CollectionsChallenge_CardGamePart1.games.poker;

import CourseCodes.NewSections.Section_15_Collections.Course06_CollectionsChallenge_CardGamePart1.Card;
import java.util.ArrayList;
import java.util.List;

public class PokerHand {

//Part-5
/*
        A poker hand needs a list of cards, and I'm going to simply call that hand. This will be the hand that's dealt.
    I want 2 other lists, one for the cards to keep, which I'll call keepers, and one for the cards I might to trade in,
    so discards. The dealer will be the last player, the last hand to be dealt, in other words. This hand will also have
    a ranking, and that'll be my enum type, and I'll initialize that to NONE to start. I also want a player number, an int,
    which is the order of the player, their placement from the dealer. Player1 is immediately next to the dealer, and then
    the dealer will be the last player, the last hand to be dealt, in other words.
*/
//End-Part-5

    private List<Card> hand;
    private List<Card> keepers;
    private List<Card> discards;
    private Ranking score = Ranking.NONE;
    private int playerNo;

//Part-6
/*
        I want to generate a constructor, and pick "hand" and "playerNo" fields for that. I'll reverse the order of the
    parameters, because I prefer to have the playerNo first. Next, I want to sort the hand that was dealt, so I'll just
    call sort on my list, and use my new static method on Card, sortRankReversedSuit, which gives me a Comparator. In
    addition to sorting and initializing hand there, I'll initialize both keepers and discards to a new ArrayList, the
    same size as the hand.
*/
//End-Part-6

    public PokerHand( int playerNo, List<Card> hand) {
        //hand.sort(Card.sortRankReversedSuit());                           >>> Commented via Part-14
        this.hand = hand;
        this.playerNo = playerNo;
        keepers = new ArrayList<>(hand.size());
        discards = new ArrayList<>(hand.size());
    }

//Part-7
/*
        Finally, I want to generate a toString method on this class, and instead of overriding toString, I'll generate it
    with IntelliJ's default way of doing that, but will select none. I'll rewrite the return part by using formatted String.
    And I'll pass the playerNo, the score, our enum, which will print the score name, so 2 Pair, or 2 of a kind for example.
    I'll also print the ordinal value of the score. I'll print the hand, and finally if there are any cards in the discards
    list, I'll print those out. Ok, now before going any further, I want another class, which will be the game, called
    PokerGame, also in the poker package.
*/
//End-Part-7

    @Override
    public String toString() {
        return "%d. %-16s Rank:%d %-40s %s".formatted(
                playerNo, score, score.ordinal(), hand,
                (discards.size() > 0) ? "Discards:" + discards : "");
    }
}
