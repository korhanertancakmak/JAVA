package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge.bank.Bank;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge.bank.BankAccount;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge.bank.BankCustomer;

//Challenge
/*
        In an earlier challenge, we created an immutable Bank Customer, and Bank Account class. We'll be building on that
    code here. If you didn't do that challenge, let me encourage you to go back and work through it. We ended up with two
    classes as shown here.

                        _______________________________             _______________________________
                        | BankCustomer                |             | BankAccount                 |
                        |_____________________________|             |_____________________________|
                        | name: String                |<>-----------| accountType: AccountType    |
                        | customerId: int             |             | balance: double             |
                        | List<BankAccount> accounts  |             |_____________________________|
                        |_____________________________|

    The getters, constructors and two string methods, aren't shown on this diagram. These are the classes we'll be starting
    with, in this challenge. Now, let's Imagine that we work on a team, with different developers.

                                                                            _______________________________
                                                                            | Transaction                 |
                                                                            |_____________________________|
        * One team creates the data transfer objects (DTOs)                 | routingNumber: int          |
                                                                            | customerId: int             |
                                                                            | transactionId: long         |
                                                                            | transactionAmount: double   |
                                                                            |_____________________________|

    which will be used by a framework, to retrieve data from and commit data to, a database. Let's assume they use software
    to generate these classes, from their data model. We want to emulate this, so we first want to Create a "Transaction"
    class in a dto package, that might mirror a data table. This class should have the fields shown above. "Include getters
    and setters" for all fields. Data transfer objects generally have both, to support two way communication with database
    entities. "Include a constructor" that takes all fields, for ease of use.

                                   Current                              After Modifications (An Example)
                        _______________________________        _________________________________________________
                        | BankAccount                 |        | BankAccount                                   |
                        |_____________________________|        |_______________________________________________|
                        | accountType: AccountType    |        | accountType: AccountType                      |
                        | balance: double             |        | balance: double                               |
                        |_____________________________|        | Map<Long, Transaction>: transactions          |
                                                               |_______________________________________________|
                                                               | getTransactions: Map<Long, Transaction>       |
                                                               |   commitTransaction(int routingNumber,        |
                                                               |     long transactionId, String customerId,    |
                                                               |       double amount)                          |
                                                               |_______________________________________________|

        For this challenge, you'll modify your BankAccount class. First, you'll want to change the balance so that it's
    mutable. Include a Transaction Collection. I'm showing a Map on my class diagram, but if you want to use another
    collection, that's good too. Provide a getter, or accessor method, for the transaction data. Provide a method to
    adjust the balance, and add the transaction data to the transaction collection. I'll be doing this in a single method
    called commitTransaction as shown. You'll also need to Modify your BankCustomer class.

                                   Current                              After Modifications (An Example)
                        _______________________________        _________________________________________________
                        | BankCustomer                |        | BankCustomer                                  |
                        |_____________________________|        |_______________________________________________|
                        | name: String                |        | name: String                                  |
                        | customerId: int             |        | customerId: int                               |
                        | List<BankAccount> accounts  |        | List<BankAccount> accounts                    |
                        |_____________________________|        |_______________________________________________|
                                                               | getCustomerId: String                         |
                                                               | getAccounts: List<Bank Account>               |
                                                               | getAccount(AccountType type): Account         |
                                                               |_______________________________________________|

    Return the customer id as a 15 digit string, with leading zeros. Design this class, so that code in other packages
    can't instantiate a new Bank Customer. Return a defensive copy of the accounts, from the getAccounts method. Include
    a getAccount method to return just one account, based on account type, either savings or checking. Assume a customer
    will have one checking account and one savings account.

                        _________________________________________________
                        | Bank                                          |
                        |_______________________________________________|
                        | routingNumber: int                            |
                        | lastTransactionId: long                       |
                        | Map<String, BankCustomer> customers           |
                        |_______________________________________________|
                        | getCustomer(String id): BankCustomer          |
                        | addCustomer(String name,                      |
                        |             double checkingInitialDeposit,    |
                        |             double savingsInitialDeposits)    |
                        | doTransaction(String id, AccountType type,    |
                        |               double amount)                  |
                        |_______________________________________________|

        Next, you want to create a Bank class, that has a routing number, and a collection of customers, as well as an
    integer that holds the next transaction id to be assigned. You should be able to look up a customer by a customer id,
    a 15 character String. Transaction id's should be assigned, by using the lastTransactionId field, on this instance
    of the bank.  A negative amount is a withdrawal, and a positive amount is a deposit. Don't let the customer's account
    balance go below zero. In the Main class's main method. Create a bank instance, and add a customer. Let a client get
    a BankCustomer instance by a customer id, and review transactions from a single selected account. These transactions
    should "not be modifiable", or susceptible to side effects. You should only be able to perform a withdrawal or deposit
    of funds, through the Bank Instance, passing the customer id as a String, the type of account this transaction will
    be against, and the amount. In other words, the main method should not have access to the commit transaction code on
    the Bank Account itself.

               _________________________________________________             _________________________________________________
               | BankCustomer                                  |             | Bank                                          |
               |_______________________________________________|             |_______________________________________________|
               | name: String                                  |             | routingNumber: int                            |
               | customerId: int                               |             | lastTransactionId: long                       |
       |-----<>| List<BankAccount> accounts                    |-----------<>| Map<String, BankCustomer> customers           |
       |       |_______________________________________________|             |_______________________________________________|
       |       | getCustomerId: String                         |             | getCustomer(String id): BankCustomer          |
       |       | getAccounts: List<Bank Account>               |             | addCustomer(String name,                      |
       |       | getAccount(AccountType type): Account         |             |             double checkingInitialDeposit,    |
       |       |_______________________________________________|             |             double savingsInitialDeposits)    |
       |                                                                     | doTransaction(String id, AccountType type,    |
       |                                                                     |               double amount)                  |
       |                                                                     |_______________________________________________|
       |
       |       _________________________________________________             _______________________________
       |       | BankAccount                                   |             | Transaction                 |
       |       |_______________________________________________|             |_____________________________|
       |       | accountType: AccountType                      |             | routingNumber: int          |
       |       | balance: double                               |             | customerId: int             |
       |------ | List<BankAccount> accounts                    |<>---------- | transactionId: long         |
               |_______________________________________________|             | transactionAmount: double   |
               | getTransactions: Map<Long, Transaction>       |             |_____________________________|
               |   commitTransaction(int routingNumber,        |
               |     long transactionId, String customerId,    |
               |       double amount)                          |
               |_______________________________________________|

    You might want to leave this diagram up as you're coding your own solution, to help you see the big picture.
*/
//End-Challenge

