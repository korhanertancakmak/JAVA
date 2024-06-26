package Udemy.JavaProgrammingTimBuchalka.OldVersion.Section_09_LambdaExpressionsAndFunctions.Course04_FunctionalInterfacesAndPredicates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("Red RidingHood", 35);
        Employee charming = new Employee("Prince Charming", 31);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);


/*
        System.out.println("Employees over 30:");
        System.out.println("==================");

        // 1st way to print older employees than 30
        for (Employee employee : employees) {
            if ( employee.getAge() > 30) {
                System.out.println(employee.getName());
            }
        }

        // 2nd way to print older employees than 30
        employees.forEach(employee -> {
            if ( employee.getAge() > 30) {
                System.out.println(employee.getName());
            }
        });
*/
        // 3rd way, which is Predicate function, to print older employees than 30
        printEmployeesByAge(employees, "Employees over 30", employee -> employee.getAge() > 30);
        printEmployeesByAge(employees, "\nEmployees 30 and under", employee ->employee.getAge() <= 30);

    }

    private static void printEmployeesByAge(List<Employee> employees, String ageText, Predicate<Employee> ageCondition) {

        System.out.println(ageText);
        System.out.println("==================");
        for(Employee employee : employees) {
            if (ageCondition.test(employee)) {
                System.out.println(employee.getName());
            }
        }
    }
}
