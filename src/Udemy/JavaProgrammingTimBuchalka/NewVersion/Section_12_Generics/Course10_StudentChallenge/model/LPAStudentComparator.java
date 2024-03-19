package CourseCodes.NewSections.Section_12_Generics.Course10_StudentChallenge.model;

//Part-16
/*
        Again, this isn't the preferred place to put a comparator. In the next section I'll present multiple other ways,
    but for now, I'll create this Comparator class this way. This class needs to implement the Comparator interface. And
    I want the type argument to be LPAStudent, since I'll be sorting by an attribute on that class only. I'll use IntelliJ's
    tool to implement the compare method. And now, instead of returning 0, I'll return the difference between the percent
    complete values of these 2 students.
*/
//End-Part-16

import java.util.Comparator;

public class LPAStudentComparator implements Comparator<LPAStudent> {
    @Override
    public int compare(LPAStudent o1, LPAStudent o2) {

        return (int) (o1.getPercentComplete() - o2.getPercentComplete());
        //return 0;
    }

//Part-17
/*
        In this code, I'm casting to an int, since the expression is evaluating 2 doubles and returning a double, but an
    int is the return type of the method on the interface. And now, I can use this to sort the matches in the main method.
*/
//End-Part-17
}
