# [Inner Class Challenge]()

<div align="justify">

Continuing with inner classes, I want to declare a second one, on **Meal**.
This one will be called **Burger**, 
and I'll add that before the closing bracket of the **Meal** class. 
Like item, this inner class is going to be private and non-static.
Any access to this class and its functionality has to go through 
the Meal class in other words. 
This **Burger** Class extends **Item**, so I don't need to include _name_, 
_type_, or _price_ as fields, but I do need a constructor here. 
In that constructor, I'm simply making a call to the super constructor, 
which calls **Item**'s constructor, passing it the name, and burger as the type, 
and 5$ as the base price. 
Notice that it is not a public constructor. 
In this case, I'm not declaring an access modifier, 
meaning I'm making it package private here. 
As long as I don't subclass **Burger**, 
this modifier's access is a restrictive as I can make it. 
Now, I want to change the type of my _burger_ field on **Meal**, 
from **Item** to **Burger**.
I also need to change Meal's constructor, 
because I need to instantiate a **Burger**, not an **Item**.
And since Burger's constructor only will take one argument:

```java  
public class Meal {
    private final double price = 5.0;
    //private Item burger;
    private final Burger burger;
    private final Item drink;
    private final Item side;
    private final double conversionRate;

    public Meal() {
        this(1);
    }

    public Meal(double conversionRate) {
        this.conversionRate = conversionRate;
        //burger = new Item("regular", "burger");
        burger = new Burger("regular");
        drink = new Item("coke", "drink", 1.5);
        System.out.println(drink.name);
        side = new Item("fries", "side", 2.0);
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n%26s$%.2f".formatted(burger, drink, side, "Total Due: ", getTotal());
    }

    public double getTotal() {
        //double total = burger.price + drink.price + side.price;
        double total = burger.getPrice() + drink.price + side.price;
        return Item.getPrice(total, conversionRate);
    }

    private class Item {

        private final String name;
        private final String type;
        private final double price;

        public Item(String name, String type) {
            this(name, type, type.equals("burger") ? Meal.this.price: 0);
        }

        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        @Override
        public String toString() {
            return "%10s%15s $%.2f".formatted(type, name, getPrice(price, conversionRate));
        }

        private static double getPrice(double price, double rate) {
            return price * rate;
        }
    }

    private class Burger extends Item {
        Burger(String name) {
            super(name, "burger", 5.0);
        }

        public double getPrice() {
            return super.price;
        }
    }
}
```

I'll remove that second argument, the type burger,
because that's hardcoded in Burger's constructor.
Now, notice I'm getting an error on the getTotal 
method in this Meal class, on **burger.price**, 
which IntelliJ says _"**price**" has private access in Meal.Item_ here.

```java  
public double getTotal() {
    double total = burger.price + drink.price + side.price;
    return Item.getPrice(total, conversionRate);
}
```

**Burger** is an inner class on **Meal**, 
why isn't its price visible to **Meal**? 
Well, **Burger** inherited that field from **Item**, 
and although **Item**'s price is available to **Meal** 
through the **Item** class, 
it's not accessible as an inherited field on **Burger**. 
The rules of inheritance are still in place even within the inner class structure. 
The easiest way to get around this is to create a getter on the **Burger** class, 
that returns **super.price**, so let me do that.

```java  
public double getPrice() {
    return super.price;
}
```

This wouldn't work if **Item** were not an inner class of **Meal**, 
because price is private on Item, but this takes advantage of the fact 
that Meal can access **Item**'s price. 
And I'll change

```java  
public double getTotal() {
    //double total = burger.price + drink.price + side.price;
    double total = burger.getPrice() + drink.price + side.price;
    return Item.getPrice(total, conversionRate);
}
```

In the getTotal method on **Meal**. 
And with those changes, I can run my Store's main method, 
and I get the same results as before. 
But I do want to specialise the Burger, and add an attribute, 
a list of Toppings.

```java  
private class Burger extends Item {

    private enum Extra {AVOCADO, BACON, CHEESE, KETCHUP, MAYO, MUSTARD, PICKLES;

        private double getPrice() {
            return switch (this) {
                case AVOCADO -> 1.0;
                case BACON, CHEESE -> 1.5;
                default -> 0;
            };
        }
    }

    private final List<Item> toppings = new ArrayList<>();
    
    Burger(String name) {
        super(name, "burger", 5.0);
    }

    public double getPrice() {
        return super.price;
    }

    private void addToppings(String... selectedToppings) {
        for (String selectedTopping : selectedToppings) {
            try {
                Extra topping = Extra.valueOf(selectedTopping.toUpperCase());
                toppings.add(new Item(topping.name(), "TOPPING",
                        topping.getPrice()));
            } catch (IllegalArgumentException ie) {
                System.out.println("No topping found for " + selectedTopping);
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder itemized = new StringBuilder(super.toString());
        for (Item topping : toppings) {
            itemized.append("\n");
            itemized.append(topping);
        }
        return itemized.toString();
    }
}
```

