# [Bill's Burger(OOP Master) Challenge]()

![image12]()

Here is the diagram of my design. 
This diagram doesn't include the DeluxeBurger class.
We'll look at that a bit later. 
The MealOrder class uses composition in this design. 
It's composed of a burger, as well as a drink and side, which will just be Items.
These are all really Items, although I specialized in the Burger class. 
I've used inheritance for the Item and Burger relationship, which means burger is an Item. 
Every Item has a name, type, price or base price, and a size. 
Item has the method getBasePrice, which is really just a getter method for the price, 
but the name is more descriptive.
Item also has getAdjustedPrice, and the printItem method. 
These methods will exhibit different behavior, based on the runtime type, and we know that's polymorphism.

For the burger, the toppings or extras are individual attributes, and also have the type Item. 
We're going to use MealOrder class to hide some of the implementation details from the calling code. 
This means we're going to use encapsulation techniques on *MealOrder* and *Item*. 
The MealOrder class has additional methods that will delegate work to some of its parts,
meaning the calling code won't interact directly with any of the parts. 
Now, it's probably very unlikely; any two developers would come up with the same design. 
This is just one way to do this, one way of many.

We're going to start with the base class for most of our classes, the Item, 
so let's create that class and its attributes:

```java  
public class Item {

    private String type, name, size = "MEDIUM";
    private double price;
}
```

Here, we have *type* (this will be a burger, drink, side, or topping, for example), *name*, and, *price*, 
and *price* will be the base price. 
Then we've also got size here, and we've said it has the type of String, and for this,
we're going to set it to "MEDIUM". 
Now that we have the fields, we can add a constructor without only a size attribute:

```java  
public Item(String type, String name, double price) {
    this.type = type.toUpperCase();
    this.name = name.toUpperCase();
    this.price = price;
}
```

And I want to just change the constructor, to make both the type and name of the Item, upper case. 
Now I could add validation here as well, but for simplicity's sake, I'll leave that out, 
but you might want to check if the type is a valid type, for example, or 
whatever kind of validation makes sense. 
And also we'll add getters for the name and price fields.
I want the name of that getter, getPrice(), to actually be getBasePrice(). 
And let's change the code in getName to include the size of the item.

```java  
public String getName() {
    if (type.equals("SİDE") || type.equals("DRİNK")) {
        return size + " " + name;
    }
    return name;
}

public double getBasePrice() {
    return price;
}

public double getAdjustedPrice() {
    return switch (size) {
        case "SMALL" -> getBasePrice() - 0.5;
        case "LARGE" -> getBasePrice() + 1;
        default -> getBasePrice();
    };
}

public void setSize(String size) {
    this.size = size;
}
```

In this code, we're saying if the type is a side, or drink 
(and remember we made the type uppercase in the constructor), 
we'll also print out the size, which we're expecting to be SMALL, MEDIUM, or LARGE. 
Next, we need a method to get the adjusted price of an item. 
And because we aren't subclassing the Item class, except for burgers, 
I want to customize what happens for the other types in this *getAdjustedPrice* method.

This *getAdjustedPrice* code adjusts pricing for drinks and sides. 
We'll make both the sides and drinks, have the same price adjustments for simplicity. 
Ok, so if something is small, we'll subtract half a dollar from it, but if it's large, we'll add a dollar.
And one other thing we want is a setter, for the size attribute. 
We want Bob's employees, to be able to adjust the size of a drink on a meal with *setSize* method.

And finally, let's create a method to print the item name, 
and the item's price, meaning the adjusted price. 
I want everything to be printed out, all the items, the burger, its toppings, 
and the subtotals and totals, to be printed out in a uniform way. 
First, I'm going to create a static method on this class, and use a format string, 
so that any code can call this method, passing a label and price, 
and it'll get formatted the same.

```java  
public static void printItem(String name, double price) {
    System.out.printf("%20s:%6.2f%n", name, price);
}
```

Ok, what's this code going to do? 
We're using the System.out.printf statement. 
And we're formatting a string literal that may look a bit different from any you've seen before. 
This string has two specifiers, but here we're including some optional features with the specifiers. 
The %s is for a String, meaning it will get replaced by a string variable, 
but we can specify how wide we want the string to be, by putting a number between the percent and the s. 
Here, we're reserving 20 characters for the first argument, and we'll just pass the name to that. 
And the second specifier is "%f", for a floating point number, but again, 
we've inserted some options between the percent and the f. 
In this case, we're saying we want 6 characters up to the decimal point, 
which includes spaces and numbers, and we want a precision of 2, meaning we want 2 decimal places. 
So this method can be called from anywhere. 
But next we'll create an instance method, with the same name, 
but no args on the Item class, and this will just call the static method.

