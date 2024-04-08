package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_Streams.Course03_StreamSources;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        List<String> bingoPool = new ArrayList<>(75);

        int start = 1;
        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++) {
                bingoPool.add("" + c + i);
//                System.out.println("" + c + i);
            }
            start += 15;
        }

        Collections.shuffle(bingoPool);
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------");

//        List<String> firstOnes = bingoPool.subList(0, 15);
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));
        firstOnes.sort(Comparator.naturalOrder());
        firstOnes.replaceAll(s -> {
            if (s.indexOf('G') == 0 || s.indexOf("O") == 0) {
                String updated = s.charAt(0) + "-" + s.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return s;
        });
        System.out.println("\n----------------------------------");

        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------");

        var tempStream = bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted();
//                .forEach(s -> System.out.print(s + " "));

        tempStream.forEach(s-> System.out.print(s + " "));
        System.out.println("\n----------------------------------");

        String[] strings = {"One", "Two", "Three"};
        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());
                //.forEach(System.out::println);

//Part-2
/*
        In this lecture, I'm going to continue with the same code I used in the last lectures. In the example I worked
    with previously, the source for my stream was a type of collection, an ArrayList. Because Lists and Sets implement
    the Collection interface, the stream method is available from any type that implements those, so ArrayList, LinkedList,
    HashSet, TreeSet, and so on.

        Next, let's look at using an array as a source. I'll just set up a Strings array with a simple array initializer
    with three string literals, One, Two and Three. I can use another helper method on the java.util.Arrays class, called
    stream, to generate a stream of elements from an array. I'll call that method, passing it my array of strings. I'll
    use sorted, as an intermediate operation, and pass that, the reverse order comparator. The terminal operation is
    forEach, and I'll use a method reference you're familiar with by now. Running that code,

                        Two
                        Three
                        One

    I get the strings printed out in reverse alphabetical order, so in this case I get Two, Three, One.
*/
//End-Part-2

        var secondStream = Stream.of("Six", "Five", "Four")
                .map(String::toUpperCase);
                //.forEach(System.out::println);

//Part-3
/*
        I could create a similar stream, by using the static of method on the Stream interface. Stream.of, takes a variable
    argument of any type, so I can simply start listing my Strings here, and here I'll put Six, Five and Four. I'll use
    map, remember this is similar to replacing a value with another, so I'll use the method reference String::toUpperCase.
    This is going to transform all my stream elements to uppercase. And I'll print each of those out, again executing
    forEach, to do that. Running this code,

                        Two
                        Three
                        One
                        SİX
                        FİVE
                        FOUR

    I get SIX, FIVE, and FOUR in upper case.
*/
//End-Part-3


        Stream.concat(secondStream, firstStream)
                .map(s -> s.charAt(0) + " - " + s)
                .forEach(System.out::println);

//Part-4
/*
        Let's look at another static method on Stream, that's kind of fun. To set this up, I want to assign the two streams
    I just created, to local variables. First, I'll set up a local variable, using var as the type for simplicity, and
    call that firstStream, and assign that to the stream pipeline I had for Arrays dot stream. I'll add a semi colon after
    the sorted operation, so this stream won't have a terminal operation. I'll comment out the for Each terminal operation.
    Doing the same kind of thing for the next pipeline, I'll set up a second local variable, called secondStream, and
    assign it my stream pipeline, which I got from Stream.of. I'll add a semi colon after the map operation, and comment
    out the for each, as I did for the first stream. I can use the concat method on Stream, to produce a single Stream,
    from two streams produced from different sources. I'll pass second stream first, and then first stream. I'll chain
    the forEach method to the result of that call. If I run this,

                    SİX
                    FİVE
                    FOUR
                    Two
                    Three
                    One

    I get the combined result of the two pipelines. The first three are in upper case, SIX, FIVE, then FOUR, and the second
    three are ordered in the reverse order they were declared. Think about what I did here for a minute. Each stream had
    different operations declared for the different sources. These weren't executed until I joined them to a single stream,
    and executed a terminal operation on the concatenated stream. Each stream still performed different operations on
    each of it's different source data. Once merged, any operations after the concat operation, were performed on the
    entire stream. Next, I'll add an intermediate operation to that last pipeline, so that you can see this more clearly.
    I'll use map again, and return a string that is the first letter of the current string, a dash, and then the string.
    Running that,

                    S - SİX
                    F - FİVE
                    F - FOUR
                    T - Two
                    T - Three
                    O - One

    you can see that this map operation was executed on all 6 strings, but the first map operation, was executed only on
    part of the stream, the three elements that came from the Stream.of method. The concat method lets you create a network
    of pipelines, with different operations for each stream segment. You might remember that the Map interface didn't
    extend the Collection interface, and the Map, and it's implementing classes, were sort of in a category of their own.
    The Map interface doesn't have a stream method either, but you can use any of Map's collection views, keySet, entrySet,
    or values, to generate a stream to process parts of the map.
*/
//End-Part-4


        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for (char c : "BINGO".toCharArray()) {
            int[] numbers = new int[15];
            int labelNo = bingoIndex;
            Arrays.setAll(numbers, i -> i + labelNo);
            myMap.put(c, numbers);
            bingoIndex += 15;
        }

