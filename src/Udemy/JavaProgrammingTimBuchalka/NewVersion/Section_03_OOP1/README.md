# [Section-3: Object-Oriented Programming (PART-I)]()

In this part of OOP, you're going to learn about the fundamentals of object-oriented programming, 
starting first with the structures we'll be using, specifically Classes, Objects, 
and Constructors. 
Along with these structures, we'll be learning Inheritance, Encapsulation, 
Polymorphism, and Composition, which are all fundamentals features of OOP.

Part-I covers only Inheritance, Part-II covers the others.

## [a. What is Object-Oriented Programming?]()

Object-oriented programming is a way to model real world objects, 
as software objects, which contain both data and code. 
OOP is a common acronym for Object-Oriented Programming. 
And in particular, we'll be talking about classes, 
which is a fundamental component of OOP in Java, 
and other languages for that matter. 
It's sometimes called Class-based programming. 
Class-based programming starts with classes, which become the blueprints for objects.

### Real World Object Exercise

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

### The Class as the blueprint

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

## [b. Organizing classes]()

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

## [c. Encapsulation]()

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

## [d. Getter & Setter Methods]()

So best practices for fields, that in general, fields on classes should be private, 
and a getter method should be created to access those fields. 
This provides encapsulation of the internals of our class, 
and supports maintenance of a public interface that doesn't have to change, 
even though our class might.

In this course, we're going to be setting up and talking about setter methods, 
which set data on the objects. 
We'll end this course by creating more objects out of the Car class, 
and talking more about that process. 
We'll be using the code from the last code, where we ended it, 
with the Main and Car classes.

So now, let's add the setter methods in the Car class:

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
                                            
We get the values we set on the car and not the defaults.

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

So now we've built a rule that we're supporting only three manufacturers. 
And we'll enforce that rule, so that if anything else is passed, 
we set make to "Unsupported." 
But the argument is null, we set it to "Unknown." 
Go back and test this by the changing Porsche to Maserati. 
And after running the code, you can see that "Unsupported" not "Maserati."

So you can see how it's very useful to have this type of functionality, 
the validation. 
So what you can do with the setter methods is you can set up all the rules 
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

We haven't included the rest (= new Car();)
Part. 
Already, IntelliJ is saying, "Variable car may not have been initialized," 
where we are attempting to call the setter method, on the car variable. 
You can't use an uninitialized variable, which car is, 
because we haven't assigned any object reference to it. 
But now, consider what happens, if we instead assign null to a car:

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

### Classes Challenge Exercise

I want to challenge your understanding of the previous classes. 
So here, what I want you to do:

### Object Oriented Challenge

Create a new class for a bank account. Crate fields for account characteristics like:
* account number
* account balance
* customer name
* email
* phone number

Create getters and setters for each field. Create two additional methods:
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

## [e. Constructors]()

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

    // Constructor Overloading
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

We've done here to set the initial parameters or the initial values of the fields. 
And you can include any other initialization code you want to perform in the constructor. 
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
We can then use these values to assign data to our fields, 
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
You can see the first statement in the output, "Account constructor with parameters called." 
Running with some System.out.println statements:

```java  
System.out.println("Account number = " + acc.getNumber());
System.out.println("Account balance = $" + acc.getBalance());
```

This shows us that the number account number printed, as well as the initial deposit amount, 
which we set to $1000. 
So having multiple constructors as we've done here 
is called "constructor overloading." 
It looks a lot like method overloading, doesn't it?

### Constructor Overloading

Constructor overloading is declaring multiple constructors, with different formal parameters. 
The number of parameters can be different between constructors. 
Or if the number of parameters is the same between two constructors, 
their types or order of the types must differ.

### Constructor Chaining

The concept of constructor chaining is the process of calling one overloaded constructor from another.
Constructor chaining is when one constructor explicitly calls another overloaded constructor. 
You can call a constructor only from another constructor. 
You must use the special statement **this()** to execute another constructor, 
passing it arguments if required. 
And **this()** must be the first executable statement if it's used from another constructor.

Let's look at another example, and this time we'll use constructor chaining, 
which we've said is, calling one constructor, from another constructor. 
That may sound a little bit confusing, but we'll cover some reasons why you would want to do this next.

```java  
public Account() {
    System.out.println("Empty constructor called");
}

public Account(int number, double balance, String customerName, String email, String phone) {
    System.out.println("Account constructor with parameters called");
    this.number = number;
    this.balance = balance;
    this.customerName = customerName;
    customerEmail = email;
    customerPhone = phone;
}
```

First, we'll use the default constructor to instantiate an object, and pass it some default values. 
In other words, from the constructor with no parameters, we'll call the one with five parameters, 
and pass in literal values. 
So to do that, we type this, followed by parentheses. 
Which constructor is called is determined by the values we pass. 
So we'll add a call to this in the no args constructor, and we'll just pass some literals as arguments. 
The type and number of arguments we pass must match one of our constructors. 
Since we only have another constructor declared, and it only has five parameters, 
we'll pass five arguments. 
But the types must match the order of the types that were declared in the constructor. 
So, the parameters are strings in the second constructor, customerName, email and phone:

```java  
public Account() {
    this(1234567, 2.5, "Default name", "Default address", "Default phone");
    System.out.println("Empty constructor called");
}
```

So what we're doing there with **this()**, is a special use of this, 
which you won't see used anywhere else. 
This is calling another constructor within a constructor. 
So what we're saying here is "look if you try and create an object from this class, 
and you don't give me any parameters, set this new object up with these values, 
by calling this other (second) constructor." 
Constructor chaining is optional, meaning it's not something you have to do, 
but there can be situations where you want to do this.

Now one other thing to keep in mind is, using **this**, to call another constructor, 
is that you have to be sure that it's a very first line that's executed. 
In other words, we couldn't have System.out.println, as the first line in the constructor. 
If we try that, we will have a compiler error, which says, 
"Call to **this()* must be first statement in constructor body". 
So the rules are pretty strict, using **this()** statement with parameters, 
can only be called in a constructor, and it has to be the very first line that's called.

Let's go back to our Main class, and we'll change the code to just call the empty constructor. 
And running that:

```java  
Account acc = new Account();
System.out.println("Account number = " + acc.getNumber()); 
System.out.println("Account balance = $" + acc.getBalance());
```

gives:

```java  
Account constructor with parameters called
Empty constructor called
Account number = 1234567
Account balance = $2.5
```

As you can see, that we get the System.out.println statement from both constructors. 
So they're actually both called as you can see there. 
The reason why you see it in that order makes sense if you think about it. 
If you come back here to the Account.java, you see
the very first line of the "no arguments" account constructor, 
called the other constructor with five arguments. 
So the statement in the five argument constructor was printed first. 
The fields were set to the values passed, and then the code returned to the no-args constructor. 
It then executed the line following the call to this, which printed out, "Empty constructor called." 
So as you can see, 1234567 and 2.5 were actually passed, and these are printed out. 
So it's obviously working, the default constructor is making a call to the five argument constructors,
which sets the fields to the values we specified.

Let's look again at the second constructor with five parameters:

```java  
public Account(int number, double balance, String customerName, String email, String phone) {
    System.out.println("Account constructor with parameters called");
    this.number = number;
    this.balance = balance;
    this.customerName = customerName;
    customerEmail = email;
    customerPhone = phone;
}
```

You may have noticed, looking at this code, is that we've actually updated the fields directly. 
We didn't call the setter methods from the constructors. 
So there's an alternative we could have done, is we actually could have 
done something like "setNumber(number)". 
If we had some validation in that setter, that was testing for valid numbers, 
and those types of things, we could actually execute that code as well.

Now in Java, there are conflicting opinions as to which is the best approach. 
Because you'll find out in the following courses, when we start talking about inheritance, 
and creating subclasses, these calls to setter methods might not work. 
So the general rule of thumb is, it's always better to assign the values directly to the field, 
rather than calling the setter, in a constructor. 
Because as you'll see in the next course, there can be scenarios where this code:

```java  
public void setNumber(int number) {
    this.number = number;
}
```

That's in this setter isn't executed. 
So by going back and actually coding it directly, in other words, going back and 
setting it to **this**, and whatever the field name is, 
you're guaranteed that the field values will be initialized. 
So my general rule of thumb is, with constructors:

**DO NOT call setters or any other method, other than another constructor within those constructors.**

And the other reason for that is, this is the point in the code where the object is being created. 
So consequently, some aspects of the initialization may not have been finished while 
you're in the constructor. 
And that's the other reason that there's an opinion out there
that suggests that you shouldn't be calling other methods, or even the setters 
within the constructor code. 
But we can talk more about that later if needed.

Let's now assume that we wanted to create another constructor, 
and for this one, we only want to pass the customer name, email address, and phone number. 
So we could do that by creating another constructor, by cutting and pasting 
and editing an existing constructor. 
But IntelliJ gives us yet another code generation tool, this one for constructors. 
So let's position our cursor after the second constructor, 
after a couple of additional empty lines. 
Next, we'll click on "Code" on the menu, and select "Generate" as the menu option. 
Then we'll select the first option which is constructor, 
and it asks which field you want to include in the constructor? 
So which ones are we going to have passed to us? 
In other words, which fields do we want the constructor to set? 
Let's pick 3 we talked about, customer name, email address, and the phone number.

```java  
public Account(String customerName, String customerEmail, String customerPhone) {
    this.customerName = customerName;
    this.customerEmail = customerEmail;
    this.customerPhone = customerPhone;
}
```

