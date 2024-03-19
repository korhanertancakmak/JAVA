package CourseCodes.NewSections.Section_10_ArrayList_LinkedList_Iterators_Autoboxing.Course08_AutoboxingUnboxing_Challenge;



/*
Part-1
                                        Autoboxing Challenge with ArrayLists

        In this challenge, you will need to create a simple banking application, with a Customer and Bank type. The "Customer"
    will have a name, and an "ArrayList" of transactions containing "Double" wrapper elements.

  - A customer's transaction can be a credit, which means a positive amount, or it can be a debit, a negative amount.

    The "Bank" will have a name, and an "ArrayList" of customers.

  - The bank should "add a new customer", if they're not yet already in the list.
  - The bank class should allow a customer to "add a transaction", to an existing Customer.
  - This class should also "print a statement", that includes the customer name, and the transaction amounts. This method
    should use unboxing.
End-Part-1
*/

/*
Part-2
        For simplicity, I'm going to add our classes in the Description.txt source file, so they won't be public. But if your
    solution put classes in separate files that's okay as well. Actually, for Customer, I'm going to make that a record,
    and not a class, since there's very little functionality needed. I'll add that now,
End-Part-2
*/

import java.util.ArrayList;

record Customer2(String name, ArrayList<Double> transactions) {

/*
Part-3
        So we have a Customer("Customer2" since my answer also includes it) record, that takes a name, and an ArrayList
    of transactions. It would be nice to create a new Customer, with just a customer's name, and an initial deposit amount.
    Like any class, we can create overloaded constructors in a record, as long as a call to the default constructor is the
    first statement. Let me add this constructor, and I'll talk about it after.
End-Part-3
*/

    public Customer2(String name, double initialDeposit) {
        this(name.toUpperCase(), new ArrayList<Double>(500));
        transactions.add(initialDeposit);
    }

/*
Part-4
        This constructor takes 2 arguments, name and a primitive double, we're calling the initialDeposit. And we first
    call the record's implicit constructor, so we'll pass name, first making it uppercase, and secondly, a new instance
    of a Double ArrayList. Also, we're setting the capacity to 500, which is what I'm estimating will be sufficient for
    most customer's, for a year of transactions. Remember, if you can guess the max size of your ArrayList, you can set
    the capacity this way. This prevents the JVM from incremental re-allocations, as the list grows. After this, we can
    add the initial deposit, to the customer's transaction list. And here, we use auto boxing, passing a double primitive
    variable, to the add method on ArrayList, which auto boxes it, to a Double wrapper instance. So that's a pretty simple
    record, that takes a double primitive argument, then passes that, with auto boxing to the add method on transactions.
    And that's our Customer Record. I'll create a new customer in the main method.
End-Part-4
*/
}

public class Main {
    public static void main(String[] args) {

        Customer2 bob = new Customer2("Bob S", 1000.0);
        System.out.println(bob);
/*
Part-5
        In this code, we create a new customer record, calling it bob, and passing "Bob S", and "$1000", to the constructor.
    Then we simply print bob out.

                    Customer2[name=BOB S, transactions=[1000.0]]

    And we can see, the record prints out the name and transactions of the customer record automatically. Now let's create
    the Bank class. As mentioned previously, my solution is putting everything in the one file, Main. This time, I'm not
    going to use a record. I want more functionality and flexibility for the Bank, so I'll use a class.
End-Part-5
*/

        Bank2 bank = new Bank2("Chase");
        bank.addNewCustomer("Jane A", 500.0);
        System.out.println(bank);

/*
Part-9
        Here, we create a new instance of a bank, add a new customer, Jane, with an initial deposit of $500. And we want
    to print out the information in the bank. Running this code,

                    Customer (Jane A) wasn't foundNew Customer added: Customer2[name=JANE A, transactions=[500.0]]
                    CourseCodes.NewSections.Section_10_ArrayList_LinkedList_Iterators_Autoboxing.Course08_AutoboxingUnboxing_Challenge.Bank2@46f7f36a

    we see that a new customer was added, and we also get the standard toString method text for a class. I want to go back
    to the Bank class, and generate a toString method for this.
End-Part-9
*/

        bank.addTransaction("Jane A", -10.25);
        bank.addTransaction("jane a", -75.01);
        bank.printStatement("jane a");

/*
Part-13
        Running this code,

                    ------------------------------
                    Customer name: JANE A
                    Transactions:
                    $    500,00 (credit)
                    $    -10,25 (debit)
                    $    -75,01 (debit)

    we get the information about Jane A, and a  list of her transactions printed out, formatted, and specifying if the
    transaction was a credit or debit. Notice that it didn't matter what case we used in Jane A's name. It found her customer
    record in each of these instances.

        Ok, so that was the auto-boxing challenge, and we created a simple bank application, that kept track of a list of
    customers. And each customer had a list of transactions. Finally, for good measure, let's test our methods, for a customer
    that we never added, Bob S. Add a $100 credit transaction for bob.
End-Part-13
*/

        bank.addTransaction("Bob s", 100);
        bank.printStatement("bob s");

/*
Part-14
        Running this code,

                    Customer (Bob s) wasn't found
                    Customer (bob s) wasn't found

    we see that in both cases, bob s isn't a customer of our bank. And even though we created a Customer instance for Bob
    S, we can't really add him to our bank. The only way to add a customer to our bank is using the only constructor we
    have, which takes a name, and an initial amount. Ok, let's add him that way, with an initial deposit of $25, and I'll
    change the case.
End-Part-14
*/

        bank.addNewCustomer("bob s", 25);
        bank.addTransaction("Bob s", 100);
        bank.printStatement("bob s");

/*
Part-15
        Running this code,

                    New Customer added: Customer2[name=BOB S, transactions=[25.0]]
                    ------------------------------
                    Customer name: BOB S
                    Transactions:
                    $     25,00 (credit)
                    $    100,00 (credit)

    we get a statement for bob s.
End-Part-15
*/
    }
}

