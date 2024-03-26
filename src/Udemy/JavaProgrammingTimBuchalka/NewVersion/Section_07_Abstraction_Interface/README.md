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

| Modifiers    | Purpose                                                                                                                                                                                                                                                                                                                      |
|--------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| abstract     | When you declare a method abstract, a method body is always omitted. An abstract method can only be declared on an abstract class or an interface.                                                                                                                                                                           |
| static       | Sometimes called a class method, rather than an instance method, because it's called directly on the Class instance and does not access any single instance of the class.                                                                                                                                                    |
| final        | A method that is final cannot be overridden by subclasses.                                                                                                                                                                                                                                                                   |
| default      | This modifier is only applicable to an interface.                                                                                                                                                                                                                                                                            |
| native       | This is another method with no-body, but it's very different from the abstract modifier. The method body will be implemented in platform-dependent code, typically written in another programming language such as C. This is an advanced topic and not generally commonly used, and we won't be covering it in this course. |
| synchronized | This modifier manages how multiple threads will access the code in this method.                                                                                                                                                                                                                                              |
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

![image01](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_07_Abstraction_Interface/images/image01.png?raw=true)

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
I won't cover the use case where we have an abstract class that extends a concrete class.
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

## [b. Abstract Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_07_Abstraction_Interface/Course02_Abstract_Challenge/README.md#abstraction-challenge)
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

## [c. Interfaces]()
<div align="justify">

We saw that an abstract class requires its subclasses to implement its abstract methods. 
An "interface" is similar to an abstract class, although it "isn't a class" at all. 
It's a "special" type, that's more like a "contract" between the class and client code, 
that the compiler enforces. 
By declaring it is using an interface, your class must implement all the abstract methods on the interface. 
A class agrees to this because it wants to be _known by that type_, 
by the outside world, or the client code. 
An "interface" lets "classes that might have little else in common", 
be recognized as a special reference type.

Declaring an interface is similar to declaring a class, using the keyword _interface_, 
where you would use _class_. 
Below, I'm declaring a public interface named FlightEnabled.

```java  
public interface FlightEnabled {}
```

An interface is usually named according to the set of behaviors it describes. 
Many interfaces will end in **able**, like Comparable, and Iterable, 
again meaning something is capable, or can do, a given set of behaviors.

A class is associated to an interface, by using the _implements_ clause in the class declaration. 
In this example, the class Bird implements the FlightEnabled interface.

```java  
public class Bird implements FlightEnabled {}
```

Because of this declaration, we can use FlightEnabled as the reference type, 
and assign it an instance of bird.

```java  
FlightEnabled flier = new Bird();
```

In this code sample, we create a new Bird object, but we assign it to the 
FlightEnabled variable named flier. 
Let's look at this interesting type in some code. 
I'm going to first create an abstract class in the same package with Main and name it Animal. 
Once created, I need to manually add the keyword abstract to it.

```java  
interface OrbitEarth extends FlightEnabled {
    void achieveOrbit();
}

interface FlightEnabled {
}
```

I'll include two interfaces in the same source file. 
Next, I want a new class named Bird, in the same package.

```java  
public class Bird extends Animal implements FlightEnabled, Trackable{
}
```

And I'll extend the Animal Class and implement both interfaces. 
What's important to see from this declaration is that 
we can use both extends and implements in the same class declaration.

**NOTE**: A class can use "extends" and "implements" in the same declaration

A class can only _extend_ a _single class_, which is why Java is called single inheritance. 
But a class can _implement many interfaces_. 
This gives us _plug and play functionality_, which is what makes them so powerful. 
A class can _both extend_ another class, and _implement_ one or more interfaces.
In this example, the Bird class extends, or inherits from Animal, 
but it's implementing both a FlightEnabled, and Trackable interface. 
We can describe Bird by what it is, and what it does.

Now that we have this Bird class, I want to get back to the main method, 
and create an instance of this class.

```java  
public class Main {
    public static void main(String[] args) {

        Bird bird = new Bird();
        Animal animal = bird;
        FlightEnabled flier = bird;
        Trackable tracked = bird;
    }
}
```

Ok, so this is the usual way to create a new instance of bird, 
and assign it to a reference variable, bird. 
But we can say a Bird is an Animal, and it's also FlightEnabled, and Trackable. 
That means I can assign my bird object to different reference types, which I'll do now. 
First, I'll assign the bird object to Animal. 
Then FlightEnabled. 
And then Trackable.

A bird, as it turns out, can be referred to by these four different types. 
I've got all this setup, but none of it's very interesting without adding any methods.
I'll start by going back to the Animal abstract class. 
And add an abstract method there, and I'll call that move.

```java  
public abstract class Animal {
    public abstract void move();
}
```

Remember that an abstract method on an abstract class has to be declared with the abstract keyword. 
This causes our Bird class to have a compiler error 
because we haven't implemented that method on Bird yet. 
Going to Bird.java source file,

```java  
@Override
public void move() {
    System.out.println("Flaps wings");
}
```

Let me produce the default implementation by using IntelliJ tools. 
Let's put in a print statement so that we know when the method is invoked. 
Going back to the main method, I want to call this new method on some of our variables.

```java  
animal.move();
flier.move();
tracked.move();
```

The First call to move will be the Animal reference. 
Then FlightEnabled. 
And then Trackable. 
Note that only the first call to move compiles, the other 2 don't. 
I've talked about this, and I want to review this again, 
so you are clear on why this is happening. 
The type you use, meaning the variable's declared type, 
determines which methods you can call in your code. 
When we assign the bird object to FlightEnabled and Trackable variables, 
those types don't have a move method on them, so this is why we get this error. 
The compiler only cares about the declared type. 
I'll comment our those last two lines. 

```java  
animal.move();
//flier.move();
//tracked.move();
```

And running that:

```java  
Flaps wings
```

We see that this animal, which is really a bird instance, flags its wings. 
Ok, now I want to add some methods related to take-off, land, 
and flying on that FlightEnabled interface.

```java  
interface FlightEnabled {
    void takeOff();
    void land();
    void fly();
}
```

We have three methods here, and you can see this code compiles. 
None of these methods have a method body, 
but we haven't used the abstract modifier on the fly method, 
so why is this still ok? 
Why does it compile?

**NOTE**: The abstract modifier is implied on an interface.

We don't have to declare the interface type abstract 
because this modifier is implicitly declared for all interfaces.

```java  
abstract interface FlightEnabled {      // abstract modifier here is unnecessary
}                                       // and redundant
```

Likewise, we don't have to declare any method abstract. 
In fact, any method declared without a body is really 
_implicitly declared both public and abstract_. 
The three declarations shown below, result in the same thing, 
under the covers:

```java  
public abstract void fly();         // public and abstract modifiers are redundant, meaning unnecessary to declare
abstract void fly();                // abstract modifier is redundant, meaning unnecessary to declare
void fly();                         // This is PREFERRED declaration, public and abstract are implied.
```

