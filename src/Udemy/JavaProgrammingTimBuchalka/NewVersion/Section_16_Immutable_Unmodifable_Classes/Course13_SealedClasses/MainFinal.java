package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course13_SealedClasses;

import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course13_SealedClasses.game.GameConsole;
import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course13_SealedClasses.pirate.PirateGame;

public class MainFinal {

    public static void main(String[] args) {

        GameConsole<PirateGame> game = new GameConsole<>(new PirateGame("Pirate Game"));

    }
}
