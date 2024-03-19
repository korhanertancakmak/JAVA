package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_NestedClassesAndTypes.Course01_StaticNestedClasses;

//Part-1
/*
                              Nesting Classes(or Types) Within Another Class(or Type)

        Up to this part of the course, I've shown you static and instance members on a class, but they've only been fields
    and methods. In addition to these, a class can contain other types within the class body, such as other classes,
    interfaces, enums, and records. These are called nested types, or nested classes. You might want to use nested classes
    when your classes are tightly coupled, meaning their functionality is interwoven. The four different types of nested
    classes you can use in Java are: the static nested class, the inner class, and the local and anonymous classes.

                Type                 |         Description
        static nested class          |      Declared in class body. Much like a static field, access to this class is
                                     |      through the Class name identifier
        instance or inner class      |      Declared in class body. This type of class can only be accessed through an
                                     |      instance of the outer class.
        local class                  |      Declared within a method body.
        anonymous class              |      Unnamed class, declared and instantiated in same statement.

    This section will cover all of these different types of nested classes, as well as additional nested types, like the
    enum & record (which are just classes at their core) and the interface. Before JDK16, only static nested classes were
    allowed to have static methods. As of JDK16, all four types of nested classes can have static members of any type,
    including static methods. We'll start out talking about static nested classes. There's a lot to cover, so let's get
    started.

        I want to start with the static nested class, because I think it's the easiest to understand and use.

                                            Static Nested Class

        The static nested class is a class enclosed in the structure of another class, declared as static. This means the
    class, if accessed externally, requires the outer class name as part of the qualifying name. This class has the advantage
    of being able to access private attributes on the outer class. The enclosing class can access any attributes on the
    static nested class, also including private attributes.

        Let's look at this in code. I'll first create an Employee class, in the package called domain.
*/
//End-Part-1

import CourseCodes.NewSections.Section_13_NestedClassesAndTypes.Course01_StaticNestedClasses.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//Part-4
/*
        In this code, I'm setting up the initial ArrayList with 5 employees, but not in any particular order. Next, I'll
    sort this list with my Comparator. Create a new instance of our EmployeeComparator and pass it to the employees sort
    method.
*/
//End-Part-4

        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(10001, "Ralph", 2015),
                new Employee(10005, "Carole", 2021),
                new Employee(10022, "Jane", 2013),
                new Employee(13151, "Laura", 2020),
                new Employee(10050, "Jim", 2018) ));

//Part-5
/*
        Here I create a comparator local variable, whose type can be inferred, when I assign a new EmployeeComparator
    instance to it. If I hover over that variable, namely "comparator", you can see its a Comparator typed with the Employee
    class. And I can pass this to the sort method. Now, to confirm the list is sorted, I want to print out the employees,
    one on each line. And running the code:

                10005 Carole   2021
                10022 Jane     2013
                10050 Jim      2018
                13151 Laura    2020
                10001 Ralph    2015

    you can see my list of employees are sorted by their names, which are printed out, left justified. Now, let's say, I
    instead want to sort by the year the Employee was hired, the year started field. Going back to the Comparator, I'll
    comment out the statement that's currently there,
*/
//End-Part-5

        //var comparator = new EmployeeComparator<>();
        //employees.sort(comparator);

        //employees.sort(new Employee.EmployeeComparator<>());
        //employees.sort(new Employee.EmployeeComparator<>("yearStarted"));
        employees.sort(new Employee.EmployeeComparator<>("yearStarted").reversed());

        for (Employee e : employees) {
            System.out.println(e);
        }

//Part-8
/*
        First, I'll comment out the two statements, the first that creates a comparator local variable, and also the
    employees.sort statement that takes the comparator variable. And I'll add a statement which is very similar, but
    instantiate and pass the comparator to the sort method. The only difference is that I need to first specify that
    this comparator is accessed through the Employee class.

                    employees.sort(new Employee.EmployeeComparator<>());

    Notice I'm using the diamond operator there but its empty because Java can infer it's type. And running that:

                    10005 Carole   2021
                    10022 Jane     2013
                    10050 Jim      2018
                    13151 Laura    2020
                    10001 Ralph    2015

    I get the same results. But if I go to that nested class, I have extra options available, that I didn't have with the
    external class.
*/
//End-Part8

    }

}
