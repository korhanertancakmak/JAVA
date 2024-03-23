package CourseCodes.NewSections.Section_10_ArrayList_LinkedList_Iterators_Autoboxing.Course01_CollectionsArrayLists;

import java.util.ArrayList;
import java.util.Arrays;

//Part-1
/*
                                     List, ArrayList, LinkedList, Iterator, Autoboxing

        Arrays were a massive improvement if you needed to store items of the same type, but as you saw, Arrays have some
    limitations. Not being able to change the number of elements in an Array being one. Fortunately, Java also includes
    an entire library for Java containers, which they call "Collections". They take the arrays we worked with to the next
    level. They allow you to change the number of elements defined in an array for one, but there are many other improvements
    as well.

        In this section, we'll be talking about lists, which are Java containers, and explain what they are, and how
    to use them. Two of the most common classes for lists, "ArrayList" and "LinkedList". We will start by looking at
    these. In addition, I'll be covering important concepts related to these topics, like Big O Notation, Iterators, Autoboxing
    and Unboxing, and the enum type. If you are unfamiliar with any of these terms, by the end of this section, they will
    make sense. There's a lot to cover in the section, so let's get started.

        An array is mutable, and we saw that we could set or change values in the array, but we could not resize it. Java
    gives us several classes that let us add and remove items, and resize a sequence of elements. These classes are said
    to implement a List's behavior. So what is a list?

                                                        Lists

        In our everyday life, we use lists all the time. When we're going to the grocery store, we've got a list. We have
    a list of things we need to do, a list of addresses, a list of contact numbers, etc. It wouldn't be a very useful list
    however, if we started with 10 items we could change, but never add or remove an item. List is a special type in Java,
    called an Interface. For now, I'll say a List Interface describes a set of method signatures that all List classes
    are expected to have.

        The ArrayList is a class that really maintains an array in memory, that's actually bigger than what we need, in
    most cases. It keeps track of the capacity, which is the actual size of the array in memory. But it also keeps track
    of the elements that'd been assigned or set, which is the size of the ArrayList. As elements are added to an ArrayList,
    its capacity may need to grow. This all happens automatically, behind the scenes. This is why the ArrayList is resizeable.
*/
//End-Part-1

record GroceryItem(String name, String type, int count) {
//Part-2
/*
        In the Description.txt source file, I want to create a record, called GroceryItem. If you remember, we can do this
    using the type "record" instead of class, and at its simplest, we pass the fields we want on the record, in the
    declaration's parentheses:

            record GroceryItem(String name, String type, int count) {
            }

    Ok, that's our grocery item record, with 3 fields, name, type, and count. But let's say 9 times out of 10, we'll have
    a count of 1, so we want a simpler way to create one of those records. We can add constructors to records, so I'll
    do that now:
*/
//End-Part-2

    public GroceryItem(String name) {

        this(name, "DAILY", 1);
    }

//Part-3
/*
        This constructor takes only 1 field, the grocery item name. For a record, we have to call the generated default
    constructor first, with all 3 fields. Now, I'll go to the main method, and create an array.
*/
//End-Part-3
    @Override
    public String toString(){

        return String.format("%d %s in %s", count, name.toUpperCase(), type);
    }

//Part-13
/*
        I'll type this manually, instead of using IntelliJ. I'll format the return String. I'm overriding the toString
    method, and returning a formatted string, using "String.format" that has 3 specifiers. The first specifier is for an
    integer, the count, and then we have 2 string specifiers, for the name which we want to be uppercase, and type. And
    if I run that:

        [6 APPLES in PRODUCE, 1 BUTTER in DAIRY, 1 MİLK in DAIRY, 5 ORANGES in PRODUCE]

    we see that 6 apples in "Produce" was added as the first list item, and the output is a bit easier to read. If instead
    of the add method, I use the set method, instead of inserting the apples, "set" will replace the first item, butter,
    with the apples, so I'll add that next:
*/
//End-Part-13
}

