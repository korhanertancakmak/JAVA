# ArrayList Challenge

<div align="justify">
First, I'm going to create a static field for the scanner instance. 
I'll use this instance, in several of the methods I'll be creating. 
I'll make this private, since it only gets used by this class. 
And it needs to be static, since we'll be calling it from static methods. 
Next, I want to create the method that displays the menu options. 
I'll call it printActions. 
And for this, I'm going to take advantage of Java's text block feature. 
Let me set this up with an empty text block first:

```java  
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static void printActions() {
        String textBlock = """
                Available actions:
                0 - to shutdown
                1 - to add item(s) to list (comma delimited list)
                2 - to remove any items (comma delimited list)
                Enter a number for which you want to do:""";
        System.out.print(textBlock + " ");
    }
}
```

So text block is just a special way to format a multi-line string in what you see is what you get a manner. 
Here I am assigning the textBlock to a String variable. 
And we start a text block with """. 
These have to be on a separate line from the rest of the block. 
After that, we type in what we want the user to see. 
And then we end the text block with """. 
I'm going to paste the menu items in between """. 
So notice, I've got the last """, immediately after the last bit of a text. 
This is on purpose, so we won't have an extra new line included. 
When the user enters input, we want it to be entered on the same line as this prompt. 
I'm also adding an extra space when I print this out, because the text block removes all trailing spaces. 
This one extra space helps separate the input from the prompt. 
Ok, now back to the main method.

```java  
public static void main(String[] args) {

    boolean flag = true;
    ArrayList<String> groceries = new ArrayList<>();

    while (flag) {
        printActions();
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> addItems(groceries);
            case 2 -> removeItems(groceries);
            default -> flag = false;
        }
        groceries.sort(Comparator.naturalOrder());
        System.out.println(groceries);
    }
}
```

First, we create a boolean flag, and set it to true. 
We set this to false in the loop if the user enters something other than one or two. 
Right now, if any number is typed in (including 1 and 2), that will be the case, 
but we'll add some extra case options shortly. 
And we set up the while loop by using that flag as the condition. 
We first execute the printActions method. 
Now, we have a switch statement. 
And in that switch expression, we have a compound expression.
I could've put these all on separate lines, but this is an example of using an expression 
that evaluates to an integer, in a switch condition. 
We use scanner.nextLine, which reads input from the console. 
That's getting passed directly to the method parseInt on the Integer wrapper class. 
We've done this quite a few times in other code. 
If the user enters anything that's not an integer, this method will fail, and the code will crash. 
But we're not doing any validation on purpose to make this code simpler. 
And then, we're setting up the default value with the enhanced switch statement syntax. 
The default behavior just sets the flag to false which should break out of the while loop. 
If I run this:

```java  
Available actions:
0 - to shutdown
1 - to add item(s) to list (comma delimited list)
2 - to remove any items (comma delimited list)
Enter a number for which you want to do:
```

We'll see the menu options, and we see that we're prompted to enter something, 
after the last statement, and colon, on the same line, so that's good. 
As I mentioned, right now, at this point any number we put in there will break out of the loop. 
We still need methods to add one or more items, and to remove items. 
I'll start with adding elements, but first we need a grocery list. 
I'm just going to add this as a local variable to the main method right before the while loop.

```java  
ArrayList<String> groceries = new ArrayList<>();        <<< added before while loop
groceries.sort(Comparator.naturalOrder());              <<< added into while loop
System.out.println(groceries);                          <<< added into while loop
```

The grocery list will just be a list of String, so we specify that type in the <> (angle brackets), 
on the left side of the assignment. 
We still want to include <>, or diamond operator, on the right side of the assignment in the creation expression.
After each selected action item, we'll sort the list. 
We do this by calling the sort method, and passing a Comparator to the method,
getting that from the static method on Comparator, called naturalOrder. 
We've seen this in use previously, in this section. 
And then we'll just print the grocery list out, passing the ArrayList instance to System.out.println directly. 
And that's it for printing out a sorted list. 
Ok, next for the methods. First **addItems**.

```java  
private static void addItems(ArrayList<String> groceries) {
    System.out.println("Add item(s) [separate items by comma]:");
    String[] items = scanner.nextLine().split(",");
    //groceries.addAll(List.of(items));
}
```

