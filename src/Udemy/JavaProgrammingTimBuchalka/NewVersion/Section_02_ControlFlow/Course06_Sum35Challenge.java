package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_02_ControlFlow;

public class Course06_Sum35Challenge {
    public static void main(String[] args) {

        int sum = 0, count = 0;
        for (int i = 1; i <= 1000; i++) {

            if ((i % 3 == 0) && (i % 5 == 0)) {
                System.out.println(i + " can be divided 3 and 5.");
                sum += i;
                count++;
            }

            if (count == 5) {
                break;
            }
        }

        System.out.println(sum + " is the sum of the 5 numbers that have met the condition.");
    }
}
