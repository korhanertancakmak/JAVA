package CourseCodes.NewSections.Section_07_OOP1.Course21_StringBuilderClass;

/*
Course-51

                                            The StringBuilder Class

        Because String is immutable, each method all returns a new instance of a String. As an alternative, Java provides
    a mutable class that lets us change its text value, or character sequence. This is the StringBuilder class.

                                               Creating Instances

            Instantiating String Objects                                    Instantiating StringBuilder Objects

    String hello = "Hello";                                                 StringBuilder helloBuilder = new StringBuilder("Hello");
    String helloWorld = "Hello" + " World";                                 StringBuilder emptyBuilder = new StringBuilder();
    String badHello = new String("Hello"); // Valid code, but redundant     StringBuilder emptyBuilder5 = new StringBuilder(5);
                                                                            StringBuilder emptyBuilder = new StringBuilder(helloBuilder);

    We've already talked about creating Strings, and the preferred way, the most efficient way, is simply to assign a literal,
    or concatenated literals to a variable. We show this on the left above. Let's compare that to the StringBuilder, which
    has 4 overloaded constructors. These are shown on the right above.

        There are 4 ways to create a new StringBuilder object, using the new keyword:
  - Pass a String.
  - Pass no arguments at all.
  - Pass an integer value.
  - Pass some other type of character sequence(like StringBuilder)

    Getting back to the code, let's compare the String and StringBuilder classes a bit. First, we'll create a couple of
    overloaded methods, one that will take a String, and the other will take a StringBuilder. Both of these will print
    the argument that's passed, and both will call the length method, which is a method on both String and STringBuilder.

                        public static void printInformation(String string) {
                            System.out.println("String = "+ string);
                            System.out.println("length = " + string.length());
                        }

    Here, we're printing out the String, and the length of the String, passed as the argument. We'll create the same method
    by changing parameter type and name for StringBuilder:

                        public static void printInformation(StringBuilder builder) {
                            System.out.println("StringBuilder = "+ builder);
                            System.out.println("length = " + builder.length());
                        }

    And that all compiles, so now, in the main method, let's create some objects:

                        String helloWorld = "Hello" + " World";

                        StringBuilder helloWorldBuilder = "Hello" + " World";

    What i wanted to show you with this code, this second statement doesn't compile. This is an important difference between
    the String and the StringBuilder, you can't assign a String literal to a StringBuilder variable. We have to use one of
    the constructors, so let's do that.

                        StringBuilder helloWorldBuilder = new StringBuilder("Hello" + " World");

    We've created 2 types of objects, which have the same set of characters in them. Let's make a call to our printInformation
    methods for each of these objects:

                        printInformation(helloWorld);
                        printInformation(helloWorldBuilder);

    and if we run that,

                        String = Hello World
                        length = 11
                        StringBuilder = Hello World
                        length = 11

    we see that the sequences have the same length, as you'd expect, since the values are the same. Ok, so now, let's
    examine what we mean, when we say the StringBuilder's text value is mutable, but the String's isn't.

        First, we'll use the concat method on String, which is a lot like using the plus operator on String. The result
    is a new String object, with the argument value concatenated to the existing text. For StringBuilder, a similar method
    is called "append", so we'll use that:

                        helloWorld.concat(" and Goodbye");

                        helloWorldBuilder.append(" and Goodbye");

    And running the code now:

                        String = Hello World
                        length = 11
                        StringBuilder = Hello World and Goodbye
                        length = 23

    look carefully at the results. String is still "Hello World", but StringBuilder is "Hello World and Goodbye", and its
    length is increased. Let's talk about what happened, by looking at a quick diagram:

                              Memory before executing helloWorld.concat method

                                        |¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯|
                                        |        Heap Memory      |
                                        |                         |
                        helloWorld ==========>  "Hello World"     |
                                        |                         |
                                        |                         |
                                        |_________________________|

                              Memory after executing helloWorld.concat method

                                        |¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯|
                                        |        Heap Memory      |
                                        |                         |
                        helloWorld ==========>  "Hello World"     |
                                        |                         |
                                        |      " and Goodbye"     |
                                        |                         |
                                        |"Hello World and Goodbye"|
                                        |                         |
                                        |_________________________|

    When we passed the String literal, "and Goodbye", to the concat method, this created an Object in memory for that literal,
    " and Goodbye". It also created the result of the concat method, the object, the String, that has the value, "Hello
    World and Goodbye". But our code has a mistake in it, because we didn't assign the result of the method, the concat
    method, to a variable. This is actually a common mistake to make.

        It's important to remember to assign the result, of any String manipulation method you call on a String, to a variable.
    These methods don't change the internals of the existing String object, as we show at the second diagram above. The
    String referenced by the helloWorld variable never changed, instead a new String was created by the method call. Now,
    let's compare that to what happened with the StringBuilder:

                              Memory before executing helloWorldBuilder.append method

                                        |¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯|
                                        |        Heap Memory      |
                                        |                         |
                 helloWorldBuilder ===  |       "Hello World"     |
                                     ∥  |                         |
                                     ∥  |                         |
                                     ========>  'Hello World'     |
                                        |                         |
                                        |_________________________|

                              Memory after executing helloWorldBuilder.append method

                                        |¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯ ¯|
                                        |        Heap Memory      |
                                        |                         |
                 helloWorldBuilder ===  | "Hello World"           |
                                     ∥  |                         |
                                     ∥  |         " and Goodbye"  |
                                     ∥  |                         |
                                     ===>'Hello World and Goodbye'|
                                        |                         |
                                        |_________________________|

    On these diagrams, I show String and StringBuilders in different quotation marks("" and ''). We still have the objects
    in memory, that represent the String literals, that were passed to the StringBuilder, in this case 'Hello World'. But
    you can see, that after the call to the "append" method, we still only have 1 StringBuilder object. The variable
    helloWorldBuilder, is still referencing the same object, but the value of that object changed. This is important,
    because it means the character sequence in the StringBuilder changed. And this time, we didn't have to assign the result,
    to another variable to access the result.

        String methods create a new object in memory, and return a reference to this new object. StringBuilder methods
    return a StringBuilder reference, but it's really a self-reference, or a reference to the same object. Unlike Strings,
    we can call methods on StringBuilder, without the need to assign the results, to intermediate variables, as we saw
    with Strings. StringBuilder methods return this self-reference, to support chaining methods together.

        Ok, so getting back to the code. In the main method, we'll create 2 more StringBuilder instances. This time, we'll
    make them both start out with empty character sequences. In other words, they won't contain any characters, or any text.
    And we'll call our printInformation method for StringBuilder for both.

                        StringBuilder emptyStart = new StringBuilder();
                        StringBuilder emptyStart32 = new StringBuilder(32);

                        printInformation(emptyStart);
                        printInformation(emptyStart32);

    In the first case, we don't pass any arguments in the constructor, but in the second, we're passing a number, 32. Let's
    run this and see what we get:

                        StringBuilder =
                        length = 0
                        StringBuilder =
                        length = 0

    In both cases, we have empty strings, as the value of the StringBuilder sequence, and length = 0. Ok, what's the difference
    here? Before we talk about that, let's change our information method, and add a call to another method on StringBuilder,
    a method named "capacity":

                        System.out.println("capacity = " + builder.capacity());

    we'll run this code, then we'll talk about it:

                        StringBuilder =
                        length = 0
                        capacity = 16
                        StringBuilder =
                        length = 0
                        capacity = 32

    A StringBuilder is mutable, which means it can shrink, or grow, in size. By default, an empty StringBuilder starts out
    with a capacity of 16, meaning it can contain up to 16 characters, before it needs to request more memory. In the second
    example, we created a StringBuilder with a starting capacity of 32, which means our sequence can grow up to 32 characters,
    without needing to request additional allocation.

        Every time a StringBuilder needs to increase capacity, the data stored in the original storage, needs to get copied
    over to the larger storage area. Let's see this in action. Let's append some text to our 2 empty StringBuilder objects.

                        emptyStart.append("a".repeat(17));
                        emptyStart32.append("a".repeat(17));

    Here, we're using the repeat method, which we introduced you to in the last course. So this creates a 17 character string,
    filled with a's. For the first StringBuilder object, which has a capacity of 16, this is going to require a request
    for a larger memory area, to store the extra data. Let's run this:

                        StringBuilder = aaaaaaaaaaaaaaaaa
                        length = 17
                        capacity = 34
                        StringBuilder = aaaaaaaaaaaaaaaaa
                        length = 17
                        capacity = 32

    First, look at the value in the StringBuilder objects, both contain a String of a's, and both have a length of 17, as
    expected. But in the first case, the capacity is now 34(when we started out with 16). The new allocation size is
    determined by JVM. In the second case, the capacity is still 32, because we didn't require a reallocation, since the
    appended string still fit in the original capacity.

        What happens, if we change our code in the first instance, to something bigger than 34 characters(which we saw was
    the next allocation 'step')? Let's change our first call to repeat, to create a String of 57 characters.

                        StringBuilder = aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                        length = 57
                        capacity = 57
                        StringBuilder = aaaaaaaaaaaaaaaaa
                        length = 17
                        capacity = 32

    Now, the output shows that the capacity of the first empty StringBuilder, is actually the same size, as the String we
    passed in, 57. At this stage, It's not that important that you understand how the next capacity is determined. But it
    is important to understand that the capacity does need to adjust, as the text in your StringBuilder grows. If you know
    you plan to house a large text value in your StringBuilder object, start out with a larger capacity.

                                    Some methods unique to the StringBuilder Class

                method              description
                --------------------------------------------------------------------------------------------------------
                delete              You can delete a substring using indices to specify a range, or delete a single character
                deleteCharAt        at an index.
                --------------------------------------------------------------------------------------------------------
                insert              You can insert text at a specified position.
                --------------------------------------------------------------------------------------------------------
                reverse             You can reverse the order of the characters in the sequence.
                --------------------------------------------------------------------------------------------------------
                setLength           setLength can be used to truncate the sequence, or include null sequence to "fill out"
                                    the sequence to that length.
                --------------------------------------------------------------------------------------------------------

    A StringBuilder class has many similar methods to Strings. But it also has methods to remove and insert characters or
    Strings, and truncate it's size. The table above shows some of these methods. delete() and deleteCharAt() will remove
    text or a character from the StringBuilder's text. The insert() method inserts text, into the StringBuilder text at
    the specified index. reverse() reverses the characters in the sequence. And setLength() is a way to truncate a StringBuilder's
    text value.

        Let's code for deleting a character, the capital G, and while we're at it, we'll insert a lowercase g, where the
    uppercase G used to be:

                            StringBuilder builderPlus = new StringBuilder("Hello" + " World");
                            builderPlus.append(" and Goodbye");

                            builderPlus.deleteCharAt(16).insert(16,'g');
                            System.out.println(builderPlus);

    We're chaining methods here again, this time with the StringBuilder object. And running that:

                            Hello World and goodbye

    the output shows that it looks like we've just replaces a big G, with a little g. But we did it by, first using the
    deleteCharAt method, and then the insert method. The StringBuilder class does also have a replace method, which requires
    a start and end index, to identify what will be replaced, which is different from the replace method for String. Let's
    set that G back to a big G, using the StringBuilder replace method:

                            builderPlus.replace(16, 17, "G");
                            System.out.println(builderPlus);

    And running that:

                            Hello World and Goodbye

    Again, the ending index we use with this method is used as an exclusive index, so this code, simply replaces a single
    character, the letter lowercase g.

        And lastly, let's look at the reverse method and the setLength method, and we'll chain them together.

                            builderPlus.reverse().setLength(7);
                            System.out.println(builderPlus);

    The reverse method will reverse all tha characters and then call the setLength method, with the number 7, meaning we're
    truncating the StringBuilder text value, to 7 characters. and running that:

                            eybdooG

    And we see the result of that, Goodbye spelled backwards.
*/

