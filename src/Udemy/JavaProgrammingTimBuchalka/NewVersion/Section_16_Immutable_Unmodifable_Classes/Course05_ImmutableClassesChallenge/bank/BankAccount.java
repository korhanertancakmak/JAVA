package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course05_ImmutableClassesChallenge.bank;

//Part-2
/*
        I'll include a very simple enum for my account types. You can imagine this might grow with different account types.
    I'll include this as a nested type, and just set it up with Checking and Savings to start. Next I'll set up my two
    fields, account type and balance, and I'll make these private and final. An enum value is an immutable type, so neither
    of these fields are subject to side effects, once assigned. This isn't going to compile unless I assign values, either
    directly with an assignment here, which wouldn't make any sense for these fields, or in a constructor. I'll generate
    the constructor, using both of my fields, and leave that as is. I'll generate some getters, for both fields. Next,
    I want to generate the toString method, generating that with no fields, so I'll select none. I'll return a formatted
    string, that prints the account type and the balance, with 2 decimal places. I'll test this out, in my Main class.
*/
//End-Part-2

public class BankAccount {

    public enum AccountType {CHECKING, SAVINGS}

    private final AccountType accountType;
    private final double balance;

    BankAccount(AccountType accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(accountType, balance);
    }
}
