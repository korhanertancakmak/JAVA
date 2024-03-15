/* Pratice51 - Number Guessing Game

We are making a "Number Guessing Game" in Java language in which we ask the user to guess a number that the program randomly chooses between 0-100.

*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Project51 {
    
    public static void main(String[] args) {
        Random rand = new Random();
        int number = rand.nextInt(100);
        //int number = (int) (Math.random() * 100);

        Scanner input = new Scanner(System.in);
        int right = 0;
        int selected;
        int[] wrong = new int[5];
        boolean isWin = false;
        boolean isWrong = false;

        System.out.println(number);
        while (right < 5) {
            System.out.print("Enter your guess : ");
            selected = input.nextInt();

            if (selected < 0 || selected > 99) {
                System.out.println("Please enter an integer between 0-100.");
                if (isWrong) {
                    right++;
                    System.out.println("Too many inputs entered! Left rights : " + (5 - right));
                } else {
                    isWrong = true;
                    System.out.println("Next time you entered wrong your right will be decreased.");
                }
                continue;
            }

            if (selected == number) {
                System.out.println("Congratulations, write guess! The guess number : " + number);
                isWin = true;
                break;
            } else {
                System.out.println("You entered wrong !");
                if (selected > number) {
                    System.out.println(selected + " is greater than the secret number.");
                } else {
                    System.out.println(selected + " is lower than the secret number.");
                }

                wrong[right++] = selected;
                System.out.println("Left rightsx : " + (5 - right));
            }
        }

        if (!isWin) {
            System.out.println("You lost ! ");
            if (!isWrong) {
                System.out.println("Your guesses : " + Arrays.toString(wrong));
            }
        }
        input.close();
    }
}


