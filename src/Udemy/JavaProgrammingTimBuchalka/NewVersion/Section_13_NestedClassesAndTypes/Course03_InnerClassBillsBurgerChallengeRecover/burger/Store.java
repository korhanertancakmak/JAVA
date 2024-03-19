package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_NestedClassesAndTypes.Course03_InnerClassBillsBurgerChallengeRecover.burger;

public class Store {

    public static void main(String[] args) {

        Meal regularMeal = new Meal();
        System.out.println(regularMeal);

//Part-6
/*
        I'll type the shortcut "psvm" to get a public static void method. Now I'll create a meal, and print it out. Running
    that out:

                coke
                    burger        regular $5,00
                     drink           coke $1,50
                      side          fries $2,00

    I get coke printed out, which just confirms that the enclosing class, Meal in the case, was able to directly access
    Item's private field, name. And I've got an itemized list of my regular meal. That was a very fast demo of how we can
    use an inner class to build our composition inside of a class when it makes sense to do so. This meal class is very
    well encapsulated. The meal and items are tightly coupled in this challenge, because we've said that Bill sells meals
    and not individual items. Before we move on, let's look at a couple more issues. I'll make a change on the Meal Class,
*/
//End-Part-6

        Meal USRegularMeal = new Meal(0.68);
        System.out.println(USRegularMeal);

//Part-10
/*
        Running that out:

                coke
                    burger        regular $5,00
                     drink           coke $1,50
                      side          fries $2,00
                               Total Due: $8,50

    I get the same prices, but I now see the total price, 8.50$. Now, I want to create a meal in US $, and then print it
    out. Just imagine, you could tap some service to give you the current conversion rate for the day, to do this. But
    we'll just hard-code the rate from Australian dollars to US dollars as 0.68. And running the code:

                coke
                    burger        regular $5,00
                     drink           coke $1,50
                      side          fries $2,00
                               Total Due: $8,50
                coke
                    burger        regular $3,40
                     drink           coke $1,02
                      side          fries $1,36
                               Total Due: $5,78

    you can see the regular meal in the converted price. Although I've only shown you an example of a static method, nested
    classes can now include a nested interface, a static enum or a static record, for example, giving your inner classes
    a lot more functionality. In the next lecture, I'm going to challenge you to make this Meal class a bit more robust
    with the use of another inner class.
*/
//End-Part-10
    }
}
