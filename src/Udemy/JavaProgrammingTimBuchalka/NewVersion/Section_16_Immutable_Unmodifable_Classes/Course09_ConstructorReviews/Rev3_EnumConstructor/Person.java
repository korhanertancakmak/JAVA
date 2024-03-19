package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev3_EnumConstructor;

public record Person(String name, String dob) {

//    public Person(String name, String dob) {
//        this.name = name;
//        this.dob = dob.replace('-', '/');
//    }

    public Person(Person p) {
        this(p.name, p.dob);
    }

    public Person {
        if (dob == null) throw new IllegalArgumentException("Bad data");
        dob = dob.replace('-', '/');
    }
}