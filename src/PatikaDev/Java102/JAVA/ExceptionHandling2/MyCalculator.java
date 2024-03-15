package JAVA.ExceptionHandling2;

public class MyCalculator {
    /*
     * Create the method long power(int, int) here.
     */
    public long power(int n, int p) throws Exception {
        if (n < 0 || p < 0) {
            throw new Exception("n or p should not be negative.");
        } else if (n == 0 && p == 0) {
            throw new Exception("n and p shoud not be zero.");
        }

        long res = 1;

        for (int i = 0; i < p; i++) {
            if (i == 0) {
                res = n;
            } else {
                res *= n;
            }
        }
        return res;
    }
}