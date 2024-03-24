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

### Array of Primitive Values

<div align="justify">
Let's start with an array of primitive data types. 
This is the simplest thing to understand.

| Index | Value | Address |
|-------|-------|---------|
| 0     | 34    | 100     |
| 1     | 18    | 104     |
| 2     | 91    | 108     |
| 3     | 57    | 112     |
| 4     | 453   | 116     |
| 5     | 68    | 120     |
| 6     | 6     | 124     |

When an array of primitive types is allocated, 
space is allocated for all of its elements contiguously, as shown above. 
You can see from this table that we have an array of seven integers. 
The index position is in the left column, and that's the number we use to access a specific array value. 
So the first element, when we use index position 0, this will retrieve value 34.
When we use index position 1, this gets the value of 18, and so on. 
The addresses we show here are memory addresses, represented by these numbers.

If 100 is the address of an integer, and we know an integer is 4 bytes, 
then the address of the next integer, if it's contiguous would be 104, as we show here, 
for the second element.
Java can use simple math, using the index, and the address of the initial element in the array, 
to get the address, and retrieve the value of the element.
</div>

### Arrays & ArrayLists of Reference Types

<div align="justify">
For reference types (meaning anything that's not a primitive type), like a String, or any other object, 
the array elements aren't the values, but the addresses of the referenced object or String. 
We've learned that ArrayLists are really implemented with arrays, under the covers. 
This means our objects aren't stored contiguously in memory, 
but their addresses are in the array behind the ArrayList.
And again, the addresses can be easily retrieved with a bit of math if we know the index of the element.

![image01](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_06_ArrayList_LinkedList_Iterators_Autoboxing/images/image01.png?raw=true)

This is a cheap lookup, and doesn't change, no matter what size the ArrayList is. 
But to remove an element, the referenced addresses have to be re-indexed, or shifted, 
to remove an empty space.
And when adding an element, the array that backs the ArrayList might be too small, 
and might need to be reallocated.
Either of these operations can be an expensive process if the number of elements is large.

An ArrayList is created with an initial capacity, depending on how many elements we create the list with, 
or if you specify a capacity when creating the list.

```java  
ArrayList<Integer> intList = new ArrayList<>(10);
for (int i = 0; i < 7; i++) {
    intList.add((i + 1) * 5);
}
```

| Index | 0 | 1  | 2  | 3  | 4  | 5  | 6  | 7 | 8 | 9 |
|-------|---|----|----|----|----|----|----|---|---|---|
| Value | 5 | 10 | 15 | 20 | 25 | 30 | 35 |   |   |   |


Here, I show an ArrayList that has a capacity of 10, because we're passing 10 in the constructor of this list. 
We then add 7 elements.

```java  
intList.add(40);
intList.add(45);
intList.add(50);
```

| Index | 0 | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9  |
|-------|---|----|----|----|----|----|----|----|----|----|
| Value | 5 | 10 | 15 | 20 | 25 | 30 | 35 | 40 | 45 | 50 |


We can add three more elements, using the ArrayList add method, 
and the array used to store the data doesn't need to change. 
The elements at indices 7, 8, and 9, get populated.

```java  
intList.add(55);            // This add exceeds the ArrayList capacity,
                            // assuming an initial capacity of 10,
                            // as an example.
```

| Index | 0 | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9  | 10 | 11 | 12 | 13 | 14 |
|-------|---|----|----|----|----|----|----|----|----|----|----|----|----|----|----|
| Value | 5 | 10 | 15 | 20 | 25 | 30 | 35 | 40 | 45 | 50 | 55 |    |    |    |    |

But if the number of elements exceeds the current capacity, 
Java needs to reallocate memory to fit all the elements, and this can be a costly operation, 
especially if your ArrayList contains a lot of items.

So now, if our code simply calls add on this ArrayList, 
the next operation is going to create a new array, with more elements, but copy the existing 10 elements over.
Then the new element is added. 
You can imagine this "add" operation costs more, in both time and memory, 
than the previous add methods did. 
When Java re-allocates new memory for the ArrayList, it automatically sets the capacity to a greater capacity. 
But the Java language doesn't really specify exactly how it determines the new capacity, 
or promise that it will continue to increase the capacity in the same way in future versions.
We can't get this capacity size, from the ArrayList.

From their own documentation, Java states that, _The details of the growth policy are not specified beyond the
fact that adding an element has constant amortized time cost_. 
Ok, maybe you're interested in what constant amortized time is. 
Let's start with how to determine cost, which in this case is generally considered in terms of time, 
but may include memory usage and processing costs, etc.
</div>

## [b. ArrayList Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_06_ArrayList_LinkedList_Iterators_Autoboxing/Course02_ArrayListChallenge/README.md#arraylist-challenge)

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

## [c. Big-O Notation and LinkedList]()

<div align="justify">
Maybe you've heard people talking about Big O Notation, or Big O, and wondered what this means. 
I won't get too deep into it, but there are a couple of concepts that are fairly easy to grasp, 
and will help us understand how cheap or expensive an operation is, in terms of time and memory usage, 
as the operation scales. 
This means it's a way to express how well the operation performs, when applied to more and more elements. 
Big O approximates the cost of an operation, for a certain number of elements, called n. 
Cost is usually determined by the time it takes, but it can include memory usage, and complexity, for example.

As n (the number of elements) gets bigger, an operation's cost can stay the same. 
But cost often grows, as the number of elements grows. 
Costs can grow linearly, meaning the cost stays in a step, with the size of the number of elements. 
Or costs can grow exponentially, or in some other non-linear fashion. 
In a perfect world, an operation's time and complexity would never change. 
This ideal world, in Big O Notation is O(1), sometimes called constant time. 
In many situations, an operation's cost is in direct correlation to the number of elements, n. 
In Big O Notation this is O(n), sometimes called linear time.

So if we have 10 elements, the cost is 10 times what it would be for one element, 
because the operation may have to execute some functions, 
up to 10 times vs. just once, and 100 times for 100 elements, for example. 
O(n) is generally our worst case scenario for List operations, 
but there are Big O Notations for worse performers. 
We won't be talking about exponential growth or non-linear growth yet, 
since it's not relevant to our discussion here.
</div>

### Constant Amortized Time Cost

<div align="justify">
Another scenario is the one the Java docs declared for the growth of the ArrayList, 
that adding an element has constant amortized time cost. 
In our case, we'll designate this constant amortized time as O(1)*. 
This means that in the majority of cases, the cost is close to O(1), but at certain intervals, 
the cost is O(n). 
If we add an element to an ArrayList, where the capacity of the List is already allocated, 
and space is available, the cost is the same each time, regardless of how many elements we add.

But as soon as we reach the capacity, and all the elements (all n elements) need to be copied in memory, 
this single adding would have a maximum cost of O(n). 
After this operation, that forced a reallocation, any additional add operations go back to O(1), 
until the capacity is reached again. As the expensive intervals decrease, the cost gets closer to O(1), 
so we give it the notation O(1)*.

| ArrayList Operations - Big-O | Worst Case | Best Case |
|------------------------------|------------|-----------|
| add(E element)               | O(1)*      |           |
| add(int index, E element)    | O(n)       | O(1)*     |
| contains(E element)          | O(n)       | O(1)      |
| get(int index)               | O(1)       |           |
| indexOf(E element)           | O(n)       | O(1)      |
| remove(int index)            | O(n)       | O(1)      |
| remove(E element)            | O(n)       |           |
| set(int index, E element)    | O(1)       |           |


- O(1): constant time - operation's cost(time) should be constant regardless of number of elements.
- O(n): linear time - operation's cost(time) will increase linearly with the number of elements n.
- O(1)*: constant amortized time—somewhere between O(1) and O(n), but closer to O(1) as efficiencies are gained.

This table shows the Big O values for the most common ArrayList operations or methods. 
Let's talk about one example, the contains method, which looks for a matching element, 
and needs to traverse through the ArrayList to find a match. 
It could find a match at the very first index; this is the best case scenario, so it's O(1). 
It might not find a match until the last index, this is the worst case scenario, so it's O(n).

In general, the cost will be something in between, for the contains method, 
because the element will be found somewhere between the first and nth (or last) element. 
You'll notice that the indexed methods are usually O(1), remembering that finding an element by its index 
is a simple calculation. 
It only gets costly with indexed add or remove methods if the ArrayList needs to be re-indexed or re-sized.

Now that you understand why some operations are more efficient and less costly 
because of indexing, it's time to look at another class, called the LinkedList.
</div>

### LinkedList

<div align="justify">
The LinkedList is not indexed at all. 
There is no array, storing the addresses in a neat ordered way, as we saw with the ArrayList. 
Instead, each element that's added to a linked list, 
forms a chain, and the chain has links to the previous element, and the next element.

![image02](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_06_ArrayList_LinkedList_Iterators_Autoboxing/images/image02.png?raw=true)

This architecture is called a doubly linked list, meaning an element is linked to the next element, 
but it's also linked to a previous element, in this chain of elements. 
The beginning of the chain is called the head of the list, and the end is called the tail. 
This can also be considered a queue, in this case, a double-ended queue, 
because we can traverse both backwards and forwards, through these elements.

Getting an element from the list, or setting a value of element, isn't just simple math anymore, 
with the LinkedList type. 
To find an element, we'd need to start at the head or tail, and check if the element matches, 
or keep track of the number of elements traversed, if we are matching by an index, 
because the index isn't stored as part of the list. 
For example, even if you know, you want to find the fifth element, 
you'd still have to traverse the chain this way, to get that fifth element. 
This type of retrieval is considered expensive in computer currency, 
which is processing time and memory usage. 
On the other hand, inserting and removing an element is much simpler for this type of collection.

In contrast to an ArrayList, inserting or removing an item in a LinkedList, 
is just a matter of breaking two links in the chain, and re-establishing two different links. 
No new array needs to be created, and elements don't need to be shifted into different positions. 
A reallocation of memory to accommodate all existing elements is never required. 
So for a LinkedList, inserting and removing elements, is generally considered cheap in computer currency, 
compared to doing these functions in an ArrayList.

All of that being said, there is a lot of debate about whether a LinkedList 
is ever really a preferred solution over the ArrayList, 
especially as the Java language improves in performance, 
and computer hardware itself continues to improve. 
I won't wade into this debate, but I want to encourage you to review both sides of the debate 
if you're interested. 
Let's look at the Big O Notation for the matching operations in a Linked List.

| Operation                 | LinkedList Worst Case | LinkedList Best Case | ArrayList Worst Case | ArrayList Best Case |
|---------------------------|-----------------------|----------------------|----------------------|---------------------|
| add(E element)            | O(1)                  |                      | O(1)*                |                     |
| add(int index, E element) | O(n)                  | O(1)                 | O(n)                 | O(1)*               |
| contains(E element)       | O(n)                  | O(1)                 | O(n)                 | O(1)                |
| get(int index)            | O(n)                  | O(1)                 | O(1)                 |                     |
| indexOf(E element)        | O(n)                  | O(1)                 | O(n)                 | O(1)                |
| remove(int index)         | O(n)                  | O(1)                 | O(n)                 | O(1)                |
| remove(E element)         | O(n)                  | O(1)                 | O(n)                 |                     |
| set(int index, E element) | O(n)                  | O(1)                 | O(1)                 |                     |

This table shows the Big O values, for the most common shared List operations or methods, for both types. 
For a LinkedList, adding elements to the start or end of the List 
will almost always be more efficient than an ArrayList. 
When removing elements, a LinkedList will be more efficient because it doesn't require re-indexing, 
but the element still needs to be found, using the traversal mechanism,
which is why it is O(n), as the worst case. 
Removing elements from the start or end of the List will be more efficient for a LinkedList.
</div>

### Review

<div align="justify">
The ArrayList is usually the better default choice for a List, 
especially if the List is used predominantly for storing and reading data. 
If you know the maximum number of possible items, 
then it's probably better to use an ArrayList, but set its capacity. 
This code demonstrates how to set the capacity of your ArrayList to 500,000.

```java  
int capacity = 500_000;
ArrayList<String> stringArray = new ArrayList<>(capacity);
```

An ArrayList's index is an int type, so an ArrayList's capacity is limited 
to the maximum number of elements an int can hold, Integer.MAX_VALUE = 2,147,483,647.

You may want to consider using a LinkedList if you're adding and processing 
or manipulating a large number of elements, and the maximum elements aren't known, 
but may be great, or if your number of elements may exceed Integer.MAX_VALUE. 
A LinkedList can be more efficient when items are being processed predominantly 
from either the head or tail of the list.

I've just talked a lot about how the LinkedList, and the ArrayList, are different under the covers. 
An ArrayList is implemented on top of an array, but a LinkedList is a doubly linked list. 
Both implement all of List's methods, but the LinkedList implements the Queue and Stack methods as well.
</div>

### A Queue is a First-In, First-Out (FIFO) Data Collection

<div align="justify">
When you think of a queue, you might think of standing in line. 
When you get in a line or a queue, you expect that you'll be processed, 
in relationship to the first person in line. 
We call this a First-in First-out, or FIFO data collection.

![image03](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_06_ArrayList_LinkedList_Iterators_Autoboxing/images/image03.png?raw=true)

If you want to remove an item, you poll the queue, getting the first element or person in the line. 
If you want to add an item, you offer it onto the queue, sending it to the back of the line. 
Single-ended queues always process elements from the start of the queue. 
A double-ended queue allows access to both the start and end of the queue. 
A LinkedList can be used as a double-ended queue.
</div>

### A Stack is a Last-In, First-Out (LIFO) Data Collection

<div align="justify">
When you think of a stack, you can think of a vertical pile of elements, one on top of another, as we show on this
diagram.

![image04](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_06_ArrayList_LinkedList_Iterators_Autoboxing/images/image04.png?raw=true)

When you add an item, you push it onto the stack. 
If you want to get an item, you'll take the top item, or pop it from the stack. 
We call this a Last-In First-out, or LIFO data collection. 
A LinkedList can be used as a stack as well.
Let's start using this new class in some code.

```java  
LinkedList<String> placesToVisit = new LinkedList<>();
```

The first thing I'll do is create a LinkedList variable called placesToVisit. 
Like an ArrayList, we include the type parameter, String, in <>, in the declaration. 
And we use the <> operator on the right side of the assignment operator. 
This is one way to declare a LinkedList. 
I want to take an extra minute here to talk to you about Lists, and the "var" keyword. 
Now I'll use the "var" keyword instead to set up this LinkedList.

```java  
var placesToVisit2 = new LinkedList<String>();
```

Creating a new LinkedList of strings and assigning to our placesToVisit, 
what I wanted to show you here is that we can use "var" keyword for type LinkedList (or any type list or collection). 
But when we do, we have to specify the type parameter on the right side of the assignment operator in < >. 
We can't just put a diamond operator there as we did on the line above.
Using the < > operator without a type in this statement doesn't give 
Java enough information to infer the type. 
So in this case, the type isn't optional on the right side of the assignment. 
Now, adding records to a LinkedList is a lot like adding records to an ArrayList, 
so let's add a few places we might want to visit in Australia.
First I'll add Sydney, then I'll add Canberra at index 0.

```java  
placesToVisit2.add("Sydney");
placesToVisit2.add(0,"Canberra");
System.out.println(placesToVisit2);
```

Like an ArrayList, we can use the "add" method, passing it just an element, 
which appends the element to the end of the list. 
We next use the overwritten method for _add_ which takes an index, again like the ArrayList. 
This inserts "Canberra" at position 0 of the list. 
Running this code,

```java  
[Canberra, Sydney]
```
                
We confirm that our list has _Canberra_ first, then _Sydney_. 
Now because the LinkedList implements methods on the deck interface, 
we have several other functions, to add elements. 
I'm going to create a method called addMoreElements, to demonstrate some of these. 
I'll make the return type void, and add a parameter of a LinkedList of strings.

```java  
private static void addMoreElements(LinkedList<String> list) {
    list.addFirst("Darwin");
    list.addLast("Hobart");
}
```

I'll then add "Darwin" at the start of the list. 
And then "Hobart" at the end. 
These methods that are on the LinkedList
class, but not on an ArrayList. 
The first statement adds _Darwin_, inserting it at the start of the list. 
The next statement adds _Hobart_, appending it to the end of the list. 
Let's call the addMoreElements from the main method and print out the list.

```java  
addMoreElements(placesToVisit2);
System.out.println(placesToVisit2);
```

Running this code,

```java  
[Canberra, Sydney]
[Darwin, Canberra, Sydney, Hobart]
```

We can confirm that "Darwin" was added at the start and how about at the end of the list. 
In addition to these methods, LinkedList has a method called offer, which queue language if you will, 
for adding an element to the end of the queue. 
Let's add that to the addMoreElements method.

```java  
private static void addMoreElements(LinkedList<String> list) {
    list.addFirst("Darwin");
    list.addLast("Hobart");
    // Queue methods
    list.offer("Melbourne");
}
```

I'll call offer passing the string literal _Melbourne_. 
And running that,

```java  
[Canberra, Sydney]
[Darwin, Canberra, Sydney, Hobart, Melbourne]
```

We see _Melbourne_ now at the end of the list or queue. 
The "offerLast" last method does the same thing as the offer method. 
We also have an "offerFirst" method that does what the addFirst method does, so let's add these next.

```java  
private static void addMoreElements(LinkedList<String> list) {
    list.addFirst("Darwin");
    list.addLast("Hobart");
    // Queue methods
    list.offer("Melbourne");

    list.offerFirst("Brisbane");
    list.offerLast("Toowoomba");
}
```

Running this,

```java  
[Canberra, Sydney]
[Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
```

We see "Brisbane" as our first element and "Toowoomba" as the last. 
Continuing on, there is one more method for adding an element. 
If you're familiar with stack language, you might know that when you want to add something to a stack, 
you push it onto the stack. 
We also have a "push" method. 
The top of the stack is the first element in the LinkedList, 
and if you're using LinkedList as a stack, pushing will insert the element at the start. 
I'll add that method:

```java  
private static void addMoreElements(LinkedList<String> list) {
    list.addFirst("Darwin");
    list.addLast("Hobart");
    // Queue methods
    list.offer("Melbourne");

    list.offerFirst("Brisbane");
    list.offerLast("Toowoomba");

    // Stack Methods
    list.push("Alice Springs");
}
```

Running that,

```java  
[Canberra, Sydney]
[Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
```

We see that the push method placed "Alice Springs" at the head, or beginning of the list. 
So you can see the LinkedList can be used as different types of data collections a list, 
a queue, a double-ended queue, and a stack. 
Like the many ways to add an element, there are also many ways to remove an item from a LinkedList.
We'll put these in a separate method called removeElements.

```java  
private static void removeElements(LinkedList<String> list) {
    list.remove(4);
    list.remove("Brisbane");
}
```

I'll remove the element at index 4. And remove _Brisbane_.
Here, we're simply calling the remove methods that we also saw on the ArrayList. 
The first statement removes the fifth element in the list 
which will be Sydney and the next removes _Brisbane_ from the list. 
Let's add a call to the removeElements method in the main method, and print the list.

```java  
private static void removeElements(LinkedList<String> list) {
    list.remove(4);
    list.remove("Brisbane");

    System.out.println(list);
}
```

```java  
removeElements(placesToVisit2);
System.out.println(placesToVisit2);
```

Running that,

```java  
[Canberra, Sydney]
[Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
[Alice Springs, Darwin, Canberra, Hobart, Melbourne, Toowoomba]
```

We can confirm that we removed _Brisbane_ and _Sydney_. 
LinkedList has additional methods to remove elements, that aren't on ArrayList. 
One of these is a no argument remove method. 
Let's add that to removeElements method.

```java  
private static void removeElements(LinkedList<String> list) {
    list.remove(4);
    list.remove("Brisbane");

    System.out.println(list);
    String s1 = list.remove();  // removes first element
    System.out.println(s1 + " was removed");
}
```

So, we call remove again, but we aren't passing any arguments to this version of the method. 
This method removes the first element in the list, and returns the removed element. 
We assign that to a variable S1 and print that out.

```java  
[Canberra, Sydney]
[Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
[Alice Springs, Darwin, Canberra, Hobart, Melbourne, Toowoomba]
Alice Springs was removed
[Darwin, Canberra, Hobart, Melbourne, Toowoomba]
```

And we can confirm that "Alice Springs" was removed from the list, which was the first item in our list.

```java  
private static void removeElements(LinkedList<String> list) {
    list.remove(4);
    list.remove("Brisbane");

    System.out.println(list);
    String s1 = list.remove();  // removes first element
    System.out.println(s1 + " was removed");

    String s2 = list.removeFirst();  // removes first element
    System.out.println(s2 + " was removed");
}
```

Running this,

```java  
[Canberra, Sydney]
[Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
[Alice Springs, Darwin, Canberra, Hobart, Melbourne, Toowoomba]
Alice Springs was removed
Darwin was removed
[Canberra, Hobart, Melbourne, Toowoomba]
```

We confirm that removeFirst is the same as calling remove, 
but might be a bit clearer to anyone reading our code. 
And there's also a removeLast method.

```java  
private static void removeElements(LinkedList<String> list) {
    list.remove(4);
    list.remove("Brisbane");

    System.out.println(list);
    String s1 = list.remove();  // removes first element
    System.out.println(s1 + " was removed");

    String s2 = list.removeFirst();  // removes first element
    System.out.println(s2 + " was removed");

    String s3 = list.removeLast();  // removes last element
    System.out.println(s3 + " was removed");
}
```

Running this,

```java  
[Canberra, Sydney]
[Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
[Alice Springs, Darwin, Canberra, Hobart, Melbourne, Toowoomba]
Alice Springs was removed
Darwin was removed
Toowoomba was removed
[Canberra, Hobart, Melbourne]
```

We see _Toowoomba_, the last element, gets removed this time. 
And like the additional queue methods offer, offerFirst and offerLast for adding an element, 
we have the poll, pollFirst and pollLast methods to remove an element. 
Let's add these, starting with Poll.

```java  
private static void removeElements(LinkedList<String> list) {
    list.remove(4);
    list.remove("Brisbane");

    System.out.println(list);
    String s1 = list.remove();  // removes first element
    System.out.println(s1 + " was removed");

    String s2 = list.removeFirst();  // removes first element
    System.out.println(s2 + " was removed");

    String s3 = list.removeLast();  // removes last element
    System.out.println(s3 + " was removed");

    // Queue/Deque poll methods
    String p1 = list.poll();             // removes first element
    System.out.println(p1 + " was removed");

    String p2 = list.pollFirst();        // removes first element
    System.out.println(p2 + " was removed");

    String p3 = list.pollLast();         // removes last element
    System.out.println(p3 + " was removed");
}
```

First, we create a local variable, a String named P1 and assign it the result of the poll method, 
which removes the first element from the list. 
And we simply print that variable out. 
And I added the rest of poll methods. 
Running this code,

```java  
.... (same)
Canberra was removed
Hobart was removed
Melbourne was removed
[]
```

We see "Canberra" was removed first then _Hobart_ and finally, _Melbourne_ which leaves our list empty. 
Let's add a couple of elements back to our list before we test one more method. 
Let's use the push method here, another stack method, that pushes the item to the top of the stack,
the start of the list. 
You can imagine, if you move that push, it's pushing all the elements back in the pile, 
or downwards in the stack. 
I'll add Sydney, Brisbane, and Canberra back into the list.

```java  
private static void removeElements(LinkedList<String> list) {
    list.remove(4);
    list.remove("Brisbane");

    System.out.println(list);
    String s1 = list.remove();  // removes first element
    System.out.println(s1 + " was removed");

    String s2 = list.removeFirst();  // removes first element
    System.out.println(s2 + " was removed");

    String s3 = list.removeLast();  // removes last element
    System.out.println(s3 + " was removed");

    // Queue/Deque poll methods
    String p1 = list.poll();             // removes first element
    System.out.println(p1 + " was removed");

    String p2 = list.pollFirst();        // removes first element
    System.out.println(p2 + " was removed");

    String p3 = list.pollLast();         // removes last element
    System.out.println(p3 + " was removed");

    list.push("Sydney");
    list.push("Brisbane");
    list.push("Canberra");
    System.out.println(list);

    String p4 = list.pop();         // removes first element
    System.out.println(p4 + " was removed");
}
```

We'll test the stack method for removing an element, which is the pop method. 
I'll remove the first element and assign it to P4 then I'll print it out. 
So let's run this code.

```java  
.... (same)
[Canberra, Brisbane, Sydney]
Canberra was removed
[Brisbane, Sydney]
```

And we can confirm "Canberra" gets removed using the pop method. 
Using the push methods, it was the last element in, and with pop, it was the first element out.

Now, I want to review the different ways to retrieve an element from a LinkedList. 
I'll add another method to this class.
I'll call it getting element static return type void:

```java  
private static void gettingElements(LinkedList<String> list) {
    System.out.println("Retrieved Element = " + list.get(4));
}
```

This method simply retrieves the fifth element in the list, and prints that out. 
Let's add a call to this method in the main method.

```java  
gettingElements(placesToVisit2);
```

First, we'll comment out the call to the removeElements method. 
And running this code,

```java  
[Canberra, Sydney]
[Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
Retrieved Element = Sydney
```

We can see we retrieved the element at index fourth or the fifth City in our list, Sydney. 
I've said this as a method on both ArrayList and LinkedList, however on the 
ArrayList the Big O Notation is O(1) at its worst, whereas for a LinkedList, the worst case is O(n). 
It's actually not as bad as that on a LinkedList.
Since it's a double-ended queue, Java will decide where to start searching. 
The retrieval will start moving from one link to the next, either from the start 
or the end of the list, whichever is closer to the specified index. 
So it would never traverse the entire number of elements. 
In addition to get method, the LinedList has the getFirst, and getLast methods. 
I'll print out the first and last elements using getFirst and getLast. 
Going back to the gettingElements method,

```java  
private static void gettingElements(LinkedList<String> list) {
    System.out.println("Retrieved Element = " + list.get(4));

    System.out.println("First element = " + list.getFirst());
    System.out.println("Last element = " + list.getLast());
}
```

Here, we're calling each method, and printing the result in the println statement. 
So no surprises here when I run it,

```java  
[Canberra, Sydney]
[Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
Retrieved Element = Sydney
First element = Alice Springs
Last element = Toowoomba
```
                
We get "Alice Springs" from the getFirst method and "Toowoomba" from the getLast method. 
We can also use the methods indexOf and lastIndexOf, to see if an element is in a list, 
as we did with the ArrayList.

```java  
private static void gettingElements(LinkedList<String> list) {
    System.out.println("Retrieved Element = " + list.get(4));

    System.out.println("First element = " + list.getFirst());
    System.out.println("Last element = " + list.getLast());
    
    System.out.println("Darwin is at position: " + list.indexOf("Darwin"));
    System.out.println("Melbourne is at posiiton = " + list.lastIndexOf("Melbourne"));
}
```

The Big O Notation is the same for these methods, as it is for the ArrayList. 
The worst case is O(n) for both if the element retrieved is the element of the last position to be checked. 
Running this code tells us the positions of these towns in our list.

```java  
[Canberra, Sydney]
[Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
Retrieved Element = Sydney
First element = Alice Springs
Last element = Toowoomba
Darwin is at position: 2
Melbourne is at posiiton = 6
```
                
So "Darwin" is at position 2, and "Melbourne" is at 6. As you'd expect, 
LinkedList has additional methods for retrieving an element. 
The first is appropriately named element, which is a Queue method.

```java  
private static void gettingElements(LinkedList<String> list) {
    System.out.println("Retrieved Element = " + list.get(4));

    System.out.println("First element = " + list.getFirst());
    System.out.println("Last element = " + list.getLast());
    
    System.out.println("Darwin is at position: " + list.indexOf("Darwin"));
    System.out.println("Melbourne is at posiiton = " + list.lastIndexOf("Melbourne"));

    // Queue retrieval method
    System.out.println("Element from element() = " + list.element());
}
```

This "element" method takes no arguments. 
So what element does it return? 
Remember, I've said it's a queue method and a queue is first in first out. 
So this should indicate that an element gets the first item out of the list. 
Running this,

```java  
[Canberra, Sydney]
[Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
Retrieved Element = Sydney
First element = Alice Springs
Last element = Toowoomba
Darwin is at position: 2
Melbourne is at posiiton = 6
Element from element() = Alice Springs
```

We can confirm that because we get _Alice Springs_. 
Now let's add the stack retrieval methods which are peek, and similar methods like peekFirst and peekLast.

```java  
private static void gettingElements(LinkedList<String> list) {
    System.out.println("Retrieved Element = " + list.get(4));

    System.out.println("First element = " + list.getFirst());
    System.out.println("Last element = " + list.getLast());
    
    System.out.println("Darwin is at position: " + list.indexOf("Darwin"));
    System.out.println("Melbourne is at posiiton = " + list.lastIndexOf("Melbourne"));

    // Queue retrieval method
    System.out.println("Element from element() = " + list.element());

    // Stack retrieval method
    System.out.println("Element from peek() = " + list.peek());
    System.out.println("Element from peekFirst() = " + list.peekFirst());
    System.out.println("Element from peekLast() = " + list.peekLast());
}
```

Running this,

```java  
.... (same)
Element from peek() = Alice Springs
Element from peekFirst() = Alice Springs
Element from peekLast() = Toowoomba
```

We can see that peek and peekFirst, get the first element of the list, and peekLast gets the last element.
Now, let's switch gears and talk about traversing and manipulating the elements in the list. 
First, let's use a simple for loop. 
We'll say this list of towns is our itinerary, 
and we plan to travel to each list in the order we've defined here. 
I'll call the method _printItinerary_ and pass it a LinkedList of string.

```java  
public static void printItinerary(LinkedList<String> list) {
    System.out.println("Trip starts at " + list.getFirst());
    System.out.println("Trip ends at " + list.getLast());
}
```

This method uses the getFirst and getLast methods to print out 
the starting and ending points of our trip. 
And I'll add a call to this from the main method.

```java
printItinerary(placesToVisit2);
```

I'll first comment out the call to the _gettingElements_ method. 
Running this code, we should see our list of places 
and then that the trip starts at _Alice Springs_ and ends at _Toowoomba_. 
Now let's include a loop to print the places in between, 
and we'll want to include an entry for each item, except the starting and ending points. 
Every line item will include where we started and where we ended up. 
Going back to _printItenirary_ method,

```java  
public static void printItinerary(LinkedList<String> list) {
    System.out.println("Trip starts at " + list.getFirst());
    System.out.println("Trip ends at " + list.getLast());

    System.out.println("Trip starts at " + list.getFirst());
    for (int i = 1; i < list.size(); i++) {
        System.out.println("--> From: " + list.get(i - 1) + " to " + list.get(i));
    }
    System.out.println("Trip ends at " + list.getLast());
}
```

So this for loop starts at i = 1, because we don't want to include an entry 
for the first time we started in, since we've already printed that out. 
And then I'll print the item we started at using a minus one as the index to get 
that down and then get the town at the current index. 
Running this code,

```java
Trip starts at Alice Springs
--> From: Alice Springs to Brisbane
--> From: Brisbane to Darwin
--> From: Darwin to Canberra 
--> From: Canberra to Sydney
--> From: Sydney to Hobart
--> From: Hobart to Melbourne
--> From: Melbourne to Toowoomba
Trip ends at Toowoomba
```
                    
We get a decent itinerary. 
I've said that indexing into a LinkedList isn't the most efficient way to access elements.
In the for loop, we're accessing two elements by index for each iteration of the loop. 
Let's try another method this time using the for each loop. 
I'm going to copy this method and rename a new method as _printItinerary2_:

```java
public static void printItinerary2(LinkedList<String> list) {
    System.out.println("Trip starts at " + list.getFirst());
    String previousTown = list.getFirst();
    for (String town : list) {
        System.out.println("--> From: " + previousTown + " to " + town);
        previousTown = town;
    }
    System.out.println("Trip ends at " + list.getLast());
}
```

So first, we set up the previousTown, to be the first place in our list. 
Then, we set up the for each loop, using a String variable named town, 
and the second component of this declaration is the list then 
we'll print out the previous town and the current town. 
Next, we set previousTown to the current iterations town variable, and that's it. 
I'll call this method now in the main method instead of _printitinerary_. 
I'll make that printItinerary2. 
And running that,

```java
Trip starts at Alice Springs
--> From: Alice Springs to Alice Springs
--> From: Alice Springs to Brisbane
--> From: Brisbane to Darwin
--> From: Darwin to Canberra
--> From: Canberra to Sydney
--> From: Sydney to Hobart
--> From: Hobart to Melbourne
--> From: Melbourne to Toowoomba
Trip ends at Toowoomba
```

So this is a bit more efficient, but it also prints out from Alice Springs, 
to Alice Springs, on that second line. 
Using this loop limits us to looping through the elements, one at a time, 
from the very first to the last. 
The traditional for loop is much more flexible if we don't want to loop through every element one at a time. 
But now, let's look at another way to do this without using either of the for loops. 
I'll copy and paste this method and rename it _printItinerary3_:

```java
public static void printItinerary3(LinkedList<String> list) {
    System.out.println("Trip starts at " + list.getFirst());
    String previousTown = list.getFirst();
    ListIterator<String> iterator = list.listIterator(1);
    while (iterator.hasNext()) {
        var town = iterator.next();
        System.out.println("--> From: " + previousTown + " to " + town);
        previousTown = town;
    }
    System.out.println("Trip ends at " + list.getLast());
}
```

Here, I've made three changes. 
I've set up a local variable with a type of list iterator with type string. 
And I've named it iterator, and assigned it to a method on LinkedList, listIterator. 
After this, I change the for loop to a while loop. 
And we're using a method on ListIterator called hasNext(), 
which will return true if there are more elements to the process. 
After this, we have a local loop variable which we call in town again. 
And we're setting this to the result of another method on the ListIterator, called next(). 
And instead of calling print itinerary2, I'll call printItinerary3.
And running this,

```java
Trip starts at Alice Springs
--> From: Alice Springs to Alice Springs
--> From: Alice Springs to Brisbane
--> From: Brisbane to Darwin
--> From: Darwin to Canberra
--> From: Canberra to Sydney
--> From: Sydney to Hobart
--> From: Hobart to Melbourne
--> From: Melbourne to Toowoomba
Trip ends at Toowoomba
```

We get the same output as before, including the problematic first line that we don't really want.
Let's make one change to this printItinerary3 method. 
When we make that call to the method list iterator on the linked list, we'll call an overloaded version, 
passing the index of the first element we want to process:

```java
ListIterator<String> iterator = list.listIterator(1);
```

And now we'll run this again:

```java
Trip starts at Alice Springs
--> From: Alice Springs to Brisbane
--> From: Brisbane to Darwin
--> From: Darwin to Canberra
--> From: Canberra to Sydney
--> From: Sydney to Hobart 
--> From: Hobart to Melbourne
--> From: Melbourne to Toowoomba
Trip ends at Toowoomba
```
                    
And you'll notice that we don't have that problematic first line 
because this particular method gets an iterator, 
that isn't set before the first element but is set before the second element in between index 0 and 1. 
By now, you must be asking what an iterator or what's a ListIterator to be more specific is.
</div>

## [d. Iterators]()

<div align="justify">
So far, we've mainly used for loops to traverse, or step through elements, in an array or list. 
We can use the traditional for loop and an index, to index into a list. 
We can use the enhanced for loop and a collection, to step through the elements, one at a time. 
But Java provides other means to traverse lists. 
Two alternatives are the Iterator, and the ListIterator.

If you're familiar with databases, you might be familiar with a database cursor, 
which is a mechanism that enables traversal over records in a database. 
An iterator can be thought of as similar to a database cursor. 
The kind of cursor we're referring to here can be described as an object 
that allows traversal over records in a collection.

The Iterator is pretty straightforward. 
When you get an instance of an iterator, you can call the "next" method, to get the next element in the list. 
You can use the _hasNext_ method, to check if any elements remain to be processed. 
In the code, you can see a while loop, which uses the iterators "hasNext" method, 
to determine if it should continue looping. 
In the loop, the "next" method is called, and its value assigned to a local variable, 
and the local variable printed out. 
This would just print each element in a list, but do it through the iterator object.

![image05](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_06_ArrayList_LinkedList_Iterators_Autoboxing/images/image05.png?raw=true)

This diagram shows visually how an Iterator works, using the PlacesToVisit List. 
When an iterator is created, its cursor position is pointed at a position _before_ the first element. 
The first call to the _next_ method gets the first element, 
and moves the cursor position to be between the first and second elements. 
Later calls to the _next_ method move the position of the iterator through the list, as shown, 
until there are _no elements left_, meaning _hasNext = false_. 
At this point, the iterator or cursor position is below the last element. 
We looked at it briefly in code in the last course, but I want to explore this type with you a bit more.

```java
var placesToVisit = new LinkedList<String>();
placesToVisit.add("Sydney");
placesToVisit.add(0,"Canberra");
addMoreElements(placesToVisit);
```

Copying and pasting the code parts from the last course, which we need for this one. 
I'll go on with a new method in our Main class, called testIterator.
I'll create a variable containing our list iterator. 
And loop through it using hasNext.
And loop through it using hasNext. 
Let's print out the element, returned by the next method. 
And then print out the list.

```java
private static void testIterator(LinkedList<String> list) {
    var iterator = list.iterator();
    while (iterator.hasNext()) {
        System.out.println(iterator.next());
    }
    System.out.println(list);
}
```

Here, I have created an iterator variable, assigning it the result of the list.iterator method. 
I have a while loop that checks the iterator.hasNext method, 
which returns true, if the cursor position is before another item to process. 
And I'm calling _iterator.next_, and passing the result directly to the println statement, 
so these will print every element in the list. 
Back to the main method,

```java
testIterator(placesToVisit);
```

And now add the call to testIterator, passing it placesToVisit. And if I run that,

```java
Alice Springs
Brisbane 
Darwin
Canberra
Sydney
Hobart
Melbourne
Toowoomba
[Alice Springs, Brisbane, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
```
                    
We first get our places printed out one by one, and then the full list. 
One of the benefits of an iterator is that you can use it to modify the list 
while you're iterating through it. 
Let's say we wanted to remove all instances of Brisbane from our list, and our list could contain duplicates. 
I'll add that code to the test Iterator method:

```java
private static void testIterator(LinkedList<String> list) {
    var iterator = list.iterator();
    /*while (iterator.hasNext()) {
        System.out.println(iterator.next());
    }
    System.out.println(list);*/

    while (iterator.hasNext()) {
        if (iterator.next().equals("Brisbane")) {
            iterator.remove();                   
            list.remove();
            iterator.add("Lake Wivenhoe");
        }
    }
}
```

Here, we just have an if condition that checks if the value we get back from the next method equals Brisbane. 
If It does, we execute iterator.remove. 
Running that,

```java
[Alice Springs, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
```

You can see this works, and Brisbane was removed from the list. 
But what If I tried to remove Brisbane directly from the list at this point. 
I'll change _iterator.remove_ to _list.remove_. 
If I run this:

```java
Exception in thread "main" java.util.ConcurrentModificationException
```

We get a _ConcurrentModificationException_. 
You'd get this same error if you tried to do something similar in an enhanced for loop. 
The iterator provides a safe way to remove elements, while still iterating through the list, 
so it's important, to make sure you're calling remove on the iterator object, 
and not the list object. 
I'll revert that last change. 
This type of iterator only allows us to move forward through the elements. 
This means we can only call the next method on this iterator instance. 
And the only method available for mutating elements in this iterator is the remove method, 
which I just showed you. 
There is another iterator, the ListIterator, that gives us additional functionality.
</div>

### Iterator & ListIterator

<div align="justify">
An Iterator is forwards only, and only supports the "remove" method. 
A ListIterator can be used to go both forwards and backwards, 
and in addition to the "remove" method, it also supports the "add" and "set" methods. 
I'll make a minor change to our code. Instead of calling the iterator method, 
I'll change that to call the listIterator method on list, which gives us a ListIterator.

```java
private static void testIterator(LinkedList<String> list) {
    var iterator = list.listiterator();

    while (iterator.hasNext()) {
        if (iterator.next().equals("Brisbane")) {
            iterator.remove();                   
            list.remove();
            iterator.add("Lake Wivenhoe");
        }
    }
}
```

And running this,

```java
[Alice Springs, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
```

We get the exact same results. 
But now, I can change remove to add, adding a new place to visit after Brisbane. 
Instead of removing Brisbane, this code _iterator.add("Lake Wivenhoe");_ 
adds _Lake Wivenhoe_, to our places to visit, after Brisbane.
Again, it's really important to note, we're using the add method on the ListIterator, 
and NOT on the list itself. 
And If we run it:

```java
[Alice Springs, Brisbane, Lake Wivenhoe, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
```

We can confirm that "Lake Wivenhoe" was added to the list, immediately following Brisbane. 
Now, after the while loop, what happens if I want to loop through the elements again? 
I'll add another while loop, just after the first, and loop through the elements again, 
just printing them out. 
We'll create a while loop, based on our iterators, hasNext method.

```java
private static void testIterator(LinkedList<String> list) {
    var iterator = list.listiterator();

    while (iterator.hasNext()) {
        if (iterator.next().equals("Brisbane")) {
            iterator.remove();                   
            list.remove();
            iterator.add("Lake Wivenhoe");
        }
    }
    
    while (iterator.hasNext()) {
        System.out.println(iterator.next());
    }
    System.out.println(list);
}
```

If I run this code:

```java
[Alice Springs, Brisbane, Lake Wivenhoe, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
```

We don't get an error, but we don't get any places printed out individually. 
And that's because _hasNext_, after that first while loop is false, and the second loop never executes. 
If we wanted to loop through this iterator again, we couldn't use this same iterator instance
to move forward anymore, not in its current state. 
And we can't simply reset it to the beginning. 
We could get a new instance by calling the listIterator method again,
or we could move backwards. 
I'll try moving backwards here, so I'll change hasNext to hasPrevious in the while condition.

```java
private static void testIterator(LinkedList<String> list) {
    var iterator = list.listiterator();

    while (iterator.hasNext()) {
        if (iterator.next().equals("Brisbane")) {
            iterator.remove();                   
            list.remove();
            iterator.add("Lake Wivenhoe");
        }
    }
    
    while (iterator.hasNext()) {
        System.out.println(iterator.next());
    }
    System.out.println(list);

    while (iterator.hasPrevious()) {
        System.out.println(iterator.previous());
    }
    System.out.println(list);

}
```

In this code, we're actually iterating backwards through the list, and printing each town. 
And If I run this:

```java
Toowoomba
Melbourne
Hobart
Sydney
Canberra
Darwin
Lake Wivenhoe
Brisbane
Alice Springs
[Alice Springs, Brisbane, Lake Wivenhoe, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
```
                
You can see in the output; Toowoomba is printed first, and Alice Springs last. 
After running this, we're at the top of the cursor again, at the start of the list. 
Another thing we can do is call the listIterator method, and pass the cursor position we want to start at. 
I demonstrated this in the last course, but I'll show it here again. 
I'll do this, creating a new iterator after the last statement. 
It's really important to understand that the positions of the cursor of iterator are between the elements.

```java
var iterator2 = list.listIterator(3);
System.out.println(iterator2.next());
```

This is a way to get an iterator, with the cursor positioned in some other place, 
other than the default, which is prior to the first element.
We've specified position 3, so the cursor is placed between the elements at index 2 and index 3. 
Running this:

```java
Toowoomba 
Melbourne
Hobart
Sydney
Canberra
Darwin
Lake Wivenhoe
Brisbane
Alice Springs
[Alice Springs, Brisbane, Lake Wivenhoe, Darwin, Canberra, Sydney, Hobart, Melbourne, Toowoomba]
Darwin
```

We get Darwin printed out. 
Let's change next to previous. 

```java
var iterator2 = list.listIterator(3);
System.out.println(iterator2.previous());
```

And running that,

```java
... (same)
Lake Wivenhoe
```

We get Lake Wivenhoe. 
It's really important to understand that the positions of the cursor of iterator are between the elements.

![image06](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_06_ArrayList_LinkedList_Iterators_Autoboxing/images/image06.png?raw=true)

When the iterator is at position 0, or the start, it's not pointing at element 0. 
The code shows an iterator for this list. 
We get Alice Springs when we first call _iterator.next_, and 
that moves the cursor of iterator to cursor position 1, meaning after Alice Springs. 
Another call to iterator next returns to Brisbane, 
and moves the cursor to cursor position 2 or between Brisbane and Darwin. 
But if we decide to reverse positions, and call previous here, we get Brisbane 
because the cursor position was 2 when we made this call. 
Traversing both backwards and forwards through a collection, 
using a listIterator, is a little tricky because of this. 
But if you remember that the cursor is always between elements, then you'll be able to keep it straight.
</div>

## [e. Iterators Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_06_ArrayList_LinkedList_Iterators_Autoboxing/Course06_LinkedListChallenge/README.md#linkedinlist-challenge)

<div align="justify">
LinkedList challenge is to use LinkedList functionality, to create a list of places, 
ordered by distance from the starting point. 
And we want to use a ListIterator, to move, both backwards and forwards, 
through this ordered itinerary of places.

First, create a type that has a town or place name, and a field for storing the distance from the start. 
Next, create an itinerary of places or towns to visit, much like we've been doing in the last few courses.

| Town          | Distance from Sydney (in km) |
|---------------|------------------------------|
| Adalaide      | 1374                         |
| Alice Springs | 2771                         |
| Brisbane      | 917                          |
| Darwin        | 3972                         |
| Melbourne     | 877                          |
| Perth         | 3923                         |
    
But this time, instead of Strings, you'll want to create a LinkedList of your place or town type. 
Here we show a list of a few places in Australia, and their distances from Sydney. 
You'll create a LinkedList, ordered by the distance from the starting point, in this case Sydney. 
Sydney should be the first element in your list. 
You don't want to allow duplicate places to be in your list for this data set.

In addition, you'll create an interactive program with the following menu item options. 
The menu will have options to move forwards and backwards your itinerary, to list the itinerary, 
and print menu options and quit the program.

```java
Available actions (select word or letter):
(F)orward
(B)ackward
(L)ist Places
(M)enu
(Q)uit
```

You'll want to use a Scanner, and the nextLine method, to get input from the console. 
You'll use a ListIterator to move forwards and backwards, through the list of places on your itinerary.
</div>

## [f. Autoboxing And Unboxing]()

<div align="justify">
Why does Java have primitive data types? Some object-oriented languages 
don't support any primitive data types at all, meaning everything is an object. 
But most of the more popular object-oriented languages of the day, 
support both primitive types and objects, as does Java. 
Primitive types generally represent the way data is stored on an operating system. 
Primitives have some advantages over objects, especially as the magnitude, 
or number of elements increase. 
This is why many of the object-oriented languages chose not to be purist on this point, 
and continue to provide support for primitive types. 
Objects take up additional memory, and may require a bit more processing power. 
We know we can create objects, with primitive data types as field types,
for example, and we can also return primitive types from methods. 
So we can mix and match primitives with objects pretty easily.

But when we look at classes like the ArrayList, or the LinkedList, 
which we've reviewed in a lot of details in this section, 
these classes don't support primitive data types, as the collection type. 
In other words, we can't do the following, creating a LinkedList, using the int primitive type.

```java
LinkedList<int> myIntegers = new LinkedList<>();
```

This code won't compile. 
This means we can't use all the great functions Lists provided with primitive values. 
More importantly, we can't easily use primitives, in some of the features
we'll be learning about in the future, like generics. 
But Java, as we know, gives us wrapper classes for each primitive type. 
And we can go from a primitive to a wrapper, which is called boxing, or a wrapper to a primitive, 
which is called unboxing, with relative ease in Java.

![image08]()

A primitive is boxed or wrapped, in a containing class, whose main data is the primitive value. 
Each primitive data type has a wrapper class, as shown on the list, which we've seen before. 
Each wrapper type boxes a specific primitive value.

```java
Integer boxedInt = Integer.valueOf(15);
```

How do we box? 
Each wrapper has a static overloaded factory method, valueOf(), 
which takes a primitive, and returns an instance of the wrapper class. 
You'll remember that factory methods are often used instead of the "new" keyword, 
to get a new instance of a class. 
The code shown above, returns an instance of the java.lang.Integer class, 
to the boxedInt variable, with the value 15 in it. 
We can say this code "manually boxes" a primitive integer.

### Deprecated Boxing using the wrapper constructor

Another manual way of boxing, which you'll see in older code, 
is by creating a new instance of the wrapper class, using the "new" keyword,
and passing the primitive value to the constructor.
We show an example of this here.

```java
Integer boxedInt = Integer.valueOf(15);
```

If you try this in IntelliJ, with any Java version greater than JDK-9, 
IntelliJ will tell you, this is deprecated code. 
**Deprecated code** means it's older, and it may not be supported in a future version. 
In other words, you should start looking for an alternate way of doing something if it's been deprecated.

### Using new (with a constructor) is deprecated for wrappers

Java's own documentation states the following:
* It is rarely appropriate to use this constructor.
* The static factory **valueOf(int)** is generally a better choice, 
as it is _likely to yield significantly better space and time performance_.

```java
Integer boxedInt = new Integer(15);
```

This deprecation applies to all the constructors of the wrapper classes, not just the Integer class. 
In truth, we rarely have to manually box primitives because Java supports something called _autoboxing_.

### What is autoboxing?

We can simply assign a primitive to a wrapper variable, as we show below.

```java
Integer boxedInt = 15;
```

Java allows this code, and it's actually preferred to manually boxing. 
Underneath the covers, Java is doing the boxing. 
In other words, an instance of Integer is created, and its value is set to 15. 
Allowing Java to autobox is preferred to any other method, 
because Java will provide the best mechanism to do it.

```java
Integer boxedInt = 15;
int unboxedInt = boxedInteger.intValue();
```

Every wrapper class supports a method to return the primitive value it contains.
This is called unboxing.
In the example above, we've autoboxed integer value 15, to a variable called boxedInteger.
This gives us an object, which is an Integer wrapper class, and has the value of 15. 
To unbox this on an Integer class, we can use the intValue method, which returns the boxed value, 
the primitive int. This method is called manually _unboxing_. 
And like boxing, it's unnecessary to manually unbox.

### Automatic Unboxing

Automatic unboxing is really just referred to as unboxing in most cases. 
We can assign an instance of a wrapper class directly to a primitive variable. 
The code below shows an example.

```java
Integer boxedInteger = 15;
int unboxedInt = boxedInteger;
```

We're assigning an object instance to a primitive variable in the second statement. 
This is allowed because the object instance is an Integer wrapper, 
and we're assigning it to an int primitive type variable. 
Again, this is the preferred way to unbox a wrapper instance. 
Let's get back to some code now, and show different examples of when this feature can be used.

There are a lot of ways Java supports autoboxing and boxing, 
and we've shown you a couple of simple examples on above. 
I want to review these in code, but I want to also get you thinking about 
other places this feature can be used. 
And I'll start with the examples above, for manually boxing and autoboxing.

```java
Integer boxedInt = Integer.valueOf(15);             // preferred but unnecessary
Integer deprecatedBoxing = new Integer(15);      // deprecated since JDK 9
int unboxedInt = boxedInt.intValue();                 // unnecessary
```

Ok, so the reason I'm showing you these three statements is you'll recognize a couple of things. 
The first two statements are attempting to manually box the primitive int 15 
to an instance Integer wrapper class. 
You'll notice that IntelliJ is trying to attract your attention. 
Depending on your configuration of IntelliJ, either some parts or everything 
after the assignment operator is highlighted. 
In my case, you can see _valueOf_ is highlighted. 
Hovering over the highlighted part of that first statement, 
IntelliJ tells us, this is _Unnecessary boxing_, and we've commented that as well. 
Now, check out the second statement. 
IntelliJ has, for me, placed an error underneath the Integer class,
but you may find it has crossed out the Integer class, 
in addition to highlighting everything after the assignment operator. 
Although it's an error, it won't stop from running. 
What IntelliJ says for that is _**Integer(int)** is deprecated and marked for removal_. 
And I've discussed that, and how this particular method is discouraged. 
We won't use it again, but you should be familiar with it if you run into legacy code. 
Lastly, the third statement is also highlighted by IntelliJ, 
and this message says it's unnecessary unboxing. 
Remember that unboxing means you're returning the primitive value,
unboxing the primitive. 
I'll leave those three statements in, but I'll add the way you should write this:

```java
// Automatic
Integer autoBoxed = 15;
int autoUnboxed = autoBoxed;
System.out.println(autoBoxed.getClass().getName());
System.out.println(autoUnboxed.getClass().getName());
```

Here we have two declarations, the first assigns 15 to an Integer variable. 
The second assigns the variable, autoBoxed, to an int variable. 
Then we print out both variables class names, attempting to use the getClass method, 
chained to _getName()_.
Notice that the last statement is a compiler error.
We can use int variable types in many different places, but not in this way. 
The JVM knows autoUnboxed is a primitive type, and we can't use methods on primitive data types like this. 
Let's comment that last line out, then run this code.

```java
java.lang.Integer
```
                
And hopefully, no surprises. 
The output shows that the autoBoxed variable is of type _java.lang.Integer_. 
This example is a bit forced, because it doesn't quite explain why you'd want boxing or unboxing, 
automatically or otherwise.
Let's create a couple of methods, one that returns a double primitive type, 
and one that returns a Double wrapper instance.

```java
private static Double getDoubleObject() {
    return Double.valueOf(100.00);
}

private static double getLiteralDoublePrimitive() {
    return 100.0;
}
```

In this code, we use the static factory method, _Double.valueOf_, 
just to make it clear that in this case, we're returning an object of type Double. 
In the second method, I want to return a double primitive value.
And this method is pretty straightforward.
We just return a literal decimal number, because that's a Java double primitive.
And going back to the main method, we'll call these methods.

```java
Double resultBoxed = getLiteralDoublePrimitive();
double resultUnboxed = getDoubleObject();
```

Now in this code, autoboxing and auto unboxing are again occurring, 
but not by the assignment of simple variables or literals.
The method getLiteralDoublePrimitive, returns a primitive data type, 
but the JVM is automatically boxing it 
because it's being assigned to a variable of the java.lang.Double class. 
And the same is true in reverse. 
The method getDoubleObject returns an object instance, 
but because we're assigning it directly to a primitive variable,
the unboxing occurs automatically. 
I hope you can see how that's helpful, being able to see method results,
and autoboxing when you want to assign the results to variables.

Next, I want to focus on this feature and how it applies to arrays, 
variable arguments, and Lists like ArraysList and LinkedList.
First, I want to create an array of the Integer wrapper class in the main method.

```java
Integer[] wrapperArray = new Integer[5];
wrapperArray[0] = 50;
System.out.println(Arrays.toString(wrapperArray));
```

In this code, we create an array of Integer wrappers, and set the array size to 5. 
Then we assign the first value, value 50, and this auto boxes the primitive integer literal, 
50, to an instance of an Integer class.
And we print this array out, using the Arrays helper class, 
and the toString method.
And running this code,

```java
[50, null, null, null, null]
```
                
We can see the array was initialized to null.
Remember, the Integer wrapper classes are really classes, and their default values in arrays 
are null references.
But the first element prints as 50. And just for good measure,
let's confirm we can print out the object type of that first element.
I'll use get class and get name, to print out the type of the element.

```java
System.out.println(wrapperArray[0].getClass().getName());
```

And running that,

```java
java.lang.Integer
```
                
We can confirm that the first element is _java.lang.Integer_.
So the JVM auto-boxed the literal number 50, before assigning it to an element in the array.
We can also use autoboxing with an array initializer,
which I'll do next.
I'll create a character array, with the elements, abc and d.
And then I'll print out the array.

```java
Character[] characterArray = {'a', 'b', 'c', 'd'};
System.out.println(Arrays.toString(characterArray));
```

Here, we've got an anonymous array initializer that sets up an array of the Character wrapper class.
And then we want to print that array out. 
Running that,

```java
[a, b, c, d]
```

We don't get any kind of runtime errors, and we get those characters printed out as a result.
So this is an example of using an array initializer, using char literals, 
and the JVM auto boxes each of those values.
Before we look at Lists, let's visit auto boxing and unboxing when it comes to method declarations.
First, you should know that this feature is supported within methods themselves, 
both with method parameters, and method return types.
I'm going to create a few more private static methods, 
which should demonstrate auto boxing, in a few different ways.

```java
private static int returnAnInt(Integer i) {
    return i;
}
```

In this code, we take a java.lang.Integer parameter as an argument, 
and simply return it from this method, whose return type is an int primitive.
So again, this unboxes the i argument to return the primitive value boxed in the wrapper argument,
as the return value from this method.
Let's try things the other way around.
Similar to before, but return as integer this time, and it returns an Integer.

```java
private static Integer returnAnInteger(int i) {
    return i;
}
```

This method takes an int parameter, and returns it from a method;
that's got a declared return type, of _java.lang.Integer_.
An object is expected to get returned, in other words.
So eye is auto boxed, to an instance of the Integer wrapper class,
and returned, without us having to worry about or write that code.
Putting another twist on this, we can invoke these methods, 
passing boxed and auto-boxed values to these methods.
I won't invoke these methods from the main method.
Instead, let's extend this a little, creating a method that 
takes a variable argument of int primitives, as one of its parameters.
And we will use this method in code.
The method will return an ArrayList of Integer, and I'll call it get list.

```java
private static ArrayList<Integer> getList(int... varargs) {
    ArrayList<Integer> aList = new ArrayList<>();
    for (int i : varargs) {
        aList.add(i);
    }
    return aList;
}
```

This method is declared with a single parameter, a variable arguments declaration, 
for the int primitive.
This means this method can be called with zero to many int values.
Inside the method, we create a new instance of an ArrayList, 
with the Integer wrapper class as its type, in < >.
We can't put just the int type in there (no <int>), sp this is a good place for auto boxing.
We use a for loop, to iterate over the values we got passed to the method, in the varargs argument, 
and add these primitives to our ArrayList. 
Now the _add_ method on an ArrayList is declared with an Integer wrapper parameter type, 
not a primitive type, so we're really showing many kinds of auto boxing here.
Finally, we return the list we created from this method. 
And we want to call that from our main method.

```java
var ourList = getList(1, 2, 3, 4, 5);
System.out.println(ourList);
```

I'm purposely using the **var** keyword here, because I want you to see the IntelliJ hint. 
I have this turned off at the moment, so nothing is showing.
Let me show you how to have it show in IntelliJ.

Come up here to File and select Settings. 
Type _inlay_ and click into "_Type_". 
Then click into Java, and check Implicit Types, which I will do now. 
Finally, I'll click _OK_ to go back to the code and notice the return type hint is showing now.

```java
var ourList : "ArrayList<Integer>" = getList(1, 2, 3, 4, 5);
```
                
It shows we're getting back an ArrayList of _Integer_. 
And then we just want to print out that list. 
When we run it:

```java
[1, 2, 3, 4, 5]
```
                
And that just prints out a list of numbers 1 through 5. 
Now, what if I change this method to take a variable argument of Integer wrapper class?

```java
private static ArrayList<Integer> getList(Integer... varargs)
```

With this change, everything still compiles and runs; even though in the main method, 
we're passing literal int values.
This method's expecting Integer instances, but the JVM will again auto box everything 
in that variable argument list.
In a way, this means we can almost use primitives, and their corresponding wrappers, 
interchangeably.
What this means is that, for the factory objects that create lists, 
we can create lists from primitive literals, as we did in line 226 of the main method. 
We can simply replace this call to our custom method with the List of factory methods, 
which we've talked about previously.

```java
var ourList : "List<Integer>" = List.of(1, 2, 3, 4, 5);
```

Remember this specific method "List.of" returns an unmodifiable list. 
Note when I do that, the inlay hints updates to show a new return type from the List of factory methods.


</div>