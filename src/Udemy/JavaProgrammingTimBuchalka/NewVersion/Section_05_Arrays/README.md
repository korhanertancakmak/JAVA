# [Section-5: Arrays]()

## [a. Arrays]()

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



    Index                                           0       1         2            3        4
    Stored values in an array with 5 elements    "Andy"   "Bob"    "Charlie"    "David"   "Eve"

    Elements in an array are indexed, starting at 0. If we have an array, storing 5 names, conceptually it looks as shown
    above. The first element is at index 0, is "Andy". The last element in this array is at index 4, and has the String
    value "Eve".

        int[] integerArray;
        String[] nameList;
        String[] courseList[];

        When you declare an array, you first specify the type of the elements you want in the array. Then you include "[]"
    square brackets in the declaration, which is the key for Java, to identify the variable as an array. The "[]" can follow
    the type as shown in the first 2 examples above. This is much more common. The brackets can also be after the variable
    name, as shown in the last example. You may see this in some code you run across. You don't specify a size, in the array
    declaration itself(remember the declaration is usually to the left of an equals or assignment operator, but not always).

                Array Creation                              Object Creation
        int[] integerArray = new int[10];           StringBuilder sb = new StringBuilder();

        One way to instantiate the array, is with the new keyword, much as we've seen, with most of the classes we've used
    until now, with the exception of String. Above, we have an array declaration on the left of the equals sign, and then
    array creation expression on the right side. For comparison, I'm showing you a typical array variable declaration, and
    a class instance, or object creation expression, using the StringBuilder class. They look pretty similar, but there
    are 2 major differences. Square brackets "[]" are required when using the new keyword, and a size is specified between
    them. So in this example, there will be 10 elements in the array. An array instantiation doesn't have a set of parentheses,
    meaning we can't pass data to a constructor for an array.

                        Invalid Array Creation - Compile Error because of "()"
                            int[] integerArray = new int[10]();

        In fact, using parentheses with an array instantiation, gives you a compile error. Ok, that's a brief overview of
    what an array is. Let's look at arrays in some code now.

![image01]()


```java  

```
