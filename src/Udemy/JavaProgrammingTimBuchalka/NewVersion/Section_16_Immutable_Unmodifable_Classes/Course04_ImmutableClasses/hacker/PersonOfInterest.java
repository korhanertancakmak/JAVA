package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course04_ImmutableClasses.hacker;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course04_ImmutableClasses.PersonImmutable;

//Part-28
/*
        I'll have this class extend PersonImmutable. I'll insert a constructor, the copy constructor. I also want to
    override the get kids method. I'll make one minor, but important change. I'm not going to return super dot get kids.
    Instead, because I'm a subclass and kids is protected, I can access kids directly from this subclass, so I'll just
    return kids, the field on super. This is all my subclass needs to get access to my data. Let me show you how, in the
    MainImmutable class.
*/
//End-Part-28

public class PersonOfInterest extends PersonImmutable {

    public PersonOfInterest(PersonImmutable person) {
        super(person);
    }

/*
    @Override
    public PersonImmutable[] getKids() {
        return super.kids;
    }
*/
}
