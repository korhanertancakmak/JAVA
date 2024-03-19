package CourseCodes.NewSections.Section_10_ArrayList_LinkedList_Iterators_Autoboxing.Course08_AutoboxingUnboxing_Challenge;

import java.util.ArrayList;

public class MyAnswer {
    public static void main(String[] args) {

/*
Part-1
                                        Autoboxing Challenge with ArrayLists

        In this challenge, you will need to create a simple banking application, with a Customer and Bank type.

  - The "Customer" will have a name, and an "ArrayList" of transactions containing "Double" wrapper elements.
  - A customer's transaction can be a credit, which means a positive amount, or it can be a debit, a negative amount.

    The "Bank" will have a name, and an "ArrayList" of customers.
      - The bank should "add a new customer", if they're not yet already in the list.
      - The bank class should allow a customer to "add a transaction", to an existing Customer.
      - This class should also "print a statement", that includes the customer name, and the transaction amounts. This
      method should use unboxing.
End-Part-1
*/

    }
}


record Customer(String name, ArrayList<Double> transactions) {
    public Customer(String name, ArrayList<Double> transactions) {
        this.name = name;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(double transaction) {
        this.transactions.add(transaction);
    }

}

class Bank {
    private String name;
    private ArrayList<Customer> customers;

    public Bank(String name, ArrayList<Customer> customers) {
        this.name = name;
        if (findCustomerIndex(this.name, customers) < 0) {
            System.out.println("This customer already exists!");
        } else {
            this.customers = new ArrayList<Customer>();
        }
    }

    public static int findCustomerIndex(String name, ArrayList<Customer> customers) {
        int index = 0;

        if (customers.get(customers.size() - 1).name().equalsIgnoreCase(name)) {
            return customers.size() - 1;
        }

        for (Customer customer : customers) {
            if (customer.name().equalsIgnoreCase(name)) {   // Does the customer, trying to be added, exist
                return index;
            }
            index++;
        }
        return -1 ;
    }

    public boolean addTransaction(String name, double transaction) {
        int index = findCustomerIndex(this.name, this.customers);
        if (index > 0) {
            customers.get(index).addTransaction(transaction);
            return true;
        } else {
            System.out.println("This customer does not exist!");
            return false;
        }
    }

    public static void printStatement(String name, ArrayList<Customer> customers) {
        int index = findCustomerIndex(name, customers);
        System.out.println("Name : " + name);
        System.out.println("Transactions: ");
        System.out.println(customers.get(index).transactions().toString());
    }
}