# Fundamental Rules of Object-Oriented Programming

## The Principle of Encapsulation

The wrapping principle is the principle that variables or attributes belonging to a class can only be changed and read by methods belonging to that class. Thanks to this principle, meaninglessness in objects can be prevented.

It also means that there is no access to variables from outside the classes, and how and how many variables there are within a class are hidden from other codes. In this way, we can wrap our variables and turn them into a filter that will protect us from undesirable situations. Let's try to understand this with an example.

Encapsuliaton Example

Let's have a class called JAVA.AbstractClass.Book and let's have ${\color{yellow}3}$ variables belonging to this class; bookName, PageNumber and Author. Let the access specifiers of these variables be ${\color{purple}public}$ and accessible from every class. Let's say that an object we create from the book class has all of these qualities. Therefore, let's create the ${\color{purple}Constructor}$ ${\color{green}method}$ ${\color{green}we}$ ${\color{green}will}$ ${\color{green}create}$ ${\color{green}in}$ ${\color{green}this}$ ${\color{green}way}$.

```java
public class JAVA.AbstractClass.Book {  
    public int numberOfPages;  
    public String bookName, writer;  
    JAVA.AbstractClass.Book(String bookName, int numberOfPages, String writer) {  
        this.bookName = bookName;  
        this.numberOfPages = numberOfPages;  
        this.writer = writer;
    }
}
```

Görüldüğü üzere normal bir sınıfımız ve kurucu metodumuz var. Kitap sınıfından bir nesne oluşturalım.

```java  
JAVA.AbstractClass.Book book = new JAVA.AbstractClass.Book("Harry Potter", 500, "JK Rowling");
```

We created an object called **book** from the book class and specified the attributes of this object. So what would happen if we entered a negative value for the number of pages in this constructor method? Since the number of pages of any book cannot be a negative value, there would be **meaninglessness** in our object. We can solve this problem by writing an **if** control in our **constructor** method.

```java  
public class JAVA.AbstractClass.Book {public int numberOfPages;
    public String bookName, writer;
    Kitap(String bookName, int numberOfPages, String writer) {
        this.bookName = bookName;
        this.writer = writer;
        if (numberOfPages < 1) {
            this.numberOfPages = 10;
        } else {
            this.numberOfPages = numberOfPages;
        }
    }
}
```

We modified the **Constructor** method as seen and prevented meaningful data from being created while the object was being created. But our problems are still not over, we can still access the attributes of the object from outside, and if we say book.pageNumber = -10, we will again make the number of pages of the object meaningless. To solve this problem, we need to turn off external access to the variables of the class and change the **access modifiers** of the variables we create. We change all **publics to private**.

The latest version of our class

```java  
public class JAVA.AbstractClass.Book {private int numberOfPages;
    private String bookName, writer;
    Kitap(String bookName, int numberOfPages, String writer) {
        this.bookName = bookName;
        this.writer = writer;
        if (numberOfPages < 1) {
            this.numberOfPages = 10;
        } else {
            this.numberOfPages = numberOfPages;
        }
    }
}
```

We solved this problem by making the permissions of the variables belonging to the class **private**, but we completely restricted access to the variables belonging to the book object. In other words, we cannot print the page number of the object we created on the screen because the variable is defined as **private**. Or, we cannot later edit an object whose page number has been entered incorrectly. To solve this problem, we **wrap** our class variables, **protect** them with the help of **methods** within the class, and make them available for use. We call these methods **Getter and Setter** methods, the names of which we will hear a lot later.

Getter and Setter Methods

Getter

There are **private** variables belonging to our class. In order to access these variables from outside, we must write a **Getter** method for **each of our variables**. When this method of the object is called, it should return a value and this value should be the **private** variable we want. Let's define a **getter method** for the **numberOfNumber** variable,

```java  
public class JAVA.AbstractClass.Book {
    private int numberOfPages;
    private String bookName, writer;

    JAVA.AbstractClass.Book(String bookName, int numberOfPages, String writer) {
        this.bookName = bookName;
        this.writer = writer;
        if (numberOfPages < 1) {
            this.numberOfPages = 10;
        } else {
            this.numberOfPages = numberOfPages;
        }
    }
    
    public int getnumberOfPages() {
        return this.numberOfPages;
    }
}
```

As you can see, we were able to access the **private** variable of the class with the help of a simple method. The points to be considered here are that **getter** methods are of the type of method that **returns** and their naming should start with **get** and then write the variable name. It will work even if we do not name it this way, but this rule **must** be followed for the sake of readability of the code.

