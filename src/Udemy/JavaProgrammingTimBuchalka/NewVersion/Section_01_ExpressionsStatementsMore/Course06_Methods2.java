package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_01_ExpressionsStatementsMore;


public class Course06_Methods2 {

    public static void main(String[] args) {

        calculateScore(true, 800, 5, 100);

        calculateScore(true, 10000, 8, 200);
    }

    public static void calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {

        int finalScore = score;

        if (gameOver) {
            finalScore += (levelCompleted * bonus);
            finalScore += 1000;
            System.out.println("Your final score was " + finalScore);
        }
    }
}
