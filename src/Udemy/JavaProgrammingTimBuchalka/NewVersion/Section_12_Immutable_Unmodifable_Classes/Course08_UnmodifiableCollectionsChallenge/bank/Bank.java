package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge.bank;

import java.util.HashMap;
import java.util.Map;

//Part-7
/*
        Every bank has a routing number, that uniquely identifies it. This will get set, when I create the bank, and
    it shouldn't change, so I'll make it final. Each bank instance will keep track of the last transaction id. This
    should be unique for every transaction. Because it's going to be changing constantly on the bank instance, I don't
    want it to be final, but I do want it private. By the way, I've made routing number public. It's final, and an int,
    so client code can't mutate it, and its a matter of public record for most banks. I could have made this private and
    added a getter, so if you did it that way, its perfectly acceptable. I also want a collection of customers. I'm going
    to make this final, and a map, so I can look up a customer by the customer id. My key type is String, which will be
    the 15 digit customer id, and the value is a Bank Customer. I'll call this field customers. Now that I have the fields,
    I'll generate a constructor, but I only want to include the routing number. Now that I have this constructor, I'll
    add code to instantiate my customer map here. I'll make this a hash map. It's not really important that it's ordered,
    but it's more important for retrieval to be faster. For this class, I don't really want getters, though one for routing
    number, might make sense. Instead I just want a public method to get a BankCustomer, and that'll be called getCustomer.
    It'll take a string, id, the customer's id for the Bank Customer I want to get. I'll look up the customer, by this
    id. I'll return this customer, for the moment. I can't get a customer, unless I have some set up on my bank, so I
    need a method to add a new customer.
*/
//End-Part-7

public class Bank {

    public final int routingNumber;
    private long lastTransactionId = 1;
    private final Map<String, BankCustomer> customers;

    public Bank(int routingNumber) {
        this.routingNumber = routingNumber;
        customers = new HashMap<>();
    }

    public BankCustomer getCustomer(String id) {

        BankCustomer customer = customers.get(id);
        return customer;
    }

//Part-8
/*
        I could return a boolean, to indicate success or not. Or I could return the Customer object created, but I don't
    want to do that here. Instead, I'll keep this simple, and just make it void. It will take a name, an initialDeposit
    for the checking account, and an initial deposit for savings. I'm going to create a new Bank Customer instance, passing
    the name and deposit amounts. This is how we created a customer in the first challenge. The difference now is that I
    can only create a BankCustomer instance, from code in classes, in this package only. I'll add this new customer, to
    the bank's customer map, using the String I get back from get customer id, as the key. Finally, I'll code the very
    important method, doTransaction.
*/
//End-Part-8

    public void addCustomer(String name, double checkingInitialDeposit, double savingsInitialDeposit) {

        BankCustomer customer = new BankCustomer(name, checkingInitialDeposit, savingsInitialDeposit);

        customers.put(customer.getCustomerId(), customer);
    }

//Part-9
/*
        I'll set this up as a public method, returning a boolean, which will be true if the transaction was successfully
    processed. It will take the customer id, a bank account type and an amount. I'll get the customer instance, using
    the id. I'll have this method return false, as the default value. Continuing after I get my BankCustomer, I'll make
    sure I got a valid customer back. I'll have an if statement, checking if customer is not null. If that's the case I
    can get the account from my customer, using the account type. if customer is null, meaning I couldn't get it based
    on the id passed, I'll print out that it's an invalid customer id. Ok, so now I have to make sure I got an account
    back from that, so I'll check if account is not null. If account exists, meaning account isn't null, I'll check if
    adding that amount to the balance would result in a balance less than zero. This would only happen if the amount is
    negative, and there weren't enough funds for the withdrawal. If this is the case, I'll print insufficient funds. If
    the amount is positive, a deposit in other words, or the previous check wasn't true, meaning there's enough funds in
    the account for this withdrawal, then I'll return true. Ok, so that's the banking application. But how secure is it?
    Let's go test it out in the Main class's main method.
*/
//End-Part-9

    public boolean doTransaction(String id, BankAccount.AccountType accountType, double amount) {

        BankCustomer customer = customers.get(id);
        if (customer != null) {
            BankAccount account = customer.getAccount(accountType);
            if (account != null) {
                if ((account.getBalance() + amount) < 0) {
                    System.out.println("Insufficient funds");
                } else {
                    account.commitTransaction(routingNumber, lastTransactionId++, id, amount);
                    return true;
                }
            }
        } else {
            System.out.println("Invalid customer id");
        }
        return false;
    }
}
