package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course07_UnmodifiableCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Part-1
/*
        In the last couple of lectures, I've been using mostly arrays, to demonstrate side effects, and to discuss mutable
    and immutable instances, as well as deep and shallow copies. Now, I want to focus on Collections, rather than Arrays,
    and talk about issues unique to these types. For this session, I'll create a class called Student.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

/*
        StringBuilder bobsNotes = new StringBuilder();
        StringBuilder billsNotes = new StringBuilder("Bill struggles with generics");

        Student bob = new Student("Bob", bobsNotes);
        Student bill = new Student("Bill", billsNotes);

        List<Student> students = new ArrayList<>(List.of(bob, bill));

        students.forEach(System.out::println);
        System.out.println("-----------------------");
*/

//Part-3
/*
        First I'll create a couple of StringBuilder objects, one for bobsNotes, and I'll just create an empty stringBuilder.
    I'll set up bills notes, with a note, that Bill struggles with generics. Now, I'll use these to create my Student
    instances. First, a new student, named bob, and I'll include bobs Notes. Next, Bill, and I'll pass bills notes this
    time. I want a list of my two students, so I'll set up a new ArrayList, and pass that my two students, using the List.Of
    method. And I'll just print my students out, including a separation line. Running this,

                    Student{name='Bob', studentNotes=}
                    Student{name='Bill', studentNotes=Bill struggles with generics}
                    -----------------------

    there shouldn't be any surprises here.
*/
//End-Part-3

        StringBuilder bobsNotes = new StringBuilder();
        StringBuilder billsNotes = new StringBuilder("Bill struggles with generics");

        Student bob = new Student("Bob", bobsNotes);
        Student bill = new Student("Bill", billsNotes);

        List<Student> students = new ArrayList<>(List.of(bob, bill));
        bobsNotes.append("Bob was one of my first students.");

        students.forEach(System.out::println);
        System.out.println("-----------------------");

//Part-3
/*
        Next, I'll insert a statement, after I create the list, but before I print the information, appending some information
    to Bob's notes. I'll add that Bob was one of my first students. Running this code now,

                    Student{name='Bob', studentNotes=Bob was one of my first students.}
                    Student{name='Bill', studentNotes=Bill struggles with generics}
                    -----------------------

    you can see I successfully changed Bob's information, several lines after I created the instance of Bob. This, by
    definition, means Student is mutable. Hopefully you know how to fix this, making a defensive copy in the constructor
    of Student, but right now, I want to leave it like this.
*/
//End-Part-3

        List<Student> studentsFirstCopy = new ArrayList<>(students);

        studentsFirstCopy.forEach(System.out::println);
        System.out.println("-----------------------");

//Part-4
/*
        I'm going to create a copy of my student list. I'll do this by simply creating a new array list, passing the previous
    list to it. List of Student, I'll call it studentsFirstCopy, and set that to a new. I'll copy the last two statements,
    where I'm printing students, and paste a copy just below, and just change students to studentsFirstCopy. If I run
    this again,

                -----------------------
                Student{name='Bob', studentNotes=Bob was one of my first students.}
                Student{name='Bill', studentNotes=Bill struggles with generics}
                -----------------------

    you'll see the same behavior. Bob has notes in the copy as well, even though I made my copy of the list, before I made
    this edit. Passing a list to a constructor of a new list, isn't going to perform a deep copy of your elements, it's
    a shallow copy. But doing this gives you a list that you can edit, adding new elements, or clearing elements, so its
    a modifiable list. I'll add a new student to my new list, the students First copy.
*/
//End-Part-4

        studentsFirstCopy.add(new Student("Bonnie", new StringBuilder()));
        studentsFirstCopy.sort(Comparator.comparing(Student::getName));
        studentsFirstCopy.forEach(System.out::println);
        System.out.println("-----------------------");

//Part-5
/*
        I'll add an element to my copied list, passing a new Student Instance, with Bonnie as the name, and an empty
    stringBuilder for notes. If I run this code,

                Student{name='Bob', studentNotes=Bob was one of my first students.}
                Student{name='Bill', studentNotes=Bill struggles with generics}
                -----------------------
                Student{name='Bob', studentNotes=Bob was one of my first students.}
                Student{name='Bill', studentNotes=Bill struggles with generics}
                Student{name='Bonnie', studentNotes=}
                -----------------------

    my second list has Bonnie, but my first list is unchanged. But again, because student isn't immutable, I can change
    bonnie's notes after the construction of bonnie, and this change will affect the list.
*/
//End-Part-5

        StringBuilder bonniesNotes = studentsFirstCopy.get(2).getStudentNotes();
        bonniesNotes.append("Bonnie is taking 3 of my courses");
        studentsFirstCopy.forEach(System.out::println);
        System.out.println("-----------------------");

