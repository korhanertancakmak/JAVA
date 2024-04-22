package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course08_WritingDataToFiles.Part2;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course08_WritingDataToFiles.Part2.student.Course;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course08_WritingDataToFiles.Part2.student.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

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


/*

        String pathName2 = "./src/CourseCodes.NewSections.Section_18_InputOutputFiles.Course08_WritingDataToFiles.Part2/take2.csv";
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(pathName2))) {
            writer.write(header);
            writer.newLine();
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    writer.write(record);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

/*
        String pathName3 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course08_WritingDataToFiles/Part2/take3.csv";
        try (FileWriter writer = new FileWriter(pathName3)) {
            writer.write(header);
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    writer.write(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

/*

        String pathName3 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course08_WritingDataToFiles/Part2/take3.csv";

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


        String pathName4 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course08_WritingDataToFiles/Part2/take4.csv";

        try (PrintWriter writer = new PrintWriter(pathName4)) {
            writer.write(header);
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    writer.println(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

/*
        String pathName3 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course08_WritingDataToFiles/Part2/take3.csv";

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


        String pathName4 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course08_WritingDataToFiles/Part2/take4.csv";

        try (PrintWriter writer = new PrintWriter(pathName4)) {
            //writer.write(header);
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

/*
        String pathName3 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course08_WritingDataToFiles/Part2/take3.csv";

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


        String pathName4 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course08_WritingDataToFiles/Part2/take4.csv";

        try (PrintWriter writer = new PrintWriter(pathName4)) {
            writer.println(header);
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    //writer.println(record);

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
*/

/*
        String pathName2 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course08_WritingDataToFiles/Part2/take2.csv";

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
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
*/

        String pathName2 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course08_WritingDataToFiles/Part2/take2.csv";

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
    }
}
