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

![image01]()

We've already looked at every one of these operations, in our study of ArrayLists and LinkedLists. When managing a
    group, you'll be adding and removing elements, checking if an element is in the group, and iterating through the elements.
    There are some others, but these are the ones that describe nearly everything you'd want to do to manage a group.
    Java uses the term "Element" for a member of the group being managed. Let's jump into IntelliJ and see what we can do
    with this interface.


```java  

```

</div>