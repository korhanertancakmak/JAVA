# [Section-5: Arrays]()

## [a. Arrays]()

<div align="justify">
Up to now, what we haven't really discussed though, is a way to have multiple values, 
all the same type. 
We had a taste of this kind of problem in the Bill's Burger challenge. 
We had several toppings for our burger, and we had to create individual attributes. 
This led to inefficient code and other limitations. 
For example, what if we needed ten toppings? 
There are much better ways to handle this. 
Java provides us with many types of containers to store multiple values of the same type. 
These start with the most basic, which is the array, and that's what this section will cover.

Let's look at ways to store, and manipulate, multiple values of the same type. 
The most common way to do this in Java is with an array. 
An array is a data structure that allows you to store a sequence of values, 
all the same type. 
You can have arrays for any primitive type, like ints, doubles, booleans, 
or any of the eight primitives we've learnt about. 
You can also have arrays for any class.

| Index                                     | 0      | 1     | 2         | 3       | 4     |
|-------------------------------------------|--------|-------|-----------|---------|-------|
| Stored values in an array with 5 elements | "Andy" | "Bob" | "Charlie" | "David" | "Eve" |

Elements in an array are indexed, starting at 0. 
If we have an array, storing five names, conceptually it looks as shown above. 
The first element is at index 0 is "Andy."
The last element in this array is at index 4, and has the String value "Eve."

| Array Variable Declaration |
|----------------------------|
| int[] integerArray;        |
| String[] nameList;         |
| String[] courseList[];     |

When you declare an array, you first specify the type of the elements you want in the array. 
Then you include "[]" square brackets in the declaration, 
which is the key for Java, to identify the variable as an array. 
The "[]" can follow the type as shown in the first two examples above. 
This is much more common. 
The brackets can also be after the variable name, as shown in the last example. 
You may see this in some code you run across. 
You don't specify a size in the array declaration itself 
(remember the declaration is usually to the left of an equals or assignment operator, but not always).

| Array Creation                    | Object Creation                         |
|-----------------------------------|-----------------------------------------|
| int[] integerArray = new int[10]; | StringBuilder sb = new StringBuilder(); |

One way to instantiate the array is with the new keyword, much as we've seen, 
with most of the classes we've used until now, except String. 
Above, we have an array declaration on the left of the equal sign, 
and then array creation expression on the right side. 
For comparison, I'm showing you a typical array variable declaration, 
and a class instance, or object creation expression, using the StringBuilder class. 
They look pretty similar, but there are two major differences. 
Square brackets "[]" are required when using the new keyword, 
and a size is specified between them. 
So in this example, there will be 10 elements in the array. 
An array instantiation doesn't have a set of parentheses, 
meaning we can't pass data to a constructor for an array.

```java  
Invalid Array Creation - Compile Error because of "()"
    int[] integerArray = new int[10]();
```

In fact, using parentheses with an array instantiation, gives you a compile error. 
Ok, that's a brief overview of what an array is. 
Let's look at arrays in some code now.

I'll create an int array, called myIntArray and set it to have 10 elements. 
Like we do, when we create any new object, we use the "new" keyword. 
Underneath the covers, a Java array is just a specialized class, so the "new" keyword here creates a new array. 
And now we again need to specify the type, similar to the way we specify the class, 
when creating a new instance.
But this time, we specify the type that all the array elements will be, so this will be int here. 
And next, we include the brackets again, but with a number, the size of the array in the brackets. 
This is the number of slots, or in this case, of separate integers, that we want in this array. 
And it's important to know that an array is not resizable.

The size of an array, once created, is fixed. 
In this case, myIntArray will have 10 elements. 
You can't change the size of an array after the array is instantiated. 
We can't add or delete elements, we can only assign values to 1 of the 10 elements in this array, 
in this example. 
Now, let's see how we'd access the array elements.

```java  
int[] integerArray = new int[10];
myIntArray[5] = 50;
```

I'll set element 6 of myIntArray to int value 50. 
This statement assigns value 50 to one of the array elements. 
You might assume that this code is assigning 50 to element 5, but it's actually saving it to element 6.
The reason for this is, as with all arrays in Java, indexing starts with position 0, not 1, 
as I've stated on above. 
And for the last element, it actually used index 9 in this case. 
Instead of starting from 1, and finishing on 10 for this array, you start from 0, 
and finish on 9. 
This may be confusing at first, but you'll soon get used to it.

Arrays can be any primitive type, so we could create an array to contain doubles. Let's do that next.

```java  
double[] myDoubleArray = new double[10];
myDoubleArray[2] = 3.5;
```

And I'll set the third element of my double array to the value 3.5. 
And here, we're creating an array called myDoubleArray, with room for 10 doubles. 
And we set the third element in that array, to a floating point number, a decimal number, 3.5.
Remember that any decimal number literal like we're using here, is really a double in Java. 
Retrieving a value from the array is done in the same way, 
using the position of the element in the array.
Let's print out the third element of the double array we just created.

```java  
System.out.println(myDoubleArray[2]);
```

Again, we call it third element, but we use index 2, because Java starts counting at zero for arrays. 
And we get 3.5 printed out, which we assigned to that element. 
Like any variable, whatever type you define the variable to be, 
that's the type of data that can be assigned to that array. 
You can't put a double into an integer array, in much the same way you can't assign a double value
to an integer variable.

```java  
myIntArray[0] = 45.0;
myIntArray[1] = "1";
```

Now, let's assign some more data to the first 2 elements of the integer array. 
Set the first element of myIntArray to double value 45.0. 
And set the second element to string literal "1." 
In this code, I've purposely created a couple of errors. 
I declared this array to store integers, and here, silly me, 
I'm trying to assign other types of values to the array elements. 
You can see we have compiler errors here, because Java doesn't allow this. 
In the first case, we have a double, and that can't be assigned to an int element.

In the second case, we're trying to assign a String literal to an int variable, 
which is what each array element really is. 
Imagine assigning values this way, one at a time like this, for the other seven elements. 
You can see this gets tedious really fast. 
Java provides another shortcut for creating an array. 
If we know the values for all the elements, at the time we create the array, 
we can use an array initializer.

