package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_05_Arrays.Course01_Arrays;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

/*
Part-1
                                                    Arrays

        Up to now, what we haven't really discussed though, is a way to have multiple values, all of the same type. We had
    a taste of this kind of problem, in the Bill's Burger challenge. We had several toppings for our burger, and we had
    to create individual attributes. This led to inefficient code and other limitations. For example, what if we needed
    ten toppings? There are much better ways to handle this. Java provides us with many types of containers, to store
    multiple values of the same type. These start with the most basic, which is the array, and that's what this section
    will cover.

        Let's look at ways to store, and manipulate, multiple values of the same type. The most common way to do this in
    Java is with an array. An array is a data structure, that allows you to store a sequence of values, all of the same
    type. You can have arrays for any primitive type, like ints, doubles, booleans, or any of the 8 primitives we've learnt
    about. You can also have arrays for any class.

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
End-Part-1
*/

        int[] myIntArray = new int[10];

/*
Part-2
        My class has the first statement, the package statement:

                                    package CoursesFrom032To065.Course063;

    I'll create an int array, called myIntArray and set it to have 10 elements. Like we do when we create any new object,
    we use the "new" keyword. Underneath the covers, a Java array, is just a specialized class, so the "new" keyword here,
    creates a new array. And now we again need to specify the type, similar to the way we specify the class, when creating
    a new instance. But this time, we specify the type that all the array elements will be, so this will be int here. And
    next, we include the brackets again, but with a number, the size of the array in the brackets. This is the number of
    slots, or in this case, of separate integers, that we want in this array. And it's important to know that an array is
    not resizable.

        The size of an array, once created, is fixed. In this case, myIntArray will have 10 elements. You can't change the
    size of an array, after the array is instantiated. We can't add or delete elements, we can only assign values to 1
    of the 10 elements in this array, in this example. Now, let's see how we'd access the array elements.
End-Part-2
*/

        myIntArray[5] = 50;

/*
Part-3
        I'll set element 6 of myIntArray to the int value 50. This statement assigns the value 50 to one of the array
    elements. You might assume that this code is assigning 50, to element 5, but it's actually saving it to element 6.
    The reason for this is, as with all arrays in Java, indexing starts with position 0, not 1, as I've stated on above.
    And for the last element, it actually used index 9 in this case. Instead of starting from 1, and finishing on 10 for
    this array, you start from 0, and finish on 9. This may be confusing at first, but you'll soon get used to it.

        Arrays can be any primitive type, so we could create an array to contain doubles. Let's do that next.
End-Part-3
*/

        double[] myDoubleArray = new double[10];
        myDoubleArray[2] = 3.5;

/*
Part-4
        And I'll set the 3rd element of my double array to the value 3.5. And here, we're creating an array called myDoubleArray,
    with room for 10 doubles. And we set the 3rd element in that array, to a floating point number, a decimal number, 3.5.
    Remember that any decimal number literal like we're using here, is really a double in Java. Retrieving a value from
    the array is done in the same way, using the position of the element, in the array, Let's print out the 3rd element,
    of the double array we just created.
End-Part-4
*/

        System.out.println(myDoubleArray[2]);

/*
Part-5
        Again, we call it 3rd element, but we use index 2, because Java starts counting at zero for arrays. And we get 3.5
    printed out, which we assigned to that element. Like any variable, whatever type you define the variable to be, that's
    the type of data that can be assigned to that array. You can't put a double into an integer array, in much the same
    way you can't assign a double value, to an integer variable.
End-Part-5
*/

        //myIntArray[0] = 45.0;
        //myIntArray[1] = "1";

/*
Part-6
        Now, let's assign some more data, to the first 2 elements, of the integer array. Set the first element of myIntArray
    to double value 45.0. And set the 2nd element to string literal "1". In this code, I've purposely created a couple of
    errors. I declared this array, to store integers, and here, silly me, I'm trying to assign other types of values to
    the array elements. You can see we have compiler errors here, because Java doesn't allow this. In the first case, we
    have a double, and that can't be assigned to an int element.

        In the second case, we're trying to assign a String literal to an int variable, which is what each array element
    really is. Imagine assigning values this way, one at a time like this, for the other 7 elements. You can see this gets
    tedious really fast. Java provides another shortcut, for creating an array. If we know the values for all the elements,
    at the time we create the array, we can use an array initializer.

                int[] firstFivePositives = new int[]{1, 2, 3, 4, 5};

    An array initializer, makes the job of instantiating and initializing a small array, much easier. In this example, you
    can see we still use the "new" keyword, and have int, with "[]". But here we specify the values, we want the array to
    be initialized to, in a comma delimited list, in {}. Because these values are specified, the length of the array can
    be determined, so we don't specify the size in the []. And actually, Java provides an even simpler way to do this.

                int[] firstFivePositive = {1, 2, 3, 4, 5};
                String[] names = {"Andy, "Bob", "Charlie", "David", "Eve"};

        The array can be initialized as an anonymous array. Java allows us to drop "new int[]", from the expression, as
    shown above. This is known as an anonymous array. Here we're showing example for both an int array as well as a String
    array. An anonymous array initializer, can only be used in a declaration statement. I'll explain what that means in
    a minute.
End-Part-6
*/

        int[] firstTen = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("first = " + firstTen[0]);
        int arrayLength = firstTen.length;
        System.out.println("length of array = " + arrayLength);

