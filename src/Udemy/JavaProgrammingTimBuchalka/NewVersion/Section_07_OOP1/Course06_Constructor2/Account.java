package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_OOP1.Course06_Constructor2;

public class Account {
    private int number;
    private Double balance;
    private String customerName;
    private String customerEmail;
    private String customerPhone;

    public Account() {

        this(1234567, 2.5, "Default name", "Default address", "Default phone");
        System.out.println("Empty constructor called");
    }

    public Account(int number, double balance, String customerName, String email, String phone) {
        System.out.println("Account constructor with parameters called");
        this.number = number;
        this.balance = balance;
        this.customerName = customerName;
        customerEmail = email;
        customerPhone = phone;
    }

    public Account(String customerName, String customerEmail, String customerPhone) {
        this(99999, 100.55, customerName, customerEmail, customerPhone);
//        this.customerName = customerName;
//        this.customerEmail = customerEmail;
//        this.customerPhone = customerPhone;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public String getEmail() {
        return customerEmail;
    }

    public void setEmail(String email) {
        this.customerEmail = email;
    }

    public String getPhoneNumber() {
        return customerPhone;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.customerPhone = phoneNumber;
    }

    public void withdrawFunds(double withdrawAmount) {

        if ((balance - withdrawAmount) <= 0) {
            System.out.println("You cannot withdraw that amount, because there isn't much money in the balance! Your balance is " +
                    balance);
        } else {
            balance -= withdrawAmount;
            System.out.println(withdrawAmount + " withdrawn from your account and your balance is now $" + balance);
        }
    }

    public void depositFunds(double depositAmount) {

        balance += depositAmount;
        System.out.println(depositAmount + " deposited to your account and your balance is now $" + balance);
    }
}
