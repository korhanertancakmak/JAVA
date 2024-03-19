package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Streams.Course08_StreamingStudentsProject;

import java.time.LocalDate;
import java.time.Period;

//Part-3
/*
        I want this to be mutable to some degree, but I still want to use encapsulation, to control the mutations. I've
    said I want five fields on this class. The first is the course, that this engagement activity pertains to, and I'll
    make that final. It'll get set on construction only. The second field is also final, and it's the enrollment Date,
    and that's a LocalDate. I've used this class a couple of times, usually to get the current year or month. For this
    code, I'm not going to do a whole lot of date processing. I really just care about year and month, but it's still
    convenient to use this class. engagementType, right would start with enrollment, then track progress. lastLecture
    will keep track of the last lecture the student watched. I'll use another Local Date for the lastActivityDate.
*/
//End-Part-3

public class CourseEngagement {

    private final Course course;
    private final LocalDate enrollmentDate;
    private String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;

    public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType) {
        this.course = course;
        this.enrollmentDate = this.lastActivityDate = enrollmentDate;
        this.engagementType = engagementType;
    }

//Part-4
/*
        I'll generate a constructor, picking the first three fields. I'll insert a new line after the enrollment Date,
    so that the whole signature is visible. I'll assign the lastActivityDate information to be equal to the enrollment
    date, to start with. I can include this in an assignment chain, so I'll insert equals lastActivityDate, after this.enrollmentDate
    equals, and before that last enrollmentDate. I'll generate getters for all my fields. I'm going to change some of
    these, starting with getCourse.
*/
//End-Part-4

    public String getCourseCode() {
        return course.courseCode();
    }

    public int getEnrollmentYear() {
        return enrollmentDate.getYear();
    }

    public String getEngagementType() {
        return engagementType;
    }

    public int getLastLecture() {
        return lastLecture;
    }

    public int getLastActivityYear() {
        return lastActivityDate.getYear();
    }

    public String getLastActivityMonth() {
        return "%tb".formatted(lastActivityDate);
    }

    public double getPercentComplete() {
        return lastLecture * 100.0 / course.lectureCount();
    }

    public int getMonthsSinceActive() {

        LocalDate now = LocalDate.now();
        var months = Period.between(lastActivityDate, now).toTotalMonths();
        return (int) months;
    }

//Part-5
/*
        Instead of getCourse, I want it to just be, getCourseCode, so that will return a String, and not a course. And
    I'll return the courseCode on the course field. Next, Instead of getEnrollmentDate, I just want to return the year.
    I'll return an int, instead of a local date, and I'll change the method name to getEnrollmentYear, and just return
    the year there. I want to do the same thing with getLastActivity Date, so it will return an int. I'll rename it to
    getLastActivityYear, and return the getYear result from that. I'm going to add some other getters now. The next one
    is getLastActivityMonth, and instead of an int, I'm going to have this return a String, which will be the month's
    abbreviation. I can use a time specifier %t for time. %tb is the way to get the three character month abbreviation.
    The next method is getPercentComplete, and that returns a double. This will divide the last lecture, by the total
    lectures, multiplying that by 100. I'll make 100 a decimal here, so that this will be processed as a double, and not
    truncated to an int. The next method is getMonthsSinceActive, and takes no parameters, but returns an int, again I'll
    make it public. This method will tell me how many months have elapsed, since a student last had any activity, for
    this course. There are different ways to get elapsed time. I'll cover date time processing in a lot of detail, in an
    upcoming lecture, but here, I'll just show you one way to get the number of months, between two date fields. First
    I'll get the current date, and you've seen me do this, using the now method on LocalDate. There's a class called Period,
    introduced in JDK 8, that has a static method on it, named between, that takes two dates. This returns a Period instance,
    which you can then query in different ways. This has a method called two TotalMonths, and that's what I want here.
    This returns a long though, but I can safely cast that to an int, because my elapsed months in this code, aren't going
    to exceed an integer's range. The next method I want to add, is watchLecture.
*/
//End-Part-5

    void watchLecture(int lectureNumber, LocalDate currentDate) {

        lastLecture = Math.max(lectureNumber, lastLecture);
        lastActivityDate = currentDate;
        engagementType = "Lecture " + lastLecture;
    }

//Part-6
/*
        I'll make this package private, it'll only get called through the student instance. It takes a lecture number,
    and another date. I can use a static method on Math, called max, that will give me the highest of the two numbers I
    pass it. Students can review earlier lectures at any time, but I don't want to set last lecture in that example.
    I'll set the lastActivity Date to the date passed. If this were a real app, I'd want the current date, but I'm going
    to mock up past activity, and pass the date in. The engagement type will just reflect the maximum lecture the student's
    watched. Finally, I want a toString method, so I'll generate that with no fields. I'll replace that return statement.
    I'll add my own return statement, and as usual, I'll use a formatted String, so it will print out the course code first.
    Then, the active month, active year, the engage Type, and the number of elapsed months, since the last activity. Ok,
    so that's if for the CourseEngagement class. Now, I'll build the Student class, and finish with a stream of randomly
    generated students. This will be the launching point, to your challenge on terminal operations, and will also provide
    many opportunities, to give stream operations a good workout.
*/
//End-Part-6

    @Override
    public String toString() {
        return "%s: %s %d %s [%d]".formatted(course.courseCode(),
                getLastActivityMonth(), getLastActivityYear(), engagementType,
                getMonthsSinceActive());
    }

}
