# Collections and Map Interface

## Collection Interface

Subclasses inherited from the “Collection” interface are collected under the “java.util” package. With the “Collection” interface, it is specified which functions should be in a data structure that holds list type data. Every class that will hold list type data must directly or indirectly inherit from this interface. For this reason, the “Collection” interface is at the top of the family tree.

With Java Collection classes, you can perform operations such as searching, adding, deleting and sorting on list data.

We know that interfaces can also inherit from each other. The most important interfaces inherited from the “Collection” interface are the “List” “Queue” and “Set” interfaces.

The subclasses that inherit from these three interfaces are as follows.

* Subclasses inherited from the List interface: ArrayList, LinkedList, Vector, Stack

* Subclasses inherited from Queue interface: PriorityQueue, ArrayQueue

* Subclasses inherited from Set interface: HashSet, LinkedHashSet, TreeSet

Functions that subclasses deriving from the Collection interface must provide:

| int size()                                       | Returns the number of elements of the dataset.                                          |
| ------------------------------------------------ | --------------------------------------------------------------------------------------- |
| boolean isEmpty()                                | Returns true if there is no element in the dataset.                                     |
| boolean contains(Object element)                 | Indicates true/false whether the searched element exists in the data set.               |
| Iterator iterator()                              | Returns the object that allows you to navigate the elements one by one in the data set. |
| Object[] toArray()                               | Returns the data set as an array.                                                       |
| boolean add(E e)                                 | Allows adding elements to the data set.                                                 |
| boolean remove(Object element)                   | Allows deleting elements from the data set.                                             |
| boolean addAll(Collection<? extends E elements>) | Adds another data set of Collection type to the existing data set as is.                |
| void clear()                                     | Clears all elements in the dataset. Deletes.                                            |
| boolean removeAll(Collection<?> elements)        | Deletes the given set of elements from the current dataset.                             |

You can also create subclasses that inherit from the Collection interface yourself.

