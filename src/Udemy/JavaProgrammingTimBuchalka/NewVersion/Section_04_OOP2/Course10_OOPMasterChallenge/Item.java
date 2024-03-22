package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_04_OOP2.Course10_OOPMasterChallenge;

public class Item {

    private String type, name, size = "MEDIUM";
    private double price;
/*
Part-3
        Here, we have type(this will be burger, drink, side, or topping for example), name, and, price, and that price will
    be the base price. Then we've also got size here, and we've said it has the type of String, and for this, we're going
    to set it to "MEDIUM". Now that we have the fields, we can add a constructor without only size attribute:
End-Part-3
*/
    public Item(String type, String name, double price) {
        this.type = type.toUpperCase();
        this.name = name.toUpperCase();
        this.price = price;
    }
/*
Part-4
        And I want to just change the constructor, to make both the type, and name of the Item, upper case. Now I could
    add validation here as well, but for simplicity's sake, I'll leave that out, but you might want to check if the type
    is a valid type for example, or whatever kind of validation makes sense. And also we'll add getters for the name and
    price fields.
End-Part-4
*/

    public String getName() {

        if (type.equals("SİDE") || type.equals("DRİNK")) {
            return size + " " + name;
        }

        return name;
    }

    public double getBasePrice() {
        return price;
    }
/*
Part-5
        And just for more clarity, I want the name of that getter, getPrice(), to actually be getBasePrice(). And let's
    change the code in getName to include the size of the item.

                        if (type.equals("SIDE") || type.equals("DRINK")) {
                            return size + " " + name;
                        }

    In this code, we're saying if the type is a side, or drink(and remember we made the type uppercase in the constructor),
    we'll also print out the size, which we're expecting to be SMALL, MEDIUM, or LARGE. Next, we need a method to get the
    adjusted price of an item. And because we aren't subclassing the Item class, except for burgers, I want to customize
    what happens for the other types in this method:
End-Part-5
*/
    public double getAdjustedPrice() {
        return switch (size) {
            case "SMALL" -> getBasePrice() - 0.5;
            case "LARGE" -> getBasePrice() + 1;
            default -> getBasePrice();
        };
    }
/*
Part-6
        This code adjusts pricing for drinks and sides. We'll make both the sides and drinks, have the same price adjustments
    for simplicity. Ok, so if something is small, we'll subtract half a dollar from it, but if it's large, we'll add a dollar.
    And one other thing we want is a setter, for the size attribute. We want Bob's employees, to be able to adjust the
    size of a drink on a meal:
End-Part-6
*/
    public void setSize(String size) {
        this.size = size;
    }
/*
Part-7
        And finally, let's create a method to print the item name, and the item's price, meaning the adjusted price. I want
    everything to be printed out, all the items, the burger, its toppings, and the subtotals and totals, to be printed
    out in a uniform way. First, I'm going to create a static method on this class, and use a format string, so that any
    code can call this method, passing a label and price, and it'll get formatted the same.
End-Part-7
*/
    public static void printItem(String name, double price) {
        System.out.printf("%20s:%6.2f%n", name, price);
    }
/*
Part-8
        Ok, what's this code going to do? We're using the System.out.printf statement. And we're formatting a string literal
    that may look a bit different from any you've seen before. This string has 2 specifiers, but here we're including some
    optional features with the specifiers. The %s is for a String, meaning it will get replaced by a string variable, but
    we can specify how wide we want the string to be, by putting a number between the percent and the s. Here, we're reserving
    20 characters for the first argument, and we'll just pass the name to that. And the second specifier is "%f", for a
    floating point number, but again, we've inserted some options between the percent and the f. In this case, we're saying
    we want 6 characters up to the decimal point, which includes spaces and numbers, and we want a precision of 2, meaning
    we want 2 decimal places. So this method can be called from anywhere. But next we'll create an instance method, with
    the same name, but no args on the Item class and this will just call the static method.
End-Part-8
*/
    public void printItem() {
        printItem(getName(), getAdjustedPrice());
    }
/*
Part-9
        And we're going to pass the results of the getName method, and the getAdjustedPrice method, as the arguments to
    printed. These are the methods that will get overridden for any subclasses. And we can test this in the main method:
End-Part-9
*/
}
