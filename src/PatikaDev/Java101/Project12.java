/* Project12 - Codes.InsuranceManagementSystem.User Login

Homework
If the password is incorrect, ask the user whether he will reset his password. 
If the user wants to reset it, check that the new password he entered should 
not be the same as the password he entered incorrectly or forgot, and if the 
passwords are the same, "The password could not be created, please enter another 
password." If there is no problem, write the program that says "Password created".

*/

import java.util.Objects;
import java.util.Scanner;

public class Project12 {
    
    public static void main(String[] args) {
        
        String userName, password;
        Scanner kb = new Scanner(System.in);
        
        System.out.println("Codes.InsuranceManagementSystem.User name: ");
        userName = kb.nextLine();
        System.out.println("Password: ");
        password = kb.nextLine();

        boolean flag = true;
        if (!Objects.equals(password, String.valueOf(5826))) {
            System.out.println("Password is wrong! Do you want to reset your password? Y/N");
            if (kb.nextLine().equalsIgnoreCase("Y")) {
                while (flag) {
                    System.out.println("Enter new password:");
                    String newPass = kb.nextLine();
                if (!newPass.equalsIgnoreCase(password) && !newPass.equalsIgnoreCase(String.valueOf(5826)) ) {
                    System.out.println(userName + ", password created!");
                    flag = false;
                } else {
                    System.out.println("The password could not be created, please enter another password.");
                }

                }
            }
        }
        kb.close();
    }
}

