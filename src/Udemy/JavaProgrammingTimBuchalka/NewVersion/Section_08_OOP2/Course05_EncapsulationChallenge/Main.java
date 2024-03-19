package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_OOP2.Course05_EncapsulationChallenge;

/*
Course-56

                                              Encapsulation Challenge

        This is the challenge to create a class, and to demonstrate proper encapsulation techniques, such as you've learnt
    in the previous course. In this challenge, you need to create a class named Printer.

                                         ________________________________________
                                         |Printer =>                            |
                                         |        tonerLevel: int               |
                                         |        pagesPrinted: int             |
                                         |        duplex: boolean               |
                                         |--------------------------------------|
                                         |        addToner(int tonerAmount): int|
                                         |        printPages(int pages): int    |
                                         |______________________________________|

    It's going to simulate a real computer printer, a laser printer. The fields on this class are going to be:
  - tonerLevel      : which is the percentage of how much toner level is left.
  - pagesPrinted    : which is the count of total pages printed by the Printer.
  - duplex          : which is a boolean indicator. If true, it can print on 2 slides of a single sheet of paper.

    You'll to initialize your printer, by specifying a starting toner amount, and whether the printer is duplex or not.
    On the Printer class, you want to create 2 methods, which the calling code should be able to access. These methods
    are:
  - addToner()      : which takes a tonerAmount argument. tonerAmount is added to the tonerLevel field. The tonerLevel
                    should never exceed 100%, or ever get below 0%. If the amount being added makes the level fall outside
                    that range, return a "-1" from the method, otherwise return the actual toner level.
  - printPages()    : which should take pages to be printed as the argument. I should determine how many sheets of paper
                    will be printed based on the duplex value, and return this sheet number from the method. The sheet
                    number should also be added to the pagesPrinted variable. If it's a duplex printer, print a message
                    that it's a duplex printer.

    In this challenge, you'll want to demonstrate proper encapsulation techniques with this class.

*/


public class Main {
    public static void main(String[] args) {

        Printer printer = new Printer(50, true);
        printer.getPagesPrinted();

        printer.printPages(50);
        printer.addToner(25);
        printer.getPagesPrinted();
    }
}
