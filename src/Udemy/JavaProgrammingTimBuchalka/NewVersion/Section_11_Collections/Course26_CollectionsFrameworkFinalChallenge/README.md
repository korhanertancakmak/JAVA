# [Collections Framework Challenge]()
<div align="justify">

I'm going to implement **Product**,
**ItemInventory** and **Cart**, which is mainly just setup,
and not specific to the **Collections** Framework,
except for the _products_ field on **Cart**.
I'll be using _minusDays_ on **LocalDate**,
which simply subtracts whatever number of days you pass,
from the date you're using.
I'll start with the **Store** class,
and include a _main_ method on that.

```java  
public class Store {
    public static void main(String[] args) {

    }
}
```

Once I've got that, I'm going to set up some of my other types.
First, I want an enum, **Category**, and for that,
I'll just create a new Java class, pick enum, and call it _Category_.
I'll set up some constants, in the order a store might be laid out,
so maybe _PRODUCE_, _dairy_, _cereal_, _meat_, and _beverage_ to start with.

```java  
public record Product(String sku, String name, String mfgr, Category category) {
}
```

Next, I want a **Product**, and I'm going to make this a record,
so new **Record**, name is _Product_.
You'll remember I said this should be immutable,
which is why I want a record here.
And I can set up my list of fields.
String _sku_, String _name_, String _mfgr_ for the manufacturer.
The last one will be type **Category**, the enum I just created.
I'll create an **InventoryItem** class next.

```java  
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
}
```

I'll quickly add all my fields, all private. 
The first is going to have a **Product** type, and I'll call it _product_.
Next, is _price_, a **double**. This calls has four quantity fields, 
the _qtyTotal_, the _qtyReserved_ (those that are in carts), 
the _qtyReorder_, that's the quantity I'd order if I ran low. 
And _qtyLow_ is the threshold that has to be reached, to order more product. 
I'll generate a constructor, using the first three fields, 
_product_, _price_, _qtyTotal_, and _qtyLow_.
I'm going to add a statement in there, and set _qtyReorder_ to _qtyTotal_.
This is the reorder amount. 
I'll generate getters for the product and price. 
Now, I want to create the methods on this, 
so first _reserveItem_.

```java  
public boolean reserveItem(int qty) {

    if ((qtyTotal - qtyReserved) >= qty) {
        qtyReserved += qty;
        return true;
    }
    return false;
}
```

This method will get called, as an item gets added 
to the shopping cart, so I want it to be public, 
boolean, and take an int quantity, 
which is the number of the product the shopper's added to the cart. 
I want to see if the _qty_ being added is going 
to take my inventory below zero, so I'll make sure the _qtyTotal_, 
the inventory minus the _qtyReserved_, that's in other people's carts, 
still is greater than the _qty_ requested. 
If it is, I want to reserve that _qty_, 
so I'll add _qty_ to the reserved amount. 
And I'll return true. If for some reason, 
a situation occurs where the stock ran out 
or my inventory is too low, I'll return false.

```java  
public void releaseItem(int qty) {
    qtyReserved -= qty;
}
```

The second method is _releaseItem_, 
again public and void, and taking an integer for _qty_ again. 
In this one, I just want to decrement the _qty_ from the _qtyReserved_. 
This method gets called when a shopper removes an item from their cart. 
It would also get called during the automated process of abandoning carts, 
removing products from those carts, and unreserving their quantity.

```java  
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
```

The next method is _sellItem_, public, returns boolean, 
and takes a quantity again. 
This one will get called during the check-out cart process. 
If the _qtyTotal_, the qty in stock is greater than 
or equal to the _qty_ requested, 
I'll subtract the _qty_ from the _qtyTotal_. 
I also need to subtract that _qty_ from the _qty_ reserved amount. 
And finally, I want to check to see if the _qtyTotal_ has gone 
below the low threshold.
If it has, I'll call a method, _placeInventory_Order, 
which I still need to create. 
If the _qty_ was in stock, I'll return true. 
If for some reason the _qty_ wasn't in stock, I'll return false. 
Now, I'll add the _placeInventoryOrder_.

```java  
private void placeInventoryOrder() {
    System.out.printf("Ordering qty %d : %s%n", qtyReorder, product);
}

@Override
public String toString() {
    return "%s, $%.2f : [%04d,% 2d]".formatted(product, price, qtyTotal,
            qtyReserved);
}
```

I'll make this private, since it will get triggered 
by a condition that happens on this inventory item. 
And I'll just print that an order was placed. 
Finally, I want a _toString_ method. 
I'll generate that with Control+O. 
Then I'll replace the call to super _toString_. 
I'm just going to return a formatted string with 
_product_, _price_, _qtyTotal_, and _qtyReserved_. 
Ok, the next class is **Cart**.

```java  
public class Cart {

    enum CartType {PHYSICAL, VIRTUAL};
    private static int lastId = 1;
    private int id;
    private LocalDate cartDate;
    private CartType type;
    private Map<String, Integer> products;

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
}
```

I first want a nested enum, that's going to tell me 
the type of cart, so physical or virtual, will be my constants 
in this case. 
Next, my fields. 
First, I want a static field called _lastId_, 
and I'll set that to one. 
You should be able to guess why I'm doing that by now. 
I'm going to use it to generate a unique cart id for each cart.
Next, I have four fields, the _id_ or cart identifier,
the _cartDate_, a local date. 
The cart type, so I'll use my enum _type_ for that. 
Finally, I'm going to have some products. 
This will be a map, keyed by a **String**, 
that's going to be the product _sku_, 
and the integer will be the quantity ordered.

