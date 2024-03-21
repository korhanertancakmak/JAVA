# Object-Oriented Programming (PART-I)

In this part of OOP, you're going to learn about the fundamentals of object-oriented programming, 
starting first with the structures we'll be using, specifically Classes, Objects, 
and Constructors. 
Along with these structures, we'll be learning Inheritance, Encapsulation, 
Polymorphism, and Composition, which are all fundamentals features of OOP.

Part-I covers only Inheritance, Part-II covers the others.

## What is Object-Oriented Programming?

Object-oriented programming is a way to model real world objects, 
as software objects, which contain both data and code. 
OOP is a common acronym for Object-Oriented Programming. 
And in particular, we'll be talking about classes, 
which is a fundamental component of OOP in Java, 
and other languages for that matter. 
It's sometimes called Class-based programming. 
Class-based programming starts with classes, which become the blueprints for objects.

## Real World Object Exercise

Just have a look around, in the area you're sitting in right now. 
And if you do that, you'll find that there are many examples of real world objects. 
For example, I'm sitting here and I can see:

* A computer
* a keyboard
* a microphone
* shelves on the wall
* a door

All of these are examples of real world objects. Real world objects have two major parts:

* state
* behavior

State, in terms of a computer object, might be:

* The amount of RAM it has
* The operating system's running
* The hard drive size
* The size of the monitor

These are characteristics about the item that can describe it. 
We could also describe animate objects, like people or animals, 
or even insects, like an ant. 
For an ant, the state might be:

* The age
* The number of legs
* The conscious state
* whether the ant is sleeping or is awake

In addition to state, objects may also have behavior, 
or actions that can be performed by the objects, 
or upon the object. 
Behavior, for a computer, might be things like:

* Booting up
* Shutting down
* Beeping or outputting some form of sound
* Drawing something on the screen, and so on

All of these could be described as behaviors for a computer. For an ant, behavior might be:

* Eating
* Drinking
* Fighting
* Carrying food, those types of things

So modeling real world objects as software objects is a fundamental 
part of Object-Oriented Programming. 
Now, a software object stores its state in fields, which can also be called variables,
or attributes. 
And Objects expose their behavior with methods, which we've talked about before.
So where does a class fit in? 
Well, think of a class as a template, or a blueprint for creating objects. 
Let's take another look at the class.

## The Class as the blueprint

The class describes the data (fields), and the behavior (methods), 
that are relevant to the real world object we want to describe. 
These are called class members: Instance Members and Static Members. 
A class member can be a field, or a method, or some other type of dependent element.

If a field is static, there is only one copy in memory, 
and this value is associated with the class, or template, itself. 
A static method can't be dependent on any one object's state, 
so it can't reference any instance members.

If a field is not static, it's called an instance field, 
and each object may have a different value stored for this field. 
Any method that operates on instance fields needs to be non-static.

So these class or member fields can be thought of as variables, 
but it's more common to call them fields or attributes. 
Now, we'll be looking at instance fields to describe our objects. 
Later, we'll be describing static members in greater detail.

```java  
public class Main {
    public static void main(String[] args) {
        
    }
}
```

You can actually see "public class Main" that's actually a statement that describes a
class in Java. 
So, why do we need to bother about classes? 
Or what benefit do classes give us in our everyday programming?

Well, think back to the basic data types that we've worked on, 
the primitive data types that we've explored so far, such as int, 
short, and those types of things. 
They're all basic data types, but they're fairly limiting. 
There's only so much you can do with them. 
So, a case could be made here that a class could be thought of as 
a powerful user-defined data type. 
That's not really correct in the true meaning, but it gives you an idea of what classes are. 
They really enable you to have sort of a powerful user-defined type, 
like a regular data type on steroids if you will.
So, to take this a little bit further, let's create our first real class.

Everytime we create a new Project, we've been creating a new class, 
usually one called CourseXXX, with capital C, and we generally add a method, called main. 
When you're creating classes in Java, the first letter should be an upper case letter. 
We haven't talked about how classes are organized, so let's look at that briefly, 
before we talk about access.

