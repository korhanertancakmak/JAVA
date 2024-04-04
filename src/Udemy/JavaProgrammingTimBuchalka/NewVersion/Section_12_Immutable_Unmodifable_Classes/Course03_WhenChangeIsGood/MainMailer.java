package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course03_WhenChangeIsGood;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//Part-5
/*
        and I'll add a main method with the pvsm shortcut. In this class, I'll have a list containing duplicate names,
    then use a map to keep track of the counts for each distinct name. I'll also write some code to standardize names,
    which would be called prior to a mailing, to clean up names before they get printed on an envelope. I'll start with
    an array of names, an array of Strings. I'll include two versions of Ann Jones, one with, and one without the PhD
    suffix, and I'll have Bob Jones, MD. Next a simple Carol Jones, and then two Ed Greens, one's a PhD and one's an MD,
    and finally Ed Black.I'll set up a private static method, that creates a random list of these names.
*/
//End-Part-5

public class MainMailer {

    public static void main(String[] args) {

        String[] names = {"Ann Jones", "Ann Jones Ph.D.", "Bob Jones M.D.",
                "Carol Jones", "Ed Green Ph.D.", "Ed Green M.D.", "Ed Black"};

        List<StringBuilder> population = getNames(names);
        Map<StringBuilder, Integer> counts = new TreeMap<>();
        population.forEach(s -> {
            counts.merge(s, 1, Integer::sum);
        });
        System.out.println(counts);

//Part-7
/*
        I'll first create a local variable, population, again a list of string builder. I'll assign that the result of
    calling my getNames method, passing it the names array. That should give me a long list of names, with a lot of duplicates.
    Next, I'll set up a map of the counts of duplicate names. The key will be a StringBuilder, and the value's going to
    be an integer. I'll call this variable counts, and set it to a new instance of a tree map, so this map will be SORTED.
    To populate the map, I'll loop through population. Using map's merge method lets me add a new name with a value of 1,
    if it's a name not yet in the map, or increment the value that's already in the map, if it is there. after this, I'll
    just print my map. If I run that,

                    {Ann Jones=3, Ann Jones Ph.D.=4, Bob Jones M.D.=5, Carol Jones=6, Ed Black=9, Ed Green M.D.=8, Ed Green Ph.D.=7}

    I get counts for each distinct name. Remember the suffixes are included in the name, and that makes them unique. My
    population has 3 Ann Jones, but 4 Ann Jones that have phd's, so some pretty smart Ann Jones. You can see each name
    has a different count, because of the way I set up my population list. Just to confirm, I'll print out the number of
    PhD Ann Jones I have in the map.
*/
//End-Part-7

        StringBuilder annJonesPhd = new StringBuilder("Ann Jones Ph.D.");
        System.out.println("There are " + counts.get(annJonesPhd) + " records for " + annJonesPhd);

//Part-8
/*
        First, I'll set up a local string builder, setting that to Ann Jones Ph d. I'll use the get method, to get the
    count of how many ann Jones with phd's there are in my population. Running that,

                There are 4 records for Ann Jones Ph.D.

    I can see that I get 4 records for Ann Jones Phd, so that's good. Ok, now let's say it's our job, to mail a flyer,
    to this population. It's our company's policy to remove suffixes, before printing the name on the envelope, for
    privacy purposes, so I'll create a method called standardize names.
*/
//End-Part-8

        List<StringBuilder> cleanedNames = standardizeNames(population);
        System.out.println(cleanedNames);

//Part-10
/*
        Running this,

        [Ann Jones, Ann Jones, Ann Jones, Ann Jones, Ann Jones, Ann Jones, Ann Jones, Bob Jones, Bob Jones, Bob Jones, Bob Jones, Bob Jones, Carol Jones, Carol Jones, Carol Jones, Carol Jones, Carol Jones, Carol Jones, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Black, Ed Black, Ed Black, Ed Black, Ed Black, Ed Black, Ed Black, Ed Black, Ed Black]

    you can see I have a long list of names, and no suffixes on any of them. If I was working with real mailing data, I'd
    have an address somewhere, and I'd address the envelope with this simplified name, and the address. Let's say I've
    successfully mailed my population, and now I want to target all Ann Jones PhD records again, for some reason. In fact,
    I'm just going to copy that statement

                    System.out.println("There are " + counts.get(annJonesPhd) + " records for " + annJonesPhd);

    and paste it below.
*/
//End-Part-10

        System.out.println("There are " + counts.get(annJonesPhd) + " records for " + annJonesPhd);

//Part-11
/*
        If I run

                There are null records for Ann Jones Ph.D.

    that, I would expect to get the same amount I got before I standardized the names, but I'm getting null back. I'll
    print out my counts map next.
*/
//End-Part-11

        System.out.println(counts);

//Part-12
/*
        Running that,

                    {Ann Jones=3, Ann Jones=4, Bob Jones=5, Carol Jones=6, Ed Black=9, Ed Green=8, Ed Green=7}

    I see something very weird. My tree map's names have all lost their suffixes. Not only that, it looks like I have
    duplicate keys in my tree map. There are two entries for Ann Jones, each with a different count, and also Ed Green
    is showing the same thing. That's ugly. What will I get if I try to look up just Ann Jones?
*/
//End-Part-12

        StringBuilder annJones = new StringBuilder("Ann Jones");
        System.out.println("There are " + counts.get(annJones) + " records for " + annJones);

//Part-13
/*
        I'll create a new String builder variable, called ann jones, and assign that a new instance with just the text ann
    jones. I'll print out the same message as before, but use this new variable to get the counts from the treemap. Running
    that

                {Ann Jones=3, Ann Jones=4, Bob Jones=5, Carol Jones=6, Ed Black=9, Ed Green=8, Ed Green=7}
                There are 4 records for Ann Jones

    you can see I do get counts, but it's the counts for only one of the Ann Jones entries. This map is really messed up.
    Let me try something else. I'll loop through the map entries and see what I get.
*/
//End-Part-13

        System.out.println("-----------------------");
        counts.forEach((k, v) -> System.out.println(k + " : " + v));

//Part-14
/*
        I'll print a separator line. I'll call for each on counts, and remember that means I have key and value as the
    arguments, and I'll print those. If I run that,

                    -----------------------
                    Ann Jones : 3
                    Ann Jones : 4
                    Bob Jones : 5
                    Carol Jones : 6
                    Ed Black : 9
                    Ed Green : 8
                    Ed Green : 7

    I do get each entry, each key, with individual counts. Now, let me loop through the key set instead.
*/
//End-Part-14

        System.out.println("-----------------------");
        counts.keySet().forEach(k -> System.out.println(k + " : " + counts.get(k)));

//Part-15
/*
        I'll print another separator line. I can use keySet to loop through my map's keys. I'll print k, the key, then
    use it to get counts for that key. Now running that,

                    -----------------------
                    Ann Jones : 3
                    Ann Jones : 4
                    Bob Jones : 5
                    Carol Jones : 6
                    Ed Black : 9
                    Ed Green : 8
                    Ed Green : 7
                    -----------------------
                    Ann Jones : 4
                    Ann Jones : 4
                    Bob Jones : 5
                    Carol Jones : 6
                    Ed Black : 9
                    Ed Green : 8
                    Ed Green : 8

    I see that I get two different results I get different keys and values when I loop through the map's entries, comparing
    that to when I looped through the keys, and use that key to get the values. That's not good. The standardized Names
    method, which seemed harmless enough, has produced a very ugly side effect, on my map of StringBuilders. It didn't
    matter what collection my StringBuilders were in, they were all referring to the same group of instances in memory.
    A change to one variable in any collection, will change that instance in memory. If that instance's a key to a mapped
    collection, you get into this ugly situation. This is why you should use an immutable object, for keys in a map, so
    that this never happens. Ok, so these are two examples of side effects, and the dangers that are possible, if you're
    using mutable objects but not programming defensively. The good news is that there's strategies for managing change,
    so you can reduce or eliminate side effects.

                                                Controlling Change

        Java provides mechanisms to control changes, and extensibility of your code, at many different levels. You can
    prevent:

       * Changes to data in Instance fields, which is called the state of the object, by not allowing clients or
        subclasses to have access to these fields.
       * Changes to methods, by not allowing code to override or hide existing functionality.
       * Your classes from being extended. You can also prevent Instantiation of your classes.

    I'll be reviewing each of these, in turn, over the next couple of lectures.
*/
//End-Part-15
    }

//Part-6
/*
        It'll return a List of StringBuilder objects, I'll call it get names, and it takes an array of strings I'll set
    up a local variable, this is the list I'll return, and I want it to be an Arraylist. I'll use an index determine how
    many names to add to my list for each distinct name. I'll return the list from this method. In this loop, I'm going
    to add a number of the same names to my list, determined by the value in my index variable. I could just add 5 of each,
    for example, but this will make the output a bit more interesting. A nested loop will loop from 0 to the current index
    value. I'll add a new StringBuilder instance to the list, using the name to construct it. I'll increment index after
    each name, so that I get a different number of names for each name in the array. Getting back to the main method,
*/
//End-Part-6

