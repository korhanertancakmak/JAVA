package CourseCodes.NewSections.Section_06_ControlFlow;

/*
Course-17
                                            Control-Flow Statements

        In this section, we'll be learning about the switch statement, which like the if then else statement, allows us
    to execute different code blocks on different conditions. Also we'll be looking at 3 additional statements, the for
    statment, the while statement, and the do while statement, which are used to repeat code segments based on conditions.

                                           The switch statement

        The syntax for the switch statement is quite a bit different than it is for the if statement.

                            switch(value) {
                                case x:
                                    // code for value == x
                                    break;
                                case y:
                                    // code for value == y
                                    break;
                                default:
                                    // code for value not equal to x or y
                            }

        The case keywords, as shown above, are used to with the switch statement for comparision. So, switch value, case x,
    essentially means, in the case that value equals x, execute this code. If it doesn't, then move on to check the next
    case. The break keyword tells the switch statement to terminate any further checks. It's technically optional, and
    we'll talk about it more shortly. And, as you can probably guess, the default keyword means if none of the above cases
    were true, execute this code.

        Switch Value Types:

    Valid swtich value types are : byte, short, int, char
                                   Byte, Short, Integer, Character
                                   String
                                   enum
    But, importantly, note that the primitive types of boolean, long, float and double, cannot be used. They'll result in
    an error in your code, if you try to use them.

                                        Fall through in switch statement

        Once a switch case label matches the switch variable, no more cases are checked. Any code after the case label where
    there was a match found, will be executed, until a break statement, or the end of the switch statement occurs. Without
    a break statement, execution will continue to fall through any case labels declared below the matching one, and execute
    each case's code.

                                        Enhanced Switch Statement

        Enhancing the switch statement and Java has introduced new syntax for the switch as shown from the old one here;

                                    switch (switchValue) {
                                        case 1:
                                            System.out.println("Value was 1");
                                            break;
                                        case 2:
                                            System.out.println("Value was 2");
                                            break;
                                        case 3: case 4: case 5:
                                            System.out.println("Was a 3, a 4, or a 5");
                                            System.out.println("Actually it was a " + switchValue);
                                            break;
                                        default:
                                            System.out.println("Was not 1, 2, 3, 4 or 5");
                                            break;
                                    }

    to new one here;

                                    switch (switchValue) {
                                        case 1 -> System.out.println("Value was 1");
                                        case 2 -> System.out.println("Value was 2");
                                        case 3, 4, 5 -> {
                                            System.out.println("Was a 3, a 4, or a 5");
                                            System.out.println("Actually it was a " + switchValue);
                                        }
                                        default -> System.out.println("Was not 1, 2, 3, 4 or 5");
                                    }

    When we compare these two statements, the first thing we notice is, that the colon after each case label has been
    replaced with the arrow token as shown here. There are no breaks in the enhanced switch statement, the enhanced statement
    doesn't require them. Fall through, which we examined above, never occurs in the enhanced switch statement. Also, notice
    that it's replaced the multiple case labels we had, case 3, case 4, and case 5, with a comma delimited list of the values.


 */
public class Course01_SwitchControlFlowStatement {
    public static void main(String[] args) {

//        int value = 3;
//        if (value == 1) {
//            System.out.println("Value was 1");
//        } else if (value == 2) {
//            System.out.println("Value was 2);
//        } else {
//            System.out.println("Was not 1 or 2");
//        }

        int switchValue = 3;

        switch (switchValue) {
            case 1:
                System.out.println("Value was 1");
                break;
            case 2:
                System.out.println("Value was 2");
                break;
            case 3: case 4: case 5:
                System.out.println("Was a 3, a 4, or a 5");
                System.out.println("Actually it was a " + switchValue);
                break;
            default:
                System.out.println("Was not 1, 2, 3, 4 or 5");
                break;
        }
        // More code here
    }
}
