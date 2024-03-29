# Method Reference Challenge
<div align="justify">

I'm going to add a private static Random field 
to support different types of randomization, 
assigning it a new instance of the random class.
You've seen me do this several times now. 
Next, I want to set up the method that will execute my list of functions, 
against an array of names.

This will be a private static method, named **applyChanges**
that has a void return type. 
The first parameter will be a String array, 
and the second will be a List of typed **UnaryOperators**, 
called string functions. 
This list will consist of instances which implement 
the Unary Operator interface, and accepts Strings. 
I said in the last lecture that the transform method 
will return other values, but here I'm going to use it 
with just Strings being returned. 
Using a **UnaryOperator** is basically the same 
as using a Function with Strings as both type arguments. 
Now I'll add the main parts of this method.

```java  
private static void applyChanges(String[] names, List<UnaryOperator<String>> stringFunctions) {
    List<String> backedByArray = Arrays.asList(names);
    for (var function : stringFunctions) {
        backedByArray.replaceAll(s -> s.transform(function));
        System.out.println(Arrays.toString(names));
    }
}
```

First, I create a list backed by an array, my _names_ array. 
Then I'm looping through the list of functions 
which were passed as the second argument of this method. 
These are the operations that will be done on each name in the array.
I'm taking advantage of the _replaceAll_ method on List here, 
which takes a **UnaryOperator**, and I simply pass this a lambda expression. 
This lambda in turn calls transform on every name string, 
and executes the function that was in the list element. 
After this, I print out the array values,
and because I'm using a list backed by an array, 
my array is really changing during these transformations.
Ok, so I want to test that out, but first I need some names in an array.

```java  
public static void main(String[] args) {
    String[] names = {"Anna", "Bob", "Cameron", "Donald", "Eva", "Francis"};

    List<UnaryOperator<String>> list = new ArrayList<>(List.of(
            String::toUpperCase
    ));

    applyChanges(names, list);
}
```

I'll call my local variable _names_ and use an array initializer as names. 
Then, I need a list of functions. 
I'll set this up, so **List**, and set the type argument to **UnaryOperator**, 
which also needs a type argument, and that's String,
so this declaration looks a little ugly. 
I'll call my list as _list_, and assign it a new ArrayList, 
passing a call to the _List.Of_ method.
First, I want to make the names all uppercase, 
as I did in the lambda expression challenge, 
so what does this look like as a method reference? 
It's **String::toUpperCase**. 
This may look like a static method reference, 
but hopefully you recognize that it's not. 
It's an instance method, used on the unbounded retriever. 
In other words, the method will get called on the first argument.
And then, all I have to do is call my applyChanges method, 
passing my names array, and my list of functions. 
I'll run this and see what I get:

```java  
[ANNA, BOB, CAMERON, DONALD, EVA, FRANCİS]
```

You can see my names, they're transformed to uppercase with that method reference. 
My first operation worked successfully on my _names_ array. 
Now, I want to add a random character, as a middle initial.
I'm going to create a method, similar to the one 
I used in the lambda expression challenge, calling it _getRandomChar_.
This is almost the same method from the previous challenge. 
It uses a range of characters, specified by the user, 
to get a new random character. 
And now, I'll add a lambda expression to my list in the main method
that uses this method.

```java  
private static char getRandomChar(char startChar, char endChar) {
    return (char) random.nextInt((int) startChar, (int) endChar + 1);
}
```

I'll need a comma after the first method reference, 
then I'll do a method reference. 
This should look familiar because I used 
almost the exact same one in the lambda expression challenge.

```java  
public static void main(String[] args) {
    String[] names = {"Anna", "Bob", "Cameron", "Donald", "Eva", "Francis"};

    List<UnaryOperator<String>> list = new ArrayList<>(List.of(
            String::toUpperCase,
            s -> s += " " + getRandomChar('D', 'M') + "."
    ));

    applyChanges(names, list);
}
```

But this time I want a middle initial between _D_ and _M_, 
and I'll append a period to that initial. 
And running that code now,

```java  
[ANNA E., BOB I., CAMERON F., DONALD E., EVA K., FRANCİS E.]
```

I get a random letter between _D_ and _M_ for all of my names. 
The selection of this letter range is just arbitrary. 
The third thing we did in the Lambda Expression Challenge 
was to take the first name, and reverse the letters, 
and add that as the last name. 
How should we do that here?
Well, I'm going to create two overloaded methods, 
both private and static, both named reverse, 
and both return a String. 

