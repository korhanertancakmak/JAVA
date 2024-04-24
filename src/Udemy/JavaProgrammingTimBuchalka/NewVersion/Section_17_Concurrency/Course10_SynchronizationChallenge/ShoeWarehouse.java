package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course10_SynchronizationChallenge;

import java.util.ArrayList;
import java.util.List;

public class ShoeWarehouse {

    private List<Order> shippingItems;
    public final static String[] PRODUCT_LIST =
            {"Running Shoes", "Sandals", "Boots", "Slippers", "High Tops"};

    public ShoeWarehouse() {
        this.shippingItems = new ArrayList<>();
    }

//Part-3
/**
        First, I need a place to put orders when they're received. For this, I'll have a List called shipping items. The
 challenge recommended that this class also include a static member, for the types of shoes which can be ordered. I'll
 include that here, as an array of Strings, and call this PRODUCT_LIST, all upper case. This is a list of constants in
 this case. Some shoe items might be, running shoes, sandals, boots, slippers, and I think I'll also add high tops. After
 this, I'll create a no args constructor, that creates a new instance of shipping items. I'll make this an arraylist. You
 might have chosen a LinkedList, which is another good option, since it's in insertion order, and has queueing methods.
 Either type of list can do the job here. Now I need to implement the two methods, so I'll start with receiveOrder.
**/
//End-Part-3

    public synchronized void receiveOrder(Order item) {

        while (shippingItems.size() > 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        shippingItems.add(item);
        System.out.println("Incoming: " + item);
        notifyAll();
    }

//Part-4
/**
        This method will be synchronized, and it'll take an order as a parameter. If there are more than 20 shipping items,
 It'll wait here. You can imagine there might be some top capacity for this list, though in reality it would not likely
 be 20. In the while loop, I'll start with a try block. I want this thread to wait until it's less busy. Don't forget,
 that by calling this method, I'm putting this thread in a wait queue, and letting other threads have access to the lock
 it acquired. If an Interrupted Exception is thrown, something is trying to interrupt this thread in other words, I'll
 get out of this method entirely here, so I'll throw a RuntimeException. Once there's room to add more items in the
 shippingItems list, the code needs to add the item to the list. I'll print the item that was added, as an incoming item.
 Finally, I'll notify all threads that there's a change that could impact them. Namely, that this thread is done its job,
 and is releasing its lock. Next will be the fulfillOrder method, and I'll add this just below the receiveOrder method.
**/
//End-Part-4

    public synchronized Order fulfillOrder() {

        while (shippingItems.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Order item = shippingItems.remove(0);
        System.out.println(Thread.currentThread().getName() + " Fulfilled: " + item);       // Added via Part-6
        notifyAll();
        return item;
    }

//Part-5
/**
        Once again it's going to be synchronized, and it'll return an order. This code needs to stay in a loop until the
 shipping items list has something in it. I'll call wait, to free the lock for other threads. Again, if some outside process
 is trying to interrupt my thread, I should quit here. Once there's at least one item in the shipping items list, I can
 retrieve the first item. That item is element zero. The remove method will both retrieve that element, and remove it from
 the list. I'll print that the order was fulfilled with its details. I'll notify all waiting threads, that I'm releasing
 the lock, and finally I'll return the item. That's really all I need for the Shoe Warehouse code. I'll jump back now to
 the Main class, and create some threads, which will use the methods on the warehouse.
 **/
//End-Part-5
}
