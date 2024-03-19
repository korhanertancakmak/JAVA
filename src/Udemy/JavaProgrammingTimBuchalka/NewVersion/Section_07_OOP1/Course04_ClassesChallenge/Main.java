package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_OOP1.Course04_ClassesChallenge;

/*
Course-34

                                      Classes Challenge Exercise

        So it's challenge time. I want to challenge your understanding of the previous classes. So here, what i want you
    to do:

            Object Oriented Challenge

        Create a new class for a bank account. Crate fields for account characteristics like:
  - account number
  - account balance
  - customer name
  - email
  - phone number

        Create getters and setters for each field. Create 2 additional methods:
  - one for depositing funds into the account
  - one for withdrawing funds from the account

        A customer should not be allowed to withdraw funds, if that withdrawal takes their balance negative. Create a new
    project called ClassesChallenge, with the usual Main Class with the usual main method. You'll create an instance of an
    Account class, and then test your withdraw and deposit methods. You'll print information to the console, that confirms
    what the balance is after the methods are called.

        So, the challenge here is to create the Bank Account Blueprint, that has 5 instance fields. You want to make this
    class encapsulated, so you'll make all your attributes private, and set up getter and methods for your attributes. In
    addition, you'll have 2 behavioral methods, for depositing funds, and withdrawing funds. In addition to this class,
    you'll set up a Main class, with a main method, that creates at least 1 instance of the Bank Account class, and simulates
    depositing and withdrawing money from the account. And you may also want to add some System.out.println statements, to
    the 2 methods above as well, to confirm how much was deposited or withdrawn.
*/

public class Main {
    public static void main(String[] args) {

        Account acc = new Account();

/*
        acc.setNumber(1234567);
        acc.setBalance(1000d);
        acc.setName("Korhan Çakmak");
        acc.setEmail("korhanertancakmak@gmail.com");
        acc.setPhoneNumber(3827846);

        System.out.println("Account number = " + acc.getNumber());
        System.out.println("Account balance = $" + acc.getBalance());
        System.out.println("Account name = " + acc.getName());
        System.out.println("Account email = " + acc.getEmail());
        System.out.println("Account phone number = " + acc.getPhoneNumber());
*/

        acc.depositFunds(255.25);
        acc.withdrawFunds(255.25);
        acc.withdrawFunds(255.25);
        acc.withdrawFunds(1000);
    }
}
