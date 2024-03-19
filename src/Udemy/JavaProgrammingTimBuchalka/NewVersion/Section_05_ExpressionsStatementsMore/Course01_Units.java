package CourseCodes.NewSections.Section_05_ExpressionsStatementsMore;/*
Course-7

                                                Java's Code Units
    Writing code is similar to writing a document. It consists of special hierarchical units, which together form a
    whole. These are:

    The Expressions - An expression computes to a single value.
    The Statement   - Statements are stand alone units of work.
    Code Block      - A code block is a set of zero, one, or more statements, usually grouped together in some way to
    achieve a single goal.
 */

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