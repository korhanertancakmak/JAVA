package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_06_ArrayList_LinkedList_Iterators_Autoboxing.Course07_AutoboxingUnboxing;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        Integer boxedInt = Integer.valueOf(15);             // preferred but unnecessary
        Integer deprecatedBoxing = new Integer(15);      // deprecated since JDK 9
        int unboxedInt = boxedInt.intValue();                 // unnecessary

/*
Part-2
        Ok, so the reason I'm showing you these 3 statements, is you'll recognize a couple of things. The first 2 statements
    are attempting to manually box the primitive int 15, to an instance Integer wrapper class. You'll notice that IntelliJ
    is trying to attract your attention. Depending on your configuration of IntelliJ, either some parts or everything after
    the assignment operator is highlighted. In my case, you can see "valueOf" is highlighted. Hovering over the highlighted
    part, of that first statement, IntelliJ tells us, this is "Unnecessary boxing", and we've commented that as well. Now,
    check out the second statement. IntelliJ has, for me, placed an error underneath the Integer class, but you may find
    it has crossed out the Integer class, in addition to highlighting everything after the assignment operator. Although
    it's an error, it won't stop from running. What IntelliJ says for that is "'Integer(int)' is deprecated and marked
    for removal". And I've discussed that, and how this particular method is discouraged. We won't use it again, but you
    should be familiar with it, if you run into legacy code. Lastly, the 3rd statement is also highlighted by IntelliJ,
    and this message says it's unnecessary unboxing. Remember that unboxing means, you're returning the primitive value,
    unboxing the primitive. I'll leave those 3 statements in, but I'll add the way you should write this,
End-Part-2
*/

        // Automatic
        Integer autoBoxed = 15;
        int autoUnboxed = autoBoxed;
        System.out.println(autoBoxed.getClass().getName());
        //System.out.println(autoUnboxed.getClass().getName());

/*
Part-3
        Here we have 2 declarations, the first assigns 15 to an Integer variable. The second assigns the variable, autoBoxed,
    to an int variable. Then we print out both variables class names, attempting to use the getClass method, chained to
    getName(). Notice though, that the last statement is a compiler error. We can use int variable types in many different
    places, but not in this way. The JVM knows autoUnboxed is a primitive type, and we can't use methods on primitive data
    types, like this. Let's comment that last line out, then run this code.

                java.lang.Integer

    And hopefully, no surprises. The output shows that the autoBoxed variable is of type java.lang.Integer. This example
    is a bit forced, because it doesn't quite explain why you'd want boxing or unboxing, automatically or otherwise. Let's
    create a couple of methods, one that returns a double primitive type, and one that returns a Double wrapper instance.
End-Part-3
*/

        Double resultBoxed = getLiteralDoublePrimitive();
        double resultUnboxed = getDoubleObject();

/*
Part-6
        Now in this code, autoboxing and auto unboxing is again occurring, but not by the assignment of simple variables
    or literals. The method getLiteralDoublePrimitive, returns a primitive data type, but the JVM is automatically boxing
    it, because it's being assigned to a variable, of the java.lang.Double class. And the same is true in reverse. The
    method getDoubleObject returns an object instance, but because we're assigning it directly to a primitive variable,
    the unboxing occurs automatically. I hope you can see how that's helpful, being able to see method results, and autoboxing
    when you want to assign the results to variables.

        Next, I want to focus on this feature and how it applies to arrays, variable arguments, and Lists like ArraysList
    and LinkedList. First, I want to create an array of the Integer wrapper class in the main method.
End-Part-6
*/

        Integer[] wrapperArray = new Integer[5];
        wrapperArray[0] = 50;
        System.out.println(Arrays.toString(wrapperArray));

/*
Part-7
        In this code, we create an array of Integer wrappers, and set the array size to 5. Then we assign the first value,
    the value 50, and this auto boxes the primitive integer literal, 50, to an instance of an Integer class. And we print
    this array out, using the Arrays helper class, and the toString method. And running this code,

                [50, null, null, null, null]

    we can see the array was initialized to nulls. Remember, the Integer wrapper classes are really classes, and their
    default values in arrays, are null references. But the first element prints as 50. And just for good measure, let's
    confirm we can print out the object type, of that first element. I'll use get class and get name, to print out the
    type of the element.
End-Part-7
*/

        System.out.println(wrapperArray[0].getClass().getName());

/*
Part-8
        And running that,

                java.lang.Integer

    we can confirm that the first element is java.lang.Integer. So the JVM auto-boxed the literal number 50, before assigning
    it to an element in the array. We can also use autoboxing, with an array initializer, which I'll do next. I'll create a
    character array, with the elements, abc and d. And then I'll print out the array.
End-Part-8
*/

        Character[] characterArray = {'a', 'b', 'c', 'd'};
        System.out.println(Arrays.toString(characterArray));