Setter

We reached our **private** variable with the **getter** method. So, what should we do when we want to change the value of this variable? To change the value of a **private** variable belonging to a class, a **setter** method must be written. Let's write a setter method for the number of pages variable.

```java  
public class JAVA.AbstractClass.Book {
    private int numberOfPages;
    private String bookName, writer;

    JAVA.AbstractClass.Book(String bookName, int numberOfPages, String writer) {
        this.bookName = bookName;
        this.writer = writer;
        if (numberOfPages < 1) {
            this.numberOfPages = 10;
        } else {
            this.numberOfPages = numberOfPages;
        }
    }

    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
```

As you can see, the **setter** method was defined as **void** because it would only perform a change operation, and it took one parameter. This parameter carries our new value and has been transferred to the variable belonging to the class. But there is still a problem here, we can enter the number of pages negatively by using the **setter** method. To overcome this problem, we can solve this problem with an **if** condition, as we did in the **constructor** method.

```java  
public class JAVA.AbstractClass.Book {
    private int numberOfPages;
    private String bookName, writer;

    JAVA.AbstractClass.Book(String bookName, int numberOfPages, String writer) {
        this.bookName = bookName;
        this.writer = writer;
        if (numberOfPages < 1) {
            this.numberOfPages = 10;
        } else {
            this.numberOfPages = numberOfPages;
        }
    }

    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    public void numberOfPages(int numberOfPages) {
        if (numberOfPages < 1) {
            this.numberOfPages = 10;
        } else {
            this.numberOfPages = numberOfPages;
        }
    }
}
```

By modifying the **Setter** method, we eliminated the meaningless situation for our object. The general features of the **Setter** method are that it is a method that does not return a value and that when naming, you write set as the beginning and then write the variable name.

In this example, we used the principle of **Encapsulation**, which is the principle of **Object-Oriented Programming**, to protect the **numberOfPages** variable and prevent it from becoming meaningless. We wrapped our variables belonging to a class with the help of **Getter** and **Setter** methods and ensured that they were created according to the desired conditions.

## Inter-Class Relations

In Java and **Object Oriented Programming**, classes need to be created. When determining the classes and designing the system, it is necessary to decide what kind of relationship will exist between the classes. If we create these system designs well, we will have a good start and increase the quality of the code we will write. So what are the relationships between these classes?

There are three basic relationships that can be created between classes: **Dependency**, **Composition**, **Inheritance**

### Dependency / “uses a” Relationship

Dependency in Java, also known as "**uses a**" relationship, if at least one of the methods of a class takes a parameter belonging to another class, the class belonging to the parameter-taking method is dependent on the other class or **uses** it. Increasing the dependency between classes in the written program causes the number of classes to be managed together to increase. In this case, the manageability or maintenance of the program will become difficult. The reason for this is that any change made within a class will also affect the classes it depends on and the maintenance burden will increase.

### Composition / "has a" Relationship

**Composition** relationship in Java, if at least one of the variables of a class is from another class type, the class that has the variable contains the other class, that is, it is called "**has a**".

### Inheritance / "is a" Relationship

**Inheritance** relationship in Java, if all objects belonging to a class also belong to another class with a more general structure, then there is an **inheritance** relationship between these two classes.

## The Principle of Inheritance

Inheritance performs a task similar to its definition in real life in the programming environment. When a class inherits from another class, it means that the inheriting class takes the qualities and behaviors of the other class. If we call the inheriting class a **subclass** and the class from which it is inherited a **parent class**, we can say that everything defined in the parent class is also defined in the child class.

If a class A is required to inherit from class B, it is defined as follows.

```java  
public class A extends B { 
    // 
}
```

### Inheritance Types

#### Single Inheritance

It refers to the child and ancestor class relationship in which one class extends another class.