//Part-6
/*
        I'll set up a new string builder variable called bonnie's notes. That gets assigned notes from the third element
    on the copied list, so this is Bonnie's notes. I'll make a change to this local variable, and append a note, that
    Bonnie is taking 3 of my courses. Running my code again,

                -----------------------
                Student{name='Bob', studentNotes=Bob was one of my first students.}
                Student{name='Bill', studentNotes=Bill struggles with generics}
                Student{name='Bonnie', studentNotes=Bonnie is taking 3 of my courses}
                -----------------------

    you can see this note is part of my Bonnie object in my second list. To get an unmodifiable collection, I'll use the
    List.copyOf method.
*/
//End-Part-6

        List<Student> studentsSecondCopy = List.copyOf(students);

        studentsSecondCopy.forEach(System.out::println);
        System.out.println("-----------------------");

//Part-7
/*
        I'll set up another variable, students second copy, and assign that the result of calling list.copyOf, with my
    students list as an argument to that. I'll again copy and paste the last two statements, and I'll change studentsFirstCopy
    to studentsSecondCopy. And running this code,

                    -----------------------
                    Student{name='Bob', studentNotes=Bob was one of my first students.}
                    Student{name='Bill', studentNotes=Bill struggles with generics}
                    Student{name='Bonnie', studentNotes=Bonnie is taking 3 of my courses}
                    -----------------------
                    Student{name='Bob', studentNotes=Bob was one of my first students.}
                    Student{name='Bill', studentNotes=Bill struggles with generics}
                    -----------------------

    you can see I again have a copy of my first list. There's a very significant difference between students first copy
    and students second copy.
*/
//End-Part-7

        //studentsSecondCopy.add(new Student("Bonnie", new StringBuilder()));
        //studentsSecondCopy.set(0, new Student("Bonnie", new StringBuilder()));
        //studentsSecondCopy.sort(Comparator.comparing(Student::getName));

//Part-8
/*
        I'll copy the stringBuilder statement for studentsFirstCopy and paste a copy directly below it, changing students
    first copy to students second copy. This code compiles, but if I run it,

                Exception in thread "main" java.lang.UnsupportedOperationException
                at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)

    it throws an exception, unsupported operation exception. For an unmodifiable list, I really can't make any modifications
    to the list. For example, in addition to being unable to remove or add elements, I also cannot reassign an element.
    I'll modify that last statement, instead of adding Bonnie, I'll try to replace element 0 with this new student. Running
    this,

                Exception in thread "main" java.lang.UnsupportedOperationException
                at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)

    I get the same exception. I'll comment that line out. Next, I want to try and sort my students in this copy. I'll pass
    a Comparator, since student doesn't implement Comparable, and I want to sort by the student name. Once again, running
    this,

                Exception in thread "main" java.lang.UnsupportedOperationException
                at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)

    I still get an unsupported operation exception, so I can't even sort an immutable collection. You might have noticed
    the package name in the stack trace, java dot util dot ImmutableCollections. This behavior, and these errors, would
    have you believe that this copy of the list is immutable, but in truth, I did mutate data in one of the elements,
    again this is the notes on bob by using append method. Although the unmodifiable list prevents many alterations, it
    can't prevent all the data from mutating. If the element itself has mutable fields, code can get references to that
    data, as I've shown you in this example.

                                Unmodifiable Collections are NOT immutable Collections

        It is very important to understand that unmodifiable collections are NOT immutable collections. They become
    immutable collections, if the elements in the collections themselves are fully immutable. They are collections with
    limited functionality that can help us minimize mutability. You can't remove, add or clear elements from an immutable
    collection. You also can't replace or sort elements. Mutator methods will throw an UnsupportedOperationException.
    You can't create this type of collection with nulls. I want to change that last statement, from sorting students
    Second Copy to students First Copy. But we have to put this on line 101. This code runs,

                -----------------------
                Student{name='Bill', studentNotes=Bill struggles with generics}
                Student{name='Bob', studentNotes=Bob was one of my first students.}
                Student{name='Bonnie', studentNotes=Bonnie is taking 3 of my courses}
                -----------------------
                Student{name='Bob', studentNotes=Bob was one of my first students.}
                Student{name='Bill', studentNotes=Bill struggles with generics}
                -----------------------

    and you can see the second list is sorted by name. There's one more method I want to look at in comparison, and that's
    the unmodifiable list, on java util collections.
*/
//End-Part-8

        List<Student> studentsThirdCopy = Collections.unmodifiableList(students);
        //studentsThirdCopy.set(0, new Student("Bonnie", new StringBuilder()));

        students.add(new Student("Bonnie", new StringBuilder()));

        studentsThirdCopy.forEach(System.out::println);
        System.out.println("-----------------------");