I'll insert a constructor, with one parameter, the **Cart** type. 
I want to include another argument, an int that will adjust 
the actual date by a day or two. 
I'm doing this just to set up some test data, 
which will let me test the abandon cart code. 
I'll add the assignments for the other fields here. 
I'll set _id_ to _lastId_, incrementing that field after. 
I'll set the _cartDate_, using the _now_ method on **LocalDate**, 
but subtracting any days passed to this constructor, 
with a method on LocalDate called _minusDays_. 
Again, this is going to let me set up tests with 
different dates.
I'll make my map a **HashMap**, and instantiate it here. 
I want one more constructor, and that one will just be for the _id_. 
I'll add two getters, one for _id_, and one for _cartDate_. 
In addition to the getters, 
this class will have a method, public void, _addItem_:

```java  
public void addItem(InventoryItem item, int qty) {

    products.merge(item.getProduct().sku(), qty, Integer::sum);
    if (!item.reserveItem(qty)) {
        System.out.println("Ouch, something went wrong, could not add item");
    }
}
```

It takes an **InventoryItem**, and a _qty_. 
I'll next use the _merge_ method, with the key, _sku_ 
I get from product on item. 
I'll pass it the _qty_, as the second argument, 
and then the method reference, _Integer::sum_. 
This code, if the item's in the map, will add _qty_ to the current _qty_, 
but if it's not in the map, it will insert a new entry using the _qty_. 
After this, I want to call the _reserveItem_ method on the _item_, 
passing it the _qty_. 
If I get false back from that, that means something went wrong during the reserve process.
If something went wrong, I'll print a message. 
Ok, that's _addItem_, and the next one is _removeItem_:

```java  
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
```

So it's public and void, takes an **InventoryItem**, and a _qty_, 
just like _addItem_ did. 
This time I'll set up a local variable for the _qty_, 
which I get from the _products_ map on this cart. 
If this current _qty_ is less than or equal to the _qty_ passed, 
I don't want to subtract more than I have, 
so I'll just set _qty_ to whatever the _qty_ is, in the map.
I'll remove the item from the map altogether.
And I'll print out that the product was removed. 
After the if statement, I want to call _releaseItem_, 
which changes the reserve amount, with the _qty_ from above. 
This code works if the quantity in the cart is less than, 
or equal to the _qty_ passed. 
Next, I want to handle removing just some items, 
reducing the quantity in other words. 
If a shopper had five apples, he might want to put back 2,
after some extra thought. 
That means I want an else statement. 
And I'll again do a _merge_. 
Instead of adding the values, I'll subtract the _newVal_ 
from the _oldVal_. 
And I'll print out how many of the product were removed. 
Ok, so that code should handle any quantity passed to it, 
even quantities that are greater than the quantity in the cart,
it'll just remove everything at that point, 
removing the product from the map altogether. 
The next method is to print the sales slip for a cart:

```java  
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
```

So I'll make this public and void again. 
I'm going to pass the store's inventory to this method, 
because that's where I have the price of an item stored. 
I'll start with a local variable, _total_, 
that will keep track of the total price of all the cart items. 
I'll print a separator line and a _Thank you for your sale_ line. 
I'll set up a loop, iterating of the products entry set view. 
After the loop, I'll print the _total sales price_. 
And another separator line. 
Inside the loop, I want to get the item from the store's inventory.
I can get the quantity from the _products_ map, using _getValue_. 
I'll get the _itemizedPrice_, which is quantity times prices. 
I'll add that to the running _total_ of the sale. 
I'll print out a formatted string, Which has a _sku_, 
the product _name_, the _qty_ of items, the _price_ of each item, 
and the _itemizedPrice_. 
Lastly, I want to implement the _toString_ method on my **Cart** Class. 
I'll generate that with alt+Insert, and select everything but the _type_. 
That's good enough for the **Cart** class. 
I have the **Cart** and **Inventory** Classes, 
as well as the **Product** record, and a couple of enums.
Now, I'm going to implement the **Store** with its multiple different 
collections.

```java  
public class Store {

    private static Random random = new Random();
    private Map<String, InventoryItem> inventory;
    private NavigableSet<Cart> carts = new TreeSet<>(Comparator.comparing(Cart::getId));
    private Map<Category, Map<String, InventoryItem>> aisleInventory;

    public static void main(String[] args) {
        
    }
}
```

I first want to set up a static field, for a **random** instance. 
I'll use it to assign different prices to my products later. 
You've seen me do something similar to this before as well. 
I'll next set up my collection fields in this class. 
I'm going to make inventory a map, keyed by the _sku_. 
The **Cart** collection can be a set. 
I'm going to make it a **NavigableSet** reference type, 
because I want to use navigable Set methods 
as I look for abandoned carts later.
I'll instantiate it as a **TreeSet**, passing a Comparator 
to that constructor, so this tree set will be ordered by the cart's id. 
The aisle inventory is going to be a map, keyed by the product category. 
For this, the type of the element is going to be another map, a nested map. 
The nested map will have the product name as its key, 
and the _InventoryItem_ as the value. 
You might be asking, why do I have this _aisleInventory_? 
Well, somebody in a store is probably going to be looking for items 
by an aisle category, and a product name or description. 
These two maps emulate the two different ways shoppers are likely to get a product.
A shopper in the store will be looking for items, first by category, 
and then by recognition of the product by name. 
An online shopper may proceed that way, 
but may also simply enter a catalog number, the _sku_. 
Next, I'm going to set up a bunch of methods, which I'll implement later. 

```java  
private void manageStoreCarts() {

}

private boolean checkOutCart(Cart cart) {
    
    return false;
}

private void abandonCarts() {
    
}

private void listProductsByCategory() {
    
}

private void stockStore() {
    
}

private void stockAisles() {
    
}
```

