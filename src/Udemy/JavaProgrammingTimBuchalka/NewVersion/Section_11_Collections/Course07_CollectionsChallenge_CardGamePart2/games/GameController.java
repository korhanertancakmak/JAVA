package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course07_CollectionsChallenge_CardGamePart2.games;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course07_CollectionsChallenge_CardGamePart2.games.poker.PokerGame;

public class GameController {

    public static void main(String[] args) {

        PokerGame fiveCardDraw = new PokerGame(8, 5);
        fiveCardDraw.startPlay();

    }
}
