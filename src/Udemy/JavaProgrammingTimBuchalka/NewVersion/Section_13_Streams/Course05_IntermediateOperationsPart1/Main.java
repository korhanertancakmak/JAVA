package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_Streams.Course05_IntermediateOperationsPart1;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//Part-1
/*                                      The Most Common Intermediate Operations
        Up until now, I've kind of glossed over intermediate operations. For one thing, most aren't that complicated,
    or difficult to understand. I've used filter, limit, map and sorted in my examples. I also showed you a quick example
    of using distinct in the last challenge.

                Return Type         Operation

                Stream<T>           distinct()
                Stream<T>           filter(Predicate<? super T> predicate)
                                    takeWhile(Predicate<? super T> predicate)
                                    dropWhile(Predicate<? super T> predicate)
                Stream<T>           limit(long maxSize)
                Stream<R>           map(Function<? super T, ? extends R> mapper)
                Stream<T>           peek(Consumer<? super T> action)
                Stream<T>           skip(long n)
                Stream<T>           sorted()
                                    sorted(Comparator<? super T> comparator)

    As you can see from this table, the operations you've already seen briefly, cover half of the basic operations available
    to your stream pipelines.

                Return Type         Operation                                       Description

                Stream<T>           distinct()                                      Removes duplicate values from the Stream.
                Stream<T>           filter(Predicate<? super T> predicate)          These methods allow you to reduce the elements in the output stream.
                                    takeWhile(Predicate<? super T> predicate)       Elements that match the filter's Predicate are kept in the outgoing stream, for the filter and takeWhile operations.
                                    dropWhile(Predicate<? super T> predicate)       Elements will be dropped until or while the dropWhile's predicate is not true.
                Stream<T>           limit(long maxSize)                             This reduces your stream to the size specified in the argument.
                Stream<T>           skip(long n)                                    This method skips elements, meaning they won't be part of the resulting stream.

    I'll start by talking about the set of operations, that may change the number of elements, in the resulting stream.
    In this group, the first is the distinct operation, which removes any duplicate values. This operation depends on the
    stream type's equals method, to determine the uniqueness of a stream element. Next, there is filter, which takes a
    conditional statement, that evaluates to true or false, usually based on some attribute of the stream element. Elements
    that match an operation's predicate, meaning the condition returns true for that element, are kept in the outgoing
    stream, for both the filter and takeWhile operations. In contrast, elements will be dropped from the stream, until
    or while the operation, dropWhile's predicate is not true. You've already seen the limit operation in action. This
    defines the exact size of the stream, cutting off the stream elements when this size has been reached. This operation
    usually turns infinite streams into finite streams. The skip method allows you to skip a certain number of elements,
    meaning they won't be in the outgoing stream. Let's see what we can do with some of these in code.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

//Part-2
/*
        I'm going to start with the ABC's. I'll use IntStream.iterate, casting the capital letter A to an integer value.
    This lets me do integer math easier on my stream. This also means my Stream is made up of integer primitives. I'll
    use the three parameter version of iterate, so my second argument is a predicate lambda. I want to continue to produce
    integers for the stream, until I get to the integer value of a lower case z. To do this, I'll make this condition test
    that the value of i, is less than or equal to whatever the integer value of lowercase z might be. Finally, the last
    argument is a UnaryOperator, and that's just going to increment each subsequent value by one. I'm going to get integers
    representing any characters between uppercase A and lowercase z. I'll print that out, using print f. I can pass an
    int to a character specifier, as I show here. Here is another example of a pipeline, that has no intermediate operations.
    If I run this,

            A B C D E F G H I J K L M N O P Q R S T U V W X Y Z [ \ ] ^ _ ` a b c d e f g h i j k l m n o p q r s t u v w x y z

    you can see, I get a couple of characters that aren't alphabetical characters.
*/
//End-Part-2

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

//Part-3
/*
        I'll filter those out. The Character wrapper provides a method, called isAlphabetic, that takes an integer, and
    returns a boolean, so this is a perfect opportunity for a method reference. Running this

            A B C D E F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z

    gives us just the uppercase and lowercase alphabet now. Let's say that next,
*/
//End-Part-3

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .dropWhile(i -> Character.toUpperCase(i) <= 'E')
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

