package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course26_CollectionsFrameworkFinalChallenge.Part1;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

//Part-7
/*
        I first want a nested enum, that's going to tell me the type of a cart, so physical or virtual, will be my
    constants in this case. Next, my fields. First, I want a static field called last id, and I'll set that to one. You
    should be able to guess why I'm doing that by now. I'm going to use it to generate a unique cart id for each cart.
    Next, I have four fields, the id or cart identifier, the cart date, a local date. the cart type, so I'll use my enum
    type for that. Finally, I'm going to have some products. This will be a map, keyed by a String, that's going to be
    the product sku, and the integer will be the quantity ordered.
*/
//End-Part-7

public class Cart {

    enum CartType {PHYSICAL, VIRTUAL};
    private static int lastId = 1;
    private int id;
    private LocalDate cartDate;
    private CartType type;
    private Map<String, Integer> products;

//Part-8
/*
        I'll insert a constructor, with one parameter, the Cart Type. I want to include another argument, an int that will
    adjust the actual date by a day or two. I'm doing this just to set up some test data, which will let me test the
    abandon cart code. I'll add the assignments for the other fields here. I'll set id to last id, incrementing that
    field after. I'll set the cart date, using the now method on LocalDate, but subtracting any days passed to this
    constructor, with a method on LocalDate called minus Days. Again, this is going to let me set up tests with different
    dates. I'll make my map a HashMap, and instantiate it here. I want one more constructor, and that one will just be
    for the id. I'll add two getters, one for id, and one for cart date. In addition to the getters, this class will have
    a method, public void, addItem,
*/
//End-Part-8

    public Cart(CartType type, int days) {
        this.type = type;
        id = lastId++;
        cartDate = LocalDate.now().minusDays(days);
        products = new HashMap<>();
    }