public class Main {
    public static void main(String[] args) {

//        Object[] groceryArray = new Object[3];
//        groceryArray[0] = new GroceryItem("milk");
//        groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
//        groceryArray[2] = "5 oranges";
//        System.out.println(Arrays.toString(groceryArray));

//Part-4
/*
        I'm next going to do, what I told you not to do previously, which is to create an array of Object. Here, I have
    groceryArray, and I've set it to a size of 3, meaning we can only have 3 items in this array. And we're assigning the
    first 2 elements, to instances of our new GroceryItem record, using the explicit one argument constructor I declared,
    and the default implicit constructor. The first instance, only takes name, and the second one, takes type and count
    as well. I'm going to add another element to this array, and then I'll print this array out. Here, I've set the element
    to a String, and not a grocery item at all. And because I've said we have an array of Objects, and not an array of
    GroceryItems, the compiler isn't going to prevent us from doing this. Any code now that expects this array to contain
    only GroceryItems, might have a problem processing this data. But I'll run this.

            [GroceryItem[name=milk, type=DAILY, count=1], GroceryItem[name=apples, type=PRODUCE, count=6], 5 oranges]

    As you can see, for our grocery items, we also know what department we might find that item in, but in the case of the
    String, it just prints "5 oranges". We could easily fix this, in this case, by making the array, an array of GroceryItem,
    so I'll change this code, improving it. I'll change the array from an array of Object, to an array of GroceryItem.
*/
//End-Part-4

        GroceryItem[] groceryArray = new GroceryItem[3];
        groceryArray[0] = new GroceryItem("milk");
        groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
        // groceryArray[2] = "5 oranges";                                                   // compiler error
        groceryArray[2] = new GroceryItem("oranges", "PRODUCE", 5);
        System.out.println(Arrays.toString(groceryArray));

//Part-5
/*
        You'll notice right away, that now, the compiler won't let us just assign a String, to the third element of this
    array. We need to change that, and properly set it to an instance of a new GroceryItem.

        groceryArray[2] = new GroceryItem("oranges", "PRODUCE", 5);

    And the compiler is happy with that change. Declaring your arrays with a specific type, allows compile-time type checking,
    as we've shown in this case. Type checking at compile-time, prevents runtime exceptions, when instances assigned to
    arrays, aren't what they are expected to be. Ok, so now I'll create a grocery list, using the ArrayList class instead
    of an array.
*/
//End-Part-5

        ArrayList objectList = new ArrayList();

//Part-6
/*
        I'll create and initialize a new ArrayList, called objectList. Now, we don't have an error, but what I want you
    to notice straight away, is that IntelliJ is highlighting the ArrayList class, in yellow, which is its way of warning
    us about something. If I hover my mouse over that class, IntelliJ gives us the message:

        "Raw use of parameterized class 'ArrayList"

    So what does that mean? Well, in truth, it's a lot like the example we just walked through, with the array of Object.
    If we don't specify a type, with an ArrayList, it's going to use the Object class by default. This is called the raw
    use of this type. And this will result in the same problems we saw, when we had an object array, meaning, any object
    at all could be put in this ArrayList. I'll type that in.
*/
//End-Part-6

        objectList.add(new GroceryItem("Butter"));
        objectList.add("Yogurt");

//Part-7
/*
        I'll add a new GroceryItem object, set to "Butter", to object list. And add a string "Yogurt", to our object list.
    First we add a groceryItem, but we're also adding a String with this second add statement. And like we had before, a
    String may not be what the code wants, or is expecting. And that's generally not behaviour we want. When you declare
    an Arraylist, you probably know what data you want in that list. In our case, it's a GroceryItem record, and we don't
    want anything else but GroceryItems in the list. So how do we specify a type for an ArrayList?

        I'll add a new declaration, then I'll talk about it:
*/
//End-Part-7

    //ArrayList<GroceryItem> groceryList = new ArrayList<GroceryItem>();
        ArrayList<GroceryItem> groceryList = new ArrayList<>();

//Part-8
/*
        So here, we used < and > signs, and we've specified the type. <GroceryItem> is the type we want all elements in
    this list to be, a GroceryItem record. And we've included this type, immediately following the ArrayList class, in the
    declaration of the variable on the left. We've also specified <GroceryItem> in the instantiation of this list, on the
    right of the assignment operator, or the equals sign "<>". Now, I'll hover over that second GroceryItem class, on the
    right side of the assignment, in IntelliJ. And notice here, that IntelliJ is telling us, this second reference to
    GroceryItem, which is called an explicit type argument, is unnecessary. In this case, I'll use IntelliJ's recommendation,
    and change this, by selecting Replace this when I hover over that second set of angle brackets. And that gets replaced
    with empty brackets:

            ArrayList<GroceryItem> groceryList = new ArrayList<>();

    These <>, when empty like this, are also called the diamond operator. Later we'll be talking about type arguments
    in a lot of details when we review generics, but right now, you should try to type your ArrayLists, as I show here.
    With this declaration, the compiler can do compile-time type checking because we've specified that we only want grocery
    items in our grocery list.
*/
//End-Part-8

        //groceryList.add("Yogurt");
        groceryList.add(new GroceryItem("Butter"));

//Part-9
/*
        I'll copy the lines above and paste them here, changing the name of the array, to groceryList in both statements.
    And you can see, the compiler won't let us add a String object to this list. If we hover over that, we see that GroceryItem
    is the required type. I'll comment that last line of code.

        Now, the other thing you might have noticed, in the previous statement, where we're creating a new ArrayList is
    we never specified a size for the list. An ArrayList is a resizable-array implementation, meaning it's not a fixed
    size, so we don't need to specify the number of elements it will have. It will grow automatically, as needed.

        In general, you'll want to include a type argument when using the ArrayList. When I said you should declare an
    ArrayList, with a type argument as we did in the code above, using GroceryItem. These <>, by themselves, called the
    diamond operator, can be used when Java can infer the type, as it can in this declaration and assignment statement.
    But if we forget the diamond operator, in the instantiation part of this statement, we'll get another warning from
    intellij about the raw use of a parameterized list. Hopefully, you'll remember this means, the compile-time type
    checking won't occur, so without using the diamond operator, we'd be allowing other types to be potentially included
    in this ArrayList. If I remove the diamond operator:

            ArrayList<GroceryItem> groceryList = new ArrayList();

    you can see intelliJ warning us about this problem. Let me print our grocery list out now.
*/
//End-Part-9

        System.out.println(groceryList);

//Part-10
/*
        For an ArrayList, we can simply use println, passing the ArrayList instance, so that's nicer than having to use
    a helper class, as we did with the array. I'll print out "grocery list" above, and we run that:

            [GroceryItem[name=milk, type=DAIRY, count=1], GroceryItem[name=apples, type=PRODUCE, count =6], GroceryItem[name=oranges, type=PRODUCE, count=5]]
            [GroceryItem[name=Butter, type=DAIRY, count=1]]

    This prints our list, which has 1 grocery item. I'll add a few more items to the list.
*/
//End-Part-10

        groceryList.add(new GroceryItem("milk"));
        groceryList.add(new GroceryItem("oranges", "PRODUCE", 5));
        System.out.println(groceryList);

//Part-11
/*
        I'll add a milk grocery item. And I'll add an "oranges" as "grocery item". And running that,

            [GroceryItem[name=Butter, type=DAIRY, count=1], GroceryItem[name=milk, type=DAIRY, count=1], GroceryItem[name=oranges, type=PRODUCE, count=5]]

    we get all 3 items in our list printed, butter and milk, both in the dairy department, and oranges in produce. And now,
    I'll add apples, but let's say we want apples to be first in the list. We can use an overloaded version of the add
    method for that.
*/
//End-Part-11

//        groceryList.add(0, new GroceryItem("apples", "PRODUCE", 6));
//        System.out.println(groceryList);

//Part-12
/*
        I'll use add again, but will specify an index number for apples. This version of "add" takes an index as the first
    argument. What we're saying is, instead of adding this new item to the end of our existing list, let's actually add
    it at index 0, moving all the other elements down. Before I run that, I'll simplify how the record gets printed out.
    Going to our GroceryItem record, I'll add my own toString method to this record.
*/
//End-Part-12

        groceryList.set(0, new GroceryItem("apples", "PRODUCE", 6));
        System.out.println(groceryList);

//Part-14
/*
        And running this code,

            [6 APPLES in PRODUCE, 1 MİLK in DAIRY, 5 ORANGES in PRODUCE]

    You see we have replaced the butter item with 6 apples. We can also remove an item, by index, so if we wanted to remove
    milk from our list, we could pass 1, which means the 2nd element. Like arrays, ArrayLists also start with an index of
    0. I'll remove the 2nd element from the grocery list:
*/
//End-Part-14

        groceryList.remove(1);
        System.out.println(groceryList);

//Part-15
/*
        And running this code,

            [6 APPLES in PRODUCE, 5 ORANGES in PRODUCE]

    we can confirm, we've removed milk, the 2nd item from the list. Now, I want to create a new class in the same package
    with Main, named MoreLists as the name, and I'll add the main method to it.
*/
//End-Part-15
    }
}
