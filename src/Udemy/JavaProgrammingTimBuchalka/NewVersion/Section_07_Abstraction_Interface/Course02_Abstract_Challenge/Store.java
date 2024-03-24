package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_Abstraction_Interface.Course02_Abstract_Challenge;

//Part-2
/*
        First, let me show you a class diagram of what we'll be building.

                                                      __________________________________
               ______________________________         |abstract ProductForSale =>      |
               |Store =>                    |         |________________________________|
               |____________________________|         | type        : String           |
               |  ArrayList<ProductForSale> |<>-------| price       : double           |
               |----------------------------|         | description : String           |
               |  addItemToOrder            |         |--------------------------------|
               |  printOrder                |         | void printPricedItem(int qty)  |
               |____________________________|         | double getSalesPrice(int qty)  |
                                                      | abstract void showDetails()    |
                                                      |________________________________|
                                                                     ↑
               ______________________________          ______________↑__________________
               |OrderItem (Record)          |          ↑             ↑                 ↑
               |____________________________|   _______↑______  _____↑________   ______↑_______
               |  qty     : int             |   | Product A  |  | Product B  |   | Product C  |
               |  product : ProductForSale  |   |------------|  |------------|   |------------|
               |----------------------------|   |____________|  |____________|   |____________|
               |____________________________|

    This covers all the requirements we talked about. You'll notice I'm specifying that OrderItem will be a record, and
    this is just to keep the code simple. And I'm not really specifying what our store products are, we can really put
    anything there.
*/
//End-Part-2


import java.util.ArrayList;

//Part-19
/*
        Create the order item record, with 2 parameters. A line item on an order would consist of a quantity and a product,
    and in this case, quantity is an int. And we're using the ProductForSale abstract type, as the parameter type for product.
    Instead of creating an Order class, I'm just going to create an ArrayList of orders, as the order type, and that'll
    be one of the arguments. You wouldn't really do this in an application. Maybe you took extra time to create an order
    class, with an order id and some other fields that made sense as well as this list of order items. So now, I'm going
    to write the addItemToOrder method. Going back to the end of Store class,
*/
//End-Part-19

record OrderItem(int qty, ProductForSale product) {}

public class Store {
//Part-14
/*
        I'm defining and then instantiating an ArrayList of ProductForSale here. Ok, so hopefully you're getting comfortable
    with the ArrayList, and we've specified the parameter type to be our abstract class ProductForSale. And I'll add some
    kind of art object, to the inventory list.
*/
//End-Part-14

    private static ArrayList<ProductForSale> storeProducts = new ArrayList<>();