//Part-4
/*
        I don't want any letters, from A through E. This can be done with an additional filter I can add the code to my
    first lambda expression, or I can include an additional filter operation, which is what I'll do here. In this case,
    I can use another method on the Character wrapper class, to upper case, that takes an integer, and compare that to
    the character, a literal E. If the value is greater than E, it will be part of the output stream. There's really no
    limit to the number of intermediate operations you specify, and you can use one operation many times, and in any order
    you want. Again, these operations are a specification to the stream processor. The processor may decide it's more
    efficient to join those two conditions together, in the black box of the stream process. This is why we have to stop
    thinking of these operations as methods, because in some cases, they'll never be used in the way we define them, but
    will be optimized by the stream processing code. Running this code now,

                F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z

    you can see I get the result I wanted, regardless of the way the processor decided to get that work done.
*/
//End-Part-4

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .skip(5)
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

//Part-5
/*
        If I only wanted to skip the capital letters, a, through e, I could use the skip method. I'll comment out that
    second filter, to show you that. Next I'll add skip as the second operation, skipping 5. Running this,

            F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z
            F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z

    you can see this operation did exactly that, it skipped the first 5 generated elements, and my letters start with a
    capital F. You might ask why you'd do this, and this probably isn't a very good example, since I could just change
    my iterate method to do this.
*/
//End-Part-5

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .dropWhile(i -> i <= 'E')
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

//Part-6
/*
        You'll definitely encounter situations though, when you'll want to skip elements in a stream. There are two other
    methods that let you fine tune what gets skipped or what gets included, takeWhile and dropWhile. I'll remove out the
    skip 5 statement, and next include a dropWhile statement that will do the same thing. This operation takes a predicate
    and will filter out any elements while they match this condition. Unlike filter, this predicate is only evaluated for
    elements, until it first becomes false. I'll explain what I mean by that in more detail, in just a minute. Here, I'll
    drop any characters that are less than or equal to a capital letter E. If I run this,

                F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z

    I get the same output as with skip 5, but this method gives you a lot more control. As I said, this will continue to
    exclude elements until this predicate turns false.
*/
//End-Part-6

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .dropWhile(i -> Character.toUpperCase(i) <= 'E')
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

//Part-7
/*
        I'll change my predicate to ignore case, by making the character uppercase before I test it. Running this,

                F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z

    the output didn't change. Did you expect the letters, a through e, in lowercase to be dropped? Well, that's where the
    word while in the operation name becomes important. This happens until the predicate becomes false the first time,
    and then that condition is no longer checked. Think of this as a mini while loop. It'll drop data until the condition
    becomes false, and then it moves past the loop altogether. It's job in the pipeline is complete, and won't be revisited.
    If you combine this with a take while operation, you can effectively describe a range of elements you want.
*/
//End-Part-7

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .dropWhile(i -> Character.toUpperCase(i) <= 'E')
                .takeWhile(i -> i < 'a')
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

//Part-8
/*
        In this case, I'll take elements while they are less than a lowercase a. Running this

                        F G H I J K L M N O P Q R S T U V W X Y Z

    you can see I get letters F through Z, in uppercase. The dropWhile and takeWhile work well with ordered streams. If
    the stream isn't ordered, oracle tells us the result will be non-deterministic, meaning the results may differ upon
    subsequent executions. Finally, there's the distinct operation.


    First I'll comment out the dropWhile and takeWhile operations. I'll use a simple map function, to map all
    elements to an upper case letter, then I'll insert the distinct operation after that. Again, this is probably not the most logical
    piece of code, but it does demonstrate how distinct works. I'll run this, and confirm I only get A to Z here.
    For another example, I'll generate some random letters instead. First I'll print out a new line to separate
    the output. l'll set up a local variable, random, a new instance of the Random class. I'll create a Stream source.
    I'm going to use Stream this time instead of IntStream, so that you can see I can use Stream with integers somewhat seamlessly.
    Java will do all the auto boxing and unboxing required. I'll execute Stream.generate, which takes a supplier, so it has no arguments, but returns a value.
    In this case, I want to return the result of the random dot next Int method. I like to just pass the characters, casting
    them to ints, so it's clearer that I'm focusing on character values. Since this is a random generator, on an infinite stream, I'm going
    to set a limit of 50. Now I'll call distinct. I'll then sort my integers. And I'll print them out. I chose 50 to limit the stream, because I
    don't want to actually get a full set of characters each time. Using fifty as the limit, means the probability
    of producing a nearly full alphabetical set, is less than likely. Running this multiple times,
    you'll see that I get most of the letters when I use 50, as the limiting number. I won't usually get a full alphabet.
    If I remove distinct, I'll see every letter generated, but in order. Running that, you can see the results without distinct.
    If these pipelines are starting to feel like database queries to you, that's for good reason. The Java API designers designed the Stream
    to let you process data in a declarative way, much like a structured query language or SQL in a database.
    Again this lets you say what should happen, and not actually how it will happen. If you've had experience querying databases,
    you might be familiar with the limit and distinct keywords, available in some database query languages.
    The filter operation represents your where clause, and sorted would be your order by clause, and so on. When we get to terminal operations, you'll
    see There are aggregate functions commonly used in queries as well, such as max, min, count and so on.
    This feels like a good place to pause a moment, so I'll end this video here. In the next video, I'll continue the discussion
    with the second set of intermediate operations I want to talk about. These process all elements in the stream.
*/
//End-Part-8

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .map(Character::toUpperCase)
                .distinct()
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

