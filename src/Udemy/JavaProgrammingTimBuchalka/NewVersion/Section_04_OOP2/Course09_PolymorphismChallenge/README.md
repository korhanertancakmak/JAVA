# [Polymorphism Challenge]()

We begin with creating our base class, Car, and creating its own constructor and methods,
firstly startEngine().

```java  
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
}
```

And now let's add runEngine. 
This time, we'll make the access modifier *protected*.
This means our subclasses can override it, but any other classes, not sharing the same package, can't. 
Don't worry, we'll be diving into packages in just a few courses. 
And lastly, we add *runEngine()*. 
In this method, we're going to print out the runtime type. 
Now, *getClass()*, you can see, we didn't have to include *this* keyword, 
meaning we don't need to specify it. 
This statement here, calls the method on the currently executing instance, 
even without the use of *this* keyword. 
Ok, that's the base car. 
And now let's go to the Main Class, and create a method, which will take a Car parameter.

Let's call it runRace, and it will execute two methods on the Car class, startEngine, and drive. 
Now, let's create an instance of the base car, in the main class's main method, and test this out:

```java  
public static void runRace(Car car) {
    car.startEngine();
    car.drive();
}
```

And running this code, we see the output:

```java  
Car -> startEngine
Car -> driving, type is Car
Car -> runEngine
```

And this is telling us that the methods on Car were called, first startEngine, then driving. 
And the current instance type is just Car, because we did use new Car to create an instance of Car. 
And the driving method in turn, called the runEngine method. 
Ok, that's the base class, or the generic car class. 
Now, we want to extend that, and create three subclasses. 
We'll start with GasPoweredCar, and we'll create this class in the Car.java source file.

```java  
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
}
```

This time, we're still declaring the type of the variable, which we're calling Ferrari as a car. 
We could have made it GasPoweredCar, and that would have worked the same. 
And we're using the constructor on GasPoweredCar that takes a description, 
the average kilometers per liter, and the number of cylinders. 

We also generate some constructors. 
First, one is the same as the Car class's, and calls super as the first statement.
And with the other constructor, we can pass the number of cylinders, 
and the average Kms Per liter for a gas, or petrol-powered car. 
Now let's go to the Main class and main method, and create an instance of this kind of car, 
and execute the runRace method on it.

```java  
Car ferrari = new GasPoweredCar("2022 Blue Ferrari 296 GTS", 15.4, 6);
runRace(ferrari);
```

And if we run that, we actually get the same output as we did:

```java  
Car -> startEngine
Car -> driving, type is Car
Car -> runEngine
Car -> startEngine
Car -> driving, type is GasPoweredCar
Car -> runEngine
```

When we created a new Car, except now the runtime type (in the driving method) is a GasPoweredCar. 
This output is telling us that the methods of Car were executed, but even though Car's methods were called, 
the actual runtime type is GasPoweredCar. 
And Car's methods were executed, because we didn't override any of those methods yet. 
Going back to the GasPoweredCar, we'll override some methods.

```java  
@Override
public void startEngine() {
    System.out.printf("Gas -> All %d cylinders are fired up, Ready!%n", cylinders);
}
@Override
protected void runEngine() {
    System.out.printf("Gas -> usage exceeds the average: %.2f %n", avgKmPerLiter);
}
```

And we'll pick just two of the methods to override, which are startEngine and RunEngine. 
I'll explain why I decided not to override the driving method in just a little bit. 
The default option that IntelliJ gives us there is, super(), 
and that's a way to call the super() functions. 
In some cases, we may want to do that. 
We might want to include the processing that's in that base class here, for startEngine, 
but then we'd want to provide functionality over and above that. 
But in this case, we're going to replace each of these calls with some different behavior. 
For a gasPoweredEngine, when we start an engine, we might fire up the cylinders in the engine, 
or something like that:

```java  
super.startEngine();
System.out.println("Gas -> All %d cylinders are fired up, Ready!%n", cylinders);
```

And when we run the engine, we might want to report back how well the engine is running, 
compared to the average:

```java  
super.startEngine();
System.out.printf("Gas -> usage exceeds the average: %.2f %n", avgKmPerLiter);
```

Ok, let's see what happens now, if we execute the code in the Main class:

```java  
Car -> startEngine
Car -> driving, type is Car
Car -> runEngine
Gas -> All 6 cylinders are fired up, Ready!
Car -> driving, type is GasPoweredCar
Gas -> usage exceeds the average: 15,40
```

