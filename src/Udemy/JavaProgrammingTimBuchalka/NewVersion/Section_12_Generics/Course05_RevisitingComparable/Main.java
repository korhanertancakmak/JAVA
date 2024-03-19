package CourseCodes.NewSections.Section_12_Generics.Course05_RevisitingComparable;


//Part-1
/*
                                        Interfaces Used For Sorting

        Now that I've covered interfaces and generic classes, I want to review in more detail, interfaces I mentioned in
    previous lectures. The first is Comparable. For an array, we can simply call Arrays.sort, and pass it an array, but
    as I have previously mentioned, the elements in the array, need to implement Comparable. Types like String, or primitive
    wrapper classes like Integer or Character are sortable, and this is because they do implement this interface.

        The interface declaration in Java:

                        public interface Comparable<T> {
                                int compareTo(T o);
                        }

    It's a generic type, meaning it's parameterized. Any class that implements this interface, needs to implement the
    "compareTo" method. This method takes one object as an argument, shown above as the letter "o", and compares it to
    the current instance, shown as this. The table below shows what the results of the compareTo method should mean, when
    implemented. This method returns an integer.

                        resulting Value                      Meaning
                            zero                            0 == this
                        negative value                      this < o
                        positive value                      this > o

    It should return zero if the two objects being compared are equal. It should return a negative value if this is less
    than "o", or a positive value if this is greater than "o". The best way to get familiar with this method is probably
    to look at it for types you're very familiar with. I'll start by comparing Integers, since it's very easy to understand
    if one number is greater than another.
*/
//End-Part-1

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Integer five = 5;
        Integer[] others = {0, 5, 10, -50, 50};

//Part-2
/*
        In this code, I plan to compare the number five, to each number in that array. Let me add that code, using a for
    each loop. I'll print out a formatted string with multiple specifiers. I'll use a nested ternary, so first, if val
    equals 0, then I can say these 2 numbers are equal, and output a double equals sign. If val less than 0, I want a less
    than sign, otherwise a greater than sign.
*/
//End-Part-2

        for (Integer i :others) {
            int val = five.compareTo(i);
            System.out.printf("%d %s %d: compareTo=%d%n", five, (val == 0 ? "==" : (val < 0) ? "<" : ">"), i, val);
        }

//Part-3
/*
        If I run this code,

                    5 > 0: compareTo=1
                    5 == 5: compareTo=0
                    5 < 10: compareTo=-1
                    5 > -50: compareTo=1
                    5 < 50: compareTo=-1

    you can see that for Integers, the compareTo method is returning only 3 unique values, -1, 0, 1. These values are
    displayed at the end of each of these output lines. If the value is 0, the numbers are equal, and we see that with
    5 == 5 on the second line, and the return value is zero. If the value comes back as -1, that means five is less than
    the array element, so five is less than 10 on line 3, and the less than 50 on line 5 above. If we get 1 back, five
    is greater than the array value, so five is greater than 0 and -50, as shown on the first and fourth lines.

        Ok, now let's look at how Strings have implemented this same method. I'll set up the scenario in the same way with
    a variable called banana.
*/
//End-Part-3

        String banana = "banana";
        String[] fruit = {"apple", "banana", "pear", "BANANA"};

        for (String s : fruit) {
            int val = banana.compareTo(s);
            System.out.printf("%s %s %s : compareTo = %d%n", banana, (val == 0 ? "==" : (val < 0 ? "<" : ">")), s, val);
        }

//Part-4
/*
        Running this code,

                    banana > apple : compareTo = 1
                    banana == banana : compareTo = 0
                    banana < pear : compareTo = -14
                    banana > BANANA : compareTo = 32

    the first thing I want you to see is, I'm not just getting "-1","0" and "1" back for Strings. Comparing banana and
    apple, returns 1, and banana to itself is 0, so that might look like the same result as Integers. But look at "banana"
    compared to "pear". I've got a "-14", so the code is saying banana is less than pear, but it's not a "-1". And comparing
    "banana" in lowercase to "BANANA" in all uppercase gives me "32" back, which means lower case banana is greater than
    uppercase banana, but again we've got a value that's something other than "1", here we have "32". I want to sort this
    list of strings, and print it out.
*/
//End-Part-4

        Arrays.sort(fruit);
        System.out.println(Arrays.toString(fruit));

//Part-5
/*
        Running this code,

                    [BANANA, apple, banana, pear]

    you can see how the strings have been sorted. Are you wondering, what these numbers(1,0,-14,32) coming back from the
    compareTo method mean? Let me add a couple lines of code, which might help you understand what's happening here.
*/
//End-Part-5

        System.out.println("A:" + (int)'A' + " " + "a:" + (int)'a');
        System.out.println("B:" + (int)'B' + " " + "b:" + (int)'b');
        System.out.println("P:" + (int)'P' + " " + "p:" + (int)'p');

