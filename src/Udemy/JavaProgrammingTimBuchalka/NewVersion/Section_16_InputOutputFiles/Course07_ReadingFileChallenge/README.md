# [Reading File Challenge]()
<div align="justify">

First, I've created the usual **main** class and method.
Before I write any code, I'll create a new file,
at the root of this project, called `article.txt`.
For my article, I've copied the text from wikipedia, for the _Grand Canyon_.
I'll paste it in my file warts and all, as the saying goes, 
meaning I'll just leave it exactly as I got it.
Now, I'll use a buffered reader, because who knows, 
maybe I'll use this code against really large text files later on.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course07_ReadingFileChallenge/article.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            System.out.printf(" %d lines in file%n", br.lines().count());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```

In the _try-with-resources_, parentheses, 
I'll create a buffered reader variable, 
just _br_ to keep it short, 
and assign that a new instance of this class. 
I'll pass an instance of **FileReader**,
using the string literal, `article.text`. 
I'll add a _catch_ block, because I know I need it.
If there's an error, 
I'll just print it and ignore it after that.
I'll be using the method lines,
that returns a stream of **Strings**.
I'll wrap that in a `system.out.printf` statement, 
because I first want to print just the count of lines in the file.
My argument will have the call to the lines method, 
chained to the terminal operation, _count_.
Running that:

```html  
533 lines in file
```

I'll get that my file has 533 lines in it.
You'll likely get different numbers for this
if you are on a Mac or running on Linux.
One thing I do want to point out to you,
while we're looking at this, is what happens
if I try to execute this method again.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course07_ReadingFileChallenge/article.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            System.out.printf(" %d lines in file%n", br.lines().count());
            System.out.printf(" %d lines in file%n", br.lines().count());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```

I'll just copy and paste that line of code here.
If I run the code like this:

```html  
533 lines in file
0 lines in file
```

I get zero lines for the second statement.
After the terminal operation on the first pipeline, 
the file pointer is at the end of the file.
I'll be talking about ways to maneuver 
this file pointer in a later section,
through the means of mark and reset methods.
For now, it's important to understand 
you can't use back to back calls like this.
I'll revert that last paste,
and I'll comment out that first statement.
Next, I want to count the number of words.
There are a few ways to do this, but let me show you one
that uses this lines method again,
with a regular expression pattern.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course07_ReadingFileChallenge/article.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            //System.out.printf(" %d lines in file%n", br.lines().count());

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");
            System.out.printf("%d words in file%n",
                    br.lines()
                            .flatMap(pattern::splitAsStream)
                            .count());
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```

First, I'll set up a pattern variable,
and compile a **Pattern**, with the string 
I know the **scanner** class uses to split strings into words.
I'll again set up a _printf_ statement,
this time for the number of words.
I'll start with my source, lines from the buffered reader. 
I'll call **flatMap**, and use a method on my pattern, 
called _splitAsStream_. 
This method will split the string and return a stream of **String**.
I need flatMap here, to flatten the hierarchy,
before I can use the terminal operation, _count_.
If I run this code:

```html  
13799 words in file
```

I'll see that I have 13,799 words in this file.
Another option would have been to split the string.
I'll comment out this flat map, and show you another version.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course07_ReadingFileChallenge/article.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");
            System.out.printf("%d words in file%n",
                    br.lines()
                            //.flatMap(pattern::splitAsStream)
                            .flatMap(l -> Arrays.stream(l.split(pattern.toString())))
                            .count());
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```

I have to create a stream here 
because flat map only works on a resulting stream, 
so I'll call stream on **Arrays**, 
passing it the string array I get back by using my pattern on each line.
Running this:

```html  
13799 words in file
```

Instead of a flat map, I could use map and sum.
I'll copy this entire `System.out.printf` statement, 
and paste a copy just below.
I'll comment the previous statement out, 
since I can't call `br.lines` consecutively like this.
I think it's a good idea to keep exercising some of these stream skills,
so I'll just show you this next variation,
in case you find **flatMap** a little confusing.
I'll remove that commented out flat map here, 
and the two operations below it.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course07_ReadingFileChallenge/article.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");
            System.out.printf("%d words in file%n",
                    br.lines()                                                  // Stream<String>
                            .mapToLong(l -> l.split(pattern.toString()).length)
                            .sum());
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```