We see that startEngine on the gasPoweredCar is executed, 
because it tells us that all six cylinders are fired up. 
But then we still run the driving method on the Car class, 
because we never did override that method. 
Our runtime type is a GasPoweredCar. 
And we know the driving method calls the runEngine method. 
What's really cool, you can see on this
next line, is the GasPoweredCar's runEngine method. 
This happened even though the method that called it, drive wasn't overridden on GasPoweredCar. 
So polymorphism doesn't just happen on methods we call directly. 
It also happens on methods we call indirectly.
This means that in essence, you can implement different behavior on just portions of a Car's behavior.

And what we want to do next is to create the other two subclasses, ElectricCar and HybridCar. 
Let's close the GasPowered node, because we want to copy this node, 
which really copies the whole class, and then paste that node below it.

```java  
class ElectricCar extends Car {
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
```

Let's make a couple of changes to the *ElectricCar* class. 
We'll change the average Kilometres per litre, to average kilometres per charge. 
We can highlight that field, select Re-factor, then Rename, 
and change Litre to Charge, to be avgKmPerCharge. 
And do the same for cylinders to be batterySize. 
Finally, we want to change the way the startEngine and runEngine methods work. 
We'll call these *BEV* for short, in the statements, which stands for BatteryElectricVehicle.
And let's repeat that process once, but for the Hybrid Car.

```java  
class HybridCar extends Car {
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
```

The fields are the same for a HybridCar as a GasPoweredCar, which actually is what we want,
but we also need an Electric Vehicle batterSize, so let's add it: 
*private int batterySize;*. 
And we'll add it to the constructor code.
We'll do this manually, and just include it, as a parameter to the constructor first. 
And now let's change the overridden methods for the Hybrid car. 
First, we'll edit the startEngine method, and for a hybrid, 
we need to fire up cylinders, and switch the battery power on.

```java  
System.out.printf("Hybrid -> %d cylinders are fired up.%n", cylinders);
System.out.printf("Hybrid -> switch %d kWh battery on, Ready!%n", batterySize);
```

And next, we'll edit the runEngine method, and we're just going to have it run 
basically the same way as a gas powered car.

```java  
System.out.printf("Hybrid -> usage below average: %.2f %n", avgKmPerLiter);
```

You might want to write more code here, with something using battery, 
if the battery is greater than 10% or something.
And you might want to include fields for that, and so on. 
But to keep this simple, we'll just have it run like a gas car for now, 
and just print the Hybrid keyword. 
Now, we want to go back to the main method, and create some of these instances.

```java  
Car tesla = new ElectricCar("2022 Red Tesla Model 3", 568, 75);
runRace(tesla);
```

First, we'll create an instance of an Electric Car, and we'll make it a 2022 Tesla Model 3, 
and say that it can go 568 kms on a battery that has a size of 75 kWh. And we run this:

```java  
Car -> startEngine
Car -> driving, type is Car
Car -> runEngine
Gas -> All 6 cylinders are fired up, Ready!
Car -> driving, type is GasPoweredCar
Gas -> usage exceeds the average: 15,40
BEV -> switch 75 kWh battery on, Ready!
Car -> driving, type is ElectricCar
BEV -> usage under the average: 568,00
```

We can see the output for these statements. 
And the output is telling us that the methods we overrode for the electric car 
were the ones that got executed. 
This means the behavior for the electric car, the Battery Electric Vehicle, BEV, 
is different from the GasPoweredCar, or a generic car. That's what we wanted, 
so that's good. 
Lastly, let's test out the hybrid car. We'll set up a Ferrari SF90 Stradale, 
which has eight cylinders, and a much smaller battery than an Electric Car, and in this case, 
it's 8 kWh.

```java  
Car ferrariHybrid = new HybridCar("2022 Black Ferrari SF90 Stradale", 16, 8 , 8);
runRace(ferrariHybrid);
```

Remember, this constructor takes the description, the average kms per liter, 
which is about 16, the number of cylinders which is 8, and finally the battery, 
so 8 for that too. 
And again, if we run that,

```java  
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
```

We now see that the last car is a HybridCar. 
There are 2 steps to starting up, eight cylinders are fired up, and the 8 kWh battery is switched on. 
And that was the challenge. 
In this example, we created a polymorphic method on the Main class.
This method only knew about the Car class, and not about any of its subclasses. 
And it could be executed for many different types as we showed. 
