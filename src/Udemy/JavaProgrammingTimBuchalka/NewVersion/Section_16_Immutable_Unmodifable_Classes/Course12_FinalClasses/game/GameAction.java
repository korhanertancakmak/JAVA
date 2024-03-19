package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course12_FinalClasses.game;

import java.util.function.Predicate;

public record GameAction(char key, String prompt, Predicate<Integer> action) {
}
