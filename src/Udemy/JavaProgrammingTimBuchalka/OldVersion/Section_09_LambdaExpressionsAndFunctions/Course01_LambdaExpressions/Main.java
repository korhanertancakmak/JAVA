package CourseCodes.OldSections.Section_09_LambdaExpressionsAndFunctions.Course01_LambdaExpressions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
/*
        //1st way
        //new Thread(new CodeToRun()).start();

        //2nd way
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printing from the Runnable");
            }
        }).start();

        //3rd way
        new Thread(()-> System.out.println("Printing from the Runnable")).start();

        //4th way
        new Thread(()-> {
            System.out.println("Printing from the Runnable");
            System.out.println("Line 2");
            System.out.format("This is line %d\n", 3);
        }).start();
*/
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

        //1st way of using sort by using standard code block
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getName().compareTo(employee2.getName());
            }
        });

        //2nd way of using sort by using lambda expression
        Collections.sort(employees, (employee1, employee2) -> employee1.getName().compareTo(employee2.getName()));

        for(Employee employee : employees) {
            System.out.println(employee.getName());
        }

/*
        String sillyString = doStringStuff(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                return s1.toUpperCase() + s2.toUpperCase();
            }
        },
        employees.get(0).getName(), employees.get(1).getName());
        System.out.println(sillyString);
*/
        UpperConcat uc = (s1, s2) -> s1.toUpperCase() +  s2.toUpperCase();
        String sillyString = doStringStuff(uc, employees.get(0).getName(), employees.get(1).getName());
        System.out.println(sillyString);

    }

    public final static String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}

/*
class CodeToRun implements Runnable{
    @Override
    public void run() {
        System.out.println("Printing from the Runnable");
    }
}
*/

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


interface UpperConcat {
    public String upperAndConcat(String s1, String s2);
}
