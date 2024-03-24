# [Section-6: Lists, Big-O Notation, Iterators, Autoboxing & Unboxing and Enum Type]()



## [a. Lists]()

<div align="justify">
Arrays were a massive improvement if you needed to store items of the same type, but as you saw, 
Arrays have some limitations. 
Not being able to change the number of elements in an Array being one. 
Fortunately, Java also includes an entire library for Java containers, which they call *Collections*. 
They take the arrays we worked with to the next level. 
They allow you to change the number of elements defined in an array for one, 
but there are many other improvements as well.

In this section, we'll be talking about lists, which are Java containers, 
and explain what they are, and how to use them. 
Two of the most common classes for lists, *ArrayList* and *LinkedList*. 
We will start by looking at these. 
In addition, I'll be covering important concepts related to these topics, 
like Big O Notation, Iterators, Autoboxing and Unboxing, and the enum type. 
If you are unfamiliar with any of these terms, by the end of this section, 
they will make sense.
There's a lot to cover in the section, so let's get started.

An array is mutable, and we saw that we could set or change values in the array, 
but we could not resize it. 
Java gives us several classes that let us add and remove items, and resize a sequence of elements. 
These classes are said to implement a List's behavior. 
So what is the list?

In our everyday life, we use lists all the time. 
When we're going to the grocery store, we've got a list. 
We have a list of things we need to do, a list of addresses, a list of contact numbers, etc. 
It wouldn't be a beneficial list, however, if we started with 10 items we could change, 
but never add or remove an item. 
List is a special type in Java, called an Interface. 
For now, I'll say a List Interface describes a set of method signatures 
that all List classes are expected to have.

The ArrayList is a class that really maintains an array in memory, 
that's actually bigger than what we need, in most cases. 
It keeps track of the capacity, which is the actual size of the array in memory. 
But it also keeps track of the elements that'd been assigned or set, which is the size of the ArrayList. 
As elements are added to an ArrayList, its capacity may need to grow. 
This all happens automatically, behind the scenes. 
This is why the ArrayList is resizeable.

I want to create a record, called GroceryItem. 
If you remember, we can do this using the type "record" instead of class, 
and at its simplest, we pass the fields we want on the record, in the declaration's parentheses:

```java  
record GroceryItem(String name, String type, int count) {
}
```

Ok, that's our grocery item record, with three fields, name, type, and count. 
But let's say 9 times out of 10, we'll have a count of 1, so we want a simpler way to create one of those records. 
We can add constructors to records, so I'll do that now:

```java  
public GroceryItem(String name) {
    this(name, "DAILY", 1);
}
```

This constructor takes only one field, the grocery item name. 
For a record, we have to call the generated default constructor first, with all three fields. 
Now, I'll go to the main method and create an array.

```java  
Object[] groceryArray = new Object[3];
groceryArray[0] = new GroceryItem("milk");
groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
groceryArray[2] = "5 oranges";
System.out.println(Arrays.toString(groceryArray));
```

I'm next going to do what I told you not to do previously, which is to create an array of Objects. 
Here, I have groceryArray, and I've set it to a size of 3, meaning we can only have three items in this array. 
And we're assigning the first two elements to instances of our new GroceryItem record, 
using the explicit one argument constructor I declared, and the default implicit constructor. 
The first instance only takes name, and the second one takes type and count as well. 
I'm going to add another element to this array, and then I'll print this array out. 
Here, I've set the element to a String, and not a grocery item at all. 
And because I've said we have an array of Objects, and not an array of GroceryItems, 
the compiler isn't going to prevent us from doing this.
Any code now that expects this array to contain only GroceryItems might have a problem processing this data. 
But I'll run this.

```java  
[GroceryItem[name=milk, type=DAILY, count=1], GroceryItem[name=apples, type=PRODUCE, count=6], 5 oranges]
```

As you can see, for our grocery items, we also know what department we might find that item in, 
but in the case of the String, it just prints *5 oranges*. 
We could easily fix this, in this case, by making the array, 
an array of GroceryItem, so I'll change this code, improving it. 
I'll change the array from an array of Object to an array of GroceryItem.

