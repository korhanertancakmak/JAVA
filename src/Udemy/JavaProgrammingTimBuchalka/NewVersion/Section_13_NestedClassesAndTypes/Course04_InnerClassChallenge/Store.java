package CourseCodes.NewSections.Section_13_NestedClassesAndTypes.Course04_InnerClassChallenge;

//Part-1
/*
        In the previous lecture, I reworked part of the Bills Burgers Challenge to use an inner class, which I called Item.
    I want you to start with that code, where we left off, and make the following enhancements to it:

      - Create another inner class, called Burger. This should be a specialized Item, and should also include a list of
        toppings, also Items. Remember Items have a name, type, price, and methods to convert prices.
      - Allow a user to add toppings using the Meal class, which it should then delegate to its burger class.
      - Allow toppings to be added with a method that allows for a variable number of Strings to be entered, representing
        the toppings selected.
      - Allow toppings to be priced differently, some are free, some have an additional cost.
      - Print the toppings out along with the burger information.
*/
//End-Part-1

public class Store {

    public static void main(String[] args) {

        Meal regularMeal = new Meal();

//Part-12
/*
        I'll add Ketchup, Mayo and Bacon as toppings. And running that:

                        coke
                            burger        regular $5,00
                           TOPPING        KETCHUP $0,00
                           TOPPING           MAYO $0,00
                           TOPPING          BACON $1,50
                             drink           coke $1,50
                              side          fries $2,00
                                       Total Due: $10,00
                        coke
                            burger        regular $3,40
                             drink           coke $1,02
                              side          fries $1,36
                                       Total Due: $5,78

    You can see that the toppings are shown in this itemized list of the first burger, and the total price has gone up to
    10$, because Bacon cost an additional 1.5$. Now, I mentioned earlier, I left a bug in this code, and I know exactly
    how to break it. I'll add a Topping that's not part of our Extra list, like Cheddar for example.

                    regularMeal.addToppings("Ketchup", "Mayo", "Bacon");
                                            to
                    regularMeal.addToppings("Ketchup", "Mayo", "Bacon", "Cheddar");

    And now if I run it:

                    Exception in thread "main" java.lang.IllegalArgumentException: No enum constant Meal.Burger.Extra.CHEDDAR

    I've caused my program to crash, and that's definitely not what I want to happen. This exception gets thrown from
    invoking the valueOf method on an enum for a constant name that's not in the enum. We've briefly touched on exception
    handling before this and learned enough to test and catch this exception. But where is a good place to catch and handle
    it? Do we want the code to add all the other toppings successfully, and proceed with just a message, that one item
    couldn't be included? Or do we want the entire add toppings method to fail, so the clerk is forced to ask the customer
    again? I'm picking the first option, so I'll put a try catch block in the method that adds toppings on the burger.
*/
//End-Part-12

        regularMeal.addToppings("Ketchup", "Mayo", "Bacon", "Cheddar");
        System.out.println(regularMeal);

//Part-14
/*
        And running the code again:

                        coke
                        No topping found for Cheddar
                            burger        regular $5,00
                           TOPPING        KETCHUP $0,00
                           TOPPING           MAYO $0,00
                           TOPPING          BACON $1,50
                             drink           coke $1,50
                              side          fries $2,00
                                       Total Due: $10,00
                        coke
                            burger        regular $3,40
                             drink           coke $1,02
                              side          fries $1,36
                                       Total Due: $5,78

    you can see that message printed out, no topping found for cheddar, so the code handled the situation and proceeded,
    created the meal with all the other valid toppings. Ok, so that was the inner class challenge.
*/
//End-Part-14

        Meal USRegularMeal = new Meal(0.68);
        System.out.println(USRegularMeal);

    }
}
