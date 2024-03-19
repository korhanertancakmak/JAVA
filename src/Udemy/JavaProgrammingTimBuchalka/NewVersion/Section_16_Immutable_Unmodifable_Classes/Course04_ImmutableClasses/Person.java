package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course04_ImmutableClasses;

import java.util.Arrays;

//Part-2
/*
        I'll add some fields, using basic encapsulation techniques. For this exercise, I'm just going to have name, date
    of birth, dob, and I'll just make that a string. And I'll have a field, kids, an array of my person class. I'll generate
    both getters and setters for all the fields. I'll also generate a toString method, but select none for the fields.
*/
//End-Part-2

public class Person {

    private String name;
    private String dob;
    private Person[] kids;

//Part-5
/*
        I'll generate the first with all three fields. The second constructor will just have name and date of birth, dob.
    I want to remove those statements. Instead, I'll chain a call to the other constructor, and pass null as the kids
    argument. Now let's say, I really don't want the code to change name or date of birth, after the person object is
    constructed. To protect against this, I'll remove the setters for those two fields. First, I'll remove the setName
    method, then I'll remove the set dob method. Getting back to the main method in Main,
*/
//End-Part-5

    public Person(String name, String dob, Person[] kids) {
        this.name = name;
        this.dob = dob;
        this.kids = kids;
    }

    public Person(String name, String dob) {
        this(name, dob, null);
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public Person[] getKids() {
        return kids;
    }

    public void setKids(Person[] kids) {
        this.kids = kids;
    }

//Part-3
/*
        I'll add some code, to print out the person data. First, I want to deal with printing out the kids information.
    If there are no kids, I'll print n slash a, for not applicable. If there are kids, I'll set up a names array, because
    I only want to print a list of the kids names. I'll return name, date of birth and the kid string. Next, I'll add
    the code to populate the names array, and build the kid string. I'll use the set all method on the arrays helper
    class, to populate my names array. The parameter for the lambda is an integer, an index. I'll first use that to get
    the child from the kids array. That could be null, so I'll use a ternary to check for that, and set the name to an
    empty String, otherwise I'll set it to the kid's name. I'll use the join method on string, joining all the kids names
    by a comma. Ok, so this is a typical class, pretty well encapsulated. The only way to access the data is with getters
    and setters. I didn't even set up a constructor. I'll create a couple of persons for my genealogy database, in the
    main method.
*/
//End-Part-3

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
}
