# Nested Classes and Generics

So far we know that variables and methods can be found inside classes. But the power of classes is not limited to this. We can also define other classes within classes. In this way, we can create **nested classes**.

When describing nested classes, we call the class that contains the other as the **outer class**, and the class that is inside the other as the **inner class**. We said that when a class is created, all its fields and methods are loaded into memory. This also applies to inner classes. In nested classes, an instance of the outer class must be taken before we can use the inner class.

The inner class can access all fields and methods of the outer class. Although the outer class is single, multiple instances of the inner class can be taken; In such cases, objects created from the inner class all access the same outer class.

So when should we use nested classes? If the existence of a class depends on another class and it is not possible for it to exist alone, we can use nested classes. For example, if we consider a university, if each department needs a faculty to exist and there is no point in the existence of this department without a faculty, nested classes can be used. However, it would be a wrong approach to use nested classes for departments independent of the faculty or departments affiliated with more than one faculty.

```java  
class Outer {
    public void run() {
        System.out.println("Outer Class's method worked.");
        Inner inner = new Inner();
        inner.run();
    }

    class Inner {
        public void run() {
            System.out.println("Inner Class's method worked.");
        }
    }
}
```

In the example above, we created 2 nested classes. We wrote methods called run() for both.

```java  
Outer outer = new Outer();
outer.run();
```

When we run the above code, the output is as follows:

```html  
Outer Class's method worked.
Inner Class's method worked.
```

Now let's examine the example below:

```java  
class Outer {
    private int number = 10;

    public void run() {
        System.out.println(number);
        
        Inner inner = new Inner();
        inner.run();

        System.out.println(number);
    }

    class Inner {
        public void run() {
            number++;
        }
    }
}
```

In the example above, we created a variable named number inside the outer class. In the inner class, we accessed this variable and increased its value by 1. When you run the run() method of the outer class, the output is as follows:

```html  
10
11
```

As you can see from here, inner classes can access elements of outer classes.

There are some points we need to pay attention to about nested classes. First, we cannot get an instance of the inner class without the outer class. Also, we cannot write a static variable or method inside the inner class. To avoid these restrictions, we must define the inner class statically.

## Making The Inner Class Static

You remember that in order to use a method, we had to get an instance of the class in which it was defined. To avoid this, we declared the method statically. The same applies to the inner classes: in order to use these classes, we need to take an instance of the outer class. To avoid this, we must define the inner class as static:

```java  
class Outer {
    static class Inner {
        public void run() {
            System.out.println("Inner Class's method worked.");
        }
    }
}
```

Now we can run the following code:

```java  
Outer.Inner inner = new Outer.Inner();
inner.run();
```

As you can see, we no longer need the outer class to get an instance of the inner class. But remember that static methods cannot access non-static fields of the class. The same applies here: inner static classes cannot access non-static fields of the outer class:

```java  
class Outer{
    private int number = 10;

    public void run() {
        System.out.println(number);
        
        Inner inner = new Inner();
        inner.run();

        System.out.println(number);
    }

    static class Inner {
        public void run() {
            number++; // This causes an error
        }
    }
}
```

The above code causes error; because the statically determined inner Inner class is trying to access the non-static number variable of the outer class.

## Making the Inner Class Private

While inner classes can be made static, private and protected access specifiers can also be used to prevent external access to the inner class.

```java  
class Outer {
  int x = 10;

  private class Inner {
    int y = 5;
  }
}

public class JAVA.MyList.Main {
  public static void main(String[] args) {
    Outer outer = new Outer();
    Outer.Inner inner = outer.new Inner();
    System.out.println(inner.y + outer.x);
  }
}
```

The above code causes an error because it wants to access a private class from outside.

## Variable number of method arguments (Varargs: variable-length arguments)

We have already seen how methods take parameters. For example, the collection method we define below takes 2 parameters:

```java  
public int add(int x, int y) {
    return x + y;
}
```

This method adds 2 numbers and returns the result. So, what if we want to add 3 numbers? We have seen that we can overload methods in Java. Now let's overload this method as follows:

