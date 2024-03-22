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


      
