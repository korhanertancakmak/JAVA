package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_09_NestedClassesAndTypes.Course02_InnerClasses.domain;

import java.util.Comparator;

public class Employee {

    public static class EmployeeComparator <T extends Employee> implements Comparator<Employee> {

        private final String sortType;

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