```java  
public int add(int x, int y) {
    return x + y;
}

public int add(int x, int y, int z) {
    return x + y + z;
}
```

Now there are different variations of the same method that take 2 and 3 parameters. But what if we want to add 4 numbers? We can continue to overload the method in the same way:

```java  
public int add(int x, int y) {
    return x + y;
}

public int add(int x, int y, int z) {
    return x + y + z;
}

public int add(int x, int y, int z, int t) {
    return x + y + z + t;
}
```

But you must have noticed that this method is not very pleasant. When we want to add 5 or 6 numbers we need to overload the method again. This causes code redundancy. The problem here is that the number of parameters the method will take is uncertain (variable). There is more than one addend in the addition process: but the number may vary. Java allows the method to take a **variable number of arguments (varargs)** for such cases where we cannot know the number of parameters in advance.

To define a variable number of parameters, we put 3 dots after the variable type. Now, when calling the method, we can give it as many parameters as we want: we do not need to overload the method for each of them. Java presents these parameters to us as an array.

```java  
public int add(int... numbers){
    int sum = 0;
    for (int number : numbers) {
        sum += number;
    }
    return sum;
}
```

We rearranged our add() method above and ensured that the number of elements included in the addition process was variable. Now we can give different numbers of aggregates as parameters to the method:

```java  
System.out.println(add(2, 3));           // Outputs 5 to the console
System.out.println(add(1, 5, 7));        // Outputs 13 to the console
System.out.println(add(9));              // Outputs 9 to the console
System.out.println(add(10, 15, 20, 25)); // Outputs 70 to the console
```

A variable number of arguments are presented as an array. The array may not have an element. That is, even if we do not give any arguments, the method that takes a variable number of arguments will still work.

```java  
System.out.println(add()); // Outputs 0 to the console
```

Even though we did not give any arguments above, we see that the method still works. This should be taken into consideration when writing methods that take a variable number of arguments.

You can overload methods that take a variable number of parameters. Let's examine the example below:

```java  
public int add(int... numbers) {
    int sum = 0;
    for (int number : numbers) {
        sum += number;
    }
    return sum;
}

public double add(double... numbers) {
    double sum = 0.0;
    for (double number : numbers) {
        sum += number;
    }
    return sum;
}
System.out.println(add(5, 4, 3));       // Outputs 12 to the console
System.out.println(add(9.0, 8.5, 8.0)); // Outputs 25.5 to the console
```

## HackerRank Exercise : Can You Access?

## Wrapper Classes

Thanks to Wrapper classes, they are classes that allow us to use primitive data types (int, byte, sort, etc.) as objects.

| Primitive Data Types | Wrapper Classes |
| -------------------- | --------------- |
|  byte                |  Byte           |
|  short               |  Short          |
|  int                 |  Integer        |
|  long                |  Long           |
|  float               |  Float          |
|  double              |  Double         |
|  boolean             |  Boolean        |
|  char                |  Character      |

## Autoboxing and Unboxing

Above we saw how to convert between primitive data types and wrapper classes. In order to do this more quickly, the **boxing** and **unboxing** feature was introduced with JDK 5. This way, you can assign primitive data types to wrapper classes without any additional processing.

```java  
byte primitiveByte = 1; 
Byte byteObject = primitiveByte;
byte byteValue = byteObject; 

short primitiveShort = 1; 
Short shortObject = primitiveShort;
short shortValue = shortObject; 

int primitiveInt = 1; 
Integer intObject = primitiveInt;
int intValue = intObject; 

long primitiveLong = 1L; 
Long longObject = primitiveLong;
long longValue = longObject; 

float primitiveFloat = 1.0f; 
Float floatObject = primitiveFloat;
float floatValue = floatObject; 

double primitiveDouble = 1.0d; 
Double doubleObject = primitiveDouble;
double doubleValue = doubleObject; 

char primitiveChar = 'a'; 
Character charObject = primitiveChar;
char charValue = charObject; 

boolean primitiveBoolean = true; 
Boolean booleanObject = primitiveBoolean;
boolean booleanValue = booleanObject;
```

