# [Section-9: Nested Classes]()

<div align="justify">

Up to this part of the course, I've shown you static and instance members in a class,
but they've only been fields and methods.
In addition to these, a class can contain other types within the class body,
such as other classes, interfaces, enums, and records.
These are called nested types, or nested classes.
You might want to use nested classes when your classes are tightly coupled,
meaning their functionality is interwoven.
The four different types of nested classes you can use in Java are:
* static nested class,
* inner class,
* local class, 
* anonymous classes.

| Type                    | Description                                                                                                  |
|-------------------------|--------------------------------------------------------------------------------------------------------------|
| Static Nested Class     | Declared in class body. Much like a static field, access to this class is through the Class name identifier. |
| Instance or Inner Class | Declared in class body. This type of class can only be accessed through an instance of the outer class.      |
| Local Class             | Declared within a method body.                                                                               |
| Anonymous Class         | Unnamed class, declared and instantiated in same statement.                                                  |

This section will cover all of these different types of nested classes, 
as well as additional nested types, like the enum & record (which are just classes at their core) 
and the interface. 
Before JDK16, only static nested classes were allowed to have static methods. 
As of JDK16, all four types of nested classes can have static members of any type,
including static methods. 
We'll start out talking about static nested classes. 
There's a lot to cover, so let's get started.
I want to start with the static nested class because I think it's the easiest to understand and use.
</div>

## [a. Static Nested Class]()
<div align="justify">

```java  
public class Employee {
    private int employeeId;
    private String name;
    private int yearStarted;

    public Employee() {
    }
    public Employee(int employeeId, String name, int yearStarted) {
        this.employeeId = employeeId;
        this.name = name;
        this.yearStarted = yearStarted;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        //return "Employee{}";
        return "%d %-8s %d".formatted(employeeId, name, yearStarted);
    }
}
```

And I'll add my attributes, I have three, the employeeId, name, and yearStarted. 
I'll generate a constructor with no arguments. 
Also, I want another constructor this time with all three fields. 
And I'm also going to generate a getter, but for the name field only. 
And after that, I'll generate a toString method.
And I'll replace the template code with my own formatting and include our three fields. 
That's a simple domain (or businessDomain class) called Employee. 
Now, in the past section, we created a Comparator class to sort Students, 
and I'm going to do something similar for this class. 
I'll create this in the same package as employee, and call it EmployeeComparator.

```java  
public static class EmployeeComparator <T extends Employee> implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        //return 0;
        return o1.getName().compareTo(o2.getName());
    }
}
```

I want this to be generic, and accept and Employee or subtype of Employee, 
so I'll include a type parameter. 
And I'll add **implements** Comparator for that, 
using Employee as the type argument. 
Now, I need to implement methods to get rid of that compiler error,
so I want to include a compare method on this class.
And because I included a getter for the name field, 
I can use that getter to compare names between two Employee objects in this method. 
I'll replace the **return 0;** statement with that. 
Next, I'll set up a few employees, in the Main class's main method, in a List.

```java  
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(10001, "Ralph", 2015),
                new Employee(10005, "Carole", 2021),
                new Employee(10022, "Jane", 2013),
                new Employee(13151, "Laura", 2020),
                new Employee(10050, "Jim", 2018) ));
    }
    var comparator = new EmployeeComparator<>();
    employees.sort(comparator);
}
```

In this code, I'm setting up the initial ArrayList with five employees, 
but not in any particular order. 
Next, I'll sort this list with my Comparator. 
Create a new instance of our EmployeeComparator 
and pass it to the employees sort method.
Here I create a comparator local variable, whose type can be inferred, 
when I assign a new EmployeeComparator instance to it. 
If I hover over that variable, namely **comparator**, 
you can see its Comparator typed with the Employee class. 
And I can pass this to the sort method. 
Now, to confirm the list is sorted, I want to print out the employees,
one on each line. 
And running the code:

```java  
10005 Carole   2021
10022 Jane     2013
10050 Jim      2018
13151 Laura    2020
10001 Ralph    2015
```

You can see my list of employees are sorted by their names, 
which are printed out, left justified. 
Now, let's say; I instead want to sort by the year the Employee was hired, 
the year started field. 
Going back to the Comparator, I'll comment out 
the statement that's currently there:

```java  
public static class EmployeeComparator <T extends Employee> implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        //return o1.getName().compareTo(o2.getName());
        return o1.yearStarted - o2.yearStarted;
    }
}
```

I'll comment out the return statement that's currently there, 
and try to do this, using year started. 
Compare year started for each employee by returning the result of deducting o2 year started from o1 year started.

```java  
return o1.yearStarted - o2.yearStarted;
```
                
But this doesn't work because that field, year started, 
is private on Employee, and this class, EmployeeComparator, 
is external to the Employee class. 
To get this class to work with year started, 
I'd need to either change the access modifier for that field, 
or provide a getter for it. 
And maybe there are reasons I don't really want to do that. 
I'll revert that code, removing that last change, 
and uncommenting the first statement. 
Now, I want to copy that whole class, 
from the class name to the ending closing brace. 
I'll jump over to the Employee class, 
and paste the code at the bottom of this class.
And I'll add **static** between **public** and **class** keywords, 
so what I'm doing here is, I'm creating a _static nested class_ inside **Employee**. 

```java  
public class Employee {
    private int employeeId;
    private String name;
    private int yearStarted;

    public Employee() {
    }
    public Employee(int employeeId, String name, int yearStarted) {
        this.employeeId = employeeId;
        this.name = name;
        this.yearStarted = yearStarted;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        //return "Employee{}";
        return "%d %-8s %d".formatted(employeeId, name, yearStarted);
    }

    public static class EmployeeComparator <T extends Employee> implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            //return 0;
            return o1.getName().compareTo(o2.getName());
            //return o1.yearStarted - o2.yearStarted;
        }
    }
}
```

And this code compiles, so let's go back to the main method, and use this class.

```java  
//var comparator = new EmployeeComparator<>();
//employees.sort(comparator);

employees.sort(new Employee.EmployeeComparator<>());
```

First, I'll comment out the two statements, 
the first that creates a comparator local variable, 
and also the _employees.sort_ statement that takes the comparator variable. 
And I'll add a statement which is very similar, 
but instantiate and pass the comparator to the sort method. 
The only difference is that I need to first specify that
this comparator is accessed through the Employee class.
Notice I'm using the diamond operator there 
but its empty because Java can infer its type. 
And running that:

```java  
10005 Carole   2021
10022 Jane     2013
10050 Jim      2018
13151 Laura    2020
10001 Ralph    2015
```

I get the same results.
But if I go to that nested class, I have extra options available 
that I didn't have with the external class.

```java  
public static class EmployeeComparator <T extends Employee> implements Comparator<Employee> {
    private String sortType;

    public EmployeeComparator() {
        this("name");
    }

    public EmployeeComparator(String sortType) {
        this.sortType = sortType;
    }

    @Override
    public int compare(Employee o1, Employee o2) {
        //return 0;
        return o1.getName().compareTo(o2.getName());
        //return o1.yearStarted - o2.yearStarted;
        if (sortType.equalsIgnoreCase("yearStarted")) {
            return o1.yearStarted - o2.yearStarted;
        }
        return o1.name.compareTo(o2.name);
    }
}
```