If I hover over the public modifier in the first statement, 
you can see the message, _Modifier '**public**' is redundant for interface members_. 
I'll mouse over the abstract modifier in the second statement, 
and this time, _Modifier **abstract** is redundant for interface methods_. 
So these declarations are all the same. 
But now, if I change _public_ to _protected_, in that first statement:

```java  
protected abstract void takeOff();
```

We do get a compiler error because modifier protected not allowed here.

**NOTE**: All members on an interface are implicitly public.

* If we omit an access modifier on a _class member_, it's _implicitly package private_. 
* If we omit an access modifier on an _interface member_, it's _implicitly public_. 

This is an important difference, and one you need to remember. 
Changing the access modifier of a method to **protected**, on an interface, 
is a **compiler error**, whether the method is concrete or abstract. 
Only a concrete method can have private access. 
Let's go back to the code and remove those modifiers. 
And while we're in this source file, let's add a method to the Trackable interface.

```java  
interface  Trackable {
    void track();
}
```

I'll call this one track. 
What's wrong with this code? 
Well, every method on an interface is assumed to be public and abstract, 
unless otherwise specified, and in this case we've included a method body. 
The method body means something is wrong. 
Either this method is missing a more meaningful body, or the method body shouldn't be here. 
I'll be talking about concrete methods on an interface in a little bit. 
But for now, I'll remove those curly braces and replace it with a semicolon.

What good is a type that has methods that don't do anything? 
Why is this such a powerful thing? 
Let's get back to the Bird class to find out why.

```java  
public class Bird extends Animal implements FlightEnabled, Trackable {

    @Override
    public void move() {
        System.out.println("Flaps wings");
    }

    @Override
    public void takeOff() {
        System.out.println(getClass().getSimpleName() + " is taking off");
    }

    @Override
    public void land() {
        System.out.println(getClass().getSimpleName() + " is landing");
    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName() + " is flying");
    }

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    }
}
```

Similar to the way an abstract class forces its subclasses 
to implement the abstract methods it declares, 
the interface requires any classes that implement it to do the same. 
This class, because it implements these two interfaces, 
now has to implement the three methods on FlightEnabled, takeOff, land, and fly, 
and the one method on Trackable track. 
And like an abstract class, we can do the same by hovering over that error, 
and selecting implement method.
And just like it did for an abstract class, 
IntelliJ generates method shells for all the abstract methods, 
for all the interfaces this class implements. 
To keep this simple, I'm just going to print something out in each of these methods. 
So for the takeOff method, I'll print the class name and a message. 
In the land method, I'll change the same message as landing.
In the fly method, I'll make that is flying. 
And finally, in the track method, I want to print out its coordinates recorded. 
And for the last method, track, if we implemented it in a real application, 
while something is flying or is in transit, this might periodically send 
its coordinates to the application. 
This would make it possible to track the flight path of this bird, for example. 
Let's add calls to these methods in the main method.

```java  
flier.takeOff();
flier.fly();
tracked.track();
flier.land();
```

Running that,

```java  
Bird is taking off
Bird is flying
Bird's coordinates recorded
Bird is landing
```

You can see that all of Bird's methods were called. 
In this code, we have a variable, flier, of type FlightEnabled, 
and when we call any FlightEnabled methods on that variable, 
it executes bird's methods, because bird is our runtime object. 
And the same thing is Trackable. 
We can describe a bird in many ways now, in the code, 
treating it as a member of multiple, and quite different groups.

We executed all the implemented interface methods on the Bird class. 
Now, I want to create another method in the Main class, called inFlight. 
I'll declare it as a private static void with a FlightEnabled parameter.

```java  
private static void inFlight(FlightEnabled flier) {

    flier.takeOff();
    flier.fly();
    if (flier instanceof Trackable tracked) {
        tracked.track();
    }
    flier.land();
}
```

The argument to the method is something that flies, it's a parameter of the FlightEnabled type. 
I'll call the takeOff and fly method on that variable. 
Then, after the flier is flying, we want to track it if it's actually trackable.
To test this, we use the instanceOf operator with pattern matching. 
We've seen and used this before, but it's important
to take note now that this is testing an interface type, Trackable, 
and it still works the same. 
If the object is a class that implements Trackable, 
then a local variable is set up, named tracked, with the type Trackable.
And because of that, we can call the track method on that variable, tracked, 
that the instanceOf operator populated for us. 
So we want to make a call to the inFlight method from the main method.

```java  
inFlight(flier);
```

I'll comment out the last four lines. 
And I'll add a call to inFlight instead. 
And running that,

```java  
Bird is taking off
Bird is flying
Bird's coordinates recorded
Bird is landing
```

We get the output, Bird is taking off, Bird is flying, 
then we get the track method statement, coordinates recorded, 
and finally bird is landing. 
Ok, so why couldn't we just have put all these methods on the abstract class, 
Animal, or a subclass Bird. 
Well, first of all, not all Animals fly, and not even all Birds fly. 
And we don't want all animals or birds to be tracked either. 
There are a lot of different things that fly, and things we might want to track, 
that aren't birds. 
Continuing on, I'm going to create another new class in our package, called Jet.

```java  
public class Jet implements FlightEnabled, Trackable {

    @Override
    public void takeOff() {
        System.out.println(getClass().getSimpleName() + " is taking off");
    }

    @Override
    public void land() {
        System.out.println(getClass().getSimpleName() + " is landing");
    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName() + " is flying");
    }

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    }
}
```

I'll have that class implement the same two interfaces. 
Now, I think you'd agree with me that a bird and jet have 
very little in common besides flying, and maybe having wings. 
But this is the beauty of interfaces.
We can treat a bird and a jet similarly, from the client code's perspective. 
What they actually do is dependent on how they implement the FlightEnabled methods, 
but the client can treat them as if they were the same. 
Let's copy and paste the last four methods we had on bird into jet. 
And going to the main method, I'll call the inFlight method, 
and pass it a new instance of Jet.

```java  
inFlight(new Jet());
```

Running that,

```java  
Jet is taking off
Jet is flying
Jet's coordinates recorded
Jet is landing
```
                    
You can see that we're able to pass both a Jet and a bird, 
to a method that can use them, because both of these classes implement FlightEnabled. 
What's really nice is that you can mix and match interfaces, as you need them. 
To demonstrate this, I'll create another class, this time called Truck in the same package.

```java  
public class Truck implements Trackable{
    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    }
}
```

I'll add implements Trackable. 
The class doesn't compile because we have to implement the track method on it.
I'll copy the track method from Bird and paste it here. 
Ok, so that's a truck, which isn't going to fly, but it might be tracked 
if it's used for moving freight around. 
And we're not going to call inFlight for a truck in the main method,
but we can call the track method.