![Collection Interface](https://raw.githubusercontent.com/Kodluyoruz/taskforce/main/java102/collection/figures/collection-interface.png)

## Set Interface and HashSet

### Set interface

It inherits from the Collection interface. It does not allow the same elements to be found again in the data set. HashSet class is the most common subclass. To ensure that there is no element repetition, the "equals" and "hashCode" functions of the objects in the data set must be defined.

Subclasses of Set interface:

* HashSet
* LinkedHashSet
* TreeSet

#### HashSet Class

It allows storing data in list type. It allows adding, deleting and accessing elements in the data set. It does not keep duplicate values ​​in the data set. It prevents duplicate values ​​by using the hashCode function on objects. “null” value can be added.

We designed a JAVA.AbstractClass.Book class with “equals” and “hasCode” functions filled in. We created a dataset consisting of JAVA.AbstractClass.Book objects.

```java  
import java.util.HashSet; 
import java.util.Iterator; 
public class HSet { 
    public static void main(String[] args) { 
        // f(x) = x*3 * xmod7 * sqrt(x) 
        HashSet<String> h = new HashSet<>(); 
        
        h.add("a"); 
        h.add("b"); 
        h.add("z"); 
        h.add(null); 
        h.remove("b");         
        System.out.println(h.size());
        System.out.println(h.contains("b")); 

        for (String s : h) {             
            System.out.println(s);
        }

        // Navigating using Itertor
        Iterator<String> itr = h.iterator();
        while (itr.hasNext()) {             
            System.out.println(itr.next());
        }
    }
}
```

Output:

```java  
3
false
null
a
z
null
a
z
```

#### HackerRank Challenge: Java Hashset

#### LinkedHashSet

LinkedHashSet is an ordered version of HashSet that maintains a doubly linked List between all elements. This class is used when the iteration order needs to be preserved. When iterating through a HashSet, the order is unpredictable, whereas LinkedHashSet allows us to iterate through elements in the order they were added. When traversing a LinkedHashSet using an iterator, the elements are returned in the order they were added.

```java  
import java.util.LinkedHashSet; 
public class LHashSet { 
    public static void main(String[] args) { 
        LinkedHashSet<String> days = new LinkedHashSet<>(); 
        days.add("Monday"); 
        days.add("Tuesday"); 
        days.add("Wednesday"); 
        days.add("Thursday"); 
        days.add("Friday"); 
        days.add("Saturday"); 
        days.add("Sunday"); 
        days.remove("Sunday"); 
        for (String day : days) {             
            System.out.println(day);
        }
    }
}
```

Output:

```java  
Monday
Tuesday
Wednesday
Thursday
Friday
Saturday
```

#### TreeSet

It ensures that the elements placed in the data set are sorted according to the rule you give. In order to achieve this, the class you will use must be “sortable”. In order for a class to be enumerable, it must inherit from the “Comparable” interface and fill in the “compareTo” method. The “compareTo” function on the object allows natural sorting from smallest to largest.

```java  
import java.util.TreeSet; 
public class TSet { 
    public static void main(String[] args) { 
        TreeSet<Integer> numbers = new TreeSet<>(); 
        numbers.add(10); 
        numbers.add(1022); 
        numbers.add(1);
        numbers.add(-10); 
        numbers.add(22); 
        for (Integer number : numbers) {
            System.out.println(number);
        }
    }
}
```

Output:

```java  
-10
1
10
22
1022
```

## List interface

The List Interface, which implements the Collection Interface, became generic with Java 5. Data is kept in order of arrival.

Each element of a list occupies a unique space in memory. Adding and accessing data on the list is done through integer numbers called index. The initial index is zero.

In classes that inherit from the List Interface, duplicate or null elements can be kept.

Lists can be used wherever arrays are used, and they can be converted into each other. Programmers prefer Lists because of their methods for data processing.

Methods implemented in this interface;

| Method signatures                   | Explanation                                                          |
| ----------------------------------- | -------------------------------------------------------------------- |
| get(int index)                      | Returns the object at the given index.                               |
| add(Object element)                 | Allows adding elements to the list. If the object is given with the index, it adds the element to the point indicated by the relevant index. If there is another object at the same index, it overwrites it. |
| indexOf(Object)                     |Finds the index at which a given object is kept in the list.          |
| remove(int index)                   | Deletes the element at the given index.                              |
| set(int index, Object element)      | Replaces the element at the given index with another element.        |
| subList(int fromIndex, int toIndex) | Creates a new list consisting of elements between the given indices. |

***Subclasses of List interface***

* ArrayList
* LinkedList
* vector
* stack

***NOTE***: Vector and Stack classes are legacy classes. These classes existed in versions before Java 5 and now have better alternatives. Since there may still be projects that use these classes, they cannot be removed completely.

### ArrayList Class

ArrayList, a subclass derived from List Interface, stores list data using dynamic arrays. The default size is 10.

As new elements are added to these arrays, if their size is not sufficient, a new array is defined at run time that is twice the size of the existing array. Elements in the old array are transferred to the new array, preserving their index values. It is a flexible but costly collection.

ArrayList is preferred for storing data and situations where access to data is intense.

When defining ArrayLists, the type of values ​​to be kept should be written between the <> (diamond) operators.

In case of insertion or deletion operations, scrolling operations are required. This reduces performance.

ArrayList class is not thread-safe. More than one thread can access an ArrayList at the same time. This situation disrupts data integrity.

Class signature:

```java  
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable  
```

| Constructor                          | Explanation                                                                          |
| ------------------------------------ | ------------------------------------------------------------------------------------ |
| ArrayList()                          | It is used to create an empty ArrayList.                                             |
| ArrayList(Collection<? extends E> c) | It is used to create an ArrayList initialized with the elements of the C Collection. |
| ArrayList(int capacity) | It is used to create an ArrayList with the specified initial capacity.                            |

```java  
import java.util.*;

public class Alist {
    public static void main(String[] args) {
        // Initialize ArrayList that holds elements of String type
        List<String> nameList = new ArrayList<String>();

        // Its elements are placed sequentially, starting from index 0.
        nameList.add("Gamze");
        nameList.add("Elif");
        nameList.add("Mustafa");
        nameList.add("Umut");
        nameList.add("Umut");
        // ArrayList can hold nullable elements.
        nameList.add(null);

        // Prints the entire list to the screen.
        System.out.println(nameList);

        // The size() method is an inheritance from the Collection Interface.
        System.out.println("Size of list: " + nameList.size());

        // Elements are read from the list with get. The element is read cannot be removed from the list.
        System.out.println("Element of 1 index: " + nameList.get(1));
        System.out.println("Element of 2 index: " + nameList.get(2));

        // It starts scanning from the beginning of the list and gives the index value of the "Umut" information at the first mentioned point.
        System.out.println("Index of 'Umut': " + nameList.indexOf("Umut"));

        // It starts scanning from the beginning of the list and gives the index value of the "Umut" information at the last mentioned point.
        System.out.println("Index of 'Umut': " + nameList.lastIndexOf("Umut"));

        // The first argument given to the add() function is the index, the next element.
        // Goes to the given index and inserts the given element.
        // If there is a previously defined value at the given index, it puts the element in the function at that index and shifts the remaining elements.
        nameList.add(3, "Zeynep");

        // The first argument given to the set() function is the index, the next element.
        // Goes to the given index and inserts the given element.
        // The given index must not be outside the list size.
        nameList.set(1, "Naz");

        // contains indicates whether the element we are looking for exists in the list or not.
        // Returns true if present, false otherwise
        System.out.println(nameList.contains("Elif"));
        System.out.println(nameList.contains("Mustafa"));

        // With the remove function, the value at the index we specified is deleted from the list.
        // the deleted value is returned to us from the function.
        String firstElement = nameList.remove(0);
        System.out.println(firstElement + " is removed from list!");

        List<String> newNameList = new ArrayList<String>();
        newNameList.add("Batuhan");
        newNameList.add("Kemal");

        // "addAll" function is used to add a list entirely to another list.
        nameList.addAll(newNameList);

        // We use the "sublist" function to create a sublist from the list.
        // Starting and ending indices are given.
        // A new list is created, including the element at the start index and excluding the element at the end index.
        List<String> subList = nameList.subList(4, 6);

        System.out.println("Sublist from name list");
        System.out.println(subList);

        // If you call the toArray function without parameters, it returns an array of type Object.
        Object[] objectArray = nameList.toArray();

        // What type of array we want to create in the toArray function,
        // we create an object of that type and send it as a parameter.
        // Since we wanted to get an array of String type, we created an object in the form of "new String[0]" and sent it to the "toArray" function.
        String[] stringArray = nameList.toArray(new String[0]);

        // clears all elements in the list. That is, it deletes all of them from the list.
        nameList.clear();
    }
}
```

Output:

```java  
[Gamze, Elif, Mustafa, Umut, Umut, null]
Size of list: 6
Element of 1 index: Elif
Element of 2 index: Mustafa
Index of 'Umut': 3
Index of 'Umut': 4
false
true
Gamze is removed from list!
Sublist from name list
[Umut, null]
```

#### HackerRank Challenge: Java ArrayList

### LinkedList and Differences Between It and ArrayList

It is the Java implementation of the doubly linked list algorithm. A two-way relationship is established so that each element points to the previous and next element. Elements can be added and removed at the beginning and end of the list, and methods are available for these operations. There is no scrolling operation when deleting or adding elements in the LinkedList.

![LinkedList Work Pattern](https://raw.githubusercontent.com/Kodluyoruz/taskforce/main/java102/collection-linkedlist/figures/linkedlist.png)

The data sent to this list is wrapped with a special object in the background. References are placed on the objects so that these objects point to each other, thus connecting them to each other.

If we put the data group to be used in a list and add data to this group continuously, it is recommended to use LinkedList.

LinkedList class is not thread-safe like ArrayList. This List type also has a data integrity problem.

LinkedList is a class that inherits from both the List interface and the Queue interface.

Class signature;

```java  
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable  
```

| Constructor                           | Explanation                                |
| ------------------------------------- | ------------------------------------------ |
| LinkedList()                          | It is used to create an empty Linked List. |
| LinkedList(Collection<? extends E> c) | Used to create a list containing the elements of the specified collection in the order returned by the collection's iterator. |

```java  
List<String> nameList = new LinkedList<String>();
nameList.add("Hale");
nameList.add("Jale");
nameList.add("Lale");
nameList.add("Ahmet");
nameList.add("Mehmet");
nameList.add("Kemal");
 
// Elements are read from the list with get. The element is read cannot removed from the list.
System.out.println("Element of 2 index: " + nameList.get(2));

// Adds element to the beginning of the list
nameList.addFirst("Naz");

// Adds an element to the end of the list
nameList.addLast("Umut");
```

Output:

```java  
Element of 2 index: Lale
```

Most of the methods implemented in ArrayList are also valid for LinkedList.

### Vector

Array plays a very important role in classical programming action. However, the length of the array; That is, the number of components is determined in the array declaration and this length cannot be changed later. In some applications, this feature creates a serious handicap. Java 2 introduced the Vector class to solve this problem and the ArrayList class which does similar work. In both classes, if new elements are added to the array or existing elements are deleted, the length of the array changes automatically. Of course, this change has a cost (complexity) in terms of memory usage and time. But, the Java programmer does not have to write the routines that do these things himself. Objects belonging to the Vector or ArrayList class do these tasks automatically.

Elements of vector type arrays have an index, just like the array type. Therefore, direct access to the elements of the vector is provided via their indices.

ArrayList is faster than Vector class because it is asynchronous. Of course, being fast has a price. Being out of sync means reduced reliability in a multiprocessing environment.

```java  
import java.util.Vector; 
public class VectorExample { 
    public static void main(String[] args) { 
        Vector<String> v = new Vector<String>(); 
        v.add("Zonguldak"); 
        v.add("Sinop"); 
        v.add("Trabzon"); 
        v.add("Rize"); 
        v.add("Yalova"); 
        // Insert an element at the position with index 3 (insetion)
        v.add(3, "Bafra"); 
        // v.size() returns the number of components of the vector
        System.out.println("Vektörün uzunluğu :" + v.size()); 
        // v.get(i) returns the i-th index term of the vector
        for (int i = 0; i < v.size(); i++) { 
            System.out.println("Vektör öğesi : " + i + " :" + v.get(i));
        }
    }
}
```

Output:

```java  
Vektörün uzunluğu :6
Vektör öğesi : 0 :Zonguldak
Vektör öğesi : 1 :Sinop
Vektör öğesi : 2 :Trabzon
Vektör öğesi : 3 :Bafra
Vektör öğesi : 4 :Rize
Vektör öğesi : 5 :Yalova
```

### Queue LinkedList

The Queue interface is a member of the Java Collections Framework. It allows items to be stored before they are processed. Since it is a subinterface of the Collection interface, it uses all its methods. In addition, it has methods that facilitate operations such as insertion and deletion in the queue structure.

Due to emergence, Queue is a FIFO (first-in-first-out) structure. However, by using the methods in the Queue and Collection interface, the FIFO structure can be used like a LIFO (last-input-first-output, last-in-first-out) structure. FIFO and LIFO structures are called priority queues. In these structures, elements are arranged either in their natural order or by a comparator created for this purpose. Of course, the LinkedList structure is more general than both. Whether the structure is FIFO or LIFO, the first item to appear is at the head of the queue; It is retrieved with the remove() or poll() method.

* ***element()*** : Returns the element at the head of the queue, but does not remove it from the queue.
* ***add(element)***: Adds the element given in the parameter to the queue. Throws an error if the operation fails.
* ***offer(element)***: Adds the element given in the parameter to the queue. Returns null if the operation fails.
* ***poll()***: Removes the element at the beginning of the queue from the queue.
* ***peek()***: Used to reach the next element in the queue.

```java  
import java.util.LinkedList; 
import java.util.Queue; 
public class QuList { 
    public static void main(String[] args) {  
        Queue<String> queue = new LinkedList<>(); 
        // Adding items to the queue with the add() method
        // throws an error if the addition is not made
        queue.add("Deniz");
        queue.add("Berna");
        // Adding items to the queue with the offer() method
        // returns false if the addition is not made
        queue.offer("Volkan"); queue.offer("Çağlar"); 
        // remove() method returns the value at the beginning of the queue and removes it from the queue
        // If the queue is empty, java.util.NoSuchElementException will be thrown.
        System.out.println("remove() : " + queue.remove());
        // element() method returns the element at the head of the queue; doesn't remove it from the queue
        // If the queue is empty, java.util.NoSuchElementException will be thrown.
        System.out.println("element() : " + queue.element()); 
        // poll() method returns the item at the head of the queue and removes it from the queue
        // Returns false if the queue is empty
        System.out.println("poll() : " + queue.poll()); 
        // peek() method returns the item at the head of the queue; doesn't remove it from the queue
        // Returns false if the queue is empty
        System.out.println("peek() : " + queue.peek());     
    }
}
```

Output:

```java  
remove() : Deniz
element() : Berna
poll() : Berna
peek() : Volkan
```

## PriorityQueue

### Priority

PriorityQueue literally defines a phenomenon that we frequently encounter in daily life. In some cases, we may have to do one job before all other jobs. For example, among those queuing at a bank counter, priority goes to the person at the front. However, at an intersection, the lifeguard has priority. Among aircraft waiting to land at an airport, priority is given to the aircraft requesting an emergency landing. In a hospital, the priority for examination is given to the patient with the most urgent condition.

As can be seen, we can determine the priority order within a collection for different purposes. The simplest is the FIFO structure, which we call first-in, first-out. But this structure cannot provide a solution to all possibilities that may be encountered. Therefore, there is a need for a structure that sorts the elements of the collection according to the desired priority.

With a little generalization, we will call the PriorityQueue structure a queue; but the meaning loaded here may be different from the FIFO structure. Sometimes the order is the natural order of the elements, sometimes it is the order determined by the relevant Comparator.

In a PriorityQueue queue, items are either in their natural order or in the order in which the Comparator used at the time of installation. Of course, this depends on which constructor is used. A null element cannot be placed in a PriorityQueue queue. An object that is incompatible with its elements cannot be placed in a PriorityQueue that is in its natural order. If this is done, a ClassCastException error will be received from the compiler.

The head of the PriorityQueue queue is the smallest element according to the order used. If there are multiple elements that are the smallest, the head of the queue is one of them. The poll(), remove(), peek() and element() methods access the head of the queue.

There is no limit to the length of the PriorityQueue queue; but it can change its length on its own as new elements are added; That is, the capacity of the queue increases automatically.

```java  
import java.util.Comparator; 
import java.util.PriorityQueue; 
public class PriorityExample { 
    public static void main(String[] args) { 
        // Comparator prioritizes even numbers
        Comparator<Integer> compareForEven = new Comparator<>() { 
            public int compare(Integer i, Integer j) { 
                int result = i % 2 - j % 2;
                if (result == 0) {
                    result = i - j;
                }
                return result;
            }
        };

        PriorityQueue<Integer> pq = new PriorityQueue<>(20, compareForEven); 
        // add numbers to the queue in reverse order
        for (int i = 0; i < 20; i++) { 
            pq.offer(20 - i);
        }
        // print items in Comparator's order
        for (int i = 0; i < 20; i++) { 
            System.out.print("\t" + pq.poll());
        }
    }
}
```

Output:

```java  
2 4 6 8 10 12 14 16 18 20 1 3 5 7 9 11 13 15 17 19
```

### HackerRank Challenge: Java Priority Queue

## Map Interface and HashMap

Map Interface is one of the concrete classes. HashMap class can also be called hash mapping. It is very effective in adding and removing elements to the mapping table and finding the element whose key is given in the table.

Classes that use Map Interface have the following methods;

* ***clear***: Clears all values ​​in the map.
* ***containsKey (Object key)***: Queries whether a certain key has been entered before.
* ***containsValue (Object value)***: Queries whether a certain object has been entered before.
* ***get (Object key)***: Returns the object corresponding to the key.
* ***put (Object key, Object value)***: Saves the key - value pair.
* ***remove (Object key)***:Deletes the value corresponding to a certain key.
* ***size***: Returns the number of key-value pairs recorded up to that time.

```java  
import java.util.HashMap; 
public class JAVA.MyList.Main { 
    public static void main(String[] args) {   
        HashMap<String, String> capitalCities = new HashMap<String, String>(); 
        capitalCities.put("England", "London"); 
        capitalCities.put("Germany", "Berlin"); 
        capitalCities.put("Norway", "Oslo"); 
        capitalCities.put("USA", "Washington DC");     
        System.out.println(capitalCities);

        capitalCities.get("England"); 
        capitalCities.remove("England");     
        capitalCities.clear();
        capitalCities.size();

        for (String i : capitalCities.keySet()) {
            System.out.println(i);
        }

        for (String i : capitalCities.values()) {
            System.out.println(i);
        }

        for (String i : capitalCities.keySet()) {
            System.out.println("key: " + i + " value: " + capitalCities.get(i));
        }
    }
}
```

Output:

```java  
{USA=Washington DC, Norway=Oslo, England=London, Germany=Berlin}
```

## LinkedHashMap and TreeMap

### LinkedHashMap

It is an implementation of the Map interface that includes Hashtable and LinkedList features. Therefore, the cycle order is predictable in this structure. The important difference of this structure from the HashMap structure is that it connects its elements to each other with a two-way link. Since it is a linked list, the order of the loop is the positions of the elements in the linked list. Of course, the position of the items is the order in which they were placed in the creation of the list.

The LinkedHashMap class avoids the ambiguous ordering of the HashMap class, allowing access to the elements of the collection in a predictable order. Of course, we can also provide this access order by converting the HashMap structure into a TreeMap structure. But, generally, the complexity of setting up the TreeMap structure is greater.

### Test  

1. Which of the following packages contains all collection classes?

    a. java.lang  
    b. java.util  
    c. java.net  
    d. java.awt  

2. Which of these classes is not part of Java's Collection class?

    a. Maps  
    b. list  
    c. Set  
    d. queue  

3. Within set collections, the same data is stored only once.

    a. TRUE  
    b. FALSE  

4. It does not guarantee that the data in the HashSet will be output in the order it was entered.

    a. TRUE  
    b. FALSE  

5. It guarantees that the data in the LinkedHashSet will be output in the order it was entered.

    a. TRUE  
    b. FALSE  

6. Which is incorrect about the TreeSet collection?

    a. It inherits from the set interface.  
    b. It ensures that the elements placed in the data set are sorted according to the rule you give.  
    c. The data class does not need to be enumerable.  
    d. Data that repeats itself does not hold.  

7. Which of the following Collection classes defines a dynamic array?

    a. ArrayList  
    b. HashSet  
    c. PriorityQueue  
    d. TreeSet  

8. Which of the following methods can be used to manually increase the capacity of the ArrayList object?

    a. Capacity()  
    b. increaseCapacity()  
    c. addCapacity()  
    d. ensureCapacity()  

9. Which of the following methods can be used to obtain a static array from an ArrayList object?

    a. array()  
    b. covertArray()  
    c. toArray()  
    d. covertoArray()  

10. Which of the following Collection classes implements the linked list data structure?

    a. AbstractList  
    b. LinkedList  
    c. HashSet  
    d. AbstractSet  

11. Which of the following methods is used to add an item to the beginning of a LinkedList object?

    a. add()  
    b. first()  
    c. AddFirst()  
    d. addFirst()  

12. Which of the following classes can be used to create a dynamic array?

    a. ArrayList  
    b. map  
    c. vector  
    d. ArrayList & Vector  

13. What is the difference between Queue and Stack?

    a. Stack is LIFO; Queue is FIFO  
    b. Queue is LIFO; Stack is FIFO  
    c. Stack and Queue are FIFO  
    d. Stack and Queue are LIFO  

14. Thanks to PriorityQueue, we can determine the order in the queue ourselves.

    a. TRUE  
    b. FALSE  

15. Which of the following classes stores the relationship between keys and values?

    a. hash table  
    b. map  
    c. Array  
    d. String  

16. Which of the following methods in the Map class is used to obtain an element with the specified key in the Map?

    a. search()  
    b. get()  
    c. set()  
    d. look()  

17. Is Hashmap class an ordered collection?

    a. TRUE  
    b. FALSE  

Answers: 1.b, 2.a, 3.a, 4.a, 5.a, 6.c, 7.a, 8.d, 9.c, 10.b, 11.d, 12.d, 13.a, 14.a, 15.b, 16.b, 17.b
