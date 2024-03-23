# [Section-4: Object-Oriented Programming (PART-II)]()

It's now time to look at the remaining three major components of OOP. 
These are:

* Composition 
* Polymorphism 
* Encapsulation

By the end of this section, you should have a solid overview of what these concepts are, 
and also how to apply them to your programs.

## [a. Composition (Part-I)]()

<div align="justify">
As a refresher, I want to review another inheritance example before we move into composition.
Let's say we're interested in building a computer, and we want to assemble it.
All the parts are manufacturer's products, which we have to buy, 
and assemble, to sell our final product, the personal computer.
Next, we'll look at a class diagram of some computer products or computer parts.
</div>

![image01](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image01.png?raw=true)

<div align="justify"> 
In this instance, we have a base class called "Product."
All of our computer parts are going to inherit from Product.
All our parts will then have the same set of attributes, a manufacturer, and model, 
and dimensions, the width, height, and depth in other words. 
All of these items are products are particular type of Product. 
Personal Computer, Monitor, Motherboard, and ComputerCase are but a few parts, or products; 
you can think of that would be sold by a computer distributor.
</div>

<p></p>

<div align="justify">
Ok, let's build this. Creating a new class named "Product," 
we start adding the attributes and a constructor, 
which only includes the model and manufacturer for simplicity:
</div>

```java  
public class Product {
    private String model;
    private String manufacturer;
    private int width;
    private int height;
    private int depth;
    public Product(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }
}
```

<div align="justify">
Ok, we now have our base class, and next let's create a couple of subclasses. 
Remember, only one class can be public, so we'll omit the access modifier for these classes.
We'll start with Monitor. 
For Monitor to be a subclass of Product, 
we have to add extends Product by generating the constructor that has got two attributes:
</div>

```java  
class Monitor extends Product {
    public Monitor(String model, String manufacturer) {
        super(model, manufacturer);
    }
}
```

<div align="justify">
Ok, that's our first subclass. 
Let's create our second class just by copying and pasting the same class 
but with changing the method name, and the constructor:
</div>

```java  
class Motherboard extends Product {
    public Motherboard(String model, String manufacturer) {
        super(model, manufacturer);
    }
}
```

And finally, let's create ComputerCase the same way:

```java  
class ComputerCase extends Product {
    public ComputerCase(String model, String manufacturer) {
        super(model, manufacturer);
    }
}
```

<div align="justify">
Ok, so our three classes all extend Product.
We can say now, that Monitor has got a relationship with Product, 
meaning that a Monitor can be said to be a type of Product.
And that's the relationship between the two classes.
And we can quite correctly say that all three of these parts, 
Monitor, Motherboard, and ComputerCase are all types of Product.
And that's essentially what inheritance is.
</div>

<p></p>

<div align="justify">
Inheritance defines an "is A" relationship.
Composition defines a "HAS A" relationship.
Next, let's look at how we'd use composition to create a personal computer.
</div>

![image02](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image02.png?raw=true)

<div align="justify">
To keep this diagram simple, I'm showing only that PersonalComputer inherits from Product, 
but remember all of our classes are really subclasses of product.
But a PersonalComputer, in addition to being a product, is actually made up of other parts.
This diagram shows that a computer has a motherboard, a case, and a monitor.
The motherboard, the computer case, and the monitor aren't computers, 
not in the same sense that they're products.
So that's what Composition is, actually modeling parts, and those parts make up a greater whole.
In this case, we're going to model the personal computer, 
and we're modeling the "has a" relationship, with the motherboard, the case, and the monitor.
And we've already created the parts, but we didn't really make them different from each other.
Now we'll look at another diagram that shows these classes, 
with some state and behavior, specific to their product type.
</div>

![image03](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image03.png?raw=true)

<div align="justify">
This diagram shows three classes that will make up the personal computer.
We'll have size and resolution for the monitor, and we'll want a method called drawPixelAt, 
so the monitor has behavior to draw.
For the computerCase, we'll have a powerSupply, and a method pressPowerButton().
And for the Motherboard, we'll have slots for ram and cards, as well as a bios' attribute, 
and a method called loadProgram().
</div>

<p></p>

<div align="justify">
Getting back to the code, let's add these members to our parts.
We'll start with the motherboard, and add the three attributes first, 
and next we want to generate another constructor, 
which initializes these three attributes but also calls super constructor:
</div>

```java  
private int ramSlots;
private int cardSlots;
private String bios;
public Motherboard(String model, String manufacturer, int ramSlots, int cardSlots, String bios) {
    super(model, manufacturer);
    this.ramSlots = ramSlots;
    this.cardSlots = cardSlots;
    this.bios = bios;
}
```

So we'll have one more method, called loadProgram:

```java  
public void loadProgram(String programName) {
    System.out.println("Program " + programName + " is now loading...");
}
```

<div align="justify">
That's our Motherboard class, pretty straightforward as you can see, 
with nothing particularly complicated there.
Now, let's move onto our Monitor class, and add those fields, size and resolution:
</div>

```java  
private int size;
private String resolution;
public ComputerCase(String model, String manufacturer, int size, String resolution) {
    super(model, manufacturer);
    this.size = size;
    this.resolution = resolution;
}
```

<div align="justify">
Ok, so now we have the fields and the constructors.
What we're going to do here next is, create a public method called drawPixelAt, 
and it's going to have an x and y location, and a color. 
We want to use String.format, which we learned to do in the last section.
And we'll just print a simple message out:
</div>

```java  
public void drawPixelAt(int x, int y, String color) {
    System.out.println(String.format(
            "Drawing pixel at %d,%d in color %s ", x, y, color));
}
```

<div align="justify">
And that completes the Monitor class.
Finally, let's finish Computer Case, which we've said has a power supply and one method, 
pressPowerButton, so we'll add the powerSupply field:
</div>

```java  
private String powerSupply;
public ComputerCase(String model, String manufacturer, String powerSupply) {
    super(model, manufacturer);
    this.powerSupply = powerSupply;
}
public void pressPowerButton() {
    System.out.println("Power button pressed");
}
```

<div align="justify">
 Now that we have all of our parts done, it's time to build the computer.
 This is the fun part, where we actually create a class that consists of the computer case, 
 the monitor, and the motherboard.
 Creating a new class, named PersonalComputer, the first thing we want to do is 
 add extends Product to this class too.
 Even though our personal computer has a bunch of parts, it's still a product, 
 with all the product's attributes.
 You can use a combination of inheritance and composition to create your model, 
 as we're doing here.
 And this class won't compile until we have a constructor, 
 but let's wait to do that until after we've added our attributes.
 Let's look at our PersonalComputer class diagram again.
 We've said it inherits from Product, and it also has three fields, 
 which are classes, these are Monitor, Motherboard, and ComputerCase.  
 Let's build this Personal Computer class now, using composition, which means we'll add three attributes:
</div>

```java  
public class PersonalComputer extends Product{
    private ComputerCase computerCase;
    private Monitor monitor;
    private Motherboard motherboard;
}
```

<div align="justify">
And you can see the obvious advantage here, because if you're using the extends option to inherit, 
Java only lets you inherit from one class at a time.
You could see that we'd run into difficulties and limitations quite quickly 
if our only tool was inheritance. 
</div>

![image04](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image04.png?raw=true)

<div align="justify">
In this case, what we've said is, the Personal computer consists of these three other classes,
namely the computer case, the monitor, and the motherboard.
Before we do anything else, we should add a constructor. 
You can see IntelliJ isn't happy with us because we haven't done that yet.
</div>

```java  
public PersonalComputer(String model, String manufacturer, ComputerCase computerCase, Monitor monitor, Motherboard motherboard) {
    super(model, manufacturer);
    this.computerCase = computerCase;
    this.monitor = monitor;
    this.motherboard = motherboard;
}
```

And for this class, we do want to add some getter methods for these attributes.

```java  
public ComputerCase getComputerCase() {
    return computerCase;
}
public Monitor getMonitor() {
    return monitor;
}
public Motherboard getMotherboard() {
    return motherboard;
}
```

<div align="justify">
Ok, that's our Personal Computer. 
We've described what we want to assemble.
In the next course, we'll actually assemble the parts, 
and we'll look at how to access the functionality of the parts
through the personal computer container.
</div>

## [b. Composition (Part-II)]()

<div align="justify">
Now, we need to assemble the Personal Computer.
Let's create some objects in the Main class firstly, an object of type ComputerCase.
We'll create a Dell 2208 case, and it will have a 240 power supply:
</div>

```java  
ComputerCase theCase = new ComputerCase("2208", "Dell", "240");
```

<div align="justify">
That's our definition for a computer case now.
Remembering that when we create the personal computer, which we're going to be doing shortly, 
it needs this reference.
PCs generally can't be put together without a case, so what we're doing here is creating that case.
Next, we need to create a monitor, this time we'll say it's an Acer 27inch beast, 
so we'll pass in the size, and the resolution of 2540 by 1440.
</div>

```java  
Monitor theMonitor = new Monitor("27inch Beast", "Acer", 27, "2540 x 1440");
```

