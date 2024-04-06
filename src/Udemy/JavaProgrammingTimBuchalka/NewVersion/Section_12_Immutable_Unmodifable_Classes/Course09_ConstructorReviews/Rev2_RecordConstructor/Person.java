package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev2_RecordConstructor;

public record Person(String name, String dob) {

/*
    public Person(String name, String dob) {
        this.name = name;
        this.dob = dob.replace('-', '/');
        //this.dob = this.dob.trim();
    }
*/

/*
    {
        this.dob = "01/01/1900";
    }
*/

    public Person(Person p) {
        this(p.name, p.dob);
    }


    public Person {
        if (dob == null) throw new IllegalArgumentException("Bad data");
        dob = dob.replace('-', '/');
        //dob = this.dob.replace('-', '/');

        //this.dob = dob;
    }

}
