package CourseCodes.NewSections.Section_10_ArrayList_LinkedList_Iterators_Autoboxing.Course01_CollectionsArrayLists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MoreLists {

    public static void main(String[] args) {

//Part-16
/*
        In this code, I want to start with a String array, with some groceries we might buy. First, we have an array of
    String, with 4 Strings in this array. Next, we're using a method onList, which is a factory method. We've talked about
    factory methods previously. This is where we call a static method on a class, and it returns a new instance of a class
    for us. And here we're calling a static method on List, simply called "of", and that returns a List of String elements
    back. And we're assigning this to a List variable. Notice we're using the type argument "String" on the left side, in
    the declaration of this variable. Now "list" is not ArrayList, and there's a reason why I'm not assigning this to an
    ArrayList of String. And that's because the object coming back from this method isn't actually an ArrayList.
*/
//End-Part-16

        String[] items = {"apples", "bananas", "milk", "eggs"};

        List<String> list = List.of(items);
        System.out.println(list);

//Part-17
/*
        Running this,

                [apples, bananas, milk, eggs]

    we get a nicely printed List of our array items. So this method transformed an array of String to a List of Strings.
    Let's expose this a bit more and see what kind of object we're getting back from this static factory method. I've
    shown you previously that we can do this by calling getClass(), and then chaining getName to that.
*/
//End-Part-17

        //System.out.println(list.getClass().getName());

//Part-18
/*
        The getName() method gives us a little extra information than getSimpleName, which we've seen before, also. And
    running this,

            java.util.ImmutableCollections$ListN

    we learn that this List is something called, java.util.ImmutableCollection$ListN. Ok, so what is that? Well, the list
    we're getting back is of type List N, and it's a nested class within the ImmutableCollections class but most importantly,
    it's immutable. Alright, we can test this by trying to add an item to this list:
*/
//End-Part-18

        //list.add("yogurt");

//Part-19
/*
        I'll attempt to add the string literal yogurt, to our list. If we try to run this code, we get an exception, an
    UnsupportedOperationException. So why would I show you a method to get an immutable list? Well, as it turns out, this
    method can be used in the creation of an ArrayList, to make populating the ArrayList a bit easier. Let me show you that
    here next. But after commenting the last 2 lines.
*/
//End-Part-19

        ArrayList<String> groceries = new ArrayList<>(list);
        groceries.add("yogurt");
        System.out.println(groceries);

//Part-20
/*
        Here, we're creating an ArrayList of String, using the variable groceries, and assigning a new instance of the
    ArrayList to it. And notice the diamond operator on the left. I could've included the type "String" in there, but it's
    not required, and this is simpler to read and write. And now look, we're calling a constructor on ArrayList, and passing
    an argument, the "list" we created above. The result of this instantiation is a new mutable ArrayList, populated with
    the elements from the immutable list we passed. And to prove this list is mutable, I'll add a new element, yogurt,
    then print groceries out. Running that,

                [apples, bananas, milk, eggs, yogurt]

    We get the four items we had, plus yogurt in the output. Looking back at the "List.of" method, it's overloaded, and
    supports different versions, including variable arguments which we talked about previously. I'll simplify this code
    and create a list with specified elements, all in one statement. Let me show you what that looks like:
*/
//End-Part-20

        ArrayList<String> nextList = new ArrayList<>(
                List.of("pickles", "mustard", "cheese"));
        System.out.println(nextList);

//Part-21
/*
        Here, we create a new ArrayList, and this time, we can just pass a list of Strings, to the static method on List,
    "List.of", and we put that in our constructor parentheses as shown. This is one way to create and populate a small
    ArrayList, in one statement, with a few known elements. Let's keep looking at ways to add elements. There is another
    method, addAll method, on ArrayList, which takes any List as an argument. I'll use that next.
*/
//End-Part-21

        groceries.addAll(nextList);
        System.out.println(groceries);

//Part-22
/*
        Here, we're adding everything in our second list to our first list, with the addAll method, and passing nextList
    to it. And when I run that,

                [apples, bananas, milk, eggs]
                [apples, bananas, milk, eggs, yogurt]
                [pickles, mustard, cheese]
                [apples, bananas, milk, eggs, yogurt, pickles, mustard, cheese]

    you can see our grocery list now has eight items in it. All the elements in nextList were added to our grocery list.
    You can retrieve an element in an ArrayList, by its index, much like the way we reference an array element by its
    position, in []. But for an ArrayList, we use the get method, not []. I'll show an example of using the get method,
    to print out the 3rd element in our list.
*/
//End-Part-22

        System.out.println("Third item = " + groceries.get(2));

//Part-23
/*
        Like arrays, ArrayLists start with a zero-index position, so the 3rd element is at index 2. Running this,

                Third item = milk

    This code simply prints out 3rd element. Next, let's talk about searching for an item in the list. The ArrayList provides
    several methods to help us do this. First, there is the contains method that returns a boolean, returning true if it
    finds a match:
*/
//End-Part-23

        if (groceries.contains("mustard")) {
            System.out.println("List contains mustard");
        }

//Part-24
/*
        And if I run that,

                List contains mustard

    we get the message. This method will work for many of Java's built-in classes, like String, because it ultimately calls
    the class's overridden equals method. We'll look at this more for our own classes in later. We can also use the indexOf
    method, and the lastIndexOf method. These methods return the index position of the element in the "list" if it finds
    it, instead of just returning true or false. These methods return "-1" if the element isn't found in the list. ArrayLists
    can have duplicate elements, so I'll add yogurt to the list, even though it's already there, as the 5th element.
*/
//End-Part-24

        groceries.add("yogurt");
        System.out.println("first = " + groceries.indexOf("yogurt"));
        System.out.println("last = " + groceries.lastIndexOf("yogurt"));

//Part-25
/*
        I'll print out groceries.indexOf for yogurt. And I'll also print out the last index of yogurt. And running this,

                first = 4
                last = 8

    we see that the indexOf method found the first instance of yogurt at index 4, and the lastIndexOf method found the
    yogurt element, just added at the end of the list, at index 8. In addition to finding elements, we have several methods
    we can use to remove elements from a list. We can remove by index if we know it, or we can remove by the element's
    value.
*/
//End-Part-25

        System.out.println(groceries);
        groceries.remove(1);
        System.out.println(groceries);
        groceries.remove("yogurt");
        System.out.println(groceries);

//Part-26
/*
        First, I'm pointing the full list out, before removing anything. Then I remove the 2nd element in the list, by
    passing int value "1", to the remove method. And print out our list again. Finally, I'll remove yogurt from the list
    If a match is found, and print the list again. When I run this again,

                [apples, bananas, milk, eggs, yogurt, pickles, mustard, cheese, yogurt]
                [apples, milk, eggs, yogurt, pickles, mustard, cheese, yogurt]
                [apples, milk, eggs, pickles, mustard, cheese, yogurt]

    you can see that "bananas", the 2nd element, was removed. And then, we removed yogurt, but notice, only the first yogurt
    element was removed, so this remove method only removes a single element. In addition to removing 1 element at a time,
    we can also remove a set of elements.
*/
//End-Part-26

        groceries.removeAll(List.of("apples", "eggs"));
        System.out.println(groceries);

//Part-27
/*
        Here, we're using the static method "List.of", to create a temporary list from a variable arguments list of elements.
    These are the elements we want removed, so if I run this code now:

                [milk, pickles, mustard, cheese, yogurt]

    our list no longer contains apples or eggs. Another method that might remove the elements is called retainAll. This
    method's functionality is opposite to that of the removeAll method, but since it potentially removes elements from a
    list, let's look at it next.
*/
//End-Part-27

        groceries.retainAll(List.of("apples", "milk", "mustard", "cheese"));
        System.out.println(groceries);

//Part-28
/*
         I'm using List.of again, passing 4 grocery items. This list gets passed to the retainAll method, called on our
    groceries list. And this method removes every item in the grocery list, which isn't one of the items passed in the
    argument. After executing this method,

                [milk, mustard, cheese]

    our list should only contain 3 items, milk, mustard, and cheese. Before retailAll call, our list didn't include apples,
    so that element is just ignored here. Finally, we can remove all the elements from a list, with the clear method.
*/
//End-Part-28

        groceries.clear();
        System.out.println(groceries);
        System.out.println("isEmpty = " + groceries.isEmpty());

//Part-29
/*
         First line clears all elements in the list, and we'll print that out to confirm it. And while we're at it, we
    call another method on list, called "isEmpty" which returns true, if the list has no elements in it. Running this,

                []
                isEmpty = true

    we get the output, "[]" with no elements listed, and "isEmpty = true". Now I'll add some elements back to this empty
    grocery list, using the addAll method.
*/
//End-Part-29

        groceries.addAll(List.of("apples", "milk", "mustard", "cheese"));
        groceries.addAll(Arrays.asList("eggs", "pickles", "mustard", "ham"));

//Part-30
/*
         By now, the first statement should be starting to look familiar. I'm using the static method "List.of", with a
    variable list of Strings, then passing that result to the first call to the "addAll" method, on our groceries ArrayList.
    Like "List.of()", "Array.asList" takes a variable argument list of elements and produces a list and we can pass that
    list to our second call to groceries.addAll method. After executing these methods, we'll have 8 elements in our list.
    Now that we have data again in the ArrayList, let's sort the elements. ArrayList has a sort method, so I'll just call
    it on groceries.
*/
//End-Part-30

        System.out.println(groceries);
        groceries.sort(Comparator.naturalOrder());
        System.out.println(groceries);

//Part-31
/*
         Again, we want to print the grocery list first before we sort it. Next, we call the sort method on the list.
    This method has 1 argument which is a type called "Comparator". This special type allows instances to be compared to
    one another. We're going to be talking a lot more about Comparators, when we talk about interfaces. For now, this
    method requires one, and Java provides the one we need, with a static factory method on the Comparator type, called
    naturalOrder. We call it and pass the result directly to the sort method argument list. This object allows ArrayLists,
    with types that have a natural order, to be sorted. Strings will be ordered alphabetically, from A to Z, for example,
    and numbers will be ordered from smallest to largest. Running this,

                    [apples, milk, mustard, cheese, eggs, pickles, mustard, ham]
                    [apples, cheese, eggs, ham, milk, mustard, mustard, pickles]

    we can see our grocery list gets sorted, as we'd expect. We can also call the reverseOrder static method, on the
    Comparator type, to sort in reverse. I'll call reverse order this time.
*/
//End-Part-31

        groceries.sort(Comparator.reverseOrder());
        System.out.println(groceries);

//Part-32
/*
         Running that,

                    [pickles, mustard, mustard, milk, ham, eggs, cheese, apples]

    Ok, so we've looked at 2 ways to transform arrays, or variable arguments into list. Now let's go the other way, getting
    list from an array, with a different method on ArrayList. This is an overloaded method, appropriately called, toArray.
    This method will return an array, the same size as the array we pass to it, and of the same type.
*/
//End-Part-32

        var groceryArray = groceries.toArray(new String[groceries.size()]);
        System.out.println(Arrays.toString(groceryArray));

//Part-33
/*
         So firstly, we're passing a new String array to the toArray method, which initialized to the type and size we
    want the returned array to be. In this case we want an array that has the same size and type as our list. This method
    then returns an array of String. There may be times, you might want to pass your data as an array, to methods that
    accept arrays, rather than lists, as an example. Here we simply pass the array "new String[groceries.size()]" to the
    Arrays.toString method, and print the array out, which we know takes an array as an argument.

                    [pickles, mustard, mustard, milk, ham, eggs, cheese, apples]

    Ok, so that was a pretty fast review, of a lot of methods on List, which were implemented for the ArrayList class.
*/
//End-Part-33
    }
}
