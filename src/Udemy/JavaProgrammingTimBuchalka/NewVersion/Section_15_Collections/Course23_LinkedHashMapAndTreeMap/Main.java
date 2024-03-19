package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course23_LinkedHashMapAndTreeMap;

import java.time.LocalDate;
import java.util.*;

//Part-1
/*
                                    Ordered and Sorted Map Implementations

        Like the Set interface, which has LinkedHashSet and TreeSet implementations, The Map interface has the LinkedHashMap
    and TreeMap classes. The LinkedHashMap is a key value entry collection, whose keys are ordered by insertion order.
    The TreeMap is sorted by it's keys, so a key needs to implement Comparable, or be initialized, with a specified Comparator.
    Let's take a look at these in code. I've created a Main class and main method. I'm going to start by creating a Student
    Class.
*/
//End-Part-1

public class Main {

//Part-5
/*
        I'm going to set up two different maps, but both are private and static. The first, I'm going to declare with a
    reference type of just Map, the key will be a String, and the value is going to be a Purchase records. I'll call this
    map, purchases. I'll assign it a new instance of a LinkedHashMap, and no arguments for the constructor. For the second
    map, I'm going to make the reference type Navigable Map, with the key as String, and the value will be Student. I'll
    call this students, and make it a new TreeMap. Using Navigable Map as the reference type, is similar to the way I
    used Navigable Set, when I was working with a TreeSet. This means I'll have access to all the additional methods,
    on both the Navigable Map interface, and the SortedMap interface. Next, I'll include a method on this class, called
    addPurchase, and
*/
//End-Part-5

    private static Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, Student> students = new TreeMap<>();

    public static void main(String[] args) {

//Part-7
/*
        I'll set up a couple of courses, the first for my Java master class. I'll use my Course record, and I'll give this
    some kind of course id, like jmc101, and that's my Java master class, and the language is going to be Java. I'll assign
    that to a local variable I'll call jmc. I'll do the same thing for the python master class, creating a local variable
    called python. I want to add some purchases next. I'll start out with five students, calling the addPurchase method,
    passing the student name, the course, and a price for each. So my first student will be Mary Martin, and jmc is the
    course, and I'll just put 129.99 in there. I'll repeat that for Andy Martin, jmc, 139.99. And let's say Mary's also
    taking python, and her price, 149 and 99. Now Joe Jones, jmc, and his price. And Bill Brown, python, and a price for
    that. My prices might be different based on special coupons and sales prices. I want to print my purchases, and my
    students now. I'll use the for each method on the map to do it, and print the key and value, of my purchases. And
    I'll do something similar, this time for my students. Running this code,

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

    I've got five purchases, and four unique students. The purchase map's a Linked Hash Map, which means it's ordered by
    insertion order. This is why I wanted the day of Year, for this first test case, to be equal to the purchase size.
    This makes it a bit easier to see, that the entries are listed in insertion order, which is the order of a Linked
    Hash Map. But my students, are in alphabetical order. My key was student name, and that's how the students are sorted.
    You can see the student id, and the courses being taken by each student, and right now, only Mary is taking more than
    one course. Ok, so that's the difference between how a Tree Map is sorted, and how a Linked Hash Map is ordered.
    What's kind of nice about a map, is that, most of the time you'll be using simpler types as keys, although that's
    not always going to be true. But the simpler types, like the strings I use for my key in my tree map here, have a
    natural order, and already implement Comparable. Next, I want to go to the addPurchase method, on the Main class,
    and change my expression for setting the day. I could use the LocalDate dot now feature, and get day of the year for
    the current year, but I want to mock up my data a little bit. I just want to limit the options for this, to be the
    first 4 days of the year. I'll change the expression after the assignment operator. This time, I'll make a call on
    a new random instance, and execute next int on that, passing 1 through 5. This will return numbers 1 through 4, which
    means the possible days of the year, for this test, are going to be January 1 through January 4.

                                int day = purchases.size() + 1;
                                              to
                                int day = new Random().nextInt(1, 5);

    I'm interested in understanding my sales for each day, so what I'll do is, create a new map, a local variable here,
    in the main method.
*/
//End-Part-7

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

//Part-8
/*
        Again, I'll set this up using a NavigableMap reference type. This map should be keyed by a date, a local date,
    and the value is going to be a list of my purchases for that day. I'll assign that to a new instance of a tree map.
    To populate this map, I'll loop through my purchase map's values, which are all purchases. I'm going to execute the
    compute method on my new map, dated Purchases, passing it first the key, which is going to be the purchaseDate, and
    that's really a method on the Purchase record. I'll set up my lambda expression, using the key, the purchase date in
    other words, and the current value, a list, which I'll call p list. In the body block of the lambda expression, I'll
    set up a local variable, a list of purchases, and call that simply list. I'll assign it the result of a ternary operator,
    so if p list is null, which it will be if this is the first entry for the date, I'll assign list a new Arraylist
    instance. If this is already in the map, p list won't be null, so I'll return p list. I'll add the current purchase
    record to the list variable. Lastly, I'll return p list from this lambda, and that's what gets put into the map.
    That's the code that sets up my map by dates, so next, I'll print the entries out, by each key value, or each date.
    If I run that, I'll get different purchases for different days each time I run this. What I want you to see though,
    is that my map is organized, in chronological order. Let me add some new students, to make this more interesting.
    I'll add five more students, dividing them between the python class and the java class, with various prices and names.
    I'll set up Chuck Cheese, to take python. Davey Jones, jmc. Eva East, python. Fred Forker, jmc, and Greg Brady,
    python. Since I have a larger set of students, I'm going to change the day local variable, in my add Purchase method.
    I'll have the random method pick one of the first 14 days, so I'll change 5 to 15 there. I'll run that.

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


    And that gives me 10 students, with various purchases, for the first two weeks of this year. Again this will change
    each time I run it. In this lecture, I've shown you examples of using a LinkedHashMap, as well as a TreeMap. For the
    tree map, I've used a String as a key, but I've also used LocalDate as a key as well. In the next lecture, I'm going
    to continue to use the TreeMap, to help me make sense of my purchase data, using methods on NavigableMap, to do that.
*/
//End-Part-8


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

//Part-6
/*
        I'll make that static and void. I'll pass a name, the student name, a Course, and a price, the price the student
    paid for that course. I'll create a local variable, called existing Student, and try to get that from my student map,
    using the name. if that's null, the student's not in the map, so it's a new student. In this case, I'll create a new
    instance of student, using the constructor with name and one course. I'll put this new student in my map, with the
    name as a key. If I did find the student in the student map, I'm just going to add the course to this existing student.
    This code is going to let me keep track of my students, and which courses they're taking. Next, I want to do the same
    for purchases. I'm going to set up two local variables, day and key. For now, I'll set day equal to the number of
    purchases I've had, plus one. I'll explain this in just a minute. The key for my purchase map is a combination of
    the course id, and the student id. To get the current year, I can use LocalDate, and its now method, which gives me
    the current date from the system clock in the default time-zone. I can chain the get Year method to that call, which
    will give me back an integer for the current year. Now I want a new instance of the Purchase record, and I'll pass
    that the course id, the student id, the price, the current year, and my day variable, which is just a number from1
    to whatever the maximum number of purchases is. Finally, I'll put the purchase in the purchase map, using my key.
    Now, I can go up to the main method.
*/
//End-Part-6

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

}