/*
Part-9
        Here, we've got an anonymous array initializer, that sets up an array of the Character wrapper class. And then we
    want to print that array out. Running that,

                [a, b, c, d]

    we don't get any kind of runtime errors, and we get those characters printed out as a result. So this is an example
    of using an array initializer, using char literals, and the JVM auto boxes each of those values. Before we look at
    Lists, let's visit auto boxing and unboxing when it comes to method declarations. First, you should know that this
    feature, is supported within methods themselves, both with method parameters, and method return types. I'm going to
    create a few more private static methods, which should demonstrate auto boxing, in a few different ways.
End-Part-9
*/

        var ourList = getList(1, 2, 3, 4, 5);
        System.out.println(ourList);

/*
Part-13
        I'm purposely using the "var" keyword here, because I want you to see the IntelliJ hint. I have this turned off
    at the moment, so nothing is showing. Let me show you how to have it show, in IntelliJ.

        Come up here to File and select Settings. Type "inlay", and click into "Type". Then click into Java, and check
    Implicit Types, which I will do now. Finally, I'll click "OK" to go back to the code and notice the return type hint
    is showing now.

                var ourList : "ArrayList<Integer>" = getList(1, 2, 3, 4, 5);

    It shows we're getting back an ArrayList of Integer. And then we just want to print out that list. When we run it,

                [1, 2, 3, 4, 5]

    and that just prints out a list of numbers 1 through 5. Now, what if I change this method, to take a variable argument
    of Integer wrapper class?

                private static ArrayList<Integer> getList(int... varargs)
                                    to
                private static ArrayList<Integer> getList(Integer... varargs)

    With this change, everything still compiles, and runs, even though in the main method, we're passing literal int values.
    This method's expecting Integer instances, but the JVM will again auto box everything in that variable argument list.
    In a way, this means we can almost use primitives, and their corresponding wrappers, interchangeably. What this means
    is that, for the factory objects, that create lists, we can create lists from primitive literals, as we did in line
    226 of the main method. We can simply replace this call to our custom method, with the List of factory method, which
    we've talked about previously.

                var ourList : "ArrayList<Integer>" = getList(1, 2, 3, 4, 5);
                            to
                var ourList : "List<Integer>" = List.of(1, 2, 3, 4, 5);

    Remember this specific method "List.of" returns an unmodifiable list. Note when I do that, the inlay hint updates to
    show a new return type from the List of factory method.
End-Part-13
*/
    }

    private static Double getDoubleObject() {
        return Double.valueOf(100.00);
/*
Part-4
        In this code, we use the static factory method, Double.valueOf, just to make it clear, that in this case, we're
    returning an object, of type Double. In the second method, I want to return a double primitive value.
End-Part-4
*/
    }

    private static double getLiteralDoublePrimitive() {
        return 100.0;
/*
Part-5
        And this method, is pretty simple. We just return a literal decimal number, because that's a Java double primitive.
    And going back to the main method, we'll call these methods.
End-Part-5
*/
    }

    private static int returnAnInt(Integer i) {
        return i;
/*
Part-10
        In this code, we take a java.lang.Integer parameter as an argument, and simply return it from this method, whose
    return type is an int primitive. So again, this unboxes the i argument, to return the primitive value boxed in the
    wrapper argument, as the return value from this method. Let's try things the other way around. Similar to before, but
    return as integer this time, and it returns an Integer.
End-Part-10
*/
    }

    private static Integer returnAnInteger(int i) {
        return i;
/*
Part-11
        This method takes an int parameter, and returns it from a method, that's got a declared return type, of java.lang.Integer.
    An object is expected to get returned, in other words. So eye is auto boxed, to an instance of the Integer wrapper
    class, and returned, without us having to worry about or write that code. Putting another twist on this, we can invoke
    these methods, passing boxed and auto-boxed values to these methods. I won't invoke these methods from the main method.
    Instead, let's extend this a little, creating a method that takes a variable argument of int primitives, as one of its
    parameters. And we will use this method in code. The method will return an ArrayList of Integer and I'll call it get
    list.
End-Part-11
*/
    }

    private static ArrayList<Integer> getList(int... varargs) {

        ArrayList<Integer> aList = new ArrayList<>();
        for (int i : varargs) {
            aList.add(i);
        }
        return aList;
/*
Part-12
        This method is declared with a single parameter, a variable arguments declaration, for the int primitive. This
    means this method can be called with 0 to many int values. Inside the method, we create a new instance of an ArrayList,
    with the Integer wrapper class as its type, in <>. We can't put just the int type in there (no <int>), sp this is a
    good place for auto boxing. We use a for loop, to iterate over the values we got passed to the method, in the varargs
    argument, and add these primitives to our ArrayList. Now the "add" method on an ArrayList, is declared with an Integer
    wrapper parameter type, not a primitive type, so we're really showing many kinds of auto boxing here. Finally, we
    return the list we created from this method. And we want to call that from our main method.
End-Part-12
*/
    }
}