The first thing I'm doing is to prompt the user to enter a list of items; they want at the grocery store. 
They can enter the items on a single line, separated by commas. 
After the prompt, we call scanner.nextLine, which reads the data. 
I chain the split method on string to that call, which splits the string we got from the user, 
by the comma, and returns a String array. 
We could loop through and add these items to the list, one at a time. 
Instead, for now, I'm using the addAll method, passing the result of a call to List.of using the String array; 
we just created out of the data we got from the user. 
Going back to the main method. 
I'll add a call to this method.

If the user enters 1, as a menu item, we'll prompt them to add items to the grocery list, 
so we'll call the addItems method there. 
Now, remember, with this enhanced syntax for the switch statement, we don't need break statements.
The enhanced switch doesn't have the problem of falling through, like the traditional switch statement does,
so the flag isn't going to get set to false there. 
Ok, so let's test this out.

```java  
Available actions:
0 - to shutdown
1 - to add item(s) to list (comma delimited list)
2 - to remove any items (comma delimited list)
Enter a number for which you want to do: 1
Add item(s) [separate items by comma]:
```

We get the first prompt, and we enter 1. 
Now, we're prompted to enter some items, separated by commas. 
I'll add eggs, milk, cereal, bananas, cheese, and ham.

```java  
[ bananas,  cereal,  cheese,  ham,  milk, eggs]
```

And there you can see, all those items got added, all at once, and although that's kind of fun, 
it's not really what we want. 
Also, notice how eggs are at the end of the sorted list. 
This is because all the other items start with a whitespace, 
and the whitespace takes precedence over letters in a sorted list. 
I'll take care of that shortly. 
Remember we don't want to add duplicate items, and this code lets us do it. 
So if I enter 1 again, and add eggs and cereal this time: 

```java  
[ bananas,  cereal,  cereal,  cheese,  ham,  milk, eggs, eggs]
```

We can see it getting added again, and now we have eggs and cereal in our list twice. 
Let's enter 0 to quit out of that loop, and then we'll fix our add items method. 
I'm going to comment out that line

```java  
groceries.addAll(List.of(items));
```

Which adds all the items the user entered. 
It really has two problems, it adds duplicates, and it includes extra spaces the user may have added. 
Let's fix both those problems. 
I'll use an enhanced for loop, to loop through the items.

```java  
for (String i : items) {
    String trimmed = i.trim();
    if (groceries.indexOf(trimmed) < 0) {
        groceries.add(trimmed);
    }
}
```

We're replacing the code that added all the items with this for loop. 
And the first thing we'll do is trim the array element, getting rid of leading and trailing whitespace, 
if the user included any, in their comma-delimited list.
Then we check if that element is already in the list with if statement.
We'll keep this code simple, just checking for a match on the exact case, 
using the indexOf method on the grocery list. 
If we don't find a match, if we get **-1** back from that method, we add the trimmed item to groceries. 
When I re-run this code, using the same tests as before, selecting 1, 
then entering eggs, milk, cereal, bananas, cheese, and ham.

```java  
[bananas, cereal, cheese, eggs, ham, milk]
```

You can see they get added, so we'll select 1 again now, and enter eggs and cereal this time.

```java  
[bananas, cereal, cheese, eggs, ham, milk]
```

So those don't get added as you can see, so that's good. 
Pressing 0 will get us out of that loop. 
Now, it's time to write the removeItems method, which will look a lot like the addItems method. 
And I'll add a call to this method in the switch statement of the main method.

```java  
rivate static void removeItems(ArrayList<String> groceries) {
    System.out.println("Remove item(s) [separate items by comma]:");
    String[] items = scanner.nextLine().split(",");
    for (String i : items) {
        String trimmed = i.trim();
        groceries.remove(trimmed);
    }
}
```

After adding the line:

```java  
case 2 -> removeItems(groceries);
```

Into the switch statement, we can test all our menu options. 
I'll start again with 1 and enter that same list of items, eggs, milk, cereal, bananas, cheese, ham, and enter 2, 
and I'll enter eggs and ham:

```java  
[bananas, cereal, cheese, milk]
```

And there you can see, our list no longer has those two items, so our remove method worked there. 
That's it for the ArrayList challenge.
</div>