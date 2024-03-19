package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Generics.Course07_GenericsExtra.model;

import java.util.Random;

public class Student {
//Part-2
/*
        My student class needs a few fields. I'm interested in a student's name, the course they're taking, and the year
    they signed up for the course.
*/
//End-Part-2

    private String name;
    private String course;
    private int yearStarted;

//Part-3
/*
        I'm going to spend a little extra time setting up this Student class, with a bit of extra functionality and complexity.
    I'll be using this over the next couple of lectures, so bear with me for a couple of minutes while I set this up. I
    want 3 more fields, which I'll use to create random data for a set of Students. I'll set up a random field to get
    random numbers. This is protected because I want subclasses to be able to access this helper field. These fields will
    help me create a lot of students with different data. I've made them static, because I don't want each instance to
    have this data, it can be stored with the class instance instead. I'm doing this because I will eventually want a
    larger set of students. Next, I'll create my constructor with no arguments, because all the student data will get
    generated.
*/
//End-Part-3

    protected static Random random = new Random();
    private static String[] firstNames = {"Ann", "Bill", "Cathy", "John", "Korhan"};
    private static String[] courses = {"C++", "Java", "Python"};

//Part-4
/*
        I want to randomly generate a single character for the last name, so 65 is the integer value for capital A. 90
    is integer value for the capital Z, so I use 91 for the upper bound, because the number generated will be exclusive
    of this upper bound. I randomly pick an integer from 0 to 5, to get a first name from my names array, then append a
    space and the last name index to that. I randomly get an integer from 0 to 2 to pick a course from the course list.
    And the year started, will be a random integer from 2018 to 2023, again because its exclusive of the upper bound of
    2024. I want to create a 2 String method for this next.
*/
//End-Part-4

    public Student() {
        int lastNameIndex = random.nextInt(65, 91);
        name = firstNames[random.nextInt(5)] + " " + (char) lastNameIndex;
        course = courses[random.nextInt(3)];
        yearStarted = random.nextInt(2018, 2024);

    }

//Part-5
/*
        And I'll change that code just to return a single line. The returned string is formatted and includes name, course
    and year started. I want name and course to be left justified, so -15 in both cases, in the format specifiers, where
    negative is the indicator to left justify, and 15 is the allotted width. Finally, I'll generate a getter for one of
    the fields, yearStarted.
*/
//End-Part-5


    @Override
    public String toString() {
        return "%-15s %-15s %d".formatted(name, course, yearStarted);
    }

//Part-6
/*
        Ok, this is enough code to give me many unique students, so I'll go back to the main method on the main class,
    and set up a quick test.
*/
//End-Part-6

    public int getYearStarted() {
        return yearStarted;
    }
}
