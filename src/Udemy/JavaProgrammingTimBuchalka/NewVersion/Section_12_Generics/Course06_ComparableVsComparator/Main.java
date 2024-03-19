package CourseCodes.NewSections.Section_12_Generics.Course06_ComparableVsComparator;


//Part-1
/*
        In the last course, we looked at how Comparable's compare 2 method was implemented on Integer and String, and we
        started implementing it on our own Student class. But we left off, having implemented Comparable without a type
        parameter. In other words, we used the raw version of Comparable. Let's change that. Going to the Student class,
*/
//End-Part-1

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Integer five = 5;
        Integer[] others = {0, 5, 10, -50, 50};

        for (Integer i :others) {
            int val = five.compareTo(i);
            System.out.printf("%d %s %d: compareTo=%d%n", five, (val == 0 ? "==" : (val < 0) ? "<" : ">"), i, val);
        }

        String banana = "banana";
        String[] fruit = {"apple", "banana", "pear", "BANANA"};

        for (String s : fruit) {
            int val = banana.compareTo(s);
            System.out.printf("%s %s %s : compareTo = %d%n", banana, (val == 0 ? "==" : (val < 0 ? "<" : ">")), s, val);
        }

        Arrays.sort(fruit);
        System.out.println(Arrays.toString(fruit));

        System.out.println("A:" + (int)'A' + " " + "a:" + (int)'a');
        System.out.println("B:" + (int)'B' + " " + "b:" + (int)'b');
        System.out.println("P:" + (int)'P' + " " + "p:" + (int)'p');

        Student tim = new Student("Tim");
        Student[] students = {new Student("Zach"), new Student("Tim"), new Student("Ann")};

        Arrays.sort(students);
        System.out.println(Arrays.toString(students));

        //System.out.println("result = " + tim.compareTo("Mary"));              >>> Commented via Part-5

//Part-5
/*
        And this is actually a good thing that we get errors now, before we run the code, because we don't really want
    to compare a String, to a Student object. I'll change that comparison to an instance of Student instead, passing it
    my name, all in caps.
*/
//End-Part-5

        System.out.println("result = " + tim.compareTo(new Student("TIM")));

//Part-6
/*
        Running this code now:

                ----(same)
                [Ann, Tim, Zach]
                result = 32

    you can see the array is sorted as before, so that's working. And the last line which compares tim in lowercase, to
    TIM in all uppercase, gives us result equals 32. And 32 is the difference between any uppercase letter and lowercase
    letter, as we saw earlier. I've spent a little extra time on this one interface to hopefully help you understand it
    as thoroughly as possible. Sorting and comparing objects, meaning instances of your own classes, will be something
    that you'll be doing a lot. You will use Comparable, when something has a natural order, as we saw here with student
    names. Natural order means that your object's compareTo method, will return a zero if one object is considered equal
    to another, or the equals method returns true, when the compareTo method returns 0. If you had a list of Students who
    could be uniquely identified by name, then this could be true. It's probably more likely that you'd have a Student Id,
    and use Comparable's compareTo method, to sort by student id, for example.

        Next, I want to review Comparator, another interface for sorting and comparing, and talk about the differences
    between those two interfaces.

                                            The Comparator Interface

        The Comparator interface is similar to the Comparable interface, and the two can often be confused with each other.
    Its declaration and primary abstract method are shown here, in comparison to Comparable. You'll notice that the method
    names are different, compare vs. compareTo.

                Comparator                                              Comparable
        public interface Comparator<T> {                           public interface Comparable<T> {
            int compare(T o1, T o2);                                    int compareTo(T o);
        }                                                          }

    The compare method takes two arguments vs. one for compareTo, meaning that it will compare the two arguments to one
    another, and not one object to the instance itself. We'll review Comparator in code, but in a slightly manufactured
    way. It's common practice to include a Comparator as a nested class, which we'll talk more about in the next section
    of the course. But I think it's valuable to talk about these interfaces together, and when to choose or use one or the
    other, or both in your class.

        I want to get back to the Student class, and add a couple of fields.
*/
//End-Part-6

        Comparator<Student> gpaSorter = new StudentGPAComparator();
        //Arrays.sort(students, gpaSorter);                                 >>> Commented via Part-15
        //System.out.println(Arrays.toString(students));