<div align="justify">
We've now got a case and a monitor; that ultimately are going to be used to create our PC.
Of course, we now need a motherboard. 
And we'll make this an Asus, BJ-200 model. 
We also need the Number of RAM slots, which we'll say is 4. 
And the number of card slots, let's say there's 6. 
And for the bios, let's say it's version v2.44.
</div>

```java  
Motherboard motherboard = new Motherboard("BJ-200", "Asus", 4,6,"v2.44");
```

<div align="justify">
That gives us our motherboard.
And now that we've got all our parts, we can put our Personal Computer together.
We've created instances of a Monitor class, a Motherboard class, and a Computer Case class, 
and it's time to build this, and see what we can do with it.
</div>

<p></p>

<div align="justify">
The PC will have the same manufacturer and model as the computer case, so 2208 as the model, 
then Dell as the manufacturer, and then we'll pass the other variables to the PC.
</div>

```java  
PersonalComputer thePC = new PersonalComputer("2208", "Dell", theCase, theMonitor, theMotherboard);
```

<div align="justify">
And you can see here, we've created the PC object by passing those three other objects to it, 
as well as the model and manufacturer.
</div>

<p></p>

<div align="justify">
With inheritance, we were able to use a method from the base class.
But how to we get access to the composite object's methods? 
In other words, how do we make it do anything? 
Well, we can do this by telling one of its parts to do something.
For example, if we want to draw something using the monitor, how do we access this method?
We have the method drawPixelAt on monitor, but how do we access that? 
Well, we can get the PC's monitor object, using the getter method, 
and then call drawPixelAt on that object, so let's do that:
</div>

```java  
thePC.getMonitor().drawPixelAt(10, 10, "red");
```

<div align="justify">
That's one way of executing functionality, by using the getter method from PC, 
to get the instance of the monitor class, that the Pc contains.
In this code, from that return object, we're chaining the call to the method drawPixelAt.
And that's invoking that method for us.
Likewise, we could call the method loadProgram on the motherboard, 
passing it Windows OS, for example, as the program we want to run.
</div>

```java  
thePC.getMotherboard().loadProgram("Windows OS");
```

<div align="justify">
We're doing the same thing here, we're getting the Motherboard object that's been created, 
and that PC has a reference to, with the variable motherboard.
And we use a getter method to get that reference, then execute loadProgram method on that.
Lastly, let's do that with the next method, the pressPowerButton on the Computer case.
</div>

```java  
thePC.getComputerCase().pressPowerButton();
```

<div align="justify">
Here, in all three cases, we're ultimately accessing functions in other classes, 
theCase, theMotherboard, and theMonitor, but we're accessing them through thePC object.
This is what composition is, this is the difference from inheritance. 
What we did to call the PC object was, we created our case, our monitor, and our motherboard.
And in reality, when putting a computer together, that's what you'd be doing.
You'd buy the computer case, you'd buy the monitor, 
and you'd buy the motherboard, and you'd then put it together to build the PC.
</div>

<p></p>

<div align="justify">
We built this PC by passing objects to the constructor, like assembling the computer.
Let's continue on now, and look at another scenario, whereby we can actually hide 
the functionality further.
In this case, we're not going to allow the calling program to access those objects, the parts, directly. 
We don't want anybody to access the Monitor, Motherboard, ComputerCase directly.
</div>

<p></p>

<div align="justify">
What we'll do first is, we'll go to our PC class, and we'll comment out all the getter methods. 
You'll remember this encapsulates these attributes it hides them from the calling code.
And just to go back and show you what happened, we're now getting an error, 
because those methods no longer exist.
We really don't want the Main class, or any class except the PC class, to make calls on its parts.
</div>

<p></p>

<div align="justify">
Ok, so next, let's create methods on PC, which we'll expose to the calling code.
The first one, we'll call drawLogo, and we're going to implement that in a private method.
And it might do some fancy graphics, but here we're not going to write that code yet.
Instead, we'll just call drawPixelAt, on the monitor attribute of our computer.
We're making this private because the only code we want to draw the computer manufacturer logo 
is the personal computer itself.
</div>

```java  
private void drawLogo() {
    monitor.drawPixelAt(1200, 50, "yellow");
}
```

<div align="justify">
Next, we want to call this method, from a method we'll call power up.
If you think about a computer, when it starts up, you press the power button, 
and then you see a logo come up on the screen.
</div>

```java  
public void powerUp() {
    computerCase.pressPowerButton();
    drawLogo();
}
```

<div align="justify">
Here, we've got a public method called powerUp, and a private function called drawLogo.
The powerUp function of the PC will draw that logo on the screen, as part of starting up the computer.
And now, going back to the main class, we'll comment out the three lines that aren't compiling.
Instead, we'll make a call to the powerUp method on the PC.
</div>

```java  
thePC.powerUp();
```

And if we run that, we get the result:

```java  
Power button pressed
Drawing pixel at 1200,50 in color yellow
```

<div align="justify">
From the calling code's perspective, this code in Main didn't have to know anything about PC's parts, 
to get the PC to do something.
Composition is actually creating objects within objects.
It's like creating a primary object. 
In this case, the PC is managing and looking after all its parts, and it uses composition to achieve that.
</div>

### Use Composition or Inheritance or BOth?

<div align="justify">
As a general rule, when you're designing your programs in Java, 
you probably want to look at composition first. 
If you do any sort of research on the Internet, most of the experts will tell you that as a rule, 
look at using composition before implementing inheritance.
You saw in this example, we actually used both.
</div>

<p></p>

<div align="justify">
All our products, the parts that made up the finished product, and the finished product itself, 
were able to inherit a set of attributes, like the manufacturer and model.
The calling code didn't have to know anything about these parts, to get PC to do something.
It was able to tell the pc to power up, and the pc delegated that work to its parts.
</div>

<p></p>

The reasons of preference of composition against inheritance are:
* Composition is more flexible.
You can add parts in, or remove them, and these changes are less likely to have a downstream effect.
* Composition provides functional reuse outside the class hierarchy, 
meaning classes can share attributes & behaviors by having similar components, 
instead of inheriting functionality from a parent or base class.
* Java's inheritance breaks encapsulation because subclasses may need direct access 
to a parent's state or behavior.

<div align="justify">
We showed you an example of using the protected access modifier in the last course, 
and we'll talk about why this breaks encapsulation, in the next courses on encapsulation.
</div>

* Inheritance is less flexible. 
Adding a class to, or removing a class form, a class hierarchy, 
may impact all subclasses from that point.
* A new subclass may not need all the functionality or attributes of its parent class, 
and therefore adding the subclasses will muddy the waters, 
meaning the model no longer represents the reality in the code.

### Adding a Digital Product

Let's say we want to include digital products, like software products, in our product inventory.
Should Digital Product inherit from Product?

![image05](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image05.png?raw=true)

<div align="justify">
Here we show the model with Digital Product, inheriting from our current definition of Product.
If we do this, this should mean Digital Product has Product's attributes, but this isn't true now.
A digital product wouldn't really have width, height, and depth, 
so this model isn't a good representation of what we want to build.
It would be better if we didn't have those three attributes on Product, 
but instead used composition to include them on certain products, but not all products.
</div>

![image06](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image06.png?raw=true)

<div align="justify">
Now, consider this revised class diagram.
We haven't completely removed the class hierarchy, but we've made the base class, Product, 
less specific, or more generic.
We've removed the width, height, and depth attributes from the Product, and made a new class, 
Dimensions, with those attributes.
And we've added an attribute to Motherboard, which is dimensions, which has those attributes.
</div>

<p></p>

<div align="justify">
This design allows for future enhancements to be made, like the addition of the subclass Digital Product, 
without causing problems for existing code, that may already be extending Product.
By pulling width, height, and depth into a dimension class, we can use composition
to apply those attributes to any product, as we did here with Motherboard, 
but we're not requiring that all subclasses be defined with those attributes.
This is just one example of the inflexibility of inheritance, 
compared to a more flexible design with composition.
</div>

## [c. Composition Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/Course03_CompositionChallenge/README.md#composition-challenge-answer)

<div align="justify">
In this challenge, you need to create an application for controlling a smart kitchen.
Your smart kitchen will have several appliances.
Your appliances will be Internet Of Things (IoT) devices, which can be programmed.
</div>

![image07](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image07.png?raw=true)

<div align="justify">
For example, you might do stuff in your kitchen in the morning, in a rush to get to work, 
and then expect your smart kitchen appliances to run automatically at a later time.
</div>

<p></p>

<div align="justify">
It's your job to write the code, to enable your Smart Kitchen application to execute certain jobs.
Methods in your SmartKitchen class will determine what work needs to be done:
</div>

* addWater() will set the Coffee Maker's hasWorkToDo field to true.
* pourMilk() will set Refrigerator's hasWorkTodo to true.
* loadDishwasher() will set the hasWorkToDo flag to true, on that appliance.

<div align="justify">
Alternatively, you could have a single method, called setKitchenState, that takes three boolean values, 
which could combine the three methods above.
</div>

To execute the work needed to be done by the appliances, you'll implement this in two ways:

* First, your application will access each appliance (by using a getter), and execute a method.
The appliance methods are orderFood() on Refrigerator, doDishes() on DishWasher, 
and brewCoffee() on CoffeeMaker.
These methods should check the hasWorkToDo flag, and if true, print a message out about 
what work is being done.
* Second, your application won't access the appliances directly.
It should call doKitchenWork(), which delegates the work to any of its appliances.

<div align="justify">
You'll have to create the appliances as classes, with their own methods, as shown on the diagram, 
and then compose your smart kitchen with these appliances.
</div>

## [d. Encapsulation]()

<div align="justify">
In this course, we talk about another fundamental OOP concept, which is Encapsulation. 
We've already talked about it briefly when we first introduced getter methods.
In Java, encapsulation means hiding things by making them private or inaccessible. 
Why would we want to hide things in Java?
</div>

* To make the interface simpler, we may want to hide unnecessary details.
* To protect the integrity of data on an object, we may hide or restrict access to some data and operations.
* To decouple the published interface from the internal details of the class, 
we may hide actual names and types of class members. 
This gives us more flexibility if we have to change the class in the future.

<div align="justify">
And you might wonder what we mean by interface here? 
Although Java has a type called interface, which is important, 
and we'll be covering it in a later section, that's not what we're talking about here. 
When we talk about a class's public or published interface, 
we're really talking about the class members that are exposed to, 
or can be accessed by the calling code. 
Everything else in the class is internal or private to it. 
An application programming interface, or API, 
is the public contract that tells others how to use the class.
</div>

<p></p>

<div align="justify">
What we're going to do is, create a class that doesn't use encapsulation, 
to show why this feature's important. 
We're going to start off with the class that does not use it, 
and then we'll create another enhanced class; that does use it. 
We'll start with the non-encapsulated class, called Player.
</div>

![image08](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image08.png?raw=true)

<div align="justify">
Hopefully you're getting used to seeing classes on a diagram now, 
and can appreciate looking at a class this way before we even build it. 
This is the model for a Player class, as in a player in a computer game. 
And The Player will have three variables: name, health, and weapon. 
And this class will have three methods, loseHealth(), restoreHealth(), and healthRemaining(), 
which I'll explain in a bit. 
And we're going to create this class without using encapsulation.
</div>

<p></p>

<div align="justify">
To do this, we'll create a new Java class and call it Player. 
And let's add the variables or fields, and we've said these are the player's name, 
the player's health, and the weapon:
</div>

```java  
public class Player {
    public String name;
    public int health;
    public String weapon;
}
```

<div align="justify">
Now, what I want you to notice is that here, we've actually used a public access modifier. 
We've discussed this before, and we've said, it's usually a good idea to use private. 
But we're using "public" for a reason here, and that'll become clear shortly. 
We're not going to create a constructor for this class, but we'll create our three methods next. 
The first one's called loseHealth. 
This gets called if the player loses some health for some reason, 
like they fell off a cliff, or they got damaged in some way. 
We pass an integer value to this method, and that's subtracted from the player's health. 
If this brings the player's health down to a value less than zero, 
we'll print that the player's been knocked out of the game:
</div>

```java  
public void loseHealth(int damage) {
    health -= damage;
    if (health <= 0) {
        System.out.println("Player knocked out of game");
    }
}
```

<div align="justify">
There we have the first method, which is going to subtract health from our player.
The second method is going to return the amount of health that's available, or what's remaining.
We're calling this method healthRemaining.
And for now, it's simply going to return our variable, health, 
which is our field in this class, so it's really a type of getter, or accessor method.
</div>

```java  
public int healthRemaining() {
    return health;
}
```

<div align="justify">
Finally, we'll add a way for the player to have this health restored, with the restoreHealth method.
This method might be called in the game if the Player finds health tokens, or does something healthy.
This method will add extraHealth points to the health field.
</div>

```java  
public void restoreHealth(int extraHealth) {
    health += extraHealth;
}
```

<div align="justify">
Now, let's make a rule that health should never go over 100. 
If health is greater than 100, we'll just set it back to be 100, 
and print that the player was completely restored.
</div>

```java  
public void restoreHealth(int extraHealth) {
    health += extraHealth;
    if (health > 100) {
        System.out.println("Player restored to 100%");
        health = 100;
    }
}
```

<div align="justify">
The Player class is done now, and we can go back to our main class. 
And we'll create a new Player.
</div>

```java  
Player player = new Player();
```

<div align="justify">
We didn't create a constructor in the Player class, 
but if you recall a default constructor with no parameters, gets created for us by Java. 
In other words, we don't now have a constructor to pass player data when we create a new player. 
This means the calling code needs to manually set the data in these fields.
</div>

```java  
player.name = "Korhan";
player.health = 20;
player.weapon = "Sword";
```

<div align="justify">
That's the way we need to initialize those fields.
And, as you can see, even though we're not in that class, we can access those fields directly.
That's because we set the access to be public for those fields. 
If we set those to private, we wouldn't be able to do this.
</div>

Next, let's test the methods on the Player class:

```java  
int damage = 10;
player.loseHealth(damage);
System.out.println("Remaining health = " + player.healthRemaining());
```

<div align="justify">
Looking at this code, you can see we first made the player health = 20 above this, 
and here we've got "10" damage. 
Then we're calling the player.loseHealth method, which actually reduces the player's 
health by the damage done. 
And if we run that,
</div>

```java  
Remaining health = 10
```

<div align="justify">
You can see the result. 
Ok, let's add some more game play with a few more statements:
</div>

```java  
player.loseHealth(11);
System.out.println("Remaining health = " + player.healthRemaining());
```

And running this:

```java  
Remaining health = 10
Player knocked out of game
Remaining health = -1
```

<div align="justify">
We can see that the second call to loseHealth, this prints that the player got knocked out, 
because after the call, the remaining health is now equal to -1. 
That's obviously working, but I'm wondering whether you can see a possible problem here. 
We've created a method in here, loseHealth, which is the code that should manage 
the process of the player losing health. 
This is where we want to put in all our formulas, for making sure that we're calculating 
health correctly when some damage happens. 
But what happens if the calling code decides to just access the player's health directly 
any time it wants? 
And it just sets the player health here, above the second call to the loseHealth method:
</div>

```java  
player.health = 200;
```

<div align="justify">
Now, here, the main method has kind of gone rogue, because it's setting the player's health manually, 
outside the normal game play method calls. 
This code actually takes control away from the Player class, for managing the Player's health. 
It didn't call the restoreHealth method, which has the additional control in place, 
that player health can never exceed 100. 
This is an example of the first problem, with a class that's not encapsulated properly.
</div>


### Problems(when no encapsulations)

<div align="justify">
1. Allowing direct access to data on an object can potentially bypass checks, 
and additional processing, your class has in place to manage the data. 
By being able to access fields directly like this, we're really potentially opening up our application 
to be accessed in ways that it shouldn't be. 
Maybe we don't ever want the code to be able to change the health like that, 
because we really wanted the restoreHealth method to be called, 
and the right set of conditions to be set. 
But because these fields are public to everyone, we can't control when they get accessed.
</div>

<p></p>

<div align="justify">
2. Allowing direct access to fields means calling code would need to change 
when you edit any of the fields. 
If we go back to our Player class again, and let's say, we've decided that 
we want to change the field for the name of our player. 
Maybe now, we think the name isn't descriptive enough, and we want to be a bit much clearer.
We're going to change name to fullName, and that's going to be the variable name that we want to use, 
from this point on. 
This is really just an internal change to the Player class, and in theory 
it shouldn't affect any other class.
We should be able to call class variables anything we want, and be able to change them if we want to.
But if we go back to the Main class now, suddenly we've got an error.
And it's quite rightly saying that it can't find that field anymore, 
because we're trying to access these fields directly, we've got an error here. 
Now, this might not seem like the end of the world. 
But what it means it, that anytime that we change this field's name, 
we have to also make the change here in the Main class. 
And in a small application like this, it's not going to be a problem. 
It's a quick change. 
But when you're talking about big applications, it does become a problem. 
Because the first thing here, looking at the Main class, 
you have to figure out what's the new correct name of the field.
This means you need to go back into the code and have a look and see what the new name is. 
And maybe this isn't really a problem as much when it's your own code, 
but if you're deploying this kind of code in a library, for example, 
you're setting yourself up for a lot of problems.
</div>

<p></p>

<div align="justify">
3. Omitting a constructor that would accept initialization data 
may mean the calling code is responsible for setting up this data on the new object. 
The third issue with this approach is, we're manually initializing our object with these calls at the start. 
This means the calling code is responsible for making all the right method calls, 
to initialize a player, at the beginning of the play. 
But the code might actually forget to initialize this all together. 
And consequently, when the class is called for the first time, 
the player may be starting out with health = 0. 
In other words, we're not guaranteeing or ensuring that to access the player class, 
we can only access it when the data is valid. 
Now we can do that with a constructor.
We can actually make sure that the data is valid, and that the object is valid before the game starts. 
But when you're allowing people to manually access the fields, 
there's no real way to guarantee that the player health is set.
</div>

<p></p>

