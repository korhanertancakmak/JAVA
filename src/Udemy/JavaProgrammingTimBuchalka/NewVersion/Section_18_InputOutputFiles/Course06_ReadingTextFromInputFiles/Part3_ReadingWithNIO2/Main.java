package CourseCodes.NewSections.Section_18_InputOutputFiles.Course06_ReadingTextFromInputFiles.Part3_ReadingWithNIO2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**                                           Character Set
 *
 *      A character set is a defined collection of symbols, letters, numbers, punctuation marks, and other characters.
 * Each character in the set is assigned a unique numerical code, called a code point, which allows computers to store,
 * transmit, and interpret text. Two of the most common character sets are ASCII and Unicode. ASCII stands for The American
 * Standard Code for Information Interchange.  It's the oldest and most widely used character set. Unicode is a newer
 * character set, designed to support all of the world's writing systems.
 *
 *                                            Character Encoding
 *
 *      Character encoding is the process of assigning numbers to various characters, called glyphs. A glyph can be an
 * alphabetical character in any language, punctuation, or emojis, for example. There are different ways to represent
 * glyphs, with a numeric value.
 *
 *                                            ASCII Encoding
 *
 *                          Size            Includes Latin Alphabet         Notes
 *          US-ASCII       7 bits                    No                 Smaller range of characters
 *          ISO-8859-1     8 bits                   Yes                 More Widely Supported than US-ASCII
 *
 *                                            Unicode Encoding
 *
 *                                Size                  Benefits
 *          UTF-8         Variable(1 to 4 bytes)        Most popular encoding on the internet.
                                                        Includes ISO-8859-1, and more.
                                                        Can represent characters from all writing systems
 *          UTF-16              2 bytes                 Widely Supported
 *          UTF-32              4 bytes                 More efficient and straightforward to process, but uses more storage
 *                                                      space. Rarely used.
 *
 *                                          Which should you use?
 *
 *      In general, UTF-8 is the better choice for most applications. It's more efficient, more widely supported, and can
 * represent a wider range of characters. However, if you're only working with ASCII characters, ISO-8859-1 may be a better
 * choice for efficiency reasons.
 *
 */

public class Main {

    public static void main(String[] args) {

        // What my default character encoding is?
        System.out.println(System.getProperty("file.encoding"));  // gives UTF-8
        System.out.println(Charset.defaultCharset());             // gives UTF-8. Then I can say my system's default encoding is UTF-8

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part3_ReadingWithNIO2/fixedWidth.txt";
        Path path = Path.of(fileName);
        try {

            // Printing the entire text in the file
            System.out.println(new String(Files.readAllBytes(path)));
            System.out.println("----------------");
            System.out.println(Files.readString(path));

            // Printing the distinct values in 3rd column of the file, which is department of the employee
            String regexPattern = "(.{15})(.{3})(.{12})(.{8})(.{2}).*";
            Pattern p = Pattern.compile(regexPattern);
            Set<String> values = new TreeSet<>();
            Files.readAllLines(path).forEach(s -> {
                if (!s.startsWith("Name")) {
                    Matcher m = p.matcher(s);
                    if (m.matches()) {
                        values.add(m.group(3).trim());
                    }
                }
            });
            System.out.println(values);

            /**
             *      Streams are lazy executed, so resources are opened and never closed, until the terminal operation is
             * applied to the stream. Inside the parentheses, I'll have a local variable called stringStream, and assign
             * that the result of Files.lines. I'm going to have this stream return an array to a variable called results.
             * Again skipping the first row. The pattern is in the p variable, so I'll use that to get a matcher. This
             * becomes a stream of matcher instances at this point. I want to filter by actual matches, because they may
             * not all match. Some of your data might not be properly formatted, but I want to keep processing. I'll map
             * again, this time to a string, using the matcher's group 3, and trimming it. I want the values to be distinct,
             * and sorted, and returned in a string array. I'll print the results. Running this code below, I'll again get
             * my list of departments printed.
             */
            try (var stringStream = Files.lines(path)) {
                var results = stringStream
                        .skip(1)
                        .map(p::matcher)
                        .filter(Matcher::matches)
                        .map(m -> m.group(3).trim())
                        .distinct()
                        .sorted()
                        .toArray(String[]::new);
                System.out.println(Arrays.toString(results));
            }

            /**
             *      To count how many employee we got in each department, we use the collect terminal operation, first
             * passing it a Collectors.groupingBy. I'll group by the column I'm interested in, column 3 or the department,
             * which I can get from the matcher's group 3, and I'll trim that here as well. I'll follow that up, with the
             * Collectors.counting methods. This counts all records within the group. The result of this is I get back
             * a map, keyed by a string, the department name, and the value is the number of records in the department.
             * I'll print that data out, using the entrySet. Running this code,
             *
             *      Finance = 5
             *      HR = 4
             *      IT = 6
             *      Marketing = 5
             *
             *                                  Support on Files for reading data from a file
             *
             *      All of these methods read the entire contents of a file into memory. These methods support files up
             * to about 2 gigabytes. After that, you're in danger of an out of memory error. For large files, you'll want
             * to use a BufferedReader, or a Channel which I'll talk about shortly.
             *
             *              Method Signature                                            Description                                          Closes file?
             *       byte[] readAllBytes(Path path) throws IOException            Reads entire content of "any" file into a byte array.         Yes
             *       String readString(Path path) throws IOException              Reads entire content of a "text" file into a string.          Yes
             *       List<String> readAllLines(Path path) throws IOException      Reads entire content of a "text" file into a list of string.  Yes
             *       Stream<String> lines(Path path) throws IOException           Reads entire contents of a text file                          On Terminal Operation
             *
             * There are other ways to read data from files, and I'll cover those later.
             *
             */
            try (var stringStream = Files.lines(path)) {
                var results = stringStream
                        .skip(1)
                        .map(p::matcher)
                        .filter(Matcher::matches)
                        .collect(Collectors.groupingBy(m -> m.group(3).trim(),
                                Collectors.counting()));

                results.entrySet().forEach(System.out::println);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
