package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course08_GenericMethodsWildcards;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course08_GenericMethodsWildcards.model.*;

import java.util.ArrayList;
import java.util.List;

//Part-1
/*
        In the last lecture, we seemed to be stuck between a rock and a hard place. We had a method that wanted a collection
    of any type of Student, but we found we couldn't pass an ArrayList of LPAStudent to that method, when we specified
    Student as the type argument for the List in the method parameter.

                            Limitation of a reference of generic class with a list argument

        When we declare a variable or method parameter with:

                                                List<Student>

    Only List subtypes with Student elements can be assigned to this variable or method argument. We can't assign a list
    of Student subtypes to this!

        We left off, by using the raw version of List, as the method parameter, but I told you this isn't recommended. So
    where does that leave us? We could write a second method, that takes as its parameter, a List of LPAStudent. You probably
    know that's not a good solution either, because of the duplication of code. This would also mean our code isn't extensible,
    because any time a new subclass of Student is added, we might have to add a new method. Doing these things defeats
    the very purpose of using generics! Fortunately, we do have several other alternatives. One of them, is to make this
    method, a generic method. We can create a generic method on any class, not just on a generic class. I'm going to set
    up a type parameter for this method, and that goes in angle brackets just before the return type, which is void in
    this case.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

//Part-4
/*
        I'll comment the initial versions of the print statements. You'll notice my code compiles and I can run it, and
    I get the same formatted output, just like I did when I called the generic method, printList. Inside this method, I'm
    still able to call the "student.getYearStarted" method. This is because I'm specifying an upper bound, using the extends
    Student expression. This means the code in the method knows that anything coming in on this list, will be a Student
    or its subtype. Let me remove that upper bound next.

                              Before                                                            After
       public static void printList(List<? extends Student> students)          public static void printList(List<?> students)

    And you can see, I've got a compiler error, because I'm trying to use a method that's on Student. This wildcard, the
    unbounded wildcard, is just the question mark alone. It doesn't restrict what types my list can contain, but it also
    limits the functionality I have in the method. What happens if I change this to a lower bound? I'll put super Student
    there now.

                              Before                                                            After
            public static void printList(List<?> students)          public static void printList(List<? super Student> students)

    Even though I'm specifying Student in my wildcard expression, I still get the same error. Why is that? Well, a lower
    bound means it can be a Student or any parent of Student, which is an object in this case. An object won't have this
    method, "getYearStarted" on it, so again, using a lower bound also means you either have to cast, or limit what operations
    you're performing on the type in your method. Let me remove that call to getYearStarted in this method, and I'll just
    print out the student alone. That fixes our method, but notice the compiler error in the main method, within the second
    "printMoreList" statement. I've got an error, on the call where I'm passing the list of LPAStudent. That's because
    we've said, we'll only take Students, and not subtypes of students, by using this kind of wildcard, with a lower bound.
    I'll revert this change. Is this solution better than the last one, meaning is it better than using a generic method?
    Well, like anything, the answer for that is, it depends. This is the best solution to the problem we've described,
    and for the method, I'm showing here. In this method, I'm using elements in the collection, and I can access functionality
    specific to the Student elements. But I'm not trying to add, or set an element in my list, in this code. Let me try
    to do that.
*/
//End-Part-4

        int studentCount = 10;
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            students.add(new Student());
        }
        //printList(students);                                                              >>> Commented via Part-4
        printMoreList(students);

        List<LPAStudent> lpaStudents = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            lpaStudents.add(new LPAStudent());
        }
        //printList(lpaStudents);                                                           >>> Commented via Part-4
        printMoreList(lpaStudents);

//Part-8
/*
        And running that:

                ...(same)
                String: ABLE
                String: BARRY
                String: CHARLİE
                Integer: 1.0
                Integer: 2.0
                Integer: 3.0

    I get the output from this code segment, first my 3 names in uppercase. Then my 3 integers, printed out doubles.
*/
//End-Part-8

        testList(new ArrayList<String>(List.of("Able", "Barry", "Charlie")));
        testList(new ArrayList<Integer>(List.of(1, 2, 3)));
    }

