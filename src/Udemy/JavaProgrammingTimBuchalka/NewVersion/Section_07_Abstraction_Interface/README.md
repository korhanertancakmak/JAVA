# [Section-7: Abstraction in Java]()

## [a. Generalization And Abstraction]()
<div align="justify">

In this section, we'll be talking about abstraction and generalization. 
These concepts, in action, reduce the amount of code we have to write, 
and encourage extensible and flexible code. 
When I say code is extensible, I mean it can support future enhancements and changes,
with little or no effort. 
An extensible application is designed with change in mind. 
In this section of the course, we'll be looking at Java's support for these two concepts.

We use the terms "Abstraction" and _Generalization_ 
when we start trying to model real world things in software. 
Before I launch into interface types and abstract classes, 
I want to talk about what these concepts mean. 
When you start modeling objects for your application, 
you start by identifying what features and behavior your objects have in common. 
We generalize when we create a class hierarchy.
A base class is the most general class, the most basic building block, 
which everything can be said to have in common.

Part of generalizing is using abstraction. 
You can generalize a set of characteristics and behavior into an abstract type. 
If you consider an octopus, a dog, and a penguin, 
you would probably say they're all animals. 
An animal is really an abstract concept. 
An animal doesn't really exist, except as a way to describe a set of more specific things. 
If you can't draw it on a piece of paper, it's probably abstract.
An animal would be hard to draw, for example, 
if you weren't given more information about the actual type of animal to draw. 
Abstraction simplifies the view of a set of items' traits and behavior, 
so we can talk about them as a group, as well as generalize their functionality. 
Java supports abstraction in several different ways:

* Java allows us to create a class hierarchy, where the top of the hierarchy, 
the base class, is usually an abstract concept, whether it's an abstract class or not.
* Java lets us create abstract classes.
* Java gives us a way to create interfaces.
</div>

### Abstract Method
<div align="justify">

An abstract method has a method signature and a return type, but doesn't have a method body. 
Because of this, we say an abstract method is _unimplemented_. 
Its purpose is to describe behavior, which any object of that type will always have. 
Conceptually, we can understand behaviors like move or eat on an Animal, 
so we might include those as abstract methods, on an abstract type. 
You can think of an abstract method as a contract. 
This contract promises that all subtypes will provide the promised functionality, 
with the agreed upon name and arguments.
</div>

### Concrete Method
<div align="justify">

A concrete method has a method body, usually with at least one statement. 
This means it has operational code that gets executed under the right conditions. 
A concrete method is said to implement an abstract method if it overrides one. 
Abstract classes and interfaces can have a mix of abstract and concrete methods.
</div>

### Method Modifiers
<div align="justify">

I've already covered access modifiers and what they mean for types, as well as members of types. 
And we know we have public, protected, package, and private access modifiers as options for the members. 
In addition to access modifiers, methods have other modifiers, 
which we'll list here, as a high-level introduction:

| Modifiers    | Purpose                                                                                                                                                                                                                                                                                                                     |
|--------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| abstract     | When you declare a method abstract, a method body is always omitted. An abstract method can only be declared on an abstract class or an interface.                                                                                                                                                                          |
| static       | Sometimes called a class method, rather than an instance method, because it's called directly on the Class instance and does not access any single instance of the class.                                                                                                                                                   |
| final        | A method that is final cannot be overridden by subclasses.                                                                                                                                                                                                                                                                  |
| default      | This modifier is only applicable to an interface.                                                                                                                                                                                                                                                                           |
| native       | This is another method with no-body, but it's very different from the abstract modifier. The method body will be implemented in platform-dependent code, typically written in another programming languagesuch as C. This is an advanced topic and not generally commonly used, and we won't be covering it in this course. |
| synchronized | This modifier manages how multiple threads will access the code in this method.                                                                                                                                                                                                                                             |
</div>

### Abstract Class
<div align="justify">

Consider these sentences:

* We adopted a new pet this weekend.
* I ordered something I really wanted from the store.
* I bought a ticket and won a prize.