//Part-5
/*
        I'll show a quick example of that. First, I'll set up a map, instead of a list, for the bingo ball labels. My map
    will be keyed by each character in the word Bingo, and will contain an integer array for the bingo numbers. I'll make
    it a LinkedHashMap, because I want it to stay in the character order for the word Bingo. I'll set up a local variable,
    bingoIndex, for the bingo numbers. I'll loop through the valid characters. I'll set up an array of 15 integers. I want
    a local variable. This can be an effectively final variable, which means I can use it in a lambda. I'll set that to
    the bingo Index, which changes for each iteration, and isn't effectively final. I'll set my numbers using Arrays.setAll,
    to start at the array index plus the labelNumber, which is really the bingo Index. I'll add this array to my map,
    using the corresponding character as the key. Finally, I'll increment bingo index by 15.
*/
//End-Part-5

        myMap.entrySet()
                .stream()
                .map(e -> e.getKey() + " has range: " + e.getValue()[0] + " - " +
                        e.getValue()[e.getValue().length - 1])
                .forEach(System.out::println);

//Part-6
/*
        Now let's say, I want to use a stream, to make sure my map looks good. I can use any of the map's set views, so
    I'll use the entrySet, and start a stream off of that. I'll use map to create a string, that prints the keyed character,
    and the first and last number in the integer array, mapped to that character. And I'll print this out. Let me run this
    code,

                        B has range: 1 - 15
                        I has range: 16 - 30
                        N has range: 31 - 45
                        G has range: 46 - 60
                        O has range: 61 - 75

    and you can see, I get the map keys printed, with the range of numbers shown there. Let's just examine IntelliJ's
    inlay hints here for a minute. I need to click the three dots to full expand the hints. I'll click it on line 79 to
    see it in full. It may be the size of my font for recording, that is preventing it from showing in full by default.
    Notice that I'm starting out with a Stream of Entry instances, which I can see as an inlay hint, after the call to
    stream there. After the map operation though, I have a Stream of String. The map operation, which I'll be covering
    in a lot more detail, let's you change the stream type.

        I said earlier that an intermediate operation can usually be recognized by it's signature, because it returns a
    stream. I want to point out, that this doesn't mean the element type of the stream can't change. In this example,
    I'm starting out with a stream of Map.Entry instances, and this gets transformed to a stream of String instances.
    I mapped an Entry instance to a String for every element in the stream. In practice, you'll be regularly transforming
    your stream element to a different type. I didn't need a print the map out this way, there were probably simpler ways
    to do it. I did want to show you, how you could use a stream pipeline, to do some processing on your map entries. In
    later lectures, we'll do more complex processing, and this will get more interesting.

        Ok, those are some examples of sources, and these all have one thing in common. They're finite streams, which means
    we know at the start, exactly how many elements we're dealing with. We also have the ability to use infinite streams.
    You might ask, won't infinite streams be like infinite loops, and grind on until you get an out of memory error?
    They do have this potential, but somewhere in your pipeline, you'll be adding a limiting operation. I haven't discussed
    all the intermediate operations yet, but I've shown you some simple ones like filter, sorted, map, and limit. I'll use
    limit in the next examples.
*/
//End-Part-6

        Random random = new Random();
        Stream.generate(() -> random.nextInt(2))
                .limit(10)
                .forEach(s -> System.out.print(s + " "));

