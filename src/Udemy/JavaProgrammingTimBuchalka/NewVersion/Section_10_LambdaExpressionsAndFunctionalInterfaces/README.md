# [Section-10: Lambda Expressions]()

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

| Generated Lambda Expression                         | Comparator's Abstract Method  |
|-----------------------------------------------------|-------------------------------|
| ((o1, o2) -> o1.lastName().compareTo(o2.lastName()) | int compare(T o1, T o2)       |

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

| Anonymous Class                                                                                                                                                                                                                                                                                              | Lambda Expression                                     |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------|
| new Comparator < Person > ( ) {<br/>&nbsp;&nbsp;&nbsp;&nbsp; @Override<br/>&nbsp;&nbsp;&nbsp;&nbsp; public int compare (Person o1, Person o2) {<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; return o1.lastName( ).compareTo(o2.lastName( ));<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;} <br/>} | (o1, o2) -> o1.lastName( ).compareTo(o2.lastName( )); |

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
This means the lambda expression passed, should represent code 
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
Now, let's say I want to turn this anonymous class, into a lambda expression? 
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

## [a. The Consumer Interface]()
<div align="justify">

The Consumer interface is in the _java.util.function_ package. 
It has one abstract method,  that takes a single argument, 
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

| Lambda Expression                                | Description                                                 |
|--------------------------------------------------|-------------------------------------------------------------|
| element -> System.out.println(element);          | A single parameter without a type can omit the parentheses. |
| (element) -> System.out.println(element);        | Parentheses are optional.                                   |
| (String element) -> System.out.println(element); | Parentheses required if a reference type is specified.      |
| (var element) -> System.out.println(element);    | A reference type can be var.                                |

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

| Lambda Expression                | Description                                                                                        |
|----------------------------------|----------------------------------------------------------------------------------------------------|
| (a, b) -> a + b;                 | Parentheses are always requires. Explicit types are not.                                           |
| (Integer a, Integer b) -> a + b; | If you use an explicit type for one parameter, you must use explicit types for all the parameters. |
| (var a, var b) -> a + b;         | If you use var for one parameter, you must use var for all parameters.                             |

You can't mix and match var with explicit types, or omit var from one 
or some of the parameters. 
This table shows the two rules for returning from a lambda expression.

| Lambda Expression                                                                         | Description                                                                                                                    |
|-------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|
| (a, b) -> a + b;                                                                          | When not using curly braces, the return keyword is unnecessary, and will throw a compiler error.                               |
| (a, b) -> {<br/>&nbsp;&nbsp;&nbsp; var c = a + b;<br/> &nbsp;&nbsp;&nbsp; return c;<br/>} | If you use a statement block, meaning you use the curly braces, a return is required if the function method has a return type. |
</div>


<div align="justify">


```java  

```

</div>


<div align="justify">


```java  

```

</div>


<div align="justify">


```java  

```

</div>


<div align="justify">


```java  

```

</div>


<div align="justify">


```java  

```

</div>