```java  
Trackable truck = new Truck();
truck.track();
```

I'm calling track method from the truck variable, which we've declared with the Trackable type. 
Running that:

```java  
Truck's coordinates recorded
```
                    
We can see that a Truck can be tracked because it implements the Trackable interface. 
Let's review what we did here.

![image03](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_07_Abstraction_Interface/images/image03.png?raw=true)

An interface lets us treat an instance of a single class as many different types. 
The Bird Class inherits behavior and attributes from Animal 
because we used the extends keyword in the declaration of Bird. 
Because the move method was abstract on Animal, Bird was required to implement it. 
The Bird Class implements the FlightEnabled interface. 
This required the Bird class to implement the takeOff, fly, and land methods, 
the abstract methods on FlightEnabled.

The Bird Class also implements the Trackable interface. 
This required the Bird class to implement the track method, 
which was the abstract method declared on Trackable. 
Because of these declarations, any instance of the Bird class can be treated as a Bird. 
This means it has access to all of bird's methods, including all those from Animal, 
FlightEnabled, and Trackable. 
An instance of Bird can be treated like, or declared as an Animal, 
with access to the Animal functionality, described in that class, 
but customized to Bird.

It can be used as a FlightEnabled type, with just the methods a FlightEnabled type needs, 
but again customized for the Bird. 
Or it can take the form of a Trackable object and be tracked 
with specifics for the Bird class.

![image04](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_07_Abstraction_Interface/images/image04.png?raw=true)

Interfaces let us take objects that may have almost nothing in common, and write reusable code, 
so we can process them all in a like manner. 
Above, you can see that a Jet, a Bird, and a DragonFly are very different entities. 
But because they implement FlightEnabled, we can treat them all as the same type, 
as something that flies, and ignore the differences in the classes. 
Interfaces allow us to type our objects differently, _by behavior only_.

Ok, so I have a few more things I want to cover. 
And this is best done in code, so let's add a field to the FlightEnabled Interface.

Here I've created two fields, both doubles. 
And it looks like I've gone against the naming convention, 
and used all uppercase letters with underscores between them. 
Why did I do this? 
Well, it turns out any fields declared on an interface are not really instance fields. 
They're implicitly public, static, and final. 
Which means they're really constants.
We haven't talked a lot about constants yet. 
We referred to them a little bit when we talked about the enum, but only briefly.
</div>

### The "final" Modifier in Java
<div align="justify">

**When we use the final modifier, we prevent any further modifications to that component.**

* a final method means it can't be overridden by a subclass.
* a final field means an object's field can't be reassigned or given a different value 
after its initialization.
* a final static field is a class field that can't be reassigned, 
or given a different value, after the class's initialization process.
* a final class can't be overridden, meaning no class can use it in the _extends_ clause.
* a final variable, in a block of code, means that once it's assigned a value, 
any remaining code in the block can't change it.
* a final method parameter means we can't assign a different value to that parameter in the method code block.

We'll be covering each of these scenarios in turn, but right now 
I want to talk about the final static field, is what you're really creating, 
when you declare a field on an interface.
</div>

### Constants in Java
<div align="justify">

A constant in Java is a variable that can't be changed. 
A constant variable is a final variable of primitive type, or type String; 
that is initialized with a constant expression. 
Constants in Java are usually named with all uppercase letters, 
and with underscores between words. 
A static constant means we access it via the type name. 
We saw this with the **INTEGER.MAX_VALUE**, and the **INTEGER.MIN_VALUE** fields.

**NOTE**: A field declared on an Interface is always public, static and final.

Java lets us specify these like an ordinary field on an interface, 
which might be kind of confusing and misleading to a new Java programmer. 
But we can declare them with any combination of those modifiers, or none at all, 
with the same result. 
These all mean the same thing on an interface.

```java  
double MILES_TO_KM = 1.60934;
final double MILES_TO_KM = 1.60934;
public final double MILES_TO_KM = 1.60934;
public static final double MILES_TO_KM = 1.60934;
```
                                        
But coding it in IntelliJ, any of the statements, except the first statement 
will give you the information that the modifiers are redundant. 
So let's go try that out. 
Going to the code,
</div>

<div align="justify">


```java  
public static final double MILES_TO_KM = 1.60934;
```

Let's add **public static final** in front of double.
Notice that all 3 of those modifiers are greyed out in IntelliJ,
and if we hover over any one of them, 
we see that "Modifier **public static final** is redundant for interface fields.
So let's revert that last change. 
You can access these fields like you do with a class's static fields, 
using the type name, and then the field name. 
I'll add some code that uses that constant, to convert km to miles. 
Going back to the main method,

```java  
double kmsTraveled = 100;
double milesTraveled = kmsTraveled * FlightEnabled.KM_TO_MILES;
System.out.printf("The truck traveled %.2f km or %.2f miles%n", kmsTraveled, milesTraveled);
```

We start with 100 kms, and we multiply that by the constant on FlightEnabled, 
which is the conversion factor, to go from kms to miles. 
And then we just print out those two values. 
Running that,

```java  
The truck traveled 100,00 km or 62,14 miles
```

We can see that the truck traveled 100 kms, or 62.14 miles. 
So that's fields on interfaces.
It's important to remember that an interface never gets instantiated, 
and won't have a subclass that gets instantiates either. 
There's no such thing as instance fields on an interface because of this. 
You might ask, isn't a bird an instance of FlightEnabled?
No, not really, bird is an instance of a class that implements the methods of FlightEnabled. 
Bird doesn't inherit traits from FlightEnabled, it just agrees to have a certain way.

So we discovered that interfaces only support public static and final fields on them, 
or constants in general. 
We'll continue to look at this unique type.
</div>

### Extending Interfaces
<div align="justify">

Interfaces can be extended, similar to classes, using the _extends_ keyword.

```java  
interface OrbitEarth extends FlightEnabled {}
```
                            
Above, I show an interface, OrbitEarth, that extends the FlightEnabled interface. 
This interface requires all classes to implement both the OrbitEarth 
and the FlightEnabled abstract methods.

```java  
interface OrbitEarth extends FlightEnabled, Trackable {}
```
                            
Unlike a class, an interface can use the _extends_ expression with multiple interfaces.

**Note**: _Implements_ is invalid on an interface.
An interface doesn't implement another interface, so the code below won't compile.

```java  
interface OrbitEarth implements FlightEnabled {}    // INVALID, invalid, interface
```

In other words, implements is an invalid clause in an interface declaration. 
Let's see this in code. 
Above the FlightEnabled interface, I'll add the OrbitEarth interface.

