package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course01_ExceptionHandling.TryWithResources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        String filename = "testing.csv";

        testFile2(null);

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("I can't run unless this file exists");
            System.out.println("Quitting Application, go figure it out");
            return;
        }
        System.out.println("I'm good to go.");
    }

    private static void testFile(String filename) {

        Path path = Paths.get(filename);
        FileReader reader = null;                                           // traditional try clause example
        try {
//            List<String> lines = Files.readAllLines(path);                // "readAllLines" is one way to read data from a text file.
            reader = new FileReader(filename);                              // An alternative way to read data is "FileReader", which works only with 'try' with resources statement
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();                                         // close method throws a checked exception. That's why we have to choose to catch or specify
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Maybe I'd log something either way...");
        }
        System.out.println("File exists and able to use as a resource");
    }

    private static void testFile2(String filename) {
/**
 *      You can't list exceptions in the multi exception clause, that are derivatives of the same class. For example below;
 *
 *                                  catch (NullPointerException | IllegalArgumentException badData)
 *                                                              to
 *                                  catch (NullPointerException | IllegalArgumentException | RuntimeException badData )
 *
 *  IntelliJ tells that types in a multi-catch must be disjoint. 'NullPointerException' and 'IllegalArgumentException' are subclasses of 'RuntimeException'
 **/
        try (FileReader reader = new FileReader(filename)) {                // try-with-resources example
        } catch (FileNotFoundException e) {                                 // This may occur when opening the resource. FileNotFoundException is a subclass of IOException,
            System.out.println("File '" + filename + "' does not exist");   // this means FileNotFoundException clause is unreachable when specified after it's super class.
            throw new RuntimeException(e);
        } catch (NullPointerException | IllegalArgumentException badData) { // If the FileReader is "null" or "bad data"
            System.out.println("User has added bad data " + badData.getMessage());
        } catch (IOException e) {                                           // This may occur when working or closing the resource
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Something unrelated and unexpected happened");
        } finally {                                                         // try-with-resources statement can be used without a finally block, because all resources
            System.out.println("Maybe I'd log something either way...");    // are automatically closed when this type of try block completes, or if it gets an exception.
        }
        System.out.println("File exists and able to use as a resource");
    }
}
