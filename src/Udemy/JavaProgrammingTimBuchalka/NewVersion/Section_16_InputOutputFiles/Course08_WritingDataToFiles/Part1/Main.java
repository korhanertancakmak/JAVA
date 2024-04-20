package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course08_WritingDataToFiles.Part1;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course08_WritingDataToFiles.Part1.student.Course;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course08_WritingDataToFiles.Part1.student.Student;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**                                             Writing Data to a File
 *
 *      There are a lot of reasons why you might want to write data to a file. These include:
 *
 *  - Storing user data. Data can be maintained across different sessions, which can be useful for saving game state, or
 *    for shopping cart items in an e-commerce application. This allows users to resume where they left off.
 *
 *  - Logging application events to a log file. This helps with troubleshooting problems, as well as monitoring.
 *
 *  - Storing configuration data. You can store settings that might change, or be configured for different environment,
 *    as well as elements shared between different parts of an application.
 *
 *  - Exporting Data for Exchange of Information. Sharing data is very common, and file formats such as CSV, Json, or XML
 *    have evolved, to support interoperability and communication between systems.
 *
 *  - Supporting Offline Usage in a File Cache.Temporary storage in a file is one way to improve application performance.
 *    If the data is needed again, it can be loaded from the file, instead of fetching it from a remote source or re-computing
 *    it. Likewise, data can be stored in a file, if a user is working remotely. When they get reconnected to the server
 *    again, this data can be uploaded at that time.
 *
 *  - Generating file products. These products might include reports, invoices, or documents, as needed by users.
 *
 *  Is writing to a file so different than reading to it? Some of the concepts of writing to a file are naturally similar
 *  to those of reading from a file. You'll use similar named classes, but instead of InputStream, you'll work with an
 *  OutputStream, for example. There's a FileWriter class, rather than a FileReader class, and so on. Understanding
 *  buffered data becomes more important, as well as managing multiple writes, to a single file from different threads.
 *  There are different ways to open a file for writing.
 *
 */

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
                .limit(5)
                .toList();

/*
        // Writing the result, which is 10 records for the 5 randomly generated students, on console.
        System.out.println(header);
        students.forEach(s -> s.getEngagementRecords().forEach(System.out::println));
*/

        String pathName = "./src/CourseCodes.NewSections.Section_18_InputOutputFiles.Course08_WritingDataToFiles.Part1/students.csv";
        Path path = Path.of(pathName);

/*
        // To write the data to a file, I'll start by specifying a path with a filename, and I'll call it student.csv.
        // And then I'll set up a try-catch block, because I know I need it for an IOException.
        try {
            Files.writeString(path, header);                // Here, I'll call writeString, a static method on the Files class
            for (Student student : students) {              // I'll loop through my student list.
                Files.write(path, student.getEngagementRecords(), StandardOpenOption.APPEND);       // I'll call Files.write this time, which lets me pass an iterable collection. That's my student engagement records.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
/**                                          Default Open Options
       All available options are found on an enum in the java.nio.file package, called StandardOpenOption. The default
    options for Files.write methods are shown in this table.

           Option                  Description
           CREATE                  This creates a new file if it does not exist.
       TRUNCATE_EXISTING           If the file already exists, and it's opened for WRITE access, then its length is truncated to 0.
           WRITE                   The file opened for write access.

    When you don't specify options, the file will get created, if it doesn't exist. The file will truncated every time
    you write to it, which is why we only see the last 2 records of student 5, in the content. And naturally, it's open
    for write, but you can open any file resource, either for reading, writing or both. For the code above, I have 2 options,
    if I want all my student data to get printed. I can pass all the data into a single write method, or I can specify a
    different set of open options. More specifically, I probably don't want to truncate existing each time. I'll start
    with the second option. I'll change my code, passing another option, from the StandardOpenOption enum.

                             Files.write(path, student.getEngagementRecords());
                                                     to
                             Files.write(path, student.getEngagementRecords(), StandardOpenOption.APPEND);

    Here, I'll specify the APPEND constant, which means any text that's written to the file, will get appended to the
    end of file. Now examining the student.csv file, I can see that all the data was printed, included the header.
 **/


        // We can make this more efficient to create a single iterable object, and pass that. This would make one call to
        // the write method, so that we're not opening and closing a file resource, for every student. I'll comment the
        // code out, as I have it above except for the Path variable.
        try {
            List<String> data = new ArrayList<>();                  // I'll start again with a try block, set up a new array list
            data.add(header);                                       // I'll add the header string to that list.
            for (Student student : students) {                      // Again, I'll loop through my students.
                data.addAll(student.getEngagementRecords());        // This time, I'll call addAll, so all the engagements records will get added to the list.
            }
            Files.write(path, data);                                // Now, all my data is in 1 iterable collection. I'll pass that to the Files.write method without any open options defined.
        } catch (IOException e) {
            e.printStackTrace();
        }
/**
 *      Running the code above, I get the same results, all 5 of my students records, are in the students.csv file. When
 *  using these write methods on File, it's very important to understand, that doing incremental writes, as I did in the
 *  first code, is going to be pretty inefficient. There are other options for incremental writes. One of these is a method
 *  on the Files class, that returns what's called a BufferedWriter. I'll show you an example of this next,
 */

        // By setting up a try with resources block.
        String pathName2 = "./src/CourseCodes.NewSections.Section_18_InputOutputFiles.Course08_WritingDataToFiles.Part1/take2.csv";
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(pathName2))) {
            writer.write(header);
            writer.newLine();
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) { // Unlike Files.write, this class's write method doesn't let us pass an iterable, so I have to loop through the records individually.
                    writer.write(record);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
