package CourseCodes.NewSections.Section_05_ExpressionsStatementsMore;

/*
Course-12

                                          The Method

    * We can delete the code part coming from Course-11 which is the part right after first calculateScore, and adding
    the second method.

    * But notice this time, i'm actually passing the values directly, I'm not using variables or anything like that.

    * This lets us delete all of the extra code, that was assigning new values to our variables.

    * Now, the other thing we could do here is, instead of mixing literal values and variables in the arguments we're
    passing, we could just pass all literal values in that first call.

    * This lets us make this even more efficient, by first deleting these variables, because of course we don't need
    them anymore. Deleted parts:

                boolean gameOver = true;
                int score = 800;
                int levelCompleted = 5;
                int bonus = 100;
                int finalScore = score;

    * We'll pass values directly to the method call, so we'll update that first call to the calculate Score method:

                calculateScore(true, 800, 5, 100);

    * We've literally only got two lines, that actually sends the information needed, to the method.

    * You can notice that now, we cleaned up a lof of code. There was a lot of extra code we had in our main method, but
    now we're down to just two lines.
 */
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
