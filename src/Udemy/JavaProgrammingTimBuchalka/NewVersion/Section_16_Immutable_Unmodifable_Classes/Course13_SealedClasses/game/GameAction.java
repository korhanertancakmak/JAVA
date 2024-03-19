package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course13_SealedClasses.game;

import java.util.function.Predicate;

public record GameAction(char key, String prompt, Predicate<Integer> action) {
}
