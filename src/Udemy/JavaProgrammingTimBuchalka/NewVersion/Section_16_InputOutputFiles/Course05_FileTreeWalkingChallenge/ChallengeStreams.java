package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course05_FileTreeWalkingChallenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ChallengeStreams {

    public static void main(String[] args) {

        //Path startingPath = Path.of("..");
        Path startingPath = Path.of("./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/");
        int index = startingPath.getNameCount();
/*

        try (var paths = Files.walk(startingPath, Integer.MAX_VALUE)) {  // paths : Stream<Path>
            paths.filter(Files::isRegularFile)                           // Stream<Path>
                    .collect(Collectors.groupingBy(p -> p.subpath(index, index + 1),
                            Collectors.summarizingLong(
                                    p -> {
                                        try {
                                            return Files.size(p);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                            )))                                          // Map<Path, LongSummaryStatistics>
                    .forEach((key, value) ->
                    {System.out.printf("[%s] %,d bytes, %d files %n", key, value.getSum(), value.getCount());});
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        try (var paths = Files.walk(startingPath, Integer.MAX_VALUE)) {  // paths : Stream<Path>
            paths.filter(Files::isRegularFile)                           // Stream<Path>
                    .collect(Collectors.groupingBy(p -> p.subpath(index, index + 1),
                            Collectors.summarizingLong(
                                    p -> {
                                        try {
                                            return Files.size(p);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    })))                                 // Map<Path, LongSummaryStatistics>
                    .entrySet()                                          // Set<Entry<...>>
                    .stream()                                            // Stream<Entry<...>>
                    .sorted(Comparator.comparing(e -> e.getKey().toString()))
                    .filter(e -> e.getValue().getSum() > 1_000_000)
                    .forEach(e ->
                    {System.out.printf("[%s] %,d bytes, %d files %n",
                            e.getKey(), e.getValue().getSum(), e.getValue().getCount());});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
