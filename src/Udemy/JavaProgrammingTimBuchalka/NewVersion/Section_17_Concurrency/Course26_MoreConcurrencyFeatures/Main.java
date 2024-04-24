package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course26_MoreConcurrencyFeatures;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

record Student(String name, int enrolledYear, int studentId) implements Comparable<Student> {

    @Override
    public int compareTo(Student o) {

        return this.studentId - o.studentId;
    }
}

//Part-1
/**
        I covered atomic actions, volatile variables, and memory consistency errors in an earlier lectures. What I didn't
    cover was another package, that's part of the java.util.concurrency group of packages. This one is java.util.concurrency.atomic.
    Java's API Documentation defines it as

        A small toolkit of classes that support "lock-free", thread-safe programming on single variables.

    So Why is lock-free so important? Because These classes can significantly improve the performance of concurrent applications,
    especially in high-throughput(high efficient) systems. Let's look at an example in code.

        For this lecture, Before I add code to the main method, I'll create some other simple types in this source file.
    The first will be a student record. This record will have a name, an enrolledYear, and a studentId. In addition I
    want it to implement Comparable, because I want to create a list of these students ordered. This code has an error
    because I have to override the compareTo method. Instead of returning 0, I'll return the difference between the student
    IDs. That's all I need for my record. Next, I'll create a class, called StudentId, that will keep track of the most
    recent student id.
**/
//End-Part-1

class StudentId {

    private int id = 0;

    public int getId() {
        return id;
    }

    public synchronized int getNextId() {
        return ++id;
    }
}

//Part-2
/**
        This is similar to the examples you'll see on this topic, that use a counter class, but in this case I don't want
    to count students, I just want to generate a unique id. This will have one field, an integer, id, which I'll initialize
    to zero. I'll call the getter, getId, and that just returns the id. The getNextId method will increment the id, with
    a pre increment operator. And that gets returned. This operation, I hope you'll remember is not atomic. Now, in the
    Main class,
 **/
//End-Part-2

class AtomicStudentId {

    private final AtomicInteger nextId = new AtomicInteger(0);

    public int getId() {
        return nextId.get();
    }

    public int getNextId() {
        return nextId.incrementAndGet();
    }
}

//Part-7
/**
        In this class, I'll use the AtomicInteger class for my nextId field, and I can instantiate that with an initial
    value, zero. I can make this final because this is an instance. I'll have a getId method, as before. Here, instead of
    returning nextId, I want to return an int, so I can use the get method, on the AtomicInteger. For the getNextId method,
    I can call the method incrementAndGet. This method is guaranteed to be atomic, meaning it's thread-safe. In the main
    method,
 **/
//End-Part-7

public class Main {

    private static Random random = new Random();

    private static ConcurrentSkipListSet<Student> studentSet = new ConcurrentSkipListSet<>();

//Part-3
/**
        I'll set up a random variable, which I'll use to generate a random year for the enrollment. Next, I'll set up my
    studentSet. I want this to be a concurrent collection and sorted, so this will be a ConcurrentSkipListSet. In the main
    method,
**/
//End-Part-3