## Organizing classes

Classes can be organized into logical groupings, which are called packages. 
You declare a package name in the class using the package statement. 
If you don't declare a package, the class implicitly belongs to the default package. 
The default package is all we've used so far, 
because we haven't yet introduced the package statement. 
We're not going to get too deep into packages just yet, 
there's more on them later. 
But you do need to understand that classes are grouped into packages, 
to understand access modifiers.

### Access modifiers for the class

A class is said to be a top-level class if it is defined in the source code file, 
and not enclosed in the code block of another class, type, or method. 
A top-level class has only two valid access modifier options: public or none.

**public**: public means any other class in any package can access this class.

**no-modifier**: When the modifier is omitted, this has special meaning, 
called package access, meaning the class is accessible only to classes in the same package.

If there is no modifier specified at all, Java by default implicitly allows 
package-private access. 
This means that classes grouped into the same package can access the class.

When we're designing our class, there are some things we want the public to know, 
and some things that aren't necessary for the public to know. 
We can have a public interface. 
This is only the information the outside world needs to know, to use our class. 
But we'll also have a non-public interface. 
This is information we may want to share but not always, and not with everyone. 
We may need to share some information with our own company and other departments,
while other data might need to be shared with our manufacturers and dealers, 
but not with the public. 
Java allows us to have this kind of control by specifying different access modifiers 
for each member in a class.

### Access modifiers for class members

An access modifier at the member level allows granular control over class members. 
The valid access modifiers are shown in this table from the least restrictive(public), 
to the most restrictive(private).

**public**: public means any other class in any package can access this class.

**protected**: allows classes in the same package, and any subclasses in other packages, 
to have access to the member.

**no-modifier**: When the modifier is omitted, this has special meaning, called package access, 
meaning the class is accessible only to classes in the same package.
**private**: means that no other class can access this member.

Here, public is still an option for class members, like it was for the class, 
and this means there is unrestricted access to the member. 
We also still have no modifier, which means package, or package-private by default, 
so that any class in the same package can access this member. 
We can also use private, which is basically the opposite of public, 
and that's where no code, outside the class, can use this field or method. 
And finally, there's the protected modifier. 
This one also allows package access, but it also permits subclasses to access this member.

As a general rule, all your fields should be private, unlike the class, 
where we'll usually use public. 
So why would we want to make all the fields in a class private? 
Doesn't this mean that nobody can access them? 
This practice has a name, encapsulation, and it's a key 
fundamental rule of Object-Oriented Programming.

## Encapsulation

Encapsulation in OOP usually has two meanings. 
One is the bundling of behavior and attributes on a single object. 
The other is the practice of hiding fields, and some methods, from public access.

In general, when we talk about encapsulation, we're taking about information hiding, 
or hiding the internal workings of a particular object. 
When we make our attributes private, we can then create methods to access the data, 
each with different degrees of access allowed, as needed.

```java  
public class Main {
    // 5 private fields for a car class(we didn't change the name of class)
    private String make;
    private String model;
    private String color;
    private int doors;
    private boolean convertible;

    public void describeCar() {
        System.out.println(doors + "-Door " +
                color + " " +
                make + " " +
                model + " " +
                (convertible ? "Convertible" : ""));
    }

    // So we've created our first real template class, called Car, and we've set up some attributes on car. This feels like
    // a good place to end this course.
}
```

The fields are here:

* make
* model
* color
* doors
* convertible

Because they're defined in the class's code block, or the body of the class, and not in a method.
When we create an object from this class,
then the values we assign to these fields represent the state of the object.
Unlike local variables, class variables should have some type of access modifier declared for it.
If you don't declare one, Java declares the default one (package private), implicitly.
So, here, we've set the access modifier to be private in all cases,
which we've said help us encapsulate this class.
We'll want to control access to these fields, and this starts by making them private.
Later we'll add the method to access them.
The other thing you might have noticed is that we're not assigning any values yet.
This is because, at this point, we don't know what these values might be,
and they'll likely be different for each instance anyway.
If we were creating a real application, we'd likely have a lot more fields,
but we'll keep this simple to start with.
Let's add a method now that will print out this information about the car object.
We'll call this method describeCar and make the method public.
This method is not static because we're accessing instance fields in the class.
Methods, unlike fields, will often be public because we want to give users
a way to interact with the object.

