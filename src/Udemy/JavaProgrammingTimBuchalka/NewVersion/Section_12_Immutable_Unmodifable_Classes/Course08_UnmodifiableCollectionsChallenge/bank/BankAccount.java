package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge.bank;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge.dto.Transaction;
import java.util.LinkedHashMap;
import java.util.Map;

//Part-3
/*
        First, I don't want balance to be final, so I'll remove the final key word. I'll add a collection of transactions,
    and of course I have many options to choose from for this type. I could make this an array, a list, a set, or a map.
    I'm going to go with map, in case I ever want to look up a transaction by it's transaction id. I'll use Long as my
    key type, so the wrapper long, uppercase there, and Transaction is going to be the value type. I'll call it transactions,
    and I'll initialize this to a new LinkedHashMap. It makes sense to have the map ordered, by the order they were added.
    I'll generate a getter for that, placing it after getBalance. I'm not going to just return transactions here, instead
    I'll return an unmodifiable collection, to try to safeguard that data. Since my field is a Map, I can use the copy
    of method on Map, passing it my transactions. Finally, I want to provide a way to change the balance on an account.
*/
//End-Part-3

public class BankAccount {

    public enum AccountType {CHECKING, SAVINGS}

    private final AccountType accountType;
    private double balance;
    private final Map<Long, Transaction> transactions = new LinkedHashMap<>();

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

//Part-18
/*
        Instead of returning an unmodifiable map, containing mutable data, I'll instead return a map of immutable instances,
    Strings. This will provide the same behavior, without exposing the data to dangerous side effects. To do this, I'll
    change the return type of this method from returning a map, keyed by long, with Transaction values, to still be keyed
    by long, but with String values instead. I'll next set up a local variable, a map, again with the key long, with a
    value of String, I'll call that txMap. I'll set that to a new LinkedHashMap. I'll loop through the map's entries.
    I'll insert the same key, so transaction get key, but the value won't be the transaction, but the string value for
    transaction. I'll return this map. Now, my main method,
*/
//End-Part-18

    public Map<Long, String> getTransactions() {

        Map<Long, String> txMap = new LinkedHashMap<>();
        for (var tx : transactions.entrySet()) {
            txMap.put(tx.getKey(), tx.getValue().toString());
        }
        return txMap;
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(accountType, balance);
    }

//Part-4
/*
        I'll call this method commitTransaction. I'll make it package private, meaning only classes in this package can
    call it, so only the Bank or BankCustomer classes will be able to call it. I'll pass the bank routing number, the
    transaction id, the customer id, and the amount of the transaction. In this code, I'll just assume validation occurred
    already, and I'll adjust the balance. Remember a withdrawal will come in as a negative number, so I can simply add
    the amount in either case. I'll also create a transaction and put it in this account's map. My customer id is a string
    with leading zeros, here I just want to pass the integer value, and let the transaction class format it in its own
    way. Ok, so that's it for the Bank Account. Next, I'll go to my BankCustomer class, and make the changes for this class.
*/
//End-Part-4

    void commitTransaction(int routingNumber, long transactionId, String customerId, double amount) {

        balance += amount;
        transactions.put(transactionId,
                new Transaction(routingNumber, transactionId,
                        Integer.parseInt(customerId), amount));
    }
}
