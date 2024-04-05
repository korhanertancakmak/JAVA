package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course05_ImmutableClassesChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course05_ImmutableClassesChallenge.bank.BankAccount;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course05_ImmutableClassesChallenge.bank.BankCustomer;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        BankAccount account = new BankAccount(BankAccount.AccountType.CHECKING, 500);
//        System.out.println(account);

        BankCustomer joe = new BankCustomer("Joe", 500.00, 10000.00);
        System.out.println(joe);

        List<BankAccount> accounts = joe.getAccounts();
        accounts.clear();
        System.out.println(joe);

        //accounts.add(new BankAccount(BankAccount.AccountType.CHECKING, 150000));
        //System.out.println(joe);

    }
}

/*
class MyAccount extends BankAccount {

    MyAccount(AccountType accountType, double balance) {
        super(accountType, balance);
    }
}
*/