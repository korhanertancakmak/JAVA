package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course04_ImmutableClasses.external.domain;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course04_ImmutableClasses.PersonImmutable;
import java.util.Arrays;

public class LivingPerson extends PersonImmutable {

    public LivingPerson(String name, PersonImmutable[] kids) {
        super(name, null, kids == null ? new PersonImmutable[10] : Arrays.copyOf(kids, 10));
    }

    public LivingPerson(PersonImmutable person) {
        super(person);
    }

    @Override
    public String getDob() {
        return null;
    }

    public void addKid(PersonImmutable person) {

        for (int i = 0; i < kids.length; i++) {
            if (kids[i] == null) {
                kids[i] = person;
                break;
            }
        }
    }
}