```java  
GroceryItem[] groceryArray = new GroceryItem[3];
groceryArray[0] = new GroceryItem("milk");
groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
groceryArray[2] = "5 oranges";                                                   // compiler error
groceryArray[2] = new GroceryItem("oranges", "PRODUCE", 5);
System.out.println(Arrays.toString(groceryArray));
```

You'll notice right away that now, the compiler won't let us assign a String to the third element of this array. 
We need to change that, and properly set it to an instance of a new GroceryItem.

```java  
groceryArray[2] = new GroceryItem("oranges", "PRODUCE", 5);
```

And the compiler is happy with that change. 
Declaring your arrays with a specific type allows compile-time type checking, as we've shown in this case. 
Type checking at compile-time prevents runtime exceptions 
when instances assigned to arrays aren't what they are expected to be. 

| Feature                         | array                        | ArrayList |
|---------------------------------|------------------------------|-----------|
| primitives types supported      | Yes                          | **No**    |
| indexed                         | Yes                          | Yes       |
| ordered by index                | Yes                          | Yes       |
| duplicates allowed              | Yes                          | Yes       |
| nulls allowed                   | Yes, for non-primitive types | Yes       |
| resizable                       | **No**                       | Yes       |
| mutable                         | Yes                          | Yes       |
| inherits from java.util.Objecct | Yes                          | Yes       |
| implements List interface       | No                           | **Yes**   |


Ok, so now I'll create a grocery list, using the ArrayList class instead of an array.

```java  
ArrayList objectList = new ArrayList();
```

I'll create and initialize a new ArrayList, called objectList.
Now, we don't have an error, but what I want you to notice straight away 
is that IntelliJ is highlighting the ArrayList class, in yellow, which is its way of warning us about something. 
If I hover my mouse over that class, IntelliJ gives us the message:

```java  
"Raw use of parameterized class 'ArrayList"
```

So what does that mean? 
Well, in truth, it's a lot like the example we just walked through, with the array of Objects. 
If we don't specify a type with an ArrayList, it's going to use the Object class by default.
This is called the raw use of this type. 
And this will result in the same problems we saw, when we had an object array, 
meaning, any object could be completely put in this ArrayList. 
I'll type that in.

```java  
objectList.add(new GroceryItem("Butter"));
objectList.add("Yogurt");
```

I'll add a new GroceryItem object, set to *Butter*, to object list. 
And add a string "Yogurt", to our object list.
First, we add a groceryItem, but we're also adding a String with this second add statement. 
And like we had before, a String may not be what the code wants, or is expecting. 
And that's generally not behaviour we want. 
When you declare an Arraylist, you probably know what data you want in that list. 
In our case, it's a GroceryItem record, and we don't want anything else but GroceryItems in the list. 
So how do we specify a type for an ArrayList?

I'll add a new declaration, then I'll talk about it:

```java  
ArrayList<GroceryItem> groceryList = new ArrayList<GroceryItem>();
```

So here, we used < and > signs, and we've specified the type. 
<GroceryItem> is the type we want all elements in this list to be, a GroceryItem record. 
And we've included this type, immediately following the ArrayList class, in the declaration of the variable on the left. 
We've also specified <GroceryItem> in the instantiation of this list, 
on the right of the assignment operator, or the equal sign "<>". 
Now, I'll hover over that second GroceryItem class, on the right side of the assignment, 
in IntelliJ. 
And notice here, that IntelliJ is telling us, this second reference to GroceryItem, 
which is called an explicit type argument, is unnecessary. 
In this case, I'll use IntelliJ's recommendation, 
and change this, by selecting Replace this when I hover over that second set of angle brackets. 
And that gets replaced with empty brackets:

```java  
ArrayList<GroceryItem> groceryList = new ArrayList<>();
```

These <>, when empty like this, are also called the diamond operator. 
Later we'll be talking about type arguments in a lot of details when we review generics, 
but right now, you should try to type your ArrayLists, as I show here. 
With this declaration, the compiler can do compile-time type checking 
because we've specified that we only want grocery items in our grocery list.

```java  
//groceryList.add("Yogurt");
groceryList.add(new GroceryItem("Butter"));
```

I'll copy the lines above and paste them here, changing the name of the array 
to groceryList in both statements.
And you can see, the compiler won't let us add a String object to this list. 
If we hover over that, we see that GroceryItem is the required type. 
I'll comment that last line of code.

