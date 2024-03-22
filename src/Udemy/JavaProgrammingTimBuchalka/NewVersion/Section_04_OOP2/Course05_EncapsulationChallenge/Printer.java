package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_04_OOP2.Course05_EncapsulationChallenge;

public class Printer {

    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;

    public Printer(int tonerLevel,  boolean duplex) {
        this.tonerLevel = (tonerLevel < 0 && tonerLevel > 100) ? -1 : tonerLevel;
        this.duplex = duplex;
        this.pagesPrinted = 0;
    }

    public int getPagesPrinted() {
        System.out.println("Pages are printed = " + pagesPrinted);
        return pagesPrinted;
    }

    public int addToner(int tonerAmount) {
        tonerLevel = (tonerLevel + tonerAmount) > 100 ? 100 : (tonerLevel + tonerAmount);
        System.out.println("New toner level is = %" + tonerLevel);
        return tonerLevel;
    }

    public int printPages(int pages) {

        if (duplex) {
            System.out.println("This is a duplex printer");
            pagesPrinted += pages / 2 + pages % 2;
        } else {
            pagesPrinted += pages;
        }

        System.out.printf("%d pages printed!", pagesPrinted);
        System.out.println();
        return pagesPrinted;
    }
}
