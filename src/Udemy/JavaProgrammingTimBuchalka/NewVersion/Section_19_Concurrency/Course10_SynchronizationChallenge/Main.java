package CourseCodes.NewSections.Section_19_Concurrency.Course10_SynchronizationChallenge;

import java.util.Random;

//Part-1
/**                                             Synchronization Challenge

        In this challenge, you'll be creating your own Producer Consumer example, for a Shoe Warehouse Fulfillment Center.
 The producer code should generate orders, and send them to the Shoe Warehouse to be processed. The consumer code should
 fulfill, or process the orders in a FIFO or first in, first out order. You'll be creating at a minimum, three types for
 this, an Order, a Shoe Warehouse, and a Main executable class.

 * An Order should include an order id, a shoe type, and the quantity ordered. A record might be a good fit for this type.
 * The shoe warehouse class should maintain a product list, as a public static field. It should also maintain a private
 list of orders. It should have two methods, receiveOrder and fulfillOrder. The receiveOrder gets called by a Producer
 thread. It should poll or loop indefinitely, checking the size of the list, but it should call wait if the list has
 reached some maximum capacity. The fulfillOrder gets called by a Consumer thread. It should also poll the list, but it
 needs to check if the list is empty, and wait in the loop, until an order is added. Both methods should invoke the wait
 and notifyAll methods appropriately.

        Finally, you'll need some kind of a Main class with a main method, to execute. This method should create and start
 a single Producer thread. This should generate 10 sales orders, and call receiveOrder on the Shoe Warehouse, for each.
 In addition, you'll create and start two Consumer threads. Each thread needs to process 5 fulfillment orders, calling
 fulfillOrder on the Shoe Warehouse for each item. You'll test your Producer Consumer application, and confirm your application
 fulfills all the 10 orders it receives.
 **/
//End-Part-1

record Order(long orderId, String item, int qty) {
};

//Part-2
/**
        I've created a new project called SynchronizationChallenge, with the usual main class and main method. First, I'll
 create the Order record, in the Main.java source file. The parameters will be the order id, the item. In this case this
 is going to be the shoe type, and the quantity ordered. Next, I'll create a new class, not in Main in other words, and
 I'll call that ShoeWarehouse.
 **/
//End-Part-2

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {

        ShoeWarehouse warehouse = new ShoeWarehouse();
        Thread producerThread = new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                warehouse.receiveOrder(new Order(
                        random.nextLong(1000000, 9999999),
                        ShoeWarehouse.PRODUCT_LIST[random.nextInt(0, 5)],
                        random.nextInt(1, 4)));
            }
        });
        producerThread.start();

        for (int i = 0; i < 2; i++) {
            Thread consumerThread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    Order item = warehouse.fulfillOrder();
                }
            });
            consumerThread.start();
        }

//Part-6
/**
        First, I'll include a field with a random number generator, to create randomized data for a set of orders. This
 will be private static final, and I'll just call it random. Next, I'll create an instance of the shoe warehouse class.
 Don't forget, the warehouse is ultimately the shared resource, and within that, it's the list of orders. Now, I'll create
 a producer Thread, by passing a Thread constructor a Runnable, which we know can be a lambda expression. In this thread,
 I'll loop for 10 iterations. In each iteration, I'll make a call to the shoe warehouse's receiveOrder method, passing it
 a new order. The order id will be in the range of a 7 digit number. I'll randomly pick a shoe type from the static PRODUCT_LIST
 on the shoe warehouse class. I'll also pick a random amount, between 1 and 3. Don't forget the second argument is an upper
 bound, so I need to make that 4. and finally, I'll start this thread. I'll create the consumer threads. Remember the
 challenge said to create two of them, so I'll start with a for loop with 2 iterations. I'll make this a for loop, because
 I may over time want to increase the number of threads to do the fulfillment. Again, I'll create each thread, by passing
 a runnable lambda expression to a new thread instance. Inside the lambda expression, which represents each thread's run
 method, I'll loop 5 times. Each time, the thread will execute fulfillOrder on the warehouse instance. This returns an
 order, the order that got fulfilled. For the moment, I'm not going to do anything with the result. Inside the outer loop,
 I'll start each of the consumer threads. I'll run that.

                 Incoming: Order[orderId=4923117, item=High Tops, qty=2]
                 Thread-1 Fulfilled: Order[orderId=4923117, item=High Tops, qty=2]
                 Incoming: Order[orderId=7976770, item=High Tops, qty=2]
                 Incoming: Order[orderId=6747672, item=Running Shoes, qty=1]
                 Incoming: Order[orderId=1608618, item=Boots, qty=1]
                 Incoming: Order[orderId=1157658, item=Sandals, qty=1]
                 Incoming: Order[orderId=6238640, item=Boots, qty=3]
                 Incoming: Order[orderId=7427572, item=Slippers, qty=3]
                 Incoming: Order[orderId=9945654, item=High Tops, qty=1]
                 Incoming: Order[orderId=5630878, item=Slippers, qty=1]
                 Incoming: Order[orderId=9856594, item=Slippers, qty=2]
                 Thread-2 Fulfilled: Order[orderId=7976770, item=High Tops, qty=2]
                 Thread-2 Fulfilled: Order[orderId=6747672, item=Running Shoes, qty=1]
                 Thread-2 Fulfilled: Order[orderId=1608618, item=Boots, qty=1]
                 Thread-2 Fulfilled: Order[orderId=1157658, item=Sandals, qty=1]
                 Thread-2 Fulfilled: Order[orderId=6238640, item=Boots, qty=3]
                 Thread-1 Fulfilled: Order[orderId=7427572, item=Slippers, qty=3]
                 Thread-1 Fulfilled: Order[orderId=9945654, item=High Tops, qty=1]
                 Thread-1 Fulfilled: Order[orderId=5630878, item=Slippers, qty=1]
                 Thread-1 Fulfilled: Order[orderId=9856594, item=Slippers, qty=2]

 And there's the output. If I examine it, I can confirm that there were 10 orders submitted by the Producer thread. And
 secondly, I can see 10 orders fulfilled, by the two consumer threads. I'll go back to the fulfill order method, and add
 the thread name to the output. I'll run my code again with this change. This output makes it more obvious, that we had
 two threads fulfilling orders. Ok, so that was all I needed to do, to complete the challenge. Next, I'll be talking about
 a different way to lock a shared resource, in a multi-threaded environment.
 **/
//End-Part-6
    }
}