Ok, so we've created the OrbitEarth interface, which extends FlightEnabled, 
and we have one abstract method declared on this interface. 
And that's the achieveOrbit method. 
To keep it simple, above this interface, I'll create a new class called Satellite. 
And I'll make that implement orbit earth. 
And we see that doesn't compile. 
Instead of picking implement methods there, 
I'm just going to copy the method achieveOrbit from the interface just below, 
and paste it into my class.
And I'll just put a statement, in its block, 
that orbit was achieved. 
But I still have a compile error on this method.
If I hover over that, you can see I get an error we've seen before, 
but maybe you've forgotten about.

```java  
achieveOrbit()' in 'Satellite' clashes with 'achieveOrbit()' in
        'OrbitEarth'; attempting to assign weaker access privileges
        ('package-private'); was 'public'
```

Well, even though we copied this method from the interface, 
we didn't copy the implicit public access modifier on that method. 
If you don't use IntelliJ's tools to implement an interface's abstract methods, 
you may discover these kinds of problems. 
Because this method, as I've stated before, is really public on the interface, 
I need to specify public here.
I'll change that. 
Ok, so that fixed that compiler error, 
and we can see that IntelliJ recognizes that we're implementing a method on the interface. 
We can see, though, there's still a problem with this class.
We still have to implement all the methods on FlightEnabled, 
because OrbitEarth extends that interface. 
And I'll implement those. 
The default implementations are good enough for this demonstration. 
Although for a Satellite, you can imagine the implementation of these methods 
might not be the same as for a Bird.

And one other thing I want to show you is that both records 
and enums can implement interfaces. 
But they can't extend classes, abstract or not. 
Again, for simplicity, above the Satellite class, I'll create a record, 
let's say it's a DragonFly. 
And I'll add two parameters of type String, and it'll implement FlightEnabled. 
And normally, records wouldn't have class bodies, 
but because I'm implementing FlightEnabled, 
this record needs to implement _FlightEnabled_'s abstract methods. 
I'll generate those methods. 
I could implement special functionality for this _DragonFly;_ 
that means it can be treated like any other FlightEnabled object, 
so that's pretty cool. 
This is also true for the enum type too.

Again, I'll create an enum here, above the DragonFly record. 
And after the enum name, I'll add implements Trackable, before the constant list.
And for the constant list, I'll add grounded, launch, cruise, and data collection. 
This enum describes 4 stages of a satellite launch. 
What we're saying by doing this is that we really want each of these stages to be trackable. 
And like before, I've got an error because this enum does not yet implement the track method.
I'll generate that method. Let me add code in that method next. 
I'll just say if the current stage, represented by this keyword, isn't GROUNDED, 
I'll do some monitoring. 
You can imagine that you'd want to monitor each of these stages in some different way, 
but I'll keep this simple. 
I'll come back to this method in a bit, but what this means is, 
you can have all these various types of classes implement an interface. 
This means we can treat these just like other types, and refer to them by the interface type. 
Let's loop back and talk about reference types again, 
now that we've covered both abstract methods and interfaces.
</div>

### Abstracted Types—Coding to an Interface
<div align="justify">

Both interfaces and abstract classes are _abstracted reference types_. 
Reference types can be used in code, as variable types, method parameters, 
and return types, list types, and so on. 
When you use an abstracted reference type, this is referred to as _coding to an interface_. 
This means your code doesn't use specific types, 
but more generalized ones, usually an interface type. 
_This technique is preferred_, because it allows many runtime instances of various classes 
to be processed uniformly by the same code. 
It also allows for substitutions of some other class or object, 
that still implements the same interface, without forcing a major refactor of your code. 
Using interface types as the reference type, is considered a best practice. 
In my coding examples to date, I didn't demonstrate this, 
and that was on purpose, because we hadn't yet reviewed interfaces. 
I want to walk through some examples of this next with you. 
I'll go back to our main method, and create an ArrayList of FlightEnabled types.

```java  
LinkedList<FlightEnabled> fliers = new LinkedList<>();
fliers.add(bird);

List<FlightEnabled> betterFliers = new LinkedList<>();
betterFliers.add(bird);
```

I'll call it fliers and will also initialize it. 
And add bird to our array list. 
Our declared type is the same as the instance type, an ArrayList, 
and I've made the elements in this list, FlightEnabled. 
But ArrayList implements the List interface, and is recommended 
to use the interface type for the declared variable. 
I'll do that next.

Same thing, but this time with List as the reference type. 
And I'll add bird to the list. 
And I'll add bird to the list. 
This time, the declared variable is a List of FlightEnabled elements, 
and not an ArrayList. 
List is the interface type. 
Ok, so why is this better? 
It's not really immediately obvious why just yet. 
Let me add some methods to demonstrate why the second declaration 
is better than the first. 
The first method, I'll call triggerFliers, 
and it'll have a method parameter, ArrayList.

```java  
private static void triggerFliers(List<FlightEnabled> fliers) {

    for (var flier : fliers) {
        flier.takeOff();
    }
}
```

This method, triggerFliers, is taking an ArrayList of FlightEnabled things, 
and then calling the takeOff method for each. 
And let's copy and pate this method, changing the name to flyFlyer, 
and change the method from takeOff to fly.

```java  
private static void flyFlyer(List<FlightEnabled> fliers) {

    for (var flier : fliers) {
        flier.fly();
    }
}
```

And I'll paste triggerFliers again, and call this method landFliers, 
and call the _flier.land_ method in the for loop.

```java  
private static void landFliers(List<FlightEnabled> fliers) {

    for (var flier : fliers) {
        flier.land();
    }
}
```

Ok, so you can imagine, if you're writing an application for flightEnabled items, 
you might have a lot of methods that take an ArrayList like this, as a parameter. 
I'm going to call these from the main method, and I'll pass them the fliers variable, 
the first variable, ArrayList, which matches the type of the methods.

```java  
triggerFliers(fliers);
flyFlyer(fliers);
landFliers(fliers);
```

In each case, I'm passing the _fliers_ variable, 
which I've declared using the specific type, the ArrayList type.
And this runs:

```java  
Bird is taking off
Bird is flying
Bird is landing
```
                    
And we get the messages for a Bird, for takeoff, flying, and landing. 
I'm going to copy those three statements. 
And this time pass the betterFliers variable. 
Now, this code doesn't compile. 
We can't pass a List to a method where an ArrayList is declared. 
So that's one problem. 
Our method parameters are very specific to the ArrayList type. 
But do they really need to be? 
For now, I'll leave those three statements as is, even though they don't compile. 
And now, let's say; we've decided a LinkedList is a better option for us than an ArrayList. 
And I'll change our _fliers_ variable to a LinkedList. 
And now, because our methods are specific about which type they expect, 
we can't pass the _fliers_ variable to any of these methods either. 
We've got more errors. 
To pass a LinkedList variable to these methods, 
we'd now have to change every single method declared with an ArrayList parameter. 
Next, I want to demonstrate coding to the interface. 
For all three methods, I'll change ArrayList to just the List type, 
which is the interface type. 
Now, the compiler errors are gone, on all six statements, those that passed fliers, 
and those that passed betterFliers. 
We can pass a LinkedList to these methods. 
But more importantly, let's change our betterFliers instance to a LinkedList. 
In this case, we simply swapped one type of list with another. 
The code still compiles. 
And I didn't have to refactor any other code, except this one line-316. 
If you code to the interface, you know anything that implements the interface 
has the same set of methods, so making changes can be as simple as I just showed you here. 
That's a large time savings. 
Method parameters, method return types, local variable references, 
and even class variables should try to use the interface type, 
as the reference variable type, when possible. 
This will make your code more extensible in the future.
</div>

