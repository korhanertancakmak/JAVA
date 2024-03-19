package CourseCodes.NewSections.Section_15_Collections.Course26_CollectionsFrameworkFinalChallenge.Part1;

//Part-1
/*
                                            Build a Store's Inventory System

        Using some combination of the classes in the Collections Framework, see if you can create the classes and methods
    shown below.

            __________________________
            | Store                  |
            |________________________|          __________________________
            | Inventory              |          | Product                |
            | carts                  |          |________________________|
            | aisleInventory         |          | sku                    |
            |________________________|          | name                   |
            | manageStoreCarts       |          | manufacturer           |
            | checkOutCart           |          | category               |
            | abandonCarts           |          |________________________|
            | listProductsByCategory |
            |________________________|          __________________________
                                                | InventoryItem          |
                __________________________      |________________________|
                | Cart                   |      | product                |
                |________________________|      | qtyTotal               |
                | id                     |      | qtyReserved            |
                | products               |      | qtyRecorder            |
                | date                   |      | qtyLow                 |
                | type                   |      | salesPrice             |
                |________________________|      |________________________|
                | addItem                |      | reserveItem            |
                | removeItem             |      | releaseItem            |
                | printSalesSlip         |      | sellItem               |
                |________________________|      | placeInventoryOrder    |
                                                |________________________|

    I'm purposely excluding types and relationships here. This is called a conceptual model, where you start by drawing
    the needs of the system, and identifying fields and methods. The information on this diagram should be enough to get
    you started. A product's information is defined by its manufacturer, so assume the information on Product isn't mutable.
    A sku is short for stock keeping unit, and is a unique identifier for the product. The category should be one of a
    defined set of categories, like product or dairy for example, for a grocery item. The inventory item is the store's
    information specific to the product, like price and quantities of each product in stock.

        qtyTotal    : The total quantity is the amount that's still in stock, so it could be in any of your carts, on your
                     aisles, or in your warehouse.
        qtyReserved : The qty that's reserved is the product that's in the carts, but not yet sold.
        qtyRecorder : The qty reorder amount is what you'd use to order more product.
        qtyLow      : The low quantity is the trigger, or threshold, to order more product. When the low qty is reached,
                     your system should order more product.

        The cart type has an id, and a collection of products, that changes as a shopper puts in new items, or removes
    them from their cart. The cart will have a date, and also a type, to indicate if the type is physical or virtual.
    Each of the fields on the Store class are collections. Which you choose is up to you. Inventory is a collection of
    Inventory Items. Carts is a collection of carts. The aisle Inventory is the inventory that's displayed physically on
    store shelves. You can assume aisles can be keyed by the product category. Your store should have a method to abandon
    physical and virtual carts, if the date associated with the cart, is different than the current date.

                    Try to use a variety of Collections Framework implementations and methods

        Think about the fields that would use collections, and the types you have to choose from. You can also use
    collections to help you manage some of the functionality in the methods. Remember the three interfaces, List, Set,
    and Map, and the classes that implement these interfaces. Do you need to allow duplicates in the collection? Do you
    need things to be sorted? Is insertion order good enough? Do you need a way to organize the data into a key value
    system, to make some of the operations easier? What methods on the Collections classes might be useful for some of
    this functionality? Would set math be useful? Or would navigational methods be simpler? Are there any methods on
    the Collections class that would make sense to use here? This may sound like a lot, but take it one type at a time,
    and one operation at a time. Collections are such an important Java topic, so take time here, to challenge yourself
    as much as possible.


        I'm going to use the rest of this lecture to implement Product, ItemInventory and Cart, which is mainly just setup,
    and not specific to the Collections Framework, except for the products field on Cart. I'll be using minusDays on LocalDate,
    which simply subtracts whatever number of days you pass, from the date you're using. I'll start with the Store class,
    and include a main method on that. Once I've got that, I'm going to set up some of my other types. First, I want an
    enum, Category, and for that, I'll just create a new Java class, pick enum, and call it Category. I'll set up some
    constants, in the order a store might be laid out, so maybe PRODUCE, dairy, cereal, meat, and beverage to start with.
    Next, I want a Product, and I'm going to make this a record, so new JavaClass, Record, name is Product. You'll remember
    I said this should be immutable, which is why I want a record here. And I can set up my list of fields. String sku,
    String name, String mfgr for manufacturer. The last one will be type Category, the enum I just created. I'll create
    an InventoryItem class next.
*/
//End-Part-1

public class Store {

    public static void main(String[] args) {

    }
}
