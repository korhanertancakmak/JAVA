# Others

## Type Casting

The process of converting a data type (int, float, double, etc.) to another data type 
is called "Type Casting."

### Widening Casting (Automatic)

It is the process of converting a small data type into a larger data type. 
In the case of Widening Casting, a lower data type (having smaller size) is converted 
to a higher data type (having larger size). 
Therefore, there is no loss of data. 
This is why this type conversion happens automatically.

byte -> short -> char -> int -> long -> float -> double

```java  
public class PatikaDev { 
    public static void main(String[] args) { 
        int myInt = 3; 
        double myDouble = myInt; 
        // Automatic Casting : int => double 
        System.out.println(myInt); 
        // Output 3 
        System.out.println(myDouble); 
        // Output 3.0    
    }
}
```

### Narrowing Casting (Manual)

It is the process of converting a large data type into a smaller data type.

double -> float -> long -> int -> char -> short -> byte

```java  
public class PatikaDev { 
    public static void main(String[] args) { 
        double myDouble = 3.78; 
        int myInt = (int) myDouble; 
        // Manuel Casting : double => int
        System.out.println(myDouble); 
        // Output 3.78 
        System.out.println(myInt); 
        // Output 3     
    }
}
```

### Integer => String Casting

```java  
public class PatikaDev { 
    public static void main(String[] args) { 
        int num = 10; 
        System.out.println("Integer değer : " + num); 
        String data = String.valueOf(num);
        System.out.println("String Değer : " + data);     
    }
}
```

### String => Integer Casting

```java  
public class PatikaDev { 
    public static void main(String[] args) { 
        String data = "15"; 
        System.out.println("String Değer : " + data); 
        int num = Integer.parseInt(data); 
        System.out.println("Integer Değer : " + num);     
    }
}
```

## Lambda Expressions

Lambda expression was first introduced in Java 8. 
Its main purpose was to increase the expressive power of the language. 
However, before we get started with lambdas, we need to understand Functional Interface.

### Functional Interface

Function interface is an interface that has only one abstract method. 
If there is an abstract method in the interface from which the relevant interface is derived, 
then it is a functional interface.

Functional interfaces are defined so that lambda expressions can be used.

When defining functional interfaces, it is not mandatory to use the @FunctionalInterface annotation. 
This annotation is used for validation only.

If an annotation is added and more than one abstract method is tried to be added,
then compile will give an error.

Example :

```java  
package java.lang; 
@FunctionalInterface 
public interface Runnable { 
    public abstract void run(); 
}

public class PatikaDev {
    public static void main(String[] args) {
        // Before Java 8
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Before 1.8");
            }
        };
        runnable1.run();

        // After Java-8
        Runnable runnable2 = () -> System.out.println("1.8");
        runnable2.run();
    }
}
```

### Functional Interface Example

```java  
@FunctionalInterface 
public interface Mathematics { 
    public abstract int transaction(int a, int b); 
}
```

### Old Usage

```java  
public class PatikaDev { 
    public static void main(String[] args) {
        Mathematics summation = new Mathematics() { 
            @Override 
            public int transaction(int a, int b) { 
                return a + b;             
            }
        };
        Mathematics abstraction = new Mathematics() { 
            @Override public int transaction(int a, int b) { 
                return a - b;             
            }
        };
        Mathematics division = new Mathematics() { 
            @Override public int transaction(int a, int b) { 
                if (b == 0) { 
                    b = 1;                 
                }
                return a / b;             
            }
        };
        Mathematics multiplication = new Mathematics() { 
            @Override 
            public int transaction(int a, int b) { 
                return a * b;             
            }
        };
        System.out.println(toplama.transaction(10 , 2)); 
        System.out.println(cikarma.transaction(10 , 2)); 
        System.out.println(bolme.transaction(10 , 2)); 
        System.out.println(carpma.transaction(10 , 2));     
    }
}

```

### Lambda Usage

```java  
public class PatikaDev { 
    public static void main(String[] args) { 
        Matematik toplam = (a, b) -> a + b;
        Matematik cikarma = (a, b) -> a - b;
        Matematik carpma = (a, b) -> a * b;
        Matematik bolme = (a, b) -> {
        if (b == 0) { b = 1;             
        }
        return a / b;         
        };
        System.out.println(toplam.transaction(10, 2)); 
        System.out.println(cikarma.transaction(10, 2)); 
        System.out.println(carpma.transaction(10, 2)); 
        System.out.println(bolme.transaction(10, 2));     
    }
}
```

## Stream API

