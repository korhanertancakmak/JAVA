package PatikaDev.Java102.JAVA.AdventureGame;

import java.util.Scanner;
public class Game {
    private final Scanner input = new Scanner(System.in);

    public void start() {
        System.out.print("Please enter a player name:");
        String userName = input.nextLine();
        Player player = new Player(userName);
        player.moveLoc();
    }
}