```java  
private static String reverse(String s) {
    return reverse(s, 0, s.length());
}

private static String reverse(String s, int start, int end) {
    return new StringBuilder(s.substring(start, end)).reverse().toString();
}
```

The first takes a String, as well as a starting index, 
and an ending index. 
It returns a new StringBuilder, which gets passed the substring, 
derived by the starting and ending indices. 
The reverse method is chained to that, and then the toString method. 
The overloaded version will reverse the entire string, 
so it only has a single string parameter, 
and it in turn calls the other version, passing 0 and the length of the string, 
as arguments to that. 
Now, I'll add a method reference in my list in the main method, 
after adding a comma after the previous expression.

```java  
public static void main(String[] args) {
    String[] names = {"Anna", "Bob", "Cameron", "Donald", "Eva", "Francis"};

    List<UnaryOperator<String>> list = new ArrayList<>(List.of(
            String::toUpperCase,
            s -> s += " " + getRandomChar('D', 'M') + ".",
            Main::reverse
    ));

    applyChanges(names, list);
}
```

And I'm simply going to call **Main::reverse**. 
This will reverse my entire string which isn't quite
what I want to do, but I did want to talk about this kind of method reference. 
What do you think? 
What kind is this? 
Well, this one is really a static method reference, 
and the string is passed as the argument to this reverse method. 
Running the code with this extra function,

```java  
[.J ANNA, .M BOB, .H NOREMAC, .M DLANOD, .K AVE, .F SİCNARF]
```
            
You can see the entire name was reversed. 
Since that's not what I want, 
I'll actually insert a lambda expression before that last method reference.

```java  
public static void main(String[] args) {
    String[] names = {"Anna", "Bob", "Cameron", "Donald", "Eva", "Francis"};

    List<UnaryOperator<String>> list = new ArrayList<>(List.of(
            String::toUpperCase,
            s -> s += " " + getRandomChar('D', 'M') + ".",
            s -> s += " " + reverse(s, 0, s.indexOf(" ")),
            Main::reverse
    ));

    applyChanges(names, list);
}
```

Here, this means I'm really passing just the first name to this reverse method, 
and that will get reversed and concatenated to the current name. 
Running this code:

```java  
---(same)
[ANNA J. ANNA, BOB M. BOB, CAMERON H. NOREMAC, DONALD M. DLANOD, EVA K. AVE, FRANCİS F. SİCNARF]
[ANNA .J ANNA, BOB .M BOB, CAMERON .H NOREMAC, DONALD .M DLANOD, EVA .K AVE, FRANCİS .F SİCNARF]
```
            
You can now see on my third line of output, the effect of this method, 
which again did the same thing we did in the lambda expression challenge. 
Notice the last bit of output, the fully reversed name, 
look almost the same as unreversed; only the period and middle initial swapped.

Ok, so I've included two of the four types of method references. 
Is there a way to use the other 2? 
Well, what happens if I had a String constructor method reference? 
I'll add a comma after the **Main::reverse**, 
and then use a method reference for a new String.

```java  
public static void main(String[] args) {
    String[] names = {"Anna", "Bob", "Cameron", "Donald", "Eva", "Francis"};

    List<UnaryOperator<String>> list = new ArrayList<>(List.of(
            String::toUpperCase,
            s -> s += " " + getRandomChar('D', 'M') + ".",
            s -> s += " " + reverse(s, 0, s.indexOf(" ")),
            Main::reverse,
            String::new,
            s -> new String(s)
    ));

    applyChanges(names, list);
}
```

This compiles, but how does that work? 
Let me add the corresponding lambda expression directly below it. 
Again, I need to add a comma after the last expression first.
Both of these statements return a new String, 
which is constructed using the current text value. 
Running this code,

```java  
[ANNA .J ANNA, BOB .M BOB, CAMERON .H NOREMAC, DONALD .M DLANOD, EVA .K AVE, FRANCİS .F SİCNARF]
[ANNA .J ANNA, BOB .M BOB, CAMERON .H NOREMAC, DONALD .M DLANOD, EVA .K AVE, FRANCİS .F SİCNARF]
```
            
You can't really see anything change, since we're really setting each name 
to a new String instance passing it its own text value.

