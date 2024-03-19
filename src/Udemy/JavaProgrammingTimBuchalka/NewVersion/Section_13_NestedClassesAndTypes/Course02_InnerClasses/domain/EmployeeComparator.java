package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_NestedClassesAndTypes.Course02_InnerClasses.domain;

import java.util.Comparator;

public class EmployeeComparator <T extends Employee> implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        //return 0;
        return o1.getName().compareTo(o2.getName());
        //return o1.yearStarted - o2.yearStarted;
    }
}