<div align="justify">
And this is what encapsulation actually does for us, and why we don't want to code like this.
We want the ability to ensure certain conditions are met before playing, 
and that access to the player data during the game is controlled and protected. 
Now that you've seen some of the bad things, the bad ways of doing it, 
let's actually comment this code out.
</div>

<p></p>

<div align="justify">
Let's create a new class that actually has got proper encapsulation by creating a new class, 
named EnhancedPlayer, and show you the right way of doing it. 
The difference here is, we're going to create our three fields as private.
</div>

```java  
private String name;
private int health;
private String weapon;
```

<div align="justify">
And here, we have the name of the Player, the health and the weapon, they're all declared as private. 
Next, we do want to create a constructor. 
Placing the cursor below those fields, we'll first create a constructor with three fields.
</div>

```java  
public EnhancedPlayer(String name, int health, String weapon) {
    this.name = name;
    this.health = health;
    this.weapon = weapon;
}
```

<div align="justify">
And what we're going to do is, use this constructor to make sure the health field stays 
in the range of 1 to 100, with an if-else statement. 
Let's edit this constructor a bit, if a user passes a value that's less than 1, 
we'll just make the health 1. 
And if we get a value greater than 100, than doesn't make sense either.
</div>

```java  
this.name = name;
if (health < 0) {
    this.health = 1;
} else if (health > 100) {
    this.health = 100;
} else {
    this.health = health;
}
this.weapon = weapon;
```

<div align="justify">
This constructor gives us a little more control about how a new Player is created.
Now, we could put more validation in there, to check the length of the name, 
or to make sure the name we get isn't null, for example, and likewise for the weapon.
In other words, we could do some additional validation to make sure that it's valid.
And actually, let's create an overloaded constructor that does not have health or weapon, 
because we'll set these to some default values. 
Generating another constructor for name:
</div>

```java  
public EnhancedPlayer(String name) {
    this(name, 100, "Sword");
}
```

<div align="justify">
This will make creating a new player easy, and we can make the default value for health be 100, 
meaning the Player starts out completely healthy. 
And we're guaranteeing now that the name, the health, and the weapon are initialized 
when the class is created. 
Now, let's copy the three methods we had for Player. 
Ok, now we have a better Player class, which we're calling EnhancedPlayer. 
And if we go back now to the Main class and main method, we can create a new EnhancedPlayer:
</div>

```java  
EnhancedPlayer korhan = new EnhancedPlayer("Korhan");
System.out.println("Initial health is " + korhan.healthRemaining());
```

<div align="justify">
Now if we run that, we should get initial health is 100. 
But what if we use the other constructor, and pass 200 as the health, and sword as the weapon? 
Let's do that and see what happens:
</div>

```java  
EnhancedPlayer korhan = new EnhancedPlayer("Korhan", 200, "Swrod");
System.out.println("Initial health is " + korhan.healthRemaining());
```

If we run that:

```java  
Initial health is 100
```

<div align="justify">
Hopefully, you can see, straight away; we've got more control.
There's no other way for the calling code to change the health, 
except to call EnhancedPlayer's method to do it.
This gives all the control back to the EnhancedPlayer class.
And just to be clear, this is the encapsulation. 
This is what we're doing, by making our fields private, 
we're making sure that the fields within the class aren't accessible to any classes that our outside.
</div>

<p></p>

<div align="justify">
And now, let's say I wanted to change the field we use for the player's name, 
changing it to "fullName" from name, like we did with Player. 
But this time, I want to show you another feature of IntelliJ that's really cool, 
and enables you to do a quick rename. 
If you actually click on what you want to rename, name in this case, 
and right-click, go to re-factor, then choose rename. 
And now if I go back and change this, the part in blue I change that to fullName, 
you see it turns pink. 
Before hitting the Enter key, we can scroll down and 
see every instance of name has been replaced with fullName. 
And if we press Enter, we get a popup that asks us if we want to change 
the parameter names of the constructors, and this really is up to you.
But this time I'll select all, and hit ok, and it changes the name to fullName in our code.
And for good measure, let's change the name of the field health, to healthPercentage.
The point is, we've now changed two fields names and the parameter name(health) 
has no effect on how the code works. 
But check out the code in the main method of the Main class. 
It didn't have to change at all. 
It runs exactly as it did before. 
The structure to create a new player hasn't changed, and the method name to get the health didn't change.
This means the code here, the calling code, has no idea what the internal naming was for the fields. 
And it doesn't need to. 
We're just calling this healthRemaining method, and it's doing the rest of us. 
This is another cool feature of encapsulation, which is, we can make all three changes 
to this enhanced player class, we can create private fields and private functions, 
that we don't want to be exposed to any other class. 
And we can change those names at any time in this code, without affecting any other code.
</div>

### Benefits of Encapsulation

<div align="justify">
That's really one of the huge benefits of encapsulation, is that 
you're not affecting any other code. 
It's sort of like a black box in many ways. 
What we're saying here is, the only way to create an enhanced player object is 
to call the constructor with these parameters. 
And also we got some rules in there for what health can be. 
But the other thing is we can't bypass the EnhancedPlayer's method 
and change the health field directly. 
We haven't provided a mechanism to change the health by any other means, 
except to use the methods, loseHealth, or restoreHealth. 
We can pass the initial amount of health in the constructor, 
but we can't change it after that, like we did in the other class. 
Using the Player class, we could set the value, and we actually changed the value midpoint 
when we shouldn't have. 
But the EnhancedPlayer class has more control over its data. 
This is why we want to use encapsulation.
</div>

<p></p>

<div align="justify">
We really want to protect access to our object's data. 
We protect the members of the class, and some methods, from external access. 
This prevents calling code from bypassing the rules and constraints, we've built into the class. 
When we create a new instance, it's initialized with valid data. 
But likewise, we're also making sure that there's no direct access to the fields. 
That's why you want to always use encapsulation. 
It's something that you should really get used to.
</div>

To create an encapsulated class, you want to:

* Create constructors for object initialization,
* which enforces that only objects with valid data will get created.
* Use the private access modifier for your fields.
* Use setter and getter methods sparingly, and only as needed.
* Use access modifiers that aren't private, only for the methods that the calling code needs to use.

<div align="justify">
You definitely don't want to be creating code like the Player class when you're creating public fields. 
In the majority of cases, you don't want to give access to other classes.
As you saw, it's more work to make those changes when you create, or when you change a field name, 
or something of that nature.
</div>

## [e. Encapsulation Challenge](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/Course05_EncapsulationChallenge)

<div align="justify">
This is the challenge to create a class, and to demonstrate proper encapsulation techniques, 
such as you've learnt in the previous course. 
In this challenge, you need to create a class named Printer.
</div>

![image09](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image09.png?raw=true)

It's going to simulate a real computer printer, a laser printer.
The fields in this class are going to be:
* **tonerLevel**: which is the percentage of how much toner level is left.
* **pagesPrinted**: which is the count of total pages printed by the Printer.
* **duplex**: which is a boolean indicator. 
If true, it can print on two slides of a single sheet of paper.

<div align="justify">
You'll to initialize your printer by specifying a starting toner amount, 
and whether the printer is duplex or not. 
On the Printer class, you want to create two methods, which the calling code should be able to access. 
These methods are:
</div>

* **addToner()**: which takes a tonerAmount argument. 
"tonerAmount" is added to the tonerLevel field. 
The tonerLevel should never exceed 100%, or ever get below 0%. 
If the amount being added makes the level fall outside that range, 
return a "-1" from the method, otherwise return the actual toner level.
* **printPages()**: which should take pages to be printed as the argument. 
I should determine how many sheets of paper will be printed based on the duplex value, 
and return this sheet number from the method. 
The sheet number should also be added to the pagesPrinted variable. 
If it's a duplex printer, print a message that it's a duplex printer.

In this challenge, you'll want to demonstrate proper encapsulation techniques with this class.

## [f. Polymorphism]()

<div align="justify">
Simply stated, polymorphism means many forms. 
Well, how does this apply to code? 
Polymorphism lets us write to call a method, but at runtime, this method's behavior can be different, 
for different objects. 
This means the behavior that occurs, while the program is executing, 
depends on the runtime type of the object. 
And the runtime type might be different from the declared type in the code. 
The declared type has to have some kind of relationship to the runtime type, 
and inheritance is one way to establish this relationship. 
There are other ways, but in this course, we'll talk about how to use inheritance to support polymorphism.
</div>

<div align="justify">
We've given you a taste of this in the inheritance courses, 
but this time we're going to look at polymorphism specifically.
</div>

![image10](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image10.png?raw=true)

<div align="justify">
This time, we're going to look at a polymorphism example, using movies. 
We'll have a base class of Movie, which has the title of the movie. 
And "Movie" will have one method, watchMovie. 
We'll have three subclasses, each a different kind of movie. 
We'll have an Adventure film, a Comedy, and a ScienceFiction movie. 
These are the different categories, so we'll use these as the subclasses. 
All of these will override and implement unique behavior for the watchMovie method.
</div>

<p></p>

<div align="justify">
After creating "Movie" class with its own constructor and one method, watchMovie:
</div>

