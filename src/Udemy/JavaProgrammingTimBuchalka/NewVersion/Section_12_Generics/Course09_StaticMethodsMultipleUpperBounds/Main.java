package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Generics.Course09_StaticMethodsMultipleUpperBounds;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Generics.Course09_StaticMethodsMultipleUpperBounds.model.*;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Generics.Course09_StaticMethodsMultipleUpperBounds.util.*;

import java.util.ArrayList;
import java.util.List;

//Part-1
/*
        In this lecture, I want to loop back to the generic class, and look again at type declarations. First, I want to
    create an interface, which I'll put in a "util" package and call it "QueryItem".
*/
//End-Part-1

record Employee(String name) implements  QueryItem {

//Part-13
/*
        And we'll just leave the default implementation, because we're really not interested in this record as much as
    we are in the QueryList class. I'll go to the end of the main method, and try to use the QueryList class with this
    new record.
*/
//End-Part-13

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        return false;
    }
}

public class Main {

    public static void main(String[] args) {

        int studentCount = 10;
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            students.add(new Student());
        }
        printMoreLists(students);

        List<LPAStudent> lpaStudents = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            lpaStudents.add(new LPAStudent());
        }
        printMoreLists(lpaStudents);

        testList(new ArrayList<String>(List.of("Able", "Barry", "Charlie")));
        testList(new ArrayList<Integer>(List.of(1, 2, 3)));

//Part-8
/*
        In this code, I want you to notice how I'm creating my list here. First I'm using var, which means the type should
    be inferred, and then I'm assigning it a new instance of my QueryList class. But notice, I don't specify a type argument
    on either side of the assignment operator. I pass my list of LPAStudents to the constructor though, and that's enough
    information for Java, to infer that this class is typed with LPAStudent. I can confirm that, by hovering over the queryList
    variable. If nothing shows up you may have to double click the variable name and then ctrl-Q. I'll do that. IntelliJ
    shows me that I have a QueryList of LPAStudent.

                    QueryList<LPAStudent> queryList = new QueryList<LPAStudent>(lpaStudents)

    Next I execute the getMatches method, which will try to match students taking the python course. I'll print out the
    results with my method from before. And running this code,

                    Cathy U         Python          2018     40,6%
                    Ann G           Python          2020     10,7%
                    Korhan G        Python          2020     63,6%
                    Ann G           Python          2022     24,1%

    I can see that I matched on all the students in my list, taking python. Your results will be different because of the
    random nature of creating the students. So that's kind of fun, but what if I want to use this functionality without
    using the QueryList class itself? Let's say I just want this functionality for any List implementation. Going back
    to QueryList,
*/
//End-Part-8

        var queryList = new QueryList<>(lpaStudents);
        var matches = queryList.getMatches("Course", "Python");
        printMoreLists(matches);

//Part-10
/*
        I'm calling the static method getMatches, passing it my list of students, but not the LPAStudents this time. I
    want to match students who enrolled in 2021. Let me run that:

                Bill G          Java            2021
                Cathy O         Python          2021

    And you can see my list there. Yours will be different, but you see I've filtered the list to only the students who
    started in 2021. Now, I want you to notice a few things. First, that students2021 is inferred to be a List of Student,
    and that's because of the argument I'm passing, which is a List of Students. But let's just see what happens if I'm
    more vague. This static method, getMatches, always returns a list, although it might be empty, if there are no elements
    that match. To demonstrate my point, I'm going to pass it an empty ArrayList, instead of a list already typed with
    a Student.

                var students2021 = QueryList.getMatches(new ArrayList<>(), "YearStarted", "2021");

    So what is "new ArrayList<>()" this really doing? Is it passing an untyped array list? No, it's passing an array list
    that's been typed, inferred to be, the upper bound we declared, a QueryItem. And that's what we get back, and why I've
    got an error on the call to printMoreLists. I can see that if I hover over the students2021 variable.

                Required type : List <? extends Student>
                Provided      : List <QueryItem>

    What we haven't done, is call this method with an explicit type argument, or a type that can be inferred. When the
    type can't be inferred, we can specify the type argument before the method invocation, after the class name and dot,
    namely "QueryList.". I'll add Student in <> before the method call, but after the dot.

                QueryList.<Student>getMatches(new ArrayList<>(), "YearStarted", "2021");

    This is saying, the new list that will get created in the getMatches method, will be a list of Students now. This is
    a bit of a contrived example, but I wanted to show you how you'd specify a type argument for a generic method, that's
    a static method on a class. In most cases, the type can be inferred by the argument being passed. I'll revert those
    last 2 changes.

        Let's go back and look at that generic class, QueryList, again.
*/
//End-Part-10

        var students2021 = QueryList.getMatches(students, "YearStarted", "2021");
        //var students2021 = QueryList.<Student>getMatches(new ArrayList<>(), "YearStarted", "2021");
        printMoreLists(students2021);

//Part-14
/*
                    QueryList<Employee> employeeList = new QueryList<>();

        And you can see IntelliJ flagging that Employee is not within its bound, it should extend Student as well. Employee
    implemented QueryItem, one of the conditions for the upper bound, but a type has to meet all the conditions for it
    to be a valid type. This means Employee is not a subtype of Student, so it's not a valid type argument for our QueryList.
    I'll just comment that line out. Ok, so that's the end of our more advanced topics on Generics. I'll be using examples
    in future code, and reiterating as many of these points as I can, as we continue to build on what we've learned, in
    each section.
*/
//End-Part-14

        //QueryList<Employee> employeeList = new QueryList<>();
    }

    public static void printMoreLists(List<? extends Student> students) {

        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

    public static void testList(List<?> list) {

        for (var element : list) {
            if (element instanceof String s) {
                System.out.println("String: " + s.toUpperCase());
            } else if (element instanceof Integer i) {
                System.out.println("Integer: " + i.floatValue());
            }
        }
    }
}

