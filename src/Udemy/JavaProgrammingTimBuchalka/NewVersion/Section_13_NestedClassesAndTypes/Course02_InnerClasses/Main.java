package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_NestedClassesAndTypes.Course02_InnerClasses;

import CourseCodes.NewSections.Section_13_NestedClassesAndTypes.Course02_InnerClasses.domain.Employee;
import CourseCodes.NewSections.Section_13_NestedClassesAndTypes.Course02_InnerClasses.domain.StoreEmployee;

import java.util.ArrayList;
import java.util.List;

//Part-1
/*
                                              Inner Classes

        Inner classes are non-static classes, declared on an enclosing class, at the member level. Inner classes can have
    any of the four valid access modifiers, meaning they can be public, private, protected or have no modifier, which makes
    them package private. An inner class has access to instance members, including private members, of the enclosing class.
    Instantiating an inner class from external code, is a bit tricky, and I'll cover that shortly. As of JDK16, static
    members of all types are supported on inner classes.

        In the previous lecture, we looked at static nested classes with a Comparator class example. I want to do something
    similar here, to look at inner classes, and how they differ from static nested classes. The first thing I'll do is
    create a new class, called StoreEmployee, in the domain package.
*/
//End-Part-1

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(10001, "Ralph", 2015),
                new Employee(10005, "Carole", 2021),
                new Employee(10022, "Jane", 2013),
                new Employee(13151, "Laura", 2020),
                new Employee(10050, "Jim", 2018) ));

//Part-5
/*
        You'll remember that the EmployeeComparator is a static class on Employee, and I can create an instance of it by
    accessing it through the enclosing class name, as you see below. I simply instantiate that comparator, by saying
    "new Employee.EmployeeComparator<>". This isn't true though for non static nested classes, or inner classes.
*/
//End-Part-5

        employees.sort(new Employee.EmployeeComparator<>("yearStarted").reversed());

        for (Employee e : employees) {
            System.out.println(e);
        }

//Part-6
/*
        First, I want to add a statement that I'll be printing the store members next. Now I'll create a list of store
    employees, much the same way I did the employees. The list will be called StoreEmployees and we'll use an ArrayList,
    and initialise that using "List.of", and passing a number of new StoreEmployee instances. And then, I'll add the for
    loop to print each store employee.
*/
//End-Part-6

        System.out.println("Store Members");
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macys"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target") ));

//Part-7
/*
        And before that loop, I want to add the call to my store employee comparator. But how do I call it? Let me set it
    up like I did before, using the EmployeeComparator on my new StoreEmployee class.

                    var comparator = new Employee.EmployeeComparator<>();
                    storeEmployees.sort(comparator);

    And this code compiles and runs,

                    Store Members
                    Target  10322 Bud      2016
                    Walmart 10515 Joe      2021
                    Walmart 10215 Marty    2018
                    Target  10015 Meg      2019
                    Macys   10105 Tom      2020

    And sorts the store members by the default sort for that comparator, which is name. But in this code, I'm not using
    the StoreComparator I created on StoreEmployee. Instead, I'm using the static EmployeeComparator on Employee, and
    you can see that, if you hover over that local variable, comparator. This means I've accessed the comparator on
    Employee through the StoreEmployee class, so static nested classes are inherited by subclasses. But that's not really
    what I want to do here. I really want an instance of StoreEmployee's own comparator class, an inner class, which I
    called StoreComparator. What happens if I just replace EmployeeComparator with StoreComparator in my local variable
    assignment? Let me try that out.
*/
//End-Part-7

        //var comparator = new Employee.EmployeeComparator<>();
        //var comparator = new StoreEmployee.StoreComparator<>();
        var genericEmployee = new StoreEmployee();
        var comparator = genericEmployee.new StoreComparator<>();
        //var comparator = new StoreEmployee().new StoreComparator<>();
        storeEmployees.sort(comparator);

//Part-8
/*
                    var comparator = new Employee.EmployeeComparator<>();
                                            to
                    var comparator = new StoreEmployee.StoreComparator<>();

    Now, I've got a problem. If I hover over that, I get the message that StoreEmployee is not an enclosing class. That's
    because an inner class requires an instance of the enclosing class to be used, to instantiate an inner class. Here,
    we're really calling the class StoreEmployee, and not an actual instance of the StoreEmployee Class, so it doesn't
    work. Ok, so I'll create a new variable, and assign it a new StoreEmployee instance, using my no args constructor.
    Now, I'll replace StoreEmployee on the next statement with my local variable, genericEmployee.

                    var genericEmployee = new StoreEmployee();
                    var comparator = new genericEmployee.StoreComparator<>();

    But that doesn't work neither, but I get a different message, "Cannot resolve StoreComparator." To access the inner
    class, to create a new instance of it, we have to use a special syntax on our outer class instance, "new". I want to
    remove the "new" keyword before genericEmployee, and I want to append ".new ".

                    var comparator = new StoreEmployee.StoreComparator<>();
                                            to
                    var comparator = genericEmployee.new StoreComparator<>();

    But I don't pass anything to ".new", because it's not a method. Instead, I include a space after it, and then I have
    the inner class construction code.
*/
//End-Part-8

        for (StoreEmployee e : storeEmployees) {
            System.out.println(e);
        }

//Part-9
/*
    Now, I got rid of the compiler errors, and I can run this:

                    Store Members
                    Macys   10105 Tom      2020
                    Target  10322 Bud      2016
                    Target  10015 Meg      2019
                    Walmart 10215 Marty    2018
                    Walmart 10515 Joe      2021

    My store employees are sorted by store first, than year started, which is how I implemented the StoreComparator's
    compare method. If I didn't want to use a local variable, I can chain the instantiations. Let me show you that. First,
    I'll remove that local variable, genericEmployee. Then I'll change the next line, and instead of genericEmployee there,
    I'll replace that with new StoreEmployee.

                    var comparator = new StoreEmployee().new StoreComparator<>();

    In either case, I am first instantiating an instance of StoreEmployee. Then I have to call what looks like, at first
    glance, a new method, but it doesn't have parentheses associated with it. This ".new" syntax isn't calling a method,
    but it will create an instance of an inner class, which we've declared on Store Employee.

        To create an instance of an inner class, you first must have an instance of the Enclosing Class. From that instance
    you call ".new ", followed by the inner class name and the parentheses, taking any constructor arguments.

                    EnclosingClass outerClass = new EnclosingClass();
                    EnclosingClass.InnerClass innerClass = outerClass.new InnerClass();

    Many times, an inner class is never accessed or instantiated from outside the enclosing class, but you should still
    be familiar with this syntax.

        For now, I want to show you how to use an inner class to reimplement part of the Bill's Burger code, that was part
    of a challenge in an earlier section. I'll do this in the next lecture.
*/
//End-Part-9
    }
}
