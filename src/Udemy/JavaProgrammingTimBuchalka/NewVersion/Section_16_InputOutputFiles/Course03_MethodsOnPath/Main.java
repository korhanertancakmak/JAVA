package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course03_MethodsOnPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

public class Main {

    public static void main(String[] args) {

        Path path = Path.of("folders/testing.txt");         /** Initially, this folder and this file doesn't exist in the project **/
        printPathInfo(path);
        logStatement(path);
        extraInfo(path);
    }

    private static void printPathInfo(Path path) {
                                                                                /** Example Output **/
        System.out.println("Path: " + path);                                    /** Path: folders/testing.txt **/
        System.out.println("fileName = " + path.getFileName());                 /** fileName: testing.txt **/
        System.out.println("parent = " + path.getParent());                     /** parent: folders **/
        Path absolutePath = path.toAbsolutePath();
        System.out.println("Absolute Path: = " + absolutePath);
        /** Absolute Path: D:\JAVA_STUDY\JAVA_CLASSES\UDEMY_CLASSES\JAVAProgrammingMasterClassTimBuchalka\PROGRAMS\Courses
         * \src\CourseCodes\NewSections\Section_18_InputOutputFiles\Course03_MethodsOnPath\folders\testing.txt **/
        System.out.println("Absolute Path Root: = " + absolutePath.getRoot());  /** Absolute Path Root: D:\ **/
        System.out.println("Root = " + path.getRoot());                         /** Root: null **/  // This is one indication that this is a relative path.
        System.out.println("isAbsolute = " + path.isAbsolute());                /** isAbsolute: false **/

        /** 2 ways of printing hierarchy directories **/
        System.out.println(absolutePath.getRoot());

        /** By using Iterator **/
//        int i = 1;
//        var it = path.toAbsolutePath().iterator();
//        while (it.hasNext()) {
//            System.out.println(".".repeat(i++) + " " + it.next());
//        }

        /** By using File Tree **/
        int pathParts = absolutePath.getNameCount();
        for (int i = 0; i < pathParts; i++) {
            System.out.println(".".repeat(i + 1) + " " + absolutePath.getName(i));
        }
        System.out.println("-----------------------");
    }


    /** To create parent folder and the file in path 'folders/testing.txt' **/
    private static void logStatement(Path path) {

        try {
            Path parent = path.getParent();                                     /** Assign the parent folder to a Path type variable **/
            if (!Files.exists(parent)) {                                        /** If the folder in parent variable does not exist **/
                //Files.createDirectory(parent);                                  /** Create it by using static method on Files **/
                Files.createDirectories(parent);                                  /** If the file is inside multiple of parent folders **/
            }

            /** Creating and Writing in one statement: path will be the first argument, since this is a log file we add
             *  the date and time and some string literals as a second argument,
             *  StandardOpenOption is an enum in the java.nio.file package. If this file doesn't exist, it will get created.
             *  APPEND is the last argument option, which means each statement, will get appended to the end of the file **/
            Files.writeString(path, Instant.now() + ": hello file world\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        } catch (IOException e) {                                               /** And this method throws an IOException, so catch has to be used **/
            e.printStackTrace();                                                /** The plan is to log statements to a file, we don't want exception or quit. This
                                                                                    kind of ignores the error, unless someone's watching the default output. **/
        }
    }

    private static void extraInfo(Path path) {

        try {
            var atts = Files.readAttributes(path, "*");     /** The string defines a list of attribute names you want information about. **/
            atts.entrySet().forEach(System.out::println);                             /** This method returns a map, so for each method can be used on the entrySet to print each key-value pairs **/
            System.out.println(Files.probeContentType(path));                         /** This method indicates the content type of text plain **/
        } catch (IOException e) {                                                     /** This method throws a checked IOException **/
            System.out.println("Problem getting attributes");
        }
    }
    /**         Output Example
     *  lastAccessTime = 2023-08-11T00:37:13.7142144Z
     *  lastModifiedTime = 2023-08-11T00:37:13.7142144Z
     *  size = 95
     *  creationTime=2023-08-11T00:34:54.9628638Z
     *  isSymbolicLink=false
     *  isRegularFile=true
     *  fileKey=null
     *  isOther=false
     *  isDirectory=false
     * **/
}
