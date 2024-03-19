package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course13_SealedClasses.sealed;

import java.util.function.Predicate;

public final class StringChecker implements SealedInterface {

    @Override
    public boolean testData(Predicate<String> p, String... strings) {
        return false;
    }
}
