package CourseCodes.NewSections.Exercises0To0029.Challenge0013_NumberInWord;

//Challenge-Extra-13
/*
                                          Number In Word

        Write a method called printNumberInWord. The method has one parameter number which is the whole number. The method
    needs to print "ZERO", "ONE", "TWO", ... "NINE", "OTHER" if the int parameter number is 0, 1, 2, .... 9 or other for
    any other number including negative numbers. You can use if-else statement or switch statement whatever is easier for
    you.

    NOTE : Method printNumberInWord needs to be public static for now, we are only using static methods.
*/

public class Main {

    public static void main(String[] args) {

        //System.out.println();
        printNumberInWord(100);
        printNumberInWord(3);
        printNumberInWord(5);
        printNumberInWord(0);
    }

    public static void printNumberInWord(int number) {

        String numberString;
        switch (number) {
            case 0 -> numberString = "ZERO";
            case 1 -> numberString = "ONE";
            case 2 -> numberString = "TWO";
            case 3 -> numberString = "THREE";
            case 4 -> numberString = "FOUR";
            case 5 -> numberString = "FIVE";
            case 6 -> numberString = "SIX";
            case 7 -> numberString = "SEVEN";
            case 8 -> numberString = "EIGHT";
            case 9 -> numberString = "NINE";
            default -> numberString = "OTHER";
        }

        System.out.println(numberString);
    }
}

