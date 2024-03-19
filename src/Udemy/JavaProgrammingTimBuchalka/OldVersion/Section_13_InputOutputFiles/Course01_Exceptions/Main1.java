package CourseCodes.OldSections.Section_13_InputOutputFiles.Course01_Exceptions;

public class Main1 {

    public static void main(String[] args) {

        int x = 98;
        int y = 0;
        System.out.println(divideLBYL(x, y));
        System.out.println(divideEAFP(x, y));
        System.out.println(divide(x, y));
    }

    private static int divideLBYL(int x, int y) {
        if(y != 0) {
            return x / y;
        } else {
            return 0;
        }
    }

    private static int divideEAFP(int x, int y) {
        try {
            return x / y;
        } catch(ArithmeticException e) {
            return 0;
        }
    }

    // This method will give us exception
    private static int divide(int x, int y) {
        return x / y;
    }
}