//Part-7
/*
        I want to first look at the static method on the interface Stream, named generate. This method takes a Supplier.
    Hopefully you'll remember that the Supplier's functional method, returns a value, but doesn't take any arguments. I'm
    going to use a random generator in this next example, so I'll create a local variable, for a new instance of random.
    Next, I'll execute generate on the Stream interface type, and pass it a lambda. The lambda has no arguments, so I need
    empty parentheses, and then I'll call random.nextInt, passing 2 as the upper bound. This will generate a series of 0's
    and 1's infinitely. I'll limit my stream to 10 integers. And I'll print those out. Running this code,

                        1 1 0 0 1 0 0 1 1 0

    you can see I get a list of 10 0's and 1's. Each time I run it, I'll get a different set, but always 10 values, because
    of the limit operation.
*/
//End-Part-7

        System.out.println();
        IntStream.iterate(1, n -> n + 1)
                .limit(20)
                .forEach(s -> System.out.print(s + " "));

//Part-8
/*
        Another static method on the Stream Class is iterate, which gives us the option of generating either a finite or
    infinite stream. I'll start with an example of an infinite stream. The simplest form of Stream.iterate takes two arguments.
    The first is a seed or starting value, and after that it's a Unary Operator, which is a special kind of function, so
    this means you want some kind of equation here. Let me show you this in action. I'll print out a new line first. To
    start with, I'm going to use IntStream, instead of Stream, simply because I'll be dealing with integers here. I could
    have used IntStream for the pipeline above as well. My first parameter is the starting value, called a seed. In this
    case, my first value will be one. The second parameter is a lambda expression that will do something with that seed
    to get the next value. In this case, it's just going to add 1 to the last value. This code works if I use IntStream
    or Stream. If I had used Stream though, Java would have been autoboxing and unboxing integers, on each method call,
    which makes it less efficient, especially with large amounts of data. I'll limit this set of data to 20 numbers. And
    I'll print them out. Again, the first parameter of this method is the seed or starting value, so this code generates
    a stream of integers, starting at one. To figure out subsequent numbers, the code will execute whatever I pass as the
    second parameter, which needs to be a Unary Operator. A Unary operator is any lambda expression that takes a parameter
    of one type, returning a result of the same type. In this case, the operator needs to operate on integers, because
    that's the type of my stream values. The seed will be fed into the unary operator, this lambda expression I've declared
    here, and it will operate first on the seed, and then every value after that. Running this code,

                    1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20

    I get the numbers 1 through 20 printed out. Now, let's make this example more interesting.
*/
//End-Part-8

        System.out.println();
        IntStream.iterate(1, n -> n + 1)
                .filter(Main::isPrime)
                .limit(20)
                .forEach(s -> System.out.print(s + " "));

//Part-9
/*
        I'll add a method you've seen before, the isPrime method from a previous video. I'll just paste this in, as a method
    on my Main class. I'll use this method, and my infinite stream to get the first 20 prime numbers. Getting back to my
    stream pipeline, I'm going to add a filter, to check if the number is prime. I'm going to add this, before the limit
    operation. I don't really know how many integers it will take, to get the first 20 prime numbers, and with this code
    I really don't need to know. I can use a method reference on a static method, and that's what I'll do here, with
    Main::isPrime. Ok, let's run this,

                    2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71

    and now you can see the first 20 primes, and it took 71 integers to get to that requirement. If I want all the prime
    numbers less than 100, I can reverse my intermediate operations. I'll do this next.
*/
//End-Part-9

        System.out.println();
        IntStream.iterate(1, n -> n + 1)
                .limit(100)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));