    private static List<StringBuilder> getNames(String[] names) {

        List<StringBuilder> list = new ArrayList<>();
        int index = 3;
        for (String name : names) {
            for (int i = 0; i < index; i++) {
                list.add(new StringBuilder(name));
            }
            index++;
        }
        return list;
    }

//Part-9
/*
        I'll make it private static, it returns a list, and takes a list. I'll set up a new ArrayList. I'll loop through
    the list, the method argument. I'll add code here in just a minute. I'll return my new list of standardized names back.
    I want to loop through a list of possible suffixes. Right now I only have Phd and MD, but you can imagine others could
    be added. I need a local variable to hold an index. I'll check if the suffix is in the name, and pass that index back.
    If it's greater than -1, there's a matching suffix, and I want to strip that out. I'll replace the suffix, with an
    empty string. And I'll add the cleaned up name to my new list. I'll call this from the main method, and print out
    the cleaned names.
*/
//End-Part-9

    private static List<StringBuilder> standardizeNames(List<StringBuilder> list) {

        List<StringBuilder> newList = new ArrayList<>();
        for (var name : list) {
            for (String suffix : new String[]{"Ph.D.", "M.D."}) {
                int startIndex = -1;
                if ((startIndex = name.indexOf(suffix)) > -1) {
                    name.replace(startIndex - 1,
                            startIndex + suffix.length(), "");
                }
            }
            newList.add(name);
        }
        return newList;
    }

}
