# [Section-4: Object-Oriented Programming (PART-II)]()

It's now time to look at the remaining three major components of OOP. 
These are:

* Composition 
* Polymorphism 
* Encapsulation

By the end of this section, you should have a solid overview of what these concepts are, 
and also how to apply them to your programs.

## [a. Composition (Part-I)]()

As a refresher, I want to review another inheritance example before we move into composition.
Let's say we're interested in building a computer, and we want to assemble it.
All the parts are manufacturer's products, which we have to buy, 
and assemble, to sell our final product, the personal computer.
Next, we'll look at a class diagram of some computer products, or computer parts.

![image01]()


In this instance, we have a base class called "Product."
All of our computer parts are going to inherit from Product.
All our parts will then have the same set of attributes, a manufacturer, and model, 
and dimensions, the width, height, and depth in other words. 
All of these items are products are particular type of Product. 
Personal Computer, Monitor, Motherboard, and ComputerCase, are but a few parts, or products; 
you can think of that would be sold by a computer distributor.

Ok, let's build this. Creating a new class named "Product," 
we start adding the attributes and a constructor, 
which only includes the model and manufacturer for simplicity:

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

Ok, we now have our base class, and next let's create a couple of subclasses. 
Remember, only one class can be public, so we'll omit the access modifier for these classes.
We'll start with Monitor. 
For Monitor to be a subclass of Product, 
we have to add extends Product by generating the constructor that has got 2 attributes:

```java  
class Monitor extends Product {
    public Monitor(String model, String manufacturer) {
        super(model, manufacturer);
    }
}
```

Ok, that's our first subclass. 
Let's create our second class just by copying and pasting the same class 
but with changing the method name, and the constructor:

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

Ok, so our three classes all extend Product.
We can say now, that Monitor has got a relationship with Product, 
meaning that a Monitor can be said to be a type of Product.
And that's the relationship between the two classes.
And we can quite correctly say that all three of these parts, 
Monitor, Motherboard, and ComputerCase are all types of Product.
And that's essentially what inheritance is.

Inheritance defines an "is A" relationship.
Composition defines a "HAS A" relationship.
Next, let's look at how we'd use composition to create a personal computer.

![image02]()

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

![image03]()

This diagram shows 3 classes that will make up the personal computer.
We'll have size and resolution for the monitor, and we'll want a method called drawPixelAt, 
so the monitor has behavior to draw.
For the computerCase, we'll have a powerSupply, and a method pressPowerButton().
And for the Motherboard, we'll have slots for ram and cards, as well as a bios attribute, 
and a method called loadProgram().

Getting back to the code, let's add these members to our parts.
We'll start with the motherboard, and add the three attributes first, 
and next we want to generate another constructor, 
which initializes these three attributes but also calls super constructor:

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

That's our Motherboard class, pretty straightforward as you can see, 
with nothing particularly complicated there.
Now, let's move onto our Monitor class, and add those fields, size and resolution:

```java  
private int size;
private String resolution;
public ComputerCase(String model, String manufacturer, int size, String resolution) {
    super(model, manufacturer);
    this.size = size;
    this.resolution = resolution;
}
```

Ok, so now we have the fields and the constructors.
What we're going to do here next is, create a public method called drawPixelAt, 
and it's going to have an x and y location, and a color. 
We want to use String.format, which we learned to do in the last section.
And we'll just print a simple message out:

```java  
public void drawPixelAt(int x, int y, String color) {
    System.out.println(String.format(
            "Drawing pixel at %d,%d in color %s ", x, y, color));
}
```

And that completes the Monitor class.
Finally, let's finish Computer Case, which we've said has a power supply and one method, 
pressPowerButton, so we'll add the powerSupply field:

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
 Let's build this Personal Computer class now, using composition, which means we'll add 3 attributes:

```java  
public class PersonalComputer extends Product{
    private ComputerCase computerCase;
    private Monitor monitor;
    private Motherboard motherboard;
}
```