When we hit ok, we get a new constructor, as above, generated for us, 
setting the instance fields to the parameters passed. 
So there's our third constructor. 
And you can see, it's only setting three of five instance fields. 
So that's one way of doing it, buf of course, the disadvantage here  
is that our account number and our balance aren't included. 
But we could call the five argument constructors, and pass a couple of default values, 
so let's do that. 
We'll also comment out the initialization code 
because we'll be initializing them in the constructor with five parameters:

```java  
public Account(String customerName, String customerEmail, String customerPhone) {
    this(99999, 100.55, customerName, customerEmail, customerPhone);
    // this.customerName = customerName;
    // this.customerEmail = customerEmail;
    // this.customerPhone = customerPhone;
}
```

So you can see what we've done there is, we've defaulted 2 parameters, 
the account number to be 99999, and the default balance to $100.55. 
So we've come up with what the default is, because they weren't specified. 
And we've still gone back and called our major constructor. 
This is the one that actually updates all the fields. 
So you'll find, as you start creating and writing more complex code, 
It's not unusual to see multiple constructors like this. 
And in that situation, often you do all your initialization in the one constructor,
like you can see here:

```java  
public Account(int number, double balance, String customerName, String email, String phone) {
    System.out.println("Account constructor with parameters called");
    this.number = number;
    this.balance = balance;
    this.customerName = customerName;
    customerEmail = email;
    customerPhone = phone;
}
```

All other constructors can call this major constructor, 
passing default values or null references, as arguments. 
That's a good way of doing things, and it often leads to perfect coding, 
because you're not having to duplicate code, or duplicating initialization in more than one place.

So how do we call this new constructor when creating an account? 
We would call that very much the same, as we've been doing before. 
Let's create a new object here, using these three argument constructors. 
So, going back to the Main class in Description.txt, 
and adding our code just before the last bracket of the main method block:

```java  
Account korhansAccount = new Account("Korhan",
        "korhanertancakmak@gmail.com", "12345");
                            System.out.println("AccountNo: " + korhansAccount.getNumber() +
        "; name " + korhansAccount.getCustomerName());
```
                            
Let's run that to make sure that it's working.

```java  
Account constructor with parameters called
Empty constructor called
Account number = 1234567
Account balance = $2.5
Account constructor with parameters called
AccountNo: 99999; name Korhan
```
                            
And you can see the last line in the output is from our new Account, korhan's Account. 
99999 was the default account number that we used in three argument constructor, 
in the Account class, as you can see. 
And the name was Korhan, which was what we passed here.

So that's constructors. 
You'll see those used extensively in Java. 
And we'll be using them a lot in this course as we move forward, 
because they're a crucial part of creating objects from classes.

### [Constructor Challenge Exercise](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_03_OOP1/Course07_ConstructorChallenge)

For this challenge, you'll want to:
1. Create a new class, called Customer, with three fields:
   * name
   * credit limit
   * email address
   * 
2. Create the getter methods only for each field. 
You don't need to create the setters.

3. Create three constructors for this class:
   * First, create a constructor for all three fields 
   that should assign the arguments directly to the instance fields.
   * Second, create a no args constructor that calls another constructor, 
   passing some literal values for each argument.
   * And lastly, create a constructor with just the name and email parameters, 
   which also calls another constructor.

## [f. Reference vs Object vs Instance vs Class]()

In this course, we'll talk about references, objects, instances, and classes. 
By now, you've probably noticed that we use the words, reference, object, instance and class, 
quite a lot when talking about Java code. 
These new concepts may well be confusing at first. 
So we're going to try and go through the process, 
and show you exactly what each of these words means, in the context of Java programming.

You might remember from an earlier section of the course, 
we said that Object and Instance are interchangeable, 
and that we create an instance or object using a class. 
These new concepts may well be confusing at first, so let's talk about them.

Let's use the analogy of building a house to understand classes. 
Now a class is basically a blueprint for the house. 
Using the blueprint, we can build as many houses as we like, based on those plans. 
So thinking back to the physical world, we use the plans for the house 
to build many houses that have the same floor plan. 
Each house we build (in other words, going back to programming terms, 
each house we instantiate using the new operator) is an object. 
This object can also be known as an instance, often we'll say it's an instance of the class. 
So we would have an instance of house in this example. 
Getting back to the physical world, each house we build has an address (a physical location). 
In other words, if we want to tell someone where we live, 
we give them our address (perhaps written on a piece of paper). 
This is known as a reference.

So that piece of paper, with the address on it, this is known as a reference. 
Now, we can copy that reference as many times as we like, 
but there is still just one house that we're referring to. 
In other words, we're copying the paper that has the address on it, 
not the house itself. 
Or we're copying the paper that has the address on it, not the house itself. 
Now back to programming terms, we can pass references as parameters to constructors and methods.

Let's now go a bit deeper, to make this a little bit clearer. 
We've got some code below:

```java  
public class House {
    private String color;
    public House(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
```

So, we've got the class House, with an instance variable, also known as a field called color. 
Now also we have got a main method in a Main class (all the way below). 
Now this code in the main method is creating instances of the house class, 
changing the color, and printing out the result. 
So let's actually go through line by line, and see what happens when this code is executed.
Line-1 is :

```java  
House blueHouse = new House("blue");
```

Alright, this code creates a new instance of the House class. 
Remember House is a blueprint, and we are assigning it to the blueHouse variable. 
In other words, it is a reference to the object in memory. 
"blueHouse" is the variable, we're creating a new instance of the House class, 
and assigning it the color blue.
Line-2 is :

```java  
House anotherHouse = blueHouse;
```

Alright, so this next line, that we've got, creates another reference  
to the same object in memory. 
Here we have two references pointing to the same object in memory. 
There is still one house, but two references to that one object. 
In other words, we have two pieces of paper with the physical 
address of where the house is built (going back to our real world example), 
written down (if we came back to a real-world example to make that clearer).
Line-3 and Line-4 are :

```java  
System.out.println(blueHouse.getColor());       // prints blueSystem.out.println(anotherHouse.getColor());    // blue
```

So next on the right-hand side, I've got two println statements highlighted. 
So they print the value of the color variable, for blueHouse and also anotherHouse. 
Now in this scenario, both will print "blue" since we have two references to the same object.
Line-5 is :

```java  
anotherHouse.setColor("yellow");
```

Alright, the next line is calling the method setColor, and setting the color to yellow. 
Now both blueHouse and anotherHouse have the same color. 
Why? 
Remember, we have two references that point to the same object in memory. 
Once we change the color of one, both references still point to the same object. 
So consequently, they've both got the same value of yellow, in this example. 
In our real world example, there is still just one physical house at that one address, 
even though we have written the same address on two pieces of paper. 
So if we went ahead and painted the house yellow, 
then both references to the physical house still point to a house that is now yellow.
Line-6 and Line-7 are :

```java  
System.out.println(blueHouse.getColor());       // yellow
System.out.println(anotherHouse.getColor());    // yellow
```

Now, we've got a couple more println statements here. 
So these two println statements are printing the same color. 
Both now print "yellow" since we still have two references that point to the same object in memory.
Line-8 is :

```java  
House greenHouse = new House("green");
```

Here we are creating another new instance of the House class with the color set to "green." 
Now we have two objects in memory, but we have three references which are blueHouse, 
anotherHouse and greenHouse. The variable (reference) greenHouse points to a different 
object in memory, but blueHouse and "anotherHouse" point to the same object in memory.
Line-9 is :

```java  
anotherHouse = greenHouse;
```

Here we assign "greenHouse" to "anotherHouse."
In other words, we are referencing anotherHouse. 
It will now point to a different object in memory. 
Before it was pointing to a house that had the "yellow" color, 
now it points to the house that has the "green" color. 
In this scenario, we still have three references and two objects in memory, 
but blueHouse points to one object while anotherHouse 
and greenHouse point to the same object in memory.
Line-10-11-12 are :

```java  
System.out.println(blueHouse.getColor());       // yellow
System.out.println(greenHouse.getColor());      // green
System.out.println(anotherHouse.getColor());    // green
```

And finally, we have three println statements. 
The first will print "yellow" since the blueHouse variable(reference) points 
to the object in memory that has the "yellow" color, 
while the next two lines will print "green" since both 
anotherHouse and greenHouse point to the same object in memory.

So keep in mind that in Java, you always have a reference to an object in memory. 
There's no way to access an object directly, everything is done using that reference.

Finally, consider the code below for a moment:

```java  
new House("red);                        // house object gets created in memory
House myHouse = new House("beige");     // house object gets created in memory
// and its location (reference) is assigned to myHouse
House redHouse = new House("red");      // House object gets created in memory
// and its location (reference) is assigned to redHouse
```

On the first line, we create a new House, and make it red. 
But we aren't assigning this to any variable. 
This compiles fine, and you can do this. 
This object is created in memory, but after that statement completes, 
our code has no way to access it. 
The object exists in memory, but we can't communicate with it
after that statement is executed. 
That's because we did not create a reference to it.

On the second line, we do create a reference to the house object we created. 
Our reference, the variable we call myHouse, 
let's have access to that beige house, as long as our variable, myHouse, stays in scope, 
or until it gets reassigned to reference a different object.

On the third line, we're creating a red house again, 
but this is a different object altogether, from the red house we created on line 1. 
This third statement is creating yet another house object in memory, 
which has no relationship to the one we created on the first line.

So this code has three instances of a house, but only two references. 
That first object will stay in memory, with no reference to it, 
until Java's automatic process (appropriately called garbage collection), 
figures out there is no running code with a reference to that object, 
and deletes it. 
In fact, that first object is said to be eligible for garbage collection, 
immediately after that first statement. 
It's useless to the code because it's no longer accessible. 
There are times we might want to instantiate an object, 
and immediately call a method on it, and not assign the object to a variable reference, 
and we'll show you some reasons later on in the course. 
But 99% of the time, we'll want to reference the objects we create. 
So we'll immediately assign our new instance to a variable, 
creating a reference to communicate with it.

