/* Pratice43 -  Average of the array elements 

Homework
Write a program that calculates the harmonic average of the array elements.

Harmonic average formula : n (the number of elements) / harmonic series of elements

Harmonik Seri Formülü :
1 + 1/2 + 1/3 + ... + = sum(0, infinite) 1/n

*/

public class Project43 {
    
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        double average = sum / numbers.length;
        System.out.println("The average of the array elements: " + average);

        double harmonic = 1.0;
        double harmonicAvg = 0.0;
        for (int i = 2; i < numbers.length + 1; i++) {
            harmonic += (double) 1/i;
        }
        harmonicAvg = numbers.length / harmonic;
        System.out.printf("The harmonic average of the array: %.2f%n", harmonicAvg);
    }
}

