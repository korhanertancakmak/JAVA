package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_Streams.Course01_StreamInAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> bingoPool = new ArrayList<>(75);

        int start = 1;
        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++) {
                bingoPool.add("" + c + i);
                //System.out.println("" + c + i);
            }
            start += 15;
        }

//Part-3
/*
        The first number on a ball starts at 1, so I want a local variable, start, and that gets set to one. I'll set up
    an enhanced for loop, to iterate over the characters. I'll use the literal string, BINGO with the toCharArray, to get
    a character array. This means I'll be looping through the characters in the BINGO word. I want a nested loop, with 15
    iterations. Each letter will get 15 different numbers, but for each letter, these numbers will increase by fifteen.
    If I just passed c plus i to this method, I'd get an integer back as a result, not the character letter concatenated
    to the integer value. If I start with an empty string, the values are concatenated. Next, I'll print out each generated
    ball label. After each letter is processed, I need to increase the starting number by 15. If I run this code, I'll
    get 75 labels printed out, from B1 to O75. Next, I want to comment out that System.out.println statement in the for
    loop, since I've confirmed this is working.
*/
//End-Part-3

        Collections.shuffle(bingoPool);
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------");

//Part-4
/*
        Now, I'll shuffle these, using Collections.shuffle, passing it my bingoPool. After they're shuffled, I'll print
    the first 15. I'll use a traditional for loop to do this, stopping after 15 iterations. I'll include a separation line
    here. Running this, I'll see the first fifteen shuffled labels.
*/
//End-Part-4

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

//Part-5
/*
        Now, I'll assign those first fifteen to another list variable, called first Ones, by using subList, with the
    arguments, 0 to 15. I'll now sort these first 15, in natural order, meaning the string order. I'll use the replace
    All method, to do some kind of transformation on each one. And actually, I'll only do this for the G and O labels,
    let's just say I want to add a dash between the character and the number, so that the G and O are more easily visible
    to the reader. I'll print the updated label for this selected group. I'll return the new label, which will replace
    the old label in the list. If it's not an O, or a G label, I'll just keep the old value, so I'll return that. I'll
    print a new line, and a separator line. Running this code,

                        G55
                        G53
                        B11
                        G60
                        G52
                        N38
                        B3
                        G49
                        N36
                        N42
                        B15
                        B9
                        O72
                        B10
                        B4
                        ----------------------------------
                        G-49 G-52 G-53 G-55 G-60 O-72
                        ----------------------------------

    the result will be different each time, but you should see a sorted list of the G and O labels, with dashes included.
    Ok, so nothing new here. I'm creating a collection, shuffling it, getting a sublist, and filtering part of it to do
    some kind of transformation on that set of elements.
*/
//End-Part-5

        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------");

//Part-6
/*
        I'm going to next print the first 15 labels again from my bingo pool list. I'll copy and paste the statements at
    the end of my main method. Running this, what I want you to see is, that everything I did to my first Ones list, was
    done to the bingo Pool list. This is just a reminder that the subList method returns a view, and to use sublist only
    when you really do want to alter the original list. If I didn't want these changes to effect my original shuffled
    bingo pool, and I don't here, then I should make a modifiable copy of the sublist. I can do this by creating a new
    array list, and passing that sublist to it. I'll comment out that first assignment on line 29. I'll wrap a new ArrayList
    instance around that call to sublist. I'll run this code again,

                        ---(same)
                        G55
                        G53
                        B11
                        G60
                        G52
                        N38
                        B3
                        G49
                        N36
                        N42
                        B15
                        B9
                        O72
                        B10
                        B4
                        ----------------------------------
                        G-49 G-52 G-53 G-55 G-60 O-72
                        ----------------------------------

    and now you can see I haven't changed anything on the original bingo pool list. It's the same as it was previously.
    Next, I want to do the same kind of thing, on the first 15 labels, using a stream.
*/
//End-Part-6

        bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted()
                .forEach(s -> System.out.print(s + " "));

        System.out.println("\n----------------------------------");

//Part-7
/*
        You may have noticed in your own research of the Collection interface and it's methods, that it declares both a
    stream and parallelStream method. That means we can create a stream from any Collection type, and I'll do that here,
    with the bingo Pool. That returns an implementation of the Stream interface, and in this case, the type of implementation
    isn't important. Having a stream, I now have access to all of the stream methods, so I can chain them to this stream.
    It's common practice to start each new chained operation, on it's own line, starting with the dot in the chain. I'll
    start with a function called limit, which limits the number of elements I get from the stream. Like before, I just
    want the first 15, and I can do this by passing that value, to the limit operation, as the first operation. I'll next
    call filter, and pass this operation the same condition I had before, to get the G and O labels. I'll follow this with
    map, which you can think of as similar to the replaceAll method on collections. I'll add that dash between the character
    and number. I'll sort the stream at this point. Finally, I'll print each label followed by a space on one line.
    Running this code, I get the same set of labels, in the same format, as the code above. For good measure, I'll copy,
    pasting those statements at the end of the method, to see if this code had any effect on the original bingo pool
    collection. Running that, you'll see that my first 15 elements on the bingo pool are unchanged. This stream code didn't
    have any effects on it. Take a minute to compare that. The stream code is a little more concise, meaning one or two
    statements less, but it's a lot more readable, and ordered. Also, the code above was left to the programmer, to decide
    and implement, both what to do, and how to get the job done. With streams, there's a pattern of operations to choose
    from, which means the developer just describes what needs to be done, and doesn't have to code the how to do it part.
    The results are also different from these two segments of code. In the first, I have an Array list of fifteen labels,
    and I've changed a few of those labels in that list. In the second segment of code, I'm simply printing information
    out about a subset of the data, after I've filtered and transformed it. Right now, it's not collecting results into
    a variable, though I could have done that as well. Each executable step in this second segment of code, is called an
    operation, when using streams. I'll be covering each of these operations and quite a few more, over the course of the
    next couple of lectures. But this chain, this listing of these five operations, in this particular order, is called a
    stream pipeline. This is what I want to examine next, because not all operations in the pipeline are equal. In the
    next video, I'll be talking about the structure of this stream pipeline.
*/
//End-Part-7

        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
    }
}