## [g. Static vs Instance Variables]()

Let's discuss the differences now between static variables and instance variables. 
So firstly, a static variable is declared by using the keyword "static." 
Static variables are also known as static member variables. 
Every instance of the class shares the same static variable. 
So if changes are made to that variable, all other instances of that 
class will see the effect of that change.

It is considered best practice to use the Class name, 
and not a reference variable to access a static variable.

```java  
class Dog {
    static String genus = "Canis";
    void printData() {
        Dog d = new Dog();
        System.out.println(d.genus);      // Confusing!
        System.out.println(Dog.genus);    // Clearer!
    }
}
```

This makes it clearer that the variable is associated with the Class and therefore shared, 
and the value is not stored with the instance. 
An instance isn't required to exist to access the value of a static variable.

```java  
class Dog {
    static String genus = "Canis";
}
class Main {
    public static void main(String[] args) {
        System.out.println(Dog.genus);       // No instance of Dog needs to exist, in order to access
        // a static variable
    }
}
```

Static variables aren't used very often, but can sometimes be beneficial. 
They can be used for:

* Storing counters.
* Generating unique ids.
* Storing a constant value that doesn't change, like PI, for example.
* Creating and controlling access to a shared resource.

Some examples of shared resources might include a log file, a database, 
or some other type of input or output stream.

```java  
class Dog {
    private static String name;
    public Dog(String name) {
        Dog.name = name;
    }
    public void printName() {
        System.out.println("name = " + name); // Using Dog.name would have made this code less confusing.
    }
}
public class Main {
    public static void main(String[] args) {
        Dog rex = new Dog("rex");               // create instance (rex)
        Dog fluffy = new Dog("fluffy");         // create instance (fluffy)
        rex.printName();                        // prints fluffy
        fluffy.printName();                     // prints fluffy
    }
}
```

In this example, we've got a class called Dog, and it's got a static variable called "name."
Now, there's a constructor that sets the static variable
to the parameter value passed to the constructor. 
And we've got a method there, called printName, which isn't static. 
So that's a pretty simple class, and inside Main, we're creating two instances of the Dog class, 
with the line, Dog rex = new Dog("rex"). 
We're creating an instance of the Dog class, and then we're passing the String rex, 
as a parameter, and that'll be the name of the dog. 
In the next line, we've got a similar situation, just passing the parameter, 
which will be used as the name, fluffy. 
So then we call the printName method, on both of the instances. 
So they're just regular instance methods, because they aren't defined using static. 
So both method calls will print fluffy. 
You might be wondering, why is that the case? 
Why would both methods here print fluffy?

Well, remember that static variables are shared between instances. 
So in other words, once we change the static variable, 
all instances will see that change we made. 
So, when we called the constructor with parameter "fluffy," 
it modified the static variable name, because both instances are sharing that variable. 
That's why it prints fluffy twice. 
So you could also say that all dogs have the same name, 
but that's logically incorrect. 
So hopefully, now you can see how static variables can be used inappropriately sometimes, 
as in this example. 
Probably, you were assuming that the dog's name would be associated with each instance of the Dog, 
and therefore would be different for each one.
One Dog would be named Fluffy, and the other would be Rex.

So this is a scenario where using a static variable probably wouldn't be a good idea, 
and using a regular instance variable would make a lot more sense, 
in this particular example. 
Alright, so let's move on now to instance variables.

### Instance Variables

They don't use the static keyword when you're defining them. 
They're also known as fields, or member variables. 
Unlike a static variable, Instance variables belong to a specific instance of a class. 
Each instance has its own copy of an instance variable. 
Every instance can have a different value. 
Instance variables represent the state of a specific instance of a class.

```java  
class Dog {
    private String name;
    public Dog(String name) {
        this.name = name;
    }
    public void printName() {
        System.out.println("name = " + name);
    }
}
public class Main {
    public static void main(String[] args) {
        Dog rex = new Dog("rex");               // create instance (rex)
        Dog fluffy = new Dog("fluffy");         // create instance (fluffy)
        rex.printName();                        // prints rex
        fluffy.printName();                     // prints fluffy
    }
}
```

So in this example, we've again got very similar code to what we looked at earlier, 
but this time, the variable "name" in the dog class isn't static. 
So it's just a regular instance variable. 
So once again, the constructor is setting the value, from the parameter passed, 
to that instance variable. 
But now the code will print rex, and on the next line fluffy, 
and that's because we're using instance variables. 
Each instance of the class has its own state, or its own values, 
for any variables that have been defined. 
So in other words, now every dog has got its own copy of the name field. 
We can also say that it's basically not shared like it was before, 
in the earlier example, which was using a static variable.

So in most cases, you'd probably want to use instance variables, 
but there'll be scenarios when it can be useful to use a static variable like in the earlier examples.

## [h. Static vs Instance Methods]()

Static methods are declared using a static modifier. 
Static methods can't access instance methods and instant variables directly. 
They're usually used for operations that don't require any data from 
an instance of the class (from "this"). 
If you remember, the "this" keyword is the current instance of a class. 
So inside a static method, we can't use the "this" keyword. 
Whenever you see a method that doesn't use instance variables, 
that method should probably be declared as a static method. 
For example, main is a static method, and it's called by the Java virtual machine 
when it starts the Java application.

```java  
class Calculator {
    public static void printSum(int a, int b) {
        System.out.println("sum= " + (a + b));
    }
}
public class Main {
    public static void main(String[] args) {
        Calculator.printSum(5, 10);
        printHello();                           // shorter from of Main.printHello();
    }
}
public static void printHello() {
    System.out.println("Hello");
}
```

In this example above, we've got a class called Calculator, with a static method called print sum. 
And it just prints the sum of two integer numbers. 
Then we've got the main class with two static methods, main and printHello.
Now inside main, we're calling the method printSum from the calculator class. 
So as you can see, to call the printSum method, we just need to type the class name, 
in this case, Calculator, and then the method name, printSum. 
Or in the second example, in the case of printHello,
we can just type the method name with parentheses, 
which will automatically call the printHello static method, 
because it's being invoked from a static method itself. 
So static methods don't require an instance to be created. 
We can just type the class name and use the dot notation with the method name to access them.

### Instance Methods

Instance methods belong to an instance(a specific instance), of a class. 
To use an instance method, we have to instantiate the class first, usually by using the "new" keyword. 
Instance methods can access instance methods and instance variables directly. 
Instance methods can also access static methods and static variables directly. 
What I mean by directly, is that we don't usually have to use the keyword "this,"
to use them. 
And we don't have to use the ClassName.StaticVariables to access static variables, 
though that can help with clarity.

```java  
class Dog {
    public void bark() {
        System.out.println("woof");
    }
}
public class Main {
    public static void main(String[] args) {
        Dog rex = new Dog();               // create instance
        rex.bark();                        // call instance method
    }
}
```

So in this example, we've got a class called Dog, with a method bark. 
So notice here, how the method bark is not using the static keyword this time. 
So in other words, that method is just a standard instance method. 
Then we've got our main class with a method main. 
Now inside main, the main method, we first need to create an instance of the dog class, 
and that's done with the line, Dog rex = new Dog(). 
As you can see, we're using the "new" keyword to create an instance of that class. 
After we've got the instance, we can call the instance method bark, in this case, 
by typing "rex.bark."
So the hard part here could be deciding when to create an instance, 
or when to create a static method. 
So let's see some basic rules that should help you decide.

![Step-1](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_03_OOP1/images/01StaticMethod.png?raw=true)

So here, we've got a small diagram that should help us decide
whether we need an instance or a static method. 
Now instance methods are created more often than static methods, 
but let's see how to follow this diagram. 
So the first question we'd ask ourselves is, should the method be static? 
After that question, the next question would be; does it use any fields? 
Instance variables in other words, or instance methods of this object? 
And remember, we're asking these questions about the proposed method we plan to write, 
so if that's true, in other words, it does use some fields and/or instance methods. 
Then we'd want to make it an instance method. 
In another scenario, if the method doesn't use, or is not proposed to use, 
any instance variables or instance methods, in that case, 
then we'd probably consider writing it as a static method.

So generally speaking, if we're not using any fields, or instance methods, 
with the new proposed method, we should consider making that method static, 
instead of a regular instance method. 
So that's the main differences between static and instance methods.

## [i. The POJO vs. The Record]()

In the last video, we talked about the Plain Old Java Object, 
and we showed you how it comes with a lot of what we call boilerplate code. 
It's code that's repetitive and follows certain rules. 
In our case, we had a constructor with parameters for every field on our class, 
as well as getters and setters for each field. 
We also set up a toString method on this class, 
so we could print out all the fields on our class in a nicely formatted way. 
This is code repeated over and over, for every POJO, or for every JavaBean, 
if you're using JavaBeans to leverage Java frameworks that use them. 
Once created, this code is rarely looked at or modified. 
In fact, there are tools that'll just regenerate all of this code 
if your underlying data or domain model changes. 
Even better though, Java introduced a new type, the record, 
which became part of the official language, in JDK 16.

### The Record Type

The record was introduced in JDK 14, and became officially part of Java in JDK 16. 
Its purpose is to replace the boilerplate code of the POJO, but to be more restrictive. 
Java calls them "plain data carriers. 
The word carrier is an important term 
because it means the record has more rules built-in than a POJO would. 
The record is a special class that contains data, that's not meant to be altered. 
In other words, it seeks to achieve immutability for the data in its members. 
It contains only the most fundamentals methods, such as constructors and accessors (or getters). 
Best of all, you the developer, don't have to write or generate any of this code.