Now, the other thing you might have noticed, in the previous statement, 
where we're creating a new ArrayList is we never specified a size for the list. 
An ArrayList is a resizable-array implementation, meaning it's not a fixed size, 
so we don't need to specify the number of elements it will have. 
It will grow automatically, as needed.

In general, you'll want to include a type argument when using the ArrayList. 
When I said you should declare an ArrayList, with a type argument as we did in the code above, 
using GroceryItem. 
These <>, by themselves, called the diamond operator, can be used when Java can infer the type, 
as it can in this declaration and assignment statement. 
But if we forget the diamond operator, in the instantiation part of this statement, 
we'll get another warning from intellij about the raw use of a parameterized list. 
Hopefully, you'll remember this means, the compile-time type checking won't occur, 
so without using the diamond operator, we'd be allowing other types to be potentially included in this ArrayList. 
If I remove the diamond operator:

```java  
ArrayList<GroceryItem> groceryList = new ArrayList();
```

You can see intelliJ warning us about this problem. 
Let me print our grocery list out now.

```java  
System.out.println(groceryList);
```

For an ArrayList, we can simply use println, passing the ArrayList instance, 
so that's nicer than having to use a helper class, as we did with the array. 
I'll print out "grocery list" above, and we run that:

```java  
[GroceryItem[name=milk, type=DAIRY, count=1], GroceryItem[name=apples, type=PRODUCE, count =6], GroceryItem[name=oranges, type=PRODUCE, count=5]]
[GroceryItem[name=Butter, type=DAIRY, count=1]]
```

This prints our list, which has one grocery item. 
I'll add a few more items to the list.

```java  
groceryList.add(new GroceryItem("milk"));
groceryList.add(new GroceryItem("oranges", "PRODUCE", 5));
System.out.println(groceryList);
```

I'll add a milk grocery item. 
And I'll add an "oranges" as "grocery item". And running that,

```java  
[GroceryItem[name=Butter, type=DAIRY, count=1], GroceryItem[name=milk, type=DAIRY, count=1], GroceryItem[name=oranges, type=PRODUCE, count=5]]
```

We get all three items in our list printed, *butter* and *milk*, both in the dairy department, 
and oranges in produce. 
And now, I'll add apples, but let's say we want *apples* to be first in the list. 
We can use an overloaded version of the add method for that.

```java  
groceryList.add(0, new GroceryItem("apples", "PRODUCE", 6));
System.out.println(groceryList);
```

I'll use *add* again, but will specify an index number for apples. 
This version of *add* takes an index as the first argument. 
What we're saying is, instead of adding this new item to the end of our existing list let's actually add
it at index 0, moving all the other elements down. 
Before I run that, I'll simplify how the record gets printed out.
Going to our GroceryItem record, I'll add my own toString method to this record.

```java  
@Override
public String toString(){
    return String.format("%d %s in %s", count, name.toUpperCase(), type);
}
```

I'll type this manually, instead of using IntelliJ. 
I'll format the return String. 
I'm overriding the toString
method, and returning a formatted string, using *String.format* that has three specifiers. 
The first specifier is for an integer, the count, and then we have two string specifiers, 
for the name which we want to be uppercase, and type. 
And if I run that:

```java  
[6 APPLES in PRODUCE, 1 BUTTER in DAIRY, 1 MİLK in DAIRY, 5 ORANGES in PRODUCE]
```

We see that six apples in *Produce* was added as the first list item, 
and the output is a bit easier to read. 
If instead of the add method, I use the set method, 
instead of inserting the apples, *set* will replace the first item, butter, with the apples, 
so I'll add that next:

```java  
groceryList.set(0, new GroceryItem("apples", "PRODUCE", 6));
System.out.println(groceryList);
```

And running this code,

```java  
[6 APPLES in PRODUCE, 1 MİLK in DAIRY, 5 ORANGES in PRODUCE]
```

You see, we have replaced the butter item with six apples.
We can also remove an item, by index, so if we wanted to remove milk from our list, 
we could pass 1, which means the second element. 
Like arrays, ArrayLists also start with an index of 0. 
I'll remove the second element from the grocery list:

```java  
groceryList.remove(1);
System.out.println(groceryList);
```

And running this code,

```java  
[6 APPLES in PRODUCE, 5 ORANGES in PRODUCE]
```

We can confirm, we've removed milk, the second item from the list. 
Now, I want to create a new class in the same package with Main, 
named MoreLists as the name, and I'll add the main method to it.

In this code, I want to start with a String array, with some groceries we might buy. 
First, we have an array of Strings, with four Strings in this array. 
Next, we're using a method onList, which is a factory method. 
We've talked about factory methods previously. 
This is where we call a static method on a class, and it returns a new instance of a class
for us. 
And here we're calling a static method on List, simply called *of*, 
and that returns a List of String elements back. 
And we're assigning this to a List variable. 
Notice we're using the type argument *String* on the left side, in the declaration of this variable. 
Now "list" is not ArrayList, and there's a reason why I'm not assigning this to an ArrayList of String. 
And that's because the object coming back from this method isn't an ArrayList.

```java  
public class MoreLists {
    public static void main(String[] args) {
        String[] items = {"apples", "bananas", "milk", "eggs"};
        List<String> list = List.of(items);
        System.out.println(list);
    }
}
```

Running this,

```java  
[apples, bananas, milk, eggs]
```

We get a nicely printed List of our array items. 
So this method transformed an array of String to a List of Strings. 
Let's expose this a bit more and see what kind of object we're getting back from this static factory method. 
I've shown you previously that we can do this by calling getClass(), and then chaining getName to that.

```java  
System.out.println(list.getClass().getName());
```

The getName() method gives us a little extra information than getSimpleName, 
which we've seen before, also. 
And running this,

```java  
java.util.ImmutableCollections$ListN
```

We learn that this List is something called, java.util.ImmutableCollection$ListN. 
Ok, so what is that? 
Well, the list we're getting back is of type List N, 
and it's a nested class within the ImmutableCollections class but most importantly, 
it's immutable. 
Alright, we can test this by trying to add an item to this list:

```java  
list.add("yogurt");
```

I'll attempt to add the string literal yogurt to our list. 
If we try to run this code, we get an exception, an UnsupportedOperationException. 
So why would I show you a method to get an immutable list? 
Well, as it turns out, this method can be used in the creation of an ArrayList, 
to make populating the ArrayList a bit easier. 
Let me show you that here next. 
But after commenting the last two lines.

```java  
ArrayList<String> groceries = new ArrayList<>(list);
groceries.add("yogurt");
System.out.println(groceries);
```

Here, we're creating an ArrayList of String, using the variable groceries, 
and assigning a new instance of the ArrayList to it. 
And notice the diamond operator on the left. 
I could've included the type _String_ in there, but it's not required, 
and this is simpler to read and write. 
And now look, we're calling a constructor on ArrayList, and passing
an argument, the **_list_** we created above. 
The result of this instantiation is a new mutable ArrayList, populated with
the elements from the immutable list we passed. 
And to prove this list is mutable, I'll add a new element, yogurt,
then print groceries out. Running that,

```java  
[apples, bananas, milk, eggs, yogurt]
```

We get the four items we had, plus yogurt in the output. 
Looking back at the **List.of** method, it's overloaded, and supports different versions, 
including variable arguments which we talked about previously. 
I'll simplify this code and create a list with specified elements, all in one statement. 
Let me show you what that looks like:

```java  
ArrayList<String> nextList = new ArrayList<>(
List.of("pickles", "mustard", "cheese"));
System.out.println(nextList);
```

Here, we create a new ArrayList, and this time, we can just pass a list of Strings 
to the static method on List, **List.of**, and we put that in our constructor parentheses as shown. 
This is one way to create and populate a small ArrayList, in one statement, with a few known elements. 
Let's keep looking at ways to add elements. 
There is another method, addAll method, on ArrayList, which takes any List as an argument. 
I'll use that next.

```java  
groceries.addAll(nextList);
System.out.println(groceries);
```

Here, we're adding everything in our second list to our first list, with the addAll method, 
and passing nextList to it. 
And when I run that:

```java  
[apples, bananas, milk, eggs]
[apples, bananas, milk, eggs, yogurt]
[pickles, mustard, cheese]
[apples, bananas, milk, eggs, yogurt, pickles, mustard, cheese]
```

