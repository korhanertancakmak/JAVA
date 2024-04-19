package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_14_InputOutputFiles.Course12_RandomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

//Part-1
/**                                             The RandomAccessFile
         So far, in all of our discussions up until now, I've been working with text, or character based files. In all
 cases, we started reading from the files at the start, and have read the data sequentially until the end of the file.
 We've written to a file, either at the start of the file, or appended to the end of it. We controlled this behavior,
 using values on the StandardOpenOptions enum. There's another way to access data from a file, and this is with a
 "RandomAccessFile". This class provides the ability, to directly access and modify data, at any specific location within
 the file. A random access file behaves like a large array of bytes, stored in the file system. There's a kind of cursor,
 or index into the implied array, called the "file pointer". A RandomAccessFile both reads and writes binary data, using
 special methods, which keep track of how many bytes will be read or written. This class can be used for both read and
 write operations. When you open a RandomAccessFile, its file pointer is at 0, or the start of the file. To move the file
 pointer, you execute a method on the file, called seek, passing it a long value, the position in the file, you wish to
 go to. To get the file pointer, you execute getFilePointer. Depending on the type of read or write method you're using,
 the file pointer will move a certain number of bytes when these operations complete. There are a lot of these methods,
 and I'll show them to you, by pulling up the Java API documentation, for this class's methods.

        Notice how many read methods you see here. There's one for every primitive, so readByte, readChar, readInt, readLong,
 and so on. There's readLine, letting you read a single line at a time, but there's some others, that may look unfamiliar.
 The most important of these is probably readUTF, which reads in a string from this file. I'll be using this method and
 talking about it more in a little bit. If I scroll down, I see the write methods, that line up with all the read methods.
 Each of these methods, when executed, moves the file pointer a certain number of bytes, so the readInt method, would move
 the file pointer 4 bytes, and so on. Are you still confused about why you'd use this?

        Let's say you have a file with many millions of records, and at any one time, you really need to access about 50
 of those. Instead of loading a million records into memory, you can load a simple array or small map, which will tell
 you how to locate records of interest in the big file. You wouldn't want to start reading from the beginning of the file,
 and read 10 million records, checking each one to see if it's a match. The RandomAccessFile lets you fast forward or
 backward, to a position in the file, using the seek method. From this position, you can read in only the data that matters,
 for your application. To do this though, you need to understand how many records are in your file, what it's record length
 is, and how you want to identify each record, to retrieve it.

        A RandomAccessFile needs an index, which houses a file pointer position, to each record of interest. This index
 could be implied, for a file with fixed width records, if you only need to get data by a row number, for example. This
 means it's very easy to do a little math to get the 10 thousandth record, when all the records are 250 characters in
 length. 10,000 * 250 will point you to the 10,000th record in your file. It's much more common though, to retrieve records
 by a non-sequential id, than a row id. For this, you'll need an index, that will contain this id, and the position in
 the file of the record associated with that id. For fixed width records, your index wouldn't need the file pointer position,
 just an association between the row id and the record id. This index might be an array of record IDs for example, in row
 id sequence.

         *Row Index     *Record ID       Position in File        Fixed Size Record (250 chars)
             0            100000                0                        First Record
             1              1                  250                       Second Record
             2            543210               500                       Third Record
             3             777                 750                       Fourth Record

 On this table, you can see that record id 100000 is the first record in the file, so it's at index 0. The columns with
 "*" on this table, represent the indexed data you'd need, to locate your records by record id. For variable length records,
 the row id alone, isn't enough information to calculate the file pointer. You could store the length of each record, or
 you could just store the starting file pointer position. It's more common to do the second. Again, the indexed information
 is represented by the "RecordId" and "Position in File" data, shown in the table below.

           Index         Record ID         File Pointer          Variable Size
             0            100000                0                First Record(50)
             1              1                   50               Second Record(250)
             2            543210                300              Third Record(150)
             3             777                  450              Fourth Record(500)

 Your index should store the record id, and its associated file pointer. Where is this index data stored, and how do you
 access it? In the case of a fixed width file, it may not exist. If it does, then it will be in the same place as an index
 for a variable width file. This may be at the beginning of the data file, before the record data. It may be at the end
 of the data file, so after all the record data or the indexed data may be in a separate file altogether. Ok, let's see
 what this class looks like in code.
 **/
//End-Part-1

public class Main {

    private static final Map<Long, Long> indexedIds = new LinkedHashMap<>();    // key = record id, value = file pointer position
    private static int recordsInFile = 0;                                       // # of records in the file

//Part-2
/**
        I've created a new project called RandomAccessFile, with the usual Main class and main method. I've also included
 the students.json file, which I've used in several other lectures. This file is going to be the source of the data file
 I create, using a RandomAccessFile. The first thing I want to start with, in the Main class, is a couple of private static
 variables. I'll create a map, which will be keyed by a long, representing the id of my record, and that will map to a
 long value, the starting file pointer position of the stored record in the file. I'll also keep track of how many records
 are in the file. I'll next create a separate class to build the data file and index, so I'll create a new class named
 BuildStudentData, in the same folder.
**/
//End-Part-2

