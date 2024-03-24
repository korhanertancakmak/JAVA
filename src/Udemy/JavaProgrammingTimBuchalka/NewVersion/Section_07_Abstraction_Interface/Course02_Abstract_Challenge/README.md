# Abstraction Challenge
<div align="justify">

First, let me show you a class diagram of what we'll be building.

![image02](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_07_Abstraction_Interface/images/image02.png?raw=true)

This covers all the requirements we talked about. 
You'll notice I'm specifying that OrderItem will be a record, 
and this is just to keep the code simple. 
And I'm not really specifying what our store products are 
we can really put anything there.

```java  
public class Store {
    public static void main(String[] args) {
        
    }
}
```

Before doing anything here, we need that abstract class, 
and that's called ProductForSale.
I'll make that a new public class in the same package.
And I have to include the abstract modifier. 
And I have to add 3 attributes: **type**, **price**, and **description**.

```java  
public abstract class ProductForSale {

    protected String type;
    protected double price;
    protected String description;

    public ProductForSale(String type, double price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }
}
```

I'm making all the attributes protected, 
which means the subclasses can access and modify these directly as well.
We want this because our overridden methods will need access to some attributes. 
I'll generate the constructor with all three fields.
Let's add the concrete methods. 
First, getSalesPrice, and that takes an int for the quantity.

```java  
public double getSalesPrice(int qty) {
    return qty * price;
}
```

And there, we just return qty times the price, to get the total sales price. 
Next, we need the printPricedItem method.

```java  
public void printPricedItem(int qty) {
    System.out.printf("%2d qty at $%8.2f each,  %-15s %-35s %n",
            qty, price, type, description);
}
```

Now this format string is a bit complex, so let me talk about it a minute. 
%2d means an integer will get printed with two digits, right justified. 
Then %8.2f means a decimal number, a double or float, 
will get printed with a precision of two digits after the period,
and a total width of 8. 
Then there's %-15s, and this prints out a String, the dash means left justify that, 
and the 15 means allow 15 spaces as a minimum.
That's our item type. 
And then we'll justify the description, the last thing we want printed on the line item. 
Finally, I'll add the abstract method declaration on this class.

```java  
public abstract void showDetails();
```

And that's our abstract class, ProductForSale. 
It's abstract because we don't really know what kind of products we're supporting yet, 
but we know what operations we want, regardless of the type of product.
And we know our Store will want to show the details of the product, 
and the product can customize that as needed.
I'm going to set up the storefront, and my store is going to sell objects of art and furniture. 
I'll create a new class in the same package, calling that ArtObject, 
and it will extend the abstract class, ProductForSale.
And right away we have a problem, and you probably know what that is. 
This class needs to implement the abstract method, showDetails, so let me do that.

```java  
public class ArtObject extends ProductForSale{

    public ArtObject(String type, double price, String description) {
        super(type, price, description);
    }
    
}
```

But this class still doesn't compile, for the same reason we saw in a previous course. 
We need to set up a constructor. 
And I'll generate that in the usual way.
Notice it calls super, the super constructor, 
which is the constructor on the abstract class, 
and that has the three parameters we declared on ProductForSale. 
Let's put some code in that overridden method.

```java  
@Override
public void showDetails() {

    System.out.println("This " + type + " is a beautiful reproduction");
    System.out.printf("The price of the piece is $%6.2f %n", price);
    System.out.println(description);
}
```

And this method's implementation is really individual to any product
that subclasses ProductForSale. 
We print out some information and the price, and we directly access that price field, 
because we declared it protected on ProductForSale.
So that's it, that's all we need to do with that class. 
Now, back to the Store, I want an inventory of products, 
so I'll make that a private static field on that class.

```java  
private static ArrayList<ProductForSale> storeProducts = new ArrayList<>();
```

I'm defining and then instantiating an ArrayList of ProductForSale here. 
Ok, so hopefully you're getting comfortable with the ArrayList, 
and we've specified the parameter type to be our abstract class ProductForSale. 
And I'll add some kind of art object to the inventory list.

