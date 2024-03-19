package CourseCodes.NewSections.Section_08_OOP2.Course09_PolymorphismChallenge;

public class Car {

    private String description;

    public Car(String description) {
        this.description = description;
    }

    public void startEngine() {
        System.out.println("Car -> startEngine");
    }

    protected void runEngine() {
        System.out.println("Car -> runEngine");
    }

    public void drive() {
        System.out.println("Car -> driving, type is " + getClass().getSimpleName());
        runEngine();
    }

/*
Part-2
        And now let's add runEngine. This time, we'll make the access modifier "protected". This means our subclasses can
    override it, but any other classes, not sharing the same package, can't. Don't worry, we'll be diving into packages
    in just a few courses. And lastly, we add runEngine(). In this method, we're going to print out the runtime type. Now,
    "getClass().", you can see, we didn't have to include "this" keyword, meaning we don't need to specify it. This statement
    here, calls the method on the currently executing instance, even without the use of the "this" keyword. Ok, that's the
    base car. And now let's go to the Main Class, and create a method, which will take a Car parameter.
End-Part-2
*/
}

class GasPoweredCar extends Car {

    private double avgKmPerLiter;
    private int cylinders = 6;

    public GasPoweredCar(String description) {
        super(description);
    }

    public GasPoweredCar(String description, double avgKmPerLiter, int cylinders) {
        super(description);
        this.avgKmPerLiter = avgKmPerLiter;
        this.cylinders = cylinders;
    }

    @Override
    public void startEngine() {
        System.out.printf("Gas -> All %d cylinders are fired up, Ready!%n", cylinders);
    }

    @Override
    protected void runEngine() {
        System.out.printf("Gas -> usage exceeds the average: %.2f %n", avgKmPerLiter);
    }

    /*
Part-7
        And we'll pick just 2 of the methods to override, which are startEngine and RunEngine. I'll explain why I decided
    not to override the driving method in just a little bit. The default option that IntelliJ gives us there is, super(),
    and that's a way to call the super() functions. In some cases, we may want to do that. We might want to include the
    processing that's in that base class here, for startEngine, but then we'd want to provide functionality over and above
    that. But in this case, we're going to replace each of these calls, with some different behavior. For a gasPoweredEngine,
    when we start an engine, we might fire up the cylinders in the engine, or something like that:

            super.startEngine();  >>>  System.out.println("Gas -> All %d cylinders are fired up, Ready!%n", cylinders);

    And when we run the engine, we might want to report back how well the engine is running, compared to the average:

            super.startEngine();  >>>  System.out.printf("Gas -> usage exceeds the average: %.2f %n", avgKmPerLiter);

    Ok, let's see what happens now, if we execute the code in the Main class:
End-Part-7
*/
}

class ElectricCar extends Car {

    /*
Part-9
        Let's make a couple of changes to the ElectricCar class. We'll change the average Kilometres per litre, to average
    kilometres per charge. We can highlight that field, select Re-factor, then Rename, and change Litre to Charge, to be
    avgKmPerCharge. And do the same for cylinders to be batterySize. Finally, we want to change the way the startEngine
    and runEngine methods work. We'll call these BEV for short, in the statements, which stands for BatteryElectricVehicle.
    And let's repeat that process once again, but for the Hybrid Car.
End-Part-9
*/

    private double avgKmPerCharge;
    private int batterySize = 6;

    public ElectricCar(String description) {
        super(description);
    }

    public ElectricCar(String description, double avgKmPerLiter, int cylinders) {
        super(description);
        this.avgKmPerCharge = avgKmPerLiter;
        this.batterySize = cylinders;
    }

    @Override
    public void startEngine() {
        System.out.printf("BEV -> switch %d kWh battery on, Ready!%n", batterySize);
    }

    @Override
    protected void runEngine() {
        System.out.printf("BEV -> usage under the average: %.2f %n", avgKmPerCharge);
    }

}

class HybridCar extends Car {

/*
Part-10
        The fields are the same for a HybridCar as a GasPoweredCar, which actually is what we want, but we also need an
    Electric Vehicle batterSize, so let's add it: "private int batterySize;". And we'll add it to the constructor code.
    We'll do this manually, and just include it, as a parameter to the constructor first. And now, let's change the overridden
    methods, for the Hybrid car. First we'll edit the startEngine method, and for a hybrid, we need to fire up cylinders,
    and switch the battery power on.

                System.out.printf("Hybrid -> %d cylinders are fired up.%n", cylinders);
                System.out.printf("Hybrid -> switch %d kWh battery on, Ready!%n", batterySize);

        And next, we'll edit the runEngine method, and we're just going to have it run basically the same way as a gas
    powered car.

                System.out.printf("Hybrid -> usage below average: %.2f %n", avgKmPerLiter);

    You might want to write more code here, with something using battery, if the battery is greater than 10% or something.
    And you might want to include fields for that, and so on. But to keep this simple, we'll just have it run like a gas
    car for now, and just print the Hybrid keyword. Now, we want to go back to the main method, and create some of these
    instances.
End-Part-10
*/

    private double avgKmPerLiter;
    private int cylinders = 6;

    private int batterySize;

    public HybridCar(String description) {
        super(description);
    }

    public HybridCar(String description, double avgKmPerLiter, int cylinders, int batterySize) {
        super(description);
        this.avgKmPerLiter = avgKmPerLiter;
        this.cylinders = cylinders;
        this.batterySize = batterySize;
    }

    @Override
    public void startEngine() {
        System.out.printf("Hybrid -> %d cylinders are fired up.%n", cylinders);
        System.out.printf("Hybrid -> switch %d kWh battery on, Ready!%n", batterySize);
    }

    @Override
    protected void runEngine() {
        System.out.printf("Hybrid -> usage below average: %.2f %n", avgKmPerLiter);
    }

}
/*
Part-5
        We also generate some constructors. First one is the same as the Car class's, and calls to super as the first statement.
    And with the other constructor, we can pass the number of cylinders, and the average Kms Per liter for a gas, or petrol
    powered car. Now let's go to the Main class and main method, and create an instance of this kind of car, and execute
    the runRace method on it.
End-Part-5
*/