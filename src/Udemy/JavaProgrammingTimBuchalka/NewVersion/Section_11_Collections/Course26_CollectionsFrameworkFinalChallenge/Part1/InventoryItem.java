package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course26_CollectionsFrameworkFinalChallenge.Part1;

//Part-2
/*
        I'll quickly add all my fields, all private. The first is going to have a Product type, and I'll call it product.
    Next, is price, a double. This calls has four quantity fields, the quantity Total, the qty Reserved (those that are
    in carts), the qty Reordered, that's the quantity I'd order if I ran low. And quantity Low is the threshold, that has
    to be reached, to order more product. I'll generate a constructor, using the first three fields, product, price,
    quantity total, and quantity low. I'm going to add a statement in there, and set qty Reordered to qty total. This is
    the reorder amount. I'll generate getters for the product and price. Now, I want to create the methods on this, so
    first reserveItem.
*/
//End-Part-2

public class InventoryItem {

    private Product product;
    private double price;
    private int qtyTotal;
    private int qtyReserved;
    private int qtyReorder;
    private int qtyLow;

    public InventoryItem(Product product, double price, int qtyTotal, int qtyLow) {
        this.product = product;
        this.price = price;
        this.qtyTotal = qtyTotal;
        this.qtyLow = qtyLow;
        this.qtyReorder = qtyTotal;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

//Part-3
/*
        This method will get called, as an item gets added to the shopping cart, so I want it to be public, boolean, and
    take an int quantity, which is the number of the product the shopper's added to the cart. I want to see if the qty
    being added is going to take my inventory below zero, so I'll make sure the qty total, the inventory minus the qty
    reserved, that's in other people's carts, still is greater than the qty requested. If it is, I want to reserve that
    qty, so I'll add qty to the reserved amount. And I'll return true. If for some reason, a situation occurs where the
    stock ran out or my inventory is too low, I'll return false.
*/
//End-Part-3

    public boolean reserveItem(int qty) {

        if ((qtyTotal - qtyReserved) >= qty) {
            qtyReserved += qty;
            return true;
        }
        return false;
    }

//Part-4
/*
        The second method is release Item, again public and void, and taking an integer for qty again. In this one, I just
    want to decrement the qty from the qty reserved. This method gets called when a shopper removes an item from their
    cart. It would also get called during the automated process of abandoning carts, removing products from those carts,
    and unreserving their quantity.
*/
//End-Part-4

    public void releaseItem(int qty) {
        qtyReserved -= qty;
    }

//Part-5
/*
        The next method is sell Item, public, returns boolean, and takes a quantity again. This one will get called during
    the check out cart process. If the qty total, the qty in stock, is greater than or equal to the qty requested, I'll
    subtract the qty from the qty total. I also need to subtract that qty from the qty reserved amount. And finally I
    want to check to see if the qty total has gone below the low threshold. If it has I'll call a method, placeInventory
    Order, which I still need to create. If the qty was in stock, I'll return true. If for some reason the qty wasn't in
    stock, I'll return false. Now, I'll add the place Inventory Order.
*/
//End-Part-5

    public boolean sellItem(int qty) {

        if (qtyTotal >= qty) {
            qtyTotal -= qty;
            qtyReserved -= qty;
            if (qtyTotal <= qtyLow) {
                placeInventoryOrder();
            }
            return true;
        }
        return false;
    }

//Part-6
/*
        I'll make this private, since it will get triggered by a condition that happens on this inventory item. And I'll
    just print that an order was placed. Finally, I want a toString method. I'll generate that with Control O. Then I'll
    replace the call to super two string. I'm just going to return a formatted string with product, price, quantity total,
    and quantity reserved. Ok, the next class is Cart.
*/
//End-Part-6

    private void placeInventoryOrder() {
        System.out.printf("Ordering qty %d : %s%n", qtyReorder, product);
    }

    @Override
    public String toString() {
        return "%s, $%.2f : [%04d,% 2d]".formatted(product, price, qtyTotal,
                qtyReserved);
    }
}
