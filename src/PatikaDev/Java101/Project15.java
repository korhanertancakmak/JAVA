/* Pratice15 - Sorting Numbers from Largest to Smallest

Homework
Write a program that sorts the 3 entered numbers "from smallest to largest".

*/

import java.util.Scanner;

public class Project15 {
    
    public static void main(String[] args) {
        
        
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the numbers:");
        int n1 = kb.nextInt();
        int n2 = kb.nextInt();
        int n3 = kb.nextInt();

        boolean c1 = (n1 >= n2 && n1 >= n3);
        boolean sc1 = (n2 >= n3);
        boolean c2 = (n2 >= n1 && n2 >= n3);
        boolean sc2 = (n1 >= n3);
        boolean c3 = (n3 >= n1 && n3 >= n2);
        boolean sc3 = (n1 >= n2);

        // Solution-1
        if (c1) {
            if (sc1) {
                System.out.println("n1 >= n2 >= n3");
            }
            else {
                System.out.println("n1 >= n3 >= n2");
            }
        } else if (c2) {
            if (sc2) {
                System.out.println("n2 >= n1 >= n3");
            }
            else {
                System.out.println("n2 >= n3 >= n1");
            }
        } else if (c3) {
            if (sc3) {
                System.out.println("n3 >= n1 >= n2");
            }
            else {
                System.out.println("n3 >= n2 >= n1");
            }
        }

        // Solution-2
        int options = c1 ? (sc1 ? 1 : 2) : 
                    c2 ? (sc2 ? 3 : 4) : 
                c3 ? (sc3 ? 5 : 6) : 0;

        switch (options) {
            case 1 :
            System.out.println("n1 >= n2 >= n3");
            break;
            case 2 :
            System.out.println("n1 >= n3 >= n2");
            break;
            case 3 :
            System.out.println("n2 >= n1 >= n3");
            break;
            case 4 :
            System.out.println("n2 >= n3 >= n1");
            break;
            case 5 :
            System.out.println("n3 >= n1 >= n2");
            break;
            case 6 :
            System.out.println("n3 >= n2 >= n1");
            break;
        }

        // Solution-3
        int[] nums = new int[3];
        nums[0] = Math.max(n1, Math.max(n2, n3));
        nums[1] = Math.max(n1, Math.min(n2, n3));
        nums[2] = Math.min(n1, Math.min(n2, n3));

        System.out.printf("%d >= %d >= %d", nums[0], nums[1], nums[2]);

        kb.close();
    }
}

