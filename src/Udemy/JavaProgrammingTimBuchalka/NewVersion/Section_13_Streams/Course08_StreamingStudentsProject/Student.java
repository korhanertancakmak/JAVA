package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_Streams.Course08_StreamingStudentsProject;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//Part-7
/*
        I left off, in the last lecture, having created the Course, and the CourseEngagement types. In this lecture, I'll
    finish this set up code, by creating my Student class. The first thing, I'll include is a static long, to help do my
    student id assignments, and call it lastStudentId, setting that to 1. I'm also going to set up a private final static,
    random variable, that I'll use for all the randomization I want to perform, when I create random students. For my
    instance fields, I'm going to make them all private and final. I've got a studentId, a long. Country code will be a
    String. yearEnrolled is an int, and will be the year of the first enrollment. age Enrolled is an int, the age of the
    student when they enrolled. gender is a String. And I have a boolean for programmingExperience. I've also got a Map
    of CourseEngagement records, keyed by the course code, a String. I'll instantiate it here, as a hashmap. It doesn't
    need to be ordered. Next, I'll generate a constructor,
*/
//End-Part-7

public class Student {

    private static long lastStudentId = 1;
    private final static Random random = new Random();

    private final long studentId;
    private final String countryCode;
    private final int yearEnrolled;
    private final int ageEnrolled;
    private final String gender;
    private final boolean programmingExperience;

    private final Map<String, CourseEngagement> engagementMap = new HashMap<>();

    public Student(String countryCode, int yearEnrolled, int ageEnrolled, String gender, boolean programmingExperience, Course... courses) {
        studentId = lastStudentId++;
        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageEnrolled = ageEnrolled;
        this.gender = gender;
        this.programmingExperience = programmingExperience;

        for (Course course : courses) {
            addCourse(course, LocalDate.of(yearEnrolled, 1, 1));
        }
    }

//Part-8
/*
        That includes all the fields, except for studentId, from the list there. I'll insert a new line after gender.
    I'll add a variable argument for courses, so I can set up a new student with one or more courses. In the constructor,
    I want my student id to be the value of last student id, and I'll increment that static field, after the assignment
    occurs. Since courses is a variable argument, I can use it in an enhanced for loop, which I'll do next. For each
    course passed in, I'll call an add course method, which I'll be implementing in a minute. I'm going to pass the course
    and also a date. I can build a date from it's parts, meaning, year, month, day, using LocalDate.of. In this case, all
    students will get enrolled on the first month of the year, and on the first day.
*/
//End-Part-8

    public void addCourse(Course newCourse) {
        addCourse(newCourse, LocalDate.now());
    }

    public void addCourse(Course newCourse, LocalDate enrollDate) {

        engagementMap.put(newCourse.courseCode(),
                new CourseEngagement(newCourse, enrollDate, "Enrollment"));
    }

//Part-9
/*
        Next, I'll add the addCourse method so this code compiles, and that'll be public and void. It'll take a course,
    I'll call that new course, and a date, enrollDate. This adds an entry to the engagement map, keyed by course code.
    The map value is a course engagement instance, and that takes a course, an enrollment date, and an engagement type
    which will be enrollment to start. I also want an overloaded version of this, that doesn't take any date. I'll put
    this one above the other. For this one, I'll pass the current date to the overloaded method. Next, I'll generate a
    bunch of getters, all public, because this is the data I'll be using, for many of the stream code samples coming up.
    I'll generate getters for every field, except the first one, the static lastStudentId.
*/
//End-Part-9

