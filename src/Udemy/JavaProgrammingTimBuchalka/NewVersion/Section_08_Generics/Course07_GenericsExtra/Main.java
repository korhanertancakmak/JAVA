package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course07_GenericsExtra;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course07_GenericsExtra.model.LPAStudent;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course07_GenericsExtra.model.Student;

//Part-1
/*
        You might be wondering what's left to understand about generics. Well, quite a bit actually. I wasn't sure this
    was the right place to cover these additional topics honestly, because I don't want to overwhelm you if you're a
    beginner, or discourage you with too much complexity all at once. But since this is a masterclass, I also want to make
    sure I cover as much as I can, in a reasonable way. If you're already feeling a bit confused about generics, review
    what we've covered so far.

                                What's left to know about Generics?

        In the next few lectures, I want to cover the following topics:

        - Using generic references that use type arguments, declared in method parameters and local variables.
        - Creating generic methods, apart from generic classes.
        - Using wildcards in the type argument.
        - Understanding static methods with generic types.
        - Using multiple upper bounds.

    I'll create a new class, called Student, and this class will go in another package, called model.
*/
//End-Part-1




import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int studentCount = 10;
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            students.add(new Student());
        }
        //printList(students);                                              >>> Commented via Part-12

//Part-12
/*

*/
//End-Part-12

        List<LPAStudent> lpaStudents = new ArrayList<>();                             //>>> Commented via Part-12
        for (int i = 0; i < studentCount; i++) {
            lpaStudents.add(new LPAStudent());                                        //>>> Commented via Part-12
            //students.add(new LPAStudent());
        }
        printList(lpaStudents);
        //printList(students);

//Part-13
/*
        You might remember in a previous lecture, with our generic class team, we simply use the raw version of List in
    the method parameter. Let me show you right here,

                            Before                                                  After
       public static void printList(List<Student> students)       public static void printList(List students)

    that lets our code compile, but IntelliJ is giving us a warning, by highlighting it, and you saw this before, it says,
    "raw use of a parameterized type". But now I can run this code:

                    Bill N          Python          2019     60,7%
                    Korhan D        Java            2018     67,9%
                    Cathy W         Python          2021     15,2%
                    Bill Q          Java            2022     33,2%
                    Ann W           Java            2022     98,2%
                    Korhan C        C++             2020     32,2%
                    Bill Y          Python          2022      9,0%
                    Ann A           Python          2019     38,1%
                    Ann G           C++             2018     79,9%
                    Korhan Q        Python          2023     64,8%

    and I get the second list of 10 LPAStudents, which have percentage complete next to the course. This may seem like a
    good solution, because it worked, but we don't really want to this, which is why IntelliJ is warning us about it. So
    what are the other alternatives? In the next lecture, we'll explore other better options.
*/
//End-Part-13

    }

    public static void printList(List students) {

        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }
}