I'll have a method to manage a store cart. 
I need a method to check out a cart that takes a cart 
and returns a boolean if that operation was successful. 
For now, I'll return false. 
I want a method called abandon Carts. 
And a method called list products by category. 
I also want two helper methods to set up my store. 
The first one is stock store.
The second one is stock Aisles. 
Ok, This might look like a lot of work, but it'll go fast.

We have to set up some products first, 
which will let us stock the store and aisles, 
so I'm going to do this in the stock store method. 
First, I'll instantiate my inventory field to a new **HashMap**. 
Now, I'll set up a list of products, a new Array list. 
I'm going to paste my products in. 
If you wish to do the same, this bit of code will be 
in the course folder of this challenge, 
in a file called **productData.txt**. 

```java  
private void stockStore() {

    inventory = new HashMap<>();
    List<Product> products = new ArrayList<>(List.of(
            new Product("A100","apple","local",Category.PRODUCE),
            new Product("B100","banana","local",Category.PRODUCE),
            new Product("P100","pear","local",Category.PRODUCE),
            new Product("L103","lemon","local",Category.PRODUCE),
            new Product("M201","milk","farm",Category.DAIRY),
            new Product("Y001","yogurt","farm",Category.DAIRY),
            new Product("C333","cheese","farm",Category.DAIRY),
            new Product("R777","rice chex","Nabisco",Category.CEREAL),
            new Product("G111","granola","Nat Valley",Category.CEREAL),
            new Product("BB11","ground beef","butcher",Category.MEAT),
            new Product("CC11","chicken","butcher",Category.MEAT),
            new Product("BC11","bacon","butcher",Category.MEAT),
            new Product("BC77","coke","coca cola",Category.BEVERAGE),
            new Product("BC88","coffee","value",Category.BEVERAGE),
            new Product("BC99","tea","herbal",Category.BEVERAGE)
    ));

    products.forEach(p -> inventory.put(p.sku(), new InventoryItem(p, random.nextDouble(0, 1.25), 1000, 5)));
}

private void listInventory() {

    System.out.println("--------------------------------------");
    inventory.values().forEach(System.out::println);
}
```

Here, I'm using my product record to set up a bunch of products 
in my five categories. 
The sku is the first field, then a simple name to describe the product, 
followed by its manufacturer, and then the category. 
Now that I have my products, I can set up my inventory map. 
I've made it a hash map because I really don't need my inventory to be ordered, 
but I use a map because it would be nice 
to quickly retrieve the information by the _sku_ field. 
I'll loop through products, and use a lambda expression,
to put a new instance of a new inventory item, into the inventory map, 
using the sku as the key. I can create an inventory item, with a product, 
a price, an inventory quantity total, and low inventory number. 
Here, I'm using the _nextDouble_ method on my static random field, 
passing it 0 and 1.25. 
This is going to generate a double value, somewhere between 0 and 1.25. 
I'll use that generated value as the price of my item. 
Ok, that's my inventory set up. 
Now, I want to print it out to confirm that my map looks the way I want it to. 
I'll do that by calling for each on inventory. 
I'm going to put this in another private method, that I'll just call list inventory. 
I'll loop through the values on my inventory, and print each out. 
I'll test this out before I stock my store aisles, getting back to the _main_ method.

```java  
Store myStore = new Store();
myStore.stockStore();
myStore.listInventory();
```

I'll create a new **store** instance and assign that to the _myStore_ variable. 
Then call _stockStore_ on this. 
And I'll print the inventory. 
Running this code:

```html  
--------------------------------------
Product[sku=R777, name=rice chex, mfgr=Nabisco, category=CEREAL], $0,43 : [1000, 0]
Product[sku=P100, name=pear, mfgr=local, category=PRODUCE], $0,75 : [1000, 0]
Product[sku=M201, name=milk, mfgr=farm, category=DAIRY], $1,12 : [1000, 0]
Product[sku=L103, name=lemon, mfgr=local, category=PRODUCE], $0,48 : [1000, 0]
Product[sku=G111, name=granola, mfgr=Nat Valley, category=CEREAL], $0,21 : [1000, 0]
Product[sku=A100, name=apple, mfgr=local, category=PRODUCE], $0,06 : [1000, 0]
Product[sku=C333, name=cheese, mfgr=farm, category=DAIRY], $0,29 : [1000, 0]
Product[sku=B100, name=banana, mfgr=local, category=PRODUCE], $0,03 : [1000, 0]
Product[sku=Y001, name=yogurt, mfgr=farm, category=DAIRY], $0,99 : [1000, 0]
Product[sku=BB11, name=ground beef, mfgr=butcher, category=MEAT], $0,30 : [1000, 0]
Product[sku=BC11, name=bacon, mfgr=butcher, category=MEAT], $0,79 : [1000, 0]
Product[sku=BC77, name=coke, mfgr=coca cola, category=BEVERAGE], $0,22 : [1000, 0]
Product[sku=BC88, name=coffee, mfgr=value, category=BEVERAGE], $0,92 : [1000, 0]
Product[sku=BC99, name=tea, mfgr=herbal, category=BEVERAGE], $0,77 : [1000, 0]
Product[sku=CC11, name=chicken, mfgr=butcher, category=MEAT], $0,18 : [1000, 0]
```
                    
I get all my inventory printed, the sku as the key, 
the product information, and the price, 
which was randomly generated.
Because inventory is a hash map, 
there's no particular order here. 
Now I'll code the stockAisles method. 
How should I set up the **aislesInventory** collection?