This time, I'll use _mapToLong_ as my intermediate operation.
My lambda expression will still split the string using the pattern, 
but this time it will return the length of that resulting array.
And instead of count, I want the terminal operation to be sum.
Running that:

```html  
13799 words in file
```

I get the same result, proving once again, there's no right way.
This way is probably more efficient,
because there's no overhead with the streams, 
and it's processing a smaller number of long values, versus strings.
Ok, I'm going to comment that out again,
Let me get back to the task at hand.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course07_ReadingFileChallenge/article.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");

            var result = br.lines()
                    .flatMap(pattern::splitAsStream)
                    .map(w -> w.replaceAll("\\p{Punct}", ""))
                    .filter(w -> w.length() > 4)
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
            
            result.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .limit(10)
                    .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue() + " times"));
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```

I'll set up a local variable for my result,
and start out with the source, br.lines.
I do want to do a flat map here,
because I want to evaluate every word.
One of the requirements, was to get rid of trailing commas and periods, 
so I'll call map, calling _replaceAll_ on the word, 
using a regular expression, that stands for any punctuation.
After this, I only want to evaluate words with more than 4 characters.
I'll make all the words lowercase, 
so I don't have duplicates, just based on case.
The terminal operation will group by the whole word, 
and count all instances of the same words.
Now, I need to print out the results,
and I've said I only want the top 10 words 
that have the highest frequency.
I'll stream again, by getting the entrySet of my result, 
and calling stream on that.
I'll sort by the entry value, which is the count of occurrence,
but I want this to be reversed, so I can pass a second comparator, 
here it's reverseOrder on **Comparator**. 
I'll limit the results to 10. 
And, I'll print this information, first the key (the word), 
and then, the number of occurrences.
Ok, I'll run this, and see what might be some of
the most important words, about the Grand Canyon.

```html  
canyon - 296 times
grand - 225 times
retrieved - 119 times
national - 102 times
river - 78 times
colorado - 73 times
archived - 56 times
original - 51 times
water - 46 times
service - 40 times
```

Not surprisingly, canyon and grand are at the top, 
but I've got national and river and colorado.
Some of these words look like they're more geared to wikipedia,
like retrieved, and original, and service, maybe.
I can create a list of words I want to exclude.
I'll do that next.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course07_ReadingFileChallenge/article.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");

            List<String> excluded = List.of(
                    "grand",
                    "canyon",
                    "retrieved",
                    "archived",
                    "service",
                    "original");
            
            var result = br.lines()
                    .flatMap(pattern::splitAsStream)
                    .map(w -> w.replaceAll("\\p{Punct}", ""))
                    .filter(w -> w.length() > 4)
                    .map(String::toLowerCase)
                    .filter(w -> !excluded.contains(w))
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
            
            result.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .limit(10)
                    .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue() + " times"));
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```

I'll just set up a list of excluded words here.
so grand, canyon, retrieved and archived.
and maybe service and original too.
I'll need to filter out the excluded words, and
I can do that by checking if the list contains
the word, and if it does, I'll return
false, so the word won't be included.
Ok, now if I run this:

```html  
national - 102 times
river - 78 times
colorado - 73 times
water - 46 times
arizona - 39 times
south - 39 times
species - 35 times
along - 29 times
north - 27 times
public - 25 times
```