/*
Part-7
        This time, we're using an array initializer to create an array with the values 1 through 10 in it. I'll tell Java
    it's an integer array called first 10 and assign the values 1 through 10 in curly braces. I'll print out the firstTen
    arrays first elements value. Let's save first tens length to a new variable array length, and print it out using println.
    if we run this code:

            3.5
            first = 1
            length of array = 10

End-Part-7
*/

        System.out.println("last = " + firstTen[arrayLength]);

/*
Part-8
        Let's add another line of code, to get the last element, using the length of the array. But if we run this though,
    we get an exception. This is an ArrayIndexOutOfBoundsException, and you can see the message, Index 10 out of bounds
    for length 10. This is a very common error to make. We've been taught to think, starting with 1, not 0, so it's easy
    to forget, that Java starts indexing with 0. Because it starts with 0, all indices are shifted by 1. The last element
    in an array that has 10 elements is at index 9. We need to subtract 1 from the length, to get the last index.
End-Part-8
*/

        int[] newArray;
        //newArray = {5, 4, 3, 2, 1};

        newArray = new int[] {5, 4, 3, 2, 1};

/*
Part-9
         Now I've said this array initializer can only be used in declaration assignments, or method arguments, so let's
    review what that means for a moment. With this code above, we've got a compiler error. If we hover our mouse, over that
    statement, we see that "Array Initializer is not allowed here". This means, you can't use the anonymous version of the
    array initializer, in a statement outside from a declaration statement. But we can use the longer form, including
    "new int[]", before the values in {}. And that code compiles. Once you have an array, you can loop through the elements,
    retrieving each value, with the loop index. We can do this with the traditional for loop:
End-Part-9
*/

        for (int i = 0; i < newArray.length; i++) {
            System.out.print(newArray[i] + " ");
        }

/*
Part-10
         What we're doing there is, we're looking through each element, starting from element zero. We start with i = 0,
    incrementing by one, while i is < newArray.length. And we print out each element, using the loop index to get the
    current element. We're just printing all of those on a single line, with a space between each. And running that:

            3.5
            first = 1
            length of array = 10
            last = 10
            5 4 3 2 1

        An array is a special class in Java. The array, like all other classes, ultimately inherits from java.lang.Object.
    When you don't use an array initializer statement, all array elements get initialized to the default value for that
    type. For primitive types, this is "zero" for any kind of "numeric primitive", like int, double or short. For "booleans",
    the default value will be "false". And for any class type, the elements will be initialized to "null". Now, let's use
    the basic for loop to assign the values, as we had them before, in reverse order.
End-Part-10
*/
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = newArray.length - i;
        }
/*
Part-11
        The loop set up is the same as before, but inside the loop, you can see we're assigning each element in the loop.
    We're using the "[]", and the loop variable "i", within the [], to assign a value to each array element. And it gets
    assigned whatever the value will be, when the current index is subtracted from the length of the array. When this is
    0 to start, we get the length of the array as the first value, which is 5. And when we reach the max value, which I've
    said was 4, the element will get the value 1.

        Java has another for statement, they call it "the enhanced for loop", but it's also sometimes known as "the for-each
    loop". This loop was designed to walk through elements in an array, or some other type of collection. It processes 1
    element at a time, from the first element to the last. Here I show you the syntax for the 2 types of for loop statements,
    side by side:

                        Enhanced For Loop                               Basic For Loop
                for (declaration : collection) {                for (init; expression; increment) {
                        // block of statements                          // block of statement
                }                                               }

    The enhanced for loop only has 2 component, instead of 3, defined in the parentheses after the for keyword. It's important
    to notice, that the separator character between components, is a colon(:), and not a semi-colon(;), for the Enhanced
    for loop. The first part is a declaration expression, which includes the type and a variable name. This is usually
    a local variable with the same type as an element in the array. This is usually a local variable with the same type
    as an element in the array. And the second component is the array, or some other collection variable. Let's see this
    for loop in action.
End-Part-11
*/

        System.out.println();
        for (int element : newArray) {
            System.out.print(element + " ");
        }

