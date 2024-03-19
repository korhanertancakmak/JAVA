package CourseCodes.NewSections.Section_08_OOP2.Course09_PolymorphismChallenge;


public class Main {
    public static void main(String[] args) {

/*
Part-1
                                       Polymorphism Challenge Exercise

        What we want to do in this challenge, is have our runtime code execute different behavior for different objects.
    Let's talk about what I want you to do in this challenge.

                                         __________________________
                                         |Car =>                  |
                                         |  description: String   |
                                         |------------------------|
                                         |  startEngine()         |
                                         |  drive()               |
                                         |  protected runEngine() |
                                         |________________________|
                      _______________________________↑____________________________________
                      |                              |                                   |
            __________|_______________      _________|________________       ____________|_______________
            |GasPoweredCar =>        |      |ElectricCar =>          |       |HybridCar =>              |
            |   avgKmPerLitre: double|      |   avgKmPerLitre: double|       |  avgKmPerLitre: double   |
            |   cylinders: int       |      |   batterySize: int     |       |  batterySize: int        |
            |------------------------|      |------------------------|       |  cylinders: int          |
            |________________________|      |________________________|       |--------------------------|
                                                                             |__________________________|

    This diagram shows a base class, Car, with 1 field, description, and 3 methods, startEngine(), drive(), and runEngine().
    The first 2 methods should be declared as public. The method runEngine however, is protected, and it will only get called
    from the drive method in Car. And we've given 3 types of subclasses, or 3 types of cars that you might find on the road.
    We have the GasPoweredCar, the ElectricCar, and the HybridCar. You can imagine that these 3 subclasses, might have
    different ways to start their engine, or drive, depending on their engine type. And each of these classes might have
    different variables or fields, that might be used in those methods. We show you a few of the fields here, but you
    should try to be creative in your own design. Maybe you might be interested in the top speed of the cars, or how fast
    they can accelerate, or some other way you want to compare these cars' performances. It's your job, to create this
    class structure in Java, and override some, or maybe all, of these methods appropriately. And you'll write code in
    a Main class and main method, that creates an instance of each of these classes, and that executes different behavior
    for each instance. At least one method should print the type of the runtime object. ,

        We begin with creating our base class, Car, and creating its own constructor and methods, firstly startEngine().
End-Part-1
*/
        Car car = new Car("2022 Blue Ferrari 296 GTS");
        runRace(car);
/*
Part-4
        And running this code, we see the output:

                Car -> startEngine
                Car -> driving, type is Car
                Car -> runEngine

    And this is telling us that the methods on Car were called, first startEngine, then driving. And the current instance
    type is just Car, because we did use new Car to create an instance of Car. And the driving method in turn, called the
    runEngine method. Ok, that's the base class, or the generic car class. Now, we want to extend that, and create 3 subclasses.
    We'll start with GasPoweredCar, and we'll create this class in the Car.java source file.
End-Part-4
*/
        Car ferrari = new GasPoweredCar("2022 Blue Ferrari 296 GTS",
                15.4, 6);
        runRace(ferrari);
/*
Part-6
        This time, we're still declaring the type of the variable, which we're calling Ferrari as a car. We could have made
    it GasPoweredCar, and that would have worked the same. And we're using the constructor on GasPoweredCar that takes a
    description, the average kilometers per liter, and the number of cylinders. And if we run that, we actually get the
    same output as we did,

                Car -> startEngine
                Car -> driving, type is Car
                Car -> runEngine
                Car -> startEngine
                Car -> driving, type is GasPoweredCar
                Car -> runEngine

    when we created a new Car, except now the runtime type(in the driving method) is a GasPoweredCar. This output is telling
    us that the methods of Car were executed, but even though Car's methods were called, the actual runtime type is GasPoweredCar.
    And Car's methods were executed, because we didn't override any of those methods, yet. Going back to the GasPoweredCar,
    we'll override some methods.
End-Part-6
*/
/*
Part-8
                Car -> startEngine
                Car -> driving, type is Car
                Car -> runEngine
                Gas -> All 6 cylinders are fired up, Ready!
                Car -> driving, type is GasPoweredCar
                Gas -> usage exceeds the average: 15,40

        We see that startEngine, on the gasPoweredCar is executed, because it tells us that all 6 cylinders are fired up.
    But then we still run the driving method on the Car class, because we never did override that method. Our runtime type
    is a GasPoweredCar. And we know the driving method calls the runEngine method. What's really cool, you can see on this
    next line, is the GasPoweredCar's runEngine method. This happened even though the method that called it, drive, wasn't
    overridden on GasPoweredCar. So polymorphism doesn't just happen on methods we call directly. It also happens on methods
    we call indirectly. This means, that in essence, you can implement different behavior on just portions of a Car's behavior.

        And what we want to do next is to create the other 2 subclasses, ElectricCar and HybridCar. Let's close the GasPowered
    node, because we want to copy this node, which really copies the whole class, and then paste that node below it.
End-Part-8
*/
        Car tesla = new ElectricCar("2022 Red Tesla Model 3", 568, 75);
        runRace(tesla);
/*
Part-11
        First, we'll create an instance of an Electric Car, and we'll make it a 2022 Tesla Model 3, and say that it can
    go 568 kms, on a battery that has a size of 75 kWh. And we run this,

                Car -> startEngine
                Car -> driving, type is Car
                Car -> runEngine
                Gas -> All 6 cylinders are fired up, Ready!
                Car -> driving, type is GasPoweredCar
                Gas -> usage exceeds the average: 15,40
                BEV -> switch 75 kWh battery on, Ready!
                Car -> driving, type is ElectricCar
                BEV -> usage under the average: 568,00

    we can see the output for these statements. And the output is telling us, that the methods we overrode for the electric
    car, were the ones that got executed. This means the behavior for the electric car, the Battery Electric Vehicle, BEV,
    is different from the GasPoweredCar, or a generic car. That's what we wanted, so that's good. Lastly, let's test out
    the hybrid car. We'll set up a Ferrari SF90 Stradale, which has 8 cylinders, and a much smaller battery than an Electric
    Car, and in this case, it's 8 kWh.
End-Part-11
*/
        Car ferrariHybrid = new HybridCar("2022 Black Ferrari SF90 Stradale", 16, 8 , 8);
        runRace(ferrariHybrid);
/*
Part-12
        Remember, this constructor takes the description, the average kms per litre, which is about 16, the number of cylinders
    which is 8, and finally the battery, so 8 for that too. And again, if we run that,

                Car -> startEngine
                Car -> driving, type is Car
                Car -> runEngine
                Gas -> All 6 cylinders are fired up, Ready!
                Car -> driving, type is GasPoweredCar
                Gas -> usage exceeds the average: 15,40
                BEV -> switch 75 kWh battery on, Ready!
                Car -> driving, type is ElectricCar
                BEV -> usage under the average: 568,00
                Hybrid -> 8 cylinders are fired up.
                Hybrid -> switch 8 kWh battery on, Ready!
                Car -> driving, type is HybridCar
                Hybrid -> usage below average: 16,00

    We now see that the last car is a HybridCar. There are 2 steps to starting up, 8 cylinders are fired up, and the 8 kWh
    battery is switched on. And that was the challenge. In this example, we created a polymorphic method on the Main class.
    This method only knew about the Car class, and not about any of its subclasses. And it could be executed, for many
    different types as we showed. 
End-Part-12
*/
    }

    public static void runRace(Car car) {
        car.startEngine();
        car.drive();
    }

/*
Part-3
        Let's call it runRace, and it will execute 2 methods on the Car class, startEngine, and drive. Now, let's create
    an instance of the base car, in the main class's main method, and test this out:
End-Part-3
*/

}
