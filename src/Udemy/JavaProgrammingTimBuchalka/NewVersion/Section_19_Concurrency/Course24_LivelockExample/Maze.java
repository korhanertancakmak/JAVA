package CourseCodes.NewSections.Section_19_Concurrency.Course24_LivelockExample;

import java.util.Arrays;

//Part-2
/**
        I'll set up a static constant, maze size. Since this is a constant, I'll make it upper case. This maze will have
 a grid of cells. I'll set this up as a two dimensional array of cells, so my grid will be 4 by 4. Next, I'll add the
 constructor. This will be a no args constructor. So just public Maze here, In this constructor, I'll initialize all the
 values in the grid, to an empty string. To do this, I'll loop through each row in my 2 d array. And fill each cell in
 that array with an empty string, using Arrays.fill. Next, I'll add a method that gets the nextLocation, for someone in
 the maze.
 **/
//End-Part-2

public class Maze {

    public static final int MAZE_SIZE = 4;
    private final String[][] cells = new String[MAZE_SIZE][MAZE_SIZE];

    public Maze() {
        for (var row : cells) {
            Arrays.fill(row, "");
        }
    }

    public int[] getNextLocation(int[] lastSpot) {

        int[] nextSpot = new int[2];
        nextSpot[1] = (lastSpot[1] == Maze.MAZE_SIZE - 1) ? 0 : lastSpot[1] + 1;
        nextSpot[0] = lastSpot[0];
        if (nextSpot[1] == 0) {
            nextSpot[0] = (lastSpot[0] == Maze.MAZE_SIZE - 1) ? 0 : lastSpot[0] + 1;
        }
        return nextSpot;
    }

//Part-3
/**
        You might imagine some algorithm here, that's dependent on your maze configuration. In this case, I've made it
 simple, and I'll just get the next cell in the grid, traversing over each cell and row, one at a time. This method will
 take a cell location, so row and column coordinate pair, and I'll put that in an int array. This will return the next
 location as the same, an int array for the new coordinates. I'll initialize the next location, which I'll call next spot,
 to a new array of ints. If it's the last cell, then the player will be moved to the first cell. The row coordinate will
 be the same, unless the cell was reset back to zero. If the row coordinate is zero, and the last spot was the last cell,
 then the position goes back to zero. I'll return the next spot from this method. Next, I'll add a method to move a person
 to another location.
**/
//End-Part-3

    public void moveLocation(int locX, int locY, String name) {

        cells[locX][locY] = name.substring(0, 1);
        resetSearchedCells(name);
    }

//Part-4
/**
        This method will take the location, as well as the name. I'll set the cell to the first initial of the person who
 is moving in the maze. When a person moves in the maze, any work done by the partner, searching for that person, would
 need to be reset. I'll code the reset searched cells method next.
**/
//End-Part-4