//Part-1
/*
        In this project I have a package, bank, that has my BankAccount and BankCustomer classes. I designed these to be
    immutable, but now I want to let customers withdraw or deposit funds, and I want to keep track of each transaction.
    I'll create the Transaction class first,
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

//Part-6
/*
        I made the Bank Customer constructor package private so I can't instantiate a class, from this method. What I'll
    do, so the code will compile, is just set joe to null for now. I wanted this code to compile. Ok, so let's build the
    bank. I'll create the Bank class, in my bank package.
*/
//End-Part-6

        Bank bank = new Bank(3214567);
        bank.addCustomer("Joe", 500.00, 10000.00);

        BankCustomer joe = bank.getCustomer("000000010000000");
        System.out.println(joe);
        //List<BankAccount> accounts = joe.getAccounts();
        //accounts.clear();
        //System.out.println(joe);

//Part-10
/*
        First, I want to create a Bank instance. I'll insert this code above the Bank Customer Joe equals null statement.
    I'll create a variable bank, and assign that a new bank, passing that a routing number. I'll add a bank customer, Joe,
    with the same deposits as before. I need to retrieve Joe from the bank's method, so instead of assigning null to joe,
    I'll assign him the result of the getCustomer method on my new bank. I know I started with ten million as the first
    bank customer id. So that's 8 digits. I'll add seven leading zeros, 10, and another 6 zeros to get our 15 digit customer
    id, which should give me Joe. And I'll print out joe Running this code,

                    Customer: Joe (id:000000010000000)
                        CHECKING $500,00
                        SAVINGS $10000,00

                    Exception in thread "main" java.lang.UnsupportedOperationException
                    	at java.base/java.util.ImmutableCollections.uoe

    I get the same output I got before, but I also get an exception in the code, where I try to clear the account data.
    This is the benefit of returning an unmodifiable collection. I'll comment those last three statements.
*/
//End-Part-10

        if (bank.doTransaction(joe.getCustomerId(), BankAccount.AccountType.CHECKING, 35)) {
            System.out.println(joe);
        }

