package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev3_EnumConstructor;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev3_EnumConstructor.external.Child;

public class Main {

    public static void main(String[] args) {

        Parent parent = new Parent("Jane Doe", "01/01/1950", 4);
        Child child = new Child();

        System.out.println("Parent: " + parent);
        System.out.println("Child: " + child);

        Person joe = new Person("Joe", "01-01-1950");
        System.out.println(joe);

        Person joeCopy = new Person(joe);
        System.out.println(joeCopy);

        //Generation g;
        Generation g = Generation.BABY_BOOMER;


        //Generation h = new Generation();
        //Generation h = new Generation.BABY_BOOMER(1900, 2000);

    }
}
