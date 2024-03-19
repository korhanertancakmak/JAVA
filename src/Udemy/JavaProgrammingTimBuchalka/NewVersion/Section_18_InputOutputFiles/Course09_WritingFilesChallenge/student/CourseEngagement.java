package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_InputOutputFiles.Course09_WritingFilesChallenge.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.StringJoiner;

public class CourseEngagement {

    private final String courseCode;
    private String engagementType;

    private final int enrollmentMonth;
    private final int enrollmentYear;

    private int lastLecture;
    private int lastActiveMonth;
    private int lastActiveYear;


    public CourseEngagement(String courseCode, int month, int year, String engagementType) {
        this.courseCode = courseCode;
        enrollmentMonth = lastActiveMonth = month;
        enrollmentYear = lastActiveYear = year;
        this.engagementType = engagementType;
    }

    public String getEngagementType() {
        return engagementType;
    }

    public int getEnrollmentMonth() {
        return enrollmentMonth;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public int getLastLecture() {
        return lastLecture;
    }

    public int getLastActiveMonth() {
        return lastActiveMonth;
    }

    public int getLastActiveYear() {
        return lastActiveYear;
    }


    public double getPercentComplete(int lectureCount) {
        return lastLecture*100.0/lectureCount;
    }

    public int getInactiveMonths() {

        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();

        int months = (now.getYear() - lastActiveYear) * 12;
        if (currentMonth > lastActiveMonth) {
            months += (currentMonth - lastActiveMonth);
        } else {
            months -= (lastActiveMonth - currentMonth);
        }
        return months;
    }


    @Override
    public String toString() {
        return "%s,%s,%d,%s".formatted(courseCode,
                Month.of(lastActiveMonth), lastActiveYear, engagementType);
    }

    public String toJSON() {
        return new StringJoiner(", ", "{", "}")
                .add("\"courseCode\":\"" + courseCode + "\"")
                .add("\"engagementType\":\"" + engagementType + "\"")
                .add("\"enrollmentMonth\":" + enrollmentMonth)
                .add("\"enrollmentYear\":" + enrollmentYear)
                .add("\"lastLecture\":" + lastLecture)
                .add("\"lastActiveMonth\":" + lastActiveMonth)
                .add("\"lastActiveYear\":" + lastActiveYear)
                .toString();
    }

//Part-8
/**
        And do the exact same thing, again right under the existing toString method. Ok, so that was easy. I want to get
 back to the Student class,
**/
//End-Part-8

    void recordLastActivity(int lectureNumber, int month, int year) {

        if (lectureNumber > lastLecture) {
            lastLecture = lectureNumber;
        }
        lastActiveMonth = month;
        lastActiveYear = year;
        engagementType = "Lecture " + lastLecture;
    }
}
