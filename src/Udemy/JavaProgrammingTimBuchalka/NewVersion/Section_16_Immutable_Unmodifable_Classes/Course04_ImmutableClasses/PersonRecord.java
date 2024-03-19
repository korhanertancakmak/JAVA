package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course04_ImmutableClasses;

import java.util.Arrays;

//Part-11
/*
        A record solves a lot of the problems for designing an immutable object, but not all. My record, PersonRecord,
    will have the same three fields as the Person class, name, dob, and kids, but for the kids, the type will be Person
    Record. We want a constructor for just name and date of birth. I'll generate a constructor using the standard IntelliJ
    tools, but watch what happens when I generate a constructor in a Record. We get these three options, Compact Constructor,
    Canonical Constructor, and Custom. We're going to ignore the first two options, and select Custom here, which will
    lead us to the standard dialog you normally see. Later in this section, I'll discuss the first two options. Instead
    of passing a zero element array, I'll change 0 to 20. This means I have a placeholder for 20 possible kids. Now, I'm
    going to copy the toString method I had on Person, and paste it here, on this record. Ok, so that's all I need to do
    right now. I'll create a new class called MainRecord, and add a main method.
*/
//End-Part-11

public record PersonRecord(String name, String dob, PersonRecord[] kids) {

    public PersonRecord(String name, String dob) {
        this(name, dob, new PersonRecord[20]);
    }

    @Override
    public String toString() {

        String kidString = "n/a";
        if (kids != null) {
            String[] names = new String[kids.length];
            Arrays.setAll(names, i -> names[i] = kids[i] == null ? "" : kids[i].name);
            kidString = String.join(", ", names);
        }
        return name + ", dob = " + dob + ", kids = " + kidString;
    }

//Part-15
/*
        I'll add a getter, picking the kids field. I'll replace return kids with a statement that first checks if the kids
    are null, if so it returns null, otherwise it will return a copy of the array, I'll use Arrays.copyOf, that takes
    an array, and the length of the array we want to copy. If I rerun my code in the MainRecord class,

                John, dob = 05/05/1900, kids = Jane, Jim, Joe
                John, dob = 05/05/1900, kids = , , , , , , , , , , , , , , , , , , , ,
                John, dob = 05/05/1900, kids = , , , , , , , , , , , , , , , , , , , ,

    you can see, whatever I'm doing in the client code, it's not changing the value of the kids array on JohnCopy. Now,
    you may feel like you've created a type that's immutable, but don't congratulate yourself just yet. Going back to
    MainRecord main method,
*/
//End-Part-15

    @Override
    public PersonRecord[] kids() {
        return kids == null ? null : Arrays.copyOf(kids, kids.length);
    }
}
