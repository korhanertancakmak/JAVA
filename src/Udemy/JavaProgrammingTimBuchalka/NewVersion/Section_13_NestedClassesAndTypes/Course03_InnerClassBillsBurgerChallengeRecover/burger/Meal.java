package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_NestedClassesAndTypes.Course03_InnerClassBillsBurgerChallengeRecover.burger;

//Part-1
/*
        I want to switch gears a little, and look at a different example. In this example, I want to use an inner class
    to reconstruct a part of Bill's Burger Challenge. The Challenge was at OOP-Part2 Section. In that challenge, we created
    a meal, which consisted of a burger, a drink, and a side. I'll do that, but I'll skip the burger toppings part of that
    challenge, as well as the deluxe meal part. I just want to give you a taste of different way to implement that code,
    using an inner class.

        First, I'll create a new class, called burger.Meal.
*/
//End-Part-1

public class Meal {

//Part-2
/*
        And I want to start with a private inner class called Item, and that'll have 3 fields, name, type and price. All
    our meal items, burger, drink and side will be an item, and item is called an inner class because it's not static,
    and it's declared as a class member. Now, I want a constructor for the inner class with all 3 fields. And I want to
    copy that constructor, pasting it directly above. I'll remove the price parameter and the code that's in there, and
    make a call to the other constructor, passing "name, type, 0", which it defaults to anyway. And now I want to override
    the toString method for this inner class. And I'll replace the template code with my own formatting, and include our
    3 fields.

                return "%10s%15s $%.2f".formatted(type, name, price);

    That'll print out the type, name and price spaced and formatted.
*/
//End-Part-2

    private class Item {

        private String name;
        private String type;
        private double price;

        public Item(String name, String type) {
            //this(name, type, 0);
            //this(name, type, type.equals("burger") ? base : 0);
            //this(name, type, type.equals("burger") ? price : 0);
            this(name, type, type.equals("burger") ? Meal.this.price: 0);
        }

        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        @Override
        public String toString() {
            //return "%10s%15s $%.2f".formatted(type, name, price);
            return "%10s%15s $%.2f".formatted(type, name, getPrice(price, conversionRate));
        }

//Part-8
/*
        I've mentioned several times that JDK 16 gave us the ability to have static members on all nested classes. Let's
    see what it means. I want to add a static method on Item, that will convert my prices, in Australian Dollars to US
    Dollars, since Bills Burger chains are opening up in the US. Now this method will take any price, and any conversion
    rate and return a converted price. It can be static, since the variables needed are all passed as arguments. I'll call
    this getPrice. And then let's add conversion rate as a field on the Meal class. Now, I want to add that as an argument
    to the no args constructor and save it. And again I need a no args constructor. I'll generate that by placing it above.
    And I'll chain a call to the other constructor, passing 1 as the conversion rate which means there is no conversion.
    Now, I want to call this method, first on Item's toString method.

                    return "%10s%15s $%.2f".formatted(type, name, price);
                                            to
                    return "%10s%15s $%.2f".formatted(type, name, getPrice(price, conversionRate));

    You know you can call a static method without using the class name from within the class itself, which I do here. Next,
    I want to add a method on meal, called getTotal.
*/
//End-Part-8

        private static double getPrice(double price, double rate) {
            return price * rate;
        }
    }

//Part-3
/*
        Now, for the Meal, I'll set up some attributes. You can see that burger, drink, and side are all using Item as
    their variable type, Item is the inner class. And I've set up a base price of 5$. Now, one bonus for nested classes
    is both the inner class and the outer class have direct access to the other's instance members. I'll go back to the
    inner class constructor, and I'm going to set up a condition, that if the item is a burger, it's price will be the
    base price.

                this(name, type, 0);
                            to
                this(name, type, type.equals("burger") ? base : 0);

    What I want you to notice with this code is that I'm assigning the base price, which is a private field on the enclosing
    Meal class, directly to an attribute on an instance of Item, if it's a burger. This is an example, showing that the
    inner class has direct access to the outer class's attributes, even private ones.
*/
//End-Part-3

    //private double base = 5.0;
    private double price = 5.0;

//Part-7
/*
        Instead of calling the base price, base, let's say I want to call it price. And now, I've got an error in my 2
    argument constructor, because base doesn't exist any more. What happens if I change that from base, to price?

                    this(name, type, type.equals("burger") ? base : 0);
                                            to
                    this(name, type, type.equals("burger") ? price : 0);

    Notice, as I'm typing, I get a popup that is showing me "Meal.this.price". I'm going to ignore that for the moment.
    But notice, that using price here gives me an error, "Cannot reference Item.price before super type constructor has
    been called". What's going on here? Well, maybe you've realized that I've got a price field on Meal, and I've also
    got a price field on Item. If I reference price without any qualifier in the code, it refers to the price applicable
    to the current scope. In this example, I'm in the Item class, so price here, in the constructor refers to Item's price,
    and not Meal's price. How do I tell it to use Meal's price and not Item's price? Well, IntelliJ gave us that hint
    when we were replacing base with price. We specify Meal.this.price:

                    this(name, type, type.equals("burger") ? price : 0);
                                            to
                    this(name, type, type.equals("burger") ? Meal.this.price: 0);

    Now, this code runs and compiles as before.
*/
//End-Part-7

    private Item burger;
    private Item drink;
    private Item side;

    private double conversionRate;

//Part-4
/*
        Finally, I'll set up a no args constructor for the enclosing Meal class. This constructor constructs a regular
    meal with a regular burger, a coke and a side of fries. I've added a statement to print out the drink's name, to
    demonstrate how the enclosing Meal class has direct access to the Item's attributes without getters or setters, even
    though those attributes are private. Both classes benefit from the nested relationship by being able to access all
    of the other's members, private or otherwise. Next, I want to toString method for the Meal,
*/
//End-Part-4


    public Meal() {
        this(1);
    }

    public Meal(double conversionRate) {
        this.conversionRate = conversionRate;
        burger = new Item("regular", "burger");
        drink = new Item("coke", "drink", 1.5);
        System.out.println(drink.name);
        side = new Item("fries", "side", 2.0);
    }

//Part-5
/*
        I'll insert an overridden version after my constructor. And I'll return a formatted string again. So that's a meal,
    using an inner class item to represent everything in a meal. Let me test this out. Instead of using the main class,
    I'll create another class in the same package, called Store.
*/
//End-Part-5

    @Override
    public String toString() {
        //return "%s%n%s%n%s%n".formatted(burger, drink, side);
        return "%s%n%s%n%s%n%26s$%.2f".formatted(burger, drink, side, "Total Due: ", getTotal());
    }

//Part-9
/*
        Now, in this method, notice I'm calling the static method getPrice, but this time I use Item's class name, because
    this code is outside of the Item class. I'll next add to the toString method, 2 more format specifiers, first separated
    by a new line from the other items, and pass the text, Total Due, and then the total price.

                    return "%s%n%s%n%s%n".formatted(burger, drink, side);
                                            to
                    return "%s%n%s%n%s%n26s$%.2f".formatted(burger, drink, side, "Total Due: ", getTotal());

    Getting back to the main method on Store class, I can run the code as before.
*/
//End-Part-9

    public double getTotal() {

        double total = burger.price + drink.price + side.price;
        return Item.getPrice(total, conversionRate);
    }
}
