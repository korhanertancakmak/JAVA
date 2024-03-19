package CourseCodes.NewSections.Section_06_ControlFlow;

/*
Course-26

                                           Local Variables And Scope

        In the past couple of courses, we've looked at many of Java's flow statements, the switch statement, the for
    statement, the while statement, as well as the do-while statement. Before than that, we covered the if-else-then
    statement.

        All of these statements may, and probably will, have their own code blocks. We've talked about code blokcs quite
    a bit, but we haven't really talked about variables declared locally to many of these code blocks.

        A local variable is called local, because it is available for use by the code block in which it was declared. It
    is also available to code blocks that are contained by a declaring block.


                    {   // Starts on outer block - for example a method block
                        int firstVariable = 5;
                        int secondVariable = 10;

                        if (firstVariable > 0) {  // flow statement block starts inner block

                            // Inner block code has access to outer block's variable
                            System.out.println(secondVariable);
                        }
                    }

        In the example above, we have two variables declared at the start of a code block. We use the firstVariable in an
    if-then statement expression, and can print the secondVariable inside of the if statement block. The if block is
    contained by the method block, and has access to the method block's variables as this demonstrates. This accessibility
    is also known as "variable scope".

        Scope describes the accessibility of a variable. "In scope" means the variable can be used by an executing block
    or any nested blocks. "Out of scope" means the variable is no longer available.

        Local variables are always in scope, in the block they are declared. They are also in scope for any nested blocks,
    or blocks contained within the outer block. So for example, a method block can declare local variables, and any flow
    statements, contained in the method block, will have access to the method's local variables. This is also true for the
    method parameters. Any code in the method, and any nested blocks, have access to the parameters. There's no limit to
    how deep you can nest code blocks, but generally, for readability and maintainability, consider replacing deeply nested
    blocks with method calls.

        Local variables are always out of scope, for outer blocks, or the containing blocks they are declared in. Let's
    look at an example:

                    public static void aMethod(boolean aBoolean) {

                        if (aBoolean) {
                            int myCounter = 10;              // myCounter is local to this if block
                        }
                        System.out.println(myCounter);       // myCounter is out of scope here
                    }

        In this example, we've declared a local variable called myCounter, inside the if block. This means myCounter will
    be out of scope, for any containing blocks. The method block is the containing block in this instance. You can see that
    the "System.out.println" statement, outside the if block, can't use a variable declared inside the if block. This line
    of code will cause a compiler error. IntelliJ shows this error with, "Cannot resolve symbol 'myCounter'".

        It is considered best practice:

      - To declare and initialize variables in the same place if possible, in a single statement.
      - To declare variables in the narrowest scope possible.

        Declaring variables in the narrowest scope means if your variable is only used in a nested block, declare it there.
    Another example that we've seen, if you're using a variable only in a loop code block, like iteration variable, and
    won't be using it outside of the loop block, declare it in the loop code, or in the for loop initialization block.

                            Local Variables and the For Statement

        In this for statement, as part of the declaration, there is an initialization part, as we've described. In this case,
    we declared a variable, i, that isn't accessible outside of the loop.


                    {       // Starts on outer block - for example a method block

                            for (int i = 1; i <= 5; i++) {     // i declared in for loop declaration
                                System.out.println(i);
                            }

                            System.out.println(i);   // ERROR! i is out of scope.
                    }

    This is because any variables declared in the init section, are local to the loop, meaning they exist and are accessible
    in memory, only while the loop is executing, and only to the loop code block. This is also true for most flow statements,
    for example the if statement.

        However, the switch statement is different from the if-then-else statement blocks. In the switch statement, a variable
    declared in one case label code block, can be accessed in another case label code block, but only if that block is
    after the declaration, and initialization of the variable.

*/
public class Course10_LocalVariablesScope {
}
