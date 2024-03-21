package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_02_ControlFlow;

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