    public static void main(String[] args) {

        /*StudentId idGenerator = new StudentId();                                           // Commented via Part-8
        Callable<Student> studentMaker = () -> {
            int studentId = idGenerator.getNextId();
            Student student = new Student("Tim " + studentId, random.nextInt(2018, 2025), studentId);
            studentSet.add(student);
            return student;
        };*/

//Part-4
/**
        I'll first create an instance of the StudentId class, idGenerator. Next, I'll create a Callable, which will return
    a generated student. I'll have a local variable, student id, which I'll set to the idGenerator.getNextId. I'll create
    a new instance of a student. The name will be Tim, plus the student id which should make it unique. The year enrolled
    will be a year between 2018 and 2024. Lastly, I'll pass the student id variable, as the studentId argument. I'll also
    add this to my studentSet. I'll return the generated student. Now that I have a Callable variable, I need an Executor
    Service to manage it for me.
 **/
//End-Part-4

        /*var executor = Executors.newCachedThreadPool();*/                                 // Commented via Part-8
        /*try {                                                                             // Commented via Part-6
            var futures = executor.invokeAll(Collections.nCopies(10, studentMaker));
            studentSet.forEach(System.out::println);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executor.shutdown();*/

//Part-5
/**
        I'll set up a variable, executor, which will be a cached thread pool. I'll need a try block. I'll set up a futures
    variable, and assign it the result of calling executor.invokeAll. I'll pass that 10 copies, of callable variable,
    which I called studentMaker. This time I'll use Collections.nCopies, to quickly pass 10 Callables to this method.
    This method will return when all the tasks have completed. At that point, I should have all my students in the student
    set, so I'll print the students out. And I've got the usual exception handler. Lastly, I need to make sure the executor
    shuts down. I'll run this code.

             Student[name=Tim 1, enrolledYear=2022, studentId=1]
             Student[name=Tim 2, enrolledYear=2019, studentId=2]
             Student[name=Tim 3, enrolledYear=2020, studentId=3]
             Student[name=Tim 4, enrolledYear=2024, studentId=4]
             Student[name=Tim 5, enrolledYear=2018, studentId=5]
             Student[name=Tim 6, enrolledYear=2022, studentId=6]
             Student[name=Tim 7, enrolledYear=2020, studentId=7]
             Student[name=Tim 8, enrolledYear=2021, studentId=8]
             Student[name=Tim 9, enrolledYear=2018, studentId=9]
             Student[name=Tim 10, enrolledYear=2021, studentId=10]

    I kicked off 10 threads, but if I run this several times,

             Student[name=Tim 1, enrolledYear=2022, studentId=1]
             Student[name=Tim 2, enrolledYear=2019, studentId=2]
             Student[name=Tim 3, enrolledYear=2020, studentId=3]
             Student[name=Tim 4, enrolledYear=2024, studentId=4]
             Student[name=Tim 5, enrolledYear=2018, studentId=5]
             Student[name=Tim 6, enrolledYear=2022, studentId=6]
             Student[name=Tim 7, enrolledYear=2020, studentId=7]

    it looks like it's alternating between 7 to 10 students. Every now and then we seem to have a glitch. The increment
    method isn't atomic, so if two threads try to run it asynchronously, there's interference happening, and maybe memory
    inconsistency. Because my collection is a set, only unique student ids are added. The threads generated 10 students,
    but 3 students might get the same id. To see this a little easier, I'll wrap the code that's invoking all of these
    tasks, in a for loop.
 **/
//End-Part-5

        /*for (int i = 0; i < 10; i++) {                                                    // Commented via Part-8
            studentSet.clear();
            try {
                var futures = executor.invokeAll(Collections.nCopies(10, studentMaker));
                System.out.println("# of students = " + studentSet.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();*/

//Part-6
/**
        I'll loop for 10 iterations. I'll clear the student set each time. I'll close the for loop with a closing brace.
    I'll print the number of students in the studentSet. I also want to comment out the code that's printing each student.
    I'll run this again.

             # of students = 8
             # of students = 9
             # of students = 9
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 7
             # of students = 9

    We may get different results each time, but I'll see probably a couple of iterations where my set only has 7,8 or 9
    students. This is bad. I should be able to fix this by synchronizing the getNextId method on the StudentId.

                                         public int getNextId() {
                                                        to
                                         public synchronized int getNextId() {

    I'll run the code again.

             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10

    This time my set, in all 10 cases, has 10 students. This is true if I run it a couple of times, so synchronizing that
    method seems to be successfully fixing the threading problems. Another option is using a class, called AtomicInteger,
    instead of just a primitive integer for my student id. After the StudentId class, I'll insert a new class, called
    AtomicStudentId.
 **/
//End-Part-6

        AtomicStudentId idGenerator = new AtomicStudentId();
        Callable<Student> studentMaker = () -> {
            int studentId = idGenerator.getNextId();
            Student student = new Student("Tim " + studentId,
                    random.nextInt(2018, 2025), studentId);
            studentSet.add(student);
            return student;
        };

        var executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            studentSet.clear();
            try {
                var futures = executor.invokeAll(Collections.nCopies(10, studentMaker));
                System.out.println("# of students = " + studentSet.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
    }

//Part-8
/**
        I'll swap out StudentId, and make it AtomicStudentId, on both sides of the declaration. Running with this change,

             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10
             # of students = 10

    With this code, I again see that the number of students, is always 10, which is a good sign. I'll run this a couple
    of times. Each time, I get the same results. The AtomicInteger, because it's lock-free, will probably be better
    performant, in a high through-put environment, then synchronizing on methods that increment integers.

                                                Atomic Classes

        The java.util.concurrent.atomic package, has several atomic classes as shown below, including atomic arrays.

                                        ___________________________________________
                                        |  Single Element  |   Array of Elements  |
                                        |------------------|----------------------|
                                        | AtomicBoolean    | n/a                  |
                                        | AtomicInteger    | AtomicIntegerArray   |
                                        | AtomicLong       | AtomicLongArray      |
                                        | AtomicReference  | AtomicReferenceArray |
                                        |__________________|______________________|

    In each of these cases, an instance of one of these classes can be updated atomically. Let me encourage you to review
    this toolkit, if you're working on concurrent applications. Here, I really just wanted to briefly introduce you to
    the classes, in this package to make you aware of them, as another option. Now, I want to talk to you about some classes
    that exist, that you might run into, that are related to concurrency. The first is the Timer class.
 **/
//End-Part-8
}
