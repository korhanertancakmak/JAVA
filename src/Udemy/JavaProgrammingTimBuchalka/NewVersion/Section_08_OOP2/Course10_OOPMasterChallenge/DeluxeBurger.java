package CourseCodes.NewSections.Section_08_OOP2.Course10_OOPMasterChallenge;

public class DeluxeBurger extends Burger{
/*
Part-32
        And we're adding extends Burger to this class. And in addition to the 3 extra attributes, we want 2 more for this
    burger:
End-Part-32
*/
    Item deluxe1;
    Item deluxe2;

/*
Part-32
        And we need a constructor, so we'll create 1 below these fields, but we don't want to include the deluxe attributes,
    so just pick the Select None button. And now we have the constructor which just calls the burger constructor, which
    is all we need.
End-Part-32
*/

    public DeluxeBurger(String name, double price) {
        super(name, price);
    }
/*
Part-33
        Next, I want to use the IntelliJ override generation feature for 2 methods on burger, these are addToppings, and
    printItemizedList. We'll do the usual "Control+O", and select just those 2 methods, addToppings and printItemizedList.
    But really, I don't want to override addToppings, I want to overload it. I'm going to remove the override annotation,
    and I'm going to add 2 extra parameters, for the extra 2 toppings, which are extra4 and extra5:
End-Part-33
*/

    public void addToppings(String extra1, String extra2, String extra3, String extra4, String extra5) {
        super.addToppings(extra1, extra2, extra3);
        deluxe1 = new Item("TOPPİNG", extra4, 0);
        deluxe2 = new Item("TOPPİNG", extra5, 0);
    }

    @Override
    public void printItemizedList() {
        super.printItemizedList();
        if (deluxe1 != null) {
            deluxe1.printItem();
        }
        if (deluxe2 != null) {
            deluxe2.printItem();
        }
    }

/*
Part-34
        That sets up the last 2 toppings on the deluxe burger, both with a price of 0. Now let's include these in the
    overridden printItemizedList. We want to print out these 2 new items, after the original three on the Burger class.
    Finally, we need a way to override the getExtraPrice method on Burger, because all toppings on a deluxe burger are
    included, or are zero priced. Again, before the closing bracket of the class, we'll override getExtraPrice. And then
    we'll just return 0 for the deluxe burger.
End-Part-34
*/

    @Override
    public double getExtraPrice(String toppingName) {
        return 0;
    }
/*
Part-35
        And now we're done with the DeluxeBurger. Now, we want to create a meal order with the deluxe burger. And where
    we want to support this new burger, is in the constructor that has parameters, on the MealOrder class. Let's go there,
    to the MealOrder: and we'll add a conditional check, if the burger type is deluxe. Let's do that with equalsIgnoreCase, so it doesn't
    matter what case the user enters.
End-Part-35
*/
}
