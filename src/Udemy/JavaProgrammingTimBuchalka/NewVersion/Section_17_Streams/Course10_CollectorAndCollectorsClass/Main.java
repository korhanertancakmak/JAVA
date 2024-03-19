package CourseCodes.NewSections.Section_17_Streams.Course10_CollectorAndCollectorsClass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//Part-1
/*
        In the last lecture, we looked at some simple results of processing stream elements. We used count and summaryStatistics
    operations, which are reduction terminal operations.

                                    Additional Terminal(Reduction) Operations

        "Reduction operations" combine the contents of a stream, to return a value, or they can return a collection. On
    this table, I want to show you some additional terminal operations, and their return types and signatures.

            Return Type               Terminal Operations

                R                     collect(Collector<? super T.A.R> collector)
                R                     collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R,R> combiner)
            Optional<T>               reduce(BinaryOperator<T> accumulator)
                T                     reduce(T identity, BinaryOperator<T> accumulator)
              <U> U                   reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
             Object[]                 toArray()
              A[]                     toArray(IntFunction<A[]> generator)
             List<T>                  toList()

    Some of these signatures, namely the parameters, look pretty cryptic. Don't let this intimidate or overwhelm you.
    Hopefully you recognize many of these types, which are functional interfaces you know by now. You can see, the Supplier,
    the BiConsumer, the BinaryOperator, the BiFunction, and the IntFunction, in these parameters. This tells you we'll
    be using lambdas or method references with these operations. This is the important part of the signature, and I would
    recommend ignoring the generic types, as you consider these. Let me show you the same set of operations, without the
    generics in the signature.

            Return Type               Terminal Operations

                R                     collect(Collector collector)
                R                     collect(Supplier supplier, BiConsumer accumulator, BiConsumer combiner)
            Optional                  reduce(BinaryOperator accumulator)
                T                     reduce(T identity, BinaryOperator accumulator)
              <U> U                   reduce(U identity, BiFunction accumulator, BinaryOperator combiner)
             Object[]                 toArray()
              A[]                     toArray(IntFunction generator)
              List                    toList()

    Ok, this view of each method is a little easier on the eyes and brain I think. There is one interface I haven't talked
    about yet, and that's the Collector, which you can see in the first collect operation. This is not a functional interface,
    but there are helper methods on another class, named Collectors, with an s, that provide these special types, that
    let us create collections of any kind. I'll be covering this interface in more detail, as I review these methods.
    Starting from the bottom and working upwards, you can see toList there. List is one of the most common types you'd
    put stream elements into, so Java provided a terminal operation to give us a List back. We can also get arrays of objects,
    or arrays of types we declare, using one of the two Array operations. What's not so obvious, when you look at the reduce
    and collect operations, is that we can also get back Maps, Sets, or any other resulting type we want from those operations.
    In some ways, lambdas and streams are a whole new language, living inside Java. You never have to use them at all,
    Java existed before these features were included. Once you do understand them though, I think you'll appreciate them.
    Let's get started. I'm back in the StreamingStudents project, because there's a lot in this code to practice on. At
    the end of the challenge lecture, I left off with identifying 5 long term students.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        Course pymc= new Course("PYMC", "Python Masterclass");
        Course jmc= new Course("JMC", "Java Masterclass");

        Student[] students = new Student[1000];
        Arrays.setAll(students, (i) -> Student.getRandomStudent(jmc, pymc));

        var maleStudents = Arrays.stream(students)
                        .filter(s -> s.getGender().equals("M"));

        System.out.println("# of male students " + maleStudents.count());

        for (String gender : List.of("M", "F", "U")) {
            var myStudents = Arrays.stream(students)
                    .filter(s -> s.getGender().equals(gender));
            System.out.println("# of " + gender + " students " + myStudents.count());
        }

        List<Predicate<Student>> list = List.of(
                (s) -> s.getAge() < 30,
                (Student s) -> s.getAge() >= 30 && s.getAge() < 60
        );

        long total = 0;
        for (int i = 0; i < list.size(); i++) {
            var myStudents = Arrays.stream(students).filter(list.get(i));
            long cnt = myStudents.count();
            total += cnt;
            System.out.printf("# of students (%s) = %d%n",
                    i == 0 ? " < 30" : ">= 30 & < 60", cnt);
        }
        System.out.println("# of students >= 60 = " + (students.length - total));

        var ageStream = Arrays.stream(students).
                mapToInt(Student::getAgeEnrolled);
        System.out.println("Stats for Enrollment Age = " + ageStream.summaryStatistics());

        var currentAgeStream = Arrays.stream(students).
                mapToInt(Student::getAge);
        System.out.println("Stats for Current Age = " +
                currentAgeStream.summaryStatistics());

        Arrays.stream(students)
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        boolean longTerm = Arrays.stream(students)
                .anyMatch(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12));
        System.out.println("longTerm students? " + longTerm);

        long longTermCount = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .count();
        System.out.println("longTerm students? " + longTermCount);

        Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .toList()
                .forEach(System.out::println); // It's not a stream anymore, List<Student>

//Part-2
/*
        Now, I want to do something, or process these students in some way, so I want to get these five students into some
    kind of container type. In this case, I'll use the two List terminal operation, inserting it after the limit operation,
    in my last pipeline. I'll put that just before the forEach statement. This compiles, and you might be wondering why,
    because I have toList, and forEach in the same pipeline here. Are both of these terminal operations? Wouldn't the
    second one cause an exception? Let me run this code.

            Student{studentId=4, countryCode='GB', yearEnrolled=2015, ageEnrolled=44, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Kas 2023 Lecture 15 [1], PYMC=PYMC: Mar 2017 Lecture 7 [81]}}
            Student{studentId=49, countryCode='CN', yearEnrolled=2015, ageEnrolled=86, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Kas 2023 Lecture 11 [1], PYMC=PYMC: Mar 2017 Lecture 5 [81]}}
            Student{studentId=135, countryCode='CN', yearEnrolled=2015, ageEnrolled=40, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Ağu 2023 Lecture 2 [4], PYMC=PYMC: Kas 2019 Lecture 21 [49]}}
            Student{studentId=175, countryCode='US', yearEnrolled=2016, ageEnrolled=57, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Nis 2016 Lecture 20 [92], PYMC=PYMC: Şub 2023 Lecture 19 [10]}}
            Student{studentId=219, countryCode='US', yearEnrolled=2016, ageEnrolled=70, gender='U', programmingExperience=false, engagementMap={JMC=JMC: May 2023 Lecture 20 [7], PYMC=PYMC: Mar 2016 Lecture 24 [93]}}

    It runs fine, and I get the five students printed out. This is because the last forEach is not technically part of
    the stream pipeline at all. In fact, if you've got IntelliJ's Inlay Hints turned on, you can see the inlay hint after
    the toList operation. It tells us that at this point, we have a List of students, and it's not a stream any more. The
    forEach call here is a method on that list, and not a terminal operation on the stream. In this scenario, the List
    method provides an unmodifiable list, restricting your ability to perform further operations, or chain additional
    methods. Instead it's more likely you'd want to assign the result of your list to some variable, so I'll do that next.
*/
//End-Part-2

        List<Student> longTimeLearners = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .toList();

