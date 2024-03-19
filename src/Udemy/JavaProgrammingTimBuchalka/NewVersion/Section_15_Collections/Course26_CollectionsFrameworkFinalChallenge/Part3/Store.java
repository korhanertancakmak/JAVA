package CourseCodes.NewSections.Section_15_Collections.Course26_CollectionsFrameworkFinalChallenge.Part3;

import java.time.LocalDate;
import java.util.*;

public class Store {

    private static Random random = new Random();
    private Map<String, InventoryItem> inventory;
    private NavigableSet<Cart> carts = new TreeSet<>(Comparator.comparing(Cart::getId));
    private Map<Category, Map<String, InventoryItem>> aisleInventory;

    public static void main(String[] args) {

        Store myStore = new Store();
        myStore.stockStore();
        myStore.listInventory();

        myStore.stockAisles();
        myStore.listProductsByCategory();

        myStore.manageStoreCarts();
        myStore.listProductsByCategory(false, true);

//Part-1
/*
        In the last video, I added products to a physical cart, meaning it's not an online cart. I used the aisle inventory
    map to first get a map of products by product category. From that map, I retrieved the inventory item, using a product
    name, like apple or coffee. I used the method to both add items to the cart and to remove them, passing the quantity
    to be added or removed. These methods adjusted the reserved quantity, on the Inventory item, for that product.
*/
//End-Part-1

        myStore.carts.forEach(System.out::println);

//Part-6
/*
        Running that,

                Cart{id=1, cartDate=2023-12-02, products={P100=3, BC88=1, A100=6}}
                Cart{id=2, cartDate=2023-12-02, products={B100=10, L103=20}}
                Cart{id=4, cartDate=2023-12-03, products={BC99=1}}

    you can see I have three carts at this point, carts 1 and 2, with yesterday's date, and cart 4 with today's date.
    Lastly, I want to implement the abandon carts code. This code is going to remove items from abandoned carts, and then
    remove the carts, from the carts set.
*/
//End-Part-6

        myStore.abandonCarts();
        myStore.listProductsByCategory(false, true);
        myStore.carts.forEach(System.out::println);

//Part-7
/*
        I'll again print my inventory after that. And I'll print my cart's set. If I run this code,

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

    first I want you to see that there are 3 carts before this code executes, and two of them have a date of yesterday.
    After the execution, there is only one cart, the cart that has today's date. Also, look at the inventory, both before
    and after, looking just at the first four items. Before I ran the abandonedCarts method, I had 6 apples, 10 bananas,
    20 lemons, and 3 pears in reserve. Looking at the inventory printed after execution, you can see that the reserve quantities have been
    reset to zero. The only reserved quantity that's not zero, is the one that has tea, which is the product that's in
    my only remaining cart. Are you confused about how the carts got deleted, or do you remember that's what pollFirst
    really does? It's removing the cart from the view, when I execute it. The view is named oldCarts, which I got by
    calling headSet above. When I call poll first on that, it takes the first element, removes it from the set, and
    returns it from this method. Because the set's only a view, the element is removed from the view's source, my carts
    set. In this case, this is exactly what I want. Ok, so that's the inventory system, for my grocery store. This is the
    last lecture in our Collections Framework Section. You'll be using collections in almost everything you do. In the
    next section, I'm going to take a little bit of time, to talk about immutable and unmodifiable classes, and related
    topics, and why these are important.
*/
//End-Part-8
    }

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

//Part-2
/*
        I want to next create another cart, this time a virtual cart. Again, I want it to have yesterday's date, so I'll
    pass one to the constructor. I'll add the new cart to my carts set. Because it's a virtual cart, I'm going to add
    items, by getting them by the sku, from the full inventory map. You can imagine that if a shopper is picking items
    from an online catalog, the sku would be available from that process, and would just get passed like I'm doing here.
    The sku here is for lemons, and I want 20 lemons. I'll also add 10 bananas, sku B100. And I'll print the second cart.
    Running this code,

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

    you can see cart 2, with yesterday's date, and two products, B100, quantity 10, and L zero 3, quantity 20. Again,
    you can see these numbers, reflected in the qty reserved counts in the inventory.
*/
//End-Part-2

        Cart cart2 = new Cart(Cart.CartType.VIRTUAL, 1);
        carts.add(cart2);
        cart2.addItem(inventory.get("L103"), 20);
        cart2.addItem(inventory.get("B100"), 10);
        System.out.println(cart2);

//Part-3
/*
        I'll set up a new cart, another virtual one, but using today's date, so I'll pass zero. I'll add that cart to my
    carts set. I'll add some rice check, sku r777, and I'm going to order a lot of them, 998. I'll print this cart out.
    When I check this cart out, this should execute my low threshold code, so let me set that up now. I'll call check out
    cart, and pass cart3. This method returns a boolean, so if a false comes back, then I know something went wrong, and
    I can print that message. If I ran that now, I'd get a message that says something went wrong, and that's because I
    haven't implemented the check out method yet. I'll do that now.
*/
//End-Part-3