And you can see the obvious advantage here, because if you're using the extends option to inherit, 
Java only lets you inherit from one class at a time.
You could see that we'd run into difficulties and limitations quite quickly 
if our only tool was inheritance. 

![image04]()

In this case, what we've said is, the Personal computer consists of these three other classes,
namely the computer case, the monitor, and the motherboard.
Before we do anything else, we should add a constructor. 
You can see IntelliJ isn't happy with us because we haven't done that yet.

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

Ok, that's our Personal Computer. 
We've described what we want to assemble.
In the next course, we'll actually assemble the parts, 
and we'll look at how to access the functionality of the parts
through the personal computer container.

## [b. Composition (Part-II)]()

Now, we need to assemble the Personal Computer.
Let's create some objects in the Main class firstly, an object of type ComputerCase.
We'll create a Dell 2208 case, and it will have a 240 power supply:

```java  
ComputerCase theCase = new ComputerCase("2208", "Dell", "240");
```

That's our definition for a computer case now.
Remembering that when we create the personal computer, which we're going to be doing shortly, 
it needs this reference.
PCs generally can't be put together without a case, so what we're doing here is creating that case.
Next, we need to create a monitor, this time we'll say it's an Acer 27inch beast, 
so we'll pass in the size, and the resolution of 2540 by 1440.

```java  
Monitor theMonitor = new Monitor("27inch Beast", "Acer", 27, "2540 x 1440");
```

We've now got a case and a monitor; that ultimately are going to be used to create our PC.
Of course, we now need a motherboard. 
And we'll make this an Asus, BJ-200 model. 
We also need the Number of RAM slots, which we'll say is 4. 
And the number of card slots, let's say there's 6. 
And for the bios, let's say it's version v2.44.

```java  
Motherboard motherboard = new Motherboard("BJ-200", "Asus", 4,6,"v2.44");
```

That gives us our motherboard.
And now that we've got all our parts, we can put our Personal Computer together.
We've created instances of a Monitor class, a Motherboard class, and a Computer Case class, 
and it's time to build this, and see what we can do with it.

The PC will have the same manufacturer and model as the computer case, so 2208 as the model, 
then Dell as the manufacturer, and then we'll pass the other variables to the PC.

```java  
PersonalComputer thePC = new PersonalComputer("2208", "Dell", theCase, theMonitor, theMotherboard);
```

And you can see here, we've created the PC object by passing those 3 other objects to it, 
as well as the model and manufacturer.

With inheritance, we were able to use a method from the base class.
But how to we get access to the composite object's methods? 
In other words, how do we make it do anything? 
Well, we can do this by telling one of its parts to do something.
For example, if we want to draw something using the monitor, how do we access this method?
We have the method drawPixelAt on monitor, but how do we access that? 
Well, we can get the PC's monitor object, using the getter method, 
and then call drawPixelAt on that object, so let's do that:

```java  
thePC.getMonitor().drawPixelAt(10, 10, "red");
```

That's one way of executing functionality, by using the getter method from PC, 
to get the instance of the monitor class, that the Pc contains.
In this code, from that return object, we're chaining the call to the method drawPixelAt.
And that's invoking that method for us.
Likewise, we could call the method loadProgram on the motherboard, 
passing it Windows OS, for example, as the program we want to run.

```java  
thePC.getMotherboard().loadProgram("Windows OS");
```

We're doing the same thing here, we're getting the Motherboard object that's been created, 
and that PC has a reference to, with the variable motherboard.
And we use a getter method to get that reference, then execute loadProgram method on that.
Lastly, let's do that with the next method, the pressPowerButton on the Computer case.

```java  
thePC.getComputerCase().pressPowerButton();
```

Here, in all three cases, we're ultimately accessing functions in other classes, 
theCase, theMotherboard, and theMonitor, but we're accessing them through thePC object.
This is what composition is, this is the difference from inheritance. 
What we did to call the PC object was, we created our case, our monitor, and our motherboard.
And in reality, when putting a computer together, that's what you'd be doing.
You'd buy the computer case, you'd buy the monitor, 
and you'd buy the motherboard, and you'd then put it together to build the PC.

