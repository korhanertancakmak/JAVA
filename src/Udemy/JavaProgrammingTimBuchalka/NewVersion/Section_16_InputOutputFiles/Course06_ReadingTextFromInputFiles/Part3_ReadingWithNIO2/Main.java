package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course06_ReadingTextFromInputFiles.Part3_ReadingWithNIO2;

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

public class Main {

    public static void main(String[] args) {
/*
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());
*/

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part3_ReadingWithNIO2/fixedWidth.txt";
        Path path = Path.of(fileName);

/*
        try {
            System.out.println(new String(Files.readAllBytes(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try {
            System.out.println(new String(Files.readAllBytes(path)));
            System.out.println("----------------");
            System.out.println(Files.readString(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (var stringStream = Files.lines(path)) {
            String regexPattern = "(.{15})(.{3})(.{12})(.{8})(.{2}).*";
            Pattern p = Pattern.compile(regexPattern);
            Set<String> values = new TreeSet<>();

            var results = stringStream
                    .skip(1)
                    .map(p::matcher)
                    .filter(Matcher::matches)
                    .map(m -> m.group(3).trim())
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);
            System.out.println(Arrays.toString(results));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/


        try (var stringStream = Files.lines(path)) {            //Stream<String>
            String regexPattern = "(.{15})(.{3})(.{12})(.{8})(.{2}).*";
            Pattern p = Pattern.compile(regexPattern);
            Set<String> values = new TreeSet<>();

            var results = stringStream
                    .skip(1)                                               //Stream<String>
                    .map(p::matcher)                                          //Stream<Matcher>
                    .filter(Matcher::matches)
                    .collect(Collectors.groupingBy(m -> m.group(3).trim(), Collectors.counting()));

            results.entrySet().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