I'm going to say **toppings** are just instances of Items. 
I'll use an ArrayList. 

```java  
private final List<Item> toppings = new ArrayList<>();
```

This is private on Burger, and I'm instantiating the list here. 
Before I add a method to add toppings to this list, 
I want to set up an enum, containing valid options for burger toppings. 

```java  
private enum Extra {AVOCADO, BACON, CHEESE, KETCHUP, MAYO, MUSTARD, PICKLES;

    private double getPrice() {
        return switch (this) {
            case AVOCADO -> 1.0;
            case BACON, CHEESE -> 1.5;
            default -> 0;
        };
    }
}
```

I'm doing this, first, because I want to keep reviewing past skills 
when the opportunity presents itself. 
It's just one way of many to meet the requirements of the challenge. 
I'm going to add this enum right here, inside my inner class, **Burger**. 
I'll call it extra, and add the various toppings. 
The first thing I want to point out is this compiles. 
Prior to the JDK 16 release, this wouldn't have been compiled 
because we couldn't include static members, 
other than a static constant variable, in an inner class.
And why am I saying this is static? 
Well, let me add the static keyword here.

```java  
static enum Extra {AVOCADO, BACON, CHEESE, KETCHUP, MAYO, MUSTARD, PICKLES}
```

Notice that IntelliJ has grayed it out.
If I hover over that, it tells me that 
_Modifier **static** is redundant for inner enums_. 
It's also redundant for interfaces and records. 
These types are all implicitly static when used as inner types. 
I'll just revert to that change. 
I can also make this private, which I want to do, 
because this enum won't be used by the client code. 
It's for internal use only, just internal to the Burger class. 
Now, I want to include my _addToppings_ method, on the **Burger** class.

```java  
private void addToppings(String... selectedToppings) {
    for (String selectedTopping : selectedToppings) {
        try {
            Extra topping = Extra.valueOf(selectedTopping.toUpperCase());
            toppings.add(new Item(topping.name(), "TOPPING", 0));
        } catch (IllegalArgumentException ie) {
            System.out.println("No topping found for " + selectedTopping);
        }
    }
}
```

I'm going to make this method private, and doesn't return anything, 
and it takes a var args method parameter. 
And now, I'll add code to loop through all the strings passed 
and look for a match on the enum constant by name. 
The _valueOf_ method on the enum lets us get an enum constant, 
by the constant's name. 
I take the varargs String passed on the method, making it all uppercases, 
then see if I can find a match to that name in my Extras enum. 
Then I add a new _Topping_ instance to my topping list on burger. 
This code is actually buggy, because what happens 
if an item isn't found on the enum? 
We'll find out when we run this code, but right now, 
let's keep going, with this as is. 
Also notice, I'm passing 0 as the price of all toppings.
This isn't what I want neither. 
In fact, in the enum lecture earlier in the course, 
I showed you an example of creating a method on your enum, 
and I'll do that, creating a _getPrice_ method.

```java  
private enum Extra {AVOCADO, BACON, CHEESE, KETCHUP, MAYO, MUSTARD, PICKLES;

    private double getPrice() {
        return switch (this) {
            case AVOCADO -> 1.0;
            case BACON, CHEESE -> 1.5;
            default -> 0;
        };
    }
}
```

Before I can do that, I have to include a semicolon after my list of constants. 
You only need that semicolon if you're including extra code, which I'll be doing. 
I'm making this private again, and it'll return a double. 
Now notice, I'm switching on the keyword, this. 
When the _getPrice_ method gets called, it will be called 
from an instance of one of these extra constants, 
so I can use this as my switch expression. 
For now, I've just set up the default label, returning zero, 
or no additional cost for a topping. 
But I do want to include some additional costs for _Avocado_, _Bacon_, 
and _Cheese_, for example, so I'll add case labels for those. 
When you switch on an enum, you use enum constants in the case labels, 
as I show above. 
I'm making Avocado 1$ extra, bacon and cheese will each be 1.50$ extra. 
Going back to my _addToppings_ method, I can call that method 
when I construct the Topping object.

I'll include a new line before the zero in that call,  
and I'll replace zero with a call to **topping.getPrice**.

```java  
private void addToppings(String... selectedToppings) {
    for (String selectedTopping : selectedToppings) {
        Extra topping = Extra.valueOf(selectedTopping.toUpperCase());
        //toppings.add(new Item(topping.name(), "TOPPING", 0));
        toppings.add(new Item(topping.name(), "TOPPING", topping.getPrice()));
    }
}
```

And this code compiles without issue, but are you wondering why? 
Why can I access a private method on the enum from this code? 
Again, this has to do with the special nature of inner types. 
Private attributes and methods are available to the enclosing class, 
and this is true for inner types like enums and records, as well as inner classes. 
Now, I want to invoke this from a method on my **Meal** class, 
and use the same signature. 
I'll add this method after the _getTotal_ method, 
and before the declaration of the **Item** class.

