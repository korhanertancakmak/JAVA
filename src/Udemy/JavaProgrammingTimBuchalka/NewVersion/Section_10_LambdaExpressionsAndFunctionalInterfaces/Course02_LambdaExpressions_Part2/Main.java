package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_10_LambdaExpressionsAndFunctionalInterfaces.Course02_LambdaExpressions_Part2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"));

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("-------");
        //list.forEach((s) -> System.out.println(s));
        //list.forEach((myString) -> System.out.println(myString));
        //list.forEach((String myString) -> System.out.println(myString));
        //list.forEach(myString -> System.out.println(myString));
        list.forEach((var myString) -> System.out.println(myString));


        System.out.println("-------");
        String prefix = "nato";
        //String myString = "enclosing Method's myString";
        list.forEach((var myString) -> {
            char first = myString.charAt(0);
            System.out.println(prefix + " " + myString + " means " + first);
        });
        //prefix = "NATO";
        //System.out.println(myString);

    }
}