If we said any of these things to a friend or coworker, it might be frustrating for them. 
We haven't given them enough information in any of these cases. 
In other words, they can't paint a picture in their head because they lack details. 
New pet, something ordered, and a ticket is too general when talking about one item. 
On the other hand, when we talk about _groups of things_, 
we don't usually need too many specifics. 
Consider these sentences:

* I need to get home to feed the animals.
* I'm waiting for my box of stuff from an online store to be delivered.

So here, _animals_, and _stuff_, are probably enough information to fully describe the situation.
The abstract class is declared with the **abstract** modifier. 
Here we are declaring an abstract class called Animal.

```java  
abstract class Animal() {}      // An abstract class is declared with the abstract modifier
```

An **abstract** class is a class that's **incomplete**. 
For that reason, You can't create an instance of an abstract class.

```java  
Animal a = new Animal();        // INVALID, an abstract class never gets instantiated.
```

But an abstract class can still have a constructor, which will be called by its subclasses,
during their construction.
An abstract class's purpose is to define the behavior; its subclasses are required to have, 
so it always participates in _inheritance_. 
For the examples below, assume that Animal is an abstract class. 
Classes extend abstract classes, and can be concrete.

```java  
class Dog extends Animal {}     // Animal is abstract, Dog is not
```

Here, Dog extends Animal, Animal is abstract, but Dog is concrete. 
A class that extends an abstract class can also be abstract itself, 
as I show with this next example.

```java  
abstract class Mammal extends Animal {}         // Animal abstract, Mammal is also an abstract
```

Mammal is declared abstract, and it extends Animal, which is also abstract. 
And finally, an abstract class can extend a concrete class.

```java  
abstract class BestOfBreed extends Dog {}       // Dog is not abstract, but BestOfBreed is
```

Here we have BestOfBreed, an abstract class, extending Dog, which is concrete.
</div>

### Abstract Method
<div align="justify">

An abstract method is declared with the modifier **abstract**. 
You can see below that we're declaring an abstract method called move, with a void return type. 
It simply ends with a semicolon. 
It doesn't have a body, not even curly braces.

```java  
abstract class Animal() {
    public abstract void move();
}
```

Abstract methods can only be declared on an abstract class or interface. 
An abstract method tells the outside world that all Animals will move, in the example we show here. 
Any code that uses a subtype of Animal knows it can call the move method, 
and the subtype will implement this method with this signature. 
This is also true for a concrete class, and a concrete method that's overridden. 
You might be asking, what's the difference, and when would you use an abstract class.

![image01]()

In the course of inheritance, we created a very basic Animal class, 
and then we extended it to create a Dog. 
Here, I show you the class diagram from that lecture again.

In that example, Animal was a concrete class, so the move and makeNoise methods had code in their method bodies. 
Subclasses have choices when they extend a concrete class with concrete methods.
* They can inherit the same behavior from their parent. 
This means they don't have to even declare the methods in their class bodies.
* They can override the behavior from their parents. 
This means they have a method with the same signature, 
but with their own code in there, ignoring the parent's code altogether.
* They can also override the behavior but leverage the parent's method code 
by calling the parent's method, using super in their overridden code.

But what happens if an Animal is declared as abstract, 
and the move and makeNoise methods are also abstract? 
If an Animal is abstract and its methods are abstract,
subclasses no longer have the options we just talked about. 
There is no concrete method for a subclass to inherit code from. 
Instead, the subclass must provide a concrete method for 
any abstract method declared on its parent. 
The subclass won't compile if it does not implement the abstract methods.
Why would this behavior be preferred to a concrete class? 
There may be times when it really doesn't make any sense at all, 
for a base class to provide default behavior for a method. 
This mechanism forces designers of the subclasses to provide 
the implementation that's appropriate to the class they're designing. 
Now let's build this new version of our model. 
I'll create a new class in this package, called Animal.
But I'm going to add the abstract modifier in this case, after the public access modifier. 
You don't have to declare the modifiers in this order, 
but it's common practice to have the access modifier be declared first. 
An abstract class can have inherited fields just like a concrete field, 
so I'll add three: _type_, _size_ and _weight_.

