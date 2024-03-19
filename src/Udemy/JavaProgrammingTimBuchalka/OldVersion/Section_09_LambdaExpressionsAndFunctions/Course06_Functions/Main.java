package CourseCodes.OldSections.Section_09_LambdaExpressionsAndFunctions.Course06_Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
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

        // 1st way of getting lastName of the employees
        employees.forEach(employee -> {
            String lastName = employee.getName().substring(employee.getName().indexOf(' ') + 1);
            System.out.println("Last Name is: " + lastName);
        });

        // 2nd way of getting lastName of the employees
        Function<Employee, String> getLastName = (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(' ') + 1);
        };

        // Test of 2nd way of getting lastName of the employees on the first object in the list
        String lastName = getLastName.apply(employees.get(1));
        System.out.println(lastName);

        // 2nd way of getting firstName of the employees
        Function<Employee, String> getFirstName = (Employee employee) -> {
            return employee.getName().substring(0, employee.getName().indexOf(' '));
        };

        // Test of 2nd way of getting firstName and lastName of the employees on all objects in the list
        Random random1 = new Random();
        for(Employee employee : employees) {
            if(random1.nextBoolean()) {
                System.out.println(getAName(getFirstName, employee));       // getAName method applies "apply" method in Function
            } else {
                System.out.println(getAName(getLastName, employee));
            }
        }
    }

    private static String getAName(Function<Employee, String> getName, Employee employee) {
        return getName.apply(employee);
    }

}