You can see our grocery list now has eight items in it. 
All the elements in nextList were added to our grocery list. 
You can retrieve an element in an ArrayList, by its index, 
much like the way we reference an array element by its position, in []. 
But for an ArrayList, we use the get method, not []. 
I'll show an example of using the get method, to print out the third element in our list.

```java  
System.out.println("Third item = " + groceries.get(2));
```

Like arrays, ArrayLists start with a zero-index position, so the third element is at index 2. 
Running this,

```java  
Third item = milk
```

This code simply prints out the third element. 
Next, let's talk about searching for an item in the list. 
The ArrayList provides several methods to help us do this. 
First, there is the contains method that returns a boolean, 
returning true if it finds a match:

```java  
if (groceries.contains("mustard")) {
    System.out.println("List contains mustard");
}
```

And if I run that:

```java  
List contains mustard
```

We get the message. 
This method will work for many of Java's built-in classes, like String, 
because it ultimately calls the class's overridden equals method. 
We'll look at this more for our own classes in later. 
We can also use the indexOf method, and the lastIndexOf method. 
These methods return the index position of the element in the **list** if it finds it, 
instead of just returning true or false. 
These methods return _-1_ if the element isn't found in the list. 
ArrayLists can have duplicate elements, so I'll add yogurt to the list,
even though it's already there, as the fifth element.

```java  
groceries.add("yogurt");
System.out.println("first = " + groceries.indexOf("yogurt"));
System.out.println("last = " + groceries.lastIndexOf("yogurt"));
```

I'll print out groceries.indexOf for yogurt. 
And I'll also print out the last index of yogurt. And running this,

```java  
first = 4
last = 8
```

We see that the indexOf method found the first instance of yogurt at index 4, 
and the lastIndexOf method found the yogurt element, just added at the end of the list, at index 8. 
In addition to finding elements, we have several methods we can use to remove elements from a list. 
We can remove by index if we know it, or we can remove by the element's value.

```java  
System.out.println(groceries);
groceries.remove(1);
System.out.println(groceries);
groceries.remove("yogurt");
System.out.println(groceries);
```

First, I'm pointing the full list out, before removing anything. 
Then I remove the second element in the list, by passing int value _1_, 
to the remove method. 
And print out our list again. 
Finally, I'll remove yogurt from the list If a match is found, and print the list again. 
When I run this again:

```java  
[apples, bananas, milk, eggs, yogurt, pickles, mustard, cheese, yogurt]
[apples, milk, eggs, yogurt, pickles, mustard, cheese, yogurt]
[apples, milk, eggs, pickles, mustard, cheese, yogurt]
```

You can see that **bananas**, the second element, was removed. 
And then, we removed yogurt, but notice only the first yogurt element was removed, 
so this remove method only removes a single element. 
In addition to removing one element at a time, we can also remove a set of elements.

```java  
groceries.removeAll(List.of("apples", "eggs"));
System.out.println(groceries);
```

Here, we're using the static method **List.of**, to create a temporary list 
from a variable arguments list of elements. 
These are the elements we want removed, so if I run this code now:

```java  
[milk, pickles, mustard, cheese, yogurt]
```

Our list no longer contains apples or eggs. 
Another method that might remove the elements is called retainAll. 
This method's functionality is opposite to that of the removeAll method, 
but since it potentially removes elements from a list, let's look at it next.

```java  
groceries.retainAll(List.of("apples", "milk", "mustard", "cheese"));
System.out.println(groceries);
```

I'm using **List.of** again, passing four grocery items. 
This list gets passed to the retainAll method, called on our grocery list. 
And this method removes every item in the grocery list, which isn't one of the items passed in the argument. 
After executing this method,

```java  
[milk, mustard, cheese]
```

Our list should only contain three items, milk, mustard, and cheese. 
Before retailAll call, our list didn't include apples, so that element is just ignored here. 
Finally, we can remove all the elements from a list with the clear method.

```java  
groceries.clear();
System.out.println(groceries);
System.out.println("isEmpty = " + groceries.isEmpty());
```

The First line clears all elements in the list, and we'll print that out to confirm it. 
And while we're at it, we call another method on the list, called "isEmpty" which returns true 
if the list has no elements in it. 
Running this,

