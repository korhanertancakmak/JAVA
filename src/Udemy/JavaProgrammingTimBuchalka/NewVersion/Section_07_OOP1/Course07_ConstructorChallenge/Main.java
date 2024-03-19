package CourseCodes.NewSections.Section_07_OOP1.Course07_ConstructorChallenge;

/*
Course-37

                                                 Constructor Challenge Exercise

        For this challenge, you'll want to:
    1. Create new class, called Customer, with 3 fields:
     - name
     - credit limit
     - email address
    2. Create the getter methods only, for each field. You don't need to create the setters.
    3. Create 3 constructors for this class:
     - First, create a constructor for all 3 fields which should assign the arguments directly to the instance fields.
     - Second, create a no args constructor that calls another constructor, passing some literal values for each argument.
     - And lastly, create a constructor with just the name and email parameters, which also calls another constructor.
*/

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
