package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_10_LambdaExpressionsAndFunctionalInterfaces.Course06_LambdaMiniChallenges;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;


public class Main {

    public static void main(String[] args) {


        Consumer<String> printWords = new Consumer<String>() {

            @Override
            public void accept(String sentence) {
                String[] parts = sentence.split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };

        Consumer<String> printWordsLambda = sentence -> {
            String[] parts = sentence.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };


        printWords.accept("Let's split this up into an array");
        printWordsLambda.accept("Let's split this up into an array");


        Consumer<String> printWordsForEach = sentence -> {
            String[] parts = sentence.split(" ");
            Arrays.asList(parts).forEach(s -> System.out.println(s));
        };

        printWordsForEach.accept("Let's split this up into an array");

        Consumer<String> printWordsConcise = sentence -> {
            Arrays.asList(sentence.split(" ")).forEach(s -> System.out.println(s));
        };

        printWordsConcise.accept("Let's split this up into an array");


/*
        Function<String, String> everySecondChar = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };
*/

        UnaryOperator<String> everySecondChar = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        System.out.println(everySecondChar.apply("1234567890"));


        String result = everySecondCharacter(everySecondChar, "1234567890");
        System.out.println(result);


        Supplier<String> iLoveJava = () -> "I love Java!";
        Supplier<String> iLoveJava2 = () -> {return "I love Java!";};

//Part-14
/*
                                                Mini Challenge 7

        As with the Function example, the Supplier lambda won't do anything until we use it. Remember, lambdas represent
    deferred execution of snippets of code. Use this Supplier to assign a String, "I love Java", to a variable called
    supplierResult. Print that variable to the console.

        I'll just call println passing it "ILoveJava", and then ".get". I'll just run that to make sure it works. For good
    measure, I'll copy and paste that last line, and change ILoveJava to ILoveJava2 there. Running that:

            ---(same)
            I love Java!
            I love Java!

    I get the second statement from my second string supplier, "I love Java" printed again. Ok, that's it for the mini
    challenges.
*/
//End-Part-14

        System.out.println(iLoveJava.get());
        System.out.println(iLoveJava2.get());
    }


    public static String everySecondChar(String source) {

        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }

    public static String everySecondCharacter(Function<String, String> func, String source) {
        return func.apply(source);
    }

}
