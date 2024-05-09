package HackerRankChallenges.MineFieldGame;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the row size of mine table :");
            int n = input.nextInt();
            System.out.print("Enter the colomn size of mine table :");
            int m = input.nextInt();
            System.out.println();
            if (n > 1 && m > 1) {
                MineSweeper mineSweeper = new MineSweeper(n, m);
                mineSweeper.run();
                break;
            } else {
                System.out.println("Please enter greater size than 2 x 2 for the table!");
            }
        }
    }
}

class MineSweeper {

    private final int ROW;
    private final int COLOMN;

    private final int nMines;

    private final Random random = new Random();

    private final String[][] tableWithMines;

    private int round = 0;
    private String[][] playTable;
    public MineSweeper(int row, int colomn) {
        this.ROW = row;
        this.COLOMN = colomn;
        this.tableWithMines = new String[this.ROW][this.COLOMN];
        this.playTable = new String[this.ROW][this.COLOMN];
        this.nMines = (ROW * COLOMN) / 4;
    }

    public void createTableMines() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLOMN; j++) {
                this.tableWithMines[i][j] = "-";
                this.playTable[i][j] = "-";
            }
        }
        for (int i = 0; i < this.nMines; i++) {
            int posX = random.nextInt(ROW);
            int posY = random.nextInt(COLOMN);
            if (this.tableWithMines[posX][posY].equals("*")) {
                i--;
            }
            this.tableWithMines[posX][posY] = "*";
        }
    }

    public void displayTableMines() {
        for (int i = 0; i < this.ROW; i++) {
            for (int j = 0; j < this.COLOMN; j++) {
                if (this.tableWithMines[i][j].equals("*")) {
                    System.out.print("* ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public void displayTable() {
        for (int i = 0; i < this.ROW; i++) {
            for (int j = 0; j < this.COLOMN; j++) {
                System.out.print(this.playTable[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void changePlayTable() {
        Scanner input = new Scanner(System.in);
        displayTable();
        while (true) {

            System.out.print("Enter row    : ");
            int x = input.nextInt();
            System.out.println("Enter colomn : ");
            int y = input.nextInt();

            if (x <= this.ROW && y <= this.COLOMN) {

                if (!this.playTable[x][y].equals("-")) {
                    System.out.println("This site is already played! Please choose another site!");
                    changePlayTable();
                } else if (this.tableWithMines[x][y].equals("*")) {
                    System.out.println("Game Over!!");
                    System.out.println("===========================");
                    System.exit(0);
                } else {
                    int counter = 0;
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (x - i >= 0 && x - i < this.ROW && y - j < this.COLOMN && y - j >= 0) {
                                if (this.tableWithMines[x - i][y - j].equals("*")) {
                                    counter++;
                                }
                            }
                        }
                    }
                    this.playTable[x][y] = Integer.toString(counter);
                    this.round++;
                    int totalElements = (this.ROW * this.COLOMN) - nMines;
                    if (totalElements - this.round == 0) {
                        System.out.println("You won the game !");
                        displayTable();
                        System.out.println("===========================");
                        System.exit(0);
                    } else {
                        changePlayTable();
                    }
                }
                System.out.println("===========================");
            } else {
                System.out.println("Please enter a site inside the table!");
            }
        }
    }

    public void run() {

        System.out.println("Location of mines");
        createTableMines();
        displayTableMines();
        System.out.println("===========================");
        System.out.println("Welcome to the minefield !");

        changePlayTable();
    }
}
