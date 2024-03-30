package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course06_CollectionsChallenge_CardGamePart1.games;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course06_CollectionsChallenge_CardGamePart1.games.poker.PokerGame;

public class GameController {

    public static void main(String[] args) {

        PokerGame fiveCardDraw = new PokerGame(4, 5);
        fiveCardDraw.startPlay();

    }
}
