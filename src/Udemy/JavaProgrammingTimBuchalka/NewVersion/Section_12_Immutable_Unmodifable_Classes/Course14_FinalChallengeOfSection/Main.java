package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.game.GameConsole;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate.PirateGame;

public class Main {

    public static void main(String[] args) {
/*
        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);
*/

/*
        Town bridgetown = new Town("BridgeTown","Barbados", 0);
        System.out.println(bridgetown);
        System.out.println(bridgetown.information());
*/


        //PirateGame.getTowns(0).forEach(t -> System.out.println(t.information()));
        //System.out.println("------------------------------------------------------");
        //PirateGame.getTowns(1).forEach(t -> System.out.println(t.information()));


        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);

    }
}



