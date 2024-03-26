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

        List<LPAStudent> lpaStudents = new ArrayList<>();                             //>>> Commented via Part-12
        for (int i = 0; i < studentCount; i++) {
            lpaStudents.add(new LPAStudent());                                        //>>> Commented via Part-12
            //students.add(new LPAStudent());
        }
        printList(lpaStudents);
        //printList(students);
    }

    public static void printList(List students) {

        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }
}