//Part-10
/*
        I'll copy the statements from the codes above, and paste a copy directly below of Part-9. For this pasted code,
    I'll cut the limit operation, and paste it above the filter operation. I'll change 20 to 100 in that limit operation,
    so it will process the first 100 integers, but filter those that are prime numbers. Running this,

                        2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97

    I now get the prime numbers that are less than or equal to 100. Consider the differences between these two pipelines
    for a minute. In the first case, numbers can be fed indefinitely into the streaming process, until enough numbers
    match the condition first, and then match the limiting size. In the second case though, I'm saying that I only want
    to check a specified number of elements, and will operate on that specific size. Because streams are lazy, the stream
    process isn't going to generate an infinite number of possibilities first and then process the other operations. In
    other words, it's not going to hang on the stream creation. The evaluation stage I talked about earlier, that optimizes
    stream operations, will manage how many elements are actually created in the stream, to provide the desired result.
    However, let me add a word of caution. If you don't provide a limiting factor, you can still produce an out of memory
    condition. The iterate method has an overloaded version, which provides a finite stream, because it's conditional.
*/
//End-Part-10

        System.out.println();
        IntStream.iterate(1, n -> n <= 100, n -> n + 1)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));

//Part-11
/*
        I'll copy my last pipeline code, and paste a copy of that just below. The overloaded iterate method, has three
    parameters, the first is still the seed, but the second parameter, the additional parameter, is a predicate functional
    interface type. The third parameter is the UnaryOperator. I'll change this code, and insert a lambda expression, that
    takes a number, so n again, and will return a boolean. I want to check if n is less than or equal to 100. After this,
    I'll remove the limit operation. Running this new pipeline,

                        2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97

    I get the exact same result as the previous pipeline. The first uses an infinite stream, limited by an intermediate
    operation. The second uses a finite stream, it's limiting condition declared as the second parameter. There are two
    additional methods, which I can use on any of the primitive streams, so on IntStream like I have here, or with
    DoubleStream or LongStream. These are range and rangeClosed.
*/
//End-Part-11


        System.out.println();
        IntStream.rangeClosed(1, 100)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));


//Part-12
/*
        I'm going to take that last bit of code and paste a copy just below. I'll replace iterate with range, which takes
    a starting and ending value, and automatically increments by one. I'll replace the parameters with just 1 and 100.
    Running this code, I'll get the same data as the other two methods. This 100 is the upper bound and it's exclusive,
    meaning this stream is really returning numbers from 1 to 99. If I wanted the full range, I could replace range with
    rangeClosed, and I'll do that next. I'll re-run it now. In this case, it doesn't change the results, but you should
    recognize the difference between the two. The rangeClosed method in this example, produces numbers from 1, up to and
    including 100. Let me show you these stream creation methods on a summary slide.

                        Method                                                              Finite          Infinite
                  Collection.stream()                                                         X
                  Arrays.stream(T[])                                                          X
                  Stream.of(T...)                                                             X
                  Stream.iterate(T seed, UnaryOperator<T> f)                                  X                 X
                  Stream.iterate(T seed, Predicate<? super T> p, UnaryOperator<T> f)          X
                  Stream.generate(Supplier<? extends T> s)                                                      X
                **IntStream.range(int startInclusive, int endExclusive)                       X
                **IntStream.rangeClosed(int startInclusive, int endExclusive)                 X

        This table shows the eight methods I covered in this lecture. Two can produce infinite streams, the Stream.generate
    method as well as Stream.iterate, which doesn't include a Predicate parameter. There are other factory methods, that
    return empty and single element streams. I'll discuss these in a later context, because at this stage, it probably
    wouldn't make sense why you'd want to use these. I have a challenge for you in the next lecture, and then after that,
    I'll be reviewing the variety of ways to operate on a stream.
*/
//End-Part-12
    }

    public static boolean isPrime(int wholeNumber) {

        if (wholeNumber <= 2) {
            return (wholeNumber == 2);
        }

        for (int divisor = 2; divisor < wholeNumber; divisor++) {
            if (wholeNumber % divisor == 0) {
                return false;
            }
        }

        return true;
    }
}
