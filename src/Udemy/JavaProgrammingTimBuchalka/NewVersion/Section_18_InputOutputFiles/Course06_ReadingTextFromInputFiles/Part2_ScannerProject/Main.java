package CourseCodes.NewSections.Section_18_InputOutputFiles.Course06_ReadingTextFromInputFiles.Part2_ScannerProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";

        //try (Scanner scanner = new Scanner(Path.of(new FileReader(fileName)))) {
        //try (Scanner scanner = new Scanner(new FileReader(fileName))) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
/**
 *      A Scanner is NOT automatically closed, so if I didn't put that in a try with resources block, this code would keep
 * the file open. I didn't have to worry about closing a scanner, when the source was a String, because a String doesn't
 * open and hold onto an open resource.
 */

/*
            // To test the file has a next line
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
*/

/*
            // What delimiter the tokens method will use
            System.out.println(scanner.delimiter());
            // output : \p{javaWhitespace}+ | means that the text will be split by 1 or more white space, any white space,
            // which includes new line characters. What this means is, if I use tokens with the default delimiter, I'll
            // just get a list of words. That's not what I want, so I'll set the scanner's delimiter.
            scanner.useDelimiter("$");
            // I'll use a regular expression, and just put a '$' sign there, which is a meta character, for end of line.
            scanner.tokens().forEach(System.out::println);
            // Now I'll call tokens, which returns a stream of String, and print each string.
*/

/*
            // The result of this scanner findAll will be a list of words that are 10 characters or more. I don't have any
            // duplicates, and they're ordered naturally, not in alphabetical order in other words.
            scanner.findAll("[A-Za-z]{10,}")
                    .map(MatchResult::group)
                    .distinct()
                    .sorted()
                    .forEach(System.out::println);
            // This scanner findAll method cam help you very quickly scan the entire text in the file for matching elements.
*/


            /**
             *      In this case, the first line of the fixedWidth file is telling the field names. name = 15, age = 3,
             * department = 12, salary = 8, state = 2 characters.
             **/

            String regularExpression = "(.{15})(.{3})(.{12})(.{8})(.{2}).*";
            // This regular expression is going to group each column. '()' for group, '.' for any character, and specify
            // exact width in curly braces. I'll end with '.*', so that the line won't fail if there are extra characters.
            var results = scanner.findAll(regularExpression)
                    .skip(1)                                // This for skipping the header.
                    .map(m -> m.group(3).trim())
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);
            System.out.println(Arrays.toString(results));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
