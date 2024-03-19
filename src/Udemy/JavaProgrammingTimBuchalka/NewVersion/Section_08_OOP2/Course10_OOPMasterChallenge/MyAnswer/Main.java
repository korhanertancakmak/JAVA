package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_OOP2.Course10_OOPMasterChallenge.MyAnswer;

public class Main {
    public static void main(String[] args) {

/*
Part-1
                                       Object-Oriented Programming Master Challenge
                                                Bill's Burger Challenge

        In this challenge, Bill runs a fast food hamburger restaurant, and sells hamburger meals. His meal orders are composed
    of 3 items, the hamburger, the drink, and the side item. Your application lets Bill select the type of burgers, and
    some of the additional items, or extras, that can be added to the burgers, as well as the actual pricing.

        You need a meal order:
  - This should be composed of exactly 1 burger, 1 drink, and 1 side item.
  - The most common meal order should be created without any args, like a regular burger, a small coke, and fries, for example.
  - You should be able to create other meal orders, by specifying different burgers, drinks, and side items.

        You need a drink, and side item:
  - The drink should have at least a type, size and price, and the price of the drink should change for each size.
  - The side item needs at least a type and price.

        You need burgers:
  - Every hamburger should have a burger type, a base price, and up to a maximum of 3 extra toppings.
  - The constructor should include only the burger type and price.
  - Extra Toppings on a burger need to be added somehow, and priced according to their type.

        The deluxe burger bonus:
  - Create a deluxe burger meal, with a deluxe burger, that has a set price, so that any additional toppings do not change
    the price.
  - The deluxe burger should have room for an additional 2 toppings.

        Your main method should have code to do the following:
  - Create a default meal, that uses the no args constructor.
  - Create a meal with a burger, and the drink and side item of your choice, with up to 3 extra toppings.
  - Create a meal with a deluxe burger, where all items, drink, side item and toppings up to 5 extra toppings, are included
    in the burger price.

        For each meal order, you'll want to perform these functions:
  - Add some additional toppings to the burger.
  - Change the size of the drink.
  - Print the itemized list. This should include the price of the burger, any extra toppings, the drink price based on
    size, and the side item price.
  - Print the total due amount for the meal.

End-Part-1
*/

//        Meal meal = new Meal();
//        meal.getMeal();

        Burger burger = new Burger("small", 0);
        Drink drink = new Drink("coke", "small");
        SideItem side = new SideItem("fries");
        System.out.println("Total price = " + (burger.getBurgerPrice() + drink.getDrinkPrice() + side.getSideItemPrice()));
    }
}
