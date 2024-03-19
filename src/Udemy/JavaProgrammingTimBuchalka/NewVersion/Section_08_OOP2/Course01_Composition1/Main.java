package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_OOP2.Course01_Composition1;

/*
Course-52

                                      Object-Oriented Programming (Part-II)

        It's now time to look at the remaining 3 major components of OOP. These are Composition, Polymorphism, and also
    Encapsulation. By the end of this section, you should have a solid overview of what these concepts are, and also how
    to apply them to your programs.

                                            Composition (Part-I)

        As a refresher, I want to review another inheritance example, before we move into composition. Let's say we're
    interested in building a computer, and we want to assemble it. All the parts are manufacturer's products, which we
    have to buy, and assemble, to sell our final product, the personal computer. Next, we'll look at a class diagram of
    some computer products, or computer parts.

                                Product =>
                                            model: String
                                            manufacturer: String
                                            width: int
                                            height: int
                                            depth: int
                                            ----------------------
                    _________________________________↑________________________________________________
                   ↑                        ↑                        ↑                               ↑
           PersonalComputer =>          Monitor =>              Motherboard =>                  ComputerCase =>
                              ------              -------                     -------                           -------

    In this instance, we have a base class called "Product". All of our computer parts are going to inherit from Product.
    All our parts will then have the same set of attributes, a manufacturer, and model, and dimensions, the width, height,
    and depth in other words. All of these items are products are particular type of Product. Personal Computer, Monitor,
    Motherboard, and ComputerCase, are but a few parts, or products, you can think of that would be sold by a computer
    distributor.

        Ok, let's build this. Creating a new class named "Product", we start adding the attributes and a constructor, which
    only includes the model and manufacturer for simplicity:


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

    Ok, we now have our base class, and next let's create a couple of the subclasses. Remember only 1 class can be public,
    so we'll omit the access modifier for these classes. We'll start with Monitor. For Monitor to be a subclass of Product,
    we have to add extends Product by generating the constructor that has got 2 attributes:

                class Monitor extends Product {
                    public Monitor(String model, String manufacturer) {
                        super(model, manufacturer);
                    }
                }

    Ok, that's our first subclass. Let's create our second class just by copying and pasting the same class but with changing
    the method name, and the constructor:

                class Motherboard extends Product {
                    public Motherboard(String model, String manufacturer) {
                        super(model, manufacturer);
                    }
                }

    And finally, let's create ComputerCase the same way:

                class ComputerCase extends Product {
                    public ComputerCase(String model, String manufacturer) {
                        super(model, manufacturer);
                    }
                }

    Ok, so our 3 classes all extend Product. We can say now, that Monitor has got a relationship with Product, meaning that
    a Monitor can be said to be a type of Product. And that's the relationship between the 2 classes. And we can quite
    correctly say, that all 3 of these parts, Monitor, Motherboard, and ComputerCase are all types of Product. And that's
    essentially what inheritance is.

        Inheritance defines an "is A" relationship. Composition defines a "HAS A" relationship. Next, let's look at how
    we'd use composition to create a personal computer.

       ____________________
      |Product =>          |                                                          ____________________
      |____________________|                                         ________________|Monitor =>          |
                ↑ Inherits(IS A)                                    |                |____________________|
       ________________________________________________             |
      | PersonalComputer =>                            |            |
      |                     monitor: Monitor           |            |
      |                     motherboard: Motherboard   |<<==========|                 ____________________
      |                     computerCase: ComputerCase |  Is Made   |________________|Motherboard =>      |
      |________________________________________________|   Up Of    |                |____________________|
                                                          (HAS A)   |
                                                                    |                 ____________________
                                                                    |________________|ComputerCase =>     |
                                                                                     |____________________|

    To keep this diagram simple, I'm showing only that PersonalComputer inherits from Product, but remember all of our
    classes are really subclasses of product. But a PersonalComputer, in addition to being a product, is actually made up
    of other parts. This diagram shows that a computer has a motherboard, a case, and a monitor. The motherboard, the computer
    case, and the monitor aren't computers, not in the same sense that they're products. So that's what Composition is,
    actually modeling parts, and those parts make up a greater whole. In this case, we're going to model the personal
    computer, and we're modeling the "has a" relationship, with the motherboard, the case, and the monitor. And we've already
    created the parts, but we didn't really make them different from each other. Now we'll look at another diagram, that
    shows these classes, with some state and behavior, specific to their product type.

          Monitor =>                                              Motherboard =>
                    size:int                                                    ramSlots:int
                    resolution:String                                           cardSlots:int
                    -----------------                                           bios:String
                    drawPixelAt(int x, int y, String color)                     -----------------
                                                                                loadProgram(String programName)

          Monitor =>
                    powerSupply:String
                    -----------------
                    pressPowerButton()

    This diagram shows 3 classes that will make up the personal computer. We'll have size and resolution for the monitor,
    and we'll want a method called drawPixelAt, so the monitor has behavior to draw. For the computerCase, we'll have a
    powerSupply, and a method pressPowerButton(). And for the Motherboard, we'll have slots for ram, and cards, as well as
    a bios attribute, and a method called loadProgram().

        Getting back to the code, let's add these members to our parts. We'll start with the motherboard, and add the 3
    attributes first, and next we want to generate another constructor, which initializes these 3 attributes but also calls
    super constructor:

                private int ramSlots;
                private int cardSlots;
                private String bios;
                public Motherboard(String model, String manufacturer, int ramSlots, int cardSlots, String bios) {
                    super(model, manufacturer);
                    this.ramSlots = ramSlots;
                    this.cardSlots = cardSlots;
                    this.bios = bios;
                }

    So we'll have 1 more method, called loadProgram:

                public void loadProgram(String programName) {
                    System.out.println("Program " + programName + " is now loading...");
                }

    That's our Motherboard class, pretty straightforward as you can see, with nothing particularly complicated there. Now,
    let's move onto our Monitor class, and add those fields, size and resolution:

                private int size;
                private String resolution;
                public ComputerCase(String model, String manufacturer, int size, String resolution) {
                    super(model, manufacturer);
                    this.size = size;
                    this.resolution = resolution;
                }

    Ok, so now we have the fields and the constructors. What we're going to do here next is, create a public method called
    drawPixelAt, and it's going to have an x and y location, and a color. We want to use String.format, which we learned
    to do in the last section. And we'll just print a simple message out:

                public void drawPixelAt(int x, int y, String color) {
                    System.out.println(String.format(
                            "Drawing pixel at %d,%d in color %s ", x, y, color));
                }

    And that completes the Monitor class. Finally, let's finish Computer Case, which we've said has a power supply and 1
    method, pressPowerButton, so we'll add the powerSupply field:

                private String powerSupply;
                public ComputerCase(String model, String manufacturer, String powerSupply) {
                    super(model, manufacturer);
                    this.powerSupply = powerSupply;
                }
                public void pressPowerButton() {
                    System.out.println("Power button pressed");
                }

    Now that we have all of our parts done, it's time to build the computer. This is the fun part, where we actually create
    a class that's comprised of the computer case, the monitor, and the motherboard. Creating new class, named PersonalComputer,
    the first thing we want to do is, add extends Product to this class too. Even though our personal computer has a bunch
    of parts, it's still a product, wth all of product's attributes. You can use a combination of inheritance, and composition,
    to create your model, as we're doing here. And this class won't compile until we have a constructor, but let's wait
    to do that until after we've added our attributes. Let's look at our PersonalComputer class diagram again. We've said
    it inherits from Product, and it also has 3 fields, which are classes, these are Monitor, Motherboard, and ComputerCase.
    Let's build this Personal Computer class now, using composition, which means we'll add 3 attributes:

                public class PersonalComputer extends Product{
                    private ComputerCase computerCase;
                    private Monitor monitor;
                    private Motherboard motherboard;
                }

    And you can see the obvious advantage here, because if you're using the extends option to inherit, Java only lets you
    inherit from one class at a time. You could see, that we'd run into difficulties and limitations quite quickly, if our
    only tool was inheritance. In this case, what we've said is, the Personal computer consists of these 3 other classes,
    namely the computer case, the monitor, and the motherboard. Before we do anything else, we should add a constructor.
    You can see IntelliJ isn't happy with us, because we haven't done that yet.

                public PersonalComputer(String model, String manufacturer, ComputerCase computerCase,
                                        Monitor monitor, Motherboard motherboard) {
                    super(model, manufacturer);
                    this.computerCase = computerCase;
                    this.monitor = monitor;
                    this.motherboard = motherboard;
                }

    And for this class, we do want to add some getter methods for these attributes.

                public ComputerCase getComputerCase() {
                    return computerCase;
                }
                public Monitor getMonitor() {
                    return monitor;
                }
                public Motherboard getMotherboard() {
                    return motherboard;
                }

    Ok, that's our Personal Computer. We've described what we want to assemble. In the next course, we'll actually assemble
    the parts together, and we'll look at how to access the functionality of the parts, through the personal computer
    container.

                                        Composition (Part-II)

        Now, we need to assemble the Personal Computer. Let's create some objects in Main class firstly, an object of type
    ComputerCase. We'll create a Dell 2208 case, and it will have a 240 power supply:

                ComputerCase theCase = new ComputerCase("2208", "Dell", "240");

    That's our definition for a computer case now. Remembering that when we create the personal computer, which we're going
    to be doing shortly, it needs this reference. PCs generally can't be put together without a case, so what we're doing
    here is creating that case.

        Next, we need to create a monitor, this time we'll say it's an Acer 27inch beast, so we'll pass in the size, and
    the resolution of 2540 by 1440.

                Monitor theMonitor = new Monitor("27inch Beast", "Acer", 27, "2540 x 1440");

    We've now got a case and a monitor, that ultimately are going to be used, to create our PC. Of course, we now need a
    motherboard. And we'll make this an Asus, BJ-200 model. We also need the Number of RAM slots, which we'll say is 4.
    And the number of card slots, let's just say there's 6. And for the bios, let's say it's version v2.44.

                Motherboard motherboard = new Motherboard("BJ-200", "Asus", 4,6,"v2.44");

    That gives us our motherboard. And now that we've got all our parts, we can put our Personal Computer together. We've
    created instances of a Monitor class, a Motherboard class, and a Computer Case class, and it's time to build this, and
    see what we can do with it.

        The PC will have the same manufacturer and model, as the computer case, so 2208 as the model, then Dell as the
    manufacturer, and then we'll pass the other variables to the PC.

                PersonalComputer thePC = new PersonalComputer("2208", "Dell",
                theCase, theMonitor, theMotherboard);

    And you can see here, we've created the PC object, by passing those 3 other objects to it, as well as the model and
    manufacturer.

        With inheritance, we were able to use a method from the base class. But how to we get access to the composite
    object's methods? In other words, how do we make it do anything? Well, we can do this by telling one of its parts, to
    do something. For example, if we want to draw something using the monitor, how do we access this method? We have the
    method drawPixelAt on monitor, but how do we access that? Well, we can get the PC's monitor object, using the getter
    method, and then call drawPixelAt on that object, so let's do that:

                thePC.getMonitor().drawPixelAt(10, 10, "red");

    That's one way of executing functionality, by using the getter method from PC, to get the instance of the monitor class,
    that the Pc contains. In this code, from that return object, we're chaining the call to the method drawPixelAt. And
    that's invoking that method for us. Likewise, we could call the method loadProgram on the motherboard, passing it Windows
    OS, for example, as the program we want to run.

                thePC.getMotherboard().loadProgram("Windows OS");

    We're doing the same thing here, we're getting the Motherboard object that's been created, and that PC has a reference
    to, with the variable motherboard. And we use a getter method to get that reference, then execute loadProgram method
    on that. Lastly, let's do that with the next method, the pressPowerButton on the Computer case.

                thePC.getComputerCase().pressPowerButton();

    Here, in all 3 cases, we're ultimately accessing functions in other classes, theCase, theMotherboard, and theMonitor,
    but we're accessing them through thePC object. This is what composition is, this is the difference from inheritance.
    What we did to call the PC object was, we created our case, our monitor, and our motherboard. And in reality, when
    putting a computer together, that's what you'd be doing. You'd buy the computer case, you'd buy the monitor, and you'd
    buy the motherboard, and you'd then put it together, to build the PC.


*/

public class Main {
    public static void main(String[] args) {

        ComputerCase theCase = new ComputerCase("2208", "Dell", "240");
        Monitor theMonitor = new Monitor("27inch Beast", "Acer", 27, "2540 x 1440");
        Motherboard theMotherboard = new Motherboard("BJ-200", "Asus", 4,6,"v2.44");
        PersonalComputer thePC = new PersonalComputer("2208", "Dell",
                theCase, theMonitor, theMotherboard);

        thePC.getMonitor().drawPixelAt(10, 10, "red");
        thePC.getMotherboard().loadProgram("Windows OS");
        thePC.getComputerCase().pressPowerButton();


    }
}
