# Generics Challenge
<div align="justify">

The first thing we want to do is change the QueryList class 
and extend ArrayList. 
This means we won't have to manage all the elements, 
we can let the ArrayList's functionality handle all of that. 
I'll add extends ArrayLists, after the type parameter.

```java  
public class QueryList <T extends Student & QueryItem> extends ArrayList<T>
```

Notice that IntelliJ is showing us a warning on ArrayList when we do that, 
and by now you should be getting used to this message, raw use of a parameterized type. 
Even when we're extending classes, we want to use a type argument. 
Can you guess what we'd use there? 
Well, just **T**, which is the type parameter for this class, 
and will be the type argument, when we extend ArrayList. 
Now, I want to remove the private field, items, actually I'll just comment that out.

```java  
public class QueryList <T extends Student & QueryItem> extends ArrayList<T> {

    private List<T> items;
    public QueryList() {

    }

    public QueryList(List<T> items) {

        super(items);
        //this.items = items;
    }

    public QueryList<T> getMatches(String field, String value) {

        QueryList<T> matches = new QueryList<>();
        for (var item : this) {
            if (item.matchFieldValue(field, value)) {
                matches.add(item);
            }
        }
        return matches;
    }
}
```

This leaves me with a couple of errors in this class, the first is in the constructor.
I'll comment out that statement. 
Instead of assigning the argument to items, 
I'll instead call the super constructor, and pass items as its argument. 
I also want to add a no args constructor for this class, 
I'll insert that as the first constructor.
The next error is in the getMatches method, where we're looping through the items.

Instead of looping through another field, called items, 
I can just change the reference, replacing items with the "this" keyword.

```java  
for (var item : this)
```
                        
I also want to return a QueryList type:

```java  
public QueryList<T> getMatches(String field, String value)
```

this will let me chain calls to getMatches, which I'll implement later.

```java  
QueryList<T> matches = new QueryList<>();
```
                        
That's it for the QueryList class. 
This class has all the functionality of an ArrayList, 
but includes functionality to search the data by field values. 
The next thing on the list is to add student id to Student, so let's do that.

```java  
public class Student implements QueryItem {

    private static int LAST_ID = 10_000;
    private final int studentId;
    private final String name;
    private final String course;
    private final int yearStarted;
    protected static Random random = new Random();
    private static final String[] firstNames = {"Ann", "Bill", "Cathy", "John", "Korhan"};
    private static final String[] courses = {"C++", "Java", "Python"};

    public Student() {
        studentId = LAST_ID++;
        int lastNameIndex = random.nextInt(65, 91);
        name = firstNames[random.nextInt(5)] + " " + (char) lastNameIndex;
        course = courses[random.nextInt(3)];
        yearStarted = random.nextInt(2018, 2024);
    }

    @Override
    public String toString() {

        return "%d %-15s %-15s %d".formatted(studentId, name, course, yearStarted);
    }

    public int getYearStarted() {
        return yearStarted;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {

        String fName = fieldName.toUpperCase();
        return switch (fName) {
            case "NAME" -> name.equalsIgnoreCase(value);
            case "COURSE" -> course.equalsIgnoreCase(value);
            case "YEARSTARTED" -> yearStarted == (Integer.parseInt(value));
            default -> false;
        };
    }
}
```

I'll first create a private static field called LAST_ID, 
which will keep track of the last student id assigned.
I did something similar in a previous lecture. 
I'm just going to set that to ten thousand to start, 
just because I don't want student IDs that start with 1. 
And I will create an instance field, called studentId. 
I want to populate that in my constructor, that does all the other data population.

I'll add studentId to the toString method, 
by adding a percent d specifier at the start, and including studentId
in the argument list. 
Now, going to the main method in the main class, 
I'll first create a new instance of the QueryList class.

```java  
public class Main {

    public static void main(String[] args) {

        QueryList<LPAStudent> queryList = new QueryList<>();

        for (int i = 0; i < 25; i++) {
            queryList.add(new LPAStudent());
        }
        System.out.println("Ordered");
        queryList.sort(Comparator.naturalOrder());
        printList(queryList);
    }
}
```

