package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course06_DefensiveShallowDeepCopies;

import java.util.Arrays;

//Part-1
/*                                            Defensive Copies as Input

        In the last couple of lectures, I've shown you examples of returning defensive copies, of a list and an array,
    from getter methods. I also made a defensive copy of an array, before I assigned it to my array field, in a constructor.

        This demonstration is using a defensive copy as input.

                                      Passing Data to an Immutable Object
                                                                                        Immutable Object
                    Outside Instance        >>>>>>      new()        >>>>>>     Make Defensive Copy Then Assign

    When you pass mutable types to an immutable object, a defensive copy should be made. The defensive copy should then
    be assigned to the instance field.

        This demonstration's using a defensive copy as output. When you retrieve data,

                                      Retrieving Data from an Immutable Object
                    Immutable Object
                     Field Instance        >>>>>>      get()        >>>>>>     Return Defensive Copy

    you should first make a defensive copy, and pass the defensive copy back to the calling code. Making a copy sounds
    easy enough, but unfortunately, that too isn't always perfectly straightforward.

                                        What's a copy? Shallow Copy vs Deep Copy

        This demonstration is showing you two very different copies of an array. A shallow copy only makes a copy of the
    structure, and not a copy of the elements in the structure.

                    Shallow Copy of an Array                                            Deep Copy of Array
       Field Array         Instances         Array Copy         Field Array         Instances      Array Copy          Instances
                         In the Memory                                            In the Memory                      In the Memory
           [0]     >>>>        A        <<<<     [0]                [0]     >>>>        A              [0]     >>>>     A Copy
           [1]     >>>>        B        <<<<     [1]                [1]     >>>>        B              [1]     >>>>     B Copy
           [2]     >>>>        C        <<<<     [2]                [2]     >>>>        C              [2]     >>>>     C Copy
           [3]     >>>>        D        <<<<     [3]                [3]     >>>>        D              [3]     >>>>     D Copy

    A deep copy makes a copy of both the structure, and copies of each element in that structure.

                                            Shallow Copy of an Array

                               Field Array         Instances         Array Copy
                                                 In the Memory
                                   [0]     >>>>        A        <<<<     [0]
                                   [1]     >>>>        B        <<<<     [1]
                                   [2]     >>>>        C        <<<<     [2]
                                   [3]     >>>>        D        <<<<     [3]

        When you use copy methods on interfaces and helper classes, the copy that's made will probably be a shallow copy.
    A shallow copy of an array, means a new array structure is created, with the same number of indexed positions. Each
    indexed position is assigned the same value that was in the previous array, at that same position. A copy of the
    referenced element isn't made. You can see in the diagram above, that both arrays, have indexed references, pointing
    to the same set of instances in memory.

                                                   Deep Copy of Array

                           Field Array         Instances      Array Copy          Instances
                                             In the Memory                      In the Memory
                               [0]     >>>>        A              [0]     >>>>     A Copy
                               [1]     >>>>        B              [1]     >>>>     B Copy
                               [2]     >>>>        C              [2]     >>>>     C Copy
                               [3]     >>>>        D              [3]     >>>>     D Copy

        Deep copies usually have to be manually implemented if you need it. This slide demonstrates a deep copy of an array.
    Each array element has been cloned for the array copy. Deep copies may need to be applied to arrays and collections,
    as well as composite classes, to ensure immutability. It's not just arrays and collections you need to worry about.
    A class can be composed of other classes, meaning its fields are instances of classes.

                                    Shallow Copy of an Object with Mutable Fields

                                                      Instances
                       Object                       In the Memory                        Copy of Object

                       Field_1          >>>>            ABC              <<<<               Field_1

                       Field_2          >>>>            XYZ              <<<<               Field_2
                                                         ↑
                                                        JKL

        When you clone or copy this type of object, you may also need to copy or clone the class's more complex fields.
    This diagram above shows a shallow copy of an instance of a composite class, comparing it to a deep copy.

                                      Deep Copy of an Object with Mutable Fields

                                                      Instances          Instances
                       Object                       In the Memory       In the Memory                    Copy of Object

                       Field_1          >>>>            ABC               ABC Copy          <<<<            Field_1

                       Field_2          >>>>            XYZ               XYZ Copy          <<<<            Field_2
                                                         ↑                   ↑
                                                        JKL       <<<<      JKL

        Nesting of a composite class can be multi-leveled, as I show here with the JKL instance. JKL is a field on XYZ,
    which is a field on our Object. The deep copy created new instances of ABC, and XYZ, the fields on the object, as
    well as a copy Object. It didn't however make a deep copy of XYZ, which is why JKL is still referencing the same
    instance, in the original xyz. Hopefully you can see that when we start making deep copies, we may need to recursively
    copy fields which contain other fields. Let's get back to code, and start looking at making copies, and different ways
    to do this. I've created the usual Main class and main method.
*/
//End-Part-1

