package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_InputOutputFiles.Course08_WritingDataToFiles.Part2;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_InputOutputFiles.Course08_WritingDataToFiles.Part2.student.Course;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_InputOutputFiles.Course08_WritingDataToFiles.Part2.student.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**                                                    Writer Classes
 *      _____________________________________________________________________________________________________________________
 *     |               |    Buffering      |      Data Format      |         Features           |        Use Case           |
 *     |---------------|-------------------|-----------------------|----------------------------|---------------------------|
 *     |BufferedWriter |       Yes         |      Characters       |    Supports line breaks    |  Writing large amounts    |
 *     |               |                   |                       |    with newline method     |   of text to a file       |
 *     |---------------|-------------------|-----------------------|----------------------------|---------------------------|
 *     |               |    Yes but much   |                       | No separate method for line|  Writing small amounts of |
 *     |FileWriter     |    smaller than   |      Characters       | separators, would need to  |   text to a file          |
 *     |               |    BufferedWriter |                       | write manually             |                           |
 *     |---------------|-------------------|-----------------------|----------------------------|---------------------------|
 *     |               | No, but often used|      Characters,      | Familiar methods, that have|  Writing text to a file,  |
 *     |PrintWriter    |      with a       |      numbers and      | same behavior as System.out|  formatting output, and   |
 *     |               |  BufferedWriter   |       objects         | methods                    |  outputting objects       |
 *     |---------------|-------------------|-----------------------|----------------------------|---------------------------|
 *
 *                                  What does it mean to flush an output buffer?
 *
 *      For writing files, there's temporary storage, that gets filled up as writes are executed, on a Writer class.
 *  Physical writes to disk happen when the buffer is flushed. This is the process of taking the text stored in the buffer,
 *  and writing it to the output file, and clearing the buffer's cache. The frequency of flushing can be affected by a
 *  number of factors, including the size of the buffer, the speed of the disk, and the amount of data that's being written
 *  to the file. The buffer is always flushed when a file is closed. You can manually flush a buffer, by calling the flush
 *  method. You might want to do this, meaning flush more frequently, when working with time sensitive data. Any other
 *  thread or process that's reading the file, won't be able to see the buffered text, until the flush occurs.
 * **/

public class Main {

    public static void main(String[] args) {

        String header = """
                Student Id,Country Code,Enrolled Year,Age,Gender,\
                Experienced,Course Code,Engagement Month,Engagement Year,\
                Engagement Type""";

        Course jmc = new Course("JMC", "Java Masterclass");
        Course pymc = new Course("PYC", "Python Masterclass");
        List<Student> students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(25)
                .toList();

        String pathName2 = "./src/CourseCodes.NewSections.Section_18_InputOutputFiles.Course08_WritingDataToFiles.Part2/take2.csv";

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(pathName2))) {
            writer.write(header);
            writer.newLine();
            int count = 0;
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    writer.write(record);
                    writer.newLine();
                    count++;
                    if (count % 5 == 0) {
                        Thread.sleep(2000);
                        System.out.print(".");
                    }
                    if (count % 10 == 0) {
                        writer.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String pathName3 = "./src/CourseCodes.NewSections.Section_18_InputOutputFiles.Course08_WritingDataToFiles.Part2/take3.csv";

        try (FileWriter writer = new FileWriter(pathName3)) {
            writer.write(header);
            writer.write(System.lineSeparator());
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    writer.write(record);
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



/*

        String pathName4 = "./src/CourseCodes.NewSections.Section_18_InputOutputFiles.Course08_WritingDataToFiles.Part2/take4.csv";
        try (PrintWriter writer = new PrintWriter(pathName4)) {
            writer.println(header);
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    writer.println(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        String pathName5 = "./src/CourseCodes.NewSections.Section_18_InputOutputFiles.Course08_WritingDataToFiles.Part2/take4.txt";
        try (PrintWriter writer = new PrintWriter(pathName5)) {
            writer.println(header);
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    String[] recordData = record.split(",");
                    writer.printf("%-12d%-5s%2d%4d%3d%-1s".formatted(
                            student.getStudentId(),                                     // Student Id
                            student.getCountry(),                                       // Country Code
                            student.getEnrollmentYear(),                                // Enrolled Year
                            student.getEnrollmentMonth(),                               // Enrolled Month
                            student.getEnrollmentAge(),                                 // Age
                            student.getGender()));                                      // Gender
                    writer.printf("%-1s", (student.hasExperience() ? 'Y' : 'N'));       // Experienced?
                    writer.format("%-3s%10.2f%-10s%-4s%-30s",
                            recordData[7],                                              // Course Code
                            student.getPercentComplete(recordData[7]),
                            recordData[8],                                              // Engagement Month
                            recordData[9],                                              // Engagement Year
                            recordData[10]);                                            // Engagement Type
                    writer.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
