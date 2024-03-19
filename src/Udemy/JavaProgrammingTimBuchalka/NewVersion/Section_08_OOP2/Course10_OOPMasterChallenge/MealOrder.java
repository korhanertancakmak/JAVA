package CourseCodes.NewSections.Section_08_OOP2.Course10_OOPMasterChallenge;

public class MealOrder {
/*
Part-21
        Now, I could've made this extend Item, but I chose not to. Maybe your design has it extending a base class, and
    that's a good design too. Now, we can add our 3 fields. We'll have burger, which is a Burger type, and drink and side
    are Item types:
End-Part-21
*/
    private Burger burger;
    private Item drink;
    private Item side;
/*
Part-22
        And now, let's add the constructor, we'll enter this one in manually, because we don't want the calling code to
    pass objects, just Strings, describing what the items in the meal are. And we've said we just want a burger type, which
    will just be regular or deluxe, for example, a drink type, so a pepsi or coke maybe, and a side type, which might be
    fries, or potato salad.
End-Part-22
*/

    public MealOrder() {
        this("regular", "coke", "fries");
    }

    public MealOrder(String burgerType, String drinkType, String sideType) {

        if (burgerType.equalsIgnoreCase("deluxe")) {
            this.burger = new DeluxeBurger(burgerType, 8.5);
        } else {
            this.burger = new Burger(burgerType, 4.0);
        }
        this.drink = new Item("drink", drinkType, 1.00);
        this.side = new Item("side", sideType, 1.5);
    }
/*
Part-23
        So what we're doing here, is constructing the meal, the parts, within this constructor. We create a burger instance,
    and a drink and side, both Item instances. Now, you could've created your constructor to accept the different parts,
    meaning your parameters could've been a Burger, or Item. But, I'm doing this, so the calling code, doesn't have to know
    about anything except the MealOrder itself. I'm encapsulating the details of the Meal, inside this class, and they can
    configure it with a few simple String literals. And let's create another constructor, that has no arguments. I'll just
    write it above of the first constructor.

        With no args constructor, the calling code can quickly set up a default meal with a regular burger, coke and fries.
    Before we try that out, let's write a method that gets the total price of the full meal:
End-Part-23
*/

/*
Part-35
        And we'll add a conditional check to "MealOrder(burgerType, drinkType, sideType)" constructor, if the burger type
    is deluxe. Let's do that with equalsIgnoreCase, so it doesn't matter what case the user enters:

        if (burgerType.equalsIgnoreCase("deluxe")) {
            this.burger = new DeluxeBurger(burgerType, 8.5);
        } else {
            this.burger = new Burger(burgerType, 4.0);
        }

    Ok, so depending on what type is passed as the first argument, we'll create the right kind of burger. If it's deluxe,
    then we create a DeluxeBurger, otherwise it's the default burger. Now, we've also said that if it's a DeluxeBurger
    meal, we're not going to charge for drinks or side items, so we need to take care of this in 2 methods on MealOrder.
    First, the getTotalPrice method needs to exclude the side and the drink, if the burger is a deluxe burger. And you'll
    remember, we can check the type of burger using the instanceof operator
End-Part-35
*/
    public double getTotalPrice() {

        if (burger instanceof DeluxeBurger) {
            return burger.getAdjustedPrice();
        }
        return side.getAdjustedPrice() + drink.getAdjustedPrice() + burger.getAdjustedPrice();
    }
/*
Part-24
        So here, we're just adding the adjusted prices of the side item, the drink and the burger, and returning that. But
    we don't have a way to print anything, so let's add a printItemizedList method, to MealOrder.
End-Part-24
*/
/*
Part-36
        if (burger instanceof DeluxeBurger) {
            return burger.getAdjustedPrice();
        }

        Now, if the burger is just a DeluxeBurger, we won't include the drink or side item. And next, we want to change
    the way the drink or side is printed, in the printItemizedList method. We still want them to print, but we want their
    prices to be excluded, so again we'll use the instanceof operator, to change what is printed:
End-Part-36
*/

    public void printItemizedList() {
        burger.printItem();
        if (burger instanceof DeluxeBurger) {
            Item.printItem(drink.getName(), 0);
            Item.printItem(side.getName(),0);
        } else {
            drink.printItem();
            side.printItem();
        }
        System.out.println("-".repeat(30));
        Item.printItem("Total Price", getTotalPrice());
    }
/*
Part-25
        And because everything's really an item, we can call the printItem method on all the parts of the meal. And we can
    also can use that static method on item, to include a total price, after we print another 30 dashes, to separate the
    total from the itemized list. So we're ready to create a meal now with this class. Going back to the Main class:
End-Part-25
*/
/*
Part-37
        if (burger instanceof DeluxeBurger) {
            Item.printItem(drink.getName(), 0);
            Item.printItem(side.getName(),0);
        } else {
            drink.printItem();
            side.printItem();
        }

    If it's a deluxe burger, we still want everything printed, but we want the price to be zero, so instead of calling
    printItem from the drink or side instance, we're calling the static method, which the instance method calls, and passing
    0 for the price. And before we can test, we need one more method on MealOrder, and this is to allow a user to define
    up to 5 toppings. I'm just going to copy the addBurger Toppings method, and paste a copy below it.
End-Part-37
*/
        public void addBurgerToppings(String extra1, String extra2, String extra3) {
            burger.addToppings(extra1, extra2, extra3);
        }
/*
Part-27
        And let's add a setDrinkSize on the MealOrder class:
End-Part-27
*/
/*
Part-38
        I'm just going to copy the addBurger Toppings method, and paste a copy below it. I'll add 2 parameters, extra4 and
    extra5, and then use the instanceof operator again to determine which method should get called, based on the burger
    type.

        In the code right below, notice that I'm using the instanceof operator, but this time I have a binding variable,
    "db". And this lets me call the DeluxeBurger's overloaded version of addToppings, using that binding variable, which
    is typed to the DeluxeBurger. So that's pretty neat. I didn't have to cast or set up a local variable. Ok, so let's
    test this out in the main method by creating a MealOrder with a deluxe type:
End-Part-38
*/
    public void addBurgerToppings(String extra1, String extra2, String extra3, String extra4, String extra5) {
        if (burger instanceof DeluxeBurger db) {
            db.addToppings(extra1, extra2, extra3, extra4, extra5);
        } else {
            burger.addToppings(extra1, extra2, extra3);
        }
    }

    public void setDrinkSize(String size) {
        drink.setSize(size);
    }
/*
Part-28
        Ok, so now we should be able to add some toppings and change the drink size, so let's do that in the Main class:
End-Part-28
*/
}