    public static void main(String[] args) {

        //BuildStudentData.build("studentData", false);            // commented via Part-11

//Part-11
/**
        I can add a call to the BuildStudentData.build method, and I want my file name to be prefixed with studentData.
 I can run this as it is,

                 # of records = 1000

 and I'll see that the studentData.dat file was created in my project folder. I can click on that to open it. I'll get
 all kinds of warnings from IntelliJ because it's a binary file, and you can see it's hard to read. The indexing part of
 this file is at the start. How do we know if this is a good file then? Well, we have to test it. The indexed data file
 exists, I don't actually want to call the build method again, so the first thing I'll do here, is comment out that statement
 that calls BuildStudentData.build, in my main method. I only need to build this file once, and now I'll write the client
 code that will make use of it. I'll start by writing code to load the indexed data first. For this, I'll set up a private
 static void method, called loadIndex.
 **/
//End-Part-11

        String cwdPath = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course12_RandomAccessFile/";
        try (RandomAccessFile ra = new RandomAccessFile(cwdPath + "studentData.dat", "r")) {
            loadIndex(ra, 0);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a Student Id or 0 to quit");
            while (scanner.hasNext()) {
                long studentId = Long.parseLong(scanner.nextLine());
                if (studentId < 1) {
                    break;
                }
                ra.seek(indexedIds.get(studentId));
                String targetedRecord = ra.readUTF();
                System.out.println(targetedRecord);
                System.out.println("Enter another Student Id or 0 to quit");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//Part-13
/**
        I'll open a random access file, passing it the name, and this time, the access mode just needs to be r, for read.
 In this case I'll only be reading the data. I'll call loadIndex, passing it r a and 0, as the starting file position,
 to read that data. And I'll catch and handle the io exception. To make this more interesting, I'll set up a scanner, so
 that the user can request a record, by inputting a student id. I'll set the scanner up, And prompt the user to enter a
 student id or 0 to quit. I'll start a while loop using the scanner.hasNext method, that returns a boolean. I'll keep
 getting input from the user until they enter 0. I'll use nextLine, and pass that to Long.parseLong to get the student
 id they entered. If the id is less than 1, then I'll quit out of the loop. I'll get the file position of this student,
 from the map, and use the seek method to go directly to that point in the file. I can use readUTF to get the record.
 I'll print the record to the console, and prompt for the next id. Before I run this, let me just talk to you about this
 readUTF method, and the corresponding writeUTF method, which I used to write this data in the last lecture. Part of the
 functionality of the writeUTF method, is to include the length of the data that was written. This means the readUTF
 method can first get that length, and then read only that specified block of data into a string. Ok, so there's the
 code to prompt a user for a student id, and to retrieve that data. This code could be a lot more robust. It's possible
 the user could enter an invalid id, and this would throw an exception. I could use max and min functions on the key set
 list, to get a valid range for example. For now, I just want to keep this short and simple, to see if this actually
 works. I'll run it.

                 1000
                 Enter a Student Id or 0 to quit

 I can see 1000 printed to the console. That's the number of records it found in the index, so the code was able to read
 that accurately, from the ".dat" file. I'm being prompted for an id, so I'll enter 777, and press enter.

                 {"studentId":777, "demographics":{"countryCode":"IN", "enrolledMonth":7, "enrolledYear":2017, "ageAtEnrollment":60, "gender":"F", "previousProgrammingExperience":false}, "coursesEnrolled":[{"courseCode":"JMC", "title":"Java Masterclass"},{"courseCode":"PYC", "title":"Python Masterclass"}], "engagementMap":[{"courseCode":"JMC", "engagementType":"Lecture 8", "enrollmentMonth":7, "enrollmentYear":2017, "lastLecture":8, "lastActiveMonth":4, "lastActiveYear":2023},{"courseCode":"PYC", "engagementType":"Lecture 12", "enrollmentMonth":7, "enrollmentYear":2017, "lastLecture":12, "lastActiveMonth":8, "lastActiveYear":2022}]},
                 Enter another Student Id or 0 to quit

 The code then prints the student data it found, and you can see, this is right on the money, and it's printed the json
 data record, for student 777. I'll try a second student, so maybe 555.

                 {"studentId":555, "demographics":{"countryCode":"CN", "enrolledMonth":1, "enrolledYear":2022, "ageAtEnrollment":61, "gender":"U", "previousProgrammingExperience":false}, "coursesEnrolled":[{"courseCode":"JMC", "title":"Java Masterclass"},{"courseCode":"PYC", "title":"Python Masterclass"}], "engagementMap":[{"courseCode":"JMC", "engagementType":"Lecture 6", "enrollmentMonth":1, "enrollmentYear":2022, "lastLecture":6, "lastActiveMonth":10, "lastActiveYear":2023},{"courseCode":"PYC", "engagementType":"Lecture 7", "enrollmentMonth":1, "enrollmentYear":2022, "lastLecture":7, "lastActiveMonth":8, "lastActiveYear":2023}]},
                 Enter another Student Id or 0 to quit

 Now, I get student id 555, and all of that student's data. This means I've been able to successfully load the index data,
 caching only that index in memory I can retrieve records from the file, on an as needed basis, and using a targeted file
 pointer to quickly do it. I'll press 0 to quit out of my application. Storing the index as the first part of a data file,
 is one way to provide this information to your users. A second way, is to store the index as a separate file altogether.
 I'll tweak the code a little bit, and show you an example of this. Getting back to the BuildStudentData class,
 **/
//End-Part-13
    }

    private static void loadIndex(RandomAccessFile ra, int indexPosition) {
        try {
            ra.seek(indexPosition);
            recordsInFile = ra.readInt();
            System.out.println(recordsInFile);
            for (int i = 0; i < recordsInFile; i++) {
                indexedIds.put(ra.readLong(), ra.readLong());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//Part-12
/**
        This will take a RandomAccessFile instance, and an indexPosition to start reading the data. I'll need a try block.
 I'll seek to the starting position, which in my case is really going to be zero. I'll call read Int, to get the first
 data element, the count of records in the file. I'll print that to the console. I'll use that variable, in a for loop,
 to determine how many times I should read the id, and the file position. I'll populate my map, using readLong to get the
 key, and a second readLong to get the stored file position. And I'll add the catch clause here. Now that I have that
 method, I'll get back to the main method, and set this up.
 **/
//End-Part-12
}