//Part-9
/*
        I'll set up a list, students third copy, and assign that to Collections dot unmodifiable list, and pass it my
    students. Once again I'll copy and paste the last 2 statements in my main method, this time changing students Second
    Copy to students Third Copy. Running this code,

                -----------------------
                Student{name='Bob', studentNotes=Bob was one of my first students.}
                Student{name='Bill', studentNotes=Bill struggles with generics}
                -----------------------
                Student{name='Bill', studentNotes=Bill struggles with generics}
                Student{name='Bob', studentNotes=Bob was one of my first students.}
                Student{name='Bonnie', studentNotes=Bonnie is taking 3 of my courses}
                -----------------------
                Student{name='Bob', studentNotes=Bob was one of my first students.}
                Student{name='Bill', studentNotes=Bill struggles with generics}
                -----------------------
                Student{name='Bob', studentNotes=Bob was one of my first students.}
                Student{name='Bill', studentNotes=Bill struggles with generics}
                -----------------------

    my last list looks just like my first and third, so it's an accurate copy. Now, let's try a couple of operations on
    this copy. I'll uncomment set method for studentsSecondCopy, and change students second copy to students Third Copy.
    I'll run this,

                Exception in thread "main" java.lang.UnsupportedOperationException
	            at java.base/java.util.Collections$UnmodifiableList.set

    and here again, I get UnsupportedOperationException. Is there any difference between this copy,

                List<Student> studentsSecondCopy = List.copyOf(students);
                List<Student> studentsThirdCopy = Collections.unmodifiableList(students);

    and the one I got back,

                studentsThirdCopy.set(0, new Student("Bonnie", new StringBuilder()));

    from calling copy of on List? Or is it just a legacy method on Collections you don't need to care about. Actually,
    there's a significant difference between what you get back from these two methods. I'll again comment that last line
    out. I'm going to add Bonnie tomy original list here next. I'll insert this, before I edit bobs Notes. And I'll call
    add on my students list, the original list, adding Bonnie. Now, running this code,

                -----------------------
                Student{name='Bob', studentNotes=Bob was one of my first students.}
                Student{name='Bill', studentNotes=Bill struggles with generics}
                -----------------------
                Student{name='Bob', studentNotes=Bob was one of my first students.}
                Student{name='Bill', studentNotes=Bill struggles with generics}
                Student{name='Bonnie', studentNotes=}
                -----------------------

    what do you notice about the last two lists in the output? One has Bonnie, and one doesn't. When we use the copy of
    method, we get a copy of the original list. This means changes to the original list, aren't going to have any effects
    on our copy. Again, that's with the caveat that the elements are purely immutable. That aside, you can see that adding
    Bonnie to students, did not add Bonnie to students Second Copy. We manually added Bonnie to students First Copy, another
    copy earlier. But the last list, the list we got back from Collections.unmodifiable list, students Third Copy, has
    Bonnie in it. This method doesn't return a copy, it actually returns a view, an unmodifiable view, of the backing
    list. This list, students Third Copy, is always going to reflect what's in the Students list, but I could pass this
    view to consumers. This feature creates a kind of window, if you will, to the most current state of a list, without
    allowing a client to modify it. In other words, you can pass a reference to a mutating list, but prevent changes through
    that particular reference. Let me show you a table now, with the unmodifiable collections available to us.

            _________________________________________________________________________________________
            |        | Unmodifiable Copy of Collection  |  Unmodifiable View of Collection          |
            |________|__________________________________|___________________________________________|
            |        | List.copyOf                      |                                           |
            | List   | List.of                          |   Collections.unmodifiableList            |
            |________|__________________________________|___________________________________________|
            |        | Set.copyOf                       |                                           |
            | Set    | Set.of                           |   Collections.unmodifiableSet             |
            |        |                                  |   Collections.unmodifiableNavigableSet    |
            |        |                                  |   Collections.unmodifiableSortedSet       |
            |________|__________________________________|___________________________________________|
            |        | Map.copyOf                       |                                           |
            |        | Map.entry(K k, V v)              |   Collections.unmodifiableMap             |
            | Map    | Map.of                           |   Collections.unmodifiableNavigableMap    |
            |        | Map.ofEntries                    |   Collections.unmodifiableSortableMap     |
            |________|__________________________________|___________________________________________|


    The three primary Collection interfaces, List, Set or Map, have methods to get an unmodifiable copy on the specific
    interface, related to the collection type, as shown, In addition, the java.util.Collections class offers methods,
    to get unmodifiable views as shown. These methods allow us to get closer to the ideal of immutability, if it's needed.
    In the next lecture, there is a challenge for you. After that, I want to revisit constructors, and initialization of
    instance fields.
*/
//End-Part-9

    }
}