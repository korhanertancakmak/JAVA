package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_01_ExpressionsStatementsMore;

public class Course07_Methods3 {

    public static void main(String[] args) {

        int highScore = calculateScore(true, 800, 5, 100);

        System.out.println("The highscore is " + highScore);

        System.out.println("The next highScore is " +
                calculateScore(true, 10000, 8, 200));
    }

    public static int calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {

        int finalScore = score;

        if (gameOver) {
            finalScore += (levelCompleted * bonus);
            finalScore += 1000;
        }

        return finalScore;
    }
}
