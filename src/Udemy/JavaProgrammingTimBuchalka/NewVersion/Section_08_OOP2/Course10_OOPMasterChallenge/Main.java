package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_OOP2.Course10_OOPMasterChallenge;

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

/*
Part-2
                                         __________________________
                                         |MealOrder =>            |
                                         |  side: Item            |
                                         |  drink: Item           |
                                         |  burger: Burger        |
                                         |------------------------|
                                         |  addToppings()         |
                                         |  setDrinkSize()        |
                                         |  printItemizedList()   |
                                         |________________________|
                      _______________________________↓(HAS A)_____________________________
                      |                                                                  |
            __________|_____________________                                 ____________|_______________
            |Item =>                       |                                 |Burger =>                 |
            |   name: String               |                                 |  extra1: Item            |
            |   type: String               |             (IS A)              |  extra2: Item            |
            |   price: double              | ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← |  extra3: Item            |
            |   size: String               |                                 |--------------------------|
            |------------------------------|                                 |  addToppings             |
            |   getBasePrice(): double     |                                 |__________________________|
            |   getAdjustedPrice(): double |
            |   printItem()                |
            |______________________________|

        Here the diagram of my design. This diagram doesn't include the DeluxeBurger class. We'll look at that a bit later.
    The MealOrder class uses composition in this design. It's composed of a burger, as well as a drink and side, which will
    just be Items.These are all really Items, although I specialized the Burger class. I've used inheritance for the Item
    and Burger relationship, which means burger is an Item. Every Item has a name, type, price or base price, and a size.
    Item has the method getBasePrice, which is really just a getter method for the price, but the name is more descriptive.
    Item also has getAdjustedPrice, and the printItem method. These methods will exhibit different behavior, based on the
    runtime type, and we know that's polymorphism.

        For the burger, the toppings or extras are individual attributes, and also have the type Item. We're going to use
    MealOrder class, to hide some of the implementation details from the calling code. This means we're going to use
    encapsulation techniques on MealOrder and Item. The MealOrder class has additional methods, that will delegate work
    to some of its parts, meaning the calling code won't interact directly with any of the parts. Now, it's probably very
    unlikely, any 2 developers would come up with the same design. This is just one way to do this, one way of many.

        We're going to start with the base class for most of our classes, the Item, so let's create that class and its
    attributes:
End-Part-2
*/

//        Item coke = new Item("drink", "coke", 1.50);
//        coke.printItem();
//        coke.setSize("Large");
//        coke.printItem();

/*
Part-10
        And this creates a drink, that's a coke, with a base price of $1.50. This is the price of the medium drink, the
    way we have it coded. We print that, but next we set the size of the drink to Large, and then we print that. If we run
    that:

                     MEDIUM COKE:  1,50
                      Large COKE:  2,50

    we see a medium coke printed for $1.50, and a large coke for $2.50. You can see those get printed out, aligned nicely,
    because of the format string we used. And we can see the price adjusted accordingly. And let's test out a topping,
    say an Avocado topping:
End-Part-10
*/

//        Item avocado = new Item("Topping", "avocado", 1.50);
//        avocado.printItem();

/*
Part-11
        And running that:

                     MEDIUM COKE:  1,50
                      Large COKE:  2,50
                         AVOCADO:  1.5O

    we can see the third line item, avocado, and that's lined up like the other items. Ok, that's the Item class. Now, we
    want to build out the Burger class, which we are saying extends the Item class.
End-Part-11
*/

//        Burger burger = new Burger("regular", 4.00);
//        burger.addToppings("BACON", "CHEESE", "MAYO");
//        burger.printItem();

/*
Part-17
        First, we'll comment out the other lines in the main method, so we can focus on the burger output. And if we run
    this:

                  REGULAR BURGER:  6,50

    we can see the output, regular burger, 6.50$. We started out with a regular burger, and we priced that at 4$. But then
    we added 3 toppings, BACON, which was an item that we said had the price of 1.5$, and cheese was 1$. Our toppings were
    2.50$ and add that to the base price, we get 6.50$. Ok, so we're half-way there. Let's add a method to Burger, that
    itemizes the extra toppings:
End-Part-17
*/
/*
Part-20
        Running again the code:

                 BASE BURGER:  4,00
                       BACON:  1,50
                      CHEESE:  1,00
                        MAYO:  0,00
        ------------------------------
              REGULAR BURGER:  6,50

    and we get the toppings itemized for the burger. So that's looking pretty good. It's time to build the meal, using
    the composition part of this challenge. And we've said we're going to do this in the MealOrder Class. Let's create
    that class:
End-Part-20
*/

//        MealOrder regularMeal = new MealOrder();
//        regularMeal.addBurgerToppings("BACON", "CHEESE", "MAYO");
//        regularMeal.setDrinkSize("LARGE");
//        regularMeal.printItemizedList();

/*
Part-26
        Let's comment out the last 3 statement. Then we'll create our first meal, and all we have to do is, new MealOrder,
    without any args. And running that:

                 BASE BURGER:  4,00
        ------------------------------
              REGULAR BURGER:  4,00
                 MEDIUM COKE:  1,00
                MEDIUM FRİES:  1,50
        ------------------------------
                 Total Price:  6,50

    we can get output that looks more like a bill, so we see the Regular Burger there, that's the burger name, and that's
    4$. And we itemized the burger above that, but right now, this burger has no toppings, so we just see the base prices.
    And the medium coke is 1$, and medium fries are 1.50$. But we haven't provided ways for the calling code to customize
    the MealOrder. We want to let them add Toppings, and change the drink size. Let's write a method on MealOrder, that
    lets us add up to 3 toppings. And this really is just a pass-through to the addToppings method on burger. This will
    be expanded when we add new burger types.
End-Part-26
*/