    public void resetSearchedCells(String name) {

        for (var row : cells) {
            Arrays.asList(row).replaceAll(c -> c.equals("!" + name.charAt(0)) ? "" : c);
        }
    }

//Part-5
/**
        I'll call this method reset searched cells, and it will take a name. I'll loop through the rows. For all cells in
 the row, I'll replace any rows that have been searched, back to un-searched, or an empty string. When a cell has been
 searched, it'll have the not or exclamation mark, and the initial of name, so any cells with these values will get reset.
 Let me show you what this code is doing, on another diagrams.

                                            Starting Position
               _________________________________________________________________________________
               |                   |                   |                   |                   |
     →→→→→→→→→ |                   |                   |                   |                   |  →→→→
     ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
     ↑         |                   |                   |                   |                   |     ↓
     ↑     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
     ↑     ↓   |                   |                   |                   |                   |
     ↑     →→→ |                   |         G         |                   |                   |  →→→→
     ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
     ↑         |                   |                   |                   |                   |     ↓
     ↑     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
     ↑     ↓   |                   |                   |                   |                   |
     ↑     →→→ |                   |                   |                   |                   |  →→→→
     ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
     ↑         |                   |                   |                   |                   |     ↓
     ↑     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
     ↑     ↓   |                   |                   |                   |                   |
     ↑     →→→ |                   |                   |                   |          A        |  →→→→
     ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
     ↑         |___________________|___________________|___________________|___________________|     ↓
     ↑                                                                                               ↓
     ↑←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←

                                         Adam Searching For Grace
               _________________________________________________________________________________
               |                   |                   |                   |                   |
     →→→→→→→→→ |        !G         |         !G        |         A         |                   |  →→→→
     ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
     ↑         |                   |                   |                   |                   |     ↓
     ↑     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
     ↑     ↓   |                   |                   |                   |                   |
     ↑     →→→ |                   |         G         |                   |                   |  →→→→
     ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
     ↑         |                   |                   |                   |                   |     ↓
     ↑     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
     ↑     ↓   |                   |                   |                   |                   |
     ↑     →→→ |                   |                   |                   |                   |  →→→→
     ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
     ↑         |                   |                   |                   |                   |     ↓
     ↑     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
     ↑     ↓   |                   |                   |                   |                   |
     ↑     →→→ |                   |                   |                   |          !G       |  →→→→
     ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
     ↑         |___________________|___________________|___________________|___________________|     ↓
     ↑                                                                                               ↓
     ↑←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←

                                         Grace Searching For Adam
               _________________________________________________________________________________
               |                   |                   |                   |                   |
     →→→→→→→→→ |                   |                   |                   |                   |  →→→→
     ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
     ↑         |                   |                   |                   |                   |     ↓
     ↑     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
     ↑     ↓   |                   |                   |                   |                   |
     ↑     →→→ |                   |        !A         |         !A        |         !A        |  →→→→
     ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
     ↑         |                   |                   |                   |                   |     ↓
     ↑     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
     ↑     ↓   |                   |                   |                   |                   |
     ↑     →→→ |         !A        |         G         |                   |                   |  →→→→
     ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
     ↑         |                   |                   |                   |                   |     ↓
     ↑     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
     ↑     ↓   |                   |                   |                   |                   |
     ↑     →→→ |                   |                   |                   |          A        |  →→→→
     ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
     ↑         |___________________|___________________|___________________|___________________|     ↓
     ↑                                                                                               ↓
     ↑←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←

        On these diagrams, the first grid shows the starting positions of both players. When Adam starts moving, the cell
 he's currently in, can be said to not contain Grace, so I'll put a not G in that cell. In the second grid, Adam has moved
 4 places from his original position at (3 3), and each cell he searched so far, he hasn't found Grace. These are shown
 as gray cells, and have not G in them, meaning Grace wasn't there. If Grace is searching, and Adam is standing still,
 then we have the third grid. Grace started in position (1 1), and has moved to (2 1), and all the cells along her path
 are marked as not A, because Adam wasn't there. This algorithm is only good, if one player stays put. As soon as Grace
 starts moving, all of Adam's efforts will get erased, so all the cells he searched will get reset to un-searched, and
 vice versa. So I've coded the reset search method, before the search method, which I want to add now.
**/
//End-Part-5

    public boolean searchCell(String partner, int[] nextSpot, int[] lastSpot) {

        if (cells[nextSpot[0]][nextSpot[1]].equals(partner.substring(0, 1))) {
            return true;
        }
        cells[lastSpot[0]][lastSpot[1]] = "!" + partner.charAt(0);
        return false;
    }

//Part-6
/**
        This method returns a boolean if the person being searched for is found in the next cell. So the arguments are
 the partner or person being searched for, and the next spot and last spot. I'll check the value in the cell of the next
 spot, the spot this person would move to, and if it has the initial of the partner, then I know I've found the partner
 in this cell. In that case, I'll return true. Otherwise, I'll set the last spot to be the not symbol, + the partner's
 initial. And I'll return false. Finally, I want to override the toString method, so I'll do a Ctrl+O, and select toString.
     **/
//End-Part-6

    @Override
    public String toString() {
        return Arrays.deepToString(cells);
    }

//Part-7
/**
        I'll update this, and instead of returning super.toString, I'll return the cells in the maze. I can do this by
 calling Arrays.deepToString, passing it the 2D cells array. Ok, so that does it for the Maze class. Next, I'll create a
 MazeRunner class, and I'll include psvm there, to get a main method.
 **/
//End-Part-7
}
