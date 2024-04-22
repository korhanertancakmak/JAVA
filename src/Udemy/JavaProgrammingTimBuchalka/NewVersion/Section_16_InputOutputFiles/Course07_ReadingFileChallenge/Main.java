package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course07_ReadingFileChallenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course07_ReadingFileChallenge/article.txt";
/*
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            System.out.printf(" %d lines in file%n", br.lines().count());
            System.out.printf(" %d lines in file%n", br.lines().count());
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

/*
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            //System.out.printf(" %d lines in file%n", br.lines().count());

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");
            System.out.printf("%d words in file%n",
                    br.lines()
                            .flatMap(pattern::splitAsStream)
                            .count());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");
            System.out.printf("%d words in file%n",
                    br.lines()
                            //.flatMap(pattern::splitAsStream)
                            .flatMap(l -> Arrays.stream(l.split(pattern.toString())))
                            .count());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");
            System.out.printf("%d words in file%n",
                    br.lines()
                            .mapToLong(l -> l.split(pattern.toString()).length)
                            .sum());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");

            var result = br.lines()
                    .flatMap(pattern::splitAsStream)
                    .map(w -> w.replaceAll("\\p{Punct}", ""))
                    .filter(w -> w.length() > 4)
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

            result.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .limit(10)
                    .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue() + " times"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course07_ReadingFileChallenge/bigben.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");

            List<String> excluded = List.of(
                    "grand",
                    "canyon",
                    "retrieved",
                    "archived",
                    "service",
                    "original");


            var result = br.lines()
                    .flatMap(pattern::splitAsStream)
                    .map(w -> w.replaceAll("\\p{Punct}", ""))
                    .filter(w -> w.length() > 4)
                    .map(String::toLowerCase)
                    .filter(w -> !excluded.contains(w))
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

            result.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .limit(10)
                    .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue() + " times"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/


        String input = null;
        fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course07_ReadingFileChallenge/bigben.txt";
        try {
            input = Files.readString(Path.of(fileName));
            input = input.replaceAll("\\p{Punct}", "");

            Pattern pattern = Pattern.compile("\\w+");
            //Pattern pattern = Pattern.compile("\\w{5,}");
            Matcher matcher = pattern.matcher(input);
            Map<String, Long> results = new HashMap<>();
            while (matcher.find()) {
                String word = matcher.group().toLowerCase();
                if (word.length() > 4) {
                    results.merge(word, 1L, (o, n) -> o += n);
                }
            }

            var sortedEntries = new ArrayList<>(results.entrySet());
            sortedEntries.sort(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
            for (int i = 0; i < Math.min(10, sortedEntries.size()); i++) {
                var entry = sortedEntries.get(i);
                System.out.println(entry.getKey() + " - " + entry.getValue() + " times");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