/*
Part-29
        What codes we added above:

                regularMeal.addBurgerToppings("BACON", "CHEESE", "MAYO");
                regularMeal.setDrinkSize("LARGE");

    and we run that:

                 BASE BURGER:  4,00
                       BACON:  1,50
                      CHEESE:  1,00
                        MAYO:  0,00
        ------------------------------
              REGULAR BURGER:  6,50
                  LARGE COKE:  2,00
                MEDIUM FRİES:  1,50
        ------------------------------
                 Total Price: 10,00

    and now, you can see a pretty well formatted bill, that includes all the toppings, and some have additional costs.
    Here we start with a regular 4$ burger, then add some bacon for 1.40$, and cheese for 1$, and mayo was no cost, so
    the total cost of the burger is shown here, and that's 6.50$. Now we can see the additional extra toppings, bacon,
    cheese, and mayo, that added another 2.50$ to the price. Ok, so that's pretty good. Let's test out a second meal,
    this time using the constructor with 3 args. First we'll comment out the first meal code.
End-Part-29
*/

//        MealOrder secondMeal = new MealOrder("turkey", "7-up", "chili");
//        secondMeal.addBurgerToppings("LETTUCE", "CHEESE", "MAYO");
//        secondMeal.setDrinkSize("SMALL");
//        secondMeal.printItemizedList();

/*
Part-30
        And running this code:

                 BASE BURGER:  4,00
                     LETTUCE:  0,00
                      CHEESE:  1,00
                        MAYO:  0,00
        ------------------------------
               TURKEY BURGER:  5,00
                  SMALL 7-UP:  0,50
                MEDIUM CHİLİ:  1,50
        ------------------------------
                 Total Price:  7,00

    we get a turkey burger, with a small 7-up, and a medium order of chili.
End-Part-30
*/
/*
Part-31
        Ok, so we created all the classes for a default meal order, and provided methods for adding toppings and customizing
    a drink size. Now, we want to create a burger subclass, the DeluxeBurger, which has 2 additional toppings, so a total
    of 5.

                                         __________________________
                                         |MealOrder =>            |
                                         |  side: Item            |
                                         |  drink: Item           |
                                         |  burger: Burger        |
                                         |------------------------|
                                         |  addToppings()         |
                                         |  setDrinkSize()        |
                                         |  printItemizedList()   |
                                         |________________________|
                      _______________________________↓(HAS A)_____________________________
                      |                                                                  |
            __________|_____________________                                 ____________|_______________
            |Item =>                       |                                 |Burger =>                 |
            |   name: String               |                                 |  extra1: Item            |
            |   type: String               |             (IS A)              |  extra2: Item            |
            |   price: double              | ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← |  extra3: Item            |
            |   size: String               |                                 |--------------------------|
            |------------------------------|                                 |  addToppings             |
            |   getBasePrice(): double     |                                 |__________________________|
            |   getAdjustedPrice(): double |                                               ↑
            |   printItem()                |                                               ↑(IS A)
            |______________________________|                                 ______________↑_____________
                                                                             |DeluxeBurger =>           |
                                                                             |  deluxe1: Item           |
                                                                             |  deluxe2: Item           |
                                                                             |--------------------------|
                                                                             |  addToppings             |
                                                                             |__________________________|

    And one other little thing, this burger when it's part of a meal, has a single set price, meaning the drink, the
    side item and the toppings, have no additional changes. Ok, now let's create this new Burger:
End-Part-31
*/
/*
Part-39
        In this case, we want to include at least 5 toppings, to test our extra fields. And we'll set the drink to small,
    to make sure the price doesn't get adjusted. But first, we'll comment out the secondMeal, so we can focus on the
    deluxeMeal.
End-Part-39
*/

        MealOrder deluxeMeal = new MealOrder("deluxe", "7-up", "chili");
        deluxeMeal.addBurgerToppings("AVOCADO", "BACON", "LETTUCE", "CHEESE", "MAYO");
        deluxeMeal.setDrinkSize("SMALL");
        deluxeMeal.printItemizedList();

/*
Part-40
        And running that code:

                 BASE BURGER:  8,50
                     AVOCADO:  0,00
                       BACON:  0,00
                     LETTUCE:  0,00
                      CHEESE:  0,00
                        MAYO:  0,00
        ------------------------------
               DELUXE BURGER:  8,50
                  SMALL 7-UP:  0,00
                MEDIUM CHİLİ:  0,00
        ------------------------------
                 Total Price:  8,50

    you can see, the base burger is 8.50$, we've added avocado, bacon, lettuce, cheese, and mayo, and the line item prices
    for each topping is zero. We also have a drink and a medium chili for side items, and both are included with no additional
    costs. The total price for the deluxe burger is the same price as the base burger, which is what we wanted. That looks
    good. Regardless of toppings, or the drink or side, the price did not change, it was 8.50$.
End-Part-40
*/
    }
}
