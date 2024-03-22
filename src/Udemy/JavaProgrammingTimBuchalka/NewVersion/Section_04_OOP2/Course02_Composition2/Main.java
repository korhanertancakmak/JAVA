package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_04_OOP2.Course02_Composition2;

/*
Course-53

                                                Composition (Part-II)

        We built this PC, by passing objects, to the constructor, like assembling the computer. Let's continue on now, and
    look at another scenario, whereby we can actually hide the functionality further. In this case, we're not going to
    allow tha calling program, to access those objects, the parts, directly. We don't want anybody to access the Monitor,
    Motherboard, ComputerCase directly.

        What we'll do first is, we'll go to our PC class, and we'll comment out all the getter methods. You'll remember
    this encapsulates these attributes, it hides them, from the calling code. And just to go back and show you what happened,
    we're now getting an error, because those methods no longer exist. We really don't want the Main class, or any class
    except the PC class, to make calls on its parts.

        Ok so next, let's create methods on PC, which we'll expose to the calling code. The first one, we'll call drawLogo,
    and we're going to implement that in a private method. And it might do some fancy graphics, but here we're obviously
    not going to write that code yet. Instead, we'll just call drawPixelAt, on the monitor attribute of our computer. We're
    making this private, because the only code we want to draw the computer manufacturer logo, is the personal computer
    itself.

                    private void drawLogo() {
                        monitor.drawPixelAt(1200, 50, "yellow");
                    }

    Next, we want to call this method, from a method we'll call power up. If you think about a computer, when it's starts
    up, you press the power button, and then you see a logo come up on the screen.

                    public void powerUp() {
                        computerCase.pressPowerButton();
                        drawLogo();
                    }

    Here, we've got a public method called powerUp, and a private function called drawLogo. The powerUp function of the PC
    will draw that logo on the screen, as part of starting up the computer. And now, going back to the main class, we'll
    comment out the 3 lines that aren't compiling. Instead, we'll make a call to the powerUp method on the PC.

                    thePC.powerUp();

    And if we run that, we get the result,

                    Power button pressed
                    Drawing pixel at 1200,50 in color yellow

    From the calling code's perspective, this code in Main didn't have to know anything about PC's parts, to get the PC
    to do something. Composition is actually creating objects within objects. It's like creating a master object. In this
    case, the PC is managing and looking after all its parts, and it uses composition to achieve that.

                                Use Composition or Inheritance or BOth?

        As a general rule, when you're designing your programs in Java, you probably want to look at composition first.
    if you do any sort of research on the Internet, most of the experts will tell you, that as a rule, look at using composition
    before implementing inheritance. You saw in this example, we actually used both.

        All our products, the parts that made up the finished product, and the finished product itself, were able to inherit
    a set of attributes, like the manufacturer and model. The calling code didn't have to know anything about these parts,
    to get PC to do something. It was able to tell the pc to power up, and the pc delegated that work to its parts.

        The reasons composition is preferred over inheritance:
  - Composition is more flexible. You can add parts in, or remove them, and these changes are less likely to have a downstream
    effect.
  - Composition provides functional reuse outside from the class hierarchy, meaning classes can share attributes & behaviors,
    by having similar components, instead of inheriting functionality from a parent or base class.
  - Java's inheritance breaks encapsulation, because subclasses may need direct access to a parent's state or behavior.

        We showed you an example of using the protected access modifier in the last course, and we'll talk about why this
    breaks encapsulation, in the next courses on encapsulation.

  - Inheritance is less flexible. Adding a class to, or removing a class form, a class hierarchy, may impact all subclasses
    from that point.
  - A new subclass may not need all the functionality or attributes of its parent class, and therefore adding the subclasses
    will muddy the waters, meaning the model no longer represents the reality in the code.

                                            Adding a Digital Product

       Let's say we want to include digital products, like software products, in our product inventory. Should Digital
    Product inherit from Product?

                                    Product =>
                                            model: String
                                            manufacturer: String
                                            width: int
                                            height: int
                                            depth: int
                                            ----------------------
                                                    ↑ Inherits(IS A)
                   ___________________________________________________________________________________
                   ↑                                                                                 ↑
           Motherboard  =>                                                      Digital Product =>
                           ramSlots: int                                                           versions: String
                           cardSlots: int                                                          releaseDate: Date
                           bios: String                                                            ---------------------
                           ----------------------                                                  runProgram()
                           loadProgram(String programName)

    Here we show the model with Digital Product, inheriting from our current definition of Product. If we do this, this
    should mean Digital Product has Product's attributes, but this isn't true now. A digital product wouldn't really have
    width, height, and depth, so this model isn't a good representation of what we want to build. It would be better if
    we didn't have those 3 attributes on Product, but instead used composition to include them on certain products, but
    not all products.

                                                                ____________________________________________
                                                               |Product =>                                  |
                                                               |            model: String                   |
                                                               |            manufacturer: String            |
                                                               |            ----------------------          |
                                                               |____________________________________________|
                                                                            ↑ Inherits(IS A)
           _________________________________________           _________________________________________________
          |Dimensions  =>                           |         |Motherboard =>                                   |
          |                width: int               |         |                ramSlots: int                    |
          |                height: int              | (HAS A) |                cardSlots: int                   |
          |                depth: int               |=======>>|                bios: String                     |
          |                ----------------------   |         |                dimension: Dimensions            |
          |_________________________________________|         |                ---------------------            |
                                                              |                loadProgram(String programName)  |
                                                              |_________________________________________________|

    Now, consider this revised class diagram. We haven't completely removed the class hierarchy, but we've made the base
    class, Product, less specific, or more generic. We've removed the width, height, and depth attributes from Product,
    and made a new class, Dimensions, with those attributes. And we've added an attribute to Motherboard, which is dimensions,
    which has those attributes.

        This design allows for future enhancements to be made, like the addition of the subclass Digital Product, without
    causing problems for existing code, that may already be extending Product. By pulling width, height, and depth, into
    a dimension class, we can use composition, to apply those attributes to any product, as we did here with Motherboard,
    but we're not requiring that all subclasses be defined with those attributes. This is just one example of the inflexibility
    of inheritance, compared to a more flexible design with composition.
*/

public class Main {
    public static void main(String[] args) {

        ComputerCase theCase = new ComputerCase("2208", "Dell", "240");
        Monitor theMonitor = new Monitor("27inch Beast", "Acer", 27, "2540 x 1440");
        Motherboard theMotherboard = new Motherboard("BJ-200", "Asus", 4,6,"v2.44");
        PersonalComputer thePC = new PersonalComputer("2208", "Dell",
                theCase, theMonitor, theMotherboard);

//        thePC.getMonitor().drawPixelAt(10, 10, "red");
//        thePC.getMotherboard().loadProgram("Windows OS");
//        thePC.getComputerCase().pressPowerButton();

        thePC.powerUp();
    }
}
