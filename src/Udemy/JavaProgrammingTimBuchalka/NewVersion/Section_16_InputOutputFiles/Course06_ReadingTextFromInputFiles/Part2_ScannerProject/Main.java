package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course06_ReadingTextFromInputFiles.Part2_ScannerProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Main {

    public static void main(String[] args) {

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/file.txt";

/*
        try (Scanner scanner = new Scanner(new File(pathName))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (Scanner scanner = new Scanner(new File(pathName))) {
            System.out.println(scanner.delimiter());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (Scanner scanner = new Scanner(new File(pathName))) {

            System.out.println(scanner.delimiter());
            scanner.useDelimiter("$");
            scanner.tokens().forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (Scanner scanner = new Scanner(new File(pathName))) {

            scanner.findAll("[A-Za-z]{10,}")        // Stream<MatchResult>
                    .map(MatchResult::group)                // Stream<Object>
                    .distinct()
                    .sorted()
                    .forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

        pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";

/*

        try (Scanner scanner = new Scanner(new File(pathName))) {

            var results = scanner.findAll(
                            "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .map(m -> m.group(5))                                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                                // String[]
            System.out.println(Arrays.toString(results));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (Scanner scanner = new Scanner(new File(pathName))) {

            var results = scanner.findAll(
                            "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .skip(1)
                    .map(m -> m.group(5))                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (Scanner scanner = new Scanner(new File(pathName))) {

            var results = scanner.findAll(
                            "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .skip(1)
                    .map(m -> m.group(3))                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (Scanner scanner = new Scanner(new File(pathName))) {

            var results = scanner.findAll(
                            "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .skip(1)
                    .map(m -> m.group(3).trim())                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (Scanner scanner = new Scanner(Path.of(pathName))) {

            var results = scanner.findAll(
                            "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .skip(1)
                    .map(m -> m.group(3).trim())                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (Scanner scanner = new Scanner(new FileReader(pathName))) {

            var results = scanner.findAll(
                            "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .skip(1)
                    .map(m -> m.group(3).trim())                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/


        try (Scanner scanner = new Scanner(
                new BufferedReader(new FileReader(pathName)))) {

            var results = scanner.findAll(
                            "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .skip(1)
                    .map(m -> m.group(3).trim())                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