```java  
List<UnaryOperator<String>> list = new ArrayList<>(List.of(
        String::toUpperCase,
        s -> s += " " + getRandomChar('D', 'M') + ".",
        s -> s += " " + reverse(s, 0, s.indexOf(" ")),
        Main::reverse,
        String::new,
        //s -> new String(s),
        String::valueOf
));
```

Instead of **new**, I can use **valueOf** with the same results, 
so another comma after last expression, then **String::valueOf**.

```java  
[ANNA .J ANNA, BOB .M BOB, CAMERON .H NOREMAC, DONALD .M DLANOD, EVA .K AVE, FRANCİS .F SİCNARF]
```
            
The results in all of these cases are new String instances, 
but with text values, the same as before. 
These aren't particularly useful, though I have more flexibility with the lambda expression. 

```java  
List<UnaryOperator<String>> list = new ArrayList<>(List.of(
        String::toUpperCase,
        s -> s += " " + getRandomChar('D', 'M') + ".",
        s -> s += " " + reverse(s, 0, s.indexOf(" ")),
        Main::reverse,
        String::new,
        //s -> new String(s),
        s -> new String("Howdy " + s),
        String::valueOf
));
```

I could, for example, add something to the name as I create a new string "_Howdy_", 
and run that

```java  
[Howdy ANNA .K ANNA, Howdy BOB .J BOB, Howdy CAMERON .D NOREMAC, Howdy DONALD .K DLANOD, Howdy EVA .L AVE, Howdy FRANCİS .K SİCNARF]
[Howdy ANNA .K ANNA, Howdy BOB .J BOB, Howdy CAMERON .D NOREMAC, Howdy DONALD .K DLANOD, Howdy EVA .L AVE, Howdy FRANCİS .K SİCNARF]
```

You'll see the last two outputs have _Howdy_ there. 
You've probably noticed that IntelliJ isn't really happy with me using new String,
and wants me to just use the String literal added to _s_. 
I'm not going to make that change. 
I included this line to help you understand what the two methods, 
**String::new** and **String::valueOf** look like as a lambda expression 
using constructor code, so now I'll just comment this line out.

```java  
private record Person(String first) {

    public String last(String s) {
        return first + " " + s.substring(0, s.indexOf(" "));
    }
}
```

Ok, for the last method reference type, 
I'll set up a private person record in my Main class with one field, 
a String first. 
I want to add a public method to this record, called _last_; 
that takes a string. 
It returns the first field with a space concatenated to it. 
This is followed by a substring of the argument passed, 
which represents the first name of that argument, 
or first string in a space delimited string.
I'll set up a Person in the main method, and pass _Tim_ as the name.

```java  
Person tim = new Person("Tim");
```

And now, I can demonstrate the fourth type of method reference, 
an instance method called on an instance, using a bounded receiver. 
This means the instance is coming from code external 
to the reference itself. 

```java  
tim::last
```

Running that code,

```java  
[Tim ANNA, Tim BOB, Tim CAMERON, Tim DONALD, Tim EVA, Tim FRANCİS]
```

You can see my name, with the first part of all the other names, 
in that last output. 
This was the result of executing last on the tim record, 
but passing each name to the last method, on the tim instance. 
When you're using an instance, you can use an expression 
on the left side of the method reference. 
Let me add an example of that.

```java  
List<UnaryOperator<String>> list = new ArrayList<>(List.of(
        String::toUpperCase,
        s -> s += " " + getRandomChar('D', 'M') + ".",
        s -> s += " " + reverse(s, 0, s.indexOf(" ")),
        Main::reverse,
        String::new,
        //s -> new String(s),
        s -> new String("Howdy " + s),
        String::valueOf,
        tim::last,
        (new Person("MARY"))::last
));
```

Running that:

```java  
[MARY Tim, MARY Tim, MARY Tim, MARY Tim, MARY Tim, MARY Tim]
```
            
And that gives me records with _MARY_ as the first name, 
and _tim_ as the last name. 
There's really no end to the number and type of method references, 
and lambda expressions you could add here. 
Would you ever code something this way?
Probably not, but I hope you can get creative with it. 
If you want to execute a lot of functionality in a sort of ordered task list way, 
like this, you'd probably do it using functional interface method chaining, 
or with Streams.
</div>