record Person (String name, String dob, Person[] kids) {

    public Person(Person p) {
        this(p.name, p.dob, p.kids == null ? null : Arrays.copyOf(p.kids, p.kids.length));
    }

//Part-10
/*
        And add a custom constructor selecting no fields. I'll add an argument, a Person type, and call it just p, to
    save some room. You'll notice that's giving me a compiler error. We'll talk about record constructors a little more
    coming up, but to make this work, I have to call what's called the canonical, or the generated, constructor. I'll
    call this passing name, and dob from the method argument, and I'll pass that ternary operation, passing null if kids
    is null, otherwise, doing a copy of the kids array on p. Now, I can get back to the Main method.
*/
//End-Part-10

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", kids=" + Arrays.toString(kids) +
                '}';
    }
}

//Part-2
/*

        First, I want to create a record, named Person, and right now I just want to have two fields, name and dob, for
    date of birth, both Strings. I'll just put this record in the Main.java file.
*/
//End-Part-2

public class Main {

    public static void main(String[] args) {

/*

        Person joe = new Person("Joe", "01/01/1961");
        Person jim = new Person("Jim", "02/02/1962");
        Person jack = new Person("Jack", "03/03/1963");
        Person jane = new Person("Jane", "04/04/1964");
        Person jill = new Person("Jill", "05/05/1965");

        Person[] persons = {joe, jim, jack, jane, jill};
        Person[] personsCopy = Arrays.copyOf(persons, persons.length);

        for (int i = 0; i < 5; i++) {
            if (persons[i] == personsCopy[i]) {
                System.out.println("Equal References" + persons[i]);
            }
        }
*/

//Part-4
/*

        I'll create five instances of these persons. I'll start with the first, Joe, and make his birth day Jan 1, 1961.
    I'm going to copy that and paste it four times. The first time, I'll change joe to jim, both the variable name, and
    the name passed to the construction of the record, and I'll change the date to Feb. 2, 1962. The next one, I'll change
    to jack, and the date March 3, 1963. After that, I want jane, and birth date April 4, 1964. Finally jill, birth day,
    May 5, 1965. Now I want an array of these persons, and I'll just use an array initializer, with my five person instances
    above. I'll now create a copy, a shallow copy of my Persons array. This should be familiar to you if you followed my
    last couple of lectures. The Person record has two fields, both Strings. Here, I'm making a shallow copy. A deep copy
    of the record would be unnecessary since there's no way to mutate these person records. To confirm that the two arrays
    are referencing the same records, I'll set up a for loop. I'll loop through the 5 elements. I'll compare the element
    in the person array, with the element in personsCopy, at the same indexed position. I'll use equals equals, the equality
    operator, which checks whether the references are equal, and not whether the values are equal. If the references are equal,
    I'll print that out. If I run that code,

                    Equal References Person[name = Joe, dob=01/01/1961]
                    Equal References Person[name = Jim, dob=01/01/1962]
                    Equal References Person[name = Jack, dob=01/01/1963]
                    Equal References Person[name = Jane, dob=01/01/1964]
                    Equal References Person[name = Jill, dob=01/01/1965]

    you can see that both arrays are referencing the same object for all 5 elements. Now, I'm going to change my Person
    record.
*/
//End-Part-4

/*

        Person joe = new Person("Joe", "01/01/1961", null);
        Person jim = new Person("Jim", "02/02/1962", null);
        Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
        Person jane = new Person("Jane", "04/04/1964", null);
        Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

        Person[] persons = {joe, jim, jack, jane, jill};
        Person[] personsCopy = Arrays.copyOf(persons, persons.length);

        for (int i = 0; i < 5; i++) {
            if (persons[i] == personsCopy[i]) {
                System.out.println("Equal References " + persons[i]);
            }
        }
*/

//Part-5
/*

        Like before, I'll include an additional field, kids, an array of Person. I'll also generate a toString method,
    and I only want to include name and kids in that. Next, in the main method, I need to include kids, for each of my
    person records. First I'll include nulls for joe, jim, and jane. Although the birth dates are wrong, I'll make joe
    and jim the children of both jack and jill. Again, running this code,

                    Equal References Person{name='Joe', kids=null}
                    Equal References Person{name='Jim', kids=null}
                    Equal References Person{name='Jack', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
                    Equal References Person{name='Jane', kids=null}
                    Equal References Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}

    I get the data that all the references are still the same. I do have kids for jack and jill, the same kids, Joe and Jim.
    What this output means is, that I still have a shallow copy of my array, the copy I made on,

                                Person[] personsCopy = Arrays.copyOf(persons, persons.length);

*/
//End-Part-5

/*
        Person joe = new Person("Joe", "01/01/1961", null);
        Person jim = new Person("Jim", "02/02/1962", null);
        Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
        Person jane = new Person("Jane", "04/04/1964", null);
        Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

        Person[] persons = {joe, jim, jack, jane, jill};
        Person[] personsCopy = Arrays.copyOf(persons, persons.length);

        var jillsKids = personsCopy[4].kids();
        jillsKids[1] = jane;

        for (int i = 0; i < 5; i++) {
            if (persons[i] == personsCopy[i]) {
                System.out.println("Equal References " + persons[i]);
            }
        }
*/

//Part-6
/*

        Now, I'll get jill's kids, Jill is the last element in my copied array, so element 4, and I'll chain the kids
    accessor method to that. I'll change the second child for Jill, from jim to jane. Running this code,

                    Equal References Person{name='Joe', kids=null}
                    Equal References Person{name='Jim', kids=null}
                    Equal References Person{name='Jack', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
                    Equal References Person{name='Jane', kids=null}
                    Equal References Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}

    all the references are still the same, but what I want you to see, is that Jill's second child is listed as Jane now.
    My code prints the reference, from the original array, so these aren't the copies. Hopefully this example helps you
    see why shallow copies of arrays could cause real problems when your array elements are mutable. You know how to make
    this record immutable, but let's just say, for some reason, we don't want to make this record immutable. Instead I
    want to make a deep copy of my array. How would I go about doing that?
*/
//End-Part-6

/*

        Person joe = new Person("Joe", "01/01/1961", null);
        Person jim = new Person("Jim", "02/02/1962", null);
        Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
        Person jane = new Person("Jane", "04/04/1964", null);
        Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

        Person[] persons = {joe, jim, jack, jane, jill};
        //Person[] personsCopy = Arrays.copyOf(persons, persons.length);
        Person[] personsCopy = new Person[5];

        for (int i = 0; i < 5; i++) {
            Person current = persons[i];
            var kids = current.kids();
            personsCopy[i] = new Person(current.name(), current.dob(), kids);
        }

        var jillsKids = personsCopy[4].kids();
        jillsKids[1] = jane;

        for (int i = 0; i < 5; i++) {
            if (persons[i] == personsCopy[i]) {
                System.out.println("Equal References " + persons[i]);
            }
        }
*/

//Part-7
/*
        I could do it manually, which I'll do first. I'll comment out the Arrays.copyOf statement. I'll create a new persons
    array, calling it persons Copy as before, and initialize that to a new array, with 5 elements. I'll loop from i = 0,
    to i less than 5. I'll set up a couple of local variables, so current will be the current array element, and I'll set
    kids to current.kids. I'll create a new person record, passing it currents name, date of birth, and the kids variable.
    That gets assigned to my persons Copy array element at the same index. Ok, so I have a new record for every array
    element. Running this code,

        What I want you to see is that I don't have any output at all. Remember, my code only prints records, if the
    references in the arrays are the same. Since nothing is printed, this confirms I've created new instances for my
    copied array's indexed elements. In other words, I've made a deep copy of the array. But what does my data look like?
*/
//End-Part-7

/*
        System.out.println(persons[4]);
        System.out.println(personsCopy[4]);

*/

//Part-8
/*
        I'll print the last element of each array. First persons, with index 4. then persons copy, using index 4 again.
    Running my code again,

                Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
                Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}

    I can see my data is the same for both records. But I made a copy of the person record, so why did the change I made,
    changing Jill's kid to Jane, show up in both of these records? Well, even though my person records are different,
    Jill's kids weren't cloned when I made a copy of Jill. This means Jill and Jill's copy are referencing the same array
    of kids. This is an example of the problem I showed you on the diagrams, where nesting can be quite deep. I really
    made a shallow copy of Jill, but I need to make a deep one.
*/
//End-Part-8

/*

        Person joe = new Person("Joe", "01/01/1961", null);
        Person jim = new Person("Jim", "02/02/1962", null);
        Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
        Person jane = new Person("Jane", "04/04/1964", null);
        Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

        Person[] persons = {joe, jim, jack, jane, jill};
        //Person[] personsCopy = Arrays.copyOf(persons, persons.length);
        Person[] personsCopy = new Person[5];

        for (int i = 0; i < 5; i++) {
            Person current = persons[i];
            var kids = current.kids() == null ? null : Arrays.copyOf(current.kids(), current.kids().length);
            personsCopy[i] = new Person(current.name(), current.dob(), kids);
        }

        var jillsKids = personsCopy[4].kids();
        jillsKids[1] = jane;

        for (int i = 0; i < 5; i++) {
            if (persons[i] == personsCopy[i]) {
                System.out.println("Equal References " + persons[i]);
            }
        }

        System.out.println(persons[4]);
        System.out.println(personsCopy[4]);
*/

//Part-9
/*
        I'll change the statement where I'm setting kids in my for loop, to give me the result of a ternary operation.

                          var kids = current.kids();
                                                                to
                          var kids = current.kids() == null ? null : Arrays.copyOf(current.kids(), current.kids().length);

    If the kids array is null, I'll return null, otherwise, I'll return a copy of the kids array. Running that,

                Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
                Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}

        I now have code that doesn't have side effects, for my original array of persons. The original wasn't changed,
    only the copy of Jill, with it's own copy of kids, got changed. I have other options for creating deep copies. I could
    create a copy constructor, which I discussed in a previous lecture. I'll go to my person record,
*/
//End-Part-9

/*

        Person joe = new Person("Joe", "01/01/1961", null);
        Person jim = new Person("Jim", "02/02/1962", null);
        Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
        Person jane = new Person("Jane", "04/04/1964", null);
        Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

        Person[] persons = {joe, jim, jack, jane, jill};
        //Person[] personsCopy = Arrays.copyOf(persons, persons.length);
        Person[] personsCopy = new Person[5];

        for (int i = 0; i < 5; i++) {
            personsCopy[i] = new Person(persons[i]);
        }

        var jillsKids = personsCopy[4].kids();
        jillsKids[1] = jane;

        for (int i = 0; i < 5; i++) {
            if (persons[i] == personsCopy[i]) {
                System.out.println("Equal References " + persons[i]);
            }
        }

        System.out.println(persons[4]);
        System.out.println(personsCopy[4]);
*/

//Part-11
/*
        I can remove the first statements in the loop where I'm copying the person data.

            Person current = persons[i];
            var kids = current.kids() == null ? null : Arrays.copyOf(current.kids(), current.kids().length);

        Now, I'll use the copy constructor I just created, so I can call new Person, and pass the element on the persons
    array at the same index.

            personsCopy[i] = new Person(current.name(), current.dob(), kids);
            personsCopy[i] = new Person(persons[i]);

    If I run my code,

                Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
                Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}

    I get the same result as before. I can simplify my code even further, and instead of the for loop, I can use the
    Arrays.setAll method.
*/
//End-Part-11


        Person joe = new Person("Joe", "01/01/1961", null);
        Person jim = new Person("Jim", "02/02/1962", null);
        Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
        Person jane = new Person("Jane", "04/04/1964", null);
        Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

        Person[] persons = {joe, jim, jack, jane, jill};
        Person[] personsCopy = persons.clone();

        //Person[] personsCopy = new Person[5];
        //Arrays.setAll(personsCopy, i -> new Person(persons[i]));

        var jillsKids = personsCopy[4].kids();
        jillsKids[1] = jane;

        for (int i = 0; i < 5; i++) {
            if (persons[i] == personsCopy[i]) {
                System.out.println("Equal References " + persons[i]);
            }
        }

        System.out.println(persons[4]);
        System.out.println(personsCopy[4]);

//Part-12
/*
        I'll remove the for loop. I'll insert a statement there.

                    Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
                    Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}

        Again, I get the same results. Finally, there's a clone method on arrays. I'll comment out the two statements I
    have here.

                    Person[] personsCopy = new Person[5];
                    Arrays.setAll(personsCopy, i -> new Person(persons[i]));
                                               to
                    Person[] personsCopy = persons.clone();

    Instead, I'll simply call clone on my persons array. Running that code,

                    Equal References Person{name='Joe', kids=null}
                    Equal References Person{name='Jim', kids=null}
                    Equal References Person{name='Jack', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
                    Equal References Person{name='Jane', kids=null}
                    Equal References Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
                    Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
                    Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}

    you can see this clone method on the array, only performs a shallow copy. Ok, so hopefully this cleared up any questions
    you might have about what shallow and deep copies really are. It's important to try to figure out, when you need to
    create a deep copy and how deep, when you're creating defensive copies. In the next lecture, I'll be discussing
    UnmodifiableCollections.
*/
//End-Part-12

    }
}