Since I've actually extended and modified my own custom list implementation, 
I no longer want to declare the variable using List. 
I want to use QueryList because I want to use functionality specific to this new class.
Now, I have an empty QueryList, so I'll start adding some students. 
I want to create instances of the LPAStudent to add, and I'll set up a loop to do this. 
I'll set it to loop five times. 
And add a new instance of LPA student, to queryList, using the add method.
And now, I'll try executing a sort on this list, 
using the inherited ArrayList's sort method. 
This method takes a Comparator, which I covered in a previous lecture, 
so I can retrieve a natural order comparator, from a Comparator interface method. 
I'm going to test the code with five students to start, 
just so the output is easier to read. 
I'll change it to 25, as we start to execute queries against it.

Ok, so here, I have an error on **QueryList.sort**. 
Hovering over the error, the message says, 
_no instances of type variable exist, so that Student conforms to **Comparable<? super T>**_. 
This message indicates that the sort method is using a lower bounded wildcard, 
and is looking up the hierarchy of LPAStudent, for an object that implements Comparable,
and it can't find one. 
That's because neither the LPAStudent class, 
nor its parent class, **Student**, implements **Comparable** yet. 
I want to just show you something else. 
Instead of passing **Comparator.naturalOrder** to that method, 
I can actually pass a null reference.

```java  
public class Main {

    public static void main(String[] args) {

        QueryList<LPAStudent> queryList = new QueryList<>();

        for (int i = 0; i < 25; i++) {
            queryList.add(new LPAStudent());
        }
        System.out.println("Ordered");
        queryList.sort(null);
        printList(queryList);
    }
}
```

This compiles but if I run it:

```java  
Exception in thread "main" java.lang.ClassCastException: class LPAStudent cannot be cast to class java.lang.Comparable
LPAStudent is in unnamed module of loader 'app'; java.lang.Comparable is in module java.base of loader 'bootstrap'
```
            
I get a class cast exception, LPAStudent cannot be cast to class Comparable. 
Let's leave the code this way for the moment. 
I'll come back to it once we have implemented something it needs. 
Right now, I do want to include a printList method, to print out the student list. 

```java  
public static void printList(List<?> students) {
    for (var student : students) {
        System.out.println(student);
    }
}
```

Notice I'm using an unbounded wildcard in the method parameter's type argument. 
Then I just loop through the list, and print each element. 
Ok, I'll come back to this after I implement Comparable on Student. 
Comparable is a typed interface, so I want to use it with a type argument. 
In this case, I use the class we're implementing it for, so Student. 
Going to Student class,

```java
public class Student implements QueryItem, Comparable<Student>
```

And now you see, I have an error, so I want to implement the CompareTo method.

```java
@Override
public int compareTo(Student o) {
    //return 0;
    return Integer.compare(studentId, o.studentId);
}
```

And I'll change the method to compare the student IDs. 
As I did, in an earlier example, I'll use the Integer wrappers **compareTo** method, 
and pass it the student id of the argument, _o_. 
Use Integer value of passing student id, and chain a call to **compareTo**, 
passing **o.studentId**. 
Going back to the main method, I'll change that null, back to **Comparator.naturalOrder()**.
Now, this code compiles and I can run it:

```java
Ordered
10000 John D          Java            2023     33,2%
10001 John L          C++             2022     19,0%
10002 John S          Java            2019     76,2%
10003 Ann M           Java            2022     30,9%
10004 John A          Java            2018     26,1%
```

And I get my five students printed out in a student id order. 
Unfortunately, the natural order is the same order I added the elements, 
so it's kind of hard to say if this was really a successful sort. 
But I'll do a better test of that shortly. 
Right now, I want to go to the LPAStudent class.

```java
@Override
public boolean matchFieldValue(String fieldName, String value) {

    if (fieldName.equalsIgnoreCase("percentComplete")) {
        return percentComplete <= Integer.parseInt(value);
    }
    return super.matchFieldValue(fieldName, value);
}
```