```java  
[]
isEmpty = true
```

We get the output, **[ ]** with no elements listed, and _isEmpty = true_. 
Now I'll add some elements back to this empty grocery list, using the addAll method.

```java  
groceries.addAll(List.of("apples", "milk", "mustard", "cheese"));
groceries.addAll(Arrays.asList("eggs", "pickles", "mustard", "ham"));
```

By now, the first statement should be starting to look familiar. 
I'm using the static method **List.of**, with a variable list of Strings, 
then passing that result to the first call to the **addAll** method, on our groceries ArrayList.
Like **List.of()**, **Array.asList** takes a variable argument list of elements 
and produces a list, and we can pass that list to our second call to **groceries.addAll** method. 
After executing these methods, we'll have eight elements in our list. 
Now that we have data again in the ArrayList, let's sort the elements. 
ArrayList has a sort method, so I'll just call it on groceries.

```java  
System.out.println(groceries);
groceries.sort(Comparator.naturalOrder());
System.out.println(groceries);
```

Again, we want to print the grocery list first before we sort it. 
Next, we call the sort method on the list.
This method has one argument, which is a type called **Comparator**. 
This special type allows instances to be compared to one another. 
We're going to be talking a lot more about Comparators when we talk about interfaces. 
For now, this method requires one, and Java provides the one we need, 
with a static factory method on the Comparator type, called naturalOrder. 
We call it and pass the result directly to the sort method argument list. 
This object allows ArrayLists, with types that have a natural order, to be sorted. 
Strings will be ordered alphabetically, from A to Z, for example, 
and numbers will be ordered from smallest to largest. 
Running this,

```java  
[apples, milk, mustard, cheese, eggs, pickles, mustard, ham]
[apples, cheese, eggs, ham, milk, mustard, mustard, pickles]
```

We can see our grocery list gets sorted, as we'd expect. 
We can also call the reverseOrder static method, on the Comparator type, to sort in reverse. 
I'll call reverse order this time.

```java  
groceries.sort(Comparator.reverseOrder());
System.out.println(groceries);
```

Running that,

```java  
[pickles, mustard, mustard, milk, ham, eggs, cheese, apples]
```

Ok, so we've looked at two ways to transform arrays, or variable arguments into the list. 
Now let's go the other way, getting the list from an array, with a different method on ArrayList. 
This is an overloaded method, appropriately called, toArray. 
This method will return an array, the same size as the array we pass to it, and of the same type.

```java  
var groceryArray = groceries.toArray(new String[groceries.size()]);
System.out.println(Arrays.toString(groceryArray));
```

So firstly, we're passing a new String array to the toArray method, 
which initialized to the type and size we want the returned array to be. 
In this case, we want an array that has the same size and type as our list. 
This method then returns an array of String. 
There may be times, you might want to pass your data as an array, 
to methods that accept arrays, rather than lists, as an example. 
Here we simply pass the array **new String[ groceries.size( ) ]** to the **Arrays.toString** method, 
and print the array out, which we know takes an array as an argument.

```java  
[pickles, mustard, mustard, milk, ham, eggs, cheese, apples]
```

Ok, so that was a pretty fast review of a lot of methods on the List, 
which were implemented for the ArrayList class.
</div>

## [b. ArrayList Challenge]()

<div align="justify">
The challenge is to create an interactive console program which gives to the user a menu of options as shown here:

```java  
        Available actions:
    0 - to shutdown
    1 - to add item(s) to list (comma delimited list)
    2 - to remove any items (comma delimited list)
        Enter a number for which you want to do:
```
                        
Using a Scanner class, solicit a menu item, 0, 1, 2, and process the item accordingly. 
This means if the user enters 0, you should quit the program. 
If the user enters 1, you want to prompt them for a comma-delimited list of items to add. 
And similarly, if the user enters 2, prompt them for a list, again comma delimited, 
to remove from the grocery list. 
Your grocery list should be an ArrayList. 
You should use methods on the ArrayList to add items, remove items, check if an item is already in the list, 
and print a sorted list. 
You should print the list, sorted alphabetically, after each operation. 
You shouldn't allow duplicate items in the list.
</div>