public class Main {
    public static void main(String[] args) {

        String helloWorld = "Hello" + " World";
        helloWorld.concat(" and Goodbye");

        StringBuilder helloWorldBuilder = new StringBuilder("Hello" + " World");
        helloWorldBuilder.append(" and Goodbye");

        printInformation(helloWorld);
        printInformation(helloWorldBuilder);

        StringBuilder emptyStart = new StringBuilder();
        emptyStart.append("a".repeat(57));

        StringBuilder emptyStart32 = new StringBuilder(32);
        emptyStart32.append("a".repeat(17));

        printInformation(emptyStart);
        printInformation(emptyStart32);

        StringBuilder builderPlus = new StringBuilder("Hello" + " World");
        builderPlus.append(" and Goodbye");

        builderPlus.deleteCharAt(16).insert(16,'g');
        System.out.println(builderPlus);

        builderPlus.replace(16,17,"G");
        System.out.println(builderPlus);

        builderPlus.reverse().setLength(7);
        System.out.println(builderPlus);
    }

    public static void printInformation(String string) {
        System.out.println("String = "+ string);
        System.out.println("length = " + string.length());
    }

    public static void printInformation(StringBuilder builder) {
        System.out.println("StringBuilder = "+ builder);
        System.out.println("length = " + builder.length());
        System.out.println("capacity = " + builder.capacity());
    }
}
