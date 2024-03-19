package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course05_ImmutableClassesChallenge.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Part-4
/*
        I want to set up a private static field. I'll call it last Customer id, setting that to 10 million. This will be
    my customer id generator, and I just want them to start with 8 digits. I'll add two instance fields, name, and customer
    id, and I'll make these both private and final. The last field, will be a list of bank account. I'll make this private
    and final, calling that accounts, and I'll make this a new ArrayList. I'll generate a constructor, I only want to pass
    the customer name for this constructor. The customer id will be generated, and I don't want to pass a list to this
    constructor either. I'll change this constructor to include a checking account balance, as well as a savings account
    balance. I'll add a double called checking amount, and another double, Savings Amount. Now I'll set up the rest of
    my fields, in this constructor. First, I'll assign lastCustomerId, to my customer id field, and increment that with
    a post fix increment. I'll add a new bank account, a checking account, with the checking amount. This gets added to
    my accounts list. I'll do the same thing for a savings account, passing the savings amount, adding this to my accounts
    list. I'll add getters, by generating them, for the name and accounts fields. I'll leave these as is for the moment,
    and first, include a toString method, selecting the select none button. I'll remove the return statement. I want to
    include all the accounts in this string. I'll start with a string array, called account strings, and that will be a
    new array, the same size as my accounts list. I'll populate each element in that array, with the string representation
    of each account. I'll do this by calling Arrays.setAll, get the account using the lambda expressions index, and return
    the string value of the element at that index. My formatted string will contain the customer name, the customer id
    in parentheses, prefixed with zeroes up to 15 characters. This will be followed by a new line and tab, and then I'll
    print my account strings, which will be joined by a newline and tab, so each account will print on a separate line,
    with an indent. I'll test this now, in my Main method.
*/
//End-Part-4

public class BankCustomer {

    private static int lastCustomerId = 10_000_000;

    private final String name;
    private final int customerId;
    private final List<BankAccount> accounts = new ArrayList<>();

    public BankCustomer(String name, double checkingAmount, double savingsAmount) {
        this.name = name;
        this.customerId = lastCustomerId++;
        accounts.add(new BankAccount(BankAccount.AccountType.CHECKING, checkingAmount));
        accounts.add(new BankAccount(BankAccount.AccountType.SAVINGS, savingsAmount));
    }

    public String getName() {
        return name;
    }

//Part-9
/*
        I'll return a new ArrayList, initialized with the current accounts data. If I run this code,

                    Customer: Joe (id:000000010000000)
                        CHECKING $500,00
                        SAVINGS $10000,00

                    Customer: Joe (id:000000010000000)
                        CHECKING $500,00
                        SAVINGS $10000,00

    I see the results look better. I'm still calling clear, on the accounts data I get back from the Bank Customer class,
    but it's a copy. It doesn't really change the data on the Bank Customer. Now, I've got a Bank Customer with accounts.
    I can view the data, and get a copy of some of the data, but I can't modify it. In the next lecture, I'll be talking
    more about defensive copies, both shallow and deep, and I'll follow that with an introduction to unmodifiable collections,
    so keep this code handy. We'll be coming back to it in other challenges coming up in this section.
*/
//End-Part-9

    public List<BankAccount> getAccounts() {
        return new ArrayList<>(accounts);
    }

    @Override
    public String toString() {

        String[] accountStrings = new String[accounts.size()];
        Arrays.setAll(accountStrings, i -> accounts.get(i).toString());
        return "Customer: %s (id:%015d)%n\t%s%n".formatted(name, customerId,
                String.join("\n\t", accountStrings));
    }
}