```java  
private void stockAisles() {

    aisleInventory = new EnumMap<>(Category.class);
    for (InventoryItem item : inventory.values()) {
        Category aisle = item.getProduct().category();

        Map<String, InventoryItem> productMap = aisleInventory.get(aisle);
        if (productMap == null) {
            productMap = new TreeMap<>();
        }
        productMap.put(item.getProduct().name(), item);
        aisleInventory.putIfAbsent(aisle, productMap);
    }

}

private void listProductsByCategory(boolean includeHeader, boolean includeDetail) {

    aisleInventory.keySet().forEach(k -> {
        if (includeHeader) System.out.println("--------\n" + k + "\n--------");
        if (!includeDetail) {
            aisleInventory.get(k).keySet().forEach(System.out::println);
        } else {
            aisleInventory.get(k).values().forEach(System.out::println);
        }
    });
}
```

Well, I know I want it to be keyed by the product category, 
but what should the collection element be? 
If you think about how a shopper might look for their item, 
they'd first find the right aisle, then search the shelves for the product, 
or product name they want. 
This is why I wanted the map to contain a nested map. 
I'll set this up as a new instance of an enum map, 
passing it my Category's class. 
I'll loop through my inventory. 
I'll get category for the item by getting that from the product. 
I want a new local variable for my nested map. 
I'll check if it's already in my aisle inventory, 
using the aisle or category key. 
If that's null, it's not in my map. 
So I'll set up a new tree map.
Again, if you think about items on shelves, 
they'd be stocked on the shelves in some order, in my case, 
it will be by product name.
In either case, I'm going to add the inventory item, keyed by the product name. 
And I only need to put this on the map if it's not there already, 
so I'll use _putIfAbsent_. 
Again, I'll print this out, but I want to do this 
in the list of products by category method. 
First, I'll loop through my aisle inventory's key, the product category. 
I'll print the key, the category, between a couple of dashed lines. 
I'll get the map by that key, and I'll loop through the nested map's keys. 
I'll go back to the main method:

```java  
myStore.stockAisles();
myStore.listProductsByCategory();
```

And add a call to these two methods next. 
Running that:

```html  
--------
PRODUCE
--------
apple
banana
lemon
pear
--------
DAIRY
--------
cheese
milk
yogurt
--------
CEREAL
--------
granola
rice chex
--------
MEAT
--------
bacon
chicken
ground beef
--------
BEVERAGE
--------
coffee
coke
tea
```
                    
You can see how my aisles are stocked, and in what order. 
The order of my aisles is in the same order, 
I set up my constants in the **Category** enum. 
And the products are alphabetical by name. 
Ok, so now that my store is stocked and there are products in my aisles, 
I can start letting the customers in. 
I'll set up the code for the manageStoreCarts next.

```java  
private void manageStoreCarts() {

    Cart cart1 = new Cart(Cart.CartType.PHYSICAL, 1);
    carts.add(cart1);

    InventoryItem item = aisleInventory.get(Category.PRODUCE).get("apple");
    cart1.addItem(item, 6);
    System.out.println(cart1);

}
```

First, I want a **Cart**, _cart1_, 
and I'll assign that a new instance of the **Cart**, passing my type. 
Since _CartType_ is a nested enum, it's implicitly static. 
That means I need to reference it, using the **Cart** class name, 
then _CartType_, and then the constant I want to pass here, 
and this is a _PHYSICAL_ cart. 
I'm also passing an integer, which will adjust the cart date. 
This means one day will be subtracted from today when creating this cart.
I want some carts with yesterday's date, to test abandoning them later. 
I'll add the new cart to my carts set on this store. 
I can add items by using the **aisleInventory** map, 
first selecting the aisle by the product category, 
and then the product name.
I'll add the apples to my cart, and the quantity, six. 
I'll print this first cart out. 
Now, I'll make a call to this method in the _main_ method:

```java  
myStore.manageStoreCarts();
```

Invoking manage Store Carts on my store. If I run that:

```html  
Cart{id=1, cartDate=2023-12-02, products={P100=5, BC88=1, A100=6}}
```
                
You can see, I get my cart's information printed out. 
My products (apples) are printed by their sku, A100. 
And look at the date on my cart. 
It's yesterday's date. 
I want to print the inventory again, after this, 
but this time I want my inventory printed out by category, 
then product, but with the inventory details.

```java  
private void listProductsByCategory() {

    listProductsByCategory(true, false);
}

private void listProductsByCategory(boolean includeHeader, boolean includeDetail) {

    aisleInventory.keySet().forEach(k -> {
        if (includeHeader) System.out.println("--------\n" + k + "\n--------");
        if (!includeDetail) {
            aisleInventory.get(k).keySet().forEach(System.out::println);
        } else {
            aisleInventory.get(k).values().forEach(System.out::println);
        }
    });
}
```

I'll copy the _listProductsByCategory_ method, 
and paste a copy below. 
In this overloaded version, I want two parameters, an includeHeader boolean, 
and an IncludeDetail, also a boolean. 
I only want to print the key in the dashed part 
if includeHeader is true, so I'll slip an if statement in there. 
Now, I want to print just the keys if no detail is requested, 
but the value otherwise, so I'll set this up with an 
if then else statement around that last statement.
I'll insert the _if statement_ if includeDetail is false, 
I'll just print the keys, as before. 
Otherwise, I want to print the value. 
So I'll add an else statement, And loop through 
the values of the sub map, printing each.
Now I'll go to the overloaded version, with no parameters. 
I'll remove the code that's there.
I'll replace that with just a call to the overloaded method,
passing true for includeHeader, and false for include detail. 
This means the default will include a header, but no inventory detail. 
Ok, now I want to use the overloaded version in my main method.

```java  
myStore.listProductsByCategory(false, true);
```

I'll call it with no headers, and with the detail included. 
Running this code:

```html  
Cart{id=1, cartDate=2023-12-02, products={P100=5, BC88=1, A100=6}}
Product[sku=A100, name=apple, mfgr=local, category=PRODUCE], $0,06 : [1000, 6]
Product[sku=B100, name=banana, mfgr=local, category=PRODUCE], $0,03 : [1000, 0]
Product[sku=L103, name=lemon, mfgr=local, category=PRODUCE], $0,48 : [1000, 0]
Product[sku=P100, name=pear, mfgr=local, category=PRODUCE], $0,75 : [1000, 5]
Product[sku=C333, name=cheese, mfgr=farm, category=DAIRY], $0,29 : [1000, 0]
Product[sku=M201, name=milk, mfgr=farm, category=DAIRY], $1,12 : [1000, 0]
Product[sku=Y001, name=yogurt, mfgr=farm, category=DAIRY], $0,99 : [1000, 0]
Product[sku=G111, name=granola, mfgr=Nat Valley, category=CEREAL], $0,21 : [1000, 0]
Product[sku=R777, name=rice chex, mfgr=Nabisco, category=CEREAL], $0,43 : [1000, 0]
Product[sku=BC11, name=bacon, mfgr=butcher, category=MEAT], $0,79 : [1000, 0]
Product[sku=CC11, name=chicken, mfgr=butcher, category=MEAT], $0,18 : [1000, 0]
Product[sku=BB11, name=ground beef, mfgr=butcher, category=MEAT], $0,30 : [1000, 0]
Product[sku=BC88, name=coffee, mfgr=value, category=BEVERAGE], $0,92 : [1000, 1]
Product[sku=BC77, name=coke, mfgr=coca cola, category=BEVERAGE], $0,22 : [1000, 0]
Product[sku=BC99, name=tea, mfgr=herbal, category=BEVERAGE], $0,77 : [1000, 0]
```
                    
You can see inventory details printed, but this time by category, 
and within category, by product name. 
I can confirm that the reserved amount for apples is 6, 
but the qty total is still 1000, since I haven't sold the product yet. 

```java  
private void manageStoreCarts() {

    Cart cart1 = new Cart(Cart.CartType.PHYSICAL, 1);
    carts.add(cart1);

    InventoryItem item = aisleInventory.get(Category.PRODUCE).get("apple");
    cart1.addItem(item, 6);
    cart1.addItem(aisleInventory.get(Category.PRODUCE).get("pear"), 5);
    cart1.addItem(aisleInventory.get(Category.BEVERAGE).get("coffee"), 1);
    System.out.println(cart1);

    cart1.removeItem(aisleInventory.get(Category.PRODUCE).get("pear"), 2);
    System.out.println(cart1);

}
```

I'll add a couple more items to my cart. 
This time, I'll just do this in one statement. 
I'll add a produce item, pear, and five of those. 
And then I want a beverage, coffee, and just quantity one for this. 
Running this code:

```html  
2 [pear]s removed
Cart{id=1, cartDate=2023-12-02, products={P100=3, BC88=1, A100=6}}
Product[sku=A100, name=apple, mfgr=local, category=PRODUCE], $0,06 : [1000, 6]
Product[sku=B100, name=banana, mfgr=local, category=PRODUCE], $0,03 : [1000, 0]
Product[sku=L103, name=lemon, mfgr=local, category=PRODUCE], $0,48 : [1000, 0]
Product[sku=P100, name=pear, mfgr=local, category=PRODUCE], $0,75 : [1000, 3]
Product[sku=C333, name=cheese, mfgr=farm, category=DAIRY], $0,29 : [1000, 0]
Product[sku=M201, name=milk, mfgr=farm, category=DAIRY], $1,12 : [1000, 0]
Product[sku=Y001, name=yogurt, mfgr=farm, category=DAIRY], $0,99 : [1000, 0]
Product[sku=G111, name=granola, mfgr=Nat Valley, category=CEREAL], $0,21 : [1000, 0]
Product[sku=R777, name=rice chex, mfgr=Nabisco, category=CEREAL], $0,43 : [1000, 0]
Product[sku=BC11, name=bacon, mfgr=butcher, category=MEAT], $0,79 : [1000, 0]
Product[sku=CC11, name=chicken, mfgr=butcher, category=MEAT], $0,18 : [1000, 0]
Product[sku=BB11, name=ground beef, mfgr=butcher, category=MEAT], $0,30 : [1000, 0]
Product[sku=BC88, name=coffee, mfgr=value, category=BEVERAGE], $0,92 : [1000, 1]
Product[sku=BC77, name=coke, mfgr=coca cola, category=BEVERAGE], $0,22 : [1000, 0]
Product[sku=BC99, name=tea, mfgr=herbal, category=BEVERAGE], $0,77 : [1000, 0]
```
                    
You can see my cart has three items listed by sku. 
And the inventory is showing the reserved quantities, 
5 for pear, and 1 for coffee. 
Now, let's remove a couple of pears from the last cart. 
I'll call remove Item, and pass the produce category with pear, 
and 2, so I want to remove 2 of the 5 pears in my cart. 
And I'll print the cart to confirm that's working.
I get the message that 2 pears were removed. 
My cart after the removal has qty 3 for P 100, the sku for pears.
And the inventory was adjusted too, so I have 3 reserved, not 5, so that's good.
This means that so far, the inventory is being managed correctly 
when adding or removing items from a cart. 

I added products to a physical cart, meaning it's not an online cart. 
I used the aisle inventory map to first get a map of products by product category. 
From that map, I retrieved the inventory item, using a product name, like apple or coffee. 
I used the method to both add items to the cart and to remove them, 
passing the quantity to be added or removed. 
These methods adjusted the reserved quantity, on the Inventory item, 
for that product.