We built this PC by passing objects to the constructor, like assembling the computer.
Let's continue on now, and look at another scenario, whereby we can actually hide 
the functionality further.
In this case, we're not going to allow the calling program to access those objects, the parts, directly. 
We don't want anybody to access the Monitor, Motherboard, ComputerCase directly.

What we'll do first is, we'll go to our PC class, and we'll comment out all the getter methods. 
You'll remember this encapsulates these attributes it hides them from the calling code.
And just to go back and show you what happened, we're now getting an error, 
because those methods no longer exist.
We really don't want the Main class, or any class except the PC class, to make calls on its parts.

Ok, so next, let's create methods on PC, which we'll expose to the calling code.
The first one, we'll call drawLogo, and we're going to implement that in a private method.
And it might do some fancy graphics, but here we're not going to write that code yet.
Instead, we'll just call drawPixelAt, on the monitor attribute of our computer.
We're making this private because the only code we want to draw the computer manufacturer logo 
is the personal computer itself.

```java  
private void drawLogo() {
    monitor.drawPixelAt(1200, 50, "yellow");
}
```

Next, we want to call this method, from a method we'll call power up.
If you think about a computer, when it starts up, you press the power button, 
and then you see a logo come up on the screen.

```java  
public void powerUp() {
    computerCase.pressPowerButton();
    drawLogo();
}
```

Here, we've got a public method called powerUp, and a private function called drawLogo.
The powerUp function of the PC will draw that logo on the screen, as part of starting up the computer.
And now, going back to the main class, we'll comment out the three lines that aren't compiling.
Instead, we'll make a call to the powerUp method on the PC.

```java  
thePC.powerUp();
```

And if we run that, we get the result:

```java  
Power button pressed
Drawing pixel at 1200,50 in color yellow
```

From the calling code's perspective, this code in Main didn't have to know anything about PC's parts, 
to get the PC to do something.
Composition is actually creating objects within objects.
It's like creating a primary object. 
In this case, the PC is managing and looking after all its parts, and it uses composition to achieve that.

### Use Composition or Inheritance or BOth?

As a general rule, when you're designing your programs in Java, 
you probably want to look at composition first. 
If you do any sort of research on the Internet, most of the experts will tell you that as a rule, 
look at using composition before implementing inheritance.
You saw in this example, we actually used both.

All our products, the parts that made up the finished product, and the finished product itself, 
were able to inherit a set of attributes, like the manufacturer and model.
The calling code didn't have to know anything about these parts, to get PC to do something.
It was able to tell the pc to power up, and the pc delegated that work to its parts.

The reasons of preference of composition against inheritance are:

* Composition is more flexible. You can add parts in, or remove them, and 
these changes are less likely to have a downstream effect.
* Composition provides functional reuse outside of the class hierarchy, 
meaning classes can share attributes & behaviors by having similar components, 
instead of inheriting functionality from a parent or base class.
* Java's inheritance breaks encapsulation because subclasses may need direct access 
to a parent's state or behavior.

We showed you an example of using the protected access modifier in the last course, 
and we'll talk about why this breaks encapsulation, in the next courses on encapsulation.

* Inheritance is less flexible. 
Adding a class to, or removing a class form, a class hierarchy, 
may impact all subclasses from that point.
* A new subclass may not need all the functionality or attributes of its parent class, 
and therefore adding the subclasses will muddy the waters, 
meaning the model no longer represents the reality in the code.

### Adding a Digital Product

Let's say we want to include digital products, like software products, in our product inventory.
Should Digital Product inherit from Product?

![image05]()

Here we show the model with Digital Product, inheriting from our current definition of Product.
If we do this, this should mean Digital Product has Product's attributes, but this isn't true now.
A digital product wouldn't really have width, height, and depth, 
so this model isn't a good representation of what we want to build.
It would be better if we didn't have those three attributes on Product, 
but instead used composition to include them on certain products, but not all products.