## Generic Classes

Generics literally mean **parameterized** type. Thanks to generics, when writing a class, interface or method, you can take the type you will operate on as a parameter rather than sticking to a single type. This way it is possible to write a single class that works on different types. Classes written in this way are called **generic classes**, and methods are called **generic methods**.

Generics were added to the language with JDK 5. Despite this, it is one of the most fundamental features of Java and has fundamentally influenced the language. Therefore, understanding generics well is of great importance in learning Java.

Generics were added to the language to ensure **type-safety**. We have previously mentioned that Java is a **strongly typed** language. But these two concepts are different from each other. Let's do an example to understand the concept of type safety and why it is necessary.

Let's write a simple class called **Nullable**. We will use this class to prevent NullPointerException errors. First, let's use this class on String values:

```java  
public class Nullable {
    private final String value;

    public Nullable(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public boolean isNull() {
        return value == null;
    }
    
    @Override
    public String toString() {
        return isNull() ? "null" : value;
    }
}
```

We will use this class to avoid null errors when dealing with String objects. When creating the class, we will give a **String** value as a parameter. We will check whether this value is **null** or not with the **isNull()** method. Now let's show an example usage:

```java  
Nullable x = new Nullable("value is not null");

if (!x.isNull()) {
    System.out.println(x.getValue());
}

String nullString = null;

Nullable y = new Nullable(nullString);

if (y.isNull()) {
    System.out.println("variable y is null");
}
```

When you run the above code, the output is as follows:

```java  
value is not null
variable y is null
```

As you can see, the class we wrote works beautifully. What if we want to use this class for types other than String? Currently, the Nullable class accepts only String values. So we cannot use this class on Date type values. So let's change the name of this class to **NullableString** and create another class called **NullableDate**:

```java  
import java.util.Date;

public class NullableDate {

    private final Date value;
    
    public NullableDate(Date value) {
        this.value = value;
    }

    public Date getValue() {
        return value;
    }

    public boolean isNull() {
        return value == null;
    }

    @Override
    public String toString() {
        return isNull() ? "null" : value.toString();
    }
}
```

As you can see, we rewrote the same class just changing the **String** type to **Date**. You must admit that this is not a good method. Not only did we end up with code duplication, but we also had to give classes different names even though they were related. Now we can use the **NullableString** class for the **String** type and the **NullableDate** class for the **Date** type.

However, we still haven't found an ideal solution. Because we can use the Nullable class for only 2 types. But what if we want to use this class for other types such as Integer, Double, Boolean? We need to copy the same code and create different classes for all of them.

What we want to do is write a **Nullable** class that will be valid for all types. We can achieve this as follows: Let's make the **Nullable** class operate on the **Object** type. As you know, the **Object** class is the ancestor of all classes. Therefore, we can express all types as **Object**. Now let's edit the class and rewrite it:

```java  
public class Nullable {
    private final Object value;
    
    public Nullable(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public boolean isNull() {
        return value == null;
    }

    @Override
    public String toString() {
        return isNull() ? "null" : value.toString();
    }
}
```

Now let's use this class on different types:

```java  
Nullable nullableString = new Nullable("abc"); 
Nullable nullableDate = new Nullable(new Date()); 
Nullable nullableInt = new Nullable(2020); 
Nullable nullableDouble = new Nullable(3.14); 
Nullable nullableBoolean = new Nullable(true);
```

As you can see above, we can use the **Nullable** class on different types. But we still have a problem: When we call the **getValue()** method, we have to convert the resulting value:

```java  
Nullable nullableString = new Nullable("abc");

if (!nullableString.isNull()) {
    String value = (String) nullableString.getValue();
}
```

This is an important gap. Because of this vulnerability, we may cause errors without realizing it. For example, let's examine the following code:

```java  
Nullable nullableString = new Nullable("abc");

if (!nullableString.isNull()) {
    boolean value = (boolean) nullableString.getValue();
}
```

In this example, while the value we give as a parameter to the **Nullable** class is **String**, we are trying to convert this value to **boolean** type. In this case, we encounter the **ClassCastException** error we saw above.