We're back to our last course. 
In that course, we created a Student class with a constructor and some getters and setters, 
then we used a for loop to create five students. 
Let's create a record now. 
As we create a default class, but at this time, I'm going to pick Record, 
instead of the default Class, we've been using all along up until now. 
You'll see there are others, like Interface, Enum and Annotation, 
but don't worry, we'll be talking about those eventually. 
Right now, we care about the Record type. 
And I'll give it a name, LPAStudent, LPA stands for my own company name, 
Learn Programming Academy, and then Student.

```java  
public record LPAStudent() {
}
```

Now you can see this (LPAStudent.java file) looks like a class, 
it starts with a public access modifier, but instead of the "class" keyword, 
it's using the "record" keyword. 
Where's the magic you ask? 
It's hard to see at first. 
But, different from a class, you'll notice that, in this code, 
there are a set of parentheses after the name of this record, LPAStudent. 
Similar to a constructor, we can set up some parameters within those parentheses, 
so let's set some up, so that the parameters are the same as our Student class parameters. 
In fact, let me copy that parameter list from the Student class constructor. 
I'll pop over to Student.java, copy the parameter list I've set up in that first constructor, 
"String id, String name, String dateOfBirth, String classList," then 
I'll come back over to LPAStudent.java, and paste those parameters in the parentheses. 
And that's it! 
We're ready to use this record now, in our code. 
It's really that simple. 
Let's go to Description.txt, and show you.

Before we make any changes, let's run the main code as it is, and you'll remember we get the output
from the toString() method we'd generated in the Student class.

```java  
Student{id='S923001', name='Mary', dateOfBirth='05/11/1985', classList='Java MasterClass'}
Student{id='S923002', name='Carol', dateOfBirth='05/11/1985', classList='Java MasterClass'}
Student{id='S923003', name='Korhan', dateOfBirth='05/11/1985', classList='Java MasterClass'}
Student{id='S923004', name='Harry', dateOfBirth='05/11/1985', classList='Java MasterClass'}
Student{id='S923005', name='Lisa', dateOfBirth='05/11/1985', classList='Java MasterClass'}
```

Now let's use our new record. 
I'm simply going to use LPAStudent, in place of Student in the code here. 
Where I have "Student," I'll modify it to be LPAStudent, the name of our record. 
I'll use LPAStudent as the variable, or reference type.
And on the same line, I'll use LPAStudent after the new keyword, instead of Student.

```java  
for (int i = 1; i <= 5; i++) {
    LPAStudent s = new LPAStudent("S92300" + i, 
            switch  (i) {
            case 1 -> "Mary";
            case 2 -> "Carol";
            case 3 -> "Korhan";
            case 4 -> "Harry";
            case 5 -> "Lisa";
            default -> "Anonymous";
        },
        "05/11/1985",
        "Java MasterClass");
    System.out.println(s);
}
```

This code compiles, and if we run it:

```java  
LPAStudent[id=S923001, name=Mary, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923002, name=Carol, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923003, name=Korhan, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923004, name=Harry, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923005, name=Lisa, dateOfBirth=05/11/1985, classList=Java MasterClass]
```

We get output, that's very similar to the output we got before. 
The bracket type is different. 
We've replaced the 58 lines of code that we have in Student.java, 
with these 4 lines of code in LPAStudent.java. 
Now, the truth is, the record, LPAStudent doesn't have or support setter methods, 
but the other functionality, calling the constructor with four parameters, 
and printing the data out, is implicitly part of the record. 
What's really happening here?

### Implicit or Generated Code that Java provides

What does Java tell us about what is implicitly created 
when we declare a record as we did in this code?

```java  
public record LPAStudent(String id, String name, String dateOfBirth, String classList) {}
```

First, it's important to understand that the part that's in parentheses; 
this entire part is called the record header. 
The record header consists of components, a comma-delimited list of components. 
For each component in the header, Java generates:

* A field with the same name and declared a type as the record component. 
So for example, it sets up fields for us, as we have them in the parentheses. 
These become the fields of the record.
* The field is declared private and final. 
We'll be talking about "final" more later, but simply put, it means the field can't be modified.
* The field is sometimes referred to as a component field.

Java generates a toString() method that prints out each attribute in a formatted String. 
In addition to creating a private final field for each component, 
Java generates a public accessor method for each component. 
This method has the same name and type of the component, 
but it doesn't have any kind of special prefix, no get, or is, for example. 
The accessor method, for id in this example, is simply id(). 
This is easier to show you in the code. 
Let's create two Students, in the main method, one will use our POJO class, 
and one will use our record:

```java  
Student pojoStudent = new Student("S923006", "Ann",
        "05/11/1985", "Java MAsterclass");
LPAStudent recordStudent = new LPAStudent("S923007", "Bill",
        "05/11/1985", "Java Masterclass");
```

Now let's print some information out about each of these students:

```java  
System.out.println(pojoStudent);
System.out.println(recordStudent);
```

And if we run that:

```java  
LPAStudent[id=S923001, name=Mary, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923002, name=Carol, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923003, name=Korhan, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923004, name=Harry, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923005, name=Lisa, dateOfBirth=05/11/1985, classList=Java MasterClass]
Student{id='S923006', name='Ann', dateOfBirth='05/11/1985', classList='Java Masterclass'}
LPAStudent[id=S923007, name=Bill, dateOfBirth=05/11/1985, classList=Java Masterclass]
```

We can see our two new students at the end of the output, 
and notice the minor difference in the output. 
This shows us that like IntelliJ's generated method, toString, 
the record is printing information out similarly, 
though it doesn't include single quotes around Strings, 
and the brackets are different.

Now let's just print out a couple of the attributes ourselves, 
using the accessor (or getter) methods. 
We'll do this first for our POJO student:

```java  
System.out.println(pojoStudent.getName() + " is taking " +
        pojoStudent.getClassList());
        System.out.println(recordStudent.getName() + " is taking " +
        recordStudent.getClassList());
```

Now, you can see we have compiler errors for getName and getClassList, 
on that new line of code. 
We stated that Java's implicit code does not include the prefix get, 
and simply uses the same name as the component or field. 
Let's remove the prefix, and change the case of Name and ClassList, to use lower camel case:

```java  
System.out.println(pojoStudent.getName() + " is taking " +
        pojoStudent.getClassList());
        System.out.println(recordStudent.name() + " is taking " +
        recordStudent.classList());
```

And we can run that:

```java  
LPAStudent[id=S923001, name=Mary, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923002, name=Carol, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923003, name=Korhan, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923004, name=Harry, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923005, name=Lisa, dateOfBirth=05/11/1985, classList=Java MasterClass]
Student{id='S923006', name='Ann', dateOfBirth='05/11/1985', classList='Java MAsterclass'}
LPAStudent[id=S923007, name=Bill, dateOfBirth=05/11/1985, classList=Java Masterclass]
Ann is taking Java Masterclass
Bill is taking Java Masterclass
```

And we get the output Anne is taking Java Masterclass, and Bill is taking Java Masterclass. 
That's how we use accessor methods, which we've called getters up until now, 
because traditionally, the prefix get was used for the accessor method. 
And finally, let's see what happens if we try to set data on these two types of students. 
Let's say they've both added a class, my Java OCP Exam 829 course, for example.

```java  
pojoStudent.setClassList(pojoStudent.getClassList() + ", Java OCP Exam 829");
recordStudent.setClassList(recordStudent.classList() + ", Java OCP Exam 829");
System.out.println(pojoStudent.getName() + " is taking " + pojoStudent.getClassList());
System.out.println(recordStudent.name() + " is taking " + recordStudent.classList());
```

And here, you can see the line for "recordStudent" doesn't compile. 
It doesn't have a setter named setClassList. 
In fact, it doesn't have a setter at all. 
There is no way to set the class list, other than by passing the value in on the record header, 
or through the use of constructors. 
This is by design because a record's goal is to be immutable. 
Let's comment out that last line of code, and run our Description.txt class again.

```java  
LPAStudent[id=S923001, name=Mary, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923002, name=Carol, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923003, name=Korhan, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923004, name=Harry, dateOfBirth=05/11/1985, classList=Java MasterClass]
LPAStudent[id=S923005, name=Lisa, dateOfBirth=05/11/1985, classList=Java MasterClass]
Student{id='S923006', name='Ann', dateOfBirth='05/11/1985', classList='Java Masterclass'}
LPAStudent[id=S923007, name=Bill, dateOfBirth=05/11/1985, classList=Java Masterclass]
Ann is taking Java Masterclass, Java OCP Exam 829
Bill is taking Java Masterclass
```

And here you can see, for our POJO student, we could modify the class list.

Why have an immutable record?
There are more use cases for immutable data transfer objects, 
and keeping them well encapsulated. 
You want to protect the data from unintended mutations.

### POJO vs. Record

If you want to modify data in your class, you won't be using the record. 
You can use the code generation options for the POJO, as we showed in the earlier. 
But if you're reading a lot of records from a database or file source, 
and simply passing this data around, then the record is a big improvement.

We've only touched on some features of the record to give you an introduction. 
When we do talk more about the "final" keyword and immutability of data, 
especially as it may be affected by concurrent threads, we'll be revisiting this type. 
We'll also be showing it to you in action when we get to the Database and I/O sections of this course.

## [i. Plain Old Java Object (The POJO) and Overridden Methods]()