![Single Inheritance](https://raw.githubusercontent.com/Kodluyoruz/taskforce/main/java102/inheritance/figures/kl1.png)

In this example, class B inherits class A.

#### Multiple Inheritance

It refers to a class inheriting more than one class; This means that a subclass has two ancestor classes.
Note: Java does not support multiple inheritance. (Interface is used)

![Multiple Inheritance](https://raw.githubusercontent.com/Kodluyoruz/taskforce/main/java102/inheritance/figures/kl2.png)

#### Multilevel Inheritance

A subclass of a class extends other classes.

In this example, class C inherits class B, and class B inherits class A. Class C indirectly inherits class A.

#### Hierarchical Inheritance

It refers to a subclass and superclass relationship in which more than one class extends the same class.

![Hierarchical Inheritance](https://raw.githubusercontent.com/Kodluyoruz/taskforce/main/java102/inheritance/figures/kl4.png)

In this example: classes B, C and D extend the same class A.

#### Hybrid Inheritance

It is called the combination of more than one inheritance type in the program. For example, class A and B extend class C, and another class D extends class A, this is an example of hybrid inheritance because it is a combination of unidirectional and hierarchical inheritance.

![Hybrid Inheritance](https://raw.githubusercontent.com/Kodluyoruz/taskforce/main/java102/inheritance/figures/kl5.jpeg)

#### Constructor Chain and Super Keyword in Inheritance

We know that when an object belonging to a class is created, a constructor of that class is executed, and after the execution of the constructor is completed, an object is created in memory. We also use constructors to move objects into meaningful states when they are first created. In this case, if the class whose object will be created is a subclass of another class, we can say that the ancestor's internal object must first be created and the initial values ​​of the attributes of this object must be given.

In order for nested objects to form, objects must form from the inside out. In order for the inner object to be created, when the constructor of the class whose object will be created starts to be executed, the constructor of the parent class is called first. If the parent class is a subclass of another class, this time the founder of that class is called. The founder chain proceeds in this way from the child class to the parent class. After the founder of the class at the top of the inheritance tree is finished, the founders of the subclasses will be terminated, respectively. Thus, nested objects are created sequentially, and the outermost object is created last, completing the constructor chain.

#### Using Super

If there is no default constructor in the parent class and the programmer does not specify which constructor of the parent class to call in the constructor in the subclass, a compilation error will be received. Because the compiler will generate super() code that calls the default constructor of the parent class unless otherwise specified. Which constructor of the ancestor class will be called is determined by the parameters given with the super keyword. Just as the parameters we use with the new operator determine which constructor will be called, the parameters used with the super keyword determine which constructor of the ancestor class will be executed.

## Method Overriding

We named the coding of more than one method with the same name with different parameter lists (parameters with different numbers, types or orders) as Method Overloading.

Overloading can also be achieved by writing new methods within a class with the same name and a different parameter list as the protected or public defined methods in the parent classes. Because this class will inherit the methods in the parent class.

Method Overriding is the name given to the coding of one (or more) identical methods (same method name and same parameter list) coming from direct or indirect ancestor classes into a subclass.

Questions like these are often asked about method overriding: “why would I re-code the same method in the subclass that is already inherited from the parent class?”, “while inheritance increases the reusability of the code, isn't it contradictory that I re-code the same method(s) in the subclass?”.

Regarding method overriding, one point should not be overlooked: the method coded in the subclass has the same name and parameter list as the method in the parent class, but should not contain the same codes as the method in the parent class! Of course, it would be contradictory, even absurd and meaningless to code exactly the same method in the subclass as in the parent class.

To understand why override is necessary, we must first remember that as you move down the inheritance tree, more specific classes are reached, and as you move up, more general classes are reached. A method defined in the ancestor class will be a method that works according to the common features of all subclasses that that class generalizes. Since classes become more specialized as you move down to subclasses, the method in the parent class may be too general and therefore insufficient for the subclass. In this case, the subclass will have a more specific implementation depending on its own properties.

Sometimes this implementation can be coded to use the parent class and add something to it, and sometimes it can be coded to be completely different. If the implementation in the subclass will use the method in the parent class and add something to it, the super keyword can be used to call the parent method. Just as the this keyword is a reference to the current object, the super keyword is a reference to the internal object of the parent class.

## The Principle of Polymorphism

Polymorphism refers to the variation of the same task or job in different ways. The object can perform the same performance in different forms and layouts.

There are two methods to do this.

1. Overriding in Java  
2. Overloading in Java  

Polymorphism Features

Example:

```java  
class Animal{
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String speak(){
        return "Animal is speaking...";
    }
}

class Cat extends Animal{

    public Cat(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return this.getName() + " Meows...";
    }
}

class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return this.getName() + " Barking...";
    }
}

class Horse extends Animal {

    public Horse(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return this.getName() + " Neighs...";
    }
}

public class JAVA.MyList.Main {

    public static void makeSpeak(Animal animal){

        System.out.println(animal.speak());
    }

    public static void main(String[] args){

        // Basic representation
        Animal animal = new Animal("Hiper");
        System.out.println(hayvan.speak());
        Animal animal1 = new Cat("Hiper");
        System.out.println(animal1.speak());
        Animal animal2 = new Dog("Hiper");
        System.out.println(animal2.speak());
        Animal animal3 = new Horse("Hiper");
        System.out.println(animal3.speak());

        // With function representation  
        makeSpeak(new Cat("Nasip"));  
        makeSpeak(new Dog("Karabaş"));  
        makeSpeak(new Horse("Bold-Pilot"));
    }
}
```

instanceof - polymorphism relation

+ We will give an object and we will check what class the object is.  
+ The Cat class also inherits from the Animal class. Therefore, if compared to the superclass, it returns true because it is inheritance.  
+ **All classes derive from objects.(Object class)**  
+ If we do not use polymorphism, we need to check all objects with instanceof.  
+ **If we create a new class** and if it inherits from the Animal class, we will still have to check it, but polymorphism reduces this workload.  

Example II:

```java  
class Animal{
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String speak(){
        return "Animal is speaking...";
    }
}

class Cat extends Animal{

    public Cat(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return this.getName() + " Meows...";
    }
}

class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return this.getName() + " Barking...";
    }
}

class Horse extends Animal {

    public Horse(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return this.getName() + " Neighs...";
    }
}

public class JAVA.MyList.Main {

    public static void makeSpeak(Object object){

        if (object instanceof Dog){
            Dog dogTest = (Dog)object;
            System.out.println(dogTest.speak());
        }
        else if(object instanceof Cat){
            Cat catTest = (Cat)object;
            System.out.println(catTest.speak());
        }
        else if (object instanceof Horse){
            Horse horseTest = (Horse)object;
            System.out.println(horseTest.speak());
        }
        else if (object instanceof Animal){
            Animal animalTest = (Animal)object;
            System.out.println(animalTest.speak());
        }
    }

    public static void main(String[] args){

        // Basic representation
        Cat cat = new Cat("Nasip");

        if (cat instanceof Cat){
            System.out.println("This object is from Cat class");
        }

        if (cat instanceof Animal){
            System.out.println("This object is from Animal class");
        }

        // With function representation

        Cat catt = new Cat("Nasip");
        Dog dog = new Dog("Zizu");
        Horse horse = new Horse("BoldPilot");
        Animal animal = new Animal("Turunç");

        makeSpeak(catt);
        makeSpeak(dog);
        makeSpeak(horse);
        makeSpeak(animal);
    }
}
```

+ Example II above fully reveals the true benefit of Polymorphism. More chances of making mistakes, more code and more manual checks. As a result, using polymorphism is an efficient feature in terms of code cleanliness and security.

## The Principle of Abstraction

In object-oriented programming, the abstraction principle states that if it is unreasonable to create an object for a class, that class can be abstracted. If it is desired to create a superclass that has the common features and functions of the subclasses but does not yet have an object, an abstract superclass is created.

Abstraction means defining the basic tasks of a class or method, but not the details. Basically, it is to make a more general, simple and abstract definition of the methods to be used in solving a problem.

"abstract" Keyword and Abstract Class Concept

The concept of abstraction means isolating the inner workings of the classroom from the outside, that is, hiding them. For example: when using a computer, most users are unaware of the inner workings of the computer. The details of many operations, such as the communication of the memory with the processor, synchronization between processes, and the reflection of the values ​​entered from the keyboard on the screen, are hidden from the users. Users simply call and use these functions or functions through an interface. It does not interfere with internal details.

Likewise, when we design our classes in Java, some functions and functions remain only within the class, and people who use objects of this class in the outside world do not need to know these internal functions. For example: The function that calculates the VAT amount may have many other functions within the class. There is no point in opening these functions outside the class. An external function that simply gives the amount and calculates the VAT amount based on that amount is sufficient. In the software world, the concept of abstraction is therefore an important concept in software design. Structures such as "abstract" keyword and "interface" help us to make abstraction.

There are two methods for abstraction in Java:

+ define "interface"
+ define "abstract" class

### Abstract Class

These are classes defined with the "abstract" keyword. Abstract methods or normal functions can be defined within the class. An object cannot be created from abstract classes with the "new" keyword.

#### Abstract Class Properties

+ It must be defined with the "abstract" keyword.
+ Abstract or non-abstract functions can be defined.
+ Objects cannot be created from abstract classes with the "new" keyword.
+ Constructor methods and static functions can be defined.
+ It may contain functions defined with the word "final". These final functions cannot be override in subclasses.

```java  
// abstract class example 
public abstract class Doping { 
    
    protected double price; protected double[] taxes; 
    
    public double[] getTaxes() { 
        return taxes;  
    }

    public void setTaxes(double[] taxes) { 
        this.taxes = taxes;  
    }

    public double getPrice() { 
        return price;   
    }

    public void setPrice(double price) { 
        this.price = price;    
    }

    // abstract method example 
    public abstract double calculate(); 
}
```

We defined an abstract class above. We defined a class with the word "abstract", and we also defined an "abstract" method called "calculate" within the class. We also defined non-abstract methods. In our scenario, let's imagine that there are additional products of the "Doping" type in an e-commerce system. Let's have a doping type that updates the announcement date, and a doping type that helps you rank higher. In these two subclasses, they inherit certain features from the class called "Doping". However, the fee calculation method for each doping may differ from each other. In addition, every doping must have a price calculation function.

In the above case, you will define an "abstract" class and other doping types will inherit from this ATA class. They will crush the "abstract" method called "calculate" with the "method overriding" method and fill the method in their own way. Other features in subclasses will be hidden from the outside world with the abstraction technique. Another class or piece of code that wants to use doping from the outside world will call the "calculate" function on the doping object and calculate the price. He will not know other internal calculation and working details.

```java  
public class TopOfListDoping extends Doping { 
    public TopOfListDoping(double price) { 
        super.setPrice(price);  
    }

    // The subclass fills the abstract method named "calculate", which is inherited from the "doping" abstract class, according to its needs, using the method crush method. 
    // Since there are no taxes in the doping type called "TopOfList", the commission rate is added and the fee is calculated.  
    // However, the calculation may be different for other doping types. 

    @Override public double calculate() { 
        return super.getPrice() + super.getPrice() * 0.35; 
    }
}

public class UptodateDoping extends Doping { 
    public UptodateDoping(double price, double[] taxes) { 
        super.setPrice(price); super.setTaxes(taxes); 
    }

    // The subclass fills the abstract method named "calculate", which is inherited from the "doping" abstract class, according to its own needs, using the method crushing method. 
    // In the doping type called "UptodateDoping", since taxes are included in the price, the commission rate is added and the taxes are calculated and the fee is determined. 
    // As can be seen, the price calculation methods of each doping type are different from each other. With abstraction, the inner working details of the classes are hidden. 
    // In doping types, we only opened the function called "calculate" to the outside world. All other functions and features remained within the class. 

    @Override public double calculate() { 
        return calculateTaxes() + commisionRate(); 
    }

    private double calculateTaxes() { 
        double totalTaxValue = 0; 
        for(int i=0; i < super.getTaxes().length; i++) { 
            totalTaxValue += super.getTaxes()[i]; 
        }
        return totalTaxValue; 
    }

    private double commisionRate() { 
        return super.getPrice() * 0.2; 
    }
}
```

The subclass fills the abstract method named "calculate", which is inherited from the "doping" abstract class, according to its needs, using the method crush method. In the type of doping called "UptodateDoping", since taxes are included in the price, the commission rate is added and the taxes are calculated and the fee is determined. As can be seen, the price calculation methods of each doping type are different from each other. With abstraction, the inner working details of the classes are hidden. In doping types, we only opened the function called "calculate" to the outside world. All other functions and features remained within the class.

## Using Interface

Another way to achieve abstraction in Java is to define an "interface". "interface"'s have a very high abstraction rate compared to abstract classes. Because you can only define abstract functions in "interface". You cannot define regular functions that have a method body.

Interfaces are like contracts. If a class inherits from an interface, it must meet all the abstract properties defined in that "interface". If the inheriting class does not need some methods in the "interface", there is a problem in your software design. At this point, I recommend you do "Interface Segregation". With "Interface Segregation", interfaces can be divided into sub-interface definitions.

### Why do we use "interface"?

If a class inherits from " $\color{#a4dceb}{interface}$ ", the " ${\color{#a4dceb}implements}$ " keyword is used. Let's take a look at an example definition.

```java  
// An interface type is defined with the interface keyword. 
public interface PaymentProvider { 
// All functions in the interface are abstract.
// These abstract functions are populated in subclasses that inherit from the interface. 
public boolean cancelCharge(int chargeId); 
public int charge(double totalPrice); 
public String loadInvoice(int chargeId); 
}
```

Subclasses inherit from the interface.

```java  
public class AssecoPaymentSystem implements PaymentProvider {

    @Override
    public boolean cancelCharge(int chargeId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int charge(double totalPrice) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String loadInvoice(int chargeId) {
        // TODO Auto-generated method stub
        return null;
    }
}

public class IyzicoPaymentSystem implements PaymentProvider {

    @Override
    public boolean cancelCharge(int chargeId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int charge(double totalPrice) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String loadInvoice(int chargeId) {
        // TODO Auto-generated method stub
        return null;
    }
}
```

## Test

1. Which of the following best describes the principle of Encapsulation?

    a. It is a way of combining various qualities into a single unit.  
    b. It is a way to combine various method parameters into a single unit.  
    c. Allows methods of classes to be written with different parameters.  
    d. It is a way to prevent class attributes from becoming meaningless.  

2. If the variable belonging to the class is defined as private, what can we do to access the attributes from the class object?

    a. We add a public method within the class that returns the relevant class variable.  
    b. We add a private method within the class that returns the relevant class variable.  
    c. We cannot access the private defined variable in any way.  
    d. A method is added to the class that prints the relevant class variable to the screen.  

3. Which feature can be implemented using encapsulation?

    a. Inheritance  
    b. Abstraction  
    c. Polymorphism  
    d. Overloading  

4. What type of inter-class relationship is inheritance modeled by?

    a. "is a" relationship  
    b. "has a" relationship.  
    c. "uses a" relationship.  
    d. None of above.  

5. Which one represents the dependency relationship?

    a. "is a" relationship  
    b. "has a" relationship.  
    c. "uses a" relationship.  
    d. None of above.  

6. Which keyword should be used to inherit from a class?

    a. super  
    b. this  
    c. include  
    d. extends  

7. Which of the following is the correct way to inherit class B from class A?

    a. class B + class A {}  
    b. class B inherits class A {}  
    c. class B extends A {}  
    d. class B extends class A {}  

8. Which of the following best describes the topic of Inheritance?

    a. It is copying of previously written code.  
    b. It is reusing a previously written piece of code.  
    c. It is using predefined functions in the programming language.  
    d. It is to use data and functions in derived classes.  

9. Which keyword is used to access constructor methods in superclasses from subclasses?

    a. super  
    b. this  
    c. extends  
    d. None of above  

10. What are subclass methods called that have the same name and parameters as a method in its parent class?

    a. Method Overloading  
    b. Method Overriding  
    c. Method Hiding  
    d. None of above  

11. Method Overriding means changing the derived class methods in the parent class in subclasses.

    a. True  
    b. False  

12. Which of the following is not true about polymorphism?

    a. It is the principle of Object Oriented Programming.  
    b. It makes the program easier to read.  
    c. Allows redefinition of inherited behavior of a class.  
    d. It allows the properties inherited from the superclass to be re-identified.  

13. What is the biggest reason to use polymorphism?

    a. It allows the programmer to think at a more abstract level.  
    b. It allows less code to be written.  
    c. The program will have a more stylish design and will be easier to maintain and update.  
    d. The program code will take up less space.  

14. Which of the following best defines abstraction?

    a. Hiding the class  
    b. Showing important attributes of the class  
    c. Hiding important attributes of the class  
    d. Showing the properties of the class and preventing the creation of objects  

15. Which of the following Access Specifiers can be used for the interface?

    a. public  
    b. protected  
    c. private  
    d. All of above  

16. Which code snippet indicates that a class A uses interface B?

    a. class A extends B {}  
    b. class A imports B {}  
    c. class A implements B {}  
    d. class A uses B {}  

17. What is the output of the code below?

```java  
interface Codes.InterfaceJava.

Calculator {
    void calculate ( int item);
}

class Patika implements JAVA.InterfaceJava.Calculator {
    int x;

    public void calculate(int item) {
        x = item * item;
    }
}

class main {
    public static void main(String args[]) {
        Patika obj = new Patika();
        obj.x = 0;
        obj.calculate(2);
        System.out.print(obj.x);
    }
}
```

a. 4  
b. 0  
c. 2  
d. 16  

Answers: 1.d, 2.a, 3.b, 4.a, 5.c, 6.d, 7.c, 8.d, 9.a, 10.b, 11.a, 12.d, 13.c, 14.d, 15.a, 16.c, 17.a,  