//Part-2
/*
        We left off, by using the raw version of List, as the method parameter, but I told you this isn't recommended. So
    where does that leave us? We could write a second method, that takes as its parameter, a List of LPAStudent. You probably
    know that's not a good solution either, because of the duplication of code. This would also mean our code isn't extensible,
    because any time a new subclass of Student is added, we might have to add a new method. Doing these things defeats
    the very purpose of using generics! Fortunately, we do have several other alternatives. One of them, is to make this
    method, a generic method. We can create a generic method on any class, not just on a generic class. I'm going to set
    up a type parameter for this method, and that goes in angle brackets just before the return type, which is void in
    this case. I can use "T" where I would otherwise have a type, so I can include "<T>", after List in the method parameter.
    That fixed the problem, and we can run the code with the same results, and no warnings. Here "List<T> students",
    instead of saying this method will take only a List of Student, I'm saying it will take a List of any kind of type.
    Later, I'll show you a couple of other ways to do this, but I do want to pause here to talk about a generic method
    first.

                                            The Generic Method

        For a method, type parameters are placed after any modifiers and before the method's return type. The type parameter
    can be referenced in method parameters, or as the method return type, or in the method code block, much as we saw a
    class's type parameter can be used. A generic method can be used for collections with type arguments, as we just saw,
    to allow for variability of the elements in the collection, without using a raw version of the collection.

                                    public <T> String myMethod(T input) {
                                            return input.toString();
                                    }

    A generic method can be used for static methods on a generic class, because static methods can't use class type parameters.
    A generic method can be used on a non-generic class, to enforce type rules on a specific method. The generic method
    type parameter is separate from a generic class type parameter. In fact, if you've used T for both, the T declared
    on the method means a different type, than the T for the class.

        Like a generic class's type parameter, we can use upper bounds for the type, which both restricts the types we can
    pass, but allows us to use that type's methods. Going back to the Main class, and the printList method, first I'll
    add the upper bound, which will be Student.

                              Before                                                            After
            public static <T> void printList(List<T> students)          public static <T extends Student> void printList(List<T> students)

    This means I can pass a list of Students or LPAStudents to this method. Now, I can only use this method for a List of
    Students, or a subtype of Students. And now, because I've done that, I'm able to use Student methods within this method
    block. I have one method, unique to the Student class, and that was the student getYearStarted method,

                              Before                                                            After
                    System.out.println(student);                        System.out.println(student.getYearStarted() + ": " + student);

    And running that:

                    2019: Cathy S         Python          2019
                    2018: Cathy N         C++             2018
                    2021: John X          Java            2021
                    2020: Ann T           C++             2020
                    2019: Korhan O        Java            2019
                    2023: Korhan J        Java            2023
                    2022: Korhan X        Python          2022
                    2021: Korhan P        C++             2021
                    2022: John D          Java            2022
                    2022: Korhan G        Python          2022

                    2021: Cathy Z         Python          2021     13,0%
                    2023: Ann E           C++             2023      7,9%
                    2022: Korhan C        Java            2022     89,2%
                    2018: Bill R          Python          2018     20,5%
                    2018: Bill U          Python          2018     27,8%
                    2019: Korhan J        Python          2019     39,7%
                    2022: John M          C++             2022     10,0%
                    2019: Cathy H         C++             2019     16,1%
                    2019: Ann M           Python          2019     62,7%
                    2021: John X          Java            2021     50,9%

    you can see I'm just now outputting the year at the start, then followed by the string representation for each Student.
    Although this solution allowed me to demonstrate generic methods, it may still not be the preferred solution. I'm
    going to copy that method and past it directly below, but not because I want two methods that do the same thing in
    this code. I just want to leave this generic method in this code, so if you're reviewing it later, you can still see
    it, and explore it.




        A "type parameter" is a generic class, or generic method's
    declaration of the type. In both of these examples, T is said to be the type parameter. You can bind a type parameter
    with the use of the "extends" keyword, to specify an "upper bound".

                        Generic Class                                   Generic Method
                   public class Team<T> {}                      public <T> void doSomething(T t) {}

    A "type argument" declares the type to be used, and is specified in a type reference, such as a local variable reference,
    method parameter declaration, or field declaration.

                                                Generic Class
                                     Team<BaseballPlayer> team = new Team<>();

    In this example, BaseballPlayer is the type argument for the Team class. A "wildcard" can only be used in a "type
    argument", not in the type parameter declaration. A wildcard is represented with the "?" character. A wildcard means
    the type is "unknown". For this reason, a wildcard "limits what you can do", when you specify a type this way.

                                        List declaration using a wildcard
                                              List<?> unknownList;

    A wild card can't be used in an instantiation of a generic class. The code shown here is invalid.

                           Invalid! You can't use a wildcard in an instantiation expression
                                       var myList = new ArrayList<?>();

    A wildcard can be unbounded, or alternately, specify either an upper bound or lower bound. You "can't specify both"
    an "upper" bound and a "lower" bound, in the same declaration.

        Argument            Example                     Description
        unbounded           List<?>                     A List of any type can be passed or assigned to a List using this wildcard.
        upper bound         List<? extends Student>     A list containing any type that is a Student or a sub type of Student
                                                        can be assigned or passed to an argument specifying this wildcard.
        lower bound         List<? super LPAStudent>    A list containing any type that is an LPAStudent or a super type
                                                        of LPAStudent, so in our case, that would be Student AND Object.

                                                 Type Erasure

        Generics exist to enforce tighter type checks, at compile time. The compiler transforms a generic class into a
    typed class, meaning the byte code, or class file, contains no type parameters. Everywhere a type parameter is used
    in a class, it gets replaced with either the type Object, if no upper bound was specified, or the upper bound type
    itself. This transformation process is called type erasure, because the T parameter (or S, U, V), is erased, or
    replaced with a true type. Why is this important? Understanding how type erasure works for overloaded methods, may
    be important.
*/
//End-Part-2

    public static void printMoreList(List<? extends Student> students) {

        //Student last = students.get(students.size() - 1);                                 >>> Commented via Part-5
        //students.set(0, last);                                                            >>> Commented via Part-5

        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();

//Part-5
/*
        Before this for loop, I'll try to assign the first element to the last.

                            Student last = students.get(students.size() - 1);
                            students.set(0, last);

    Now, I've got another problem, with the error message, "Required Type: capture of ? extends Student" and "Provided
    type: Student". What does that mean? It really means that this method with wildcards has no way of knowing the type
    of the list elements that actually get passed to it. They're unknown, except it could be one of many types, subclassed
    from Student. In other words, the compiler doesn't have enough information to enforce the type checking rules. It knows
    there are rules, because we're using type arguments. But it can't safely say if this List is a list of Students, a
    List of LPAStudents, or a mix, so it won't let you add an element. Wildcard capture is the ability of the compiler
    to infer the correct type parameter, and here it can't do it. I want to revert that last bit of code, removing those
    two statements, and this compiles again.

        Now that we know about type arguments and wildcard syntax, let's quick talk about how generics work at runtime.

                                                 Type Erasure

        Generics exist to enforce tighter type checks, at compile time. The compiler transforms a generic class into a
    typed class, meaning the byte code, or class file, contains no type parameters. Everywhere a type parameter is used
    in a class, it gets replaced with either the type Object, if no upper bound was specified, or the upper bound type
    itself. This transformation process is called type erasure, because the T parameter (or S, U, V), is erased, or
    replaced with a true type. Why is this important? For the most part, this isn't really something you have to be too
    worried about. But understanding how type erasure works for overloaded methods, may be important.

        In the main class, I'll create a static method that takes a list of String,
*/
//End-Part-5
    }

