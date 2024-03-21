package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_03_OOP1.Course06_Constructor2;


public class Main {
    public static void main(String[] args) {

        Account acc = new Account();

        System.out.println("Account number = " + acc.getNumber());
        System.out.println("Account balance = $" + acc.getBalance());

        Account korhansAccount = new Account("Korhan",
                "korhanertancakmak@gmail.com", "12345");
        System.out.println("AccountNo: " + korhansAccount.getNumber() +
                "; name " + korhansAccount.getCustomerName());
    }
}