        Cart cart3 = new Cart(Cart.CartType.VIRTUAL, 0);
        carts.add(cart3);
        cart3.addItem(inventory.get("R777"), 998);
        System.out.println(cart3);
        if (!checkOutCart(cart3)) {
            System.out.println("Something went wrong, could not check out");
        }

//Part-5
/*
        Ok, so I'm going to create one more cart, a physical one, dated today. I'll add that to my carts set. I'm going
    to add 1 box of tea to this last cart. And I'll print that out. Running that,

                Cart{id=4, cartDate=2023-12-03, products={BC99=1}}

    you can see cart 4 and the inventory again. I can confirm that the reserved count for the last item in my inventory,
    herbal tea, is one. Let me print out my carts next in the main method.
*/
//End-Part-5

        Cart cart4 =  new Cart(Cart.CartType.PHYSICAL, 0);
        carts.add(cart4);
        cart4.addItem(aisleInventory.get(Category.BEVERAGE).get("tea"), 1);
        System.out.println(cart4);

    }

//Part-4
/*
        First, I'll just change the return false to return true. Next, I want to set up a for loop before the return
    statement. I'm going to get the view, the entry set of the products in my cart, and loop through each of these Entries.
    Notice we have an error there with the get products method. It doesn't exist. I meant to add a getter two lectures ago
    when setting up our Cart. Let me switch over quickly now and add the getter so that the code compiles. Right thats
    the getter created, lets go back our Store class again. Back in the check out cart method. Remember that Entry is a
    nested interface type on Map, and each of these has a key and a value. I can get the key using getKey, and the value
    using getValue. getKey is going to return the product sku, which is how I've keyed products in my products map.
    getValue is going to return the quantity of this product, how many I want to buy. I really want the inventory item,
    and I can get that from my full inventory set, using cart item get key, which returns the sku. I get quantity using
    get value. I'll assign that to a local variable, qty. I'm going to invoke sellItem, on each inventory item, passing
    the quantity. If that method returns false, I want to stop processing and quit out of this method, returning false.
    If this loop ends successfully, it means I've executed sell Item on every inventory item successfully. That means I
    can print a sales slip. And after that, I want to remove the cart from my carts list. Now, I'll re run my code,

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

    Here, you can see, I get a message, that the system is ordering, more rice chex cereal, 1000 more units. I also get
    the sales slip printed after that. Thank you for your sale, and it prints the line items, and the full price of 998
    boxes of rice chex. After this, the inventory is printed for all products. If you find chex mix in the inventory, sku
    R777, You can see that the total quantity, the first number in square brackets, is now 0 0 0 2, and the reserved qty
    is zero. The check out code that adjusts the inventory is working. It reset the reserved count (subtracting the qty
    from the reserved quantity). It decremented the quantity from the total qty, the stock in inventory. It also automatically
    ordered more riced check, when the inventory total quantity went below the threshold, the low qty, which I had set
    to 5 for all inventory items.
*/
//End-Part-4

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

//Part-7
/*
        An abandoned cart is going to be any cart that doesn't have today's date. To set this up, I first want to get the
    current day of the year. I'll call LocalDate now, then chain getDay of Year, assigning that result to my day of year
    variable. I'll set up a local variable, last Cart and set it to null. This variable will get set to the last cart in
    the carts set, with a date that's not equal to the current date. I'll loop through the carts. If the cart's day of
    the year is equal to today, I want to break this loop. I'll assign the last cart to the current cart instance. I've
    kind of dummied my data up, but you can imagine that the shopping carts, which are in cart id order, would have newer
    carts with ids, that are greater than older carts. This means I can loop, from the start of my sorted set of carts,
    and as soon as I find a cart, that has a day of the year the same as today's, I can break the loop. I assign each
    current cart instance, until that point, to the last cart. This means, when I break out of this loop, last cart should
    be referencing, the last cart of the previous day. I can now use last cart to splice my cart set, using the head set
    method. I'll set up a variable called oldCarts and assign that the result of calling the head set method on carts.
    I'll pass last cart, and I'm going to set the inclusive flag to true. Now old carts has all the carts from our carts
    set, that don't have today's date. I'm going to set up a local variable called abandoned cart, initializing it to null.
    I'll kick off a while loop, executing poll first, on the old carts set, assigning whatever comes back from that to
    the abandonedCart variable. If that's not null, my loop statements will get executed. Each cart has a map of products,
    and I want to loop through the keySet of that collection, each key represents a sku. I'll use the sku, to find the
    product in the inventory collection. I'll then call releaseItem on that inventory Item, which will reset the reserved
    qty for the item. This code is simply going to adjust the reserved quantity in my inventory, subtracting the abandoned
    product's qty from the reserved quantity. I'll call this method from my main method.
*/
//End-Part-7

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

        products.forEach(p -> inventory.put(p.sku(), new InventoryItem(p,
                random.nextDouble(0, 1.25), 1000, 5)));

    }

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

    private void listInventory() {

        System.out.println("--------------------------------------");
        inventory.values().forEach(System.out::println);
    }
}
