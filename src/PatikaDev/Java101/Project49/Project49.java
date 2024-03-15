package Project49;

/** Project49 - Jump Game

Let's play a game on an array! 
You're standing at index "0" of an n-element array named "game". From some index i (where 0 <= i < n), you can perform one of the following moves:

Move Backward : If cell "i - 1" exists and contains "0", you can walk back to cell "i - 1".
Move Forward  : If cell "i + 1" contains a zero, you can walk to cell "i + 1".
                If cell "i + leap" contains a zero, you can jump to cell "i + leap".
                If you're standing in cell "n - 1" or the value of "i + leap >= n", you can walk or jump off the end of the array and win the game.

In other words, you can move from index "i" to index "i + 1", "i - 1", or "i + leap" as long as the destination index is a cell containing "0". 
If the destination index is greater than "n - 1", you win the game.

Function Description
Complete the "canWin" function in the editor below. "canWin" has the following 

Parameters:
* int leap    : the size of the leap
* int game[n] : the array to traverse

Returns:
* boolean     : true if the game can be won, otherwise false

Input Format
The first line contains an integer, "q", denoting the number of queries (i.e., function calls).
The "2q" subsequent lines describe each query over two lines:

1. The first line contains two space-separated integers describing the respective values of "n" and "leap".
2. The second line contains "n" space-separated binary integers (i.e., zeroes and ones) describing the respective values of game_0, game_1, ..., game_(n - 1).

Constraints
* 1 <= q <= 5000
* 2 <= n <= 100
* 0 <= leap <= 100
* It is guaranteed that the value of "game[0]" is always 0. 
**/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Project49 {
    
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
                System.out.println(record.get(record.size()));

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

