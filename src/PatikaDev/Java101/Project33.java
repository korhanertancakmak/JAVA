/* Pratice33 - Prime numbers

Write a program that prints all the prime numbers between 1 - 100.

Scenario
2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 

*/

public class Project33 {
    
    public static void main(String[] args) {

        int number = 100;

        boolean isPrime;
        for (int j = 2; j <= number; j++) {
            isPrime = true;
            for (int i = 2; i <= j; i++) {
                if (j % i == 0 && i != j) {
                     isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                    System.out.print(j + " ");
            }
        }
    }
}

