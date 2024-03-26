package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course10_StudentChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course10_StudentChallenge.model.*;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course10_StudentChallenge.util.*;

import java.util.Comparator;
import java.util.List;

//Part-1
/*
        In this challenge, I want you to start with some of the code we just talked about in the last lecture. Be sure to
    start with the Student and LPAStudent classes, and the QueryItem interface and QueryList class. In this challenge,
    you'll want to do the following items:

            - Change QueryList to extend ArrayList, removing the items field.
            - Add a student id field to the Student class, and Implement a way to compare Students, so that students are
              naturally ordered by a student id.
            - Implement at least one other mechanism for comparing Students by course or year, or for LPA Students, by
              percent complete.
            - Override the matchFieldValue method on the LPAStudent class, so that you return students, not matched on
              percent complete equal to a value, but on percent less than or equal to a submitted value.

    Note : An LPA Student should be searchable by the same fields as Student as well.

            - Run your code for 25 random students, selecting students who are less than or equal to 50% done their course,
              and print out the list, sorted in at least two ways, first by using List.sort with the Comparator.naturalOrder()
              comparator, and then using your own Comparator, so first by student id, as well as one of the other ways
              you selected.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

//Part-7
/*
        Since I've actually extended and modified my own custom list implementation, I no longer want to declare the
    variable using List. I want to use QueryList because I want to use functionality specific to this new class.
*/
//End-Part-7

        QueryList<LPAStudent> queryList = new QueryList<>();

//Part-8
/*
        Now, I have an empty QueryList, so I'll start adding some students. I want to create instances of the LPAStudent
    to add, and I'll set up a loop to do this. I'll set it to loop 5 times. And add a new instance of LPA student, to
    queryList, using the add method.
*/
//End-Part-8

        for (int i = 0; i < 25; i++) {
            queryList.add(new LPAStudent());
        }

//Part-9
/*
        And now, I'll try executing a sort on this list, using the inherited ArrayList's sort method. This method takes
    a Comparator, which I covered in a previous lecture, so I can retrieve a natural order comparator, from a Comparator
    interface method. I'm going to test the code with 5 students to start, just so the output is easier to read. I'll
    change it to 25, as we start to execute queries against it.
*/
//End-Part-9

        System.out.println("Ordered");
        queryList.sort(Comparator.naturalOrder());
        printList(queryList);

//Part-10
/*
        Ok, so here, I have an error on QueryList.sort. Hovering over the error, the message says, "no instances of type
    variable exists, so that Student conforms to Comparable<? super T>". This message indicates that the sort method is
    using a lower bounded wildcard, and is looking up the hierarchy of LPAStudent, for an object that implements Comparable,
    and it can't find one. That's because neither the LPAStudent class, or its parent class, Student, implements Comparable
    yet. I want to just show you something else. Instead of passing Comparator.naturalOrder to that method, I can actually
    pass a null reference.

                        queryList.sort(Comparator.naturalOrder());
                                        to
                                queryList.sort(null);

    This compiles but if I run it,

            Exception in thread "main" java.lang.ClassCastException: class LPAStudent cannot be cast to class java.lang.Comparable
            LPAStudent is in unnamed module of loader 'app'; java.lang.Comparable is in module java.base of loader 'bootstrap'

    I get a class cast exception, LPAStudent cannot be cast to class Comparable. Let's leave the code this way for the
    moment. I'll come back to it once we have implemented something it needs. Right now, I do want to include a printList
    method, to print out the student list. Go to Part-11, and come back from Part-13,

        Now, this code compiles and I can run it,

                    Ordered
                    10000 John D          Java            2023     33,2%
                    10001 John L          C++             2022     19,0%
                    10002 John S          Java            2019     76,2%
                    10003 Ann M           Java            2022     30,9%
                    10004 John A          Java            2018     26,1%

    and I get my five students printed out in student id order. Unfortunately, the natural order, is the same order I
    added the elements, so it's kind of hard to say if this was really a successful sort. But I'll do a better test of
    that shortly. Right now, I want to go to the LPAStudent class.
*/
//End-Part-10

        System.out.println("Matches");
        var matches = queryList.getMatches("PercentComplete", "50")
                                                   .getMatches("Course", "Python");
        //printList(matches);                                   >>> Commented via Part-18

//Part-15
/*
        I'll add another heading in the output. Running that code, hopefully, I'll get a few matches, for percentComplete
    less than or equal to 50.

                    Ordered
                    10000 John S          C++             2023     10,7%
                    10001 Ann R           C++             2019      4,7%
                    10002 Ann U           C++             2022     72,9%
                    10003 Korhan C        Python          2019     25,1%
                    10004 Bill I          C++             2018     93,6%
                    Matches
                    10000 John S          C++             2023     10,7%
                    10001 Ann R           C++             2019      4,7%
                    10003 Korhan C        Python          2019     25,1%

    Let's change the number of students to 25 so we have a bigger sample of students to work with. And now, I get quite
    a few more matches for students who are less than or equal to 50% done. And because I'm getting a queryList back from
    that method call, I can chain another match request directly to it:

                    var matches = queryList.getMatches("PercentComplete", "50");
                                                to
                    var matches = queryList.getMatches("PercentComplete", "50");

    And now I'll run that:

                    ---(too long)
                    Matches
                    10013 Ann H           Python          2019     37,1%
                    10015 Korhan X        Python          2021      9,1%
                    10019 Bill V          Python          2019     47,5%

    Now, I get back the students who are less than or equal to 50 percent done, and who are taking the Python course. We
    have one last requirement left, and that's to implement a different sort. Remember from the Comparator video, that if
    you want to sort differently, without changing the most common, or the natural order sort, you can implement a
    Comparator. So let me do that. I'm going to create a new class in the model package, and call it LPAStudentComparator.
*/
//End-Part-15

        matches.sort(new LPAStudentComparator());
        printList(matches);

//Part-18
/*
        And running the code:

                Matches
                10012 John F          Python          2018      7,8%
                10024 Bill V          Python          2023     23,7%

    my matches are ordered by percent complete, ascending. And for good measure, I'll use the Comparable, or student id
    sort, right after this, which will be better test, to test the student id test. I'll print Ordered out first.
*/
//End-Part-18

        System.out.println("Ordered");
        matches.sort(null);
        printList(matches);

//Part-19
/*
        And running that:

                Matches
                10022 John H          Python          2023     24,6%
                10006 Korhan R        Python          2019     29,5%
                10012 Ann D           Python          2021     36,0%
                10008 John O          Python          2018     41,3%
                10023 Ann T           Python          2019     44,8%
                Ordered
                10006 Korhan R        Python          2019     29,5%
                10008 John O          Python          2018     41,3%
                10012 Ann D           Python          2021     36,0%
                10022 John H          Python          2023     24,6%
                10023 Ann T           Python          2019     44,8%

    you can see the matches are first ordered by percent complete, and then ordered by student id. And that completes
    this challenge.
*/
//End-Part-19
    }

//Part-11
/*
        Notice I'm using an unbounded wildcard in the method parameter's type argument. Then I just loop trough the list,
    and print each element. Ok, I'll come back to this after I implement Comparable on Student. Comparable is a typed
    interface, so I want to use it with a type argument. In this case, I use the class we're implementing it for, so
    Student. Going Student class,
*/
//End-Part-11

    public static void printList(List<?> students) {

        for (var student : students) {
            System.out.println(student);
        }
    }
}

