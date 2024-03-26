package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course10_StudentChallenge.model;

import java.util.Comparator;

public class LPAStudentComparator implements Comparator<LPAStudent> {
    @Override
    public int compare(LPAStudent o1, LPAStudent o2) {

        return (int) (o1.getPercentComplete() - o2.getPercentComplete());
        //return 0;
    }
}
