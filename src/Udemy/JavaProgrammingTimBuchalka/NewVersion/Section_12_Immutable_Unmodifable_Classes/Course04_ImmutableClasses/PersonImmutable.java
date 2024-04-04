package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course04_ImmutableClasses;

import java.util.Arrays;

//Part-17
/*
        The first suggestion listed I've shown several times, the strategies for creating an immutable class, is to make
    all fields private, which they already are, and final, which I'll do next. I'll make all three of my private fields
    final. This causes a compiler error in the setKids method. Once the kids field is initialized, it can't be reassigned,
    and that's what it's trying to do in the set method here. I'll get rid of this setter method next, which is actually
    the second suggestion, to eliminate setters in your class. By making your fields final, the compiler can check, if
    your methods do attempt to mutate these fields. The third recommendation is to make defensive copies in all of your
    getters. Does this make sense in getters that return immutable types? No, probably not, but I do have a getter that
    returns a reference to an array.
*/
//End-Part-17

public class PersonImmutable {

    private final String name;
    private final String dob;
    protected final PersonImmutable[] kids;

//Part-19
/*
        I'll return the result of the same ternary operation I used in the getter, returning null if the argument is null,
    or an array copy if not. Let's set up some of these. First, I'll add a new package under Course04, and call that
    external. I'm going to copy the MainRecord class, and paste that in this new package, MainImmutable.
*/
//End-Part-19

    public PersonImmutable(String name, String dob, PersonImmutable[] kids) {
        this.name = name;
        this.dob = dob;
        this.kids = kids == null ? null : Arrays.copyOf(kids, kids.length);
    }

    public PersonImmutable(String name, String dob) {
        this(name, dob, null);
    }

//Part-21
/*
        One of the changes is to add a constructor, what's called a copy constructor, which takes as an argument, an object
    of the same type. Our new developer is going to make this a protected constructor, so that's a good thing, and he'll
    assign its fields, to those values on the person instance, passed to this constructor. I'll set

                                                this.name = person.name
                                                this.dob = person.dob
                                                this.kids = person.kids

    With this code, a subclass can easily construct a new person, using another person to do it, basically making a copy
    of the method argument. Now this developer wants to be able to access kids directly from the subclass, so he'll change
    the access modifier on kids to protected, from private.

                                                private final PersonImmutable[] kids;
                                                protected final PersonImmutable[] kids;

    For good measure, I'll change the two string method, and use the get method for dob, which will allow my subclasses
    to override it. There doesn't seem to be any real harm in these minor changes. In fact, if I try to create a new
    PersonImmutable instance, using this constructor from my MainImmutable class, I can't do it. Let me demonstrate that.
*/
//End-Part-21

    protected PersonImmutable(PersonImmutable person) {
        this(person.getName(), person.getDob(), person.getKids());
    }

//Part-31
/*
        I'll remove the three assignment statements.

                    this.name = person.name;
                    this.dob = person.dob;              to      this(person.getName(), person.getDob(), person.getKids())
                    this.kids = person.kids;

    I'll replace that by chaining to the constructor, using the getters on person to get the data from the method arguments.
    Running the MainImmutable class again,

                    John, dob = 05/05/1900, kids = Jane, Jim, Joe
                    John, dob = 05/05/1900, kids = Jane, Ann, Joe
                    John, dob = 05/05/1900, kids = Jane, Jim, Joe

    you can see that change has protected John, the original John's kids, because of the defensive copy made in the
    constructor that it was chained to. The code can still get a reference and change the Person of Interest's kids.
    Another protection mechanism, would be to make the method getKids final, in the PersonImmutable class, so I'll do
    that. Going back to the Person Of Interest class,
*/
//End-Part-31

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

//Part-18
/*
        I've already established, this is mutable, in both the Person and PersonRecord examples. Like I did in the person
    record example previously, I'll change this code. I'm going to return null, if the array is null, or a copy of the
    array if it isn't. The next strategy is to use constructors to populate data, and this class already does that. I did show you, with the record example,
    that even that, might not be good enough You'll want to make a defensive copy of the input if it's mutable, which prevents the client from maintaining
    a reference to it, and altering it later. I demonstrated that scenario with my johnsKids array, that I passed to the constructor.
    To prevent this, I'll do something similar in the constructor, to what I did in the getter.
*/
//End-Part-18

    public final PersonImmutable[] getKids() {
        return kids == null ? null : Arrays.copyOf(kids, kids.length);
    }

//Part-32
/*
        you can see that I have an error on this method, so I can't override it. I'll remove that method. It's true I
    could create another named method to return super.kids, but this method wouldn't be a polymorphic one, used by some
    other unsuspecting code. Later, I'll be introducing you to sealed classes, which gives you more control over who can
    subclass our classes. But you should be aware that allowing your classes to be subclassed, and providing access to
    fields through the use of a protected modifier, can provide opportunities for unwanted side effects. In this lecture,
    I showed you how to use defensive copies, but what really is the definition of a defensive copy? I've got a challenge
    for you in the next lecture, but after that one, the next lecture will go into detail about the definition of a defensive
    copy.
*/
//End-Part-32

    @Override
    public String toString() {

        String kidString = "n/a";
        if (kids != null) {
            String[] names = new String[kids.length];
            Arrays.setAll(names, i -> names[i] = kids[i] == null ? "" : kids[i].name);
            kidString = String.join(", ", names);
        }
        return name + ", dob = " + getDob() + ", kids = " + kidString;
    }
}
