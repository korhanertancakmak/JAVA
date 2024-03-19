package CourseCodes.NewSections.Section_05_ExpressionsStatementsMore;

/*
Course-10

                                          if then else Challenge

    * Insert a code segment after the code we have just reviewed:

        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        int finalScore = score;

        if (gameOver) {
            finalScore += (levelCompleted * bonus);
            System.out.println("Your final score was " + finalScore);
        }

    * Set the existing score variable to 10000.
    * Set the existing levelCompleted variable to 8.
    * Set the existing bonus variable to 200.
    * Use the same if condition (meaning if gameOver is true) you want to perform the same calculation, and print out
    the value of the finalScore variable.
 */
public class Course04_IfThenElseChallenge {

    public static void main(String[] args) {

//        // Challenge Part
//        boolean newGameOver = true;
//        int newScore = 10000;
//        int newLevelCompleted = 8;
//        int newBonus = 200;
//
//        int newFinalScore = score;
//
//        if (newGameOver) {
//            newFinalScore += (newLevelCompleted * newBonus);
//            System.out.println("Your final score was " + newFinalScore);

        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        int finalScore = score;

        if (gameOver) {
            finalScore += (levelCompleted * bonus);
            System.out.println("Your final score was " + finalScore);
        }

    }
}
