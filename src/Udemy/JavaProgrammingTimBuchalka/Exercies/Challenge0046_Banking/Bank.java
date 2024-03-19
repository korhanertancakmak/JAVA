package CourseCodes.NewSections.Exercises0030To0049.Challenge0046_Banking;

import java.util.ArrayList;

public class Bank {

    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<Branch>();
    }

    private Branch findBranch(String branchName) {
        for (var branch : branches) {
            if (branch.getName().equalsIgnoreCase(branchName)) {
                return branch;
            }
        }
        return null;
    }

    public boolean addBranch(String branchName) {

        if (findBranch((branchName)) == null) {
            Branch branch = new Branch(branchName);
            branches.add(branch);
            return true;
        }
        return false;
    }

    public boolean addCustomer(String branchName, String customerName, double initialTransaction) {

        Branch branch = findBranch(branchName);
        if (branch != null) {
            return branch.newCustomer(customerName, initialTransaction);
        }
        return false;
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double transaction) {

        Branch branch = findBranch(branchName);
        if (branch != null) {
            return branch.addCustomerTransaction(customerName, transaction);
        }
        return false;
    }

    public boolean listCustomers(String branchName, boolean printTransaction) {
        if (findBranch(branchName) != null) {
            int indexCustomer = 1;
            if (printTransaction) {
                System.out.println();
                System.out.println("Customer details for branch " + branchName);
                for (var branch : branches) {
                    for (var customer : branch.getCustomers()) {
                        System.out.println("Customer: " + customer.getName() + "[" + indexCustomer + "]");
                        indexCustomer++;
                        int indexTransaction = 1;
                        System.out.println("Transactions");
                        for (var transaction : customer.getTransactions()) {
                            System.out.println("[" + indexTransaction + "]" + " Amount " + transaction);
                            indexTransaction++;
                        }
                    }
                }
            } else {
                System.out.println();
                System.out.println("Customer details for branch " + branchName);
                for (var branch : branches) {
                    for (var customer : branch.getCustomers()) {
                        System.out.println("Customer: " + customer.getName() + "[" + indexCustomer + "]");
                        indexCustomer++;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
