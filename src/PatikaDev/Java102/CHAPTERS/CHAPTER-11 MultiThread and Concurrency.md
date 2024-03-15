# MultiThread and Concurrency

## Creating and Using Java Threads

When we run any application in Java, a main thread is created by default. 
In addition to this main thread, developers can run sub-threads. 
We have already mentioned this. 
Now let's examine how to define and run Thread in Java language.

There is a class called "Thread" to create threads in Java. 
Thus, we simply open a thread. 
Of course, we should not forget that opening a thread is a task that costs the system. 
Continuously producing Threads negatively affects resource usage. 
Therefore, there is the concept of Thread Pooling to solve this problem.

These costly items are initially created in a certain amount and placed in the pool ready. 
Those who need a thread use a thread from this pool and return it to the system. 
Thus, in addition to performance gains, resource utilization is also improved.

```java  
Thread thread = new Thread();
```

As seen above, we created an object of the "Thread" class and created a thread. 
We will need to call the “start” function to start this thread.

```java  
thread.start();
```

Thus, our thread will continue to run until it finishes its work. 
However, in the example above, we did not give the thread a piece of code to run. 
There are two ways to give this.

1. It is necessary to create a subclass that inherits from the “Thread” class and override 
its “run” function.
2. Creating a subclass that inherits from the “Runnable” interface and overriding 
the “run” function, then sending it as an object to the constructor of the “Thread” class.

```java  
public class SimpleThread extends Thread { 
    @Override 
    public void run() {
		// We get the name of the Thread currently running.
        String threadName = Thread.currentThread().getName();
		System.out.println("My summation " + threadName + " is started!");
		int total = 0;
		for(int i=0; i < 1000; i++) {
			total += i;
		}
		System.out.println("Total: " + total);
	}
}
```

Above, we defined a class named "SimpleThread" that inherits from Java's "Thread" class. 
We write the codes we want to run in Thread into the "run" method in this class. 
This piece of code will be executed simultaneously on any CPU at the operating system level. 
It should not be forgotten that the thread we created is a sub-thread of the Java console 
application we prepared.

```java  
SimpleThread simpleThread = new SimpleThread(); 
simpleThread.start();
```

We create an object of the “SimpleThread” class. 
Then, when we call the “start” function, the operating system creates a Thread resource for us,  
and the “run” function that we override in the “SimpleThread” class begins to be executed. 
The codes in the “run” function are now processed in a separate Thread. 
Likewise, when we create another object and call "start," a new Thread is created 
and another thread is created.

```java  
SimpleThread simpleThread2 = new SimpleThread(); 
simpleThread2.start();
```

The results are as follows. 
As can be seen, two different Threads were run at the same time.

```java  
My summation Thread-0 is started! 
Total: 499500 
My summation Thread-1 is started! 
Total: 499500
```

## Runnable Interface

We can run the piece of code as a thread by giving objects of type Runnable to threads.

```java  
public class SimpleRunnable implements Runnable { 
    @Override 
    public void run() { 		
        // We get the name of the Thread currently running.
        String threadName = Thread.currentThread().getName();
        System.out.println("My summation " + threadName + " is started!"); 		
        int total = 0; for(int i=0; i < 1000; i++) {
			total += i;
		}
        System.out.println("Total: " + total); 	
    }
}
```

Above, we defined a class named "SimpleRunnable" that inherits from the "Runnable" interface. 
We write the code to be run in the thread by overriding the "run" method in this class.

```java  
// We create an object of the "SimpleRunnable" class, which inherits from the Runnable interface.
SimpleRunnable simpleRunnable = new SimpleRunnable(); 
// We send the Runnable type object to the Thread constructor.
Thread simpleThread3 = new Thread(simpleRunnable); 
// When we call the start function, the "run" function in the "SimpleRunnable" class will be executed.
simpleThread3.start();
```

We create an object from our class of type “Runnable.” 
Then, we create an object from the Thread class and send the "Runnable" type object 
we created into the constructor method. 
With this, we define three Threads.

