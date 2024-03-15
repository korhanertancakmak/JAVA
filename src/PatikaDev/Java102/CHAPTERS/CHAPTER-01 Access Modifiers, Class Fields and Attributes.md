# Access Modifiers

In Java, **Access Modifiers** are used to access the attributes and behaviors of a class. Access specifiers are written in front of variables, methods and classes, and they determine the areas where the objects they are written in can be accessed. There are 3 access specifiers in Java: **public**, **private** and **protected**.

## Private Modifier

In Java, the **private** statement defines that the element in which it is written is directly accessible only from the class to which it belongs and cannot be directly accessed from code snippets outside that class. According to the **wrapping** principle, which is one of the basic principles of **Object-Oriented Programming**, variables within the class must be **directly** accessible only within the class. Therefore, variables are often declared "**private**". Sometimes, we define variables or methods that we want to be called only in that class as **private**.

## Public Modifier

In Java, the "**public**" statement ensures that the element in which it is written is directly accessible not only to the class it is in, but also to other classes. "**Public Access Editor**" is used for objects belonging to classes and methods that are intended to be used by other objects.

## Protected Modifier

In Java, the "**protected**" statement is an access modifier that falls between **public** and **private**. Elements defined with Protected are directly accessed by classes that have the same **package**.

## Default (Package-Private)

If we do not write an access modifier in front of any element in the codes we write, the access of that element is limited to the package it belongs to. That element is accessed from another class in the same package.

All way of use

| Access Modifiers  | Access inside the class | Access outside the class | Access outside the package by inheritance | Access outside the package |
| ----------------- | ----------------------- | ------------------------ | ----------------------------------------- | -------------------------- |
| Private           |           Yes           |            No            |                      No                   |             No             |
| Default           |           Yes           |            Yes           |                      No                   |             No             |
| Protected         |           Yes           |            Yes           |                      Yes                  |             No             |
| Public            |           Yes           |            Yes           |                      Yes                  |             Yes            |

## Static Keyword

In Java, the Static statement is used when defining class variables or class methods. If "static" is written before the variables belonging to a class, those variables become class variables. Variables defined as class variables do not occur separately each time we create an object. No matter how many objects belong to the class, there is only one class variable. This variable can be accessed through any object belonging to the class. Another feature of class variables is that they take up space in memory even if no objects of the relevant class are created.

```java  
public class Player {
    static int onlinePlayers;

    Player() {
        onlinePlayers++;
    }   

    public static void main(String[] args) {
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        System.out.println("Online players : " + Player.onlinePlayers);
    } 
}
```

If the "static" statement is written in front of one or more of the methods belonging to the class, those methods become class methods. The most important feature of class methods is that the class method can be called without creating an object of the relevant class. We said that a class variable occupies physical space in memory even if the object has not been created yet. We stated that a class method can be called via the class name without creating an object. In this case, it is not possible for class methods that can be called before the object exists to access event variables that cannot exist in memory without the object existing. Similarly, methods that operate on class variables that exist in memory without the object existing must be able to be called without the object existing.

```java  
public class Course {   
    String name, code, prefix;   
    int note;   
    public Course(String name, String code, String prefix) {     
       this.name = name;     
       this.code = code;     
       this.prefix = prefix;     
       this.note = 0;   
    }   
    public static void courseList() {     
       String[] courseList = {"physics", "chemistry", "mathematics"};     
       for (String courseName : courseList) {
       System.out.println(courseName);     
       }
    }   
    public static void main(String[] args) {     
       Course c1 = new Course("Math-101" , "MATH" , "MATH");     
       Course.courseList();   
    } 
}
```

## Static Code Blocks

We can use constructor methods to initialize class variables. However, it may be wrong to try to give the initial values ​​of the static variables of the class in the constructor. Because class variables can be used even if no object has been created. Code segments that are desired to be executed only once regarding class variables can be coded in **static code** blocks. **Static code blocks** are executed as soon as the class is loaded into memory. Thus, class variables receive their initial values ​​as soon as they are created in memory.

```java  
public class Writer {
    private String firstName;
    private String lastName;

    public Yazar(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInfo() {
        return this.firstName + " " + this.lastName;
    }
}
```

```java  
public class WriterStuffs {
    private static Writer[] writers;
    static {
        writers = new Writer[5];
        writers[0] = new Writer("Reşat Nuri", "Güntekin");
        writers[1] = new Writer("Necip Fazıl", "Kısakürek");
        writers[2] = new Writer("Yakup Kadri", "Karaosmanoğlu");
        writers[3] = new Writer("Halit Ziya", "Uşaklıgil");
        writers[4] = new Writer("Yahya Kemal", "Beyatlı");
    }
    public static Writer[] getWriters() {
        return WriterStuffs.writers;
    }
}
```

## HackerRank Challenge : Static Initializer Block

## Final Keyword and Constant Description

In Java, the "**final**" statement tells you that the value of the element in front of which cannot be changed once defined. That's why it's used to define "**constant**" within our program.

Since a constant value cannot be changed once it is defined, there will be no problem in a variable defined with **final** being "**public**".

NOTE: When defining constants, names are written in capital letters. If the constant name consists of more than one word, the words are separated by an underscore ( _ ). For example, the name of the constant that will express the maximum number of records can be given as follows: **PATIKA_DEV_JAVA_102**

```java  
final double PI = 3.14;
```

## Test

1. Which access specifier should be used for the JAVA.MyList.Main method?

    a. public  
    b. private  
    c. protected  
    d. Default  

2. Which of the following information is incorrect?

    a. Items defined with public can be accessed from anywhere within the program.  
    b. Items defined with Private can be accessed through the class they belong to.  
    c. Items defined with Private can be accessed within the package they belong to.  
    d. Items defined with Protected can be accessed within the package they belong to.  

3. Which of the following statements is incorrect?

    a. Static methods are only called by other static methods.  
    b. Static methods can only access static variables.  
    c. Static methods cannot use the this keyword.  
    d. When an object is created from a class, static variables belonging to the class are created again for the objects.  

4. Which of the following methods should be static?

    a. main()  
    b. delete()  
    c. run()  
    d. finalize()  

5. Which of the following keywords is used to prevent the contents of a variable from being changed?

    a. final  
    b. last  
    c. constant  
    d. static  

6. Which of the following cannot be defined statically?

    a. objects  
    b. variables  
    c. methods  

7. Which access specifier allows access only within the class and within the package?

    a. private  
    b. public  
    c. default  

Answers: 1.a, 2.c, 3.d, 4.a, 5.a, 6.a, 7.c  