//Part-11
/*
        Now, I'll add some funds to Joe's account, calling the do transaction method on Bank to do it. This method returns
    a boolean, so I'll put it in an if statement. I'll pass the customer id, Checking for the account type, and 35 dollars.
    If that's successful, I'll print out Joe's account again. Running that code,

                    Customer: Joe (id:000000010000000)
                        CHECKING $500,00
                        SAVINGS $10000,00

                    Customer: Joe (id:000000010000000)
                        CHECKING $535,00
                        SAVINGS $10000,00

    you can see Joe's checking account balance has gone up.
*/
//End-Part-11

        if (bank.doTransaction(joe.getCustomerId(), BankAccount.AccountType.CHECKING, -535)) {
            System.out.println(joe);
        }

//Part-12
/*
        I'll copy that last if statement, and paste a copy right below it, changing the amount to -535. And running this,

                    Customer: Joe (id:000000010000000)
                        CHECKING $500,00
                        SAVINGS $10000,00

                    Customer: Joe (id:000000010000000)
                        CHECKING $535,00
                        SAVINGS $10000,00

                    Customer: Joe (id:000000010000000)
                        CHECKING $0,00
                        SAVINGS $10000,00

    you can see I can empty the account of every dollar.
*/
//End-Part-12

/*
        if (bank.doTransaction(joe.getCustomerId(), BankAccount.AccountType.CHECKING, -0.01)) {
            System.out.println(joe);
        }
*/

//Part-13
/*
        I'll change that to withdraw a penny over. Running that,

                    Customer: Joe (id:000000010000000)
                        CHECKING $500,00
                        SAVINGS $10000,00

                    Customer: Joe (id:000000010000000)
                        CHECKING $535,00
                        SAVINGS $10000,00

                    Customer: Joe (id:000000010000000)
                        CHECKING $0,00
                        SAVINGS $10000,00

                    Insufficient funds

    I can confirm, that I get insufficient funds, and can't actually withdraw the funds, so the checking amount stays at
    535. I'll comment that last change, because I want to have a couple of good transactions in my account.
*/
//End-Part-13

        BankAccount checking = joe.getAccount(BankAccount.AccountType.CHECKING);
        var transactions = checking.getTransactions();
        transactions.forEach((k, v) -> System.out.println(k + ": " + v));

//Part-14
/*
        Next, I'll get the checking account from the Joe's BankCustomer instance. I can get the transactions from that
    account. And I'll print the transactions. Running that,

                    ----(same)
                    Customer: Joe (id:000000010000000)
                        CHECKING $0,00
                        SAVINGS $10000,00

                    1: 000000003214567:00000000000010000000:000000000000001:000000035,00
                    2: 000000003214567:00000000000010000000:000000000000002:-00000535,00

    you can see my transactions printed, in a banky kind of way. You might notice they're not in order. The returned map
    is not guaranteed to be ordered, in the same matter as the map from which it was created. I'll see if I can modify
    those checking account transactions next.
*/
//End-Part-14

        //transactions.put(3L, new Transaction(1,1,Integer.parseInt(joe.getCustomerId()),500).toString());

