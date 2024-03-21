package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_03_OOP1.Course05_Constructors1;

public class Main {
    public static void main(String[] args) {

        Account acc = new Account(1234567,1000d,
                "Korhan Çakmak","korhanertancakmak@gmail.com",3424241);

        System.out.println("Account number = " + acc.getNumber());
        System.out.println("Account balance = $" + acc.getBalance());

//        acc.setNumber(1234567);
//        acc.setBalance(1000d);
//        acc.setName("Korhan Çakmak");
//        acc.setEmail("korhanertancakmak@gmail.com");
//        acc.setPhoneNumber(3424241);

/*        System.out.println("Account number = " + acc.getNumber());
        System.out.println("Account balance = $" + acc.getBalance());
        System.out.println("Account name = " + acc.getName());
        System.out.println("Account email = " + acc.getEmail());
        System.out.println("Account phone number = " + acc.getPhoneNumber());*/

        acc.depositFunds(255.25);
        acc.withdrawFunds(255.25);
        acc.withdrawFunds(255.25);
        acc.withdrawFunds(1000);
    }
}