```java  
public abstract class Animal {

    protected String type;
    private  String size;
    private double weight;

    public Animal(String type, String size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }
}
```

I've made _type_ protected, but size and weight private. 
This means subclasses can access type directly, without a getter method. 
And I'll set up a constructor, by having IntelliJ generate it, using all three fields.
And now, I'll set up the two methods on the diagram, but this time, 
I'm going to make those methods abstract.

```java  
public abstract void move(String speed);
public abstract void makeNoise();
```

Here, I'm using abstract in the declaration of the methods, 
again after the public access modifier. 
After the method signature, I end with a semicolon. 
We don't even specify an empty method body, meaning a set of curly braces { }. 
Actually, let's see what happens if I do that. 
I'll add a set of curly braces to the first method, inserting them, 
before the ending semicolon.

```java  
public abstract void move(String speed) {};
```

And you can see we've got an error there, and IntelliJ tells us 
that _Abstract methods cannot have a body_. 
So including the curly braces is really including a method body, 
even though there's no code in it. 
It's an empty code block, or empty method body, but it's still a method body, 
and abstract methods can't be declared with a method body. 
So let's revert that change.

When we declare abstract method, this means we don't provide any 
default behavior for our subclasses. 
And we're actually forcing subclasses to declare and implement these methods. 
Another thing you can't do is have an abstract method that's private. 
Let's try that next.

```java  
private abstract void move(String speed);
```

And that gives us a different error, _Illegal combination of modifiers: **abstract** and **private**_. 
Can you understand why this combination would be illegal? 
When we use abstract, we're saying subclasses have to declare this method. 
But if it's private, the subclass wouldn't even see it. 
I'll revert that change, so the Animal class compiles. 
Let's create the Dog class now, in the same package.

```java  
public class Dog extends Mammal {

    @Override
    public void move(String speed) {
    }

    @Override
    public void makeNoise() {
    }
}
```

And I'll add extends Animal there, meaning Dog inherits from Animal, or Dog is an Animal.
So we have an error on that class declaration, says _Class **Dog** must either be declared 
abstract or implement abstract method **move** in 'Animal'_. 
Actually, there are two methods there that we need to implement, 
but this little error message is only popping up the first one. 
And still hovering my mouse over that, let me show you that IntelliJ 
has a selectable option in that popup, _Implement methods_. 
I'm going to pick that, which will bring up this next popup, 
and I want to select both the methods there.

And that generates the methods, with the same signature as Animal's abstract methods, and empty code blocks.
You'll notice we still have an error there, 
_There is no default constructor available in '**Animal**'_. 
We didn't create a default constructor (meaning a no argument constructor) in Animal. 
And because of that, we're forced to create a constructor; 
that will in turn call the explicit constructor we created on Animal. 
Using again IntelliJ's options, and creating constructor matching super,

```java  
public Dog(String type, String size, double weight) {
    super(type, size, weight);
}
```

I want you to think about what this means for a minute. 
Even though our abstract class won't ever be instantiated, 
a constructor we declared on it must be called by every subclass constructor. 
This means we're forcing subclasses to also use this constructor. 
Are you starting to get a picture of the abstract class, as a much stricter parent? 
The abstract class can make subclasses follow its rules. 
Truthfully, any parent can, but an abstract class never gets instantiated,
so you have more freedom in building the rules on this kind of class. 
And well-behaved classes are a good idea.

In the inheritance example, we had Dog implemented with its own methods, 
like bark, run and wagTail. 
And we could still do that, but for the sake of time, I won't be implementing these extra methods. 
I'll just make the code, in these overridden methods, pretty simple. 
First, I'll implement makeNoise.

```java  
@Override
public void makeNoise() {

    if (type == "Wolf") {
        System.out.print("Howling! ");
    } else {
        System.out.print("Woof! ");
    }
}
```

