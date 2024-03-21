package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_02_ControlFlow;

public class Course10_LocalVariablesScope {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {     // i declared in for loop declaration
            System.out.println(i);
        }
        //System.out.println(i);   // ERROR! i is out of scope.
    }
}
