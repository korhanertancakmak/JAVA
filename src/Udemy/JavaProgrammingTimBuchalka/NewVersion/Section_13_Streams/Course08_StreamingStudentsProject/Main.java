package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_Streams.Course08_StreamingStudentsProject;

import java.util.stream.Stream;

//Part-1
/*
        In the code ahead, I'll be setting up a couple of familiar classes, a Student, and a Course. I'll be mocking up
    a lot of students, to put some of the stream operations into practice. Again, you can choose to approach this as an
    additional challenge, or you could just follow along, or even skip over these set up lectures. This code is very basic,
    with only the use of the Stream's generate method used at the very end. I'll also be using Local Dates, and a class
    called Period, I haven't used before, to get the number of months elapsed between two dates.

                                               ___________________________
                                               |  Course                 |
                                               |_________________________|
                                               | courseCode: String      |
                                               | title: String           |
                                               | lectureCount: int       |
                                               |_________________________|

        The course type should have a course code, a course title, and a lecture count. You can make this an immutable
    class.

                                               _______________________________________
                                               |  CourseEngagement                   |
                                               |_____________________________________|
                                               | course: Course                      |
                                               | enrollmentDate: LocalDate           |
                                               | engagementType: String              |
                                               | lastLecture: int                    |
                                               | lastActivityDate: LocalDate         |
                                               |_____________________________________|
                                               | getCourseCode(): String             |
                                               | getEnrollmentYear(): int            |
                                               | getLastActivityYear(): int          |
                                               | getLastActivityMonth(): String      |
                                               | getMonthsSinceActive(): int         |
                                               | getPercentComplete(): double        |
                                               | watchLecture(lecture, date)         |
                                               |_____________________________________|

        Each student will have a course engagement instance, for every course they're enrolled in. It should have the fields
    for the course, the enrollment date, the engagement type, the last lecture, and the last activity date. It should have
    the usual getters, plus getters for calculated fields as shown here. The getMonthsSinceActive method should return
    the months elapsed, since the last course activity.

        The getPercentComplete method should use the last lecture, and the lecture count on course, to return a percentage
    complete. The watchLecture method would get called, when a student engaged in the course, and should update fields
    on the engagement record. It takes a lecture number, and an activity date.

                                         ________________________________________________________
                                         |  Student                                             |
                                         |______________________________________________________|
                                         | studentId: long                                      |
                                         | countryCode: String                                  |
                                         | yearEnrolled: int                                    |
                                         | ageEnrolled: int                                     |
                                         | gender: String                                       |
                                         | programmingExperience: boolean                       |
                                         | engagementMap: Map<String, CourseEngagement>         |
                                         |______________________________________________________|
                                         | addCourse(course)                                    |
                                         | addCourse(course, enrollDate)                        |
                                         | getAge():int                                         |
                                         | getMonthsSinceActive(courseCode): int                |
                                         | getMonthsSinceActive(): int                          |
                                         | getPercentComplete(courseCode): double               |
                                         | watchLecture(lectureNumber, month, year)             |
                                         | static getRandomStudent(Course... courses): Student  |
                                         |______________________________________________________|

    The Student class should have a student id, and demographic data, like country code, year enrolled, age at time of
    enrollment, gender, and a programming experience flag. Your student should also have a map of CourseEngagements, keyed
    by course code. Include getters for all of these fields

        In addition to the usual getters, add getter methods for calculated fields, like getYearsSinceEnrolled, and getAge.
    Include the getters, getMonthsSinceActive and getPercentComplete, that take a course code, and return data, from the
    matching CourseEngagement record. Add an overloaded version of getMonthsSinceActive, to get the least number of inactive
    months, from all courses.

        You should have overloaded addCourse methods, one that takes a specified activity date, and one that will instead
    default to the current date. Include the method, watchLecture, that takes a course code, a lecture number and an
    activity year and month, and calls the method of the same name, on the course engagement record.

        Finally, Create a static factory method on this class, getRandomStudent, that will return a new instance of Student,
    with random data, populating a student's fields. Make sure to pass courses to this method, and pass them to the student
    constructor. For each course, call watchLecture, with a random lecture number, and activity year and month, so that
    each Student will have different activity for each course.

        Ok, so let's build this. I've created the usual Main class. I'll start with the Course, and that's going to be a
    record.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        Course pymc= new Course("PYMC", "Python Masterclass");
        Course jmc= new Course("JMC", "Java Masterclass");
        Student tim = new Student("AU", 2019, 30, "M", true, jmc, pymc);
        System.out.println(tim);

//Part-13
/*
        I'll create a pymc variable, for the python master class with a course code of PYMC. That has the title Python
    masterclass. I'll do the same for the jmc course, this time it's JMC, and Java masterclass for the title. I'll create
    a student tim, a new student, and country code, AU for Australia. The year enrolled is 2019, and my age I'll say was
    30, and M for male. I'll say true, I had programming experience, and the last parameter is the variable arguments so
    I can pass in these two classes. And I'll print that student out. Running the code,

            Student{studentId=1, countryCode='AU', yearEnrolled=2019, ageEnrolled=30, gender='M', programmingExperience=true, engagementMap={JMC=JMC: Oca 2019 Enrollment [59], PYMC=PYMC: Oca 2019 Enrollment [59]}}

    you can see I've got a lot of information there. The student id is 1, and the student demographics are there, country
    code, year enrolled, age, gender. Tim is signed up for two classes, and right now the only engagement is enrollment,
    both happened in January of 2019, and there, is the months elapsed, in square brackets after that.
*/
//End-Part-13

        tim.watchLecture("JMC", 10, 5, 2019);
        tim.watchLecture("PYMC", 7, 7, 2020);
        System.out.println(tim);

