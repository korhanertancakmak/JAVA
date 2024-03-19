package CourseCodes.NewSections.Section_07_OOP1.Course04_ClassesChallenge;

public class Account {
    private Integer number;
    private Double balance;
    private String customerName;
    private String customerEmail;
    private Integer customerPhone;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getName() {
        return customerName;
    }

    public void setName(String name) {
        this.customerName = name;
    }

    public String getEmail() {
        return customerEmail;
    }

    public void setEmail(String email) {
        this.customerEmail = email;
    }

    public Integer getPhoneNumber() {
        return customerPhone;
    }

    public void setPhoneNumber(Integer phoneNumber) {
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