```java  
My summation Thread-0 is started! 
My summation Thread-1 is started! 
Total: 499500 
Total: 499500 
My summation Thread-2 is started! 
Total: 499500
```

The results from three threads are above.

Note: With Thread.currentThread() we can get the reference of the currently actively running thread. 
The Thread object we get here represents the code executed in the thread.

## Stopping and Holding a Thread

### Putting the thread on hold

If we want to make a thread wait for a certain period of time in Java, 
we can use the "sleep" function belonging to the Thread class.

The “sleep” function expects a value in milliseconds. 
So, for example, the value 3000 corresponds to 3 seconds.

```java  
try {
    Thread.sleep(10L * 1000L);
} catch (InterruptedException e) {
    e.printStackTrace();
}
```

With "Thread.sleep" above, the thread is put on hold for 10 seconds. 
Since the “sleep” function is a function that can throw errors, 
as we have seen in previous topics, 
it should be checked in the try-catch block, or 
we need to throw the error, it throws one higher with the “throws” keyword.

### Stop the thread

When a thread starts running, it starts being executed by a CPU. 
There is a "stop" function in the Thread class to stop the thread.

However, this function does not guarantee to stop the thread. 
Therefore, when designing the code to run in Thread, 
the developer must code how to stop the running piece of code. 
For this reason, we can guarantee the termination of the code piece 
by keeping a boolean value in the Thread class or a class derived from the Runnable interface.

Let's examine this with an example.

```java  
public class ThreadSleeper {
	public static void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
```

We created a class called “ThreadSleeper.” 
The “sleep” function in this class allows the thread to wait for a certain period of time.

Then, we update the piece of code we wrote in the "run" method in our "SimpleRunnable" class. 
We defined a boolean variable named “live” to stop the running code snippet. 
With this variable, we stop the work running in the Thread.

```java  
public class SimpleRunnable implements Runnable {
	private boolean live = true;
	@Override 
    public void run() {
		// We get the name of the Thread currently running.
		String threadName = Thread.currentThread().getName();
		System.out.println("My summation " + threadName + " is started!");
		int total = 0;
		while(live) {
			total += 1;
			// We sleep it for a half second.
			ThreadSleeper.sleep(500);
		}
		System.out.println("Total: " + total);
	}
	public void stop() {
		this.live = false;
	}
}
```

We start the thread with the start function. 
Then we let the main program wait for 10 seconds. 
When the waiting is over, we call the "stop" function in "SimpleRunnable" 
and stop the running code piece.

```java  
// We create an object of the "SimpleRunnable" class, which inherits from the Runnable interface.
SimpleRunnable simpleRunnable = new SimpleRunnable();

// We send the Runnable type object to the Thread constructor.
Thread simpleThread3 = new Thread(simpleRunnable);

// When we call the start function, the "run" function in the "SimpleRunnable" class will be executed.
simpleThread3.start();
ThreadSleeper.sleep(10000);
simpleRunnable.stop();
```

## Critical Sections and Race Condition

There is no need for synchronization when applications running 
as a single thread access the memory area and other system resources. 
The program runs and uses resources, then returns the resources to the system. 
However, in multithreading programming, if more than one thread attempts 
to access a common resource at the same time, it will be necessary 
to grant them access sequentially.

While one thread is using the resource, the others must wait for it. 
These shared common areas used by more than one thread are called "Critical Sections." 
The best example of this is changing the value of a variable. 
Because changing the value of the variable means making a change in the memory area. 
Threads that use such a common resource must access this "Critical Section" 
sequentially while reading and writing.

```java  
public class QMatic implements Runnable { 
    private int orderNo; 	
    public QMatic() { 
        this.orderNo = 0; 	
    }
    @Override 
    public void run() { 		
        // a little bit delay to see race condition!
        ThreadSleeper.sleep(50); 		
        // Critical section for all threads! 
        this.orderNo = this.orderNo + 1; 		
        StringBuilder builder = new StringBuilder(); 		
        builder.append(Thread.currentThread().getName());
        builder.append(" thread got "); 
        builder.append(this.orderNo); 
        builder.append(" from Qmatic!"); 
		System.out.println(builder.toString());
	}
}
```