```java  
int[] firstFivePositives = new int[]{1, 2, 3, 4, 5};
```

An array initializer makes the job of instantiating and initializing a small array, much easier. 
In this example, you can see we still use the "new" keyword, and have int, with "[]". 
But here we specify the values, we want the array to be initialized to, in a comma delimited list, in {}. 
Because these values are specified, the length of the array can be determined, 
so we don't specify the size in the []. 
And actually, Java provides an even simpler way to do this.

```java  
int[] firstFivePositive = {1, 2, 3, 4, 5};
String[] names = {"Andy, "Bob", "Charlie", "David", "Eve"};
```

The array can be initialized as an anonymous array. 
Java allows us to drop "new int[]", from the expression, as shown above. 
This is known as an anonymous array. 
Here we're showing example for both an int array and a String array. 
An anonymous array initializer can only be used in a declaration statement. 
I'll explain what that means in a minute.

```java  
int[] firstTen = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
System.out.println("first = " + firstTen[0]);
int arrayLength = firstTen.length;
System.out.println("length of array = " + arrayLength);
```

This time, we're using an array initializer to create an array with values 1 through 10 in it. 
I'll tell Java it's an integer array called first 10 and assign values 1 through 10 in curly braces. 
I'll print out the firstTen arrays first elements value. 
Let's save first tens length to a new variable array length, and print it out using println.
If we run this code:

```java  
3.5
first = 1
length of array = 10
```

Let's add another line of code, to get the last element, using the length of the array. 
But if we run this though, we get an exception. 
This is an ArrayIndexOutOfBoundsException, and you can see the message, 
Index 10 out of bounds for length 10. 
This is a common error to make. 
We've been taught to think, starting with one, not 0, so it's easy to forget 
that Java starts indexing with 0. 
Because it starts with 0, all indices are shifted by 1. 
The last element in an array that has 10 elements is at index 9. 
We need to subtract 1 from the length to get the last index.

```java  
int[] newArray;
//newArray = {5, 4, 3, 2, 1};

newArray = new int[] {5, 4, 3, 2, 1};
```

Now I've said this array initializer can only be used in declaration assignments, 
or method arguments, so let's review what that means for a moment. 
With this code above, we've got a compiler error. 
If we hover our mouse over that statement, we see that "Array Initializer is not allowed here." 
This means you can't use the anonymous version of the array initializer 
in a statement outside a declaration statement. 
But we can use the longer form, including "new int[]", before the values in {}. 
And that code compiles. 
Once you have an array, you can loop through the elements, retrieving each value 
with the loop index. 
We can do this with the traditional for loop:

```java  
for (int i = 0; i < newArray.length; i++) {
    System.out.print(newArray[i] + " ");
}
```

What we're doing there is; we're looking through each element, starting from element zero. 
We start with i = 0, incrementing by one, while "i" is < newArray.length. 
And we print out each element, using the loop index to get the current element. 
We're just printing all of those on a single line, with a space between each. 
And running that:

```java  
3.5
first = 1
length of array = 10
last = 10 
5 4 3 2 1
```

An array is a special class in Java. 
The array, like all other classes, ultimately inherits from java.lang.Object. 
When you don't use an array initializer statement, 
all array elements get initialized to the default value for that type. 
For primitive types, this is "zero" for any kind of "numeric primitive," like int, 
double or short. 
For "booleans," the default value will be "false." 
And for any class type, the elements will be initialized to "null." 
Now, let's use the basic for loop to assign the values, as we had them before, in reverse order.

The loop set up is the same as before, but inside the loop, 
you can see we're assigning each element in the loop.
We're using the "[]", and the loop variable "i," within the [], 
to assign a value to each array element. 
And it gets assigned whatever the value will be when the current index is 
subtracted from the length of the array. 
When this is 0 to start, we get the length of the array as the first value, 
which is 5. 
And when we reach the max value, which I've said was 4, the element will get value 1.

Java has another for statement, they call it "the enhanced for loop," 
but it's also sometimes known as "the for-each loop." 
This loop was designed to walk through elements in an array, or some other type of collection. 
It processes one element at a time, from the first element to the last. 
Here I show you the syntax for the two types of for loop statements, side by side:

