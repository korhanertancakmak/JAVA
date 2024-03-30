package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course06_CollectionsChallenge_CardGamePart1.games.poker;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course06_CollectionsChallenge_CardGamePart1.Card;
import java.util.ArrayList;
import java.util.List;

public class PokerHand {

    private final List<Card> hand;
    private List<Card> keepers;
    private final List<Card> discards;
    private final Ranking score = Ranking.NONE;
    private final int playerNo;

    public PokerHand( int playerNo, List<Card> hand) {
        //hand.sort(Card.sortRankReversedSuit());
        this.hand = hand;
        this.playerNo = playerNo;
        keepers = new ArrayList<>(hand.size());
        discards = new ArrayList<>(hand.size());
    }

    @Override
    public String toString() {
        return "%d. %-16s Rank:%d %-40s %s".formatted(
                playerNo, score, score.ordinal(), hand,
                (discards.size() > 0) ? "Discards:" + discards : "");
    }
}
