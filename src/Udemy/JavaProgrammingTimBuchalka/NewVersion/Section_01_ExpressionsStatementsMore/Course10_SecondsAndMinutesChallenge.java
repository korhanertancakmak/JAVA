package CourseCodes.NewSections.Section_05_ExpressionsStatementsMore;

/*
Course-16

                                          Seconds And Minutes Challenge

        In this challenge, we're going to create a method, that takes time, represented in seconds, as the parameter. We'll
    then want to transform the seconds into hours. Next we'll display the time in hours, with the remaining minutes and
    seconds, in a String. We'll do this transformation in two steps, which allows us to use overloaded methods.

        We want to create two methods with the same name: getDurationString
    * The first method has one parameter of type int, named seconds.
    * The second method has two parameters, named minutes and seconds, both ints.
    * Both methods return a String in the format shown:

                                             "XXh YYm ZZs"

    where XX represents the number of hours, YY the number of minutes, and ZZ the number of seconds.
    * The first method should in turn call the second method to return its results.
    * Make both methods public and static as we've been doing so far in this course.
    * Remember that one minute is 60 secs, and 1 hour equals 60 mins, or 3600 secs.
    * Start by creating a new project, and call it SecondsAndMinutesChallange.

        Add validation to the methods as a bonus:
    * For the first method, the seconds parameter should be >= 0.
    * For the second method, the minutes parameter should be >= 0, and the seconds parameter should be >= 0, and <= 59.
    * If either method is passed an invalid value, print out some type of meaningful message to the user.

*/
public class Course10_SecondsAndMinutesChallenge {
    public static void main(String[] args) {

        System.out.println(getDurationString(65, 145));
    }

    public static String getDurationString(int secs) {
        return (secs >= 0) ? getDurationString(0,secs) : "This is an invalid value";
    }

    public static String getDurationString(int mins, int secs) {
        int hours = (secs / 3600) + (mins / 60);
        int totalmins = ((secs % 3600) / 60) + (mins % 60);
        int remainingSecs = (secs % 3600) % 60;

        return (totalmins >= 0 && (remainingSecs >= 0 && remainingSecs <= 59)) ?
                hours + "h " + totalmins + "m " + remainingSecs + "s" : "This is an invalid value";
    }
}