```java  
public void printItem() {
    printItem(getName(), getAdjustedPrice());
}
```

And we're going to pass the results of the getName method, 
and the getAdjustedPrice method, as the arguments to get printed. 
These are the methods that will get overridden for any subclasses. 
And we can test this in the main method:

```java  
Item coke = new Item("drink", "coke", 1.50);
coke.printItem();
coke.setSize("Large");
coke.printItem();
```

And this creates a drink, that's a coke, with a base price of $1.50. 
This is the price of the medium drink, the way we have it coded. 
We print that, but next we set the size of the drink to Large, and then we print that. 
If we run that:

```java  
MEDIUM COKE:  1,50
 Large COKE:  2,50
```

We see a medium coke printed for $1.50, and a large coke for $2.50. 
You can see those get printed out, aligned nicely, because of the format string we used. 
And we can see the price adjusted accordingly. 
And let's test out a topping, say an Avocado topping:

```java  
Item avocado = new Item("Topping", "avocado", 1.50);
avocado.printItem();
```

And running that:

```java  
MEDIUM COKE:  1,50
 Large COKE:  2,50
    AVOCADO:  1,5O
```

We can see the third line item, avocado, and that's lined up like the other items. 
Ok, that's the Item class. 
Now, we want to build out the Burger class, which we are saying extends the Item class.

```java  
public class Burger extends Item {

    private Item extra1;
    private Item extra2;
    private Item extra3;
}
```

This class can inherit name and price. 
And we'll add the three additional fields we have for the Burger, 
which we'll call extra1, extra2, and extra3. 
Now, in the next section, we'll learn more about things called containers, 
which are a way to group the same kind of items together, 
so instead of having 3 attributes like this, we could have a container of extra toppings, 
but first let's see how to do it, without those structures. 
Ok, so those are the burger attributes. 
And again, this doesn't compile because we need a constructor. Let's generate one:

```java  
public Burger(String name, double price) {
    super("Burger", name, price);
}
```

We don't want to include the extras in the constructor, so I'll use the select none button, 
which will still add code to initialize the superclass. 
Instead of including extras in the constructor, we'll include a method to add toppings,
because if you think about it, these are probably going to be something specific for each customer. 
Firstly, I want to change the constructor to remove *type* as a parameter, 
and instead pass the type, Burger, to the super call.
Now let's override a few methods from the Item class:

```java  
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
```

And we'll do "Control+O," and select getName, and getAdjustedPrice methods. 
For Burger, let's change the getName method, and include the word Burger in there, 
because the type for the burger will be something like *regular* or *deluxe*, for example. 
And then we'll also change the getAdjustedPrice method. We need the price to include the
toppings, and we'll start by adjusting it for one additional topping first.

And we're using ternary operator here, in each case, to determine if the extra topping is null, 
if it is, we just return 0, but otherwise, we'll add the getAdjustedPrice for each extra Item. 
And this gets added to the Burger base price, so each extra topping will get added 
if it has a price. We don't really have a way to test this right now, 
because we don't have any way to add toppings. 
Let's add a method to add toppings next. 
Now, we're going to let a String be passed to this method, 
and the code will create the right kind of item. 
But I'm also going to include a method to determine the price, 
based on the name of topping that's passed, so this method will be called getExtraPrice:

```java  
public double getExtraPrice(String toppingName) {
    return switch (toppingName.toUpperCase()) {
        case "AVOCADO", "CHEESE" -> 1.0;
        case "BACON", "HAM", "SALAM" -> 1.5;
        default -> 0.0;
    };
}
```

Here, we're making Avocado and Cheese be an extra 1$, and these 3 meat types be an extra 1.50$. 
Everything else will have no additional price. 
Next, we'll write the *addToppings* method, and we'll pass the three individual String variables
as individual parameters, and we'll set up the extra fields by creating a new Item for each String passed.

```java  
public void addToppings(String extra1, String extra2, String extra3) {
    this.extra1 = new Item("TOPPİNG", extra1,
            getExtraPrice(extra1));
    this.extra2 = new Item("TOPPİNG", extra2,
            getExtraPrice(extra2));
    this.extra3 = new Item("TOPPİNG", extra3,
            getExtraPrice(extra3));
}
```

Ok, let's see if we can create a Burger now, add some toppings, and print its price. 
Going to the main method:

