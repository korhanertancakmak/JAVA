package CourseCodes.OldSections.Section_09_LambdaExpressionsAndFunctions.Course07_ChainingFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

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


        // Another Function to use toUpperCase
        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        // Another Function to get first name
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));
        // Chaining Functions to get first name in uppercase
        Function<Employee, String> chainedFunction = upperCase.andThen(firstName);
        // Test of Chaining Functions to get first name in uppercase
        System.out.println(chainedFunction.apply(employees.get(0)));

        // BiFunction representation, which we can use 2 arguments in it
        BiFunction<String, Employee, String> concatAge = (String name, Employee employee) -> name.concat(" " + employee.getAge());

        // First apply for making the name uppercase
        String upperName = upperCase.apply(employees.get(0));
        // And then second apply to concat the name with the age
        System.out.println(concatAge.apply(upperName, employees.get(0)));

        // Specific Type of Function, UnaryOperator, and testing it
        IntUnaryOperator incBy5 = i -> i + 5;
        System.out.println(incBy5.applyAsInt(10));

        // Consumer Function representation
        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello, World!");
    }

}