We understand from this example that although we developed the Nullable class to include all types, we could not ensure type safety. Before JDK 5, such situations occurred frequently and type-unsafe codes were written. It has been possible to prevent this with generics. Generics, on the one hand, allow us to write a single code for different types, and on the other hand, they ensure type safety.

## Generic Methods

When writing a method, you can make the method generic if the class it is in is not generic. To do this, simply specify the type parameter before the return type of the method. For example, let's write a class called **ArrayUtil**. Let's have a method called **arrayContains()** in this class. This method tests whether any element is in the array.

```java  
public class ArrayUtil { 
    public <T> boolean arrayContains(T[] array, T elem) { 
        for (T item : array) {
            if (item != null && item.equals(elem)) {
                return true;
            }
        }
        return false;   
    }
}
```

As you can see, we made our method generic even though the class is not generic. Now we can use this method for arrays of any type.

You can also use the same features in generic methods that are valid when writing a generic class. For example, you can limit the generic type or use a wildcard parameter. You can also create generic constructors.

## HackerRank Exercise : Java Generics

## Generic Interface

Let's consider below a IDatabase interface that has insert() method for adding data to the database, delete() method for deleting data from the database, update() method for updating the data in the database, and select() method for getting data from the database.  

```java  
public interface IDatebase {

    public boolean insert();
    public boolean delete();
    public boolean update();
    public String select();
}
```

We expect the selected data as an specific object such as String, Integer, etc. But, we cannot know it before we get it. For example, we can get student data type that includes student's names, ages, etc. and we can create a student object. So, in that case the return type would be a student object, here we need to generalize it. We can do this just like we did it in the classes. So when we rewrite it below:

```java  
public interface IDatebase <T> {

    public boolean insert(T data);
    public boolean delete(T data);
    public boolean update(T data);
    public T select();
}
```

So, if we get a class named student into this interface, insert, delete, update and select statements of the student is provided now. Let's create a student class that implements this interface now:

```java  
public class JAVA.PriorityQueueJava.Student implements IDatabase<JAVA.PriorityQueueJava.Student>{

@Override
public boolean insert(JAVA.PriorityQueueJava.Student data) {
    return false;
}

@Override
public boolean delete(JAVA.PriorityQueueJava.Student data) {
    return false;
}

@Override
public boolean update(JAVA.PriorityQueueJava.Student data) {
    return false;
}

@Override
public JAVA.PriorityQueueJava.Student select() {
    return false;
}
}
```

If we would use String instead of JAVA.PriorityQueueJava.Student objects, we should write this class just like below:

```java  
public class JAVA.PriorityQueueJava.Student implements IDatabase<String>{

    @Override
    public boolean insert(String data) {
        return false;
    }

    @Override
    public boolean delete(String data){
        return false;
    }

    @Override
    public boolean update(String data){
        return false;
    }

    @Override
    public String select(){
        return false;
    }
}
```

What if we would use again **T** generic type in this class by implementing the generic interface above;

```java  
public class JAVA.PriorityQueueJava.Student implements IDatabase<T>{
    // This design throws a error!
}
```

We then get error, because there is no such a design. For this purpose, we should rewrite the Class data type as below:

```java  
public class JAVA.PriorityQueueJava.Student<T> implements IDatabase<T>{
    @Override
    public boolean insert(T data) {
        System.out.println("data");
        System.out.println("The data is added!")
        return true;
    }

    @Override
    public boolean delete(T data){
        System.out.println("The data is removed!")
        return true;
    }

    @Override
    public boolean update(T data){
        System.out.println("The data is updated!")
        return true;
    }

    @Override
    public T select(){
        System.out.println("The data is obtained!")
        return null;
    }
}
```

Now, we provided a generic object from a generic interface, here. If we get into the main method of a JAVA.MyList.Main Class:

```java  
public class Codes.MyList.

Main {
    public static void main (String[]args){
        JAVA.PriorityQueueJava.Student<String> std = new JAVA.PriorityQueueJava.Student<>();
        std.insert("ABC");
    }
}
```