### Coding to an Interface
<div align="justify">

Coding to an interface scales well to support new subtypes, 
and it helps when refactoring code. 
The downside, though, is that alterations to the interface may wreak havoc 
on the client code. 
Imagine that you have 50 classes using your interface, 
and you want to add an extra abstract method to support new functionality. 
As soon as you add a new abstract method, all 50 classes won't compile. 
Your code isn't backwards compatible with this kind of change to an interface. 
Interfaces haven't been easily extensible in the past. 
But Java has made several changes to the Interface type over time, 
to try to address this last problem.

```java  
triggerFliers(betterFliers);
flyFlyer(betterFliers);
landFliers(betterFliers);
```
</div>

### Default & Static Methods
<div align="justify">

JDK 8 introduced the _default_ method and public _static_ methods, 
and JDK 9 introduced _private_ methods, both static and non-static. 
All of these new method types (on the interface) are concrete methods. 
In last course, I created three interfaces: FlightEnabled, Trackable, and OrbitEarth. 
I also created classes, records and enums, that implemented one or two of these.

Now, let's consider what happens if we learn about a new requirement for flightEnabled objects. 
A new method is needed, called transition that uses the FlightStages enum type we set up. 
I'm going to add this method to the FlightEnabled interface, which I've declared in the **Animal.java** 
source file. 
And this method takes a stage, one of the enum values, and returns another stage, 
because it's transitioning from one stage to another.

All I did here was to define a new abstract method on this interface. 
Unfortunately, this one change means every class that implements this interface now won't compile. 
This means that for us, Bird, Jet, Satellite, and DragonFly, all have to change. 
This is painful, and we've only got a small set of classes. 
Imagine facing this kind of change in a production environment, especially if this is a library type. 
If you'd published this interface, to be used in multiple applications, 
making this kind of change wouldn't go well for anyone using it. 
Your updated interface wouldn't be backwards compatible. 
Before JDK 8, you didn't have a lot of options 
for making changes like this to your interfaces. 
You either had to force all classes implementing it, to be edited so that they'd compile, 
or provide an alternate interface with their new functionality. 
Java came to the rescue with a feature officially called, the extension method.

An extension method is identified by the modifier _default_, 
so it's more commonly known as the default method. 
This method is a _concrete_ method, meaning it has a code block, 
and we can add statements to it. 
In fact, it has to have a method body, even just an empty set of curly braces. 
It's a lot like a method on a superclass, because we can override it. 
Adding a default method doesn't break any classes currently implementing the interface. 
Let's get back to the code, and I'll switch to using a default implementation for this new method. 
I'm going to comment that single statement, for the abstract method, 
and write right below it with code for a default method.

In the body of this method, I'm printing the text, and including the class name.

```java  
//FlightStages transition(FlightStages stage);                  // replacing it with the default method version below
default FlightStages transition (FlightStages stage) {
    System.out.println("transition not implemented on " + getClass().getName());
    return null;
}
```

It's common practice to write a statement like this, or throw an exception, 
that a default method isn't implemented. 
This means I really want every class, using this interface, 
to override this method before they actually use it in code. 
I can't force them to implement their own version, like I could if it were an abstract method. 
What I can do, though, is either cause an error, or print a statement like this, 
so they'll implement it when they actually are ready to use it.

Now, the other thing I want you to see here is that I'm using _this.getClass().getName()_. 
By now you know this will give us the class name of the instance. 
But this is really something new, for instance, because it isn't a class,
and an object is never really an instance of an interface. 
We may not even have any idea, when we create an interface, 
what an object executing this method will really be. 
It could be anything. 
But the Java developers worked their magic, 
and now give us the ability, to access a runtime instance from this method. 
And normally, we wouldn't use the explicit this keyword in a class, only in a setter or constructor, 
as we've previously talked about. 
I can remove this:

```java  
System.out.println("transition not implemented on " + getClass().getName());
```

Because it's implicit in non-static methods, including this default method on an interface. 
Next, I'll create a new class, called Test, in the same package, 
because I want to keep the Main class, the way we left.

```java  
public class Test {

    public static void main(String[] args) {

    }
}
```

I'll add a public static void main method in this class. And I'm going to copy the inFlight method from the Main
class, and paste it into the Test class.

```java  
public class Test {

    public static void main(String[] args) {
        inFlight(new Jet());
    }

    private static void inFlight(FlightEnabled flier) {

        flier.takeOff();
        flier.transition(FlightStages.LAUNCH);

        flier.fly();
        if (flier instanceof Trackable tracked) {
            tracked.track();
        }
        flier.land();
    }
}
```

I want to insert a call to the transition method between takeOff and fly. 
Going back to the main method on the Test class, I'll call the inFlight method, 
and pass it a new instance of the Jet class.
I want to run this code, but I have to be sure to run the main method on Test class, 
and not Main class.

```java  
Jet is taking off
transition not implemented on Section_11_..._default_PublicStatic_Methods.Jet
Jet is flying
Jet's coordinates recorded
Jet is landing
```
                
I hope you can see that message, transition not implemented for _...Jet_, 
on the second line of the output. 
This means, in this case, that Jet didn't override this method. 
If you really want your default method to get implemented, or overridden, 
on every class, you'd want to code something like this in the default method. 
If I don't want this to be what happens, and I probably don't, 
I have to go to the Jet class, and override that method. 
To do that, I want to generate an override method for that transition method.

```java  
@Override
public FlightStages transition(FlightStages stage) {
    return FlightEnabled.super.transition(stage);
}
```

And this gives me the default overridden implementation. 
And this looks a lot like what we'd see, if we overrode a class's method, 
except the keyword super here, is qualified by the interface type, FlightEnabled. 
This means the default method on the Interface type gets called. 
But that's not what I want either, because that's what happens 
if I don't override the method at all. 
I want to change the code in my overridden method, 
I'll print something out and return a different stage. 
I'll print the class name with a string literal.

```java  
System.out.println(getClass().getSimpleName() + " transitioning");
```

And then I'll return an enum constant.

```java  
return FlightEnabled.super.transition(stage);
```