## Classes, Using Setter and Creating Objects

So best practices for fields, that in general, fields on classes should be private, 
and a getter method should be created to access those fields. 
This provides encapsulation of the internals of our class, 
and supports maintenance of a public interface that doesn't have to change, 
even though our class might.

In this course, we're going to be setting up, and talking about setter methods, 
which set data on the objects. 
We'll end this course by creating more objects out of the Car class, 
and talking more about that process. 
We'll be using the code from the last code, where we ended it, 
with the Main and Car classes.

So now, let's add the setter methods, in the Car class:

```java  
public void setMake(String make) {
    this.make = make;
}
```

So, we have public methods that don't return anything, they're void, 
because remember, the setter method sets data, it doesn't retrieve it. 
And the parameter is going to be String, because that's what we're setting. 
For example, the field "make" is a String, and we'll use String as our parameter type. 
And what we want to do is, assign the field on our object, "make" in this case, 
to the value passed as an argument to the method. But is that what this is doing?

### What is null?

"Null" is a special keyword in Java, meaning the variable or attribute has a type, but
no reference to an object.
This means that no instance or object is assigned to the variable or field.
Fields with primitive data types are never null.
Java assigns fields in classes default values, intrinsically, if not assigned explicitly.

| Data Type                    | Default Value Assigned |
|------------------------------|------------------------|
| boolean                      | false                  |
| byte, short, int, long, char | 0                      |
| double, float                | 0.0                    |


### "this" Keyword 

**this** is a special keyword in Java. 
What it really refers to is the instance that was created when the object was instantiated. 
So **this** is a special reference name for the object or instance, 
which it can use to describe itself. 
And we can use **this** to access fields on the class.

So, because we're trying to set make, the field, 
here on the left side of the assignment operator, we want to specify 
that the make field we want to use, to assign a value to, i
s the make field on the object, and not the make method parameter. 
So, that's a way of updating the make attribute on Car, using a method, 
instead of trying to access it directly. 
Also, in the main method, we can add this setter:

```java  
car.setMake("Porsche");
```
                                            
We can see that our car isn't using the default "make," 
but is using the "make" we set it to, Porsche, in both of these cases, 
when we look at its output. 
So again, we don't really want to manually code all of these setters, 
like we did this first one. 
By using IntelliJ code generation, it can create the rest of the setters for us. 
Adding other setters into the Main class:

```java  
car.setModel("Carrera");
car.setDoors(2); 
car.setConvertible(true);
car.setColor("black");
```
                                            
We get the values we set on the car; and not the defaults.

So let's talk about why we'd want to use getters and setters?
What's the advantage of setMake? 
By using setMake, what we could do is, for example, 
we could go back to our Car.java. 
We can have a look at the setMake method, our setter,
and we could add some validation:

```java  
public void setMake(String make) {
    if (make == null) make = "Unknown";
    String lowercaseMake = make.toLowerCase();
    switch (lowercaseMake) {
        case "holden" , "porsche", "tesla" -> this.make = make;
        default -> {
            this.make = "Unsupported ";
        }
    };
}
```

So, first in this code, we check if the argument being passed is null. 
If it is, we set the make to "Unknown." 
Next, we're using a method on String to test the argument being passed. 
So now, this expression:

```java  
String lowercaseMake = make.toLowerCase();
```
                                            
It shouldn't be completely unfamiliar to you. 
We know the make parameter is a String, or more correct, it's an object of type String, 
and because of that, we have access to many methods on instances of String. 
One of these methods is this one, toLowerCase, which returns a new string, 
that's all lowercase. 
We'll use the switch statement on this variable, 
and test if it's one of the three makes we support. 
These are holden, porsche, and tesla. 
If the make matches one of those, we'll set the make field to the argument passed. 
But if it doesn't, we use the default case, and set the make field to "Unsupported."

