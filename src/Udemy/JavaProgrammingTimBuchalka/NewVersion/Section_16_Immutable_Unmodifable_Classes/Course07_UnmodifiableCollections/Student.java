package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course07_UnmodifiableCollections;

//Part-2
/*
        I'll add two fields, a String for name, and a String builder for student notes. I'll make both private and final.
    I've said before, that when we use the final modifier, we need to initialize the fields. I'll be talking about alternate
    initialization methods later, but the usual method is a constructor, and I'll generate that here, with both fields.
    I want getters for my two fields, so I'll generate those. I'll generate a toString method, with both of these fields.
    Ok, that's my Student class, and now I'll get back to the Main class, main method.
*/
//End-Part-2

public class Student {

    private final String name;
    private final StringBuilder studentNotes;

    public Student(String name, StringBuilder studentNotes) {
        this.name = name;
        this.studentNotes = studentNotes;
    }

    public String getName() {
        return name;
    }

    public StringBuilder getStudentNotes() {
        return studentNotes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", studentNotes=" + studentNotes +
                '}';
    }
}