Before I run that, take a look at the little icon in IntelliJ, 
comparing it to the one next to the previous method, track. 
There is a green circle with an _i_, and red arrow next to the track method, 
vs. a blue circle with an _o_, and red arrow, for the transition method. 
The green icon indicates we're implementing an abstract method, 
whereas the blue icon implies an override, 
even though we use the override annotation for each. 
This is one small way to differentiate between these two types of methods. 
Now, I'll run that.

```java  
Jet is taking off
Jet transitioning
Jet is flying
Jet's coordinates recorded
Jet is landing
```

And this code runs, and you can see that the new method was executed, 
because we see Jet transitioning. 
Now, I could've been nicer, and provided a better default implementation. 
In some cases, the default method on the interface can be used much like a class method, 
providing some behavior that'll work for all classes.
</div>

### Overriding a Default Method
<div align="justify">

So like overriding a method on a class, you have three choices
when you override a default method on an interface.
* You can choose not to override it at all.
* You can override the method and write code for it, so that the interface method isn't executed.
* Or you can write your own code, and invoke the method on the interface, as part of your implementation.

Let's look at this in some code. 
First, I'll change the transition method on the FlightEnabled interface, 
to have something other than the unimplemented statement. 
But even before I do that, I want to first add a method to the enum type, 
that gets the next Stage.

```java  
@Override
public FlightStages transition(FlightStages stage) {
    System.out.println(getClass().getSimpleName() + " transitioning");
    return FlightEnabled.super.transition(stage);
}
```

I'll declare it as public, returning FlightStages, and I'll call it getNextStage. 
Next, I'll create a FlightStages array and set it to the array returned from calling the values() method. 
And finally, I'll return an element from the array created on the previous line.

```java  
public FlightStages getNextStage() {

    FlightStages[] allStages = values();
    return allStages[(ordinal() + 1) % allStages.length];
}
```

Ok, so with this code, I've purposely avoided using any of the enum constant values, 
like GROUNDED or LAUNCH. 
If the enum constants change, and more are included, or some are moved around, 
I still want this method to work.

Firstly, I create a local variable allStages, which gets assigned the array of enum values, 
that we get back from the values() method. 
I want to return the next stage, which is the stage at ordinal + 1, 
in every instance, except when the current stage is the last enum constant, DATA_COLLECTION. 
In that case, there is no next stage, so I want to return to the first stage. 
This expression,

```java  
return allStages[(ordinal() + 1) % allStages.length];
```
                
Use the modulus operator to return ordinal + 1 in every case,
except when ordinal + 1 is equal to the length of the array, allStages. 
In that case, it will be 4 over four, as the constant list stands now, 
with a reminder of zero, and it will give us the first stage. 
What I'm really doing is cycling through the stages. 
Now, I want to use this method in the default transition method on FlightEnabled.
I'll comment out the statements in there.

```java  
default FlightStages transition (FlightStages stage) {
    System.out.println("transition not implemented on " + getClass().getName());
    return null;
}
```

Since I overrode this method on the Jet class, 
I want to invoke this updated default method from there. 
Going back to that Jet class, and the transition method, 
I'll return the next stage I get from the default 
transition method of FlightEnabled, instead of the hardcoded CRUISE stage.

And now, you can see I'm using _FlightEnabled.super.transition_. 
If I tried to use just super, I'd get a compiler error. 
When we try that, IntelliJ gives us the message, _Cannot resolve method transition in Object_, 
which is the Jet's implied superclass. 
And that's because no class in Jet's hierarchy has a method called transition. 
Remember, interfaces aren't classes, and aren't really a part of a class, 
or the object that gets created from a class. 
They're just a way to make your class and object behaves a certain way.

Whenever you call a default method from an overridden method, 
you need to qualify super with the interface type. 
I'll run that:

```java  
Jet is taking off
Jet transitioning
Transitioning from LAUNCH to CRUISE
Jet is flying
Jet's coordinates recorded
Jet is landing
```
                
And you can see there, Jet transitioning, and it's going from the LAUNCH stage to the CRUISE stage. 
That's how a default method works on an interface. 
Java introduced it to help with building extensible and backwards compatible interfaces.
</div>

### Public Static Methods on an Interface
<div align="justify">

The default method lets us extend an interface's functionality while staying backwards compatible.
Another enhancement that Java included in JDK 8 was support for public static methods on the interface.
Before JDK 8, an interface would often come packaged with a helper class that provided static method. 
With this change, the static methods can be included on the interface type itself. 
Static methods don't need to specify a public access modifier
because it's implied. 
When you call a public static method on an interface, you must use the interface name as a qualifier. 
In the ArrayList lectures, you may remember I used two static helper methods 
on the Comparator interface, which were added in JDK 8. 
These were Comparator.naturalOrder() and Comparator.reverseOrder().

Let's see what this really means when we create a static method on our own interface. 
This time, I want to work with the OrbitEarth interface.
And I'm going to add a method to log data to the console in a flexible way.

```java  
static void log(String description) {

    var today = new java.util.Date();
    System.out.println(today + ": " + description);
}
```

Notice here, I don't specify _public_. 
I could have, but it would just be redundant. 
All methods on an interface are _public_, unless otherwise specified. 
And next, I'm using _java.util.Date_, a class we haven't covered yet, 
but will in an upcoming lecture, in much more detail. 
And what I want you to see here is, I'm using the fully qualified name, 
and not waiting for IntelliJ to add that import statement. 
There are two classes called Date in Java's libraries, 
one in this package, _java.util_, and another in _java.sql_. 
This is one way to do it. 
If you choose to have IntelliJ generate the import statement, 
make sure to use the Date class in _java.util_. 
I wanted to show you an example of using a fully qualified name. 
You might do something like this, if you're only ever referencing 
that class once in your code, as I'm doing here. 
If we create a new instance of that class, it gives us today's date and time, 
for our own local time, so that's pretty handy. 
And now I'll call this, from the main method on the Test class.

```java  
OrbitEarth.log("Testing " + new Satellite());
```

And if I run that:

```java  
Wed Aug 09 19:39:54 TRT 2023: Testing ...Satellite@9807454
```
                
You'll see the date and time, printed out in what is called the long form along with the other items. 
We didn't override the toString method on Satellite, so we got the default output for that class. 
That's not complicated, but the inclusion of public static methods is a definite bonus. 
It allows us to include helper methods on the interface type itself, 
as I've shown right above Part-3.

We covered a default method, and a public static method introduced in JDK8. 
It wasn't long before developers were requesting more.
</div>

### Private methods (JDK 9)
<div align="justify">

JDK 9 gave us private methods, both static and not static. 
This enhancement primarily addresses the problem of re-use of code
within concrete methods on an interface. 
A private static method can be accessed by either a public static method,
a default method, or a private non-static method. 
A private non-static method is used to support default methods and other private methods.

Getting back to the code, I created a public log method on OrbitEarth. 
I can also make this method private and static, 
which means only methods on this interface can actually call it. 
I'll make that change next. 
Going to the OrbitEarth interface,