A plain old Java object (whose acronym is POJO) is a class that generally only has instance fields. 
It's used to house data and pass data between functional classes. 
It usually has few methods other than getters and setters. 
Many database frameworks use POJO's to read data from, or to write data to, 
databases, files or streams. 
You'll remember, we have said several times that a class can be thought of as a super data type. 
A POJO is really just that. 
It lets you extend, and combine, your definition of data types.

A POJO also might be called a bean, or a JavaBean. 
Maybe you've heard of the term JavaBean. 
A JavaBean is just a POJO, with some extra rules applied to it. 
These rules are in place, so that Java frameworks have a standard way to manipulate
and manage these objects. 
A POJO is sometimes called an Entity, because it mirrors database entities. 
Another acronym is DTO, for Data Transfer Object. 
It's a description of an object that can be modeled as just data.

There are many generation tools that will run a data model into generated POJO's or JavaBeans. 
You've seen an example of similar code generation in IntelliJ, 
which allowed us to generate getters, setters, and constructors in a uniform way, 
based on our class's fields.

Let's make an example of a POJO code that helps us to read data out of a database
that has a table of Student records. 
So, we need four fields: id, name, date of birth, and class list. 
We're going to make all of these Strings for this exercise. 
Later in the course, we'll make this a lot more interesting, with dates, arrays, and custom types.

```java  
public class Student {
    private String id;
    private String name;
    private String dateOfBirth;
    private String classList;
}
```

So now, let's make this a Plain Old Java Object. 
A POJO, in its simple form, requires a way to populate data, 
and we can do this with a constructor. 
We'll use IntelliJ's code generation tool 
to create a constructor first with all of these fields. 
Select 'Code' from menu, select 'Generate' next, and select 'Constructor,' 
and select all the fields, then press OK:

```java  
public Student(String id, String name, String dateOfBirth, String classList) {
    this.id = id;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.classList = classList;
}
```

So, this class, even without getters and setters, 
lets us create and populate new Student objects, using just this constructor. 
And let's really do that now, in the main method of the Main class. 
Let's have some fun with this exercise, and put some of the Java skills
we've learnt to date to work. 
We'll create five students in a loop. 
We'll use the loop variable to create a unique id. 
At first, we'll hard code the name as Mary, as well as set dateOfBirth, 
and classList, to some literal values.

```java  
for (int i = 1; i <= 5; i++) {
Student s = new Student("S92300" + i,
        "Mary",
        "05/11/1985",
        "Java MasterClass");
}
```

Okay, so here, we create five student objects, we pass an id that starts with S92300, 
then appends the loop index("i"), so each student has different id, 
and the rest of the values are literals. 
Let's now replace Mary. 
We're going to put a switch expression, that'll give us a different name, 
based on the value of the loop index i:

```java  
for (int i = 1; i <= 5; i++) {
Student s = new Student("S92300" + i,
        switch  (i) {
            case 1 -> "Mary";
            case 2 -> "Carol";
            case 3 -> "Korhan";
            case 4 -> "Harry";
            case 5 -> "Lisa";
            default -> "Anonymous";
        },
        "05/11/1985",
        "Java MasterClass");
}
```

So we're using a switch expression as a parameter to a constructor, 
which is pretty neat, you have to admit. 
Maybe you noticed that I have a default case label defined, although in this loop, 
it won't ever be true. 
This is actually a requirement for the switch expression 
when it's used with a numeric switch value. 
It requires that all possible values be resolved, and with a numeric switch value, 
the only way to really do this is to use a default label, 
like we did here. 
The last two parameters are simply hard-coded to the same birthdate, 
and the class name "Java Masterclass." 
So, we can run this, but since there's no output, it's not terribly interesting. 
I want to show you another generation option on IntelliJ, this one to print out data. 
When we get to inheritance, I'll explain how this method actually works, 
but for now, let's use it, because it really makes life a lot easier.

So, back to our Student POJO, put your cursor before the last closing brace of the class. 
We'll pick Code, and Generate, and now look for the option that says "toString()", 
and select that. 
Select all fields, then press OK.

```java  
@Override
public String toString() {
    return "Student{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", dateOfBirth='" + dateOfBirth + '\'' +
            ", classList='" + classList + '\'' +
            '}';
}
```

And now you see, we have the method called toString, 
which will print out all the attributes in our class, in a nicely formatted way. 
So the "toString()" method is a special method in Java. 
We can implement this method in any class we create, 
and doing this lets us print out the current state of our object. 
IntelliJ, when it generates this code, adds a special statement, 
as you can see, "@override" displayed above the method.

### Annotations

Anytime, you see a statement like this, which starts with an at symbol; 
this is called an "annotation." 
Annotations are a type of metadata. 
Metadata is a way of formally describing additional information about our code. 
Annotations are more structured and have more meaning than comments. 
This is because they can be used by the compiler, 
or other types of pre-processing functions, 
to get information about the code. 
And don't worry, we'll be covering the annotations in a later section of the course, 
as well as introducing you to the most commonly used. 
Metadata doesn't affect how the code runs, so this code will still run, 
with or without the annotation.

This particular annotation we see in our code, @Override, 
is one of the most common annotations in Java that you'll use. 
It tells the compiler that this is a special type of method in Java, an overridden method.

### Overridden Method

An overridden method is not the same thing as an overloaded method. 
And overridden method is a special method in Java, 
that other classes can implement if they use a specified method signature. 
So, similar to how we create the main method, with its special signature, 
we have to create the toString method a certain way. 
When we do that, we have access to special Java processing, which I'll show you next.

Popping back over to the Main class, and main method, 
we can simply print out each one of our student objects, using the System.out.println statement:

```java  
System.out.println(s);
```

You might notice that we didn't even call the toString method on the Student, 
the s variable in this loop, in the System.out.println statement. 
We didn't have to. 
This is another built-in feature from Java. 
Every object, when passed to System.out.println, will have the toString method implicitly executed, 
if you've created such a method in your class. 
And now, running this code, we get output:

```java  
Student{id='S923001', name='Mary', dateOfBirth='05/11/1985', classList='Java MasterClass'}
Student{id='S923002', name='Carol', dateOfBirth='05/11/1985', classList='Java MasterClass'}
Student{id='S923003', name='Korhan', dateOfBirth='05/11/1985', classList='Java MasterClass'}
Student{id='S923004', name='Harry', dateOfBirth='05/11/1985', classList='Java MasterClass'}
Student{id='S923005', name='Lisa', dateOfBirth='05/11/1985', classList='Java MasterClass'}
```
                            
And we can see the data in our five Student objects, or records. 
That's another pretty handy code generation tool to know we can use. 
So, this was contrived. 
However, you can imagine, if you were reading data from a database, 
or a comma-delimited file, how you could create a whole set of POJO's,
to collect all the data elements, in all your records. 
Once you have all this information in the POJO, 
you can pass these objects to whatever code would process it, 
that would need to do something with it, perhaps generate a mailing list or whatever.

So, for good measure, let's add the setters and getters to the POJO. 
Going back to the Student class, we'll generate these. 
I think you may know how to do it. 
And now, our code has all we need to manipulate data, setting, updating and retrieving data. 
So, we're lucky we have IntelliJ's code generation tool
to do so much of this work for us. 
This kind of code has a name, it's called boilerplate code. 
It's code that's repetitive and follows a pattern, 
which is why code generation tools can create it for us. 
But it's still a lot of code to look at, and we only have four fields in our POJO. 
Wouldn't it be nice if we had a type that did all this for us, 
and we didn't have to put all this code in our class? 
Well, we do! 
Java introduced a new type called the "record," 
which officially became part of Java, in JDK 16. 

## [k. Inheritance (Part-I)]()

We can look at Inheritance as a form of code re-use. 
It's a way to organize classes into a parent-child hierarchy, which lets the child inherit (re-use), 
fields and methods from its parent.

![Step-2](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_03_OOP1/images/02Inheritance1.png?raw=true)

The animal kingdom, with its many classifications, is a pretty good place to start 
looking at hierarchical relationships. 
On this scheme, we show a small part of the Animal classification chart. 
Each word on this diagram represents a Class. 
The most generic, or base class, starts at the top of the hierarchy, **Animal**. 
Every class below it is a subclass. 
So the **Animal** is the base class. 
All the other classes can be said to be subclasses of **Animal**.
A parent can have multiple children, as we see with **Mammal** which is the parent of Dog and Cat. 
A child can only have one direct parent in Java. 
But it will inherit from its parent class's parent, and so on.

Let's actually start working through some scenarios in code, so that I can show 
you what inheritance looks like in Java. 
First, let's start with an animal example. 
We'll create the animal base class, and define all the attributes 
and behaviors that animals have in common. 
We know that any animal would have characteristics, like size and weight. 
And animals move and make noise. 
Though animals move and make noise in unique ways, we declare the methods on the base class.
Let's see what this class might look like on a class diagram. 
This is just a drawing of the class, showing its fields first, 
and methods or behaviors in the section below that.

A class diagram, allows us to design our classes before we build them. 
This diagram shows the Animal class, with the attributes we think that every kind of animal has. 
All animals have a type (what kind of animal it is). 
All animals have a size and a weight. 
Below the fields, we have the behavior that animals have in common, move, and makeNoise. 
The move method will include a speed, fast or slow, as an argument.

Let's create this Animal class now. 
After we have the class in the editor, we'll add the fields from our diagram:

```java  
private String type;
private String size;
private double weight;
```

Okay, those are the fields, so now let's add a constructor. 
We'll use IntelliJ's code generation tool to do this:

```java  
public Animal(String type, String size, double weight) {
    this.type = type;
    this.size = size;
    this.weight = weight;
}
```