We would get Strings from **T** types in the JAVA.PriorityQueueJava.Student objects and we don't get any errors. And we would see this output:

```java  
ABC
The data is added!
```

Instead, we could define the data type in main method as Integer like the following:

```java  
public class Codes.MyList.

Main {
    public static void main (String[]args){
        JAVA.PriorityQueueJava.Student<Integer> std = new JAVA.PriorityQueueJava.Student<>();
        std.insert("123");
    }
}
```

We could get now this following output:

```java  
123
The data is added!
```

## Generic Bounded Types

You can limit the type when specifying it as a parameter to a generic class or method. For example, you can say, as a parameter, let the type be one of the subclasses of the **Number** class. In such a case, you cannot give any class as a parameter that is not derived from the **Number** class.

The **extends** or **super** statement is used when limiting the type. After these statements, a class or explanations are given. We can use these expressions generically delimited using parameters:

* We can use the **extends** statement and use only the types or subclasses we can use. This is called the ***upper limit***.

* We can use the **super** clause and use only the superclasses of the type we are left with. This is called the ***lower limit***.

Now an example can be by updating the Nullable class we wrote above:

```java  
public class Nullable<T extends Number> {
    private final T value;
    
    public Nullable(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isNull() {
        return value == null;
    }

    @Overridepublic String toString() {
        return isNull() ? "null" : value.toString();
    }
}
```

As you can see above, we limited the type parameter we took to the generic Nullable class with the ***extends*** clause. Accordingly, we can only use this class with Number or its subtypes.

```java  
Nullable<Integer> nullableInteger = new Nullable<Integer>(2020);
// This usage is convenient; because Integer is derived from Number class

Nullable<String> nullableString = new Nullable<String>("2020"); 
// This user is not eligible; String is derived from the Number class
```

## HomeWork - Writing Our Own List Class

We design a class in Java where we store data using the generic structure.

The purpose of the class is to hold a dynamic Array and get the data type dynamically. For this, it is necessary to create a generic class.

***NOTE: COLLECTION CLASS WILL NOT BE USED! WE MUST CREATE OUR OWN LIST CLASS.***

Class features:

* The default size of the array within the class is 10, and the number of elements of the array should increase by a factor of 2 as needed.

* There must be two types of constructor methods for the class.

* JAVA.MyList.MyList(): If an empty contructor is used, the initial size of the array must be 10.

* JAVA.MyList.MyList(int capacity): The initial value of the array must be taken from the capacity parameter.

* size() : returns the number of elements in the array.

* getCapacity(): returns the capacity value of the array.

* add(T data): should be used to add elements to the array of the class. If there is not enough space in the array, the array size should be doubled.

Example:

```java  
import JAVA.MyList.MyList;

public class Main {
    public static void main(String[] args) {
        MyList<Integer> liste = new MyList<>();
        System.out.println("The number of elements in array : " + liste.size());
        System.out.println("The capacity of array : " + liste.getCapacity());
        liste.add(10);
        liste.add(20);
        liste.add(30);
        liste.add(40);
        System.out.println("The number of elements in array : " + liste.size());
        System.out.println("The capacity of array : " + liste.getCapacity());
        liste.add(50);
        liste.add(60);
        liste.add(70);
        liste.add(80);
        liste.add(90);
        liste.add(100);
        liste.add(110);
        System.out.println("The number of elements in array : " + liste.size());
        System.out.println("The capacity of array : " + liste.getCapacity());
    }
}
```

Output:  

```java  
The capacity of array : 10
The number of elements in array : 0
The capacity of array : 10
The number of elements in array : 4
The capacity of array : 20
The number of elements in array : 11
```

* get(int index): Returns the value at the given index. If an invalid index is entered, it returns null.

* remove(int index): It should delete the data in the given index and shift the data after the deleted index to the left. If an invalid index is entered, it returns null.

* set(int index, T data) : It must replace the data in the given index with the new one. If an invalid index is entered, it returns null.

* String toString() : A method that lists the elements in the array belonging to the class.

Example:

```java  
import JAVA.MyList.MyList;

public class Main {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("The value at 2. index : " + list.get(2));
        list.remove(2);
        list.add(40);
        list.set(0, 100);
        System.out.println("The value at 2. index : " + list.get(2));
        System.out.println(list.toString());
    }
}
```

Output:  

```java  
The value at 2. index : 30
The value at 2. index : 40 
[100,20,40]
```

* int indexOf(T data): Returns the index of the object given in the parameter in the list. Returns -1 if the object is not in the list.

* int lastIndexOf(T data): Tells the last index of the specified item in the list. Returns -1 if the object is not in the list.

* boolean isEmpty(): Tells whether the list is empty or not.

* T[] toArray(): Converts the items in the list into an array in the same order.

* clear(): Clears all items in the list, turning it into an empty list.

* JAVA.MyList.MyList< T > sublist(int start,int finish) : Returns a list of the index range given in the parameter.

* boolean contains(T data): Tells whether the value given in the parameter is in the array.

Example:

```java  
import JAVA.MyList.MyList;

public class Ata {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        System.out.println("List status : " + (list.isEmpty() ? "Empty" : "Full"));
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(20);
        list.add(50);
        list.add(60);
        list.add(70);

        System.out.println("List status : " + (list.isEmpty() ? "Empty" : "Full"));

        // Returns the first index it finds
        System.out.println("Index : " + list.indexOf(20));

        // If not found it returns -1
        System.out.println("Index :" + list.indexOf(100));

        // Returns the last index it finds
        System.out.println("Index : " + list.lastIndexOf(20));

        // Returns the list as an Object[] array.
        Object[] array = list.toArray();
        System.out.println("The first element of the Object array :" + array[0]);

        // Creating a sublist of list data type
        MyList<Integer> subMyArray = list.subList(0, 3);
        System.out.println(subMyArray.toString());

        // Queried whether the value is in the list
        System.out.println("Value 20 on my list : " + list.contains(20));
        System.out.println("Value 120 on my list : " + list.contains(120));

        // Empties the list completely and returns it to its default size
        list.clear();
        System.out.println(list.toString());

    }
}

```

Output:

```java  
List status : Boş
List status : Dolu
Index : 1
Index :-1
Index : 4
The first element of the Object array :10 
[10,20,30,40]
Value 20 on my list : true
Value 120 on my list : false []
```

### Test

1. Which of the following defines Nested classes?

    a. class within class  
    b. Method in class  
    c. package in class  
    d. Variable within class  

2. Which feature of OOP reduces the use of nested classes?

    a. encapsulation  
    b. Inheritance  
    c. abstraction  
    d. polymorphism  

3. How many main categories are nested classes divided into?

    a. 2  
    b. 3  
    c. 4  
    d. 5  

4. Which of the following is a wrapper class for int data type?

    a. Integer  
    b. int  
    c. Number  
    d. Byte  

5. What is the purpose of wrapper classes?

    a. Making primitive data types behave like objects.
    b. It is used to convert data types into other data types.  
    c. It allows writing special classes for data types.  
    d. Creating our own data types.  

6. What is the purpose of the concepts of Autoboxing and Unboxing?

    a. It is a structure that automatically performs conversions between Wrapper classes of primitive data types.  
    b. It is a structure that automatically converts primitive data types into other data types.  
    c. Wrapper is a structure that automatically produces objects from classes.  
    d. It is the structure that enables conversion between data types.  

7. Which one(s) is true about generics?  

    I - Provides type safety  
    II - Cannot take more than one type parameter  
    III - Prevents code repetition  

    a. Only I  
    b. Only II  
    c. I, III  
    d. II, III  
    e. I, II, III

8. Which of the following is not one of the advantages of the diamond operator?

    a. It allows the Java compiler to automatically detect the generic type.  
    b. Prevents unchecked alerts in the program.  
    c. It is easier to create objects with this operator.  
    d. It simplifies the use of generics when creating an object.  

Answers: 1.a, 2.b, 3.a, 4.a, 5.b, 6.a, 7.c, 8.c
