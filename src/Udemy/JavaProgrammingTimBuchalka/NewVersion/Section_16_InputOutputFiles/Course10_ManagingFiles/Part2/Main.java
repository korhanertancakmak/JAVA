package CourseCodes.NewSections.Section_18_InputOutputFiles.Course10_ManagingFiles.Part2;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void main(String[] args) {

//Part-1
/**
        In the last lecture, I had copied a directory, but learned that this is a shallow copy, meaning none of the directory's
 contents were copied. In this lecture, I want to show you a way to copy the entire contents of a directory, or a deep
 copy. I've got my project, ManagingFiles/Part2, and I'm in the main class.I'll create a private static void method, and
 call it recurseCopy.
 **/
//End-Part-1

        String pathName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/Part2/files";
        String pathName2 = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/Part2/resources";

        Path fileDir = Path.of(pathName);
        Path resourceDir = Path.of(pathName2);
        try {
            //Files.deleteIfExists(resourceDir)
            recurseDelete(resourceDir);
            recurseCopy(fileDir, resourceDir);
            System.out.println("Directory copied to " + resourceDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

//Part-3
/**
        I'll replace the call to Files.copy with this one. I'll run this code.

            Directory copied to ....\Part2\resources

 It runs without errors and gives me the message as it did before, Directory copied to resources. Let's see what that folder
 looks like in the project panel. I can see the resources folder there, that it was created again, and I'll open that up.
 You can see that everything that was in the files folder, including the json file and the sub folder, data, is now in
 the resources directory. That's good. That means this code is working. What happens if I re-run this though, when the
 resources folder now exists. Let me do that.

            java.nio.file.FileAlreadyExistsException: ....\Part2\resources

 You can see I get a FileAlreadyExistsException, thrown by the recurseCopy method. The copy method includes an overloaded
 version, which let's you specify an option to copy a path, even if it already exists. I'll change my recurse method, to
 show you that. I'll add another argument to the Files.copy method, and this time I'll pass it an enum value from the
 StandardCopyOption enum, and this is called REPLACE_EXISTING. This means a copy will be done, even if the targeted path
 exists. Now, I'll run this again.

            java.nio.file.DirectoryNotEmptyException: ....\Part2\resources

 I still get an error, but this time it's a different exception, it's a DirectoryNotEmptyException. Similarly to the issue
 of being unable to do a deep copy, I can't replace an existing directory that already has content. One solution would be
 to delete the target directory if it exists, so I'll go back to my main method, and I'll try to do that. I'll first check
 to see if the directory exists. If it does, I'll try to delete it. Ok, now I'll run this.

             java.nio.file.DirectoryNotEmptyException: ....\Part2\resources

 Still, I get the exception, DirectoryNotEmptyException. As it turns out, I can't delete a directory if it has contents
 either. If I want to delete a directory, I'd have to recursively delete all the contents of its sub folders, similar to
 what I did with the recurseCopy method. There's another delete method, called deleteIfExists, which replaces this whole
 if statement. That looks cleaner, but if I run it,

             java.nio.file.DirectoryNotEmptyException: resources

 I've still got the same problem.
 **/
//End-Part-3

/*
        try (BufferedReader reader = new BufferedReader(new FileReader(pathName + "/student-activity.json"));
             PrintWriter writer = new PrintWriter(pathName + "/students-backup.json")) {
            reader.transferTo(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

//Part-5
/**
        For good measure, I'll add another sub folder, under data, and call it new data. And I'll run my code again.

                Directory copied to ...\Part2\resources

 I'll examine the resources folder. You can see new data, the folder I added, is now in the resources folder, confirming
 that the resources folder, matches files. In the past couple of examples, I've shown you how to copy, rename and delete
 files, as well as recursively copy and delete directories. Next, I want to explore a method on both the Reader and the
 InputStream interfaces, called transferTo. This method was added to InputStream in JDK 9, and to the Reader interface
 in JDK 10. Let me set up an example of using this, in the main method. I'll set up a Buffered Reader, to my student
 activity json that's now in the files sub folder. I'll pass a new File Reader to the buffered reader. I'll also create
 a new writer, a PrintWriter, which I've said, if I pass a string to it, will create a Buffered Writer for me underneath.
 I want my output file to be called students-backup.json. Here, I'll call reader.transferTo, and pass it my writer. I'll
 need the IOException catch clause as usual. If I run this code,

                Directory copied to ...\Part2\resources

 and I examine my project pane, I'll see my new file, students-backup.json. If I open that file, I can see, I've simply
 made a copy of the json file here. In essence, I've used reader.transferTo, to do what the Files.copy method did. That
 probably leads you to the question, Which is better?

 >>> Well, when you're working with files, you'll probably want to stick to Files.copy. Files.copy takes advantage of the
     File System provider, to do the work as efficiently as possible.
 >>> The reader dot transfer method might be more efficient for very large files, especially if a file is being copied
 across different network drives. Where the transfer to method really shines though, is when one of your input streams,
 differs from the output stream type.

    Let me give you a couple of examples. I'm going to use functionality in the java.net package, to make a request to a
 web site, to get a json response. I want to first comment out that last code before I start this.
 **/
//End-Part-5

        String urlString = "https://api.census.gov/data/2019/pep/charagegroups?get=NAME,POP&for=state:*";
        URI uri = URI.create(urlString);
        try (var urlInputStream = uri.toURL().openStream();
        ) {
            urlInputStream.transferTo(System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//Part-6
/**
 I'll create a local variable, that has the url to request data, from the United States Census Bureau, which will
 return the population for each state. I'll create a Uniform Resource Identifier or URI, which is a class in java.net, and
 I'll create an instance, passing the factory create method, the urlString. Now, a URL, which I'm sure you're familiar
 with, is always a URI. But there's another sub category of URI, which includes uniform resource names. I'll be getting
 into all of this later, in the networking section. For now, I want to use URI, because it provides me with handy methods,
 to get an input stream based on the URI. In a try with resources block, I'll set up a variable, url input stream. I'll
 set that to my uri.toURL method, chaining openStream. This opens a stream to the url, making the request, and retrieving
 the response, which in this case is JSON. I can call transferTo, on that urlInputStream, and pass System.out to that,
 which is just a specialized output stream. And I'll add the IOException. If I run this code,

                         [["NAME","POP","state"],
                         ["Mississippi","2976149","28"],
                                    ......,
                         ["Michigan","9986857","26"],
                         ["Minnesota","5639632","27"]]

 I should see the json response, being printed directly to my console. Here, I have the state name, the population from
 2019, and a state numeric id. Now, I'll do something similar, but this time, I'll request this data, but then dump it
 into a file.
 **/
//End-Part-6

        String newJsonPath1 = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/USPopulationByState.txt";
        Path jsonPath = Path.of(newJsonPath1);
        try (var reader = new InputStreamReader(uri.toURL().openStream());
             var writer = Files.newBufferedWriter(jsonPath)) {
            reader.transferTo(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//Part-7
/**
        I'll set up a variable, called jayson path, and this will be a file, named US Population by State.txt. In this
 case, I'm going to create a reader to writer scenario, so my reader will be a newInputStreamReader. I'll pass the code
 I used previously, uri.toURL.openStream. I'll get my writer, by using Files.newBufferedWriter, and pass that my json path
 variable. I'll call reader.transferTo and pass it my writer. Running this code, I can see a new file in my project pane,
 USPopulationByState.txt. If I open that, I see the same data that was printed to my console. This is pretty handy stuff,
 if you need to query resource data from external sites, because it allows you to quickly stream it to a local file. Now,
 let's say, I really want this data to be a csv file, and not json.
 **/
//End-Part-7

        String newJsonPath2 = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/USPopulationByState.csv";
        try (var reader = new InputStreamReader(uri.toURL().openStream());
             PrintWriter writer = new PrintWriter(newJsonPath2)) {
            reader.transferTo(new Writer() {
                @Override
                public void write(char[] cbuf, int off, int len) throws IOException {

                    String jsonString = new String(cbuf, off, len).trim();
                    jsonString = jsonString.replace('[', ' ').trim();
                    jsonString = jsonString.replaceAll("\\]", "");
                    writer.write(jsonString);
                }

                @Override
                public void flush() throws IOException {
                    writer.flush();
                }

                @Override
                public void close() throws IOException {
                    writer.close();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//Part-8
/**
        I can use the transferTo method, with a customized writer, that will do a transformation first. This is a little
 more complex, but it's pretty interesting code, and I want you to see it. I'll start by copying that last code block,
 and pasting a copy just below it. I'll first remove the writer statement. I'll replace that with the declaration of a
 PrintWriter instance, passing that a file name, USPopulationByState.csv. Now, on the next line, where I have writer, I
 want to replace that. I'll create an anonymous class, which will have the type of Writer, so new Writer empty parentheses,
 then opening and closing brackets. I've got an error, and if I hover over that, I see that Writer is abstract, and that
 I need to implement its abstract methods. I'll select implement methods. It will have all the abstract methods selected,
 which is what I want, so I'll press OK. Now, I have the method signatures for three methods. For the flush and close
 methods, I'll just delegate to my local variable writer's methods. But for the first method, the write method, I want
 to do some transformation here, before I pass off to the writer variable. First, I'll create a local string variable,
 called json string, and I can create that from the arguments passed to this method, the character buffer, the offset,
 and the length. I can replace the left square bracket character, with a space character, and I'll trim this at the end.
 My other option is to use replaceAll. The first argument is a regular expression, and since the closing square bracket
 is a meta character, I'll need to escape it with a pair of backslashes. This statement and the one above remove all the
 square brackets in the json response. Finally, I'll delegate to my local variable's write method. I'll run my code. After
 I see the state population data in the console, I'll see that the csv file was created in my project pane. I'll open that.
 You can see I was able, in a few short lines of code, to grab information from a web url, transform it, and write it to
 an output file, in a different format. Later, when I cover threads, I'll show you another example using this method,
 that will transfer input from the console, to a log file. You can see that this method, let's you transfer data from one
 type of source, to another, making the task very easy. Next couple of lectures, I want to talk about more advanced file
 topics. These include random access files, and serialization. First though, I've got a challenge for you.
 **/
//End-Part-8
    }

    public static void recurseCopy(Path source, Path target) throws IOException {

        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        if (Files.isDirectory(source)) {
            try (var children = Files.list(source)) {
                children.toList().forEach(
                        p -> {
                            try {
                                Main.recurseCopy(
                                        p, target.resolve(p.getFileName()));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        });
            }
        }
    }

//Part-2
/**
        This will take two arguments, a path, the source, and the target path which is the directory it will get copied
 to. This time I'll have this method declare a throws clause, throwing any IOException, since this code could fail in
 multiple different ways. I'll start by making a shallow copy of the source path. Next, I'll check if the source path is
 a directory. If it is, I'll get the contents by using Files.list. That returns a stream, which I'll call children. Don't
 forget, for any method on Files, that returns a stream, it's super important, to wrap it in a try with resources block,
 so you don't create a resource leak, if it's not properly closed. I'll use the stream's to list operation, to create a
 list of Path. I can chain a forEach to that. And pass a lambda expression. For each path, I want to call this method,
 Main.recurse copy recursively, meaning I'm calling it from inside itself. I'll pass the current path to this method.
 For the target though, I need to adjust the name of the targeted child path. I can do this with the resolve method. This
 code won't compile as is. I'm getting an error on Main.recurseCopy, and that's because this method throws an IO Exception.
 I have to surround it with a try catch block, which makes the code a bit uglier. Before I invoke this, I want to just
 explain what the resolve method is doing here. For relative paths, the source and target paths are joined or concatenated,
 when you use this method. As I iterate through my nested folders, I need to create a mirrored structure below the parent
 folder, which is the target, so I join the target, with the last part of the path name. For children that are sub folders,
 the last part is the unqualified or simple path name, in our case here, it will be data. Ok, that's my recurseCopy method.
 Getting back to the main method,
**/
//End-Part-2

    public static void recurseDelete(Path target) throws IOException {

        if (Files.isDirectory(target)) {
            try (var children = Files.list(target)) {
                children.toList().forEach(
                        p -> {
                            try {
                                Main.recurseDelete(p);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        });
            }
        }
        Files.delete(target);
    }

//Part-4
/**
        I'll copy my recurseCopy method, and paste that right below recurseCopy. I'll rename it to recurseDelete. For
 deleting, I just need one path for the method argument, and that would be the targeted deletion path. I'll remove that
 Files.copy statement. I'll change source to target, in the two statements that use source. I'll change the call to
 Main.recurseCopy to Main.recurseDelete, and remove the second argument, so just passing the streamed path, p. Ok, so I'm
 not actually deleting anything yet. The right place for the delete, is after this if statement, this is when the recursive
 operations have completed. If I placed it before the if statement, I'd simply get exceptions, saying the directory's not
 empty. I'll go back to my main method, and comment Files.deleteIfExists, and call this method instead. I'll run that.

                 Directory copied to ...\Part2\resources

 My console output simply says, directory copied to resources. I can see resources, and again it matches files directory.
 **/
//End-Part-4
}