package CourseCodes.NewSections.Section_19_Concurrency.Course07_Synchronization.Part2_synchronizedBlocks;

//Part-1
/**
        In this lecture, I want to pick up where I left off in the last lecture, which was in the Synchronized Project,
 looking at synchronized blocks of code. I've shown you two examples in this code. The first example used the "synchronized"
 keyword on the invoking methods, and you can see that on the withdraw method. The alternative to a synchronized method,
 is the "synchronized statement". This requires us to pass some object instance to that statement, and in my example, in
 the deposit method, I passed the special keyword "this", which means the current instance. In both these cases, a lock
 was acquired on my BankAccount instance. In the synchronized statement, this is explicitly declared. When I use the
 synchronized keyword on a method, the current instance is implicitly locked. But what does it mean when an object is
 locked?

                                            The Object instance monitor

 Every object instance in Java has a built-in intrinsic lock, also known as a monitor lock. A thread acquires a
 lock by executing a synchronized method on the instance, or by using the instance as the parameter to a synchronized
 statement. A thread releases a lock when it exits from a synchronized block or method, even if it throws an exception.
 Only one thread at a time can acquire this lock, which prevents all other threads from accessing the instance's state,
 until the lock is released. All other threads, which want access to the instance's state through synchronized code, will
 block, and wait, until they can acquire a lock. The synchronized statement is usually a better option in most circumstances,
 since it limits the scope of synchronization, to the critical section of code. In other words, it gives you much more
 granular control, over when you want other threads to block. In fact, it can give you more control than that. The
 synchronized block can use a different object, on which to acquire its lock. This means that code, accessing this bank
 account instance, wouldn't have to block entirely. Let me explain this last point, in some code.
 **/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        BankAccount companyAccount = new BankAccount("Tom", 10000);

        Thread thread1 = new Thread(() -> companyAccount.withdraw(2500));
        Thread thread2 = new Thread(() -> companyAccount.deposit(5000));
        Thread thread3 = new Thread(() -> companyAccount.setName("Tim"));
        Thread thread4 = new Thread(() -> companyAccount.withdraw(5000));

        thread1.start();
        thread2.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Balance: " + companyAccount.getBalance());
    }
}