| Enhanced For Loop                                                                                | Basic For Loop                                                                                        |
|--------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| for (declaration : collection) {<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// block of statement<br/> } | for (init; expression; increment) {<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// block of statement<br/> }   |

The enhanced for loop only has two components, instead of three, defined in the parentheses 
after the for keyword. 
It's important to notice that the separator character between components is a colon(:), 
and not a semicolon(;), for the Enhanced for loop. 
The first part is a declaration expression, which includes the type and a variable name. 
This is usually a local variable with the same type as an element in the array. 
This is usually a local variable with the same type as an element in the array. 
And the second component is the array, or some other collection variable. 
Let's see this for loop in action.

```java  
System.out.println();
for (int element : newArray) {
    System.out.print(element + " ");
}
```

First, we just printed a new line with println because the previous output didn't include it. 
Now we've got the enhanced for loop code. 
Notice that it looks similar to a traditional for loop, 
but it uses a colon, and not semicolons, and expects only two components in the parentheses. 
The first component just needs to be a declared variable. 
In this case, we're looping through an array of integers, so we want to set this variable to an int type. 
And here, rather than just the letter "i" I'm purposely using something more descriptive, element. 
This is because the variable is the actual element, it's not the array index we're looping through. 
And the second part, on the right side of the colon, in the parentheses, is the thing we're looping over. 
In this case, it's our integer array, newArray. 
And now, in the print statement, we don't have to use an index to get the element from the array. 
The enhanced for loop gets it for us, and stores it in that local variable, element. 
In this case, we don't have to know what the index is at all. 
If we wanted it or needed it, we'd probably use the traditional for loop. 
If I run that:

```java  
5 4 3 2 1
```

We get the exact same output, the values 5 through one are printed out. 
That's rather a nice thing, because you don't have to deal with the array index position. 
The enhanced for loop gives you a simpler, less error-prone option, 
if all you need to do is process the elements from start to finish, 
and you aren't trying to set, swap, or sort elements. 
What if we try to print the whole array with println?

```java  
System.out.println();
System.out.println(newArray);
```

And if we run that:

```java  
[I@214c265e
```

We get this strange thing printed out, and not a neatly formatted list of our array elements. 
Yours might look different from mine, but that's okay. 
I'll explain the numbers and letters shortly. 
You'll remember this is Java's toString method that gets called. 
And I've said our array is really just a special Java class. 
But the array class doesn't print the elements out neatly for us, as we might hope. 
In other words, it doesn't override the toString method, so it uses java.lang.Object's toString method. 
And this gives us the left square bracket "[" with a capital "I," 
which means it's a primitive integer array, and then it's followed 
by the hexadecimal representation of the hash code. 
This is not likely to be very helpful to you, as you examine and manipulate your array. 
However, Java does provide a helper class, which contains a lot of static methods, 
which we can use for arrays. 
And this class is the java.util.Arrays class.
        
Java's array type is very basic, it comes with very little built-in functionality. 
It has a single property or field, named length. 
And it inherits java.util.Object's functionality. 
Java provides a helper class named java.util.Arrays, providing common functionality 
you'd want for many array operations. 
These are static methods on Arrays, so are class methods, not instance methods. 
For now, we'll use one method on this class to print out the elements in our array, 
and this method is Arrays.toString(). 
The toString() method on this helper class, prints out all the array elements, 
comma delimited, and contained in square brackets.

```java  
String arrayElementsInAString = Arrays.toString(newArray);
```

The output from this method is shown here, conceptually:

```java  
[  e[0], e[1], e[2], e[3], ... ]
```

It prints the element at index 0 first, followed by a comma, 
then element at index 1 next, comma, and so on, until all the elements are printed. 
Let's see what this looks like for our integer array.

```java  
System.out.println(Arrays.toString(newArray));
```

And if we run that:

```java  
[5, 4, 3, 2, 1]
```

That's a handy method, which may be all you need
if you just want your elements printed in a single string this way. 
One other thing, you should know, is that you can assign any array to an Object variable. 
Let's look at this more closely.

```java  
Object objectVariable = newArray;
if (objectVariable instanceof int[]) {
    System.out.println("objectVariable is really an int array");
}
```

In this code, we're assigning an integer array reference to an object reference. 
And notice here; we can still use the instanceof operator, 
using an array type on the right side of the operand. 
This operand will test if our Object variable is really an instance of an integer array. 
An array is a special class, recognized by Java through the use of the "[]". 
It still inherits from the java.lang.Object class. 
You might ask why we'd do something like this? 
Well, in fact, you could create an array of Objects that support any kind of type in Java. 
Let's look at that now:

```java  
Object[] objectArray = new Object[3];
objectArray[0] = "Hello";
objectArray[1] = new StringBuilder("World");
```

In this code, we're creating an array of Objects, and this array will hold three object references. 
And now, we can assign any object reference, of any type, to the elements in this array. 
First, we assign it a String literal, and then we assign it a StringBuilder instance. 
You could assign an object of any of your own classes to this kind of array. 
This is one way of having an array of many different types. 
I'm not saying this is good practice, but I did want to point out that you could do this with arrays. 
Later, we'll learn more elegant ways to support different types in arrays.
And because we know an array is an object, you can assign an array to be an element 
of this type of Object array.

```java  
objectArray[2] = newArray;
```

We've got an array of Objects, and one of the elements is an array of integers. 
This is a nested array. 
We'll be looking at this in more detail when I talk about multidimensional arrays. 
But what's important for you to understand right now is, 
an array can be treated like any other object instance in Java. 
So those are the basics for the array.

## [b. java.util.Arrays Class and Binary Search]()

### java.util.Arrays Class

We use arrays to manage many items of the same type. 
Some common behavior for arrays would be sorting, initializing values, copying the array, and finding an element. 
For an array, this behavior isn't on the array instance itself, but it's provided on a helper class, 
java.util.Arrays. 
Also, we saw an example that using toString method.

The first thing I want to do is, create another method on this class, which will return an array of random integers.
I'll call this method getRandomArray, and it'll take the length of the array to be created, as the only argument.

```java  
private static int[] getRandomArray(int len) {
    Random random = new Random();
    int[] newInt = new int[len];
    for (int i = 0; i < len; i++) {
        newInt[i] = random.nextInt(100);
    }
    return newInt;
}
```

The first thing you notice here is the first statement in the method, 
where we are creating a variable of type Random. 
Random is another class in the java.util library that can be beneficial. 
This class has methods on it to return random numbers, which we'll be using shortly. 
You can see we're also creating a new array, and setting it to the size; that was passed in, 
as the methods, argument. 
This new array gets returned from this method. 
Next, I want to assign a random number to each element in the array.

Since we're assigning values to the array elements, we use traditional for loop. 
Each element gets assigned the result of the random.nextInt method, which returns a random integer values. 
If you don't pass any argument to this nextInt method, it returns any number, from 0 to the maximum integer value. 
But we want to limit the random numbers, so we pass 100. This argument is called the exclusive upper bound. 
This method returns a random number, between 0 and 99, and it excludes 100. 
If we wanted 100 to be included in the random selection, we'd specify 101, and so on. 
Our new method, getRandomArray, returns a new array of the length we specify, and fills it with random numbers. 
Going back to the main method, I want to call this method, to get a random array and then print it out.

```java  
int[] firstArray = getRandomArray(10);
System.out.println(Arrays.toString(firstArray));
```

Here, we create a variable of type int array, and assign it the result of our getRandomArray method. 
And we call Arrays.toString, because it's a static method on Arrays, and we pass our array to it. 
And running this, you can see we get 10 random numbers there.

```java  
[78, 94, 93, 99, 9, 19, 51, 81, 22, 70]
```

Your own numbers will be different, and they'll be different every time you run it. 
This is because they're randomly generated. 
And maybe the first thing you'd like to do, when you get data from any source, is sort it
if it's not already sorted. 
Java provides a sort method for us in this case, on this same Arrays class. 
Let's give that a try. 
I'll call "Arrays.sort" passing a first array and print out the value returned from a call too:

```java  
Arrays.sort(firstArray);
System.out.println(Arrays.toString(firstArray));
```

We call sort directly using the class name, Arrays, and pass it firstArray. 
This method doesn't return anything,
it's void, so we're not getting a new instance of an array back. 
The actual array we pass to it actually gets sorted.
And running that:

```java  
[49, 13, 37, 58, 3, 70, 48, 56, 84, 73][3, 13, 37, 48, 49, 56, 58, 70, 73, 84]
```

We can see our numbers get ordered, from low to high, which is called the natural order, for numeric values. 
Let's say, we want to set all the values in an array, to our own initial value, before processing. 
We want something besides zeros, but we don't want to use an array initializer 
because we want them all initialized with the same value. 
We can do this with the "Arrays.fill" method. 
I'm going to create a new array for this:

```java  
int[] secondArray = new int[10];
System.out.println(Arrays.toString(secondArray));
Arrays.fill(secondArray, 5); 
System.out.println(Arrays.toString(secondArray));
```

This first statement creates a zero filled array, because zero is Java's default for elements in an integer array.
But on the third line, where we call the fill method, it sets every element to 5. 
And running this:

```java  
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[5, 5, 5, 5, 5, 5, 5, 5, 5, 5]
```

We can confirm elements start out as all zeros then it's all 5's. 
Now, maybe you don't really want these changes being made to your original array. 
You can create a copy of the array and apply operations to the copy.
Let's look at how we do that:

```java  
int[] thirdArray = getRandomArray(10);
System.out.println(Arrays.toString(thirdArray));

int[] fourthArray = Arrays.copyOf(thirdArray, thirdArray.length);
System.out.println(Arrays.toString(fourthArray));
```

Again, we're creating a random number array there, on that first line. 
Then we call Arrays.copyOf, which takes the array, and a size as the second argument. 
Since we want an exact copy, we just pass the current size of the array, we want copied. 
Running that:

```java  
[34, 16, 86, 70, 49, 46, 13, 57, 60, 91]
[34, 16, 86, 70, 49, 46, 13, 57, 60, 91]
```

We see that the first and second array have the same elements in the same order.
And now, what if we sort the elements in this copied array, in the fourth array? 
Will this have any effect on the third array, the array we made a copy of? 
Let's test that:

```java  
Arrays.sort(fourthArray);
System.out.println(Arrays.toString(thirdArray));
System.out.println(Arrays.toString(firstArray));
```

And running that:

```java  
[6, 40, 78, 55, 90, 91, 76, 83, 10, 68]
[6, 40, 78, 55, 90, 91, 76, 83, 10, 68]
[6, 40, 78, 55, 90, 91, 76, 83, 10, 68]
[2, 2, 14, 36, 44, 49, 63, 70, 84, 96]
```

We see that fourthArray is sorted, but the array we copied the data from is still in its original state. 
An array copy creates a new array, a new instance of an array, and copies the array elements over to the new array.
For primitives, the values get copied. 
For objects, the object references get copied. 
Performing operations on the copied array, like sort and fill, don't impact the original array. 
Let's see what happens when the length we pass is less than the number of elements in the array.

```java  
int[] smalllerArray = Arrays.copyOf(thirdArray, 5);
System.out.println(Arrays.toString(smalllerArray));
```

Now here, we're passing 5 as the second argument. 
When we run this:

```java  
[39, 34, 49, 26, 73, 53, 81, 18, 87, 27]
[39, 34, 49, 26, 73, 53, 81, 18, 87, 27]
[39, 34, 49, 26, 73, 53, 81, 18, 87, 27]
[19, 24, 24, 40, 48, 49, 54, 73, 79, 92]
[39, 34, 49, 26, 73]
```

You can see this gives us only the first five elements in the copied array. 
And let's test one more example we'll pass a value, greater than the size of the array:

```java  
int[] largerArray = Arrays.copyOf(thirdArray, 15);
System.out.println(Arrays.toString(largerArray));
```

Now here, the length of our array is 10, but we want an array of 15 elements. 
Is this going to work? 
Let's run it:

```java  
[1, 47, 14, 2, 77, 17, 48, 4, 90, 71]
[1, 47, 14, 2, 77, 17, 48, 4, 90, 71]
[1, 47, 14, 2, 77, 17, 48, 4, 90, 71]
[3, 13, 31, 45, 57, 60, 65, 89, 93, 94]
[1, 47, 14, 2, 77]
[1, 47, 14, 2, 77, 17, 48, 4, 90, 71, 0, 0, 0, 0, 0]
```

You can see, from this output, we get our 10 elements copied over, in the order they were when we made the call, 
but we have 5 more elements in this array, initialized to zero. 
This one method can be used for three different types of copies, a full copy, a partial copy, 
and a copy plus additional elements.

### Finding a Match

You can imagine if you were going to start writing code to do this, you might start looping from start to finish,
and check each element, to see it equals what you're looking for. 
If you find a match, you'd stop looping, and return that a match was found, 
either with the position you found the element at, or just a boolean value, true if it was found, and false if not. 
This is called a "linear search," or "sequential," because you're stepping through the elements,
one after another. 
If your elements are sorted though, using this type of linear search, is unnecessarily inefficient.

Imagine looking for the word middle, in the dictionary, starting at the first page, and looking on every sequential
page, until you finally find the page that has the word middle. 
That is not really how you'd look for it though, is it, because you'd waste a lot of time. 
You'd pick a spot in the dictionary, based on the word you're trying to find, 
that you think might be close to where your word might be. 
In this case, it might just be somewhere in the middle of the dictionary. 
You might divide the pages in half, and determine which half included the word middle. 
In other words, you'd figure out which section of the 2, to keep looking in.

### Using intervals to Search:

You split each section up, testing the values at the boundaries, and based on that,
split again into smaller sections, narrowing the number of elements to test each time.
This type of searching, in software, is called "interval searching." 
Within these two categories, sequential and interval, there are many existing algorithms in each. 
One of the most common interval searches is the "binary search," 
which is why Java provides this search on so many of its collection classes. 
In this search, "intervals" are continually "split into 2," hence the word binary. 
If you're a software engineer at heart, you'll have some fun, 
researching these algorithms, and how they differ, and studying the code behind their implementations.
For most of us, we just need to understand that Java gives us methods for searching,
and when and how we should use those methods.

### Arrays.binarySearch:

The static method, binarySearch, is on the Arrays class. 
We can use this method to test if a value is already in our array, 
but there are some "important" things to remember:

* First, the array has to be "sorted"
* Second, if there are duplicate values in the array, there's no guarantee which one it'll match on.
* Finally, elements must be comparable. 
Trying to compare instances of different types may lead to errors and invalid results.

These methods return:

* "The position of a match" if found.
* It returns a "-1" when no match was found.
* It's important to remember that a positive number "may not be the position of the first match."
* If your array has duplicate values, and you need to find the first element, other methods should be used.

```java  
String[] sArray = {"Able", "Jane", "Mark", "Ralph", "David"};
Arrays.sort(sArray);
System.out.println(Arrays.toString(sArray));
if (Arrays.binarySearch(sArray, "Mark") >= 0) {
    System.out.println("Found Mark in the list");
}
```

So, Let's look at this method. 
I'll create a String array called sArray and initialize it with five string literals.
We sort this array because the binary search only works on sorted arrays. 
This is really important and can't be stated enough. 
What good is a dictionary if it's not sorted after all? 
The dictionary allows your brain to do interval searching, because the words are sorted alphabetically. 
A binarySearch can do interval searching successfully, because the elements are sorted. 
A negative number returned from the binarySearch method, means the item wasn't found.
Otherwise, the item was found in the array,
and it returns the position of a match, though it doesn't guarantee which match. 
And running that code,

```java  
[Able, David, Jane, Mark, Ralph]
Found Mark in the list
```

We can confirm that "Mark" is in the list. 
What if we want to compare if two arrays are equal? 
Two arrays are considered equal if both arrays contain the same number of elements, 
and all elements in the same position in both arrays are equal.

```java  
int[] s1 = {1, 2, 3, 4, 5};
int[] s2 = {1, 2, 3, 4, 5};
if (Arrays.equals(s1, s2)) {
    System.out.println("Arrays are equal");
} else {
    System.out.println("Arrays are not equal");
}
```

I'll create two arrays of numbers. 
I'll call them s1 and s2, and initialize these arrays with the values of 1 through 5. 
Now I'll use the equals method on Arrays. 
The equals method on Arrays, takes 2 args, two arrays, whose types have to match, 
so you have to compare 2 int arrays, or two boolean arrays, etc. 
This method returns true or false depending on the arrays are equal or not.
Let's run this.
And we get both arrays are equal. 
But what if one of those arrays have the values in different order?

In the second array, we'll just switch where 1 and five are located. 
Now, if we run it, we see Arrays are not equal. 
This method takes the order of the array elements into account as well.

### [Array Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_05_Arrays/Course02_JavaUtilArraysSortFillCopyOf/TheArrayChallenge.java)

Create a program using arrays, that "sorts" a list of "integers," in "descending order." 
Descending order means from the highest value to lowest.

* First, create an array of randomly generated integers.
* Print the array before and after you sort it.
* You can choose to create a new sorted array, or just sort the current array.

## [c. Reference Types vs Value Types]()

We talked about the differences between a Reference vs. an Object, vs. an Instance, vs. a Class.
I want to revisit this a little, and talk about why this matters when we're talking about arrays. 
When you assign an object to a variable, the variable becomes a reference to that object. 
This is true of arrays, but the array has yet another level of indirection if it's an array of objects. 
This means every array element is also a reference.

```java  
int[] myIntArray = new int[5];
int[] anotherArray = myIntArray;
```

A reference is an address to the object in memory, but not the object itself. 
Stated differently, the variable myIntArray holds a reference or address to an array in memory. 
And we use a reference to access and control the object in memory. 
Here, in the second, I'm declaring another reference to the same array in memory, so now we've got two references,
pointing to the same array in memory. 
In other words, both myIntArray and "anotherArray" hold the same address. 
One way to know it's a reference type is the new operator, 
because that creates a new object in memory, and we're using the new operator on line 20, 
where we created an array of 5 elements. 
Basically, new here means new object. 
On the second line, we haven't used the "new" keyword. 
We've just used the equal sign, which means we're assigning 1 reference to another. 
Let's print these array references out.

```java  
System.out.println("myIntArray = " + Arrays.toString(myIntArray));
System.out.println("anotherArray = " + Arrays.toString(anotherArray));
```

We've got all zeros because all we've done is initialize the array with a size and the default values. 
Let's actually see what happens if we make a change to one array:

```java  
anotherArray[0] = 1;
System.out.println("after change myIntArray = "+ Arrays.toString(myIntArray));
System.out.println("after change anotherArray = " + Arrays.toString(anotherArray));
```

I'll add a call to Arrays.toString, for myIntArray, and print what is returned. 
And do the same for "anotherArray."
And printing out both reference variables again, after we make the change. 
Will the anotherArray be changed? 
When we run that:

```java  
myIntArray = [0, 0, 0, 0, 0]
anotherArray = [0, 0, 0, 0, 0]
after change myIntArray = [1, 0, 0, 0, 0]
after change anotherArray = [1, 0, 0, 0, 0]
```

And you can see what's happened with those last two output statements. 
Both variables are referencing the same array in memory, so there's only one copy of the array. 
When you use one reference variable to make changes to the object in memory, 
it's like making that change with the other variable.

## [d. Variable Arguments (Varargs)]()

Notice here that the parameter to the main method is an array of String. 
This means we can pass an array of Strings to this method when it's called. 
Or, if we use this method as the entry point to our application, we can pass data
on the command line to this method. 
Up until now, I've only shown you this particular method signature. 
But this signature can be written in a slightly different way.

```java  
public static void main(String... args) {
}
```

We can replace the brackets after the String type, which we know tells us this method will take an array of String.
And we can instead replace that with three periods. 
This is a special designation for Java, that means Java will take zero, one, or many Strings, 
as arguments to this method, and create an array with which to process them, in the method. 
The array will be called args, and be of type String. 
So what's the difference then? 
The difference is minor within the method body, but significant to the code that calls the method. 
Let's review this in code.

```java  
System.out.println("Hello World again");
```

I'm going to change the main method, to use the varargs version for the parameter: "main(String... args)". 
And let's print something out and confirm that works. 
I'll print the string literal, "hello world" again and running that, Everything works as before, 
so this change is not really evident to us, using the main method, as the entry point to
the application. 
Let's create our own method that will print out an array of Strings, each on its own line.

```java  
private static void printText(String... textList) {
    for (String t : textList) {
        System.out.println(t);
    }
}
```

Using for each, we will cycle through the text list array. 
And print t. 
This method takes an array of Strings, and prints each String on its own line, using an enhanced for loop. 
I'll call this method from the main method. 
I'll come back and change the method signature in a bit.

```java  
String[] splitStrings = "Hello World again".split(" ");
printText(splitStrings);
```

The split method splits a String into a string array, by whatever delimiter you tell it to use. 
In this code, we have a string literal, which is just the "Hello World again" sentence, 
and we're immediately calling the split method, a String instance method. 
This method takes a String, which can support regular expressions, which tells the method 
what the String should be split on. 
In this case, we're going to split by the spaces in the sentence. 
This means this String will get split into pieces, and the method returns an array of the parts, 
an array of String. 
You might remember, we learned about the join method on String,
which concatenated multiple Strings together, into one String. 
The split method has the opposite functionality. 
Let's run this code,

```java  
Hello World again
Hello
World
again
```

You see printed out on separate lines, using our method. 
Let's change our method that prints this array, from accepting an array, 
to accepting variable arguments for the String. 
And all we need to do is replace "[]" with "...". 
And if we run that,

```java  
Hello World again
Hello
World
again
```

We get the exact same result. 
So what's the big deal? 
The variable arguments parameter gives us a lot more options 
when we execute the method. 
Let's look at these. 
First, we already know we can pass an array to this method. 
But what's really neat is we can pass a simple String if we want to:

```java  
System.out.println("_".repeat(20));
printText();
```

And this code runs too. 
Now there are some restrictions on when you can use variable arguments as a method parameter.
There can be only one variable argument in a method. 
The variable argument must be the last argument. 
Those rules are pretty easy to remember. 
You'll find that Java uses variable arguments in many methods contained in their library classes. 
In fact, the "String.join" method itself uses a variable argument, as its last argument, 
which is why the delimiter needs to be specified first.
We showed you how to use this method with multiple strings, 
but it can also be used with an array of Strings, so let's look at that:

```java  
String[] sArray = {"first", "second", "third", "fourth", "fifth"};
System.out.println(String.join(",", sArray));
```

I'm using an anonymous array initializer. 
It's called anonymous because the type isn't included, but Java can figure 
it out because it's being used in a declaration statement. 
Next, we print out the result of the "String.join" method, with a comma as the first value, and if we run that:

```java  
first,second,third,fourth,fifth
```

And we can see the output, printing a single String of our elements, comma delimited. 
So that was variable arguments, and using arrays as method arguments. 
Whichever you choose will depend on the other parameters in the method arguments list, 
as well as whether you want your users to be able to pass a variable list of elements, vs. 
just passing an array of elements.

### [Minimum Element Challenge](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_05_Arrays/Course05_MinElementChallenge)

Write a method called readIntegers, that reads a comma delimited list of numbers, entered by the user from the
console, and then returns an array, containing those numbers that were entered. 
Next, write a method called findMin, that takes the array as an argument, 
and returns the minimum value found in that array.

In the main method :
* Call the method readIntegers, to get the array of integers from the user, and print these outs, 
using a method found in java.util.Arrays.
* Next, call the findMin method, passing the array, returned from the call to the readIntegers method.
* Print the minimum element in the array, which should be returned from the findMin method.

A tip here: Assume that the user will only enter numbers, 
so you don't need to do any validation for the console input.

### [Reverse Array Challenge](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_05_Arrays/Course06_ReverseArrayChallenge)

The challenge is to write a method called reverse, that takes an int array as a parameter. 
In the main method, call the reverse method, and print the array both before and after the reverse method is called. 
To reverse the array, you have to swap the elements, so that the first element is swapped with the last element, 
and so on. 
So for example, if the array contains the numbers 1,2,3,4,5, then the reversed array should be, 5,4,3,2,1.

## [e.Multi-Dimensional Arrays]()

### 2D arrays

An array element can actually be an array. 
It's known as a nested array, or an array assigned to an outer array's element. 
This is how Java supports two and 3D arrays, of varying dimensions. 
A two-dimensional array can be thought of, as a table or matrix of values, with rows and columns. 
You can use an array initializer for this, which I'm showing on below.

```java  
int[][] array = {
        {1, 2, 3},
        {11, 12, 13},
        {21, 22, 23},
        {31, 32, 33}
};

int[][] array = {{1, 2, 3}, {11, 12, 13}, {21, 22, 23}, {31, 32, 33}};
```

Notice the two sets of square brackets on the left side of the assignment, in the declaration. 
Using this type of declaration, tells Java we want a 2D array of integers. 
Here we show the same declaration with array initializers, that means the same thing. 
The first example, just uses white space to make it more readable. 
In the second example, all the nested arrays have the same length.

```java  
int[][] array = {
        {1, 2},
        {11, 12, 13},
        {21, 22, 23, 24, 25}
};
```

A 2-dimensional array doesn't have to be a uniform matrix though. 
This means the nested arrays can be different sizes, as we show with this next initialization statement. 
Here, we have an array with three elements. 
Each element is an array of integers (a nested array). 
Each nested array is a different length.

```java  
int[][] array = new int[3][3];
```

You can initialize a two-dimensional array and define the size of the nested arrays, as shown here. 
This statement says we have an array of three nested arrays, and each nested array will have three ints.

|   | "0" | "1" | "2" |
|---|-----|-----|-----|
| 0 | 0   | 0   | 0   |
| 1 | 0   | 0   | 0   |
| 2 | 0   | 0   | 0   |

The result of this initialization is shown in the table on above. 
Java knows we want a 3x3 matrix of ints, and defaults the values of the nested arrays to zeros, 
as it would for any array.

```java  
int[][] array = new int[3][];
```

You can initialize a two-dimensional array without specifying the size of the nested arrays. 
Here, we're specifying only the outer array size, by specifying the length, 
only in the first set of square brackets.
We've left the second set of square brackets empty. 
The result of this initialization is an array of three null elements. 
We are limited to assigning integer arrays to these elements, but they can be any length.

```java  
int[][] myDoubleArray;
int[] myDoubleArray[];
```

There are a lot of ways to declare a two-dimensional array. 
I'll just cover the two most common ways here. 
The most common, and in my opinion, the clearest way to declare a two-dimensional array
is to stack the square brackets as shown in the first example. 
You can also split up the brackets as shown in the second example, 
and you'll likely come across this in Java code out in the wild.

```java  
int[][] array2 = new int[4][4];
System.out.println(Arrays.toString(array2));
System.out.println("array2.length = " + array2.length);
```

Ok, so here, I'm using 2 [], after the primitive type,int, and naming the array variable, array2. 
This instantiates an array that has four elements. 
Each element in the outer array gets assigned a nested array with 4 elements, 
each an int, with a default value of 0. 
I'll print out the value returned, from a call to arrays.toString, passing array2.
And print out that array2's length. 
And we can run this code:

```java  
[[I@6acbcfc0, [I@5f184fc6, [I@3feba861, [I@5b480cf9]
array2.length = 4
```

We can see the length of array2 is still 4, but when we used the Arrays.toString method, 
we see four elements printed.
You might remember this odd-looking printout, which is really the result of just calling toString, 
on an integer array, without using the helper class method. 
We would get this, if we printed out each element in the array, individually, so let's do that next.

```java  
for (int[] outer : array2) {
    System.out.println(outer);
    System.out.println(Arrays.toString(outer));
}
```

Define our for each loop, print outer. 
In this code, I'm using the for each loop, but notice what our loop element is, it's an array of integers. 
This loops through the outer array, and each element is an array of four integers. 
And if we run this:

```java  
[I@6acbcfc0
[I@5f184fc6
[I@3feba861
[I@5b480cf9
```

We have similar output to before, but now, each array is printed on its own line. 
But if we change the code, and instead of just printing each loop element, 
which we know each is an array, we can pass it to the Arrays.toString method instead:

```java  
[0, 0, 0, 0]
[0, 0, 0, 0]
[0, 0, 0, 0]
[0, 0, 0, 0]
```

We can see each nested array gets printed. 
And we can confirm now that each of the elements in the nested arrays is initialized to zeros. 
You can print all these elements out, using a nested for loop. 
Let's do that next, because you'll probably see code processing nested arrays like this a lot.

When we access a one-dimensional array element, we do it with square brackets, and an index value.
So this code sets the first element in the array to 50:

```java  
array[0] = 50;
```

To access elements in a two-dimensional array, we use two indices, 
so this code sets the first element in the first array to 50.

```java  
twoDarray[0][0] = 50;
```

This next code sets the second element in the second array to 10.

```java  
twoDarray[1][1] = 10;
```

The code below is similar to the code we have in our class, using nested traditional for loops.

```java  
for (int i = 0; i < array2.length; i++) {
    for (int j = 0; j < array2[i].length; j++) {    // while i = 0, j will loop from 0 to 3
        System.out.println(array2[i][j] + " ");
    }
}
```

In this case, we're not using any local variables, but accessing array elements and variables directly.

|       | j = 0  | j = 1  | j = 2  | j = 3  |
|-------|--------|--------|--------|--------|
| i = 0 | [0][0] | [0][1] | [0][2] | [0][3] |
| i = 1 | [1][0] | [1][1] | [1][2] | [1][3] |
| i = 2 | [2][0] | [2][1] | [2][2] | [2][3] |


This table shows the indices, which are used to access the elements in the two-dimensional array in our code sample.
When we loop through the outer loop, we're accessing each row of elements.
We've highlighted the first row, which would be the elements accessed, when "i = 0" for the outer for loop.
When we loop through the inner loop, we're accessing each cell in the array.
A cell in this matrix can be any type.
In our code, each is an integer value, and we know they've all been initialized to zero.

```java  
for (var outer : array2) {
    for (var element : outer) {
        System.out.print(element + " ");
    }
    System.out.println();
}
System.out.println(Arrays.deepToString(array2));
```

We're going through the loop (which in this case, we'll use to traverse the outer array), using the "i" index. 
We'll loop from zero to one less than the length of the array. 
And right now, we're not doing anything except assigning each element to a variable in the loop, 
we called innerArray. 
Here, I'm using "var" as the type, because the JVM can infer the type of our elements, 
which, in this case, is an integer array.

