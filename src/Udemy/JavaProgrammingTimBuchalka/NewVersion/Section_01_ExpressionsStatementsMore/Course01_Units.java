package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_01_ExpressionsStatementsMore;

public class Course01_Units {

    public static void main(String[] args) {
        double kilometers = (100 * 1.609344);

        int highScore = 50;

        if (highScore > 25) {
            highScore = 1000 + highScore;       // Here, there are 2 expressions.
                                                // First is "1000 + highScore" and Second is "highScore = first expression"
                                                // So, the assignment can be taken as the second expression.
        }

        int health = 100;

        if ((health < 25) && (highScore > 1000)) {
            highScore = highScore - 1000;
        }
    }
}