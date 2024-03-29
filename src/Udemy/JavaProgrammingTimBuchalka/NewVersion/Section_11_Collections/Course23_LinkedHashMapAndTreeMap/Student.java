package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course23_LinkedHashMapAndTreeMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Part-2
/*
        Before I work on this, I'm going to set up two records, the first is for Course, this will be the courses a student
    will enrol in. I'll include these in the Student.java file for simplicity. Course will have three fields, all strings,
    course id, name and subject. The second record will be for a course purchase, so I'll call it Purchase. I want this
    to have five fields. The first is the course Id, an identifier for the course they'll be purchasing, an integer for
    the student id, and a double for the price of the course. In addition to that, I want a year, and a day of year, both
    integers. The year will just be the year of the purchase, and day of Year, is an integer, which is between 1 and 365.
    These two pieces of information can give us a date. I'll add a method to this record, called purchaseDate, it's public
    and will return a type, Local Date, which I've used once or twice before. I'll be covering dates in great detail,
    in an upcoming section, so this is a preview of things to come. LocalDate has a static method, named, of year day,
    that takes an integer for a year, and an integer for the dayOfYear, and returns a date instance. I'll be using this
    date, a bit later, as a key in my map. Let's get back to the Student class, and set up a few fields.
*/
//End-Part-2

record Course(String courseId, String name, String subject) {}

record Purchase (String courseId, int studentId, double price, int yr, int dayOfYear) {

    public LocalDate purchaseDate() {
        return LocalDate.ofYearDay(yr, dayOfYear);
    }

}

//Part-3
/*
        First, I want a static int, lastId, and I'll assign that an initial value of 1. You've seen me do this quite
    often, with student or employee classes, because I want id assigned, as part of the construction of the object. The
    lastId used gets stored in this field. Students need a name. The id field is going to get automatically assigned,
    using the static last id, when a new Student is created. And students will have a list of courses. I'll set up two
    constructors, the first will have name and course list as arguments. I'll add another statement to this constructor,
    to set the id. This gets assigned the value in last id, my static field. I'll increment lastId after the assignment,
    so I use a post fix increment. I'm going to copy this constructor, and paste a copy right below the first constructor.
    I'll change the second argument. The type will be my Course record, and its name will be course in lower case. This
    is going to let me create a new student, with just a name and single course. I'll remove all the statements that are
    there, and instead chain a call to the first constructor. I'll pass name, and a new ArrayList, which will include the
    one course passed to this constructor. Next, I'll create two getters, one for name, and one for the student's id. I'll
    include a method to add a course, so public, void, add Course, and that has one parameter, a Course. And I'll simply
    call courseList.add, passing it the course argument.
*/
//End-Part-3

public class Student {

    public static int lastId = 1;

    private String name;
    private int id;
    private List<Course> courseList;

    public Student(String name, List<Course> courseList) {
        this.name = name;
        this.courseList = courseList;
        id = lastId++;
    }

    public Student(String name, Course course) {
        this(name, new ArrayList<>(List.of(course)));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

//Part-4
/*
        Lastly, I'll add a two string method, by generating the override. I'll replace that statement, with a bit of code.
    First, I'll set up a String array, called courseName, and it'll be the same size as the course list field. This is
    just going to house the course names. I'll use Arrays.setAll, to populate my string arrays, using a lambda expression,
    and using the index, to get the course, and ultimately it's name, which goes in my local array. From the lambda, I'll
    return a formatted string that has the student id, and the course name list. Finally, I'll use join on my local string
    array, to create a comma delimited list of course names. Ok, so that's enough set up, to let me simulate students
    registering for my courses, for example. Getting back to the Main class,
*/
//End-Part-4


    @Override
    public String toString() {

        String[] courseNames = new String[courseList.size()];
        Arrays.setAll(courseNames, i -> courseList.get(i).name());
        return "[%d] : %s".formatted(id, String.join(", ", courseNames));
    }
}