Next, I want to loop through the elements that are in each of these nested arrays. 
Add a for loop for an inner array.
We're looping through the inner Array's elements, using a nested for loop, and a different look index, j. 
And j start with 0, because arrays are always indexed, starting at zero. 
We'll print each element, using the indices for the outer loop and the inner loop.

You can see that it gives us the same output as before, but the output is a bit easier to read. 
We already know we can use toString, on the Arrays class, to print out a nicely formatted 1D array.
As it turns out, the Arrays class provides us with another method to print multidimensional arrays, 
called deepToString.

```java  
System.out.println(Arrays.deepToString(array2));
```

I'm calling Arrays.deepToString(), passing array2, and print the value returned, and if we run that:

```java  
[[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]
```

We can see our 2D array, printed on a single line. 
Here you can see the outer square brackets, which represent the outer array, 
and the inner brackets each marks the inner arrays.

### Multi-Dimensional Arrays

There is no requirement that the nested arrays be any particular size, or even have any values at all. 
In fact, even though we've initialized an array as a 4x4 matrix of integers, 
we're not restricted to staying with that. 
We can actually assign any array of integer to one of the outer array's elements, 
and I'll demonstrate that now.

```java  
int[][] array2 = new int[4][4];
array2[1] = new int[] {10, 20, 30};
System.out.println(Arrays.deepToString(array2));
```

