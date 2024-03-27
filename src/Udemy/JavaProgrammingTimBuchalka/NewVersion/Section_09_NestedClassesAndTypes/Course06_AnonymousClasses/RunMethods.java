package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_09_NestedClassesAndTypes.Course06_AnonymousClasses;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_09_NestedClassesAndTypes.Course06_AnonymousClasses.domain.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Part-1
/*
                                            Anonymous Classes

        An anonymous class is a local class that doesn't have a name. All the nested classes we've looked at so far have
    been created with a class declaration. The anonymous class is never created with a class declaration, but it's always
    instantiated as part of an expression. Anonymous classes are used a lot less, since the introduction of Lambda
    Expressions in JDK 8.

        I had a student ask, why do we care about anonymous classes, when we now have lambda expressions? Aren't anonymous
    classes just legacy code? It's true, that since JDK 8, when lambda expressions were released, they did start to replace
    anonymous class usage. But there are still some use cases where an anonymous class might be a good solution. And it's
    likely you'll be running across anonymous classes in older code. I also think understanding anonymous classes leads
    to a better understanding of lambda expressions. Because of these reasons, I do want to cover the anonymous class,
    in the context of the other nested classes.

        I'm going to create a new class and call it RunMethods, and I'll add a main method. I'm going to use generic method
    in the main method,
*/
//End-Part-1

public class RunMethods {

    public static void main(String[] args) {

        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macys"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target")));

//Part-3
/*
        I had 2 options for Employee Comparator and note I selected the top level class. So c0 is an instance of the top
    level EmployeeComparator class, typed with the StoreEmployee class. I'll add the other 2 comparators now. The second
    variable, c1, uses the static nested class on the Employee class. The third variable, c2, is using the inner class
    on StoreEmployee. Remember that this syntax, ".new ", creates an instance of StoreEmployee first, then uses that to
    create an instance of the inner class. Now I'll invoke the sortIt method with the storeEmployees list, and each of
    these comparators.
*/
//End-Part-3

        var c0 = new EmployeeComparator<StoreEmployee>();
        var c1 = new Employee.EmployeeComparator<StoreEmployee>();
        var c2 = new StoreEmployee().new StoreComparator<StoreEmployee>();

//Part-5
/*
        And I want to implement the compareTo method in this local class, using IntelliJ's tools to do that for me. And
    I'll replace "return 0;", with my own comparison, using names again.
*/
//End-Part-5

        // This is a local class
        class NameSort<T> implements Comparator<StoreEmployee> {

            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                //return 0;
                return o1.getName().compareTo(o2.getName());
            }
        }

//Part-6
/*
        And now, I want another variable, c3, that is an instance of this local class. And I'll invoke another call to
    the sort it method with this new local comparator.
*/
//End-Part-6

        var c3 = new NameSort<StoreEmployee>();

//Part-8
/*
        The first thing I want you to notice is that I'm getting an error in the statement of c4. I'll deal with that
    shortly. The second thing is that I'm creating the variable c4, and immediately assigning it an instance, using the
    new keyword. At first glance, this might look like I'm creating an instance of an interface, the comparator interface.
    Hopefully you remember I can't do that, we can't instantiate an interface directly. For example, if I remove the opening
    and closing braces on that line, the message I get back from IntelliJ is, Comparator is abstract and can't be instantiated.
    I'll put back the curly braces. The message I'm getting is quite different now, indicating that I need to implement
    a method on this class. The class body, which is represented by these opening and closing curly braces is telling Java
    that this isn't an instance of an interface, But actually, it's special syntax, that means this is an anonymous class
    being created, that implements Comparator. And because this unnamed class is implementing an interface, this means
    I still need to implement any abstract methods on that interface. I'll do that for the compareTo method. And finally,
    I'll replace "return 0;" with the same comparison I used for all these other comparators. That's all I need to create
    an anonymous class, that implements this interface. To use it, I'll add another call to sort list.
*/
//End-Part-8

        // This is anonymous class
        var c4 = new Comparator<StoreEmployee>() {
            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                //return 0;
                return o1.getName().compareTo(o2.getName());
            }
        };

//Part-4
/*
        First c0 variable, I'll copy that statement, and paste it twice, right below it. And then changing c0 to c1 and
    for the third one c0 to c2. As you can see from this code, any of these comparators works with a StoreEmployee, when
    I pass it to the sortIt method. If I run it:


                Sorting with Comparator: domain.EmployeeComparator@b4c966a
                Target  10322 Bud      2016
                Walmart 10515 Joe      2021
                Walmart 10215 Marty    2018
                Target  10015 Meg      2019
                Macys   10105 Tom      2020
                Sorting with Comparator: domain.Employee$EmployeeComparator@37a71e93
                Target  10322 Bud      2016
                Walmart 10515 Joe      2021
                Walmart 10215 Marty    2018
                Target  10015 Meg      2019
                Macys   10105 Tom      2020
                Sorting with Comparator: domain.StoreEmployee$StoreComparator@7e6cbb7a
                Macys   10105 Tom      2020
                Target  10322 Bud      2016
                Target  10015 Meg      2019
                Walmart 10215 Marty    2018
                Walmart 10515 Joe      2021

    the results are the same for the first 2 comparators, sorting by name. The last is sorted by store, then yearStarted,
    which is how we created that comparator. In each case, you can see the string representation of each comparator, which
    is the default toString method for any object. The first is an instance of the EmployeeComparator class. The second
    is an instance of the nested class, EmployeeComparator, on the Employee class. The $ in the output you see here, indicates
    that the class following, is for a nested class. And you can see that also, for the StoreComparator, an inner class,
    on Store Employee. Ok, so next, I'll quickly create a local class in the main method that acts as a comparator. I'll
    insert this after my 3 local variables. I'll put a comment here this is a local class.
*/
//End-Part-4

        sortIt(storeEmployees, c0);
        sortIt(storeEmployees, c1);
        sortIt(storeEmployees, c2);