//Part-9
/*
        First I'll remove out the dropWhile and takeWhile operations. I'll use a simple map function, to map all elements
    to an upper case letter, then I'll insert the distinct operation after that. Again, this is probably not the most logical
    piece of code, but it does demonstrate how distinct works. I'll run this,

                    A B C D E F G H I J K L M N O P Q R S T U V W X Y Z

    and confirm I only get A to Z here.
*/
//End-Part-9

        System.out.println();
        Random random = new Random();

        Stream.generate(() -> random.nextInt((int)'A', (int)'Z' + 1))
                .limit(50)
                .distinct()
                .sorted()
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

//Part-10
/*
        For another example, I'll generate some random letters instead. First I'll print out a new line to separate
    the output. I'll set up a local variable, random, a new instance of the Random class. I'll create a Stream source.
    I'm going to use Stream this time instead of IntStream, so that you can see I can use Stream with integers somewhat
    seamlessly. Java will do all the auto boxing and unboxing required. I'll execute Stream.generate, which takes a supplier,
    so it has no arguments, but returns a value. In this case, I want to return the result of the random.nextInt method.
    I like to just pass the characters, casting them to ints, so it's clearer that I'm focusing on character values. Since
    this is a random generator, on an infinite stream, I'm going to set a limit of 50. Now I'll call distinct. I'll then
    sort my integers. And I'll print them out.

                A B D E G H J K L M N O Q R S T U V X Y Z

    I chose 50 to limit the stream, because I don't want to actually get a full set of characters each time. Using fifty
    as the limit, means the probability of producing a nearly full alphabetical set, is less than likely. Running this
    multiple times, you'll see that I get most of the letters when I use 50, as the limiting number. I won't usually get
    a full alphabet.



     If I remove distinct, I'll see every letter generated, but in order. Running that, you can see the results without distinct.
    If these pipelines are starting to feel like database queries to you, that's for good reason. The Java API designers designed the Stream
    to let you process data in a declarative way, much like a structured query language or SQL in a database.
    Again this lets you say what should happen, and not actually how it will happen. If you've had experience querying databases,
    you might be familiar with the limit and distinct keywords, available in some database query languages.
    The filter operation represents your where clause, and sorted would be your order by clause, and so on. When we get to terminal operations, you'll
    see There are aggregate functions commonly used in queries as well, such as max, min, count and so on.
    This feels like a good place to pause a moment, so I'll end this video here. In the next video, I'll continue the discussion
    with the second set of intermediate operations I want to talk about. These process all elements in the stream.
*/
//End-Part-10

        Stream.generate(() -> random.nextInt((int)'A', (int)'Z' + 1))
                .limit(50)
                .sorted()
                .forEach(d -> System.out.printf("%c ", d));

//Part-11
/*
        If I remove distinct, I'll see every letter generated, but in order. Running that,

                A A B C C C D D D E E E F F H I I J J K K L L L L M M M M M N O O P P R T T T T T T U W W X X Y Y Z

    you can see the results without distinct. If these pipelines are starting to feel like database queries to you, that's
    for good reason. The Java API designers designed the Stream to let you process data in a declarative way, much like
    a structured query language or SQL in a database. Again this lets you say "what should happen", and "not actually how
    it will happen". If you've had experience querying databases, you might be familiar with the limit and distinct keywords,
    available in some database query languages. The filter operation represents your where clause, and sorted would be
    your order by clause, and so on. When we get to terminal operations, you'll see there are aggregate functions commonly
    used in queries as well, such as max, min, count and so on. In the next lecture, I'll continue the discussion with
    the second set of intermediate operations I want to talk about. These process all elements in the stream.
*/
//End-Part-11

    }
}