```java  
public class Store {
    private static ArrayList<ProductForSale> storeProducts = new ArrayList<>();
    public static void main(String[] args) {

    }
}
```

For the first product added to the list, I'll add an ArtObject of type Oil Painting. 
For the second product, I'll add an ArtObject of type Sculpture.

```java  
storeProducts.add(new ArtObject("Oil Painting",1350, "Impressionistic work by ABF painted in 2010"));
storeProducts.add(new ArtObject("Sculpture", 2000, "Bronze work by JKF, produced in 1950"));
```

Now notice, we're calling the add method on the ArrayList, 
and we're passing it a new instance of the concrete class ArtObject, with some parameters. 
Now storeProducts, that list, had a type parameter of ProductForSale, the abstract class, 
but any class that is a subclass of ProductForSale can be an element in this list. 
When we get to generics a little later in the section, we'll explain this in more detail. 
But this is the same concept as making our method parameters be more generic 
than the runtime type will be, which enables polymorphism. 
Ok, so we have a couple of products. 
Let's add a couple of methods to this Store class.

```java  
public static void listProducts() {
    for (var item : storeProducts) {
        System.out.println("-".repeat(30));
        item.showDetails();
    }
}
```

The first will list all our products. 
I'll use the enhanced for loop to go through each product in the list. 
I'll then print out a line, using the repeat method from the String class. 
And finally, I'll make a call to show details.
This code just uses a for loop, and I'm using var. 
I like to use var in places where I want things to be very general, as I do here. 
And Java can infer the type from our storeProducts field, and infers it to be a ProductForSale. 
And so we can call showDetails on each loop variable. 
We first print a dashed line, with 30 dashes, to separate each entity.
You've seen me do this several times. 
In the main method, I'll call that:

```java  
listProducts();
```

Call the list product method, and running that:

```java  
------------------------------
This Oil Painting is a beautiful reproduction
The price of the piece is $1350,00
Impressionistic work by ABF painted in 2010
------------------------------
This Sculpture is a beautiful reproduction
The price of the piece is $2000,00
Bronze work by JKF, produced in 1950
```
                            
We get what we might see on a product page, for pieces of art. 
Next, I want to create the OrderItem record.
This will be used to create a line item for an order.
Going back to the Store.java source file, let's add it there:

```java  
record OrderItem(int qty, ProductForSale product) {}
```

Create the order item record with two parameters. 
A line item on an order would consist of a quantity and a product,
and in this case, quantity is an int.
And we're using the ProductForSale abstract type, as the parameter type for product.
Instead of creating an Order class, I'm just going to create an ArrayList of orders, 
as the order type, and that'll be one of the arguments. 
You wouldn't really do this in an application.
Maybe you took extra time to create an order class,with an order id 
and some other fields that made sense as well as this list of order items. 
So now, I'm going to write the addItemToOrder method. 
Going back to the end of Store class,

```java  
public static void addItemToOrder(ArrayList<OrderItem> order, int orderIndex, int qty) {
    order.add(new OrderItem(qty, storeProducts.get(orderIndex)));
}
```

This method takes an ArrayList as a parameter, which represents the order, 
and we're passing an index as the second parameter. 
This index is the index of the product in our inventory field. 
And then, we have quantity, which is the quantity of product they're ordering. 
Then we use the add method on the order ArrayList, and pass it a new OrderItem, a record. 
And that's got the quantity, and we retrieve the product from inventory, 
using the index passed to this method.
And now, I'll create the printOrder method. 
I'll make it static void, and have an ArrayList of Order Items, as a parameter.

```java  
public static void printOrder(ArrayList<OrderItem> order) {
    double salesTotal = 0;
    for (var item : order) {
        item.product().printPricedItem(item.qty());
        salesTotal += item.product().getSalesPrice(item.qty());
    }
    System.out.printf("Sale Total = $%6.2f %n", salesTotal);
}
```