//Part-6
/*
        Running this code,

                    A:65 a:97
                    B:66 b:98
                    P:80 p:112

    Here, I'm printing out the capital letter A, and it's underlying integer value. You may remember, chars are stored in
    memory as positive integer values, and that's what this is showing. Capital A is stored as 65. Lowercase a is stored
    as 97. When we use the compareTo method on Strings, we're really comparing the integer values of the characters, in
    the strings. The method will compare the first characters, and if they're the same, it next compares the second characters,
    and so on, returning the difference between the character's underlying integer values. In this example, all my strings
    start with a different letter, so only the first letter will be compared. If we compare "banana" with "apple", we're
    comparing 98(the value for b) with 97(the value for a), and the compareTo method returns the numeric difference, which
    is 1. If we compare "banana" to "pear", we're comparing 98 with 112, and that gives us the difference, -14. And the
    same with comparing lowercase "banana" with uppercase "BANANA", we get 98-66, which is 32. This is how Java implemented
    the compareTo method on the String class.

        Now, I'll create my own class, I'll just call it Student, and put it in the Main.java source file.
*/
//End-Part-6

        Student tim = new Student("Tim");
        Student[] students = {new Student("Zach"), new Student("Tim"), new Student("Ann")};

//Part-8
/*
        That's the setup, and I'm going to compare the student "Tim", to a series of other students. Before we setup the
    for loop, let's just call Arrays.sort on this array, and print the sorted Students array out.
*/
//End-Part-8

        Arrays.sort(students);
        System.out.println(Arrays.toString(students));

//Part-9
/*
        This code compiles, so let me run that:

            Exception in thread "main" java.lang.ClassCastException: class "Student" cannot be cast to class "Comparable"
            "Student" is in unnamed module of loader 'app'; "Comparable" is in module java.base of loader 'bootstrap'

    And you can see, I get a ClassCastException. We get that because our class Student can't be cast to Comparable. This
    is an example, of not being able to use Arrays.sort, on just any class or type we want. Your class has to be derived
    in some way from Comparable, meaning it has to implement Comparable, or an interface that extends Comparable. I'll
    do that now. I'll add "implements Comparable" to Student which force me to override compareTo method. I'll accept
    the default implementation, and ignore IntelliJ's warnings on that class for the moment. If I run the code now:

            [Zach, Tim, Ann]

    I don't get an error, but you can see my students aren't sorted either, at least not alphabetically by name, which
    would be the natural order for this class at this point. That's because my compareTo method on Student, always return
    0, so one Student is never less than or greater than another. That's not a good implementation of this method. I want
    to compare students by their names, so going back to the Student class, I want to look at the compareTo method, it's
    got an argument with the type Object.
*/
//End-Part-9

        System.out.println("result = " + tim.compareTo("Mary"));

//Part-11
/*
        This code compiles, but if i run it:

            Exception in thread "main" java.lang.ClassCastException:
            class java.lang.String cannot be cast to class "Student" (java.lang.String is in module java.base of loader 'bootstrap';
            "Student" is in unnamed module of loader 'app')

    I get another ClassCastException, because my compareTo method on the Student class is trying to cast a String to a
    Student, and that's not a good cast for a String argument. When you implement Comparable on a class, you should specify
    a type parameter.
*/
//End-Part-11
    }
}


class Student implements Comparable {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

//Part-7
/*
        I also want to generate a toString method. I'll do that by overriding toString. And I'll replace super.toString
    with name. Now, I'll go back to the main method, and setup a test, for a few of these Student instances.
*/
//End-Part-7

    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;
        return name.compareTo(other.name);
    }

//Part-10
/*
        If the argument is really going to be a Student, we have to cast this argument to Student, if we want to compare
    names.

                    Before                      After
                   return 0;                Student other = (Student) 0;
                                            return name.compareTo(other.name);

    In this code, I cast the method parameter o, to a Student type, and assign it to a Student variable, called other.
    This lets me compare other to the current instance, so I'll compare the name fields on each, using String's compareTo
    method. Now, If I run the code:

                [Ann, Tim, Zach]

    my list gets sorted alphabetically by name, which is good. Although you can write the compareTo method this way, you
    shouldn't. When I showed you the declaration of the Comparable interface above, you saw that it was a generic type,
    but here
                return name.compareTo(other.name);

    I'm using the raw version. It works, but now I want to discourage you from coding your compareTo method this way. Going
    back to the main method, I'm going to try to compare my Tim Student to a String literal, Mary.
*/
//End-Part-10
}