package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course04_FilesClass.DirectoryListings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
                                                                                /** Some methods on Files Class: list, walk, find **/
        Path path = Path.of("");                                            /** Path with an empty string means the current working directory **/
        System.out.println("cwd = " + path.toAbsolutePath());
                                                                                /** list method of Files class **/
        try (Stream<Path> paths = Files.list(path)) {                           /** this method returns a stream, of path instances **/
            paths                                                               /** each representing either a file or a sub folder
                                                                                    in the specified directory path **/
                    .map(Main::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("---------------------------------------");          /** walk method of Files class **/
        try (Stream<Path> paths = Files.walk(path, 2)) {               /** Depth = 1 means only the cwd list, Depth = 2 means all the files in folders of cwd **/
            paths.filter(Files::isRegularFile)
                    .map(Main::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("---------------------------------------");          /** find method of Files class **/
        try (Stream<Path> paths = Files.find(path, Integer.MAX_VALUE,
                (p, attr) -> attr.isRegularFile() && attr.size() > 300
        )) {
            paths
                    .map(Main::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        path = path.resolve(".idea");
        System.out.println("==============Directory Stream==============");
        try (var dirs = Files.newDirectoryStream(path, "*.xml")) {
            dirs.forEach(d -> System.out.println(Main.listDir(d)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("==============Directory Stream==============");
        try (var dirs = Files.newDirectoryStream(path,
                p -> p.getFileName().toString().endsWith(".xml")
                        && Files.isRegularFile(p) && Files.size(p) > 1000
        )) {
            dirs.forEach(d -> System.out.println(Main.listDir(d)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String listDir(Path path) {

        try {
            boolean isDir = Files.isDirectory(path);
            FileTime dateField = Files.getLastModifiedTime(path);
            LocalDateTime modDT = LocalDateTime.ofInstant(
                    dateField.toInstant(), ZoneId.systemDefault());
            return "%tD %tT %-5s %12s %s"
                    .formatted(modDT, modDT, (isDir ? "<DIR>" : ""),
                            (isDir ? "" : Files.size(path)), path);
        } catch (IOException e) {
            System.out.println("Whoops! Something went wrong with " + path);
            return path.toString();
        }
    }
}