    public static void main(String[] args) {

//Part-3
/*
        Before doing anything here, we need that abstract class, and that's called ProductForSale. I'll make that a new
    public class in the same package.
*/
//End-Part-3

//Part-15
/*
        For the first product added to the list, I'll add an ArtObject of type Oil Painting. For the second product, I'll
    add an ArtObject of type Sculpture.
*/
//End-Part-15

        storeProducts.add(new ArtObject("Oil Painting",1350,
                "Impressionistic work by ABF painted in 2010"));
        storeProducts.add(new ArtObject("Sculpture", 2000,
                "Bronze work by JKF, produced in 1950"));

//Part-16
/*
        Now notice, we're calling the add method on the ArrayList, and we're passing it a new instance, of the concrete
    class ArtObject, with some parameters. Now storeProducts, that list, had a type parameter of ProductForSale, the abstract
    class, but any class that is a subclass of ProductForSale can be an element in this list. When we get to generics a
    little later in the section, we'll explain this in more detail. But this is the same concept as making our method
    parameters be more generic, than the runtime type will be, which enables polymorphism. Ok, so we have a couple of
    products. Let's add a couple of methods to this Store class.
*/
//End-Part-16

        //added after part-22
        storeProducts.add(new Furniture("Desk", 500, "Mahogany Desk"));
        storeProducts.add(new Furniture("Lamp", 200, "Tiffany Lamp with Hummingbirds"));
        //added after part-22
        listProducts();

//Part-18
/*
        Call the list product method, and running that,

                            ------------------------------
                            This Oil Painting is a beautiful reproduction
                            The price of the piece is $1350,00
                            Impressionistic work by ABF painted in 2010
                            ------------------------------
                            This Sculpture is a beautiful reproduction
                            The price of the piece is $2000,00
                            Bronze work by JKF, produced in 1950

    we get what we might see on a products page, for pieces of art. Next I want to create the OrderItem record. This will
    be used to create a line item, for an order. Going back to the Store.java source file, let's add it there,
*/
//End-Part-18

        System.out.println("\nOrder 1");
        var order1 = new ArrayList<OrderItem>();
        addItemToOrder(order1, 1, 2);
        addItemToOrder(order1, 0, 1);
        printOrder(order1);

//Part-22
/*
        First, we just print out that this is order 1. Next, we create a new ArrayList of our record, OrderItem. Remember
    this array list represents the order. Now, this declaration and assignment is a little different from the usual one,
    though I showed it to you, in the Lists section of the course. You can use the var keyword for typed ArrayLists, but
    when you do, you do have to specify the type, on the right side of the assignment operator, in <>. And then, we call
    the addItemToOrder method, passing the order (which is really just the array list). The second argument is the index
    location of the product in the inventory list. For the last argument, we pass the quantity of the item we're ordering.
    And we'll make this call twice, with different product indexes and different quantities. Then we call printOrder, passing
    it the array list of order item records. Now, when I run this,

            Order 1
             2 qty at $ 2000,00 each,  Sculpture       Bronze work by JKF, produced in 1950
             1 qty at $ 1350,00 each,  Oil Painting    Impressionistic work by ABF painted in 2010
            Sale Total = $5350,00

    we get the line items of the order, printed out. This order request has 2 Sculptures at $2000 each, and 1 oil painting,
    which gave us a sales total of $5350. We can see our product's printPricesItem, and getSalesPrice methods were executed.
    Now, for our second type of product, we could go through the same process, but I'm just going to copy Art Object, pasting
    it in the same package, and rename it Furniture. For the furniture class, I'll change the showDetails method, making
    it unique for this class.

        Next, I'll add some pieces of furniture to our store's inventory list. I'll add a Furniture object to the list,
    with the values desk, 500, and Mahogany desk. And for the second Furniture object that I add to the list, it'll have
    values lamp, 200, and Tiffany Lamp with Hummingbirds. And now I want to create a second order. I'll copy order 1, and
    I'll paste it below just before the closing curly brace.
*/
//End-Part-22

        System.out.println("\nOrder 2");
        var order2 = new ArrayList<OrderItem>();
        addItemToOrder(order2, 3, 5);
        addItemToOrder(order2, 0, 1);
        addItemToOrder(order2, 2, 1);
        printOrder(order2);

//Part-23
/*
        Here again, we're printing out the order number first, and this is order 2. Then we set up a new ArrayList, which
    represents the second order. Right now, we're adding one product, specifying the product index as the second argument,
    and the quantity as the third argument, as we did before. In this case, I'll use index 3, which we know is a furniture
    item. And we print the order. Before I run this, I want to copy that addItemToOrder statement and paste it twice, directly
    below it, so we have 3 line items. I'll change the second one to have a 0, index and a quantity of 1. And for the 3rd
    item I'll use index 2, which is a piece of furniture in the inventory, and again quantity 1. And running the code,

                Order 2
                 5 qty at $  200,00 each,  Lamp            Tiffany Lamp with Hummingbirds
                 1 qty at $ 1350,00 each,  Oil Painting    Impressionistic work by ABF painted in 2010
                 1 qty at $  500,00 each,  Desk            Mahogany Desk
                Sale Total = $2850,00

    we have 2 new items, the desk and lamp printed out in the inventory list. And you can see Order 2, printed out. And
    on the second order, we have 5 lamps, an oil painting, and a desk. So that was the Abstract Class Challenge.
*/
//End-Part-23
    }

    public static void listProducts() {

        for (var item : storeProducts) {
            System.out.println("-".repeat(30));
            item.showDetails();
        }
    }

//Part-17
/*
        The first will list all our products. I'll use the enhanced for loop, to go through each product in the list. I'll
    then print out a line, using the repeat method from the String class. And finally, I'll make a call to show details.
    This code just uses a for loop, and I'm using var. I like to use var in places where I want things to be very general,
    as I do here. And Java can infer the type, from our storeProducts field, and infers it to be a ProductForSale. And so
    we can call showDetails on each loop variable. We first print a dashed line, with 30 dashes, to separate each entity.
    You've seen me do this several times. In the main method, I'll call that,
*/
//End-Part-17

    public static void addItemToOrder(ArrayList<OrderItem> order, int orderIndex, int qty) {

        order.add(new OrderItem(qty, storeProducts.get(orderIndex)));
    }

//Part-20
/*
        This method takes an ArrayList, as a parameter, which represents the order, and we're passing an index as the second
    parameter. This index is the index of the product in our inventory field. And then, we have quantity, which is the
    quantity of product they're ordering. Then we use the add method on the order ArrayList, and pass it a new OrderItem,
    a record. And that's got the quantity, and we retrieve the product from inventory, using the index passed to this method.
    And now, I'll create the printOrder method. I'll make it static void, and have an ArrayList of Order Items, as a
    parameter.
*/
//End-Part-20

        public static void printOrder(ArrayList<OrderItem> order) {

            double salesTotal = 0;
            for (var item : order) {
                item.product().printPricedItem(item.qty());
                salesTotal += item.product().getSalesPrice(item.qty());
            }
            System.out.printf("Sale Total = $%6.2f %n", salesTotal);
        }

//Part-21
/*
        This method again takes our ArrayList of OrderItem. We're going to figure out the sales Total, so we initialize
    a local variable to 0. Then we loop through the order items. And we get the product from our item, with the product
    accessor method on the record, and simply chain that to the printPriceItem. But printPricedItem takes a quantity, which
    is the number of items ordered. And that's in our order item record, so we again use the accessor method, quantity
    for that. And the same goes with calculating the sales price. We call the getSalesPrice method on the item record,
    passing it the quantity of items ordered, and we add that to our running total, salesTotal. Then we print that out.
    So now, I'll create our first order, in the main method of the Store class.
*/
//End-Part-21
}