Now we've got the first constructor. 
Let's keep this code simple, so we'll leave the getters and setters out. 
But we do want a method to print the fields out, so again using IntelliJ's generation tool, 
we'll pick Code, Generate, and this time, we'll select toString. 
And again, pick all the fields, and hit OK.

```java  
@Override
public String toString() {
    return "Animal{" +
            "type='" + type + '\'' +
            ", size='" + size + '\'' +
            ", weight=" + weight +
            '}';
}
```

And there's our toString method, with all the fields in it. 
Ok, so we have attributes, a constructor, and a way to print out information about the animal, 
but we haven't implemented any behavior on Animal. 
Now, we'll add two methods on Animal, move and makeNoise. 
For move, we'll take a speed, which will just be a String for fast or slow, 
and then we'll print the animal type and the speed at which it's moving:

```java  
public void move(String speed) {
    System.out.println(type + " moves " + speed);
}
```

And now we'll add makeNoise, that doesn't have any parameters:

```java  
public void makeNoise() {
    System.out.println(type + " makes some kind of noise");
}
```

There you have it, we have our base class, the Animal, with its attributes, 
type, size and weight, and we have three methods on this class, toString, 
as well as move and makeNoise. 
Next, we want to create a more specific type of Animal, and for this, 
we're going to create a Dog class. 
Let's look at a class diagram that includes the Dog class.

```java  
public void makeNoise() {
    System.out.println(type + " makes some kind of noise");
}
```

![Step-3](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_03_OOP1/images/03DogAnimalInherits.png?raw=true)

So, here we show a Dog class above, but connected to Animal. 
This means "Dog" inherits from Animal. 
We can also say "Dog" is a type of Animal when we create a Dog object, 
it'll inherit type, size, and weight fields from the Animal class. 
These don't have to be explicitly declared in Dog. 
This is also true for the method move, and makeNoise. 
We can specialise in the Dog class with its own fields and behavior. 
We do this here, with earShape and tailShape, because dogs have ears and tails, 
whose shape is unique to their breeds. 
We're also saying Dogs have behaviors like bark, run, walk, and wagTail. 
How do we build this in Java and make Dog inherit from Animal? 
Let's create the Dog class, first. 
To specify we want this class to inherit from Animal, we use another Java keyword, **extends**.

Using **extends** specifies the superclass (or the parent class) of the class we're declaring. 
We can say Dog is a subclass, or child class, of Animal. 
We can say Animal is a parent, or superclass of Dog. 
A class can specify one, and only one, class in its extends clause. 
Let's add that clause to our Dog class, it goes right after the class name, 
and before the opening curly brace:

```java  
public class Dog extends Animal {
}
```

Now, you notice straight away that we've got an error. 
The error on the screen is saying, "There is no default constructor available 
in '.Animal' class" and that's true; we never did create one, meaning we never created a constructor, 
with no arguments, on Animal. 
But why does it matter to the Dog class? 
Well, right now we haven't declared any constructor for Dog, 
but maybe you'll remember that Java will declare one, implicitly, if we don't explicitly declare one. 
And just to see that, let's add a constructor to Dog, which mirrors the implicit constructor Java creates
for us:

```java  
public class Dog extends Animal {
    public Dog() {
        super();
    }
}
```

This is what the implicit constructor looks like, and now our error is in "super()". 
The reason it isn't working is that statement on that line, super parentheses, 
saying that "Expected 3 arguments but found 0" so maybe, you're asking, what are super parentheses? 
You'll remember we used the keyword this, followed by parentheses and parameters, 
as a way to call another constructor in the same class. 
Well, super parentheses are similar to that. 
It's a way to call the constructor on the parent class, or superclass. 
Here, we're calling Animal's constructor by using the keyword super, and then parentheses, 
which calls the default constructor on Animal.

**super()** is a lot like **this()**.
It's a way to call a constructor on the super class, directly from the subclass's constructor. 
Like **this()"-**, it has to be the first statement of the constructor.
Because of that rule, "this()" and "super()" can never be called from the same constructor. 
If you don't make a call to super(), then Java makes it for you, using super's default constructor. 
If your superclass doesn't have a default constructor, 
then you must explicitly call "super()" in all of your constructors, 
passing the right arguments to that constructor. 
Those are a lot of rules, but don't worry if that's confusing right now. 

We have a compiler error on Dog because Animal doesn't have a default, 
or no argument constructor declared for it. 
Let's go back to Animal and add that in:

```java  
public Animal() {}
```

And now we've got everything compiled, so that's a good thing. 
Before we do anything else with Dog, let's go back to the Main class. 
Before we create any instances, I want to create a method on the Main class, 
that'll take any Animal object, and execute its three methods. 
We'll call it doAnimalStuff, and pass it an Animal object, and the speed we want this animal to move. 
And then we'll have animal makeNoise, move, and then we'll print out all the attributes on animal.

```java  
public static void doAnimalStuff(Animal animal, String speed) {
    animal.makeNoise();
    animal.move(speed);
    System.out.println(animal);
    System.out.println("_ _ _ _");
}
```

This method is static, because we want to call it from the main method. 
The last line is just to separate the data, so reading the output is easier. 
Let's create an instance of Animal first, and then pass that to this method:

```java  
public static void main(String[] args) {
    Animal animal = new Animal("Generic Animal", "Huge", 400);
    doAnimalStuff(animal, "slow");
}
```

We created an animal object, gave it the type "Generic Animal" and the size "huge" as 
well as a weight of 400.
And running that, we get:

```java  
Generic Animal makes some kind of noise
Generic Animal moves slow
Animal{type='Generic Animal', size='Huge', weight=400.0}
_ _ _ _
```

Ok, so that's a generic animal. Let's create a dog this time:

```java  
Dog dog = new Dog();
doAnimalStuff(dog, "fast");
```

Before we run this, do you notice what we're doing here?
We're passing a Dog object as the method argument, when the type was declared as an Animal. 
Why is this okay? 
It works because "Dog" inherits from Animal, it's a type of Animal, 
as we've said, and where that becomes really important 
is in code like this. 
We can pass a dog instance to any method that takes an animal. 
And running that:

```java  
Generic Animal makes some kind of noise
Generic Animal moves slow
Animal{type='Generic Animal', size='Huge', weight=400.0}
_ _ _ _
null makes some kind of noise
null moves fast
Animal{type='null', size='null', weight=0.0}
_ _ _ _
```

The code compiles, runs, and we get output. 
We created a Dog with a default constructor(no arguments passed), 
so nothing got set on this class, 
but you can see Dog has inherited all-Animals attributes on that last line. 
The values have the default values for their type, 
because we didn't create a way to pass any data to these fields on Dog. 
So far, all we've done with Dog is extend Animal. 
Next, let's change our default constructor in Dog, and this time, 
when we call "super()", we'll pass values to Animal's three parameter constructors. 
We'll pass the type of dog, which here we'll call it a mutt, then the size, big, and 50 for the weight.

```java  
public Dog() {
    super("Mutt", "Big", 50);
}
```

And we can rerun our main method:

```java  
Generic Animal makes some kind of noise
Generic Animal moves slow
Animal{type='Generic Animal', size='Huge', weight=400.0}
_ _ _ _
Mutt makes some kind of noise
Mutt moves fast
Animal{type='Mutt', size='Big', weight=50.0}
_ _ _ _
```

And now, the output has our Mutt's attributes, so it prints out that Mutt makes some kind of noise, 
Mutt moves fast, and then it shows the other attributes on Mutt. 
What I want you to see here is, look at the Dog class. 
It's six lines of code, including the opening and closing braces and white space. 
But this code can now execute three methods, and has three attributes we can set, 
all inherited from Animal. 
And better yet, we can treat this Dog like any Animal, for any code that uses the Animal class. 
Our doAnimalStuff method in the Main class didn't have to change at all, 
even though we introduced a new class. 
Up until this point, we haven't specialized anything for the Dog.

## [l. Inheritance (Part-II)]()

We introduced you to the concept of Inheritance, and showed you how to implement it in Java, 
using the "extends" keyword. 
We talked about using the statement "super()" to call the constructor on the superclass. 
In the last course, we created Dog as a subclasses of Animal, 
and demonstrated that Dog inherited all of Animal's fields, 
and that it can be passed to methods that are declared with the type of Animal. 
In this course, we want to make Dog different from Animal, 
by declaring the fields and methods that are specific to it.

Let's look at the class diagram for just Dog, at the moment.

![Step-4](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_03_OOP1/images/04Dog.png?raw=true)

We want to add the attributes, earShape and tailShape, as shown here, in our class diagram. 
Then we'll implement the methods, bark, run, walk, and wagTail. 
Going back to the Dog.java file, we'll add earShape and tailShape.

```java  
public class Dog extends Animal {
    private String earShape;
    private String tailShape;

    public Dog() {
        super("Mutt", "Big", 50);
    }
}
```

Those are our Dog's specific fields. 
Let's create a more specific Dog constructor than the one we have. 
This time, we have an additional choice, and that's which parent constructor we should use. 
Let's pick the one with parameters. 
And then, we pick both of our dog attributes, and hit OK.

```java  
public Dog(String type, String size, double weight, String earShape, String tailShape) {
    super(type, size, weight);
    this.earShape = earShape;
    this.tailShape = tailShape;
}
```

Let's actually change this constructor, so it's easier to create a Dog object. 
We'll remove size as a parameter, and instead write code to derive it, passing that to the super call.

```java  
public Dog(String type, double weight, String earShape, String tailShape) {
    super(type, weight < 15 ? "small" : (weight < 35 ? "medium" : "large"), weight);
    this.earShape = earShape;
    this.tailShape = tailShape;
}
```