I get some other descriptive words, so national,
likely because it's a national park in the U.S.
River and Colorado, because the canyon was carved out 
by the Colorado River.
Water is a huge deal for this canyon and river,
so it's not surprising that's there, 
and arizona is the state it's located in.
So that's a nice snapshot of the article in 10 words or less.
I'll try another example, this time, called `bigben.txt`.
I again want this at our package folder.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course07_ReadingFileChallenge/bigben.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");

            List<String> excluded = List.of(
                    "grand",
                    "canyon",
                    "retrieved",
                    "archived",
                    "service",
                    "original");
            
            var result = br.lines()
                    .flatMap(pattern::splitAsStream)
                    .map(w -> w.replaceAll("\\p{Punct}", ""))
                    .filter(w -> w.length() > 4)
                    .map(String::toLowerCase)
                    .filter(w -> !excluded.contains(w))
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
            
            result.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .limit(10)
                    .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue() + " times"));
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```

I'll change my code, so that I'm pointing at the `bigben.txt` file.
I'll run that:

```html  
clock - 95 times
tower - 78 times
april - 32 times
parliament - 28 times
london - 28 times
bells - 25 times
great - 23 times
november - 21 times
elizabeth - 21 times
january - 21 times
```

If you didn't know anything about Big Ben,
you might guess that it has something to do with
a clock tower, in london, and that it has bells.
I don't know what the different months are
all about, so maybe I could add those to the
excluded words, but anyway you can kind of
get the gist of this topic, in a few words.

Ok, so the bonus part of this was to do this with a stream,
and then without, or vice versa, 
so I'll do this without a stream next.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course07_ReadingFileChallenge/bigben.txt";
        try {
            input = Files.readString(Path.of(fileName));
            input = input.replaceAll("\\p{Punct}", "");

            Pattern pattern = Pattern.compile("\\w+");
            Matcher matcher = pattern.matcher(input);
            Map<String, Long> results = new HashMap<>();
            while (matcher.find()) {
                String word = matcher.group().toLowerCase();
                if (word.length() > 4) {
                results.merge(word, 1L, (o, n) -> o += n);
                }
            }

            var sortedEntries = new ArrayList<>(results.entrySet());
            sortedEntries.sort(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
            for (int i = 0; i < Math.min(10, sortedEntries.size()); i++) {
                var entry = sortedEntries.get(i);
                System.out.println(entry.getKey() + " - " + entry.getValue() + " times");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```

I'll use _readString_ on **Files**.
I'll then create a string variable, input, 
assigning it what I get back from `Files.readString`, 
and I'll pass that a **Path** for the big ben file.
I can replace all punctuation in the entire
String, with one call, so that's kind of nice.
I've got a compiler error on readString, and
it's the _IOException_, so I'll add a _try-catch_.
I'll just move that last input statement into the _try_ block.
Next, I'll set up my pattern.
This is going to be different, because I'm not splitting or tokenizing.
I'll use **Matcher** to find all the words in this big string.
My pattern will look for one or more word characters.
I'll get a **Matcher**, passing the _matcher_ method all the text from my file.
I'll set up a new map manually, keyed by string, containing a long value.
Next, I'm going to use matcher's find method to loop through each match, 
or each word it found.
I can use `matcher.group` to get the next word, and make that lower case.
I'll check the length, making sure it's greater than 4 characters.
I'll use _merge_, putting 1 as the first value, 
and incrementing by that, if it's not a new keyed entry.
That'll populate my map with distinct values, and counts, 
so next I'll sort this, and print it.
I'll first create an array list, of the entries.
I'll sort the entries, by the value, in reverse.
I'll loop from 0 to 9, or less if the text doesn't have 10 entries.
I'll get the entry from the list.
I'll print it as I did before.
Ok, that's it, and I can now give that a shot.

```html  
clock - 95 times
tower - 78 times
retrieved - 61 times
april - 32 times
parliament - 28 times
london - 28 times
bells - 25 times
original - 24 times
great - 23 times
november - 21 times
```

In this code, I didn't exclude any words,
but you can see the counts for clock and tower are the same, 
and so on, so this code is also working.
I could also have included that check for 5 characters, 
in my regular expression.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course07_ReadingFileChallenge/bigben.txt";
        try {
            input = Files.readString(Path.of(fileName));
            input = input.replaceAll("\\p{Punct}", "");
            
            //Pattern pattern = Pattern.compile("\\w+");
            Pattern pattern = Pattern.compile("\\w{5,}");
            Matcher matcher = pattern.matcher(input);
            Map<String, Long> results = new HashMap<>();
            while (matcher.find()) {
                String word = matcher.group().toLowerCase();
                if (word.length() > 4) {
                results.merge(word, 1L, (o, n) -> o += n);
                }
            }

            var sortedEntries = new ArrayList<>(results.entrySet());
            sortedEntries.sort(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
            for (int i = 0; i < Math.min(10, sortedEntries.size()); i++) {
                var entry = sortedEntries.get(i);
                System.out.println(entry.getKey() + " - " + entry.getValue() + " times");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```

I can do that with curly braces, then 5 comma, 
which says I want at least 5 word characters, but I'll take more.
I can comment out that if statement now, around the merge.
Running that:

```html  
clock - 95 times
tower - 78 times
retrieved - 61 times
april - 32 times
parliament - 28 times
london - 28 times
bells - 25 times
original - 24 times
great - 23 times
november - 21 times
```

I'll get the same results.
I could have also just used the method on matcher 
that returns a stream of matches, 
but the challenge was to use a streamless option.
</div>