So now we've built a rule, that we're supporting only three manufacturers. 
And we'll enforce that rule, so that if anything else is passed, 
we set make to "Unsupported." 
But the argument is null, we set it to "Unknown." 
Go back and test this by the changing Porsche to Maserati. 
And after running the code, you can see that "Unsupported" not "Maserati."

So you can see how it's very useful to have this type of functionality, 
the validation. 
So what you can do with the setter methods, is you can set up all the rules, 
related to that class, what is valid, and what is not valid. 
You can have all that functionality set up within the Car class itself,
so that these rules are in place as we're creating the objects. 
What that means is, the code creating objects can't make invalid objects, so to speak. 
In other words, it can't assign a make that 
we haven't defined as being valid in our Car class. 
So that's the reason, and that's really the whole concept of encapsulation, 
is that we're not allowing people to access the field directly. 
We force them to go through a controlled way of setting up the data on the object. 
Using a setter method, we can really make sure that the data in our objects is valid data.

So we've covered setter and getter methods, and why you might want to use them. 
Now, let's talk a little bit about declaring variables using classes. 
I want to show you what happens, if we don't do this first line here:

```java  
Car car = new Car();
```

If I comment that second bit out here, = new Car, so we've just got a variable. 
So comment that out and just define the variable so that we've not initialized it:

```java  
Car car; // = new Car();
```

We haven't included the rest (= new Car();) part. 
Already, IntelliJ is saying, "Variable car may not have been initialized," 
where we are attempting to call the setter method, on the car variable. 
You can't use an uninitialized variable, which car is, 
because we haven't assigned any object reference to it. 
But now, consider what happens, if we instead assign null, to a car:

```java  
Car car = null; // = new Car();
```
                                            
So IntelliJ is not showing any errors when we do this, but let's try running it. 
We actually get an exception, NullPointerException, and the additional information 
that we can't call a method, or invoke a method(an instance method) on a null instance. 
And what that essentially means, is we've defined a variable, called car, 
but it doesn't have a reference to a valid instance of a Car. 
So we can't run a method on null, and we couldn't set or get attributes on null.

So there's a distinction here, I want to point out, between an uninitialized variable, 
and a variable with a null reference.
An uninitialized variable, as we saw in the first instance, causes a compile time error. 
But a variable with a null reference can be used in code, without compiler errors, 
but will throw an exception at runtime.

So, in both of these scenarios, we haven't created an object from the Car template, 
which is the class. 
So that's why you need to make sure that new is always executed. 
The bottom line I'm trying to say here is that, make sure when you're creating objects, 
you always use the keyword new, and then include the name of the class, and then 
follow it with the parentheses.

An example Car class is here:

```java  
public class Car {
    private String make = "Tesla";
    private String model = "Model X";
    private String color = "Gray";
    private int doors = 2;
    private boolean convertible = true;

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getDoors() {
        return doors;
    }

    public boolean isConvertible() {
        return convertible;
    }

    public void setMake(String make) {
        if (make == null) make = "Unknown";
        String lowercaseMake = make.toLowerCase();
        switch (lowercaseMake) {
            case "holden" , "porsche", "tesla" -> this.make = make;
            default -> {
                this.make = "Unsupported ";
            }
        };
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public void setConvertible(boolean convertible) {
        this.convertible = convertible;
    }

    public void describeCar() {
        System.out.println(doors + "-Door " +
                color + " " +
                make + " " +
                model + " " +
                (convertible ? "Convertible" : ""));
    }
}
```

And the Main class:

```java  
public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        //car.setMake("Porsche");
        car.setMake("Maserati");
        car.setModel("Carrera");
        car.setDoors(2);
        car.setConvertible(true);
        car.setColor("black");
        System.out.println("make = " + car.getMake());
        System.out.println("model = " + car.getModel());
        car.describeCar();
    }
}
```