```java  
Burger burger = new Burger("regular", 4.00);
burger.addToppings("BACON", "CHEESE", "MAYO");
burger.printItem();
```

First, we'll comment out the other lines in the main method, so we can focus on the burger output. 
And if we run this:

```java  
REGULAR BURGER:  6,50
```

We can see the output, regular burger, 6.50$. 
We started out with a regular burger, and we priced that at 4$. 
But then we added three toppings, BACON, which was an item that we said had the price of 1.5$, 
and cheese was 1$. 
Our toppings were 2.50$ and add that to the base price, we get 6.50$. 
Ok, so we're half-way there. 
Let's add a method to Burger that itemizes the extra toppings:

```java  
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
```

Here, we first print out the base price of the burger. 
And then if extra1 isn't null, we'll print that out, and remember, 
extra1 is just an Item, so we can call printItem on that. 
Now we'll copy and paste that, and change extra1 to extra2, in both those cases. 
And we'll do the same for extra3. 
We could do this with an else statement, but there could be a chance 
if our code wasn't perfectly encapsulated, that extra2 might get populated and not extra3. 
So we'll just do this, to stay on the safe side. 
Let's add this to the burger printItem method now, so that any time we print a burger, 
we get an itemized list. 
After that method, we'll do "control+O," and then select the printItem method:

```java  
@Override
public void printItem() {
    printItemizedList();
    System.out.println("-".repeat(30));
    super.printItem();
}
```

And now, we'll just insert the call to printItemizedList method first, 
so the items are printed first. 
Here, we're also just including "-", using the String repeat method, 
we've used before, which prints out 30 dashes here. 
And now, we can just re-run our code from the main method:

```java  
       BASE BURGER:  4,00
             BACON:  1,50
            CHEESE:  1,00
              MAYO:  0,00
------------------------------
    REGULAR BURGER:  6,50
```

And we get the toppings itemized for the burger. 
So that's looking pretty good. 
It's time to build the meal, using the composition part of this challenge. 
And we've said we're going to do this in the MealOrder Class. 
Let's create that class:

```java  
public class MealOrder {

    private Burger burger;
    private Item drink;
    private Item side;
}
```

Now, I could've made this extend Item, but I chose not to. 
Maybe your design has it extending a base class, and that's a good design too. 
Now, we can add our three fields. 
We'll have burger, which is a Burger type, and drink and side are Item types:

```java  
public class MealOrder {

    private Burger burger;
    private Item drink;
    private Item side;
}
```

And now, let's add the constructor, we'll enter this one in manually, 
because we don't want the calling code to pass objects, just Strings, 
describing what the items in the meal are. 
And we've said we just want a burger type, which will just be regular or deluxe, 
for example, a drink type, so a pepsi or coke maybe, and a side type, 
which might be fries, or potato salad.

```java  
public MealOrder() {
    this("regular", "coke", "fries");
}

public MealOrder(String burgerType, String drinkType, String sideType) {

    this.burger = new Burger(burgerType, 4.0);
    this.drink = new Item("drink", drinkType, 1.00);
    this.side = new Item("side", sideType, 1.5);
}
```

So what we're doing here is constructing the meal, the parts, within this constructor. 
We create a burger instance, and a drink and side, both *Item* instances. 
Now, you could've created your constructor to accept the different parts, 
meaning your parameters could've been a Burger, or Item. 
But I'm doing this, so the calling code doesn't have to know about anything except the MealOrder itself. 
I'm encapsulating the details of the Meal, inside this class, 
and they can configure it with a few simple String literals. 
And let's create another constructor that has no arguments. 
I'll just write it above of the first constructor.

With no args constructor, the calling code can quickly set up a default meal with a regular burger, 
coke and fries. 
Before we try that out, let's write a method that gets the total price of the full meal:

```java  
public double getTotalPrice() {

    return side.getAdjustedPrice() + drink.getAdjustedPrice() + burger.getAdjustedPrice();
}
```

So here, we're just adding the adjusted prices of the side item, 
the drink and the burger, and returning that. 
But we don't have a way to print anything, so let's add a printItemizedList method to MealOrder.

```java  
public void printItemizedList() {
    burger.printItem();
    drink.printItem();
    side.printItem();
    System.out.println("-".repeat(30));
    Item.printItem("Total Price", getTotalPrice());
}
```

And because everything's really an item, we can call the printItem method on all the parts of the meal. 
And we can also use that static method on item to include a total price, 
after we print another 30 dashes, to separate the total from the itemized list. 
So we're ready to create a meal now with this class. 
Going back to the Main class:

