package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course13_SealedClasses;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course13_SealedClasses.game.GameConsole;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course13_SealedClasses.pirate.PirateGame;

public class MainFinal {

    public static void main(String[] args) {

        GameConsole<PirateGame> game = new GameConsole<>(new PirateGame("Pirate Game"));

    }
}
