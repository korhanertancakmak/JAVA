package CourseCodes.OldSections.Section_01_FirstStepsTokens.Course03_PrimitiveTypes;

public class Main {

    public static void main(String[] args) {

        int myValue = 10000;

        // A int = 32 bits.
        int myMinIntValue = Integer.MIN_VALUE;                                  // Integer.MIN_VALUE = -2147483648
        int myMaxIntValue = Integer.MAX_VALUE;                                  // Integer.MAX_VALUE =  2147483647
        System.out.println("Integer Minimum Value = " + myMinIntValue);
        System.out.println("Integer Maximum Value = " + myMaxIntValue);
        System.out.println("Busted MAX value = " + (myMaxIntValue + 1));        // Overflow for Integer.MAX_VALUE
        System.out.println("Busted MIN value = " + (myMinIntValue - 1));        // Underflow for Integer.MIN_VALUE

        int myMaxIntTest = 2_147_483_647;

        // A byte = 8 bits.
        byte myMinByteValue = Byte.MIN_VALUE;                                   // Byte.MIN_VALUE = -128
        byte myMaxByteValue = Byte.MAX_VALUE;                                   // Byte.MAX_VALUE =  127
        System.out.println("Byte Minimum Value = " + myMinByteValue);
        System.out.println("Byte Maximum Value = " + myMaxByteValue);

        // A short = 16 bits.
        short myMinShortValue = Short.MIN_VALUE;                                // Short.MIN_VALUE = -32768
        short myMaxShortValue = Short.MAX_VALUE;                                // Short.MAX_VALUE =  32767
        System.out.println("Short Minimum Value = " + myMinShortValue);
        System.out.println("Short Maximum Value = " + myMaxShortValue);

        // A long = 64 bits.
        long myLongValue = 100;
        long myMinLongValue = Long.MIN_VALUE;                                   // Long.MIN_VALUE = -9223372036854775808
        long myMaxLongValue = Long.MAX_VALUE;                                   // Long.MAX_VALUE =  9223372036854775807
        System.out.println("Long Minimum Value = " + myMinLongValue);
        System.out.println("Long Maximum Value = " + myMaxLongValue);

        long bigLongLiteralValue = 2_147_483_647_234L;
        System.out.println(bigLongLiteralValue);
        short bigShortLiteralValue = 32767;
    }
}