After lambda expressions were added to Java with JDK 8, 
Stream API was written in connection with this. Simply put, 
it added methods that allow us to operate on collections using lambda expressions.

Stream means flow. 
A flow is created by the succession of objects. 
By creating a flow, we can perform operations on the elements of an array or collection. 
Streams don't care about how data is stored, they just transfer data from one place to another. 
It is possible that one or more operations will be performed on the data during this transfer. 
This process may include filtering, sorting or transforming data. 
This process does not change the source of the stream; but it creates a new flow. 
For example, if you sort objects within a stream, the source does not change;
but a new stream of ordered objects is created.

With JDK 8, we can express streams as an object of type Stream. 
Although Stream API is a very comprehensive topic, 
we will only examine operations performed on collections.

To stream a collection, a new method called stream() was added 
to the Collection interface in JDK 8. 
The structure of this method is as follows:

```java  
interface Collection<T> { 
    Stream<T> stream(); 
}
```

Using this method, we can create a new flow for a collection. 
Each time this method is called, a new stream is created on the collection.

Now let's examine the most used methods of the Stream interface.
We will use the same list in all of these methods. 
Let's create this list first:

```java  
ArrayList<Integer> list = new ArrayList<>(); 
list.add(25); 
list.add(12); 
list.add(3); 
list.add(89); 
list.add(25); 
list.add(44); 
list.add(100); 
list.add(7); 
list.add(63);
```

### forEach()

Using this method, you can perform an operation on all elements of the flow. 
It takes a parameter of type Consumer < T >. 
This method is a method that terminates the stream, 
meaning you cannot perform any other action on the stream after using this method.

```java  
list.stream().forEach(number -> System.out.println(number));
```

As you can see, we print all the elements of the stream to the console using the forEach() method. 
When you run this code, the output is as follows:

```java  
25 12 3 89 25 44 100 7 63
```

### filter()

You can filter the elements of the stream using this method. 
It takes a parameter of type Predicate < T >. 
Elements that do not comply with this test are not included in the flow.

```java  
list.stream()
.filter(number -> number > 60)
.forEach(number -> System.out.println(number));
```

Here, we want to print only numbers greater than 60 to the console using the filter() method. 
When you run this code, the output is as follows:

```java  
89 100 63
```

### distinct()

By using this method, you can ensure that each element is included in the flow at most once. 
If an element has been defined before in the flow, it will not appear a second time. 
It does not take parameters.

```java  
list.stream()
.distinct()
.forEach(number -> System.out.println(number));
```

If you run this code, you'll see that the number 25 added to the list twice is printed 
to the console only once:

```java  
25 12 3 89 44 100 7 63
```

### sorted()

You can sort the elements of the flow using this method.

```java  
list.stream()
.sorted()
.forEach(number -> System.out.println(number));
```

When you run this code, the output is as follows:

```java  
3 7 12 25 25 44 63 89 100
```

There is another version of this method that takes a parameter of type Comparator < T >. 
Using this version, you can change the sorting algorithm of the stream.

```java  
list.stream()
.sorted(Comparator.reverseOrder())
.forEach(number -> System.out.println(number));
```

For example, if you run this code, you'll see the elements printed 
to the console in ascending order:

```java  
100 89 63 44 25 25 12 7 3
```

### limit()

Using this method, you can limit the operations you can perform on the flow to a certain number. 
It takes a number of types long as a parameter.

```java  
list.stream()
.limit(5L) .forEach(number -> System.out.println(number));
```

If you run this code, you'll see that only the first five elements are printed to the console:

```java  
25 12 3 89 25
```

### skip()

Using this method, you can skip a certain number of elements of the flow. 
No action is taken on these elements. 
It takes a number of types long as a parameter.

```java  
list.stream()
.skip(5L) .limit(2L) 
.forEach(number -> System.out.println(number));
```

Here, we skip the first five elements of the stream and print the next two elements to the console:

```java  
44 100
```

### count()

Using this method, you can find out the number of elements in the flow. 
This method is a method that terminates the stream, 
meaning you cannot perform any other action on the stream after using this method.

```java  
long count = list.stream()
.filter(number -> number < 40) .distinct()
.count();
System.out.println(count);
```

Here, we print to the console how many different numbers less than ***40*** are in the list. 
If you run this code, it writes ***4*** to the console.

### anyMatch()

It takes a parameter of type Predicate < T > and performs this test on all elements of the flow. 
Returns ***true*** if any of the elements pass this test, ***false*** otherwise. 
This method is a method that terminates the stream, 
meaning you cannot perform any other action on the stream after using this method.

```java  
boolean match = list.stream()
.anyMatch(number -> number < 5); 
System.out.println(match);
```

