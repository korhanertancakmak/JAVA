package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_InputOutputFiles.Course12_RandomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Scanner;

public class MainSeparate {

    private static final Map<Long, Long> indexedIds = new LinkedHashMap<>();    // key = record id, value = file pointer position
    private static int recordsInFile = 0;                                       // # of records in the file

    static {
        try (RandomAccessFile rb = new RandomAccessFile(
                "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course12_RandomAccessFile/student.idx",
                "r");) {
            loadIndex(rb, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//Part-16
/**
        You'll remember this is just a block of code at the class level, that starts with the keyword static. I'll create
 a new RandomAccessFile instance, which will read student.idx. And my mode can just be read here. I'll call the loadIndex
 method, passing it this random access file variable, and 0 as the starting position. And of course, I have to deal with
 an IO Exception. Now, I just need to go down to my main method.
 **/
//End-Part-16

    public static void main(String[] args) {

        BuildStudentData.build("student", true);            // commented via Part-11 and uncommented via Part-15

//Part-15
/**
        I'll uncomment that line that will build the file. I'll change the first argument to just student, so that my file
 names will be student.dat, and student.idx. I also don't want to override studentData.dat either. I'll pass true to that
 method now. I'll run this as is,

                 # of records = 1000
                 1000
                 Enter a Student Id or 0 to quit

 so that this code will create the separate data and index files. I'll also just quit out of the program right away, by
 entering 0 at the prompt. My code will still be using the old data file, to search for records. To see the new files in
 your project panel, you may need to reload from disk. But you should now see a student.dat and student.idx file, in your
 project folder. I'm going to again comment out the line to build this data. I have to change my code next, to use this
 new data file, with its separate index. For this, I'll change the way I load the index data. I'll create a static initializer
 to load the index.
 **/
//End-Part-15

        String cwdPath = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course12_RandomAccessFile/";
        try (RandomAccessFile ra = new RandomAccessFile(cwdPath + "student.dat", "r")) {
            //loadIndex(ra, 0);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a Student Id or 0 to quit");
            while (scanner.hasNext()) {
                long studentId = Long.parseLong(scanner.nextLine());
                if (studentId < 1) {
                    break;
                }
                ra.seek(indexedIds.get(studentId));
                String targetedRecord = ra.readUTF();
                System.out.println(targetedRecord);
                System.out.println("Enter another Student Id or 0 to quit");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//Part-17
/**
        I'll change the name of the file from studentData.dat, to just student.dat. I'll remove the loadIndex method here.
 It's much better to load the indices once, in a static initializer, rather than any time this method is executed. Ok,
 so let's see if this works. I'll run it,

                 1000
                 # of records = 1000
                 Enter a Student Id or 0 to quit

 I can see again that the loadIndex method found 1000 records, and that's a good sign. I'll again enter 777.

                 {"studentId":777, "demographics":{"countryCode":"IN", "enrolledMonth":7, "enrolledYear":2017, "ageAtEnrollment":60, "gender":"F", "previousProgrammingExperience":false}, "coursesEnrolled":[{"courseCode":"JMC", "title":"Java Masterclass"},{"courseCode":"PYC", "title":"Python Masterclass"}], "engagementMap":[{"courseCode":"JMC", "engagementType":"Lecture 8", "enrollmentMonth":7, "enrollmentYear":2017, "lastLecture":8, "lastActiveMonth":4, "lastActiveYear":2023},{"courseCode":"PYC", "engagementType":"Lecture 12", "enrollmentMonth":7, "enrollmentYear":2017, "lastLecture":12, "lastActiveMonth":8, "lastActiveYear":2022}]},
                 Enter another Student Id or 0 to quit

 The application found the record, in the new data file. This time, the index was built in its own file, and the record
 data is in a second file. This is a mini database now. I'll enter a second record, let's say 999.

                 {"studentId":999, "demographics":{"countryCode":"CN", "enrolledMonth":2, "enrolledYear":2016, "ageAtEnrollment":38, "gender":"U", "previousProgrammingExperience":false}, "coursesEnrolled":[{"courseCode":"JMC", "title":"Java Masterclass"},{"courseCode":"PYC", "title":"Python Masterclass"}], "engagementMap":[{"courseCode":"JMC", "engagementType":"Lecture 13", "enrollmentMonth":2, "enrollmentYear":2016, "lastLecture":13, "lastActiveMonth":12, "lastActiveYear":2020},{"courseCode":"PYC", "engagementType":"Lecture 2", "enrollmentMonth":2, "enrollmentYear":2016, "lastLecture":2, "lastActiveMonth":11, "lastActiveYear":2020}]},
                 Enter another Student Id or 0 to quit

 And there I get 999's record data. I'll press 0, to quit out of this application. Whether you include the index at the
 start, or the end of the records data file, or you maintain it as a separate file is really up to you.

    In this example, I've used the RandomAccessFile to create a binary data file that contains an indexed mechanism to
 locate data records. I've also used the RandomAccessFile to use this indexed binary data file to read data. These are
 only a couple of use cases, for this type of class. You've seen that I didn't have to cache all the records, just the
 index. Another reason to use a random access file, would be for targeted data modifications, of a large file. For
 example,I could have found the record for student 777 and modified that record for example. Random access files are
 also commonly used for binary data storage as you've seen.
 **/
//End-Part-17
    }



    private static void loadIndex(RandomAccessFile ra, int indexPosition) {
        try {
            ra.seek(indexPosition);
            recordsInFile = ra.readInt();
            System.out.println(recordsInFile);
            for (int i = 0; i < recordsInFile; i++) {
                indexedIds.put(ra.readLong(), ra.readLong());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
