package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course26_CollectionsFrameworkFinalChallenge.Part2;

import java.util.*;

//Part-1
/*
        In the last lecture, I created the Product, the Inventory Item, and the Cart. If you decided to skip walking
    through that and want to grab the code for those types, give yourself a few minutes to examine the code. Product is
    a record, and InventoryItem is a pretty simple class with methods to adjust quantities. For the Cart class, I used
    a hashmap for the products. I chose a map, so I could get items by their sku, and I wanted to store the qty of the
    item in that map. I also decided items in my cart really don't need to be ordered. If you think about a physical
    shopping cart, it can often just be a pile of items. A Set might have been a good alternative if only one item for
    each product was allowed in the cart. I could also have used an ArrayList, or two of them, one to manage the products,
    and one to manage the quantity for each product, but that's really the purpose of a Map. In this lecture, I'm going
    to finish coding the Store class, and start working with the inventory counts, setting up a few shopping sessions.

        Getting back to the Store now, I first want to set up a static field, for a random instance. I'll use it to assign
    different prices to my products later. You've seen me do something similar to this before as well. I'll next set up
    my collection fields on this class. I'm going to make inventory a map, keyed by the sku. The carts collection can be
    a set. I'm going to make it a NavigableSet reference type, because I want to use navigable Set methods as I look for
    abandoned carts later. I'll instantiate it as a TreeSet, passing a Comparator to that constructor, so this tree set
    will be ordered by the cart's id. The aisle inventory, is going to be a map, keyed by the product category. For this,
    the type of the element, is going to be another map, a nested map. The nested map will have the product name as its
    key, and the Inventory Item as the value. You might be asking, why do I have this aisleInventory? Well, somebody in
    a store is probably going to be looking for items by an aisle category, and a product name or description. These two
    maps emulate the two different ways shoppers are likely to get a product. A shopper in the store, will be looking for
    items, first by category, and then by recognition of the product by name. An online shopper may proceed that way, but
    may also simply enter a catalog number, the sku. Next, I'm going to set up a bunch of methods, which I'll implement
    later. I'll have a method to manage a store cart. I need a method to check out a cart, that takes a cart and returns
    a boolean if that operation was successful. For now, I'll return false. I want a method called abandon Carts. And a
    method called list products by category. I also want two helper methods to set up my store. The first one is stock store.
    The second one is stock Aisles. Ok, This might look like a lot of work, but it'll go fast.
*/
//End-Part-1

public class Store {

    private static Random random = new Random();
    private Map<String, InventoryItem> inventory;
    private NavigableSet<Cart> carts = new TreeSet<>(Comparator.comparing(Cart::getId));
    private Map<Category, Map<String, InventoryItem>> aisleInventory;

    public static void main(String[] args) {

//Part-3
/*
        I'll create a new store instance, and assign that to the myStore variable. Then just call stockStore on this. And
    I'll print the inventory. Running this code,

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

    I get all my inventory printed, the sku as the key, the product information, and the price, which was randomly generated.
    Because inventory is a hash map, there's no particular order here. Now I'll code the stockAisles method. How should
    I set up the aislesInventory collection?
*/
//End-Part-3

        Store myStore = new Store();
        myStore.stockStore();
        myStore.listInventory();

//Part-5
/*
        and add a call to these two methods next. Running that,

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

    you can see how my aisles are stocked, and in what order. The order of my aisles is in the same order, I set up my
    constants in the Category enum. And the products are alphabetical by name. Ok, so now that my store is stocked and
    there are products in my aisles, I can start letting the customers in. I'll set up the code for the manageStoreCarts
    next.
*/
//End-Part-5

        myStore.stockAisles();
        myStore.listProductsByCategory();

//Part-7
/*
        Invoking manage Store Carts on my store. If I run that,

                ---(same)
                Cart{id=1, cartDate=2023-12-02, products={P100=5, BC88=1, A100=6}}

        you can see, I get my cart's information printed out. My products (apples) are printed by their sku, A 100. And
    look at the date on my cart. It's yesterday's date. I want to print the inventory again, after this, but this time
    I want my inventory printed out by category, then product, but with the inventory details.
*/
//End-Part-7

        myStore.manageStoreCarts();

//Part-9
/*
        I'll call it with no headers, and with the detail included. Running this code,

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

        you can see inventory details printed, but this time by category, and within category, by product name. I can
    confirm that the reserved amount for apples is 6, but the qty total is still 1000, since I haven't actually sold the
    product yet. I'll add a couple more items to my cart. This time, I'll just do this in one statement. I'll add a produce
    item, pear, and five of those. And then I want a beverage, coffee, and just quantity one for this. Running this code,

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

    you can see my cart has three items listed by sku. And the inventory is showing the reserved quantities, 5 for pear,
    and 1 for coffee. Now, let's remove a couple of pears from the last cart. I'll call remove Item, and pass the produce
    category with pear, and 2, so I want to remove 2 of the 5 pears in my cart. And I'll print the cart to confirm that's
    working. I get the message that 2 pears were removed. My cart after the removal has qty 3 for P 100, the sku for pears.
    And the inventory was adjusted too, so I have 3 reserved, not 5, so that's good. This means that so far, the inventory
    is being managed correctly when adding or removing items from a cart. In the next lecture, I want to check out a shopping
    cart, and delete abandoned carts.
*/
//End-Part-9

        myStore.listProductsByCategory(false, true);

    }

//Part-6
/*
        First, I want a cart, cart1, and I'll assign that a new instance of the Cart, passing my type. Since CartType is
    a nested enum, it's implicitly static. That means I need to reference it, using the Cart class name, then CartType,
    and then the constant I want to pass here, and this is a PHYSICAL cart. I'm also passing an integer, which will adjust
    the cart date. This means one day will be subtracted from today when creating this cart. I want some carts with yesterday's
    date, to test abandoning them later. I'll add the new cart to my carts set on this store. I can add items by using the
    aisleInventory map, first selecting the aisle by the product category, and then the product name. I'll add the apples
    to my cart, and the quantity, six. I'll print this first cart out. Now, I'll make a call to this method in the main
    method,
*/
//End-Part-6

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

