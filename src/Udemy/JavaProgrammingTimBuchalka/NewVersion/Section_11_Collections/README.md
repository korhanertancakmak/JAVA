# [Section-11: Collections Framework]()
<div align="justify">

It represents different types of collections, such as sets, lists, and maps, 
belong to this framework. 
In this section, I'll revisit these types a little bit, 
to demonstrate how they fit into 
the big picture of this series of interfaces and classes. 
I'll also cover some other crucial types of collection objects, 
such as sets and maps.
By the end of this section,
you'll have a few more classes in your tool box for groups of many objects. 
Let’s get started.

A **collection** is just an object
that represents a group of objects.
In general, the group of objects has some relationship to each other.
Computer science has common names,
and an expected set of behavior,
for different types of collection objects.
Collection objects, in various languages,
include arrays, lists, vectors, sets, queues, tables, dictionaries,
and maps (not geographical maps, but data maps).
These are differentiated by the way
they store the objects in memory,
how objects are retrieved and ordered,
and whether nulls and duplicate entries are permitted.
Oracle's Java documentation describes its _collections framework_ as:
_A unified architecture for representing and manipulating collections,
enabling collections to be manipulated independently of implementation details_.
That's a mouthful, but the term manipulated independently of implementation
details should give you a hint that it's based on interfaces.
If you're interested in reading the Oracle documentation,
they have a good overview at the following
[link](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html)

This was written for JDK-8, but it still applies.
Strictly speaking, arrays and the array utilities in the **java.util.Arrays** class
are not considered part of this framework.
All collection objects implement the Collection interface,
except maps, and I'll explain why in this section.
</div>

## [a. Collection Interface]()
<div align="justify">

The Collection interface is the root of the collection hierarchy. 
Like most roots in software hierarchies, it's an abstract 
representation of the behavior you'd need for managing a group of objects. 
This type is typically used to _pass "collections_ around, 
and manipulate them where _maximum generality_ is desired. 
Remember, the interface lets us describe objects by what they can do, 
rather than what they really look like, 
or how they're ultimately constructed. 
If you look at the methods on this interface, 
you can see the basic operations a collection of any shape, 
or type, would need to support.

![image01](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image01.png?raw=true)

We've already looked at every one of these operations
in our study of ArrayLists and LinkedLists. 
When managing a group, you'll be adding and removing elements, 
checking if an element is in the group, and iterating through the elements.
There are some others, but these are the ones that describe nearly 
everything you'd want to do to manage a group.
Java uses the term **Element** for a member of the group being managed. 
Let's jump into IntelliJ and see what we can do with this interface.

First, I'll create an empty array list of type String 
and assign it to a list local variable. 
I'll then create an array of Strings, 
and I'll use an array initializer to assign the names. 
I'll add these strings via a call to _addAll_, 
nesting a call to **Arrays.asList**. 
Print the list.

```java  
List<String> list = new ArrayList<>();
String[] names = {"Anna", "Bob", "Carol", "David", "Edna"};
list.addAll(Arrays.asList(names));
System.out.println(list);
```

This should be familiar ground for you. 
Running that:

```java  
[Anna, Bob, Carol, David, Edna]
```
            
I get all the names printed out.
I'll add a couple more statements, again, 
just using methods I showed you
that are defined on the Collection interface. 
Let's add another name, Fred. 

```java  
list.add("Fred");
list.addAll(Arrays.asList("George", "Gary", "Grace"));
System.out.println(list);
System.out.println("Gary is in the list? " + list.contains("Gary"));
```

And three more, via call to addAll,
and as list as we did earlier.
Print what we have.
Running that code:

```java  
[Anna, Bob, Carol, David, Edna]
[Anna, Bob, Carol, David, Edna, Fred, George, Gary, Grace]
Gary is in the list? true
```

I'll add a _removeIf_ statement for good measure with a lambda expression,
which will remove any names from the list 
that start with **G**, checking if _charAt(0) = G_. 

```java  
list.removeIf(s -> s.charAt(0) == 'G');
System.out.println(list);
System.out.println("Gary is in the list? " + list.contains("Gary"));
```

And I'll copy the 2 println statements from above 
to below this statement. 
Running that:

```java  
---(same)
[Anna, Bob, Carol, David, Edna, Fred]
Gary is in the list? false
```
            
These are all methods, defined on the Collection interface, 
but executed on a specific implementation, the ArrayList, 
but assigned to a List variable.

```java  
//List<String> list = new ArrayList<>();
Collection<String> list = new ArrayList<>();
```

I can actually abstract my variable type further, to Collection. 
I can still add elements and check if an element is in the collection. 
And all of my code compiles and runs, as before.
Ok, this is nothing new or exciting it's just how interfaces work. 

```java  
//List<String> list = new ArrayList<>();
//Collection<String> list = new ArrayList<>();
Collection<String> list = new TreeSet<>();
```

Now, let's change ArrayList to say TreeSet in that first statement. 
TreeSet is a different implementation, 
meaning it implements the Collection interface,
and don't worry I'll be talking about this class in detail coming up. 
Right now, I just want to swap out my collection type for another, 
that is significantly different from an ArrayList. 
And this code still runs, and the output is the same.

```java  
//List<String> list = new ArrayList<>();
//Collection<String> list = new ArrayList<>();
//Collection<String> list = new TreeSet<>();
Collection<String> list = new HashSet<>();
```

Now, let's try a different implementation, HashSet. 
This compiles and runs too:

```java  
[Edna, Bob, David, Carol, Anna]
[Gary, Edna, Bob, George, Grace, David, Fred, Carol, Anna]
Gary is in the list? true
[Edna, Bob, David, Fred, Carol, Anna]
Gary is in the list? false
```
                
But the output is different, isn't it? 
The elements aren't ordered. 
Not all collections are implicitly ordered,
by the way you add the elements, 
the insertion order in other words, like lists are. 
What if I wanted to sort this? 
Can I just call the _sort_ method on **List** as we did before? 
I'll add that statement, calling sort on my list variable 
right below the last println. 

```java  
String[] names = {"Anna", "Bob", "Carol", "David", "Edna"};
list.addAll(Arrays.asList(names));
System.out.println(list);

list.add("Fred");
list.addAll(Arrays.asList("George", "Gary", "Grace"));
System.out.println(list);
System.out.println("Gary is in the list? " + list.contains("Gary"));

list.removeIf(s -> s.charAt(0) == 'G');
System.out.println(list);
System.out.println("Gary is in the list? " + list.contains("Gary"));
list.sort();
```

But this doesn't compile, and if I hover over that, the error says,
_cannot resolve **sort** in Collection_. 
The Collection interface doesn't have a sort method.
The List interface does, but the Collection interface is more abstracted than a list. 
Another thing to know about the Collection interface is that 
there aren't any direct implementations of this top level interface. 
Other interfaces are derived from it,
and implementations (or concrete classes) implement the derived interfaces, 
like List and Set. 

![image02](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image02.png?raw=true)

This chart above shows the interface hierarchy.
It's also showing the implementations or concrete classes that
implement these interfaces, in bottom parts. 
Notice that **Map** does not extend Collection,
although still part of this framework. 
Maps are uniquely different, which I'll be explaining 
when I cover Maps in this section. 
You can see that LinkedList implements both List and Deque (double-ended queue), 
and I discussed this in detail when I covered LinkedLists.
Let's look at this on chart for an interface we know pretty well by now, **List**.
</div>

### List Interface
<div align="justify">

![image03](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image03.png?raw=true)

This chart shows the List interface extending Collection. 
For simplicity, I'm not showing the Collection methods that 
I showed on the previous chart. 
I'm only showing additional methods specifically declared on the List interface. 
We covered most of these methods, but I wanted you to see here that 
most of these are dealing with an index. 
A list can be either indexed, as an ArrayList, or not, like a LinkedList, 
but a LinkedList is implemented to support all of these methods as well. 
Derived interfaces may have specific ways to add, remove, get, 
and sort elements for their specific type of collection, 
in addition to those defined on the Collection Interface itself. 
Now, let's look at the big picture of interfaces
and some specific implementations.

![image04](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image04.png?raw=true)

A List is an ordered collection (also known as a sequence). 
These can be sequenced in memory like an ArrayList,
or maintain links to the next and previous values, as a LinkedList.                           
</div>

### Queue Interface
<div align="justify">

A Queue is a collection designed for holding elements prior to processing, 
in other words, the processing order matters, 
so the first and last positions, or the head and tail,
are prioritized. 
Most often these may be implemented as First In, First Out (FIFO), 
but can be implemented like a Stack, as Last In First Out (LIFO) 
which we've discussed.
Remember a Deque supports both.

![image05](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image05.png?raw=true)
</div>

### Set Interface
<div align="justify">

A Set is a collection conceptually based off of mathematical sets. 
Importantly, it contains no duplicate elements,
and isn't naturally sequenced or ordered.
You can think of a set as a kind of penned in chaotic grouping of objects.

![image06](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image06.png?raw=true)

Java has three implementations,
which I'll be reviewing in this section of the course in detail, 
the HashSet, the TreeSet and the LinkedHashSet. 
These are distinguished by the underlying way they store the elements in the set.
A Sorted Set is a set that provides a total ordering of the elements.
</div>

### Map Interface
<div align="justify">

A Map is a collection that stores key and value pairs. 
The keys are a set, and the values are a separate collection,
where the key keeps a reference to a value. 
Keys need to be unique, but values don't. 

![image07](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image07.png?raw=true)

Elements in a tree are stored in a key value Node, also called an Entry. 
In the sections coming up, we'll be looking at Set and Map, 
and how they resemble and differ from **List**. 
But before that, I want to talk about polymorphic algorithms.
</div>

### What's a polymorphic algorithm?
<div align="justify">

Oracle's documentation describes a polymorphic algorithm 
as a piece of reusable functionality.
At one time, most of these methods were provided to us, 
as static methods, on a class called **java.util.Collections**. 
Since JDK-8, and the advent of multiple interface enhancements, 
some of these methods are now on the interfaces themselves, 
as default or static methods. 
But not all, so I'll be discussing this class, 
and what it has to offer, in comparison to what's available on each collection class. 
It's also important to understand that legacy code 
will be using this class for some operations that can be done from the class itself. 
I'll comment out that last line of code 
so that the class still compiles. 

```java  
String[] names = {"Anna", "Bob", "Carol", "David", "Edna"};
list.addAll(Arrays.asList(names));
System.out.println(list);

list.add("Fred");
list.addAll(Arrays.asList("George", "Gary", "Grace"));
System.out.println(list);
System.out.println("Gary is in the list? " + list.contains("Gary"));

list.removeIf(s -> s.charAt(0) == 'G');
System.out.println(list);
System.out.println("Gary is in the list? " + list.contains("Gary"));
//list.sort();
```

Next, I'll be talking about this helper class and its methods. 
There are some fun polymorphic algorithms on there I haven't covered,
so I'll see you in that next section.
</div>

## [b. java.util.Collections Methods]()
<div align="justify">

In this lecture, I want to set up a Card class, 
which will be used to create a deck of playing cards. 
I'll be using these cards, and decks of cards, 
to demonstrate many of the methods on **java.util.Collections**. 
The Card will have three fields:

* a Suit, meaning Club, Diamond, Heart, or Spade.
* a face field, which will be a String, containing either 
the number of the card, 
or the face value of the card, Jack, Queen, King or Ace.
* a rank, an integer.

The Card should override the _toString_ method, 
and print the card with the face value (abbreviated, if a face card),
the _ascii_ character of the suit, and the rank in parentheses.
I'm including the _ascii_ characters 
that will print out each suit as a printable character.

```java  
CLUB  = 9827
DIAMOND = 9830
HEART = 9829
SPADE = 9824
```

The card should have the following public static methods 
to help anyone to use this class:

* _getNumericCard_ which should return an instance of a Card, 
based on the suit and number passed to it.
* _getFaceCard_ which should return an instance of a Card, 
based on the suit and abbreviation (J, Q, K, A) passed to it.
* _getStandardDeck,_ which should return a list of cards 
you'd find in a standard deck. 
See the previous notes for a full set of cards.
* _printDeck_, which should take a description, 
a list of cards, and a row count. 
This method will print the cards out in the number of rows passed.
* Finally, the card should have an overloaded printDeck method 
that will print _Current Deck_ as the description, 
and use 4, as the number of rows to be printed.

![image08](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image08.png?raw=true)

This chart shows my own plan, or the class diagram I'll be coding towards. 
I'm going to create a Card class. 
In my case, I'm just going to make it a record, 
because once a card is created, 
there's no sense in allowing anyone to change it.

Using a record gives me built in immutability,
if all my attributes are simple data types, like primitives and Strings.
Maybe you decided to create a Deck class, to contain your cards, 
and that's a good idea too. 
For the examples ahead, I'm just going to use List as my Deck container.

```java  
public record Card(Suit suit, String face, int rank) {
    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;

        public char getImage() {
            return (new char[]{9827, 9830, 9829, 9824})[this.ordinal()];
        }
    }

    @Override
    public String toString() {

        int index = face.equals("10") ? 2 : 1;
        String faceString = face.substring(0, index);
        return "%s%c(%d)".formatted(faceString, suit.getImage(), rank);
    }
}
```

First, I'll create a new record in the same package, 
and call that **Card**. 
I'll add my fields in the parentheses there, 
first a field with a type of **Suit**. 
That type is going to be an **enum** 
I'll create in just a minute, and that field will be named suit in lowercase. 
I'll follow that with a string field called face, and an int named rank.
I'm going to insert my enum **Suit** as a nested type in this record. 
I'll make it public because other classes, 
not in my package, might use it later down the road. 
It'll have four constants, each representing a suit, 
so **CLUB**, **DIAMOND**, **HEART**, and **SPADE**. 
For the standard deck, there's no real difference in ranking 
for these suits, so my ordinal value in this enum isn't really important. 
In other words, it doesn't matter how I list my constants in this enum, 
so I'm just choosing alphabetical order. 
I've added a semicolon after the last constant, 
because I'm going to include an additional method on this enum. 
Next, I want to add a public method, _getImage_, that just returns 
the ascii character to print for each suit. 
I'm just going to set up a char array of those values 
in the same order as my constants, then index that array by the ordinal value. 
That completes the enum.

For the card record itself, I'll first override the _toString_ method, 
so I'll insert an override. 
I'll set up a local variable, index, which in all cases will be 1, 
unless my card is a 10, which is the only case 
where I want two digits to be retrieved. 
I'll retrieve either the first number 
or letter from the face field of the card, 
or just 10 if it's 10. 
I return a formatted string which is the number or face card abbreviation,
the suit character, which I can get from the get Image method I created, 
and then the rank of the card. 
Now, I want a bunch of public static methods on this class, 
to help anyone to build a deck, or printing out a list of cards.

```java  
public static Card getNumericCard(Suit suit, int cardNumber) {

    if (cardNumber > 1 && cardNumber < 11) {
        return new Card(suit, String.valueOf(cardNumber), cardNumber - 2);
    }
    System.out.println("Invalid Numeric card selected");
    return null;
}
```

I'll start with the method to return a standard numeric card, 
so it'll be a public static, and the return type is Card. 
I'm calling it _getNumericCard_, 
and passing it a specific suit and a cardNumber, 
so this will be a number between 2 and 10, for a standard deck. 
I want to make sure the card number passed is really a number between 2 and 10.
If it is, I'll return a new card instance with the suit passed,
the cardNumber as a string, and a generated rank. 
I want my lowest card to have a rank of zero, 
and since my lowest card is a 2, I'll subtract 2 here. 
Finally, if this method was called with some other number, 
I'll print there was a problem. 
And I'll return null in that case.

```java  
public static Card getFaceCard(Suit suit, char abbrev) {

    int charIndex = "JQKA".indexOf(abbrev);
    if (charIndex > -1) {
        return new Card(suit, "" + abbrev, charIndex + 9);
    }
    System.out.println("Invalid Face card selected");
    return null;
}
```

The next method, _getFaceCard_, is going to be very similar. 
However, instead of taking a card number,
it will take a char, the abbreviation for the face card, 
so J for Jack, Q for Queen, K for King, and A for ace. 
I'll validate that the character passed is one of those four, 
by calling the indexOf method, on a string that has those four characters. 
Now, this card isn't checking for the proper case, 
and you could add that if you wanted your own code to be more robust.
In general, the mechanism for creating a card will be 
in the context of creating a deck, 
so I'm not going to be too worried about bad data passed in here. 
If the index is greater than -1, it's one of the four valid letters. 
And I'll return a new card instance with the suit specified,
the abbreviation passed as a string, and again a generated ranking,
such that a Jack gets the rank of 9, and an Ace gets the highest rank of 12. 
If the code falls through to this point, an invalid character was passed. 
So I'll return null.

```java  
public static List<Card> getStandardDeck() {

    List<Card> deck = new ArrayList<>(52);
    for (Suit suit : Suit.values()) {
        for (int i = 2; i <= 10; i++) {
            deck.add(getNumericCard(suit, i));
        }
        for (char c : new char[]{'J', 'Q', 'K', 'A'}) {
            deck.add(getFaceCard(suit, c));
        }
    }
    return deck;
}
```

Our client can call these two methods. 
However, in most normal circumstances, 
they'll be called by the next method,
which returns an array list of cards, 
the standard deck of 52 cards, 
every card unique by suit and face card value.
Not all decks are standard. 
If any of you have played pinochle, 
you'll know there are other variations.
This method again will be public and static, 
it will return a List, 
and I'm going to specifically make a list of Cards,
and the name will be _getStandardDeck_, with no parameters. 
I'll first set up a local variable, again List, 
type argument Card, and I'm calling that deck, 
and assigning a new ArrayList with a capacity of 52, 
since I know this is the size of my deck. 
At the end of this method, I'll return this deck.
What I need to do between those two statements is 
add the 52 cards.
I'll loop through my enum values, my suits, 
using the values method on enum, and a for loop. 
For each suit, I'm going to create a set of numeric cards, 
then a set of face cards.
My first nested loop will loop from 2 to 10.
And I'll add the card I get back from calling the _getNumericCard_ method, 
passing it the current suit and the loop variable, _i_. 
My next nested loop will loop through the four characters 
that represent my face cards, J, Q, K and A. 
I'll add the card, calling _getFaceCard_ to get an instance. 
That method will give me a deck of 52 cards, in four suits,
first the cards 2 through 10, ranked from zero to eight, 
then the face cards, Jack through Ace, ranked from 9 to 13. 
I want to include a couple of overloaded printDeck methods next.

```java  
public static void printDeck(List<Card> deck) {
    printDeck(deck, "Current Deck", 4);
}

public static void printDeck(List<Card> deck, String description, int rows) {

    System.out.println("---------------------------");
    if (description != null) {
        System.out.println(description);
    }
    int cardsInRow = deck.size() / rows;
    for (int i = 0; i < rows; i++) {
        int startIndex = i * cardsInRow;
        int endIndex = startIndex + cardsInRow;
        deck.subList(startIndex, endIndex).forEach(c -> System.out.print(c + " "));
        System.out.println();
    }
}
```

These are public static void, named printDeck and take a List, 
with a type argument of Card. 
But the first one also takes a description, 
and the number of rows desired to use to print out the deck.
I'll always print a separator line of dashes to help see different outputs. 
If the description passed isn't null. 
I'll print that out on its own line.
I want a local variable, cards in row, 
which is the number of cards to be printed on any one row, 
which is the deck size divided by rows, 
If I passed 4 as rows, and my deck was a standard one, 
I'd get 13 cards in four rows this way. 
I loop from zero to one less than the rows. 
The starting index will be _i_, times the cards in the row, 
so for the first iteration this will be 0, 
for the second (in a standard deck), it'll be 13, 
then 26 and so on. 
The ending index is just the starting index plus 
the cards in the row. 
Next, I want to use the start and end index 
to get a sublist of my deck, 
then I chain the forEach method to the sublist, 
printing each card on the same line,
separated by a space.
After each row of cards, I want a line break. 
The overloaded version of this method will only have a list of cards,
and will call this method with some defaults. 
So I'm just going to call the overloaded version,
with CurrentDeck as the header, and in four rows. 
And actually, I'll move this overloaded version of the print deck method 
above the other one by cutting and pasting it.
So that's my setup. 
I'll test that this code works in the Main class:

```java  
public class Main {

    public static void main(String[] args) {
        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);
    }
}
```

Simply by calling the _getStandardDeck_ method on the **Card** class, 
assigning it to a variable, 
and then calling printDeck on the Card class as well. 
Running this code:

```java  
---------------------------
Current Deck
2♣(0) 3♣(1) 4♣(2) 5♣(3) 6♣(4) 7♣(5) 8♣(6) 9♣(7) 10♣(8) J♣(9) Q♣(10) K♣(11) A♣(12)
2♦(0) 3♦(1) 4♦(2) 5♦(3) 6♦(4) 7♦(5) 8♦(6) 9♦(7) 10♦(8) J♦(9) Q♦(10) K♦(11) A♦(12)
2♥(0) 3♥(1) 4♥(2) 5♥(3) 6♥(4) 7♥(5) 8♥(6) 9♥(7) 10♥(8) J♥(9) Q♥(10) K♥(11) A♥(12)
2♠(0) 3♠(1) 4♠(2) 5♠(3) 6♠(4) 7♠(5) 8♠(6) 9♠(7) 10♠(8) J♠(9) Q♠(10) K♠(11) A♠(12)
```
        
You can see why I wanted to print my deck by rows, 
it's easier to see all the cards, 
and when I create a standard deck, 
they're ordered by suit and rank as you can see. 
Ok, so that's the setup. 

It's important to understand that the **Collections** class 
is **not** the **Collections Framework**. 
The framework contains many interfaces and implemented classes,
as well as helper classes, which this Collections class is just one example. 
At one time, Java had interfaces,
but no support for static or default methods on them, 
so useful methods were packaged in these helper classes. 
Some of these methods have since been implemented 
on the interfaces themselves, 
but there's still some functionality on the Collections class 
you might find useful. 
I'll examine some of these in code, 
and compare them to the methods on the interfaces now available.

In the last lecture, I created some code I'll be using in this lecture, 
and a couple of the ones that follow. 
I'll briefly discuss it here, 
for those of you who decided to skip that walk through.

![image09](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image08.png?raw=true)

This code has **Card**, a record, with a nested enum, 
named **Suit**, declared in it. 
**Suit** is either a **Club**, **Diamond**, **Heart** or **Spade**, 
and this enum has a helper function, _getImage_, 
that returns a printable character value for that suit. 
I overrode the _toString_ method on **Card**, 
to print that character along with what I call the face, 
or face value of the card, so 2 through 10, 
or **Jack**, **Queen**, **King**, or **Ace**. 
Each card will also have a rank, 
starting with 0 for the lowest card. 
In a standard deck, the lowest card, a two, is 0, 
because an **Ace** is usually the highest value card, 
though an ace can represent a one sometimes. 
The **Card** has static helper functions 
I'll be using in the code in this lecture, one
to get an instance of a numeric card, 
one to get an instance of a face card. 
In addition, I can get a list of cards,
that represents a standard deck of cards. 
And there are functions to print the deck of cards.
This set up will give us lots of opportunities, 
to test out the many methods on **java.util.Collections**. 
I want to start out by exploring some methods on collections
for populating a list.

```java  
public class Main {

    public static void main(String[] args) {
        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART, 'A');
        Arrays.fill(cardArray, aceOfHearts);
        Card.printDeck(Arrays.asList(cardArray), "Aces of Hearts", 1);
    }
}
```

I want to start out by exploring some methods 
on collections for populating a list. 
Before I start, I'll first use a method I've shown you before, 
on the Arrays helper class, called _fill_, to fill an array.
I'm going to create an array of Cards, 
just for one suit, to start out, 
so I'll create an empty array of 13 Cards.
I'll generate an ace of Hearts card using the static _getFaceCard_ method. 
The thing to note here is that **Suit** is a nested enum inside **Card**, 
so for me to access it from this Main class, 
I have to use the Card type as a qualifier to the enum type name, **Suit**. 
Next, I'll call the _fill_ method on the **Arrays** class, 
passing the ace of hearts card. 
And I'll print that out, passing my array as a list backed by an array. 
I'll call the overloaded version of _printDeck_,
so I can pass a description, 
and I just want that printed out in a single row. 
Running this code,

```java  
---------------------------
Aces of Hearts
A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12)
```

You can see the output. 
I have an array of 13 cards, 
all filled with the same kind of card, 
an ace of hearts, 
and aces have a rank of 12.
I'm reviewing this method on arrays 
because I wanted to also show you that 
the Collections class has a _fill_ method 
that seems very similar,
so I want to look at that in comparison.

```java  
List<Card> cards = new ArrayList<>(52);
Collections.fill(cards, aceOfHearts);
System.out.println(cards);
System.out.println("cards.size() = " + cards.size());
```

This method takes a list, and an element. 
I'll create a new array list of cards, just calling it cards,
and specify that it can hold 52 cards, 
which is the number of cards in a standard deck. 
I'll next call fill on the Collections class this time, 
and pass it my card list, as well as the _aceOfHearts_ card. 
I'll print the list, simply by passing it to _System.out.println_ this time. 
And I'll print the size of the list.
This method, _fill_, looks a lot like **Arrays.fill**, 
so you'd expect to get an array of 52 cards back, 
all aces of hearts. 
Running this code though,

```java  
[]
cards.size() = 0
```

