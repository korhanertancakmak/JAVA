package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection;

import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.game.GameConsole;
import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate.PirateGame;

public class MainFinal {

    public static void main(String[] args) {

        GameConsole<PirateGame> game = new GameConsole<>(new PirateGame("Pirate Game"));

    }
}
