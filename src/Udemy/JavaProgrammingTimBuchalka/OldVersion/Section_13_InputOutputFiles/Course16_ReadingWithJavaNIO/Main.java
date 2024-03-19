package CourseCodes.OldSections.Section_13_InputOutputFiles.Course16_ReadingWithJavaNIO;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    try {
//            FileInputStream file = new FileInputStream("input.txt");
//            FileChannel channel = file.getChannel();
            Path dataPath = FileSystems.getDefault().getPath("input.txt");
            Files.write(dataPath, "\nLine 5".getBytes("UTF-8"), StandardOpenOption.APPEND);
            List<String> lines = Files.readAllLines(dataPath);
            for(String line : lines) {
                System.out.println(line);
            }

        } catch(IOException e) {
	        e.printStackTrace();
        }
    }
}
