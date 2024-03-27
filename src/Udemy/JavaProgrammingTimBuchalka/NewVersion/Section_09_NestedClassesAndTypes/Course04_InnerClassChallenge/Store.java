package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_09_NestedClassesAndTypes.Course04_InnerClassChallenge;

public class Store {

    public static void main(String[] args) {

        Meal regularMeal = new Meal();

        regularMeal.addToppings("Ketchup", "Mayo", "Bacon", "Cheddar");
        System.out.println(regularMeal);

        Meal USRegularMeal = new Meal(0.68);
        System.out.println(USRegularMeal);

    }
}
