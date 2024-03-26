package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course09_StaticMethodsMultipleUpperBounds.model;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course09_StaticMethodsMultipleUpperBounds.util.QueryItem;

import java.util.Random;

public class Student implements QueryItem {
    private final String name;
    private final String course;
    private final int yearStarted;

    protected static Random random = new Random();
    private static final String[] firstNames = {"Ann", "Bill", "Cathy", "John", "Korhan"};
    private static final String[] courses = {"C++", "Java", "Python"};

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
    }
}