//Part-3
/*
        I'll set up a local variable assignment. I'll use List with Student, and call it longTimeLearners, and assign it
    the result of the stream pipeline. I'll add a semi-colon after the terminal operation, toList, and remove the forEach
    statement altogether. Again, the returned list is unmodifiable. But now you have a concrete collection of elements
    you could pass to other methods, as long as they don't attempt to modify the original list. I can always pass this
    list to the constructor, of a new ArrayList or LinkedList, if I want my own modifiable list. Let's look at the two
    Array operation next.
*/
//End-Part-3

        var longTimeLearners1 = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .toArray(size -> new Student[size]);

//Part-5
/*
        First, I'll change my variable type to just var. I'll change the toList operation, making it toArray, without passing
    that any arguments. This compiles, but again let's see what IntelliJ's inlay hints can tell us. I'm getting back an
    array of Object, which is basically an untyped array. I know every object is a Student in this stream, but if I want
    to access a Student method on these array elements, I'd have to cast that to a Student array. There's an overloaded
    version of this operation, that let's us specify the type of array we want back. It takes an IntFunction type, which
    means, it takes an int as a parameter, and then returns an instance of something else. This means I can use the parameter,
    to create a new array of Student, and use that argument value, as the size of the array. I'll use the variable name,
    size, as the parameter variable name in my lambda, to make this really clear.

                .toArray();             to              .toArray(size -> new Student[size]);

    I can then use this size, and pass that to the array construction code. This means it goes in the square brackets
    when I declare a new array of Student. IntelliJ is telling me there's a better way to represent this, but I wanted
    you to see it this way first, so it's easier to see what's happening.
*/
//End-Part-5

        var longTimeLearners2 = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .toArray(Student[]::new);

