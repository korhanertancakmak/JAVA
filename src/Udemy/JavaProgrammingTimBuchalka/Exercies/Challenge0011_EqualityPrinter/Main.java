package CourseCodes.NewSections.Exercises0To0029.Challenge0011_EqualityPrinter;

//Challenge-Extra-11
/*
                                          Equality Printer

        Write a method printEqual with 3 parameters of type int. The method should not return anything (void). If one of
    the parameters is less than 0, print text "Invalid Value". If all numbers are equal print text "All numbers are equal"
    If all numbers are different print text "All numbers are different". Otherwise, print "Neither all are equal or different".

    EXAMPLES OF INPUT/OUTPUT:
        printEqual(1, 1, 1); should print text All numbers are equal
        printEqual(1, 1, 2); should print text Neither all are equal or different
        printEqual(-1, -1, -1); should print text Invalid Value
        printEqual(1, 2, 3); should print text All numbers are different

    TIP : Be extremely careful about spaces in the printed message.

    NOTES : The solution will not be accepted if there are extra spaces. The method printEqual needs to be defined as public
    static like we have been doing so far in the course. Do not add main method to solution code.
*/

public class Main {

    public static void main(String[] args) {

        printEqual(1, 1, 1);
        printEqual(1, 1, 2);
        printEqual(-1, -1, -1);
        printEqual(1, 2, 3);
        //System.out.println();
    }

    public static void printEqual(int a, int b, int c) {

        if (a < 0 || b < 0 || c < 0) {
            System.out.printf("Invalid Value%n");
        } else if (a == b && b == c) {
            System.out.printf("All numbers are equal%n");
        } else if (a != b && a != c && b != c ) {
            System.out.printf("All numbers are different%n");
        } else {
            System.out.printf("Neither all are equal or different%n");
        }
    }
}