## Classes Challenge Exercise

I want to challenge your understanding of the previous classes. 
So here, what I want you to do:

### Object Oriented Challenge

Create a new class for a bank account. Crate fields for account characteristics like:
* account number
* account balance
* customer name
* email
* phone number
 
Create getters and setters for each field. Create 2 additional methods:
* one for depositing funds into the account
* one for withdrawing funds from the account

A customer should not be allowed to withdraw funds if that withdrawal takes their balance negative. 
Create a new project called ClassesChallenge, with the usual Main Class with the usual main method. 
You'll create an instance of an Account class, and then test your withdrawal and deposit methods. 
You'll print information to the console that confirms what the balance is after the methods are called.

So, the challenge here is to create the Bank Account Blueprint, that has five instance fields. 
You want to make this class encapsulated, so you'll make all your attributes private, 
and set up getter and methods for your attributes. 
In addition, you'll have two behavioral methods, for depositing funds, and withdrawing funds. 
In addition to this class, you'll set up a Main class, with a main method, 
that creates at least one instance of the Bank Account class, and simulates depositing 
and withdrawing money from the account. 
And you may also want to add some System.out.println statements, 
to the two methods above as well, to confirm how much was deposited or withdrawn.

## Constructors (Part-I)

A constructor is used in the creation of an object, that's an instance of a class. 
It is a special type of code block that has a specific name and parameters, much like a method. 
It has the same name as the class itself, and it doesn't return any values. 
You never include a return type from a constructor, not even void. 
You can, and should, specify an appropriate access modifier, 
to control who should be able to create new instances of the class.

```java  
public class Account { // This is the class declaration
    // The fields
    private int number;
    private Double balance;
    private String customerName;
    private String customerEmail;
    private int customerPhone;
    
    public Account () { // This is the defualt constructor declaration
        // Constructor code is the code to be executed as the object is created.
        System.out.println("Empty constructor called");
    }

    public Account(int number, double balance, String customerName, String email, int phone) {
        System.out.println("Account constructor with parameters called");
        this.number = number;
        this.balance = balance;
        this.customerName = customerName;
        customerEmail = email;
        customerPhone = phone;
    }

    // Getter & Setter Methods
    public Integer getNumber() {return number;}
    public void setNumber(int number) {this.number = number;}
    public Double getBalance() {return balance;}
    public void setBalance(Double balance) {this.balance = balance;}
    public String getName() {return customerName;}
    public void setName(String name) {this.customerName = name;}
    public String getEmail() {return customerEmail;}
    public void setEmail(String email) {this.customerEmail = email;}
    public Integer getPhoneNumber() {return customerPhone;}
    public void setPhoneNumber(int phoneNumber) {this.customerPhone = phoneNumber;}
    
    // Behaviors of the Account Class
    public void withdrawFunds(double withdrawAmount) {
        if ((balance - withdrawAmount) <= 0) {
            System.out.println("You cannot withdraw that amount, because there isn't much money in the balance! Your balance is " +
                    balance);
        } else {
            balance -= withdrawAmount;
            System.out.println(withdrawAmount + " withdrawn from your account and your balance is now $" + balance);
        }
    }

    public void depositFunds(double depositAmount) {
        balance += depositAmount;
        System.out.println(depositAmount + " deposited to your account and your balance is now $" + balance);
    }
}
```

So you can essentially do all the statements:

```java  
acc.setNumber(1234567);
acc.setBalance(1000d);
acc.setName("Korhan Çakmak");
acc.setEmail("korhanertancakmak@gmail.com");
acc.setPhoneNumber(3424241);
```

We've done here to set the initial parameters, or the initial values of the fields. 
And you can include any other initialization code, you want to perform, in the constructor. 
So let's see whether we can replace all this code.

