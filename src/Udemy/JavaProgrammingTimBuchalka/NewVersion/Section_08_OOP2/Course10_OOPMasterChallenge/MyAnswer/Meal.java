package CourseCodes.NewSections.Section_08_OOP2.Course10_OOPMasterChallenge.MyAnswer;

public class Meal {

    private String burgerType, drinkType, drinkSize, sideItemType;

    private int extraToppings;

    public Meal() {
        this.burgerType = "Regular";
        this.drinkType = "Coke";
        this.drinkSize = "Small";
        this.sideItemType = "fries";
        this.extraToppings = 0;
    }

    public Meal(String burgerType, String drinkType, String drinkSize, String sideItemType, int extraToppings) {
        this.burgerType = burgerType;
        this.drinkType = drinkType;
        this.drinkSize = drinkSize;
        this.sideItemType = sideItemType;
        this.extraToppings = extraToppings;
    }

    public double calculateTotalPrice(String burgerType, int extraToppings, String drinkSize, String sideItemType) {
        Burger burger = new Burger(burgerType, extraToppings);
        Drink drink = new Drink(drinkType, drinkSize);
        SideItem side = new SideItem(sideItemType);

        return burger.getBurgerPrice() + drink.getDrinkPrice() + side.getSideItemPrice();
    }

    public void getMeal() {
        if (burgerType.toUpperCase().charAt(0) != 'D' && extraToppings > 3) {
            System.out.println("Enter extraTopping lower than 3 for " + burgerType + " size");
            return;
        } else if (burgerType.toUpperCase().charAt(0) == 'D' && extraToppings > 5) {
            System.out.println("Enter extraTopping lower than 5 for " + burgerType + " size");
            return;
        }
        System.out.println("Total Price = " + calculateTotalPrice(burgerType,extraToppings, drinkSize, sideItemType));
    }
}

class Burger extends Meal {
    private String burgerType;
    private int extraToppings;
    private double basePrice = 10d;

    public Burger(String burgerType, int extraToppings) {
        this.burgerType = burgerType;
        this.extraToppings = extraToppings;
    }

    public double getBurgerPrice() {
        basePrice = calculateBurgerPrice(burgerType, extraToppings);
        return basePrice;
    }

    private double calculateBurgerPrice(String burgerType, int extraToppings) {
        double extraPrice = extraToppings * 2.5d;
        double rPrice = 10d + extraPrice;
        double mPrice = 15d + extraPrice;
        double lPrice = 20d + extraPrice;
        double dPrice = 25d + extraPrice;
        if (burgerType.toUpperCase().charAt(0) == 'M') {
            System.out.println("Medium burger is ordered with " + extraToppings + " extraToppings");
            return mPrice;
        } else if (burgerType.toUpperCase().charAt(0) == 'L') {
            System.out.println("Large burger is ordered with " + extraToppings + " extraToppings");
            return lPrice;
        } else if (burgerType.toUpperCase().charAt(0) == 'D') {
            System.out.println("Deluxe burger is ordered with " + extraToppings + " extraToppings");
            return dPrice;
        } else {
            System.out.println("Regular burger is ordered with " + extraToppings + " extraToppings");
            return rPrice;
        }
    }
}

class Drink extends Meal {

    private String drinkType, drinkSize;
    private double basePrice;

    public Drink(String drinkType, String drinkSize) {
        this.drinkType = drinkType;
        this.drinkSize = drinkSize;
    }

    public double getDrinkPrice() {
        basePrice = calculateDrinkPrice(drinkType, drinkSize);
        return basePrice;
    }

    private double calculateDrinkPrice(String drinkType, String drinkSize) {
        int multiplier;
        double price;
        if (drinkSize.toUpperCase().charAt(0) == 'S') {
            multiplier = 1;
        } else if (drinkSize.toUpperCase().charAt(0) == 'M') {
            multiplier = 2;
        } else if (drinkSize.toUpperCase().charAt(0) == 'L') {
            multiplier = 3;
        } else {
            multiplier = 4;
        }

        if (drinkType.toUpperCase().charAt(0) == 'C' || drinkType.toUpperCase().charAt(0) == 'F') {
            price = 5d;
        } else if (drinkType.toUpperCase().charAt(0) == 'S') {
            price = 3d;
        } else if (drinkType.toUpperCase().charAt(0) == 'W') {
            price = 1d;
        } else {
            System.out.println("Enter Valid Drink Parameter!");
            price = 0;
        }

        if (drinkSize.toUpperCase().charAt(0) == 'M') {
            System.out.println("Medium " + drinkType + " is ordered");
            return price * multiplier;
        } else if (drinkSize.toUpperCase().charAt(0) == 'L') {
            System.out.println("Large " + drinkType + " is ordered");
            return price * multiplier;
        } else if (drinkSize.toUpperCase().charAt(0) == 'D') {
            System.out.println("Deluxe " + drinkType + " is ordered");
            return price * multiplier;
        } else if (drinkSize.toUpperCase().charAt(0) == 'S') {
            System.out.println("Small " + drinkType + " is ordered");
            return price * multiplier;
        } else {
            System.out.println("Not Valid Calculation has been done!");
            return -1d;
        }
    }
}

class SideItem extends Meal {

    private String sideItemType;

    public SideItem(String sideItemType) {
        this.sideItemType = sideItemType;
    }

    public double getSideItemPrice() {
        return calculateSideItemPrice(sideItemType);
    }

    private double calculateSideItemPrice(String sideItemType) {

        if (sideItemType.toUpperCase().charAt(0) == 'F') {          //Fries
            System.out.println("Side Item " + sideItemType + " is ordered");
            return 5d;
        } else if (sideItemType.toUpperCase().charAt(0) == 'D') {   //Desert
            System.out.println("Side Item " + sideItemType + " is ordered");
            return 7.5d;
        } else {
            System.out.println("Enter Valid Side Item Parameter!");
            return 0d;
        }
    }
}