package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge.game;

import java.util.function.Predicate;

public record GameAction(char key, String prompt, Predicate<Integer> action) {
}
