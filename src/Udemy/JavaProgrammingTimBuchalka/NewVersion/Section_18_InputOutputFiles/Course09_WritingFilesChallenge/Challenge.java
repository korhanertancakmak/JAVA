package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_14_InputOutputFiles.Course09_WritingFilesChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_14_InputOutputFiles.Course09_WritingFilesChallenge.student.Course;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_14_InputOutputFiles.Course09_WritingFilesChallenge.student.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
                                                        JSON

    It stands for "JavaScript Object Notation", and it's a pretty popular lightweight data interchange format.

                                                    The Challenge

    Start with the Student Engagement code from the previous videos, and first generate 1000 students. To output each
 student as a JSON record:

        - You can use the write, or writeString methods on Files.
        - Or you can try FileWriter, PrintWriter or BufferedWriter, or some combination of these.

 The text printed should be a list of students.

        [ {student 1 data here} , { student2 data here }, ... ]

 The entire set of student records should be contained in square brackets. Each student record should be enclosed in
 curly braces, containing student data in key-value pairs, and separated by commas.

                                     {
                                     "studentId": 2,
                                         "countryCode": "GB",
                                         "enrolledMonth": 1,
                                         "enrolledYear": 2017,
                                         "ageAtEnrollment": 43,
                                         "gender": "U",
                                         "previousProgrammingExperience": false
                                     }

 At a minimum, print the student id, and some demographics data. The example below, demonstrates a flattened structure.
 You can see all fields are key-value pairs, and all are children of the student record. Keys are enclosed in quotes, as
 are values if they're text based, and the colon is used to separate the key and value.

                                     {
                                        "studentId": 2,
                                             "demographics": {                  // demographics has a value in {}
                                             "countryCode": "GB",
                                             "enrolledMonth": 1,
                                             "enrolledYear": 2017,
                                             "ageAtEnrollment": 43,
                                             "gender": "U",
                                             "previousProgrammingExperience": false
                                         },
                                         "engagement": [{                       // engagement key's values is an array as [{}, {}]
                                             "courseCode": "JMC",
                                             "engagementType": "Lecture 1",
                                             "enrollmentMonth": 1,
                                             "enrollmentYear": 2017
                                         }, {
                                             "courseCode": "PYC",
                                             "engagementType": "Lecture 13",
                                             "enrollmentMonth": 1,
                                             "enrollmentYear": 2017
                                         }]
                                     }

 Above, I'm showing a more hierarchical structure, for a single student. The demographics key, has a value that's
 an object and not a simple string, and that's enclosed in curly braces. The engagement key's values is also an array,
 containing separate engagement records.

                                            Hints And Suggestions

    You might want to explore the use of IntelliJ's "template" functionality, to create your own JSON string template.
 Don't forget about the StringJoiner class, that lets you define a delimiter, as well as a prefix and suffix. Start out
 by testing 2 or 3 students in your data set.

 */


//Part-1
/**
        The first thing I want to do is, show you how to create your own to String template. I'll start in the StudentDemographics
    record with my pointer just below the toString method.
**/
//End-Part-1


public class Challenge {