```java  
public class Movie {
    private String title;
    public Movie(String title) {
        this.title = title;
    }
    public void watchMovie() {
        String instanceType = this.getClass().getSimpleName();
        System.out.println(title + "is a " + instanceType + " film");
    }
}
```

<div align="justify">
Now, here on the first line of this method is something new. 
You've seen the use of the keyword "this" before, which refers to the current instance, 
and now we're calling a method on that called "getClass()".
</div>

<p></p>

<div align="justify">
The method getClass() is on Java linked object, which we've talked about. 
This method returns class type information about the runtime instance on which this method is executed. 
And from that we can get the name of the class using the getSimpleName method.
And here again, we're just using method chaining for convenience. 
This prints out the class which will be moving if we execute this method on a runtime movie object. 
But when we implement the subclasses and run this method, 
the runtime object could be an instance of one of those classes, The Adventure class, for example. 
This will hopefully make more sense when we run this code. 
Next in this code, we print out the title of the movie and the top of the movie runtime instance. 
And that's the movie class.
</div>

Let's go to the main method, and test out the watchMovie method:

```java  
Movie theMovie = new Movie("Star Wars");
theMovie.watchMovie();
```

And running this,

```java  
Star Wars is a Movie film
```

<div align="justify">
We got this output. 
And that's because the runtime instance of the movie variable is the "Movie" class. 
It's the object we created here in the main method. 
And we did a new Movie, which means it's really an instance of a Movie. 
Here we've created the object using the "new Movie" statement and past it the title of "Star Wars." 
And then we assigned our movie instance to a "movie" reference variable, 
and here we just called it the "Movie."
Ok, so no surprises here, I hope.
</div>

<p></p>

<div align="justify">
What we'll do next is add some subclasses of movies that represent different genres 
or classifications of Movies. 
You can think of these as the way a streaming company would group different movies. 
And we'll start with the Adventure movie type. 
We'll add this class to the Movie.java source file, and we won't give it an access modifier, 
meaning it has package or default access. 
But we're going to have it extend Movie:
</div>

```java  
class Adventure extends Movie {
}
```

<div align="justify">
This doesn't compile yet because we need to add a constructor. 
Adding it:
</div>

```java  
public Adventure(String title) {
    super(title);
}
```

<div align="justify">
And we generated constructor which uses movies field, title as a parameter, 
and makes a call to "super," using that argument. 
Now we got the constructor and an Adventure movie will get created with just the title of the movie.
</div>

<p></p>

<div align="justify">
Next, we want to implement or override the watch movie method. 
When you're overriding a method, it's a pretty good idea to start with IntelliJ's override generation tool. 
This ensures that your method signature is right, so let's do that for this class.
</div>

```java  
@Override
public void watchMovie() {
    super.watchMovie();
}
```

<div align="justify">
And that gives us the IntelliJ's default generated code, which includes that Override annotation, 
which we've seen before. 
And this method simply calls the method on the Movie class, which is the super or base class for Adventure.
We want to do that, but we want to include extra functionality that's specific to the adventure class, 
like some of the major plot stages of an Adventure film, for example. 
And we'll just use the printf method as well as the repeat method on String 
to print out the plot stages. 
Three stages on three lines:
</div>

```java  
System.out.printf(".. %s%n".repeat(3), "Pleasant Scene", "Scary Music", "Something Bad Happens");
```

<div align="justify">
Let's talk about this code a minute, because here we're using the format specifiers "%s" and "%n".
The format specifier "%s" is used to replace any String which is not as commonly used as others, 
but it will work well here.
And we've set before that "%n" puts it a new line there. 
Now, this string gets repeated three times with this repeat method before the formatting takes place. 
This means that all these stage plots get printed each on its own line.
</div>

<p></p>

<div align="justify">
Now let's go back to the main method and test the make Movie method on an Adventure object this time. 
And what we can do here is simply replace this "new Movie" statement with "new Adventure" instead. 
We don't have to change the type of "theMovie" variable. 
It can stay as "Movie."
</div>

```java  
Movie theMovie = new Adventure("Star Wars");
theMovie.watchMovie();
```

<div align="justify">
And this is because adventure is really a type of Movie, a subclass. 
And inheritance lets us say, "Adventure is a Movie, and it's ok; we can do this, 
we can assign an Adventure object to a Movie variable."
This code compiles and we can run it.
</div>

```java  
Star Wars is a Adventure film
.. Pleasant Scene
.. Scary Music
.. Something Bad Happens
```

<div align="justify">
And look what happened. 
The code ran, the watchMovie method on the Adventure class. 
That's because at runtime, the method that gets run 
is determined by the Java Virtual machine (JVM) based on the runtime object and not this variable type.
And that's our first test of polymorphism in this code. 
We've declared a variable of type Movie and assigned it an object that's really an Adventure type of movie. 
And when we called watchMovie on that, the behavior was the Adventure movies behavior. 
It wasn't just the base class behavior. 
Now, the method that's on the Adventure class, first calls Movie's method, 
which is why we see that first statement, "Star Wars is an Adventure film."
But this time, we get in the text that "Adventure", 
and this is the actual type of object at runtime as "instanceType." 
And then we have the plot stages for an Adventure Film, pleasant scene, scary music, 
and something bad happens. 
That's a really simple example of polymorphism.
</div>

<p></p>

<div align="justify">
Getting back to the movie.java source file, and creating the "comedy" subclass next:
</div>

```java  
class Comedy extends Movie {
    public Comedy(String title) {
        super(title);
    }
    @Override
    public void watchMovie() {
        super.watchMovie();
        System.out.printf(".. %s%n".repeat(3),
                "Something funny happens",
                "Something even funnier happens",
                "Happy Ending");
    }
}
```

<div align="justify">
Ok, we have two of our movie subclasses built, but let's do the last one, 
which we're calling Science-Fiction.
</div>

```java  
class ScienceFiction extends Movie {
    public ScienceFiction(String title) {
        super(title);
    }
    @Override
    public void watchMovie() {
        super.watchMovie();
        System.out.printf(".. %s%n".repeat(3),
                "Bad Alien do Bad Stuff",
                "Space Guys Chase Aliens",
                "Planet Blows up");
    }
}
```

<div align="justify"> 
And that's the ScienceFiction class, which means now we've built the movie class 
and oll of its subclasses.
</div>

<p></p>

<div align="justify">
Up to now, we've only assigned an adventure movie instance to a Movie variable and saw that when it ran, 
Java figured it out which method to run, not on the compile time code, but on the runtime instance's method. 
But this time, we'll create a method on the Movie class, 
that the calling code can execute, that will return a movie instance for us. 
We'll make this method public and static, which means anybody can call this method to get a movie instance, 
based on the parameter type being passed in a title. 
Let's type this method our partially.
</div>

```java  
public static Movie getMovie (String type, String title) {
    return switch (type.toUpperCase().charAt(0)) {
        default -> new Movie(title);
    };
}
```

<div align="justify">
In this code, the parameters of this method are the type of the movie, and the title. 
And we're going to return an instance of the Movie class, or a subclass of a Movie. 
And this gets returned by the switch expression. 
But what are we really doing here with the switch expression? 
Well, we're taking whatever was passed to us, making it uppercase, then just getting the first letter. 
In our case, each of our subclasses has a unique letter for its class name, 
so we can use that to figure out the right kind of movie to create.
And just for now, we're returning a generic movie instance. 
Now, let's go back to the main method, and test that out. 
We'll call the static method on Movie, to get an Adventure movie.
</div>

```java  
Movie theSecondMovie = Movie.getMovie("Adventure", "Star Wars");
theSecondMovie.watchMovie();
```

Now, notice if we run this,

```java  
Star Wars is a Movie film
```

<div align="justify">
It works, but we're still not really getting "an Adventure" movie. 
That's because we haven't really completed the getMovie method. 
Going back to the Movie Class, we want to add the code to run return the different subclasses, 
based on the type argument:
</div>

```java  
public static Movie getMovie (String type, String title) {
    return switch (type.toUpperCase().charAt(0)) {
        case 'A' -> new Adventure(title);
        case 'C' -> new Comedy(title);
        case 'S' -> new ScienceFiction(title);
        default -> new Movie(title);
    };
}
```

<div align="justify">
Here, our switch expression is really evaluating a char, a single character. 
We get this character from the String method, charAt() that we're using in the switch expression. 
That's going to give us the first letter of the type. 
And if it's an "A" we'll return a new Adventure instance, 
if it's "C" we'll return a Comedy, and if it's "S" that means we want 
to create a new Science-Fiction Movie. 
If it's not one of those, then we'll just return the base class, an instance of Movie. 
By providing this method, the code in the main method (the calling code) 
doesn't really need to know anything about any of Movie's subclasses. 
It can just pass in the type, and get a different type of Movie subclasses. 
This kind of method that returns a new instance object is known as a factory method, 
in software programming design patters. 
Factory methods give us a way to get an object, 
without having to know the details of how to create a new one, 
or specify the exact class we want. 
Actually, if we run this code now, from the main method:
</div>

