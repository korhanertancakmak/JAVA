package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course01_TheFinalModifier;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course01_TheFinalModifier.consumer.specific.*;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course01_TheFinalModifier.generic.BaseClass;

public class Main {

    public static void main(String[] args) {

        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();
        BaseClass childReferredToAsBase = new ChildClass();

        parent.recommendedMethod();
        System.out.println("--------------------");
        childReferredToAsBase.recommendedMethod();
        System.out.println("--------------------");
        child.recommendedMethod();

        System.out.println("--------------------");
        parent.recommendedStatic();
        System.out.println("--------------------");
        childReferredToAsBase.recommendedStatic();
        System.out.println("--------------------");
        child.recommendedStatic();

    }
}
