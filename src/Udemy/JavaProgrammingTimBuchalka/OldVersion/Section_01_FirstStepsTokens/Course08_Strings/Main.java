package CourseCodes.OldSections.Section_01_FirstStepsTokens.Course08_Strings;

public class Main {

    public static void main(String[] args) {
        // 8 Primitive Data Types we got : byte, short, int, long, float, double, char, boolean

        // String is not a "regular" data type but it's a IMMUTABLE(you cannot change it once it's created) class!
        String myString = "This is a string";
        System.out.println("myString is equal to " + myString);
        myString = myString + ", and this is more.";
        System.out.println("myString is equal to " + myString);
        myString = myString + " \u00A9 2019";
        System.out.println("myString is equal to " + myString);

        // no numerical calculation will be done here! (Concatenation example)
        String numberString = "250.55";
        numberString = numberString + "49.95";
        System.out.println(numberString);

        // second concatenation example
        String lastString = "10";
        int myInt = 50;
        lastString = lastString + myInt;
        System.out.println("LastString is equal to " + lastString);

        // third concatenation example
        double doubleNumber = 120.47d;
        lastString = lastString + doubleNumber;
        System.out.println("LastString is equal to " + lastString);


    }
}