![image06]()

Now, consider this revised class diagram.
We haven't completely removed the class hierarchy, but we've made the base class, Product, 
less specific, or more generic.
We've removed the width, height, and depth attributes from the Product, and made a new class, 
Dimensions, with those attributes.
And we've added an attribute to Motherboard, which is dimensions, which has those attributes.

This design allows for future enhancements to be made, like the addition of the subclass Digital Product, 
without causing problems for existing code, that may already be extending Product.
By pulling width, height, and depth into a dimension class, we can use composition
to apply those attributes to any product, as we did here with Motherboard, 
but we're not requiring that all subclasses be defined with those attributes.
This is just one example of the inflexibility of inheritance, 
compared to a more flexible design with composition.

## [c. Composition Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/Course03_CompositionChallenge/README.md#composition-challenge-answer)

In this challenge, you need to create an application for controlling a smart kitchen.
Your smart kitchen will have several appliances.
Your appliances will be Internet Of Things (IoT) devices, which can be programmed.

![image07]()

For example, you might do stuff in your kitchen in the morning, in a rush to get to work, 
and then expect your smart kitchen appliances to run automatically at a later time.

It's your job to write the code, to enable your Smart Kitchen application to execute certain jobs.
Methods in your SmartKitchen class will determine what work needs to be done:

* addWater() will set the Coffee Maker's hasWorkToDo field to true.
* pourMilk() will set Refrigerator's hasWorkTodo to true.
* loadDishwasher() will set the hasWorkToDo flag to true, on that appliance.

Alternatively, you could have a single method, called setKitchenState, that takes 3 boolean values, 
which could combine the three methods above.

To execute the work needed to be done by the appliances, you'll implement this in two ways:

* First, your application will access each appliance (by using a getter), and execute a method.
The appliance methods are orderFood() on Refrigerator, doDishes() on DishWasher, 
and brewCoffee() on CoffeeMaker.
These methods should check the hasWorkToDo flag, and if true, print a message out about 
what work is being done.
* Second, your application won't access the appliances directly.
It should call doKitchenWork(), which delegates the work to any of its appliances.

You'll have to create the appliances as classes, with their own methods, as shown on the diagram, 
and then compose your smart kitchen with these appliances.

## [d. Encapsulation]()

In this course, we talk about another fundamental OOP concept, which is Encapsulation. 
We've already talked about it briefly when we first introduced getter methods.
In Java, encapsulation means hiding things by making them private or inaccessible. 
Why would we want to hide things in Java?

* To make the interface simpler, we may want to hide unnecessary details.
* To protect the integrity of data on an object, we may hide or restrict access to some data and operations.
* To decouple the published interface from the internal details of the class, 
we may hide actual names and types of class members. 
This gives us more flexibility if we have to change the class in the future.

And you might wonder what we mean by interface here? 
Although Java has a type called interface, which is important, 
and we'll be covering it in a later section, that's not what we're talking about here. 
When we talk about a class's public or published interface, 
we're really talking about the class members that are exposed to, 
or can be accessed by the calling code. 
Everything else in the class is internal or private to it. 
An application programming interface, or API, 
is the public contract that tells others how to use the class.

What we're going to do is, create a class that doesn't use encapsulation, 
to show why this feature's important. 
We're going to start off with the class that does not use it, 
and then we'll create another enhanced class; that does use it. 
We'll start with the non-encapsulated class, called Player.

![image08]()

Hopefully you're getting used to seeing classes on a diagram now, 
and can appreciate looking at a class this way before we even build it. 
This is the model for a Player class, as in a player in a computer game. 
And The Player will have three variables: name, health, and weapon. 
And this class will have three methods, loseHealth(), restoreHealth(), and healthRemaining(), 
which I'll explain in a bit. 
And we're going to create this class without using encapsulation.

To do this, we'll create a new Java class and call it Player. 
And let's add the variables or fields, and we've said these are the player's name, 
the player's health, and the weapon:

```java  
public class Player {
    public String name;
    public int health;
    public String weapon;
}
```

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

```java  
public void loseHealth(int damage) {
    health -= damage;
    if (health <= 0) {
        System.out.println("Player knocked out of game");
    }
}
```

There we have the first method, which is going to subtract health from our player.
The second method is going to return the amount of health that's available, or what's remaining.
We're calling this method healthRemaining.
And for now, it's simply going to return our variable, health, 
which is our field in this class, so it's really a type of getter, or accessor method.

```java  
public int healthRemaining() {
    return health;
}
```

Finally, we'll add a way for the player to have this health restored, with the restoreHealth method.
This method might be called in the game if the Player finds health tokens, or does something healthy.
This method will add extraHealth points to the health field.

```java  
public void restoreHealth(int extraHealth) {
    health += extraHealth;
}
```

Now, let's just make a rule that health should never go over 100. 
If health is greater than 100, we'll just set it back to be 100, 
and print that the player was completely restored.

```java  
public void restoreHealth(int extraHealth) {
    health += extraHealth;
    if (health > 100) {
        System.out.println("Player restored to 100%");
        health = 100;
    }
}
```

The Player class is done now, and we can go back to our main class. 
And we'll create a new Player.

```java  
Player player = new Player();
```

We didn't create a constructor in the Player class, 
but if you recall a default constructor with no parameters, gets created for us by Java. 
In other words, we don't now have a constructor to pass player data when we create a new player. 
This means the calling code needs to manually set the data in these fields.

```java  
player.name = "Korhan";
player.health = 20;
player.weapon = "Sword";
```

That's the way we need to initialize those fields.
And of course, as you can see, even though we're not in that class, we can access those fields directly.
That's because we set the access to be public for those fields. 
If we set those to private, we wouldn't be able to do this.

Next, let's test the methods on the Player class:

```java  
int damage = 10;
player.loseHealth(damage);
System.out.println("Remaining health = " + player.healthRemaining());
```

Looking at this code, you can see we first made the player health = 20 above this, 
and here we've got "10" damage. 
Then we're calling the player.loseHealth method, which actually reduces the player's 
health by the damage done. 
And if we run that,

```java  
Remaining health = 10
```

You can see the result. 
Ok, let's add some more game play with a few more statements:

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

```java  
player.health = 200;
```

Now, here, the main method has kind of gone rogue, because it's setting the player's health manually, 
outside the normal game play method calls. 
This code actually takes control away from the Player class, for managing the Player's health. 
It didn't call the restoreHealth method, which has the additional control in place, 
that player health can never exceed 100. 
This is an example of the first problem, with a class that's not encapsulated properly.


### Problems(when no encapsulations)

1. Allowing direct access to data on an object, can potentially bypass checks, 
and additional processing, your class has in place to manage the data. 
By being able to access fields directly like this, we're really potentially opening up our application 
to be accessed in ways that it shouldn't be. 
Maybe we don't ever want the code to be able to change the health like that, 
because we really wanted the restoreHealth method to be called, 
and the right set of conditions to be set. 
But because these fields are public to everyone, we can't control when they get accessed.

2. Allowing direct access to fields means calling code would need to change when you edit any of the fields. 
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

3. Omitting a constructor, that would accept initialization data 
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

And this is what encapsulation actually does for us, and why we don't want to code like this.
We want the ability to ensure certain conditions are met before playing, 
and that access to the player data during the game is controlled and protected. 
Now that you've seen some of the bad things, the bad ways of doing it, 
let's actually comment this code out.
 
Let's create a new class that actually has got proper encapsulation by creating a new class, 
named EnhancedPlayer, and show you the right way of doing it. 
The difference here is, we're going to create our 3 fields as private.

```java  
private String name;
private int health;
private String weapon;
```

And here, we have the name of the Player, the health and the weapon, they're all declared as private. 
Next, we do want to create a constructor. 
Placing the cursor below those fields, we'll first create a constructor with three fields.