This constructor has a combination of the Dog and the Animal fields, in its argument list. 
We can pass it the type of the dog, the dog's weight, and the earShape and tailShape. 
We're calling the super constructor to set some of our fields, the Animal specific fields. 
And for the size, we're deriving the size of the dog from the weight, small, medium, or large. 
To do this, we use a nested ternary operator, which is passed directly to the super constructor. 
We couldn't do this operation before the call to super, because "super()" must be the first statement. 
But we can do it directly like this, as an expression, in the argument list. 
This is one way to perform calculations, in your constructor, and pass the result to the super call. 
After the call to the super constructor, we set some of the Dog-specific attributes, 
the earShape and tailShape; that were passed to us.

Before we do anything else, let's generate toString method for the Dog class. 
But this time, not the template as "String concat(+)", which gives this:

```java  
@Override
public String toString() {
    return "Dog{" +
            "earShape='" + earShape + '\'' +
            ", tailShape='" + tailShape + '\'' +
            '}';
}
```

but the one says the template as "String concat(+) and super.toString()", 
which also includes super.toString:

```java  
@Override
public String toString() {
    return "Dog{" +
            "earShape='" + earShape + '\'' +
            ", tailShape='" + tailShape + '\'' +
            "} " + super.toString();
}
```

You can see 2 new fields there, plus a call to super.toString(). 
Now, this super is different from the super parentheses call. 
It's a lot like using the "this" notation to access a field on the current instance. 
This code lets us call a method of superclass. 
We'll talk about this a lot more in upcoming courses. 
I want to add 1 more constructor, after the first one, before we test this:

```java  
public Dog(String type, double  weight) {
    this(type, weight, "Perky", "Curled");
}
```

This constructor makes it even simpler to create a Dog object, 
for the majority of dogs (if their ears are Perky, and their tails are Curled). 
It calls the other Dog constructor that has four parameters, which in turn calls the Animal constructor.
We're using constructor chaining to make this work. 
Let's go back to Description.txt, and create some more dogs, calling the doAnimalStuff for each one:

```java  
Dog yorkie = new Dog("Yorkie", 15);
doAnimalStuff(yorkie, "fast");
Dog retriever = new Dog("Labrador Retriever", 65,
        "Floppy", "Swimmer");
doAnimalStuff(retriever, "slow");
```

Dogs have over 20 ear types, and more than 9 tail types, one of which is a Swimmer tail type, 
which we use here. 
And now, check out what gets printed for both of these Dog objects, when we run this code:

```java  
Yorkie makes some kind of noise
Yorkie moves fast
Dog{earShape='Perky', tailShape='Curled'} Animal{type='Yorkie', size='medium', weight=15.0}
_ _ _ _
Labrador Retriever makes some kind of noise
Labrador Retriever moves slow
Dog{earShape='Floppy', tailShape='Swimmer'} Animal{type='Labrador Retriever', size='large', weight=65.0}
_ _ _ _
```

We get all the fields that are specific for Dog, and the fields that are more general to the Animal. 
That's because our toString method printed out the Dog fields then made a call to super.toString(), 
which was Animal's toString method. 
We were also able to calculate the size of the dog, based on its weight. 
And we created constructors for Dog, that targeted more Dog-like features, 
and passed default values for Animal's more general fields.

Let's talk a little bit about the behavior of Animal and Dog next. 
Up to now, we called methods on Animal, and were able to access the functionality 
that's part and parcel of the animal class, and we're able to use methods on, 
and through Dog, that were defined on Animal. 
And even more importantly, we're doing this from a method 
that really doesn't even know anything about the Dog class. 
Dog can use methods on Animal and print out its own object's values, 
in this case, both the move and makeNoise methods printed the type field. 
You can see why inheritance promotes code re-use.

### Code Re-use

All subclasses can execute methods, even though the code is declared on the parent class. 
The code doesn't have to be duplicated in each subclass. 
But it does even get better than that. 
We can use code, out of the box, from the parent, as we did in this example. 
Or, we can change that code for the subclass. 
We did this with the toString() method.

The toString() method called in the doAnimalStuff method of the Main class didn't call 
the Animal toString method. 
It is called the Dog toString() method, when an animal is an instance of a Dog. 
I want you to really understand that, because it's so important. 
And really, it's one of the best parts about this inheritance feature.

We told doAnimalStuff method that we were dealing with an Animal class (the first parameter of it), 
and we called the toString method (by the command of "System.out.println(animal)"), 
which is declared as a method on Animal.

```java  
@Override
public String toString() {
    return "Animal{" +
            "type='" + type + '\'' +
            ", size='" + size + '\'' +
            ", weight=" + weight +
            '}';
}
```

At run time, Java figures out the Animal object is even more specific than Animal, 
it's really a Dog, and it actually calls the toString() method on Dog (if one exists on Dog). 
If the toString() method doesn't exist on Dog, that's no problem, 
because then it just uses the toString method on Animal. 
This is good stuff, so let's explore this a little bit more.

Let's create a makeNoise method on Dog next, and this method has the same signature as makeNoise on Animal:

```java  
public void makeNoise() {
}
```

And now what happens if we run our code?

```java  
Generic Animal makes some kind of noise
Generic Animal moves slow
Animal{type='Generic Animal', size='Huge', weight=400.0}
_ _ _ _
Mutt moves fast
Dog{earShape='null', tailShape='null'} Animal{type='Mutt', size='Big', weight=50.0}
_ _ _ _
Yorkie moves fast
Dog{earShape='Perky', tailShape='Curled'} Animal{type='Yorkie', size='medium', weight=15.0}
_ _ _ _
Labrador Retriever moves slow
Dog{earShape='Floppy', tailShape='Swimmer'} Animal{type='Labrador Retriever', size='large', weight=65.0}
_ _ _ _
```

The last time we ran this code, we had statements that said "the Yorkie and Labrador made noise." 
But now we don't see anything like that. 
This method, makeNoise on Dog, which doesn't do anything, was called, 
and not the makeNoise method, on Animal. 
What have we really done here? 
Well, we've overridden Animal's makeNoise method.

### Overriding a method

Overriding a method is when you create a method on a subclass, 
which has the same signature as a method on a superclass. 
Remember that a method signature consists of the method name, 
and the number and types of parameters. 
You override a parent class method when you want the child class 
to show different behavior for that method.

So notice, in IntelliJ that it has a special icon next to this makeNoise method, 
the little o with an arrow.
This is IntelliJ telling us this method is overriding a parent class's method.
Another option, is to use IntelliJ's code generation tool to override methods.

Let's use that way now, to override the move method on Animal.
Select 'Code' from the menu, but let's select 'Override Methods' this time. 
And IntelliJ is showing us all the methods we could override, starting with Animal, 
but it's also showing us methods on java.lang.Object. 
I'll be talking about java.lang.Object, in just a bit. 
Let's pick the move method on Animal, and hit the ok button:

```java  
@Override
public void move(String speed) {
    super.move(speed);
}
```

Now, look at the difference, between the code we created manually, the makeNoise method, 
and this one, the move method that IntelliJ created for us. 
IntelliJ's generation tool adds this @Override symbol, and that's to remind us, 
that we're overriding a method in the superclass. 
In this case, it's in the Animal class. 
And notice that the automatically generated code 
simply makes a call to the parent class's method, move using the keyword super and dot move. 
What that means is, we're calling the move method on the parent class, the Animal class. 
This code kind of does the same thing as not having that overridden method at all. 
It simply executes the Animal class's move method, which would have happened 
if we didn't create this method at all. 
Why would we do this? Well, most likely, we'll want to change or extend the code here. 
We changed the makeNoise method by having a method with no code at all. 
This changed the behavior of makeNoise for all Dog objects. 
It made all our dogs silent, for the moment.

Next, let's extend the functionality for the move method. 
This means we'll do what the animal class does, but we'll do additional stuff as well. 
We'll leave the "super.move" statement there, but we'll add more code. 
Here, we'll just print out another statement, that Dogs walk and run, and wag their tail:

```java  
@Override
public void move(String speed) {
    super.move(speed);
    System.out.println("Dogs walk, run and wag their tail ");
}
```

And running the code now:

```java  
Generic Animal makes some kind of noise
Generic Animal moves slow
Animal{type='Generic Animal', size='Huge', weight=400.0}
_ _ _ _
Mutt moves fast
        Dogs walk, run and wag their tail
Dog{earShape='null', tailShape='null'} Animal{type='Mutt', size='Big', weight=50.0}
_ _ _ _
Yorkie moves fast
        Dogs walk, run and wag their tail
Dog{earShape='Perky', tailShape='Curled'} Animal{type='Yorkie', size='medium', weight=15.0}
_ _ _ _
Labrador Retriever moves slow
        Dogs walk, run and wag their tail
Dog{earShape='Floppy', tailShape='Swimmer'} Animal{type='Labrador Retriever', size='large', weight=65.0}
_ _ _ _
```

We can see from the output, that when we called the move method, 
we did what Animal had us do with that statement, 
"Yorkie moves fast" but we added another line of text to the output, 
"Dogs walk, run and wag their tail." 
We extended the functional behavior of Animal for Dogs. 
We used what was there (with the call to "super.move"), but then added our own code to it. 
I think this is a good place to end this course.

We created methods on a superclass, then called them from a subclass, directly, 
showing you that methods can be inherited, as well as fields. 
We also showed you that a subclass can override the methods of a superclass.
The overridden method can do one of three things:

1. It can implement completely different behavior, overriding the behavior of the parent.
2. It can simply call the parent class's method, which is somewhat redundant to do. 
This is the default behavior of an inherited method.
3. Or the method can call the parent class's method, and include other code to run, 
so it can extend the functionality for the Dog, for that behavior.