//Part-3
/**
        I next need to change the line, where I'm constructing my instance of bank account, and include a name, and I'll
 use Tom to start. I'll also change one of these withdraw threads, let's say the third one. Here, I'll call my method to
 change the name on the bank account, so setName, and I'll pass Tim to that. Just to make sure we can really see whose
 being blocked, let's give the first two threads a 500 millisecond head start. Ok, so running this code,

                     STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 7500
                     Deposit - Talking to the teller at the bank...
                     STARTING BALANCE: 7500, DEPOSIT (5000) : NEW BALANCE = 12500
                     STARTING BALANCE: 12500, WITHDRAWAL (5000) : NEW BALANCE = 7500
                     Updated name = Tim
                     Final Balance: 7500.0

 each time will be a little different. Sometimes I'll get the first withdrawal thread before I see the Talking to the
 teller statement, and sometimes I won't. But notice, that the thread that updates the name on the account, is blocked,
 just the same as those updating the balance. All synchronized methods, regardless of what they're accessing or doing,
 can't run, until they've acquired the monitor lock, on the current instance. Now, I'll change my setName method, to
 use a synchronized statement instead.

                         public synchronized void setName(String name) {

                             this.name = name;
                             System.out.println("Updated name = " + this.name);
                         }

                                                to

                         public void setName(String name) {

                             synchronized (this) {
                                 this.name = name;
                                 System.out.println("Updated name = " + this.name);
                             }
                         }

 I'll add the synchronized statement around both statements. and I'll again synchronize on "this". Let's see if that helped
 the situation. I'll run the code a couple of times.

                     STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 7500
                     Deposit - Talking to the teller at the bank...
                     STARTING BALANCE: 7500, DEPOSIT (5000) : NEW BALANCE = 12500
                     STARTING BALANCE: 12500, WITHDRAWAL (5000) : NEW BALANCE = 7500
                     Updated name = Tim
                     Final Balance: 7500.0

 You can see that updating the name, is blocked until the conversation with the teller ends. Even though I'm using a
 synchronized block in my setName, I'm synchronizing on an instance, and that's going to get locked by our long running
 deposit method. But I don't have to always synchronize on the current instance, the keyword "this". In fact, in most
 instances, I won't want to synchronize on the current instance. Instead of this, I'll change the argument to this.name,
 so the field name. If I run this,

                     STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 7500
                     Deposit - Talking to the teller at the bank...
                     Updated name = Tim
                     STARTING BALANCE: 7500, DEPOSIT (5000) : NEW BALANCE = 12500
                     STARTING BALANCE: 12500, WITHDRAWAL (5000) : NEW BALANCE = 7500
                     Final Balance: 7500.0

 I can see that the name gets updated before the deposit thread completed. In other words, it doesn't block for the deposit
 method thread. Why couldn't we do this with the synchronized statement for the deposit?

                             public synchronized void deposit(double amount) {

                                 try {
                                         System.out.println("Deposit - Talking to the teller at the bank...");
                                         Thread.sleep(7000);
                                     } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                     }

                                     double origBalance = balance;
                                     balance += amount;
                                     System.out.printf("STARTING BALANCE: %.0f, DEPOSIT (%.0f)" +
                                            " : NEW BALANCE = %.0f%n", origBalance, amount, balance);
                             }

                                                                to

                             public void deposit(double amount) {

                                 try {
                                     System.out.println("Deposit - Talking to the teller at the bank...");
                                     Thread.sleep(7000);
                                 } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                 }

                             Double boxedBalance = this.balance;
                             synchronized (boxedBalance) {
                                 double origBalance = balance;
                                 balance += amount;
                                 System.out.printf("STARTING BALANCE: %.0f, DEPOSIT (%.0f)" +
                                        " : NEW BALANCE = %.0f%n", origBalance, amount, balance);
                             }
                             }


 Let's go back to that code, and again, remove the synchronized modifier, and uncomment out that block. Now I'll change
 "this" to "this.balance". IntelliJ doesn't like that change, so let's see why. The message is telling me, that required
 type is an object, and we've provided a double. An intrinsic lock or monitor is only available on an object, not on any
 of the primitive types. What if I created a local variable, a Double wrapper type and used that instead. I'll set this
 variable to the current value of balance. I'll change the argument to the synchronized statement, and pass it my boxedBalance.
 This code compiles, but IntelliJ does have a warning there. First let's run this code.

                     Deposit - Talking to the teller at the bank...
                     STARTING BALANCE: 10000, WITHDRAWAL (2500) : NEW BALANCE = 7500
                     Updated name = Tim
                     STARTING BALANCE: 7500, WITHDRAWAL (5000) : NEW BALANCE = 2500
                     STARTING BALANCE: 2500, DEPOSIT (5000) : NEW BALANCE = 7500
                     Final Balance: 7500.0

 This seems to work, but we don't really have the right example, to test this kind of change. Let's see what IntelliJ is
 warning us. Hover over that, "synchronized (boxedBalance)", you can see the statements "Attempt to synchronize on an
 instance of a value-based class". That's a little vague. Essentially we are attempting synchronization on a local variable.
 'boxedBalance' in this case. Why is synchronization on a local variable a bad thing? Think about this, for a second or
 two. Local variables are stored on the thread's stack, so each thread would have it's own copy of this boxedBalance
 variable. The lock would be on the thread's stack instance only. When you lock on a local variable, it's not going to
 be a shared lock, so it's useless. The only local variable that might work, is a String, as long as it's been interned
 to the String pool. To simplify the rules for yourself, just don't use any local variable or method argument in this
 synchronized statement. Ok, that's fine, but how do we solve this problem then? What can we put in here, besides this?
 You might remember that we talked about using BigDecimal, when you're working on financial transactions, but let's just
 put that aside for now, and keep this as a double. What now then? Well, you can create lock objects, whose sole purpose
 is to manage locking for different fields. In my BankAccount class, I'll create two lock objects.
 **/
//End-Part-3