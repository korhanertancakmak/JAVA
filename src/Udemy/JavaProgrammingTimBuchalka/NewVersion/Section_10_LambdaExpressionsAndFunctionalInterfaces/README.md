# [Section-10: Lambda Expressions, Functional Interfaces, and Method Reference]()

## [a. Lambda Expressions]()
<div align="justify">

I wanted to introduce you to this section a little earlier in the course than here, 
because many methods supporting these expressions have been introduced 
on Java's interfaces and classes, since JDK 8. 
Lambda expressions let you pass around snippets of custom code, 
giving you so much more functionality than you might otherwise be able to achieve,
and with very little effort. 
It's sometimes confusing to understand these expressions, 
or how much power is in their punch, and sometimes with just a single statement. 
I hope that by the end of this section, you'll be kind of excited about 
what you can do with them, and be ready to use them a lot. 
A lambda expression can be thought of as implicit code for an anonymous class, 
using a special kind of interface, as the mechanics to do this. 
The method reference goes even further, and is a shortcut 
for the lambda expression syntax, for existing methods. 
To conclude this section, I'll introduce you to convenient methods available on many interfaces. 
Don't miss out on this part of the section, 
as it will simplify some of the repetitive work you may encounter. 
Ok, so let's get started.

In the lecture on anonymous classes, I replaced an anonymous class 
with a lambda expression in a method argument, using the IntelliJ code hint, 
and generation tools. 
I want to do that again here, at the start of this lecture, so let me that code up real quick. 
I'm going to create a record, and just for fun, 
I'll make that inner record on the Main class, since you already know how to do this, 
if you've been following along with the course in order. 
If you need a refresher on inner classes, look at the previous section.

```java  
record Person (String firstName, String lastName) {

    @Override
    public String toString() {
        //return null;
        return firstName + " " + lastName;
    }
} 
```

I'll call this person, and for the fields, I'll have two strings, 
first name and last name. 
I think I've said this before, but it bears repeating that records are implicitly static, 
when used as an inner class, in the same way enums and interfaces are. 
What this means is, I can access the record directly using Main's class name, 
if I wanted to create instances of it, from outside this class. 
Next, I want to include a simpler String representation, 
so generate an overridden toString method. 
I'll change **return null;**, to return the first and last name with a space between them.
Now, I want to create a list of these person records in the main method:

```java  
public static void main(String[] args) {
    List<Person> people = new ArrayList<>(Arrays.asList(
            new Main.Person("Lucy", "Van Pelt"),
            new Person("Sally", "Brown"),
            new Person("Linus", "Van Pelt"),
            new Person("Peppermint", "Patty"),
            new Person("Charlie", "Brown")));
    
    // Using anonymous class
    var comparatorLastName = new Comparator<Person>() {

        @Override
        public int compare(Person o1, Person o2) {
            //return 0;
            return o1.lastName().compareTo(o2.lastName());
        }
    };

    people.sort(comparatorLastName);
}
```

I'll set up a list variable, named people, assigning that a new ArrayList. 
I'm going to pass it a list of person records, 
so I'll use the helper method on the Arrays class, as List to do it. 
And I'll create new records for some of the peanut characters. 
I purposely used **Main.Person()** for the first-person creation, 
just to demonstrate that I could use the class qualifier to create a new person, 
because person is really a static nested class of main. 
This isn't required inside this class, so for the rest of the records, 
I simply use the unqualified Person class. 
Now, I want to create a custom comparator.

I'll do this first with an anonymous class. 
I'm using var as the type for simplicity, 
and assigning it to a new Comparator with a type of Person. 
I'll use IntelliJ tools to add the overridden method. 
And I'll replace **return 0;** with the code to compare people by their last names. 
**CompareTo** will work nicely.

Next, I'll execute sort on my people list, 
passing it this anonymous instance of the Comparator interface, 
by passing the comparatorLastName variable to the sort method. 
Then I'll print my list out. 
Running that:

```java  
[Sally Brown, Charlie Brown, Peppermint Patty, Lucy Van Pelt, Linus Van Pelt]
```
            
You can see my list of peanut characters printed out there, 
sorted by their last names, where Sally Brown in listed first. 
Because I only used last name to sort, any person's records 
that have the same last name will still be ordered by insertion order, 
so that's why Sally comes before _Charlie Brown_ in this case.

Next, instead of assigning an anonymous class to a variable, 
I'm to both create and use this anonymous class directly in the method argument. 
First, I'll copy most of the anonymous class statement, from after the assignment operator,
the equals sign there, and before the final semicolon. 
I'm going to paste that, in place of the comparatorLastName variable:

```java  
public static void main(String[] args) {
    List<Person> people = new ArrayList<>(Arrays.asList(
            new Main.Person("Lucy", "Van Pelt"),
            new Person("Sally", "Brown"),
            new Person("Linus", "Van Pelt"),
            new Person("Peppermint", "Patty"),
            new Person("Charlie", "Brown")));

    // Using anonymous class
    var comparatorLastName = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            //return 0;
            return o1.lastName().compareTo(o2.lastName());
        }
    };

    people.sort(new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            //return 0;
            return o1.lastName().compareTo(o2.lastName());
        }
    });
}
```

In the call to the _people.sort_ method. 
I can run it this way, and it works just as before. 
But again, notice that IntelliJ has grayed out **new Comparator<Person>()**, 
when I use it in the method argument. 
If I hover over that, I can see I can replace that with a lambda expression, 
so I'll do that.

```java  
public static void main(String[] args) {
    List<Person> people = new ArrayList<>(Arrays.asList(
            new Main.Person("Lucy", "Van Pelt"),
            new Person("Sally", "Brown"),
            new Person("Linus", "Van Pelt"),
            new Person("Peppermint", "Patty"),
            new Person("Charlie", "Brown")));

    // Using anonymous class
    var comparatorLastName = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            //return 0;
            return o1.lastName().compareTo(o2.lastName());
        }
    };

    people.sort((o1, o2) -> o1.lastName().compareTo(o2.lastName()));
    System.out.println(people);
}
```

You might notice that even after I changed the last bit to a lambda expression, 
IntelliJ has again grayed that out and has another hint for us. 
IntelliJ says _we can now replace our code with **Comparator.comparing**_. 
I want you to ignore this suggestion until the end of this section of the course for now. 
It helps to understand first lambda expressions, then method references, 
then Comparator's special convenience methods, which the comparing method is. 
Don't worry. 
I'm going to cover all of these topics in order. 
For now, I want to ignore this hint and leave this code as I currently have it 
with this lambda expression. 

Let's review this expression for a few minutes.
The syntax of this lambda expression is on the left below.

