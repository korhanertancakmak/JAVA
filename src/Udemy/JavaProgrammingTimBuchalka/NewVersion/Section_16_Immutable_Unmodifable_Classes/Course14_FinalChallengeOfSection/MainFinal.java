package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.game.GameConsole;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate.PirateGame;

public class MainFinal {

    public static void main(String[] args) {

        GameConsole<PirateGame> game = new GameConsole<>(new PirateGame("Pirate Game"));

    }
}