I'll assign array2's second element, to a new int array, containing three elements. 
Now I'll print the value, returned from a call to Arrays.toDeepToString, for array2. 
On the first line, we set the second element of the outer array to a new integer array. 
We're using an array initializer with new, int, and [], and then a list of different values, in {}.
Now notice, there are only three elements in this array. 
This code compiles without issue. 
And if we run it:

```java  
[[0, 0, 0, 0], [10, 20, 30], [0, 0, 0, 0], [0, 0, 0, 0]]
```

We can see that we still have an array of four arrays, but now, 
our second nested array is a list of 3 integers, 10, 20 and 30. 
One other thing I want to point out, with this code, 
is that we can't use an anonymous array in this assignment. 
What do I mean by that? 
Let's see what happens if we remove the new int [] in that last assignment. 
And you can see IntelliJ is flagging that as an error, that an array initializer can't be used here. 
This means we need the array creation part of the code, in other words, the new, int and [].

When we declare multidimensional arrays, the declared type can itself be an array, 
and this is how Java supports two-dimensional arrays. 
We can take that even further, the outer array can have references to any kind of array itself.
In this example, we have an outer array with three elements.

```java  
Object[] multiArray = new Object[3];
multiArray[0] = new Dog[3];
multiArray[1] = new Dog[3][];
multiArray[2] = new Dog[3][][];
```

