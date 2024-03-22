package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_04_OOP2.Course10_OOPMasterChallenge;

public class Burger extends Item{

    private Item extra1;
    private Item extra2;
    private Item extra3;

/*
Part-12
        This class can inherit name, and price. And we'll add the 3 additional fields we have for the Burger, which we'll
    call extra1, extra2, and extra3. Now, in the next section, we'll learn more about things called containers, which are
    a way to group the same kind of items together, so instead of having 3 attributes like this, we could have a container
    of extra toppings, but first let's see how to do it, without those structures. Ok, so those are the burger attributes.
    And again, this doesn't compile, because we need a constructor. Let's generate one:
End-Part-12
*/
    public Burger(String name, double price) {
        super("Burger", name, price);
    }
/*
Part-13
        We don't want to include the extras in the constructor, so I'll use the select none button, which will still add
    code to initialise the super class. Instead of include extras in the constructor, we'll include a method to add toppings,
    because if you think about it, these are probably going to be something specific for each customer. Firstly though,
    I want to change the constructor to remove "type" as a parameter, and instead pass the type, Burger, to the super call.
    Now let's override a few methods from the Item class:
End-Part-13
*/
    @Override
    public String getName() {
        return super.getName() + " BURGER";
    }

    @Override
    public double getAdjustedPrice() {
        return getBasePrice() +
                ((extra1 == null) ? 0 : extra1.getAdjustedPrice()) +
                ((extra2 == null) ? 0 : extra2.getAdjustedPrice()) +
                ((extra3 == null) ? 0 : extra3.getAdjustedPrice());
    }
/*
Part-14
        And we'll do "Control+O", and select getName, and getAdjustedPrice methods. For Burger, let's change the getName
    method, and just include the word Burger in there, because the type for the burger, will be something like "regular"
    or "deluxe", for example. And then we'll also change the getAdjustedPrice method. We need the price to include the
    toppings, and we'll start by adjusting it for one additional topping first.

        And we're using ternary operator here, in each case, to determine if the extra topping is null, if it is, we just
    return 0, but otherwise, we'll add the getAdjustedPrice for each extra Item. And this gets added to the Burger base
    price, so each extra topping will get added, if it has a price. We don't really have a way to test this right now,
    because we don't have any way to add toppings. Let's add a method, to add toppings next. Now, we're going to let a
    String be passed to this method, and the code will create the right kind of item. But I'm also going to include a
    method to determine the price, based on the name of topping that's passed, so this method will be called getExtraPrice:
End-Part-14
*/
    public double getExtraPrice(String toppingName) {
        return switch (toppingName.toUpperCase()) {
            case "AVOCADO", "CHEESE" -> 1.0;
            case "BACON", "HAM", "SALAM" -> 1.5;
            default -> 0.0;
        };
    }
/*
Part-15
        Here, we're making Avocado and Cheese be an extra 1$, and these 3 meat types be an extra 1.50$. Everything else
    will have no additional price. Next, we'll write the addToppings method, and we'll pass the 3 individual String variables
    as individual parameters, and we'll set up the extra fields, by creating a new Item for each String passed.
End-Part-15
*/
    public void addToppings(String extra1, String extra2, String extra3) {
        this.extra1 = new Item("TOPPİNG", extra1,
                getExtraPrice(extra1));
        this.extra2 = new Item("TOPPİNG", extra2,
                getExtraPrice(extra2));
        this.extra3 = new Item("TOPPİNG", extra3,
                getExtraPrice(extra3));
    }
/*
Part-16
        Ok, let's see if we can create a Burger now, add some toppings, and print its price. Going to the main method:
End-Part-16
*/
        public void printItemizedList() {

            printItem("BASE BURGER", getBasePrice());
            if (extra1 != null) {
                extra1.printItem();
            }
            if (extra2 != null) {
                extra2.printItem();
            }
            if (extra3 != null) {
                extra3.printItem();
            }
        }
/*
Part-18
        Here, we first print out the base price of the burger. And then if extra1 isn't null, we'll print that out, and
    remember, extra1 is just an Item, so we can call printItem on that. Now we'll copy and paste that, and change extra1
    to extra2, in both those cases. And we'll do the same for extra3. We could do this with an else statement, but there
    could be a chance, if our code wasn't perfectly encapsulated, that extra2 might get populated and not extra3. So we'll
    just do this, to stay on the safe side. Let's add this to the burger printItem method now, so that any-time we print
    a burger, we get an itemized list. After that method, we'll do "control+O", and then select the printItem method:
End-Part-18
*/
    @Override
    public void printItem() {
        printItemizedList();
        System.out.println("-".repeat(30));
        super.printItem();
    }
/*
Part-19
        And now, we'll just insert the call to printItemizedList method first, so the items are printed first. Here, we're
    also just including "-", using the String repeat method, we've used before, which prints out 30 dashes here. And now,
    we can just re-run our code from the main method:
End-Part-19
*/
}

