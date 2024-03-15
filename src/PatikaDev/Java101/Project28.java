/* Pratice28 - ATM Project

We create a project in that an user can manage his bank account.

*/

import java.util.Scanner;

public class Project28 {
    
    public static void main(String[] args) {

        String userName, password;
        Scanner input = new Scanner(System.in);
        int right = 3;
        int balance = 1500;
        int select;
        while (right > 0) {
            System.out.print("Your Name(patika?) :");
            userName = input.nextLine();
            System.out.print("Password(dev123?) : ");
            password = input.nextLine();

            if (userName.equals("patika") && password.equals("dev123")) {
                System.out.println("Hello, welcome to the kodluyoruz bank!");
                do {
                    System.out.println("1-Deposit\n" +
                            "2-Withdraw\n" +
                            "3-Balance\n" +
                            "4-Quit");
                    System.out.print("Please pick the process you want : ");
                    select = input.nextInt();
                    if (select == 1) {
                        System.out.print("The amount : ");
                        int price = input.nextInt();
                        balance += price;
                    } else if (select == 2) {
                        System.out.print("The amount : ");
                        int price = input.nextInt();
                        if (price > balance) {
                            System.out.println("Fund is insufficient!");
                        } else {
                            balance -= price;
                        }
                    } else if (select == 3) {
                        System.out.println("Balance : " + balance);
                    }
                } while (select != 4);
                System.out.println("See you later");
                break;
            } else {
                right--;
                System.out.println("Incorrect name or password. Please try again.");
                if (right == 0) {
                    System.out.println("Your account is blocked. Please contact with a bank branch.");
                } else {
                    System.out.println("You have " + right + " rights left.");
                }
            }
        }
        input.close();
    }
}