//Part-14
/*
        I'll next call the watchLecture, first for the JMC class. I'll send it lecture number 10, in the 5th month of 2019.
    Then for the python master class, let's say tim watched lecture 7, in the 7th month of 2020. I'll print the tim instance
    out again. Running this code,

        Student{studentId=1, countryCode='AU', yearEnrolled=2019, ageEnrolled=30, gender='M', programmingExperience=true, engagementMap={JMC=JMC: Oca 2019 Enrollment [59], PYMC=PYMC: Oca 2019 Enrollment [59]}}
        Student{studentId=1, countryCode='AU', yearEnrolled=2019, ageEnrolled=30, gender='M', programmingExperience=true, engagementMap={JMC=JMC: May 2019 Lecture 10 [55], PYMC=PYMC: Tem 2020 Lecture 7 [41]}}

    my course engagement records are reflecting the additional data, about what the last lecture watched was, on each course.
    The student class has a lot of different data elements on it, and I planned it this way, so there would be a lot of
    diversity in the kinds of things we can do with this type, in our stream pipelines.
*/
//End-Part-14

        Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(10)
                .forEach(System.out::println);

//Part-17
/*
        And use Stream's generate method to create as many random students as I want. My lambda expression has no parameters,
    it's a Supplier, so it supports no parameters, but that doesn't mean I can't pass arguments to the method I want to
    call. In this case, I'm going to call the static method, getRandomStudent on the Student class, and pass it my jmc,
    and pymc variables. These have to be effectively final, which they are. I'll limit this to 10 random students at the
    moment. And I'll print each student to see how each student looks. Before I run it, I'll comment all the code
    above Stream.generate, except for setting up the two courses. Running this,

                Student{studentId=1, countryCode='CN', yearEnrolled=2017, ageEnrolled=20, gender='U', programmingExperience=true, engagementMap={JMC=JMC: Mar 2021 Lecture 33 [33], PYMC=PYMC: Ara 2018 Lecture 7 [60]}}
                Student{studentId=2, countryCode='CN', yearEnrolled=2017, ageEnrolled=52, gender='F', programmingExperience=true, engagementMap={JMC=JMC: Ağu 2018 Lecture 2 [64], PYMC=PYMC: Mar 2019 Lecture 20 [57]}}
                Student{studentId=3, countryCode='AU', yearEnrolled=2018, ageEnrolled=22, gender='U', programmingExperience=true, engagementMap={JMC=JMC: Tem 2019 Lecture 16 [53], PYMC=PYMC: Haz 2021 Lecture 32 [30]}}
                Student{studentId=4, countryCode='GB', yearEnrolled=2015, ageEnrolled=25, gender='F', programmingExperience=true, engagementMap={JMC=JMC: Eyl 2023 Lecture 27 [3], PYMC=PYMC: Nis 2015 Lecture 17 [104]}}
                Student{studentId=5, countryCode='CA', yearEnrolled=2017, ageEnrolled=72, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Tem 2023 Lecture 18 [5], PYMC=PYMC: Ara 2018 Lecture 15 [60]}}
                Student{studentId=6, countryCode='AU', yearEnrolled=2015, ageEnrolled=63, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Ağu 2019 Lecture 38 [52], PYMC=PYMC: Oca 2015 Lecture 11 [107]}}
                Student{studentId=7, countryCode='US', yearEnrolled=2016, ageEnrolled=63, gender='F', programmingExperience=false, engagementMap={JMC=JMC: Oca 2021 Lecture 19 [35], PYMC=PYMC: Şub 2016 Lecture 21 [94]}}
                Student{studentId=8, countryCode='AU', yearEnrolled=2017, ageEnrolled=75, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Eki 2022 Lecture 37 [14], PYMC=PYMC: Eyl 2023 Lecture 5 [3]}}
                Student{studentId=9, countryCode='AU', yearEnrolled=2018, ageEnrolled=23, gender='U', programmingExperience=true, engagementMap={JMC=JMC: May 2022 Lecture 18 [19], PYMC=PYMC: Nis 2021 Lecture 17 [32]}}
                Student{studentId=10, countryCode='CN', yearEnrolled=2020, ageEnrolled=34, gender='F', programmingExperience=false, engagementMap={JMC=JMC: Ağu 2022 Lecture 36 [16], PYMC=PYMC: Oca 2020 Lecture 31 [47]}}

    you can see I get 10 students back. Your own data will be different of course, but now we've got students with plenty
    of variety in the data, and that's what's going to let us have some fun with this. That's it, we're ready to put some
    stream operations to work. The next lecture is a challenge, which uses this code, to test what you learned about the
    first set of terminal operations I covered.
*/
//End-Part-17

    }
}