    public Cart(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getCartDate() {
        return cartDate;
    }

//Part-9
/*
        and it takes an Inventory item, and a quantity. I'll next use the merge method, with the key, sku I get from
    product on item. I'll pass it the qty, as the second argument, and then the method reference, Integer::sum. This code,
    if the item's in the map, will add qty to the current qty, but if it's not in the map, it will insert a new entry
    using the qty. After this, I want to call the reserve item method on the item, passing it the qty. If I get false
    back from that, that means something went wrong during the reserve process. If something went wrong, I'll print a
    message. Ok, that's add Item, and the next one is removeItem,


    so it's public and void, takes an Inventory Item, and
    a qty, just like add Item did. This time I'll set up a local variable for the qty, which I get from the products map
    on this cart. If this current qty is less
    than or equal to the qty passed, I don't want to subtract more than I have, so I'll just set qty to whatever the qty
    is, in the map. I'll remove the item from the map altogether. And I'll print out that the product was removed. After
    the if statement, I want to call release item, which changes the reserve amount, with the qty from above. This code
    works if the quantity in the cart, is less than, or equal to the qty passed. Next I want to handle, removing just
    some of the items, reducing the quantity in other words. If a shopper had 5 apples, he might want to put back 2,
    after some extra thought. That means I want an else statement. And I'll again do a merge. Instead of adding the
    values, I'll subtract the new val from the old val. And I'll printout how many of the product were removed. Ok, so
    that code should handle any quantity passed to it, even quantities that are greater than the quantity in the cart,
    it'll just remove everything at that point, removing the product from the map altogether. The next method is to print
    the sales slip for a cart, so I'll make this public and void again. I'm going to pass the store's inventory to this
    method, because that's where I have the price of an item stored. I'll start with a local variable, total, that will
    keep track of the total price of all the cart items. I'll print a separator line and a Thank you for your sale line.
    I'll set up a loop, iterating of the products entry set view. I'll be filling this in, in just a minute. After the
    loop, I'll print the total sales price. And another separator line. Inside the loop, I want to get the item from the
    store's inventory. I can get the quantity from the products map, using get Value. I'll get the itemized price, which
    is quantity times prices. I'll add that to the running total of the sale. I'll print out a formatted string, Which
    has a sku, the product name, the qty of items, the price of each item, And the itemized price. Lastly, I want to
    implement the two string method on my Cart Class. I'll generate that with alt Insert, and select everything but the
    type. That's good enough for the Cart class. Now that I have the Cart and Inventory Classes, as well as the Product
    record, and a couple of enums, I want to stop this video here. In the next video, I'm going to implement the Store,
    with it's multiple different collections.
*/
//End-Part-9

    public void addItem(InventoryItem item, int qty) {

        products.merge(item.getProduct().sku(), qty, Integer::sum);

        if (!item.reserveItem(qty)) {
            System.out.println("Ouch, something went wrong, could not add item");
        }
    }

//Part-10
/*
        so it's public and void, takes an Inventory Item, and a qty, just like add Item did. This time I'll set up a
    local variable for the qty, which I get from the products map on this cart. If this current qty is less than or equal
    to the qty passed, I don't want to subtract more than I have, so I'll just set qty to whatever the qty is, in the map.
    I'll remove the item from the map altogether. And I'll print out that the product was removed. After
    the if statement, I want to call release item, which changes the reserve amount, with the qty from above. This code
    works if the quantity in the cart, is less than, or equal to the qty passed. Next I want to handle, removing just
    some of the items, reducing the quantity in other words. If a shopper had 5 apples, he might want to put back 2,
    after some extra thought. That means I want an else statement. And I'll again do a merge. Instead of adding the
    values, I'll subtract the new val from the old val. And I'll printout how many of the product were removed. Ok, so
    that code should handle any quantity passed to it, even quantities that are greater than the quantity in the cart,
    it'll just remove everything at that point, removing the product from the map altogether. The next method is to print
    the sales slip for a cart,
*/
//End-Part-10

    public void removeItem(InventoryItem item, int qty) {

        int current = products.get(item.getProduct().sku());
        if (current <= qty) {
            qty = current;
            products.remove(item.getProduct().sku());
            System.out.printf("Item [%s] removed from basket%n", item.getProduct().name());
        } else {
            products.merge(item.getProduct().sku(), qty, (oldVal, newVal) -> oldVal - newVal);
            System.out.printf("%d [%s]s removed%n", qty, item.getProduct().name());
        }
        item.releaseItem(qty);
    }

//Part-11
/*
        so I'll make this public and void again. I'm going to pass the store's inventory to this method, because that's
    where I have the price of an item stored. I'll start with a local variable, total, that will keep track of the total
    price of all the cart items. I'll print a separator line and a Thank you for your sale line. I'll set up a loop,
    iterating of the products entry set view. I'll be filling this in, in just a minute. After the loop, I'll print the
    total sales price. And another separator line. Inside the loop, I want to get the item from the store's inventory.
    I can get the quantity from the products map, using get Value. I'll get the itemized price, which is quantity times
    prices. I'll add that to the running total of the sale. I'll print out a formatted string, Which has a sku, the product
    name, the qty of items, the price of each item, And the itemized price. Lastly, I want to implement the toString
    method on my Cart Class. I'll generate that with alt Insert, and select everything but the type. That's good enough
    for the Cart class. Now that I have the Cart and Inventory Classes, as well as the Product record, and a couple of
    enums, in the next lecture, I'm going to implement the Store, with it's multiple different collections.
*/
//End-Part-11

    public void printSalesSlip(Map<String, InventoryItem> inventory) {

        double total = 0;
        System.out.println("------------------------------------");
        System.out.println("Thank you for your sale: ");
        for (var cartItem : products.entrySet()) {
            var item = inventory.get(cartItem.getKey());
            int qty = cartItem.getValue();
            double itemizedPrice = (item.getPrice() * qty);
            total += itemizedPrice;
            System.out.printf("\t%s %-10s (%d)@ $%.2f = $%.2f%n",
                    cartItem.getKey(), item.getProduct().name(), qty,
                    item.getPrice(), itemizedPrice);
        }
        System.out.printf("Total Sale: $%.2f%n", total);
        System.out.println("------------------------------------");
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cartDate=" + cartDate +
                ", products=" + products +
                '}';
    }
}