```java  
private void manageStoreCarts() {

    Cart cart1 = new Cart(Cart.CartType.PHYSICAL, 1);
    carts.add(cart1);

    InventoryItem item = aisleInventory.get(Category.PRODUCE).get("apple");
    cart1.addItem(item, 6);
    cart1.addItem(aisleInventory.get(Category.PRODUCE).get("pear"), 5);
    cart1.addItem(aisleInventory.get(Category.BEVERAGE).get("coffee"), 1);
    System.out.println(cart1);

    cart1.removeItem(aisleInventory.get(Category.PRODUCE).get("pear"), 2);
    System.out.println(cart1);

    Cart cart2 = new Cart(Cart.CartType.VIRTUAL, 1);
    carts.add(cart2);
    cart2.addItem(inventory.get("L103"), 20);
    cart2.addItem(inventory.get("B100"), 10);
    System.out.println(cart2);

}
```

I want to next create another cart, this time a virtual cart. 
Again, I want it to have yesterday's date, so I'll pass one to the constructor. 
I'll add the new cart to my carts set. 
Because it's a virtual cart, I'm going to add items, by getting them by the sku, 
from the full inventory map. 
You can imagine that if a shopper is picking items from an online catalog, 
the sku would be available from that process, and would just get passed like 
I'm doing here. 
The sku here is for lemons, and I want 20 lemons. 
I'll also add 10 bananas, sku B100. 
And I'll print the second cart.
Running this code:

```html  
Cart{id=2, cartDate=2023-12-02, products={B100=10, L103=20}}
Product[sku=A100, name=apple, mfgr=local, category=PRODUCE], $0,63 : [1000, 6]
Product[sku=B100, name=banana, mfgr=local, category=PRODUCE], $0,32 : [1000, 10]
Product[sku=L103, name=lemon, mfgr=local, category=PRODUCE], $0,57 : [1000, 20]
Product[sku=P100, name=pear, mfgr=local, category=PRODUCE], $0,98 : [1000, 3]
Product[sku=C333, name=cheese, mfgr=farm, category=DAIRY], $1,17 : [1000, 0]
Product[sku=M201, name=milk, mfgr=farm, category=DAIRY], $0,98 : [1000, 0]
Product[sku=Y001, name=yogurt, mfgr=farm, category=DAIRY], $0,99 : [1000, 0]
Product[sku=G111, name=granola, mfgr=Nat Valley, category=CEREAL], $0,06 : [1000, 0]
Product[sku=R777, name=rice chex, mfgr=Nabisco, category=CEREAL], $0,85 : [0002, 0]
Product[sku=BC11, name=bacon, mfgr=butcher, category=MEAT], $0,03 : [1000, 0]
Product[sku=CC11, name=chicken, mfgr=butcher, category=MEAT], $0,81 : [1000, 0]
Product[sku=BB11, name=ground beef, mfgr=butcher, category=MEAT], $0,66 : [1000, 0]
Product[sku=BC88, name=coffee, mfgr=value, category=BEVERAGE], $0,66 : [1000, 1]
Product[sku=BC77, name=coke, mfgr=coca cola, category=BEVERAGE], $1,25 : [1000, 0]
Product[sku=BC99, name=tea, mfgr=herbal, category=BEVERAGE], $0,03 : [1000, 0]
```
                
You can see cart 2, with yesterday's date, and two products, B100, quantity 10, 
and L zero 3, quantity 20. 
Again, you can see these numbers, reflected in the qty reserved counts in the 
inventory.

```java  
private void manageStoreCarts() {

    Cart cart1 = new Cart(Cart.CartType.PHYSICAL, 1);
    carts.add(cart1);

    InventoryItem item = aisleInventory.get(Category.PRODUCE).get("apple");
    cart1.addItem(item, 6);
    cart1.addItem(aisleInventory.get(Category.PRODUCE).get("pear"), 5);
    cart1.addItem(aisleInventory.get(Category.BEVERAGE).get("coffee"), 1);
    System.out.println(cart1);

    cart1.removeItem(aisleInventory.get(Category.PRODUCE).get("pear"), 2);
    System.out.println(cart1);

    Cart cart2 = new Cart(Cart.CartType.VIRTUAL, 1);
    carts.add(cart2);
    cart2.addItem(inventory.get("L103"), 20);
    cart2.addItem(inventory.get("B100"), 10);
    System.out.println(cart2);

    Cart cart3 = new Cart(Cart.CartType.VIRTUAL, 0);
    carts.add(cart3);
    cart3.addItem(inventory.get("R777"), 998);
    System.out.println(cart3);
    if (!checkOutCart(cart3)) {
        System.out.println("Something went wrong, could not check out");
    }

}
```

I'll set up a new cart, another virtual one, but using today's date, 
so I'll pass zero. 
I'll add that cart to my carts set. 
I'll add some rice check, sku r777, and I'm going to order a lot of them, 998.
I'll print this cart out.
When I check this cart out, this should execute my low-threshold code, 
so let me set that up now. 
I'll call check out cart, and pass cart3. 
This method returns a boolean, so if a false comes back, 
then I know something went wrong, and I can print that message. 
If I ran that now, I'd get a message that says _something went wrong_, 
and that's because I haven't implemented the check-out method yet. 
I'll do that now.

```java  
private boolean checkOutCart(Cart cart) {

    for (var cartItem : cart.getProducts().entrySet()) {
        var item = inventory.get(cartItem.getKey());
        int qty = cartItem.getValue();
        if (!item.sellItem(qty)) return false;
    }
    cart.printSalesSlip(inventory);
    carts.remove(cart);
    return true;
}
```

