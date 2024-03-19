package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course07_CollectionsChallenge_CardGamePart2.games;

import CourseCodes.NewSections.Section_15_Collections.Course07_CollectionsChallenge_CardGamePart2.games.poker.PokerGame;

public class GameController {

    public static void main(String[] args) {

        PokerGame fiveCardDraw = new PokerGame(8, 5);
        fiveCardDraw.startPlay();

//Part-7
/*
        I'll change my players, to let's say 8 players in the GameController, and run it again which lets us see more hands.

                ---------------------------
                1. ONE PAIR         Rank:1 [K♠(11), 6♠(4), 4♥(2), 2♦(0), 2♠(0)]
                2. NONE             Rank:0 [A♥(12), K♦(11), 10♣(8), 9♣(7), 4♣(2)]
                3. ONE PAIR         Rank:1 [A♣(12), A♦(12), J♣(9), 10♥(8), 7♦(5)]
                4. ONE PAIR         Rank:1 [9♥(7), 8♦(6), 8♥(6), 5♦(3), 3♠(1)]
                5. ONE PAIR         Rank:1 [K♥(11), 6♦(4), 6♥(4), 5♥(3), 4♦(2)]
                6. ONE PAIR         Rank:1 [Q♠(10), 9♦(7), 7♥(5), 7♠(5), 3♣(1)]
                7. NONE             Rank:0 [Q♥(10), J♥(9), 10♦(8), 4♠(2), 2♣(0)]
                8. NONE             Rank:0 [A♠(12), J♦(9), 10♠(8), 9♠(7), 3♦(1)]

    And I can keep running that, until I see more interesting hands show up. If you're lucky, you may see even better hands
    like a full house or four of a kind. Ok, so this code meets all of the challenge requirements. Next, for good measure,
    I'm going to populate the discards pile for each player. This will be a suggested list of cards to discard based on
    cards that aren't part of a card rank as well as lower ranked cards. I'll do this in PokerHands.
*/
//End-Part-7

    }
}
