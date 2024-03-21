package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_02_ControlFlow;


import java.util.Scanner;
public class Course12_ScannerClassReadingData1 {
    public static void main(String[] args) {

        int currentYear = 2023;

        try {
            System.out.println(getInputFromConsole(currentYear));
        } catch (NullPointerException e) {
            System.out.println(getInputFromScanner(currentYear));
        }
    }

    public static String getInputFromConsole(int currentYear) {

        String name = System.console().readLine("Hi, What's your name?");  // ERROR! IDE's disable the console. IntelliJ disabled this to be used!
        System.out.println("Hi " + name + ", Thanks for taking the course!");  // But you can use the "Terminal" on below of the IntelliJ window!

        String dateOfBirth = System.console().readLine("What year were you born?");
        int age = currentYear - Integer.parseInt(dateOfBirth);

        return "So you are " + age + " years old";
    }

    public static String getInputFromScanner(int currentYear) {

        Scanner cs = new Scanner(System.in);

        System.out.println("Hi, What's your name? ");
        String name = cs.nextLine();

        System.out.println("Hi " + name + ", Thanks for taking the course!");

        System.out.println("What year were you born? ");
        String dateOfBirth = cs.nextLine();
        int age = currentYear - Integer.parseInt(dateOfBirth);

        return "So you are " + age + " years old";
    }
}