Here, we can directly access type, a field on the abstract class, 
because we made that field protected. 
We didn't create any getters or setters on the Animal class. 
And this protected modifier lets subclasses use the field directly, 
as we show in this if condition. 
And again, if the type is a wolf, the noise will be howling, 
otherwise the noise will be a **Woof**. 
Now notice, if we do nothing else, our Dog class works. 
Even though the abstract class is forcing us to create a method named move in our class,
we don't have to put statements in that block. 
This is probably not what you'd do in a real application, but it's an option 
if that method really makes no sense for your class. 
Let's go now to the main method, and see what we can do with Animal and Dog. 
I'll create an Animal variable.

```java  
Animal animal = new Animal("animal", "big", 100);
```

And this won't compile. 
Error saying _**Animal** is abstract; cannot be instantiated_. 
Animal is abstract, and even though we have a constructor and fields,
we can't create an instance of an abstract class. 
This is another advantage of an abstract class. 
What is an animal after all, that you'd want a real instance of one? 
So let's comment that code out, and create a Dog instead.

```java  
Dog dog = new Dog("Wolf", "big", 100);
dog.makeNoise();
```

Ok, so we have a Dog variable, and create a new instance of Dog, 
passing Wolf as the type, big as the size, and 100 as the weight. 
And we call makeNoise on the dog object. Running that,

```java  
Howling!
```

Because we made the type of wolf, this dog is howling. 
This is all well and good, but it doesn't really explain 
why we'd build a hierarchy with an abstract class. 
Where this becomes interesting, is when we can use Dog 
anywhere an Animal is used. 
Let me create a method on this Main class. 
I'm going to call id doAnimalStuff.

```java  
private static void doAnimalStuff(Animal animal) {
    animal.makeNoise();
    animal.move("slow");
}
```

I've said that you can't create an instance of Animal because it's abstract. 
That's true, but that doesn't prevent us from using that type in methods, 
declarations, and lists. 
In fact, this is what makes the code so flexible and scalable. 
The abstract class told the world that animals move and make noise, 
so anyone can use an Animal class, and call those methods on the object 
that it gets at runtime. 
And regardless of what that object is, as long as it's an Animal, 
meaning it has the Animal class in its hierarchy, this code will continue to work. 
This is an abstraction, promoting polymorphism techniques.
I'll add a call to this in the main method.

```java  
doAnimalStuff(dog);
```

And when I run that:

```java  
Howling! Howling!
```
                    
We see that our dog howled twice, and yet didn't do anything for moving. 
Let's put some simple code in that method, on the dog class.

```java  
@Override
public void move(String speed) {

    if (speed.equalsIgnoreCase("slow")) {
        System.out.println(type + " walking");
    } else {
        System.out.println(type + " running");
    }
}
```

And running the code again,

```java  
Howling! Howling! Wolf walking
```
                    
See that even though we called move on the Animal class, 
in the Main Class's doAnimalStuff method, and that class 
didn't have any code in it for the move method, we get Wolf Walking. 
This is really calling Dog's move method, because we passed a dog 
instance to this method. 
And for good measure, let's create a fish class. 
For this, I'm just going to copy Dog.

```java  
public class Fish extends Animal {
    public Fish(String type, String size, double weight) {
        super(type, size, weight);
    }
}
```

We now have a Fish class that extends Animal, and it's just copied over 
the move and makeNoise method, which I want to change. 
First the move method.

```java  
public class Fish extends Animal {
    public Fish(String type, String size, double weight) {
        super(type, size, weight);
    }
}
```

So fish, if they're unafraid, have a nice slow lazy way of gliding through the water. 
But if they're threatened, they might dart behind something. 
And now I'll change the makeNoise method.

```java  
@Override
public void makeNoise() {
    if (type == "Goldfish") {
        System.out.print("swish ");
    } else {
        System.out.print("splash ");
    }
}
```

Although fish may not make noises in general, I'll just put something in here. 
So Goldfish will swish, and everything else will splash. 
Going back to the main method, what I want to show you next,
what I want you to appreciate, when you code, 
is that writing code using the more generic or abstract type, saves you a lot of effort. 
Let's create an ArrayList of Animals now.

```java  
ArrayList<Animal> animals = new ArrayList<>();
```