First, I can access the attributes on instances of the Employee class directly, 
even the private ones, so I'll update my method, 
and instead of calling **getName**, I'll just access **name** directly. 
I can replace **getName** with simply **names** in both cases. 
Now let's make this comparator more flexible. 
I'll add a private attribute to it, called sort type. 
I'll generate a constructor with one argument, 
this new field, **sortType**. 
And I also want a no args constructor,
so I'll generate that before the previous one. 
And for that one, I just want to chain a call to the other instructor,
passing name as the default sort type. 
Finally, let's make the compare method figure out how to sort, 
based on that sort type. 
Are we comparing by yearStarted? 
If yes, so return the result of "o1.yearStarted - o2.yearStarted". 
Now, If I go back to the main method and run it, 
it should run as it did before, 
because sorting by name is the default sort type. 
Next, I'll pass yearStarted, to the comparator's constructor.

```java  
//var comparator = new EmployeeComparator<>();
//employees.sort(comparator);

//employees.sort(new Employee.EmployeeComparator<>());
employees.sort(new Employee.EmployeeComparator<>("yearStarted"));
```

Running that code:

```java  
10022 Jane     2013
10001 Ralph    2015
10050 Jim      2018
13151 Laura    2020
10005 Carole   2021
```
                
Our employees are sorted by the yearStarted, or were hired. 
And I also want to remind you that the Comparator interface has a default method, 
called reversed, which I can use next, by chaining it to my current comparator.

```java  
//var comparator = new EmployeeComparator<>();
//employees.sort(comparator);

//employees.sort(new Employee.EmployeeComparator<>());
//employees.sort(new Employee.EmployeeComparator<>("yearStarted"));
employees.sort(new Employee.EmployeeComparator<>("yearStarted").reversed());
```

Running that:

```java  
10005 Carole   2021
13151 Laura    2020
10050 Jim      2018
10001 Ralph    2015
10022 Jane     2013
```
                
You can now see that the employees are sorted by year descending, 
so that's a nice feature. 
The reversed method will reverse the sort for any Comparator. 
To create a static nested class, you add a class as part of another class's body, 
making it static. 
This lets you access it via the class name, like other static variables. 
But this nested static class has access to all the outer class's private members and vice versa. 
Using a nested class for this comparator, I was able to keep my attributes encapsulated, 
without providing getter methods for each one.
</div>

## [b. Non-Static Nested (Inner) Class]()
<div align="justify">

Inner classes are non-static classes, 
declared on an enclosing class, at the member level. 
Inner classes can have any of the four valid access modifiers, 
meaning they can be 
* public, 
* private, 
* protected, 
* no modifier (which makes them package private). 

An inner class has access to instance members, 
including private members, of the enclosing class.
Instantiating an inner class from external code is a bit tricky, 
and I'll cover that shortly. 
As of JDK16, static members of all types are supported on inner classes.

In the previous section, we looked at static nested classes with a Comparator class example. 
I want to do something similar here, to look at inner classes, 
and how they differ from static nested classes. 
The first thing I'll do is create a new class, called StoreEmployee, 
in the domain package.
I'll have it extend the Employee class that we created in the last lecture. 
I want to add a field to this class, called store.

I also want two constructors in this class. 
I'll be using the first, a no args constructor, to access this comparator
from the calling code, so I'll generate that now. 
And then, I'll generate a four argument constructor that will call
super's constructor, and then assign the store argument to the store field. 
Finally, I want the store to print out for each employee, 
so I want to override the toString method. 
I want to print out the store, so I'll remove that return statement. 
Instead, I'll return formatted string that has the store, 
and also the string representation of a regular employee.

```java  
public class StoreEmployee extends Employee {
    private String store;
    public StoreEmployee() {
    }

    public StoreEmployee(int employeeId, String name, int yearStarted, String store) {
        super(employeeId, name, yearStarted);
        this.store = store;
    }

    @Override
    public String toString() {
        //return super.toString();
        return "%-8s%s".formatted(store, super.toString());
    }

    public class StoreComparator<T extends StoreEmployee> implements Comparator<StoreEmployee>{
        @Override
        public int compare(StoreEmployee o1, StoreEmployee o2) {
            //return 0;
            int result = o1.store.compareTo(o2.store);
            if (result == 0) {
                return new Employee.EmployeeComparator<>("yearStarted").compare(o1, o2);
            }
            return result;
        }
    }
}
```

Next I'll add an inner class, and call it StoreComparator.
I do this by declaring a class inside the StoreEmployee class's opening and closing brackets. 
I'll have that implement Comparator with a type argument of StoreEmployee. 
I'll add the class and add a diamond operator with **T extends StoreEmployee** within. 
And implement Comparator with StoreEmployee in the diamond operator. 
And IntelliJ tells me _I need to implement a compare method_, so I'll do that. 
I want to add code that first compares the store field.
Comparing o1.store with o2.store and save the result, return it. 
If that result is 0, that means the employees work at the same store, 
so I want to add another comparison level. 
This time, I'll call the **Employee.EmployeeComparator** constructor, 
passing it the yearStarted, and using that compare method.
This is checking if employees are in the same store. 
If yes, then return yearStarted, compare for both stores. 
This class's compare method will sort by store, 
then yearStarted within the same store.
Now, I want to go back to the main method.

```java  
public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(10001, "Ralph", 2015),
                new Employee(10005, "Carole", 2021),
                new Employee(10022, "Jane", 2013),
                new Employee(13151, "Laura", 2020),
                new Employee(10050, "Jim", 2018)));

        employees.sort(new Employee.EmployeeComparator<>("yearStarted").reversed());
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}
```

You'll remember that the EmployeeComparator is a static class on Employee, 
and I can create an instance of it by accessing it through the enclosing class name. 
I simply instantiate that comparator, by saying **new Employee.EmployeeComparator<>**. 
This isn't true though for non-static nested classes, or inner classes.

```java  
public class Main {
    public static void main(String[] args) {

        System.out.println("Store Members");
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macys"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target") ));

        var comparator = new Employee.EmployeeComparator<>();
        storeEmployees.sort(comparator);

        for (StoreEmployee e : storeEmployees) {
            System.out.println(e);
        }
    }
}
```

First, I want to add a statement that I'll be printing the _store members_ next. 
Now I'll create a list of store employees, much the same way I did the employees. 
The list will be called StoreEmployees, and we'll use an ArrayList,
and initialise that using **List.of**, and passing a number of new StoreEmployee instances. 
And then, I'll add the for loop to print each store employee.
And before that loop, I want to add the call to my store employee comparator. 
But how do I call it? 
Let me set it up like I did before, using the EmployeeComparator on my new StoreEmployee class.
And this code compiles and runs:

