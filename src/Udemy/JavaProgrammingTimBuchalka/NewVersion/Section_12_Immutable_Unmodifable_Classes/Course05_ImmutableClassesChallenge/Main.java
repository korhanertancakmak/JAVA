package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course05_ImmutableClassesChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course05_ImmutableClassesChallenge.bank.BankAccount;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course05_ImmutableClassesChallenge.bank.BankCustomer;
import java.util.List;

//Part-1
/*
                                  Write immutable classes for a Bank Account and a Bank Customer

        In this challenge, you should create a "BankAccount" class. This should have a type, indicating the type of account,
    like Checking or Savings or some other type. It should have a balance, the initial dollar amount in the account. You
    should also create a "BankCustomer" that has a customer name, a customer id, and a List of accounts. You should use
    the techniques I discussed in the last lectures, to design these classes. Create a couple of instances of bank customers,
    confirming that you can't change a Customer's data at all, after it's initialized. Create a subclass of the bank customer,
    and confirm that the subclass can't tamper with the customer's data as well.

        I'm going to use these two classes in challenges coming up, so make sure to either create your own classes, or
    walk through it with me now. I've created the usual main class and main method. Before I do anything there, I'll start
    with the BankAccount class. I'll put it in a different package, bank.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

//        BankAccount account = new BankAccount(BankAccount.AccountType.CHECKING, 500);
//        System.out.println(account);

//Part-3
/*
        I'll create a local variable, type BankAccount, named account. I'll assign that a new Bank account instance, passing
    the Checking enum, and 500 as the initial deposit. I'll print that account. Running that,

                        CHECKING $500.00

    I see that I have a checking account, with 500 dollars in it. This class can be called immutable. Once created, I can't
    change the type or balance. Now, this may not be very realistic, if I'm writing a banking application. On the other
    hand, if I'm writing a service that will pass my bank's accounts to some sort of clearing house for example, I wouldn't
    want these classes to be tampered with. Next, I'll create the BankCustomer class, also in the same bank package.
*/
//End-Part-3

        BankCustomer joe = new BankCustomer("Joe", 500.00, 10000.00);
        System.out.println(joe);

//Part-5
/*
        First, I'll comment out the code for the account data. I'll create joe next, so a new BankCustomer named joe, and
    that's a new Bank customer, name is joe, checking balance is 500, and savings is ten thousand. I'll print Joe out.
    When I run this,

                        Customer: Joe (id:000000010000000)
                        CHECKING $500,00
                        SAVINGS $10000,00

    you can see Joe, with his 15 digit id, prefixed with zeros, and his two accounts. Let's get Joe's accounts and see
    if Joe's Customer record is really immutable.
*/
//End-Part-5

        List<BankAccount> accounts = joe.getAccounts();
        accounts.clear();
        System.out.println(joe);

//Part-6
/*
        I'll get the account data. I'll call clear on it, and I'll print it out. Running that,

                    Customer: Joe (id:000000010000000)
                        CHECKING $500,00
                        SAVINGS $10000,00

                    Customer: Joe (id:000000010000000)

    you can see I was able to successfully clear the accounts. That's probably not good. I can also add accounts this
    way:
*/
//End-Part-6

        //accounts.add(new BankAccount(BankAccount.AccountType.CHECKING, 150000));
        //System.out.println(joe);

//Part-7
/*
        I'll create a new Bank Account, a checking account, with one hundred and fifty thousand dollars, and add that
    to the accounts variable. I'll again print joe here. And running that,

                        Customer: Joe (id:000000010000000)
                            CHECKING $150000,00

    you can see I made Joe a lot richer than he was initially. I can prevent this last behavior fairly easily, by making
    Bank Account's constructor package private, so let me do that, removing the accessor altogether, removing public there.
    This gives me an error now in my main method, which is good, so I'll remove those last two statements.
*/
//End-Part-7
    }
}

//Part-8
/*
        Also, if I want to create a subclass of Bank Account, look what happens if I try. You can see I can't do this,
    because there is no default constructor available on BankAccount. And let's say I add a constructor, that matches
    the signature of Bank Account's constructor, and simply try to call it. Here in super too, I have an error, on that
    call to super, that it's not public, and it's also not protected. This means I can't create a subclass of BankAccount,
    from any other package. Ok, that's a good thing. I'll remove that class from my Main dot java file. Ok, I'm partially
    there. I can create accounts from any classes, not in the same package as the BankAccount class, but I need to fix
    my get method on the BankCustomer.
*/
//End-Part-8

/*
class MyAccount extends BankAccount {

    MyAccount(AccountType accountType, double balance) {
        super(accountType, balance);
    }
}
*/