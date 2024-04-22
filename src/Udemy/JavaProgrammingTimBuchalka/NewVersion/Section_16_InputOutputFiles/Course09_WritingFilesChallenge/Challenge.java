package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course09_WritingFilesChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course09_WritingFilesChallenge.student.Course;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course09_WritingFilesChallenge.student.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


        String delimiter = "," + System.lineSeparator();
        String students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(1000)
                .map(Student::toJSON)
                .collect(Collectors.joining(delimiter, "[", "]"));
        System.out.println(students);

/*
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course09_WritingFilesChallenge/students.json";
        try {
            Files.writeString(Path.of(pathName), students);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

    }
}