//Part-6
/*
        Now let's use IntelliJ's popup to change that to a method reference. This is probably the first time I've showed
    you an array instantiation as a method reference. It's doing exactly what I showed you in the lambda expression. This
    is another example of the unbounded receiver method reference type. In this case, the unbounded receiver is the array
    size, and it's used in the array's specialized construction statement. Now, the inlay hint is telling me I'm getting
    back an array of Student, because of this minor change. I can also use the reduce terminal operation, to give me a
    list back. I'll copy all code, and paste that below.
*/
//End-Part-6

        var learners = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .collect(Collectors.toList());

        Collections.shuffle(learners);

//Part-7
/*
        I'll change the name of my variable to just learners. I'll replace the toArray operation with collect, and I'm
    going to pass the result of calling a method on that Collectors helper class. In this case, it has a toList method
    also. Now, IntelliJ is giving me a warning on that. If I hover over that, the message is that this can be simplified,
    and replaced with just the Stream's toList terminal operation. There's a very important difference between these two
    operations, between the Stream's toList operation, and the collect operation, that take's Collectors.toList as an
    argument. In fact, I'll add a statement after this, I'll call Collections.shuffle on my learners list. You'll remember
    this just shuffles up values in the list. Ok, now the warning disappears. That's because IntelliJ can tell I want to
    modify this list. The collect terminal operation returns mutable reductions, as I've demonstrated here. I can run this
    code without any errors. If I changed that operation back to the toList call,

                                             .collect(Collectors.toList());
                                                         to
                                             .toList();

    I would get a warning on that call to shuffle, and I'd get an exception if I tried to run it. There are two versions
    of the collect method, as I showed you on the first couple of operations in the table. This one, that I'm showing you
    here, takes one argument, with a type of Collector. I was able to get this by calling the toList method, on the Collectors
    class.

        So what's a Collector anyway? Let's go look at that interface, I'll pull it up in the api documentation. There are
    three really important things on this page I want to point out. First, the Interface has three type parameters.

        - T is the type of input elements to the reduction, so this is the type of your stream elements.
        - A is the mutable type you want to collect elements into. This can be a single type, like a StringBuilder, or
          a collection type, like List.
        - R is the final result type of the reduction operation.

    The second two types are usually the same, meaning your result type(R) is often the same type you've been accumulating
    elements(A) into, but you're not limited to that. The second really important thing, is that this is a mutable reduction
    operation. I already demonstrated this to you, with the list I got back from the collect operation, in my last code
    sample.

        Lastly, a Collector is specified by four functions that work together, to accumulate entries, into a mutable result
    container.

        - The supplier, accumulator, and combiner methods are the primary methods you'll be using, to build a result container.
        - There is also a less used method, called the finisher, that provides a way to do a final transformation, on the
          fully combined accumulated instance created.

          Let's look at the abstract methods on this interface. I'll select methods, then abstract methods at the top of
    this page. And there they are, the four methods just discussed, and one additional one. This interface means, we could
    create our own implementations of a Collector interface, and implement these methods. But Java has done this work for
    us, for most of the types we'd want to use. These are supplied as static methods, on the Collectors class. Additionally,
    rather than implement a class, there is an overloaded version of the collect method. This provides a way to specify
    code for the supplier, the accumulator and the combiner. This means we can just pass lambda expressions or method
    references, rather than build a type that implements the Collector interface. Let's look at the Collectors class now.

        First there's a list here of the most common usages, with examples. You see there at the top, is an example using
    the Collectors.toList method I used in my own example. I'll again select the method tab at the top of the screen.
    There's a lot of methods here, and this can seem quite overwhelming. But look at the names, they start with things
    like averaging, filtering, groupingBy, joining, and partitioningBy, to name a few. Again, this is language you'd see
    in data queries. You can do a lot with just a few of these in your arsenal, and that's what I'm going to show you next.
    I'll pick up where I left off in the code, in the next lecture, and we'll start exploring the collect and reduce
    operations, and some of the Collectors methods.
*/
//End-Part-7
    }
}