    private boolean checkOutCart(Cart cart) {

        return false;
    }

    private void abandonCarts() {

    }

//Part-8
/*
        I'll copy the listProductsByCategory method, and paste a copy below. In this overloaded version, I want two parameters,
    an includeHeader boolean, and an IncludeDetail, also a boolean. I only want to print the key in the dashed part, if
    includeHeader is true, so I'll slip an if statement in there. Now, I want to print just the keys if no detail is
    requested, but the value otherwise, so I'll set this up with an if then else statement around that last statement.
    I'll insert the if statement, if includeDetail is false, I'll just print the keys, as before. Otherwise I want to
    print the value. So I'll add an else statement, And loop through the values of the sub map, printing each. Now I'll
    go to the overloaded version, with no parameters. I'll remove the code that's there. I'll replace that with just a
    call to the overloaded method, passing true for includeHeader, and false for include detail. This means, the default
    will include a header, but no inventory detail. Ok, now I want to use the overloaded version in my main method.
*/
//End-Part-8

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

//Part-2
/*
        We have to set up some products first, which will let us stock the store and aisles, so I'm going to do this in
    the stock store method. First, I'll instantiate my inventory field to a new HashMap. Now, I'll set up a list of products,
    a new Array list. I'm going to paste my products in. If you wish to do the same, this bit of code will be in the
    resources section of this lecture, in a file called productData. Here, I'm using my product record to set up a bunch
    of products, in my five categories. The sku is the first field, then a simple name to describe the product, followed
    by it's manufacturer, and then the category. Now that I have my products, I can set up my inventory map. I've made
    it a hash map, because I really don't need my inventory to be ordered, but I use a map, because it would be nice to
    quickly retrieve the information, by the sku field. I'll loop through products, and use a lambda expression, to put
    a new instance, of a new inventory item, into the inventory map, using the sku as the key. I can create an inventory
    item, with a product, a price, an inventory quantity total, and low inventory number. Here, I'm using the nextDouble
    method on my static random field, passing it 0 and 1.25. This is going to generate a double value, somewhere between
    0 and 1.25. I'll use that generated value as the price of my item. Ok, that's my inventory set up. Now, I want to
    print it out to confirm that my map looks the way I want it to. I'll do that by calling for each on inventory. I'm
    going to put this in another private method, that I'll just call list inventory. I'll loop through the values on my
    inventory, and print each out. I'll test this out, before I stock my store aisles, getting back to the main method.
*/
//End-Part-2

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

//Part-4
/*
        Well, I know I want it to be keyed by the product category, but what should
    the collection element be? If you think about how a shopper might look for their item, they'd first find the right
    aisle, then search the shelves for the product, or product name, they want. This is why I wanted the map to contain
    a nested map. I'll set this up as a new instance of an enum map, passing it my Category's class. I'll loop through
    my inventory. I'll get category, for the item, by getting that from the product. I want a new local variable, for my
    nested map. I'll check if it's already in my aisle inventory, using the aisle or category key. If that's null, it's
    not in my map. So I'll set up a new tree map. Again, if you think about items on shelves, they'd be stocked on the
    shelves in some order, in my case it will be by product name. In either case, I'm going to add the inventory item,
    keyed by the product name. And I only need to put this in the map, if it's not there already, so I'll use put If
    ABSENT. Again, I'll print this out, but I want to do this in the list products by category method. First I'll loop
    through my aisle inventory's key, the product category. I'll print the key, the category, between a couple of dashed
    lines. I'll get the map by that key, and I'll loop through the nested map's keys. I'll go back to the main method,
*/
//End-Part-4

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
