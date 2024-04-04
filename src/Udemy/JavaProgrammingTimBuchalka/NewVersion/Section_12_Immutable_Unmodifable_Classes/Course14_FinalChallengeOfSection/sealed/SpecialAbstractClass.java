package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.sealed;

public sealed abstract class SpecialAbstractClass permits FinalKid,
        NonSealedKid, SealedKid, SpecialAbstractClass.Kid {

    final class Kid extends SpecialAbstractClass {

    }

}
