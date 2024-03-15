/** Pratice53 - Minefield

Write a program for minefield game

Game rules:

    * The game is text based.
    * The project should be built using double-dimensional arrays.
    * It should be designed within the MineSweeper class. Methods should be used to prevent code duplication.
    * The user must determine the matrix size, that is, the number of rows and columns. Matrix entries with a 
    minimum size of 2x2 should be allowed. In case of entry of values ​​smaller than 2x2, the user should be warned 
    and asked to enter rows and columns again.
    * Random mines should be placed as a quarter of the number of elements in the array (number of elements / 4). 
    The number of mines should not be more or less than the number of personnel/4. For example, if the array is 
    4x3 in size, the number of elements should be calculated with the formula (Number of rows * Number of columns) 
    and its size will be 12. In this case, the number of mines should be 12 / 4 = 3 pieces. There should be no more 
    or less than 3.
    * Having 2-dimensional arrays in string data type will make your work easier. You must define an array to hold 
    the locations of the mines, and a separate array to display to the player.
    * You should indicate the unopened boxes on the map shown to the player with the "-" symbol.
    * Mines must be indicated with the symbol "*". Do not use different symbols.
    * The user must select a point on the matrix and be asked to enter row and column values ​​for point selection.
    * It should be checked whether the selected point is within the boundaries of the array, and if the condition 
    is not met, a warning text should be printed on the console and the user should be asked for a new coordinate 
    again.
    * When a previously entered coordinate is entered, a warning should be displayed to the user saying "this 
    coordinate has been selected before, enter another coordinate" and a new entry should be made.
    * If there is a mine at the point where the user enters, he must lose the game. The message should be displayed 
    on the console accordingly.
    * If there are no mines at the point the user enters, all neighboring locations around the point should be looked 
    at (right, left, above, below, upper left diagonally, upper right diagonally, lower right diagonally, lower left 
    diagonally) and the sum of the number of mines at these neighboring points should be written to the coordinate 
    entered by the user. . If there is no mine touching the point, the value "0" should be written. Do not use different 
    values ​​and symbols.
    * If the user opens all the points without stepping on any mines, he must win the game. In this case too, the 
    appropriate message should be displayed on the console.

Losing Scenario

Location of mines
* - - 
- - * 
- - - 
===========================
Welcome to the minefield ! 
- - - 
- - - 
- - - 
Enter row    : 0
Enter colomn : 1 
===========================
- 2 - 
- - - 
- - - 
Enter row     : 2
Enter colomn  : 0 
===========================
- 2 - 
- - - 
0 - - 
Enter row     : 0
Enter colomn  : 2 
===========================
- 2 1 
- - - 
0 - - 
Enter row     : 1
Enter colomn  : 0 
===========================
- 2 1 
1 - - 
0 - - 
Enter row     : 2
Enter colomn  : 1 
===========================
- 2 1 
1 - - 
0 1 - 
Enter row     : 2
Enter colomn  : 2 
===========================
- 2 1 
1 - - 
0 1 1 
Enter row     : 1
Enter colomn  : 2
Game Over!! 
===========================

Winning Scenario

Location of mines
- - - 
- * - 
- * - 
===========================
Welcome to the minefield ! 
- - - 
- - - 
- - - 
Enter row     : 0
Enter colomn  : 0 
===========================
1 - - 
- - - 
- - - 
Enter row     : 0
Enter colomn  : 1 
===========================
1 1 - 
- - - 
- - - 
Enter row     : 0
Enter colomn  : 2 
===========================
1 1 1 
- - - 
- - - 
Enter row     : 1
Enter colomn  : 0 
===========================
1 1 1 
2 - - 
- - - 
Enter row     : 2
Enter colomn  : 0 
===========================
1 1 1 
2 - - 
2 - - 
Enter row     : 1
Enter colomn  : 2 
===========================
1 1 1 
2 - 2 
2 - - 
Enter row     : 2
Enter colomn  : 2
You won the game !
1 1 1 
2 - 2 
2 - 2 
===========================

**/

import java.util.Random;
import java.util.Scanner;

public class Project53 {

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
        input.close();
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
        
        displayTable();
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter row    : ");
            int x = input.nextInt();
            System.out.print("Enter colomn : ");
            int y = input.nextInt();
    
            if (x < this.ROW && y < this.COLOMN) {
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
                        System.out.println("===========================");
                        changePlayTable();
                    }
                }
                System.out.println("===========================");
            } else {
                System.out.println("Please enter a site inside the table!");
                changePlayTable();
            }
            input.close();
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

