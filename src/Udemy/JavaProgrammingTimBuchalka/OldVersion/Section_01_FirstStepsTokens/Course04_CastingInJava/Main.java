package CourseCodes.OldSections.Section_01_FirstStepsTokens.Course04_CastingInJava;

public class Main {

    public static void main(String[] args) {

        int myMinIntValue = Integer.MIN_VALUE;
        byte myMinByteValue = Byte.MIN_VALUE;
        short myMinShortValue = Short.MIN_VALUE;

        int myTotal = (myMinIntValue / 2);

        byte myNewByteValue = (byte) (myMinByteValue / 2);
        short myNewShortValue = (short) (myMinShortValue / 2);
    }
}