//Part-13
/*
        Ok, so I've got a new instance of my Comparator, notice my reference type there, Comparator with <Student>. I could
    have used var for simplicity, but this is the explicit type. And next, I call sort, passing it the gpaSorter. Now, the
    sort method won't use the Comparable compareTo method. It will instead use this Comparator's compare method when sorting,
    so that's pretty neat. Running this:

                ....(same)
                [1002 - Tim (2,29), 1001 - Zach (3,47), 1003 - Ann (3,95)]

    you can see my students are sorted by gpa, lowest to highest. But that's not what I want, I want to be sorted highest
    to lowest. Now, I could change my Comparator's compare method. Let me show you that.
*/
//End-Part-13

        Arrays.sort(students, gpaSorter.reversed());
        System.out.println(Arrays.toString(students));

//Part-15
/*
        Running that:

                [1003 - Ann (3,31), 1001 - Zach (2,92), 1002 - Tim (2,61)]

    I get my students in reverse gpa order. Let me summarize the differences between these interfaces.

                Comparator(int compare(T o1, T o2);)               |             Comparable(int compareTo(T o);)
       - Compares two arguments of the same type with each other.  |    - Compares the argument with the current instance.
       - Called from an instance of Comparator.                    |    - Called from the instance of the class that implements
                                                                   |      Comparable.
       - Does not require the class itself to implement Comparator,|    - Best practice is to have this.compareTo(o) == 0
         though you could also implement it this way.              |      result in this.equals(o) being true.
       - Array.sort(T[] elements, Comparator<T>) does not require T|    - Arrays.sort(T[] elements) requires T to implement
         to implement Comparable.                                  |      Comparable.

    We'll be revisiting Comparator in our nested types discussion, and we'll have plenty of opportunity to use both when
    we explore more of Java's collection types.
*/
//End-Part-15
    }
}

//Part-11
/*
        Ok, so I've got a class called StudentGPAComparator, and that implements Comparator with a Student type parameter.
    And I've implemented the compare method. To be technically correct, because I am overriding the compare method, I'll
    add the override annotation above the method. I want to compare gpa scores, but if there's a tie, meaning two students
    have the same GPA, I'll sort alphabetically after that.
*/
//End-Part-11

class StudentGPAComparator implements Comparator<Student> {

//Part-12
/*
        Now notice, I have a compiler error, and that's because name is private on Student. For this example, I'll change
    it. In the next section, we're going to make this comparator class a nested type, but for now, it's outside of the
    Student class, and the only way it can access this field, is if we make the name field protected or package private.
    I'll make it package private, meaning I won't specify an access modifier at all. You might want to make this protected,
    like I did for gpa. I wanted to show you that either way will work, in your comparator class. The protected modifier
    would allow subtypes of Student, outside of the "CourseCodes.NewSections.Section_12_Generics.Course06_ComparableVsComparator" package, to
    access the field as well. But really, in this example, I won't have a subtype, so I'll use this variation. That change
    means our custom Comparator class compiles, but how do I use it? Well, it turns out the "Arrays.sort" method has an
    overloaded version, that takes a comparator as the second argument. I'll create a variable of this type in the main
    method, then call sort on my students using it.
*/
//End-Part-12

    @Override
    public int compare(Student o1, Student o2) {
        return (o1.gpa + o1.name).compareTo(o2.gpa + o2.name);
        //return (o2.gpa + o2.name).compareTo(o1.gpa + o1.name);            >>> commented via Part-14
    }

//Part-14
/*
        I'm going to swap o1 with o2 in that return statement. And running it this way:

                [1002 - Tim (2,13), 1001 - Zach (1,95), 1003 - Ann (1,68)]

    you can see I get my students by the GPA in descending order, but you don't really want to do this. You want this
    method to return things in order of lowest to highest, and we've reversed that. Let me revert the last change. The
    Comparator interface, unlike Comparable, comes with many other methods, most of them static helper methods, but some
    are default methods. Many of these are useful streams, so we'll hold of on discussing them until that section, but one
    of them is very convenient for what I want to do here. And that's the reversed default method. I can call that on the
    gpaSorter as I pass it to the Arrays.sort method, in the main method.
*/
//End-Part-14
}