```java  
MealOrder regularMeal = new MealOrder();
regularMeal.addBurgerToppings("BACON", "CHEESE", "MAYO");
regularMeal.setDrinkSize("LARGE");
regularMeal.printItemizedList();
```

Let's comment out the last three statements. 
Then we'll create our first meal, and all we have to do is, 
new MealOrder, without any args. 
And running that:

```java  
                 BASE BURGER:  4,00
        ------------------------------
              REGULAR BURGER:  4,00
                 MEDIUM COKE:  1,00
                MEDIUM FRİES:  1,50
        ------------------------------
                 Total Price:  6,50
```

We can get output that looks more like a bill, so we see the Regular Burger there, 
that's the burger name, and that's 4$. And we itemized the burger above that, 
but right now, this burger has no toppings, so we just see the base prices.
And the medium coke is 1$, and medium fries are 1.50$. 
But we haven't provided ways for the calling code to customize the MealOrder. 
We want to let them add Toppings, and change the drink size. 

Let's write a method on MealOrder, that lets us add up to three toppings. 
And this really is just a pass-through to the addToppings method on burger. 
This will be expanded when we add new burger types.

```java  
public void addBurgerToppings(String extra1, String extra2, String extra3) {
    burger.addToppings(extra1, extra2, extra3);
}
```

And let's add a setDrinkSize on the MealOrder class:

```java  
public void setDrinkSize(String size) {
    drink.setSize(size);
}
```

Ok, so now we should be able to add some toppings and change the drink size, 
so let's do that in the Main class.
What codes we added above:

```java  
regularMeal.addBurgerToppings("BACON", "CHEESE", "MAYO");
regularMeal.setDrinkSize("LARGE");
```

And we run that:

```java  
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
```

And now, you can see a pretty well formatted bill, that includes all the toppings, 
and some have additional costs. 
Here we start with a regular 4$ burger, then add some bacon for 1.40$, and cheese for 1$, 
and mayo was no cost, so the total cost of the burger is shown here, and that's 6.50$. 
Now we can see the additional extra toppings, bacon, cheese, and mayo, 
that added another 2.50$ to the price. 
Ok, so that's pretty good. 
Let's test out a second meal, this time using the constructor with 3 args. 
First, we'll comment out the first meal code.

```java  
MealOrder secondMeal = new MealOrder("turkey", "7-up", "chili");
secondMeal.addBurgerToppings("LETTUCE", "CHEESE", "MAYO");
secondMeal.setDrinkSize("SMALL");
secondMeal.printItemizedList();
```

And running this code:

```java  
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
```

We get a turkey burger, with a small 7-up, and a medium order of chili.

Ok, so we created all the classes for a default meal order, 
and provided methods for adding toppings and customizing a drink size. 
Now, we want to create a burger subclass, the DeluxeBurger, 
which has two additional toppings, so a total of 5.

![image13]()

And one other little thing, this burger when it's part of a meal, has a single set price, 
meaning the drink, the side item and the toppings, have no additional changes. 
Ok, now let's create this new Burger:

```java  
public class DeluxeBurger extends Burger {

    Item deluxe1;
    Item deluxe2;
}
```

And we're adding extends Burger to this class. 
And in addition to the three extra attributes, we want two more for this burger:

```java  
public DeluxeBurger(String name, double price) {
    super(name, price);
}
```

And we need a constructor, so we'll create one of these fields, 
but we don't want to include the deluxe attributes, so pick the Select None button. 
And now we have the constructor which just calls the burger constructor, which
is all we need.

Next, I want to use the IntelliJ override generation feature for two methods on burger, 
these are addToppings, and printItemizedList.
We'll do the usual "Control+O," and select just those two methods, addToppings and printItemizedList.
But really, I don't want to override addToppings, I want to overload it. 
I'm going to remove the override annotation, and I'm going to add two extra parameters, 
for the extra two toppings, which are extra4 and extra5:

```java  
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
```

That sets up the last two toppings on the deluxe burger, both with a price of 0. 
Now let's include these in the overridden printItemizedList. 
We want to print out these two new items, after the original three on the Burger class.
Finally, we need a way to override the getExtraPrice method on Burger, 
because all toppings on a deluxe burger are included, or are zero priced. 
Again, before the closing bracket of the class, we'll override getExtraPrice. 
And then we'll just return 0 for the deluxe burger.