/*
Part-12
        First, we just print a new line with println, because the previous output didn't include it. Now we've got the
    enhanced for loop code. Notice that it looks similar to a traditional for loop, but it uses a colon, and not semi-colons,
    and expects only 2 components in the parentheses. The first component just needs to be a declared variable. In this
    case, we're looping through an array of integers, so we want to set this variable to an int type. And here, rather than
    just the letter i, I'm purposely using something more descriptive, element. This is because the variable is the actual
    element, it's not the array index we're looping through. And the second part, on the right side of the colon, in the
    parentheses, is the thing we're looping over. In this case, it's our integer array, newArray. And now, in the print
    statement, we don't have to use an index to get the element from the array. The enhanced for loop gets it for us, and
    stores it in that local variable, element. In this case, we don't have to know what the index is at all. If we wanted
    it or needed it, we'd probably use the traditional for loop. If I run that:

        5 4 3 2 1

    we get the exact same output, the values 5 through 1 are printed out. That's rather a nice thing, because you don't
    have to deal with the array index position. The enhanced for loop gives you a simpler, less error-prone option, if all
    you need to do is process the elements from start to finish, and you aren't trying to set, swap, or sort elements.
    what if we try to print the whole array with println?
End-Part-12
*/

        System.out.println();
        System.out.println(newArray);

/*
Part-13
       And if we run that:

       [I@214c265e

    We get this strange thing printed out, and not a neatly formatted list of our array elements. Yours might look different
    from mine, but that's okay. I'll explain the numbers and letters shortly. You'll remember this is Java's toString method
    that gets called. And I've said our array is really just a special Java class. But the array class doesn't print the
    elements out neatly for us, as we might hope. In other words, it doesn't override the toString method, so it uses
    java.lang.Object's toString method. And this gives us the left square bracket "[" with a capital "I", which means it's
    a primitive integer array, and then it's followed by the hexadecimal representation, of the hash code. This is not likely
    to be very helpful to you, as you examine and manipulate your array. However, Java does provide a helper class, which
    contains a lot of static methods, which we can use for arrays. And this class is the java.util.Arrays class.

        Java's array type is very basic, it comes with very little built-in functionality. It has a single property or
    field, named length. And it inherits java.util.Object's functionality. Java provides a helper class named java.util.Arrays,
    providing common functionality, you'd want for many array operations. These are static methods on Arrays, so are class
    methods, not instance methods. For now, we'll use 1 method on this class, to print out the elements in our array, and
    this method is Arrays.toString(). The toString() method on this helper class, prints out all the array elements, comma
    delimited, and contained in square brackets.

                    String arrayElementsInAString = Arrays.toString(newArray);

    The output from this method is shown here, conceptually:

                    [  e[0], e[1], e[2], e[3], ... ]

    It prints the element at index 0 first, followed by a comma, then element at index 1 next, comma, and so on, until all
    the elements are printed. Let's see what this looks like for our integer array.
End-Part-13
*/

        System.out.println(Arrays.toString(newArray));

/*
Part-14
       And if we run that:

       [5, 4, 3, 2, 1]

    That's a handy method, which may be all you need, if you just want your elements printed in a single string this way.
    One other thing, you should know, is that you can assign any array, to an Object variable. Let's look at this more
    closely.
End-Part-14
*/

        Object objectVariable = newArray;
        if (objectVariable instanceof int[]) {
            System.out.println("objectVariable is really an int array");
        }

/*
Part-15
       In this code, we're assigning an integer array reference, to an object reference. And notice here, we can still use
    the instanceof operator, using an array type on the right side of the operand. This operand will test if our Object
    variable, is really an instance of an integer array. An array is a special class, recognized by Java through the use
    of the "[]". It still inherits from the java.lang.Object class. You might ask why we'd do something like this? Well,
    in fact, you could create an array of Objects, that supports any kind of type in Java. Let's look at that now:
End-Part-15
*/

        Object[] objectArray = new Object[3];
        objectArray[0] = "Hello";
        objectArray[1] = new StringBuilder("World");

/*
Part-16
       In this code, we're creating an array of Objects, and this array will hold 3 object references. And now, we can assign
    any object reference, of any type, to the elements in this array. First, we assign it a String literal, and then we
    assign it a StringBuilder instance. You could assign an object of any of your own classes, to this kind of array. This
    is 1 way of having an array of many different types. I'm not saying this is good practice, but i did want to point
    out that you could do this with arrays. Later, we'll learn more elegant ways, to support different types in arrays.
    And because we know an array is an object, you can assign an array to be an element, of this type of Object array.
End-Part-16
*/

        objectArray[2] = newArray;

/*
Part-17
       We've got an array of Objects, and one of the elements is an array of integers. This is a nested array. We'll be
    looking at this in more detail, when i talk about multi-dimensional arrays. But what's important for you to understand
    right now is, an array can be treated like any other object instance in Java. So those are the basics, for the array.
End-Part-17
*/
    }
}
