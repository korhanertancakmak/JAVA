package CourseCodes.NewSections.Section_05_ExpressionsStatementsMore;

/*
Course-11

                                          The Method

        Java's description of the method is:

    A method declares executable code that can be invoked(called), passing a fixed number of values as arguments.

        Benefits of the Method

    * A method is a way of reducing code duplication.
    * A method can be executed many times with potentially different results, by passing data to the method in the form
    of arguments.

        Structure of the Method

    One of the simplest ways to declare a method is,

                public static void methodName() {
                    // method statements form the method body
                }

  * This method has a name, but takes no data in, and returns no data from the method(which is what the special word
    "void" means in this declaration), since there is no parameters in "()" comes after the methodName.
  * It's accessible to the outside world because it uses the keyword "public".
  * It can be called directly using the Class name, and we'll have more on this later, because it uses the keyword static.
  * methodName can be any valid identifier in Java, but we'll be using the lower Camel case style to name methods.

    So let's try to create a method called "calculateScore" other than "main" method below:
  * You cannot put a named method within another method, they need to be outside of it.
  * It has to be within the class' code block, meaning you can't have a method exist on its own, outside of a class'body.
  * We'll not going to get too concerned about the "public" and "static" keywords, we'll be talking about these a lot in
  the future.

    After creating a method, let's execute the method as a statement:
  * To execute a method, we can write a statement in code, which we say is calling, or invoking, the method.
  * For a simple method like calculateScore, we just use the name of the method, where we want it to be executed, followed
  by  parenthesses, and a semi-colon to complete the statement.

                calculateScore();

  * Where we previously had empty parenthesses after the method name, we now have method parameters in the declaration.

                public static void methodName(p1type p1, p2type p2, {more}) {

                    // method statements form the method body

                }

  * Parameters and arguments are terms that are often used interchangeably by developers.
  * But technically, a parameter is the definition as shown in the method declaration, and the argument will be the value
  that's passed to the method when we call it.

  * So what we need to do is define, in the method declaration, what parameters, or what information, we need to send
  through to it, from our main method, where we're actually calling calculateScore.
  * You define a parameter like a local variable, first by declaring a data type, and then give it a name of your choice.
  So let's add our four parameters, and then we'll talk about it some more:

                public static void methodName(boolean gameOver, int score, int levelCompleted, int bonus) {

                    // method statements form the method body

                }

  * You'll notice i didn't put any values in "()", I've only put the data type, and the name of the variable.
  * To execute a method that's defined with parameters, you have to pass variables, values or expressions that match the
  type, order and number of the parameters declared.
  * In the calculateScore example, we declared the method with four parameters, the first a boolean, and the other three
  of int data types.
  * So we have to pass first a boolean, and then 3 int values as shown in this statement:

                calculateScore(true, 800, 5, 100);

  * We can't pass the boolean type in any place, other than as the first argument, without an error. For examples;

                calculateScore(800, 5, 100, true);

  * And you can't pass only a partial set of parameters as shown here. This statement, too, would cause an error.

                calculateScore(true, 800);
 */
public class Course05_Methods1 {

    public static void main(String[] args) {

        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        int finalScore = score;

        calculateScore(true, 800, levelCompleted, bonus);

        score = 10000;
        levelCompleted = 8;
        bonus = 200;

        finalScore = score;

        if (gameOver) {
            finalScore += (levelCompleted * bonus);
            System.out.println("Your final score was " + finalScore);
        }
    }

    public static void calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {

        int finalScore = score;

        if (gameOver) {
            finalScore += (levelCompleted * bonus);
            finalScore += 1000;
            System.out.println("Your final score was " + finalScore);
        }
    }
}