    public static void main(String[] args) {


        Course jmc = new Course("JMC", "Java Masterclass");
        Course pymc = new Course("PYC", "Python Masterclass");

/*
        List<String> students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(2)
                .map(Student::toJSON)
                .toList();
        students.forEach(System.out::println);
*/

//Part-4
/**
        I'll go to the Main class, and copy the code that creates my list of students. I don't want the header, so starting
 at the declaration of the jmc course, through the toList operation. I'll paste that in the main method of my Challenge
 class. I'll change the limit to 2 for now, while I'm testing out my code. Next, I'm going to change my List of Students
 to a List of Strings. That's because, I'll insert a map operation, that will take a student, mapping it to a string,
 using the toJSON method. I can do that with a method reference. And I'll print each element in this list to the console
 to start.

 {"studentId":1, "demographics":{"countryCode":"AU", "enrolledMonth":12, "enrolledYear":2015, "ageAtEnrollment":24, "gender":"U", "previousProgrammingExperience":false}, "coursesEnrolled":[{"courseCode":"JMC", "title":"Java Masterclass"},{"courseCode":"PYC", "title":"Python Masterclass"}], "engagementMap":[{"courseCode":"JMC", "engagementType":"Lecture 4", "enrollmentMonth":12, "enrollmentYear":2015, "lastLecture":4, "lastActiveMonth":5, "lastActiveYear":2018},{"courseCode":"PYC", "engagementType":"Lecture 11", "enrollmentMonth":12, "enrollmentYear":2015, "lastLecture":11, "lastActiveMonth":3, "lastActiveYear":2015}]}
 {"studentId":2, "demographics":{"countryCode":"AU", "enrolledMonth":7, "enrolledYear":2023, "ageAtEnrollment":21, "gender":"U", "previousProgrammingExperience":false}, "coursesEnrolled":[{"courseCode":"JMC", "title":"Java Masterclass"},{"courseCode":"PYC", "title":"Python Masterclass"}], "engagementMap":[{"courseCode":"JMC", "engagementType":"Lecture 5", "enrollmentMonth":7, "enrollmentYear":2023, "lastLecture":5, "lastActiveMonth":7, "lastActiveYear":2023},{"courseCode":"PYC", "engagementType":"Lecture 8", "enrollmentMonth":7, "enrollmentYear":2023, "lastLecture":8, "lastActiveMonth":2, "lastActiveYear":2023}]}

 And maybe that's right, but it's kind of hard to tell. I'll copy the last student record in my console, and I'll pull
 up a JSON linter. A "linter" is a software development tool, that will analyze source code for potential errors, and
 styling issues. "jsonlint.com", paste the JSON linter, and pressing Validate JSON button, this formats the JSON, and
 validates it, the results are shown below the validate button. Now I know I've created Valid JSON for this one student
 record.
 **/
//End-Part-4

        String delimiter = "," + System.lineSeparator();
        String students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(2)
                .map(Student::toJSON)
                .collect(Collectors.joining(delimiter, "[", "]"));
        System.out.println(students);

//Part-5
/**
    I'll be doing this with Files.writeString, passing it one very large string. How do I do this? Getting back to the
 Challenge code, I'll first change my stream pipeline. I'll make the result a String, and not a list. Next, I'll remove
 both the toList statement, and that forEach statement, that prints each student json record. Instead of toList, I want
 to use the collect terminal operation. I can use Collectors.joining which uses a StringJoiner underneath the covers.
 The first argument is the delimiter I'll make that a comma. The next argument is a prefix which for this array of students,
 needs to be an opening square bracket, and my suffix is a closing square bracket. I'll print this to the console as well.
 There's actually one more change I want to make, to help with the readability of the output a little bit. I want to make
 my delimiter between students, also include a line separator. I'll put this variable as the first argument instead of
 just a comma. Ok, now it's time to write that out to a file. I'll start with a try block.
 **/
//End-Part-5

        String pathName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course09_WritingFilesChallenge/students.json";
        try {
            Files.writeString(Path.of(pathName), students);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//Part-6
/**
    I'll call Files.writeString, passing it a new Path, for a file names student-activity.json, and I'll pass my students string.
 I'll catch the IOException. And just wrap that in a runtime exception and throw it. I'll run this again with just 2 records,
 to test it.

 [{"studentId":1, "demographics":{"countryCode":"IN", "enrolledMonth":12, "enrolledYear":2015, "ageAtEnrollment":44, "gender":"M", "previousProgrammingExperience":false}, "coursesEnrolled":[{"courseCode":"JMC", "title":"Java Masterclass"},{"courseCode":"PYC", "title":"Python Masterclass"}], "engagementMap":[{"courseCode":"JMC", "engagementType":"Lecture 4", "enrollmentMonth":12, "enrollmentYear":2015, "lastLecture":4, "lastActiveMonth":8, "lastActiveYear":2018},{"courseCode":"PYC", "engagementType":"Lecture 10", "enrollmentMonth":12, "enrollmentYear":2015, "lastLecture":10, "lastActiveMonth":3, "lastActiveYear":2021}]},
 {"studentId":2, "demographics":{"countryCode":"IN", "enrolledMonth":9, "enrolledYear":2018, "ageAtEnrollment":21, "gender":"U", "previousProgrammingExperience":true}, "coursesEnrolled":[{"courseCode":"JMC", "title":"Java Masterclass"},{"courseCode":"PYC", "title":"Python Masterclass"}], "engagementMap":[{"courseCode":"JMC", "engagementType":"Lecture 10", "enrollmentMonth":9, "enrollmentYear":2018, "lastLecture":10, "lastActiveMonth":10, "lastActiveYear":2023},{"courseCode":"PYC", "engagementType":"Lecture 5", "enrollmentMonth":9, "enrollmentYear":2018, "lastLecture":5, "lastActiveMonth":9, "lastActiveYear":2019}]}]

 I'll see my students printed in the console. Notice that I have an opening square bracket, and ending square bracket,
 and at the end of the first student, there's a comma. This is what the Collectors.joining did. But I also see students.json
 listed in my project panel, and I can open that up. There you can see my students.json code, as it was in the console.

    I'll quick go to the Course record,
 **/
//End-Part-6
    }
}
