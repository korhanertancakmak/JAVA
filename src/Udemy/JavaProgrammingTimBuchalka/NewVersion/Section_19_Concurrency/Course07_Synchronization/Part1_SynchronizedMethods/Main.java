package CourseCodes.NewSections.Section_19_Concurrency.Course07_Synchronization.Part1_SynchronizedMethods;

//Part-1
/**
        In the last couple of lectures, I introduced you to some of the problems you can encounter in a multi-threaded
 environment. In this lecture, I want to talk to you about another tool you can use, to ensure your code is thread-safe.
 I've created a new project called Synchronization, and I've got my Main class. Before I start coding the main method,
 I want to create a new BankAccount class, that multiple threads will want to access. Again, I'll create this in the same
 package.
 **/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        BankAccount companyAccount = new BankAccount(10000);

        Thread thread1 = new Thread(() -> companyAccount.withdraw(2500));
        Thread thread2 = new Thread(() -> companyAccount.deposit(5000));
        Thread thread3 = new Thread(() -> companyAccount.withdraw(2500));
        Thread thread4 = new Thread(() -> companyAccount.withdraw(5000));               // Added after Part-6

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();                                                                       // Added after Part-6

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();                                                                    // Added after Part-6
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Balance: " + companyAccount.getBalance());

//Part-5
/**
        I'll start with creating a variable, which I'll call companyAccount, and that's a bank account, so new BankAccount,
 and I'll start this account with an initial amount of 10 thousand dollars. Now, I'll set up three threads. Thread 1 will
 withdraw 25 hundred dollars. Thread2 will deposit 5000 dollars into the account. Thread3 will also withdraw 25 hundred
 dollars. I should always have enough funds to cover the withdrawal, no matter what happens. I'll start up these threads
 asynchronously. Which just means I'll call start on each of my threads. Don't forget, there's no guarantee which thread
 will really start first, when I call them consecutively like this. Lastly, I'll start thread 3. After all these threads
 have done their work, I want to print out the final balance of the company's bank account. I can do this by joining all
 three threads to the main thread, and again the order doesn't really matter. When I use join, I need a try catch block,
 or specify it in a throws clause, so I'll use the try block. And I'll just call thread1.join, then thread2, then thread3.
 I need the catch clause. I'll just print any exception out. Then I'll print the company's final balance. If you do the
 math here, you can see I started with ten thousand dollars, I'm depositing 5000. I'm also withdrawing a total of 5000
 dollars, so I should end up with ten thousand dollars as the balance, after all the transactions, however you do the
 math. I'll run this,

                     Deposit - Talking to the teller at the bank...
                     STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 7500
                     STARTING BALANCE: 10000, DEPOSIT (5000) : NEW BALANCE = 7500
                     STARTING BALANCE: 7500, WITHDRAWAL (2500) : NEW BALANCE = 5000
                     Final Balance: 5000.0

 you may start to notice some weird output. I'll run it several times. If I run this often enough, I'll get numbers that
 aren't ten thousand, so I'm seeing 5000 sometimes,

                     Deposit - Talking to the teller at the bank...
                     STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 12500
                     STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 12500
                     STARTING BALANCE: 7500, DEPOSIT (5000) : NEW BALANCE = 12500
                     Final Balance: 12500.0

 and 12 thousand 500, and so on. Multi-tasking math isn't very good here. Again, notice that the order of the tasks being
 printed isn't consistent either, and the information being printed doesn't look right. Usually the starting balance won't
 line up with the previous statements' new balance. That might be due to lag in the print statement itself, but even in
 the same printed line, you may see math that isn't right. We don't really know which thread will be exercising the methods
 first, or when they'll be paused because of time slicing, or even if they'll be paused as part of the printing operation.
 I can see though, that all three threads printed a statement, and that the amounts of the deposit, and withdrawal are
 correct. After that, all bets are off. This problem could be caused by the caching of the balance by each thread, memory
 inconsistency in other words, or thread interference, or both. In fact, let's just see what happens if I make the balance,
 on the BankAccount be volatile. I'll go back to the main method, and run my code.

                     Deposit - Talking to the teller at the bank...
                     STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 7500
                     STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 7500
                     STARTING BALANCE: 7500, DEPOSIT (5000) : NEW BALANCE = 12500
                     Final Balance: 12500.0

 Again, I'm going to run this repeatedly 5 or 10 times, to see if that modifier will fix what I was seeing here. The answer
 to that is no. I've still got problems. This is because of the threads getting in, and changing the balance in multiple
 spots, and in multiple methods. One task could be subtracting, at the same time another is adding, and since these use
 compound assignment operators, and I'm working with double values, none of these are atomic operations. This is certainly
 a valid scenario that might occur, so our code should be able to handle this, but how do we fix this?

        I'm going to revert that last change, and remove volatile from the balance. There's another solution, that deals
 with both problems, of caching and thread interference, and this is using the synchronized keyword. I'll go back to the
 Bank Account Class. I'll add the word synchronized to any method that will modify my balance. I only have two, so I'll
 add this modifier to both the deposit and the withdraw methods. And that's that. I'll run the code again.

                     STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 7500
                     STARTING BALANCE: 7500, WITHDRAWAL (2500) : NEW BALANCE = 5000
                     STARTING BALANCE: 5000, DEPOSIT (5000) : NEW BALANCE = 10000
                     Final Balance: 10000.0

 This time, if I read my three statements, in the order they're printed, the math seems logical. I first do a deposit of
 withdrawal of 2500, my starting balance is 10000 dollars, so my new balance is 7500. Again, your output might be in a
 different order, and mine may change if I run it again. Next, there's a deposit of 5000 dollars, and at that time, my
 starting balance is reflecting the previous deposit, so its 75 hundred and the balance then becomes 12 thousand 500.
 Lastly, there's a withdrawal of 2500, with the starting balance of 12 thousand 500, so I get 10000. That's also my final
 balance. I'll run this again multiple times. Occasionally, I'll see the order change, and the deposit will be second or
 third, but no matter what, the math is correct, for all print statements, and it's right for the final balance. Let's
 talk about the synchronized keyword a little bit, when you apply it to methods on your class.

                                                Synchronized Methods

        First, different invocations of synchronized methods, on the same object, are guaranteed not to interleave. When
 one thread is executing a synchronized method for an object, all other threads that invoke synchronized methods for the
 same object, block, and suspend their execution, until the first thread is done with the object. Threads have to sit and
 wait, for access to that code block. When a synchronized method exits, it ensures that the state of the object is visible
 to all threads, similar to what the volatile key word would do. You might ask, if we're making threads sit around and
 wait, aren't we kind of defeating the purpose of a multi-threading environment? And the answer to that is yes, for that
 bit of code that is updating the balance. To be clear, if a class has three synchronized methods, then only one of these
 methods can ever run at a time, and only by one thread. This is why it's really important to ensure that the code, in
 your synchronized methods is limited, to just code that has access to the shared object. This is called the critical
 section.

                                                 Critical Section

        The critical section is the code that's referencing a shared resource like a variable. Only one thread at a time
 should be able to execute a critical section. When all critical sections are synchronized, the class is thread safe.
 Let's now talk about what a critical section is, looking at this code, in the bank account class.
 **/
//End-Part-5
    }
}
