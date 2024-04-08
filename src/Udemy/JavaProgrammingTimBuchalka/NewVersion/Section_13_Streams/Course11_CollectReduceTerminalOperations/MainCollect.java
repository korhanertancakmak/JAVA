package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_Streams.Course11_CollectReduceTerminalOperations;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCollect {

    public static void main(String[] args) {

        Course pymc= new Course("PYMC", "Python Masterclass");
        Course jmc= new Course("JMC", "Java Masterclass");

        List<Student> students =
                Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                        .limit(1000)
                        .toList();

//Part-2
/*
        Instead of setting up an array and populating it with the randomly generated students, this time I'll stream students,
    with the generate method, into a list, using toList. At this point, I really don't want to modify my first collection
    of students once I have them, so getting back an unmodifiable list is fine. I'll start with a List of Students, called
    students. I'll call Stream.generate and pass it almost the same lambda expression I used, in the setAll method for
    the array in the Main class's main method. Here, it's type is Supplier, which means it takes no arguments. It starts
    with empty parentheses, and then returns random students from that static method on Student. I'll limit this to 1000
    students. And I'll call toList, which gives me a list of Students, in the order they were created.
*/
//End-Part-2

        Set<Student> australianStudents = students.stream()
                .filter((s) -> s.getCountryCode().equals("AU"))
                .collect(Collectors.toSet());
        System.out.println("# of Australian Students = " + australianStudents.size());

//Part-3
/*
        Next, I want to get a set of my Australian students. I'll set up a local variable, a Set, with a type argument of
    Student, and call it australian Students. I'll start by getting a stream from my students list. I'll filter, using
    getCountryCode on student, and testing for "AU". Now, I'll collect to a set, using Collectors.toSet. I'll print out
    the number of students in my set. This method is a lot like Collectors.toList, but it returns a HashSet. If I run this
    code,

                # of Australian Students = 147

    I'll get a different number of Aussie students each time, but that number should be about a seventh, of the total
    students. Next, I want another set, called underThirty,
*/
//End-Part-3

        Set<Student> underThirty = students.stream()
                .filter((s) -> s.getAgeEnrolled() < 30)
                .collect(Collectors.toSet());
        System.out.println("# of Under Thirty Students = " + underThirty.size());

//Part-4
/*
        For students who enrolled when they were under thirty at the time. I'll copy the statements above and paste a copy
    just below. I'll change the variable name to underThirty. I'll change the condition I'm filtering on to check if the
    age enrolled, is less than 30. Lastly, I want the text that gets printed out, to print the under thirty students, and
    use the underThirty.size. Ok, so this is just a different query about our student population. Running this several
    times,

                # of Under Thirty Students = 187

    I'll get a different number of under thirties. Now let's say I want to understand how many of the under thirties are
    also Australian.
*/
//End-Part-4

        Set<Student> youngAussies1 = new TreeSet<>(Comparator.comparing(
                Student::getStudentId));
        youngAussies1.addAll(australianStudents);
        youngAussies1.retainAll(underThirty);
        youngAussies1.forEach((s) -> System.out.print(s.getStudentId() + " "));
        System.out.println();

//Part-5
/*
        If your population is very large, and your sets are much smaller, there's an argument for using some set math,
    rather than processing another stream pipeline. Let's walk through an example of this first. I'll create a new set,
    and call it young youngAussies1 . I want this to be a tree set, and I want it ordered by student id, so I can do this
    by passing a comparator into the constructor of TreeSet. I'll add all my australian students. I'll retain all students
    who are also underThirty. You'll remember this is really an intersect of the two sets, returning only the students
    who are in both sets. I'll print each student's id out, in a single line, separated by spaces, and finish with a newline.
    If I run this code,

                45 53 74 107 147 150 283 299 309 350 419 420 439 447 485 510 511 520 560 616 636 693 698 731 758 805 817 824 848 852 859 968 974 1000

    I'll get a list of student id's. These are the students who are both from Australia, and were under thirty when they
    enrolled. Now, let's just do the same thing, but use a stream pipeline.
*/
//End-Part-5

        Set<Student> youngAussies2 = students.stream()
                .filter((s) -> s.getAgeEnrolled() < 30)
                .filter((s) -> s.getCountryCode().equals("AU"))
                //.sorted()
                .collect(Collectors.toSet());

        youngAussies2.forEach((s) -> System.out.print(s.getStudentId() + " "));
        System.out.println();

//Part-6
/*
        I'll copy the underThirty stream pipeline and paste it, at the end of this method. I'll change the variable name
    to young Aussies 2. I'll copy the filter from Australian students, on line 35, and paste that as a second filter in
    my new stream pipeline. I'll also copy the two output statements, from lines 78 and 79. pasting a copy at the end of
    this method. I'll change youngAussies1 to youngAussies2. I'll run that now.

                45 53 74 107 147 150 283 299 309 350 419 420 439 447 485 510 511 520 560 616 636 693 698 731 758 805 817 824 848 852 859 968 974 1000
                309 848 74 420 299 974 1000 805 350 616 693 852 147 510 511 859 107 45 447 968 817 53 419 485 731 636 698 758 439 283 520 150 824 560

    The good news is I get the same set of id's, but the bad news is that the last set's not ordered. Can't I just add
    sorted to the pipeline? Let me try that. I'll insert that, before the collect terminal operation. Notice that IntelliJ
    has it grayed out. If I hover over that, I get the message that sorted is redundant, because two Set doesn't depend
    on the sort order. The pipeline knows the set isn't ordered, so it would be wasted effort to sort it here. But let's
    say I really do want it sorted. This means I want a TreeSet, and not a HashSet. Is there a Collectors helper method
    for that? Well, there isn't, at least not like the Collectors.toSet method. This means, that I could either pass this
    HashSet to a TreeSet constructor later. My other option is, to use the overloaded version of collect. Before I do that,
    I want to first go up to this toSet method call, and I'll ctrl click on it. This opens a window that shows me the
    code it's using. You don't have to understand all of this to understand a few important points. Namely, that there
    are 4 parameters, three are either method references or lambda expressions. These are the three we'd use, if we were
    to do something similar. In truth, I do want to do something very similar, using the alternate collect method, passing
    expressions that mirror these. The first method reference in this code instantiates the new HashSet. This is the
    Supplier. The second method reference adds one element at a time, to the HashSet. This is the Accumulator. The third
    is a lambda expression, and a little more complex. Mainly what I want you to see is, that it's calling addAll on the
    set, so it's adding one collection, to another collection. That's the Combiner. That's enough information, to get us
    started, so let's get back to the code.
*/
//End-Part-6

/*
        Set<Student> youngAussies3 = students.stream()
                .filter((s) -> s.getAgeEnrolled() < 30)
                .filter((s) -> s.getCountryCode().equals("AU"))
                //.sorted()
                .collect(TreeSet::new, TreeSet::add, TreeSet::addAll);

        youngAussies3.forEach((s) -> System.out.print(s.getStudentId() + " "));
        System.out.println();
*/

//Part-7
/*
        I'm going to remove Collectors.toSet as an argument, in the collect terminal operation. I'll replace it with three
    arguments this time. First, the Supplier. You saw that the code we just looked at, had HashSet::new for it's supplier,
    so you'd imagine I could put TreeSet::new here, which I'll do. The second parameter is the accumulator, and that would
    be the same as when I'd use the HashSet, so I'll add "add" as a method reference too. The combiner might be a little
    confusing because I haven't covered parallel streams, or splitting up your streams to process them more efficiently.
    In this case, they'd need to be put back together, and that's what this method would be needed for. It joins the
    results of multiple stream's accumulated outputs, into a single collection, and this would be done using an addAll
    method. In our case, it's not likely to be used, but it's still required. And that's it, we've set up our first
    collect method with a supplier, accumulator and combiner. Notice that the sorted method isn't grayed out anymore,
    but it isn't needed either, so I'll remove it. The tree set is ordered, as elements are added. I'll run this,

            Exception in thread "main" java.lang.ClassCastException: class Student cannot be cast to class java.lang.Comparable

    but this doesn't work, I get an exception. That's because Student doesn't implement comparable. This is the same reason
    I had to set up the tree set for the youngAussies1 variable, with my own Comparator. I want to do that here, but how
    do I do it? Well, I can't use a method reference for the Supplier to do it.
*/
//End-Part-7

        Set<Student> youngAussies4 = students.stream()
                .filter((s) -> s.getAgeEnrolled() < 30)
                .filter((s) -> s.getCountryCode().equals("AU"))
                .collect(() -> new TreeSet<>(Comparator.comparing(Student::getStudentId)), TreeSet::add, TreeSet::addAll);

        youngAussies4.forEach((s) -> System.out.print(s.getStudentId() + " "));
        System.out.println();

//Part-8
/*
        I need to change that first argument to use a lambda expression. I'll start with the empty parentheses, and after
    the arrow token, I'll have new TreeSet, and in the constructor parentheses, I'll pass a Comparator. I'll build this
    like I did before, when I created the TreeSet for the youngAussies1 set. If I run my code now,

        45 53 74 107 147 150 283 299 309 350 419 420 439 447 485 510 511 520 560 616 636 693 698 731 758 805 817 824 848 852 859 968 974 1000
        309 848 74 420 299 974 1000 805 350 616 693 852 147 510 511 859 107 45 447 968 817 53 419 485 731 636 698 758 439 283 520 150 824 560
        45 53 74 107 147 150 283 299 309 350 419 420 439 447 485 510 511 520 560 616 636 693 698 731 758 805 817 824 848 852 859 968 974 1000

    it works and I get the same results, from both sets. The collect method has two overloaded versions. I show them here
    again, without showing the generic type parameters. The first can be used, by passing it the result of any of the
    many factory methods, on the Collectors class. I showed you asList, and asSet, as two examples of static methods on
    that class. The second is more complex, but gives you ultimate flexibility, as you saw with the TreeSet example I
    just showed you. You could also create your own Collector type, by writing a class that implements Collector, overriding
    those abstract methods with custom functionality.
*/
//End-Part-8

        String countryList = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .reduce("", (r, v) -> r + " " + v);
        System.out.println("countryList = " + countryList);

//Part-9
/*
        Lastly, I want to cover the reduce terminal operation, so let's get back to the code. The reduce method is different
    from collect, because you're not accumulating elements into a container. Instead, you're accumulating elements into
    a single type. Let me show you an example of this. I'll set up a variable called country list and start a stream pipeline
    on students. I'll map to the country, using getCountryCode. I only want distinct countries. I'll sort. And now I'll
    seed this, with an empty string, returning a concatenated string with the currently accumulated one, plus the new
    value or country. And I'll print that out. Running this code,

            countryList =  AU CA CN GB IN UA US

    I'll get my country codes printed out, as a single concatenated string. You can imagine this would be useful, if you
    didn't know the distinct values in some fields, because this method lets you get them all in a single string. Ok, so
    that covers the introduction to the collect and reduce terminal operations. These methods can be used, to do almost
    any kind of transformation you can think of. I'll be covering more complex features, as we move forward. For now
    though.
*/
//End-Part-9

    }
}