The first element is itself a single-dimension array. 
The second element is a two-dimensional array. 
And lastly, the third element is a three-dimensional array.

The easiest way to examine this multidimensional aspect is to create an array of Objects as I showed above. 
I've said everything can be referred to as an object, including arrays themselves, 
so I'll use this to help you to understand how Java implements arrays simply as nested arrays.

```java  
Object[] anyArray = new Object[3];
System.out.println(Arrays.toString(anyArray));
```

Setting up our Object array with three non-initialized elements.
And finally, I'll print the value returned from Arrays.toString, passing our array. 
I'll run the code:

```java  
[null, null, null]
```

You can see we have an array of three elements, and they're all initialized to null. 
Now, we can really assign anything we want to any of these three elements, including an array, 
so let's do that.

```java  
anyArray[0] = new String[] {"a", "b", "c"};
System.out.println(Arrays.deepToString(anyArray));
```

I'll assign element zero on our array to a new string array. 
And print the value, returned from a call to Arrays.deepToString.
And running this,

```java  
[[a, b, c], null, null]
```

We can now see that we have a nested array, and that we've nested the ABC String array, in our outer array. 
At this point, you could say we have a 2D array. 
But let's keep going with this. 
Let's set element 2 of our array to a 2D array.

```java  
anyArray[1] = new String[][]{
        {"1", "2"},
        {"3", "4", "5"},
        {"6", "7", "8", "9"}
};
System.out.println(Arrays.deepToString(anyArray));
```

