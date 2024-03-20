package CourseCodes.NewSections.Section_05_ExpressionsStatementsMore;

/*
Course-8

                                                Java Statements
    Java statements can be assignment expressions, like the one below, where we're assigning the value 50 to the variable
    myVariable.

    * Also we can do that;

    myVariable++;
    myVariable--;

    statements. ";" makes each of these a statement. Without the semi-colon, myVariable++ for example, is really an
    expression.

    * The other thing to keep in mind with statements is, they don't have to be on the one line. For example below;

    System.out.println("This is" +
                " another" +
                " still more.");

    So looking at the first part of System.out.println, there's no semicolon. This means the statement hasn't really
    ended, and somewhat similar to what we saw in JShell, we can continue on the next line here in IntelliJ.

    So you'll see that typed in Java code, and often it's a good idea to do that, to break up the parts of your statement,
    so it makes more sense, so it's more readable.

                                               Whitespace
    It is any extra spacing, horizontally or vertically, placed around Java source code. It's usually added for human
    readability purposes. In java, all these extra spaces are ignored.

      So Java treats code like this:

                int anotherVariable = 50;myVariable--; System.out.println("myVariable = " +myVariable);

    The same as code like this:

                int anotherVariable = 50;
                myVariable--;
                System.out.println("myVariable = " +myVariable);


 */
public class Course02_Statements {

    public static void main(String[] args) {

        int myVariable = 50;

        myVariable++;
        myVariable--;

        System.out.println("This is a test");

        System.out.println("This is" +
                " another" +
                " still more.");
    }
}