//Part-3
/*
        I'm going to copy that method and past it directly below, but not because I want two methods that do the same thing
    in this code. I just want to leave this generic method in this code, so if you're reviewing it later, you can still
    see it, and explore it. I'll rename the new method to printMoreLists. First, I'll change this method back to the way
    we originally had the previous method, passing a List of Student, and not using type parameters. In other words, I
    don't want this to be a generic method. Now, I'll change the type argument being used in the method parameter, so in
    the angle bracket where I have Student defined, I'm going to add "? extends Student" there. So what is this? This
    syntax is what Java calls a wildcard in the type argument. A wildcard is represented by a question mark. Let me pause
    here, to discuss a bit of terminology, so we're all on the same page.

                                Type Parameters, Type Arguments and using a Wildcard

        A "type parameter" is a generic class, or generic method's declaration of the type. In both of these examples, T
    is said to be the type parameter. You can bind a type parameter with the use of the "extends" keyword, to specify an
    "upper bound".

                        Generic Class                                   Generic Method
                   public class Team<T> {}                      public <T> void doSomething(T t) {}

    A "type argument" declares the type to be used, and is specified in a type reference, such as a local variable reference,
    method parameter declaration, or field declaration.

                                                Generic Class
                                     Team<BaseballPlayer> team = new Team<>();

    In this example, BaseballPlayer is the type argument for the Team class. A "wildcard" can only be used in a "type
    argument", not in the type parameter declaration. A wildcard is represented with the "?" character. A wildcard means
    the type is "unknown". For this reason, a wildcard "limits what you can do", when you specify a type this way.

                                        List declaration using a wildcard
                                              List<?> unknownList;

    A wild card can't be used in an instantiation of a generic class. The code shown below is invalid.

                           Invalid! You can't use a wildcard in an instantiation expression
                                       var myList = new ArrayList<?>();

    A wildcard can be unbounded, or alternately, specify either an upper bound or lower bound. You "can't specify both"
    an "upper" bound and a "lower" bound, in the same declaration.

        Argument            Example                     Description
        unbounded           List<?>                     A List of any type can be passed or assigned to a List using this wildcard.
        upper bound         List<? extends Student>     A list containing any type that is a Student or a sub type of Student
                                                        can be assigned or passed to an argument specifying this wildcard.
        lower bound         List<? super LPAStudent>    A list containing any type that is an LPAStudent or a super type
                                                        of LPAStudent, so in our case, that would be Student AND Object.

        Let's get back to our code, to explore some of these concepts, then I'll summarize when you'd want to use these
    different variations. Getting back to the main method, I want to invoke this new method, in the two instances where
    I was invoking the previous method.
*/
//End-Part-3

