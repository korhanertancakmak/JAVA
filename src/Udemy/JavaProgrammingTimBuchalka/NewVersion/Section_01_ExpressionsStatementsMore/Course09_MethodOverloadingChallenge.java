package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_01_ExpressionsStatementsMore;

/*
Course-15

                                          Overloaded Method Challenge

        Create two methods with the same name: convertToCentimeters.

    * The first method has one parameter of type int, which represents the entire height in inches. You'll convert inches
    to centimeters, in this method, and pass back the number of centimeters, as a double.

    * The second method has two parameters of type int, one to represent height in feet, and one to represent the remaining
    height in inches. So if a person is 5 foot, 8 inches, the values 5 for feet and 8 for inches would be passed to this
    method. This method will convert feet and inches to just inches, then call the first method, to get the number of
    centimeters, also returning the value as a double.

    * Both methods should return a real number or decimal value for total height in centimeters.

    * Call both methods, and print out the results.

    The conversion formula from inches to centimeters is 1 inch = 2.54 cm. Also, remember one foot = 12 inches.

                                            Some examples :

                     6 feet to Centimeters => you should get
                     6 ft = 182.8800 cm


*/
public class Course09_MethodOverloadingChallenge {
    public static void main(String[] args) {

        System.out.println("5ft, 8in = " + convertToCentimeters(5,8) + "cm");
        System.out.println("68in = " + convertToCentimeters(68) + "cm");
    }

    public static double convertToCentimeters(int inches) {
        return inches * 2.54d;
    }

    public static double convertToCentimeters(int feet, int inches) {
        return convertToCentimeters(inches + (feet * 12));
    }
}