```java  
Star Wars is a Adventure film
.. Pleasant Scene
.. Scary Music
.. Something Bad Happens
```

<div align="justify">
We see that we really do get an Adventure object back, 
because you can see Adventure film there on the first line. 
The getMovie method returned an instance which we maybe didn't know what it was, 
and maybe we don't really care. 
But because the runtime object was an instance of the Adventure class, 
the method on that class was executed. 
And now we can see the output for an Adventure instance, with the title Star-Wars, 
that it's an Adventure Film. 
And then we also get the 3 plot stages for the Adventure Class.
</div>

<p></p>

<div align="justify">
Ok, next, we'll change the main method, passing ScienceFiction, 
because really Star-Wars is more of a ScienceFiction Movie. 
And really, we don't want to type in the full class name, 
we can just put in Science, or even "S" if we wanted.
</div>

```java  
Movie theSecondMovie = Movie.getMovie("S", "Star Wars");
theSecondMovie.watchMovie();
```

And if we run this:

```java  
Star Wars is a ScienceFiction film
.. Bad Alien do Bad Stuff
.. Space Guys Chase Aliens
.. Planet Blows up
```

<div align="justify">
We get different type names and different plot stages. 
With this method, the calling code doesn't need to know about each subclass, 
or how to create different instances of movies. 
We can just call this method, passing the type and name, and the right object type is instantiated, 
and returned, but it's assigned to a variable with the Movie type, 
so this code will work for any Movie, or any of its subclasses, 
including subclasses that haven't even been created yet. 
This keeps all the information about the Movie, and its subclasses, 
in the control of the Movie class, and simplifies the work that needs to be done by the calling code. 
That sounds like a good encapsulation technique, doesn't it?
</div>

<p></p>

<div align="justify">
Ok, going back to our main method, we'll use polymorphism to watch a variety of movies. 
This time, we'll make the code interactive, using the Scanner class we've seen before. 
We'll let the user enter the type of movie, and then the title of the movie they want to watch. 
First, we'll comment out the first four lines of code, then add the code for a new Scanner.
</div>

```java  
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
    }
}
```

<div align="justify">
In the Exception course, I showed you how to manually add import lines. 
Because we still have "Auto Imports enabled"
you saw how IntelliJ added the import for us automatically. 
Pretty nice, right? 
Essentially, IntelliJ adds and manages the imports for you 
as much as it can with the Auto import option turned on. 
So that's "Auto-import". 
These are the types of little automations that collectively, 
make you a much more productive programmer.
</div>

<p></p>

<div align="justify">
Ok, next, we'll create a loop, so we'll keep getting information from the user 
until they quit out of the loop.
</div>

```java  
while (true) {
    System.out.println("Enter Type (A for Adventure, C for Comedy, S for Science Fiction, or Q to quit): ");
    String type = s.nextLine();
}
```

<div align="justify">
In this code, we're using a while loop, with a condition that's always true. 
That's really an infinite loop, and you definitely don't want to run this code yet. 
But first, we'll prompt the user for the type of Movie, enter A for Adventure, 
C for Comedy, Q for quitting. 
After that, we'll get the user's response, using nextLine method on Scanner. 
We want to get all the data they entered, up do and including the new line. 
The nextLine method gets the type of the movie they want to watch, 
but we're not doing anything with it yet, except assigning it to type. 
If we ran this now, we'd be stuck in an infinite loop. 
We need to add the code to break out of the loop next.
</div>

```java  
if ("Qq".contains(type)) {
    break;
}
```

<div align="justify">
Ok, what is this code doing? 
This "if" statement uses the contains method on a String, 
which we've covered briefly in the String courses. 
"Qq," here we have a String literal, that has an uppercase and lowercase Q in it, 
and we're using contains to test what the user entered. 
This means, if the user enters a single character, either an uppercase or lowercase Q, 
then the code will break out of the loop. 
The contains method will determine if the String literal, "Qq" contains the single letter q, 
and returns true. 
Now that we can get out of the loop, we next want the code to get the title of the movie. 
And after that, we'll use the factory method on the Movie class, to get the right kind of instance:
</div>

```java  
System.out.print("Enter Movie Title: ");
String title = s.nextLine();
Movie movie = Movie.getMovie(type, title);
movie.watchMovie();
```

<div align="justify">
Here again, we prompt the user to enter the movie title, 
and we'll read that in, using the nextLine method on scanner. 
After that, we can call Movie.getMovie to get an instance of a movie, 
passing the type, and title that the user entered. 
And regardless of what comes back from this method, 
we're going to assign it to a variable with a type that we've said is Movie. 
So as long as whatever is being returned from the getMovie method, is a Movie, 
or a subclass of Movie, we can assign it to a Movie variable. 
And because it's a Movie variable, we can call any of Movie's methods on it. 
And that method is watchMovie.
</div>

And running this code now:

```java  
Enter Type (A for Adventure, C for Comedy, S for Science Fiction, or Q to quit):
S
Enter Movie Title: Star-Wars
Star-Wars is a ScienceFiction film 
.. Bad Alien do Bad Stuff
.. Space Guys Chase Aliens
.. Planet Blows up
```

<div align="justify">
We get prompted to Enter the Type, so let's enter an S, for ScienceFiction, and press enter. 
And now we'll put in the title, and I'll type in, Star-Wars, 
but you can enter anything you want there.
And look at the output for the Star-Wars ScienceFiction movie. 
The code called the method watchMovie() using a Movie reference variable. 
But at runtime, the Movie wasn't really a Movie, it was an instance of the subclass, 
the ScienceFiction class. 
And it was the method watchMovie(), that's actually declared on the ScienceFiction class, 
that really got executed. 
This is polymorphism in action. 
Our compiled code, in the main method of the Main class, 
never knew anything about the ScienceFiction class, or any of the other subclasses. 
But at runtime, we got an object of type ScienceFiction back from the factory method. 
And when the method watchMovie() was called on that, it called watchMovie on the ScienceFiction class. 
I hope you agree with me that that's pretty neat. 
And remember the watchMovie method on ScienceFiction, first called the method on Movie, 
and we can see that first output statement, "Star-Wars is a ScienceFiction film." 
This got printed out because we called super.watchMovie(), 
when we overrode that method, on the ScienceFiction class.
</div>

<p></p>

<div align="justify">
This is the ability to execute different behavior for different types, 
which are determined at runtime. 
And yet we did it with just two statements, in the main method, as shown here:
</div>

```java  
Movie movie = Movie.getMovie(type, title);
movie.watchMovie();
```

<div align="justify">
Polymorphism enables you to write generic code, based on the base class, or a parent class. 
And this code, in the main method, is extendable, meaning it doesn't have to change, 
as new subclasses become available. 
This code can handle any instances that are a Movie, or a subclass of movie, 
that are returned from the factory method, even ones that don't exist yet.
</div>

## [g. Casting with Classes, Using Object and var References]()

<div align="justify">
We showed you an example of polymorphism in action. 
There were two lines in that code, they're really important for you to understand. 
These are the lines:
</div>

```java  
Movie movie = Movie.getMovie("A", "Jaws");
movie.watchMovie();
```

<div align="justify">
Now let's talk about these two lines of code, because there are several things happening here. 
First, we're creating a variable with the Movie type, and we've called it "movie." 
We then assigned the result of this static method, Movie.getMovie, to that variable.
We know we could've done this several other ways. 
Let's use that method and try to assign it to an Adventure variable, 
since we know that we'll really get an Adventure Movie, if we call it this way.
</div>

```java  
Adventure jaws = Movie.getMovie("A", "Jaws");
```

<div align="justify">
Now, this code doesn't compile. 
IntelliJ tells us that the "Required Type is Adventure," and "Provided is Movie."
Why is this a problem? 
First of all, the compiler isn't going to run the code to figure out what will really happen.
It has to be satisfied with making assumptions about the code, based on how we write the code. 
In this case, we declared that the method, getMovie, is going to return a Movie Class.
We didn't say it was going to return an instance of the Adventure Class. 
The compiler asks, can every kind of Movie (which is the return type of this method),
be called an Adventure, meaning, can every kind of Movie be assigned to an Adventure variable? 
And here, the answer is "no". 
The Adventure reference, jaws, would not be able to handle a Comedy movie if that got returned, 
for example. 
That's because we can't say a Comedy is an Adventure. 
But we absolutely know that when we pass the letter A as the type,
that we'll get an Adventure movie back. 
But the compiler can't figure that out without executing code, and it's not going to do that. 
Now, we can give the compiler more detailed instructions to get around this issue. 
One way to do is casting. 
Like casting with primitives, we can cast instances, so let's do that here.
</div>

```java  
Adventure jaws = (Adventure) Movie.getMovie("A", "Jaws");
jaws.watchMovie();
```

<div align="justify">
We'll cast the result of the Movie.getMovie, to an Adventure type. 
And we'll also add the call to watchMovie here.
Ok, now this code compiles, and we can give it a go, and run it:
</div>

```java  
Jaws is a Adventure film 
.. Pleasant Scene
.. Scary Music
.. Something Bad Happens
Jaws is a Adventure film
.. Pleasant Scene
.. Scary Music
.. Something Bad Happens
```

