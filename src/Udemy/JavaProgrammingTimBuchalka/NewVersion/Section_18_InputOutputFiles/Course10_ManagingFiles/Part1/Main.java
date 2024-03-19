package CourseCodes.NewSections.Section_18_InputOutputFiles.Course10_ManagingFiles.Part1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//Part-1
/**
                                            File and Directory Management

        So much of what we might want to do with files and directories is renaming, copying, moving and deleting them.
 Occasionally, we might also want to make a global search and replacement, on the contents of an existing file. I'll devote
 the next couple of lectures to these kinds of tasks, and how to use Java's support for this kind of work. I've created
 a new project called ManagingFiles, and have the Main class's main method ready to go. I've also copied over my students.json
 file, from the previous challenge video, and I've put that in this project's root folder. It contains information about
 1000 students, and their course engagement records, mocked up with random data, and formatted using json. The Files class
 has a wealth of helpful methods I haven't yet covered, and I'll be using this file, to demonstrate some of these methods.
 I'll rename, move, copy and delete files, using methods on this class. Under the covers, these methods delegate to the
 OS FileSystem provider, for increased efficiency.
 **/
//End-Part-1

public class Main {

    public static void main(String[] args) {

/*
        String pathName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/Part1/students.json";
        String pathName2 = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/Part1/student-activity.json";

        File oldFile = new File(pathName);
        File newFile = new File(pathName2);

        if (oldFile.exists()) {
            oldFile.renameTo(newFile);
            System.out.println("File renamed successfully!");
        } else {
            System.out.println("File does not exist!");
        }
*/

//Part-2
/**
        I'll start by showing you how to rename a file using the IO way, with two File instances. I'll create a File instance
 to the exist in students.json file. Next, I'll create a File instance using the new name of the file, in this case
 student-activity.json. Notice that, this is perfectly valid to do, you can create a File instance, using a file name,
 even though this file doesn't exist. I'll confirm that the old file, the file I plan to rename, actually exists. I can
 execute a renameTo method, on the old file, passing it the new file instances. If this is successful, I'll print that
 out. If the file doesn't exist, I'll print that out too. If I run this code, as is,

        File renamed successfully!

 it runs without any issues, and my students.json file is successfully renamed, to student-activity.json. There's a couple
 of problems with this code though. IntelliJ is showing me one issue, by highlighting a warning, that I'm not using the
 output from the renameTo method. There are a lot of things that could go wrong, while trying to perform the rename operation.
 Ignoring the result, is a bad idea, because it's possible the rename operation actually failed. This could be due to user
 permissions, or a variety of other reasons like network connectivity, and so on. Remember, one of the problems with the
 java io classes, is they don't throw an IO Exception. Instead they simply return a boolean, and you don't have a good
 window into what went wrong, if you do get a false back from this method. I'm going to leave this code as it is though,
 because instead I want to use Path, and the Files class.
**/
//End-Part-2

/*
        String pathName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/Part1/students.json";
        String pathName2 = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/Part1/student-activity.json";

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

//Part-3
/**
        First, I'll execute the toPath method on File, that lets me take an existing File instance, and turn it into an
 NIO2 Path instance. The Path interface has a similar method, called toFile, so you can work with both IO and NIO2 classes,
 by converting between them this way. I'll create a new variable, oldPath, setting it to oldFile. toPath. And I'll do the
 same with new Path, from the newFile. I'll start with a try block, because by now, you'll know most methods on Files,
 throw an IO Exception. This is considered an improvement, because it results in targeted and informative exceptions,
 about any problems that occur during the operation. I'll start out typing Files.r, and wait for IntelliJ to list the
 method names. What I want you to see here is, that there isn't a rename method on Files. Instead, I need to use the move
 method, so I'll back out that r, and finish with a move method call, passing it first newPath in this case. Our file was
 renamed in the last bit of code, so I want to now rename students-activity.json back to students.json, so I pass the new
 path first, and the old path next. After this statement, If I get to this point, I can print that the rename was successful.
 I'll again catch the IOException that's thrown. And print out any stack trace, from the error. Running this code,

            File does not exist!
            Path renamed successfully!

 I get the console output for the first rename, that the file doesn't exist. That's because I already renamed it, in the
 previous run. In the case of the Path move operation, I get the Path renamed successfully. I can see that if I examine
 the project pane, that the students.json file is listed there, so it was renamed back to the original name. Let's explore
 this a bit further, and change the path we're moving to, adding a sub folder as part of that path.
**/
//End-Part-3

/*
        String pathName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/Part1/students.json";
        String pathName2 = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/Part1/files/student-activity.json";

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

//Part-4
/**
        I'm going to first comment out the Files code, because I think it'll make the following code less confusing. Now,
 my code doesn't compile, since my Paths, newPath and oldPath, are derived from the file instances, but that's ok. I'll
 remove those two local variable statements, and start over. I'll make oldPath the students.json file this time. For the
 new path, I want to include a sub folder, so files, then a slash or file separator, and that goes before the new file
 name, student-activity.json. I also want to reverse the arguments in the Files.move method, since now oldPath is students.json.
 If I run this code,

        java.nio.file.NoSuchFileException : students.json -> files\student-activity.json

 I get a NoSuchFileException, and the message shows the from file, then an arrow token, and the to file name. It's important
 to understand that this move method isn't going to create subdirectories. Since the files directory doesn't yet exist,
 I'll get this error. I can fix this by first using "createDirectories", before I call the move method. I don't want to
 call this method, with the entire path name though. I need to strip off the last part of the path, or the file name here.
 To do this, I can use the "subpath" method to just get the directories path. I'll start at index 0, and include everything
 except the last part of the name, so name "count - 1". I'll change the message I output to the console, to be more informative,
 so old path moved and renamed to new path. I'll re-run this again.

        ...\Part1\students.json moved (renamed to) --> ...\Part1\files\student-activity.json

 My console output tells me that students.json was moved and renamed to, the files sub folder, and the file name student-activity.json.
 I should see the files subfolder in my project panel. If you don't see it, just reload from disk and it should show up.
 Opening that folder, I can see the students-activity.json file there. In this example, I was able to use this one method,
 to both rename a file, and move it to another directory. I can also use the move method, to rename directories, which
 I'll do next. I'll comment this last bit of code out.
 **/
//End-Part-4

        String pathName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/Part1/files";
        String pathName2 = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/Part1/resources";

/*
        Path fileDir = Path.of(pathName);
        Path resourceDir = Path.of(pathName2);

        try {
            Files.move(fileDir, resourceDir);
            System.out.println("Directory renamed");
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

//Part-5
/**
        I'll create a path to my files folder, and I can do that using a relative path, so just files. I don't want to
 include a preceding file separator symbol in other words, this is what makes it relative. I'll do the same for the folder
 I want it to be renamed to, so resources here. I'll start with a try block. I'll call files.move, with the existing directory,
 so fileDir, and then the directory I want it to be called, so I'll pass it the resourceDir path. If no exception is thrown,
 then I know this was successful, so I'll print Directory renamed. Otherwise I'll print information about the exception.
 Running this code,

            Directory renamed

 I get the message that directory was renamed. Looking at my project panel, I see that I no longer have a files folder,
 but I do have a resources folder, and that contains my json file. I'll manually refactor, then rename the "resources"
 folder in IntelliJ back to "files", so my code will run as before. While I'm in here, I'll also add a nested folder in
 the "files" folder, and I'll call that the "data" folder.
 **/
//End-Part-5

        Path fileDir = Path.of(pathName);
        Path resourceDir = Path.of(pathName2);
        try {
            Files.copy(fileDir, resourceDir);
            System.out.println("Directory copied to " + resourceDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

//Part-6
/**
        Getting back to my code, instead of the move method, I want to now use the copy method. I'll also change my message
 from directory renamed, to directory copied to, and I'll print the path that I copied it to. I'll run this real quick.

            Directory copied to ...\Part1\resources

 I get the message that the directory was copied to resources, so I'll explore the results in the project panel. I can
 see the resources folder, but there's nothing in it. The copy method performs a shallow copy of your folder, and in this
 case, that shallow copy didn't even include my student file. I'll delete the resources folder manually, using IntelliJ's
 delete menu option. If you want to do a deep copy, you'd have to write a little bit of recursive code to handle it. You'll
 see a lot of examples online using the walkFileTree method, but let's see if we can do something similar, with less code,
 and using streams. I'll demonstrate a way to recursively copy your directory's content, in the next lecture.
 **/
//End-Part-6

    }
}