First, we'll edit our Account.java class, and add a constructor. 
It turns out that Java actually creates a constructor for you implicitly. 
When we say things are implicit in Java, we mean you can't see the code in the source, 
but it's in the byte code, generated during the compilation process. 
So, when you actually type new, and the name of the class, and then parentheses,
this is actually calling the constructor.

```java  
Account acc = new Account();
```  

We didn't explicitly create a constructor in the Account class, so Java created one for us. 
This is called the default constructor.

### The Default Constructor

If a class contains no constructor declarations, then a default constructor is implicitly declared. 
This constructor has no parameters, and is often called the no-args (no arguments) constructor. 
If a class contains any other constructor declarations, 
then a default constructor is NOT implicitly declared. 
There are other rules for the default constructor, which we'll talk about, 
as we get more familiar with more advanced topics. 
For now, it's important to just understand that a constructor exists, 
whether you explicitly declare one or not. 
This is why creating an object with the "new" keyword, 
and passing no arguments in the parentheses is supported in nearly all cases.

So let's create our own constructor in the Account class next:

```java  
public Account() {
    System.out.println("Empty constructor called");
}
```  

So again, the rules for a constructor are:

* Its name has to be the same as the class
* It has no return type, not even void
* Also, we use an access modifier, here we use public

We'll talk about when you'd want to use other access modifiers later. 
So if we run this now, what we should see at the top, before any other output, 
is the message we put in the constructor, "Empty constructor called." 
So when we type, new Account with parentheses and no parameters, 
that actually was Java's cue to call the constructor that we just added. 
So the purpose of the constructor is to essentially initialize the object that we're creating, 
and do whatever else we need to happen, while the object is being instantiated. 
So it's only ever called once, at the start, when we're creating the object.

A class can have one or many constructors, one of which can be a No Args constructor. 
So now we'll add another constructor, and this time we'll declare some parameters. 
Doing this will let us pass values to the constructor. 
We can then use these values, to assign data to our fields, 
instead of calling a bunch of setters. 
This time, it will have five parameters, one for each of the fields on the Account class.

```java  
public Account(int number, double balance, String customerName, String email, int phone) {
    System.out.println("Account constructor with parameters called");
    this.number = number;
    this.balance = balance;
    this.customerName = customerName;
    customerEmail = email;
    customerPhone = phone;
}
```  

Here I just want to point out a few things with this constructor. 
First, we're passing two parameters, email and phone, 
where the parameter name doesn't match the field name, 
which are customerEmail and customerPhone. 
It's common practice to make the parameter names the same as the field names, 
but it's not required. 
If you do make them the same, use the "this." 
Notation is required, just like it was in the setter methods. 
So here, you can see; we're setting "this.number = number." 
This means we're assigning the number attribute on the instance, 
that's being created (known as "this.number"), to the argument value, "number," 
passed in the "number" parameter. But we don't need the "this." 
Notation for customerEmail, or customerPhone, 
because these field names are different from the parameter names.

So, we can now change the way we instantiate "acc," 
passing all the values to the call to the "new" keyword. 
Let's comment on all the set methods to show they're not needed anymore. 
They're being replaced with a single statement.

```java  
Account acc = new Account(1234567,1000d, "Korhan Çakmak","korhanertancakmak@gmail.com",3424241);
```

And running this, we can confirm that we executed the new constructor. 
You can see the first statement, in the output, "Account constructor with parameters called." 
Running with some System.out.println statements:

```java  
System.out.println("Account number = " + acc.getNumber());
System.out.println("Account balance = $" + acc.getBalance());
```

This shows us that the number account number printed, as well as the initial deposit amount, 
which we set to $1000. 
So having multiple constructors as we've done here, 
is called "constructor overloading." 
It looks a lot like method overloading, doesn't it?

### Constructor Overloading

Constructor overloading is declaring multiple constructors, with different formal parameters. 
The number of parameters can be different between constructors. 
Or if the number of parameters is the same between two constructors, 
their types or order of the types must differ.



```java  

```
```java  

```
```java  

```
```java  

```
```java  

```


![Step-3]()
![Step-3]()
![Step-3]()