First, I'll just change the return false to return true. 
Next, I want to set up a for loop before the return statement. 
I'm going to get the view, the entry set of the products in my cart, 
and loop through each of these Entries.
Notice we have an error there with the get products method. 
It doesn't exist. 
I meant to add a getter when setting up our Cart. 
Let me switch over quickly now and add the getter so that the code compiles. 
Right, that's the getter created, lets go back our Store class again.
Back in the check-out cart method. 
Remember that Entry is a nested interface type on Map, 
and each of these has a key and a value. 
I can get the key using getKey, and the value using _getValue_. 
_getKey_ is going to return the product sku, 
which is how I've keyed products in my _products_ map.
_getValue_ is going to return the quantity of this product, 
how many I want to buy. 
I really want the inventory item, and I can get that from my full inventory set, 
using cart item get key, which returns the sku. 
I get quantity using get value. 
I'll assign that to a local variable, qty. 
I'm going to invoke sellItem on each inventory item, passing the quantity.
If that method returns false, I want to stop processing and quit out of this method, returning false.
If this loop ends successfully, it means I've executed sell Item on every inventory item successfully. 
That means I can print a sales slip. 
And after that, I want to remove the cart from my _carts_ list. 
Now, I'll re-run my code:

```html  
2 [pear]s removed
Cart{id=1, cartDate=2023-12-02, products={P100=3, BC88=1, A100=6}}
Cart{id=2, cartDate=2023-12-02, products={B100=10, L103=20}}
Cart{id=3, cartDate=2023-12-03, products={R777=998}}
Ordering qty 1000 : Product[sku=R777, name=rice chex, mfgr=Nabisco, category=CEREAL]
------------------------------------
Thank you for your sale:
R777 rice chex  (998)@ $0,36 = $354.35
Total Sale: $354.35
------------------------------------
Cart{id=4, cartDate=2023-12-03, products={BC99=1}}
Product[sku=A100, name=apple, mfgr=local, category=PRODUCE], $1,16 : [1000, 6]
Product[sku=B100, name=banana, mfgr=local, category=PRODUCE], $0,34 : [1000, 10]
Product[sku=L103, name=lemon, mfgr=local, category=PRODUCE], $1,19 : [1000, 20]
Product[sku=P100, name=pear, mfgr=local, category=PRODUCE], $0,27 : [1000, 3]
Product[sku=C333, name=cheese, mfgr=farm, category=DAIRY], $1,00 : [1000, 0]
Product[sku=M201, name=milk, mfgr=farm, category=DAIRY], $1,02 : [1000, 0]
Product[sku=Y001, name=yogurt, mfgr=farm, category=DAIRY], $0,17 : [1000, 0]
Product[sku=G111, name=granola, mfgr=Nat Valley, category=CEREAL], $0,98 : [1000, 0]
Product[sku=R777, name=rice chex, mfgr=Nabisco, category=CEREAL], $0,36 : [0002, 0]
Product[sku=BC11, name=bacon, mfgr=butcher, category=MEAT], $0,39 : [1000, 0]
Product[sku=CC11, name=chicken, mfgr=butcher, category=MEAT], $0,81 : [1000, 0]
Product[sku=BB11, name=ground beef, mfgr=butcher, category=MEAT], $0,50 : [1000, 0]
Product[sku=BC88, name=coffee, mfgr=value, category=BEVERAGE], $0,66 : [1000, 1]
Product[sku=BC77, name=coke, mfgr=coca cola, category=BEVERAGE], $1,25 : [1000, 0]
Product[sku=BC99, name=tea, mfgr=herbal, category=BEVERAGE], $0,03 : [1000, 1]
```
                
Here, you can see I get a message that the system is 
ordering more rice chex cereal, 1000 more units. 
I also get the sales slip printed after that. 
_Thank you for your sale_, and it prints the line items, 
and the full price of 998 boxes of rice chex. 
After this, the inventory is printed for all products. 
If you find chex mix in the inventory, sku R777. 
You can see that the total quantity, the first number in square brackets, 
is now 0002, and the reserved qty is zero. 
The check-out code that adjusts the inventory is working. 
It resets the reserved count (subtracting the qty from the reserved quantity). 
It decremented the quantity from the total qty, the stock in inventory. 
It also automatically ordered more riced check, when the inventory total 
quantity went below the threshold, the low qty, 
which I had set to 5 for all inventory items.

```java  
Cart cart4 =  new Cart(Cart.CartType.PHYSICAL, 0);
carts.add(cart4);
cart4.addItem(aisleInventory.get(Category.BEVERAGE).get("tea"), 1);
System.out.println(cart4);
```

Ok, so I'm going to create one more cart, a physical one, dated today. 
I'll add that to my _carts_ set. 
I'm going to add one box of tea to this last cart. 
And I'll print that out. 
Running that:

```html  
Cart{id=4, cartDate=2023-12-03, products={BC99=1}}
```
                
You can see cart 4 and the inventory again. 
I can confirm that the reserved count for the last 
item in my inventory, herbal tea, is one. 
Let me print out my carts next to the _main_ method.

```java  
myStore.carts.forEach(System.out::println);
```

Running that,

```html  
Cart{id=1, cartDate=2023-12-02, products={P100=3, BC88=1, A100=6}}
Cart{id=2, cartDate=2023-12-02, products={B100=10, L103=20}}
Cart{id=4, cartDate=2023-12-03, products={BC99=1}}
```
                
You can see I have three carts at this point, carts 1 and 2, with yesterday's date, 
and cart 4 with today's date.
Lastly, I want to implement the abandon carts code.
This code is going to remove items from abandoned carts, 
and then remove the carts from the carts set.