Adding numbers, 1 through 9 as separate strings. 
And let's print out anyArray:

```java  
[[a, b, c], [[1, 2], [3, 4, 5], [6, 7, 8, 9]], null]
```

Finally, I'll initialize the last element.

```java  
anyArray[2] = new int[2][2][2];
// anyArray[2] = "Hello";
System.out.println(Arrays.deepToString(anyArray));
```

I'll assign element 3, to a 3D array, and another print of anyArray. 
Ok, so let's run this:

```java  
[[a, b, c], [[1, 2], [3, 4, 5], [6, 7, 8, 9]], [[[0, 0], [0, 0]], [[0, 0], [0, 0]]]]
```

So this is a bit hard to look at, and figure out, which arrays are nested in which. 
We'll print these nested arrays by using a for each loop.

```java  
for (Object element : anyArray) {
    System.out.println("Element type = " + element.getClass().getSimpleName());
    System.out.println("Element toString() = " + element);
    System.out.println(Arrays.deepToString((Object[]) element));
}
```

Notice in this particular for each loop, we're using Object as the type of our element, in the loop declaration.
First, we print out the class name of each element in this array.
Second, we print out the toString representation of each array element. 
And then we want to print out each element contained in this multidimensional array. 
Now, we happen to know that each element is an array, but the Java compiler doesn't know that.

