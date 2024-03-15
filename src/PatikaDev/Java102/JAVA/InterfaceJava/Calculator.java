package JAVA.InterfaceJava;

public class Calculator implements AdvancedArithmetic {
    @Override
    public int divisor_sum(int n) {
        int sum = 0;
        for(int i = n; i >= 1; i--) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum;
    }
}