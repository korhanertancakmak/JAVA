package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course13_SealedClasses.sealed;

//Part-4
/*
        Instead of adding a permits clause here, I'll add a nested class GrandKid. I'll make the class final, and have it
    extend SealedKid. If I go back to the SpecialAbstractClass,
*/
//End-Part-4

public sealed class SealedKid extends SpecialAbstractClass {

    final class GrandKid extends SealedKid {

    }

}
