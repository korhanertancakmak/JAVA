package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_03_OOP1.Course07_ConstructorChallenge;

public class Main {
    public static void main(String[] args) {

        Customer customerKorhan = new Customer("Korhan", 100, "korhan@email.com");

        System.out.println("Customer name: " + customerKorhan.getCustomerName());
        System.out.println("Customer Credit Limit: $" + customerKorhan.getCustomerCreditLimit());
        System.out.println("Customer Email: " + customerKorhan.getCustomerEmail());

        Customer secondCustomer = new Customer();

        System.out.println("Customer name: " + secondCustomer.getCustomerName());
        System.out.println("Customer Credit Limit: $" + secondCustomer.getCustomerCreditLimit());
        System.out.println("Customer Email: " + secondCustomer.getCustomerEmail());

        Customer thirdCustomer = new Customer("Joe", "joe@email.com");

        System.out.println("Customer name: " + thirdCustomer.getCustomerName());
        System.out.println("Customer Credit Limit: $" + thirdCustomer.getCustomerCreditLimit());
        System.out.println("Customer Email: " + thirdCustomer.getCustomerEmail());
    }
}
