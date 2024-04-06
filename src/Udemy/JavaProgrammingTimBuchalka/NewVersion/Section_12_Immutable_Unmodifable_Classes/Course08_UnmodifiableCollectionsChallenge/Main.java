package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge.bank.Bank;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge.bank.BankAccount;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge.bank.BankCustomer;

public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank(3214567);
        bank.addCustomer("Joe", 500.00, 10000.00);

        BankCustomer joe = bank.getCustomer("000000010000000");
        System.out.println(joe);
        //List<BankAccount> accounts = joe.getAccounts();
        //accounts.clear();
        //System.out.println(joe);

        if (bank.doTransaction(joe.getCustomerId(), BankAccount.AccountType.CHECKING, 35)) {
            System.out.println(joe);
        }

        if (bank.doTransaction(joe.getCustomerId(), BankAccount.AccountType.CHECKING, -535)) {
            System.out.println(joe);
        }

/*
        if (bank.doTransaction(joe.getCustomerId(), BankAccount.AccountType.CHECKING, -0.01)) {
            System.out.println(joe);
        }
*/

        BankAccount checking = joe.getAccount(BankAccount.AccountType.CHECKING);
        var transactions = checking.getTransactions();
        transactions.forEach((k, v) -> System.out.println(k + ": " + v));

        //transactions.put(3L, new Transaction(1,1,Integer.parseInt(joe.getCustomerId()),500).toString());

/*
        System.out.println("---------------------");
        for (var tx : transactions.values()) {
            tx.getCustomerId();
            tx.setAmount(10000.00);
        }
        transactions.forEach((k, v) -> System.out.println(k + ": " + v));
*/


        System.out.println("---------------------");
        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions().
                forEach((k, v) -> System.out.println(k + ": " + v));


        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions().clear();
        System.out.println("---------------------");
        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions().
                forEach((k, v) -> System.out.println(k + ": " + v));

    }
}