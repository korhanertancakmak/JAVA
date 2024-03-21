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
In other words, from the constructor with no parameters, we'll call the one with 5 parameters, 
and pass in literal values. 
So to do that, we type, this, followed by parentheses. 
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

So what we're doing there with the **this()**, is a special use of this, 
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
The reason why you see it in that order, makes sense if you think about it. 
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
So there's an alternative, we could have done, is we actually could have 
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
So which ones are we going to have passed to us. 
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
is that our account number and our balance, aren't included. 
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

So how do we call this new constructor, when creating an account? 
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
So thinking back to the physical world, we use the plans for the house, 
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
Now this code in the main method. is creating instances of the house class, 
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

So keep in mind, that in Java, you always have a reference to an object in memory. 
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

Let's discuss the differences now between static variables, and instance variables. 
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
* Creating, and controlling access, to a shared resource.

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
if you remember, the "this" keyword is the current instance of a class. 
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
We can just type the class name, and use the dot notation, with the method name to access them.

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
So let's see some basic rules, that should help you decide.



    So here, we've got a small diagram, that should help us decide, whether we need an instance or a static method. Now
    instance methods are created more often than static methods, but let's see how to follow this diagram. So the first
    question we'd ask ourselves is, should the method be static? After that question, the next question would be, does it
    use any fields? Instance variables in other words, or instance methods of this object? And remember, we're asking
    these questions about the proposed method we plan to write, so if that's true, in other words, it does use some fields
    and/or instance methods. Then we'd want to make it an instance method. In other scenario if the method doesn't use,
    or is not proposed to use, any instance variables or instance methods, in that case, then we'd probably consider writing
    it, as a static method.

        So generally speaking, if we're not using any fields, or instance methods, with the new proposed method, we should
    consider making that method static, instead of a regular instance method. So that's the main differences between static
    and instance methods.

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