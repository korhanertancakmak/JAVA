package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course09_StaticMethodsMultipleUpperBounds.model;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course09_StaticMethodsMultipleUpperBounds.util.QueryItem;

import java.util.Random;

//Part-5
/*
        And I'll implement that method, using IntelliJ's tools.
*/
//End-Part-5
public class Student implements QueryItem {
    private String name;
    private String course;
    private int yearStarted;

    protected static Random random = new Random();
    private static String[] firstNames = {"Ann", "Bill", "Cathy", "John", "Korhan"};
    private static String[] courses = {"C++", "Java", "Python"};

    public Student() {
        int lastNameIndex = random.nextInt(65, 91);
        name = firstNames[random.nextInt(5)] + " " + (char) lastNameIndex;
        course = courses[random.nextInt(3)];
        yearStarted = random.nextInt(2018, 2024);

    }

    @Override
    public String toString() {
        return "%-15s %-15s %d".formatted(name, course, yearStarted);
    }

    public int getYearStarted() {
        return yearStarted;
    }

//Part-6
/*
        And I'll change that code, to use a switch expression, to check each field name that I want to be searchable.

                        String fName = fieldName.toUpperCase();
                        return switch (fName) {
                            default -> false;
                        };

        Ok, so far in this code, all I'm doing is making the field name uppercase. The field name is what gets passed as
    the first argument. I'll return false as a default, meaning, right now, it wouldn't match on anything, because it's
    not really checking any fields. Now I'll add the field names, and what a match is, returning a boolean based on that
    condition.
*/
//End-Part-6

    @Override
    public boolean matchFieldValue(String fieldName, String value) {

        String fName = fieldName.toUpperCase();
        return switch (fName) {
            case "NAME" -> name.equalsIgnoreCase(value);
            case "COURSE" -> course.equalsIgnoreCase(value);
            case "YEARSTARTED" -> yearStarted == (Integer.parseInt(value));
            default -> false;
        };
        //return false;

//Part-7
/*
                        case "NAME" -> name.equalsIgnoreCase(value);

    This means a match is found if the name field is equal to the value, ignoring case.

                        case "NAME" -> name.equalsIgnoreCase(value);

    Same thing here, if the course field's value is equal to what the user is trying to match on, it will return true.

                        case "YEARSTARTED" -> yearStarted == (Integer.parseInt(value));

    The argument is a string, but we want it to be an integer, so I can return true if the year started is equal to the
    year passed. This code will let me filter my student list, by checking any field. I'll add a call to this method, in
    the main method.
*/
//End-Part-7

    }
}
