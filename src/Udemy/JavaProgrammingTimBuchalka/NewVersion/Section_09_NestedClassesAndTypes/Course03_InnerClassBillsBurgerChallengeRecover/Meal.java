package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_09_NestedClassesAndTypes.Course03_InnerClassBillsBurgerChallengeRecover;

public class Meal {

    private class Item {

        private final String name;
        private final String type;
        private final double price;

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
        private static double getPrice(double price, double rate) {
            return price * rate;
        }
    }

    //private double base = 5.0;
    private final double price = 5.0;
    private final Item burger;
    private final Item drink;
    private final Item side;

    private final double conversionRate;

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

    @Override
    public String toString() {
        //return "%s%n%s%n%s%n".formatted(burger, drink, side);
        return "%s%n%s%n%s%n%26s$%.2f".formatted(burger, drink, side, "Total Due: ", getTotal());
    }

    public double getTotal() {

        double total = burger.price + drink.price + side.price;
        return Item.getPrice(total, conversionRate);
    }
}