<div align="justify">
And we can see that it runs just like it did, in the two lines above these. 
But what happens if we make a mistake, and we pass a C as the type?
</div>

```java  
Adventure jaws = (Adventure) Movie.getMovie("C", "Jaws");
jaws.watchMovie();
```

<div align="justify">
We know that doing that should give us a comedy movie. 
But let's not change anything else, and see what happens.
We've told the compiler, with this cast that we're smarter than it, 
and to just leave this line alone. 
So this code compiles. 
But if we run it?
</div>

```java  
Exception in thread "main" java.lang.ClassCastException: class Comedy cannot be cast to class Adventure 
        (Comedy and Adventure are in unnamed module of loader 'app') at Main.main
```

<div align="justify">
We get a special kind of exception, a ClassCastException. 
And the message is pretty informative, that we got a Comedy object when an Adventure object was expected. 
This is a bad situation to have your code compile, but then get exceptions at runtime. 
Ok, maybe you're asking, when can you assign an object of one type, to a reference, 
with a different type? 
Well, first of all, you can assign any object to a reference that is of type Object. 
We'll try that next. 
First, we'll revert that last change.
</div>

```java  
Adventure jaws = (Adventure) Movie.getMovie("A", "Jaws");
jaws.watchMovie();

Object comedy = Movie.getMovie("C", "Airplane");
comedy.watchMovie();
comedy.watchComedy();
```

<div align="justify">
And then we'll create a comedy instance, but assign it to an Object reference. 
Here, now we've created a variable called comedy, but we've said its type is Object. 
And this code doesn't compile, but not on the first line where we're doing the assignment, 
but on the second statement. 
Can you figure out why? 
The compiler won't use the method return type to figure out what comedy really is 
after you make this assignment. 
It just assumes it's an Object, and this variable only has access to Object's functionality. 
This is because, at any time in the code, an instance of Object itself could be assigned to this variable. 
And the code has to work for whatever object gets assigned to this variable.
This means that when you try to call a Movie method on this object reference, you'll get an error. 
This is because the compiler can't locate that method, watchMovie, on the Object class. 
Let's pause here a minute to add a method on each of the subclasses, 
and we'll make each method unique to the class.
</div>

<p></p>

<div align="justify">
Going back to the Adventure class, we'll add a simple method, that's only on that class.
</div>

```java  
public void watchAdventure() {
    System.out.println("Watching an Adventure!");
}
```

<div align="justify">
And let's copy that method and paste it in the Comedy class. 
And name it for Comedy. 
Same, we do it for the ScienceFiction Class as well.
</div>

<p></p>

<div align="justify">
And if we try to run watchComedy on that Object variable, 
we have the same problem that we had with watchMovie. 
It doesn't see that method watchComedy on the Object class, 
and it's not going to compile. 
Let's revert that for the moment. 
Now, you could assign every instance to an Object reference like this, 
but you wouldn't be able to do much with them without casting them to other typed references. 
In other words, to run watchMovie on comedy (which has the type Object), 
we'd have to cast it to a Movie. 
And we can do that. 
We'll cast the object reference to a movie reference, 
and then we'll execute watchMovie on that new reference variable.
</div>

```java  
Movie comedyMovie = (Movie) comedy;
comedyMovie.watchComedy();
```

<div align="justify">
What I want you to see by this is that using references that are too generic, 
like Object, means you'll be doing a lot of casting. 
But even now, since we cast to a Movie, check out what happens, 
if I want to execute one of the methods we just added, watchComedy. 
This doesn't compile either. 
For the same reason we couldn't execute watchMovie on an Object reference, 
we can't run watchComedy on just a Movie reference. 
The compiler will only look at the reference type to determine if that method is on that type, 
and watchComedy is not on the Movie class. 
In this case, we'd need to actually cast to a more specific type, Comedy. 
And let's do that.
</div>

```java  
Comedy comedyMovie = (Comedy) comedy;
comedyMovie.watchComedy();
```

And now all of that works, so that's good. For good measure, let's try one other thing.

```java  
var airplane = Movie.getMovie("C", "Airplane");
airplane.watchMovie();
```

<div align="justify">
This code compiles, but here is something new. 
We haven't used this before, you're probably asking, what is this "var" word means? 
Well, "var" is a special contextual keyword in Java, that lets our code take advantage of Local
Variable Type Inference. 
By using "var" as the type, we're telling Java to figure out the compile-time type for us.
Since the Movie class was declared as the return type of the static method getMovie, 
then Java can infer that the type of this variable, Airplane, should be a Movie. 
You can see that in the hints if you've configured IntelliJ to show them. 
Now why didn't it infer that it was a Comedy Class? 
Nothing about the signature of the method indicated that a Comedy instance might be returned 
from the method. 
Only that a Movie would be.
</div>

<p></p>

<div align="justify">
Ok, let's try another example of using type inference, this time by just assigning a new instance:
</div>

```java  
var plane = new Comedy("Airplane");
plane.watchComedy();
```

<div align="justify">
In this case, the compiler had an easier job to infer the type, 
because we're simply assigning a new instance of Comedy to this variable, plane. 
But you can see now that using this plane variable, we can execute methods, specific to Comedy, 
without compile time errors. 
One of the benefits of Local Variable Type Inference(LVTI) is to help the 
readability of the code, and to reduce boilerplate code.
</div>

* It can't be used in field declarations on a class.
* It can't be used in method signatures, either as a parameter type or a return type.
* It can't be used without an assignment, because the type can't be inferred in that case.
* It can't be assigned a null literal again because a type can't be inferred in that case.

<div align="justify">
Are you still confused about the difference between run-time and compile-time typing? 
You can think of the compile time type as the declared type. 
This type is declared either as a variable reference, or a method return type, or a method parameter, 
for example. 
In the case of LVTI, we don't declare a type for the compiled reference type, it gets inferred, 
but the bytecode is the same, as if we had declared it. 
In many cases, the compile time type is the declared type to the left of the assignment operator. 
What is returned on the right side of the assignment operator, 
from whatever expression or method is executed, sometimes can only be determined at runtime, 
when the code is executed conditionally, through the statements in the code. 
You can assign a runtime instance to a different compile time type, only if certain rules are followed. 
In this course, up to now, we've looked at only one rule that applies, 
and that's the inheritance rule. 
We can assign an instance to a variable of the same type, or a parent type, 
including java.lang.Object, the ultimate base class.
</div>

<p></p>

<div align="justify">
Why are run-time types different from compile-time types? 
Because of polymorphism. 
Polymorphism lets us write code once, in a more generic fashion, 
like the code we started this lecture with. 
We saw that those two lines of code, using a single compile time type of Movie, 
actually supported four different runtime types. 
Each type was able to execute behavior unique to the class.
</div>

## [h. Testing the run-time Type]()

<div align="justify">
We've said that you can always assign an expression to a type, without casting, 
if you're always assigning it to a parent class, or a base class type. 
In this course, we're going to show you how to test what the runtime type of object really is. 
And how do we test what the runtime type of variable really is at runtime, 
if the declared type is something else? 
We can test to see what type the actual object is, at runtime, in several different ways. 
First, we can use an if statement to see what the class name of the object, 
coming back from that method is, when this code is running. 
Getting back to the main method, we'll add another call to the Movie.getMovie method. 
We'll assign that to an Object reference, called unknownObject.
</div>

```java  
Object unkownObject = Movie.getMovie("S", "Star Wars");
if (unkownObject.getClass().getSimpleName() == "Comedy") {
    Comedy c = (Comedy) unkownObject;
    c.watchComedy();
} else if (unkownObject instanceof Adventure) {
    ((Adventure) unkownObject).watchAdventure();
} else if (unkownObject instanceof ScienceFiction syfy) {
    syfy.watchScienceFiction();
}
```

<div align="justify">
Here, in this if statement, we're using a method called getClass. 
We've used this getClass method before, in the Movie class, watchMovie method. 
But in that case, we used it with the keyword this, whereas here, we're simply calling it,
on the local variable reference, unknownObject. 
This method is available to any instance because it's a method on Object. 
And getSimpleName is a method that returns the class name of our instance here. 
This means we're testing if the object, coming back from that factory method, 
has a class name that's Comedy. 
And if it does, we can cast the object to Comedy, and assign it to a Comedy variable. 
Then we can call any method in Comedy. 
The reason to cast to a Comedy class here is, we want to execute the method 
that's specific to Comedy, watchComedy. 
Without casting to a Comedy class, we couldn't execute that method. 
And you'll remember that we did not have to cast airplane to Comedy class, on 
"var airplane = Movie.getMovie("C", "Airplane");", to run watchMovie, 
and what I mean is, running the Comedy class's own version of watchMovie. 
This is because of polymorphism, which works in this case, through the use of overridden methods. 
Without polymorphism, you'd be in this world of having to test, at runtime, 
what your object really is every time, to execute code. 
This code shows you one way to test for the runtime type, but it's not the best way.
</div>

<p></p>

