package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course04_ImmutableClasses.external.domain;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course04_ImmutableClasses.PersonImmutable;

import java.util.Arrays;

//Part-22
/*
        This class is going to extend PersonImmutable, but have special functionality for a Living Person. In fact, this
    class will hide birthdate data for a living person. A date of birth is considered personally identifiable information,
    and needs to be handled differently for living persons. Rather than have to deal with all these requirements, this
    class is going to make sure that birthdate is null. I'll generate two constructors, picking the one with all three
    arguments, and then the copy constructor, the one that takes person immutable as an argument. This gives me two
    constructors, that simply make calls to the super constructors, with the same set of arguments. I want to change the
    first constructor, first removing dob as a parameter. Since, I want the ability to add a new child on this class,
    I'm going to change the kids array a little bit.
*/
//End-Part-22

public class LivingPerson extends PersonImmutable {

//Part-23
/*
        The way I'll do this is, if the kids are null, I'll set kids to a new array of 10 persons. If the kids aren't null,
    I'll copy the kids into a 10 element array.
*/
//End-Part-23

    public LivingPerson(String name, PersonImmutable[] kids) {
        super(name, null, kids == null ? new PersonImmutable[10] : Arrays.copyOf(kids, 10));
    }

    public LivingPerson(PersonImmutable person) {
        super(person);
    }

//Part-24
/*
        Next, I'll override the getter method, for date of birth. I'll replace the call to the super getter, and just
    return null instead, I don't want to expose the date of birth, in other words. Getting back to the MainImmutable class,
*/
//End-Part-24

    @Override
    public String getDob() {
        return null;
    }

//Part-26
/*
        Public void, add Kid, that takes a PersonImmutable type. I'll loop through the kids. As soon as I find a null entry,
    I'll assign that to the person argument. And I'll break out of this. I'll test this out, getting back to the MainImmutable
    class.
*/
//End-Part-26

    public void addKid(PersonImmutable person) {

        for (int i = 0; i < kids.length; i++) {
            if (kids[i] == null) {
                kids[i] = person;
                break;
            }
        }
    }
}
