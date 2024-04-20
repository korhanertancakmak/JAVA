package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course12_RandomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildStudentData {

    public static void build(String datFileName, boolean separateIndex) {

        String pathStudentJson = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course12_RandomAccessFile/";
        Path studentJson = Path.of(pathStudentJson + "students.json");
        String dataFile = pathStudentJson + "/" + datFileName + ".dat";
        Map<Long, Long> indexedIds = new LinkedHashMap<>();

        try {
            Files.deleteIfExists(Path.of(dataFile));
            String data = Files.readString(studentJson);
            data = data.replaceFirst("^(\\[)", "")
                    .replaceFirst("(\\])$", "");
            var records = data.split(System.lineSeparator());
            System.out.println("# of records = " + records.length);

//Part-3
/**
 This class will have a public static void method, called build, that takes one argument, the data file name, which
 will really just be the prefix of the file name. I'll create a path to my students.json file. I'll create a filename
 from the argument passed, and concatenate ".dat" extension. I'll create a map, same as I did in the main class, mapped
 by an id, and the mapped value, will be the position in the file. I'll add a try block because I know I'm going to need
 it to catch IO Exceptions. If the date file already exists, I'll delete it. I'll read the entire contents of my json
 file in as a string. My json student records are wrapped in an opening square bracket and a closing square bracket, which
 I want to remove. I'll use replaceFirst, with a little regular expression magic to remove these enclosing brackets. The
 first expression removes the first opening bracket at the start of the entire string, and the next expression removes
 the first closing square bracket, it finds at the end of the entire string. I'll split the records by the line separator.
 I'll print the number of records to the console. And finally I add the catch block. Before I go any further, I think it
 will help you, if I show you what this file is going to look like, on a table.
 **/
//End-Part-3

//Part-4
/**
                            _________________
                            |  Record Count |
                            |  Int(4 bytes) |
                            |---------------|
                                    ----------------------------------------------
                     |<<<<<<<<<<<<<<<<<             Record 0                    >>>>>>>>>>>>>|
                     |                  Record Id               Record Position              |
                     |                Long(8 bytes)              Long(8 bytes)               |
                     |               ----------------------------------------------          |
                     |                              Record 1                                 |
                     |                  Record Id               Record Position              |
                     |                 Long(8 bytes)              Long(8 bytes)              |
          index      |               ----------------------------------------------          |       Step-2
                     |                                                                       |
                     |                                ......                                 |
                     |                                                                       |
                     |               ----------------------------------------------          |
                     |                              Record 1000                              |
                     |                  Record Id               Record Position              |
                     |<<<<<<<<<<<<<<<  Long(8 bytes)              Long(8 bytes) >>>>>>>>>>>>>|
                                    ----------------------------------------------
                                                                                            <<<<<<<< File Pointer = 4 + (16 * 1000)
                                    ______________________________________________
                        |<<<<<<<<<< Record 0                                        >>>>>>>|
                        |           Variable Bytes                                         |
                        |           ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯         |
                        |           Record 1                                               |
               data     |           Variable Bytes                                         |        Step-1
                        |           ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯         |
                        |           ----------------------------------------------         |
                        |           Record 1000                                            |
                        |<<<<<<<<<< Variable Bytes                                  >>>>>>>|

 This diagram represents what the file will look like once it's been completely generated. I won't be creating this file
 in top-down order however. In fact, Step-1 will consist of writing the records to the output file, at the file pointer
 position shown by the arrow. I'll be keeping track of the file position of each record with my map, as I write out each
 of the records. Once I have all the records inserted, and the index map complete, I'll write the total number of records
 at position 0. Then I'll start outputting each index key-value pair. This is Step 2. I'm able to start writing my records
 in step 1, before I insert the index, because I'm using a random access file, and I can move to the position I want to
 write to. I can derive the position to start, by calculating that I'll use 4 bytes to store the record count, which I'll
 output as an integer. Then I'll have 16 bytes for each indexed entry, because each long takes up 8 bytes. I can multiply
 that by the number of records, so 1000 * 16, then plus 4, will be my file pointer position, to start writing the records.
 **/
//End-Part-4

            //long startingPos = 4 + (16L * records.length);
            long startingPos = separateIndex ? 0 : 4 + (16L * records.length);

//Part-5
/**
        I'll write the code to derive the starting position. This is the position, where I'll start outputting the data
 records. I've said this starting position is going to be 16 times the record count, plus the 4 bytes I need to print the
 record count, at the start of the file.
 **/
//End-Part-5

            Pattern idPattern = Pattern.compile("studentId\":([0-9]+)");

//Part-6
/**
        To create my index, I need to extract the student id from each record. I'll do this with a regular expression
 pattern and the matcher class. I know the Json format, has the studentId text in quotes, followed by a colon, then the
 actual student id. My expression represents that, with the variable number in parentheses, representing group 1.
 This number is the student id.
 **/
//End-Part-6

            try (RandomAccessFile ra = new RandomAccessFile(dataFile, "rw")) {
                ra.seek(startingPos);
                for (String record : records) {
                    Matcher m = idPattern.matcher(record);
                    if (m.find()) {
                        long id = Long.parseLong(m.group(1));
                        indexedIds.put(id, ra.getFilePointer());
                        ra.writeUTF(record);
                    }
                }

//Part-7
/**
        I'll use a try with resources block, and create a new instance of a RandomAccessFile. This constructor takes two
 string arguments, the first is the file name, and the second is a string representing what mode I want to open this file
 in. In this case, I want to both read and write to it, so I pass "rw". I'll call seek on the random access file, leaving
 enough space to print my indexed data later. I'll loop through the records, which I got from the json file. I'll match
 each record to my pattern. I'll look for the first match. If I find a match, I can get the student id from group 1. I
 can add this id as the key to the index map, and the current file pointer as the position of the record in the data file.
 I'll "use writeUTF" to print the record to the file.
 **/
//End-Part-7

                //writeIndex(ra, indexedIds);
                writeIndex((separateIndex) ? new RandomAccessFile(pathStudentJson + "/" + datFileName + ".idx", "rw") : ra, indexedIds);

//Part-10
/**
        The last thing I need to do for this class, is to call the writeIndex method. This call needs to go after the for
 loop in the build method. To test this, I need to build out the code that's going to read the file with its index. That's
 going to be my Main class.
 **/
//End-Part-10

//Part-14
/**
        I'll add an argument to the build method, a boolean, called separateIndex. After this, I need to change the starting
 file position, based on this flag. I'll use a ternary operator, so that if this flag is true, my starting position, to
 write the records will be 0, otherwise it'll be the calculated file pointer. Remember, that if the index is in a separate
 file, the records will be written at position 0, of the data file. The last thing I need to change, is the call to the
 writeIndex method. I'll first add a new line after the opening parentheses. I'm going to insert a ternary here too, again
 checking if separate index is true. If it is, I want to create another randomAccessFile, with a suffix of idx, and I
 need to pass rw here, because I need to be able to both read and write to the index file, from this code that generates
 it. I'll get back to the Main class.
 **/
//End-Part-14

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//Part-8
/**
    Finally, I'll include the usual catch block. I'm not done yet though. This code represents only step 1 from the
 diagram. Step 2 is printing the record count, and the index data. To do this, I'll create a private static method on this
 class.
 **/
//End-Part-8
    }

    private static void writeIndex(RandomAccessFile ra, Map<Long, Long> indexMap) {
        try {
            ra.seek(0);
            ra.writeInt(indexMap.size());
            for (var studentIdx : indexMap.entrySet()) {
                ra.writeLong(studentIdx.getKey());
                ra.writeLong(studentIdx.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//Part-9
/**
        It returns void, and I'll call it writeIndex. It'll take the RandomAccessFile to be written to, and the index map,
 as arguments. I'll need a try catch. My index is always going to be at the start of the file. I'll write the size of the
 map, which should be equal to the number of records. Looping through the map entries, I'll write the key, and the value
 as longs, each write taking up 8 bytes. The usual catch block goes here.

        One thing I didn't really mention earlier, but it's important, is that you want to use a LinkedHashMap for the
 index map, so that your indexed data is in insertion order, matching the order the records were printed in.
 **/
//End-Part-9
}