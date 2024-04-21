package external;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        String filePath = "D:/JAVA_STUDY/Github/JAVA/src/external";
        rewriteSubtitle(filePath);
    }

    private static void rewriteSubtitle(String filePath) {

        String filename = filePath + "/subtitle.txt";
        if (createSubtitle(filePath)) {
            System.out.println("New subtitle created!");
        } else {
            System.exit(0);
        }

        BufferedReader reader;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + "/newSubtitle.txt", true))) {
            reader = new BufferedReader(new FileReader(filename));

            for (int i = 0; i <= 3; i++){
                reader.readLine();
            }

            String line = reader.readLine();
            while (line != null) {
                if (!line.isEmpty() && !line.startsWith("00")) {
                    writer.write(line);
                    writer.newLine();
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean createSubtitle(String filePath) {
        File newSubtitleFile = new File(filePath + "/newSubtitle.txt");
        try {
            boolean fileExists = newSubtitleFile.createNewFile();
            return true;
        } catch (IOException e) {
            System.out.println("Something went wrong at creating new subtitle");
            return false;
        }
    }
}
