package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_OOP1.Course07_ConstructorChallenge;

public class Customer {
    private String customerName;
    private double customerCreditLimit;
    private String customerEmail;

    public Customer(String customerName, double customerCreditLimit, String customerEmail) {

        System.out.println("The Customer constructor with parameters called");
        this.customerName = customerName;
        this.customerCreditLimit = customerCreditLimit;
        this.customerEmail = customerEmail;
    }

    public Customer() {

        this("Nobody", 1000.00, "nobody@nowhere.com");
        System.out.println("Empty constructor called");
    }

    public Customer(String name, String email) {
        this(name, 0.00, email);
    }
    public String getCustomerName() {
        return customerName;
    }

    public double getCustomerCreditLimit() {
        return customerCreditLimit;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
}
