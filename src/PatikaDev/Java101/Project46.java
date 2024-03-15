/* Pratice46 - Program that finds repeating elements in an array 

Homework
Write a program that finds repeating even values and prints them to the screen.
*/

public class Project46 {
    
    public static void main(String[] args) {
        int[] list = {2, 7, 3, 3, 2, 9, 10, 21, 1, 33, 9, 10};

        String duplicate = "";
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if ((i != j) && (list[i] == list[j]) && list[i] % 2 == 0) {
                    duplicate += list[i] + " ";
                    break;
                }
            }
        }

        System.out.println(duplicate);
    }
}

