package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_06_ArrayList_LinkedList_Iterators_Autoboxing.Course08_AutoboxingUnboxing_Challenge;

import java.util.ArrayList;

record Customer2(String name, ArrayList<Double> transactions) {

    public Customer2(String name, double initialDeposit) {
        this(name.toUpperCase(), new ArrayList<>(500));
        transactions.add(initialDeposit);
    }
}

public class Main {
    public static void main(String[] args) {

        Customer2 bob = new Customer2("Bob S", 1000.0);
        System.out.println(bob);

        Bank2 bank = new Bank2("Chase");
        bank.addNewCustomer("Jane A", 500.0);
        System.out.println(bank);


        bank.addTransaction("Jane A", -10.25);
        bank.addTransaction("jane a", -75.01);
        bank.printStatement("jane a");


        bank.addTransaction("Bob s", 100);
        bank.printStatement("bob s");


        bank.addNewCustomer("bob s", 25);
        bank.addTransaction("Bob s", 100);
        bank.printStatement("bob s");

    }
}

class Bank2 {
    private final String name;
    private final ArrayList<Customer2> customers = new ArrayList<>(5000);

    public Bank2(String name) {
        this.name = name;
    }


    private Customer2 getCustomer(String customerName) {

        for (var customer : customers) {
            if (customer.name().equalsIgnoreCase(customerName)) {
                return customer;
            }
        }

        System.out.printf("Customer (%s) wasn't found%n", customerName);
        return null;
    }

    public void addNewCustomer(String customerName, double initialDeposit) {

        if (getCustomer((customerName)) == null) {
            Customer2 customer = new Customer2(customerName, initialDeposit);
            customers.add(customer);
            System.out.println("New Customer added: " + customer);
        }
    }

    @Override
    public String toString() {
        return "Bank2{" +
                "name='" + name + '\'' +
                ", customers=" + customers +
                '}';
    }

    public void addTransaction(String name, double transactionAmount) {
        Customer2 customer = getCustomer(name);
        if (customer != null) {
            customer.transactions().add(transactionAmount);
        }
    }

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
}