```java  
Store Members
Target  10322 Bud      2016
Walmart 10515 Joe      2021
Walmart 10215 Marty    2018
Target  10015 Meg      2019
Macys   10105 Tom      2020
```

And sorts the store members by the default sort for that comparator, which is name. 
But in this code, I'm not using the StoreComparator I created on StoreEmployee. 
Instead, I'm using the static EmployeeComparator on Employee, and you can see that, 
if you hover over that local variable, comparator.
This means I've accessed the comparator on Employee through the StoreEmployee class, 
so subclasses inherit static nested classes. 
But that's not really what I want to do here. 
I really want an instance of StoreEmployee's own comparator class, 
an inner class, which I called StoreComparator. 
What happens if I just replace EmployeeComparator with StoreComparator in my local variable
assignment? 
Let me try that out.

```java  
public class Main {
    public static void main(String[] args) {

        System.out.println("Store Members");
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macys"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target") ));

        //var comparator = new Employee.EmployeeComparator<>();
        var comparator = new StoreEmployee.StoreComparator<>();
        storeEmployees.sort(comparator);

        for (StoreEmployee e : storeEmployees) {
            System.out.println(e);
        }
    }
}
```

Now, I've got a problem. If I hover over that, 
I get the message that StoreEmployee is not an enclosing class. 
That's because an inner class requires an instance of the enclosing class 
to be used to instantiate an inner class. 
Here, we're really calling the class StoreEmployee, 
and not an actual instance of the StoreEmployee Class, 
so it doesn't work.
Ok, so I'll create a new variable, and assign it a new StoreEmployee instance,
using my no args constructor.
Now, I'll replace StoreEmployee on the next statement with my local variable, genericEmployee.

```java  
var genericEmployee = new StoreEmployee();
var comparator = new genericEmployee.StoreComparator<>();
```

But that doesn't work either, but I get a different message, 
**Cannot resolve StoreComparator**.
To access the inner class, to create a new instance of it, 
we have to use a special syntax on our outer class instance, **new**. 
I want to remove the **new** keyword before genericEmployee, 
and I want to append **.new** .

```java  
var genericEmployee = new StoreEmployee();
var comparator = genericEmployee.new StoreComparator<>();
```

But I don't pass anything to **.new**, because it's not a method. 
Instead, I include a space after it, and then I have the inner class construction code.
Now, I got rid of the compiler errors, and I can run this:

```java  
Store Members
Macys   10105 Tom      2020
Target  10322 Bud      2016
Target  10015 Meg      2019
Walmart 10215 Marty    2018
Walmart 10515 Joe      2021
```

My store employees are sorted by store first, then year started, 
which is how I implemented the StoreComparator's compare method. 
If I don't want to use a local variable, I can chain the instantiations. 
Let me show you that. 

```java  
var comparator = new StoreEmployee().new StoreComparator<>();
```

First, I'll remove that local variable, genericEmployee. 
Then I'll change the next line, and instead of genericEmployee there,
I'll replace that with **new StoreEmployee()**.

In either case, I am first instantiating an instance of StoreEmployee. 
Then I have to call what looks like, at first glance, a new method, 
but it doesn't have parentheses associated with it. 
This **.new** syntax isn't calling a method, 
but it will create an instance of an inner class,
which we've declared on Store Employee.

To create an instance of an inner class, 
you first must have an instance of the Enclosing Class. 
From that instance you call **.new**, 
followed by the inner class name and the parentheses, 
taking any constructor arguments.

```java  
EnclosingClass outerClass = new EnclosingClass();
EnclosingClass.InnerClass innerClass = outerClass.new InnerClass();
```
                    
Many times, an inner class is never accessed or 
instantiated from outside the enclosing class, 
but you should still be familiar with this syntax.

