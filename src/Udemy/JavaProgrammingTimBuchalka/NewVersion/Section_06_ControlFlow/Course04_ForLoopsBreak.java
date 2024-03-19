package CourseCodes.NewSections.Section_06_ControlFlow;

/*
Course-20

                                                     Looping

        Looping lets us execute the code a multiple number of times. Java supports several statements for looping, or
    executing code repetitively.

  * for statements      : The for loop is more complex to set up but is commonly used when you are iterating over a set
                         of values.
  * while statements    : The while loop executes until a specified condition becomes false.
  * do while stataments : The do while loop always execute at least one regardless of whether the loop condition is true
                         or false, and then continue looping until a specified condition becomes false.

                                            The For Statement

        The for statement is often referred to as the for loop. It repeatedly loops something until a condition is satisfied.
    When we look at its syntax,


                                    for (init; expression; increment) {
                                        // block of statement
                                    }

    there are 3 parts of the basic for statement's declaration. These are declared in parenthesis, after the "for" keyword,
    and are separated by semi-colon(;). These parts are all optional and consist of the following:

        * The initialization section declares or sets state, usually declaring and initializing a loop variable, before
        the loop begins processing.
        * The expression section, once it becomes false, will end the loop processing.
        * The increment section is executed after the expression is tested, and is generally the place where the loop
        variable is incremented.

                                            The Break Statement

        A break statement transfers control out of an enclosing statement. We've seen the break statement in the switch
    statement, but it can also be used in a loop.


*/
public class Course04_ForLoopsBreak {
    public static void main(String[] args) {

        for (int counter = 1; counter <= 5; counter++) {
            System.out.println(counter);
        }

        for (double rate = 2.0; rate <= 5.0; rate++) {
            double interestAmount = calculateInterest(10000, rate);
            System.out.println("10,000 at " + rate + "% interest = " + interestAmount);
        }

        for (double i = 7.5; i <= 10.0; i += 0.25) {
            double interestAmount = calculateInterest(100.00, i);
            if (interestAmount > 8.5) {
                break;
            }
            System.out.println("$100.00 at " + i + "% interest = $" + interestAmount);
        }
    }

    public static double calculateInterest(double amount, double interestRate) {

        return (amount * (interestRate / 100));
    }
}