//Part-2
/*
        I'll include the type parameter, and set it to the current type, Student. Because of this change, my code doesn't
    compile any more. I've got the error that the compareTo method isn't implemented. But we have it implemented, below.
    Well, not really. We have a method called compareTo, yes, but now its signature doesn't match the one we need to match,
    because it's not typed correctly. I need to re-implement this method, and I'll again use IntelliJ's feature, to
    automatically add that.
*/
//End-Part-2
class Student implements Comparable<Student> {

    //private String name;                                                  >>> commented via Part-12
    String name;

//Part-7
/*
        First I'll add a couple of private static fields, these fields will exist only in memory on the Student class. I'm
    making them private, because they're only needed inside the Student methods. I'll add the instance fields, student id,
    and GPA, which stands for Grade Point Average, which is how well the student is doing overall.
*/
//End-Part-7

    private static int LAST_ID = 1000;
    private static Random random = new Random();
    private int id;
    protected double gpa;

//Part-8
/*
        Notice gpa is protected, and not private. I'll explain why in a minute. Next I'll change the constructor, and
    assign values to id and gpa, using my static fields.

                        id = LAST_ID++;

    Because LAST_ID is a static there's only one copy in memory. And instance that increments it, increments that one copy,
    meaning no two students should get the same id, since we're keeping the last id in a central place.

                        gpa = random.nextDouble(1.0, 4.0);

    A grade point average can be any value from 0 to 4.0. 4.0 is a A, 3.0 is a B, 2.0 is a C, and so on. Next, I'll change
    the compareTo method, so I'm comparing id's since this is the field that makes our student unique, and we'll sort by
    student id, as the natural sort.
*/
//End-Part-8

    public Student(String name) {
        this.name = name;
        id = LAST_ID++;
        gpa = random.nextDouble(1.0, 4.0);
    }

//Part-10
/*
        I'll return formatted string for id, name and gpa. This will print out the student id, student name, and their
    gpa, with 2 decimal places. Lets run the code:

                ....(same)
                [1001 - Zach (3,18), 1002 - Tim (2,83), 1003 - Ann (3,74)]
                result = -1

    The students are sorted by the assigned student id, as you can see, lowest id to highest. And the result of comparing
    a new TIM student with the existing tim student instance, is a mines one, and this is because the first tim's id is
    always less than the new Tim's id. Ok, so we've got a comparable and sortable student. But what if we want to sort
    by gpa, to figure out who our best students are? We don't want to touch the compareTo method. We could write our own
    mechanism, but we don't have to. We just have to create a class that implements Comparator, comparing two Students.
    I'll do this next. I'll add this class above the Student class in this file.
*/
//End-Part-10

    @Override
    public String toString() {
        //return name;                                                      >>> commented via Part-10
        return "%d - %s (%.2f)".formatted(id, name, gpa);
    }

//Part-4
/*
        But now, I'm still encountering another error which may be new to you. IntelliJ is telling us that "both methods
    have the same erasure, yet neither overrides the other". This is a bit complicated, and I'll be talking about this in
    a later lecture. In summary though, this error means that Java at runtime can't figure out which method here should
    get called, the one with Object as an argument, or the one with Student. I'm going to comment out this entire method,
    the one that has Object as the argument, which will solve the problem for now.

        And now, this class compiles, but in the main method, I've got a compiler error on the last line.
*/
//End-Part-4

/*    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;
        return name.compareTo(other.name);
    }
*/

//Part-3
/*
        Notice now, the argument in this method has a type of Student, not an Object. Now, I'll replace the "return 0"
    statement. I'll make this return "name.compareTo(o.name)". The only difference between these two methods is, I don't
    have to cast in this one, because the argument has the Student type already. But now, I'm still encountering another
    error which may be new to you. IntelliJ is telling us that "both methods have the same erasure, yet neither overrides
    the other". This
*/
//End-Part-3

    @Override
    public int compareTo(Student o) {
        //return 0;                                                         >>> commented via part-3
        //return name.compareTo(o.name);                                    >>> commented via part-9
        return Integer.valueOf(id).compareTo(Integer.valueOf(o.id));
    }

//Part-9
/*
        Since my id field is a primitive int, I'll need to box those in wrappers, to compare the id's. I'll do this manually.
    I'll compare the id field, using Integer.valueOf. You may ask why I just didn't use an int calculation here, returning
    "id - o.id", but it's less error-prone to use Java's comparison of Integers, so I'll just leverage the compareTo method
    on Integer. And I want to print out all the fields I have in the toString method.
*/
//End-Part-9

}