For now, I want to show you how to use an inner class 
to reimplement part of the Bill's Burger code, 
that was part of a challenge in an [earlier](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_04_OOP2/README.md#j-bills-burgeroop-master-challenge) 
section. 
</div>

## [c. Recover Bill's Burger Challenge]()
<div align="justify">

I want to switch gears a little, and look at a different example. 
In this example, I want to use an inner class to reconstruct a part of Bill's Burger Challenge. 
The Challenge was at OOP-Part2 Section. 
In that challenge, we created a meal, which consisted of a burger, a drink, and a side. 
I'll do that, but I'll skip the burger toppings part of that challenge, 
as well as the deluxe meal part. 
I just want to give you a taste of different way to implement that code,
using an inner class.
First, I'll create a new class, called **Meal**.

```java  
public class Meal {

    private Item burger;
    private Item drink;
    private Item side;

    private class Item {

        private final String name;
        private final String type;
        private final double price;

        public Item(String name, String type) {
            this(name, type, 0);
        }

        @Override
        public String toString() {
            return "%10s%15s $%.2f".formatted(type, name, price);
        }
    }
}
```

And I want to start with a private inner class called Item, 
and that'll have three fields, name, type and price. 
All our meal items, burger, drink and side will be an item,  
and the item is called an inner class because it's not static, 
and it's declared as a class member. 
Now, I want a constructor for the inner class with all three fields. 
And I want to copy that constructor, pasting it directly above. 
I'll remove the price parameter and the code that's in there, 
and make a call to the other constructor, passing **name, type, 0**, 
which it defaults to anyway. 
And now I want to override the **toString** method for this inner class. 
And I'll replace the template code with my own formatting,
and include our three fields.
That'll print out the type, name and price spaced and formatted.

Now, for the Meal, I'll set up some attributes. 
You can see that burger, drink, and side are all using Item as their variable type, 
Item is the inner class. 
And I've set up a base price of 5$. 
Now, one bonus for nested classes is both the inner class 
and the outer class have direct access to the other's instance members. 
I'll go back to the inner class constructor, 
and I'm going to set up a condition that if the item is a burger, 
its price will be the base price.

```java  
public class Meal {

    private Item burger;
    private Item drink;
    private Item side;

    private double base = 5.0;

    public Meal() {
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n".formatted(burger, drink, side);
    }
    
    private class Item {

        private final String name;
        private final String type;
        private final double price;

        public Item(String name, String type) {
            //this(name, type, 0);
            this(name, type, type.equals("burger") ? base : 0);
        }

        @Override
        public String toString() {
            return "%10s%15s $%.2f".formatted(type, name, price);
        }
    }
}
```

What I want you to notice with this code is that I'm assigning the base price, 
which is a private field on the enclosing **Meal** class, 
directly to an attribute on an instance of **Item** if it's a burger. 
This is an example, showing that the inner class has direct access 
to the outer class's attributes, even private ones.

Finally, I'll set up a no args constructor for the enclosing **Meal** class. 
This constructor constructs a regular meal with a regular burger, 
a coke and a side of fries. 
I've added a statement to print out the drink's name, 
to demonstrate how the enclosing Meal class has direct access 
to the Item's attributes without getters or setters, 
even though those attributes are private. 
Both classes benefit from the nested relationship 
by being able to access all the other's members, private or otherwise. 
Next, I want to toString method for the Meal.
I'll insert an overridden version after my constructor. 
And I'll return a formatted string again. 
So that's a meal, using an inner class item to represent everything in a meal. 
Let me test this out. 
Instead of using the main class, 
I'll create another class in the same package, called Store.

```java  
public class Store {

    public static void main(String[] args) {

        Meal regularMeal = new Meal();
        System.out.println(regularMeal);
    }
}
```

I'll type the shortcut **psvm** to get a public static void method. 
Now I'll create a meal, and print it out. 
Running that out:

```java  
coke
burger       regular $5,00
drink           coke $1,50
side           fries $2,00
```

I get coke printed out, which just confirms that the enclosing class, 
Meal in the case, was able to directly access Item's private field, name. 
And I've got an itemized list of my regular meal. 
That was a rapid demo of how we can use an inner class to build 
our composition inside a class when it makes sense to do so. 
This meal class is very well encapsulated. 
The meal and items are tightly coupled in this challenge, 
because we've said that Bill sells meals and not individual items. 
Before we move on, let's look at a couple more issues.
I'll make a change on the Meal Class:

```java  
public class Meal {

    private Item burger;
    private Item drink;
    private Item side;

    //private double base = 5.0;
    private double price = 5.0;
    private double conversionRate;

    public Meal() {
        this(1);
    }

    public Meal(double conversionRate) {
        this.conversionRate = conversionRate;
        burger = new Item("regular", "burger");
        drink = new Item("coke", "drink", 1.5);
        System.out.println(drink.name);
        side = new Item("fries", "side", 2.0);
    }

    @Override
    public String toString() {
        //return "%s%n%s%n%s%n".formatted(burger, drink, side);
        return "%s%n%s%n%s%n26s$%.2f".formatted(burger, drink, side, "Total Due: ", getTotal());
    }

    public double getTotal() {

        double total = burger.price + drink.price + side.price;
        return Item.getPrice(total, conversionRate);
    }
    
    private class Item {

        private final String name;
        private final String type;
        private final double price;

        public Item(String name, String type) {
            //this(name, type, 0);
            //this(name, type, type.equals("burger") ? base : 0);
            //this(name, type, type.equals("burger") ? price : 0);
            this(name, type, type.equals("burger") ? Meal.this.price: 0);
        }

        @Override
        public String toString() {
            //return "%10s%15s $%.2f".formatted(type, name, price);
            return "%10s%15s $%.2f".formatted(type, name, getPrice(price, conversionRate));
        }
    }
}
```

Instead of calling the base price, **base**, 
let's say I want to call it **price**. 
And now, I've got an error in my two arguments constructor, 
because **base** doesn't exist anymore. 
What happens if I change that from base, to price?

Notice, as I'm typing, I get a popup that is showing me **Meal.this.price**. 
I'm going to ignore that for the moment. 
But notice that using price here gives me an error, 
_Cannot reference **Item.price** before super type constructor has been called_. 
What's going on here? 
Well, maybe you've realized that I've got a **price** field on Meal, 
and I've also got a **price** field on Item. 
If I reference price without any qualifier in the code, 
it refers to the price applicable to the current scope. 
In this example, I'm in the Item class, so price here, 
in the constructor refers to Item's price, and not Meal's price. 
How do I tell it to use Meal's price and not Item's price? 
Well, IntelliJ gave us that hint when we were replacing base with price. 
We specify **Meal.this.price**.
Now, this code runs and compiles as before.

```java  
private static double getPrice(double price, double rate) {
    return price * rate;
}
```

I've mentioned several times that JDK 16 allowed us 
to have static members on all nested classes. 
Let's see what it means.
I want to add a static method on Item; that will convert my prices, 
in Australian Dollars to US Dollars, 
since Bills Burger chains are opening up in the US. 
Now this method will take any price and any conversion rate 
and return a converted price. 
It can be static, since the variables needed are all passed as arguments. 
I'll call this **getPrice**. 
And then let's add conversion rate as a field on the Meal class. 
Now, I want to add that as an argument to the no args constructor and save it. 
And again I need a no args constructor. 
I'll generate that by placing it above.

```java  
public Meal() {
    this(1);
}

public Meal(double conversionRate) {
    this.conversionRate = conversionRate;
    burger = new Item("regular", "burger");
    drink = new Item("coke", "drink", 1.5);
    System.out.println(drink.name);
    side = new Item("fries", "side", 2.0);
}
```

And I'll chain a call to the other constructor, 
passing 1 as the conversion rate, which means there is no conversion.
Now, I want to call this method, first on Item's toString method.

```java  
@Override
public String toString() {
    //return "%10s%15s $%.2f".formatted(type, name, price);
    return "%10s%15s $%.2f".formatted(type, name, getPrice(price, conversionRate));
}
```

You know you can call a static method without using the class name from within the class itself, 
which I do here. 
Next, I want to add a method on meal, called getTotal.

```java  
public double getTotal() {

    double total = burger.price + drink.price + side.price;
    return Item.getPrice(total, conversionRate);
}
```

Now, in this method, notice I'm calling the static method getPrice, 
but this time I use Item's class name, because this code is outside the Item class. 
I'll next add to the toString method, two more format specifiers, first separated
by a new line from the other items, and pass the text, Total Due, and then the total price.

```java  
@Override
public String toString() {
    //return "%s%n%s%n%s%n".formatted(burger, drink, side);
    return "%s%n%s%n%s%n26s$%.2f".formatted(burger, drink, side, "Total Due: ", getTotal());
}
```

Getting back to the main method on Store class, I can run the code as before.

```java  
Meal regularMeal = new Meal();
System.out.println(regularMeal);
```

Running that out:

```java  
coke
    burger        regular $5,00
    drink            coke $1,50
    side            fries $2,00
               Total Due: $8,50
```

I get the same prices, but I now see the total price, 8.50$. 
Now, I want to create a meal in US $, and then print it out. 
Just imagine, you could tap some service to give you 
the current conversion rate for the day, to do this. 
But we'll just hard-code the rate from Australian dollars 
to US dollars as 0.68. 

```java  
Meal regularMeal = new Meal();
System.out.println(regularMeal);

Meal USRegularMeal = new Meal(0.68);
System.out.println(USRegularMeal);
```

And running the code:

```java  
coke
    burger        regular $5,00
    drink            coke $1,50
    side            fries $2,00
               Total Due: $8,50
coke
    burger        regular $3,40
    drink            coke $1,02
    side            fries $1,36
               Total Due: $5,78
```

You can see the regular meal in the converted price. 
Although I've only shown you an example of a static method, 
nested classes can now include a nested interface, 
a static enum or a static record, for example, 
giving your inner classes a lot more functionality. 
</div>

## [d. Inner Class Challenge](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_09_NestedClassesAndTypes/Course04_InnerClassChallenge/README.md#inner-class-challenge)
<div align="justify">

I want you to start with the Bill's Burger code from 
[c](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_09_NestedClassesAndTypes/README.md#c-recover-bills-burger-challenge), 
where we left off, and make the following enhancements to it:

* Create another inner class, called Burger. 
This should be a specialized Item, and should also include a list of toppings, also Items. 
Remember Items have a name, type, price, and methods to convert prices.
* Allow a user to add toppings using the Meal class, 
which it should then delegate to its burger class.
* Allow toppings to be added with a method that allows 
for a variable number of Strings to be entered, representing the toppings selected.
* Allow toppings to be priced differently, some are free, some have an additional cost.
* Print the toppings out along with the burger information.
</div>

## [e. Local Class]()
<div align="justify">

Local classes are inner classes, but declared directly in a code block, 
usually a method body. 
Because of that, _they don't have access modifiers_, 
and are only accessible to that method body while it's executing. 
Like an inner class, they have access to all fields and methods on the enclosing class. 
They can also access local variables and method arguments 
that are final or effectively final.

Let's review this code. 
Let's have a little fun with this new type of nested class. 
When I was a kid, we spoke in a code called pig Latin, 
so I want to create a method that creates a special class of StoreEmployee. 

```java  
public class StoreEmployee extends Employee {

    private String store;
    public StoreEmployee() {
    }

    public StoreEmployee(int employeeId, String name, int yearStarted, String store) {
        super(employeeId, name, yearStarted);
        this.store = store;
    }

    @Override
    public String toString() {
        return "%-8s%s".formatted(store, super.toString());
    }

    public class StoreComparator<T extends StoreEmployee> implements Comparator<StoreEmployee>{

        @Override
        public int compare(StoreEmployee o1, StoreEmployee o2) {
            int result = o1.store.compareTo(o2.store);
            if (result == 0) {
                return new EmployeeComparator<>("yearStarted").compare(o1, o2);
            }
            return result;
        }
    }
}
```

This Employee will include a special pig latin name, and we'll then sort on that. 
For those who don't know, pig Latin strips off the first letter, 
and tacks it onto the end of the name or word, and adds the letters aye and y. 
So my name, Tim, would become im-Tay, and Jane would become ane-Jay, for example. 
Although this is a contrived example, I want you to imagine that 
there might be times when you have access to data, 
but want to create a computed attribute of your own, 
or perform a specialized operation of your own. 
This is one way to do it. 
Later, when we get to streams, we'll learn more elegant and easier ways. 
But let's see what this looks like. 
I'll create a new static method on the **Main** class, 
called add _PigLatinName_, that will accept a List.

```java  
public static void addPigLatinName(List<? extends StoreEmployee> list) {

    class DecoratedEmployee extends StoreEmployee {
        private final String pigLatinName;
        private final Employee originalInstance;

        public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
            //this.pigLatinName = pigLatinName;
            this.pigLatinName = pigLatinName + " " + lastName;
            this.originalInstance = originalInstance;
        }

        @Override
        public String toString() {
            //return super.toString();
            return originalInstance.toString() + " " + pigLatinName;
        }
    }
}
```

Hopefully, you'll remember, this is a generics wild card that 
specifies an upper bound for this method parameter's type argument. 
This means this method will only accept a List containing StoreEmployees,
or any subtype of StoreEmployee.
Next, I'm going to declare the local class, right here inside the method block. 
I'll name this local class decoratedEmployee.
Notice, there are no modifiers. 
In fact, if I tried to use any here, I'd get a compiler error. 
When I try to make it **public class**, I'd get an error which says 
_modifier **public** not allowed here_. 
But like any class, as I'm showing here, it can extend. 
I can add attributes, which I'll do now. 
I'm making these **private**. 
However, the enclosing code in this method can still access this class's private fields, 
even after this class declaration's closing bracket.
First, I have the field that will hold the pigLatinName, 
and another field that gets assigned the original StoreEmployee instance. 
Now, I want to include a constructor. 
I'll generate that, and for this constructor, 
I only want these two additional fields, not the StoreEmployee fields.

Ok, so next, I want to include the toString using the override features in IntelliJ. 
I'll replace that super, and I really want to return the StoreEmployee's string 
representation by calling its toString method, and appending this additional field, 
_pigLatinName_. 
You can see I've created an entire class, all within my method body. 
Why would you want a class in a method? 
Well, it's possible, even probable that you really want the ability 
to create a class for just a single purpose. 
In this case, the only purpose of this class is to add a derived field, 
the pigLatinName, to an existing set of instances of StoreEmployees. 
I don't want to reinvent the wheel or add this class to my library of classes. 
I don't want anyone else to use it or extend it. 
I only want it to exist for this one specific purpose.
Now, I want to create a list of these DecoratedEmployee types, 
and print them out. 
I'm going to do this in this same method, 
directly after the class declaration I just added.

```java  
List<DecoratedEmployee> newList = new ArrayList<>(list.size());

for (var employee : list) {
    String name = employee.getName();
    String pigLatin = name.substring(1) + name.charAt(0) + "ay";
    newList.add(new DecoratedEmployee(pigLatin, employee));
}
```

First, I'll create a new list of DecoratedEmployees, 
and I'll pass the size of the list as the initial capacity.
Then I'll loop through the list passed to this method, 
which is my list of store employees, and I'll set up
instances of the new type of employee, adding them to my new list. 
This code sets up a list of my local class, the DecoratedEmployee, 
which you can think of as a wrapper class to the StoreEmployee 
with one additional field. 
This code shows how the pig latin name is derived. 
The first character is removed, with the subString method, 
and starting with 1 as the index. 
And that first character is then retrieved with charAt, 
and 0 as the index, and appended. 
Finally, I end with A and Y, tacked on to that. 
I'll print out each of my decorated employees with an enhanced for loop. 
Ok, so that's the entire method. 
I want to execute this from the main method.

```java  
public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(10001, "Ralph", 2015),
                new Employee(10005, "Carole", 2021),
                new Employee(10022, "Jane", 2013),
                new Employee(13151, "Laura", 2020),
                new Employee(10050, "Jim", 2018)));

        employees.sort(new Employee.EmployeeComparator<>("yearStarted").reversed());

        for (Employee e : employees) {
            System.out.println(e);
        }

        System.out.println("Store Members");
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macys"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target")));

        var genericEmployee = new StoreEmployee();
        var comparator = genericEmployee.new StoreComparator<>();
        storeEmployees.sort(comparator);

        for (StoreEmployee e : storeEmployees) {
            System.out.println(e);
        }

        System.out.println("With Pig Latin Names");
        addPigLatinName(storeEmployees);
    }
}
```

First, I'll print out a title, then I'll call the method. If I run that:

```java  
 ...(same)
With Pig LAtin Names
Macys   10105 Tom      2020 omTay
Target  10322 Bud      2016 udBay
Target  10015 Meg      2019 egMay
Walmart 10215 Marty    2018 artyMay
Walmart 10515 Joe      2021 oeJay
```
                   
You can see my calculated Pig Latin name, printed out for each employee. 
But let's sort by this name now. 
How would I go about doing that? 
You might say **Comparator**, since we've spent so much time with that interface. 
But this time, I'm going to have this local class **implement Comparable**. 
These local classes can implement interfaces, just like any other class. 
I'll go back to this class, and add, **implements Comparable**, 
and make the type argument equals to this new class **DecoratedEmployee**.

```java  
class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee> {
    private final String pigLatinName;
    private final Employee originalInstance;

    public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
        //this.pigLatinName = pigLatinName;
        this.pigLatinName = pigLatinName + " " + lastName;
        this.originalInstance = originalInstance;
    }

    @Override
    public String toString() {
        //return super.toString();
        return originalInstance.toString() + " " + pigLatinName;
    }
    @Override
    public int compareTo(DecoratedEmployee o) {
        //return 0;
        return pigLatinName.compareTo(o.pigLatinName);
    }
}
```

So my compareTo method will compare these specific types. 
This means I can use the Pig Latin name directly in this method without casting.
Here, I want to compare the pig latin names on the current 
instance with the one passed to it.

```java  
public static void addPigLatinName(List<? extends StoreEmployee> list) {

    String lastName = "Piggy";
    
    class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee> {
        private final String pigLatinName;
        private final Employee originalInstance;

        public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
            //this.pigLatinName = pigLatinName;
            this.pigLatinName = pigLatinName + " " + lastName;
            this.originalInstance = originalInstance;
        }

        @Override
        public String toString() {
            //return super.toString();
            return originalInstance.toString() + " " + pigLatinName;
        }

        @Override
        public int compareTo(DecoratedEmployee o) {
            //return 0;
            return pigLatinName.compareTo(o.pigLatinName);
        }
    }

    List<DecoratedEmployee> newList = new ArrayList<>(list.size());

    for (var employee : list) {
        String name = employee.getName();
        String pigLatin = name.substring(1) + name.charAt(0) + "ay";
        newList.add(new DecoratedEmployee(pigLatin, employee));
    }

    newList.sort(null);

    for (var dEmployee : newList) {
        //System.out.println(dEmployee);
        System.out.println(dEmployee.originalInstance.getName() + " " + dEmployee.pigLatinName);
    }
}
```

Finally, I want to make a call to sort my list of DecoratedEmployee 
before I print them out. 
Remember, if I pass null to the sort method on a list, 
it will use the Comparable _compareTo_ method. 
This is the same as if I had passed the result of the _Comparator.naturalOrder_ 
method to sort. 
Running that:

```java  
With Pig LAtin Names
Walmart 10215 Marty    2018 artyMay
Target  10015 Meg      2019 egMay
Walmart 10515 Joe      2021 oeJay
Macys   10105 Tom      2020 omTay
Target  10322 Bud      2016 udBay
```
                    
You can see that StoreEmployee have been sorted by the pig latin names, 
in alphabetical order, so Marty, whose pig latin name is artyMay is first. 
This local class decorated an existing class, meaning it added extra 
functionality to the class, like this new derived field, 
and also provided a way to sort by this new field. 
This class both extended a class and implemented an interface. 
It overrode the toString method and implemented the abstract method compareTo. 
Ok, now, let me show you a couple of other things about local classes.

```java  
public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
    //this.pigLatinName = pigLatinName;
    this.pigLatinName = pigLatinName + " " + lastName;
    this.originalInstance = originalInstance;
}
```

I'm first going to set up a local variable in my method, 
and call that last name. 
In the local class, I'm going to use this local variable in the class constructor code. 
I'll add it to the end of the generated pig latin name.

This is pretty neat that I can use local variables in the method, 
or even method arguments to influence whatever I want to happen in this local class. 
If I run this code now:

```java  
With Pig LAtin Names
Walmart 10215 Marty    2018 artyMay Piggy
Target  10015 Meg      2019 egMay Piggy
Walmart 10515 Joe      2021 oeJay Piggy
Macys   10105 Tom      2020 omTay Piggy
Target  10322 Bud      2016 udBay Piggy
```

You can see Piggy as the last name for these pig latin names. 
Let me go down to the last for loop,

```java  
for (var dEmployee : newList) {
    //System.out.println(dEmployee);
    System.out.println(dEmployee.originalInstance.getName() + " " + dEmployee.pigLatinName);
}
```

And this time, I'm just going to print the original name and the pig latin name.
You might be asking why this is interesting? 
Because here, in both cases, I'm accessing private attributes on the local class, 
the pigLatinName field, and the original Instance field. 
I'm accessing these outside of the class declaration. 
A method that has a local class can access any of that class's fields, 
private or not, on any instances used in the method. 
You may also be wondering why I'm using getName, on the original Instance, 
and not on my Decorated Employee instance. 
Well, I never actually set name on the decorated employee instances themselves,
or any of the other store employee fields that were inherited. 
Most of those fields were private with no getters or setters, so my class, 
even though it was a subtype, had no access to these fields. 
Finally, the last thing you need to understand is final and effective final variables. 
This is because local variables can only be used in a local class 
if they are final or effectively final. 
So what does this mean?
</div>

### "Captured Variables" of the Local Classes
<div align="justify">

When you create an instance of a local class, 
referenced variables used in the class, 
from the enclosing code, are **captured**. 
This means a copy is made of them, and the copy is stored with the instance. 
This is done because the instance is stored in a different memory area, 
then the local variables in the method. 
For this reason, if a local class uses local variables, or method arguments, 
from the enclosing code, these must be final or effectively final.
</div>

### Final Variables and Effectively Final
<div align="justify">

The code sample on below shows:

```java  
class ShowFinal {
    private void doThis(final int methodArgument) {
        final int Field30 = 30;
    }
}
```

* A method parameter, called methodArgument in the doThis method, declared as final.
* And a local variable, in the method block, Field30, also declared with the key word final.

In both these cases, this means you can't assign a different value
once these are initialized. 
These are **explicitly final**, and any of these could be used in a local class, because of this.
</div>

### Effectively Final
<div align="justify">

In addition to explicitly final variables, 
you can also use **effectively final**variables in your local class.
A local variable or a method argument is effectively final 
if a value is assigned to them, and then never changed after that. 
_Effectively final_ variables can be used in a local class.

In my code, I can use the last name in my local class's constructor, 
because I assigned it a value and never changed it. 
But what if I assign it a different value, let's say at the end of the method? 
Then, I'll get a compiler error where I use the local variable in the constructor of my local class. 
If I hover over that error, IntelliJ tells me that 
_lastName needs to be final or effectively final_. 
Because I made changes to the value of that variable in the method, 
even after the class declaration, this means the variable isn't effectively final, 
and for that reason I can't use it in my class. 
I'll revert that last change, so the code compiles.

You may have noticed that IntelliJ has been giving us some warnings, or indication, 
for some of our fields and variables that they might be final. 
We'll be talking more about when to use final and when not to, 
when we review the final modifier in an upcoming section. 
For local classes, it's just important to recognize when a variable is not final 
or effectively final, because you can't use it in the local class directly, 
as I've demonstrated here.
</div>

### Additional Local Types
<div align="justify">

As of JDK 16, you can also create a local record, 
interface and enum type, in your method block. 
These are all implicitly static types, and therefore aren't inner classes, 
or types, but static nested types. 
The record was introduced in JDK16. 
Prior to that release, there was no support for a local interface 
or enum in a method block either. 
You may see older tutorials saying you can't do this, 
but know that this functionality is now available. 
I'll be demonstrating an example of this later.
</div>

## [f. Anonymous Class]()
<div align="justify">

An anonymous class is a local class that doesn't have a name. 
All the nested classes we've looked at so far have been created with a class declaration. 
The anonymous class is never created with a class declaration, 
but it's always instantiated as part of an expression. 
Anonymous classes are used a lot less 
since the introduction of Lambda Expressions in JDK 8.

I had a student ask, why do we care about anonymous classes, 
when we now have lambda expressions? 
Aren't anonymous classes just legacy code? 
It's true that since JDK 8, when lambda expressions were released, 
they did start to replace anonymous class usage. 
But there are still some use cases where an anonymous class might be a good solution. 
And it's likely you'll be running across anonymous classes in older code. 
I also think understanding anonymous classes leads 
to a better understanding of lambda expressions.
Because of these reasons, I do want to cover the anonymous class, 
in the context of the other nested classes.

I'm going to create a new class and call it RunMethods, 
and I'll add a main method. 
I'm going to use generic method in the main method:

```java  
public class RunMethods {

    public static void main(String[] args) {
        
    }

    public static <T> void sortIt(List<T> list, Comparator<? super T> comparator) {

        System.out.println("Sorting with Comparator: " + comparator.toString());
        list.sort(comparator);
        for (var employee : list) {
            System.out.println(employee);
        }
    }
}
```

So this method called sortIt is generic and its type parameter, 
I've named _T_, it takes two arguments. 
The first has to be listed of type _T_. 
The second argument is a Comparator, so I am doing here with this wildcard _?_. 
This Comparator instead of specifying 
that it's to be a Comparator with the same type as the list, 
I'm using super here. 
Remember that's a lower bounded wildcard and what it means is 
that I can use a Comparator that's either the same type _T_, 
or a super type of _T_. 
This means if I'm sorting store employees, 
I can still use an Employee Comparator as one of the arguments, 
because Employee is a super type of Store Employee. 
Hopefully that makes sense.
And then, I'll sort the list, and print each employee. 
In our examples so far in this project, we created three comparators. 
One was a stand-alone or top level class, one was a static nested class on Employee, 
and one was an inner class on StoreEmployee. 
I want to use each of these now, first setting up local variables 
for each of these comparators. 
I'll start with the top-level class.

```java  
public class RunMethods {

    public static void main(String[] args) {
        
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macys"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target")));

        var c0 = new EmployeeComparator<StoreEmployee>();
        var c1 = new Employee.EmployeeComparator<StoreEmployee>();
        var c2 = new StoreEmployee().new StoreComparator<StoreEmployee>();

        sortIt(storeEmployees, c0);
        sortIt(storeEmployees, c1);
        sortIt(storeEmployees, c2);
    }

    public static <T> void sortIt(List<T> list, Comparator<? super T> comparator) {

        System.out.println("Sorting with Comparator: " + comparator.toString());
        list.sort(comparator);
        for (var employee : list) {
            System.out.println(employee);
        }
    }
}
```

I had two options for Employee Comparator and note I selected the top level class. 
So c0 is an instance of the top level **EmployeeComparator** class, 
typed with the **StoreEmployee** class. 
I'll add the other two comparators now. 
The second variable, c1, uses the static nested class on the Employee class. 
The third variable, c2, is using the inner class on StoreEmployee. 
Remember that this syntax, **.new**, creates an instance of StoreEmployee first, 
then uses that to create an instance of the inner class. 
Now I'll invoke the sortIt method with the storeEmployees list, 
and each of these comparators.

First c0 variable, I'll copy that statement, 
and paste it twice, right below it. 
And then changing c0 to c1 and for the third one c0 to c2.
As you can see from this code, 
any of these comparators works with a **StoreEmployee** 
when I pass it to the sortIt method. 
If I run it:

```java  
Sorting with Comparator: domain.EmployeeComparator@b4c966a
Target  10322 Bud      2016
Walmart 10515 Joe      2021
Walmart 10215 Marty    2018
Target  10015 Meg      2019
Macys   10105 Tom      2020
Sorting with Comparator: domain.Employee$EmployeeComparator@37a71e93
Target  10322 Bud      2016
Walmart 10515 Joe      2021
Walmart 10215 Marty    2018
Target  10015 Meg      2019
Macys   10105 Tom      2020
Sorting with Comparator: domain.StoreEmployee$StoreComparator@7e6cbb7a
Macys   10105 Tom      2020
Target  10322 Bud      2016
Target  10015 Meg      2019
Walmart 10215 Marty    2018
Walmart 10515 Joe      2021
```

The results are the same for the first two comparators, sorting by _name_. 
The last is sorted by store, then _yearStarted_, 
which is how we created that comparator. 
In each case, you can see the string representation of each comparator, 
which is the default _toString_ method for any object. 
The first is an instance of the **EmployeeComparator** class. 
The second is an instance of the nested class, **EmployeeComparator**, 
on the Employee class. 
The _$_ in the output you see here, indicates that the class following, 
is for a nested class. 
And you can see that also, for the **StoreComparator**, an inner class, 
on Store Employee. 
Ok, so next, I'll quickly create a local class in the main method 
that acts as a comparator. 
I'll insert this after my three local variables. 
I'll put a comment here this is a local class.

```java  
public class RunMethods {

    public static void main(String[] args) {
        
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macys"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target")));

        var c0 = new EmployeeComparator<StoreEmployee>();
        var c1 = new Employee.EmployeeComparator<StoreEmployee>();
        var c2 = new StoreEmployee().new StoreComparator<StoreEmployee>();

        // This is a local class
        class NameSort<T> implements Comparator<StoreEmployee> {

            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                //return 0;
                return o1.getName().compareTo(o2.getName());
            }
        }

        var c3 = new NameSort<StoreEmployee>();

        sortIt(storeEmployees, c0);
        sortIt(storeEmployees, c1);
        sortIt(storeEmployees, c2);
    }

    public static <T> void sortIt(List<T> list, Comparator<? super T> comparator) {

        System.out.println("Sorting with Comparator: " + comparator.toString());
        list.sort(comparator);
        for (var employee : list) {
            System.out.println(employee);
        }
    }
}
```

And I want to implement the compareTo method in this local class, 
using IntelliJ's tools to do that for me. 
And I'll replace **return 0;**, with my own comparison, using names again.
And now, I want another variable, c3, that is an instance of this local class. 
And I'll invoke another call to the sort it method with this new local comparator.
And running that;

```java  
----(same)
Sorting with Comparator: RunMethods$1NameSort@7c3df479
Target  10322 Bud      2016
Walmart 10515 Joe      2021
Walmart 10215 Marty    2018
Target  10015 Meg      2019
Macys   10105 Tom      2020
```
                            
Notice the string representation of this last comparator.
You can see that it includes the current class name, **RunMethods**, 
then a _$_ sign with a number, and then my local class's name, **NameSort**. 
You can see that, though it's a local class in a method block, 
it's still a named class. 
Now, how is an anonymous class different? 
I'll create one, then we'll examine it. 
I'll add a comment that this is an anonymous class.

```java  
public class RunMethods {

    public static void main(String[] args) {
        
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macys"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target")));

        var c0 = new EmployeeComparator<StoreEmployee>();
        var c1 = new Employee.EmployeeComparator<StoreEmployee>();
        var c2 = new StoreEmployee().new StoreComparator<StoreEmployee>();

        sortIt(storeEmployees, c0);
        sortIt(storeEmployees, c1);
        sortIt(storeEmployees, c2);
        
        // This is a local class
        class NameSort<T> implements Comparator<StoreEmployee> {
            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                //return 0;
                return o1.getName().compareTo(o2.getName());
            }
        }
        var c3 = new NameSort<StoreEmployee>();
        sortIt(storeEmployees, c3);

        // This is anonymous class
        var c4 = new Comparator<StoreEmployee>() {
            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                //return 0;
                return o1.getName().compareTo(o2.getName());
            }
        };
        sortIt(storeEmployees, c4);
    }

    public static <T> void sortIt(List<T> list, Comparator<? super T> comparator) {

        System.out.println("Sorting with Comparator: " + comparator.toString());
        list.sort(comparator);
        for (var employee : list) {
            System.out.println(employee);
        }
    }
}
```

The first thing I want you to notice is that I'm getting an error in the statement of c4. 
I'll deal with that shortly. 
The second thing is that I'm creating the variable c4, 
and immediately assigning it an instance, using the new keyword. 
At first glance, this might look like I'm creating an instance of an interface, 
the comparator interface. 
Hopefully you remember I can't do that, we can't instantiate an interface directly. 
For example, if I remove the opening and closing braces on that line, 
the message I get back from IntelliJ is, _Comparator is abstract and can't be instantiated_.
I'll put back the curly braces. 
The message I'm getting is quite different now, indicating that 
_I need to implement a method on this class_. 
The class body, which is represented by these opening and closing curly braces, 
is telling Java that this isn't an instance of an interface. 
But actually, it's special syntax; that means this is an anonymous class being created 
that implements Comparator. 
And because this unnamed class is implementing an interface, 
this means I still need to implement any abstract methods on that interface. 
I'll do that for the _compareTo_ method. 
And finally, I'll replace **return 0;** with the same comparison 
I used for all these other comparators. 
That's all I need to create an anonymous class that implements this interface. 
To use it, I'll add another call to _sortList_.
Running the code now:

```java  
Sorting with Comparator: RunMethods$1@7106e68e
Target  10322 Bud      2016
Walmart 10515 Joe      2021
Walmart 10215 Marty    2018
Target  10015 Meg      2019
Macys   10105 Tom      2020
```

The string representation of this last comparator indicates 
that it's a class local to the RunMethod class. 
But it has no name, as you can see. 
This is how java represents an anonymous class.

An anonymous class is instantiated and assigned in a single statement. 
The **new** keyword is used followed by any type. 
This is **NOT** the type of the class being instantiated. 
It's the superclass of the anonymous class, 
or it's the interface this anonymous class will implement 
as I'm showing here.

```java  
var c4 = new Comparator<StoreEmployee>() {};
```
                        
In the first example above, the anonymous unnamed class 
will implement the Comparator interface. 
In the second example below, the anonymous class extends the Employee class, 
meaning it's a subclass of Employee.

```java  
var e1 = new Employee {};
```
                        
In both cases, it's important to remember the semicolon 
after the closing bracket, because this is an expression, 
not a declaration.

Anonymous classes were a pretty fun feature in their heyday, 
because you could create a type on the fly, 
and pass it as a method argument. 
What do I mean by that? 
Well, I didn't really have to assign this bit of code to a variable. 
Actually, let me invoke the sort it method, one more time.

```java  
sortIt(storeEmployees, (o1, o2) -> o1.getName().compareTo(o2.getName()));
```

Here, I'm creating an anonymous class directly as a method argument. 
In other words, I'm using the anonymous class to create a bit of custom functionality, 
and pass that functionality as an argument to a method, via the interface.
This code is a little harder to read, 
but you may see code like this that pre-dates lambda expressions. 
You should recognize this as an on-th-fly anonymous class, 
being passed as a method argument. 
If I run this code, I again see 
that this class is considered part of the RunMethods class, 
and it's simply given a number. 
Wouldn't it be nice if we did not have to go through 
all of this effort to create these little classes, 
anonymous or otherwise, that implement this one line of code, 
which is really the thing we're interested in controlling and customizing.
It's this one line of code, which ultimately the sort method is interested in, 
in every one of these cases. 
Creating a class that implements an interface, and overriding a method, 
feels like a heavyweight solution to pass a small bit of custom functionality around. 
Now notice, IntelliJ has grayed out the new Comparator part of this expression. 
If I hover over that, it tells me 
"_Anonymous new Comparator can be replaced with a lambda expression_" 
and it gives me the option to replace with a lambda, so I'll select that. 
That's a little easier on the eyes, in some ways, and it's also more visible 
that it's this statement of code, that's ultimately being passed to the sortIt method. 
If I run that:

```java  
Sorting with Comparator: RunMethods$$Lambda$28/0x0000000801006530@76fb509a
Target  10322 Bud      2016
Walmart 10515 Joe      2021
Walmart 10215 Marty    2018
Target  10015 Meg      2019
Macys   10105 Tom      2020
```

The string representation indicates a Lambda expression 
is the source of the comparator passed to the method. 
In the next section of the course, I'm going to cover the lambda expression in a lot of details. 
However, first I have a final challenge for you in this section, 
which will help give you some extra experience with local and anonymous classes.
</div>

## [g. Local and Anonymous Class Challenge](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_09_NestedClassesAndTypes/Course07_LocalAnonymousClassChallenge/README.md#local-and-anonymous-class-challenge)
<div align="justify">

First, you need to create a record named **Employee**, 
that contains **First Name**, **Last Name**, and **hire date**. 
We'll be using this record as a domain class, 
which we'll pretend we don't have the luxury of changing.
*  Set up a list of Employees with various names and hire dates in the main method.
*  Set up a new method that takes this list of Employees as a parameter.
*  Create a local class to wrap this class 
(pass Employee to the constructor and include a field for this) 
and add some calculated fields, such as full name, and years worked.
*  Create a list of employees using your local class.
*  Create an anonymous class to sort your local class employees, 
by full name, or years worked.
*  Print the sorted list.

**Hint**: Here is another review of a date function, 
which should help you with calculating years worked.

```java  
int currentYear = LocalDate.now().getYear();
```
                    
We'll be covering dates and time thoroughly in our next section of the course. 
Remember that a local class is a class declaration in a method block, 
and you can create many instances of it, 
so that's why your Employee wrapper class should be set up as a local class. 
An anonymous class is a single instance of an unnamed class, 
and you've seen an example of a Comparator already, 
which you'll be doing that here.
</div>


<div align="justify">

</div>