/*
    public static <T extends Student> void printList(List<T> students) {

        for (var student : students) {
            System.out.println(student.getYearStarted() + ": " + student);
        }
        System.out.println();
    }
*/

/*                                                                                          >>> Commented via Part-6
    public static void testList(List<String> list) {

        for (var element : list) {
            System.out.println("String: " + element.toUpperCase());
        }
    }

    public static void testList(List<Integer> list) {

        for (var element : list) {
            System.out.println("Integer: " + element.floatValue());
        }
    }
*/

//Part-6
/*
        And duplicate the method right below the first one. In this second method, I want to change the method parameter,
    to be a List of Integer. I'm using the float value method, just to use a method specific to the Integer class. This
    looks like a valid way to overloaded the method, the first method takes a List of String, and I've overloaded that,
    to take a List of Integer. But you see I've got an error, on that first testList method. The message I get is that
    "the two methods clash, because they have the same type erasure". A List has no upper bound declared to it, so it
    always resolves, in byte code, to a List of Object. In both of these cases, the method parameters, after type erasure,
    would be a List of Objects. This means these methods won't overload each other in the byte code, they'd have exactly
    the same signature, the same name, and parameter type. So how would you code something like this? I'll comment out
    both of those 2 methods, and start with a new method.
*/
//End-Part-6

    public static void testList(List<?> list) {

        for (var element : list) {
            if (element instanceof String s) {
                System.out.println("String: " + s.toUpperCase());
            } else if (element instanceof Integer i) {
                System.out.println("Integer: " + i.floatValue());
            }
        }
    }

//Part-7
/*
        And now I can take advantage of the instance of operator, with pattern matching, to handle Strings and Integers
    differently. Check if element is a string. If it is, print the uppercase string. And I'll make a call to that method,
    for two very different types of lists, in the main method.
*/
//End-Part-7
}