```java  
private void abandonCarts() {

    int dayOfYear = LocalDate.now().getDayOfYear();
    Cart lastCart = null;
    for (Cart cart : carts) {
        if (cart.getCartDate().getDayOfYear() == dayOfYear) {
            break;
        }
        lastCart = cart;
    }

    var oldCarts = carts.headSet(lastCart, true);
    Cart abandonedCart = null;
    while ((abandonedCart = oldCarts.pollFirst()) != null) {
        for (String sku : abandonedCart.getProducts().keySet()) {
            InventoryItem item = inventory.get(sku);
            item.releaseItem(abandonedCart.getProducts().get(sku));
        }
    }
}
```

An abandoned cart is going to be any cart that doesn't have today's date. 
To set this up, I first want to get the current day of the year. 
I'll call LocalDate now, then chain _getDay_ of Year, 
assigning that result to my day of year variable. 
I'll set up a local variable, last Cart and set it to null. 
This variable will get set to the last cart in the carts set, 
with a date that's not equal to the current date. 
I'll loop through the carts. 
If the cart's day of the year is equal to today, I want to break this loop. 
I'll assign the last cart to the current cart instance. 
I've kind of dummied my data up, but you can imagine that the shopping carts, 
which are in cart id order, would have newer carts with ids, 
that are greater than older carts.
This means I can loop, from the start of my sorted set of carts, 
and as soon as I find a cart, that has a day of the year the same as today's, 
I can break the loop. 
I assign each current cart instance, until that point, to the last cart. 
This means, when I break out of this loop, the last cart should be referencing, 
the last cart of the previous day. 
I can now use last cart to splice my cart set, using the _headset_ method. 
I'll set up a variable called oldCarts and assign that the result of calling 
the _headset_ method on carts.
I'll pass the last cart, and I'm going to set the inclusive flag to true. 
Now old carts have all the carts from our carts set that don't have today's date. 
I'm going to set up a local variable called abandoned cart, initializing it to null.
I'll kick off a while loop, executing _pollFirst_ on the old carts set, 
assigning whatever comes back from that to the abandonedCart variable. 
If that's not null, my loop statements will get executed. 
Each cart has a map of products, and I want to loop through 
the keySet of that collection; each key represents a sku. 
I'll use the sku to find the product in the inventory collection. 
I'll then call releaseItem on that inventory Item, 
which will reset the reserved qty for the item. 
This code is simply going to adjust the reserved quantity in my inventory, 
subtracting the abandoned product's qty from the reserved quantity. 
I'll call this method from my _main_ method.

```java  
myStore.abandonCarts();
myStore.listProductsByCategory(false, true);
myStore.carts.forEach(System.out::println);
```

I'll again print my inventory after that. 
And I'll print my cart's set. 
If I run this code:

```html  
Cart{id=1, cartDate=2023-12-02, products={P100=3, BC88=1, A100=6}}
Cart{id=2, cartDate=2023-12-02, products={B100=10, L103=20}}
Cart{id=4, cartDate=2023-12-03, products={BC99=1}}
Product[sku=A100, name=apple, mfgr=local, category=PRODUCE], $0,63 : [1000, 0]
Product[sku=B100, name=banana, mfgr=local, category=PRODUCE], $0,32 : [1000, 0]
Product[sku=L103, name=lemon, mfgr=local, category=PRODUCE], $0,57 : [1000, 0]
Product[sku=P100, name=pear, mfgr=local, category=PRODUCE], $0,98 : [1000, 0]
Product[sku=C333, name=cheese, mfgr=farm, category=DAIRY], $1,17 : [1000, 0]
Product[sku=M201, name=milk, mfgr=farm, category=DAIRY], $0,98 : [1000, 0]
Product[sku=Y001, name=yogurt, mfgr=farm, category=DAIRY], $0,99 : [1000, 0]
Product[sku=G111, name=granola, mfgr=Nat Valley, category=CEREAL], $0,06 : [1000, 0]
Product[sku=R777, name=rice chex, mfgr=Nabisco, category=CEREAL], $0,85 : [0002, 0]
Product[sku=BC11, name=bacon, mfgr=butcher, category=MEAT], $0,03 : [1000, 0]
Product[sku=CC11, name=chicken, mfgr=butcher, category=MEAT], $0,81 : [1000, 0]
Product[sku=BB11, name=ground beef, mfgr=butcher, category=MEAT], $0,66 : [1000, 0]
Product[sku=BC88, name=coffee, mfgr=value, category=BEVERAGE], $0,66 : [1000, 0]
Product[sku=BC77, name=coke, mfgr=coca cola, category=BEVERAGE], $1,25 : [1000, 0]
Product[sku=BC99, name=tea, mfgr=herbal, category=BEVERAGE], $0,03 : [1000, 1]
Cart{id=4, cartDate=2023-12-03, products={BC99=1}}
```
                
First, I want you to see that there are three carts before this code is executed, 
and two of them have a date of yesterday. 
After the execution, there is only one cart, the cart that has today's date. 
Also, look at the inventory, both before and after, 
looking just at the first four items. 
Before I ran the abandonedCarts method, I had 6 apples, 
10 bananas, 20 lemons, and 3 pears in reserve. 
Looking at the inventory printed after execution, 
you can see that the reserve quantities have been reset to zero. 
The only reserved quantity that's not zero is the one that has tea, 
which is the product that's in my only remaining cart. 
Are you confused about how the carts got deleted, 
or do you remember that's what _pollFirst_ really does? 
It's removing the cart from the view when I execute it. 
The view is named _oldCarts_, which I got by calling _headSet_ above. 
When I call _pollFirst_ on that, it takes the first element, 
removes it from the set, and returns it from this method. 
Because the set's only a view, the element is removed from the view's source, 
my carts set. 
In this case, this is exactly what I want. 
Ok, so that's the inventory system for my grocery store.
</div>