The "Arrays.deepToString" method takes an array, so we need to cast our element to an array of Object 
before we pass it to this method. 
Now, if we assigned something other than an array, this would break the code, but we didn't. 
We'll talk more about this issue later. 
Running this,

```java  
Element type = String[]
Element toString() = [Ljava.lang.String;@3feba861
[a, b, c]
Element type = String[][]
Element toString() = [[Ljava.lang.String;@5b480cf9
[[1, 2], [3, 4, 5], [6, 7, 8, 9]]
Element type = int[][][]
Element toString() = [[[I@b4c966a
[[[0, 0], [0, 0]], [[0, 0], [0, 0]]]
```

We can see each type printed out, so the type for the first element, 
the element type is String with a single "[a, b, c]". 
The second element is a 2D string array, "[[1, 2], [3, 4, 5], [6, 7, 8, 9]]". 
And the last one is a 3D integer array, "[[[0, 0], [0, 0]], [[0, 0], [0, 0]]]", 
with all its values set to the default zero. 
And we can also see the elements printed out in each case.

In general, it's not good practice to create Object arrays like this. 
This is because the compiler won't do any type checking, 
meaning anything can be assigned to this outer array, 
and this code wouldn't work if we simply assigned a String to anyArray[2], as an example. 
Let's do to see what happens.

```java  
anyArray[2] = "Hello";
```

I'm going to type this in a statement. 
You can see this above as "commented." 
Now, with this change in place, the Arrays.deepToString method still works, 
the one that we call immediately after that. 
But the call to the deepToString in the for loop will give us an exception if we run it.

We're trying to cast something that's a String to an Array of Objects, 
and we can't do that. 
When using arrays, you should really stick to more strictly typed arrays, as a general rule. 
This will prevent any calling code from inserting object types that you don't expect, 
and can't handle, just like we did here.
</div>