//Part-7
/*
        And running that;

                            ----(same)
                            Sorting with Comparator: RunMethods$1NameSort@7c3df479
                            Target  10322 Bud      2016
                            Walmart 10515 Joe      2021
                            Walmart 10215 Marty    2018
                            Target  10015 Meg      2019
                            Macys   10105 Tom      2020

    Notice the string representation of this last comparator. You can see that it includes the current class name, RunMethods,
    then a $ sign with a number, and then my local class's name, NameSort. You can see that, though it's a local class
    in a method block, it's still a named class. Now, how is an anonymous class different? I'll create one, then we'll
    examine it. I'll add a comment that this is an anonymous class.
*/
//End-Part-7

        sortIt(storeEmployees, c3);

//Part-10
/*
        Running the code now:

                        Sorting with Comparator: RunMethods$1@7106e68e
                        Target  10322 Bud      2016
                        Walmart 10515 Joe      2021
                        Walmart 10215 Marty    2018
                        Target  10015 Meg      2019
                        Macys   10105 Tom      2020

    The string representation of this last comparator indicates that it's a class local to the RunMethod class. But it
    has no name, as you can see. This is how java represents an anonymous class.

        An anonymous class is instantiated and assigned in a single statement. The new keyword is used followed by any
    type. This is "NOT" the type of the class being instantiated. It's the super class of the anonymous class, or it's
    the interface this anonymous class will implement as I'm showing here.

                        var c4 = new Comparator<StoreEmployee>() {};

    In the first example above, the anonymous unnamed class will implement the Comparator interface. In the second example
    below, the anonymous class extends the Employee class, meaning it's a subclass of Employee.

                        var e1 = new Employee {};

    In both cases, it's important to remember the semi-colon after the closing bracket, because this is an expression,
    not a declaration.

        Anonymous classes were a pretty fun feature in their hey day, because you could create a type on the fly, and pass
    it as a method argument. What do I mean by that? Well, I didn't really have to assign this bit of code to a variable.
    Actually, let me invoke the sort it method, one more time.
*/
//End-Part-10

        sortIt(storeEmployees, c4);

//Part-11
/*
        Here, I'm creating an anonymous class directly as a method argument. In other words, I'm using the anonymous class
    to create a bit of custom functionality, and pass that functionality as an argument to a method, via the interface.
    This code is a little harder to read, but you may see code like this that pre-dates lambda expressions. You should
    recognize this as an on-th-fly anonymous class, being passed as a method argument. If I run this code, I again see
    that this class is considered part of the RunMethods class, and it's simply given a number. Wouldn't it be nice, if
    we didn't have to go through all of this effort to create these little classes, anonymous or otherwise, that implement
    this one line of code, which is really the thing we're interested in controlling and customizing. It's this one line
    of code, which ultimately the sort method is interested in, in every one of these cases. Creating a class that implements
    an interface, and overriding a method, feels like a heavy weight solution, to pass a small bit of custom functionality
    around. Now notice, IntelliJ has grayed out the new Comparator part of this expression. If I hover over that, it tells
    me "Anonymous new Comparator can be replaced with a lambda expression", and it gives me the option to replace with
    a lambda, so I'll select that. That's a little easier on the eyes, in some ways, and it's also more evident, that it's
    this statement of code, that's ultimately being passed to the sortIt method. If I run that:

                        Sorting with Comparator: RunMethods$$Lambda$28/0x0000000801006530@76fb509a
                        Target  10322 Bud      2016
                        Walmart 10515 Joe      2021
                        Walmart 10215 Marty    2018
                        Target  10015 Meg      2019
                        Macys   10105 Tom      2020

    The string representation indicates a Lambda expression is the source of the comparator passed to the method. In the
    next section of the course, I'm going to cover the lambda expression in a lot of detail, but first I have a final
    challenge for you in this section, which will help give you some extra experience with local and anonymous classes.
*/
//End-Part-11

        sortIt(storeEmployees, (o1, o2) -> o1.getName().compareTo(o2.getName()));
    }

//Part-2
/*
        So this method called sortIt is generic and its type parameter, I've named T, it takes 2 arguments. The first has
    to be list of type T. The second argument is a Comparator, so what am I doing here with this wildcard? This Comparator
    instead of specifying that it's to be a Comparator with the same type as the list, I'm using super here. Remember
    that's a lower bounded wildcard and what it means is, that I can use a Comparator that's either the same type T, or
    a super type of T. This means if I'm sorting store employees, I can still use an Employee Comparator as one of the
    arguments, because Employee is a super type of Store Employee. Hopefully that makes sense. And then, I'll sort the
    list, and print each employee. In our examples so far in this project, we created 3 comparators. One was a stand-alone
    or top level class, one was a static nested class on Employee, and one was an inner class on StoreEmployee. I want to
    use each of these now, first setting up local variables, for each of these comparators. I'll start with the top-level
    class.
*/
//End-Part-2

    public static <T> void sortIt(List<T> list, Comparator<? super T> comparator) {

        System.out.println("Sorting with Comparator: " + comparator.toString());
        list.sort(comparator);
        for (var employee : list) {
            System.out.println(employee);
        }
    }

}
