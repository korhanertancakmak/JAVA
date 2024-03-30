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
Can I just call the sort method on list as we did before? 
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
to assist anyone using this class:

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
you'll notice that, I reversed the original sort. 
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
I'll  revert to that last change,
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

## [c. Card Game Challenge]()
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



<div align="justify">


```java  

```

</div>


<div align="justify">


```java  

```

</div>