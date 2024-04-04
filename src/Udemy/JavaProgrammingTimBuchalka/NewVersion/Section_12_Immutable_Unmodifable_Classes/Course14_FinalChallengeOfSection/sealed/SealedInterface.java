package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.sealed;

import java.util.function.Predicate;

public sealed interface SealedInterface permits BetterInterface, StringChecker {

    boolean testData(Predicate<String> p, String... strings);
}
