package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Abstraction_Interface.Course02_Abstract_Challenge;

//Part-4
/*
        And I have to include the abstract modifier. And I have to add 3 attributes: type, price, and description.
*/
//End-Part-4

public abstract class ProductForSale {

    protected String type;
    protected double price;
    protected String description;

//Part-5
/*
        I'm making all the attributes protected, which means the subclasses can access and modify these directly as well.
    We want this, because our overridden methods will need access to some of the attributes. I'll generate the constructor
    with all 3 fields.
*/
//End-Part-5

    public ProductForSale(String type, double price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }

//Part-6
/*
        Let's add the concrete methods. First, getSalesPrice, and that takes an int, for the quantity.
*/
//End-Part-6

    public double getSalesPrice(int qty) {
        return qty * price;
    }

//Part-7
/*
        And there, we just return the qty times the price, to get the total sales price. Next, we need the printPricedItem
    method.
*/
//End-Part-7

    public void printPricedItem(int qty) {
        System.out.printf("%2d qty at $%8.2f each,  %-15s %-35s %n",
                qty, price, type, description);
    }

//Part-8
/*
        Now this format string is a bit complex, so let me talk about it a minute. %2d means an integer will get printed
    with 2 digits, right justified. Then %8.2f means a decimal number, a double or float, will get printed with a precision
    of 2 digits after the period, and a total width of 8. Then there's %-15s, and this prints out a String, the dash means
    left justify that, and the 15 means allow 15 spaces as a minimum. That's our item type. And then we'll left justify
    the description, the last thing we want printed on the line item. Finally, I'll add the abstract method declaration
    on this class.
*/
//End-Part-8

    public abstract void showDetails();

//Part-9
/*
        And that's our abstract class, ProductForSale. It's abstract because we don't really know what kind of products
    we're supporting yet, but we know what operations we want, regardless of the type of product. And we know our Store
    will want to show the details of the product, and the product can customize that as needed. I'm going to set up the
    storefront, and my store is going to sell objects of art, and furniture. I'll create a new class in the same package,
    calling that ArtObject, and it will extend the abstract class, ProductForSale.
*/
//End-Part-9
}