```java  
public EnhancedPlayer(String name, int health, String weapon) {
    this.name = name;
    this.health = health;
    this.weapon = weapon;
}
```
                      
And what we're going to do is, use this constructor to make sure the health field stays 
in the range of 1 to 100, with an if-else statement. 
Let's edit this constructor a bit, if a user passes a value that's less than 1, 
we'll just make the health 1. 
And if we get a value greater than 100, than doesn't make sense either.

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

This constructor gives us a little more control about how a new Player is created.
Now, we could put more validation in there, to check the length of the name, 
or to make sure the name we get isn't null, for example, and likewise for the weapon.
In other words, we could do some additional validation to make sure that it's valid.
And actually, let's create an overloaded constructor that does not have health or weapon, 
because we'll set these to some default values. 
Generating another constructor for name:

```java  
public EnhancedPlayer(String name) {
    this(name, 100, "Sword");
}
```

This will make creating a new player easy, and we can make the default value for health be 100, 
meaning the Player starts out completely healthy. 
And we're guaranteeing now that the name, the health, and the weapon are initialized 
when the class is created. 
Now, let's copy the three methods we had for Player. 
Ok, now we have a better Player class, which we're calling EnhancedPlayer. 
And if we go back now to the Main class and main method, we can create a new EnhancedPlayer:

```java  
EnhancedPlayer korhan = new EnhancedPlayer("Korhan");
System.out.println("Initial health is " + korhan.healthRemaining());
```

Now if we run that, we should get initial health is 100. 
But what if we use the other constructor, and pass 200 as the health, and sword as the weapon? 
Let's do that and see what happens:

```java  
EnhancedPlayer korhan = new EnhancedPlayer("Korhan", 200, "Swrod");
System.out.println("Initial health is " + korhan.healthRemaining());
```

If we run that:

```java  
Initial health is 100
```

Hopefully, you can see, straight away; we've got more control.
There's no other way for the calling code to change the health, 
except to call EnhancedPlayer's method to do it.
This gives all the control back to the EnhancedPlayer class.
And just to be clear, this is the encapsulation. 
This is what we're doing, by making our fields private, 
we're making sure that the fields within the class aren't accessible to any classes that our outside.

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

### Benefits of Encapsulation

That's really one of the huge benefits of encapsulation, is that 
you're not actually affecting any other code. 
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

We really want to protect access to our object's data. 
We protect the members of the class, and some methods, from external access. 
This prevents calling code from bypassing the rules and constraints, we've built into the class. 
When we create a new instance, it's initialized with valid data. 
But likewise, we're also making sure that there's no direct access to the fields. 
That's why you want to always use encapsulation. 
It's something that you should really get used to.

To create an encapsulated class, you want to:

* Create constructors for object initialization,
* which enforces that only objects with valid data will get created.
* Use the private access modifier for your fields.
* Use setter and getter methods sparingly, and only as needed.
* Use access modifiers that aren't private, only for the methods that the calling code needs to use.

You definitely don't want to be creating code like the Player class when you're creating public fields. 
In the majority of cases, you don't want to give access to other classes.
As you saw, it's more work to make those changes when you create, or when you change a field name, 
or something of that nature.

## [e. Encapsulation Challenge]()

This is the challenge to create a class, and to demonstrate proper encapsulation techniques, 
such as you've learnt in the previous course. 
In this challenge, you need to create a class named Printer.

![image09]()

It's going to simulate a real computer printer, a laser printer.
The fields in this class are going to be:
* **tonerLevel**: which is the percentage of how much toner level is left.
* **pagesPrinted**: which is the count of total pages printed by the Printer.
* **duplex**: which is a boolean indicator. 
If true, it can print on two slides of a single sheet of paper.

You'll to initialize your printer by specifying a starting toner amount, 
and whether the printer is duplex or not. 
On the Printer class, you want to create two methods, which the calling code should be able to access. 
These methods are:

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

