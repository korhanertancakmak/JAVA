package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course10_ManagingFiles.Part2;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void main(String[] args) {


        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part2/files";
        String pathName2 = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part2/resources";

        Path fileDir = Path.of(pathName);
        Path resourceDir = Path.of(pathName2);

/*
        try {
            recurseCopy(fileDir, resourceDir);
            System.out.println("Directory copied to " + resourceDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

/*
        try {
            if (Files.exists(resourceDir)) {
                Files.delete(resourceDir);
            }
            recurseCopy(fileDir, resourceDir);
            System.out.println("Directory copied to " + resourceDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

/*
        try {
            Files.deleteIfExists(resourceDir)
            recurseCopy(fileDir, resourceDir);
            System.out.println("Directory copied to " + resourceDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

/*
        try {
            recurseDelete(resourceDir);
            recurseCopy(fileDir, resourceDir);
            System.out.println("Directory copied to " + resourceDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

/*
        try (BufferedReader reader = new BufferedReader(new FileReader(pathName + "/student-activity.json"));
             PrintWriter writer = new PrintWriter(pathName2 + "/students-backup.json")) {
            reader.transferTo(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/


        String urlString = "https://api.census.gov/data/2019/pep/charagegroups?get=NAME,POP&for=state:*";
        URI uri = URI.create(urlString);
        try (var urlInputStream = uri.toURL().openStream();
        ) {
            urlInputStream.transferTo(System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        String JsonPath = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course10_ManagingFiles/Part2/USPopulationByState.txt";
        Path jsonPath = Path.of(JsonPath);
        try (var reader = new InputStreamReader(uri.toURL().openStream());
             var writer = Files.newBufferedWriter(jsonPath)) {
            reader.transferTo(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String csvPath = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course10_ManagingFiles/USPopulationByState.csv";
        try (var reader = new InputStreamReader(uri.toURL().openStream());
             PrintWriter writer = new PrintWriter(csvPath)) {
             reader.transferTo(new Writer() {
                @Override
                public void write(char[] cbuf, int off, int len) throws IOException {

                    String jsonString = new String(cbuf, off, len).trim();
                    jsonString = jsonString.replace('[', ' ').trim();
                    jsonString = jsonString.replaceAll("\\]", "");
                    writer.write(jsonString);
                }

                @Override
                public void flush() throws IOException {
                    writer.flush();
                }

                @Override
                public void close() throws IOException {
                    writer.close();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void recurseCopy(Path source, Path target) throws IOException {

        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        if (Files.isDirectory(source)) {
            try (var children = Files.list(source)) {
                children.toList().forEach(
                        p -> {
                            try {
                                Main.recurseCopy(
                                        p, target.resolve(p.getFileName()));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        });
            }
        }
    }

    public static void recurseDelete(Path target) throws IOException {

        if (Files.isDirectory(target)) {
            try (var children = Files.list(target)) {
                children.toList().forEach(
                        p -> {
                            try {
                                Main.recurseDelete(p);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        });
            }
        }
        Files.delete(target);
    }
}