package CourseCodes.NewSections.Section_18_InputOutputFiles.Course11_FileManipulationChallenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Part-1
/**                                 Directory and File Manipulation Challenge.

        In this challenge, I want you to

    - Create a directory at the root of your IntelliJ Project named "public" in the current working directory.
    - Inside "public," create a subdirectory named "assets".
    - Inside "assets," create another subdirectory named "icons".
    - Create a process that will generate an index.txt file for each directory and subdirectory.
        * In each of the directories ("public," "assets," and "icons"), create an index.txt file.
        * In each index.txt file, list all the contents in the current directory, with full paths and the date each item
          was created. This should be recursive. The index.txt file of the parent should contain all items that are listed
          in the index.txt of the child.

    - Next, make a copy of the index.txt in each sub folder.
    - After you've created these copies, Run your code to re-generate each index.txt file, and verify your backup copies
      are listed there.

        Remember, the Files class offers you many different ways to create directories, files, and for iterating through
 a file tree. Stick to using Paths and NIO2 functionality. Later on, I'll be covering how you can execute the method you
 create here, to create the index.txt files, from an asynchronous thread, using a special mechanism, called a file watcher.
 For now, just assume this will be manually run.
**/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        String publicDirPath = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course11_FileManipulationChallenge/";

        Path deepestFolder = Path.of(publicDirPath + "public", "assets", "icons");

        try {
            Files.createDirectories(deepestFolder);
            generateIndexFile(deepestFolder.getName(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//Part-2
/**
 I've created a new project called FileManipulationChallenge, and I've got the main class, main method ready to go.
 The first thing I want to do is create my directory tree. First, I'll set up a path, using a factory method, that takes
 a variable list of paths. I can pass each directory, in a list, so first public, then assets, then icons. To create these
 directories, I can just call Files.createDirectories, and pass it this path. This of course fails, because I need to catch
 the IO Exception, so I'll surround that code with a try catch. I'll run this. Opening the project panel I'll see the public
 folder, and can drill down into it, to the icons sub folder.

 Next, I want to write the code to generate the index.txt at each sub folder level. I'll create a private static
 void method, named generateIndexFile, that takes a path argument.
 **/
//End-Part-2

//Part-4
/**
        I'll start by passing it just public, the first sub folder, and I can do that by calling the getName method, on
 my path variable, passing it the index 0. Running this code, I can see it created one file, in the public folder. If I
 open that, I see my header, and the listing of my nested sub folders, with the absolute path there. Since I used the find
 method, my text includes the absolute path of the current directory as the first item. I sort of like that myself, but
 maybe that's not what you did and that's ok, the challenge didn't call for this. Anyway, I'm going to leave it. I don't
 see index.txt, but if I run this code again, I'll see it listed. The challenge was to create an index.txt for each sub
 folder, and I've only created one for the top level. I need to change my generateIndex method, to call itself recursively,
 for any children that are directories.
 **/
//End-Part-4

        for (int i = 1; i <= deepestFolder.getNameCount(); i++) {
            Path indexedPath = deepestFolder.subpath(0, i).resolve("index.txt");
            Path backupPath = deepestFolder.subpath(0, i).resolve("indexCopy.txt");
            try {
                Files.copy(indexedPath, backupPath,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            generateIndexFile(deepestFolder.getName(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//Part-6
/**
        I'll loop through the name parts, starting with 1, to get each sub path of the deepestFolder path. I'll get the
 path for the existing file, index.txt, resolving to the sub path. I'll create a backup path in the same way, but with
 the name indexCopy.txt. Finally, I'll execute files.copy here, and I want to copy the indexedPath to the backupPath. I
 need to add a try catch around that last copy method. Ok, that should do it. I'll run that. I can see my copies in the
 project panel, within every sub folder. The final step is to generate the index.txt again, after this, so I'll call that.
 I'll start with the try block. I'll again call generateIndexFile, and pass it the name part at index 0. And I'll add the
 usual bit for the IO Exception. I'll run that. This time though, I've got an exception, File already exists exception,
 through from the Files.copy method. Do you know why this is? Remember, copy isn't going to overwrite an existing file,
 unless we specifically tell it to, by using a copy option. I'll scroll up to the Files.copy command there, and add the
 Standard Copy Option's enum value, called REPLACE EXISTING. I'll run this again. This time, my code ran without any issues,
 so let's examine the contents of index.txt in the public folder. You can see this file has grown, and the content now
 includes the listings for the copies. Ok, so that's it, the challenge is complete. There's no right way to do this, but
 in this challenge, you got to create directories, create files, and copy them, as well as walk or list a directory's
 contents, to generate the index.txt files. In the next lecture, I'll be talking about a totally different kind of class
 for reading and writing to a file, and that's the RandomAccessFile.
 **/
//End-Part-6
    }

    private static void generateIndexFile(Path startingPath) throws IOException {
        Path indexFile = startingPath.resolve("index.txt");
        try (Stream<Path> contents = Files.find(startingPath, Integer.MAX_VALUE,
                (path, attr) -> true)) {
            String fileContents = contents
                    .map((path) -> path.toAbsolutePath().toString())
                    .collect(Collectors.joining(
                            System.lineSeparator(),
                            "Directory Contents: " + System.lineSeparator(),
                            System.lineSeparator() + "Generated: " + LocalDateTime.now()
                    ));
            Files.writeString(indexFile, fileContents, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//Part-3
/**
        I want to create one of these files in every sub folder, so I need a path index, that reflects the changing path,
 based on what's passed as the argument. I do this by calling resolve on the starting path, and passing it my filename
 which will always be index.txt. I'll be using a stream, so I'll start with a try with resources block, and set up the
 variable to be a Stream of Path, called contents. This may surprise you, but I'm going to use Files.find here, passing
 it the starting path, the max value for the depth, and the predicate, right now, I'm always going to return true. And
 I'll need to catch the IOException. Why am I using find, when I could use list or walk?

        For one thing, I may want to change my rules later, and only print files, or certain types of folders, or only
 items that are owned by a certain user. Anyway, the criteria could change, and I always want to imagine how my methods
 might need to change in the future. Second, I like the idea of having access to the file attributes in my predicate,
 should I need it. Next, I want to collect all the stream's paths into a string, that'll be the content of my file. I'll
 start with a local variable, a String, called fileContents. I'll map this to the absolute path's string. I'll collect
 that with Collectors.joining. I'll join each item with a system line separator. I'll have a header in the file. And a
 trailer that will have the local date time. Finally, I'm going to write this string to a file, using Files.writeString,
 with my indexFile path. I'll pass it my fileContents variable, and I want to include some options, first to create the
 file if it doesn't exist, and second, to truncate the contents, if it does exist. I'll add a call to this method in my
 main method.
 **/
//End-Part-3

        try (Stream<Path> contents = Files.list(startingPath)) {
            contents
                    .filter(Files::isDirectory)
                    .toList()
                    .forEach(dir -> {
                        try {
                            generateIndexFile(dir);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
//Part-5
/**
        This time I'll use Files.list, in a try with resources block. By default this is only one level deep. I'll filter
 by paths that are directories. I'll collect the paths to a list, because I don't want to nest a recursive call within a
 stream operation. I'll chain forEach to the list I get back. And I'll execute my method on the child directory. I've got
 the usual error on generateIndexFile, so I'll surround that with a try catch. I'll re-run my code. Looking at the project
 panel, I can see four index dot txt files. I'll start with the one in icons. Here, you can see I just have the path of
 the current directory listed there. I'll examine the assets one next. The same for this, I have the current directory
 listed and its sub folder, icons. If I run this code again, I'll get each of the index.txt files listed in the output.
 I can see that best, if I look at index.txt in the public folder, so I'll open that up. This lists the sub folders, and
 all three of my index files. So far so good.

        The next part of the challenge was to create copies of the index.txt files. I'll do this in the main method,
 using a for loop.
 **/
//End-Part-5
    }
}

