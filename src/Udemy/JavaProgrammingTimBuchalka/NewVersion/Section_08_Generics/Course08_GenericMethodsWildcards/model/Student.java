package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course08_GenericMethodsWildcards.model;

import java.util.Random;

public class Student {
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
}