```java  
@Override
public double getExtraPrice(String toppingName) {
    return 0;
}
```

And now we're done with the DeluxeBurger. 
Now, we want to create a meal order with the deluxe burger. 
And where we want to support this new burger, 
is in the constructor that has parameters on the MealOrder class. 
Let's go there, to the MealOrder: and we'll add a conditional check, 
if the burger type is deluxe. 
Let's do that with equalsIgnoreCase, so it doesn't matter what case the user enters.

```java  
public MealOrder(String burgerType, String drinkType, String sideType) {

    if (burgerType.equalsIgnoreCase("deluxe")) {
        this.burger = new DeluxeBurger(burgerType, 8.5);
    } else {
        this.burger = new Burger(burgerType, 4.0);
    }
    this.drink = new Item("drink", drinkType, 1.00);
    this.side = new Item("side", sideType, 1.5);
}
```

And we'll add a conditional check to "MealOrder(burgerType, drinkType, sideType)" constructor 
if the burger type is deluxe. 
Let's do that with equalsIgnoreCase, so it doesn't matter what case the user enters.

Ok, so depending on what type is passed as the first argument, we'll create the right kind of burger. 
If it's deluxe, then we create a DeluxeBurger; otherwise it's the default burger. 
Now, we've also said that if it's a DeluxeBurger meal, 
we're not going to charge for drinks or side items, 
so we need to take care of this in two methods on MealOrder. 
First, the getTotalPrice method needs to exclude the side and the drink 
if the burger is a deluxe burger. 
And you'll remember, we can check the type of burger using the *instanceof* operator

```java  
public double getTotalPrice() {

    if (burger instanceof DeluxeBurger) {
        return burger.getAdjustedPrice();
    }
    return side.getAdjustedPrice() + drink.getAdjustedPrice() + burger.getAdjustedPrice();
}
```

Now, if the burger is just a DeluxeBurger, we won't include the drink or side item. 
And next, we want to change the way the drink or side is printed, in the printItemizedList method. 
We still want them to print, but we want their prices to be excluded, 
so again we'll use the instanceof operator, to change what is printed:

```java  
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
```

If it's a deluxe burger, we still want everything printed. 
However, we want the price to be zero, so instead of calling printItem 
from the drink or side instance, we're calling the static method, 
which the instance method calls, and passing 0 for the price. 
And before we can test, we need one more method on MealOrder, 
and this is to allow a user to define up to five toppings. 
I'm just going to copy the addBurgerToppings method, and paste a copy below this:

```java  
public void addBurgerToppings(String extra1, String extra2, String extra3) {
    burger.addToppings(extra1, extra2, extra3);
}
```

I'm just going to copy the addBurger Toppings method, and paste a copy below it.
I'll add two parameters, extra4 and extra5, and then use the instanceof operator 
again to determine which method should get called, based on the burger type.

In the code right below, notice that I'm using the instanceof operator, 
but this time I have a binding variable, *db*. 
And this lets me call the DeluxeBurger's overloaded version of addToppings, 
using that binding variable, which is typed to the DeluxeBurger. 
So that's pretty neat. 
I didn't have to cast or set up a local variable. 
Ok, so let's test this out in the main method by creating a MealOrder with a deluxe type:

```java  
public void addBurgerToppings(String extra1, String extra2, String extra3, String extra4, String extra5) {
    if (burger instanceof DeluxeBurger db) {
        db.addToppings(extra1, extra2, extra3, extra4, extra5);
    } else {
        burger.addToppings(extra1, extra2, extra3);
    }
}
```

In this case, we want to include at least five toppings, to test our extra fields. 
And we'll set the drink to small, to make sure the price doesn't get adjusted. 
But first, we'll comment out the secondMeal, so we can focus on the deluxeMeal.

```java  
MealOrder deluxeMeal = new MealOrder("deluxe", "7-up", "chili");
deluxeMeal.addBurgerToppings("AVOCADO", "BACON", "LETTUCE", "CHEESE", "MAYO");
deluxeMeal.setDrinkSize("SMALL");
deluxeMeal.printItemizedList();
```

And running that code:

```java  
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
```

You can see, the base burger is 8.50$, we've added avocado, bacon, lettuce, 
cheese, and mayo, and the line item prices for each topping is zero. 
We also have a drink and a medium chili for side items, and both are included 
with no additional costs. 
The total price for the deluxe burger is the same price as the base burger, 
which is what we wanted. 
That looks good. 
Regardless of toppings, or the drink or side, the price did not change, it was 8.50$.
