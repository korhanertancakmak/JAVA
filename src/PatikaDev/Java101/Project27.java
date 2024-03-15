/* Pratice27 - Creating triangle with "*"

We create a triangle with "*" in java by using loops.

wwwww*wwwww 
wwww***wwww 
www*****www 
ww*******ww 
w*********w
***********

If the input is 5:
        1st line has 5 whitespace(w)-1 star(*)- 5 whitespace(w), totally 11 character.
        2nd line has 4 whitespace(w)-3 star(*)- 4 whitespace(w), totally 11 character.
        3rd line has 3 whitespace(w)-5 star(*)- 3 whitespace(w), totally 11 character.
        4th line has 2 whitespace(w)-7 star(*)- 2 whitespace(w), totally 11 character.
        5th line has 1 whitespace(w)-9 star(*)- 1 whitespace(w), totally 11 character.
        6th line has 0 whitespace(w)-11 star(*)- 0 whitespace(w), totally 11 character.
*/

import java.util.Scanner;

public class Project27 {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number :");
        int n = input.nextInt();
        
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j < (n - i); j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i + 1); k++) {
                System.out.print("*");
            }
            System.out.println(" ");
        }
        input.close();
    }
}