class Bank2 {
    private String name;
    private ArrayList<Customer2> customers = new ArrayList<>(5000);

    public Bank2(String name) {
        this.name = name;
    }

/*
Part-6
        In this class, we have name and an ArrayList of customers. Note the lack of a public access modifier because it's
    in this file and not a separate one. We initialize the customer list with a capacity of 5000, assuming a bank could
    easily have that many customers, as an example. And then we set up a simple constructor, just with the bank name.
    Because we don't have getters and setters, no client code can access the customer list, and this is exactly what we
    want. Before we add a customer, I want to add a method that returns a matching customer from the list, based on the
    customer name. So first, I'll add this method, calling it getCustomer.
End-Part-6
*/

    private Customer2 getCustomer(String customerName) {

        for (var customer : customers) {
            if (customer.name().equalsIgnoreCase(customerName)) {
                return customer;
            }
        }

        System.out.printf("Customer (%s) wasn't found%n", customerName);
        return null;
    }

/*
Part-7
        Again, hopefully, there's no surprises with this code. We use an enhanced for loop, looping through the customer
    list. We compare the customer name, with the name passed as the method's argument, ignoring the case. If we find a
    match, we'll return that match immediately, quitting out of the loop and the method, at the same time. Otherwise, if
    we get to the end of the loop, we haven't found a match, so we return a null from this method which indicates no match.
    Ok, so now it's time for the addNewCustomer method.
End-Part-7
*/

    public void addNewCustomer(String customerName, double initialDeposit) {

        if (getCustomer((customerName)) == null) {
            Customer2 customer = new Customer2(customerName, initialDeposit);
            customers.add(customer);
            System.out.println("New Customer added: " + customer);
        }
    }

/*
Part-8
        This code is pretty straightforward I hope. We take a customer name and an initial deposit amount, and create a
    new Customer record, with these values. First, we call the getCustomer method, and if it returns null, we know we
    need to add a new Customer. So if we get a null back, we create a new Customer record, then add that to the bank's
    customer list. And we print out a statement that we've added a new customer. At this point, we have a bank class,
    which we can use to add customers, and a customer class to add transactions. Now, I want to go to the main method,
    and create a new bank, then add a new customer.
End-Part-8
*/

    @Override
    public String toString() {
        return "Bank2{" +
                "name='" + name + '\'' +
                ", customers=" + customers +
                '}';
    }

/*
Part-10
        This code has been built by using IntelliJ's generate option. When I run this code again,

                    Customer (Jane A) wasn't foundNew Customer added: Customer2[name=JANE A, transactions=[500.0]]
                    Bank2{name='Chase', customers=[Customer2[name=JANE A, transactions=[500.0]]]}

    we get a slightly better idea of what our Bank class consists of. Our bank class doesn't expose its customers, meaning
    there's no getCustomers getter method, or a public getCustomer method. The only way we want anyone to be able to add
    a transaction, is through the bank, with a customer name. So now, I'll add the addTransaction method to bank. Ensuring
    that it's marked public:
End-Part-10
*/

    public void addTransaction(String name, double transactionAmount) {
        Customer2 customer = getCustomer(name);
        if (customer != null) {
            customer.transactions().add(transactionAmount);
        }
    }

/*
Part-11
        This method, allows transactions to happen through a bank instance, which gets a matching customer, and the customer's
    transactions, then adds the transaction, again with the use of auto boxing. Next, we need a way to see the customer's
    transactions, so I'm going to create the method printStatement next: I'll make this one public as well. Looking at the
    addNewCustomer method, I made that one public as well, which is necessary, so we can call it from Main.
End-Part-11
*/

    public void printStatement(String customerName) {
        Customer2 customer = getCustomer(customerName);
        if (customer == null) {
            return;
        }
        System.out.println("-".repeat(30));
        System.out.println("Customer name: " + customer.name());
        System.out.println("Transactions:");
        for (double d : customer.transactions()) {   // unboxing
            System.out.printf("$%10.2f (%s)%n", d, d < 0 ? "debit" : "credit");
        }
    }

/*
Part-12
        The first thing we do in this method, is again, try to locate the customer record, by customerName, calling the
    getCustomer method to do that. Remember that getCustomer is marked as private so is not directly available outside
    of the class. If null is returned, meaning the customer was not found, then we'll simply return from this method,
    doing nothing. We could throw an exception, or return a null, or some other methods, but I'm really just keeping it
    simple here, especially given the method is just printing a statement. If a customer is found, we want to print out
    the customer's name, and the transactions, in a well-formatted way.

        In the second half of the code above, we first print a line, to separate entries, using the repeat method on String
    which I've used before. Then we print the customer name. Note, we use the accessor method on our customer record, and
    not the name passed in on the method. That's because we want the name formatted in uppercase, which was part of the
    record creation. And then we print out the transactions, using a for each loop. And in the (), is the unboxing part
    of the challenge. You can see we've set up a local loop variable, typed with a primitive double. This means every entry
    in our ArrayList, gets unboxed, then assigned to this primitive variable. And then we print out a formatted statement,
    using printf. You'll remember that specifiers have additional options, and we use one here for the transaction amount,
    %10.2f. This prints a double value, with a width of 10, and a precision of 2 decimals. Then we'll print out whether
    the transaction was a debit or credit, in parentheses. We use a ternary operator to check if the amount is less than
    zero, to determine what will get printed. And that's it. I'll add a couple more transactions for Jane, and print her
    statement out, in the main method.
End-Part-12
*/
}