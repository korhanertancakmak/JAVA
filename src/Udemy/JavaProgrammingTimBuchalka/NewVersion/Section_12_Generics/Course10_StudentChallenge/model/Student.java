package CourseCodes.NewSections.Section_12_Generics.Course10_StudentChallenge.model;

import CourseCodes.NewSections.Section_12_Generics.Course10_StudentChallenge.util.QueryItem;

import java.util.Random;

//Part-12
/*
                        public class Student implements QueryItem
                                            to
                        public class Student implements QueryItem, Comparable<Student>

    And now you see I have an error, so I want to implement the CompareTo method.
*/
//End-Part-12

public class Student implements QueryItem, Comparable<Student> {

    private static int LAST_ID = 10_000;
    private int studentId;
    private String name;
    private String course;
    private int yearStarted;

//Part-5
/*
        I'll first create a private static field called LAST_ID, which will keep track of the last student id assigned.
    I did something similar in a previous lecture. I'm just going to set that to ten thousand to start, just because I
    don't want student IDs that start with 1. And I will create an instance field, called studentId. I want to populate
    that in my constructor, that does all the other data population.
*/
//End-Part-5

    protected static Random random = new Random();
    private static String[] firstNames = {"Ann", "Bill", "Cathy", "John", "Korhan"};
    private static String[] courses = {"C++", "Java", "Python"};

    public Student() {
        studentId = LAST_ID++;
        int lastNameIndex = random.nextInt(65, 91);
        name = firstNames[random.nextInt(5)] + " " + (char) lastNameIndex;
        course = courses[random.nextInt(3)];
        yearStarted = random.nextInt(2018, 2024);
    }

//Part-6
/*
        I'll add studentId to the toString method, by adding a percent d specifier at the start, and including studentId
    in the arguments list. Now, going to the main method in the main class, I'll first create a new instance of the
    QueryList class.
*/
//End-Part-6

    @Override
    public String toString() {

        return "%d %-15s %-15s %d".formatted(studentId, name, course, yearStarted);
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
    }

//Part-13
/*
        And I'll change the method to compare the student IDs. As I did in an earlier example, I'll use the Integer wrappers
    compareTo method, and pass it the student id of the argument, o. Use Integer value of passing student id, and chain
    a call to compareTo, passing o.studentId. Going back to the main method, I'll change that null, back to Comparator.naturalOrder().
*/
//End-Part-13

    @Override
    public int compareTo(Student o) {
        //return 0;
        return Integer.valueOf(studentId).compareTo(o.studentId);
    }
}
