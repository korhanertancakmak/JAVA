package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_NestedClassesAndTypes.Course02_InnerClasses.domain;

//Part-2
/*
        I'll have it extend the Employee class that we created in the last lecture. I want to add a field to this class,
    called store. Next I'll add an inner class, and call it StoreComparator.
*/
//End-Part-2

import java.util.Comparator;

public class StoreEmployee extends Employee {

    private String store;

//Part-4
/*
        I also want 2 constructors on this class. I'll be using the first, a no args constructor, to access this comparator
    from the calling code, so I'll generate that now. And then, I'll generate a four argument constructor that will call
    super's constructor, and then assign the store argument to the store field. Finally, I want the store to print out
    for each employee, so I want to override the toString method. I want to print out the store, so I'll remove that return
    statement. Instead I'll return formatted string that has the store, and also the string representation of a regular
    employee. Now, I want to go back to the main method.
*/
//End-Part-4

    public StoreEmployee() {
    }

    public StoreEmployee(int employeeId, String name, int yearStarted, String store) {
        super(employeeId, name, yearStarted);
        this.store = store;
    }

    @Override
    public String toString() {
        //return super.toString();
        return "%-8s%s".formatted(store, super.toString());
    }

    public class StoreComparator<T extends StoreEmployee> implements Comparator<StoreEmployee>{

//Part-3
/*
        I do this by declaring a class inside the StoreEmployee class's opening and closing brackets. I'll have that implement
    Comparator with a type argument of StoreEmployee. I'll add the class, and add a diamond operator with T extends
    StoreEmployee within. And implement Comparator with StoreEmployee in the diamond operator. And IntelliJ tells me "I
    need to implement a compare method", so I'll do that. I want to add code, that first compares the store field.
    Comparing o1.store with o2.store and save the result, return it. If that result is 0, that means the employees work
    at the same store, so I want to add another comparison level. This time, I'll call the Employee.EmployeeComparator
    constructor, passing it the yearStarted, and using that compare method.

                if (result == 0) {
                    return new Employee.EmployeeComparator<>("yearStarted").compare(o1, o2);
                }

    This checking if employees are the same store. If yes, then return yearStarted, compare for both stores. This class's
    compare method will sort by store, then yearStarted within the same store.
    string
*/
//End-Part-3

        @Override
        public int compare(StoreEmployee o1, StoreEmployee o2) {
            //return 0;
            int result = o1.store.compareTo(o2.store);
            if (result == 0) {
                return new Employee.EmployeeComparator<>("yearStarted").compare(o1, o2);
            }
            return result;
        }
    }
}
