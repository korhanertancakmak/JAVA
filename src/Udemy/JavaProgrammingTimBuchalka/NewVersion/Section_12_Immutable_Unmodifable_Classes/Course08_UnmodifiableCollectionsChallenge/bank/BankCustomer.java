package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Part-5
/*
        First, I want to make the constructor package private, so I'm not going to specify any access modifier on this
    constructor. I'll remove the public access modifier that's there now. We have an error because we were using this
    constructor in Main. I'll fix that shortly. Right now, I'll add a getter for customer id, putting that under get name.
    I'll change this though, to return a 15 digit string. To do this, I'll change the return type, to a string. Instead
    of just returning the customer id, I'll return a formatted string that returns my customer id, with leading zeros,
    up to 15 characters. Next I want to change the getAccounts method, to return an unmodifiable collection, by using the
    copy of method, on List this time. This will prevent any clients from adding, deleting or reassigning accounts, among
    other things. Lastly, I want to be able to get a customer account, by the account type. I'll place this method after
    the getAccounts method. I'll make this public, returning a BankAccount, and I'll call it getAccount, It will take an
    enum constant, an AccountType as the parameter. I'll return null to start with. Next, I'll add code to loop through
    my list of accounts, returning the first account that matches the type passed. This isn't realistic for an actual
    bank, because you could have different multiple checking and savings accounts, but let's keep this simple. My code
    isn't compiling right now, because, as I mentioned previously, its because of the code in the Main class's main method.
*/
//End-Part-5

public class BankCustomer {

    private static int lastCustomerId = 10_000_000;

    private final String name;
    private final int customerId;
    private final List<BankAccount> accounts = new ArrayList<>();

    BankCustomer(String name, double checkingAmount, double savingsAmount) {
        this.name = name;
        this.customerId = lastCustomerId++;
        accounts.add(new BankAccount(BankAccount.AccountType.CHECKING, checkingAmount));
        accounts.add(new BankAccount(BankAccount.AccountType.SAVINGS, savingsAmount));
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return "%015d".formatted(customerId);
    }

    public List<BankAccount> getAccounts() {
        return List.copyOf(accounts);
    }

    public BankAccount getAccount(BankAccount.AccountType type) {

        for (var account : accounts) {
            if (account.getAccountType() == type) {
                return account;
            }
        }
        return null;
    }

    @Override
    public String toString() {

        String[] accountStrings = new String[accounts.size()];
        Arrays.setAll(accountStrings, i -> accounts.get(i).toString());
        return "Customer: %s (id:%015d)%n\t%s%n".formatted(name, customerId,
                String.join("\n\t", accountStrings));
    }
}
