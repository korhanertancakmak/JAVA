package CourseCodes.NewSections.Section_05_ExpressionsStatementsMore;

/*
Course-9

                                          If-then, Else,Control Statements
    Syntax for an "if-else statement" is;

                if (condition) {
                    // Code in block will execute only if
                    // condition is true
                } else {

                    // Code in block will execute only if
                    // condition is false
                }

    Syntax for an "if-else if-else statement" is;

                if (firstCondition) {
                    // Code in block will execute only if
                    // firstCondition is true

                } else if (secondCondition) {

                    // Code in block will execute if firstCondition is false
                    // and secondCondition is true
                    // There is no limit to the number of conditions that can be tested

                } else {

                    // Code in block will execute only if
                    // all conditions are false
                    // The else block must be last but is optional
                }



 */
public class Course03_IfElseStatements {

    public static void main(String[] args) {

        boolean gameOver = true;
        int score = 5000;
        int levelCompleted = 5;
        int bonus = 100;

        if (score < 5000 && score > 1000) {
            System.out.println("Your score was less than 5000 but greater than 1000");
        } else if (score < 1000) {
            System.out.println("Your score was less than 1000");
        } else {
            System.out.println("Got here");
        }

    }
}
