package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.sealed;

import java.util.function.Predicate;

public final class StringChecker implements SealedInterface {

    @Override
    public boolean testData(Predicate<String> p, String... strings) {
        return false;
    }
}
