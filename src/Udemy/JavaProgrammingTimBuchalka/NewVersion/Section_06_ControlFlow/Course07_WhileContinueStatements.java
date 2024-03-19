package CourseCodes.NewSections.Section_06_ControlFlow;

/*
Course-23

                                         The while and do while statements

        What if you want to loop until some condition is met, that's not associated with a known range of values? Java
    has 2 types of while loops:

      * while    : Continues executing code block until the loop expression becomes false.
      * do-while : Execute the code block once, then continue executing until the loop condition becomes false.

        So, we've also stated several times, that the for loop has three declaration parts in the parentheses, separated
    by semicolons. But looking at the while loop in comparision:

        The for statement                    |      The while statement         |       The do-while statement
                                             |                                  |
        for (init; expression; increment)    |      while (expression {         |       do {
            // block of statements           |           // block of statement  |            // block of statements
        }                                    |      }                           |       } while (expression);

                                          The Continue Statement

        There's another statement that's important to all of these loops, and that's the continue statement. The continue
    statement, in its simplest form, will stop executing the current iteration of a block of code in a loop, and start a
    new iteration. In some cases, you'll have a loop where the majority of iterations in your loop, meet the criteria, for
    which you want to do a lot of work. But you might have a couple of exceptions.

        So rather than use a big if-then-else statement, you just want to ignore a few iterations, and skip the code that
    would be normally executed, but keep the loop going.
*/
public class Course07_WhileContinueStatements {
    public static void main(String[] args) {

        // basic for statement example
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }

        // while statement example
        int j = 1;
        while (j <= 5) {
            System.out.println(j);
            j++;
        }

        // while with break statement example
        int k = 1;
        boolean isReady = false;
        while (isReady) {
            if (k > 5) {
                break;
            }
            System.out.println(k);
            k++;
        }

        // do-while statement example
        int m = 1;
        do {
            if (m > 5) {
                break;
            }
            System.out.println(m);
            m++;
        } while (isReady);

        // while with continue statement example
        int number = 0;
        while (number < 50) {
            number += 5;
            if (number % 25 == 0) {
                continue;            // it doesn't process the number 25 and its multiple orders.
            }
            System.out.println(number + "_");
        }
    }
}
