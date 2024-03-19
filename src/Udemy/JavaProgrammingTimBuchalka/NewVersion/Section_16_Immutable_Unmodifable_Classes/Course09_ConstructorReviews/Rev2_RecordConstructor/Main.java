package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev2_RecordConstructor;

import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev2_RecordConstructor.external.Child;

//Part-1
/*
        In this lecture, I want to look at record and enum constructors in some detail. I'll continue the same Project
    from the previous lecture. I'll create a record, and call it Person, and include two strings, a name and a date of
    birth, dob.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        Parent parent = new Parent("Jane Doe", "01/01/1950", 4);
        Child child = new Child();

        System.out.println("Parent: " + parent);
        System.out.println("Child: " + child);

        Person joe = new Person("Joe", "01-01-1950");
        System.out.println(joe);

//Part-4
/*
        I'll pass Joe as the name, and a date of birth string, with some dashes. Running this code,

                            Parent static initializer: class being constructed
                            In Parent Initializer
                            In Parent Constructor
                            In Parent Initializer
                            In Parent Constructor
                            Child: Initializer, birthOrder = 3, birthOrderString = Middle
                            Child: Constructor
                            Parent: name='Jane Doe', dob='01/01/1950'
                            Child: name='Jane Doe', dob='02/02/1920', Middle child
                            Person[name=Joe, dob=01/01/1950]

    I get the default String printed for a record, the toString method is another implicitly generated method. Getting
    back to the record,
*/
//End-Part-4

        Person joeCopy = new Person(joe);
        System.out.println(joeCopy);

//Part-8
/*
        I'll set up a test case, in my main method. I'll create a new local variable, joe copy, and assign that a new
    instance of Person, passing my joe variable to that constructor. I'll print this joe copy out. Running that,

                Person[name=Joe, dob=01/01/1950]
                Person[name=Joe, dob=01/01/1950]

    you can see this works, and I've got a copy of Joe's data in my new variable. Getting back to my Person record,
*/
//End-Part-8
    }
}
