package PatikaDev.Java102.JAVA.InsuranceManagementSystem.InsuranceCompany;

import java.util.ArrayList;
import java.util.Scanner;

/**
 The insurance company has two types of customer profiles: "Individual"
 and "Enterprise."
 Design an abstract class named "Codes.InsuranceManagementSystem.Account.Account" for the customer profile.
 Codes.InsuranceManagementSystem.Account.Account class is the account information where all information of the
 customer is kept after logging into the system.
 There is an object reference of type "Codes.InsuranceManagementSystem.User" within the "Codes.InsuranceManagementSystem.Account.Account" class.
 (As Aggregation relationship)
**/

public abstract class Account implements Comparable<Account>{

    protected AuthenticationStatus authenticationStatus;
    protected User user;
    ArrayList<Insurance> insurances = new ArrayList<>();

    protected Scanner input = new Scanner(System.in);

    public Account(User user) {
        this.user = user;
    }

    abstract Account login(String userEmail, String userPassWord);

    abstract void addInsurance();

    final void showUserInfo() {
        System.out.println("User Info:");
        System.out.println("-".repeat( 129));
        System.out.printf("| %-10s| %-10s| %-20s| %-20s| %-4s| %-15s| %-35s|%n",
                "Name", "Surname", "Email", "Profession", "Age", "Last Login Date", "Address");
        System.out.println("-".repeat( 129));
        System.out.printf("| %-10s| %-10s| %-20s| %-20s| %-4s| %-15s| %-35s|%n",
                user.getName(), user.getSurname(), user.getEmail(), user.getProfession(),
                user.getAge(), user.getLastLoginLocalDate(),
                user.getAddressList() == null ? "null" :
                        user.getAddressList().getFirst().getAddress());
        System.out.println("-".repeat(129));
    }

    public User getUser() {
        return user;
    }
}