It shows that's not what really happens. 
Instead, the list is empty, 
and the size of the list is zero. 
When I initialize the ArrayList, passing it 52, 
it just sets the capacity to 52. 
It doesn't populate the list with elements.
Unlike an array, the list isn't populated with 52 null references, for example. 
Using _fill_ here, on the **Collections** class would fill 
the list (if it's size were greater than zero), 
meaning it would replace every element with the element passed to the method. 
The **Collections.fill** method doesn't add elements, 
so if my list is empty, it stays empty.
How can I fill this list with some default value? 
The Collections class offers us some alternatives.

```java  
List<Card> acesOfHearts = Collections.nCopies(13, aceOfHearts);
Card.printDeck(acesOfHearts, "Aces of Hearts", 1);
```

One of these methods is called _nCopies_ 
which creates a new list with the number of elements 
you specify as the first argument, 
filling it with the element you pass as the second argument. 
First, I'll set up another local variable,
and call it _acesOfHearts_, 
and assign that to the call to **Collections.nCopies**, 
passing 13, and the card I create earlier, _aceOfHearts_. 
And I'll use _printDeck_, so I get a line separator and a header, 
but I'll print these cards in a single row.

```java  
---------------------------
Aces of Hearts
A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12)
```
                
Like the **Array.fill** example, 
I now have 13 aces of hearts in this new list. 
I'm going to copy those last two lines, 
and paste a copy directly below that.

```java  
Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUB, 'K');
List<Card> kingsOfClubs = Collections.nCopies(13, kingOfClubs);
Card.printDeck(kingsOfClubs, "Kings of Clubs", 1);
```

Before I change anything, 
I want to first add a new card, before these two statements. 
I want to create a king of clubs, so I'll call get face card, 
passing it **Card.suit.CLUB**, and the character K. 
Now, I'll change the list name from aces of hearts,
to king of clubs on the first line.
I'll change the card passed to _nCopies_ on the second statement
from _aceOfHearts_ to _kingOfClubs_. 
Finally, I'll change the call to print deck, 
again replacing _acesOfHearts_ with _kingsOfClubs_,
and also specifying King of Clubs as the descriptive header. 
Ok, running that,

```java  
---------------------------
Kings of Clubs
K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11)
```

I now have a second list with just king of clubs. 
Next, I want to populate my full deck with some of these. 
Like **List**, **Collections** includes an _addAll_ method.

```java  
Collections.addAll(cards, cardArray);
Card.printDeck(cards, "Card Collection with Aces added", 1); 
```

Its first argument is the list I want to add elements to. 
The second argument is for the elements to be added,
but unlike list's _addAll_ method, 
this is a variable argument of elements to be added. 
The difference is that List's _addAll_ method takes a collection of elements to be added. 
Remember, my _cards_ list is still empty with an initial capacity of 52 cards.
I'll call **Collections.addAll**, 
passing it my _cards_ list, 
and I'll just simply pass my cardArray, of acesOfHearths, to that. 
And then I'll print out the _cards_ deck. 
Running this code,

```java  
---------------------------
Card Collection with Aces added
A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12)
```
            
You can see that my _cards_ list has the same cards now 
as the aces of hearts list. 
For good measure, what do you think happens, 
If I just execute _addAll_ on cards, 
passing it this array? 
Let me add that code, right after the _Collections.addAll_ method. 

```java  
Collections.addAll(cards, cardArray);
Card.printDeck(cards, "Card Collection with Aces added", 1); 
//cards.addAll(cardArray);
```

**cards.addAll(cardArray)** doesn't compile 
because you can't pass an array to the _addAll_ method on **List**.
You can pass a collection, or another list, 
which is why you'll often see me using _List.Of_, or _Array.asList_, 
as part of that method. 
I'll comment that last line.

Next, I want to examine the copy method on **java.util.Collections**. 
First, I'll comment last two statements. 
I'll next make a call to _Collections.copy_, 
passing it my now empty cards list, 
and the _kingsOfClubs_ list, 
which had 13 king of clubs cards in it. 
And then I'll print that out by calling print deck. 

```java  
Collections.copy(cards, kingsOfClubs);
Card.printDeck(cards, "Card Collection with Kings copied", 1);
```

The copy method on collections takes two arguments,
the first is the destination of the copied elements, 
and here I'm passing my _cards_ list, 
which I want to be my full deck. 
The second argument is the elements to be copied, 
and I'm passing the kings of clubs list. 
You'd expect this to copy the elements in kings of clubs to cards, 
so let me run this.

```java  
Exception in thread in "main" java.lang.IndexOutOfBounds Exception Create breakpoint: Source does not fit in destination
```

You can see I've got an error _Source does not fit in destination_. 
This is similar to the problem I had earlier with the fill method on Collections. 
My card list is empty. 
Remember I commented on the _addAll_ method, 
so nothing has filled or populated,
or added elements yet, to my card deck, 
so its size is still 0. 
You can't use this copy method if the number of elements 
in your current list is less than the number of elements 
in the source list (cards = 0, kingsOfClubs = 13).
I'll uncomment out those two lines of code above, 
so cards will have 13 aces of hearts in it,
before I use the copy method. 
And now running that code,

```java  
---------------------------
Card Collection with Kings copied
K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11)
```

You can see my aces in the card list, were replaced with kings. 
What happens if my destination has more elements than the source? 
Let's add some more cards.  

```java  
Collections.addAll(cards, cardArray);
Collections.addAll(cards, cardArray);
Card.printDeck(cards, "Card Collection with Aces added", 1); 
//cards.addAll(cardArray);

Collections.copy(cards, kingsOfClubs);
Card.printDeck(cards, "Card Collection with Kings copied", 1);
```

I'm just going to copy _Collections.addAll_ statement, 
pasting a copy right below it, so that we get 26 aces added.
I'm also going to change the call to print deck, to print on two rows:

```java  
Card.printDeck(cards, "Card Collection with Aces added", 2);
Card.printDeck(cards, "Card Collection with Kings copied", 2);
```

So it's easier to read the output. And running this,

```java  
---------------------------
Card Collection with Aces added
A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12)
A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12)
        ---------------------------
Card Collection with Kings copied
K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11)
A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12) A♥(12)
```
            
You can see it only copied over 13 elements, replacing the first 13 aces with kings. 
What I want you to understand here is that 
this method copies elements from one list to another 
it doesn't return a copy of your list. 
Elements are being assigned to the existing destination list, vs. 
a copy of the list being made. 
If you want a full list copy, you'd use the **List.copy**method, 
so let me revisit that method real quickly for you here.

```java  
cards = List.copyOf(kingsOfClubs);
Card.printDeck(cards, "List Copy of Kings", 1);
```

I'll reassign cards to the result of List.copyOf, 
passing it my kingsOfClubs list. 
And I'll print my _cards_ list in a single row.

```java  
---------------------------
List Copy of Kings
K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11) K♣(11)
```

This gives us the result, which is the same as the list kingsOfClubs. 
This method is a true copy, though it's important to remember that 
this method returns an unmodifiable list. 
If you needed a modifiable copy of the list, 
you'd make a call to the copy of method, 
and pass it to a List constructor, 
or use _addAll,_ for example. 

Now, I'm going to pick up the discussion 
to look at another method on collections,
that's fun to use when you have a deck of cards, 
and that's shuffle. 
I've already got a variable setup, called deck,
that contains my standard deck of cards.
I'll comment these at the top of the main method, 
and copy-paste it right below. 
Now, I want to shuffle my deck.

```java  
List<Card> deck = Card.getStandardDeck();
Card.printDeck(deck);
```

Now, I want to shuffle my deck. 
I could write some random function 
to do it for me, but even easier, 
I can use the shuffle method on Collections. 

```java  
Collections.shuffle(deck);
Card.printDeck(deck, "Shuffled Deck" , 4);
```

And I'll print my deck with a header 
of **Shuffled Deck**, and in four rows. 
Running this code:

```java  
---------------------------
Shuffled Deck
J♥(9) 6♠(4) 5♦(3) 2♠(0) 9♣(7) 5♠(3) J♦(9) Q♣(10) A♦(12) 8♥(6) A♣(12) 10♠(8) K♠(11)
2♣(0) A♠(12) 3♦(1) 3♣(1) Q♠(10) 9♥(7) 2♥(0) 6♥(4) 7♦(5) 8♠(6) A♥(12) 5♥(3) 10♥(8)
Q♦(10) 4♠(2) 10♣(8) K♥(11) J♠(9) 8♣(6) J♣(9) 8♦(6) 9♦(7) 3♠(1) 10♦(8) K♦(11) 9♠(7)
4♦(2) K♣(11) 5♣(3) 6♣(4) 2♦(0) 3♥(1) 7♥(5) Q♥(10) 4♣(2) 7♣(5) 6♦(4) 4♥(2) 7♠(5)
```
                
You can see that my deck got shuffled. 
This is a useful method if you're writing a card game certainly, 
and a good tool in your arsenal, 
if you ever want to randomize a list of existing elements for some reason. 
Another method is the _reverse_ method, 
and it's easier to see what that's doing after the _shuffle_ method, 
so let me add that next.

```java  
Collections.reverse(deck);
Card.printDeck(deck, "Reversed Deck of Cards:", 4);
```

I'll call the _reverse_ method on Collections, 
passing it my deck. 
I'll print the deck again, 
after this method. 
Running this code:

```java  
---------------------------
Reversed Deck of Cards:
7♠(5) 4♥(2) 6♦(4) 7♣(5) 4♣(2) Q♥(10) 7♥(5) 3♥(1) 2♦(0) 6♣(4) 5♣(3) K♣(11) 4♦(2)
9♠(7) K♦(11) 10♦(8) 3♠(1) 9♦(7) 8♦(6) J♣(9) 8♣(6) J♠(9) K♥(11) 10♣(8) 4♠(2) Q♦(10)
10♥(8) 5♥(3) A♥(12) 8♠(6) 7♦(5) 6♥(4) 2♥(0) 9♥(7) Q♠(10) 3♣(1) 3♦(1) A♠(12) 2♣(0)
K♠(11) 10♠(8) A♣(12) 8♥(6) A♦(12) Q♣(10) J♦(9) 5♠(3) 9♣(7) 2♠(0) 5♦(3) 6♠(4) J♥(9)
```

You see that this method simply reverses the order of the elements. 
It doesn't reorder it first, 
so the first element in the shuffled deck, _J♥(9)_, 
becomes the last element in reversed deck, and vice versa. 
Since I mentioned sorting, let's talk about that next. 
You may run across legacy code that uses Collections to sort. 
There are two flavors much like the _sort_ method on the **List** interface. 
One requires your elements in the list to implement Comparable, 
and one doesn't. 
Right now, I'll look at the one that does not. 
The _collections.sort_ method takes your list 
as the first argument.
If your elements implement Comparable, 
that would be it. 
But if they don't, or you want to force an alternative sort,
you can pass a **Comparator**, as we've seen in previous lectures. 
My Card record doesn't implement Comparable, 
and let's say, for whatever reason, I don't want it to.

Instead, I'll sort with a Comparator, 
which I'll set up as a local variable, 
because I think I may want to use 
this sort in different scenarios.
Hopefully you remember the easy way of using 
the convenience methods on Comparator, 
which let me do this.

```java  
var sortingAlgorithm = Comparator.comparing(Card::rank).thenComparing(Card::suit);
Collections.sort(deck, sortingAlgorithm);
Card.printDeck(deck, "Standard Deck sorted by rank, suit", 13);
```

I'll call the _comparing_ method on Comparator, 
passing it a method reference for Card rank. 
I'll chain the _thenComparing_ method to that, 
this time passing **Card::suit**. 
You can see, by looking at IntelliJ hints, 
I'm getting a **Comparator<Card>** back 
from those chained methods. 
Now I can pass my _sortingAlgorithm_ variable 
to the **Collections.sort** method, 
so first pass the deck, then sorting algorithm. 
I'll call **Card.printDeck**, 
with a header specifying how the deck is sorted, 
but this time I want to print 13 rows. 
You'll see why in a minute. 
Running this code:

```java  
---------------------------
Standard Deck sorted by rank, suit
2♣(0) 2♦(0) 2♥(0) 2♠(0)
3♣(1) 3♦(1) 3♥(1) 3♠(1)
4♣(2) 4♦(2) 4♥(2) 4♠(2)
5♣(3) 5♦(3) 5♥(3) 5♠(3)
6♣(4) 6♦(4) 6♥(4) 6♠(4)
7♣(5) 7♦(5) 7♥(5) 7♠(5)
8♣(6) 8♦(6) 8♥(6) 8♠(6)
9♣(7) 9♦(7) 9♥(7) 9♠(7)
10♣(8) 10♦(8) 10♥(8) 10♠(8)
J♣(9) J♦(9) J♥(9) J♠(9)
Q♣(10) Q♦(10) Q♥(10) Q♠(10)
K♣(11) K♦(11) K♥(11) K♠(11)
A♣(12) A♦(12) A♥(12) A♠(12)
```

You can see my cards are now ordered by the rank 
(and therefore their face value), 
from lowest to highest. 
This view is a little easier to see, 
that a standard deck of cards contains a two of every suit, 
and so on. 
Of course, I could have just called sort on Deck, 
and passed it that sortingAlgorithm. 
You might be asking which sort method you should use. 
I'll point out that IntelliJ has flagged this method in my code,
and if I hover over that, 
we get the answer to that question,
**Collections.sort** could be replaced with **List.sort**. 
In fact, if I control click on sort, 
and bring up the declaration of that method, 
you'll see that it simply calls **list.sort** anyway. 
When the sort method was added to the **List** interface, 
the underlying code on the **Collections** class
was changed to leverage it to support backwards 
compatibility of code that still uses the **Collections** class sort. 
In other words, there's no need to rush out 
and change the legacy code 
if you run across code still using **Collections.sort**. 
I won't change it in this code, but in the future, 
I'll be using **List**'s _sort_ method.

```java  
Collections.reverse(deck);
Card.printDeck(deck, "Sorted by rank, suit reversed:", 13);
```

Next, I'll call **collections.reverse** again 
so that my cards are sorted by highest to lowest rank. 
And I'll print that again, using 13 rows. 
Running that code:

```java  
---------------------------
Sorted by rank, suit reversed:
A♠(12) A♥(12) A♦(12) A♣(12)
K♠(11) K♥(11) K♦(11) K♣(11)
Q♠(10) Q♥(10) Q♦(10) Q♣(10)
J♠(9) J♥(9) J♦(9) J♣(9)
10♠(8) 10♥(8) 10♦(8) 10♣(8)
9♠(7) 9♥(7) 9♦(7) 9♣(7)
8♠(6) 8♥(6) 8♦(6) 8♣(6)
7♠(5) 7♥(5) 7♦(5) 7♣(5)
6♠(4) 6♥(4) 6♦(4) 6♣(4)
5♠(3) 5♥(3) 5♦(3) 5♣(3)
4♠(2) 4♥(2) 4♦(2) 4♣(2)
3♠(1) 3♥(1) 3♦(1) 3♣(1)
2♠(0) 2♥(0) 2♦(0) 2♣(0)
```

You'll see the standard deck sorted by rank,  
and the suit reversed.

```java  
List<Card> kings = new ArrayList<>(deck.subList(4, 8));
Card.printDeck(kings, "Kings in deck", 1);

List<Card> tens = new ArrayList<>(deck.subList(16, 20));
Card.printDeck(tens, "Tens in deck", 1);
```

The next couple of methods let you 
compare sub lists to full lists. 
I'll carve out a couple of smaller lists 
from my standard deck here, using list's sublist method, 
and passing them to a new array list constructor. 
My First sublist will be kings. 
Since my deck is ordered, 
I know my kings are in the second row of my last output, 
so the starting index of the sublist is 4, 
and the ending index is 8, 
which is an exclusive index.
And I'll print this new list in a single row. 
I'm going to copy and paste those two statements. 
I'll change kings to tens, and the indices used 
from 4 to 8, to 16 and 20, 
because this should be a list of my 10 cards. 
Ok, running that:

```java  
---------------------------
Kings in deck
K♠(11) K♥(11) K♦(11) K♣(11)
---------------------------
Tens in deck
10♠(8) 10♥(8) 10♦(8) 10♣(8)
```
                
You can see I have two lists, one with kings, 
and the other with tens.
Now I can use these lists 
to test a couple of other methods on Collections.

```java  
Collections.shuffle(deck);
int subListIndex = Collections.indexOfSubList(deck, tens);
System.out.println("sublist index for tens = " + subListIndex);
System.out.println("Contains = " + deck.containsAll(tens));
```

The first is _indexOfSubList_ that returns an integer 
if it finds a sublist in the collection passed to it. 
The first argument, I'll make the deck, will be searched. 
The second argument, I'll make that the tens list. 
_subListIndex_ will return an integer if tens are found in the deck, 
or a **-1** if it wasn't. 
And I'll print the result of that method call.
Running that:

```java  
---(same)
sublist index for tens = 16
```

You can see this method told me 
where I could find the sublist in the full list, 
starting at index 16, which we already knew. 
However, this method could be useful 
if you ever needed to identify 
if some portion of a list already exists in a bigger list.
Unlike _contains_, the elements in the sublist 
must be contiguously found in the full list.
I'll add _contains_ here next.
I'll print contains equals, 
then call _deck.containsAll_, and pass tens there.
Running that:

```java  
---(same)
Contains = true
```
                
You can see I got the result. 
So the list contains all the elements in the sublist. 
Now, I want to shuffle the cards 
before executing these two tests, 
so I'll add that, _Collections.shuffle_ 
and pass it deck, and running that:

```java  
sublist index for tens = -1
Contains = true
```

You can see the sublist index is -1, 
meaning the sublist wasn't found in the full list, 
but contains all, is still returning true, 
so it found the four elements. 
I'll revert to that last change, 
and comment the call to shuffle. 
Have you noticed that IntelliJ is highlighting _containsAll_, 
and if I hover over that, 
it gives me the message 
that _containsAll_ might have a poor performance.
The suggestion is to wrap the deck in a HashSet constructor. 
Don't worry about this suggestion right now. 
Our deck only has 52 cards, 
and I'm only showing you the containsAll method, 
in comparison to the index of subList method. 
But this hint is a good teaser of things to come. 
After I finish covering the Collections methods,
we'll be talking about Sets and HashSets.

```java  
boolean disjoint = Collections.disjoint(deck, tens);
System.out.println("disjoint = " + disjoint);
```

The next method I want to look at is the _disjoint_ method. 
This method returns true if the two lists have no elements
in common. 
This method takes two collections, 
and returns true if the two collections don't share elements, 
or false if they do.
I'll call this method, passing it my deck, and tens sublist,
and I'll assign the result to a boolean variable I'll call disjoint. 
And I'll print the value of disjoint. 
Running this code:

```java  
---(same)
disjoint = false
```
                
You see that disjoint is false. 
In this code, I'm comparing the full list with a just tens list, 
and since ten lists are really just a sublist of deck, 
we know there's elements in common, 
so they aren't disjoint collections.
Whether the tens are contiguous or not, 
disjoint should return false, 
meaning it found at least 
one of the elements in tens in the _cards_ list.

```java  
boolean disjoint2 = Collections.disjoint(kings, tens);
System.out.println("disjoint = " + disjoint2);
```

I'll copy and paste those two statements, 
I'll change the name of my variable to _disjoint2_ 
in that first statement, and change _deck_ to my _kings_ list. 
And in the second statement, 
I'll change disjoint to disjoint2 in both cases. 
Running this code:

```java  
---(same)
disjoint = true
```
                
You see that _disjoint2_ is true, 
meaning _kings_ and _tens_ don't have any elements in common.
Now that we've covered _shuffle_, _reverse_, _sort_, 
_indexOfSubList_, and _disjoint_. 

Again, like the **Arrays** class 
that gave us a _binarySearch_ method for arrays,
the Collections method supports a binary search method for lists. 
Both methods require the elements to be sorted first,
and neither guarantees what index is returned 
if you've got duplicates. 
The _binarySearch_ method on Collections is
overloaded like the _sort_ method. 
I can pass a List of Comparable elements, 
or I can pass a list with a separate Comparator,
which should match the way the elements are sorted. 
Both methods take an element, the element that will be searched for, 
which should be the same type 
as the elements in the List passed. 
I'll use this method on my deck of cards next.

```java  
deck.sort(sortingAlgorithm);
Card tenOfHearts = Card.getNumericCard(Card.Suit.HEART, 10);
int foundIndex = Collections.binarySearch(deck, tenOfHearts, sortingAlgorithm);
System.out.println("foundIndex = " + foundIndex);
System.out.println("foundIndex = " + deck.indexOf(tenOfHearts));
System.out.println(deck.get(foundIndex));
```

First, I want to create a ten of hearts card, 
which will be the card I want to search for in my deck. 
I'll set up a ten of hearts variable, 
and assign it what I get 
from the static method **Card.getNumericCard**
with Hearts as the suit, and the face value of 10. 
Now, I want a local variable, an int, 
which I'll call _foundIndex_, 
and assign that the result of calling 
binary search on the **Collections** class. 
I'm going to pass my deck, a list, 
and this new tenOfHearts. 
Lastly, I'll pass the sorting algorithm 
I set up earlier in this method, 
which I used to sort with, 
and I called that sorting Algorithm. 
Print the resulting index.
Print the matched element at that index.
However, running this code gives me an error.

```java  
Exception in thread "main" java.lang.IndexOutOfBoundsException Create breakpoint : Index -53 out of bounds for length 52
```
            
The index that came back was -53, not -1, 
but it still means the ten of hearts wasn't found. 
Can you guess why? 
If you scroll up and review my code, 
you'll notice that I reversed the original sort. 
Now, I'm attempting to use binary search on a list 
that isn't sorted the way I specified. 
This is important.
Your list must be sorted 
before you can execute binarySearch on it. 
If I'm passing a comparator, 
my list has to be sorted that way before executing this method. 
I can fix this by calling sort again on my deck 
with the same comparator I passed to the binary search. 
I'll insert **deck.sort(sortingAlgorithm)** statement 
before I create the _tenOfHearts_ card. 
Re-running my code now,

```java  
---(same)
foundIndex = 34 
10♥(8)
```
                
I get a found index of 34, 
and sure enough, it prints out the 10 of hearts element 
in the deck of cards, which is good, 
since that's the card I was looking for. 
Remember, you can use List's index of method 
to do this same thing, without the list being required 
to be in sorted order. 
The _contains_ method on **List**, 
uses the _IndexOf_ method to return its result as well. 
Let me show you the _indexOf_ method here for comparison. 
I'll add another println statement by calling 
_deck.indexOf_, passing it _tenOfHearts_ variable. 
Running that:

```java  
---(same)
foundIndex = 34
foundIndex = 34
10♥(8)
```

You can see that the found index is still 34, 
when using this method on the _cards_ list. 
But now let's see what happens, 
if I comment out that the first statement, _deck.sort_ there. 
Running this code now:

```java  
---(same)
foundIndex = -53
foundIndex = 17
Exception in thread "main" java.lang.IndexOutOfBoundsException Create breakpoint : Index -53 out of bounds for length 52
```
            
You can see that the found index of the tenOfHearts 
is 17 in the second instance. 
The _indexOf_ method on **List** can find 
the ten of hearts in my list, sorted or not. 
The _binarySearch_ gives us a result, 
but not on that is reliable, 
and that last statement again gives me an exception,
when I try to retrieve an element with that _index = -53_. 
I'll revert to that last change,
where I commented out the sort, 
so this code compiles and runs.
Ok, then, you might be asking which method should you use? 
The basic rule of thumb is, 
if your lists contain a small number of elements, 
or if your list is unsorted or may contain duplicates, 
then using the _indexOf_ method, or its cohort, 
the _lastIndexOf_ method, will provide better performance. 
If your list is already sorted 
and contains a large number of elements, 
then the binary search method may provide performance improvements.

```java  
Card tenOfClubs = Card.getNumericCard(Card.Suit.CLUB, 10);
Collections.replaceAll(deck, tenOfClubs, tenOfHearts);
Card.printDeck(deck.subList(32, 36), "Tens row", 1);
```

Moving on, I want to examine just a couple more methods 
in the **Collections** class. 
First, this class has a _replaceAll_ method 
like the **List** interface. 
You'll remember the _replaceAll_ method on **List**, 
allowed us to write a function, a lambda expression 
to do a global replacement of all elements. 
The method on this **collections** class is 
much more limited, because it requires you 
to replace one or more instances with another. 
I'll use the _replaceAll_ method of Collections 
to replace the _tenOfClubs_ with a _tenOfHearts_ in my deck. 
My tens row is now lower in the list, 
since I re-sorted it, so I'm using elements 32 through 36, 
just to print out the tens in the list.

```java  
---------------------------
Tens row
10♥(8) 10♦(8) 10♥(8) 10♠(8)
```

The output shows that I now have 2 tensOfHearts. 
This method will replace more than one element 
if it finds multiple matches.

```java  
Collections.replaceAll(deck, tenOfHearts, tenOfClubs);
Card.printDeck(deck.subList(32, 36), "Tens row", 1);
```

I'll copy those last two statements, 
and pass a copy below. 
This time, I want the tenOfHearts as the first argument,
and the tenOfClubs as the second argument, 
so this is doing the _reverse_ now, 
replacing any 10s of hearts with a tensOfClubs.
Running that:

```java  
---------------------------
Tens row
10♣(8) 10♦(8) 10♣(8) 10♠(8)
```
                
You can see it replaced both 10s of hearts 
with 10s of clubs.
Now, IntelliJ wants our attention 
on both of those _replaceAll_ methods, 
and it's indicating that 
_the result of **Collections.replaceAll()** is ignored_. 
That's because this method returns a boolean value, 
true if the list was really changed, 
meaning one or more elements was replaced, 
or false if not.

```java  
if (Collections.replaceAll(deck, tenOfHearts, tenOfClubs)) {
    System.out.println("Tens of hearts replaced with tens of clubs");
} else {
    System.out.println("No tens of hearts found in the list");
}
```

I'll add one more call to this method, 
duplicating the call previously,
but wrapping it in an if statement. 
If the method returns true, I'll print that cards were replaced, 
otherwise, I'll print that no matching tens of hearts were found. 
Running this code:

```java  
---(same)
No tens of hearts found in the list
```
                
You can see my output, no tens of hearts found in the list. 
That's because I replaced them all, in the _replace_ call
just previous to this call, with tens of clubs. 
I'll leave those other warnings from IntelliJ. 
You should continue to pay attention to any warnings you see, 
since IntelliJ has had a lot more time 
to learn the rules than you have.
I will sometimes purposely ignore these warnings, 
as I am showing you the ropes, 
but let me encourage you to trust and use IntelliJ's suggestions,
while you're learning on your own.

```java  
System.out.println("Ten of Clubs Cards = " + Collections.frequency(deck, tenOfClubs));
```

Another interesting function is the frequency method, 
which allows you to check for duplicates in your collection.
I'll make a call to this method, directly in a println statement. 
I'll pass it my deck of cards, and the _ten of clubs_ cards. 
Running this:

```java  
---(same)
Ten of Clubs Cards = 2
```
            
I get that two tens of clubs were found in my list.

```java  
System.out.println("Best Card = " + Collections.max(deck, sortingAlgorithm));
System.out.println("Worst Card = " + Collections.min(deck, sortingAlgorithm));
```

Collections also give us both _min_ and _max_ methods, 
which take a collection, and will return the last 
or first element. 
Like _sort_ and _binarySearch_,
you can use one of the two overloaded versions, 
the first if your class implements Comparable, 
which I'm purposely not doing for Card. 
The second takes a Comparator, 
and that's the one I'll use here,
passing my sortingAlgorithm variable, 
to each of these methods. 
I'll call these from within a println statement, 
first _max_, passing deck and sorting algorithm. 
And now _min_, again passing deck and sorting algorithm. 
When I run that:

```java  
---(same)
Best Card = A♠(12)
Worst Card = 2♣(0)
```
            
It identifies the best card as the _ace of spades_, 
and the worst card is a 2 of clubs.

```java  
var sortBySuit = Comparator.comparing(Card::suit).thenComparing(Card::rank);
deck.sort(sortBySuit);
Card.printDeck(deck, "Sorted by Suit, Rank", 4);
```

Finally, let's look at the _rotate_ method. 
First I want to sort my deck by suit, 
and then rank, as it was in the beginning. 
I could just make another call to _getStandardDeck_, 
but instead I'll just create a new Comparator, 
and call sort on deck, and then print that out. 
Running that code:

```java  
---(same)
---------------------------
Sorted by Suit, Rank
2♣(0) 3♣(1) 4♣(2) 5♣(3) 6♣(4) 7♣(5) 8♣(6) 9♣(7) 10♣(8) 10♣(8) J♣(9) Q♣(10) K♣(11)
A♣(12) 2♦(0) 3♦(1) 4♦(2) 5♦(3) 6♦(4) 7♦(5) 8♦(6) 9♦(7) 10♦(8) J♦(9) Q♦(10) K♦(11)
A♦(12) 2♥(0) 3♥(1) 4♥(2) 5♥(3) 6♥(4) 7♥(5) 8♥(6) 9♥(7) J♥(9) Q♥(10) K♥(11) A♥(12)
2♠(0) 3♠(1) 4♠(2) 5♠(3) 6♠(4) 7♠(5) 8♠(6) 9♠(7) 10♠(8) J♠(9) Q♠(10) K♠(11) A♠(12)
```
                
My deck is again sorted by suits, and then rank.

```java  
List<Card> copied = new ArrayList<>(deck.subList(0, 13));
Collections.rotate(copied, 2);
System.out.println("UnRotated: " + deck.subList(0, 13));
System.out.println("Rotated " + 2 + ": " + copied);
```

Now I'll set up an example of using the _rotate_ method.
First I want a copy of the first suit in my deck. 
I'll create a new **List** variable, called _copied_, 
and that will be a new ArrayList. 
I'll pass a call to _deck.sublist_, 
from 0 to 13, to the ArrayList constructor, 
so this should create a list of just my _clubs_ cards.
The rotate method is a lot easier to understand 
if we look at the results while discussing it. 
Call _rotate_, passing it my new list, 
and the number 2. 
Print the _unrotated_ sublist from my deck.
Print the _rotate_ copied list.
Running that:

```java  
---(same)
UnRotated: [2♣(0), 3♣(1), 4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9), Q♣(10), K♣(11)]
Rotated 2: [Q♣(10), K♣(11), 2♣(0), 3♣(1), 4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9)]
```
                
You can see that the last 2 elements in the original list (the first list printed), 
my Queen and King of clubs were moved 
to be the first two elements in the following rotated list.
A positive number passed to this method moves that 
number of elements in the list from the back of the list 
to the front of the list, as you can see here. 
Notice that the order of the elements 
that were rotated is maintained, so Q is still before K, 
in the rotated list.

```java  
copied = new ArrayList<>(deck.subList(0, 13));
Collections.rotate(copied, -2);
System.out.println("UnRotated: " + deck.subList(0, 13));
System.out.println("Rotated " + -2 + ": " + copied);
```

Now I'll try a negative number,
I'll copy those last four statements 
and paste them directly below. 
I'll remove the type, List with Card there,
in front of copied, 
so I'm just re-declaring this list. 
Then I'll change two to -2 on the second 
and fourth lines.
Running this code:

```java  
---(same)
UnRotated: [2♣(0), 3♣(1), 4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9), Q♣(10), K♣(11)]
Rotated 2: [Q♣(10), K♣(11), 2♣(0), 3♣(1), 4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9)]
UnRotated: [2♣(0), 3♣(1), 4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9), Q♣(10), K♣(11)]
Rotated -2: [4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9), Q♣(10), K♣(11), 2♣(0), 3♣(1)]
```

I want you to compare the output from the two different calls to rotate.
In the first instance, 
the last two elements were moved to the start of the list, 
so the King and Ace of clubs were moved to the start of the list.
Using a negative number though,
you can see it moves the elements from the start 
to the end of the list.
Here, the _2_ and _3_ of clubs were rotated to the end of the list. 
Like _shuffle_, and _reverse_, 
it's good to know these methods exist, 
so you don't have to spend time writing your own algorithm 
to do these things.

```java  
copied = new ArrayList<>(deck.subList(0, 13));
for (int i = 0; i < copied.size() / 2; i++) {
    Collections.swap(copied, i, copied.size() - 1 - i);
}
System.out.println("Manual reverse :" + copied);
```

Finally, I want to look at the swap method. 
You'll remember one of the previous challenges had you swap 
characters in a character array, 
reversing the characters in a string. 
Now, I'll show you how to use the swap method 
on the **Collections** class to do something similar. 
I'll reverse the order of my suit of cards. 
Again, I'll make a copy of my first 13 cards, 
the club cards in the deck. 
Hopefully you'll remember that 
when swapping elements to do a full reverse,
you only need to swap half the elements.
My for loop is from 0 to copied size divided by 2.
Passing the copied list,
and an element _i_, which is the for loop parameter, 
and _j_, which is from the first half of my copied list 
to second half into the **Collections.swap**. 
Print out the copied array. 
Running that:

```java  
---(same)
UnRotated: [2♣(0), 3♣(1), 4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9), Q♣(10), K♣(11)]
Rotated -2: [4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9), Q♣(10), K♣(11), 2♣(0), 3♣(1)]
Manual reverse :[K♣(11), Q♣(10), J♣(9), 10♣(8), 10♣(8), 9♣(7), 8♣(6), 7♣(5), 6♣(4), 5♣(3), 4♣(2), 3♣(1), 2♣(0)]
```

You can see I simply reversed the sublist elements.
I could have simply done this by using the reverse method 
on this sublist (UnRotated).

```java  
copied = new ArrayList<>(deck.subList(0, 13));
Collections.reverse(copied);
System.out.println("Using reverse :" + copied);
```

Let me do that for completeness. 
First, I'll copy and paste the array list again. 
And then call **collections.reverse**, passing my copied list, 
print that out.

```java  
---(same)
Using reverse :[K♣(11), Q♣(10), J♣(9), 10♣(8), 10♣(8), 9♣(7), 8♣(6), 7♣(5), 6♣(4), 5♣(3), 4♣(2), 3♣(1), 2♣(0)]
```

And that does the same thing. 
Having access to the swap method allows you 
to have a lot more granularity in the ways you might use it. 
Ok, so those are a lot of the methods on Collections, 
some of which you might find some use for, 
and it's good to know they exist, 
if you might need this kind of functionality. 
Others are better replaced by List's default method, 
under most circumstances. 
This class contains a lot of methods 
for transforming collections into other collection types, 
such as _viewable_, _immutable_, _checkable_, and empty collections,
which I'm not going to review here.
Many of these have been replaced with additional implementations 
in the **Collections** Frameworks, 
and I'll be covering these in sections 
they're more relevant.
</div>

## [c. Card Game Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/Course07_CollectionsChallenge_CardGamePart2/README.md#card-game-challenge)
<div align="justify">

I introduced you to quite a few methods 
on the **java.util.Collections class**, 
and showed you examples, using a deck of cards. 
Now, it's your turn. 
Think for a moment about a card game that you'd enjoy building,
and one you know some of the rules for.

1. **Create a deck of cards** either a standard deck, 
or a deck that's specialized the card game you want to create. 
Feel free to use my Card record from the last several sections 
as a place to start, or if you want to start from scratch 
with your own Card class, by all means, try that out.
2. **Shuffle your deck**. 
Maybe you want to include cutting the deck, after shuffling.
3. **Deal your players' hands**.
Pick the number of players playing, and figure out 
how you'll deal the cards, one at a time to each hand, 
or some other way. 
For example, in pinochle, 
it's common to deal the cards out three or four at
a time to each player, until the deck is exhausted.
4. **Evaluate your players' hands** for card combinations 
that are important to the game.
5. Use a combination of **java.util.Collections** and **List** methods 
to achieve your results.
</div>

## [d. Equal and HashCode Methods]()
<div align="justify">

I'll discuss two classes, HashSet and HashMap, 
which are based on the hash codes of objects. 
This can be a confusing topic for new programmers, 
so I want to spend some extra time explaining it. 

I showed you an image of an abstract set, 
which showed chaotically placed elements. 
Since sets are unique because they don't support duplicates, 
adding an element always incurs the cost of first 
checking for a match. 
If your set is large or huge, this becomes a costly operation, 
O(n), or linear time, 
if you remember the Big-O notations I covered previously.
A mechanism to reduce this cost is introduced by something called hashing. 
If we created two buckets of elements, 
and the element could consistently identify
which bucket it was stored in, then half could reduce the lookup. 
If we created four buckets, we could reduce the cost by a quarter. 
A hashed collection will optimally create a limited set of buckets, 
to provide an even distribution of the objects 
across the buckets in a full set. 
A hash code can be any valid integer, 
so it could be one of 4.2 billion valid numbers. 
If your collection only contains 100 thousand elements, 
you don't want to back it with a storage mechanism of 
four billion possible placeholders.
And you don't want to have to iterate through 100 thousand 
elements one at a time to find a match or a duplicate.
A hashing mechanism will take an integer hash code, 
and a capacity declaration which specifies 
the number of buckets to distribute the objects over. 
It then translates the range of hash codes 
into a range of bucket identifiers. 
Hashed implementations use a combination of the hash code and other means, 
to provide the most efficient bucketing system,
to achieve this desired uniform distribution of the objects.
</div>

### Hashing Starts With Understanding Equality
<div align="justify">

To understand hashing in Java, I think it helps 
to first understand the equality of objects. 
I've touched on this in previous lectures, 
but now I want to be sure you thoroughly understand 
this subject because it matters when dealing with any hashed collections.
There are two methods on **java.util.Object** that all objects inherit.

| Testing for equality              | The hashcode Method   |
|-----------------------------------|-----------------------|
| public boolean equals(Object obj) | public int hashCode() |

These are _equals_ and _hashCode_, 
and I show the method signatures from Object here. 
The implementation of equals on Object is shown here. 
It simply returns _this == obj_.

```java  
public boolean equals(Object obj) {
    return (this == obj);
}
```
            
Do you remember what _==_ means for Objects? 
It means two variables have the _same reference to a single object in memory_. 
Because both references are pointing to the same object, 
then this is a good equality test. 
Objects can be considered equal in other instances as well, 
if their attribute values are equal, for example. 
The String class overrides this method, 
so that it compares all the characters in each String, 
to confirm that two Strings are equal. 
Let's review this in some code, 
as the jumping off point for understanding the hash code.

```java  
String aText = "Hello";
String bText = "Hello";
String cText = String.join("l","He","lo");
String dText = "He".concat("llo");
String eText = "hello";
```

I'll set up a series of String local variables. 
I'll set _a-text_ and _b-text_ to the same string literal, 
so _Hello_ in double quotes. 
I have _c-text_, and that's assigned the result of calling 
_String.join_ with _l_, as the first argument,
and _He_, and _lo_, as the next two arguments. 
_d-text_ is the result of concatenating _llo_, 
to the string literal _He_.
Finally, _e-text_ is set to the string literal, 
_hello_, but all lowercase this time. 
Now, I'll create a list out of these strings, 
so I can process them more easily.

```java  
List<String> hellos = Arrays.asList(aText, bText, cText, dText, eText);
hellos.forEach(s -> System.out.println(s + ": " + s.hashCode()));
```

I'll call asList on the Arrays class, 
passing it my list of Strings.
Remember this takes a variable arguments list,
so I can just list all my strings there. 
Next, I'll print each of these Strings out, 
along with their hashCodes.
Running this code:

```java  
Hello: 69609650
Hello: 69609650
Hello: 69609650
Hello: 69609650
hello: 99162322
```

I've got five strings printed, 4 have the value _Hello_, 
with a capital _H_, and one is _hello_ with a lower case _h_.
Notice that all four of the Strings with a capital _h_, 
return the exact same hash code, 
but the last one is different.
Java doesn't care if these are different objects in memory 
when it tests the equality of Strings, 
using the equals method. 
It just cares that the characters match, in one instance, 
compared to another instance.

```java  
Set<String> mySet = new HashSet<>(hellos);
System.out.println("mySet = " + mySet);
System.out.println("# of elements = " + mySet.size());
```

Now, let's create a **Set** of strings. 
I do that by declaring **Set** as the type, 
with String as the type argument. 
I'll call it my _set_ and assign it a new **HashSet**, 
and I'll pass my _hellos_ list to the hash set constructor. 
A hash set is a class that implements the **Set** interface 
and tracks duplicates by their hash code. 
Most collections allow the creation of another collection type, 
by passing a different collection to the constructor, 
as I'm doing here, passing a list to a set 
but a Set's constructor allows any instance 
that implements Collection to be passed to it. 
Ok, now, I'll print out my set, and like a list, 
I can pass that to a **System.out.println** statement. 
I'll also print out the size of my set. 
Running this code:

```java  
mySet = [Hello, hello]
# of elements = 2
```

I get two elements in my set, _Hello_ with a capital _h_, 
and _hello_ with a lower case _h_. 
You can see the number of elements is 2, even though the list 
I passed to the constructor had five references. 
Let's loop through the elements and see 
which String references are really in this set.

```java  
for (String setValue : mySet) {
    System.out.print(setValue + ": ");
    for (int i = 0; i < hellos.size(); i++) {
        if (setValue == hellos.get(i)) {
            System.out.print(i + ", ");
        }
    }
    System.out.println(" ");
}
```

I'll loop through the elements in the set, 
using an enhanced for loop, printing the set value. 
I'll loop through the string in the _hellos_ list, 
using a traditional for loop. 
I'm going to compare the String in the set with the String in the list, 
and I want to use equals sign here, 
because I'm really interested in whether 
these instances are the same object in memory. 
If they're the same reference, I'll print out the index of the element in the list. 
I'll print a new line after a set element. 
And running that code:

```java  
---(same)
Hello: 0, 1,
hello: 4,
```

I get three matches. 
The first set element, _Hello_, with an uppercase _H_,
is the same reference as the first two list elements, 
at index 0 and 1. 
The "_hello_" element with lowercase is the last element 
in the Strings list. 
The hash set will only add new references to its collection 
if it doesn't find a match in its collection, 
first using the _hashCode_ and then the object's equals method. 
It uses hashCode to create a bucket identifier 
to store the new reference. 
I'll talk about the underlying mechanism of the hash set a bit later.

Our code sets up five String reference variables, 
but two of these referenced the same string object in memory,
as shown here with _aText_ and _bText_ pointing to the same string instance.

![image10](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image09.png?raw=true)

When we passed our list of five strings to the **HashSet**, 
it added only unique instances to its collection.
It locates elements to match by first deriving which bucket to look through, 
based on the hash code.
It then compares those elements to the next element to be added, 
with other elements in that bucket, using the _equals_ method. 
Again, we'll talk about how Java implements the **HashSet** in a bit. 
For now, I want you to understand how equality, 
and the _hashCode_ go hand in hand, when using hashed collections. 
Let's get back to the code and explore this a bit more.

```java  
public class PlayingCard {
    private String suit;
    private String face;
    private int internalHash;

    public PlayingCard(String suit, String face) {
        this.suit = suit;
        this.face = face;
        this.internalHash = 1;
    }
    @Override
    public String toString() {
        return face + " of " + suit;
    }
}
```

This time I'll create my own class, again a playing card, 
called **PlayingCard** in the same package. 
I'll add three fields, suit and face as strings, 
and an integer I'll call internalHash.
I'll generate a constructor with only two arguments.
For the moment, I'll add a statement 
to set the internalHash field to one.
I'll explain this field in a little bit. 
I'll quickly add a _toString_ method, 
creating the default override. 
I'll return the face and the suit, in place of super _toString_. 
Ok, so that's the playing card. 
I'll go back to the main method on the main class, 
and now set up a couple of cards:

```java  
PlayingCard aceHearts = new PlayingCard("Hearts", "Ace");
PlayingCard kingClubs = new PlayingCard("Clubs", "King");
PlayingCard queenSpades = new PlayingCard("Spades", "Queen");
List<PlayingCard> cards = Arrays.asList(aceHearts, kingClubs, queenSpades);
cards.forEach(s -> System.out.println(s + ": " + s.hashCode()));
```

An ace of hearts, a king of clubs, and a queen of spades. 
I'll again create a list of these, passing the list of cards to the asList method. 
I'll print the hashCode for each. 
Running that code:

```java  
---(same)
Ace of Hearts: 2065951873
King of Clubs: 793589513
Queen of Spades: 1313922862
```
                
You can see I get unique hash codes for each. 
Let's create a set of cards and add the cards one at a time.

```java  
Set<PlayingCard> deck = new HashSet<>();
for (PlayingCard c : cards) {
    if (!deck.add(c)) {
        System.out.println("Found a duplicate for " + c);
    }
}
System.out.println(deck);
```

I'll call the Set of cards deck, and make it a **HashSet**. 
Loop through the _cards_ list.
Add these cards one at a time.
The _add_ method on any collection returns true 
if the element is successfully added, and false if not. 
Because sets don't allow duplicates, 
it becomes more important to check if the element you expect 
to be added, really was added.
In _if (!deck.add(c))_, I check if I get false back, and if so, 
I'll print that a duplicate was found. 
I'm also printing the entire set after this loop to see 
what's been added to the set. 
Running this code:

```java  
---(same)
[Ace of Hearts, King of Clubs, Queen of Spades]
```

We see that all three cards were added to the set. 
The elements are not guaranteed to be in any order, 
so you may not see the elements printed in the same order as mine, 
or in the order you added the elements. 
Ok, next I want to go back to the PlayingCard class, 
and override both the _hashCode_ and the _equals_ method.

```java  
@Override
public boolean equals(Object o) {
    //return super.equals(o); 

    System.out.println("--> Checking Playing Card equality");
    return true;
}
```

Now, instead of returning _super.equals_ in the _equals_ method, 
which we know just calls the equals method on Object,
I'll just return true, so that all my objects will be equal no matter what.
But first, I want to print out something from this method, 
like _checking playing card equality_, 
so I can see when this method is called. 
Ok, when we run the code,
This gives me the same result as before, 
but it doesn't appear the equals method on Playing card was ever executed, 
because my statement I just added was never printed. 
I want to change the hashCode method here next:

```java  
@Override
public int hashCode() {
    return super.hashCode();
}
```

This time, I'm just going to return my internal hash field,
which is currently just set to 1. 
Now, I want to run it again:

```java  
---(same)
Ace of Hearts: 1
King of Clubs: 1
Queen of Spades: 1
--> Checking Playing Card equality
Found a duplicate for King of Clubs
--> Checking Playing Card equality
Found a duplicate for Queen of Spades
[Ace of Hearts]
```
                
You can see the hashCode is 1, for all three cards. 
But now you can see, the code is calling the equals method 
during the second and third iteration of the loop, 
and it indicates it's finding duplicates in each case. 
The final result is that it considered every other playing card a duplicate, 
and so only one card is in the set, 
the first in our list, the ace of hearts. 
The only time the equals method is called 
when adding elements to the hash set is 
when the hash code algorithm returns the same bucket identifier, 
which is true here, for our hash code = 1.

```java  
public class PlayingCard {
    private String suit;
    private String face;
    private int internalHash;

    public PlayingCard(String suit, String face) {
        this.suit = suit;
        this.face = face;
        //this.internalHash = 1;
        this.internalHash = (suit.equals("Hearts")) ? 11 : 12;
    }
    @Override
    public String toString() {
        return face + " of " + suit;
    }
}
```

Let's go up, and change the internal hash to return an 11 
if the suit is hearts, and 12 if it doesn't. 
Let's run this:

```java  
---(same)
Ace of Hearts: 11
King of Clubs: 12
Queen of Spades: 12
--> Checking Playing Card equality
Found a duplicate for Queen of Spades
[Ace of Hearts, King of Clubs]
```
                
You can see the hash code for the ace of hearts is 11, 
but in other cases it's 12.
Where there is a duplicate hash code, the equals method is called, 
which always returns true, so the third element, 
the Queen of Spades, doesn't get added.
Let's comment these two overridden methods, _hashCode_ and _equals_, 
and instead use IntelliJ's code generation tools to regenerate them. 
The first time you go into this tool, the Template won't show IntelliJ default,
so select that like so.

Let's comment these two overridden methods, 
_hashCode_ and _equals_, and instead use IntelliJ's code 
generation tools to regenerate them. 
The first time you go into this tool, the Template won't show IntelliJ default, 
so select that like so.
We want to use getters, so tick that box. 
Leave the other checkbox unchecked. 
Click next. 
For fields to be included in equals, ensure only suit 
and face are selected and click next.

For fields to be included in hashcode,
select box fields, and Next again.
You actually now want to check both boxes. 
But I want to show you the code 
generated if this is not checked. 
So I'll ensure both are deselected here and 
click Create. 
Looking at the hash code, we have these extra tests for null. 
Which we really don't need.
So what I am going to do is undo this with _ctrl+Z_, 
and go through the creating of the methods again. 
Note that this time IntelliJ remembers my Template selection
and also that I checked the use getters during code generation. 
I'll click Next. 
I'll deselect internal Hash like last time and click next. 
For _hashcode_ I'll leave both fields selected and click Next. 
This time for non-null fields, I'll check both, 
as we know these won't be null and click Create again. 
This is as good as any implementation for these methods.

```java  
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PlayingCard that = (PlayingCard) o;

    if (!suit.equals(that.suit)) return false;
    return face.equals(that.face);
}
```

Notice that the equals method first checks for equal references,
because two references pointing to the same object
is the surest test of equality. 
Then it compares the result of the getClass methods on both objects, 
and if they're equal, it will cast the object passed to a Playing Card.
It then checks if the suits are equal. 
If they are, it will check if the faces are equal.

```java  
@Override
public int hashCode() {
    int result = suit.hashCode();
    result = 31 * result + face.hashCode();
    return result;
}
```

You can see the hashCode implementation that was generated. 
It starts with the hashCode for the string for the suit. 
It then uses that, multiplying it by 31, a small prime number, 
and adding the hash code for the string, the face string's hash code. 
Running this code:

```java  
---(same)
Ace of Hearts: -1834509066
King of Clubs: 2023815418
Queen of Spades: -269088580
[Ace of Hearts, King of Clubs, Queen of Spades]
```
                
You see our set has all three cards in it, 
and the hash codes for each card are unique.
</div>

### Creating HashCode Method
<div align="justify">

You don't have to use the generated algorithm as I did here. 
You could create your own, but your code should stick to the following rules.

1. It should be rapid to compute.
2. It should produce a consistent result each time it's called.
For example, you wouldn't want to use a random number generator, 
or a date-time-based algorithm that would return a different 
value each time the method is called.
3. Objects that are considered equal should produce the same hashCode.
4. Values used in the calculation should not be mutable.

It is common practice to include a small prime number 
as a multiplicative factor (although some non-prime numbers 
also provide good distributions). 
This helps ensure a more even distribution 
for the bucket identifier algorithm, 
especially if your data might exhibit clustering in some way. 
IntelliJ and other code generation tools use 31, 
but other good options could be 29, 33 (not prime but shown to have good results), 
37, 43 and so on. 
You want to avoid the single digit primes, 
because more numbers will be divisible by those, 
and may not produce the even distribution 
that will lend itself to improved performance. 
For those who like to understand how things really work, 
let me encourage you to do some research on this topic. 
For the rest of you, remember that 
if you are using your own classes in hashed collections, 
you'll want to override both the equals and the hashCode methods. 
I'll be covering this quite a bit over the next couple of lectures, 
so you'll have more time to get exposed to these concepts. 
Java supports four hashed collections implementation, 
which we'll be looking at coming up. 
These are the HashSet, LinkedHashSet, the HashMap, and LinkedHashMap. 
In addition, there's one legacy implementation, 
the HashTable, which I won't be covering, 
since there are more efficient implementations that replace this legacy class.
</div>

## [e. Set Interface]()
<div align="justify">

Like the code setup lecture of Collections CardGame I presented earlier, 
this is another lecture that will set up code 
for upcoming lectures on sets. 

In this example, _I'll be using HashSets as fields_, 
and I'll use the **Scanner** class, 
which I've used many times before, 
always passing _System.in_ to the constructor. 
In this code, _I'll be using **Scanner** with just 
a String passed to the constructor_". 
It works similarly.
If you want to see this in action, be sure to follow along. 
Eventually I'll cover reading input from files, 
although this code won't be doing that. 
Using scanner this way, gives you a taste for a way to do this, 
without the file complexities which I'll cover later. 

![image11](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image10.png?raw=true)

In this setup code, you want to create a **Contact** class
that has the fields, a String name, a HashSet of String emails and phones. 
This class should have multiple constructors. 
The first just takes a name. 
The second should have name, and a single email of type String. 
Next, another with two arguments, name, 
but this time a long, which represents a 10-digit phone number. 
Lastly, the constructor that should do most of the work, 
the last in your chain, in other words, should take name, 
a single email, and a single phone.

This last constructor should do the following:
* Add the email argument to the emails set, if email is not null.
* Transform the phone argument, a long, (if it's not zero), 
to a string in the format (123) 456–7890, for example.
* Add the transformed phone to the phone set.

This class should also include three public instance methods. 
A getter for name, an override for the toString method will print the name, 
set of emails, and set of phones in a simple form. 
Finally, include a method called _mergeContactData_, 
that takes a contact, and returns a new **Contact** instance, 
which merges the current instance with the **Contact** passed. 
I've only just started the conversation about **Sets** and **HashSets**, 
but you should be able to set up emails and phones as shown 
for this setup.

The **ContactData** class is going to emulate getting data 
from an external source, but instead of an external source, 
I just want you to set this data up with two different text blocks, 
in the format shown below. 
This data purposely has duplicates.

| Phone Data                | Email Data                           |
|---------------------------|--------------------------------------|
| Charlie Brown, 3334445555 | Mickey Mouse, mckmouse@gmail.com     |
| Maid Marion, 1234567890   | Mickey Mouse, micky1@aws.com         |
| Mickey Mouse, 9998887777  | Mickey Mouse, 9998887777             |
| Mickey Mouse, 1247489758  | Robin Hood, rhood@gmail.com          |
| Minnie Mouse, 4567805666  | Linus Van Pelt, lvpelt2015@gmail.com |
| Robin Hood, 5647893000    | Daffy Duck, daffy@google.com         |
| Robin Hood, 7899028222    |                                      |
| Lucy Van Pelt, 5642086852 |                                      |
| Mickey Mouse, 9998887777  |                                      |

**Create a method named _getData_** that **takes a String type** 
(either **phone** or **email**), and returns a List of **Contact**.
Now, I'm going to use **Scanner** to parse the data in these text blocks. 
My reason for using **Scanner** is twofold.

First, I want to demonstrate this variation, 
and second, I want you to imagine this data coming 
from an input file or a database. 
IO and database access are for a later section, 
but this code simulates getting data from an external source. 
However, you do it, you want to parse this data, 
create a contact for each row, and return the list. 
You want to return the lists separately, meaning return a list of phones, 
or a list of emails, based on the type passed to this method. 

Now, I'll create the Contact class:

```java  
public class Contact {

    private String name;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();
}
```

And I'll put it in the same package. 
I want three fields in this class, 
which I described in the challenge info. 
Name as string, emails, and phones both declared as sets, 
and assigned new HashSet instances. 
I'm going to make emails and phones both HashSets, 
because I don't want duplicates in my contact data.

```java  
public Contact(String name) {
    this(name, null);
}

public Contact(String name, String email) {
    this(name, email, 0);
}

public Contact(String name, long phone) {
    this(name, null, phone);
}
```

Next, I want a series of constructors. 
I'm going to set most of these up manually, 
because my constructor arguments aren't going 
to match up with my fields. 
I'll use IntelliJ's tools to generate for the first one, 
which has an argument for just name. 
But I don't want to set the name field here.
Instead, I want to chain another constructor 
that I haven't yet created, one with two arguments,
and I'll pass name, and null for email. 
I'll add another constructor, has two arguments,
the new argument is going to be String and email, 
and I'm going to chain this to another constructor call, 
to a three argument constructor, defaulting zero as the phone 
as the third argument. 
I'll add another constructor again, 
I'll add phone as a long in the parameters, 
and then I'll add it to the three constructor calls. 
For the last constructor, I'm just going to manually type this one out 
and talk through it. 
As I stated in my challenge info, this will have name 
and email, both strings, and a long for phone.

```java  
public Contact(String name, String email, long phone) {
    this.name = name;
    if (email != null) {
        emails.add(email);
    }
    if (phone > 0) {
        String p = String.valueOf(phone);
        p = "(%s) %s-%s".formatted(p.substring(0, 3), p.substring(3, 6),
                p.substring(6));
        phones.add(p);
    }
}
```

Ok, the _name_ is straightforward; 
I'm just assigning it to the method argument. 
Next, I want to add the _email_ passed to this constructor,
to the emails set on this class, 
only if email is not equal to null. 
Now, adding the phone is a bit more complicated,
first of all because it's a long, and it needs to be a String, 
formatted in a specific way. 
If the phone number isn't greater than 0, I won't do anything. 
If it is, I'll first create a String out of it, 
using the value of method, passing it a long, the phone. 
I'll now set up a formatted string, and pass the first three digits, 
then the next three, and the final four, 
which is how phone numbers are formatted in some US entries. 
Then, I'll simply add that local variable to the phone set. 
Ok, so that's all the constructors. 
Now I have three methods I need to code.

```java  
public String getName() {
    return name;
}

@Override
public String toString() {
    return "%s: %s %s".formatted(name, emails, phones);
}
```

First a getter for _name_, so I'll generate that. 
I also want a _toString_ method. 
I'll use the override feature in IntelliJ, to generate that. 
I want to replace that return line, 
which is _return super.toString()_ with my own formatted String. 
Like lists, I can pass sets right to _println_, 
or to the formatted method, as I show here.

```java  
public Contact mergeContactData(Contact contact) {

    Contact newContact = new Contact(name);
    newContact.emails = new HashSet<>(this.emails);
    newContact.phones = new HashSet<>(this.phones);
    newContact.emails.addAll(contact.emails);
    newContact.phones.addAll(contact.phones);
    return newContact;
}
```

Ok, I've got one last method. 
That's public returns a Contact, 
I'll name it _mergeContactData_, and it takes a Contact as an argument. 
I'll create a local variable, also a contact, 
and assign that a new Contact instance, 
passing the name on the current instance to that constructor. 
That gets returned from this method.
But I also want to include the emails 
and phones from both contacts, 
so I'll set the emails on the new contact 
to be a new hash set, passing it the current instance's emails. 
I'll do the same thing for the phones, 
passing it the phones which are on the current instance.
At this juncture, this code is really just cloning the data, 
by using all the data from the current instance, 
to create a new contact. 
To make it a merge, I want to add the emails and phones 
from the contact passed to this method. 
To do that, I can just call "_addAll_" on the new Contact's emails field, 
and pass it the emails on the Contact that is passed to this method. 
I'll do the same thing for the phones. 
Ok, so that completes the Contact class. 
Now I want to do the second part of the code setup, 
and that's to create a ContactData class:

```java  
public class ContactData {

    private static final String phoneData = """
            Charlie Brown, 3334445555
            Maid Marion, 1234567890
            Mickey Mouse, 9998887777
            Mickey Mouse, 1247489758
            Minnie Mouse, 4567805666
            Robin Hood, 5647893000
            Robin Hood, 7899028222
            Lucy Van Pelt, 5642086852
            Mickey Mouse, 9998887777
            """;

    private static final String emailData = """
            Mickey Mouse, mckmouse@gmail.com
            Mickey Mouse, micky1@aws.com
            Minnie Mouse, minnie@verizon.net
            Robin Hood, rhood@gmail.com
            Linus Van Pelt, lvpelt2015@gmail.com
            Daffy Duck, daffy@google.com
            """;
    
    public static List<Contact> getData(String type) {

        List<Contact> dataList = new ArrayList<>();
        Scanner scanner = new Scanner(type.equals("phone") ? phoneData : emailData);
        while (scanner.hasNext()) {
            String[] data = scanner.nextLine().split(",");
            Arrays.asList(data).replaceAll(String::trim);
            if (type.equals("phone")) {
                dataList.add(new Contact(data[0], Long.parseLong(data[1])));
            } else if (type.equals("email")) {
                dataList.add(new Contact(data[0], data[1]));
            }
        }
        return dataList;
    }
}
```

That would simulate a data reader of some kind. 
In our case, this is just going to read data from static strings,
but you could imagine we could read this data from ta file, 
a database, or get it from some kind of service provider.
Again, in the same package, I'll create a new class named **ContactData**. 
I'll create two static text fields, using text blocks, 
one for phone data, and one for email data. 
Initially, both fields will be empty. 
I'm just going to paste this data in.

```java  
public static List<Contact> getData(String type) {
    List<Contact> dataList = new ArrayList<>();
    Scanner scanner = new Scanner(type.equals("phone") ? phoneData : emailData);
    while (scanner.hasNext()) {
        String[] data = scanner.nextLine().split(",");
        Arrays.asList(data).replaceAll(String::trim);
        if (type.equals("phone")) {
            dataList.add(new Contact(data[0], Long.parseLong(data[1])));
        } else if (type.equals("email")) {
            dataList.add(new Contact(data[0], data[1]));
        }
    }
    return dataList;
}
```

Ok, so next, I want a method to read this data, 
emulating reading it from a file, 
or retrieving it from an external source. 
This is going to be a public static method called getData,
it'll take a String, which will identify 
if it's retrieving phone or email data, 
and it'll return a List of contacts. 
I'll set up a local variable, data list, 
and instantiate a new array list. 
I'll return this local variable from the method.
Now, I'll set up my scanner variable. 
If the type is phone, 
I'll pass in the phoneData text block to the constructor. 
If the type is email, I'll pass in the emailData text block. 
You've seen **Scanner** before with _System.in_, 
but it can also be used with any string, 
including a multi-string text block, 
which is what I'm doing here. 
There are other ways to split this data by new lines and so forth,
and I'll show you these examples in future code. 
I wanted to write the code this way, 
as a sort of mock file read if you will. 
Now I can use the Scanner functionality 
to treat the string like any input stream. 
I'll use a while loop to continue looping 
while hasNext on the scanner is true. 
I'll call the nextLine to get the next string 
and split that on a comma, 
passing it to a local variable, data, an array of strings. 
I'll pass the array to the asList method on Arrays, 
so that I can use the replaceAll method,
passing it the method reference for the trim method on String.
This is going to trim leading and trailing whitespace, 
from each of the strings in my text block. 
I could have also used strip Indent there, 
but I've formatted my text block itself without indents,
so I won't worry about it for simplicity. 
Next, I want to create specific types of contacts, 
based on the type passed.
If the type is phone, I'll add a new Contact, 
using the constructor that takes name, 
which is the first comma delimited element, 
in both of my text block elements. 
I'll also pass it the phone number, 
which I first need to parse, using parseLong on the long wrapper. 
The phone number is the second comma delimited element, in my text block. 
If the type is email, 
I'll just pass the second comma delimited element as is, 
which is the email as a string. 
And that's it. 
That's the setup. 
I'll add a public static print method on the Main class.

```java  
public class Main {

    public static void main(String[] args) {
        List<Contact> emails =  ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Phone List", phones);
        printData("Email List", emails);
    }
    public static void printData(String header, Collection<Contact> contacts) {

        System.out.println("----------------------------------------------");
        System.out.println(header);
        System.out.println("----------------------------------------------");
        contacts.forEach(System.out::println);
    }
}
```

I'll call it _printData_, and it'll take a string, 
a header, as well as a Collection, with a type of Contact.
This prints the header between some separator lines,
and then uses forEach to print each contact. 
Next, I'll test this out.
In the main method, I'll create a List, called emails, 
and assign that the result of calling getData 
on my ContactData class with emails as the type. 
I'll repeat that for phone, invoking the same method, 
but passing phone as the type. 
I'll execute my new printData method to print first the emails, 
and then the phones. 
Running that:

```java  
----------------------------------------------
Phone List
----------------------------------------------
Charlie Brown: [] [(333) 444-5555]
Maid Marion: [] [(123) 456-7890]
Mickey Mouse: [] [(999) 888-7777]
Mickey Mouse: [] [(124) 748-9758]
Minnie Mouse: [] [(456) 780-5666]
Robin Hood: [] [(564) 789-3000]
Robin Hood: [] [(789) 902-8222]
Lucy Van Pelt: [] [(564) 208-6852]
Mickey Mouse: [] [(999) 888-7777]
----------------------------------------------
Email List
----------------------------------------------
Mickey Mouse: [mckmouse@gmail.com] []
Mickey Mouse: [micky1@aws.com] []
Minnie Mouse: [minnie@verizon.net] []
Robin Hood: [rhood@gmail.com] []
Linus Van Pelt: [lvpelt2015@gmail.com] []
Daffy Duck: [daffy@google.com] []
```

I'm getting the list of phone contacts, and the list of email contacts, 
printed out neatly, You can see I have several _Mickey Mouses_ in both lists, 
and a couple of _Robin Hoods_ in the phone list. 
Ok, so that was the setup challenge. 
I'll be using this code to explore sets, 
and merging contacts using sets. 

A Set is not implicitly ordered. 
A Set contains no duplicates. 
A Set may contain a single null element. 
Sets can be useful because operations on them are rapid. 
In fact, the lack of duplicates is the most important differentiator, 
as there are ordered sets, such as the **LinkedHashSet**, and **TreeSet**.

The **set** interface defines the basic methods 
_add_, _remove_ and _clear_, to maintain the items in the set. 
We can also check if a specific item is in the set using the contains method. 
Interestingly enough, there's no way to retrieve an item from a set. 
You can check if something exists, using _contains_, 
and you can iterate over all the elements in the set,
but attempting to get the 10th element, for example, 
from a set isn't possible, with a single method.

The best-performing implementation of the **Set** interface 
is the **HashSet class**. 
This class uses hashing mechanisms to store the items. 
This means the hash code method is used 
to support even distributions of objects in the set. 
Oracle describes this class as offering constant time performance 
for the basic operations (add, remove, contains and size).
This assumes the hash function disperses the elements properly 
among the buckets.
Constant time has the Big O Notation O(1). 
Although I haven't covered the Map and HashMap types yet, 
the **HashSet** actually uses a **HashMap** in its own implementation,
as of JDK 8. 
Later, when I cover maps and hash maps, 
I'll swing back and explain how the **HashSet** uses a hash map under
its covers.

In the lecture called Set Up for **Sets** and **Maps**, 
I created code for a Contact class, 
as well as code that simulated getting phone lists and email lists, 
from an external source. 
I have two classes set up in this code:

1. The **Contact** class that consists of a name, a set of phones, 
which are strings, and a set of emails, also strings.
This class has only three methods on it. 
The first two are a getter for name, 
as well as an overridden _toString_ method.
There's also a merge **Contact** Data method
that has a contact as an argument, and creates a new contact. 
It populates the new contact, first with the data on the current instance, 
and then merges emails and phone numbers, from the contact passed to this method.

2. The **ContactData** has one method on it, _getData_, 
that takes a type, a String, and will return a list of Contact instances. 
The _type_ can be _phone_ or _email_. 
If I pass _phone_, I get a list of contacts each with a single phone number. 
If I pass _email_, I get a list of contacts with a single email. 
The data's stored as text blocks, simulating what each record might look like,
in a comma-delimited file. 
Data is read by a Scanner instance, constructed with one of these text blocks. 
Every time I call getData on this class, 
I'll get a new list with new instances of contacts.

```java  
List<Contact> emails =  ContactData.getData("email");
List<Contact> phones = ContactData.getData("phone");
printData("Phone List", phones);
printData("Email List", emails);
```

I get the two lists from the ContactData, 
and the contact info printed out. 
Ok, so now imagine I've got a list of names with phone numbers,
with my mobile phone contacts, 
and also a list of email contacts from an internet provider.
Now, I want to combine these contacts, 
merging any duplicates into a single contact, 
with multiple emails and phone numbers, on a single record. 
To do this, I'll create two Sets:

```java  
Set<Contact> emailContacts = new HashSet<>(emails);
Set<Contact> phoneContacts = new HashSet<>(phones);
printData("Phone Contacts", phoneContacts);
printData("Email Contacts", emailContacts);
```

Two **HashSets** to be specific, one for the phone data, 
and one for the email data. 
Like Lists, I start with the interface type as the variable type, 
so **Set** here, and a type argument of **Contact**, in both cases. 
The first variable, email contacts will be assigned a new **HashSet**, 
constructed by passing the email list to it. 
The phone contacts are the same, a new **Hashset** 
but constructed using the phone list.
Most constructors of classes implementing the **Collection** interface
support a constructor that accepts a **Collection**. 
You can see this lets me very quickly create anew hashSet from a list, 
with one line of code. 
I'll again use my printData method on this class, passing a header, 
and the phone or email contacts. 
The _printData_ method will take any **Collection** type, 
meaning any instance of a class that implements **Collection**. 
This lets me pass any list or a set to this method to get printed. 
Let me run this.

```java  
----------------------------------------------
Phone Contacts
----------------------------------------------
Lucy Van Pelt: [] [(564) 208-6852]
Charlie Brown: [] [(333) 444-5555]
Maid Marion: [] [(123) 456-7890]
Robin Hood: [] [(564) 789-3000]
Mickey Mouse: [] [(999) 888-7777]
Minnie Mouse: [] [(456) 780-5666]
----------------------------------------------
Email Contacts
----------------------------------------------
Linus Van Pelt: [lvpelt2015@gmail.com] []
Robin Hood: [rhood@gmail.com] []
Mickey Mouse: [mckmouse@gmail.com] []
Daffy Duck: [daffy@google.com] []
Minnie Mouse: [minnie@verizon.net] []
```

Here, I hope you can see, 
there are still duplicates in both of these sets, 
but the order is different from the order that was in the list. 
Can you guess why there's duplicates in these sets, 
even though I just said earlier, 
that **HashSets** won't have duplicates? 
Well, duplicates are determined for hashed collections, 
first by the hash code, and then the _equals_ method. 
In this instance, both the hash code method, 
and the _equals_ method, are using Object's implementation. 
This means each of these instances of contacts is considered unique, 
by that definition. 
In most cases, this is probably a good thing. 
But since these are personal contacts, 
I'm going to make a rule that _contacts_ that have the same _name_ 
are really the same person, 
but with different data. 
To implement this rule, 
I want to go to the **Contact** class, 
and generate both an _equals_ method, and a _hashCode_ method.

```java  
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Contact contact = (Contact) o;

    return getName().equals(contact.getName());
}
@Override
public int hashCode() {
    return 33 * getName().hashCode();
}
```

Instead of selecting the IntelliJ default, 
I want to select **java.util.Objects.equals** 
and hashCode (java 7 +). 
I'll accept the defaults for the first two screens, 
where all fields are selected. 
In the third dialogue, where it says select all non-null fields, 
I'm going to just select name here. 
Now I want to examine these generated methods, in light of 
what I've previously talked about. 
First, let's look at the equals method.
We've said before that if the references are equal,
they're the same instance, 
and we can return true in this case. 
Next, the code checks if the object passed is a null, 
or if the classes (or types) of the objects differ.
If either of these conditions is true, then these are definitely not equal. 
Since the class type was previously checked, 
and found to be the same type as the current instance,
we can safely cast the object _o_, to a contact. 
Ok, so notice that for name, which is the only field
I said was non-null, the code is directly calling the _equals_ method, 
against the _getName_ results on both objects. 
For the other two fields, the code is using another utility class, 
called Objects, a class in the java util package.
If I press control click on that class name, Objects, 
I can see the comments in the code, 
explaining that this is a utility class, 
introduced in JDK 7. 
This class provides static utility methods to handle nulls, 
to generate equals results, and hash codes.
Getting back to the code, you could guess that a contact is equal,
based on the name, and if the other attributes aren't null,
by matching on emails and phones.

```java  
@Override
public int hashCode() {
    return 33 * getName().hashCode();
}
```

Now, look at the hash code method. 
Here again, it's using the Objects class, 
calling a hash method on that. 
I'll press control click on the hash method next. 
You can see here, this method takes a variable arguments parameter, 
and this code simply passes those off to the hash Code method, 
on **java.util.Arrays**. 
And continuing, I'll press control click on _hashCode_ there. 
From this, you see that Java, like IntelliJ, 
creates a hash code using 31, as a multiplier for each values' hash code. 
Ok, so there's no right way to generate these methods; 
a lot will depend on your own organizations' standards. 
I'll run my code again, with these methods in place now.

```java  
----------------------------------------------
Phone Contacts
----------------------------------------------
Mickey Mouse: [] [(124) 748-9758]
Charlie Brown: [] [(333) 444-5555]
Maid Marion: [] [(123) 456-7890]
Robin Hood: [] [(789) 902-8222]
Minnie Mouse: [] [(456) 780-5666]
Robin Hood: [] [(564) 789-3000]
Mickey Mouse: [] [(999) 888-7777]
Lucy Van Pelt: [] [(564) 208-6852]
----------------------------------------------
Email Contacts
----------------------------------------------
Mickey Mouse: [mckmouse@gmail.com] []
Mickey Mouse: [micky1@aws.com] []
Minnie Mouse: [minnie@verizon.net] []
Robin Hood: [rhood@gmail.com] []
Linus Van Pelt: [lvpelt2015@gmail.com] []
Daffy Duck: [daffy@google.com] []
```

My set still contains duplicate names, 
but Mickey Mouse only has one entry now, 
for _(999) 888–7777_ in its phone number. 
This means my contacts are distinct by the name, 
all the emails, and all the phone numbers.

Going back to the Contact class,
I'll again delete both the _equals_ and _hashCode_ method. 
I'll regenerate these,
using Intelli-J's default template, 
but in every instance, on each dialogue window, 
I want to just check the name.
Make sure to select name on the last dialogue,
where it's not selected as a default.
Now, here, _hashCode_ doesn't use a multiplier,
but simply returns the name's hash code.
This means a **Contact** instance, and a String, 
which has the value Mickey Mouse, will result in the same hash code. 
This is ok, but it's usually a good idea to have objects 
which are a different class type, return different hash codes, 
so I'm going to add my own multiplier here.
I'll make it 33, a composite number, but some research indicates, 
this multiplier can produce consistent results similar to 31.
As with almost everything in software engineering, 
there's debate over what the best multiplier might be. 
I don't want you to think that 33 is better than 31, 
it's just a different choice. 
I know this will drive a couple of my students to go 
and research that debate, and that's a good thing. 
For my own small sets in this code, 
it isn't really going to matter. 
Now, if I run the code again:

```java  
----------------------------------------------
Phone Contacts
----------------------------------------------
Lucy Van Pelt: [] [(564) 208-6852]
Charlie Brown: [] [(333) 444-5555]
Maid Marion: [] [(123) 456-7890]
Robin Hood: [] [(564) 789-3000]
Mickey Mouse: [] [(999) 888-7777]
Minnie Mouse: [] [(456) 780-5666]
----------------------------------------------
Email Contacts
----------------------------------------------
Linus Van Pelt: [lvpelt2015@gmail.com] []
Robin Hood: [rhood@gmail.com] []
Mickey Mouse: [mckmouse@gmail.com] []
Daffy Duck: [daffy@google.com] []
Minnie Mouse: [minnie@verizon.net] []
```
                    
I get only distinct records by name in each set. 
My sets no longer have multiple records for a single contact name.
Using this method to create my sets, however, 
means I've lost a couple of phone numbers and emails in the process.
To resolve that, I'm going to add two methods to the Contact class.

```java  
public void addEmail(String companyName) {

    String[] names = name.split(" ");
    String email = "%c%s@%s.com".formatted(name.charAt(0), names[names.length - 1],
            companyName.replaceAll(" ", "").toLowerCase());
    emails.add(email);
}
```

The first method will generate and _add_ a company email 
to the current instance's email set. 
This will be public, void, called addEmail, 
and take a string, company name. 
This code splits the contact name into an array of strings,
splitting on a space. 
The email is made up of the first character of the name, 
then the last string in the array,
which I'm going to assume is the last name. 
That's followed by the company name, 
removing any spaces in it, and finally appending _.com_ to that. 
Then I execute just the add method on the emails set, 
passing this new String.
To test this out, I'll add a call to this method in the main method.

```java  
int index = emails.indexOf(new Contact("Robin Hood"));
Contact robinHood = emails.get(index);
robinHood.addEmail("Sherwood Forest");
System.out.println(robinHood);
```

But I want to first get _Robin Hood_ from the list of email contacts. 
I can do this by first creating a new Contact,
using just the name _Robin Hood_ now. 
I can pass this new contact, 
to the index-of method on the email list, 
which returns the integer index, where that element is located the list. 
Then I can use the get method on the list 
to return the original email contact, which is what I want. 
Now that I've got the original Robin Hood contact, 
I'll add the company email to this record, 
calling the addEmail method with the company name, 
Sherwood Forest. 
I'll add that statement before the _system.out.println_ statement. 
If I run that:

```java  
----------------------------------------------
Phone Contacts
----------------------------------------------
Lucy Van Pelt: [] [(564) 208-6852]
Charlie Brown: [] [(333) 444-5555]
Maid Marion: [] [(123) 456-7890]
Robin Hood: [] [(564) 789-3000]
Mickey Mouse: [] [(999) 888-7777]
Minnie Mouse: [] [(456) 780-5666]
----------------------------------------------
Email Contacts
----------------------------------------------
Linus Van Pelt: [lvpelt2015@gmail.com] []
Robin Hood: [rhood@gmail.com] []
Mickey Mouse: [mckmouse@gmail.com] []
Daffy Duck: [daffy@google.com] []
Minnie Mouse: [minnie@verizon.net] []
Robin Hood: [RHood@sherwoodforest.org, rhood@gmail.com] []
```

You can see my method works, and now I have two emails for Robin Hood, 
one is _rhood@sherwoodforest.com_. 
So what happens if I call that method again? 
Actually, before I do that, 
I want to change the _addEmail_ method on **contact**.

```java  
public void addEmail(String companyName) {

    String[] names = name.split(" ");
    String email = "%c%s@%s.com".formatted(name.charAt(0), names[names.length - 1],
            companyName.replaceAll(" ", "").toLowerCase());
    //emails.add(email);
    if (!emails.add(email)) {
        System.out.println(name + " already has email " + email);
    } else {
        System.out.println(name + " now has email " + email);
    }
}
```

The _add_ method on any collection instance returns a boolean 
if the element is added successfully.
I want to test that result here.
I'll remove the _emails.add_ statement, 
and replace it with an if statement, 
that executes the add method on this set, 
and tests the value returned, the negated value. 
If add returns false, 
I print that emails already have that value in its set, otherwise,
I'll print that it was added. 
Getting back to the main method,

```java  
int index = emails.indexOf(new Contact("Robin Hood"));
Contact robinHood = emails.get(index);
robinHood.addEmail("Sherwood Forest");
robinHood.addEmail("Sherwood Forest");
//System.out.println(robinHood);
```

I'll copy that first addEmail statement, 
and paste a copy right below it. 
Running this,

```java  
Robin Hood now has email RHood@sherwoodforest.com
Robin Hood already has email RHood@sherwoodforest.com
```

You can see that the first time, _RHood@sherwoodforest.com_ was added, 
but in the second instance, 
the _add_ wasn't successful because that email is already there. 
A better approach would have been to use the _contains_ method, 
before I did all the work of generating the email string. 
I'll do this in the next method I want to add to **Contact** Class:

```java  
public void replaceEmailIfExists(String oldEmail, String newEmail) {

    if (emails.contains(oldEmail)) {
        emails.remove(oldEmail);
        emails.add(newEmail);
    }
}
```

I'm going to call, _replaceEmailIfExists_, 
a public method with a _void_ return type. 
This method takes two Strings,
an old email, and a new email, both strings. 
It checks the set to see if the old email is in the set, 
using the contains method. 
If the old email is in the set, then it removes that old email, 
and adds the new email. 
In other words, the new email only gets added 
if the old email is found in the set first, 
so it'd really a replacement. 
Let's say I made a mistake, and now need to fix robin hood's email, 
changing it from _sherwoodforest.com_, 
to _sherwoodforest.org_. 
Going to the main method,
I'll add this statement 
after the second _addEmail_ call. 

```java  
robinHood.replaceEmailIfExists("RHood@sherwoodforest.com", "RHood@sherwoodforest.org");
System.out.println(robinHood);
```

Running that code,

```java  
Robin Hood: [RHood@sherwoodforest.org, rhood@gmail.com] []
```
                    
You can see that I successfully replaced the company email, 
with the dot org version, for Robin hood. 
Unlike lists, the hash set implementation doesn't include a replacement
or _replaceAll_ method. 
Those are the basic functions on **set**, _add_, _remove_, and _contains_. 
Now, there's no get method on a set. 
If you want to get an element from the set, 
you'll have to iterate through every element, 
and look for a match manually. 
And remember your **HashSet**'s not going to be ordered or sorted. 
Sets are valuable for groups of elements, 
when you'll be adding elements, removing duplicates, 
checking if an element is in the list, 
or other set operations I'll be covering shortly. 
This isn't the collection you'd use, 
if you mostly want to get elements from your collection, 
and manipulating values. 

Now, I'll talk about set operations 
which are ways to evaluate relationships of elements in different sets.
</div>

### Set Operations
<div align="justify">

So, I used a **HashSet** to control 
what went in a set, and what didn't,
by manipulating the _equals_ and _hashcode_ methods. 
Now, I'll be explaining a little bit about **Set Operations**, 
first what they are, and second, why you'd want to use them.
When you're trying to understand data in multiple sets, 
you might want to get the data in all the sets,
that's in every set, or the data where there's no overlap. 
The collection interface's bulk operations 
(_addAll_, _retainAll_, _removeAll_, and _containAll_)
can be used to perform these set operations.

Sets are often represented as circles or ovals, 
with elements inside, on what is called a **Venn Diagram**. 
Here, I'm showing two sets that have no elements in common.

![image12](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image11.png?raw=true)

This _venn diagram_ shows some of the cartoon characters 
of the Peanuts and Mickey Mouse cartoons. 
Because the characters are distinct for each set, 
the circles representing the sets don't overlap, or intersect.

![image13](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image12.png?raw=true)

This diagram shows two sets of characters that do overlap. 
Let's say that Goofy and Snoopy have guest appearances 
in the other's holiday special show. 
The intersection of these sets is represented 
by the area where the two circles (sets) overlap, 
and contains the elements that are shared by both sets. 
Goofy and Snoopy are both in Set A and Set B, in other words. 
_Venn Diagrams_ are an easy way to quickly see
how elements in multiple sets relate to each other.
</div>

### Union A u B
<div align="justify">

The union of two or more sets will return elements 
that are in any or all of the sets, removing any duplicates.
These may look familiar because these are the sets I had 
when I left off in the code in the last lecture.

![image14](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image13.png?raw=true)

The table shown here is showing my two sets, names on an email list, 
and names on a phone numbers list. 
The overlap is names that are on both lists. 
In the example shown above, 
all names on the email list 
and phone list will be included in a union of the two sets. 
However, Minnie, Mickie and Robin Hood, 
which are the only elements included in both sets,
are included in the resulting set only once. 
If you have any experience with Structured Query Language, 
or SQL, you'll already be familiar with the union command, 
which joins two datasets in the same way described here. 
Java doesn't have a union method on Collections, 
but the addAll bulk function, 
when used on a Set, 
can be used to create a union of multiple sets.

Getting back to the code, 
I left off with two sets, phone contacts and email contacts. 
A union of these two sets should give us a set 
that consists of distinct contacts, by contact name. 
To perform a union, I can use the bulk operation, _addAll_. 
I'll first create a new Set, called union A, B, a **HashSet**, 
with a no args constructor.

```java  
Set<Contact> unionAB = new HashSet<>();
unionAB.addAll(emailContacts);
unionAB.addAll(phoneContacts);
printData("(A ∪ B) Union of emails (A) with phones (B)", unionAB);
```

I'm going to stick to the convention on
my notes, where A is the emails set, 
and the B is the phones. 
I'll use the _addAll_ method on the unionAB set, 
to add first _emailContacts_. 
I'll do the same thing, _unionAB.addAll()_, 
passing it _phoneContacts_.
Lastly, I'll call _printData_, with a header saying 
this is the union of emails, A, and phones, B. 
There's a special _ascii_ character, _\u222A_,
that represents the union character, 
that looks like a _U_, and I'll include that. 
If I type _\u222A_ in my text, Intelli-J highlights 
that unicode character, 
and prompts me to replace it with the actual character, 
so I'll do that.
Running this code:

```java  
----------------------------------------------
(A ∪ B) Union of emails (A) with phones (B)
----------------------------------------------
Linus Van Pelt: [lvpelt2015@gmail.com] []
Lucy Van Pelt: [] [(564) 208-6852]
Charlie Brown: [] [(333) 444-5555]
Maid Marion: [] [(123) 456-7890]
Robin Hood: [RHood@sherwoodforest.org, rhood@gmail.com] []
Mickey Mouse: [mckmouse@gmail.com] []
Daffy Duck: [daffy@google.com] []
Minnie Mouse: [minnie@verizon.net] []
```
                        
I get the union of these sets, 
and there are no duplicates by name. 
These operations combined the two different sets of contacts 
into a single set of unique contacts. 
Again, I've lost some information 
where I had multiples, 
the other phone numbers and emails, I mean. 
But if you ever just need a distinct set of elements 
from multiple sets, this union operation can give you that information. 
At this point, it would be nice to know 
which contacts overlap the sets. 
This information might allow me to process those records 
in a different way, perhaps capturing the additional emails 
and phone numbers, for example.
</div>

### Intersect A n B
<div align="justify">

The intersection of two or more sets will return only the elements the sets have in common.

![image15](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image14.png?raw=true)

These are shown in the overlapping area of the sets on this table, 
the intersecting includes Mickey and Minnie Mouse, and Robin Hood.

```java  
Set<Contact> intersectAB = new HashSet<>(emailContacts);
intersectAB.retainAll(phoneContacts);
printData("(A ∩ B) Intersect emails (A) and phones (B)", intersectAB);
```

Let's try this out with our same two sets. 
I'll start out by creating a local variable, 
a Set I'm calling intersectAB.
I'll again assign that a new instance of **HashSet**, 
but this time I'm just going to pass 
my email contacts set directly to that constructor. 
I'll execute _retainAll_ on the intersected set, 
passing it the phone contacts. 
And I'll print the result. 
The ascii character for the _intersect_ operator is _\u2229_, 
it looks like an upside down _u_, 
and I want to include that as part of my message, A intersect B. 
I'll again take IntelliJ's suggestion, 
and just convert the unicode escape sequence 
to the actual character. 
On the first line, this time, 
I'm simply passing my first set 
to the constructor of my new set. 
In fact, if I control click _HashSet_ there, 
the code will be displayed in another editor session, 
showing me how it's implemented. 
And you can see, this constructor simply calls _addAll_ there. 
You maybe also noticed it's creating a new _HashMap_, 
assigning it to a map field on this _HashSet_ class, 
in the first statement. 
I said before that the _HashSet_ uses a _HashMap_ in its implementation, 
and this is obvious here. 
These two classes are tightly interwoven in current versions of Java. 
But regardless of the underlying way the _HashSet_ implements 
its collection, and there's never any guarantee in Java 
that the implementation won't change, 
the behavior of the **HashSet** will be consistent. 
This means duplicates aren't supported, 
the collection won't be ordered, 
and hashing will be used to provide close to constant time access. 
Ok, so getting back to the main method, 
the other thing to see there is that I used _retainAll_, 
and passed it the email contacts. 
Running that code,

```java  
----------------------------------------------
(A ∩ B) Intersect emails (A) and phones (B)
----------------------------------------------
Robin Hood: [RHood@sherwoodforest.org, rhood@gmail.com] []
Mickey Mouse: [mckmouse@gmail.com] []
Minnie Mouse: [minnie@verizon.net] []
```
                    
I get what's called the intersection, 
where the two sets intersect, 
and the result is the elements the sets 
have in common. 
This gives me the 3 contacts that are on both lists, 
Mickey, Minnie, and Robin Hood. 
Notice that the contacts all have emails, 
and that's because multiple records were not added, 
and it's the first record that's added, 
that remains in the set. 
In other words, a duplicate element won't replace the current element. 
In this case, when I added the phone contacts
for Mickey, Minnie, and Robin Hood, those records were ignored 
because the set already had records for them in it. 
I can switch the order of the way I intersect around, 
and I'll show that to you next.

```java  
Set<Contact> intersectBA = new HashSet<>(phoneContacts);
intersectBA.retainAll(emailContacts);
printData("(B ∩ A) Intersect phones (B) and emails (A)", intersectBA);
```

I'll copy those last three statements and paste them below. 
I'll change my set name to intersectBA, in all three statements. 
I'll pass phone contacts to the constructor this time, 
so those are the first set of elements included.
I'll change phone contacts to email contacts 
when I call the retainAll method. 
I'll also change my message to say this is B intersect A, 
Intersect of phones (Bin parentheses) and emails, (A, in parentheses).
Running that code,

```java  
----------------------------------------------
(B ∩ A) Intersect phones (B) and emails (A)
----------------------------------------------
Robin Hood: [] [(564) 789-3000]
Mickey Mouse: [] [(999) 888-7777]
Minnie Mouse: [] [(456) 780-5666]
```

I get the same set of contacts, the same set of three cartoon characters. 
I mean, Mickey, Robin, and Minnie. 
The extra data, the data that wasn't included in the equality test, 
is different here though, so now I get phone numbers. 
That's because the first contact records added for these three, 
had the phone data on them. 
Even though I flipped the sets in my intersection set, 
the result was the same, returning Mickey, Robin, and Minnie.
</div>

### Asymmetric Differences
<div align="justify">

The ability to evaluate sets, A intersect B 
and get the same result as B intersect A, 
means that the intersect operation is a symmetric set operation. 
Union is also a symmetric operation. 
It doesn't matter if you do A Union B, or B union A, 
the final set of elements will all be the same set of names. 
Another useful evaluation might be to identify 
which elements are in one set, but not the other. 
This is called a set difference.

A difference subtracts elements in common from one set and another, 
leaving only the distinct elements from the first set as the result.

![image16](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image15.png?raw=true)

This is an asymmetric operation because if we take Set A 
and subtract Set B from it, we'll end up with a different set of elements 
than if we take Set B and subtract Set A. 
The sets from these two operations won't result in the same elements.
Let's see what this looks like in our code. 

```java  
Set<Contact> AMinusB = new HashSet<>(emailContacts);
AMinusB.removeAll(phoneContacts);
printData("(A - B) emails (A) - phones (B)", AMinusB);
```

I'm going to copy the intersectAB's three statements, 
and paste a copy at the end of the main method. 
I'll change intersectAB to AMinusB on all three lines. 
On the second line, I'll change retainAll to removeAll. 
On the third line, I'll change the intersect character
to a minus sign, and I'll just say this is emails, A, minus phones, B. 
Running this code:

```java  
----------------------------------------------
(A - B) emails (A) - phones (B)
----------------------------------------------
Linus Van Pelt: [lvpelt2015@gmail.com] []
Daffy Duck: [daffy@google.com] []
```

This tells us that Linus and Daffy are the only two records in the email list 
that aren't in the phones list.

```java  
Set<Contact> BMinusA = new HashSet<>(phoneContacts);
BMinusA.removeAll(emailContacts);
printData("(B - A) phones (B) - emails (A)", BMinusA);
```

Now let me copy those last three statements 
and paste them right below. 
I want to change the Set name A, Minus B, 
in all three statements to B Minus A. 
I'll also change the first statement, 
and pass it phone contacts, instead of email contacts, 
to the constructor. 
In the second statement, I want to pass email contacts there, 
instead of phone contacts, so I'm reversing the direction if you will. 
I'll change my header to say B minus A, phones (B) - emails (A). 
Running that code,

```java  
----------------------------------------------
(B - A) phones (B) - emails (A)
----------------------------------------------
Lucy Van Pelt: [] [(564) 208-6852]
Charlie Brown: [] [(333) 444-5555]
Maid Marion: [] [(123) 456-7890]
```
                    
You now see that the set of contacts is a different set of characters all together, 
Lucy Van Pelt, Charlie Brown, and Maid Marion.
</div>

### Symmetric Differences
<div align="justify">

Now, let's look at what's called the set symmetric difference. 
This is really the union of the two asymmetric set differences. 
You can think of the set symmetric difference,
as the elements from all sets that don't intersect.

![image17](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image16.png?raw=true)

On this table, these are the elements that are represented in the intersection. 
This data can be retrieved in one of two ways.

```java  
Set<Contact> symmetricDiff = new HashSet<>(AMinusB);
symmetricDiff.addAll(BMinusA);
printData("Symmetric Difference: phones and emails", symmetricDiff);
```

The first would be to do a union of our two difference sets, 
AMinusB and BMinusA, so I'll do that next. 
I'll set up a new set, and call it symmetric Diff, 
assigning it a new HashSet, and passing A, 
minus B to that constructor. 
I'll call add all, on the symmetricDiff set, and pass it B Minus A. 
I'll print out that this is the Symmetric Difference of phones and emails. 
Running that,

```java  
----------------------------------------------
Symmetric Difference: phones and emails
----------------------------------------------
Linus Van Pelt: [lvpelt2015@gmail.com] []
Lucy Van Pelt: [] [(564) 208-6852]
Charlie Brown: [] [(333) 444-5555]
Maid Marion: [] [(123) 456-7890]
Daffy Duck: [daffy@google.com] []
```

I get the five contacts that don't intersect, 
the union of my two asymmetric differences set. 

```java  
Set<Contact> symmetricDiff2 = new HashSet<>(unionAB);
symmetricDiff2.removeAll(intersectAB);
printData("Symmetric Difference: phones and emails", symmetricDiff2);
```

The other way to get these same set of elements is 
to take the unioned set, and subtract the intersected set. 
I'll first create a set, called symmetric diff2, 
a hash set constructed by passing the unionAB set, to it. 
I'll execute remove all on that set, passing it the intersectAB set. 
And I'll print the same text, as previously, 
but pass this new set, symmetric Diff2. 
And running that,

```java  
----------------------------------------------
Symmetric Difference: phones and emails
----------------------------------------------
Linus Van Pelt: [lvpelt2015@gmail.com] []
Lucy Van Pelt: [] [(564) 208-6852]
Charlie Brown: [] [(333) 444-5555]
Maid Marion: [] [(123) 456-7890]
Daffy Duck: [daffy@google.com] []
```
                    
I get the same results this way. 
Ok, so that covers the most commonly set operations, 
and the difference between symmetric operations 
and asymmetric operations. 
I hope you can start to imagine how you might use these combinations 
to drive processing, based on the relationships of the data in multiple sets. 
</div>

## [f. Set Operations Challenge Setup]()
<div align="justify">

Part of the upcoming challenge on Set Operations 
will be using a **Task** class. 
This is a fairly simple class, with multiple attributes, 
some with enums. 
This will be very similar to the Contact Data set up lecture 
I did earlier, which produced lists. 
In this case, I'll be getting different sets of tasks, 
and instead of using Scanner to parse text block strings, 
I'll use methods on String. 
I'm also going to implement Comparable in **Task**. 
If you feel you need more experience working with Comparable or enums, 
then this is a good opportunity to do it. 
If you just want to concentrate on the set operations part of the challenge, 
you can skip this setup lecture.

You'll want to create a class that represents a **Task**. 
It should have:

* an assignee.
* a project name.
* a task description.
* a status (assigned, in progress, or not yet assigned).
* a priority, High, Low, or Medium.

Each of these attributes should be editable. 
A task is uniquely identified by its project name and description. 
The task should implement Comparable, 
so that tasks are sorted by project name and description. 
The **TaskData** class will be used to set up 
and return some test data. 
If you want to use my data, it can be found in a csv file, 
and consists of the following:

* All tasks identified by the manager.
* Tasks identified by Ann that she's working on or plans to work on.
* Tasks which Bob says have been assigned to him.
* Tasks Carol is doing, as reported by herself.

This class should have a getData method 
that returns a Set of **Tasks**. 
This method should take a String, either the name of one of the employees 
to get a specific set, or _all_ to get the full task set.

I've created h a **Main** class and _main_ method. 
Before I do anything with that, I'll start by creating a **Task** class.

```java  
enum Priority {HIGH, MEDIUM, LOW}
enum Status {IN_QUEUE, ASSIGNED, IN_PROGRESS}

public class Task {

    private String project;
    private String description;
    private String assignee;
    private Priority priority;
    private Status status;
}
```

I'm going to include two enums in this source file. 
You'll remember I can do this, as long as I don't make them public.
I'll call the first enum, **Priority**, and its constants will be 
_HIGH_, _MEDIUM_, and _LOW_, in that order. 
My second enum is **Status**, and that's going to have
_IN_QUEUE_, which will be the default status of any new task. 
I'll also include _ASSIGNED_, and _IN_PROGRESS_. 
Right now, I'm not going to worry about other statuses, 
like completed. 
I've said I want five fields in this **task** class, 
and they'll all be _private_. 
The first three are strings, 
and the first of those, I'm going to call _project_. 
Each task will belong to some project. 
Next, description, which just describes a unit of work.
Then, the _assignee_ or the employee completing the task. 
_Priority_, with my **Priority** enum type for that, 
so this could be _low_, _medium_ or _high_. 
_Status_ will be the type **Status**, my other enum type.

```java  
public Task(String project, String description, String assignee, Priority priority,
            Status status) {
    this.project = project;
    this.description = description;
    this.assignee = assignee;
    this.priority = priority;
    this.status = status;
}

public Task(String project, String description, String assignee, Priority priority) {
    this(project, description, assignee, priority,
            assignee == null ? Status.IN_QUEUE : Status.ASSIGNED);
}
```

I'll generate some constructors, the first will have all five fields. 
The second constructor will have all fields except status. 
I'm going to remove all the statements in this generated constructor. 
I want to replace all that by chaining a call to the five argument constructors. 
I'll simply pass along the arguments for the first four. 
For the status, if the assignee is null, 
I'll say it's _IN_QUEUE_; otherwise, it's _ASSIGNED_. 
I'll create another constructor, my third,
and for this one, I want the fields, _project_, 
_description_, and _priority_. 
Again, I'll remove the statements in there.
I'll chain a constructor call here too, 
this time to the four argument constructors, 
passing _null_ as the third argument to that, 
which is the _assignee_. 
That chained constructor will, in turn, 
set the status to _IN_QUEUE_, 
because the assignee is _null_. 
I'll generate getters and setters for all five fields. 
I may want to use the getters as field references for
other sorting mechanisms, 
from outside of this class. 
You can also imagine task fields changing quite a bit over time,
so we'll want a way to set data on a task.

```java  
@Override
public String toString() {
    return "%-20s %-25s %-10s %-10s %s".formatted(project, description, priority, assignee, status);
}
```

I'll also generate a _toString_ method. 
I'm going to replace that generated code, 
returning a formatted string instead, 
with all five fields passed as parameters. 
I'll print a first _project_, then _description_, 
then _priority_. 
Finally, the _assignee_ and the _status_. 
I said I wanted my tasks to be unique by project 
and description.

```java  
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Task task = (Task) o;

    if (!getProject().equals(task.getProject())) return false;
    return getDescription().equals(task.getDescription());
}

@Override
public int hashCode() {
    int result = getProject().hashCode();
    result = 31 * result + getDescription().hashCode();
    return result;
}
```

This means I want to generate the equals and hashCode methods, 
using only those two fields, 
so I'll need to deselect all other fields in the first popup. 
I want to select them on the last popup, 
because they should never be null.
Finally, I want to implement Comparable on this class, 
so I'll scroll up to the **Task** declaration 
and include implements Comparable. 

```java  
public class Task implements Comparable<Task> 
```

I should always specify a type argument 
when I implement comparable, 
and here it should be **Task**, the current class. 
I've got an error 
because I haven't yet implemented the abstract method on Comparable.
I'll use IntelliJ's help to do that.

```java  
@Override
public int compareTo(Task o) {

    int result = this.project.compareTo(o.project);
    if (result == 0) {
        result = this.description.compareTo(o.description);
    }
    return result;
}
```
        
That gives me the default implementation, 
which just _returns 0_. 
I want to compare two fields in this class. 
I'll start by creating a local variable, int result,
and assign that the comparison of the project fields, 
on this and on the argument passed. 
If the result is zero, the project names are the same. 
In that case, I want to then sort by the task description. 
I'll assign the result to be the comparison of the description 
fields on these objects. 
And I'll return the result. 
That finishes the code for the task class, at this point. 
Setting up all these methods manually can be quite tedious, 
so thank goodness for IntelliJ's tools here, 
that help us with this boilerplate code. 
Now that I've got the Task class, I want to load up some data, 
into tasks, and put them in sets. 
I'll do this with a **TaskData** class, so I'll create that.

```java  
public class TaskData {

    private static String tasks = """
            Infrastructure, Logging, HIGH
            Infrastructure, DB Access, MEDIUM
            Infrastructure, Security, HIGH
            Infrastructure, Password Policy, MEDIUM
            Data Design, Task Table, MEDIUM
            Data Design, Employee Table, MEDIUM
            Data Design, Cross Reference Tables, HIGH
            Data Design, Encryption Policy, HIGH
            Data Access, Write Views, Low
            Data Access, Set Up Users, Low
            Data Access, Set Up Access Policy, Low
            """;

    private static String annsTasks = """
            Infrastructure, Security, HIGH, In Progress
            Infrastructure, Password Policy,MEDIUM, In Progress
            Research, Cloud solutions, MEDIUM, In Progress
            Data Design, Encryption Policy, HIGH
            Data Design, Project Table, MEDIUM
            Data Access, Write Views, Low, In Progress
            """;

    private static String bobsTasks = """
            Infrastructure, Security, High, In Progress
            Infrastructure, Password Policy, Medium
            Data Design, Encryption Policy, High
            Data Access, Write Views, Low, In Progress
            """;

    private static String carolsTasks = """
            Infrastructure, Logging, High, In Progress
            Infrastructure, DB Access, Medium
            Infrastructure, Password Policy, Medium
            Data Design, Task Table, High
            Data Access, Write Views, Low
            """;
}
```

I'll set up an empty text block, 
private and static on this class, 
called _tasks_.
I'm going to paste some data in here, 
in just a minute, 
but first I want to copy that empty text block, 
and paste it, renaming it, _annsTasks_. 
I'll paste that again, and name this one _bobsTasks_. 
And one more time, pasting that,
and renaming it to _carolsTasks_. 
I'm going to use the data from the csv file, 
and paste all tasks in the first text block. 
I'll repeat that process for Ann's tasks. 
Next, I'll copy and paste Bob's tasks. 
Finally, I'll repeat that for Carol's tasks. 
Any time you get data from anywhere, 
you should spend a few minutes 
getting familiar with it.
You'll notice this data comes in two different ways. 
For the full task list, there are three comma-delimited fields, 
the first is _project_, and it appears the list is sorted by this field. 
The second field is _description_, or task description. 
The third is a _priority_, meaning 
we have to get this text to match our priority enum values 
when we load the data. 
Scroll down to look at ann's and Bob's data, 
you can see there's a fourth field, 
a _status_, and that's in mixed case. 
Again, we'll have to transform that string into the enum type 
we've got for Status. 
Also, there's no assignee field in this data.
That's going to have to be derived by the field itself. 
In other words, ann's Task will all need to default Ann as the assignee. 
Now that we've got an understanding of this data, 
I want to create the getTasks method.

```java  
public static Set<Task> getTasks(String owner) {

    Set<Task> taskList = new HashSet<>();
    String user = ("ann,bob,carol".contains(owner.toLowerCase())) ? owner : null;

    String selectedList = switch (owner.toLowerCase()) {
        case "ann" -> annsTasks;
        case "bob" -> bobsTasks;
        case "carol" -> carolsTasks;
        default -> tasks;
    };

    for (String taskData : selectedList.split("\n")) {
        String[] data = taskData.split(",");
        Arrays.asList(data).replaceAll(String::trim);

        Status status = (data.length <= 3) ? Status.IN_QUEUE :
                Status.valueOf(data[3].toUpperCase()
                        .replace(' ', '_'));

        Priority priority = Priority.valueOf(data[2].toUpperCase());
        taskList.add(new Task(data[0], data[1], user, priority, status));
    }

    return taskList;
}
```

It's going to be public and static, 
so users can call it by qualifying the class name, **TaskData**. 
It's going to return a Set of tasks, 
and it's going to take a String, the owner, or assignee. 
I'll set up a local variable, that's the variable 
that'll get returned from this method, so it's declared as a Set, 
but it's going to be a hash set instance.
Since I have a limited set of users, 
I'll just list their names in a comma-delimited string, 
and use the contains method to determine if the name passed, 
ignoring the case, is one of those names. 
If it is, I'll use the method argument as the user or the assigned person, 
otherwise I'll set that to null. 
I want to return my local variable at the end of this method.
Of course, I'm not done with this method. 
There's a bit more work to do.

I need to figure out which text block to use, 
and that's again determined by the method argument, the owner. 
This time I'll switch on an owner, again, calling a lower case. 
If Ann was passed to this, I'll set selected List to ann's Tasks. 
If it was Bob, I'll pass back bobs Tasks. 
If Carol, then it's Carol's tasks. 
And if it wasn't any of those, I'll pass back the full task list, 
so just tasks.

Now that I've got the right data, based on the owner, 
meaning I have the text block I want to process, 
I need to split each text block by lines. 
I did this in an earlier example, 
using a Scanner instance and its nextLine method.
Here I'm simply going to use the split method on String, 
and pass it the escape sequence for a new line, backslash n.
I'm going to loop through every line, 
which I get from this split method. 
After I have the line, I want to split by commas to get the field data. 
I'll trim every field's data, using replace all, 
which you've seen me do before. 
Ok, so I've got all my data in an array. 
Before I can create an instance of a Task, 
I want to transform status, 
which you'll remember was in the fourth place in a record that had a status. 
If there are less than three fields, 
I'll default the status to the in queue constant. 
Otherwise, I will pass the value of the status data field 
to the Status enums value of method. 
I want to make the string pass all upper cases, 
and replace spaces with underscores. 
Getting the priority enum constant is similar, 
but a little simpler. 
All records should have this field, 
and I just want it to be upper case. 
Now, I can create the task, passing it the first field, project, 
the second field, description, the user variable, 
and my priority and status variables, which are now both enum constants. 
I'm creating the task and adding it to the task list set at the same time. 
There are a lot of things that could go wrong with this method, 
but again I'm just keeping it simple here for the sake of time. 
To make it more robust, you'd want to check the data for nulls, 
and empty strings, or lines that don't have enough data, and so on.
With this code, we can get data from our source with a parameterized
call of a static method on this class. 
Getting back to the **main** class,

```java  
private static void sortAndPrint(String header, Collection<Task> collection) {
    sortAndPrint(header, collection, null);
}

private static void sortAndPrint(String header, Collection<Task> collection, Comparator<Task> sorter) {

    String lineSeparator = "_".repeat(90);
    System.out.println(lineSeparator);
    System.out.println(header);
    System.out.println(lineSeparator);

    List<Task> list = new ArrayList<>(collection);
    list.sort(sorter);
    list.forEach(System.out::println);
}
```

I'm going to add a method that will sort and print tasks. 
I'll make this private and static, and call it sort and print. 
It will take a header or description of the collection, 
a collection containing tasks, and a comparator I'm calling sorter. 
I'll set up 90 dashes, separator line as a variable. 
I'll print my 90 dashes, followed by the header, 
followed by another string of dashes. 
To sort, I'd need to use a sortable set,
which I haven't covered yet, so I'm going to set up a list here, 
an array list and pass it the collection, the method argument. 
I can call sort directly on an ArrayList, and pass it the sorter, a comparator. 
And I'll just print out each element of the sorted list. 
I also want to add an overloaded version of this method 
that doesn't take a Comparator. 
I'll insert this above the first method. 
I'll chain a call to the sort and print method, 
but pass null as the comparator, the last argument.
Remember, I made the **Task** Comparable,
so if I call _list.sort_ with a null, 
and the list has elements that implement Comparable, 
it will get sorted using **Comparable**'s _compareTo_ method.

```java  
public class Main {

    public static void main(String[] args) {
        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("All Tasks", tasks);

        Set<Task> annsTasks = TaskData.getTasks("Ann");
        sortAndPrint("Ann's Tasks", annsTasks);
    }
}
```

I'll create a **Set**, named tasks 
and call get tasks on task data, 
passing all as the owner. 
I'll invoke my sortAndPrint method, with a header,
passing it tasks. 
Ok, so I can run this and see the full task list, 
sorted by my Comparable sort, project first, then description.
I'll copy and paste those two lines of code. 
I'll change tasks to ann's tasks, and all to Ann, on the first statement. 
I'll change the header to Ann's Tasks, 
and I want to again change tasks to ann's Tasks, 
on the second statement. 
If I run that:

```java  
__________________________________________________________________________________________
All Tasks
__________________________________________________________________________________________
Data Access          Set Up Access Policy      LOW        null       IN_QUEUE
Data Access          Set Up Users              LOW        null       IN_QUEUE
Data Access          Write Views               LOW        null       IN_QUEUE
Data Design          Cross Reference Tables    HIGH       null       IN_QUEUE
Data Design          Employee Table            MEDIUM     null       IN_QUEUE
Data Design          Encryption Policy         HIGH       null       IN_QUEUE
Data Design          Task Table                MEDIUM     null       IN_QUEUE
Infrastructure       DB Access                 MEDIUM     null       IN_QUEUE
Infrastructure       Logging                   HIGH       null       IN_QUEUE
Infrastructure       Password Policy           MEDIUM     null       IN_QUEUE
Infrastructure       Security                  HIGH       null       IN_QUEUE
__________________________________________________________________________________________
Ann's Tasks
__________________________________________________________________________________________
Data Access          Write Views               LOW        Ann        IN_PROGRESS
Data Design          Encryption Policy         HIGH       Ann        IN_QUEUE
Data Design          Project Table             MEDIUM     Ann        IN_QUEUE
Infrastructure       Password Policy           MEDIUM     Ann        IN_PROGRESS
Infrastructure       Security                  HIGH       Ann        IN_PROGRESS
Research             Cloud solutions           MEDIUM     Ann        IN_PROGRESS
```

I'll now get Ann's tasks printed out. 
For good measure, I'll change the way I want this sorted.

```java  
public class Main {

    public static void main(String[] args) {
        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("All Tasks", tasks);

        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);
        Set<Task> annsTasks = TaskData.getTasks("Ann");
        //sortAndPrint("Ann's Tasks", annsTasks);
        sortAndPrint("Ann's Tasks", annsTasks, sortByPriority);
    }
}
```

I'll add a local variable, a Comparator, 
with a type argument task. 
I'll call it sort of priority. 
I'll assign it the result of calling comparing, on Comparator, 
with the method reference _Task::getPriority_. 
I want to change the call to sort and Print on that last statement,
where I print ann's tasks, and include the sort by priority 
as an additional argument. 
Rerunning the code,

```java  
__________________________________________________________________________________________
Ann's Tasks
__________________________________________________________________________________________
Data Design          Encryption Policy         HIGH       Ann        IN_QUEUE
Infrastructure       Security                  HIGH       Ann        IN_PROGRESS
Infrastructure       Password Policy           MEDIUM     Ann        IN_PROGRESS
Research             Cloud solutions           MEDIUM     Ann        IN_PROGRESS
Data Design          Project Table             MEDIUM     Ann        IN_QUEUE
Data Access          Write Views               LOW        Ann        IN_PROGRESS
```
                
I get Ann's task sorted by priority there, 
with the highest priority tasks list first. 
If I had set up my enum to go from Low to High,
instead of High to Low, these would have been sorted in reverse. 
Ok, so these are the classes 
that you'll need to complete the Set Operations Challenge, 
which is coming up next.
</div>

## [g. Set Operations Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/Course13_SetOperationsChallenge/README.md#set-operations-challenge)
<div align="justify">

In the last section, I walked through creating a **Task** class, 
which I'll be using in this challenge. 
And I created a TaskData class to return four different sets of data, 
which we'll be using here.

![image18](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image17.png?raw=true)

This class diagram shows you the two classes. 
**Task** has five fields, two with enum types shown. 
Task is unique by the project and description fields combined. 
Task implements comparable, and is sorted by project then description.
Some test data was set up on the TaskData class, 
and you can get this data by calling _TaskData.getData_, 
passing it the names, _Ann_, _Bob_, or _Carol_, or some other string, 
like _all_, to get all tasks. 
If you didn't see the last section, take a minute to get familiar with the data, 
set up as four text blocks, on the **TaskData** class. 
In addition to this code, I included a couple of methods in the **Main** class 
to print sorted collections, with a descriptive header. 
One takes a comparator of your choice. 
The other doesn't, meaning the elements will get sorted by the **Comparable** sort.

Let's say you're a new manager of a team 
that consists of three team members working under you, 
_Ann_, _Bob_, and _Carol_.
Each of these developers is working on a set of tasks. 
The management of the tasks has been a manual process, 
and you don't really have a good way of knowing whose working on what, 
how things are prioritized, or 
how evenly the work is distributed. 
You've asked all your developers to submit 
what they're working on to you. 
You also have a primary set of tasks, 
which your own boss sent to you. 
I've included this data in a csv file, 
and it's built into the **TaskData** class. 
You'll be using that data to answer the following questions.

* What is the full task list? 
This is the list of all tasks described by your manager or boss, 
and any additional tasks the employees have that may not be on that list.
* Which tasks are assigned to at least one of your 3 team members?
* Which tasks still need to be assigned?
* Which tasks are assigned to multiple employees?
To do some of this work, create three methods in your **Main** class. 
Be sure the sets you pass to these methods don't mutate in these methods. 
In other words, return a new set. 
* Create a getUnion method that takes a List of Sets, 
and will return the union of all the sets.
* Create a getIntersect method that takes two Sets,
and returns the intersection of the sets. 
* Create a getDifference method that takes two Sets, 
and removes the second argument's set from the first.
</div>

## [h. LinkedHashSet and TreeSet Interfaces]()
<div align="justify">

In the last section, we reviewed the **HashSet**, 
and I've said before, that ordering is unspecified 
and generally chaotic. 
If you need an ordered set, 
you'll want to consider either the **LinkedHashSet** 
or the **TreeSet**. 

A **LinkedHashSet** maintains the insertion order of the elements. 
The **TreeSet** is a sorted collection, sorted 
by the natural order of the elements, 
or by specifying the sort during the creation of the set. 
The **LinkedHashSet extends the HashSet** class. 
It maintains relationships between elements 
with the use of a doubly linked list between entries. 
The **iteration order** is therefore 
the same as the **insertion order** of the elements, 
meaning the order is **predictable**. 
All the methods for the **LinkedHashSet** are the same 
as those for the **HashSet**. 
Like **HashSet**, it provides constant-time performance, 
O(1), for the _add_, _contains_ and _remove_ operations. 
But like the _HashSet_, this assumes an efficient hashing function.

![image19](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image18.png?raw=true)

A **TreeSet**'s class uses a data structure 
that's a derivative of what's called a binary search tree, 
or **Btree** for short, which is based on the concept 
and efficiencies of the binary search. 
I've discussed the binarySearch method on the List, 
as well as the **java.util.Collections** class, 
and shown that this type of search is rapid, 
if the elements are sorted. 
This search iteratively tests the mid-range of a group of elements 
to be searched, to quickly find its element in a collection.

As elements are added to a **TreeSet**, 
they're organized in the form of a tree, 
where the top of the tree represents 
that mid-point of the elements. 
This tree shows a conceptual example, 
using some of the character contacts from my last samples of code.
Further binary divisions become nodes under that. 
The **left** node and its children are elements 
that are **less than** the parent node. 
The **right** node and its children are elements 
that are **greater than** the parent node. 
Instead of looking through all the elements in the collection 
to locate a match, this allows the tree to be quickly traversed, 
each node a simple decision point. 
Java's internal implementation uses a balanced tree data structure,
called the red-black tree. 
This class isn't about software engineering, 
but if this topic interests you, 
let me encourage you to explore software data structures. 
The main point is the tree remains balanced as elements are added.
</div>

### TreeSet O Notation
<div align="justify">

You'll remember that O(1) is constant time, 
meaning the time or cost of an operation doesn't change, 
regardless of how many elements are processed. 
O(n) is a linear time, meaning it grows in line 
with the way the collection grows.
Another notation is O(log(n)), 
which means the cost falls somewhere 
in between constant and linear time. 
The **TreeSet** promises O(log(n)) 
for the _add_, _remove_, and _contains_ operations, 
compared to the **HashSet** 
which has constant time O(1) for those same operations.
</div>

### TreeSet Interface
<div align="justify">

![image20](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image19.png?raw=true)

A **TreeSet** can be declared or passed 
to arguments typed with any of the interfaces shown on above. 
This class is sorted and implements the **SortedSet** interface, 
which has such methods as _first_, _last_, _headSet_ and _tailSet_, 
and comparator.
This set also implements the **NavigableSet** Interface, 
so it has methods such as _ceiling_, _floor_, _higher_, _lower_, 
_descendingSet_ and others. 
The **TreeSet** gives us a lot more functionality, 
but at a higher cost, than a **LinkedHashSet**, or a **HashSet**.
You don't really have to understand the underlying data structure 
to understand two important points.

Elements which implement Comparable 
(said to have a natural order sort, like Strings and numbers) 
can be elements of a **TreeSet**. 
If your elements don't implement Comparable, 
you must pass a Comparator to the constructor.

Let's jump into code, and start exploring the **TreeSet** class, 
and how to use it. 
I will not really spend much time on the **LinkedHashSet**, 
since it doesn't differ significantly, as far as functionality, 
from the **HashSet**. 
Know that the **LinkedHashSet** provides a predictable iterable order, 
and this incurred slightly more cost than a **HashSet**, 
because of the doubly linked list structure that supports it. 
Ok, I want to create a new class, apart from the **Main** class 
to keep the **TreeSet** code separate, 
so I'll create a **TreeSetMain** class.

```java  
public class TreeSetMain {

    public static void main(String[] args) {
        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        NavigableSet<Contact> sorted = new TreeSet<>(phones);
        sorted.forEach(System.out::println);
    }
}
```

I want to get the phone and email lists from the **ContactData** class.
I'll set up two local variables, both lists. 
The _phones_ variable will get assigned 
the result of calling get data on **ContactData**, 
passing phone as the **type**. 
Emails get set the same way, but this time 
I pass email to the _getData_ method as the **type**. 
If I want the additional functionality of the **SortedSet** 
or **NavigableSet**, then I need to declare my **Set** as one of these types. 
I'm going to start out using the **NavigableSet** as my reference type, 
the most specific of the interfaces, 
because I want to cover all the methods unique 
to both the **SortedSet** and **NavigableSet**. 
I'll call this variable sorted, and assign a new **TreeSet**, 
passing it my _phones_ list. 
And I'll print the elements in that set out.
This code compiles, but if I run it, 
I get a familiar error, that _**Contact** cannot be cast to class **Comparable**_. 

```java  
public class TreeSetMain {

    public static void main(String[] args) {
        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        //NavigableSet<Contact> sorted = new TreeSet<>(phones);
        Comparator<Contact> mySort = Comparator.comparing(Contact::getName);
        NavigableSet<Contact> sorted = new TreeSet<>(mySort);
        sorted.addAll(phones);
        sorted.forEach(System.out::println);
    }
}
```

This exception confirms what I stated before, 
that elements must implement Comparable. 
I can get around this requirement if I pass a **Comparator** to the constructor, 
defining the sort, which I'll do next. 
Now, the constructor that takes a Comparator is a single argument constructor, 
so I'll create that, then add my phone contacts.
I'll comment out that first statement. 
Right below that, and before the for each statement, 
I'll add a couple of lines.
First, I'll create a comparator called _mySort_. 
I'll set that to the result of calling 
the _comparing_ method on comparator,
using the method reference **contact::getMame**. 
I'll again create a variable with the type-**NavigableSet**, 
type argument **Contact**, name is _sorted_, 
and assign a new **TreeSet** to that, but this time, 
I'm passing the _mySort_ variable to it. 
I'll next use the _addAll_ method to add the phone contacts. 
Ok, let me run that:

```java  
Charlie Brown: [] [(333) 444-5555]
Lucy Van Pelt: [] [(564) 208-6852]
Maid Marion: [] [(123) 456-7890]
Mickey Mouse: [] [(999) 888-7777]
Minnie Mouse: [] [(456) 780-5666]
Robin Hood: [] [(564) 789-3000]
```
                
And now you can see my phone contacts, in name order, 
and without duplicates.
Since we know Strings have a natural sort of order, 
I'll create a TreeSet of those next. 

```java  
NavigableSet<String> justNames = new TreeSet<>();
phones.forEach(c -> justNames.add(c.getName()));
System.out.println(justNames);
```

I'm using the no args constructor in this case. 
And I'll loop through my phone contacts, 
adding each name of the contact to my set of strings. 
And I can print that out. 
Running this code works,

```java  
[Charlie Brown, Lucy Van Pelt, Maid Marion, Mickey Mouse, Minnie Mouse, Robin Hood]
```
                
And my set of _justNames_ is sorted. 
I can also pass in a sorted set 
to the **TreeSet** constructor, and 
since I already have one set up, sorted, 
I'll pass that.

```java  
NavigableSet<Contact> fullSet = new TreeSet<>(sorted);
fullSet.addAll(emails);
fullSet.forEach(System.out::println);
```

Now, I'll create a new set, another **NavigableSet**, 
called _fullSet_, and assign that a new **TreeSet**, 
passing my sorted set to the constructor. 
I'll add the _emails_ list, 
passing them to the _addAll_ method on this set.
And I'll print the elements out in the full set. 
This compiles and runs:

```java  
Charlie Brown: [] [(333) 444-5555]
Daffy Duck: [daffy@google.com] []
Linus Van Pelt: [lvpelt2015@gmail.com] []
Lucy Van Pelt: [] [(564) 208-6852]
Maid Marion: [] [(123) 456-7890]
Mickey Mouse: [] [(999) 888-7777]
Minnie Mouse: [] [(456) 780-5666]
Robin Hood: [] [(564) 789-3000]
```
                
It shows that I now have a combined list of contacts, 
in alphabetical order. 
In this case, the sort was determined by the sort mechanism 
of the TreeSet passed to the constructor. 
In fact, there's a method on the SortedSet interface 
that returns the comparator used in the set.

```java  
List<Contact> fullList = new ArrayList<>(phones);
fullList.addAll(emails);
fullList.sort(sorted.comparator());
System.out.println("--------------------------");
fullList.forEach(System.out::println);
```

I'll create a **List**, _fullList_, 
assigning that a new array list passing it 
my _phones_ list. 
I'll add the _emails_ to the _fullList_, using _addAll_. 
Now, I want to sort the same way the tree set is sorted, 
by passing the result of calling the comparator method on the sorted set.
I'll print a separator line. 
And then I'll print my combined list. 
And running this code:

```java  
--------------------------
Charlie Brown: [] [(333) 444-5555]
Daffy Duck: [daffy@google.com] []
Linus Van Pelt: [lvpelt2015@gmail.com] []
Lucy Van Pelt: [] [(564) 208-6852]
Maid Marion: [] [(123) 456-7890]
Mickey Mouse: [] [(999) 888-7777]
Mickey Mouse: [] [(124) 748-9758]
Mickey Mouse: [] [(999) 888-7777]
Mickey Mouse: [mckmouse@gmail.com] []
Mickey Mouse: [micky1@aws.com] []
Minnie Mouse: [] [(456) 780-5666]
Minnie Mouse: [minnie@verizon.net] []
Robin Hood: [] [(564) 789-3000]
Robin Hood: [] [(789) 902-8222]
Robin Hood: [rhood@gmail.com] []
```
                        
Notice that this list contains duplicates, 
but I was able to sort in the same way as the set, 
alphabetically by the name. 
Next, I want to show you the _first_, _last_, _pollFirst_ 
and _pollLast_ methods on this class. 
But before I do that, I also want 
to get the minimum and maximum values of this set.

```java  
Contact min = Collections.min(fullSet, fullSet.comparator());
Contact max = Collections.max(fullSet, fullSet.comparator());
```

You may remember there is a _min_, and _max_ method on 
**java.util.Collections**, so I'll try to use those first, 
passing the _fullSet_ to those methods. 
You can see this code doesn't compile 
when I try to use this version of the _min_ and _max_ methods, 
because I still haven't implemented Comparable on **Contact**. 
But there are overloaded versions of these methods, 
that let me pass a comparator, and I can actually get this from the set itself, 
as I showed you earlier. 
I'll add **fullSet.comparator()** in both of those method calls. 
This code compiles and runs, 
and I'll print the results in just a minute. 
But actually, the **SortedSet** interface includes 
_first_ and _last_ methods, and I'll demonstrate them next.

```java  
Contact first = fullSet.first();
Contact last = fullSet.last();

System.out.println("--------------------------");
System.out.printf("min = %s, first=%s %n", min.getName(), first.getName());
System.out.printf("max = %s, last=%s %n", max.getName(), last.getName());
System.out.println("--------------------------");
```

I'll create two variables, both **Contacts**, 
and I'll set first to the result of calling 
the _first_ method on _fullSet_.
I'll call _last_ on _fullSet_ 
and assign that to my variable called _last_. 
These methods give me the same results 
as the methods on **Collections**, 
and are the preferred way to get this data, 
but I did want you to see the alternative, 
which you might again see in legacy code. 
I'll print these out next, printing the name of the min value, 
then the name of the first variable. 
And I'll do that for the _max_, and _last_ variables too. 
And I'll include another line of dashes.
Running this code,

```java  
--------------------------
min = Charlie Brown, first=Charlie Brown
max = Robin Hood, last=Robin Hood
--------------------------
First element = Charlie Brown: [] [(333) 444-5555]
Last element = Robin Hood: [] [(564) 789-3000]
```

You can see both elements are the same, 
meaning _min_ and _first_ get the same element, 
and _max_ and _last_ also get the same element. 
In addition to _first_ and _last_ methods, 
there's also the _pollFirst_, and _pollLast_ methods.

```java  
NavigableSet<Contact> copiedSet = new TreeSet<>(fullSet);
System.out.println("First element = " + copiedSet.pollFirst());
System.out.println("Last element = " + copiedSet.pollLast());
copiedSet.forEach(System.out::println);
System.out.println("--------------------------");
```

These remove the _first_ or _last_ sorted element 
from the set, and the methods return the removed element. 
To test this, I first want to create a copy of the set, 
so I'll create a new **NavigableSet** variable, 
called copied set, setting that to a new **TreeSet** 
with the _fullSet_ passed to the constructor. 
I'll print out what I get back, 
from executing _pollFirst_, and do the same for _pollLast_. 
Then I'll print all the set elements, 
and a separator line. 
Running this code:

```java  
First element = Charlie Brown: [] [(333) 444-5555]
Last element = Robin Hood: [] [(564) 789-3000]
Daffy Duck: [daffy@google.com] []
Linus Van Pelt: [lvpelt2015@gmail.com] []
Lucy Van Pelt: [] [(564) 208-6852]
Maid Marion: [] [(123) 456-7890]
Mickey Mouse: [] [(999) 888-7777]
Minnie Mouse: [] [(456) 780-5666]
--------------------------
```
                
You can see I get the same result as 
I did with the _first_ and _last_ methods, 
but with one crucial difference.
The elements are first removed from the set, 
which you can see here, 
when I print out the elements in the copied set.
_Charlie Brown_ and _Robin Hood_ are no longer in the set. 
Ok, this feels like a good place to end this lecture. 
I've introduced you to the **TreeSet**, 
which implements both the **SortedSet** 
and the **NavigableSet**.
This set has _first_ and _last_ methods 
which retrieve the first sorted element, 
and the last sorted element. 
The _pollFirst_ and _pollLast_ methods
do the same thing, but in addition, 
they remove the element from the set. 
In the next lecture, I'll be covering 
additional methods unique to the navigable set 
as well as a few on the sorted set interface.
</div>

### TreeSet Methods
<div align="justify">

Previously, we looked at different ways 
to instantiate a **TreeSet**, 
and confirmed that the elements were ordered
when printed out. 
I covered the _first_ and _last_ methods, 
as well as the _pollFirst_ and _pollLast_ methods. 
In this section, I want to cover additional functionality unique 
to this kind of set. 
The next set of methods I'll look at, 
identify the closest match in a set, 
to the value you pass to the method. 

```java  
Contact daffy = new Contact("Daffy Duck");
Contact daisy = new Contact("Daisy Duck");
Contact snoopy = new Contact("Snoopy");
Contact archie = new Contact("Archie");
```

First, I'll set up a couple of individual contacts,
one for Daffy Duck who is a contact in my set. 
And one for Daisy Duck who isn't. 
I'll also include two others, 
who are also not in the set, 
_snoopy_ who would be last, 
if inserted in the tree set. 
And _Archie_ who would be first, 
if inserted.
Ok, so now I have all the test cases 
I need to test the navigation methods, 
_higher_, _lower_, _ceiling_ and _floor_.

```java  
for (Contact c : List.of(daffy, daisy, last, snoopy)) {
    System.out.printf("ceiling(%s)=%s%n", c.getName(), fullSet.ceiling(c));
    System.out.printf("higher(%s)=%s%n", c.getName(), fullSet.higher(c));
}
System.out.println("--------------------------");
```

I'm going to loop through a list of contacts, 
which include _daffy_, _daisy_, 
the contact I got back from the last method, 
meaning the last contact in the set, and snoopy. 
I want to execute _ceiling_ on each of those, 
and print out what comes back from that method. 
After that, I'll execute the _higher_ method, 
passing each of the contacts, and print that, 
so we can look at the results of _ceiling_ and _higher_, 
using the same contact. 
I'll end that by printing a separator line.
Running this code,

```java  
--------------------------
ceiling(Daffy Duck)=Daffy Duck: [daffy@google.com] []
higher(Daffy Duck)=Linus Van Pelt: [lvpelt2015@gmail.com] []
ceiling(Daisy Duck)=Linus Van Pelt: [lvpelt2015@gmail.com] []
higher(Daisy Duck)=Linus Van Pelt: [lvpelt2015@gmail.com] []
ceiling(Robin Hood)=Robin Hood: [] [(564) 789-3000]
higher(Robin Hood)=null
ceiling(Snoopy)=null
higher(Snoopy)=null
```
                    
You can see the results for each different argument. 
Daffy is in the **TreeSet**, 
so _ceiling_ returns _Daffy_, 
because you can think of _ceiling_ as returning the element, 
that is either greater than 
or equal to the element passed. 
But the _higher_ method never returns the value 
that's equal to it in a set, 
it always returns the next greater element, 
so I get _Linus_ there. 
But for _Daisy_, because she's not in the set at all, 
_higher_ and _ceiling_ 
both return the same result, _Linus_. 
Now look what happens if we pass the last element. 
Again, _ceiling_ will return the element 
that equals the value passed, so that's Robin Hood. 
Higher returns a null. 
With _Snoopy_, both _ceiling_ and _higher_ return null, 
because _Snoopy_ isn't in the set, 
and there aren't any elements greater than snoopy.

```java  
for (Contact c : List.of(daffy, daisy, first, archie)) {
    System.out.printf("floor(%s)=%s%n", c.getName(), fullSet.floor(c));
    System.out.printf("lower(%s)=%s%n", c.getName(), fullSet.lower(c));
}
System.out.println("--------------------------");
```

I'll repeat these tests for the _floor_ and _lower_ methods next. 
I'll copy that last for loop, 
and following separator line. 
I want to still test _daffy_ and _daisy_, 
but now I want to test first, rather than last, 
and I want to use _Archie_ instead of _snoopy_. 
Inside the loop, I'll change _ceiling_ to _floor_ in both instances, 
and on the next line, I'll change _higher_ to _lower_, 
in two instances. 
And running that,

```java  
--------------------------
floor(Daffy Duck)=Daffy Duck: [daffy@google.com] []
lower(Daffy Duck)=Charlie Brown: [] [(333) 444-5555]
floor(Daisy Duck)=Daffy Duck: [daffy@google.com] []
lower(Daisy Duck)=Daffy Duck: [daffy@google.com] []
floor(Charlie Brown)=Charlie Brown: [] [(333) 444-5555]
lower(Charlie Brown)=null
floor(Archie)=null
lower(Archie)=null
```
            
You can see that _floor_ is similar to _ceiling_. 
It returns the element that's equal to the argument passed, 
if that element's in the set.
Here, I get _Daffy_ back for the first call to _floor_,
but the _lower_ method returns the element just lower 
than the element passed, so I get _Charlie Brown_. 
I get the same results, when I run the methods 
for _Daisy Duck_, getting _Daffy Duck_ back in both cases. 
Daffy's the element that's just lower 
than _Daisy_ would be if she were in the set. 
Notice that when I pass in the first element (_Charlie Brown_), 
_floor_ gives that element back, 
but I get _null_ from the _lower_ method. 
When I pass _Archie_, I get _null_ back 
from both methods, because there is no element less 
than _Archie_ in the set. 
Let me show you these methods on a table.

<table>
    <th rowspan="3"> Element passed as the argument </th>
    <th colspan="4" align="center"> Result From Methods</th>
    <tr></tr>
    <th align="center"> floor(E) (&le;) </th>
    <th align="center"> lower(E) (&lt;)</th>
    <th align="center"> ceiling(E) (&ge;)</th>
    <th align="center"> higher(E) (&gt;)</th>
    <tr> In Set 
        <td>Matched Element</td>
        <td>Next Element &lt; Element <br> or null if none found</td>
        <td>Matched Element</td>
        <td>Next Element &gt; Element <br> or null if none found</td>
    </tr>
    <tr> Not in Set 
        <td>Next Element &lt; Element</td>
        <td>Next Element &lt; Element <br> or null if none found</td>
        <td>Next Element &gt; Element</td>
        <td>Next Element &gt; Element <br> or null if none found</td>
    </tr>
</table>

All the methods shown on this table take an element as an argument, 
and return an element in the set, the closest match to the element passed. 
_Lower_ returns an element immediately from the set 
that is less than the method argument, or _null_ if the argument 
is the minimum element in the set. 
_Floor_ returns elements respectively less than, 
or less than or _equal_, to the method argument. 
_Higher_ returns an element that is immediately greater 
than the method argument in the set, or _null_ 
if the argument is the maximum element in the set. 
_Ceiling_ returns an element in the set 
that is greater than or equal to the method argument. 
The _lower_ and _higher_ methods will never return 
the same element as the method argument. 
Ok, so I've shown you the closest matches methods. 
I want you to think back to some code in some of the challenges 
we've done in this section, and how you might use methods like this. 
Perhaps, in the card game, knowing what the next card in a sorted hand would be, 
in a player's hand, would make it easier 
to look for straights and flushes, as an example.

```java  
NavigableSet<Contact> descendingSet = fullSet.descendingSet();
descendingSet.forEach(System.out::println);
System.out.println("--------------------------");
```

Now, let's look at a few other methods 
that return different sets. 
All of these sets are backed by the original set used to create them. 
First, I can get a descending set,
by a method of that name on the **TreeSet**. 
I'll set up a local variable, setting it 
to the result of calling _descendingSet_, on _fullSet_. 
I'll use for each, to print all the elements, 
and include a separator line. 
Running that code,

```java  
--------------------------
Robin Hood: [] [(564) 789-3000]
Minnie Mouse: [] [(456) 780-5666]
Mickey Mouse: [] [(999) 888-7777]
Maid Marion: [] [(123) 456-7890]
Lucy Van Pelt: [] [(564) 208-6852]
Linus Van Pelt: [lvpelt2015@gmail.com] []
Daffy Duck: [daffy@google.com] []
Charlie Brown: [] [(333) 444-5555]
```
            
You can see the set printed out in descending sorted order. 
The set that's returned is backed by the original set, 
so any changes to the _fullSet_ will be reflected in this set, 
and vice versa. 
Let's confirm that by making a change to this descending set.

```java  
Contact lastContact = descendingSet.pollLast();
System.out.println("Removed " + lastContact);
descendingSet.forEach(System.out::println);
System.out.println("--------------------------");
fullSet.forEach(System.out::println);
System.out.println("--------------------------");
```

I'll use _pollLast_ on the _descendingSet_ 
and assign it to a local variable, 
a **Contact** named _lastContact_. 
I'll print out the element that got removed, the _lastContact_. 
Then I'll print out the _descendingSet_, and a separator line.
After that, I'll print the original set, _fullSet_, 
and another separator line. 
Running this,

```java  
--------------------------
Removed Charlie Brown: [] [(333) 444-5555]
Robin Hood: [] [(564) 789-3000]
Minnie Mouse: [] [(456) 780-5666]
Mickey Mouse: [] [(999) 888-7777]
Maid Marion: [] [(123) 456-7890]
Lucy Van Pelt: [] [(564) 208-6852]
Linus Van Pelt: [lvpelt2015@gmail.com] []
Daffy Duck: [daffy@google.com] []
--------------------------
Daffy Duck: [daffy@google.com] []
Linus Van Pelt: [lvpelt2015@gmail.com] []
Lucy Van Pelt: [] [(564) 208-6852]
Maid Marion: [] [(123) 456-7890]
Mickey Mouse: [] [(999) 888-7777]
Minnie Mouse: [] [(456) 780-5666]
Robin Hood: [] [(564) 789-3000]
--------------------------
```
            
You can see that _Charlie Brown_ was removed 
and returned from the _pollLast_ method. 
After that, the descending list is printed, 
and now it goes from _Robin Hood_ to _Daffy Duck_, 
_Charlie_ isn't listed. 
The output from printing the _fullSet_, 
which is still ordered alphabetically, 
doesn't have _Charlie Brown_ either. 
Whatever you do to the _descendingSet_ 
will be reflected in the set that backs it, 
in my case the _fullSet_. 
We can also get subsets from the head, 
or beginning, of the sorted set, 
or the tail or end of the sorted set.

```java  
Contact marion = new Contact("Maid Marion");
var headSet = fullSet.headSet(marion);
headSet.forEach(System.out::println);
System.out.println("--------------------------");

var tailSet = fullSet.tailSet(marion);
tailSet.forEach(System.out::println);
System.out.println("--------------------------");
```

To test this, I'll create another contact variable, 
named _Marion_, and assign that a new instance of a Contact,
with the name of _Maid Marion_. 
These methods take an element of the type in the set, 
so I'll pass the _Marion_ contact 
to the _headset_ method on _fullSet_. 
I'll return the result to a local variable named _headSet_, 
I'm just using var here for simplicity. 
I'll call the _forEach_ method on that subset. 
I'll include a separator line after that. 
Now, I want to copy and paste those last three statements. 
I'll change _headSet_ to _tailSet_ in both cases on the first statement. 
Again on the second statement, 
I'll change _headset_ to _tailSet_. 
Running this code,

```java  
--------------------------
Daffy Duck: [daffy@google.com] []
Linus Van Pelt: [lvpelt2015@gmail.com] []
Lucy Van Pelt: [] [(564) 208-6852]
--------------------------
Maid Marion: [] [(123) 456-7890]
Mickey Mouse: [] [(999) 888-7777]
Minnie Mouse: [] [(456) 780-5666]
Robin Hood: [] [(564) 789-3000]
--------------------------
```

You can see the results of these two methods. 
The first, the _headSet_, 
when passed the _Maid Marion_ contact, 
returned all the elements less than _Maid Marion_. 
The second the _tailSet_, when passed the same contact, 
returned all the elements after _Maid Marion_, 
but also included the _maid Marion_ record. 
_headSet_ is exclusive by default, 
meaning it will exclude the element passed. 
But _tailSet_ is inclusive by default, 
meaning it will include the element 
I can change the default behavior, 
by using the overloaded versions, 
and passing a boolean value to each, 
to determine if the element passed, should be included.

```java  
Contact marion = new Contact("Maid Marion");
//var headSet = fullSet.headSet(marion);
var headSet = fullSet.headSet(marion, true);
headSet.forEach(System.out::println);
System.out.println("--------------------------");

//var tailSet = fullSet.tailSet(marion);
var tailSet = fullSet.tailSet(marion, false);
tailSet.forEach(System.out::println);
System.out.println("--------------------------");
```

I'll edit the statement that executes _headset_, 
and I'll pass true as a second argument. 
For the _tailSet_ statement, I'll pass false 
as the second argument. 
And rerunning that:

```java  
--------------------------
Daffy Duck: [daffy@google.com] []
Linus Van Pelt: [lvpelt2015@gmail.com] []
Lucy Van Pelt: [] [(564) 208-6852]
Maid Marion: [] [(123) 456-7890]
--------------------------
Mickey Mouse: [] [(999) 888-7777]
Minnie Mouse: [] [(456) 780-5666]
Robin Hood: [] [(564) 789-3000]
--------------------------
```

I've now got _Maid Marion_ in the _headset_, 
but she's no longer in the _tailSet_, 
so these second arguments can override the default behavior. 
You can chain these methods together 
to get a subset in the middle, 
but it's far easier to just use the subSet method on **NavigableSet**, 
which I'll do next.

```java  
Contact linus = new Contact("Linus Van Pelt");
var subset = fullSet.subSet(linus, marion);
subset.forEach(System.out::println);
```

I'll create a new contact named _Linus_, 
with a name of _Linus Van Pelt_. 
And I'll execute the subset method on my full set, 
passing first _Linus_, and second _Marion_. 
I'll assign that to a variable, using var as my type, 
and _subset_ as the name. 
I'll call the _forEach_ method to print each element in the _subset_. 
And running that:

```java  
Linus Van Pelt: [lvpelt2015@gmail.com] []
Lucy Van Pelt: [] [(564) 208-6852]
```

Now I just get _Linus_ and _Lucy_, 
so this _subSet_ method includes the first contact, 
_Linus_, and excludes the second contact, _Marion_. 
Like _headSet_ and _tailSet_, there's an overloaded version, 
so I can specify the inclusive flag for both elements passed. 

```java  
Contact linus = new Contact("Linus Van Pelt");
//var subset = fullSet.subSet(linus, marion);
var subset = fullSet.subSet(linus, false, marion, true);
subset.forEach(System.out::println);
```

I'll add false as the second argument, which should exclude _Linus_, 
and true in the fourth argument which should include _Marion_ in the _subset_. 
And running the code again:

```java  
Lucy Van Pelt: [] [(564) 208-6852]
Maid Marion: [] [(123) 456-7890]
```
            
I've now excluded _Linus_ this time, 
but I've got _Maid Marion_. 
Let me show you these methods on a table, and review them.

| sub set methods                                                                                                        | Inclusive                                                                          | Description                                                                                                |
|------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| headSet(E toElement)<br/> headSet(E toElement, boolean inclusive                                                       | toElement is exclusive if not specified                                            | returns all elements less than the passed toElement (unless inclusive is specifically included).           |
| tailSet(E fromElement)<br/> tailSet(E toElement, boolean inclusive                                                     | fromElement is inclusive if not specified                                          | returns all elements greater than or equal to the fromElement (unless inclusive is specifically included). |
| subSet(E fromElement, E toElement)<br/> subSet(E fromElement, boolean frominclusive, E toElement, boolean toInclusive) | fromElement is inclusive if not specified, toElement is exclusive if not specified | returns elements greater than or equal to the fromElement and less than toElement.                         |

All three methods, _headSet_, _tailSet_ and _subSet_ 
return a subset of elements, backed by the original set. 
The _headSet_ method will return all elements at the head 
(or the elements that are less than) the argument passed to it. 
It won't include the element passed unless you use the overloaded version, 
as true as your second argument. 
The _tailSet_ method will return all elements from the passed element to the tail 
(or all elements greater than or equal) 
to the argument passed. 
It is inclusive of the element passed if it's in the set.
The _subset_ takes two arguments, a _fromElement_ and a _toElement_, 
and returns a subset that includes the _fromElement_, 
and all records less than the _toElement_. 
This method also has an overloaded version to specify 
different inclusion flags, then the default.

When would you use a TreeSet?
The _TreeSet_ does offer many advantages in terms of built-in functionality 
over the other two Set implementations, 
but it does come at a higher cost.
If your number of elements is not large, 
or you want a collection that's sorted, 
and continuously re-sorted as you add and remove elements, 
and that shouldn't contain duplicate elements, 
the _TreeSet_ is a good alternative to the ArrayList.
</div>

## [i. TreeSet Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/Course16_TreeSetChallenge/README.md#treeset-challenge)
<div align="justify">

In this challenge, you'll be creating a **Theatre** class 
that has a set of _seats_. 
The **Seat** class should be a nested class on the **Theatre** class. 
A Seat should be constructed with a row character and an integer, 
that represents the _seat_ number within the row. 
Each **Seat** should have a string, a seat number, 
in the format _A005_, where **A** is the row number, 
and **005** is the seat number within the row, 
It should be zero padded up to three digits. 
**Seat** should also have a field, 
a _boolean_, indicating 
if the seat is reserved or not.

The **theatre** class should have three fields, 
theatre name, an integer for seats in a row, 
how many seats are in a single row in other words, 
and a field for the seats themselves. 
This last field should be a **TreeSet**. 
A Theatre instance should be constructed with the theatre name, 
the number of rows in the theatre, 
and the number of total seats in the theatre. 
For simplicity, assume there are a uniform number of seats in every row, 
and the number of rows should never exceed _26_, 
so the rows will be labeled _A_ through _Z_.

You should create the seats, and number them, 
as part of the initialization of a **theatre** class. 
The **theatre** class should also have a _printSeatMap_ method 
that prints each seat, 
with each row printed on a separate line. 
You should allow a booking agent to reserve a single seat, 
and the printed seat map should show which seats are reserved. 
If you want an extra challenge, create a second method on theatre, 
that lets an agent specify.

* the number of reservations being requested.
* a range of rows (from _A_ to _C_ for example, for front row seats).
* a range of seats (a number greater than or equal to one, and less than 
or equal to the rows per seat).

For example, if there are 10 seats in each row, 
you could assume the aisle seats are 1 and 9. 
Maybe your buyer isn't interested in an aisle seat, 
so they should be able to specify two through nine 
as the range of seat numbers. 
The seats that get reserved 
should be contiguous within a row.

The bonus is to create a second method on the **Theatre** class, 
that lets an agent reserve a number of seats, contiguous within a row. 
The parameters should be:

* the number of reservations being requested.
* a range of rows (from _A_ to _C,_ for example, for front row seats).
* a range of seats (a number greater than or equal to one, 
and less than or equal to the rows per seat).
</div>

## [j. Map Interface]()
<div align="justify">

I'm going to leave the collection side of the java **collections** framework for now, 
and look at the **Map** interface, and the Java classes that implement it. 
The **map** interface is part of the **collections** framework, 
even though it doesn't derive from, or implement, the **Collection** interface.

![image21](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image20.png?raw=true)

From this diagram above, you can obviously see the Map is out here on its own.
A map in the **collections** framework
is another data structure. 
Although it's still a grouping of elements, it's different because elements are stored
with keyed references.

| Collection Interface                        | Map Interface       |
|---------------------------------------------|---------------------|
| interface Collection<E> extends Iterable<E> | interface Map<K, V> |

This means a **Map** requires two type arguments, as you can see above, 
where I'm showing the root interface, **Collection**, compared to the **Map** interface. 
The **Map** has _K_ for its _key_ type, and _V_ for the _value_ type. 
As with any generic classes, the only restriction on these types is, 
they must be reference types, and not primitives. 
The map interface replaces the now obsolete dictionary abstract class. 
And like the class that it replaces, it maps one key to one value. 
A language dictionary is a classic example of a map, 
with the keys being the words in the dictionaries, 
and the values would be the definitions of the words. 
Now unfortunately, the analogy falls down a bit with the English language. 
And the reason for that is, many English words have the same meanings. 
So the word _put_, for example, has four definitions, 2 as a verb, and 2 as a noun. 
A Java **Map** can't contain duplicate keys, so I couldn't have four keys, 
all named _put,_ in my map. 
Each key can only map to a single value, 
so I couldn't reference four different string descriptions 
for the key _put_, without aggregating the descriptions into a collection of some sort. 
In the next few lectures, I'll be looking at 3 of the Java classes that implement the map interface, 
the **HashMap,** the **LinkedHashMap,** and the **TreeMap.** 
The **HashMap** is unordered, the **LinkedHashMap** is ordered by insertion order, 
and the **TreeMap** is a sorted map. 
I want to continue with the contact merge example I used in the **Set** discussion.
Remember this has a **Contact** class that represents either a phone or email contact, or both. 
A **Map** is a much easier data structure to work with if you're doing updates to a keyed item.

```java  
public class MapMain {

    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
        System.out.println("-----------------------------");
    }
}
```

I'll create a new Class, called **MapMain**, 
and I'll create a _main_ method in that. 
First, I want to get a list of phone and email contacts, creating a combined list. 
I've done this before, soI'll set this up real quickly. 
I'm going to have two lists, one for phones, 
getting that from get data on the **ContactData** class, 
first passing phone for the type. 
For the _emails_ list, I'll do the same thing, but pass the type email. 
I'll create a full list, assigning that a new instance of an ArrayList, 
and passing that _phones_. 
Next I'll add the _emails_ list. 
And I'll print those elements out with a separator line.

```java  
Charlie Brown: [] [(333) 444-5555]
Maid Marion: [] [(123) 456-7890]
Mickey Mouse: [] [(999) 888-7777]
Mickey Mouse: [] [(124) 748-9758]
Minnie Mouse: [] [(456) 780-5666]
Robin Hood: [] [(564) 789-3000]
Robin Hood: [] [(789) 902-8222]
Lucy Van Pelt: [] [(564) 208-6852]
Mickey Mouse: [] [(999) 888-7777]
Mickey Mouse: [mckmouse@gmail.com] []
Mickey Mouse: [micky1@aws.com] []
Minnie Mouse: [minnie@verizon.net] []
Robin Hood: [rhood@gmail.com] []
Linus Van Pelt: [lvpelt2015@gmail.com] []
Daffy Duck: [daffy@google.com] []
-----------------------------
```
                    
And I get a list, with duplicate records, 
one for each phone number and email record, 
each record in other words. 
Records in a list are by default in insertion order, 
and include all duplicates.
Now, I want to create my first map, 
and I'm going to start with a hash map. 
I have to specify two type arguments when I do this, 
the first type is the type of the key, 
and the second is the type of the value, or collection element.

```java  
Map<String, Contact> contacts = new HashMap<>();

for (Contact contact : fullList) {
    contacts.put(contact.getName(), contact);
}
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

I'll use Map as my reference type, 
and my key is going to be the contact name so string, 
and the value is going to be a **Contact** record. 
I'll assign a new instance of the **HashMap** to that, 
with nothing passed to the constructor.
Unlike the classes with **Collection** at their root, 
**Map** implementations don't have an _addAll_ method, 
and I can't simply pass collection types to the constructor, 
only other map types. 
To add my Contacts, I'm going to loop through the full list, 
and use the _put_ method on my hash map. 
The _put_ method takes a _key_ and a _value_, 
and inserts what's called an Entry into the map. 
I'll pass the name, using get name on **Contact**, as the _key_, 
and my current contact as the _value_. 
Now I want to print out my elements in the map. 
There are multiple ways to do that, but I'll start with the for each method
on the map itself, on contacts, and pass it a lambda expression. 
This lambda expression requires two arguments, one for the _key_, 
and one for the _value_, and I'll call those _k_ and _v_ for simplicity,
and to save a little space. 
My expression will just print the _key_ and the _value_ here. 
Running this class's main method:

```java  
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] []
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] []
key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com] []
```
                    
You can see the _keys_ are the contact names, 
and the _values_ are the contact records.
These elements aren't in any particular order. 
Also notice that there's no duplicates of either _key_ or _value_. 
Keys must be unique, but also, I want you to see 
that _Mickey_'s contact has an email,
and not a phone number. 
Why is that, when the ArrayList, fullList, listed _Mickey_ records 
with the phone numbers first? 
Wouldn't this mean that the first _Mickey_ record, 
with phone number 999, 888, 7777, would have been added first, 
and other records ignored? 
You'll remember that when we called add, on a set, it would return true or false,
false if the element wasn't added, because it was already in the set.
The map's _put_ method is different from the set's _add_ method, 
because the _put_ method always puts the element in the map. 
If the key is not in the map, the key and value are added. 
If the key is in the map, the value is replaced, 
and the previous value is returned from the put method. 
For a map, this means the last element in your list, 
is the one that ends up in your map, which is what happened here. 
There are other methods for adding elements to the map, 
and I'll discuss each of these in turn in a bit.

```java  
System.out.println("-----------------------------");
System.out.println(contacts.get("Charlie Brown"));
System.out.println(contacts.get("Chuck Brown"));
```

But before that, something about a map that makes it so nice is 
I can simply use the key value to look up my contact. 
I'll do that next, so first I'll print a separation line. 
Then I'll print the result of the get method, 
and for a map, I pass the key to that, 
so I'll pass the string Charlie Brown. 
Running that:

```java  
-----------------------------
Charlie Brown: [] [(333) 444-5555]
```
                        
You can see the get method successfully retrieved 
my _Charlie Brown_ contact record. 
If _Charlie Brown_ is not a key in the map, 
the get method would return a null. 
JDK8 introduced a method called _getOrDefault_, 
which will replace that _null_ value with a default value. 
Let's look at that for a moment.
First, I'll use the get method and pass it _Chuck Brown_, 
passing that directly to a **System.out.println** method. 
And you can see when I run that:

```java  
null
```
                        
It just prints a null. 
There may be some cases where you're chaining methods, for example, 
where you really don't want a null back, 
because it will cause a null reference exception to occur.

```java  
Contact defaultContact = new Contact("Chuck Brown");
System.out.println(contacts.getOrDefault("Chuck Brown", defaultContact));
```

I'll create a default contact for _Chuck Brown_, 
by just passing his name to the Contact constructor. 
Here, I'll use the _get_ or _default_ method, 
with the _Chuck Brown_ key, but also pass it the defaultContact. 
If I run that code:

```java  
Chuck Brown: [] []
```
                    
You can see that gives me a Contact record back 
for _Chuck Brown_ with no other data. 
It's important to remember, though that _Chuck Brown_ never gets added to the map, 
that contact is just there, the defaultContact, as a convenience.

```java  
System.out.println("-----------------------------");
contacts.clear();
for (Contact contact : fullList) {
    Contact duplicate = contacts.put(contact.getName(), contact);
    if (duplicate != null) {
        System.out.println("duplicate = " + duplicate);
        System.out.println("current = " + contact);
    }
}
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

The _put_ method will also return a _null_ 
if the element isn't found in the map, 
or the element if it is. 
I'll add another separator line, and now, 
I'll clear the _contacts_ map of elements. 
I'll again loop through the contacts. 
This time I'll assign the outcome of the _put_ method to a local variable. 
If that's not _null_, I'll print the value that came back, 
as well as the current value, which is the value 
that always gets put in the map. 
In this code, I can recognize a duplicate if something other 
than null is returned from the put method. 
Running this code:

```java  
-----------------------------
duplicate = Mickey Mouse: [] [(999) 888-7777]
current = Mickey Mouse: [] [(124) 748-9758]
duplicate = Robin Hood: [] [(564) 789-3000]
current = Robin Hood: [] [(789) 902-8222]
duplicate = Mickey Mouse: [] [(124) 748-9758]
current = Mickey Mouse: [] [(999) 888-7777]
duplicate = Mickey Mouse: [] [(999) 888-7777]
current = Mickey Mouse: [mckmouse@gmail.com] []
duplicate = Mickey Mouse: [mckmouse@gmail.com] []
current = Mickey Mouse: [micky1@aws.com] []
duplicate = Minnie Mouse: [] [(456) 780-5666]
current = Minnie Mouse: [minnie@verizon.net] []
duplicate = Robin Hood: [] [(789) 902-8222]
current = Robin Hood: [rhood@gmail.com] []
```
                    
It prints duplicate records by name, as well as the record 
that I'm iterating over, the current contact. 
I can use this bit of information to my advantage, 
especially since my goal is to merge my contacts. 
First, I'll comment out those two print statements in this loop

```java  
System.out.println("-----------------------------");
contacts.clear();
for (Contact contact : fullList) {
Contact duplicate = contacts.put(contact.getName(), contact);
    if (duplicate != null) {
        //System.out.println("duplicate = " + duplicate);
        //System.out.println("current = " + contact);
        contacts.put(contact.getName(), contact.mergeContactData(duplicate));
    }
}
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

I'll call my _mergeContactData_ method when this situation occurs, 
and then put the new contact that comes back from that, in the map. 
I'll make another call to the _put_ method on contacts,
using the same key, and then pass a call to _mergeContactData_ 
as the expression for the second argument. 
If I pass that the duplicate entry, 
I'll get a new contact with the current and duplicate data merged. 
I want to copy that _forEach_ statement that prints the whole map out, 
and paste it after this loop. 
Can you guess what the results of this will be now? 
If I run that:

```java  
-----------------------------
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
```
                    
You can see I have a distinct listing of my contacts, 
with merged emails, and phone numbers. 
You can see _Mickey Mouse_ has two distinct emails, 
and two distinct phone numbers.
Merging my contacts was quick work, using a hash map. 
Now, let's say I did not really want to merge these elements, 
but I also don't want each additional matching record to replace the initial entry either. 
In other words, I don't want to replace the value every time I do a put.

```java  
System.out.println("-----------------------------");
contacts.clear();

for (Contact contact : fullList) {
    contacts.putIfAbsent(contact.getName(), contact);
}
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

First, I'll add two statements, a separation line 
and a call to clear the map again. 
I'm going to copy that first for loop, 
and paste that at the end of this main method. 
And instead of put, I'll use another method, called _putIfAbsent_,
which is a default method on the **Map** interface. 
I'll run this real quick, then talk about 
the differences between _put_ and _putIfAbsent_.

```java  
-----------------------------
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Minnie Mouse, value= Minnie Mouse: [] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Robin Hood, value= Robin Hood: [] [(564) 789-3000]
key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
key=Mickey Mouse, value= Mickey Mouse: [] [(999) 888-7777]
```
                    
Looking at the results of my map, 
you can see that Mickey's record reflects the first record 
I had in my full list, meaning this record has the phone number 999, 888, 7777. 
The _put_ if _absent_ method won't put an updated value in the map, 
it just ignores the element, 
if it already finds something in the map for the key. 
Again, this method returns an element if one is already in the map for the key, 
but the method doesn't replace it with the current element. 
It returns null if this is the first time an entry is being added 
to the map for that key. 
And now, If I wanted to merge contacts in this case, 
I could do something similar, 
so I'll copy the statements above, and paste them below.

```java  
System.out.println("-----------------------------");
contacts.clear();

for (Contact contact : fullList) {
    Contact duplicate = contacts.putIfAbsent(contact.getName(), contact);
    if (duplicate != null) {
        contacts.put(contact.getName(), contact.mergeContactData(duplicate));
    }
}
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

In this loop, I want to assign what comes back from _put_ 
If Absent, to a local variable, and again I'll call that duplicate. 
I'll add an if statement, checking if duplicate isn't null, 
and if it isn't, I'll now make a call to put here. 
I'll put my merged contact, again merging the current contact, 
with the duplicate variable. 
And running that code:

```java  
-----------------------------
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
```
                
I again get my contacts, nicely merged. 
There's still another method, introduced in JDK8, 
that I can use, rather than doing this kind of check, 
with put or putIfAbsent. 
That's the merge method. 
It also takes a key, and a value, 
but the third parameter is a **BiFunction** interface, 
meaning it's a target for a lambda expression 
that takes two parameters, and returns a result.

```java  
System.out.println("-----------------------------");
contacts.clear();
fullList.forEach(contact -> contacts.merge(contact.getName(), contact,
    (previous, current) -> {
        System.out.println("prev: " + previous + " : current" + current);
        Contact merged = previous.mergeContactData(current);
        System.out.println("merged: " + merged);
        return merged;
        }
    ));
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

I'll print another separator line yet, and clear my contacts map. 
Now, I'll loop through my full list, but this time I'm going to do it 
with the _forEach_ method. 
And that takes a lambda expression, and in there, 
I'm just going to call merge on the _contacts_ map,
passing the current contacts name, then the current contact.
I need to follow that with another lambda expression. 
I'll leave a placeholder here for a minute. 
Finally, I'll print the elements in the _contacts_ map after this call. 
Again, this next lambda is a Function Interface, 
and in this case, my two types and result are 
all going to be the same type, Contact. 
I need to set up two parameters in my lambda,
and I'm going to call them previous and current, 
and now I'll set up a multi-line lambda, so an opening bracket. 
I want to print out the previous and the current contacts. 
I'll call merge contact data to merge the data on my two contacts. 
I'll print the merged contact and return it from this nested lambda expression. 
This looks like a lot, but bears with me a minute here.
There's a reason I'm using the forEach method, 
and not the enhanced for loop, 
which might have been a little bit easier to see. 
Running this code:

```java  
-----------------------------
prev: Mickey Mouse: [] [(999) 888-7777] : currentMickey Mouse: [] [(124) 748-9758]
merged: Mickey Mouse: [] [(124) 748-9758, (999) 888-7777]
prev: Robin Hood: [] [(564) 789-3000] : currentRobin Hood: [] [(789) 902-8222]
merged: Robin Hood: [] [(789) 902-8222, (564) 789-3000]
prev: Mickey Mouse: [] [(124) 748-9758, (999) 888-7777] : currentMickey Mouse: [] [(999) 888-7777]
merged: Mickey Mouse: [] [(124) 748-9758, (999) 888-7777]
prev: Mickey Mouse: [] [(124) 748-9758, (999) 888-7777] : currentMickey Mouse: [mckmouse@gmail.com] []
merged: Mickey Mouse: [mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
prev: Mickey Mouse: [mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777] : currentMickey Mouse: [micky1@aws.com] []
merged: Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
prev: Minnie Mouse: [] [(456) 780-5666] : currentMinnie Mouse: [minnie@verizon.net] []
merged: Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
prev: Robin Hood: [] [(789) 902-8222, (564) 789-3000] : currentRobin Hood: [rhood@gmail.com] []
merged: Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
```

You can now see the progression of the merge happening, 
with both the previous, current and merged contact printed out. 
If I just look at Mickey Mouse's progression, 
I can see that the map started out with the 999 phone contacts, 
then it merged that with the number 124, 
and it continues to merge any elements where the key already exists in the map.
Then I've got the full map printed after that, 
and all my elements are again nicely merged. 
Ok, so let's clean this code up a bit, 
and get rid of the print statements in my lambda expression. 
I want to collapse this multi-line lambda into a single line lambda expression. 
Next, I'll remove the opening bracket, and the new line.

```java  
System.out.println("-----------------------------");
contacts.clear();
fullList.forEach(contact -> contacts.merge(contact.getName(), contact,
        (previous, current) -> previous.mergeContactData(current);
    ));
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

I want to remove _Contact merged=_, and the ending _semicolon_. 
After that, I'll remove the return statement altogether, and the closing bracket.
And running this:

```java  
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
```
                    
The result is the same, but IntelliJ is telling me 
I can even make this more succinct. 
You can see that _previous.mergeContactData_ is highlighted. 
I'll hover over that, and there it says 
I can replace this entire lambda expression with a method reference, so I will.

```java  
System.out.println("-----------------------------");
contacts.clear();
fullList.forEach(contact -> contacts.merge(contact.getName(), contact,
        Contact::mergeContactData
    ));
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

This gives us the exact same results.
That's a neat little bit of code, that lets me merge a bunch of contacts from
an ArrayList, into a HashMap, with a single statement. 
Who doesn't love one statement of code, that does all the work for us.

In the last section, so far, I covered the _put_, _putIfAbsent_, 
and _merge_ methods. 
Somewhat similar to those, are the _compute_, _computeIfPresent_, 
and _computeIfAbsent_. 
Like the _merge_ method, these are default methods 
that were added to the **Map** interface with JDK 8. 
The _compute_ and _computeIfAbsent_ methods take two values 
for their _BiFunction_ as well, 
ut these represent the _key_ and the _value_, not two contacts.
Let's look at this in action.

```java  
System.out.println("-----------------------------");
for (String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
    contacts.compute(contactName, (k, v) -> new Contact(k));
}
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

This time, I don't want to clear my map. 
I'll loop through an array of names. 
One name, _Daffy Duck_, is already a contact in my list, 
but the other two aren't. 
And this method takes a _BiFunction_, 
so I've got a lambda expression that has parameters, 
_k_ for the key and _v_ for value. 
I'll just return a new Contact, using the constructor 
that only takes a name, passing that my k argument, the key in other words. 
Running this code,

```java  
-----------------------------
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Daisy Duck, value= Daisy Duck: [] []
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Daffy Duck, value= Daffy Duck: [] []
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Scrooge McDuck, value= Scrooge McDuck: [] []
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
```
                    
You can see, I've got two new contacts, _Daisy Duck_ and _Scrooge McDuck_, 
with no emails or phone numbers. 
But now, look at _Daffy Duck_, I've also erased his previous information, 
and replaced it with a brand-new contact record. 
_Compute_ is like the _put_ method in this way, 
replacing what's in the map with the result of 
the _BiFunction_, or lambda expression.
Maybe that's not what I really want to do. 
I can replace my _compute_ method with a _computeIfAbsent_ call there.

```java  
System.out.println("-----------------------------");
for (String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
    //contacts.compute(contactName, (k, v) -> new Contact(k));
    contacts.computeIfAbsent(contactName, (k, v) -> new Contact(k));
}
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

But that doesn't compile. 
The _computeIfAbsent_ method only takes a _key_ value as an argument, 
so I need to change this code once more. 
I'll just use _k_ as the parameter, with my lambda expression. 
I could keep the parentheses around that, 
but I kind of prefer it with no parentheses 
when it's a single parameter like this.

```java  
System.out.println("-----------------------------");
for (String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
    //contacts.compute(contactName, (k, v) -> new Contact(k));
    contacts.computeIfAbsent(contactName, k -> new Contact(k));
}
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

And notice, I can replace that with a method reference, 
but I don't want to do that right now. 
This code compiles now and runs it:

```java  
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Daisy Duck, value= Daisy Duck: [] []
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Scrooge McDuck, value= Scrooge McDuck: [] []
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
```
                    
You can see _Daisy_ and _Scrooge_ there hasn't been touched, 
but _Daffy Duck_ has a new record. 

```java  
System.out.println("-----------------------------");
for (String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
    contacts.computeIfPresent(contactName, (k, v) -> {
        v.addEmail("Fun Place"); return v; });
}
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

Going back to the map main method, I want to use _computeIfPresent_, 
for my same list of duck contacts. 
I'm going to say all my duck contacts, 
work at a family owned business called fun place. 
I'll copy and paste the codes.
I want to remove the statement in the for loop, 
replacing that with a call to _computeIfPresent_. 
Like the first _compute_ method, the first parameter is the _key_, 
and the next is a function, and like _compute_, it's a _bifunction_ 
that takes both the _key_ and the _value_. 
Here, I've got a multi-line lambda, 
and I'll execute _addEmail_ on the element, 
passing it _Fun Place_ as the company name, 
and I'll return the updated contact. 
Running this code,

```java  
-----------------------------
Daisy Duck now has email DDuck@funplace.com
Daffy Duck now has email DDuck@funplace.com
Scrooge McDuck now has email SMcDuck@funplace.com
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Daisy Duck, value= Daisy Duck: [DDuck@funplace.com] []
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Daffy Duck, value= Daffy Duck: [daffy@google.com, DDuck@funplace.com] []
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Scrooge McDuck, value= Scrooge McDuck: [SMcDuck@funplace.com] []
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
```
                    
You can see that now all my duck contacts had a new email added, 
but my email logic's not very good, since _Daisy_ and _Daffy_ have the same email. 
The _compute_ methods give you a lot of functionality, 
for adding elements that aren't in the map, 
replacing values already keyed, 
resetting all elements in the map to some default value, 
or executing some code on the map elements that do exist. 
Next, I want to fix the two _Ducks_ with the same email, 
using yet another default method on the **Map** interface, 
this one called _replaceAll_. 
This method is similar to the _replaceAll_ method on the **List** interface, 
except for a map, this takes a _bifunction_ that has two arguments. 
It takes the _key_ and _value_, 
and the function should return an object the same type as the value.

```java  
System.out.println("-----------------------------");
contacts.replaceAll((k, v) -> {
    String newEmail = k.replaceAll(" ", "") + "@funplace.com";
    v.replaceEmailIfExists("DDuck@funplace.com", newEmail);
    return v;
});
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

I'll start with a separator line, followed 
by calling _replaceAll_ on my _contacts_ map. 
I'll call my parameters _k_ and _v_, for _key_ and _value_, 
as usual, and start a multi-line lambda. 
I want a new email, that's made up of the contact name with spaces removed, 
then appends _@funplace.com_ to the name, 
which will make it a unique email name for my contacts. 
And then I execute the _replaceEmailIfExists_ method on contact, 
passing the old email, and the new email. 
Finally, I return that resulting contact, 
which is my lambda parameter, _v_. 
Ok, so this code isn't really very efficient, 
since I'm calling _replaceAll_ on every single entry in the map, 
when I really only want to replace the email of two contacts. 
I did want to show you, however, that you can execute this method on the entire map, 
just like the list's _replaceAll_ method. 
Running this code,

```java  
-----------------------------
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Daisy Duck, value= Daisy Duck: [DaisyDuck@funplace.com] []
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Daffy Duck, value= Daffy Duck: [daffy@google.com, DaffyDuck@funplace.com] []
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Scrooge McDuck, value= Scrooge McDuck: [SMcDuck@funplace.com] []
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
```
                
You can see that _Daisy_ and _Daffy_'s emails have been updated, 
but all other contact emails are untouched. 
In addition to _replaceAll_, I can replace just a single element 
in the map, by either matching on _key_ alone, or both _key_ and _value_.
Let's say I have a contact where the name is _Daisy Jane Duck_, 
with an email of _daisyj@duck.com_.

```java  
System.out.println("-----------------------------");
Contact daisy = new Contact("Daisy Jane Duck", "daisyj@duck.com");

Contact replacedContact = contacts.replace("Daisy Duck", daisy);
System.out.println("daisy = " + daisy);
System.out.println("replacedContact = " + replacedContact);
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

I'll set up that record here. 
I'll create a new contact, _daisy_, 
and pass that _Daisy Jane Duck_ as the name, 
and _daisyj@duck.com_ as the email. 
Now, I want to add this contact to my map, for _Daisy Duck_, 
ignoring any middle name or initial. 
I'll call _replace_ on my _contacts_ map, passing the _key_, _Daisy Duck_, 
and my new contact _daisy_. 
I'll assign the result to a variable called _replacedContact_. 
And I'll print _Daisy_. 
Then the _replacedContact_. 
Finally, all the key value pairs in my map. 
Running that code,

```java  
-----------------------------
daisy = Daisy Jane Duck: [daisyj@duck.com] []
replacedContact = Daisy Duck: [DaisyDuck@funplace.com] []
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Daisy Duck, value= Daisy Jane Duck: [daisyj@duck.com] []
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Daffy Duck, value= Daffy Duck: [daffy@google.com, DaffyDuck@funplace.com] []
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Scrooge McDuck, value= Scrooge McDuck: [SMcDuck@funplace.com] []
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
```
                    
You can see the new _daisy_ contact, 
and the _replacedContact_ was the one that was in the map already. 
But this code replaced that in the map, with the new daisy, 
with the name _Daisy Jane Duck_. 
There's no rule that says the contact name has to match the _key_, and right now, 
they don't for this record. 
The _replace_ method has an overloaded version, which lets you specify 
that you only want to replace the value in the map if both the _keys_ and _values_ match.

```java  
System.out.println("-----------------------------");
Contact updatedDaisy = replacedContact.mergeContactData(daisy);
System.out.println("updatedDaisy = " + updatedDaisy);
boolean success = contacts.replace("Daisy Duck", replacedContact, updatedDaisy);
if (success) {
    System.out.println("Successfully replaced element");
} else {
    System.out.printf("Did not match on both key: %s and value: %s %n"
             .formatted("Daisy Duck", replacedContact));
}
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

To set this up, I'll add a separator line, 
then I'm going to merge my two daisy contacts, 
_daisy_ and _replacedContact_, assigning the result to a variable, _updatedDaisy_. 
Then I'll print _updatedDaisy_. 
Next, I want a boolean flag called _success_, 
and I'll assign that the result of calling _contacts.replace_, 
with _Daisy Duck_ still as my _key_, and _replacedContact_ as the second argument, 
and _updatedDaisy_ as the third. 
I'll add an if then else statement, based on that _success_ variable.
If _success_ is true, meaning the _replace_ was successful, 
I'll print that the code successfully _replaced_ element. 
Otherwise, I'll print that the code did not match both _key_ and _value_, 
and print those both out. 
I'll end by printing out the contacts in my map. 
Running that code:

```java  
-----------------------------
updatedDaisy = Daisy Duck: [daisyj@duck.com, DaisyDuck@funplace.com] []
Did not match on both keys: Daisy Duck and value: Daisy Duck: [DaisyDuck@funplace.com] []
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Daisy Duck, value= Daisy Jane Duck: [daisyj@duck.com] []
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Daffy Duck, value= Daffy Duck: [daffy@google.com, DaffyDuck@funplace.com] []
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Scrooge McDuck, value= Scrooge McDuck: [SMcDuck@funplace.com] []
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
```
                    
You can see the merged contact, _updatedDaisy_, with the name _Daisy Duck_, 
but with two emails. 
You can see that the code to replace Daisy, 
testing the key _Daisy Duck_ and the record that's in _replacedContact_, wasn't successful. 
Remember that a contact is considered equal if they have the same name. 
Our map's daisy record now has _Daisy Jane Duck_ as the name, 
and that doesn't match the name of the _replacedContact_, 
which was the original record. 
You can see that when I printed out the map, the _key_ says _Daisy Duck_, 
but the _value_ shows _Daisy Jane Duck_. 

```java  
System.out.println("-----------------------------");
Contact updatedDaisy = replacedContact.mergeContactData(daisy);
System.out.println("updatedDaisy = " + updatedDaisy);
//boolean success = contacts.replace("Daisy Duck", replacedContact, updatedDaisy);
boolean success = contacts.replace("Daisy Duck", daisy, updatedDaisy);
if (success) {
    System.out.println("Successfully replaced element");
} else {
    System.out.printf("Did not match on both key: %s and value: %s %n"
             .formatted("Daisy Duck", replacedContact));
}
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

I'll change my arguments, swapping _replacedContact_ with just _daisy_, 
the contact that has _Daisy Jane_. 
Running that code,

```java  
-----------------------------
updatedDaisy = Daisy Duck: [daisyj@duck.com, DaisyDuck@funplace.com] []
Successfully replaced element
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Daisy Duck, value= Daisy Duck: [daisyj@duck.com, DaisyDuck@funplace.com] []
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Daffy Duck, value= Daffy Duck: [daffy@google.com, DaffyDuck@funplace.com] []
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Scrooge McDuck, value= Scrooge McDuck: [SMcDuck@funplace.com] []
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
```

I see that this _successfully replaced element_. 
And looking at the data in the _Daisy Duck_ record, 
I am back to just _Daisy Duck_ as the name, and I have two emails, 
so this is the merged contact that replaced the previous record. 
Those are the two overloaded _replace_ methods. 
Similarly, there are also two overloaded versions of the _remove_ method. 
The first _remove_ method takes a key, and returns the value that was removed, 
or _null_, if a value doesn't exist for that key.
The second _remove_ method takes both a _key_ and a _value_. 
It only removes the element from the map if the key is in the map, 
and the element to be removed equals the value passed. 
This method returns a boolean. 
Let's look at this one next.

```java  
System.out.println("-----------------------------");
success = contacts.remove("Daisy Duck", daisy);
if (success) {
    System.out.println("Successfully removed element");
} else {
    System.out.printf("Did not match on both key: %s and value: %s %n".formatted("Daisy Duck", daisy));
}
contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
```

This is going to look very similar to the code above. 
I'll print a separator line. 
I'll assign the result of the _remove_ method 
that takes two arguments to the _success_ variable. 
I'm going to pass the _key_, _Daisy Duck_ 
and the contact record, _daisy_, 
which I set up with the name _Daisy Jane Duck_. 
If that comes back true, I'll print out that the code _successfully removed the element_. 
Otherwise, I'll print out that it _did not match_, 
and print both the _key_ and _value_.
And I'll end by again printing all key value elements in the map. 
Running that code,

```java  
-----------------------------
Did not match on both key: Daisy Duck and value: Daisy Jane Duck: [daisyj@duck.com] []
key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
key=Daisy Duck, value= Daisy Duck: [daisyj@duck.com, DaisyDuck@funplace.com] []
key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
key=Daffy Duck, value= Daffy Duck: [daffy@google.com, DaffyDuck@funplace.com] []
key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
key=Scrooge McDuck, value= Scrooge McDuck: [SMcDuck@funplace.com] []
key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]
```

I get the statement that they did not match on the _key_ and the _value_. 
The _value_ in the map doesn't include _Jane_ in the name, so it doesn't match. 
Like the _replace_ method that returns a boolean, 
this version of the _remove_ method must find the _key_ in the map. 
It won't remove the element, though, unless it's considered equal 
to the element passed as the second argument.
</div>

## [k. HashMap Implementation]()
<div align="justify">

The _view_, or _view_ collection as Java calls it, 
doesn't store elements but depends on a backing collection 
that stores the data elements. 
You saw this with the _headSet_, _tailSet_ 
and _subSet_ methods on **Sets**. 
You're also very familiar now with a list backed by an array, 
a _view_ we get back, when we use the _Arrays.asList_ method,
to get an array in the form of a list. 
You'll remember when we make changes to that list, 
the changes are reflected in the underlying array, and vice versa. 
The functionality available to us on the list is limited 
to features supported by the backing storage, so for a list backed by an array, 
we can't add or remove elements as an example.

Some of you might be familiar with database views 
which hide the details of the underlying data structures, 
to make it easier for clients to use the data. 
These view collections serve a similar purpose. 
They let us manipulate the collections, without really having 
to know exact details about the storage of the data. 
In other words, we don't have to keep learning new methods 
to manipulate data. 
As long as we can get a collection view of the data, 
we can use many of the collection methods to simplify our work.

![image22](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image21.png?raw=true)

On this diagram, I'm showing you a high-level 
overview of the structure of the **HashMap** class. 
First, it's important to know 
that there's a static nested interface on the **Map** interface, 
and its name is **Entry**. 
Concrete classes, implement both the **Map** interface, 
and the **Map.Entry** interface. 
The **HashMap** implements **Map** 
and has a static nested class, **Node**, 
that implements the **Map.Entry** interface. 
The **HashMap** maintains an array of these **Nodes**, 
in a field called table, whose size is managed by Java, 
and whose indices are determined by hashing functions.
For this reason, a **HashMap** is not ordered.

![image23](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image22.png?raw=true)

I want to focus now on the three view collections we can get from the map, 
which are the _keySet_, the _entrySet_, and the _values_. 
We know a map has keys, and these can't contain duplicates. 
These keys can be retrieved as a **Set** view, 
by calling the _keySet_ method on any map object. 
Each key-value pair is stored as an instance of an _Entry_, 
and the combination of the key and value will be unique, 
because the key is unique.

A **Set** view of these entries, or nodes in the case of the **HashMap**, 
can be retrieved from the method entrySet. 
Finally, values are stored and referenced by the key, 
and values can have duplicates, 
meaning multiple keys could reference a single value. 
You can get a Collection view of these, from the values method, on a map instance. 
Let's look at these views in some code. 

```java  
public class MapViewsMain {

    public static void main(String[] args) {
        Map<String, Contact> contacts = new HashMap<>();
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));

        Set<String> keysView = contacts.keySet();
        System.out.println(keysView);
    }
}
```

I'm going to create a new map, with String as the key type, 
and **Contact** as the value type. 
I'll set that to a new **HashMap**. 
I'll follow that with a call to _getData_ on **ContactData**, 
passing it the phone type, and chain a for each to that. 
In the lambda expression, I'll make a call to the _put_ method on contacts, 
pass the contact name as the key, and the contact object, as the value. 
I'll copy that statement, pasting it, changing the type from phone to email. 
Next, I want to get a view of my keys, 
and I can do that with the _keySet_ method on a map. 
I'll create a local variable with a type of **Set**, 
and type argument String. 
I'll call it _keysView_, and assign that to _contacts.keySet_. 
After that, I'll just print that variable out. 
Since the method _keySet_ returns a set and not a map, 
I need only one type argument in the declaration, 
and that's the type of the key, a string in this case.
If I run this:

```java  
[Lucy Van Pelt, Linus Van Pelt, Minnie Mouse, Maid Marion, Charlie Brown, Robin Hood, Daffy Duck, Mickey Mouse]
```
            
You can see the keys, my contact names printed, 
in no particular order there. 
If I had used a constructor, for example, perhaps a **TreeSet**, 
I would not get a view at that point, but a copy of the keys. 
Let me do that next.

```java  
Set<String> copyOfKeys = new TreeSet<>(contacts.keySet());
System.out.println(copyOfKeys);
```

I'll create another variable, _copyOfKeys_, 
and set that to a new **TreeSet**. 
And, I'll print this variable out. 
Here, I'm doing something similar, 
except I'm passing the result of calling a _keySet_ 
to a **TreeSet** constructor. 
Running that,

```java  
[Charlie Brown, Daffy Duck, Linus Van Pelt, Lucy Van Pelt, Maid Marion, Mickey Mouse, Minnie Mouse, Robin Hood]
```
                
My keys are now in alphabetical order, as you can see.

```java  
if (contacts.containsKey("Linus Van Pelt")) {
    System.out.println("Linus and I go way back, so of course I have info");
}

keysView.remove("Daffy Duck");
System.out.println(keysView);
contacts.forEach((k, v) -> System.out.println(v));
```

I can use the _containsKey_ method on the map 
to see if the element exists by key. 
I'll call that in an if statement, and look for the key name, 
_Linus Van Pelt_. 
If contacts have a key matching that, 
I'll print that _linus_ and I go way back, 
meaning I've known him a long time. 
You can see that it's a lot easier 
than previous attempts I made, 
where I created a contact instance with a matching name, 
to try to find a match. 
What's neat though is; I can use this key set view
to remove elements from the map. 
I'll call the remove method on the keys view, 
passing it _Daffy Duck_. 
Then I'll print out the keys view. 
And I'll also print out my _contacts_ set. 
Running this code:

```java  
Linus and I go way back, so of course I have info
[Lucy Van Pelt, Linus Van Pelt, Minnie Mouse, Maid Marion, Charlie Brown, Robin Hood, Mickey Mouse]
Lucy Van Pelt: [] [(564) 208-6852]
Linus Van Pelt: [lvpelt2015@gmail.com] []
Minnie Mouse: [minnie@verizon.net] []
Maid Marion: [] [(123) 456-7890]
Charlie Brown: [] [(333) 444-5555]
Robin Hood: [rhood@gmail.com] []
Mickey Mouse: [micky1@aws.com] []
```
                
Notice that _Daffy Duck_'s not in the _keySet_, 
which should be no surprise. 
What might be a surprise though, 
is that this removed the key and value pair in the map as well, 
and the _Daffy Duck_ contact isn't printed out. 
Let me do the same thing with my copy of the _keySet_.

```java  
copyOfKeys.remove("Linus Van Pelt");
System.out.println(copyOfKeys);
contacts.forEach((k, v) -> System.out.println(v));
```

I'll copy those last three statements, 
and paste that copy directly below them, 
and I'll change _keysView_ to _copyOfKeys_. 
I'll also change the contact I want removed 
to be _Linus Van Pelt_ for this test, 
since I already removed _Daffy_ from my _contacts_ map. 
Running this code:

```java  
[Charlie Brown, Daffy Duck, Lucy Van Pelt, Maid Marion, Mickey Mouse, Minnie Mouse, Robin Hood]
Lucy Van Pelt: [] [(564) 208-6852]
Linus Van Pelt: [lvpelt2015@gmail.com] []
Minnie Mouse: [minnie@verizon.net] []
Maid Marion: [] [(123) 456-7890]
Charlie Brown: [] [(333) 444-5555]
Robin Hood: [rhood@gmail.com] []
Mickey Mouse: [micky1@aws.com] []
```
                    
It's true _Linus_ is removed from copy of Keys. 
Since this is a copy though, 
it had no effect on the original map, 
so _Linus_ is still in that map. 
If your goal is to use the key set to manage 
and manipulate the underlying map data, 
make sure you don't make the simple mistake of passing your _keySet_ 
to a new **Set**'s constructor. 
Let's say that next, I want to purge some old contacts, 
like _Lucy_, _Minnie_ and _Maid Marion_. 
I can use _removeAll_ on my _keySet_ with these three contacts, 
or alternatively I can use the _retainAll_ method, 
and name the contacts I want to keep. 
I'll go with the second approach.

```java  
keysView.retainAll(List.of("Linus Van Pelt","Charlie Brown", "Robin Hood", "Mickey Mouse"));
System.out.println(keysView);
contacts.forEach((k, v) -> System.out.println(v));
```

Again I'll copy the three statements of _keysView_, 
and paste them below. 
I'll _remove_ and _replace_ that first line,
and call _keysView.retainAll_. 
I'll pass a list of strings, so _List.of_, 
then my list of 4 records that I want to keep,
_Linus_, _Charlie Brown_, _Robin Hood_ and _Mickey_. 
This code runs and compiles successfully:

```java  
[Linus Van Pelt, Charlie Brown, Robin Hood, Mickey Mouse]
Linus Van Pelt: [lvpelt2015@gmail.com] []
Charlie Brown: [] [(333) 444-5555]
Robin Hood: [rhood@gmail.com] []
Mickey Mouse: [micky1@aws.com] []
```
                    
And I've got four names in my _keySet_, and four contacts in my map. 
Ok, so that's pretty convenient to be able to just perform functions 
on your set of keys, and have those operations be propagated to the map.

The set returned from the _keySet_ method, is backed by the map. 
This means changes to the map are reflected in the set, and vice versa. 
The set supports element removal, 
which removes the corresponding mapping from the map. 
You can use the methods _remove_, _removeAll_, _retainAll_, and _clear_ operations. 
It does not support the _add_ or _addAll_ operations. 
Let's continue with this a bit longer.

```java  
keysView.clear();
System.out.println(contacts);
```

First, I'll execute clear on the _keysView_. 
I'll print my contacts set again. 
And running this,

```java  
{}
```
                    
I hope you can see that I've cleared the _contacts_ map of elements, 
and a pair of empty brackets is printed out for my _contacts_ map. 
Now, I want to try to start adding contacts back in, using the _keysView_.

```java  
keysView.add("Daffy Duck");
System.out.println(contacts);
```

I'll add _Daffy Duck_ to the _keysView_ set, 
and I'll again print out the contacts. 
This code compiles, but now if I run it:

```java  
Exception in thread "main" java.lang.UnsupportedOperationException
```
                    
I get an exception, an _UnsupportedOperation_ Exception as I stated before, 
_add_ operations can't be used on the _keysView_. 
If you think about that, it's kind of logical 
because adding just a key is only part of the data needed. 
You don't have a way if you do this, to say what the keyed value would be. 
Let me comment those two statements.

```java  
//keysView.add("Daffy Duck");
//System.out.println(contacts);

ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));
ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
System.out.println(keysView);
```

Next, I'll add my contacts back, as I did before. 
I'll make a call to _ContactData.getData_, 
passing it the _email_ type. 
I'll chain the _forEach_ method to that, 
adding each of these contacts to my set. 
I'll repeat that, but pass the _phone_ type. 
And then I'll immediately print out the _keysView_ set, not my map. 
Running this code:

```java  
[Linus Van Pelt, Lucy Van Pelt, Minnie Mouse, Maid Marion, Robin Hood, Daffy Duck, Charlie Brown, Mickey Mouse]
```
                
You can see all my newly added contacts are in the _keysView_ set. 
I didn't have to execute the _keySet_ method again
to get the refreshed data. 
My view was refreshed automatically.
This is a pretty powerful and easy way to manipulate elements in the map.

```java  
var values = contacts.values();
values.forEach(System.out::println);
```

Now, let's consider the next view. 
I'll call _values_ on my _contacts_ map 
and assign to that to a variable, 
called values, with _var_ as the reference type. 
And I'll call _forEach_, on the result of that, 
printing out each element. 
The first thing I want you to see is that IntelliJ is telling me, 
with its hints that I'm getting back a collection of **Contacts** there. 
And running that code:

```java  
Linus Van Pelt: [lvpelt2015@gmail.com] []
Lucy Van Pelt: [] [(564) 208-6852]
Minnie Mouse: [] [(456) 780-5666]
Maid Marion: [] [(123) 456-7890]
Robin Hood: [] [(789) 902-8222]
Daffy Duck: [daffy@google.com] []
Charlie Brown: [] [(333) 444-5555]
Mickey Mouse: [] [(999) 888-7777]
```
                    
I get all my contacts printed out.

```java  
values.retainAll(ContactData.getData("email"));
System.out.println(keysView);
contacts.forEach((k, v) -> System.out.println(v));
```

Once again, I'll use the _retainAll_ method, 
but this time using it on the _values_ collection, 
passing it the _emails_ retrieved from _ContactData.getData_. 
I'll print the keys, using my _keysView_, 
and after that, I'll print my map's keys and values. 
Running that code:

```java  
[Linus Van Pelt, Minnie Mouse, Robin Hood, Daffy Duck, Mickey Mouse]
Linus Van Pelt: [lvpelt2015@gmail.com] []
Minnie Mouse: [] [(456) 780-5666]
Robin Hood: [] [(789) 902-8222]
Daffy Duck: [daffy@google.com] []
Mickey Mouse: [] [(999) 888-7777]
```
                
Notice that the _keysView_ and the map have 
the same contacts as the email contacts, 
even though the contact's attributes (phone in some cases), 
may not match those in the email list. 
I was able to change my mapped data 
by using a _values_ view, which was then also reflected in the _keys_ view. 
I'm going to add a method to the **contact** class.

```java  
public String getNameLastFirst() {
    return name.substring(name.indexOf(" ") + 1) + ", " +
            name.substring(0, name.indexOf(" "));
}
```

That will return the contact's name listed as the last name, comma, 
then first name. 
I'll add this just under the _getName_ method, 
and call it _getNameLastFirst_. 
This will return the last part of the name, 
from the last space to the end of the String, 
followed by a comma, 
then followed by the first part of the name, 
from the beginning of the string to the first space. 
What's nice about getting the values back as a collection 
is you can pass this to any other type of class that implements a **Collection**. 
That's what I want to do next.
Getting back to the MapViewsMain class,
I'll first add a separator line.

```java  
System.out.println("------------------");
List<Contact> list = new ArrayList<>(values);
list.sort(Comparator.comparing(Contact::getNameLastFirst));
list.forEach(c -> System.out.println(c.getNameLastFirst() + ": " + c));
```

I'll create a new ArrayList and pass it values. 
I'll sort the list, using my new method, with the helper methods on **Comparator** to do that. 
And I'll print out the elements in my list, including a call to the new method name, 
and then printing the contact info. 
Running this code:

```java  
------------------
Duck, Daffy: Daffy Duck: [daffy@google.com] []
Hood, Robin: Robin Hood: [] [(789) 902-8222]
Mouse, Mickey: Mickey Mouse: [] [(999) 888-7777]
Mouse, Minnie: Minnie Mouse: [] [(456) 780-5666]
Van Pelt, Linus: Linus Van Pelt: [lvpelt2015@gmail.com] []
```
                
You can see I have a nice printed list, alphabetical by last name. 
One thing I haven't demonstrated yet is adding a duplicate contact, 
but under a different key name, so let me do 
that with the first element in my list here.

```java  
System.out.println("------------------");
Contact first = list.get(0);
contacts.put(first.getNameLastFirst(), first);
values.forEach(System.out::println);
keysView.forEach(System.out::println);
```

I'll print a separator line, then set a local variable 
to the first element in my list. 
I'll call _put_ on my _contacts_ map, 
but instead of using name for the key, 
I'm going to use my new method, _getNameLastFirst_, 
and I'll pass my local variable as the value. 
Now, I'll print out the values collection view, using _forEach_. 
And after that, I'll print the keys in my _keysView_. 
Running that:

```java  
------------------
Linus Van Pelt: [lvpelt2015@gmail.com] []
Daffy Duck: [daffy@google.com] []
Minnie Mouse: [] [(456) 780-5666]
Robin Hood: [] [(789) 902-8222]
Daffy Duck: [daffy@google.com] []
Mickey Mouse: [] [(999) 888-7777]
Linus Van Pelt
Duck, Daffy
Minnie Mouse
Robin Hood
Daffy Duck
Mickey Mouse
```
                
I hope you can see there are two _Daffy Duck_ contacts, 
but the keys are unique, 
because _Duck, Daffy_ is different from _Daffy Duck_. 
Now, I can also pass my _values_ collection to a **HashSet**, 
which also takes a Collection as an argument to the constructor.

```java  
HashSet<Contact> set = new HashSet<>(values);
set.forEach(System.out::println);
if (set.size() < contacts.keySet().size()) {
    System.out.println("Duplicate Values are in my Map");
}
```

I'll set up another local variable, _set_ a **HashSet**, 
again with a type argument of **Contact**. 
I'll set that equal to a new _HashSet_, passing its values. 
Then I'll print the values in this set. 
I'll also test the size of this set 
and the size of my _contacts_ map's _keySet_. 
If the size of my set is less than the _keySet_ then 
I can assume I have duplicate values, in the _values_ collection, 
and I'll print that out. 
Running this:

```java  
Linus Van Pelt: [lvpelt2015@gmail.com] []
Robin Hood: [] [(789) 902-8222]
Mickey Mouse: [] [(999) 888-7777]
Daffy Duck: [daffy@google.com] []
Minnie Mouse: [] [(456) 780-5666]
Duplicate Values are in my Map
```

I have five elements printed now, from my set, 
and I see that my statement got printed, 
Duplicate values are in my map. 
Ok, so I've covered two of the three view collections. 
The final view collection is the entry set. 
I showed you on the earlier, 
that key value pairs are stored in instances that implement _Map.Entry_. 
In the **HashMap**, this is the **Node**. 
I can get a set of these **Nodes** and examine them. 
I'll do this here, to look for keys that aren't in sync with their contact name.

```java  
var nodeSet = contacts.entrySet();
for (var node : nodeSet) {
    if (!node.getKey().equals(node.getValue().getName())) {
        System.out.println("Key doesn't match name: " + node.getKey() + ": " + node.getValue());
    }
}
```

I'll set a local variable, using type inference, so _var_, 
and call that _nodeSet_, making that equal to _contacts.entrySet_.
I'll use an enhanced for loop, to loop through what I get back. 
Again, I'll just use var for the looped element, 
and call that _node_. 
On any _node_, or _Map.Entry_ instance, 
there's a _getKey_ and a _getValue_ method, 
which returns the key value pair of this node.
I'll compare the key with the value's name field next.
If they're not equal, I'll print the key does not match the name,
and then print both the key and the value. 
If you have IntelliJ's hints turned on, you'll see 
that **nodeSet**'s type is a **Set** of _Map.Entry_, 
and the key type is String, the value type is **Contact**. 
I'll run this code:

```java  
java.util.HashMap$EntrySet
java.util.HashMap$EntrySet
java.util.HashMap$Node
Key doesn't match name: Duck, Daffy: Daffy Duck: [daffy@google.com] []
```
                    
And the result confirms what I already knew, that I have a key value pair, 
where the key doesn't match up with the contact name.
I'll change this code for my more curious students, 
and add two statements to print out the class name,
first right after the nodeSet. 

```java  
var nodeSet = contacts.entrySet();
for (var node : nodeSet) {
    System.out.println(nodeSet.getClass().getName());
    if (!node.getKey().equals(node.getValue().getName())) {
        System.out.println(node.getClass().getName());
        System.out.println("Key doesn't match name: " + node.getKey() + ": " + node.getValue());
    }
}
```

Then, I'll also include that for the node in the if statement. Running that code,

```java  
java.util.HashMap$EntrySet
java.util.HashMap$EntrySet
java.util.HashMap$Node
Key doesn't match name: Duck, Daffy: Daffy Duck: [daffy@google.com] []
java.util.HashMap$EntrySet
java.util.HashMap$EntrySet
java.util.HashMap$EntrySet
java.util.HashMap$EntrySet
```
                
We'll see what these instances really are. 
You can see both nested types on the **HashMap** class itself. 
If I go up to my **HashMap** instantiation, and click control on that class 
that opens the source code for HashMap. 
I'll look for Class EntrySet first, using Control F. 
That will take me to the nested class declaration. 
This is an inner class, declared as final.
I'll explain why you'd want to use final as a modifier, for some classes,
in a section coming up. 
Here it prevents classes from extending this class, 
including classes that extend the **HashMap**. 
You can see this class is a **Set**, it extends an **Abstract** Set, 
and it's type is a **Map.Entry**. 
Let's look for **Node** next, 
so I'll change **EntrySet** to **Node** in the prompt up there. 
And that will take me to that class.
This one is a static class, and implements the **Map.Entry** interface. 
I'm sure I've said this before, but you can learn a lot 
if you take a little time to explore the code in Java's class libraries.
At this point, I've covered the major functionality on the **Map** interface, 
using the **HashMap** to do it. 
</div>

## [l. HashMap Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/Course21_HashMapChallengePart1/README.md#hashmap-challenge)
<div align="justify">

In this challenge, you'll be creating a text-based game, using a **HashMap**. 
This game will be loosely based on the original **Colossal Cave Adventure**, 
which was one of the first adventure games 
that came out years and years ago. 
Feel free to google that if you want to find out more information about this game.

![image24](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image23.png?raw=true)

The game starts with the user standing in a road, 
in the center of the map. 
North would take the user to the Forest and South, 
would take the user to the Valley, East would be the well house, 
and West would be the Hill. 
For simplicity, we'll just support North, West, South and East compass directions, 
so each location will only have up to at most, 
four directions to choose from. 
In other words, we won't support diagonal moves, 
such as moving Northeast, although you're welcome to code your game that way. 
You'll want to use two HashMaps in this challenge, 
one for the board locations, and one for the next places data.

This table shows one way to structure your data, 
but feel free to come up with your own configuration 
if you'd prefer to. 
Use a HashMap for the game board locations, keyed on a short descriptor,
road or stream, for example, or some other key of your choice. 
Your Location class should have a location description, 
and could also have a field, a **HashMap**, to store next places to go from there. 
The next places Map should be keyed by the compass direction, 
and its value should be the key to the next location. 
As an example, the stream location will have a next places map,
which has two entries. 
The first entry will have a key of W or West, 
and the value will be valley (or whatever key you would use, 
to get the valley data from the board locations map).

```java  
*** You're standing inside a well house for a small sprint ***
    From here, you can see:
        * A road to the West (W)
        * A lake to the North (N)
        * A stream to the South (S)
    Select Your Compass Direction (Q to quit) >>
```

Use the console to describe to the player 
what their current location is (starting at the road), 
and show them what options they have to go from here. 
Feel free to edit the descriptions 
and make them as detailed as you want. 
Prompt the player to enter the direction they want to go next. 
You can accept the whole word, east, or just the letter e. 
You can let the user know what lies ahead, 
by giving them a hint about the next place, or not. 
For example, you could tell them they could go either East or West, 
and offer no hints. 
Continue to play until the user quits, Q for quit, for example. 
I've included some starting data as a csv file (or comma-separated file), 
if you want to use that, you can cut and paste it for now, 
and set it up in a text block, for example. 
Or you can set up your own set of data from scratch. 
I'm showing a sample of what the console display might look like above. 
This is a suggestion only to help you understand how the game might work.

A bonus part of the challenge is to allow customizations 
to the board locations, as well as the next place directions.
For example, your player should be able to add custom board locations, 
to include more places, or change the descriptions 
or destinations available.
</div>

## [m. LinkedHashMap and TreeMap Interfaces]()
<div align="justify">

Like the **Set** interface, which has **LinkedHashSet** 
and **TreeSet** implementations, 
the **Map** interface has the **LinkedHashMap** 
and **TreeMap** classes. 
The **LinkedHashMap** is a key value entry collection, 
whose keys are ordered by insertion order.
The **TreeMap** is sorted by its keys,
so a key needs to implement **Comparable**, 
or be initialized, with a specified **Comparator**.
Let's take a look at these in code. 
I've created a **Main** class and _main_ method. 
I'm going to start by creating a **Student** Class.
Before I work on this, I'm going to set up two records, 
the first is for **Course**, 
this will be the courses a student will enrol in. 

```java  
record Course(String courseId, String name, String subject) {}

record Purchase (String courseId, int studentId, double price, int yr, int dayOfYear) {

    public LocalDate purchaseDate() {
        return LocalDate.ofYearDay(yr, dayOfYear);
    }

}
```

I'll include these in the **Student.java** file for simplicity. 
**Course** will have three fields, all strings, _courseId_, _name_ and _subject_. 
The second record will be for a course purchase, 
so I'll call it **Purchase**. 
I want this to have five fields. 
The first is the _courseId_, an identifier for the course 
they'll be purchasing, an integer for the _studentId_, 
and a double for the _price_ of the course. 
In addition to that, I want a _year_, and a _dayOfYear_, 
both integers. 
The _year_ will just be the year of the purchase, 
and _dayOfYear_ is an integer, which is between 1 and 365.
These two pieces of information can give us a date. 
I'll add a method to this record, called _purchaseDate_, 
it's public and will return a type, **LocalDate**, 
which I've used once or twice before. 
I'll be covering dates in great detail,
in an upcoming section, so this is a preview of things to come. 
**LocalDate** has a static method, named, of year day,
that takes an integer for a _year_, and an integer for the _dayOfYear_, 
and returns a date instance. 
I'll be using this date, a bit later, as a key in my map. 
Let's get back to the **Student** class, and set up a few fields.

```java  
public class Student {

    public static int lastId = 1;

    private String name;
    private int id;
    private List<Course> courseList;

    public Student(String name, List<Course> courseList) {
        this.name = name;
        this.courseList = courseList;
        id = lastId++;
    }

    public Student(String name, Course course) {
        this(name, new ArrayList<>(List.of(course)));
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {

        String[] courseNames = new String[courseList.size()];
        Arrays.setAll(courseNames, i -> courseList.get(i).name());
        return "[%d] : %s".formatted(id, String.join(", ", courseNames));
    }
}
```

First, I want a static int, _lastId_, 
and I'll assign that an initial value of 1.
You've seen me do this quite often, with student or employee classes, 
because I want id assigned, as part of the construction of the object. 
The _lastId_ used gets stored in this field.
Students need a _name_. 
The id field is going to get automatically assigned,
using the static last id, when a new Student is created. 
And students will have a list of courses. 
I'll set up two constructors, the first will have _name_ 
and _courseList_ as arguments. 
I'll add another statement to this constructor,
to set the id. 
These gets assigned the value in last id, my static field. 
I'll increment _lastId_ after the assignment,
so I use a post-fix increment. 
I'm going to copy this constructor, 
and paste a copy right below the first constructor.
I'll change the second argument. 
The type will be my **Course** record, 
and its name will be course in lower case. 
This is going to let me create a new student, 
with just a name and single course. 
I'll remove all the statements that are there,
and instead chain a call to the first constructor. 
I'll pass name, and a new ArrayList, 
which will include the one course passed to this constructor. 
Next, I'll create two getters, one for name, 
and one for the student's id. 
I'll include a method to add a course, 
so public, void, add **Course**, and that has one parameter, a _course_. 
And I'll simply call **courseList.add** passing it the course argument.

Lastly, I'll add a _toString_ method by generating the override. 
I'll replace that statement with a bit of code.
First, I'll set up a String array, called _courseName_, 
and it'll be the same size as the course list field. 
This is just going to house the course names. 
I'll use _Arrays.setAll_, to populate my string arrays, 
using a lambda expression, and using the index, 
to get the course, and ultimately its name, 
which goes in my local array. 
From the lambda, I'll return a formatted string that has the student id, 
and the course name list. 
Finally, I'll use join on my local string array, to create a comma-delimited 
list of course names. 
Ok, so that's enough setup, to let me simulate students
registering for my courses, for example. 
Getting back to the **Main** class,

```java  
public class Main {
    private static Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, Student> students = new TreeMap<>();

    public static void main(String[] args) {
        
    }
}
```

I'm going to set up two different maps, but both are private and static. 
The first, I'm going to declare with a reference type of just Map, 
the key will be a String, and the value is going to be a **Purchase** records. 
I'll call this map, _purchases_. 
I'll assign it a new instance of a **LinkedHashMap**, 
and no arguments for the constructor. 
For the second map, I'm going to make the reference type **NavigableMap**, 
with the key as String, and the value will be **Student**. 
I'll call this student, and make it a new **TreeMap**. 
Using **NavigableMap** as the reference type is similar 
to the way I used **NavigableSet** when I was working with a **TreeSet**. 
This means I'll have access to all the additional methods,
on both the **NavigableMap** interface, and the **SortedMap** interface. 
Next, I'll include a method on this class, called _addPurchase_.

```java  
private static void addPurchase(String name, Course course, double price) {

    Student existingStudent = students.get(name);
    if (existingStudent == null) {
        existingStudent = new Student(name, course);
        students.put(name, existingStudent);
    } else {
        existingStudent.addCourse(course);
    }

    int day = purchases.size() + 1;
    String key = course.courseId() + "_" + existingStudent.getId();
    int year = LocalDate.now().getYear();
    Purchase purchase = new Purchase(course.courseId(), existingStudent.getId(), price, year, day);
    purchases.put(key, purchase);
}
```

I'll make that static and void. 
I'll pass a _name_, the student name, a _Course_, and a **price**, 
the price the student paid for that course. 
I'll create a local variable, called _existingStudent_, 
and try to get that from my _student_ map, using the _name_. 
If that's _null_, the student's not in the map, so it's a new student. 
In this case, I'll create a new instance of a **Student** 
using the constructor with _name_ and one _course_. 
I'll put this new student in my map, with the _name_ as a key. 
If I did find the student in the _student_ map, 
I'm just going to add the course to this _existingStudent_.
This code is going to let me keep track of my students, 
and which courses they're taking. 
Next, I want to do the same for _purchases_. 
I'm going to set up two local variables, _day_ and _key_. 
For now, I'll set day equal to the number of _purchases_ I've had, plus one. 
I'll explain this in just a minute. 
The key for my purchase map is a combination of the _courseId_, 
and the student id. 
To get the current year, I can use **LocalDate**, 
and its _now_ method, which gives me the current date 
from the system clock in the default time-zone. 
I can chain the _getYear_ method to that call, 
which will give me back an integer for the current year.
Now I want a new instance of the **Purchase** record. 
I'll pass that the course id, the student id, the price, 
the current year, and my day variable, 
which is just a number from 1 to whatever the maximum number of purchases is. 
Finally, I'll put the purchase in the purchase map, using my key.
Now, I can go up to the main method.

```java  
public class Main {
    private static Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, Student> students = new TreeMap<>();

    public static void main(String[] args) {

        Course jmc = new Course("jmc101", "Java Master Class", "Java");
        Course python = new Course("pyt101", "Python Master Class", "Python");

        addPurchase("Mary Martin", jmc, 129.99);
        addPurchase("Andy Martin", jmc, 139.99);
        addPurchase("Mary Martin", python, 149.99);
        addPurchase("Joe Jones", jmc, 149.99);
        addPurchase("Bill Brown", python, 119.99);

        purchases.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("-----------------------");
        students.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
```

I'll set up a couple of courses, the first for my Java master class. 
I'll use my **Course** record, 
and I'll give this some kind course id, like jmc101, 
and that's my Java master class, and the language is going to be Java.
I'll assign that to a local variable I'll call jmc. 
I'll do the same thing for the python master class, 
creating a local variable called python. 
I want to add some purchases next. 
I'll start out with five students, 
calling the _addPurchase_ method, passing the student name, 
the course, and a price for each. 
So my first student will be Mary Martin, and jmc is the
course, and I'll just put 129.99 in there.
I'll repeat that for Andy Martin, jmc, 139.99. 
And let's say Mary's also taking python, and her price, 149 and 99. 
Now Joe Jones, jmc, and his price. 
And Bill Brown, python, and a price for that. 
My prices might be different based on special coupons and sales prices. 
I want to print my purchases, and my students now. 
I'll use the _forEach_ method on the map to do it, 
and print the key and value, of my purchases. 
And I'll do something similar, this time for my students. 
Running this code:

```html  
jmc101_1: Purchase[courseId=jmc101, studentId=1, price=129.99, yr=2023, dayOfYear=1]
jmc101_2: Purchase[courseId=jmc101, studentId=2, price=139.99, yr=2023, dayOfYear=2]
pyt101_1: Purchase[courseId=pyt101, studentId=1, price=149.99, yr=2023, dayOfYear=3]
jmc101_3: Purchase[courseId=jmc101, studentId=3, price=149.99, yr=2023, dayOfYear=4]
pyt101_4: Purchase[courseId=pyt101, studentId=4, price=119.99, yr=2023, dayOfYear=5]
-----------------------
Andy Martin: [2] : Java Master Class
Bill Brown: [4] : Python Master Class
Joe Jones: [3] : Java Master Class
Mary Martin: [1] : Java Master Class, Python Master Class
```

I've got five purchases, and four unique students. 
The purchase map's a Linked Hash Map,
which means it's ordered by insertion order. 
This is why I wanted the day of Year, 
for this first test case, to be equal to the purchase size.
This makes it a bit easier to see that 
the entries are listed in insertion order, 
which is the order of a **LinkedHashMap**. 
But my students are in alphabetical order. 
My key was student name, and that's how the students are sorted.
You can see the student id, and the courses 
being taken by each student, and right now, 
only _Mary_ is taking more than one course.
Ok, so that's the difference between 
how a **TreeMap** is sorted, 
and how a **LinkedHashMap** is ordered.
What's kind of nice about a map is that
most of the time you'll be using simpler types as keys, 
although that's not always going to be true. 
But the simpler types, like the strings 
I use for my key in my tree map here, 
have a natural order, and already implement Comparable. 
Next, I want to go to the _addPurchase_ method, on the Main class,
and change my expression for setting the day. 

```java  
private static void addPurchase(String name, Course course, double price) {

    Student existingStudent = students.get(name);
    if (existingStudent == null) {
        existingStudent = new Student(name, course);
        students.put(name, existingStudent);
    } else {
        existingStudent.addCourse(course);
    }

    //int day = purchases.size() + 1;
    int day = new Random().nextInt(1, 5);
    String key = course.courseId() + "_" + existingStudent.getId();
    int year = LocalDate.now().getYear();
    Purchase purchase = new Purchase(course.courseId(), existingStudent.getId(), price, year, day);
    purchases.put(key, purchase);
}
```

I could use the _LocalDate.now_ feature 
and get day of the year for the current year, 
but I want to mock up my data a little bit. 
I just want to limit the options for this, 
to be the first 4 days of the year. 
I'll change the expression after the assignment operator. 
This time, I'll make a call on a new random instance, 
and execute the next int on that, passing 1 through 5. 
This will return numbers 1 through 4, 
which means the possible days of the year, for this test, 
are going to be January 1 through January 4.

I'm interested in understanding my sales for each day, 
so what I'll do is, create a new map, 
a local variable here, in the main method.

```java  
public class Main {
    private static Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, Student> students = new TreeMap<>();

    public static void main(String[] args) {

        Course jmc = new Course("jmc101", "Java Master Class", "Java");
        Course python = new Course("pyt101", "Python Master Class", "Python");

        addPurchase("Mary Martin", jmc, 129.99);
        addPurchase("Andy Martin", jmc, 139.99);
        addPurchase("Mary Martin", python, 149.99);
        addPurchase("Joe Jones", jmc, 149.99);
        addPurchase("Bill Brown", python, 119.99);

        addPurchase("Chuck Cheese", python, 119.99);
        addPurchase("Davey Jones", jmc, 139.99);
        addPurchase("Eva East", python, 139.99);
        addPurchase("Fred Forker", jmc, 139.99);
        addPurchase("Greg Brady", python, 129.99);

        purchases.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("-----------------------");
        students.forEach((key, value) -> System.out.println(key + ": " + value));
        
        
        NavigableMap<LocalDate,List<Purchase>> datedPurchases = new TreeMap<>();

        for (Purchase p : purchases.values() ) {
            datedPurchases.compute(p.purchaseDate(),
                    (pdate, plist) -> {
                        List<Purchase> list = (plist == null) ? new ArrayList<>() : plist;
                        list.add(p);
                        return list;
                    });
        }
        datedPurchases.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
```

Again, I'll set this up using a **NavigableMap** reference type. 
This map should be keyed by a date, a local date, 
and the value is going to be a list of my purchases for that day. 
I'll assign that to a new instance of a **TreeMap**.
To populate this map, I'll loop through my purchase map's values, 
which are all purchases. 
I'm going to execute the _compute_ method on my new map, _datedPurchases_, 
passing it first the key, which is going to be the _purchaseDate_, 
and that's really a method on the **Purchase** record. 
I'll set up my lambda expression, using the key, 
the purchase date in other words, and the current value, 
a list, which I'll call _plist_. 
In the body block of the lambda expression,
I'll set up a local variable, a list of purchases, 
and call that simply _list_. 
I'll assign it the result of a ternary operator,
so if _plist_ is null, which it will be 
if this is the first entry for the date, 
I'll assign list a new Arraylist instance. 
If this is already in the map, _plist_ won't be _null_, 
so I'll return _plist_. 
I'll add the current purchase record to the list variable. 
Lastly, I'll return p list from this lambda, 
and that's what gets put into the map.
That's the code that sets up my map by dates, 
so next, I'll print the entries out, by each key value, or each date.
If I run that, I'll get different purchases for different days each time I run this. 
What I want you to see, though, is that my map is organized, in chronological order. 
Let me add some new students to make this more interesting.
I'll add five more students, dividing them between the python class 
and the java class, with various prices and names.
I'll set up _Chuck Cheese_, to take python. 
_Davey Jones_, jmc. 
_Eva East_, python. 
_Fred Forker_, jmc, 
and _Greg Brady_, python. 
Since I have a larger set of students, 
I'm going to change the day local variable in my _addPurchase_ method.
I'll have the random method pick one of the first 14 days, 
so I'll change 5 to 15 there. 
I'll run that:

```html  
jmc101_1: Purchase[courseId=jmc101, studentId=1, price=129.99, yr=2023, dayOfYear=5]
jmc101_2: Purchase[courseId=jmc101, studentId=2, price=139.99, yr=2023, dayOfYear=1]
pyt101_1: Purchase[courseId=pyt101, studentId=1, price=149.99, yr=2023, dayOfYear=5]
jmc101_3: Purchase[courseId=jmc101, studentId=3, price=149.99, yr=2023, dayOfYear=10]
pyt101_4: Purchase[courseId=pyt101, studentId=4, price=119.99, yr=2023, dayOfYear=7]
pyt101_5: Purchase[courseId=pyt101, studentId=5, price=119.99, yr=2023, dayOfYear=1]
jmc101_6: Purchase[courseId=jmc101, studentId=6, price=139.99, yr=2023, dayOfYear=14]
pyt101_7: Purchase[courseId=pyt101, studentId=7, price=139.99, yr=2023, dayOfYear=5]
jmc101_8: Purchase[courseId=jmc101, studentId=8, price=139.99, yr=2023, dayOfYear=11]
pyt101_9: Purchase[courseId=pyt101, studentId=9, price=129.99, yr=2023, dayOfYear=6]
-----------------------
Andy Martin: [2] : Java Master Class
Bill Brown: [4] : Python Master Class
Chuck Cheese: [5] : Python Master Class
Davey Jones: [6] : Java Master Class
Eva East: [7] : Python Master Class
Fred Forker: [8] : Java Master Class
Greg Brady: [9] : Python Master Class
Joe Jones: [3] : Java Master Class
Mary Martin: [1] : Java Master Class, Python Master Class
2023-01-01: [Purchase[courseId=jmc101, studentId=2, price=139.99, yr=2023, dayOfYear=1], Purchase[courseId=pyt101, studentId=5, price=119.99, yr=2023, dayOfYear=1]]
2023-01-05: [Purchase[courseId=jmc101, studentId=1, price=129.99, yr=2023, dayOfYear=5], Purchase[courseId=pyt101, studentId=1, price=149.99, yr=2023, dayOfYear=5], Purchase[courseId=pyt101, studentId=7, price=139.99, yr=2023, dayOfYear=5]]
2023-01-06: [Purchase[courseId=pyt101, studentId=9, price=129.99, yr=2023, dayOfYear=6]]
2023-01-07: [Purchase[courseId=pyt101, studentId=4, price=119.99, yr=2023, dayOfYear=7]]
2023-01-10: [Purchase[courseId=jmc101, studentId=3, price=149.99, yr=2023, dayOfYear=10]]
2023-01-11: [Purchase[courseId=jmc101, studentId=8, price=139.99, yr=2023, dayOfYear=11]]
2023-01-14: [Purchase[courseId=jmc101, studentId=6, price=139.99, yr=2023, dayOfYear=14]]
```
                
And that gives me 10 students, with various purchases, 
for the first two weeks of this year. 
Again, this will change each time I run it. 
In this lecture, I've shown you examples of using a **LinkedHashMap**, 
as well as a **TreeMap**. 
For the tree map, I've used a String as a key, 
but I've used **LocalDate** as a key as well. 
</div>

### TreeMap Methods
<div align="justify">

In the last section, I created two maps of data, one, 
a **LinkedHashMap** of purchases made, and the other, 
a **TreeMap** of my enrolled students. 
Because I used **LinkedHashMap**, purchases are stored
in the order they were made, which is pretty good in general. 
However, I also created a sorted tree map, 
keying on purchase date, and maintained a list of purchases 
by each distinct date. 

In this section, I'm going to use that last map 
to derive some information about my sales.
Like **TreeSet**, which had _headSet_ and _tailSet_, 
the **TreeMap** has _headMap_ and _tailMap_ methods, 
which I'll show you next. 
First, I'll get the current year, using the now method on LocalDate, 
and chaining get year to that. 

```java  
int currentYear = LocalDate.now().getYear();

LocalDate firstDay = LocalDate.ofYearDay(currentYear, 1);
LocalDate week1 = firstDay.plusDays(7);
Map<LocalDate, List<Purchase>> week1Purchases = datedPurchases.headMap(week1);
Map<LocalDate, List<Purchase>> week2Purchases = datedPurchases.tailMap(week1);

System.out.println("-----------------------");
week1Purchases.forEach((key, value) -> System.out.println(key + ": " + value));
System.out.println("-----------------------");
week2Purchases.forEach((key, value) -> System.out.println(key + ": " + value));
```

I'll create a date with the current year, and first day.
The local date class has methods that let you add date units or subtract them. 
The one to add days is called plusDays, and I can pass the number of days to add, so 7. 
I'll set up a Map, with the same type arguments as my datedPurchases map, 
and that's local date, and a List of purchases. 
I'll assign that the result from calling the _headMap_ method on purchased dates, 
with the week1 date. 
I'll do the same thing for the next map, week 2 purchases, but call _tailMap_ this time, 
again using the date that is the cut-off date for week 1.
In addition to plusDays, the local date class has such methods as _plusWeeks_, 
_plusYears_, as well as _minusDays_, _minusWeeks_ and _minusYears_, 
which make manipulating dates very easy. 
Again, there's a lot to know about date time processing, 
but for the basic examples here, I'll just use a few of these 
somewhat self-explanatory methods. 
In this code I'm using _headMap_, and like _headSet_, 
this excludes the value you pass unless you use the overloaded method,
and specify the inclusive flag to be true. 
On the other hand, the _tailMap_ includes the value you pass if it's in the map. 
I want to print these out and see what I get. 
I'll print a separator line, followed by the keys and values of my head view of the map,
which I've called week 1 purchases. 
And I want to repeat that for the week 2 purchases. 
Running that code:

```html  
-----------------------
2023-01-02: [Purchase[courseId=pyt101, studentId=4, price=119.99, yr=2023, dayOfYear=2]]
2023-01-05: [Purchase[courseId=jmc101, studentId=1, price=129.99, yr=2023, dayOfYear=5]]
2023-01-06: [Purchase[courseId=pyt101, studentId=1, price=149.99, yr=2023, dayOfYear=6], Purchase[courseId=jmc101, studentId=3, price=149.99, yr=2023, dayOfYear=6], Purchase[courseId=pyt101, studentId=5, price=119.99, yr=2023, dayOfYear=6]]
2023-01-07: [Purchase[courseId=jmc101, studentId=2, price=139.99, yr=2023, dayOfYear=7]]
-----------------------
2023-01-10: [Purchase[courseId=jmc101, studentId=6, price=139.99, yr=2023, dayOfYear=10], Purchase[courseId=pyt101, studentId=7, price=139.99, yr=2023, dayOfYear=10]]
2023-01-11: [Purchase[courseId=pyt101, studentId=9, price=129.99, yr=2023, dayOfYear=11]]
2023-01-12: [Purchase[courseId=jmc101, studentId=8, price=139.99, yr=2023, dayOfYear=12]]
```
            
I get my purchases divvied up by the first two weeks of this year. 
I'll run this a couple of times until I can confirm that January 7 is 
in the first week's map, and January 8 is in the second week.
Finally, I want to know how many of each course I sold for each week. 
I'll do this in another method on the **Main** class.

```java  
private static void displayStats(int period, Map<LocalDate, List<Purchase>> periodData) {

    System.out.println("-----------------------");
    Map<String, Integer> weeklyCounts = new TreeMap<>();
    periodData.forEach((key, value) -> {
        System.out.println(key + ": " + value);
        for (Purchase p : value) {
            weeklyCounts.merge(p.courseId(), 1, (prev, current) -> {
                return prev + current;
            });
        }
    });
    System.out.printf("Week %d Purchases = %s%n", period, weeklyCounts);
}
```

I'll make that private static and void. 
I'll call it _displayStats_, and it will take an int for the period. 
If I pass it weekly data, this will be an int from 1 to 52, 
but if I pass it monthly data, it would be 1 through 12, 
and for quarterly data, it could be 1 through 4, and so on. 
I'll also have a Map for the second parameter, again with _LocalDate_ as the key, 
and a List of Purchase records as the value, and I'll call that _periodData_. 
I'll print out some dashes to separate the data. 
I'll create a local variable, a map, that's going to keep track of course I.D.'s, 
and the number of purchases for each, 
so the types are String for the course id key, and Integer for the course counts.
I'm calling this variable _weeklyCounts_, and since I want it sorted, 
I'll make it a new tree map. 
Next, I'll loop through each entry of my period data. 
I'll first print the key and value. 
Next, I'll add the code that actually keeps track of my counts 
by course id for this period. 
I'll loop through the elements in the value variable, 
remember this is a list of purchases, so I'll loop through purchases for each date. 
I'm going to use the merge method on the map here. 
My map is keyed by the course id, which I can get from the purchase record. 
The next argument for the merge method is what the value will get set to, 
if it doesn't yet exist in the map. 
I'll set that to 1. 
The last argument is going to be a lambda expression. 
The parameters for this **BiFunction** Interface Lambda are 
what's currently in the map for the key, and the current value, in this case, 
that's 1 here. 
I'll return these two values added together, 
which should keep a running tally of purchases by course. 
I'll next print my weekly counts out, after the loop. 
I'll use _system.out.printf_, with a formatted string, passing it the period, 
and the weeklyCounts map. IntelliJ is suggesting
I change this code, where I return previous plus current to a method reference, 
but I think right now, it's a little easier to understand 
if I leave it this way.
Getting back to the _main_ method,

```java  
//System.out.println("-----------------------");
//week1Purchases.forEach((key, value) -> System.out.println(key + ": " + value));
//System.out.println("-----------------------");
//week2Purchases.forEach((key, value) -> System.out.println(key + ": " + value));

displayStats(1, week1Purchases);
displayStats(2, week2Purchases);
```

I'll comment out the code where I was printing week1 and week2 purchases.
I'll replace that with calls to my new
method for each of these maps. 
I'll call the display stats method, passing it first 1, 
and week 1 purchases.
Then I'll call it again, with 2 and week 2 purchases.
Running that, I'll see if that gives me the information I want.

```html  
-----------------------
2023-01-02: [Purchase[courseId=jmc101, studentId=8, price=139.99, yr=2023, dayOfYear=2]]
2023-01-03: [Purchase[courseId=pyt101, studentId=9, price=129.99, yr=2023, dayOfYear=3]]
2023-01-04: [Purchase[courseId=pyt101, studentId=1, price=149.99, yr=2023, dayOfYear=4]]
2023-01-05: [Purchase[courseId=jmc101, studentId=1, price=129.99, yr=2023, dayOfYear=5]]
Week 1 Purchases = {jmc101=2, pyt101=2}
-----------------------
2023-01-08: [Purchase[courseId=jmc101, studentId=3, price=149.99, yr=2023, dayOfYear=8]]
2023-01-09: [Purchase[courseId=jmc101, studentId=2, price=139.99, yr=2023, dayOfYear=9], Purchase[courseId=jmc101, studentId=6, price=139.99, yr=2023, dayOfYear=9], Purchase[courseId=pyt101, studentId=7, price=139.99, yr=2023, dayOfYear=9]]
2023-01-10: [Purchase[courseId=pyt101, studentId=5, price=119.99, yr=2023, dayOfYear=10]]
2023-01-14: [Purchase[courseId=pyt101, studentId=4, price=119.99, yr=2023, dayOfYear=14]]
Week 2 Purchases = {jmc101=3, pyt101=3}
```

Again, this prints out all my purchases by date for the first week, 
but then I have the weekly totals, Week 1 Purchases, 
and these should match what I see in the printed data above. 
And I get similar information for the second week. 
The total of all purchases should always be 10 
if I tally up all purchases for the two weeks. 
Let's look at a couple of other methods on the TreeMap. 
These are also very similar to TreeSet. 
You'll remember **TreeSet** and _last_, _first_, _pollLast_ and _pollFirst_. 
**TreeMap** has the last key and the first key methods, 
and _lastEntry_ and _firstEntry_, 
instead of just _first_ and _last_ methods. 
**TreeMap** has _pollLast_ key and _pollFirst_ key, 
and poll last entry and poll first entry methods,
instead of just _pollFirst_ and _pollLast_. 
It has similar methods, but you have to specify key or Entry.

```java  
System.out.println("-----------------------");

LocalDate lastDate = datedPurchases.lastKey();
var previousEntry = datedPurchases.lastEntry();

List<Purchase> lastDaysData = previousEntry.getValue();
System.out.println(lastDate + " purchases : " + lastDaysData.size());
```

I'll add a separator line. 
Now I'll set up a local variable, a local date, 
and assign it the result of calling the last key method on my _datedPurchases_ map. 
I'll use the var type, and assign that the lastEntry result. 
You can see from IntelliJ's hints that previousEntry is an Entry type. 
To get the list of purchases, I need to call the get value method on previous entry, 
and assign that to a List of purchases, in a local variable I'll call _lastDaysData_.
Here, I'll print that last date, and the size of the list. 
Let me run that:

```html  
2023-01-13 purchases : 2
```
                
It will show me the last day I made any purchases, and how many. 
Now, let's look at the lower key and lower entry methods. 

```java  
System.out.println("-----------------------");

LocalDate lastDate = datedPurchases.lastKey();
var previousEntry = datedPurchases.lastEntry();

while (previousEntry != null) {
    List<Purchase> lastDaysData = previousEntry.getValue();
    System.out.println(lastDate + " purchases : " + lastDaysData.size());

    LocalDate prevDate = datedPurchases.lowerKey(lastDate);
    previousEntry = datedPurchases.lowerEntry(lastDate);
    lastDate = prevDate;
}
```

To do this, I'm going to wrap the last two statements in a while loop. 
I'll add the while statement, and the condition previousEntry is not null. 
This means I want to keep looping until the previous entry is null. 
I have to add the closing brace for my while loop, after those two statements. 
If I left the code this way, I'd be in an infinite loop, 
because previousEntry never gets changed, it will always be non-null. 
I'm going to reassign the previous entry, as well as last date,
but first I'll set up a local variable, a date called prevDate. 
I'll assign that the result of calling lowerKey, on the dated purchases map, 
using the lastDate. 
The previous entry will then get the result of calling lower entry, 
on my dated purchases map, again using last date, 
which you'll remember was the last key. 
After that, I reassign lastDate to the value of previous date. 
This loop will continue processing until the lower entry method returns a null, 
which should happen, after processing the first element in the map. 
Let me run that:

```html  
2023-01-14 purchases : 1
2023-01-10 purchases : 1
2023-01-09 purchases : 3
2023-01-08 purchases : 1
2023-01-05 purchases : 1
2023-01-04 purchases : 1
2023-01-03 purchases : 1
2023-01-02 purchases : 1
```
 
This gives me the number of purchases for each day, in reverse order. 
What would happen if I changed these two lower method calls 
to the corresponding floor methods? 
Let me do that, just to show you what happens. 
I'll change lowerKey to the floor key, and lower entry to floor entry.

```java  
System.out.println("-----------------------");

LocalDate lastDate = datedPurchases.lastKey();
var previousEntry = datedPurchases.lastEntry();

while (previousEntry != null) {
    List<Purchase> lastDaysData = previousEntry.getValue();
    System.out.println(lastDate + " purchases : " + lastDaysData.size());

    //LocalDate prevDate = datedPurchases.lowerKey(lastDate);
    //previousEntry = datedPurchases.lowerEntry(lastDate);
    LocalDate prevDate = datedPurchases.floorKey(lastDate);
    previousEntry = datedPurchases.floorEntry(lastDate);
    lastDate = prevDate;
}
```

If I run this code, I'll be stuck in an infinite loop. 
Why's this? 
Remember, the _lower_ method on **Set** always returned
the element that was less than the method argument, 
but the _floor_ method gets the value, that's less than, 
or equal to, the method argument. 
This is true for these methods as well. 
In this case, the _floorEntry_ method just keeps returning 
the same element each time, as does the _floor_ key, 
because it finds an element whose key is equal to that key.
I'll revert those last changes, so that my code runs again. 
While I'm at it, I might as well give Map's last and higher methods a try. 
I'll also use this to show you another map view collection, the _descendingMap_. 
First, I'll use the _descendingMap_ method to get my purchases 
in the reverse order, reversed by date which is my key.

```java  
System.out.println("-----------------------");
var reversed = datedPurchases.descendingMap();

LocalDate firstDate = reversed.firstKey();
var nextEntry = reversed.firstEntry();

while (nextEntry != null) {
    List<Purchase> lastDaysData = nextEntry.getValue();
    System.out.println(firstDate + " purchases : " + lastDaysData.size());

    LocalDate nextDate = reversed.higherKey(firstDate);
    nextEntry = reversed.higherEntry(firstDate);
    firstDate = nextDate;
}
```

I'll put a separator line here. 
I'll use _var_ as the type for the _reversed_ local variable, 
and assign that _datedPurchases.descendingMap_. 
This code is going to look very similar to the code I just showed you, 
where I looped through my entries, using lower key, 
and lower entry to process the data. 
I'll set up two local variables, as I did before. 
The first one, a **LocalDate**, first date, 
gets assigned what I get back, from _reversed.firstKey_. 
I want to use the _reversed_ map I just created, 
to get the first key, and not the _datedPurchases_ map. 
Because this map is in reverse order, 
I can produce the same output, but use different methods. 
The next variable is _nextEntry_, and that's what I get back, 
from calling the _firstEntry_ method on the _reversed_ map. 
I'll set up a while loop, checking to see
if _nextEntry_ is not _null_ for this loop. 
I'll set up a local variable for my list of daily purchases. 
I'll print the date and number of purchases like before. 
I'll set up another local variable in my loop, 
and this time I'm going to call it _nextDate_, 
and instead of calling lowerKey, I'll use the higher key method here. 
Because my map's in reversed order, 
higher is going to return the next most current date and purchases. 
_nextEntry_ gets assigned what I get back,
from the higher entry method, using whatever is in _firstDate_. 
I'll reassign _firstDate_ to the _nextDate_. 
If I run that code, I get the same results, 
as the previous while loop, this time by using a _reversed_ map,
and the _first_ and _higher_ methods. 
For good measure, I'll also print out my original map after this loop.

```java  
System.out.println("-----------------------");
datedPurchases.forEach((key, value) -> System.out.println(key + ": " + value));
```

I'll add a separator line, 
and use the _forEach_ method on my _datedPurchases_ map, 
to print that data again. 
Running that, I can confirm that my _datedPurchases_ map hasn't changed at all. 
Now, I'll make two minor changes to this last bit of code. 
First, I'll copy the statement, pasting a copy just below it, and I'll comment out.

```java  
System.out.println("-----------------------");
var reversed = datedPurchases.descendingMap();

LocalDate firstDate = reversed.firstKey();
//var nextEntry = reversed.firstEntry();
var nextEntry = reversed.pollFirstEntry();

while (nextEntry != null) {
    List<Purchase> lastDaysData = nextEntry.getValue();
    System.out.println(firstDate + " purchases : " + lastDaysData.size());

    LocalDate nextDate = reversed.higherKey(firstDate);
    //nextEntry = reversed.higherEntry(firstDate);
    nextEntry = reversed.pollFirstEntry();
    firstDate = nextDate;
}
```

I'll do the same thing for the next statement, 
pasting a copy below, and commenting the first statement out.
In both these cases, where I'm assigning _nextEntry_, 
I want to use a different method, 
replacing both with the same expression. 
In the first case, I'm changing first entry, to poll first entry method. 
In the second, I'm replacing the call to higher entry, 
also replacing it with _pollFirstEntry_. 
Remember that _pollFirstEntry_ doesn't take any arguments. 
How does this work? 
Well, let me run it, and see what I get.

```html  
-----------------------
2023-01-13 purchases : 2
2023-01-12 purchases : 1
2023-01-10 purchases : 1
2023-01-08 purchases : 1
2023-01-06 purchases : 1
2023-01-05 purchases : 2
2023-01-03 purchases : 1
2023-01-02 purchases : 1
-----------------------
```
                    
You can see, my output looks the same for the loop results. 
Using this method gave me the same result. 
But there's a really important difference. 
Can you see it? 
Remember, I've got code after this loop, 
to print the information in my _datedPurchases_ map. 
So why didn't that data get printed out? 
There are two important things you need to remember,
which is why I'm showing you this particular example. 
First, you need to understand that the _poll_ methods, 
_pollFirstEntry_ and _pollLastEntry_, remove data from the map 
on each later call. 
These aren't just an alternate way to get the first and last entry, 
because they're also mutating the map. 
And secondly, the reversed map is a view. 
The true source is my _datedPurchases_ map. 
When I execute the _poll_ methods on the reversed map, 
those operations are actually occurring on the _datedPurchases_ map. 
If you're not really careful, when you use these views, 
you could really mess things up for code using the _datedPurchases_ map later on. 
Views are very helpful, but they can also get you in trouble, 
if you don't remember that's what they are. 
I'll put up a table and list the Map's view collections. 
They're very similar to **TreeSet**'s, 
but there are separate views for the keys and the entries.

<table>
    <th> View collection methods </th>
    <th> Notes </th>
    <tr>
        <td> entrySet(), keySet(), values()</td>
        <td> Provides views of mappings, keys and values. 
             These are views available to map, and just the Treemap. 
             I include them here to remind you. 
             These are <b>views</b>.
        </td>
    </tr>
    <tr>
        <td> descendingKeySet()<br/> descendingKeyMap()</td>
        <td> Provides reversed order key set or map, 
             reversed by the key values. 
        </td>
    </tr>
    <tr>
        <td> headMap(K key) <br/> 
             headMap(K key, boolean inclusive) <br/>
             tailMap(K key) <br/>
             tailMap(K key, boolean inclusive)
        </td>
        <td> Provides views of either the first or last parts of the map, divided by the key passed. <br/>
             The <b>head</b> map is by default <b>EXCLUSIVE</b> of all elements higher or equal to the key. <br/>
             The <b>tail</b> map is by default <b>INCLUSIVE</b> of all elements higher or equal to the key. <br/>
        </td>
    </tr>
    <tr>
        <td> subMap(K fromKey, K toKey) <br/> 
             subMap(K fromKey, boolean inclusive, K toKey, boolean inclusive) <br/>
        </td>
        <td> Provides a view of a contiguous section of the map, 
             higher or equal to the fromKey and lower than the toKey, so the <b>toKey is EXCLUSIVE.</b> <br/>
             The overloaded version allows you to determine the inclusivity you want for both keys. <br/>
        </td>
    </tr>
</table>

The _entrySet_, _keySet_, and _values_ methods 
return views of the mappings, keys, and values respectively. 
These are views available to any map, 
and not just the **TreeMap**. 
I include them here, to remind you; these are still views.
Manipulating the collections returned from these methods 
will be manipulating the source map. 
The _descendingKeySet_ and _descendingKeyMap_ methods provide reversed order views, 
reversed by the key values. 
The _headMap_ and _tailMap_ methods provide views of either 
the first or last parts of the map, spliced or divided by 
the key argument that's passed. 
The head map is by default, _EXCLUSIVE_ of all elements higher 
or equal to the key. 
The tail map is, by default, _INCLUSIVE_ of all elements higher 
or equal to the key. 
The _subMap_ method provides a view of a contiguous subsection of the map,
higher or equal to the _fromKey_, and lower than the _toKey_, 
so the _toKey_ is _EXCLUSIVE_. 
The overloaded version allows you to determine the inclusivity 
you want for both keys. 
</div>

## [n. EnumSet and EnumMap Collections]()
<div align="justify">

Before we move on, I want to talk about two more classes 
in the **collections** framework, specifically created to
support enum types more efficiently. 
You can use any **List**, **Set**, or **Map**, 
with an enum constant. 
The **EnumSet**, and **EnumMap**, each has a special implementation 
that differs from the **HashSet** or **HashMap**. 
These implementations make these two types extremely compact and efficient. 
There's no special list implementation for enum types.

The **EnumSet** is a specialized Set implementation for use with enum values. 
All the elements in an **EnumSet** must come from a single enum type. 
The **EnumSet** is abstract, meaning we can't instantiate it directly. 
It comes with many factory methods to create instances. 
In general, this set has much better performance than using a **HashSet**, 
with an enum type.
Bulk operations (such as _containsAll_ and _retainAll_) should run very quickly, 
in constant time, O(1) if they're run on an **EnumSet**, 
and their argument is an **EnumSet**.

The **EnumMap** is a specialized **Map** implementation for use with enum type keys. 
The keys must all come from the same enum type, and they're ordered naturally 
by the ordinal value of the enum constants. 
This map has the same functionality as a **HashMap**, with O(1) for basic operations. 
The enum key type is specified during construction of the **EnumMap**, 
either explicitly by passing the key type's class, or implicitly by passing another **EnumSet**. 
In general, this map has better performance than using a **HashMap** with an enum type.
Let's review some of the things you can do when you combine enums with these collections. 
I've created a **Main** class and _main_ method.
I'm going to create an enum type in this class, called **WeekDay**. 
This enum will just have all the days in the week.

```java  
public class Main {

    enum WeekDay {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}

    public static void main(String[] args) {
        List<WeekDay> annsWorkDays = new ArrayList<>(List.of(WeekDay.MONDAY,
                WeekDay.TUESDAY, WeekDay.THURSDAY, WeekDay.FRIDAY));

        var annsDaysSet = EnumSet.copyOf(annsWorkDays);
        System.out.println(annsDaysSet.getClass().getSimpleName());
    }
}
```

Let's say I'm interested in assigning work days to different employees. 
I already have an employee named _Ann_, so I'll set up a list of the days she works. 
The list type will be the enum type, **weekDay**, 
I'll call the variable _annsWorkDays_, and that will be a new ArrayList, 
created using the list dot of method, with _Monday_, _Tuesday_, _Thursday_ and _Friday_. 
I'm going to use Ann's work days to figure out what kind of coverage 
I'll need for other employees I want to hire. 
First, let me use one of the factory methods on the EnumSet, to create a set of Ann's work days. 
I'll set my type to var right now. 
I'll call this variable _annsDaysSet_. 
I'll assign that the result of the _EnumSet.copyOf_ method, with _annsWorkDays_. 
This method will take any collection type. 
Then, I want to print out the actual type of the instance I get back. 
Before I run it, notice that the inferred type is an **EnumSet**, with the type of _WeekDay_. 
The **EnumSet** is an abstract class, as I said before, 
so if I want to know what the set type really is, I'll need to run this code. 
Running this,

```html  
RegularEnumSet
```

You can see my instance is a **RegularEnumSet**.

Enum sets are represented internally as bit vectors, 
which is just a series of ones and zeros. 
A one indicates that the enum constant 
(with an ordinal value that is equal 
to the index of the bit) is in the set. 
A zero indicates the enum constant is not in the set. 
Using _bit_ vector allows all set operations to use math, 
which makes it rapid.
A **RegularEnumSet** uses a single long as its _bit_ vector, 
which means it can contain a maximum of 64 bits,
representing 64 enum values. 
A **JumboEnumSet** gets returned if you have more than 64 enums.

![image26](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image25.png?raw=true)

This is a visual representation of the EnumSet for Ann's work days. 
Its size is 7, for the 7 possible values (based on the number of 
constants in the WeekDay Enum). 
Any weekday that's part of her set will be set to 1, 
at the index that corresponds to the weekday ordinal value. 
_MONDAY_ has an ordinal value of 1 in our **WeekDays** enum, 
and the value in the underlying _bit_ vector, at position 1, is a 1.
This means _MONDAY_ is part of Ann's **EnumSet**. 
Ok, so let's get back to the code and explore this class a little more.

```java  
annsDaysSet.forEach(System.out::println);
```

I'll print AnnsDaysSet out each day on its own line. 
Running this,

```html  
RegularEnumSet
MONDAY
TUESDAY
THURSDAY
FRIDAY
```
                    
Ann's workdays are printed out, in the order as defined in the Enum. 
This is another advantage of using just a **HashSet** with an enum type, 
because the set is naturally ordered by the ordinal value. 
We can create a **Set** for all the enum values, using another factory method, _allOf_. 

```java  
var allDaysSet = EnumSet.allOf(WeekDay.class);
System.out.println("---------------------");
allDaysSet.forEach(System.out::println);
```

I'll set up a local variable, again using var, named all days set, 
and assign that _EnumSet.allOf_, and pass that the _WeekDay.class_. 
I'll print a separator line for clarity. 
I'll print each element. 
Running that:

```html  
---------------------
SUNDAY
MONDAY
TUESDAY
WEDNESDAY
THURSDAY
FRIDAY
SATURDAY
```
                    
It just prints out all the days in our enum, in order. 
You might be wondering why that's useful, 
since that's data we can just get, using enum's values methods. 
Hold that thought for a minute.

```java  
Set<WeekDay> newPersonDays = EnumSet.complementOf(annsDaysSet);
System.out.println("---------------------");
newPersonDays.forEach(System.out::println);
```

First, I want to show you another method, called _complementOf_. 
I'll set up a Set of **WeekDay** this time, 
calling it new person days. 
I'll assign that the result of enum set's static method, _complementOf_, 
and passing it _annsDaysSet_.
Again, I'll output a separator line. 
And print what that gives me. 
Running this:

```html  
---------------------
SUNDAY
WEDNESDAY
SATURDAY
```
                
I get _Sunday_, _Wednesday_, and _Saturday_, 
so what did this really do? 
What set math operation is this? 
This is a difference of the full set of 
all possible work days, minus ann's set, isn't it? 
I can get the same results with the _removeAll_ bulk function, 
which I'll do next.

```java  
Set<WeekDay> anotherWay = EnumSet.copyOf(allDaysSet);
anotherWay.removeAll(annsDaysSet);
System.out.println("---------------------");
anotherWay.forEach(System.out::println);
```

I'll make a copy of the full set, and call it _anotherWay_. 
I'll call the _removeAll_ method, on that, passing it _annsDaysSet_. 
And I'll print this set out. 
Running that:

```html  
---------------------
SUNDAY
WEDNESDAY
SATURDAY
```

You can see I get the same result.
It was a lot easier to use the _complementOf_ method. 
I didn't have to create a set of all elements, 
or call the remove all method. 
One other creation method is the _range_ method.

```java  
Set<WeekDay> businessDays = EnumSet.range(WeekDay.MONDAY, WeekDay.FRIDAY);
System.out.println("---------------------");
businessDays.forEach(System.out::println);
```

I'll set up a new variable called _businessDays_. 
I'll set that to the result of enum set dot range, 
passing it _Monday_ and _Friday_. 
I'll print those out.

```html  
---------------------
MONDAY
TUESDAY
WEDNESDAY
THURSDAY
FRIDAY
```
                
You can get all the days between _Monday_ and _Friday_, 
including both _Monday_ and _Friday_. 
All other operations on this set are the same as those 
I covered with the **HashSet**, so I won't go through them here.

```java  
Map<WeekDay, String[]> employeeMap = new EnumMap<>(WeekDay.class);

employeeMap.put(WeekDay.FRIDAY, new String[]{"Ann", "Mary", "Bob"});
employeeMap.put(WeekDay.MONDAY, new String[]{"Mary", "Bob"});
employeeMap.forEach((k, v) -> System.out.println(k + " : " + Arrays.toString(v)));
```

Let's now look at the **EnumMap**.
This class is not abstract, so I can create an instance of it 
directly using _new_, but unlike other **Map** implementations, 
it does not have a no args constructor. 
I'll create a map, keyed by _WeekDay_, 
and each mapped element will be an array of string. 
That array will be the names of my employees who work those days. 
I'll assign that a new **EnumMap** instance, 
and pass it the _WeekDay.class_. 
Now, I have an empty enum map. 
I can add elements in the usual way, 
with _put_, _putAll_, _putIfAbsent_, _computeIfPresent_, 
_replaceAll_, and the other many methods of a Map. 
I'll just use the _put_ method. 
I'll add Friday's crew, _Ann_, _Mary_ and _Bob_. 
I'll next add Monday's crew, _Mary_ and _Bob_.  
I'll print this data out. 

```html  
---------------------
MONDAY
TUESDAY
WEDNESDAY
THURSDAY
FRIDAY
MONDAY : [Mary, Bob]
FRIDAY : [Ann, Mary, Bob]
```

Notice here that these are again ordered by the week day, 
without me having to use a **SortedMap**. 
I also didn't have to insert them in order, 
like I would have if I had used a **LinkedHashMap**. 
Again, I won't cover all the methods 
I've covered previously with the **HashMap**. 
This class has all those methods, and works very similarly 
to a **HashMap** with a key type that's an enum. 
But it's more efficient, and it's naturally ordered. 
</div>

## [o. Collections Framework Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/Course26_CollectionsFrameworkFinalChallenge/README.md#collections-framework-challenge)
<div align="justify">

Using some combination of the classes in the **Collections** Framework, 
see if you can create the classes and methods shown below.

![image27](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_11_Collections/images/image26.png?raw=true)

I'm purposely excluding types and relationships here. 
This is called a conceptual model, where you start by drawing
the needs of the system, and identifying fields and methods. 
The information on this diagram should be enough to get you started. 
A product's information is defined by its manufacturer, 
so assume the information on **Product** isn't mutable.
A _sku_ is short for stock keeping unit, and is a unique identifier for the product. 
The _category_ should be one of a defined set of categories, 
like product or dairy, for example, for a grocery item. 
The **InventoryItem** is the store's information specific to the product, 
like the price and quantities of each product in stock.

* _qtyTotal_: The total quantity is the amount that's still in stock, 
so it could be in any of your carts, on your aisles, or in your warehouse.
* _qtyReserved_: The qty that's reserved is the product in the carts, but not yet sold.
* _qtyRecorder_: The qty reorder amount is what you'd use to order more product.
* _qtyLow_: The low quantity is the trigger, or threshold, to order more product. 
When the low qty is reached, your system should order more products.

The **cart** type has an _id_, and a collection of _products_ 
that changes as a shopper puts in new items, 
or removes them from their cart. 
The cart will have a date, and also a type, 
to indicate if the type is physical or virtual.
Each of the fields on the **Store** class is collections. 
Which you choose is up to you. 
_Inventory_ is a collection of **InventoryItems**. 
_Carts_ is a collection of **carts**. 
The _aisleInventory_ is the inventory displayed 
physically on store shelves. 
You can assume aisles can be keyed by the product category. 
Your store should have a method to abandon physical and virtual carts, 
if the date associated with the cart is different from the current date.

Try to use a variety of **Collections** Framework 
implementations and methods. 
Think about the fields that would use collections, 
and the types you have to choose from. 
You can also use collections to help you manage some 
functionality in the methods. 
Remember the three interfaces, **List**, **Set**, and **Map**,
and the classes that implement these interfaces. 
Do you need to allow duplicates in the collection? 
Do you need things to be sorted? 
Is insertion order good enough? 
Do you need a way to organize the data into a key value system, 
to make some operations easier?
What methods on the Collections classes might be useful 
for some of this functionality? 
Would set math be useful? 
Or would navigational methods be simpler? 
Are there any methods on the Collections class that 
would make sense to use here? 
This may sound like a lot,
but take it one type at a time, 
and one operation at a time. 
Collections are such an important Java topic, 
so take time here to challenge yourself as much as possible.
</div>


<div align="justify">


```java  

```

</div>