And we do this by passing the type parameter as Animal. 
If we used Dog or Fish, we'd have to have two array lists.
But if we type it with Animal, this list can hold any kind of Animal, 
and that includes both Fish and Dog. 
Again, this feature is not unique to an abstract class, 
it's just a benefit of inheritance, in this case. 
Now I'll add some animals to this list.

```java  
animals.add(dog);
animals.add(new Dog("German Shepard", "big", 150));
animals.add(new Fish("Goldfish", "small", 1));
animals.add(new Fish("Barracuda", "big", 75));
animals.add(new Dog("Pug", "small", 20));
```

In this code, we use the add method on the array list, 
and can pass any instance that inherits from animal. 
You can see I've added a number. 
Next, I'll add an enhanced for loop.

```java  
for (Animal animal : animals) {
    doAnimalStuff(animal);
}
```

Notice that loop variable is an Animal. 
Remember, we can do this for any object that is an Animal. 
And then we call doAnimalStuff, passing the animal in our array list. 
And now if we run this,

```java  
Howling! Wolf walking
Woof! German Shepard walking
swish Goldfish lazily swimming
splash Barracuda lazily swimming
Woof! Pug walking
```

We see output for all our different types of dogs and fish. 
So that's nice, that we can have an array list of an abstracted type. 
And at runtime, instances that inherit from that class 
can use polymorphism to execute code specific to the concrete type.

Eventually, our abstract class had protected and private fields, and two abstract methods. 
But an abstract class can also have concrete methods. 
Let's say we wanted a helper method on Animal, that printed out both the instance's class name, 
and its type. 
Going to the Animal Class, I'll add that.

```java  
public String getExplicitType() {
    return getClass().getSimpleName() + " (" + type + ")";
}
```

Now we have a concrete method, on an abstract class. 
And it has a body. 
We get the class's simple name, and print that with the type that was passed as the field. 
And like any method in a parent class, that's a method we can use in the subclasses. 
So going to Dog, and the move method.

```java  
@Override
public void move(String speed) {

    if (speed.equalsIgnoreCase("slow")) {
        System.out.println(getExplicitType() + " walking");
    } else {
        System.out.println(getExplicitType() + " running");
    }
}
```

In both cases, where I was printing out the type, I can make a call _getExplicitType_, 
which is the method this class inherits from Animal. 
And I want to do that same thing on the Fish class, and its _move_ method.

```java  
@Override
public void move(String speed) {

    if (speed.equalsIgnoreCase("slow")) {
        System.out.println(getExplicitType() + " lazily swimming");
    } else {
        System.out.println(getExplicitType() + " darting frantically");
    }
}
```

I'll change the type to a call to getExplicitType. 
Ok, so now I'm going to run this by going back to the main method.
And running,

```java  
Woof! Dog (German Shepard) walking
swish Fish (Goldfish) lazily swimming
splash Fish (Barracuda) lazily swimming
Woof! Dog (Pug) walking
```

We can see we have the simple class name, Dog or Fish, 
and the type of the dog or fish in parentheses. 
Your abstract class can provide concrete methods, as we showed here, 
that follow the same rules as a concrete class's method. 
Our subclasses can simply use them, as we did here, inheriting that behavior from the parent. 
We could also override those methods. 
But let's say Animal is a strict parent indeed, 
and doesn't want us to override this method. 
It likes this format and wants all classes to use it, just the way it's written. 
Going back to the Animal class, I'll add the modifier final.

```java  
public final String getExplicitType() {
    return getClass().getSimpleName() + " (" + type + ")";
}
```

And the convention has that again, being declared after the access modifier, 
public in this case. 
If I ran this code, the behavior would be exactly the same. 
But if we went to our Dog class, and we decided we want to create our own version
of _getExplicitType_, we actually can't do it. 
Then going to Dog class,

```java  
@Override
public String getExplicitType() {
    return "";
}
```

Looking at control+o options, notice, it's not an option to override this method in the list shown there. 
But let's try anyway. 
This is the syntax for overriding a method from the parent class, but look, we've got an error.

```java  
'getExplicitType()' cannot override 'getExplicitType()' in '....Animal'; overridden method is final
```
        
