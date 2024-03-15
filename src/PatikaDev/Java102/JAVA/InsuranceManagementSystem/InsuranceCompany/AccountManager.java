package InsuranceManagementSystem.InsuranceCompany;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Design a class named Codes.InsuranceManagementSystem.AccountManager.
 * Let this class keep a data list of type TreeSet. Keep the individual and corporate accounts you create in this list.
 * Define a function named login in this class.
 * This function takes the email and password information given externally, navigates through the Codes.InsuranceManagementSystem.Account.Account list,
 * and if it finds a suitable login process, it will return the Codes.InsuranceManagementSystem.Account.Account object to the place where it was called.
 * This function will call the "login" function on the Codes.InsuranceManagementSystem.Account.Account object.
 * Remember, this function could throw an error of type "InvalidAuthenticationException."
 * Therefore, remember to set up a try-catch mechanism here.
 **/
public class AccountManager {
    private final TreeSet<Account> accounts = new TreeSet<>();
    Scanner input = new Scanner(System.in);

    public AccountManager() {
    }

    public void login() {
        System.out.println("Welcome to the Insurance Management System!");
        System.out.println("Please Enter your email :");
        String email = input.nextLine();
        System.out.println("Please Enter your password :");
        String password = input.nextLine();
        try {
            for (Account item : accounts) {
                String str = item.getUser().getEmail();
                Individual indi = new Individual(new User(email, password));
                if (item.compareTo(indi) == 0 && item.getUser().getPassword().equalsIgnoreCase(password)) {
                    System.out.println("Login is successful!");
                    item.login(email, password);
                    while (true) {
                        System.out.println("Main menu : ");
                        System.out.println("-".repeat(25));
                        System.out.println("0 - Log out");
                        System.out.println("1 - List your insurances");
                        System.out.println("2 - Purchase an insurance");
                        System.out.println("3 - Profile Info");
                        System.out.println("4 - Profile Setting");
                        System.out.println("-".repeat(25));
                        System.out.print("Your choice : ");
                        byte chosen = input.nextByte();
                        switch (chosen) {
                            case 0 -> {
                                return;
                            }
                            case 1 -> {
                                System.out.println("Insurance List :");
                                System.out.println("-".repeat(69));
                                item.insurances.forEach(insurance ->
                                        System.out.printf("| %-20s| %-15s| %-15s| %-10s|%n",
                                                insurance.insuranceName, insurance.getInsuranceStartDate(),
                                                insurance.getInsuranceEndDate(), insurance.insurancePrice));
                                System.out.println("-".repeat(69));
                            }
                            case 2 -> item.addInsurance();
                            case 3 -> item.showUserInfo();
                            case 4 -> item.user.settings();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAccounts(Account account) {
        this.accounts.add(account);
    }
}