In the example above, imagine that there is a device in a bank branch 
whose sequence number is taken. 
The arriving customer takes a receipt according to the order 
and sees his number. 
The value of the variable named "orderNo" is changed in the "run" method 
in the class named "QMatic." 
When customers press the device button, the sequence number increases by one.

If all customers get their numbers from a single device in the branch, 
there is no problem. 
One device, one Thread! 
However, imagine that the branch manager adds one of these devices to the 
branch for efficiency. 
We had two number taking devices.

However, even if there are two devices, we still need to give the sequence 
numbers consecutively and consistently. 
Two Thread 1 Critical Section! 
"Two devices" means two Threads for us, and the sequence number means 
"Critical Section" for us. 
We need to manage the sequence number here properly.

In the sample code above, the “Critical Section” section is :

```java  
this.orderNo = this.orderNo + 1; 
```

This may seem like a one-line command, but behind the scenes, at the processor level, 
this process consists of multiple steps.

* ***Qmatic1*** : Reads the value in this.orderNo variable from memory as 0.
* ***Qmatic2*** : Reads the value in this.orderNo variable from memory as 0.
* ***Qmatic2*** : Adds 1 to the current value 0.
* ***Qmatic2*** : Changes the “orderNo” variable to 1. 
The value of the orderNo variable is now 1.
* ***Qmatic1*** : Adds 1 to the current value 0.
* ***Qmatic1*** : Changes the “orderNo” variable to 1. 
The value of the orderNo variable is now 1.

As can be seen, if reading and writing operations for threads are not synchronized, 
we can give the same sequence number to more than one person. 
This event is called "Race Condition." 
Because Threads compete with each other to access the common resource.

If the first accessor starts performing its operations, 
but other Threads do not wait for it to finish its work, 
inconsistent situations as above occur.
Therefore, access of Threads to the common resource must be sequential.

Results for 10 Threads created:

```java  
Thread-2 thread got 1 from Qmatic! 
Thread-9 thread got 1 from Qmatic! 
Thread-1 thread got 1 from Qmatic! 
Thread-0 thread got 2 from Qmatic! 
Thread-7 thread got 2 from Qmatic! 
Thread-3 thread got 2 from Qmatic! 
Thread-4 thread got 2 from Qmatic! 
Thread-8 thread got 2 from Qmatic!
Thread-5 thread got 4 from Qmatic! 
Thread-6 thread got 4 from Qmatic!
```

### "Synchronized" Keyword in Java

Reminders:
* ***Critical Region*** : In applications using Thread, 
it is the memory region that is shared and can be processed.
* ***Race Condition*** : It is a situation where more than one thread 
accesses the shared memory region at the same time 
and performs operations in the memory region at the same time.

One of the methods that can be used to prevent "Race Condition" 
in code areas with "Critical Section" is the "synchronized" keyword. 
With this keyword, the "Critical Section" code region can be opened 
to sequential access between Threads.

Briefly, the sequential access logic is as follows. 
The first thread to reach the critical region locks this region to 
indicate that it is processing. 
In this way, other threads understand that a thread is executing inside 
and can wait for the process to finish. 
When the thread that completed the transaction exits, 
the lock is removed and the transaction is reopened for waiting threads. 
When another thread waiting for the operation enters, 
it restricts access again by using the lock structure and continues in a loop.

For example;

This will create a big problem when one thread is writing to a file 
and another thread wants to do the same operation. 
In such cases, the relevant resource must be blocked from access 
with a lock mechanism until the first thread that reaches the resource 
completes its work, and then it must be accessed again.

### Using Synchronized Keyword in Methods

This method is generally used in cases where the code is written by someone else,  
but synchronization is desired. 
In this way, management is intervened, at least partially.

```java  
metot(){     
    synchronized(Lock objesi){ 
        // kritik bölgede yapılacak işlemler     
    }
}
```

The “synchronized” keyword can be given to ***a variable, a block fragment, 
or a method***. 
If the above example is now written as “Thread Safe”:

```java  
public class QMatic implements Runnable { 
    private int orderNo; 	
    private Object LOCK = new Object(); 	
    public QMatic() { 
        this.orderNo = 0; 	
    }
    @Override 
    public void run() { 		
        // a little bit delay to see race condition!
        ThreadSleeper.sleep(50); 		
        // Critical section for all threads! 		
        synchronized (LOCK) { 
            this.orderNo = this.orderNo + 1; 			
            StringBuilder builder = new StringBuilder(); 			
            builder.append(Thread.currentThread().getName());
            builder.append(" thread got "); 
            builder.append(this.orderNo); 
            builder.append(" from Qmatic!");
            System.out.println(builder.toString());
        }
    }
}
```

In the class derived from the "Runnable" interface named QMatic,
a lock object of type Object named "LOCK" is created.
Then, the code block that operates with the "orderNo" variable, 
which is common to all Threads, designated as "Critical Section," 
is protected with the "synchronized" keyword 
and opened to sequential access for Threads. 
If a Thread enters the code block marked as "Critical Section" 
and starts using resources, other Threads have to wait until it finishes its job.

```java  
Thread-9 thread got 1 from Qmatic! 
Thread-1 thread got 2 from Qmatic!
Thread-3 thread got 3 from Qmatic! 
Thread-7 thread got 4 from Qmatic! 
Thread-2 thread got 5 from Qmatic! 
Thread-4 thread got 6 from Qmatic! 
Thread-5 thread got 7 from Qmatic! 
Thread-0 thread got 8 from Qmatic! 
Thread-6 thread got 9 from Qmatic! 
Thread-8 thread got 10 from Qmatic!
```

### Using Synchronized Methods

When any method in a class receives the synchronized statement, 
when a thread enters that method, 
the object containing the method is automatically closed to access 
with the lock mechanism. 
In this case, another thread cannot access any synchronized methods 
in that class. 
When the thread operating on the synchronized method exits the method, 
the lock is removed and the entire object becomes accessible again.

As can be seen, the "synchronized" structure can be used 
within the method or can be given to the method itself. 
This keyword should be placed after the access token and before the return type of the function.

```java  
access_token synchronized return_type method_name(){ 
    // operations to be done 
}
```

If the code block we mentioned above as "synchronized" is included in a method, 
it can do as follows.

```java  
private synchronized void increment() { 
    this.orderNo = this.orderNo + 1; 	
    StringBuilder builder = new StringBuilder(); 	
    builder.append(Thread.currentThread().getName());
    builder.append(" thread got "); 
    builder.append(this.orderNo); 
    builder.append(" from Qmatic!");
    System.out.println(builder.toString());
}
```

## Using "Volatile" Keyword

The “volatile” keyword guarantees that the value stored in the variable 
will be read the same value in all Threads when they try to read it. 
There is a main memory region in computer architecture. 
The data and program commands processed by a program 
while it is running are stored in this main memory area.

This memory area is known as RAM (Random Access Memory). 
Also, there are processors (CPU) in computer architecture. 
The CPU and memory area are in constant communication. 
There is tight communication traffic.

For this reason, there are small memory areas on the CPU side, as seen below. 
These are called cache. 
These caches are used to reduce the speed of reading and writing data from 
the main memory to a minimum level. 
Frequently used variable values are placed in these cache regions. 
Thus, they are processed faster instead of wasting time by constantly 
going to the main memory.