We need to override the matchFieldValue method. 
And then I'll add the code to check the value of percentComplete with the argument passed, 
if the field name is percentComplete. 
Does field name equal percentComplete ignoring case?
Here, I'm returning true if percentComplete is less than or equal to the value passed. 
Remember, if this method returns true, our query will include this item as a match. 
Now, going to the main method, I want to test this out.

```java
System.out.println("Matches");
var matches = queryList.getMatches("PercentComplete", "25").getMatches("Course", "Python");
//printList(matches); 
```

I'll add another heading in the output. 
Running that code, hopefully, I'll get a few matches, 
for percentComplete less than or equal to 50.

```java
Ordered
10000 John S          C++             2023     10,7%
10001 Ann R           C++             2019      4,7%
10002 Ann U           C++             2022     72,9%
10003 Korhan C        Python          2019     25,1%
10004 Bill I          C++             2018     93,6%
Matches
10000 John S          C++             2023     10,7%
10001 Ann R           C++             2019      4,7%
10003 Korhan C        Python          2019     25,1%
```

Let's change the number of students to 25, 
so we have a bigger sample of students to work with. 
And now, I get quite a few more matches for students 
who are less than or equal to 50% done. 
And because I'm getting a queryList back from that method call, 
I can chain another match request directly to it:

```java
var matches = queryList.getMatches("PercentComplete", "50");
```

And now I'll run that:

```java
---(too long)
Matches
10013 Ann H           Python          2019     37,1%
10015 Korhan X        Python          2021      9,1%
10019 Bill V          Python          2019     47,5%
```
                    
Now, I get back the students who are less than or equal to 50 percent done, 
and who are taking the Python course. 
We have one last requirement left, and that's to implement a different sort. 
Remember from the **Comparator** that if you want to sort differently, 
without changing the most common, 
or the natural order sort, you can implement a Comparator. 
So let me do that. 
I'm going to create a new class in the model package, and call it LPAStudentComparator.

```java
public class LPAStudentComparator implements Comparator<LPAStudent> {
    @Override
    public int compare(LPAStudent o1, LPAStudent o2) {
        return (int) (o1.getPercentComplete() - o2.getPercentComplete());
        //return 0;
    }
}
```

Again, this isn't the preferred place to put a comparator. 
In the next section, I'll present multiple other ways,
but for now, I'll create this Comparator class this way. 
This class needs to implement the Comparator interface. 
And I want the type argument to be LPAStudent, 
since I'll be sorting by an attribute on that class only. 
I'll use IntelliJ's tool to implement the compare method. 
And now, instead of returning 0, I'll return the difference between the percent
complete values of these two students.
In this code, I'm casting to an int, 
since the expression is evaluating two doubles and returning a double, 
but an int is the return type of the method on the interface.
And now, I can use this to sort the matches in the main method.

```java
matches.sort(new LPAStudentComparator());
printList(matches);
```

And running the code:

```java
Matches
10012 John F          Python          2018      7,8%
10024 Bill V          Python          2023     23,7%
```
                
My matches are ordered by percent complete, ascending. 
And for good measure, I'll use the Comparable, or student id sort, 
right after this, which will be better test, to test the student id test. 
I'll print Ordered out first.

```java
System.out.println("Ordered");
matches.sort(null);
printList(matches);
```

And running that:

```java
Matches
10022 John H          Python          2023     24,6%
10006 Korhan R        Python          2019     29,5%
10012 Ann D           Python          2021     36,0%
10008 John O          Python          2018     41,3%
10023 Ann T           Python          2019     44,8%
Ordered
10006 Korhan R        Python          2019     29,5%
10008 John O          Python          2018     41,3%
10012 Ann D           Python          2021     36,0%
10022 John H          Python          2023     24,6%
10023 Ann T           Python          2019     44,8%
```
                
You can see the matches are first ordered by percent complete,
and then ordered by student id. 
And that completes this challenge.
</div>