## [m. Inheritance (Part-III)]()

In the last course, we were working on a simple class diagram, to create two classes, 
Animal and Dog, where Dog inherited from Animal. 
Let's continue with this example, by adding other methods that are really specific to Dog. 
Remember the Class diagram for Dog and Animal:

![Step-5](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_03_OOP1/images/04Dog.png?raw=true)

We can see there, the methods on Dog are bark, run, walk, and wagTail. 
Let's add these, starting with the bark method. 
I'm going to make this method private, because I'm going to call it from the move method. 
This is a reminder that not all methods need to be exposed, 
especially if you only intend them to be called internally from the current class.

```java  
private void bark() {
    System.out.println("Woof! ");
}

private void run() {
    System.out.println("Dog running ");
}

private void walk() {
    System.out.println("Dog walking ");
}

private void wagTail() {
    System.out.println("Tail wagging ");
}
```

Okay, so those are our Dog behaviors, so let's use some of these methods from Dog's move method.

```java  
@Override
public void move(String speed) {
    super.move(speed);
    //System.out.println("Dogs walk, run and wag their tail ");
    if (speed == "slow") {
        walk();
        wagTail();
    } else {
        run();
        bark();
    }
}
```

Now if we run that:

```java  
..... // above all the same(since there is no move method output)
Yorkie moves fast
Dog running
Woof!
Dog{earShape='Perky', tailShape='Curled'} Animal{type='Yorkie', size='medium', weight=15.0}
_ _ _ _
Labrador Retriever moves slow
Dog walking
Tail wagging
Dog{earShape='Floppy', tailShape='Swimmer'} Animal{type='Labrador Retriever', size='large', weight=65.0}
_ _ _ _
```

the output shows our dogs moving, first the "yorkie moves fast" 
and then it prints "Dog running, woof!"
Then we have "Labrador Retriever moves slow"
and that's now also printing "Dog walking, tail wagging."
And again, nothing in the doAnimalStuff method had to change 
for this new functionality to be called. 
I hope you're starting to get a little glimpse at how powerful a feature this is. 
And lastly, we can call the bark method in the overridden method, makeNoise, 
which right now has no code in it. 
Here we're not calling Animal's makeNoise method, and we don't want to. 
We want the Dog to have behavior that the Animal doesn't have.

```java  
public void makeNoise() {
    bark();
}
```

Let's run that:

```java  
Woof!
Yorkie moves fast
Dog running
Woof!
Dog{earShape='Perky', tailShape='Curled'} Animal{type='Yorkie', size='medium', weight=15.0}
_ _ _ _
Woof!
Labrador Retriever moves slow
Dog walking
Tail wagging
Dog{earShape='Floppy', tailShape='Swimmer'} Animal{type='Labrador Retriever', size='large', weight=65.0}
_ _ _ _
```

Now, we can get "woof" for both types of dogs, when we call the makeNoise method, in the main class. 
We've overridden the makeNoise method, with code unique to the dog, 
which, in this case, makes a call to the bark method. 
And that's how you do it, you can separate the functionality just for a dog, 
and only include it in the dog class. 
Let's try to change our makeNoise method again. 
This time, if the type of the dog is a Wolf, let's have the Dog howl and bark.

```java  
public void makeNoise() {
    if (type == "Wolf") {
        System.out.println("Ow Woooo! ");
    }
    bark();
}
```

Now, in this case, where we're referencing type we get a compiler error, 
which says "**type** has private access in **Animal**. 
This is because the type has private access in Animal. 
But the type is one of the fields inherited by Dog.
Yes, but because the type is private on Animal, no other classes, 
not even subclasses, can access or use this field, in its own methods. 
We've said there's a modifier that allows access for subclasses, and that's the protected modifier. 
Let's go to the Animal class, and change the modifier from private to protect for the type field. 
What this modifier says is, let any class, that is a subclass, access this field. 
This is a conditional encapsulation. 
We're allowing some limited access, to our internal fields, and that's to subclass. 
Protected access also means that any classes in the same package will also have access. 
And changing that modifier means our code compiles successfully.

Let's look at that code in Dog again. 
Notice here that we just simply reference type here. 
We didn't add any other qualifier, not this(), or super(),
and we didn't have to call the type from a different instance of Dog.
This is another advantage of Inheritance, for fields and methods that aren't private.
They can be accessed directly, as if they really were declared, on the subclass itself.
Java first looks on the subclass for a method or a field with that name; 
then it'll go up the inheritance tree, looking for a match.
Let's quickly test this by creating a wolf in the main method of the Main class:

```java  
.... (nothing changed)
Dog wolf = new Dog("Wolf", 40);
doAnimalStuff(wolf, "slow");
```

And now, running this code:

```java  
..... (nothing changed)
Ow Woooo!
Woof!
Wolf moves slow
Dog walking
Tail wagging
Dog{earShape='Perky', tailShape='Curled'} Animal{type='Wolf', size='large', weight=40.0}
_ _ _ _
```

We can see all the information about our wolf, and we can also see the output
that our wolf is howling, as well as barking. 
That was the Dog class. Let's add another subclass, 
so you can get used to this inheritance concept, 
and the idea of extending another class.

Let's add the Fish class to our hierarchy. 
What are some unique characteristics of a fish?
Well, let's go with a couple, like gills, and fins.
And instead of a generic move method, we might have more specific methods like moveMuscles,
and moveBackFins.
Let's look at our class diagram that includes this new Fish class:



                    Fish =>
                            fins  : int
                            gills : int
                            -----------
                            moveMuscles()
                            moveBackFins()

    It's quite a bit different from Dog, but it's still an Animal. This diagram shows a new class named Fish, that extends
    Animal. It has 2 fields and 2 methods, specific to its own type. Let's build that Fish class.

                    public class Fish extends Animal {
                        private int gills;
                        private int fins;

                        public Fish(String type, double weight, int gills, int fins) {
                            super(type, "small", weight);
                            this.gills = gills;
                            this.fins = fins;
                        }
                    }

    Also we pick the Animal constructor with parameters as its first constructor, and then 2 field for Fish. And like we
    did with Dog, we changed "size" parameter from the input parameter, and this time, to be hard coded to "small", for
    simplicity. This constructor is a lot like Dog's. We're calling the super constructor, the constructor on Animal, and
    we pass the type, the size, and this time we'll make all fish small. And finally, we pass the weight. Then we add the
    assignments for Fish's more specialized fields, gills and fins. So, very similar to the dog class. But in this case,
    we've created a new fish class, that inherits from the animal class, and we've defined some unique characteristics
    for the fish, namely, gills and fins. Let's next add Fish's custom behavior, and add the method, moveMuscles first,
    and we'll just print out a statement for that. And we'll make this method private, because we only want the move method
    to call it. We won't expose this behavior in other words, for any outside code to call it directly.

                    private void moveMuscles() {
                        System.out.print("muscles moving ");
                    }

                    private void moveBackFin() {
                        System.out.print("Backfin moving ");
                    }

    And now, we'll override the move method from Animal, so that our fish moves(or swims). With our cursor right before
    the closing brace of the Fish class, we'll start typing public void, and you'll see IntelliJ pops up a list of methods,
    and from that we'll select move.

                    @Override
                    public void move(String speed) {
                        super.move(speed);
                    }

    And now we have the overridden move method generated for us. Like we did with the Dog class, let's extend this behavior
    for a fish. We'll have our fish move its muscles regardless of the speed, but use its backfin if it wants to go fast.

                    @Override
                    public void move(String speed) {
                        super.move(speed);
                        moveMuscles();
                        if (speed == "fast") {
                            moveBackFin();
                        }
                        System.out.println();
                    }

    That would be one way to model the fish moving, or swimming. It moves its muscles, and it moves the back fin, which
    the net result of that is, it actually propels itself or moves. Let's add a code-generated toString method for Fish,
    like we did for Dog, that print's both Fish's fields as well as Animal's.

                    @Override
                    public String toString() {
                        return "Fish{" +
                                "gills=" + gills +
                                ", fins=" + fins +
                                "} " + super.toString();
                    }

    And that's it. We've built the Fish class, so let's create an instance of fish, and call our doAnimalStuff method. And
    we can call that method with Fish, without changing that method at all, because Fish is another type of Animal.

                    Fish goldie = new Fish("Goldfish", 0.25, 2, 3);
                    doAnimalStuff(goldie, "fast");

    And running that:

                    _ _ _ _
                    Goldfish makes some kind of noise
                    Goldfish moves fast
                    muscles moving Backfin moving
                    Fish{gills=2, fins=3} Animal{type='Goldfish', size='small', weight=0.25}
                    _ _ _ _

    we can see the output from this additional code. Goldfish makes some kind of noise, Goldfish moves fast, and there we
    have muscles moving backfin moving. Again, we used Animal's fields and behaviors, the ones we wanted to use, and then
    added some more specific elements to the Fish class. And we passed Fish to a method, that never even had to know a Fish
    class existed. We're going to be coming back to this particular feature a lot, because it has a special name, Polymorphism.
    It simply means "many forms".

        In this course, we showed that Animal can take multiple forms, the base class Animal, or a Dog, or a Fish. And as
    you've seen, some advantages of Polymorphism are:

- It makes code simpler. We can write code once, using the base class or super class, as we did with our doAnimalStuff
  method. We wrote that code without ever having to know about subclass types. We didn't have to write code to check the
  type of the object, and then decide what method to call, Java did all that at runtime.

- It encourages code extensibility. It's very easy to subclass, and override or extend the method, that'll be called as
  we demonstrated. We have a whole course on polymorphism, where we cover this powerful object-oriented concept, as well
  as others.

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