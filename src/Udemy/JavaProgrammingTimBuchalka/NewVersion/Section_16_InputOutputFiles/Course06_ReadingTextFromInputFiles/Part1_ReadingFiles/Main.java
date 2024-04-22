package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course06_ReadingTextFromInputFiles.Part1_ReadingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1_ReadingFiles/numbers.txt";
/*
        try (FileReader reader = new FileReader(fileName)) {
            int data;
            while ((data = reader.read()) != -1) {

                System.out.println(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

/*

        try (FileReader reader = new FileReader(fileName)) {
            int data;
            while ((data = reader.read()) != -1) {

                System.out.println((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1_ReadingFiles/file.txt";
/*
        try (FileReader reader = new FileReader(fileName)) {
            char[] block = new char[1000];
            int data;
            while ((data = reader.read(block)) != -1) {

                String content = new String(block, 0, data);
                System.out.printf("---> [%d chars] %s%n", data, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

/*
        System.out.println("-----------------------------------");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        System.out.println("-----------------------------------");
        fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1_ReadingFiles/file.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
/*
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
*/

            bufferedReader.lines().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