    public long getStudentId() {
        return studentId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getYearEnrolled() {
        return yearEnrolled;
    }

    public int getAgeEnrolled() {
        return ageEnrolled;
    }

    public String getGender() {
        return gender;
    }

    public boolean hasProgrammingExperience() {
        return programmingExperience;
    }

    public Map<String, CourseEngagement> getEngagementMap() {
        return Map.copyOf(engagementMap);
    }

    public int getYearsSinceEnrolled() {
        return LocalDate.now().getYear() - yearEnrolled;
    }

    public int getAge() {
        return ageEnrolled + getYearsSinceEnrolled();
    }

    public int getMonthsSinceActive(String courseCode) {

        CourseEngagement info = engagementMap.get(courseCode);
        return info == null ? 0 : info.getMonthsSinceActive();
    }

    public int getMonthsSinceActive() {

        int inactiveMonths = (LocalDate.now().getYear() - 2014) * 12;
        for (String key : engagementMap.keySet()) {
            inactiveMonths = Math.min(inactiveMonths, getMonthsSinceActive(key));
        }
        return inactiveMonths;
    }

//Part-10
/*
        I want to change the getter that's got the name, is programming experience, to has programming experience. I'll
    make a change to the getEngagementMap method, and that's because I want to return a defensive copy. I'll return a copy
    of the engagementMap using Map.copyOf. I'm going to next, add getters for some calculated fields, that'll be useful.
    I'll create a method called getYearsSinceEnrolled. This returns an int. It returns the years, between the current Year,
    which I'll get from LocalDate.now, and the enrolled Year. After this, I'll add getAge, which also returns an int.
    This one will return the enrolled age, plus the years since enrolled. Next, I need a method that will give me the
    elapsed months, since the last activity. It should return an int, and be called getMonthsSinceActive, taking a String
    for the course code. I'll get the CourseEngagement record from the map, using courseCode. I'll use a ternary operator
    to avoid a null pointer exception, so if the course wasn't found, it'll return 0 months. Otherwise, I'll call the
    getMonths since active method, on the course engagement record. Next, I want to get the overall Months Since Active,
    so this has the same name, returns an int, but doesn't take any parameters. In this method, I'll first set up a variable,
    an int, inactiveMonths. I'll initialize this to the maximum number of months possible, getting the current year and
    subtracting 2014, and multiplying 12 to get months. Any activity for a student, should be less than this number. I'll
    loop through each key of the engagement map, for each course code. I'll use another static method on Math, called min.
    It gives me the lesser of the two numbers. This means I'll get back the months elapsed for the most recent activity.
    I'll add one more getter method on this class, a method called getPercentComplete.
*/
//End-Part-10

    public double getPercentComplete(String courseCode) {

        var info = engagementMap.get(courseCode);
        return (info == null) ? 0 : info.getPercentComplete();
    }

    public void watchLecture(String courseCode, int lectureNumber, int month, int year) {

        var activity = engagementMap.get(courseCode);
        if (activity != null) {
            activity.watchLecture(lectureNumber, LocalDate.of(year, month, 1));
        }
    }

//Part-11
/*
        The CourseEngagement class has a method of the same name, so in this case, I'll invoke that, so I'll need course
    code as the input, and it will return a double. I'll use the course code, to get a course engagement record. If that
    doesn't exist, I'll just return zero, otherwise I'll call getPercentComplete on it. I said in the introduction, that
    I wanted a method called watchLecture, that takes a course code, a lecture number, and a month and year for the date.
    This is public, because you can imagine a course management program, calling this code, when a student logs into a
    course, and listens to a lecture. This takes a month and a year, both integers. Again, I'm passing this, because these
    will be randomly generated. I'll get the activity record from the engagement map, using the course code. If I found
    an engagement record, I'll call watchLecture on that, passing it the lecture number. I can construct a date, from the
    year, month, and day 1, and pass that next. I use one as the day because I really don't care about days in the code
    ahead.
*/
//End-Part-11


    private static String getRandomVal(String... data) {
        return data[random.nextInt(data.length)];
    }

//Part-15
/*
        The last thing I want to do is, create a public static method on student, that will generate a new instance,
    populated with a lot of random data. Before I do that, I'll create a little helper method that picks a random item
    from an array of Strings. This will be private and static, and return a String. I'll call it getRandomVal, and it'll
    take a variable argument of strings. This will return an element from the array, using the index picked. It'll call
    nextInt on random, passing the length of this array as the upper bound number.
*/
//End-Part-15

    public static Student getRandomStudent(Course... courses) {

        int maxYear = LocalDate.now().getYear() + 1;

        Student student = new Student(
                getRandomVal("AU", "CA", "CN", "GB", "IN", "UA", "US"),
                random.nextInt(2015, maxYear),
                random.nextInt(18, 90),
                getRandomVal("M", "F", "U"),
                random.nextBoolean(),
                courses);

        for (Course c : courses) {
            int lecture = random.nextInt(1, c.lectureCount());
            int year = random.nextInt(student.getYearEnrolled(), maxYear);
            int month = random.nextInt(1, 13);
            if (year == (maxYear - 1)) {
                if (month > LocalDate.now().getMonthValue()) {
                    month = LocalDate.now().getMonthValue();
                }
            }
            student.watchLecture(c.courseCode(), lecture, month, year);
        }
        return student;
    }

//Part-16
/*
        Now I'll create the getRandomStudent method, and that'll be public and static. It returns a Student, and the only
    data it will take is a variable argument for courses. First I want the maximum bound for any generated year. This will
    be the current year + 1. I'll create a new student. I'll first pass a random country code, from a short list, using
    the helper method I just created, and passing a list of country codes. I'll pass an enrollment year between 2015, so
    the first year I started offering courses. After that, I'll use maxYear, so this will give me a year between 2015,
    and the current year included. Next, I need an age, so I'll make that between 18 and 89. To get gender, I'll use
    getRandomVal, with three possibilities, M for male, f for female, or u for unselected. I'll generate a random boolean,
    for the Programming experience flag. Finally, I'll just pass the courses straight into this constructor. I can get a
    random student with this data, but I also want to create some random course activity, and I'll code this next. I'll
    loop through the courses that were passed. For each course I want a random lecture number. I want a random year, but
    it should not be less than the year enrolled. I'll get a random month. If the year I got back is the current year, I
    need to make sure the month isn't ever greater than the current month. If it is, I'll just set it to the current month.
    I'll execute watchLecture. This will update the engagement record, for this course, with some activity, other than
    enrollment. Now I'll get back the main method,
*/
//End-Part-16

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", countryCode='" + countryCode + '\'' +
                ", yearEnrolled=" + yearEnrolled +
                ", ageEnrolled=" + ageEnrolled +
                ", gender='" + gender + '\'' +
                ", programmingExperience=" + programmingExperience +
                ", engagementMap=" + engagementMap +
                '}';
    }

//Part-12
/*
        After this, I want to generate a two String method, using all the fields. I went through this code, to set up
    Student pretty quickly, because it's a bit tedious to set up all the getters and so on. I've written a lot of code
    without testing anything yet, so it's time for a bit of code, in the main method of the Main class.
*/
//End-Part-12
}