![Computer Architecture](https://raw.githubusercontent.com/Kodluyoruz/taskforce/main/java102/thread-volatile/figures/volatile_osman_deniz.png)

It is stated that when it is necessary to access the value of a variable 
marked with the "volatile" keyword, 
it will be taken directly from the main memory, 
and if the relevant variable is written, 
it will be written directly to the main memory region.

Thoughts such as "Okay, we understand, it is kept in the main memory, 
but there are still things that do not fit in my mind." may have arisen in the mind. 
In response to such questions, the following scenario can be considered. 
For example, let's assume that a multithread application is written 
in which there is a variable controlled between threads, 
and this application can be run by dozens of threads. 
Also, let's not forget that these threads can be run on different cores on the processor. 
In such a scenario, since all threads cannot use the same cache, 
inconsistencies may occur in the program's operation. 
When the control variable in this scenario is marked as "volatile," 
it will be kept in the main memory instead of cache. 
In this way, all threads will be able to check the state of the variable simultaneously.

Use of:

```java  
access_token volatile return_type method_name;
```

```java  
private volatile int orderNo;
```

We saw the use of the “volatile” keyword in our QMatic example above.

## ThreadPooling

Creating a thread is a really costly affair. 
A certain resource is allocated in the system for each Thread. 
These resources are important ones such as CPU and Memory. 
While our application is running, we may want to limit it to a certain amount of Threads.

Therefore, we create a Thread pool and fill this pool with previously created and 
ready-to-use Thread objects. 
Thus, we can achieve performance gains and efficient use of system resources.

```java  
ExecutorService executor = Executors.newFixedThreadPool(15);
```

You can create a Thread pool by calling the "newFixedThreadPool" method in the 
"Executors" class available in Java above. 
Then, it will create and give us a pool with 15 Threads ready for use. 
This function will also give us an object of type “ExecutorService” that will manage 
Thread usage in this pool. 
We will use a Thread in the pool with the "execute" function on this object 
and return it to the system when we are done.

```java  
ExecutorService executor = Executors.newFixedThreadPool(15); 
QMatic qmatic = new QMatic(); 
for(int i=0; i < 100; i++) {  
    executor.execute(qmatic);
}
```

In the example above, we create an object named "QMatic" from the Runnable type class 
that we used before, which gives a sequence number. 
We run this piece of code using a Thread in the pool with the “execute” function. 
As you can see, although the pool has a capacity of 15, 
there is a request to use Thread 100 times in the loop. 
If there is no suitable free Thread in the pool, it will remain pending. 
It will take the first idle Thread object from the pool and run it.

## Test

1. Which method is used to create and run threads?  

    a. start()  
    b. run()  
    c. thread()  
    d. play()  

2. Which class is used to create threads?  

    a. String  
    b. System  
    c. Thread  
    d. Runnable  

3. Which of the interfaces contains all the methods used to process thread-related operations in Java?

   a. Runnable interface  
   b. math interface  
   c. System interface  
   d. ThreadHandling interface  

4. Which of the following methods is used to implement the Runnable interface?

   a. stop()  
   b. run()  
   c. runThread()  
   d. stopThread()  

5. Which method of Thread class is used to suspend a thread for a certain period of time?

   a. sleep()  
   b. terminate()  
   c. suspend()  
   d. stop()  

6. Which of these methods is used to find out whether a thread is still running or not?

   a. run()  
   b. Alive()  
   c. isAlive()  
   d. checkRun()  

7. What is synchronization relative to a thread?

   a. It is the process of handling situations when two or more threads need to access a shared resource.  
   b. It is a process in which many threads can access the same shared resource simultaneously.  
   c. It is a process in which a method can access many different threads simultaneously.  
   d. It is a method that allows multiple threads to access any information they need.

8. Concurrent access to shared data may result in ____________.

   a. Data consistency  
   b. Data insecurity  
   c. Data inconsistency  
   d. None of the mentioned

9. A situation in which several Threads access and manipulate the same data simultaneously, 
and the result of the execution, that depends on the specific order, is not consistent 
is called ____________.

   a. Data consistency  
   b. Race condition  
   c. Aging  
   d. Starvation

10. What is the logic of thread pools?

   a. At the beginning of the process, 
   a set of threads are created and placed in a pool where they sit and wait to execute.  
   B. When a process starts, a thread pool is selected from many available threads 
   and each thread is given an equal amount of work.  
   c. All threads in a pool distribute the task equally among themselves  
   d. None

11. If there are no threads available in the thread pool ____________

   a. The server runs a new process  
   b. Server goes to another thread pool  
   c. Requests from the server to create a new pool  
   d. The server waits until someone is freed  

Answers : 1.a, 2.c, 3.a, 4.b, 5.a, 6.c, 7.a, 8.c, 9.b, 10.b, 11.d