package CourseCodes.HackerRankChallenges.ArrayGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Array1DGame {
    public static boolean canWin(int leap, int[] game) {

        return isSolvable(leap, game, 0);
    }

    public static boolean isSolvable(int leap, int[] game, int i) {
        if (i >= game.length)
            return true;
        if (i < 0 || game[i] == 1)
            return false;
        game[i] = 1;

        return isSolvable(leap, game, i + leap) ||
                isSolvable(leap, game, i + 1) ||
                isSolvable(leap, game, i - 1);
    }

    public static void main(String[] args) {

        String filePath = "./src/CourseCodes/input.txt";
        ArrayList<String> record = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {
            String str;
            ArrayList<String> line = new ArrayList<>();
            while ((str = buffer.readLine()) != null) {
                line.add(str);
            }
            int q = line.size() / 2;
            while (q-- > 0) {
                String[] first = line.get(0).split(" ");
                int n = Integer.valueOf(first[0]);
                int leap = Integer.valueOf(first[1]);
                line.remove(0);

                String[] gameStr = line.get(0).split(" ");
                int[] game = new int[n];
                for (int i = 0; i < n; i++) {
                    game[i] = Integer.valueOf(gameStr[i]);
                }
                line.remove(0);

                record.add(canWin(leap, game) ? "YES" : "NO");
                System.out.println(record.get(record.size() - 1));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path file = Paths.get("./src/CourseCodes/output.txt");
        try {
            Files.write(file, record, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*public static void main(String[] args) {
        int n = 84;
        int leap = 14;
        String str = "0 0 0 0 0 1 0 1 1 1 1 0 1 0 1 0 0 1 0 0 0 1 0 1 0 0 0 0 1 1 0 0 1 0 0 1 1 1 1 0 0 0 0 0 0 1 0 0 0 0 0 1 1 0 0 1 1 1 0 0 0 0 1 0 0 1 1 1 0 0 1 1 1 1 1 1 1 1 0 1 1 0 0 0";
        String[] strArr = str.split(" ");
        int[] game = new int[n];
        for (int i = 0; i < n; i++) {
            game[i] = Integer.valueOf(strArr[i]);
        }
        System.out.println((canWin(leap, game)) ? "YES" : "NO");
    }*/
}