```java  
private static void log(String description) {
    var today = new java.util.Date();
    System.out.println(today + ": " + description);
}
```

And like any private method, static or otherwise, 
this means it's only available to be used by the interface's concrete method. 
And now, I've got an error in my main method of the Test class.
I'll comment out the line where I'm using it. 
Going to that class,

```java  
//OrbitEarth.log("Testing " + new Satellite());         // commented via Part-5
```

While I'm this class, I want a method, similar to the inFlight method, 
but which is more specific to things that orbit the earth. 
I'll copy it and paste below, renaming the method to orbit, 
and changing the parameter type to OrbitEarth, 
which is the name of the interface I just changed. 
I also want to remove the transition method, and that if statement.
I'll explain why in a minute.

```java  
private static void orbit(OrbitEarth flier) {
    flier.takeOff();
    flier.fly();
    flier.land();
}
```

And right now, the only class that implements OrbitEarth is Satellite. 
And this class actually doesn't do anything for any of the methods. 
It overrode the methods, but I didn't include any code in them yet. 
And going to the main method on the Test class, 
I'll now add a call to orbit and pass it a new Satellite, 
which you'll remember I created in a previous course.

And now if I run this code, you'll see there is no output from that change. 
Nothing gets printed out from any of the Satellite's methods. 
Before I work on those, I want to next create a private method on OrbitEarth, 
and call it logStage.

```java  
private void logStage(FlightStages stage, String description) {

    description = stage + ": " + description;
    log(description);
}
```

In this code, I'm just prefixing the stage to the description that was passed. 
We can only use a private method, from either another private method, 
or a default method. 
I want to override the transition method, that was on FlightEnabled, 
doing that on OrbitEarth. 
Right below, I'll generate that overridden method. 
And change it a little bit.

```java  
@Override
default FlightStages transition(FlightStages stage) {
    FlightStages nextStage = FlightEnabled.super.transition(stage);
    logStage(stage, "Begining Transition to " + nextStage);
    return nextStage;
}
```

First, I'm assigning what comes back from the transition method of 
FlightEnabled to a local variable called nextStage,
because I want to print it out. 
On the second line, I do that, passing the current stage as the 
first parameter to the private method, and appending the nextStage 
to the text in the second argument. 
And then I'll just return the nextStage variable.

Ok, so now it's time to have the Satellite class actually do something. 
First, I want to add a stage field to the Satellite class, 
which has the type of our enum, FlightStages.

```java  
class Satellite implements OrbitEarth {

    FlightStages stage = FlightStages.GROUNDED;
}
```

Now, our Satellite is going to have each stage set, 
as it cycles through the flight stages. 
The only thing left now is to implement the rest of Satellite's methods.
But for this, I want a private method on Satellite, also called transition, 
but this method isn't overriding the interface's default method. 
It's overloading it. 
Remember, that means we're changing the signature, so in this case, 
I'm passing a String, and not a FlightStages variable.

```java  
public void transition(String description) {
    System.out.println(description);
    stage = transition(stage);
    stage.track();
}
```

The method parameter is just a description that gets printed out. 
Then I set the stage using the OrbitEarth transition method.
Now, I could've just called _stage.getNextStage_ here. 
But you can imagine that when you're transitioning from
one flight stage to another, there's some other work involved. 
And maybe a transition might fail, and you'd want to deal with that. 
In a real application, you'd probably have support 
for these kinds of different transition stages. 
On the last line, I'm calling track for each stage. 
Remember that our enum type is really trackable, 
meaning every stage is trackable in some way.

Now, back to Satellite, I want to put some code in the 
three remaining methods: takeOff, fly, and land. 
First the takeOff method.

```java  
@Override
public void takeOff() {

    transition("Taking Off");
}

@Override
public void land() {

    transition("Landing");
}

@Override
public void fly() {

    achieveOrbit();
    transition("Data Collection while Orbiting");
}
```

Here, I just make a call to our transition method,
with the description we're taking off. 
And then the land method will do a similar thing, 
calling transition with the description Landing. 
And same for the fly method here, I've also got a call to achieveOrbit, 
because flying for an orbiting vehicle consists of 2 stages, 
the launch, then achieving orbit. 
And then I call transition again. 
When a Satellite has achieved orbit, it can start collecting data, 
or taking photos, and so on. 
And for completeness, I'll change the code in achieveOrbit(), so it calls
the transition method as well:

```java  
transition("Orbit achieved!");
```
            
There's the setup. 
And now if I run this,

```java  
Taking Off
Transitioning from GROUNDED to LAUNCH                                              <<<< Stage-1
Wed Aug 09 20:25:59 TRT 2023: GROUNDED: Begining Transition to LAUNCH
Monitoring LAUNCH
Orbit achieved!
Transitioning from LAUNCH to CRUISE                                                <<<< Stage-2
Wed Aug 09 20:25:59 TRT 2023: LAUNCH: Begining Transition to CRUISE
Monitoring CRUISE
Data Collection while Orbiting
Transitioning from CRUISE to DATA_COLLECTION                                       <<<< Stage-3
Wed Aug 09 20:25:59 TRT 2023: CRUISE: Begining Transition to DATA_COLLECTION
Monitoring DATA_COLLECTION
Landing
Transitioning from DATA_COLLECTION to GROUNDED                                     <<<< Stage-4
Wed Aug 09 20:25:59 TRT 2023: DATA_COLLECTION: Begining Transition to GROUNDED
```
            
You can now see all the stages get cycled through, 
including going back to _GROUNDED_ at the end. 
And notice that each stage is Trackable, 
so you're seeing that with the Monitoring Launch,
Monitoring Cruise, and Monitoring Data Collection statements there.
</div>

### Differences Between Abstract & Interface
<div align="justify">

I want to discuss the main differences between an abstract class and an interface, 
and when to use either of them.

* Abstract classes are very similar to interfaces. 
You can't instantiate either of them. 
Both types may contain a mix of methods declared with or without a method block. 
Remember, when a method has a method block {}, it's a concrete method, 
and we can say it's implemented, or provides an implementation for that method.
* With abstract classes, you can declare fields that aren't static and final, 
instance fields in other words.
* Also with abstract classes, you can use any of the four access modifiers for its concrete methods. 
So public, private, protected, or the default package.
* You can also use all but the private access modifier for its abstract methods.
* An abstract class can extend only one parent class, but it can implement multiple interfaces.
* When an abstract class is subclassed, the subclass usually provides implementations 
for all the abstract methods in its parent class.
* However, if it doesn't, then the subclass must also be declared abstract.

You'll want to use an abstract class, when:

* You want to share code among several closely related classes 
(Animal, for example, with fields, name, age...).
* You expect classes that extend your abstract class, to have many common methods or fields, 
or require access modifiers other than public.
* You want to declare non-static or non-final fields (for example, name, age), 
so this enables you to define methods that can access and modify 
the state of an object (getName, setName).
* You have a requirement for your base class to provide a default implementation of certain methods, 
but other methods should be open to being overridden by child classes.

**Summary**: An abstract class provides a common definition, as a base class, that multiple, derived classes can share.

Alright, now in comparison, looking at interface.

* An interface is just the declaration of methods, which you want some classes to have, 
it's not the implementation.
* In an interface, we define what kind of operation an object can perform. 
These operations are defined by the classes that implement the interface.
* Interfaces form a contract between the class and the outside world, 
and this contract is enforced at build time by the Java compiler.
* You can't instantiate interfaces, but they may contain a mix of methods declared with, 
or without an implementation.
* All methods on interfaces, declared without a method body, 
are automatically public and abstract.
* An interface can extend another interface.
* Interfaces are more flexible, and can deal with a lot more stress 
on the design of your program, because they aren't part of the class hierarchy.
* A best practice way of coding is commonly called Coding to an Interface.
* By introducing interfaces into your program, you're really introducing points of variation, 
at which you can plug in different implementations for that interface.

**Summary**: The interface decouples the _what_, from the _how_, 
and is used to make different types behave in similar ways.
**Notes**: Since Java 8, interfaces can now contain default methods, 
so in other words methods with implementation. 
The keyword default is used mostly for backwards compatibility. 
Public static methods were also introduced in Java 8.
**Notes**: Since Java 9, an interface can also contain private methods, 
commonly used when default methods share common code.

Alright, so when do you want to use an interface? 
Well, when:
* You expect that unrelated classes will implement your interface. 
* For example, two of Java's own interfaces, Comparable and Cloneable, 
can be implemented by many unrelated classes.
* You want to specify the behavior of a particular data type, 
but you're not concerned about who implements its behavior.
* You want to separate different behavior.

I've briefly discussed some interfaces, like List and Queue, and their implementations, 
_ArrayList_ and _LinkedList_. 
These are part of what Java calls its Collection Framework. 
Interfaces are also the basis for many of the features that are coming up, 
for example, _lambda expressions_, which were introduced in JDK8. 
Another example is _Java's database connectivity support, or JDBC_, 
built almost entirely with interfaces. 
The concrete implementation of methods is different 
for each database vendor, and comes in the form of JDBC drivers. 
This enables you to write all database code without being 
concerned about the details of the database, you're connected to.

I've said that interfaces and abstract classes are both abstracted types, 
and abstracted types are used as reference types in code. 
The table below is a summary of the similarities and differences.

|                                                              | Abstract Class         | Interface                           |
|--------------------------------------------------------------|------------------------|-------------------------------------|
| An instance can be created from it                           | NO                     | NO                                  |
| Has a constructor                                            | YES                    | NO                                  |
| Implemented as part of the Class Hierarchy. Uses inheritance | YES(in extends clause) | NO(in implements clause             |
| records and enums can extend or implement?                   | NO                     | YES                                 |
| Inherits from java.lang.Object                               | YES                    | NO                                  |
| Can have both abstract method and concrete method            | YES                    | YES(as of JDK8)                     |
| Abstract methods must include abstract modifier              | YES                    | NO(Implicit)                        |
| Supports defaults modifier for its method                    | NO                     | YES(as of JDK8)                     |
| Can have instance fields (non-static instance fields)        | YES                    | NO                                  |
| Can have static fields (class fields)                        | YES                    | YES(implicitly public static final) |

Alright, so this is the differences between abstract classes and interfaces.
</div>

## [d. Interface Challenge]()
<div align="justify">

In this challenge, we'll be working on creating some mappable output. 
In the past decade or so, maps have become part of so many applications. 
Everything it seems can be described on a map, and you may need to provide this support
for existing classes. 
Most things, when drawn on a map, fall into three categories, a point, a line, or a polygon or
geometric shape. 
The result of your code will be text that could be printed out to a file, 
for exchanging data with a mapping application.
One such file is a specially formatted file, called geo json, 
which is a JSON file extended for geographical elements. 
You don't have to know JSON or geo json to be successful at this challenge.

For this challenge, you'll create a String for every feature that will be mapped. 
An example of such a String is shown below.

```java  
"properties": { "name": "Sydney Opera House", "usage": "Entertainment"}
```
                
Notice that data is represented in name-value pairs. 
The property name, and its corresponding value is separated by a colon, 
Pairs are separated by commas. 
Values can be String values or a list of values. 
You can see properties is the name, 
and the values is a nested list of name value pairs in curly brackets.

First, Create a _Mappable_ Interface. 
The interface should _force classes to implement three methods_.
* One method should return a _label_ (how the item will be described on the map).
* One should return a _geometry type_ (POINT or LINE) which is what the type will look like on the map.
* The last should return an _icon type_ (sometimes called a map marker). 
This could be a black push pin for a point of interest, 
or a red solid line for a highway, for example.

In addition to the three methods described, the interface should also include:
* A constant String value called JSON_PROPERTY, which is equal to: _properties_:{%s}. 
A hint here, using a text block will help maintain quotation marks in your output.
* Include a default method called toJSON() that prints out the type, label, and marker. 
I'll show examples shortly.
* A _static method,_ that takes a Mappable instance as an argument. 
* This method should print out the properties for each mappable type, 
including those mentioned above, but also any other fields in the business classes.

You'll also want to create two classes that implement this interface, 
a _Building_ and _UtilityLine_.
* One class, in my case, the Building, should have a geometry type of POINT, 
and One class should have a geometry type of Line. 
The UtilityLine class will be my example for a class that will be a LINE on a map.
* When these items are mapped, the Building will be shown on a city map, 
as a point with the icon and label specified, and the Utility Line will be a line on the map.

We aren't going to map it, we'd need quite a bit more time for that, 
but we'll have some of the data elements that a mapping system would need, 
and in one format that it could use. 
Your final output should look something like I show below.

```java  
"properties": {"type": "POINT", "label": "Sydney Town Hall (GOVERNMENT)", "marker": "RED STAR", "name": "Sydney Town Hall", "usage": "GOVERNMENT"}
```

You should output the geometry type, the icon information, and the label. 
Here is an example for a building, including type, label, and marker, 
but also the building name and usage, which are fields on building. 
And here is an example for a fiber optic Utility line, so a LINE, a green dotted line, 
would get drawn for a fiber optic cable on College Street.

```java  
"properties": {"type": "LINE", "label": "College St (FIBER_OPTIC)", "marker": "GREEN DOTTED", "name": "College St", "utility": "FIBER_OPTIC"}
```

You can see that the properties are a comma-delimited list, in curly braces, 
with the property or field name in quotes, then a colon, 
followed by the property value or field value, and that's also in double quotes.
</div>
