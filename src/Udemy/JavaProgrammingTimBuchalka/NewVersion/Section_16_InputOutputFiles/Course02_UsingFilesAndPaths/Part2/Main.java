package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course02_UsingFilesAndPaths.Part2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        useFile("testfile.txt");
        //usePath("pathfile.txt");
    }

    /** Creating and deleting a file by testing its existence with an instance of File. **/
    private static void useFile(String fileName) {

        File file = new File(fileName);
        boolean fileExists = file.exists();

        System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "does not exist.");

        if (fileExists) {
            System.out.println("Deleting File: " + fileName);
            fileExists = !file.delete();                        // This returns a boolean
        }

        if (!fileExists) {
            try {
                file.createNewFile();                           // This returns a boolean, If we don't use try-catch, it gives Unhandled IOException
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
            System.out.println("Created File: " + fileName);
            if (file.canWrite()) {                              // test the file can be written to
                System.out.println("Would write to file here");
            }
        }
    }

    /** Creating and deleting a file by testing its existence with an instance of Path type with the help of methods on Files class. **/
    private static void usePath(String fileName) {

        Path path = Path.of(fileName);
        boolean fileExists = Files.exists(path);

        System.out.printf("File '%s' %s%n", fileName,
                fileExists ? "exists." : "does not exist.");

        if (fileExists) {
            System.out.println("Deleting File: " + fileName);
            try {
                Files.delete(path);
                fileExists = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!fileExists) {
            try {
                Files.createFile(path);
                System.out.println("Created File: " + fileName);
                if (Files.isWritable(path)) {
                    Files.writeString(path, """
                            Here is some data,
                            For my file,
                            just to prove,
                            Using the Files class and path are better!
                            """);
                }
                System.out.println("And I can read too");
                System.out.println("-------------------------");
                Files.readAllLines(path).forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
        }
    }
}
