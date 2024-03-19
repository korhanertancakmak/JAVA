package CourseCodes.NewSections.Section_13_NestedClassesAndTypes.Course01_StaticNestedClasses.domain;

//Part-3
/*
        I want this to be generic, and accept and Employee or subtype of Employee, so I'll include a type parameter. And
    I'll add implements Comparator for that, using Employee as the type argument. Now, I need to implement methods to get
    rid of that compiler error, so I want to include a compare method on this class. And because I included a getter for
    the name field, I can use that getter to compare names between 2 Employee objects in this method. I'll replace the
    "return 0;" statement with that. Next, I'll set up a few employees, in the Main class's main method, in a List.
*/
//End-Part-3

import java.util.Comparator;

public class EmployeeComparator <T extends Employee> implements Comparator<Employee> {

//Part-6
/*
        I'll comment out the return statement that's currently there, and try to do this, using year started. Compare
    year started for each employee by returning the result of deducting o2 year started from o1 year started.

                return o1.yearStarted - o2.yearStarted;

    But this doesn't work, because that field, year started, is private on Employee, and this class, EmployeeComparator,
    is external to the Employee class. To get this class to work with year started, I'd need to either change the access
    modifier for that field, or provide a getter for it. And maybe there are reasons I don't really want to do that. I'll
    revert that code, removing that last change, and uncommenting the first statement. Now, I want to copy that whole class,
    from the class name to the ending closing brace. I'll jump over to the Employee class, and paste the code at the top
    of this class.
*/
//End-Part-6

    @Override
    public int compare(Employee o1, Employee o2) {
        //return 0;
        return o1.getName().compareTo(o2.getName());
        //return o1.yearStarted - o2.yearStarted;
    }
}
