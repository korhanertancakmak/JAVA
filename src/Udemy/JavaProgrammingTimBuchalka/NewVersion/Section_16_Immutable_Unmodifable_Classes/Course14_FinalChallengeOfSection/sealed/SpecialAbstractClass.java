package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.sealed;

public sealed abstract class SpecialAbstractClass permits FinalKid,
        NonSealedKid, SealedKid, SpecialAbstractClass.Kid {

    final class Kid extends SpecialAbstractClass {

    }

}
