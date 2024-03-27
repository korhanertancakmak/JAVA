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

## [d. Inner Class Challenge]()
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




<div align="justify">

</div>


<div align="justify">

</div>


<div align="justify">

</div>


<div align="justify">

</div>


<div align="justify">

</div>





