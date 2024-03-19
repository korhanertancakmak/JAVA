package CourseCodes.OldSections.Section_01_FirstStepsTokens.Course07_CharBooleanPrimitiveTypes;

public class Main {

    public static void main(String[] args) {

        // char = 2 bytes
        char myChar = 'D';
        char myUnicodeChar = '\u0044';
        System.out.println(myChar);
        System.out.println(myUnicodeChar);
        char myCopyrightChar = '\u00A9';
        System.out.println(myCopyrightChar);

        // boolean = 1 byte
        boolean myTrueBooleanValue = true;
        boolean myFalseBooleanValue = false;
        boolean isCustomerOverTwentyOne = true;
    }
}
