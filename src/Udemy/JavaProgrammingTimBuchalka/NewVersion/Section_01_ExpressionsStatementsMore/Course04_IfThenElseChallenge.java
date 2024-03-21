package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_01_ExpressionsStatementsMore;


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
