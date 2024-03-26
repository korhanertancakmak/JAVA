package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course07_GenericsExtra.model;

//Part-9
/*
        And I'll have that extend Student. I'll add an extra field, which isPercentComplete, which indicates how far along
    the student is in the course. This will be a double. And I'll type in my constructor, which again will be no args
    constructor. This code will execute the super constructor implicitly, and that's going to generate data for the other
    fields. This class has one additional field, so I want to randomly generate data for it. Here, I'm using the random
    field from the Student class, and I get a random number from 0 to 100 percent. Next, I'll generate an override for
    the toString method, and I'll include this new field in a formatted string.
*/
//End-Part-9
public class LPAStudent extends Student {

    private double percentComplete;

    public LPAStudent() {

        percentComplete = random.nextDouble(0, 100.001);
    }

//Part-10
/*
        Notice, in this formatted String, I have 2 percent signs after the specified percent 8.1f. This is how you print
    out a percent sign in the output, so it's a specifier for a percent sign. After creating getter for PercentComplete,
    going back to the main method,
*/
//End-Part-10

    @Override
    public String toString() {
        return "%s %8.1f%%".formatted(super.toString(), percentComplete);
    }

    public double getPercentComplete() {
        return percentComplete;
    }
}

