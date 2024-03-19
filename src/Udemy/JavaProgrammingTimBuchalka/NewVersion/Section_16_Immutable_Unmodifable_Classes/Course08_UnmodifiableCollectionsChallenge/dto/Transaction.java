package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course08_UnmodifiableCollectionsChallenge.dto;

//Part-2
/*
        Emulating code that might get generated by tools, using database tables to produce them. I want this to be in the
    dto package. This class has 4 fields:

        - The routing number unique to a bank.
        - The transaction id, that would be unique within each bank instance.
        - The customer id, here it's an integer.
        - The amount of the transaction, this is a double and could be negative.

    Some DTOs have constructors and some don't. I'm going to include a constructor with all four fields, and I'll generate
    that. Frameworks that generate DTOs include getter and setter methods for every field, so I'll generate those. I'll
    include a toString method, generating that but select no fields, so Select None, and I'll replace the generated code.
    I want to return a formatted String in its place. I'll start with a 15 digit zero filled routing number, followed by
    a 20 digit zero filled customer id, then a 15 digit zero filled transaction id. Lastly 12 digits, zero filled, which
    includes a minus sign for a withdrawal, and two digits for the cents. That's a DTO, that'll be packaged with our
    banking code. Next, I'll work on the changes to my BankAccount class.
*/
//End-Part-2

public class Transaction {

    private int routingNumber;
    private long transactionId;
    private int customerId;
    private double amount;

    public Transaction(int routingNumber, long transactionId, int customerId, double amount) {
        this.routingNumber = routingNumber;
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.amount = amount;
    }

    public int getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(int routingNumber) {
        this.routingNumber = routingNumber;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "%015d:%020d:%015d:%012.2f".formatted(routingNumber, customerId, transactionId, amount);
    }
}
