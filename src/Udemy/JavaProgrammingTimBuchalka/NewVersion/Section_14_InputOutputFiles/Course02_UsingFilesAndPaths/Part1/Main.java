package CourseCodes.NewSections.Section_18_InputOutputFiles.Course02_UsingFilesAndPaths.Part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
/**
 *                                      File Handle vs. File Resource
 *
 *      A file handle is a reference to a file that is used by the operating system to keep track of the file. It is an
 * abstract representation of the file, and it does not contain any of the actual data from the file. A file resource, on
 * the other hand, is the actual data from the file. It is stored on the disk, and it can be accessed by the operating
 * system, and by applications. In the case of the File Class, we're only dealing with a file handle, so we don't open
 * or close this.
 *
 *      Directory(Folder) : is a file system container for other directories or files.
 *      Path              : is either a directory or a filename, and may include information about the parent directories.
 *      Root directory    : is the top-level directory in a file system.
 *      Current working
 *         directory      : is the directory that the current process is working in or running from.
 *      Absolute path     : includes the root (by either starting with / or optionally, C:\ in windows, where c is the
 *                          root identifier.
 *      Relative path     : defines a path relative to the current working directory, and therefore would not start with
 *                          /, but may optionally start with a dot . then a file separator character.
 **/

        System.out.println("Current Working Directory (cwd) = " + new File("").getAbsolutePath());

        String filename = "files/testing.csv";

        File file = new File(new File("").getAbsolutePath(), filename);
        System.out.println(file.getAbsolutePath());
        if (!file.exists()) {
            System.out.println("I can't run unless this file exists");
            return;
        }
        System.out.println("I'm good to go.");

        for (File f : File.listRoots()) {
            System.out.println(f);
        }

        Path path = Paths.get("files/testing.csv");
        System.out.println(file.getAbsolutePath());
        if (!Files.exists(path)) {
            System.out.println("2. I can't run unless this file exists");
            return;
        }
        System.out.println("2. I'm good to go.");

    }

    private static void testFile(String filename) {

        Path path = Paths.get(filename);
        FileReader reader = null;
        try {
//            List<String> lines = Files.readAllLines(path);
            reader = new FileReader(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Maybe I'd log something either way...");
        }
        System.out.println("File exists and able to use as a resource");
    }

    private static void testFile2(String filename) {

        try (FileReader reader = new FileReader(filename)) {
        } catch (FileNotFoundException e) {
            System.out.println("File '" + filename + "' does not exist");
            throw new RuntimeException(e);
        } catch (NullPointerException | IllegalArgumentException badData) {
            System.out.println("User has added bad data " + badData.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Something unrelated and unexpected happened");
        } finally {
            System.out.println("Maybe I'd log something either way...");
        }
        System.out.println("File exists and able to use as a resource");
    }
}
