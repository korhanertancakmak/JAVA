package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course03_WhenChangeIsGood.consumer.specific;


import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course03_WhenChangeIsGood.generic.BaseClass;

public class ChildClass extends BaseClass {

    @Override
    protected void optionalMethod() {

        System.out.println("[Child:optionalMethod] EXTRA Stuff Here");
        super.optionalMethod();
    }

//    @Override
//    public void recommendedMethod() {
//
//        System.out.println("[Child:recommendedMethod]: I'll do things my way");
//        optionalMethod();
//    }

    private void mandatoryMethod() {
        System.out.println("[Child:mandatoryMethod]: My own important stuff");
    }

    public static void recommendedStatic() {

        System.out.println("[Child.recommendedStatic] BEST Way to Do it");
        optionalStatic();
//        mandatoryStatic();
    }
}
