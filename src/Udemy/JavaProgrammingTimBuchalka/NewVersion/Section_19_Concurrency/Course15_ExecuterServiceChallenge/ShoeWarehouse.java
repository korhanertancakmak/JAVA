package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_19_Concurrency.Course15_ExecuterServiceChallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoeWarehouse {

    private List<Order> shippingItems;

    private final ExecutorService fulfillmentService;

    public final static String[] PRODUCT_LIST =
            {"Running Shoes", "Sandals", "Boots", "Slippers", "High Tops"};

    public ShoeWarehouse() {
        this.shippingItems = new ArrayList<>();
        fulfillmentService = Executors.newFixedThreadPool(3);
    }

//Part-6
/**
        I'll create a private final field on my warehouse class. It'll be an executor service type, and I'll call it
 fulfillmentService. In the constructor, I'll assign this new field, an instance I'll get back from the new fixed thread
 pool method on the Executors class. I'll pass three to this method, meaning this service will only ever have a maximum
 of three threads in the pool. I'll set up a shutDown method on this class, so that this class's fulfillment service can
 be shutdown smoothly. This will just delegate to the shutdown method on the service. Ok, so that's the set up, for the
 creation of the thread pool.
**/
//End-Part-6

    public void shutDown() {
        fulfillmentService.shutdown();
    }

    public synchronized void receiveOrder(Order item) {

        while (shippingItems.size() > 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        shippingItems.add(item);
        System.out.println(Thread.currentThread().getName() + " Incoming: " + item);
        fulfillmentService.submit(this::fulfillOrder);
        notifyAll();
    }

//Part-7
/**
        To use it, I'll add code to the receiveOrder method in this class. Before I do that, I want to include the thread
 name in the System.out.println method, because it'll be interesting to see, which thread is processing which task. In
 this case, I'll call submit, on the service on this class, passing it a method reference to the fulfillOrder method. Ok,
 so now I have both sides of my Consumer and Producer code, both using executor Services. In the main method, I've got
 an ordering service,

                ExecutorService orderingService = Executors.newCachedThreadPool();

 a cached thread pool, that's going to kick off 15 tasks, using the invokeAll method. On the warehouse side, I've got a
 fixed thread pool, with 3 threads available to handle the work.

                 public ShoeWarehouse() {
                     this.shippingItems = new ArrayList<>();
                     fulfillmentService = Executors.newFixedThreadPool(3);
                 }

 Before I run this, I'll add code to the main method.
**/
//End-Part-7

    public synchronized Order fulfillOrder() {

        while (shippingItems.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Order item = shippingItems.remove(0);
        System.out.println(Thread.currentThread().getName() + " Fulfilled: " + item);
        notifyAll();
        return item;
    }
}