```java  
public void addToppings(String... selectedToppings) {

    burger.addToppings(selectedToppings);
}
```

I'll call it addToppings and support passing one or more toppings. 
This method simply hands off to (or delegates) the work to the _burger_ instance. 
Again, the **Meal** class is able to call a private method on the **Burger** class. 
Before I add any toppings in the main code, 
I want to make sure they get printed out, and any additional prices get added
to the total price. 
First, on burger, I want to implement a _toString_ method.

```java  
@Override
public String toString() {

    StringBuilder itemized = new StringBuilder(super.toString());
    for (Item topping : toppings) {
        itemized.append("\n");
        itemized.append(topping);
    }
    return itemized.toString();
}
```

I'm using _StringBuilder_, because I'll be appending different numbers of toppings 
to this data, and I'll do that next with an enhanced for loop, first I'll print out 
the base price of the burgers, then print each topping and its price, on separate lines. 
Now, I want to go back to the Burger's _getPrice_ method.

```java  
public double getPrice() {
    //return super.price;
    //double total = price;
    double total = super.price;
    for (Item topping : toppings) {
        total += topping.price;
    }
    return total;
}
```

I'll remove the return _super.price_ statement, and I'll add the code to get 
the full price of the burger. 
I'll loop through each topping, and add the price to total. 
What is price on this first statement, **double total = price;** anyway?
Is it burger's price? 
Luckily, IntelliJ is keeping track for us if we get confused. 
Hovering over price there, you can see it's not burger's price, 
it's Meal's base price.
Now, for the regular meal, this may be fine, but the burger's base price may change. 
I want to get the price off the superclass, so **super.price**. 
This ultimately gets the burger's set price without the additional toppings. 
Ok, so I set up a local variable, total to be the burger's base price, 
then I loop through every topping, and get the price off of each topping. 
I can run this again from Store's main method, 
and I should get all the same pricing as before, 
since I haven't yet added any toppings. 
But we went through all this work, to add some toppings, 
so let's do that in the main method of Store for our regular burger.

```java  
public class Store {

    public static void main(String[] args) {

        Meal regularMeal = new Meal();

        regularMeal.addToppings("Ketchup", "Mayo", "Bacon");
        System.out.println(regularMeal);
    }
}
```

I'll add Ketchup, Mayo and Bacon as toppings. 
And running that:

```java  
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
```

You can see that the toppings are shown in this itemized list of the first burger, 
and the total price has gone up to 10$, because Bacon cost an additional 1.5$. 
Now, I mentioned earlier, I left a bug in this code, and I know exactly how to break it. 
I'll add a Topping that's not part of our Extra list, like Cheddar, for example.

```java  
public class Store {

    public static void main(String[] args) {

        Meal regularMeal = new Meal();

        regularMeal.addToppings("Ketchup", "Mayo", "Bacon", "Cheddar");
        System.out.println(regularMeal);
    }
}
```

And now if I run it:

```java  
Exception in thread "main" java.lang.IllegalArgumentException: No enum constant Meal.Burger.Extra.CHEDDAR
```
                    
I've caused my program to crash, and that's definitely not what I want to happen. 
This exception gets thrown from invoking the _valueOf_ method on an enum 
for a constant name that's not in the enum. 
We've briefly touched on exception handling before this and learned enough to test 
and catch this exception. 
But where is a good place to catch and handle it? 
Do we want the code to add all the other toppings successfully, 
and proceed with just a message that one item couldn't be included? 
Or do we want the entire add toppings method to fail, 
so the clerk is forced to ask the customer again? 
I'm picking the first option, 
so I'll put a try catch block in the method that adds toppings on the burger.

```java  
private void addToppings(String... selectedToppings) {
    for (String selectedTopping : selectedToppings) {
        try {
            Extra topping = Extra.valueOf(selectedTopping.toUpperCase());
            //toppings.add(new Item(topping.name(), "TOPPING", 0));
            toppings.add(new Item(topping.name(), "TOPPING", topping.getPrice()));
        } catch (IllegalArgumentException ie) {
            System.out.println("No topping found for " + selectedTopping);
        }
    }
}
```

I'll insert a try statement before I invoke the value of method on my enum, 
and I'll add a catch clause. 
Illegal argument exception is what I want to catch. 
And If I end up inside the catch block, it means the topping, 
I'm trying to add, does not exist.

```java  
public class Store {

    public static void main(String[] args) {

        Meal regularMeal = new Meal();

        regularMeal.addToppings("Ketchup", "Mayo", "Bacon", "Cheddar");
        System.out.println(regularMeal);
    }
}
```

And running the code again:

```java  
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
```

You can see that message printed out, no topping found for cheddar, 
so the code handled the situation and proceeded, 
created the meal with all the other valid toppings. 
Ok, so that was the inner class challenge.
</div>