package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course01_TheFinalModifier.consumer.specific;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course01_TheFinalModifier.generic.BaseClass;

public class ChildClass extends BaseClass {

    @Override
    protected void optionalMethod() {

        System.out.println("[Child:optionalMethod] EXTRA Stuff Here");
        super.optionalMethod();
    }

/*
    @Override
    public void recommendedMethod() {

        System.out.println("[Child:recommendedMethod]: I'll do things my way");
        optionalMethod();
    }
*/


    private void mandatoryMethod() {
        System.out.println("[Child:mandatoryMethod]: My own important stuff");
    }

}
