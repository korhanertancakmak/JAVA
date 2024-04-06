package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge.game.GameConsole;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge.game.ShooterGame;

public class Main {

    public static void main(String[] args) {

        var console = new GameConsole<>(new ShooterGame("The Shootout Game"));

        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);
    }
}
