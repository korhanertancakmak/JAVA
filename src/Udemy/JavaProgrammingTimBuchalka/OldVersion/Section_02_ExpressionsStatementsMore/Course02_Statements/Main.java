package CourseCodes.OldSections.Section_02_ExpressionsStatementsMore.Course02_Statements;

public class Main {

    public static void main(String[] args) {

        int myVariable = 50;                           // "myVariable = 50" expression, but complete line is a statement

        if (myVariable == 50) {
            System.out.println("Printed");
        }

        myVariable++;
        myVariable--;
        System.out.println("This is a test");

        // Whitespaces and Indenting
        System.out.println("This is" +                 // This is multi-line statement example
                " another" +
                " still more.");

        int anotherVariable = 50;
        myVariable--;
        System.out.println("This is another one");
    }
}