//Part-15
/*
        I'll try to add a new transaction directly to the transactions variable. Running this code,

                    1: 000000003214567:00000000000010000000:000000000000001:000000035,00
                    2: 000000003214567:00000000000010000000:000000000000002:-00000535,00

    I get same transactions so that's a good thing. I shouldn't be able to just modify this transactions collections,
    once I get a reference to it. But is this code really safe?
*/
//End-Part-15

/*
        System.out.println("---------------------");
        for (var tx : transactions.values()) {
            tx.getCustomerId();
            tx.setAmount(10000.00);
        }
        transactions.forEach((k, v) -> System.out.println(k + ": " + v));
*/

//Part-16
/*
        I'll revert that last change, removing that statement all together. Instead, I'll loop through the transactions,
    and I'll just see if I can tamper with each individual transaction: I'll add a separation line. Looping through the
    transactions of my local variable, I'll set the customer id to 2. I'll change each transaction amount to 10 thousand
    dollars. And I'll again print these transactions. Running that,

                    ---------------------
                    1: 000000003214567:00000000000010000000:000000000000001:000010000,00
                    2: 000000003214567:00000000000010000000:000000000000002:000010000,00

    you see that it not only compiles and runs, but it looks like my transactions have changed? Have they changed on Joe's
    account, or just on this copy? To answer that question, I'll get the joe's account again and the transactions on that.
*/
//End-Part-16

        System.out.println("---------------------");
        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions().
                forEach((k, v) -> System.out.println(k + ": " + v));

//Part-17
/*
        I'll add another separator line. I'll call get account directly on joe, getting the checking account, and chain a
    call to getTransactions, so I'm again getting transactions off of Joe's data, not local variables. I'll print these transactions.
    Running that,

                    ---------------------
                    1: 000000003214567:00000000000010000000:000000000000001:000010000,00
                    2: 000000003214567:00000000000010000000:000000000000002:000010000,00

    I hope you're cringing a little. Ouch! Our client can change data on our Bank Customer's account. We put in a lot of
    effort to prevent this behavior. What could we do to fix this? Maybe we want to create a deep copy of the account.
    This would mean creating a copy, not only of the transactions list, but each individual transaction as well. I could
    also return a different kind of Map, from this method. In fact, this is what I'll do. I'll go to the getTransactions
    method on Bank Account.
*/
//End-Part-17

        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions().clear();
        System.out.println("---------------------");
        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions().
                forEach((k, v) -> System.out.println(k + ": " + v));

//Part-19
/*
        The code doesn't compile, and that's a good thing. I haven't exposed the Transaction instances to the client, but
    I've been able to use the Transaction class in my model. I'll comment out that whole for loop code. Instead of that
    code, I'll try to clear joe's transactions. I'll get the checking account, get the transactions on that, and invoke
    the clear method on that. This code compiles and runs,

                1: 000000003214567:00000000000010000000:000000000000001:000000035,00
                2: 000000003214567:00000000000010000000:000000000000002:-00000535,00
                ---------------------
                1: 000000003214567:00000000000010000000:000000000000001:000000035,00
                2: 000000003214567:00000000000010000000:000000000000002:-00000535,00

    but it has no effect on Joe's transactions. I passed back a modifiable collection, with immutable data. This was more
    secure than passing back an unmodifiable collection, that has mutable instances. You want to be really careful about
    what you return from mutable classes, because the mutable object could be buried several layers deep. You could try
    to do deep copies, or if there's no real reason for the client to have access to the full functionality of the instances,
    return alternative immutable instances that contain the information they may be interested in. Ok, so I hope you got
    something out of that, and can see the value of using immutable classes. Don't assume just using an unmodifiable
    collection is going to be good enough. Combining immutable classes with unmodifiable collections, gives you the best
    protection for your data. In the next lecture, I'll be doing a review of Constructors. There's a surprising amount of
    complexity around constructors, so I want to take another look at them. I'll follow up with examining enum and record
    constructors.
*/
//End-Part-19
    }
}