package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course10_StudentChallenge.model;

public class LPAStudent extends Student {

    private double percentComplete;

    public LPAStudent() {

        percentComplete = random.nextDouble(0, 100.001);
    }

    @Override
    public String toString() {
        return "%s %8.1f%%".formatted(super.toString(), percentComplete);
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {

        if (fieldName.equalsIgnoreCase("percentComplete")) {
            return percentComplete <= Integer.parseInt(value);
        }
        return super.matchFieldValue(fieldName, value);
    }

//Part-14
/*
        We need to override the matchFieldValue method. And then I'll add the code to check the value of percentComplete
    with the argument passed, if the field name is percentComplete. Does field name equal percentComplete ignoring case?

                        if (fieldName.equalsIgnoreCase("percentComplete")) {
                            return percentComplete <= Integer.parseInt(value);
                        }

    Here, I'm returning true if percentComplete is less than or equal to the value passed. Remember, if this method returns
    true, our query will include this item, as a match. Now, going to the main method, I want to test this out.
*/
//End-Part-14
}

