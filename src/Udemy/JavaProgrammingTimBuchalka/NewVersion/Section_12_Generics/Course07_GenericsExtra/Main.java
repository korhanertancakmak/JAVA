package CourseCodes.NewSections.Section_12_Generics.Course07_GenericsExtra;

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


import CourseCodes.NewSections.Section_12_Generics.Course07_GenericsExtra.model.LPAStudent;
import CourseCodes.NewSections.Section_12_Generics.Course07_GenericsExtra.model.Student;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//Part-8
/*
        Here, I start with the number of students I want, so that's 10 for now, and then I set up an ArrayList of Students.
    I assign that to the students variable, which I've declared as a List, with a type argument of Student in angle brackets.
    Then I loop from 0 to that count, and add a new Student each time to the list. And lastly, I want to invoke the printList
    method. I'll pass students to this method. If I run this:

                John H          Java            2022
                Ann M           C++             2020
                Cathy A         Java            2019
                Cathy A         Java            2020
                Ann R           C++             2022
                Ann A           Python          2020
                John I          Java            2018
                Cathy R         Java            2022
                Bill C          Python          2022
                Korhan R        Python          2020

    you can see 10 students, whose name, course year started, were randomly generated. Ok, so that's the set up for a variety
    of students. I next want to create a subclass of Student, which I'll call LPAStudent, also in the model package.
*/
//End-Part-8

        int studentCount = 10;
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            students.add(new Student());
        }
        //printList(students);                                              >>> Commented via Part-12

//Part-12
/*
        I'll copy the code above and past it below, I'll change Student to LPAStudent. But we have a problem, you can see
    that, on the last statement, the call to the static method here. Java tells us "required type is a List of Student,
    and we're providing an ArrayList of LPAStudent." Isn't this valid? Well, no it's not. And this gets pretty confusing
    I know.

                                                This isn't inheritance

        We know LPAStudent inherits from Student, and we can pass an instance of LPA Student to any method, or assign it
    to any reference type, declared with the type Student. We also know that ArrayList implements List, and we can pass
    an ArrayList to a method or assign it to a reference of the List type. And we saw this in both cases for our Student
    ArrayList. But why can't we pass an ArrayList of LPAStudent, to the method parameter that's declared as a List of Student?

            --------------                  ------------------              -------------------------
            |Student     |                  |List<Student>   |              |ArrayList<Student>     |
            |____________|                  |________________|              |_______________________|
            |____________|                  |________________|              |_______________________|
                   ↑                                  ↑                                 |
                   ↑                                  |                                 |
              Inheritance                 Interface Implementation              NOT INHERITANCE!!!
                   ↑                                  |                                 |
            -------↑---------              -----------|-----------          ------------|---------------
            |LPAStudent     |              |ArrayList<Student>   |          |ArrayList<LPAStudent>     |
            |_______________|              |_____________________|          |__________________________|
            |_______________|              |_____________________|          |__________________________|

    Surely, if an LPAStudent is a Student, a List of LPAStudent is ultimately a List of Student. It's very natural to
    assume that a method that takes a List with Students should accept a List with LPAStudents, because LPAStudent is a
    Student after all. But that's not how it works. When used as reference types, a container of one type has no relationship
    to the same container of another type, even if the contained types do have a relationship.

        Let's explore this just a little further, because this concept is sure to trip you up. It's important to understand
    that this restriction has to do with variable reference types and method parameters. First, I'll comment out "lpaStudents.add"
    call for the moment. And I'll use "students.add" to add an LPAStudent to that list in the for loop. And that compiles
    and runs:

                        Ann B           Java            2020
                        Korhan K        Python          2019
                        John Q          C++             2018
                        Bill L          C++             2018
                        Bill G          Java            2021
                        Cathy X         C++             2023
                        Cathy E         Python          2018
                        John P          Python          2023
                        Ann N           Python          2023
                        John L          C++             2021
                        Ann K           C++             2019     50,4%
                        John B          C++             2022     56,9%
                        John V          C++             2018     60,4%
                        Bill U          Java            2020     44,1%
                        Cathy L         Java            2019     55,1%
                        Ann M           Python          2021     98,7%
                        Cathy N         Python          2021     90,6%
                        Ann W           Python          2018     93,8%
                        Ann Q           Java            2020     78,5%
                        Ann S           Java            2018     64,6%

    you can see my last students are printed out with percentage complete, indicating these students are LPAStudents. This
    confirms we can add any type of Student to this List. But consider another next change. I'm going to put LPAStudent
    in <>(diamond) operator, on the right side of the assignment of our students variable.

                            Before                                                  After
          List<Student> students = new ArrayList<>();              List<Student> students = new ArrayList<LPAStudent>();

    And now, we've got a very similar error to the one we had with the printList method. Even if I change List to ArrayList
    in the reference type,

                            Before                                                  After
      List<Student> students = new ArrayList<LPAStudent>();      ArrayList<Student> students = new ArrayList<LPAStudent>();

    I have the same problem. The problem isn't that I'm assigning an ArrayList to a List reference. The problem is the
    type argument in the references. When we specify Student as a type argument to a generic class or container, only
    Student, and not one of its subtypes is valid for this container. And although we can add Students of any type to the
    container, we can't pass a List typed as LPAStudent to a reference variable of List typed with Student. I'll revert
    the last 2 changes, putting back our List of Student to the way it was.

        Now, I'll show you different ways to handle this situation. I'll uncomment the statements below.
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

//Part-7
/*
        First, I want a method, a static method that I'll call from the main method, that'll print my list of Students
    out. This method takes a List of Students. Notice I'm not specific about the type of List, so I'm not putting ArrayList
    there in the method parameter, but just List. Next, I want to generate 10 students, so in the main method, I'll set
    up a for loop.
*/
//End-Part-7

    public static void printList(List students) {

        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }
}

