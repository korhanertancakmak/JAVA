package CourseCodes.NewSections.Section_18_InputOutputFiles.Course06_ReadingTextFromInputFiles.Part1_ReadingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
/**
 *        Base path of the fileName has to be taken as Project File. Here, it is Courses, parent package of src and out.
 */

        //String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1/numbers.txt";
        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1/file.txt";
        try (FileReader reader = new FileReader(fileName)) {
            /** This numbers.txt has a line with "12345678910" numbers. But output is char. That's why
             * We could see the output like
             *                              49      char('1') = 49
             *                              50      char('2') = 50
             *                              51              .
             *                              52              .
             *                              53              .
             *                              54              .
             *                              55              .
             *                              56              .
             *                              57              .
             *                              49              .
             *                              48      char('0') = 48
             *
             * So if I want to see the character value, I can use the character wrapper to parse the int, or I can just
             * cast data, to a char in while loop.
             **/
            int data;
            char[] block = new char[1000];          // To read more than one char at a time and avoid casting in println
            /**                                     Disk Read(reader.read())
             *      A disk read means, something is physically, or mechanically, occurring on your hard disk to read
             * that character from the file. This is expensive, and Java provides ways to reduce the number of disk
             * reads being done.
             */
            while ((data = reader.read(block)) != -1) {
                /**                                     What's a file buffer?
                 *      A file buffer is just computer memory temporarily used to hold data, while it's being read from
                 * a file. Its primary purpose is to improve the efficiency of data transfer and processing. It reduces
                 * the number of direct interactions, or disk reads, against the actual storage device.
                 */
                //System.out.println(data);
                //System.out.println((char) data);

                String content = new String(block, 0, data);
                System.out.printf("---> [%d chars] %s%n", data, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**                                         Input Streams
         *
         *      For files, the implementation is the "FileInputStream". This class is used for files containing binary
         * data, so we'll be getting back to it later. Using the read method on FileInputStream is very inefficient, because
         * each read is a hard disk read, so if you're going to use a FileInputStream, you'll want to wrap it in a BufferedInputStream.
         *
         *                                             Readers
         *
         *      Readers read characters, as you can see from the methods on the abstract Reader parent, which is
         *
         *                      abstract read(char[] cbuf, int off, int len)
         *
         * An InputStreamReader is a bridge, from byte streams to character streams. If you want to read a character stream,
         * it's recommended you use a FileReader.
         *
         *      The FileReader will do the work of opening a FileInputStream for you. FileReader is doing buffered reading,
         * so it's doing a hard disk read, for a certain amount of characters, and storing those characters in memory.
         *
         *      A BufferedReader will also do buffered reading, using a much larger buffer size than the FileReader. You
         * can modify the size of the buffer on BufferedReader, by passing it to the constructor. But Java states that
         * the default buffer size of the BufferedReader is large enough for most purposes. The BufferedReader also provides
         * convenience methods for reading lines of text. So that's probably pretty confusing, if that's the first time
         * you've been exposed to these Java IO classes. In truth, Java's NIO2 provides functionality that reduces the
         * need to use these classes, under many circumstances. I wanted to show you these classes however, so that you'd
         * get more familiar with some terms, such as binary data vs. character data, input streams and readers, as well
         * as buffers, and disk reads.
         */

        System.out.println("-----------------------------------");

        /**
         *      To use a buffered reader, you usually wrap a FileReader with it. What I mean by that is, you'll pass a File
         * Reader to the constructor of the BufferedReader.
         */

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
/*
        With a file reader, I read the data either by integers, or by character arrays. What's nice about the buffered
    reader, besides it being more efficient, is that it gives us methods to read the data by lines.
*/
/*
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
*/
// We can replace this while loop with a single statement

            bufferedReader.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
