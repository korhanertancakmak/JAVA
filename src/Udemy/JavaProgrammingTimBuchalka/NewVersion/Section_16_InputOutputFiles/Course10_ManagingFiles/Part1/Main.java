package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course10_ManagingFiles.Part1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

/*
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part1/students.json";
        String pathName2 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part1/student-activity.json";

        File oldFile = new File(pathName);
        File newFile = new File(pathName2);

        if (oldFile.exists()) {
            oldFile.renameTo(newFile);
            System.out.println("File renamed successfully!");
        } else {
            System.out.println("File does not exist!");
        }
*/

/*
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part1/students.json";
        String pathName2 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part1/student-activity.json";

        File oldFile = new File(pathName);
        File newFile = new File(pathName2);

        Path oldPath = oldFile.toPath();
        Path newPath = newFile.toPath();

        try {
            Files.move(newPath, oldPath);
            System.out.println("Path renamed successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

/*
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part1/students.json";
        String pathName2 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part1/student-activity.json";

        Path oldPath = Path.of(pathName);
        Path newPath = Path.of(pathName2);

        try {
            Files.createDirectories(newPath.subpath(0, newPath.getNameCount() - 1));
            Files.move(oldPath, newPath);
            System.out.println(oldPath + " moved (renamed to) --> " + newPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

/*
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part1/files";
        String pathName2 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part1/resources";

        Path fileDir = Path.of(pathName);
        Path resourceDir = Path.of(pathName2);

        try {
            Files.move(fileDir, resourceDir);
            System.out.println("Directory renamed");
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part1/files";
        String pathName2 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part1/resources";

        Path fileDir = Path.of(pathName);
        Path resourceDir = Path.of(pathName2);
        try {
            Files.copy(fileDir, resourceDir);
            System.out.println("Directory copied to " + resourceDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
