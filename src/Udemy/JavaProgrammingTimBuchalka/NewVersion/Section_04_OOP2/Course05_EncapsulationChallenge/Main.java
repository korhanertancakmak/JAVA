package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_04_OOP2.Course05_EncapsulationChallenge;


public class Main {
    public static void main(String[] args) {

        Printer printer = new Printer(50, true);
        printer.getPagesPrinted();

        printer.printPages(50);
        printer.addToner(25);
        printer.getPagesPrinted();
    }
}
