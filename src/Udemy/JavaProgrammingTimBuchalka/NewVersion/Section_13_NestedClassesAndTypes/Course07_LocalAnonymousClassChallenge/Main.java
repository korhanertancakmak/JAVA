package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_NestedClassesAndTypes.Course07_LocalAnonymousClassChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_NestedClassesAndTypes.Course07_LocalAnonymousClassChallenge.domain.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//Part-1
/*
                                 The Local and Anonymous Class Challenge

        First, you need to create a record named Employee, that contains First Name, Last Name, and hire date. We'll be
    using this record as a domain class, which we'll pretend we don't have the luxury of changing.
     -  Set up a list of Employees with various names and hire dates in the main method.
     -  Set up a new method that takes this list of Employees as a parameter.
     -  Create a local class to wrap this class, (pass Employee to the constructor and include a field for this) and add
     some calculated fields, such as full name, and years worked.
     -  Create a list of employees using your local class.
     -  Create an anonymous class to sort your local class employees, by full name, or years worked.
     -  Print the sorted list.

    Hint : Here is another review of a date function, which should help you with calculating years worked.

                    int currentYear = LocalDate.now().getYear();

    We'll be covering dates and time thoroughly in our next section of the course. Remember that a local class is a class
    declaration in a method block, and you can create many instances of it, so that's why your Employee wrapper class
    should be set up as a local class. An anonymous class is a single instances of an unnamed class, and you've seen an
    example of a Comparator already, which you'll be doing that here.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        Employee e1 = new Employee("Minnie" , "Mouse", "01/02/2015");
        Employee e2 = new Employee("Mickie" , "Mouse", "05/08/2000");
        Employee e3 = new Employee("Daffy" , "Duck", "11/02/2011");
        Employee e4 = new Employee("Daisy" , "Duck", "05/03/2013");
        Employee e5 = new Employee("Goofy" , "Dog", "23/07/2020");

        List<Employee> list = new ArrayList<>(Arrays.asList(e1, e2, e3, e4, e5));

//Part-10
/*
        Running that code:

                Daffy Duck has been an employee for 12 years
                Daisy Duck has been an employee for 10 years
                Goofy Dog has been an employee for 3 years
                Mickie Mouse has been an employee for 23 years
                Minnie Mouse has been an employee for 8 years

    I get my employees printed out by their full name. Now I'll try this with a year sort, so in the main method, I'll
    add a separator line, and then invoke that method again, with year, but I could really put anything there, as long
    as it wasn't name.
*/
//End-Part-10

        printOrderedList(list, "name");
        System.out.println();
        printOrderedList(list, "year");

//Part-11
/*
        Running that code:

                Daffy Duck has been an employee for 12 years
                Daisy Duck has been an employee for 10 years
                Goofy Dog has been an employee for 3 years
                Mickie Mouse has been an employee for 23 years
                Minnie Mouse has been an employee for 8 years

                Goofy Dog has been an employee for 3 years
                Minnie Mouse has been an employee for 8 years
                Daisy Duck has been an employee for 10 years
                Daffy Duck has been an employee for 12 years
                Mickie Mouse has been an employee for 23 years

    I get the list printed again, but sorted by the year. Ok, so this challenge allowed you to create both a local class
    and an anonymous class. That's the end of this section.
*/
//End-Part-11
    }

//Part-3
/*
        After creating the list of Employee, I'll set up my method, that will have both the local and anonymous classes.
    Ok, I'm passing a list of Employee, and I also have 1 other parameter, which is sortField. If this field indicates
    to sort by name, I'll sort by name, otherwise by years worked. Next, I want to get the current year, using the simple
    date function I gave you as a hint.
*/
//End-Part-3

    public static void printOrderedList(List<Employee> eList, String sortField) {

        int currentYear = LocalDate.now().getYear();

//Part-4
/*
        Now, I'll set up my local class. In this code, I'm calling the class myEmployee. I'm not extending the Employee
    class, because it's a record, and we can't use a record in the extends class, but this isn't a problem for what we
    want to do here. I've got a contained Employee field, which will just reference the original employee instance. I'll
    have 2 other fields, yearsWorked, an integer, and fullName, a string. I'll add a constructor.
*/
//End-Part-4

        class MyEmployee {

            Employee containedEmployee;
            int yearsWorked;
            String fullName;

//Part-5
/*
        In addition to assigning contained employee to my containedEmployee field, I'll derive the years worked next.

                yearsWorked = currentYear - Integer.parseInt(containedEmployee.hireDate().split("/")[2]);

    In this code, I'm splitting my string by the forward slash "/", and then the third split should be the year. I run
    that through Integer.parseInt to get the year as an integer, and subtract it from the current year. And now for the
    full name, I'll use String.join, because I want to keep reminding you of the many options on String. Lastly, I want
    toString method for my local class.
*/
//End-Part-5

            public MyEmployee(Employee containedEmployee) {
                this.containedEmployee = containedEmployee;
                yearsWorked = currentYear - Integer.parseInt(containedEmployee.hireDate().split("/")[2]);
                fullName = String.join(" ", containedEmployee.first(), containedEmployee.last());
            }

//Part-6
/*
        I'll generate this by generating an override. I'll replace the call to super.toString in that method. Here, I'll
    just print the full name, and the years worked by the employee. And that's the local class, so it's not too complicated.
*/
//End-Part-6

            @Override
            public String toString() {
                return "%s has been an employee for %d years".formatted(fullName, yearsWorked);
            }
        }

//Part-7
/*
        And I want a list of these now, so I'll loop through the list of Employees, and create a list of MyEmployees. Ok,
    now I have a list of my employees, but I want to sort them in 2 different ways, based on the method argument, sortField.
    Let me set that up.
*/
//End-Part-7

        List<MyEmployee> list = new ArrayList<>();
        for (Employee employee : eList) {
            list.add(new MyEmployee(employee));
        }

//Part-8
/*
        Unlike the local class, which we used to create many instances of, we really only need one instance of this class,
    for a very finite purpose, to change the way these employees are sorted. This doesn't compile yet, because it implements
    the Comparator interface, so I have to implement the compare method. Let me generate that code. Now, what's fun about
    a class generated in a method is, we can use method arguments, or local variables, to determine the behavior that will
    happen in the anonymous class. I'm going to use my sortField method argument to determine how to compare the employees.
*/
//End-Part-8

        var comparator = new Comparator<MyEmployee>() {

            @Override
            public int compare(MyEmployee o1, MyEmployee o2) {

                if (sortField.equals("name")) {
                    return o1.fullName.compareTo(o2.fullName);
                }
                return o1.yearsWorked - o2.yearsWorked;
            }
        };

//Part-9
/*
        Because sortField never changes value, it's effectively final, and I can use it in my anonymous class code. If
    sortField is name, the sort will use the full name field on the local class to sort by. Otherwise, it will sort by
    the years worked on the local class. Here, I just want to give you a little something extra to think about how local
    variables and method arguments, can influence what is happening in these temporary classes. Now that I have a comparator,
    I can sort my list, and I'll follow that up with an enhanced for loop to print each employee instance on a separate
    line. Now, I just need to invoke this method from the main method.
*/
//End-Part-9

        list.sort(comparator);

        for (MyEmployee myEmployee : list) {
            System.out.println(myEmployee);
        }
    }
}