<div align="justify">
Let's look at another way, testing if the object coming back might be an Adventure type.
We'll insert a couple of statements, before that last closing bracket, 
for the else-if statement: instanceOf. 
Here is something new, and this is the instanceof operator. 
The "instanceof" operator, lets you test the type of object or instance. 
The reference variable you are testing is the left operand. 
The type you are testing for is the right operand. 
It's important to see that Adventure is not in quotes, 
meaning we're not testing the type name, but the actual type. 
This operator returns true if unknownObject is an instance of Adventure. 
In our code here, we're testing if unknownObject is really an Adventure object, 
and then, if it is, if that's true, we want to cast unknownObject to Adventure, 
and call watchAdventure, a method only on the Adventure class. 
And this next statement is different from anything I've shown you before, 
but perfectly valid. 
This set of outer parentheses is the result of the Adventure type, "(Adventure) unknownObject" 
and we can chain a method directly on that, as we do here. 
In other words, we don't have to assign the result of the cast to a local variable.
</div>

<p></p>

<div align="justify">
Ok, now lastly, let's look at one more way to do this: 
""else if (unknownObject "instanceof" ScienceFiction "syfy")". 
Here, we can see we're using a slightly different version of the "instanceof" operator. 
This is called pattern matching support, for the "instanceof" operator. 
If the JVM can identify that the object matches the type, 
it can extract data from the object, without casting. 
For this operator, the object can be assigned to a binding variable, which here is called "syfy". 
The variable "syfy" (if the "instanceof" method returns true) 
is already typed as a ScienceFiction variable. 
You can see in our code, "syfy.watchScienceFiction();", 
we don't have to create the variable in the block statement, and we don't have to cast it. 
Let's run this code through a couple of tests. 
First, we'll run this code as it is. 
We're interested in the last statement of the output. 
And that gives us the last output statement, "Watching an Adventure!". 
Now, let's test a ScienceFiction movie, making the type an "S" and the title Star-Wars. 
And that gives us the output: 
"Watching a Science Fiction Thriller!". 
Ok, those are a few ways to test the runtime type of object in our code. 
You can see that this newest feature in Java makes a job a lot easier.
</div>

## [i. Polymorphism Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/Course09_PolymorphismChallenge/README.md#polymorphism-challenge)

<div align="justify">
What we want to do in this challenge is have our runtime code execute 
different behavior for different objects. 
Let's talk about what I want you to do in this challenge.
</div>

![image11](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image11.png?raw=true)

<div align="justify">
This diagram shows a base class, Car, with one field, description, 
and three methods, startEngine(), drive(), and runEngine(). 
The first two methods should be declared as public. 
The method "runEngine" however, is protected, 
and it will only get called from the drive method in Car. 
And we've given three types of subclasses, or three types of cars that you might find on the road. 
We have the GasPoweredCar, the ElectricCar, and the HybridCar. 
You can imagine that these three subclasses might have different ways to start their engine, 
or drive, depending on their engine type. 
And each of these classes might have different variables or fields that might be used in those methods. 
We show you a few of the fields here, but you should try to be creative in your own design. 
Maybe you might be interested in the top speed of the cars, or how fast they can accelerate, 
or some other way you want to compare these cars' performances. 
It's your job to create this class structure in Java, and override some, or maybe all, 
of these methods appropriately.
And you'll write code in a Main class and main method, 
that creates an instance of each of these classes, and that executes 
different behavior for each instance. 
At least one method should print the type of the runtime object.
</div>

## [j. Bill's Burger(OOP Master) Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/Course10_OOPMasterChallenge/README.md#bills-burgeroop-master-challenge)

<div align="justify">
In this challenge, Bill runs a fast food hamburger restaurant, and sells hamburger meals. 
His meal orders are composed of three items, the hamburger, the drink, and the side item. 
Your application lets Bill select the type of burgers, and some of the additional items, 
or extras, that can be added to the burgers, as well as the actual pricing.
</div>

You need a meal order:

* This should be composed of exactly one burger, one drink, and one side item.
* The most common meal order should be created without any args, like a regular burger, 
a small coke, and fries, for example.
* You should be able to create other meal orders by specifying different burgers, drinks, and side items.

You need a drink and side item:

* The drink should have at least a type, size and price, 
and the price of the drink should change for each size.
* The side item needs at least a type and price.

You need burgers:

* Every hamburger should have a burger type, a base price, and up to a maximum of three extra toppings.
* The constructor should include only the burger type and price.
* Extra Toppings on a burger need to be added somehow, and priced according to their type.

The deluxe burger bonus:

* Create a deluxe burger meal, with a deluxe burger, that has a set price, 
so that any additional toppings do not change the price.
* The deluxe burger should have room for an additional two toppings.

Your main method should have code to do the following:

* Create a default meal that uses the no args constructor.
* Create a meal with a burger, and the drink and side item of your choice, with up to three extra toppings.
* Create a meal with a deluxe burger, where all items, drink, side item and toppings up to 5 extra toppings, 
are included in the burger price.

For each meal order, you'll want to perform these functions:

* Add some additional toppings to the burger.
* Change the size of the drink.
* Print the itemized list. 
This should include the price of the burger, any extra toppings, the drink price based on size, 
and the side item price.
* Print the total due amount for the meal.

## [k. Organizing Java Classes, Packages and Import Statements]()

<div align="justify">
Up until this point in the class, we haven't created a lot of classes, 
so we haven't had to think much about organizing those classes. 
As the course progresses, we're going to be using more and more of Java's libraries, 
and our applications are going to get more complex. 
This feels like a good time to talk about the "package" and "import" statements, 
in more detail. 
We've talked briefly about "import" statements when we used the "Scanner" class, 
and we mentioned packages when we talked about access modifiers.
In this course, I want to focus on what a package is, 
why we'll be switching to using it from this period forward, 
and how to access classes in different packages.
</div>

As per the Oracle Java Documentation:

* A package is a namespace that organizes a set of related types.
* In general, a package corresponds to a folder or directory on the operating system, 
but this isn't a requirement.
* When using an IDE, like IntelliJ, we don't have to worry about how packages 
and classes are stored on the file system.

<div align="justify">
The package structure is hierarchical, meaning you group types in a tree fashion. 
You can use any legal Java identifier for your package names, but common practice 
has package names as all lower cases. 
The period separates the hierarchical level of the package. 
By now, you're familiar with two of Java's packages, java.lang, and java.util. 
</div>

![image14](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image14.png?raw=true)

<div align="justify">
You may remember, when we used the Scanner class or the Random class, 
we were required to use an "import" statement. 
The "import" statement has to be declared before any class or type declarations, 
but after any "package" statement. 
In this code, we don't have a "package" statement, so the "import" statement must be 
the first statement in the code. 
There is no limit to the number of "import" statements you can have. 
Alternately, we could use a wildcard, "&ast;", with the asterisk character, 
with the "import" statement. 
We're telling Java to import all classes from that package with the use of "*".
</div>

![image15](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image15.png?raw=true)

<div align="justify">
Packages let us re-use common class names across different libraries or applications, 
and provide a way to identify the correct class, either with an import statement, 
or a qualifying name. 
For example, you might have a package for utility classes that can provide common functionality 
for all of your classes to access. 
Packages let us or organize our classes by functionality, or relationships. 
Packages also let us encapsulate our classes, from classes in other packages. 
So you might have a package of tightly coupled classes that should only be accessed by each other, 
but not by the outside world, as an example.
</div>

<p></p>

<div align="justify">
What would a package name look like? 
We've seen that Java starts their package names with java, in some of the examples we've looked at. 
However, it is common practice to use the reverse domain name 
to start your own package naming conventions. 
If your company is "abcompany.com," for example, your package prefixes would be "com.abccompany."
For the rest of the course, I'll be using dev.lpa, 
which is the reverse domain of my Learn Programming Academy development company. 
The package name hierarchy is separated by periods. 
</div>

![image16](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image16.png?raw=true)

<div align="justify">
The package statement needs to be the first statement in the code except comments. 
The package statement comes before any import statements. 
There can be only one package statement, because a class or type can only be in a single package.
</div>

<p></p>

<div align="justify">
A class's fully qualified class name (FQCN) consists of the package name and the class name. 
It's unlikely a class, with its fully qualified name, will have a naming conflict, 
with a Main class in another package. 
As an example, the fully qualified class name of the Scanner class in a code is java.util.Scanner. 
You can use the fully qualified class name instead of the import statement just as below:
</div>

```java  
java.util.Scanner scanner = new java.util.Scanner(System.in);
```

<div align="justify">
You can imagine this could get tedious if you have to use the type often in your code. 
Later in the course, we'll talk about using a combination of the import statement,
and the fully qualified class name, to resolve conflicts.
</div>

![image17](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/images/image17.png?raw=true)

<div align="justify">
For your applications, you should always specify a package statement 
and avoid using the default or unnamed package. 
Although that's all we've been using up until now, 
it has some disadvantages when you work in a true development environment. 
The main disadvantage is you can't import types from the default package into other classes, 
outside the default package. 
In other words, you can't qualify the name 
if it's in the default package, and you can't import classes from the default package.
</div>


