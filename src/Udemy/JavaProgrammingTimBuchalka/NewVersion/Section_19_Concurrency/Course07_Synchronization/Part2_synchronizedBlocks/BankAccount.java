package CourseCodes.NewSections.Section_19_Concurrency.Course07_Synchronization.Part2_synchronizedBlocks;

public class BankAccount {

    private double balance;
    private String name;
    private final Object lockName = new Object();
    private final Object lockBalance = new Object();

//Part-4
/**
        I'll make them both private and final. And these can simply be type Object. The first I'll call lockName, and set
 that to a new Object. The second one I'll call lockBalance. Now I can go to the setName method,
**/
//End-Part-4

    public BankAccount(String name, double balance) {
        this.balance = balance;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        synchronized (lockName) {
            this.name = name;
            System.out.println("Updated name = " + this.name);
        }
    }
//Part-5
/**
        And synchronize on lockName. I'll do the same thing in my deposit method, making that lockBalance.
**/
//End-Part-5

    public double getBalance() {
        return balance;
    }

//Part-2
/**
        I'll add a private final String field to my bank account class, and call it name. I'll include that in my constructor,
 and I'll make it the first argument. I'll assign that to my local field. Now, I want a getter and a setter, so I'll generate
 those. I'll change the setter, first to be synchronized, because I don't want two threads to try to set the last name at
 the same time. I'll also add a println statement in this method, printing out the updated name. I'll output this.name
 here, to confirm the name on the instance was properly set. I'll make my deposit method a synchronized method, so I can
 demonstrate this easier. I'll add synchronized to the declaration, and I'll comment out the synchronized block I had there.
 Getting back to the main method,
**/
//End-Part-2

    public void deposit(double amount) {

        try {
            System.out.println("Deposit - Talking to the teller at the bank...");
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Double boxedBalance = this.balance;
        synchronized (lockBalance) {
            double origBalance = balance;
            balance += amount;
            System.out.printf("STARTING BALANCE: %.0f, DEPOSIT (%.0f)" +
                    " : NEW BALANCE = %.0f%n", origBalance, amount, balance);
            addPromoDollars(amount);
        }
    }

//Part-6
/**
        Now, as you saw earlier, I didn't really need it for the name field, but this does make it a little clearer, what
 the intentions are. It also removes any dependencies on these locks, to your field types. Next, I'm going to add a method
 to this Bank Account method, because the bank is running a promotional event. It's going to gift twenty five dollars to
 anyone who deposits 5000 dollars or more.
**/
//End-Part-6

    private void addPromoDollars(double amount) {

        if (amount >= 5000) {
            synchronized (lockBalance) {
                System.out.println("Congratulations, you earned a promotional deposit.");
                balance += 25;
            }
        }
    }

//Part-7
/**
        I'll make this private and void, and call it addPromoDollars. It takes a double, for the deposit amount. I'll keep
 this code in its own method, because I may change the promo quite a bit. If the amount is greater than or equal to 5000,
 they qualify for this promotion. Here, I'll synchronize on the same lock as the other code that updates the balance, so
 on lock balance. I'll print that the depositor gets the promotional deposit. And I'll update the balance by 25 dollars.
 Since the addPromoDollars method is going to be used in the deposit method, let me move it right below that method. I'll
 call this from the deposit method putting it inside the synchronised block I'll call it as the last statement in this
 block, passing it the deposit amount.

        Let's think about this code for a minute. I'm in a synchronized block, I've got a lock on the lockBalance object.
 My code then calls addPromoDollars, which in turn tries to get a lock on lockBalance. Does this mean my code is going
 to lock up in this scenario? Let's run it and see. When I run that,

                     Deposit - Talking to the teller at the bank...
                     STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 7500
                     Updated name = Tim
                     STARTING BALANCE: 7500, WITHDRAWAL (5000) : NEW BALANCE = 2500
                     STARTING BALANCE: 2500, DEPOSIT (5000) : NEW BALANCE = 7500
                     Congratulations, you earned a promotional deposit.
                     Final Balance: 7525.0

 I can see everything is working normally, and that I was able to get the promotional deposit, with a final balance of
 7 thousand five hundred and 25 dollars. So how did that work? Why didn't the synchronized block in the addPromoDeposit
 wait on getting a lock?

                                        Reentrant Synchronization

        I said that once a thread acquires a lock, all other threads will block, which also require that lock. But in this
 scenario, one thread has acquired a lock, and is calling another method. This is the same thread calling a different
 method, which is also trying to acquire the lock. Because these method calls are executed from the "same thread", any
 nested calls which try to acquire the lock, won't block, because the current thread already has it. This feature is called
 Reentrant Synchronization. Without this, threads could block indefinitely. This concept is built into the Java language,
 and it's based on the monitor mechanism. This is not to be confused with the ReentrantLock class, which I'll be covering,
 along with other locking topics, in the next lecture.
 **/
//End-Part-7

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
}
