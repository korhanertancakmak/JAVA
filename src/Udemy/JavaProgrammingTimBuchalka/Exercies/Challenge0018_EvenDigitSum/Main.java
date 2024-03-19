package CourseCodes.NewSections.Exercises0To0029.Challenge0018_EvenDigitSum;

//Challenge-Extra-18
/*
                                          Even Digit Sum

        Write a method named getEvenDigitSum with one parameter of type int called number. The method should return the
    sum of the even digits within the number. If the number is negative, the method should return -1 to indicate an invalid
    value.

    EXAMPLE INPUT/OUTPUT:
        getEvenDigitSum(123456789); → should return 20 since 2 + 4 + 6 + 8 = 20
        getEvenDigitSum(252); → should return 4 since 2 + 2 = 4
        getEvenDigitSum(-22); → should return -1 since the number is negative

    NOTE: The method getEvenDigitSum should be defined as public static like we have been doing so far in the course.
*/

public class Main {

    public static void main(String[] args) {

        //System.out.println();
        System.out.println(getEvenDigitSum(123456789));
        System.out.println(getEvenDigitSum(252));
        System.out.println(getEvenDigitSum(-22));
    }

    public static int getEvenDigitSum(int number) {

        if (number < 0) {
            return -1;
        }
        int length = Integer.toString(number).length();
        int remainder = number / 10, lastDigit = number % 10;
        int sum = 0;
        for (int i = 0; i < length; i++) {

            if (lastDigit % 2 == 0) {
                sum += lastDigit;
            }
            lastDigit = remainder % 10;
            remainder = remainder / 10;
        }

        return sum;
    }
}