| Generated Lambda Expression                             | Comparator's Abstract Method  |
|---------------------------------------------------------|-------------------------------|
| ((o1, o2) &rarr; o1.lastName().compareTo(o2.lastName()) | int compare(T o1, T o2)       |

This was passed directly as a method argument, for a parameter type that was a Comparator. 
The Comparator's abstract method, compare, is shown here on the right side. 
The lambda expression parameters are determined 
by the associated interface's method, the functional method. 
In the case of a Comparator and its compare method, there are two arguments.
This is why we get o1, and o2 in parentheses, in the generated lambda expression. 
These arguments can be used in the expression, which is on the right of the arrow token.

A lambda expression consists of a formal parameter list, 
usually but not always declared in parentheses; the arrow token; 
and either an expression or a code block after the arrow token. 
Because lambda expressions are usually simple expressions, 
it's more common to see them written as shown below, 
which doesn't use a code block, or opening and closing curly braces in other words.

```java  
(parameter1, parameter2, ... ) -> expression;
```

The expression should return a value 
if the associated interface's method returns a value. 
In the case of our generated expression, it returns an int, 
which is the result of the compare method on Comparator.

```java  
(o1, o2) -> o1.lastName().compareTo(o2.lastName())
```

Next, I want to look at the anonymous class side by side with the lambda expression. 
I think it helps with understanding the mechanism of lambdas. Are you asking, 
where's the link between the compare method and this lambda expression? 
It's obvious in the anonymous class, because we override the compare method, 
and return the result of that expression.

| Anonymous Class                                                                                                                                                                                                                                                                                              | Lambda Expression                                         |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------|
| new Comparator < Person > ( ) {<br/>&nbsp;&nbsp;&nbsp;&nbsp; @Override<br/>&nbsp;&nbsp;&nbsp;&nbsp; public int compare (Person o1, Person o2) {<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; return o1.lastName( ).compareTo(o2.lastName( ));<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;} <br/>} | (o1, o2) &rarr; o1.lastName( ).compareTo(o2.lastName( )); |

We can see the two parameters and their types, and what the return value should be, 
in the anonymous class. 
It's all spelled out for us there. 
But the lambda expression has no reference to an enclosing method, 
as far as we can see from this code. 
How can these mean the same thing? 
How does Java know what the parameters and return type are, 
or even the method for that method?

For a lambda expression, _the method is inferred_ by Java! 
How can Java infer the method to use? 
Java takes its clue from the reference type, in the context of the lambda expression usage. 
What do I mean by that? 
Here, I show a simplified view, ignoring the generic types, of the sort method on List.

```java  
void sort(Comparator c)
```

And here is the call to that method passing the lambda expression.

```java  
people.sort((o1, o2) -> o1.lastName().compareTo(o2.lastName()));
```

From this, Java can infer that this lambda expression resolves to a Comparator type 
because of the method declaration. 
This means the lambda expression passed should represent code 
for a specific method on the Comparator interface. 
But which method? Well, there's only one the lambda expression cares about, 
and that's the **abstract method** on Comparator. 
Java requires types which support lambda expressions
to be something called a functional interface.

**A functional interface** is an interface that has _one, and only one, abstract method_. 
This is how Java can infer the method, to derive the parameters and return type, 
for the lambda expression. 
You may also see this referred to as **SAM**, which is short for **Single Abstract Method**, 
which is called the functional method. 
A functional interface is the _target type for a lambda expression_. 
Let's explore this further in a bit code.

```java  
interface EnhancedComparator<T> extends Comparator<T> {
    int secondLevel(T o1, T o2);
}
```

And next, I want to create my own local interface that extends Comparator. 
As of JDK 16, this functionality is supported, meaning I can declare an interface right here, 
a local interface, in the method block, local only to this method. 
I'm going to call this EnhancedComparator, a generic interface, with type _T_. 
It's going to extend the Comparator interface. 
And this interface is going to have one abstract method, called secondLevel, 
that takes two arguments of type _T_, the generic type. 
I'll next use this interface to create a second anonymous class. 
My variable will be called comparatorMixed, and I'll assign it a new anonymous class expression, 
using this EnhancedComparator, and declaring the type argument to be person. 
IntelliJ is prompting me there's an error, 
and the solution is to implement the abstract method, so I'll do that, 
picking the highlighted ones by default. 
Notice here that IntelliJ has highlighted two methods. 
Why two, when my local interface only has one? 
What you shouldn't forget is that interfaces inherit abstract methods.
My new interface here, inherits the abstract method, compare, 
that's on the Comparator interface. 
I could've implemented it in EnhancedComparator, but I didn't. 
Remember, I don't have to implement abstract methods when I extend interfaces.
This means there are now two abstract methods, on the **EnhancedComparator**; 
that concrete classes have to implement, if they use this interface. 
I'll generate these two methods. 
And I'll replace **return 0;**, in the _compare_ method, with two statements.

```java  
var comparatorMixed = new EnhancedComparator<Person>() {

    @Override
    public int compare(Person o1, Person o2) {
        //return 0;
        int result = o1.lastName().compareTo(o2.lastName());
        return (result == 0 ? secondLevel(o1, o2) : result);
    }

    @Override
    public int secondLevel(Person o1, Person o2) {
        //return 0;
        return o1.firstName().compareTo(o2.firstName());
    }
};

people.sort(comparatorMixed);
System.out.println(people);
```

First, I'll set up a local variable, result, assigning it the result of comparing the last names. 
And then in my return statement, I'm going to use a ternary operator. 
If the result of comparing the last names is zero, meaning they have the same last names, 
I want to execute the secondLevel method. 
Now I need to implement the second level method, which will compare first names. 
I'll replace **return 0;** with one that returns the comparison of the 2-person first names. 
Ok, so that's my new anonymous comparator. 
I want to again sort my people list, using this variable, comparator Mixed, 
and print my list out to confirm it worked.
Running that:

```java  
[Sally Brown, Charlie Brown, Peppermint Patty, Lucy Van Pelt, Linus Van Pelt]
[Charlie Brown, Sally Brown, Peppermint Patty, Linus Van Pelt, Lucy Van Pelt]
```

You can see the people sorted by last name, then first name, on the second output there, 
so now Charlie Brown comes before Sally Brown, and Linus is before Lucy.
Now, let's say I want to turn this anonymous class into a lambda expression? 
Well, I can't. 
This is because it's not technically a functional interface. 
When we say a functional interface can only have one abstract method, this means only 1, 
and includes counting any inherited methods. 
Java can't determine which abstract method to use for this interface, 
so it can't be a target type for lambda expressions. 
Because this interface ultimately requires classes to implement two abstract methods, 
it's not a functional interface. 
I hope that makes sense, and that now you have a pretty good handle on 
how a lambda expression works under the covers, 
and why the functional interface is the underlying framework for its use.

So far, I talked about how the functional interface is the framework 
that lets a lambda expression be used. 
Lambda expressions are also called lambdas for short. 
Many of Java's classes use functional interfaces in their method signatures, 
which allows us to pass lambdas as arguments to them. 
You'll soon discover that lambdas will reduce the amount of code you write. 
Once you get the app froze of using lambdas, 
I think you'll really go to appreciate this feature of Java. 
Now, I'm going to cover many of Java's built-in functional interfaces.
</div>

## [b. Functional Interfaces]()

### [Consumer Interface]()
<div align="justify">

The Consumer interface is in the _java.util.function_ package. 
It has one abstract method that takes a single argument, 
and doesn't return anything.

```java  
void accept(T t)
```
                                
This doesn't seem like a very interesting interface at first, 
but let's see what this means in practice, 
as far as the lambda expressions we can use with it.

```java  
public static void main(String[] args) {

    List<String> list = new ArrayList<>(List.of("alpha", "bravo", "charlie", "delta"));

    for (String s : list) {
        System.out.println(s);
    }
}
```

This isn't new, but you may remember that this felt like 
a really nice improvement over the traditional for loop, 
which requires us to use indexing. 
But actually, the ArrayList comes with an easier way, 
which I haven't yet discussed.
Let me show you how you'd do this, 
using a method that's a default method on the Iterable interface, 
which List inherits, called forEach. This method takes a lambda expression, 
a Consumer type, which I just talked about, as its argument.
First I'll add a separator line, then I'll invoke forEach on my list, 
and pass it a lambda expression.

```java  
System.out.println("-------");
list.forEach((s) -> System.out.println(s));
```

My lambda expression in this case has one parameter, which I'm simply calling s. 
This parameter is the element that's being iterated over, 
so much like I did with the enhanced for loop, I'm simply calling this variable s. 
Then I have the arrow **->** token and call "**System.out.println**", 
passing that method s, my lambda expression parameter. 
And running that,

```java  
alpha
bravo
charlie
delta
-------
alpha
bravo
charlie
delta
```

You can see my strings are printed out, one on each line, just as before. 
How does it know **s** is the element in the list? 
It's part of the mechanism of that forEach method. 
If you're curious what the underlying code looks like, 
you can control click on the forEach method, or alternately, 
right-click to show the menu bar, select **Go to**, 
and then select Declarations or usages. 
This will open the Iterable interface to that method, 
and you can see the underlying implementation is

```java  
default void forEach(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    for (T t : this) {
        action.accept(t);
    }
}
```

It's just enhanced for loop after all; that 
calls the accept method on the argument passed. 
Each element is passed as an argument to that method. 
Hopefully that helped you understand that this **forEach** 
method is really looping through Strings and each is getting printed. 
I don't have to call the parameter s in that expression, 
I can call it anything I want. 
Like the loop element in the for loop, 
I can name the argument variable anything that seems relevant, 
so let me now call it my String, instead of _s_.

```java  
System.out.println("-------");
//list.forEach((s) -> System.out.println(s));
list.forEach((myString) -> System.out.println(myString));
```

Whatever I call the variable on the left is 
what I need to refer to it as, on the right, 
in the code in the lambda expression code section. 
I can also include a type in the parameter declaration 
if I wanted to. 
I'll do that next, including String as the type of my String.

```java  
System.out.println("-------");
//list.forEach((s) -> System.out.println(s));
//list.forEach((myString) -> System.out.println(myString));
list.forEach((String myString) -> System.out.println(myString));
```

This might help with readability, but it's not really necessary, 
since Java can infer the type because of our list's type. 
I'll revert to that change. 
If you've only got one parameter, as I do here, 
you don't even need the parentheses around that parameter, 
so I'll remove them.

```java  
System.out.println("-------");
//list.forEach((s) -> System.out.println(s));
//list.forEach((myString) -> System.out.println(myString));
//list.forEach((String myString) -> System.out.println(myString));
list.forEach(myString -> System.out.println(myString));
```

This is also a valid way to write a lambda expression. 
But if I now try to include the type again, without the parentheses,
the java compiler doesn't like that. 
Parentheses are optional only when you have a single parameter, 
and aren't including the reference type of that parameter. 
I'll put the parentheses back in this case to get it to compile. 
Instead of string in the lambda expression parameter, I can use var. 
I'll do that, replacing String with var.

```java  
System.out.println("-------");
//list.forEach((s) -> System.out.println(s));
//list.forEach((myString) -> System.out.println(myString));
//list.forEach((String myString) -> System.out.println(myString));
//list.forEach(myString -> System.out.println(myString));
list.forEach((var myString) -> System.out.println(myString));
```

Java can infer the type, again because my list 
was declared with the type argument, String. 
I'm also not limited to a single statement of code 
to the right of the arrow token.
Now, IntelliJ's trying to get our attention with **System.out.println**.
If I hover over that you'll see the message, 
"_Lambda can be replaced with method reference_". 
I'll be covering method references shortly, but again for now, 
just ignore these warnings as I introduce you to these expressions. 
Ok now, if I want to include multiple statements, 
I need to use opening and closing curly braces around the statements. 
Let me show you that.

```java  
System.out.println("-------");
list.forEach((var myString) -> {
    char first = myString.charAt(0);
    System.out.println(prefix + " " + myString + " means " + first);
});
```

First, I'll add another separator line. 
Then I'll invoke forEach on list with the same parameter, 
_var myString_ in parentheses, followed by the arrow token,
but now I'm going to put an opening curly brace here, 
and I'll set up a local variable, char, 
assigning that the first character of the **myString** variable. 
I'll print myString, and that first letter out next. 
Now, because I'm using curly braces, each statement 
in this lambda expression code block must end in a semicolon, 
as you can see I've done here, in this code. 
And running the code:

```java  
-------
alpha means a
bravo means b
charlie means c
delta means d
```
                
I get the output. 
Let's review these syntax variations I just covered 
to summarize some of what I've just gone over. 
This table shows the different ways 
to declare a single parameter in a lambda expression.

| Lambda Expression                                    | Description                                                 |
|------------------------------------------------------|-------------------------------------------------------------|
| element &rarr; System.out.println(element);          | A single parameter without a type can omit the parentheses. |
| (element) &rarr; System.out.println(element);        | Parentheses are optional.                                   |
| (String element) &rarr; System.out.println(element); | Parentheses required if a reference type is specified.      |
| (var element) &rarr; System.out.println(element);    | A reference type can be var.                                |

In the first example, a single parameter without a type can 
omit the parentheses around the parameter. 
Or I could include the optional parentheses in the second example. 
Parentheses are required if a referenced type is specified, 
which I'm showing in the third example, where I'm specifying the type as String. 
A reference type can be replaced with var, as demonstrated by the fourth example. 
We'll look at how to declare multiple parameters a bit later. 
The lambda body can be a single expression, 
or can contain a lambda body in opening and closing curly brackets.

```java  
(element) -> System.out.println(element);
```

An expression can be a single statement. 
Like a switch expression, that does not require yield 
for a single statement result, the use of return is not needed 
and would result in a compiler error if you did use it.

```java  
(var element) -> {
    char first = element.charAt(0);
    System.out.println(element + " means " + first);
};
```

An expression can be a code block.  
Like a switch expression with a code block that requires yield, 
a lambda that returns a value would require a final return statement. 
We'll be covering these examples in upcoming lectures. 
All statements in the block must end with semicolons.

Next, I want to talk about scoped variables and lambda expressions. 
Like local and anonymous classes, the lambda expression's code 
can use the enclosing code's local variables or method parameters, 
under certain circumstances. 
These need to be final or effectively final. 
Let me set up a local variable, just above that last forEach method call.
I'm going to set up a String variable named prefix, and set it to nato, 
all in the lower case. 
I can use this prefix variable in any of my lambda expressions now, 
as long as I never make any changes to that variable or reassign it. 
I'm going to add that prefix variable inside my lambda code, 
printing it out first for each statement.

```java  
System.out.println("-------");
String prefix = "nato";
list.forEach((var myString) -> {
    char first = myString.charAt(0);
    System.out.println(prefix + " " + myString + " means " + first);
});
```

This code runs:

```java  
-------
nato alpha means a
nato bravo means b
nato charlie means c
nato delta means d
```
                    
And I now get "nato" printed out on each line. 
It's important to understand that if we're using local variables 
from the enclosing scope, we can't change them anywhere in this main method. 
This means we can't change this String in any code in this method, 
either before or after the lambda expression it's used in. 
For example, if I added a statement after the lambda expression, 
and set **prefix = "NATO"**, in all upper cases this time, 
notice what happened. 
I've got an error on prefix inside the lambda expression, 
even though it's executed before this line of code, 
_Variable used in lambda expression should be final or effectively final_. 
Later I'm going to cover the concept of deferred lambda expressions, 
which is one reason you can't use variables that aren't effectively 
final in your lambda. 
Here, in this code, it's not so intuitive, 
since the lambda was created and executed in the same statement, 
but this always isn't how lambdas are used. 
We'll be covering deferred lambdas in just a bit. 
Right now, I want to remove that last statement.

Another thing you have to remember is that the parameter list 
you use in the lambda can't conflict with the enclosing 
scope's parameters and local variables. 
What do I mean by that? 
Let me show you. 
I'm going to add a local variable, called myString, again right 
before that last forEach method call, 
and I'll set that to the text, enclosing method's myString. 
This code also gives me a compiler error on the very next line, 
where I'm declaring my lambda expressions parameters. 
IntelliJ's message is _Variable 'myString' is already defined in the scope_. 
These lambda parameters can't have the same name as the enclosing block's variables. 
Let me revert to that change.
And like any other enclosing block, if we declare local variables 
in the lambda expression, we can't access them after that block. 
I'll demonstrate that by trying to print myString after the last forEach call, 
**System.out.println(myString);**. 
Now, this too is a compiler error, which IntelliJ says 
_Cannot resolve symbol **myString**_, because myString goes out of scope 
outside the lambda expression. 
I'll revert that change as well so that my code compiles and runs.

Now that we've actually seen lambda expressions, how have they enhanced java? 
Well, some developers see lambdas as nothing more than syntactic sugar, 
giving us a more convenient and concise way of writing anonymous classes. 
I mean, after all, they don't add anything to the language that was not already there. 
We can assign them to variables and pass them around, 
but we can do that with anonymous class objects as well. 
Now, other people say that lambdas are Java's first step into functional programming. 
This is a programming paradigm that focuses on computing and returning results. 
And just for more information about functional programming, 
there's a good wikipedia article [here](https://en.wikipedia.org/wiki/Functional_programming).

Check that out to find out a bit more about functional programming.
Another feature of Java makes extensive use of lambda expressions, and that's streams. 
Streams are exciting because they create a pipeline of work that can be formed into a chain. 
Many of the stream operations take functional interfaces as parameters, 
meaning we can code them with lambda expressions.

Previously, we've studied a functional interface 
with a functional method that took one parameter 
and didn't return a value; it was void in other words. 
Here, I want to make my own interface, 
that will perform some calculations on two values, 
and return a value from the calculation. 
I'll create a new interface in the same package, 
and call it Operation.

```java  
@FunctionalInterface
public interface Operation<T> {

    T operate(T value1, T value2);
}
```

I'll make it a generic interface, meaning I'll include a type parameter. 
For this type, I won't include any bounds, so this interface can be used with any type. 
I'll add a single abstract method, called **operate** that returns **T**, 
and takes 2 values, also type **T**, so value 1 and value 2. 
This means this method takes two arguments of the same type, 
and returns a value, also the same type as the values. 
This interface is a functional interface.
It has one single abstract method. 
I can verify that by adding an annotation statement above it, **@FunctionalInterface**. 
And you can see the code still compiles, 
so I know for sure this is a functional interface. 
I'll talk more formally about annotations later.
I'm using this one now, because it's best practice to include it, 
and it'll also help you verify that what you intended it 
to be is really what it is. 
I showed you earlier that this can get confusing 
if you're extending existing interfaces.
This annotation also informs future developers what your intentions were, 
and warns them not to include additional abstract methods, 
which could break code that uses this interface 
as a target for lambda expressions. 
Before I continue, I want to make sure you have an IntelliJ feature toggled on 
that will help you understand lambda expressions better.
Going to settings, under the File Menu. 
Open the Editor node, and under that open the general node, 
and look for Gutter Icons. 
Select that. 
About in the center of the list, you'll see Lambda listed there, 
and you want to make sure that's checked. 
You can see that mine was unchecked, so I'll check it now. 
You'll notice this is a pink icon with the greek lambda symbol in it. 
I'll show you what this looks like in the IntelliJ editor in just a moment. 
But first, I want to create a public static generic method in my Main class:

```java  
public static <T> T calculator(Operation<T> function, T value1, T value2) {
    T result = function.operate(value1, value2);
    System.out.println("Result of operation: " + result);
    return result;
}
```

So I want to include **< T >** before the return type, which I'm saying is **T**. 
I'm calling this calculator, and it's got three arguments, 
the first is an instance of my new interface type, **Operation**. 
And the second and third are the values 
that will be used in the operation of type **T**, 
the generic declared type. 
Next, I'll set up a local variable, result, type **T**, 
and that's going to get the value that comes back 
from calling operate on the function instance, passing it value1 and value2. 
I'm going to print that result out and return it from this method. 
Ok, so what does all this set up do for me? 
Well, I want to use this calculator method, to do different operations, 
on different types of data.
Let me set this up. I'll scroll up to the end of the main method,
and put the code in there.

```java  
public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"));

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("-------");
        list.forEach((var myString) -> System.out.println(myString));

        System.out.println("-------");
        String prefix = "nato";
        list.forEach((var myString) -> {
            char first = myString.charAt(0);
            System.out.println(prefix + " " + myString + " means " + first);
        });

        int result = calculator((a, b) -> a + b, 5, 2);
        int result = calculator((a, b) -> System.out.println(a + b), 5, 2);
        int result = calculator((a, b) -> "Test " + a + b, 5, 2);
        int result = calculator((Integer a, Integer b) -> a + b,5, 2);
        int result = calculator((var a, var b) -> a + b, 5, 2);
        int result = calculator((var a, var b) -> return a + b,5, 2);
        int result = calculator((var a, var b) -> { return a + b; }, 5, 2);
        int result = calculator((var a, var b) -> { int c = a + b; }, 5, 2);
    }
}
```

I'll pass a lambda expression with two parameters, 
and the code will add these together with the plus operator. 
And I'll pass the values 5 and 2. 
That's one statement, and the first argument is the lambda expression. 
This looks a little like the one I used in the first forEach method example. 
Except here, though, I've got two parameters; a and b. 
Notice I haven't specified a type here anywhere. 
The expression on the right of the arrow token is, a + b. 
Now remember, this is going to map back to the Operation's method under the covers. 
How do I know? 
Well, notice the pink gutter icon next to this statement. 
If I hover over that, IntelliJ tells me that this lambda expression, 
_Overrides method in Operation_.
Clicking on the icon, takes me to that interface's functional method's declaration, 
operate here, on Operation. 
This IntelliJ feature will help you identify the inferred method 
which will be used in the context of your lambda expression usage. 
In this example, I purposely created my own interface, 
so this information was known to me. 
But let's go up to the forEach method now, above this, 
and click on that gutter icon. 
This time, you see the Consumer interface is opened in the editor, 
and the **accept** method is displayed. 
If you're ever in doubt about the parameters needed, or 
what the resulting type should be, this feature can be very helpful. 
There's a lot more to cover here, but first I want 
to run this code from the Main class.

```java  
----(same)
Result of operation: 7
```

And you can see that the result of the operation is 7. 
Now, let me start working through some variations to this lambda expression.
First, I'll change **a + b** to **System.out.println(a + b)**. 
This change results in a compiler error. 
If I hover over that, IntelliJ gives 
_Bad return type in lambda expression: void cannot be converted to Integer_. 
Even though it doesn't look like I'm returning a value from this expression, 
I really am because the underlying abstract method has declared a return type. 
I can't have an expression here that does not return a value, 
like I'm trying to do by using **System.out.println**.
I'll undo that. 
I also can't return a result that has a different value type than the parameters. 
But I haven't defined the types for the parameters, have I? 
Well, I actually have passed integers as the second and third parameters to the
calculator method. 
The Operation interface's type is inferred in this case, not with an int value, 
because you'll remember Generics don't work with primitive values, 
but with Integer, the wrapper class. 
I can assign it to an int result because of auto unboxing, 
and the values are auto boxed before the operation is performed. 
Now what happens if I return something other than an int from this expression? 
I'll include adding a string as the first part of my lambda expression. 
And that's another error, similar to the previous, 
_Bad return type in lambda expression: String cannot be converted to Integer_. 
When I included **Test** in part of the equation there, 
everything was evaluated as Strings, but 5 and 2 are integers, 
so I can't return a String from my lambda code. 
Again I'll undo that. 
Now let me type my parameters this time.
And this is another valid lambda expression. 
Whether we specify the types, or their inferred, 
when you have multiple parameters, you need the parentheses either way. 
If I try to remove the parentheses, that will be an error. 
A couple more things here, first I'll change Integer b, to var b. 
Ok, now this too is an error, and hovering over that,
_Cannot mix var and explicitly typed parameters in lambda expression_. 
If you want to use var, to get java to infer the type, 
you have to use var for every parameter in the lambda expression parameter list. 
And let me do that. 
Ok, so now the compiler's happy, so I want to perform some other calculations. 
I'll next try division, on two decimal values.

```java  
var result2 = calculator((a, b) -> a / b, 10.0, 2.5);
```

This time I'll call this result2, and use the var type,
which will display a Java hint for us. 
I'll execute calculator with a lambda expression with the code to divide a by b. 
What I want to point out here, is the IntelliJ hint, tells me I'm getting a Double, 
with a capital D, meaning a double wrapper instance back. 
And running that:

```java  
---(same)
Result of operation: 4.0
```

I get a result of the operation is 4.0. 
I've used my single calculator method on both integers and doubles, 
and with different operations, the first addition, and second division. 
Let's keep going.

```java  
var result3 = calculator(
        (a, b) -> a.toUpperCase() + " " + b.toUpperCase(),
        "Ralph", "Kramden");
```

I'll add another, result3, again using var, and calling calculator. 
My expression is going to use string methods on a and b. 
I'm going to concat a to b, after calling an upper case, on both a and b, 
and pass two strings to the calculator method. 
You can see from this code that operations aren't restricted to just numbers, 
or even numeric operations.
Any operation can be executed by my calculator method, 
on any two elements of the same type, as I'm showing you with these Strings. 
I don't have to write a calculator method for every single type I want to support, 
and I don't have to write every possible operation in my code 
that I think calculator should support.
Instead, the code that gets executed can be anything that can be passed as a lambda, 
and that's pretty cool. 
This frees you up to write code once, that can be easily extendable. 
And it frees up client code to have more functionality which lambdas can customize.
Running this code:

```java  
---(same)
Result of operation: RALPH KRAMDEN
```
            
I get my answer all in uppercase, as a result. 
Ok, so you've gotten used to seeing the return keyword,
when something gets returned from a method. 
Why is the lambda expression different? 
First, let me try adding a return in that first call to calculator, 
in that lambda expression.

```java  
int result = calculator((var a, var b) -> return a + b, 5, 2);
```

And you can see that gives an error. 
You can only use return in a code block, inside a set of curly braces in other words.
I'll add those curly brackets, keeping the expression on a single line. 
But this still gives me an error, although this time it's a different error. 
The problem is, I need a semicolon after the return statement, 
before the closing bracket.
All statements in the curly braces must end in a semicolon, like any code block. 
I'll add one.

```java  
int result = calculator((var a, var b) -> { return a + b; }, 5, 2);
```

Now I've got a valid lambda expression, which has a return statement. 
I'll take out the return statement, and instead just include a local variable,
and assign it the result of **a + b**.

```java  
int result = calculator((var a, var b) -> { int c = a + b; }, 5, 2);
```

And without the return keyword, I get a message that _Missing return statement_ 
from this expression. 
When you use curly braces, and your method has a return type defined for it, 
you've got to have a return statement, as you would 
with any code inside a method declared with a return type. 
This isn't true though, when you don't have the curly braces, 
because the result is inferred, and in fact, as I showed earlier, 
if you do include return in a simple lambda, that's also a compiler error. 
Only use return in a code block when the functional method has a return type. 
I'm going to revert that lambda to its simplest form, 
which is what I started with it. 
That was a quick tour through a few more lambda expression rules. 
Let me try to summarize these on a couple of tables next.

The rules for multiple parameters used in a lambda expression are shown here.

| Lambda Expression                    | Description                                                                                        |
|--------------------------------------|----------------------------------------------------------------------------------------------------|
| (a, b) &rarr; a + b;                 | Parentheses are always requires. Explicit types are not.                                           |
| (Integer a, Integer b) &rarr; a + b; | If you use an explicit type for one parameter, you must use explicit types for all the parameters. |
| (var a, var b) &rarr; a + b;         | If you use var for one parameter, you must use var for all parameters.                             |

You can't mix and match var with explicit types, or omit var from one 
or some of the parameters. 
This table shows the two rules for returning from a lambda expression.

| Lambda Expression                                                                             | Description                                                                                                                    |
|-----------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|
| (a, b) &rarr; a + b;                                                                          | When not using curly braces, the return keyword is unnecessary, and will throw a compiler error.                               |
| (a, b) &rarr; {<br/>&nbsp;&nbsp;&nbsp; var c = a + b;<br/> &nbsp;&nbsp;&nbsp; return c;<br/>} | If you use a statement block, meaning you use the curly braces, a return is required if the function method has a return type. |
</div>

### [BinaryOperator Interface]()
<div align="justify">

Java provides a library of functional interfaces in the java.util.function package.
We looked at one already when I demonstrated the forEach method
which had an argument for a type that implemented the Consumer interface.
I'll look at another of these interfaces now, the BinaryOperator, in code.

```java  
public static <T> T calculator(BinaryOperator<T> function, T value1, T value2) {
    T result = function.apply(value1, value2);
    System.out.println("Result of operation: " + result);
    return result;
}
```

I'm just going to change my calculator method. 
Instead of my own interface which I called Operation, 
I'm going to make the first parameter, a **BinaryOperator**. 
This gives me an error on the first statement, where I'm executing operate, 
which was the functional method name, on my own interface. 
The functional method on the BinaryOperator is **apply**, 
so I'll change operate, to apply. 
With those two changes, my code compiles and If I run it:

```java  
---(same)
Result of operation: 7
Result of operation: 4.0
Result of operation: RALPH KRAMDEN
```
            
I get the exact same results. 
I didn't really need to create this Operation interface at all. 
As it turns out, a lot can be achieved with just a few different types of interfaces, 
and Java gives us a library of these. 
Java's libraries also use these interfaces extensively 
so that we can leverage lambda expressions in many methods, 
the forEach method on the ArrayList, being just one example.

It's a good idea to know the four basic types of functional interfaces 
in the java.util.function package. 
There are over forty interfaces in this package, 
but I don't want you to feel overwhelmed by that. 
These can all be categorized as one of the following types. 
This table shows the four categories, with the simplest method shown.

| Interface Category | Basic Method Signature | Purpose                                     |
|--------------------|------------------------|---------------------------------------------|
| Consumer           | void accept(T t)       | execute code without returning data         |
| Function           | R apply(T t)           | return a result of an operation or function |
| Predicate          | boolean test(T t)      | test if a condition is true or false        |
| Supplier           | T get()                | return an instance of something             |

Consumer interfaces are used to execute statements but don't pass any data back. 
They take one or two arguments of various types, but return no value. 
Function interfaces are used to perform an operation and return a result. 
These take one or two arguments of various types, 
and return the result of some formula or calculation, 
much like the Operation interface we created in the last lecture. 
Predicate interfaces are used to test a condition, and take one or two arguments,
again of various types, and always return a boolean result. 
Supplier interfaces are different because they take no args, 
but return an object which can be various types. 
Supplier interfaces are used to return an instance of something, 
a factory method might be a good lambda expression for this type of interface.

On the table below, I'm showing the two most common Consumer interfaces, 
and the functional method on each.

| Interface Name | Method Signature      |
|----------------|-----------------------|
| Consumer       | void accept(T t)      |
| BiConsumer     | void accept(T t, U u) |

The Consumer interface takes one argument of any type. 
The BiConsumer interface takes two arguments of two different types. 
Below, I show an example consumer lambda expression.

| Example Lambda Expression for Consumer  | Consumer Method  |
|-----------------------------------------|------------------|
| s &rarr; System.out.println(s)          | void accept(T t) |

It takes one argument and executes a single statement. 
No result is returned. 
We saw an example of the single argument consumer 
that using the forEach method on a List of Strings, 
to print out each string.

```java  
var coords = Arrays.asList(
        new double[]{47.2160, -95.2348},
        new double[]{29.1566, -89.2495},
        new double[]{35.1556, -90.0659});

coords.forEach(s -> System.out.println(Arrays.toString(s)));
```

Getting back to the code, I want to set up an example that 
uses both Consumer and BiConsumer. 
I'll start with a list of arrays, each, which has two double values. 
These sets of values are going to represent the latitude and longitude
locations of three points on the Mississippi River. 
You may remember that I used these coordinates in a previous lecture.
I'll create a local variable, **coords** and simplifying the type declaration, 
by letting Java infer it, with **var**. 
I'll assign it the value off **Arrays**, _asList_. 
With some hard-coded double arrays, containing some coordinate data. 
That gives me a list of double arrays where my double arrays 
only contain two doubles in each array. 
Now I want to print that out, and I'll use the _forEach_ method with _println_. 
This time, I'll pass it **Arrays.toString** with s, as its argument. 
Remember, the _forEach_ method is really using an enhanced for loop, 
iterating over each element in my coordinate list.
That means the _s_ variable here, is really an array of double, 
because my list is made up of arrays of doubles. 
I use **Arrays.toString** to print out the elements in my array, 
and pass that to **System.out.println**. 
I showed you this method before, except I just printed Strings previously, 
and here I want to print arrays. 
Running that:

```java  
---(same)
[47.216, -95.2348]
[29.1566, -89.2495]
[35.1556, -90.0659]
```

I get my coordinates printed out, one for each line. 
But what if I want to print this out a different way? 
I'll do this, demonstrating a BiConsumer functional interface in the process.

```java  
public static <T> void processPoint(T t1, T t2, BiConsumer<T, T> consumer) {
    consumer.accept(t1, t2);
}
```

My method will again be a generic method with no return type, **void**, 
and called processPoint. 
I'll pass _t1_ and _t2_, both will have the generic type _T_, 
and I'll also pass an instance of the _BiConsumer_ interface 
that takes two type arguments.
I'll make both of these _T_ as well, and name the argument consumer. 
Then I'll simply execute **consumer.accept**, passing it method arguments, 
_t1_ and _t2_. 
Now, a BiConsumer takes 2 args, and this code, 
I'm saying they'll be the same type, by using **<T, T>**. 
This executes code that doesn't return a value, 
or whose return value can be ignored. 
Before I call this, another thing I want to show you is that 
a lambda expression can be assigned to a local variable. 
I'll do that in the main method.

```java  
BiConsumer<Double, Double> p1 = (lat, lng) -> System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lng);
```

I'll set up a variable with a reference type, **BiConsumer**, 
and the same type arguments, both **Double**. 
I'll call that _p1_, and assign it a lambda expression. 
My lambda takes two parameters, so I'm calling those _lat_ and _long_, 
and then I'm going to print out my coordinates in a nicely formatted way, 
using the printf method. 
If I run this code:

```java  
----(same)
[47.216, -95.2348]
[29.1566, -89.2495]
[35.1556, -90.0659]
```
            
Nothing additional is executed, even though I've declared 
this lambda expression here. 
Declaring a variable with a lambda expression isn't going to actually execute that expression. 
Instead, I need to manually call the functional method on that variable 
or pass that variable to a method that does the same thing. 
I'll pass this variable by calling my processPoint method.

```java  
var firstPoint = coords.get(0);
processPoint(firstPoint[0], firstPoint[1], p1);
```

First, I'll create a local variable called firstPoint, 
and assign it the first element in the coordinate list, by calling get(0), 
this gives me the first coordinate pair for the River location data. 
Then I'll call processPoint, passing the first double in the firstPoint array, 
and the second double, as well as the variable p1, my lambda expression variable. 
And running that:

```java  
---(same)
[47.216, -95.2348]
[29.1566, -89.2495]
[35.1556, -90.0659]
[lat:47,216 lon:-95,235]
```
            
You can see the firstPoint gets printed out, using that lambda expression's formatted String. 
Next, I'll print all the points, and use the forEach method to do it. 
First, I'll print a separator line.

```java  
System.out.println("-------");
coords.forEach(s -> processPoint(s[0], s[1], p1));
```

Then I'll call for each on my coordinate list and pass it a new lambda. 
We know that it takes a single parameter, and in turn this will call processPoint method, 
passing the first and second elements in the array, 
and also the p1 variable I set up earlier. 
Now If I run this:

```java  
---(same)
-------
[lat:47,216 lon:-95,235]
[lat:29,157 lon:-89,250]
[lat:35,156 lon:-90,066]
```

I get all 3 of my points printed out, using my custom formatting. 
I could replace p1 in this expression with the actual lambda expression 
that I assigned to that variable. 
Let me show you that.

```java  
coords.forEach(s -> processPoint(s[0], s[1], (lat, lng) -> System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lng)));
```

First I'll copy and past that the last statement, 
adding a new line after the first 2 args. 
Then I'll go where I've created p1 local variable, 
and copy everything after the equal sign, after **p1 =** on the line, 
and the next, except the ending semicolon. 
I'll go down and replace _p1_, in the last statement with that. 
Here, you can see I've really got nested lambda expressions, 
which may not have been obvious, when I was using a lambda expression local variable. 
Now, this code is getting a bit ugly really, for anyone trying to read it, 
but it works the same. 
If I run that, I get my three coordinates printed yet again. 
Ok, so this covers the **Consumer** and **BiConsumer** interfaces.

In addition to these 2, there are various special variations of other Consumer 
category type interfaces. 
For example, some let you pass a primitive as an argument. 
I won't cover all the variations of all these built-in interfaces here,
because that would take a long time, and wouldn't really add a lot of value. 
Many are for very specific cases, and not used quite as often, 
but as the course progresses, you'll be seeing me use them where appropriate. 
Moving on form the Consumer, I now want to look at the Predicate interface,
which is the second category of interfaces in this package.
</div>

### [Predicate Interface]()
<div align="justify">

The predicate interfaces take one or two arguments, 
and always return a boolean value. 
They are used to test a condition, and if the condition is true, 
some operation will be performed.

| Interface Name | Method Signature       |
|----------------|------------------------|
| Predicate      | boolean test(T t)      |
| BiPredicate    | boolean test(T t, U u) |

```java
s -> s.equalsIgnoreCase("Hello")
```

In this example, the expression takes a String, 
and tests if it's equal to the literal text, 
**Hello** here, ignoring case, so it returns either true or false.

```java
list.removeIf(s -> s.equalsIgnoreCase("bravo"));
list.forEach(s -> System.out.println(s));
```
            
Getting back to our code, I want to review another method on the **List** interface, 
that I haven't yet covered, again because you needed to understand some basics about lambdas first. 
This is a method called **removeIf**, which takes a predicate type as an argument. 
I want to use my list of nato alphabet strings to demonstrate this method, 
so I'll invoke **removeIf** on **list**, and pass a lambda expression. 
The lambda has one parameter, like a _consumer_, and in this case, 
it will be inferred to be a string, because my list is made up of strings. 
I'll follow that statement with a _forEach_ call to print my list elements. 
Can you guess what the results will be by looking at this lambda expression? 
I'm calling _removeIf_ and passing it an expression that says **s = bravo**, 
ignoring the case. 
If I run that:

```java
---(same)
alpha
charlie
delta
```

You can see that I'e successfully removed bravo from my list. 
This occurred because if the lambda expression that gets passed to **removeIf**, 
evaluates to true for any element in the list, it gets removed. 
That's pretty slick. 
This method removes any elements that match that predicate code, 
meaning it can remove zero to all elements in the list, 
depending on the expression used.

```java
list.addAll(List.of("echo", "easy", "earnest"));
list.forEach(s -> System.out.println(s));
```

I'll add a couple more elements to this list, just some strings starting with e, so echo, easy, and earnest. And
I'll print my list out. And running that:

```java
---(same)
alpha
charlie
delta
echo
easy
earnest
```
                
You can see these strings printed out. 
Next, I want to use this same _removeIf_ method, 
but this time to remove any string that starts with _e_, and _a_.

```java  
System.out.println("-------");
list.removeIf(s -> s.startsWith("ea"));
list.forEach(s -> System.out.println(s));
```

I'll first add a line separator to make the output to read easier 
than I'll invoke **list.removeIf**, with a lambda that uses the string method, 
starts with, passing that a string value of _EA_. 
And again, I'll print the elements in the list out.
And running that:

```java  
-------
alpha
charlie
delta
echo
```
                
You can see that both easy and earnest were removed from the list 
with a single line of code. 
Now, let's see what this _removeIf_ method is really doing under the covers. 
I'll use _ctrl_ click on that method name, 
and that will bring up the **Collection** interface class at the default method, 
_removeIf_. 
The method argument is of type **Predicate**, and called filter. 
It uses an iterator type to loop through all the elements, 
and that calls the test method on the filter instance 
to determine if an element should be removed. 
This is a good reminder that an iterator allows the removal of an element being iterated over, 
as this code demonstrates and which I covered in an earlier lecture. 
You'll learn more and be able to review many of the concepts 
I've covered in real-world scenarios if you make it a practice 
to see how Java implements its own code. 
This ability to see Java's source code is a great learning tool. 
You can imagine how many ways you can customize expressions you pass. 
This method, by using a functional predicate interface, 
gives us straightforward customizable ways to remove elements from a list. 
Those are real-world usages of both the consumer and predicate functional interfaces.
The next category of interfaces I want to cover is the Function category.
</div>

### [Function Interface]()
<div align="justify">

Below, I'm showing four of the most common interfaces in this category.

| Interface Name       | Method Signature   | Interface Name       | Method Signature     |
|----------------------|--------------------|----------------------|----------------------|
| Function <T, R>      | R apply (T t)      | UnaryOperator < T >  | T apply (T t)        |
| BiFunction <T, U, R> | R apply (T t, U u) | BinaryOperator < T > | T apply (T t1, T t2) |

Each has a return type, shown here as either **T**, or **R**, 
which stands for a result, meaning a result is expected for any of these. 
In addition to **Function** and **BiFunction**, 
there is also **UnaryOperator** and **BinaryOperator**. 
You can think of the **UnaryOperator** as a **Function** Interface, 
but where the argument type is the same as the result type. 
The **BinaryOperator** is a **BiFunction** interface, 
where both arguments have the same type, as does the result, 
which is why the result is shown as _T_, and not _R_. 
This reminds us that the result will be the same type 
as the arguments to the methods. 
I've also included the type parameters with each interface name above, 
because I wanted you to see that the result, for a **Function** or **BiFunction**, 
is declared as the last type argument. 
For **UnaryOperator** and **BinaryOperator**, 
there is only one type argument declared, 
because the types of the arguments and results will be the same. 
Below, I'm showing an example of a lambda expression 
which targets a **Function** interface.

| Example                | Function Method | Variable Declaration for this example |
|------------------------|-----------------|---------------------------------------|
| s &rarr; s.split(","); | R apply(T t)    | Function < String, String[]> f1;      |

This lambda expression takes a String, _s_, and splits that String on commas, 
returning an array of String. 
In this case, the argument type, _T_, is a String, and the result, _R_, is an array of String. 
To demonstrate how to declare a variable of this type, 
I'm showing a variable declaration as well, for this specific example. 
So let's get back to the code, and use some of these interfaces.

```java  
System.out.println("-------");
coords.forEach(s -> processPoint(s[0], s[1], p1));
coords.forEach(s -> processPoint(s[0], s[1],(lat, lng) ->System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lng)));

list.removeIf(s -> s.equalsIgnoreCase("bravo"));
list.forEach(s -> System.out.println(s));
```

There are two useful methods, 1 on List, 1 one on the java.util.Arrays class, 
I haven't yet reviewed which I'll cover now.
As with the _forEach_ and _removeIf_ methods, 
it didn't make sense to talk about them before this 
without understanding interfaces and lambdas. 
I'll use these as examples of two different interfaces in this **Function** category.
The first method is on **List**, and it's called _replaceAll_. 
It takes a **UnaryOperator**:

```java  
s -> s.charAt(0) + " - " + s.toUpperCase()
```
                    
As an argument. 
Can you guess why? 
A **UnaryOperator** takes one argument and returns a result 
which has the same type as the argument. 
By executing this kind of method, we're guaranteed to get back 
the same type we pass to it, so we can pass an array element, 
and get a transformed array element back, 
which replaces the current value in the specified array position. 

```java  
list.replaceAll(s -> s.charAt(0) + " - " + s.toUpperCase());
System.out.println("-------");
list.forEach(s -> System.out.println(s));
```

I'll again use my NATO Alphabet list for this example. 
I'll execute the replace all method on a list, and pass it a lambda expression, 
this one also takes a single parameter, so I'll make that s for string. 
I'll execute the charAt method on that, concatenate a dash,
then execute to UpperCase on s, also adding that to the result. 
I'll print a separator line, and then print my list elements. 
Running that:

```java  
-------
a - ALPHA
c - CHARLİE
d - DELTA
e - ECHO
```

You can see a is for Alpha. 
You'll remember I removed BRAVO earlier, 
and so I have c - CHARLIE, d - DELTA, and e - ECHO.
With one statement, I can update every element in the array 
with a formula I pass directly to the _replaceAll_ method. 
Again, I want to look at the implementation of this _replaceAll_ method, 
by _ctrl_ clicking on it in my code. 
This method uses ListIterator, because for each element, it's going to call the set method, 
replacing the current element in the iterator with a different value, 
the value that gets returned from the apply method. 
This should remind you that the ListIterator gives us this option, 
meaning using a set which the Iterator by itself doesn't, 
because it only supports removing elements. 
I hope some of these examples are starting to capture your attention 
and generate enthusiasm for using lambdas. 
They'll ultimately save you from wiring a lot of unnecessary code. 

```java  
String[] emptyStrings = new String[10];
System.out.println(Arrays.toString(emptyStrings));
Arrays.fill(emptyStrings,"");
System.out.println(Arrays.toString(emptyStrings));
```

This one is similar to the _list.replaceAll_ method, 
but it takes an IntFunction. 
What's an IntFunction? 
It's a function interface that has the apply method with an int primitive argument. 
And it returns any type, but when executed as part of _setAll_, 
the type will match the type of the array element. 
I'll create a new array of strings first, and print the elements out, 
using two string methods on Arrays. 
Here, I'm just creating an array of 10 strings, printing them out. 
Hopefully you'll remember, this just gives us an array of 10 null references, 
and you can see that when I run this code.

```java  
---(same)
[, , , , , , , , , ]
```

```java  
Arrays.fill(emptyStrings,"");
System.out.println(Arrays.toString(emptyStrings));
```

Next, I'll use the _fill_ method, which I have covered previously, 
and it just takes a single string, and sets each
element to that string. 
This time I'll fill the array with empty strings, 
and again print that out.

```java  
Arrays.setAll(emptyStrings, (i) -> "" + (i + 1) + ". ");
System.out.println(Arrays.toString(emptyStrings));
```

And now you can see, I don't have null references anymore. 
But even better than the fill method, there's a _setAll_ method 
that lets us use a formula to populate each element. 
This interface is different from the one in the **list.replaceAll** method, 
because instead of having access to each element, 
we actually have access to each index value in the array, 
so the parameter is an integer representing the current index of the array element.
I'll invoke the _setAll_ method on the Arrays class, 
first passing it the emptyStrings array, 
and then I'll include a lambda expression. 
The parameter is _i_, which traditionally is the way to refer 
to an array index in a loop, and on the right side of the arrow token, 
I'm setting up a string that includes the number, _i + 1_, 
and a period _._ 
Running that:

```java  
---(same)
[1. , 2. , 3. , 4. , 5. , 6. , 7. , 8. , 9. , 10. ]
```
            
My array elements now include numbers in their Strings. 
Again, I'll control click the _setAll_ method. 
That opens the Arrays class, and you can see the _setAll_ method. 
You can see it takes an array, and this second parameter, 
IntFunction type, called generator. 
Now, instead of an enhanced for loop or an iterator, 
it's using a traditional for loop shown there,
iterating over every element in the array. 
It then sets each array element to the result of the apply method on the generator. 
For my code, this is the lambda expression 
which used index to populate each array element with a number, 
starting with one, not 0. 
Ok, let's get back to the main method, 
and explore what else we can do in that lambda expression a little bit.

```java  
Arrays.setAll(emptyStrings, (i) -> "" + (i + 1) + ". "
        + switch (i) {
                case 0 -> "one";
                case 1 -> "two";
                case 2 -> "three";
                default -> "";
            }
        );
System.out.println(Arrays.toString(emptyStrings));
```

Because Java now gives us switch expressions, 
I can use a switch expression directly in my lambda expression. 
I'll copy those last two statements and paste them right below. 
And I'll add a new line before the closing parentheses 
on the **Arrays.setAll** statement. 
Now, I'll add a switch expression, a simple one, as part of 
this lambda expression single statement. 
I'll add plus then switch with _i_. 
For case 0, I want to add the text **one**, 
case 1, I'll add the text **two**, 
case 2 gets **three**, 
and then the default will just return an empty string. 
And running that code:

```java  
[1. one, 2. two, 3. three, 4. , 5. , 6. , 7. , 8. , 9. , 10. ]
```

You can see I'm able to fill the array with values, 
using a switch expression in my lambda. 
As with anything, you'll want to achieve a balance 
between the readability of code and its succinctness. 
This lambda could be a candidate for a method somewhere. 
Here, I just wanted to show you 
that there's really no limit to what you can do 
when a method supports these functional interface types.
Ok, those are two useful methods you're sure to have opportunities to use, 
and two examples of interfaces that fall into the function category.
This leaves us with one more major interface category left to discuss, 
and that's the Supplier.
</div>

### [Supplier Interface]()
<div align="justify">

The supplier interface takes no arguments but returns an instance of some type, _T_.

| Interface Name | Method Signature |
|----------------|------------------|
| Supplier       | T get()          |

You can think of this as kind of like a factory method code. 
It will produce an instance of some object. 
However, this doesn't have to be a new or distinct result returned. 
In the example, I'm showing you below:

```java  
() -> random.nextInt(1, 100);
```

I'm using the Random class to generate a random Integer. 
This method takes no argument, but lambda expressions can use final 
or effectively final variables in their expressions, which I'm demonstrating here. 
The variable random is an example of a variable from the enclosing code. 
Let's get back to the code, and I'll demonstrate code similar to this in my Main method, 
but first I'll create a method on the main class,

```java  
public static String[] randomlySelectedValues(int count, String[] values, Supplier<Integer> s) {

    String[] selectedValues = new String[count];
    for (int i = 0; i < count; i++) {
        selectedValues[i] = values[s.get()];
    }
    return selectedValues;
}
```

In previous lectures, I used an instance of random 
to pick randomly generated names from an array of names. 
That's what I want to do here, but using a Supplier lambda expression. 
This method will return an array of String, and I'll call it randomly selected values. 
It has three parameters, an integer count, an array of String 
I'll call values, and a **Supplier** interface type, that takes an Integer. 
This method is going to return an Array of String 
with the same number of elements that are passed as the first argument. 
It'll use the second argument to get a value randomly picked from that array. 
It will use a **Supplier** interface to get an integer 
to use as the index to pick the name.
Now, let me add the code that does all **selectedValues** is another array of string 
and will have count elements. 
I'll loop from 0 to count—1. 
I'll set each element in my new array 
to a corresponding element from the array passed to this method.
However, I'll get the index from the supplier argument, 
the lambda expression in other words, that gets passed. 
Finally, I return my new array. 
Back to the bottom of the main method,

```java  
String[] names = {"Ann", "Bob", "Carol", "David", "Ed", "Fred"};
String[] randomList = randomlySelectedValues(15, names, () -> new Random().nextInt(0, names.length));
System.out.println(Arrays.toString(randomList));
```

I'll now set up my name array, with names. 
I'll set up a local variable, an array of String. 
And call that random List. 
I'll assign the result of calling my new method, randomly selected values. 
I want an array of 15 strings back, so that's my first argument, 15, 
and I'll pass my list of valid name entries, and finally, 
I want to pass a lambda expression. 
This lambda has no arguments, so I need a set of empty parentheses 
before the arrow token. 
After the token, I'm going to invoke nextInt on a new random instance, 
passing it 0 as the first possible value, and _names.length_ 
as the exclusive ending boundary, so this will give me an integer 
between 0 and 5. 
And I'll print my list of random names after. 
And if I run that:

```java  
---(same)
[Fred, Carol, Fred, Ann, Ed, Ed, Fred, Ann, Fred, Ann, Fred, Carol, Fred, Fred, David]
```

I'll get an Array of Strings that contains 15 names, randomly selected from my name array.
I want you to look at this lambda expression closely.

```java  
() -> new Random().nextInt(0, names.length)
```
                    
This is the first time I've showed you an example that had no argument at all. 
In this case, the parentheses, an empty set of parentheses, are required. 
In a lambda expression, on the left side of the arrow token, 
you can have empty parentheses like we do here, which means no arguments. 
You can have a single variable name, which means there is a single argument.
You can have a typed single variable, but that needs to be in parentheses. 
And multiple arguments also need to be enclosed in parentheses.

<table>
    <tr>
        <th>Arguments in Functional Method</th>
        <th>Valid Lambda Syntax</th>
    </tr>
    <tr>
        <td>None</td>
        <td align="center">( ) &rarr; statement </td>
    </tr>
    <tr>
        <td rowspan="5">One</td>
    </tr>
    <tr align="center">s &rarr; statement</tr>
    <tr align="center">(s) &rarr; statement</tr>
    <tr align="center">(var s) &rarr; statement</tr>
    <tr align="center">(String s) &rarr; statement</tr>
    <td rowspan="4">Two</td>
    <tr align="center">(s, t) &rarr; statement</tr>
    <tr align="center">(var s, var t) &rarr; statement</tr>
    <tr align="center">(String s, List t) &rarr; statement</tr>
</table> 

* When using var, all args must use var
* When specifying explicit types, all args must specify explicit types 

This table shows the many varieties of declaring a parameter type in a lambda expression. 
Parentheses are required in all but the one case, 
where the functional method has a single argument, 
and you don't specify a **type**, or use **var**. 
When using var as the **type**, every argument must use **var**. 
When specifying explicit types, every argument must include a specific **type**. 
This ends our introduction to Java's functional interfaces. 
There are other interfaces which aren't in the **java.util.function** package, 
that are considered functional interfaces, one of which is **Comparator**, 
which we've already covered pretty thoroughly.
We'll learn about others as we progress through the course, 
which are specific to other topics. 
These four sets of interfaces alone cover a lot of ground, 
and provide endless fertile ground for usage.
</div>

## [c. Lambda Mini Challenges](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_10_LambdaExpressionsAndFunctionalInterfaces/Course06_LambdaMiniChallenges/README.md#lambda-mini-challenges)
<div align="justify">
This challenge lecture is going to be a little different, 
and consist of several small tasks, to help you really practice creating 
and using lambda expressions. 
I'll assign a task for you to try on your own, 
and then we can solve it together. 
Then I'll present the next one.
</div>

## [d. Lambda Expression Challenge](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_10_LambdaExpressionsAndFunctionalInterfaces/Course07_LambdaExpressionChallenge/README.md#lambda-expression-challenge)
<div align="justify">

This challenge is to exercise your skills with Arrays, ArrayLists,
and the methods on these, which are targets
for lambda expressions. 
First, I want you to create an array of Strings, 
which is populated with first names, in mixed case. 
Include at least one name, which is spelled the same backwards, and forwards, 
like Bob, or Anna. 
Use _Arrays.setAll_, or _List.replaceAll_, to change the values in this array. 
If you use List methods, you'll need _a list backed by the array_, 
so that changes get made to the initial array. 
In other words, I don't want you to make a copy of the array elements 
for the first part of this challenge.

Using one of those two methods, perform the following functions 
on the elements in the array, with appropriate lambda expressions.

* Transform names to all uppercases.
* Add a randomly generated middle initial, and include a period.
* Add a last name that is the reverse of the first name.

Print your array or the array elements, after each change, 
using the forEach method, at least once. 
Finally, create a new modifiable ArrayList from your name array, 
removing any names where the last name equals the first name. 
Use _removeIf_ with a lambda expression to do this last operation. 
You can create helper methods as needed to facilitate some of this functionality. 
You can use single line, or multi-line lambda expressions. 
Ignore IntelliJ's warnings and hints which prompt you 
to replace any lambda expressions with method references for now. 
We'll be reviewing method references in the next lecture.
</div>

## [e. Method References]()
<div align="justify">

In some of the lambda expressions I've used so far, 
I've written an operation or expression. 
In others, I've simply referenced other named methods 
like **System.out.println**, for example. 
Java gives us an alternative syntax to use for this second kind of lambda, 
that uses named methods. 
These are called method references. 
These provide a more compact, easier-to-read lambda expression 
for methods that are already defined in a class. 
For the last couple of lectures, for example, 
I've been ignoring Intelli-J's warnings and hints 
whenever I've used **System.out.println** in a lambda expression.
That's because it can be replaced with a method reference.

```java  
List<String> list = new ArrayList<>(List.of( "Anna", "Bob", "Chuck", "Dave"));
list.forEach(s -> System.out.println(s));
```

I'll set up a simple list of names, so List, type String, named list, 
equals a new ArrayList, and I'll pass a List of Strings with values Anna, 
Bob, Chuck, and Dave. 
And as you've seen many times, I'll execute the _forEach_ method on **list**, 
and pass the lambda expression that uses **System.out.println**. 
You've probably noticed, IntelliJ's been highlighting this lambda expression 
each time I've used it. 
If I hover over that, it says _Lambda can be replaced with method reference_,
and I can select that link, so I'll do that.

```java  
list.forEach(System.out::println);
```

That replaces my code with a different kind of expression. 
This is probably going to be the most commonly used method reference you'll see.
At first glance, it's not really obvious why a method reference has this syntax.

| Lambda Expression              | Method Reference    |
|--------------------------------|---------------------|
| s &rarr; System.out.println(s) | System.out::println |

A method reference abstracts the lambda expression even further, 
eliminating the need to declare formal parameters.
We also don't have to pass arguments to the method in question, in this case println. 
A method reference has double colons, between the qualifying type, or object, 
and the method name. 
In this example of a Consumer interface, not only is the method inferred, 
but the parameters are as well. 
Does this mean you can use any method in method references?

Methods which can be used, are based on the context of the lambda expression. 
This means the method reference is again dependent on the targeted interface's method. 
You can reference a static method in a class. 
You can reference an instance method from either an instance external to the expression, 
or an instance passed as one of the arguments.
Or you can reference a constructor by using new as the method. 
Method references can be used to increase the readability of your code. 
The **System.out.println** method reference is an example of an instance method, 
called on an external instance. 
The instance is the _PrintStream_ object that gets returned from the _System.out_ method.
</div>

### Deferred Method Invocation
<div align="justify">

When you create variables that are lambda expressions or method references,
it's important to remember that the code isn't invoked at that point.
The statement or code block gets invoked at the point in the code
that the targeted functional method is called.
</div>

<div align="justify">

I'm going to add a method similar to the one I included in the lambda expressions lectures,
called calculator.

```java  
private static <T> void calculator(BinaryOperator<T> function, T value1, T value2) {
    T result = function.apply(value1, value2);
    System.out.println("Result of operation: " + result);
}
```

I'll make it private and static, and generic with a type T.
It won't have a return type, so void, and I'm going
to call it calculator with the first parameter a BinaryOperator,
and 2 additional parameters, _value1_ and _value2_, also both type _T_.
As I did before, inside this method, I'll call the Binary Operator's functional method,
apply, on function, and this method will print the result, instead of returning it.
In the main method,

```java  
calculator((a, b) -> a + b, 10, 25);
```

I'll set up a call to that method, using a lambda expression,
which I also used previously that has two parameters,
a and b, and returns a + b, and I'll also pass 10 and 25 to that method.
Again, IntelliJ has highlighted part of this lambda expression.
Hovering over that, you can see it still says I can replace this
with a method reference, so let me do that.

```java  
calculator(Integer::sum, 10, 25);
```

That gets replaced with the method reference, Integer, colon-colon, sum.
I don't think we talked about the _sum_ method,
but it's simply a static method on the Integer wrapper class,
that returns the sum of two integers, which obviously replaces the plus operator,
when the operands are integers.
You may or may not agree that this code is a little simpler to read
than trying to look at the lambda expression itself.
This is the second type of method reference,
that uses a static method on a class with the class type as the reference on the left.
Let's try that same exercise with decimal numbers.

```java  
calculator((a, b) -> a + b, 2.5, 7.5);
```

I'll call calculator with the same lambda expression,
but pass some decimal values instead, 2.5 and 7.5.
And again, IntelliJ is prompting me to turn this into a method reference, so I will:

```java  
calculator(Double::sum, 2.5, 7.5);
```

You can see it using the static method on Double this time,
and this still produces 10.0 from the calculation.
Before we look at the second type of instance method reference,
which can be a little confusing, I want to look at using a method reference
to instantiate a new instance of a class.
I'll create a class above Main, called Plain old.

```java  
class PlainOld {

    private static int last_id = 1;
    private int id;
    public PlainOld() {
        id = PlainOld.last_id++;
        System.out.println("Creating a PlainOld Object: id = " + id);
    }
}
```

I'll set up a public constructor for that, using IntelliJ generation tool to do that.
Print creating a Plain Old Object.
I now want to add a private static int variable that keeps track of the last id.
I'll initialize that to 1.
Create a field o this class ID.
I'll set that field in the constructor, assigning the Plain Old last id to id, and
incrementing the last id with a post-decrement operator.
I'll also add id to the statement that gets printed there.
Remember there's only one copy of last id, shared by all instances,
so this code that is incrementing the shared value.

```java  
Supplier<PlainOld> reference1 = () -> new PlainOld();
```

In the main code, I want to create a local variable.
Its type will be a **Supplier**.
You'll remember this is a functional interface,
that has the functional method, _get_, that takes no arguments,
but returns an instance.
I'll set this up, using a lambda expression that returns a new instance of a Plain Old class.
Here, my variable is a Supplier, and I've
used Plain Old as a type argument.
I've assigned it a lambda expression with no arguments, which means I need to use
the empty parentheses there.
And with the expression itself to the right of the array token,
this just calls new keyword with the class name, and empty parentheses.
We know this calls the empty constructor, and will return a new instance
of the PlainOld object.
Here again, IntelliJ is indicating that I can make this code better,
saying it can be replaced with a method reference, so I'll do that:

```java  
Supplier<PlainOld> reference1 = PlainOld::new;
```

This method reference is a special type, a constructor method reference.
If I run this code like this, nothing happens though.
I didn't see my constructor executed or see a statement that a PlainOld object was created.
Why not?
Well, a method reference, like lambda expression variables, is sort of like a method declaration.
It's created and then used at a later time.
It's not immediately executed at the time it's declared, it's deferred,
and the code snippet gets passed around.

```java  
PlainOld newPojo = reference1.get();
```

How can I execute this method reference then?
I could simply execute the get method on the variable,
so I can set up another PlainOld variable, named new Pojo, and assign it to _reference1.get_.
If I run that now:

```java  
---(same)
Creating a PlainOld Object: id = 1
```

I can see that the constructor was executed.
Why would you ever do this?
Isn't it just simpler to call new on the PlainOld class, as we would any new instance?
Yes, in this case it would be.
Later we'll learn methods for creating a lot of instances at once.
For now, I'll set up my own method that does a little bit of that.

```java  
private static PlainOld[] seedArray(Supplier<PlainOld> reference, int count) {

    PlainOld[] array = new PlainOld[count];
    Arrays.setAll(array, i -> reference.get());
    return array;
}
```

I'll make this private static, and it'll return an array of PlainOld instances.
I'll call it a seed array, and the parameters will be Supplier called reference,
and an int called count.
Now, I'll set up an array variable in this method,
and assign it new PlainOld with the count in brackets.
I'll call _Arrays.setAll_, passing it my array, and a lambda expression,
where i is the parameter, but instead of doing anything with i, in the lambda,
I'll just call _reference.get_.
I'll then return the array from this method.
This code will assign every element in the array,
whatever is the result of executing get for the lambda expression passed to this method.
We've said this is going to be a PlainOld class,
by making the array that type.
Let me execute this from the main method, using my method reference as the Supplier variable.

```java  
System.out.println("Getting array");
PlainOld[] pojo1 = seedArray(PlainOld::new, 10);
```

First I'll print out a descriptive statement, getting the array,
and then I'll assign another local variable, pojo1,
an array of PlainOld instances,
and I'll assign that to the result of a call to this new method.
I'll use a method reference as the first argument,
and the number 10 as the second argument.
And running that:

```java  
Getting array
Creating a PlainOld Object: id = 2
Creating a PlainOld Object: id = 3
Creating a PlainOld Object: id = 4
Creating a PlainOld Object: id = 5
Creating a PlainOld Object: id = 6
Creating a PlainOld Object: id = 7
Creating a PlainOld Object: id = 8
Creating a PlainOld Object: id = 9
Creating a PlainOld Object: id = 10
Creating a PlainOld Object: id = 11
```

You can see I've created 10 of these objects now, and put them in an array.
We'll be revisiting suppliers and additional use cases for using them
when we get to streams.
</div>

<div align="justify">

So far, we talked about three of the four types of method references. 
These were straight forward, but this last one can be harder to grasp. 
First, I want to talk about some terminology 
that will hopefully help you understand this last type of reference. 
A Type Reference refers to a class name, an interface name, an enum name, 
or a record name. 
Remember that static methods are usually called using **Type References**, 
but can also be called by instances in our code. 
This is NOT true, however, for method references. 
Static methods, in method references and lambda expressions, 
must be invoked using a reference type only. 
There are two ways to call an instance method.

The first is when you refer to the method with an instance derived from the enclosing code. 
This instance is declared outside the method reference. 
The **System.out::println** method reference is an example. 
You'll find that some websites call this instance a bounded receiver, 
and I actually like that terminology as a differentiator. 
A **Bounded Receiver** is an instance derived from the enclosing code, 
used in the lambda expression, on which the method will be invoked.

The second way is where the confusion starts. 
The instance used to invoke the method will be the first argument passed 
to the lambda expression or method reference when executed. 
This is known in some places as the **Unbounded Receiver**. 
It gets dynamically bound to the first argument, which is passed to the lambda expression, 
when the method is executed. 
Unfortunately, this looks an awful lot like a static method reference, 
using a reference type. 
This means there are two method references that resemble each other, 
but have _two very different meanings_.

The first actually does call a static method, and uses a reference type to do it. 
We saw this earlier when we used the sum method on the Integer wrapper class.

```java  
Integer::sum
```
                                                    
This is a Type Reference (Integer is the type), which will invoke a static method. 
This is easy to understand. 
But there is another, which you'll see when we start working 
with String method references in particular. 
Here, I show a method reference for the concat method on String.

```java  
String::concat
```

Now, we know by now I hope that the concat method isn't a static method on String. 
Why is this method reference even valid?
We could never call concat from the String class directly 
because it needs to be called on a specific instance.
As I just said that, instance methods can't be called using Reference Types. 
But the example has shown right above, **String::concat**, is a special syntax, 
when it's declared in the right context, meaning when it's associated to the
right type of interface. 
**String::concat** is valid when we use a method reference 
in the context of an **unbounded receiver**.

**NOTE**: Remember, the unbounded receiver means, the first argument becomes the instance used, 
on which the method gets invoked.

Any method reference that uses **String::concat** must be 
in the context of a two-parameter functional method. 
The first parameter is the String instance on which the _concat_ method gets invoked, 
and the second argument is the String argument passed to the _concat_ method. 
This will hopefully make more sense when you see it in code.

```java  
calculator((s1, s2) -> s1 + s2, "Hello ", "World");
```

I'm going to invoke my calculator method again, passing a different lambda expression. 
This lambda will have two parameters, _s1_ and _s2_, and I'll use the **+** operator on them, 
and I'll pass the strings, _hello_ and _world_, as the last two parameters to the calculator method.
This looks like a simple addition operation, but it's not 
because we're operating on Strings. 
The **+** sign, when used with String operands, is a concatenation operator. 
Running this code:

```java  
Result of operation: Hello World
```
            
You can see that we get **Hello World** as the result of this.
I'm going to change my lambda expression to use the _concat_ method, 
instead of the **+** sign operator. 

```java  
calculator((s1, s2) -> s1.concat(s2), "Hello ", "World");
```

And executing that,

```java  
Result of operation: Hello World
```

I get the same results. 
But once again, IntelliJ is requesting my attention, and sure enough, 
it's saying _this could be a method reference_, so let me take that suggestion.

```java  
calculator(String::concat, "Hello ", "World");
```

But once again, IntelliJ is requesting my attention, and sure enough, 
it's saying _this could be a method reference_, so let me take that suggestion. 
Does this method reference give you pause? 
Maybe not, since I just gave you a few notes on this topic, but wait, 
how does this work again? 
Notice that there are two parameters on the right side of the arrow token 
for the version of _(s1, s2) -> s1.concat(s2)_, 
the _concat_ method is invoked on the first argument, _s1_.
The second argument, _s2_, is passed to the _concat_ method. 
**String::concat** is exactly what the method reference will do implicitly, 
because it's declared in the context of a 2-parameter function method. 
How's that again? 

```java  
private static <T> void calculator(BinaryOperator<T> function, T value1, T value2) {

    T result = function.apply(value1, value2);
    System.out.println("Result of operation: " + result);
}
```

The method calculator has as its first argument a **BinaryOperator**. 
A BinaryOperator has a method, called **apply**, 
that takes two arguments of the same type, 
and returns a result, also of that same type. 
Let me make this really clear, by setting up a local with the type BinaryOperator, 
using String as its type argument.

```java  
BinaryOperator<String> b1 = (s1, s2) -> s1.concat(s2);
BiFunction<String, String, String> b2 = (s1, s2) -> s1.concat(s2);
```

I'll assign it the same lambda expression I was using, _s1.concat_, passing it _s2_. 
I could also make this a **BiFunction** interface, 
using String for all three type arguments (which is all the BinaryOperator really does).
I'll copy that last statement and paste it, changing BinaryOperator to BiFunction, 
and I'll have to declare three types for a BiFunction.
The first 2 are the method parameters, strings, and the last is the result type, 
so String again. 
I want to click on the gutter icon next to the last statement here, 
on the **BiFunction** statement line, and show you the method, **apply;** 
that has two parameters. 
The two parameters in the targeted method are the very reason 
we can use a method reference like **String::concat**. 
Going back to the main method, I'll change both of these method references, 
using IntelliJ's recommendation.

```java  
BinaryOperator<String> b1 = String::concat;
BiFunction<String, String, String> b2 = String::concat;
```

What happens if I try to use an interface that has a method without two arguments?

```java  
UnaryOperator<String> u1 = String::concat;
```

IntelliJ is giving me an error there, on concat, 
_Non-static method cannot be referenced from a static context_. 
Are you confused? 
Don't worry, this is definitely confusing for a while. 
If I click the gutter icon next to the UnaryOperator line, I can see the method. 
It's actually showing me the method on **Function**, 
which ultimately **UnaryOperator** is, 
but has the constraint that the returned type equals the method parameter type. 
And you can see the method is **apply**, it has only one argument. 
Only one argument is available to the method reference, 
assigned to a variable of this type. 
Well, with only one argument available, calling concat on the first argument,
would leave us with no additional argument to pass to the concat method, 
so it doesn't make any sense to use concat for this interface. 
Let me try to type this out a lambda expression, 
so I'll pop back to the Main.java file.

```java  
//UnaryOperator<String> u1 = String::concat;
UnaryOperator<String> u1 = (s1, s2) -> s1.concat(s2);
```
                            
This is an invalid lambda expression, and hopefully this is easier to understand. 
For an **UnaryOperator**, there's no 2 available to it, 
so we can't set this up s2, so I'll remove s2.

```java  
//UnaryOperator<String> u1 = String::concat;
//UnaryOperator<String> u1 = (s1, s2) -> s1.concat(s2);
UnaryOperator<String> u1 = (s1) -> s1.concat(s2);
```

And now you can see, the lambda expression has no idea what the variable s2 is. 
This code doesn't work out of the box like this. 
Java figures all of this out, and won't let us use **String::concat**, 
for the Unary Operator. 
But I can call a method on String that doesn't take a parameter, 
for example, the instance method, toUpperCase.
Let me do that now,

```java  
//UnaryOperator<String> u1 = String::concat;
//UnaryOperator<String> u1 = (s1, s2) -> s1.concat(s2);
//UnaryOperator<String> u1 = (s1) -> s1.concat(s2);
UnaryOperator<String> u1 = (s1) -> s1.toUpperCase();
```

And that compiles, and I can now change that to a method reference, 
so I'll do that. 
**String::toUpperCase** again, the implicit argument is used 
to invoke the toUpperCase method on. 
The good news is that Java and IntelliJ figure most of this out for us. 
The bad news is that this may be confusing for a while, 
until you get the app froze of this.

```java  
System.out.println(b1.apply("Hello ","World"));
System.out.println(b2.apply("Hello ","World"));
System.out.println(u1.apply("Hello "));
```

I want to execute all of these method references next, 
and I'll call the apply method on each of these three variables,
passing each to _println_. 
For _b1_ and _b2_, I'll pass two strings, _Hello_ and _World_. 
For _u1_, I'll just pass one string, _Hello_. 
And executing this:

```java  
---(same)
Hello World
Hello World
HELLO
```

You can see the three output statements at the end. 
The first 2 concatenated _Hello_ and _World_, 
and the second changed _hello_ to uppercase.

```java  
String result = "Hello".transform(u1);
System.out.println("Result = " + result);
```

Now, I want to introduce you to another method on String, 
one I haven't shared with you yet.
This method is named transform, and takes first a Function with a String type, 
as an argument.
And it returns an object.
Let's use this, to test a method reference or two. 
First, I already have a **UnaryOperator** variable set up, 
which is a derivative of the Function interface. 
This means I can simply pass my variable _u1_ 
to the transform method on a string.
I'll set up a local variable, result, 
also a String and assign it the result of calling 
the _transform_ method on the string "_Hello_", 
and I'll pass my _u1_ variable to that transform method. 
The transform method isn't required to return a String. 
It does, in this case, because we've defined the variable 
to be aUnaryOperator with a String type argument.
This means String is used as the argument, 
and returns a String as well. 
Running this code:

```java  
---(same)
Result = HELLO
```

You can see I get the result with hello all in uppercase.

```java  
result = result.transform(String::toLowerCase);
System.out.println("Result = " + result);
```

I'll just pass a method reference directly executing transform on the result variable, 
and passing it **String::toLowerCase**.
This will take the result and apply this method reference, 
and make it all lowercase now, which you can see when I run it:

```java  
---(same)
Result = hello
```

You're not restricted to returning a String from this method. 
Let's try another method reference. 
First, I'll create a Function variable, _f0_, 
which will take a String and return a Boolean. 
I'll assign this, the method reference **String::isEmpty**.

```java  
Function<String, Boolean> f0 = String::isEmpty;
boolean resultBoolean = result.transform(f0);
System.out.println("Result = " + resultBoolean);
```

Next, I want a boolean variable, and I'll call this result boolean, 
and that gets assigned the result of the transform method with 
the _f0_ variable on the result string. 
You can see with this code, I'm specifying a local variable, 
a Function with two different type arguments, _String_ and _Boolean_. 
The last argument describes the type of the result. 
I could have passed the method reference directly to the transform method, 
but I wanted you to see this declaration 
because it describes the result a bit more clearly. 
Java could have inferred all of this. 
Running this code,

```java  
---(same)
Result = false
```
            
You can see the last output statement. 
That's because the string variable, _result_ wasn't empty. 
Ok, so I hope that was interesting and helpful to you. 
We're going to be using lambda expressions a lot over 
the next sections of the course, 
and method references where appropriate.

| Type                                                                      | Syntax                                                | Method Reference Example | Corresponding Lambda Expression   |
|---------------------------------------------------------------------------|-------------------------------------------------------|--------------------------|-----------------------------------|
| static method                                                             | ClassName::staticMethodName(p1, p2, ..., pn)          | Integer::sum             | (p1, p2) &rarr; p1 + p2           |
| instance method of a particular (Bounded) object                          | ContainingObject::instanceMethodName(p1, p2, ..., pn) | System.out.println       | p1 &rarr; System.out.println(p1)  |
| instance method of an arbitrary (Unbounded) object (as determined by p1)" | ContainingType[=p1]::instanceMethodName(p2, ..., pn)  | String::concat           | (p1, p2) &rarr; p1.concat(p2)     |
| constructor                                                               | ClassName::new                                        | LPAStudent::new          | ( ) &rarr; LPAStudent()           |

This chart shows the four different types of method references, 
with method reference examples, and a corresponding lambda expression.

<table>
    <th></th>
    <th>No args</th>
    <th colspan="3" align="center">One Argument</th>
    <tr>
        <td>Types of Method References</td>
        <td>Supplier</td>
        <td>Predicate</td>
        <td>Consumer</td>
        <td>Function(UnaryOperator)</td>
    </tr>
    <tr> 
        <td>Reference Type (Static)</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr><tr> 
        <td>Reference Type (Constructor)</td>
        <td>Employer::new</td>
        <td></td>
        <td>n/a</td>
        <td>Employee::new</td>
    </tr><tr> 
        <td>Bounded Retriever (Instance)</td>
        <td></td>
        <td></td>
        <td>System.out::println</td>
        <td></td>
    </tr><tr> 
        <td>Unbounded Retriever (Instance)</td>
        <td>n/a</td>
        <td>String::isEmpty</td>
        <td>List::clear</td>
        <td>String::length</td>
    </tr>
</table>

This chart shows some of the valid ways to use method references 
when assigned to different interface types. 
These interface types have no arguments in the case of a **Supplier**,
and one argument for the other interfaces.
If a cell is empty, it's not because it's not valid, 
but there are many possibilities. 
**N/a** means not applicable, so a Supplier or an interface method 
that has no arguments, can never be a target for the unbounded receiver
type of method reference.


<table>
    <th></th>
    <th colspan="3" align="center">Two Argument</th>
    <tr>
        <td>Types of Method References</td>
        <td>BiPredicate</td>
        <td>BiConsumer</td>
        <td>BiFunction(BinaryOperator)</td>
    </tr>
    <tr> 
        <td>Reference Type (Static)</td>
        <td></td>
        <td></td>
        <td>Integer::sum</td>
    </tr><tr> 
        <td>Reference Type (Constructor)</td>
        <td></td>
        <td></td>
        <td>Employee::new</td>
    </tr><tr> 
        <td>Bounded Retriever (Instance)</td>
        <td></td>
        <td>System.out::printf</td>
        <td>new Random()::nextInt</td>
    </tr><tr> 
        <td>Unbounded Retriever (Instance)</td>
        <td>String::equals</td>
        <td>List::add</td>
        <td>String::concat, String::split</td>
    </tr>
</table>

        
This chart shows some of the valid ways to use method references 
when assigned to different interface types. 
These interface types have two arguments,
and therefore it's more common to see the unbounded retriever method 
references used for these.
</div>

## [f. Method Reference Challenge]()
<div align="justify">

At the end of the last lecture, I introduced you 
to the transform method on String. 
In this challenge, I want you to explore what you can do with that method. 
First, create an array of names, in mixed case, 
as you did in the Lambda Expression Challenge. 
Create a list of Function interfaces, or alternately UnaryOperator, 
which will contain all the operations you want executed on each name in your array. 
Do something similar to what we did in the Lambda Expression challenge:

* Make each name upper case,
* Add a random middle initial,
* Add a last name, which should be the reverse of the first.

In addition to this, add some custom transformations of your own. 
Use a mix of lambda expressions and method references. 
Create a method that takes the name array, and the function list, 
and applies each function to each name, 
using the transform method on String, to do this. 
All changes should be applied to the original array. 
Make sure you explore as many transformations as you can, 
trying as many different types of method references as you can think of.
</div>


<div align="justify">


```java  

```

</div>


<div align="justify">


```java  

```

</div>