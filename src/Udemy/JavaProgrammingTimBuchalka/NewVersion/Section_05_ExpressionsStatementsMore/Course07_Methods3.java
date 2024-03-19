package CourseCodes.NewSections.Section_05_ExpressionsStatementsMore;

/*
Course-13

                                Method Structure with parameters and return type

    * We can get our method to do some calculations much like doing before. But we can send the result of that calculation
    back to the code that called it.

    * We do this by declaring a data type before the method name, much like we do for a variable, declaring a data type
    before the variable name.

    * Method return type is a declared data type for the data that will be returned from the method. So similar to declaring
    a variable with a type, we can declare a method to have a type.

                public static dataType methodName(p1type p1, p2type p2, p3type p3, {more}) {

                    // method statements

                    return value;
                }

    * Thic declared type is placed just before the method name. In addition, a return statement is required in the code
    block, as shown above, which returns the result from the method.

    * In previous examples, we declared the type to be void, which has the special meaning that no data would be returned
    from the method. An example of a method declaration with a return type is shown:

                public static int calculateMyAge(int dateOfBirth) {

                    return (2023 - dateOfBirth);
                }

    * This method will return an integer when it finishes executing succesfully. Being able to return a value from a
    method, lets the calling code have a two way conversation with the method code. So we can calculate something, and
    send that value back to the code, that called the method in the first place.

        Return Statement
    * Java states that a return statement returns control to the invoker of a method.
    * The most common usage of the return statement, is to return a value back from a method.
    * In a method that doesn't return anything, in other words a method declared with void as the return type, a return
    statement is not required. But in methods that do return data, a return statement with a value is required.

    * If a method declares a return type, meaning it's not void, then a return type is required at any exit point from
    the method block. Consider the method block shown here:

                public static boolean isTooYoung(int age) {
                    if (age > 21) {
                        return true;
                    }
                }

    * This code won't compile, because there's no return statement, to cover the cases where age is greater than or equal
    to 21. So in the case of using return statement in nested code blocks in a method, all possible code segments must
    result in a value being returned. The following code demonstrates one way to do this:

                public static boolean isTooYoung(int age) {
                    if (age > 21) {
                        return true;
                    }
                    return false;
                }


    * One common practice is to declare a default return value at the start of a method, and only have a single return
    statement from a method, returning that variable, as shown in this example method:

                public static boolean isTooYoung(int age) {
                    boolean result = false;
                    if (age > 21) {
                        result = true;
                    }
                    return result;
                }

    * The return statement can return with no value from a method, which is declared with a "void" return type. In this
    case, the return statement is optional, but it may be used to terminate execution of the method, at some earlier point
    than the end of the method block, as we show here:

                public static void methodDoesSomething(int age) {
                    if (age > 21) {
                        return;
                    }
                    // Do more stuff here
                }

        The Method Signature
    * A method is uniquely defined in a class by its name, and the number and type of parameters that are declared for it.
    This is called the method signature.
    * You can have multiple methods with the same method name, as long as the method signature (meaning the paraters
    declared) are different. This will become important later in this section, when we cover overloaded methods.

 */
public class Course07_Methods3 {

    public static void main(String[] args) {

        int highScore = calculateScore(true, 800, 5, 100);

        System.out.println("The highscore is " + highScore);

        System.out.println("The next highScore is " +
                calculateScore(true, 10000, 8, 200));
    }

    public static int calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {

        int finalScore = score;

        if (gameOver) {
            finalScore += (levelCompleted * bonus);
            finalScore += 1000;
        }

        return finalScore;
    }
}