We can see that overridden method is final, and we can't do this. 
I haven't really talked about the final keyword very much, but it simply means 
for a method that a subclass can't override it. 
So the final method is final and cannot be overridden. 
Again, this isn't specific to an abstract class, 
but I wanted to show you how an abstract class can have both concrete 
and abstract methods. 
And the concrete methods can be designed, so that the subclass can't change them.
I'll comment this method so that the code compiles. 
There are two other things I want to cover, and I showed this on earlier courses, 
First I want to create another abstract class that extends Animal. 
I'll do that in the Animal.java source file.

```java  
abstract class Mammal extends Animal {
    public Mammal(String type, String size, double weight) {
        super(type, size, weight);
    }
}
```

Here, I'm declaring an abstract class, Mammal, that extends the abstract class, Animal. 
Now, we're getting an error on this class because of the constructor, 
so I'll include the constructor using code generation.

Now, this code compiles. 
But wait a minute. 
We weren't forced to create implementations for the move and makeNoise methods, 
like we were for the Dog and Fish class. 
Why not?

**NOTE**: An Abstract class doesn't have to implement abstract methods!

An abstract class that extends another abstract class has some flexibility:
* It can implement all the abstract methods of its parent.
* It can implement some of them.
* Or it can implement none of them.
* It can also include additional abstract methods, 
which will force subclasses to implement both Animal's abstract methods 
and Mammal's abstract methods.

Ok, so first, let's create a new class in the same package, and I'll call it Horse.

```java  
public class Horse extends Mammal{
    public Horse(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void makeNoise() {

    }

    @Override
    public void shedHair() {

        System.out.println(getExplicitType() + " sheds in the spring");
    }
}
```

And I'll have that extend Mammal. 
And we see right away this doesn't compile. 
It's saying we have to implement move on Animal, 
even though we're inheriting from Mammal. 
This is because Mammal didn't include an implementation for the move method. 
Let's add that on the Mammal class, using implement methods.

```java  
@Override
public void move(String speed) {

    System.out.print(getExplicitType() + " ");
    System.out.println(speed.equals("slow") ? "walks" : "runs");
}
```

And now, I'll just put something in there. 
So first, we'll print out the explicit type, and then for mammals, 
if the speed is slow, we'll print walks, otherwise runs. 
And now I'm going to add an abstract method, specific for mammals, like shedHair.

```java  
public abstract void shedHair();
```

And again, we just enter the method signature, 
remembering to include the abstract modifier there. 
And we follow that with a semicolon. 
This is the structure of an abstract method. 
And going back to Horse, we still have that error, 
but it's specifying that now, we need to implement the method shedHair on Mammal. 
And I'll implement all the methods left now.

```java  
@Override
public void shedHair() {

    System.out.println(getExplicitType() + " sheds in the spring");
}
```

And now the Horse class is valid, but let's actually put something in there for shedHair.
I'll print out the type with a message. 
And for good measure, I want to create a Horse in the main method.

```java  
animals.add(new Horse("Clydesdale", "large", 1000));
for (Animal animal : animals) {
    doAnimalStuff(animal);
    if (animal instanceof Mammal currentMammal) {
        animal.shedHair();
    }
}
```

After commenting the for loop to rewrite it after the code we add, and running this code:

```java  
Horse (Clydesdale) walks
```
                
We see that our horse is included now in the output. 
But how would we call the shedHair method? 
Well, maybe you remember instanceof method, we introduce you to earlier. 
I'll use it here. 
So in the if loop, we know the _animal_ is an _Animal_, 
but it could be a lot of things at this point, like Dog, Fish or Horse. 
But it could also be a Mammal, a type of Mammal. 
And we should always try to code generically, so that when new subclasses are added, 
it won't be disruptive to our code. 
In the if loop, we use the pattern matching version of the instanceof operator. 
We check if our loop variable, currentAnimal, is a mammal. 
If that's true, we'll get a mammal variable, named currentMammal, 
which gets assigned the loop element, only if it's really a Mammal subtype. 

