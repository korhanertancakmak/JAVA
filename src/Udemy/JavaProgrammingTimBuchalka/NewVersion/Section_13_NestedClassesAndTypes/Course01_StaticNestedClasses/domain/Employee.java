package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_NestedClassesAndTypes.Course01_StaticNestedClasses.domain;

import java.util.Comparator;

//Part-2
/*
        And I'll add my attributes, I have three, the employeeId, name, and yearStarted. I'll generate a constructor with
    no arguments. Also I want another constructor this time with all 3 fields. And I'm also going to generate a getter,
    but for the name field only. And after that, I'll generate a toString method. And I'll replace the template code with
    my own formatting and include our 3 fields. That's a simple domain(or businessDomain class) called Employee. Now, in
    the past section, we created a Comparator class, to sort Students, and I'm going to do something similar for this
    class. I'll create this in the same package as employee, and call it EmployeeComparator.
*/
//End-Part-2
public class Employee {

//Part-7
/*
        And I'll add static between public and class keywords, so what I'm doing here is, I'm creating a static nested
    class inside of Employee. And this code compiles, so let's go back to the main method, and use this class.
*/
//End-Part-7

    public static class EmployeeComparator <T extends Employee> implements Comparator<Employee> {

//Part-9
/*
        First, I can access the attributes on instances of the Employee class directly, even the private ones, so I'll
    update my method, and instead of calling getName, I'll just access name directly. I can replace getName with simply
    names in both cases. Now let's make this comparator more flexible. I'll add a private attribute to it, called sort
    type. I'll generate a constructor with one argument, this new field, sortType. And I also want a no args constructor,
    so I'll generate that before the previous one. And for that one, I just want to chain a call to the other instructor,
    passing name as the default sort type. Finally, let's make the compare method figure out how to sort, based on that
    sort type. Are we comparing by yearStarted? If yes, so return the result of "o1.yearStarted - o2.yearStarted". Now,
    If I go back to the main method and run it, it should run as it did before, because sorting by name is the default
    sort type. Next, I'll pass yearStarted, to the comparator's constructor.

                    employees.sort(new Employee.EmployeeComparator<>());
                                            to
                employees.sort(new Employee.EmployeeComparator<>("yearStarted"));

    Running that code:

                10022 Jane     2013
                10001 Ralph    2015
                10050 Jim      2018
                13151 Laura    2020
                10005 Carole   2021

    our employees are sorted by the yearStarted, or were hired. And I also want to remind you that the Comparator interface
    has a default method, called reversed, which I can use next, by chaining it to my current comparator.

                    employees.sort(new Employee.EmployeeComparator<>("yearStarted"));
                                                to
                    employees.sort(new Employee.EmployeeComparator<>("yearStarted").reversed());

    Running that:

                10005 Carole   2021
                13151 Laura    2020
                10050 Jim      2018
                10001 Ralph    2015
                10022 Jane     2013

    you can now see that the employees are sorted by year descending, so that's a nice feature. The reversed method will
    reverse the sort for any Comparator. To create a static nested class, you add a class as part of another class's body,
    making it static. This lets you access it via the class name, like other static variables. But this nested static class
    has access to all the outer class's private members and vice versa. Using a nested class for this comparator, I was
    able to keep my attributes encapsulated, without providing getter methods for each one.
*/
//End-Part9

        private String sortType;

        public EmployeeComparator() {
            this("name");
        }

        public EmployeeComparator(String sortType) {
            this.sortType = sortType;
        }

        @Override
        public int compare(Employee o1, Employee o2) {
            //return 0;
            //return o1.yearStarted - o2.yearStarted;
            //return o1.getName().compareTo(o2.getName());
            if (sortType.equalsIgnoreCase("yearStarted")) {
                return o1.yearStarted - o2.yearStarted;
            }
            return o1.name.compareTo(o2.name);
        }
    }


    private int employeeId;
    private String name;
    private int yearStarted;

    public Employee() {
    }

    public Employee(int employeeId, String name, int yearStarted) {
        this.employeeId = employeeId;
        this.name = name;
        this.yearStarted = yearStarted;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        //return "Employee{}";
        return "%d %-8s %d".formatted(employeeId, name, yearStarted);
    }
}
