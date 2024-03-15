package PatikaDev.Java102.JAVA.InsuranceManagementSystem;

import PatikaDev.Java102.JAVA.InsuranceManagementSystem.InsuranceCompany.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Account account1 = new Individual(new User("korhan", "cakmak", "korhan@gmail.com",
                "123", "Java Developer", 34, new ArrayList<>()));
        Account account2 = new Individual(new User("ayhan", "turan", "ayhan@gmail.com",
                "1234", "Retired", 65, new ArrayList<>()));
        Account account3 = new Individual(new User("ekrem", "inan", "ekrem@gmail.com",
                "12345", "Police", 25, new ArrayList<>()));
        AccountManager accountManager = new AccountManager();
        accountManager.addAccounts(account1);
        accountManager.addAccounts(account2);
        accountManager.addAccounts(account3);

        accountManager.login();

    }
}