```java  
animals.add(new Horse("Clydesdale", "large", 1000));
for (Animal animal : animals) {
    doAnimalStuff(animal);
    if (animal instanceof Mammal currentMammal) {
        //animal.shedHair();
        currentMammal.shedHair();
    }
}
```

Doing this means we can call any method on currentMammal, 
that's declared on the Mammal abstract class, 
in addition to the ones on Animal. 
And running this,

```java  
Horse (Clydesdale) sheds in the spring
```
                
We can see since Horse is the only class that extends Mammal 
it prints out _Horse (Clydesdale) sheds in the spring_. 
Notice what happens if we try to call shedHair on the animal variable.

```java  
animal.shedHair();
```
                
This code won't even compile. 
That's because animal is an Animal type, and the method shedHair's not in the Animal class.
I want to make one more change. 
I want to change **Dog**, to extend Mammal instead of Animal.

```java  
public class Dog extends Mammal {
}
```
                
Hopefully, you're not surprised we get an error here, 
and that's because Mammal has that abstract method, shedHair. 
And this class, Dog, doesn't have a method for that. 
So I need to implement shedHair.

```java  
@Override
public void shedHair() {
    System.out.println(getExplicitType() + " shed hair all the time");
}
```

And then I'll just add a line of code in that generated method. 
So every dog I've ever had pretty much shed hair all year long, 
so I'll put that there. 
So go to the main method and run this code:

```java  
@Override
public void shedHair() {
    System.out.println(getExplicitType() + " shed hair all the time");
}
```

And running the code again,

```java  
Howling! Howling! Dog (Wolf) walking
Howling! Dog (Wolf) walking
Dog (Wolf) shed hair all the time                       <<<<<<<<<
Woof! Dog (German Shepard) walking
Dog (German Shepard) shed hair all the time             <<<<<<<<<
swish Fish (Goldfish) lazily swimming
splash Fish (Barracuda) lazily swimming
Woof! Dog (Pug) walking
Dog (Pug) shed hair all the time                        <<<<<<<<<
Horse (Clydesdale) walks
Horse (Clydesdale) sheds in the spring
```
                
You can see that for all our dogs; we're getting that they shed hair all the time. 
So that's an example of an abstract class, extending another abstract class. 
I won't cover the use case where we have an abstract class, that extends a concrete class.
It's a lot rarer to do something like that, but just be aware that that's an option. 
You might be asking, why use an Abstract Class?

In truth, you may never need to use an abstract class in your design, 
but there are some good arguments for using them. 
An abstract class in your hierarchy forces the designers of subclasses to think about, 
and create unique and targeted implementations for the abstracted methods. 
It may not always make sense to provide a default, or inherited implementation, 
of a particular method. 
An abstract class can't be instantiated, so if you're using abstract classes 
to design a framework for implementation, this is definitely an advantage.

In our example, we don't really want people creating instances of Animals or Mammals. 
We used those classes to abstract behavior, at different classification levels. 
All Animals have to implement the move and makeNoise methods, but only Mammals 
needed to implement shedHair, as we demonstrated.
</div>

### [Abstract Challenge]()
<div align="justify">

In this challenge, you need to build an application 
that can be a storefront for any imaginable item for sale.
Instead of the Main class we usually create, create a Store class with a main method. 
The Store class should:
* manage a list of products for sale, including displaying the product details.
* manage an order, which can just be a list of OrderItem objects.
* have methods to add an item to the order, and print the ordered items, 
so it looks like a sales receipt.

Create a **ProductForSale** class that should have at least three fields: 
a **type**, **price**, and a **description**, and should have methods to:
* get a Sales Price, a _concrete method_, which takes a _quantity_,
and _returns the quantity times the price_.
* print a Priced Line Item, a _concrete method_, which takes a _quantity_, 
and should _print an itemized line item_ for an order, with _quantity and line item price_.
* show Details, an _abstract method_, which represents 
what might be _displayed_ on a product page, _product type_, _description_, _price_, 
and so on.

Create an _OrderItem_ type that has at a minimum two fields, _quantity_
and a _Product for Sale_. 
You should create _two or three classes that extend the ProductForSale class_, 
that will be products in your store.



</div>


<div align="justify">


</div>