This method again takes our ArrayList of OrderItem. 
We're going to figure out the sales Total, so we initialize a local variable to 0. 
Then we loop through the order items. 
And we get the product from our item, with the product accessor method on the record, 
and simply chain that to the printPriceItem. 
But printPricedItem takes a quantity, which is the number of items ordered. 
And that's in our order item record, so we again use the accessor method, 
quantity for that. 
And the same goes with calculating the sales price. 
We call the getSalesPrice method on the item record,
passing it the quantity of items ordered, and we add that to our running total, salesTotal. 
Then we print that out.
So now, I'll create our first order, in the main method of the Store class.

```java  
System.out.println("\nOrder 1");
var order1 = new ArrayList<OrderItem>();
addItemToOrder(order1, 1, 2);
addItemToOrder(order1, 0, 1);
printOrder(order1);
```

First, we just print out that this is order 1. 
Next, we create a new ArrayList of our record, OrderItem. 
Remember this array list represents the order. 
Now, this declaration and assignment is a little different from the usual one, 
though I showed it to you, in the Lists section of the course. 
You can use the var keyword for typed ArrayLists, 
but when you do, you do have to specify the type, 
on the right side of the assignment operator, in < >. 
And then, we call the addItemToOrder method, passing the order (which is really just the array list). 
The second argument is the index location of the product in the inventory list. 
For the last argument, we pass the quantity of the item we're ordering.
And we'll make this call twice, with different product indexes and different quantities. 
Then we call printOrder, passing it the array list of order item records. 
Now, when I run this:

```java  
Order 1
 2 qty at $ 2000,00 each,  Sculpture       Bronze work by JKF, produced in 1950
 1 qty at $ 1350,00 each,  Oil Painting    Impressionistic work by ABF painted in 2010
Sale Total = $5350,00
```

We get the line items of the order, printed out. 
This order request has two Sculptures at $2000 each, and one oil painting, 
which gave us a sales total of $5350. 
We can see our product's printPricesItem, and getSalesPrice methods were executed. 
Now, for our second type of product, we could go through the same process, 
but I'm just going to copy Art Object, pasting it in the same package, and rename its Furniture. 
For the furniture class, I'll change the showDetails method, making it unique for this class.

```java  
storeProducts.add(new Furniture("Desk", 500, "Mahogany Desk"));
storeProducts.add(new Furniture("Lamp", 200, "Tiffany Lamp with Hummingbirds"));
```

Next, I'll add some pieces of furniture to our store's inventory list. 
I'll add a Furniture object to the list, with the value desk, 500, and Mahogany desk. 
And for the second Furniture object that I add to the list, 
it'll have values lamp, 200, and Tiffany Lamp with Hummingbirds. 
And now I want to create a second order. 
I'll copy order 1, and I'll paste it below just before the closing curly brace.

```java  
System.out.println("\nOrder 2");
var order2 = new ArrayList<OrderItem>();
addItemToOrder(order2, 3, 5);
addItemToOrder(order2, 0, 1);
addItemToOrder(order2, 2, 1);
printOrder(order2);
```

Here again, we're printing out the order number first, and this is order 2. 
Then we set up a new ArrayList, which represents the second order. 
Right now, we're adding one product, specifying the product index as the second argument,
and the quantity as the third argument, as we did before. 
In this case, I'll use index 3, which we know is a furniture item. 
And we print the order. 
Before I run this, I want to copy that addItemToOrder statement 
and paste it twice, directly below it, so we have 3-line items. 
I'll change the second one to have a zero index and a quantity of 1. 
And for the third item I'll use index 2, which is a piece of furniture in the inventory, 
and again quantity 1. 
And running the code,

```java  
Order 2
 5 qty at $  200,00 each,  Lamp            Tiffany Lamp with Hummingbirds
 1 qty at $ 1350,00 each,  Oil Painting    Impressionistic work by ABF painted in 2010
 1 qty at $  500,00 each,  Desk            Mahogany Desk
Sale Total = $2850,00
```
                
We have two new items, the desk and lamp printed out in the inventory list. 
And you can see Order 2, printed out. 
And on the second order, we have five lamps, an oil painting, 
and a desk. 
So that was the Abstract Class Challenge.
</div>
