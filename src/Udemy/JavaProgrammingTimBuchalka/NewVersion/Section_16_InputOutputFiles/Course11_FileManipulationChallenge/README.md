# [Directory and File Manipulation Challenge]()
<div align="justify">

```java  
public class Main {

    public static void main(String[] args) {

        String publicDirPath = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course11_FileManipulationChallenge/";

        Path deepestFolder = Path.of(publicDirPath + "public", "assets", "icons");

        try {
            Files.createDirectories(deepestFolder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I've created a new **main** class, _main_ method ready to go.
The first thing I want to do is create my directory tree. 
First, I'll set up a path, using a factory method, 
that takes a variable list of paths. 
I can pass each directory, in a list, so first **public**, 
then **assets**, then **icons**. 
To create these directories, I can just call `Files.createDirectories`, 
and pass it this path. 
This fails because I need to catch the _IOException_, 
so I'll surround that code with a _try-catch_. 
I'll run this. 
Opening the project panel I'll see the public folder, 
and can drill down into it, to the **icons** subfolder.
Next, I want to write the code to generate the `index.txt` at each subfolder level. 
I'll create a private static void method, named _generateIndexFile_, 
that takes a path argument.

```java  
private static void generateIndexFile(Path startingPath) throws IOException {
    Path indexFile = startingPath.resolve("index.txt");
    try (Stream<Path> contents = Files.find(startingPath, Integer.MAX_VALUE, (path, attr) -> true)) {
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
}
```

I want to create one of these files in every subfolder, 
so I need a path index that reflects the changing path,
based on what's passed as the argument. 
I do this by calling _resolve_ on the _startingPath_, 
and passing it my filename, which will always be `index.txt`. 
I'll be using a stream, so I'll start with a _try-with-resources_ block, 
and set up the variable to be a **Stream** of **Path**, called _contents_. 
This may surprise you, but I'm going to use `Files.find` here, 
passing it the _startingPath_, the max value for the depth, 
and the predicate, right now, I'm always going to return **true**. 
And I'll need to catch the _IOException_. 
Why am I using _find_, when I could use _list_ or _walk_?
For one thing, I may want to change my rules later, 
and only print files, or certain types of folders, 
or only items that are owned by a certain user. 
Anyway, the criteria could change, 
and I always want to imagine how my methods might need to change in the future. 
Second, I like the idea of having access to the file attributes in my predicate,
should I need it. 
Next, I want to collect all the stream's paths into a string, 
that'll be the content of my file. 
I'll start with a local variable, a **String**, called _fileContents_. 
I'll map this to the absolute path's string. 
I'll collect that with `Collectors.joining`. 
I'll join each item with a system line separator. 
I'll have a header in the file. 
And a trailer that will have the local date time. 
Finally, I'm going to write this string to a file, 
using `Files.writeString`, with my _indexFile_ path. 
I'll pass it my _fileContents_ variable, 
and I want to include some options, 
first to create the file if it doesn't exist, 
and second, to truncate the contents if it does exist. 
I'll add a call to this method in my _main_ method.

```java  
public class Main {

    public static void main(String[] args) {

        String publicDirPath = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course11_FileManipulationChallenge/";

        Path deepestFolder = Path.of(publicDirPath + "public", "assets", "icons");

        try {
            Files.createDirectories(deepestFolder);
            generateIndexFile(deepestFolder.getName(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll start by passing it just **public**, the first subfolder, 
and I can do that by calling the _getName_ method, 
on my path variable, passing it index 0. 
Running this code, I can see it created one file, in the public folder. 
If I open that, I see my header, and the listing of my nested subfolders, 
with the absolute path there. 
Since I used the _find_ method, 
my text includes the absolute path of the current directory as the first item. 
I sort of like that myself. 
I don't see `index.txt`, but if I run this code again, I'll see it listed. 
The challenge was to create an `index.txt` for each subfolder, 
and I've only created one for the top level. 
I need to change my _generateIndex_ method, 
to call itself recursively, for any children that are directories.

```java  
private static void generateIndexFile(Path startingPath) throws IOException {
    Path indexFile = startingPath.resolve("index.txt");
    try (Stream<Path> contents = Files.find(startingPath, Integer.MAX_VALUE, (path, attr) -> true)) {
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
}
```

This time I'll use `Files.list`, in a _try-with-resources_ block. 
By default, this is only one level deep. 
I'll filter by paths that are directories. 
I'll collect the paths to a list, 
because I don't want to nest a recursive call within a stream operation. 
I'll chain _forEach_ to the list I get back. 
And I'll execute my method on the child directory. 
I've got the usual error on _generateIndexFile_, 
so I'll surround that with a _try-catch_. 
I'll re-run my code. 
Looking at the project panel, I can see four `index.txt` files. 
I'll start with the one in **icons**. 
You can see I just have the path of the current directory listed there. 
I'll examine the **assets** one next. 
The same for this, I have the current directory listed and its subfolder **icons**. 
If I run this code again, I'll get each of the `index.txt` files listed in the output.
I can see that the best if I look at `index.txt` in the **public** folder, 
so I'll open that up. 
This lists the subfolders and all three of my index files. 
So far, so good.
The next part of the challenge was to create copies of the `index.txt` files. 
I'll do this in the _main_ method, using a for loop.

```java  
public class Main {

    public static void main(String[] args) {

        String publicDirPath = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course11_FileManipulationChallenge/";

        Path deepestFolder = Path.of(publicDirPath + "public", "assets", "icons");

        try {
            Files.createDirectories(deepestFolder);
            generateIndexFile(deepestFolder.getName(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= deepestFolder.getNameCount(); i++) {
            Path indexedPath = deepestFolder.subpath(0, i).resolve("index.txt");
            Path backupPath = deepestFolder.subpath(0, i).resolve("indexCopy.txt");
            try {
                Files.copy(indexedPath, backupPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

I'll loop through the name parts, starting with 1, 
to get each sub path of the _deepestFolder_ path. 
I'll get the path for the existing file, `index.txt`, 
resolving to the sub path. 
I'll create a backup path in the same way, 
but with the name `indexCopy.txt`. 
Finally, I'll execute `Files.copy` here, 
and I want to copy the _indexedPath_ to the _backupPath_. 
I need to add a _try-catch_ around that last _copy_ method. 
Ok, that should do it. 
I'll run that. 
I can see my copies in the project panel, within every subfolder. 
The final step is to generate the `index.txt` again, 
after this, so I'll call that.

```java  
public class Main {

    public static void main(String[] args) {

        String publicDirPath = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course11_FileManipulationChallenge/";

        Path deepestFolder = Path.of(publicDirPath + "public", "assets", "icons");

        try {
            Files.createDirectories(deepestFolder);
            generateIndexFile(deepestFolder.getName(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= deepestFolder.getNameCount(); i++) {
            Path indexedPath = deepestFolder.subpath(0, i).resolve("index.txt");
            Path backupPath = deepestFolder.subpath(0, i).resolve("indexCopy.txt");
            try {
                Files.copy(indexedPath, backupPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            generateIndexFile(deepestFolder.getName(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll start with the _try_ block. 
I'll again call _generateIndexFile_, 
and pass it the name part at index 0. 
And I'll add the usual bit for the _IOException_. 
I'll run that. 
This time though, I've got an exception _FileAlreadyExistsException_,
through from the `Files.copy` method. 
Do you know why this is? 
Remember, _copy_ isn't going to overwrite an existing file,
unless we specifically tell it to, by using a _copy_ option. 
I'll scroll up to the `Files.copy` command there, 
and add the _StandardCopyOption_'s enum value, called _REPLACE_EXISTING_. 
I'll run this again. 
This time, my code ran without any issues,
so let's examine the contents of `index.txt` in the **public** folder. 
You can see this file has grown, 
and the content now includes the listings for the copies. 
Ok, so that's it, the challenge is complete.
</div>