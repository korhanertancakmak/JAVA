package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_Streams.Course05_IntermediateOperationsPart1;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();


        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();


        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .dropWhile(i -> Character.toUpperCase(i) <= 'E')
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();


        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .skip(5)
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();


        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .dropWhile(i -> i <= 'E')
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();


        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .dropWhile(i -> Character.toUpperCase(i) <= 'E')
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();


        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .dropWhile(i -> Character.toUpperCase(i) <= 'E')
                .takeWhile(i -> i < 'a')
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();


        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .map(Character::toUpperCase)
                .distinct()
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

        System.out.println();
        Random random = new Random();

        Stream.generate(() -> random.nextInt((int)'A', (int)'Z' + 1))
                .limit(50)
                .distinct()
                .sorted()
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();


        Stream.generate(() -> random.nextInt((int)'A', (int)'Z' + 1))
                .limit(50)
                .sorted()
                .forEach(d -> System.out.printf("%c ", d));

    }
}
