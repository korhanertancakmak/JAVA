package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_19_Concurrency.Course07_Synchronization.Part1_SynchronizedMethods;

public class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

//Part-2
/**
        I'll set up a private variable, a double for the balance. I'll generate a constructor, using that field. I'll also
 generate a getter for this field. I'll set up a method to deposit funds.
**/
//End-Part-2

    public void deposit(double amount) {

        try {
            System.out.println("Deposit - Talking to the teller at the bank...");                  // Added after Part-6
            //Thread.sleep(100);                                                                   // Commented via Part-6
            Thread.sleep(7000);                                                              // Added after Part-6
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (this) {
            double origBalance = balance;
            balance += amount;
            System.out.printf("STARTING BALANCE: %.0f, DEPOSIT (%.0f)" +
                    " : NEW BALANCE = %.0f%n", origBalance, amount, balance);
        }
    }

//Part-3
/**
        This will be a public method, void return type, and it'll take a double, for the deposit amount I'm going to have
 this thread sleep a short time, 100 ms, representing perhaps the mechanics of depositing cash. That goes in a try catch.
 I'll throw the exception but as a runtime exception. I'll store the original balance in a variable, to print it. I'll
 add the amount to the balance. I'll print the starting balance, the deposit amount, and the new balance. That's it for
 the deposit method. To create the withdraw method, I'll copy this method, and paste a copy directly below.
**/
//End-Part-3

    public synchronized void withdraw(double amount) {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        double origBalance = balance;
        if (amount <= balance) {
            balance -= amount;
            System.out.printf("STARTING BALANCE: %.0f, WITHDRAWAL (%.0f)" +
                    " : NEW BALANCE = %.0f%n", origBalance, amount, balance);
        } else {
            System.out.printf("STARTING BALANCE: %.0f, WITHDRAWAL (%.0f)" +
                    ": INSUFFICIENT FUNDS!", origBalance, amount);
        }
    }

//Part-4
/**
        I'll rename this from deposit to withdraw. I'll also change the plus to a minus, since an amount is being withdrawn,
 and I'll change DEPOSIT in the print statement, to WITHDRAWAL. For a withdrawal, I should really check that the balance
 is higher than the amount being withdrawn, so I'll add this code. I'll add an if statement, so that the amount is only
 subtracted if the balance is higher than the amount. I'll include an else statement. I'll print the starting balance
 again, and the withdrawal amount, But in this case, I'll say there were INSUFFICIENT FUNDS. Ok, so that's all I need for
 this very simple bank account class. Now, I'll get back to my main method on the Main class.
 **/
//End-Part-4
}

//Part-6
/**
        Let's say, as an example, that any deposits to this account, must always be done at a bank, so you have to physically
 go in to the bank. And this process, includes talking to the teller, with all the usual chit chat about weather and so
 on. I'll first add a println statement, before the sleep, and print that the person is talking to the teller at the bank.
 I'll add some time to this Thread.sleep, in the deposit method. You can imagine this could really be 5 minutes or longer,
 but I'll make it 7 seconds, to give you the idea. I also want to just throw in another withdraw thread. I'll call it
 thread 4, and this thread will withdraw 5000 dollars. I'll kick it off with the others. I'll include it in the join here.
 Later, I'll show you much better ways to manage multiple threads like this, when I introduce you to thread pools. But
 for now, this is good enough for this demonstration. So what happens if I run this?

                 STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 7500
                 STARTING BALANCE: 7500, WITHDRAWAL (5000) : NEW BALANCE = 2500
                 STARTING BALANCE: 2500, WITHDRAWAL (2500) : NEW BALANCE = 0
                 Deposit - Talking to the teller at the bank...

 You can see, all my threads block, until my employee finishes talking to the bank teller. This is not a good thing. I've
 synchronized an entire method, which included long running code that had nothing to do with the critical section of code.
 You have to be really careful when you use the synchronized keyword on a method, that you're not unintentionally creating
 this kind of situation. What do you do now though? Your depositing employee doesn't have a choice, he has to go through
 this deposit process as it's laid out. But that really shouldn't stop your other employees wherever they are, from
 withdrawing money from the automatic tellers. We can get more targeted, or synchronize on just a part of code. I'll do
 this here in the deposit method. I'll remove the synchronized keyword from the method. This time I'm going to create a
 specialized code block, wrapping the statements that deal with my balance.

                 double origBalance = balance;
                 balance += amount;
                 System.out.printf("STARTING BALANCE: %.0f, DEPOSIT (%.0f)" +
                 " : NEW BALANCE = %.0f%n", origBalance, amount, balance);

                                            to

                 synchronized (this) {
                     double origBalance = balance;
                     balance += amount;
                     System.out.printf("STARTING BALANCE: %.0f, DEPOSIT (%.0f)" +
                     " : NEW BALANCE = %.0f%n", origBalance, amount, balance);
                 }

 I'll type the synchronized key word here, this time I need parentheses, and then an opening curly brace. I have to end
 this synchronized code block with a closing brace. I've got an error here, because when I use the synchronized key word
 here, I have to pass it an object instance. But What object instance? That's a really good question, and one that's open
 for debate. For now, I'll put the keyword "this" in there, meaning the current instance of this bank account. You'll see
 "this" used a lot for these synchronized blocks, but in the next lecture, I'll talk about what all of this really means,
 and some best practices. For now, I'll add this, to satisfy the requirement of putting something there. I'll run this code.

                     Deposit - Talking to the teller at the bank...
                     STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 7500
                     STARTING BALANCE: 7500, WITHDRAWAL (5000) : NEW BALANCE = 2500
                     STARTING BALANCE: 2500, WITHDRAWAL (2500) : NEW BALANCE = 0
                     STARTING BALANCE: 0, DEPOSIT (5000) : NEW BALANCE = 5000
                     Final Balance: 5000.0

 Now you can see, that none of the withdrawal threads got stuck waiting for the teller conversation to end, and the
 transactions are still occurring with integrity. I'll be talking about synchronized blocks, and what that parameter
 really means, as well as other alternatives in the next lecture.
 **/
//End-Part-6