Here, we test if there are numbers less than five in the list. 
There are only three less than five in the list. 
But even this is enough for the method to return ***true***. 
If you run this code, it writes ***true*** to the console.

### allMatch()

It takes a parameter of type Predicate < T > and performs this test on all elements of the flow. 
It returns ***true*** if all the elements pass this test, otherwise ***false***. 
This method is a method that terminates the stream, 
meaning you cannot perform any other action on the stream after using this method.

```java  
boolean match = list.stream()
.allMatch(number -> number < 5); 
System.out.println(match);
```

If you run this code, it will write ***false*** to the console, 
because there are also elements larger than 5 in the list.

### noneMatch()

It takes a parameter of type Predicate < T > and performs this test on all elements of the flow. 
Returns ***true*** if none of the elements pass this test, ***false*** otherwise. 
This method is a method that terminates the stream, 
meaning you cannot perform any other action on the stream after using this method.

```java  
boolean match = list.stream()
.noneMatch(number -> number < 5); 
System.out.println(match);
```

If you run this code, it will write false to the console, 
because there are less than five elements in the list.

### map()

You can use this method to change the elements of the flow. 
It takes a parameter of type Function < T, R > and applies 
this function to all elements of the flow. 
The new elements of the flow are the values returned from this method.

```java  
list.stream()
.map(number -> number * 2)
.forEach(number -> System.out.println(number));
```

In this example, we multiplied all the elements of the flow by 2. 
If you run this code, the output will be as follows:

```java  
50 24 6 178 50 88 200 14 126
```

It is also possible to change the type of elements inside the stream using this method:

```java  
list.stream()
.map(number -> Math.sqrt(number))
.forEach(number -> System.out.println(number));
```

Here we change the type of the stream from Integer to Double. 
If you run this code, the output will be as follows:

```java  
5.0 
3.4641016151377544
1.7320508075688772 
9.433981132056603 
5.0 
6.6332495807108 
10.0 
2.6457513110645907 
7.937253933193772
```

## Enum Class

In Java, Enum types are used to express predefined fixed values. 
For example, the most classic example is the days of the week. 
Since it is known how many days a week has and which days it consists of, 
we can express this with a class as follows.

```java  
public final class Gun {
    public static final Gun PAZARTESI = new Gun(1);
    public static final Gun SALI = new Gun(2);
    public static final Gun CARSAMBA = new Gun(3);
    public static final Gun PERSEMBE = new Gun(4);
    public static final Gun CUMA = new Gun(5);
    public static final Gun CUMARTESI = new Gun(6);
    public static final Gun PAZAR = new Gun(7);
    private final int day;
    private Gun(int f) {
        this.day = f;
    }
    public int getDay() {
        return day;
    }
}
```

For classes consisting only of constants in the relevant example, 
ENUM classes are used in Java and are easier to use.

```java  
public enum Day {
    PAZARTESI(1),
    SALI(2),
    CARSAMBA(3),
    PERSEMBE(4),
    CUMA(5),
    CUMARTESI(6),
    PAZAR(7);
    private int day;
    private Day(int day) {
        this.day = day;
    }
    public int getDay() {
        return day;
    }
}
```

```java  
public class PatikaDev {
    public static void main(String[] args) {
        Day gunPzt = Day.PAZARTESI;
        System.out.println(gunPzt.ordinal()); // Dizideki indeksini verir.
        System.out.println(gunPzt.name()); // Sabit'in adını döndürür.
        Day[] values = Day.values(); // Tüm sabitlerini döndürür.
        Day gun = Day.valueOf("PAZARTESI"); // String'i enum'a dönüştürür.
    }
}
```

## Test

1. Which is required for automatic type conversion in Java?

    a. The target data type must be smaller than the source data type.  
    b. The target data type must be larger than the source data type.  
    c. The target data type may be larger or smaller than the source data type.  
    d. None  

2. If an expression contains double, int, float, long, the entire expression is 
promoted to which of these data types?

   a. long  
   b. int  
   c. double  
   d. float  

3. On which interfaces can lambda expressions be implemented in Java?

   a. Functional Interface  
   b. Runnable Interface  
   c. Stream Interface  
   d. Collection Interface  

4. On which interfaces can lambda expressions be implemented in Java?

   a. ->  
   b. ==  
   c. =>  
   d. <>

5. Can we create an Enum object outside the Enum itself?

   a. TRUE  
   b. FALSE

6. Does enum ensure type safety?

   a. TRUE  
   b. FALSE

Answers: 1.b, 2.c, 3.a, 4.a, 5.b, 6.a

