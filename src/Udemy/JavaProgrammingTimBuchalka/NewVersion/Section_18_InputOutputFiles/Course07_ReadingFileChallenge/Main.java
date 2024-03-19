package CourseCodes.NewSections.Section_18_InputOutputFiles.Course07_ReadingFileChallenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**                                     Reading text from a File
 *
 *      In this challenge, I want you to pick some text of your choice, from a document you have, or an online article,
 *  or some wiki page. You'll create a program to read the text document, with one of the methods we talked about in the
 *  last couple of lectures. You can pick any method you want to use, but whichever you use, your program should do the
 *  following:
 *      - Tokenize the text into words, remove any punctuation.
 *      - Ignore words with 5 characters or less.
 *      - Count the occurrences of each word.
 *      - Display the top 10 most used words.
 *
 *  Bonus: After you use one method, try a second method. If you used a method that used a stream, try some code without
 *  using a streaming method, or vice versa.
 */

public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course07_ReadingFileChallenge/article.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

/*
            // How many lines we have in the file
            System.out.printf(" %d lines in file%n", br.lines().count());

            // If I try to execute this method once again, I get 0 lines for the second statement. After the terminal
            // operation on the first pipeline, the file pointer is at the end of the file. You can't use back to back
            // calls like this.
            //System.out.printf(" %d lines in file%n", br.lines().count());
*/

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");

/*
            // How many words in the file by using splitAsStream method on flatMap and by calling stream on Arrays, passing
            // it the string array.
            System.out.printf("%d words in file%n",
                    br.lines()
                            //.flatMap(pattern::splitAsStream)
                            .flatMap(l -> Arrays.stream(l.split(pattern.toString())))
                            .count());
*/


/*
            // Instead of a flatMap, I could use map and sum. This time I'll use mapToLong and sum operations.
            System.out.printf("%d words in file%n",
                    br.lines()
                            .mapToLong(l -> l.split(pattern.toString()).length)
                            .sum());

        } catch (IOException e) {
            e.printStackTrace();
        }
*/

            // This is the words I want to exclude.
            List<String> excluded = List.of(
                    "grand",
                    "canyon",
                    "retrieved",
                    "archived",
                    "service",
                    "original");

            // I'll start out with the source, br.lines, I'll want to do a flatMap, cuz I want to evaluate every word.
            // One of the requirements was to get rid of trailing commas and periods, so I'll call map, calling replaceAll
            // method on the word, using a regular expression that stands for any punctuation. I'll filter words with
            // more than 4 characters. I'll make all the words lowercase, so I don't have duplicates, just based on case.
            // I'll need to filter out the excluded words, and I can do this by checking if the list contains the word,
            // and if it does, I'll return false, so the word won't be included. The terminal operation will group by the
            // whole word, and count all instances of the same words.
            var result = br.lines()
                    .flatMap(pattern::splitAsStream)
                    .map(w -> w.replaceAll("\\p{Punct}", ""))
                    .filter(w -> w.length() > 4)
                    .map(String::toLowerCase)
                    .filter(w -> !excluded.contains(w))
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

            // I only want the top 10 words that have the highest frequency. I'll stream again, by getting the entrySet
            // of my result, and calling stream on that.

            result.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .limit(10)
                    .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue() + " times"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("--------------------");

        // The Bonus part of this was to do this with a stream, and then without, or vice versa. So I'll do this now
        // without a stream next. I'll use readString on Files.

        String input = null;
        String fileName2 = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course07_ReadingFileChallenge/bigben.txt";
        try {
            input = Files.readString(Path.of(fileName2));
            // I can replace all punctuation in the entire String with one call.
            input = input.replaceAll("\\p{Punct}", "");

            // In this case, setting up the pattern will be different, cuz now we're not splitting or tokenizing.
            // I'll use Matcher to find all the words in this big string. This pattern will look for 1 or more word
            // characters with "\\w+". I'll get a Matcher, passing the matcher method all the text from my file.
            // I'll set up a new map manually, keyed by string, containing a long value. We'll use matcher's find
            // method to loop through each match, or each word it found. Then, we can use matcher.group to get next
            // word, and make that lowercase. I'll check the length, making sure it's greater than 4 characters. And
            // I'll use merge, putting 1 as the first value, and incrementing by that, if it's not a new keyed entry.
            // That'll populate my map with distinct values, and counts, so next I'll sort this, and print it.
            Pattern pattern2 = Pattern.compile("\\w{5,}");
            Matcher matcher = pattern2.matcher(input);
            Map<String, Long> results = new HashMap<>();
            while (matcher.find()) {
                String word = matcher.group().toLowerCase();
//                if (word.length() > 4) {
                results.merge(word, 1L, (o, n) -> o += n);
//                }
            }

            // I'll first create an array list of the entries. I'll sort the entries, by the value in reverse. I'll
            // loop from 0 to 9, or less if the text doesn't have 10 entries. I'll get the entry from the list. I'll
            // print it as I did before
            var sortedEntries = new ArrayList<>(results.entrySet());
            sortedEntries.sort(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
            for (int i = 0; i < Math.min(10, sortedEntries.size()); i++) {
                var entry = sortedEntries.get(i);
                System.out.println(entry.getKey() + " - " + entry.getValue() + " times");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // I could also have included that check for 5 characters, in the regular expression. I can do that with {}, then
        // "5,", which says I want at least 5 word characters, but I'll take more. And I can comment the if loop around
        // the merge. 
        //                              Pattern pattern2 = Pattern.compile("\\w+");
        //                                                  to
        //                              Pattern pattern2 = Pattern.compile("\\w{5,}");
    }
}
