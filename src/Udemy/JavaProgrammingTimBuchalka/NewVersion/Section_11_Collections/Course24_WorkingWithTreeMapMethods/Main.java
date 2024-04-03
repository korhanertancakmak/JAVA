package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course24_WorkingWithTreeMapMethods;

import java.time.LocalDate;
import java.util.*;

public class Main {

    private static Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, Student> students = new TreeMap<>();

    public static void main(String[] args) {

        Course jmc = new Course("jmc101", "Java Master Class",
                "Java");
        Course python = new Course("pyt101", "Python Master Class",
                "Python");

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

        NavigableMap<LocalDate,List<Purchase>> datedPurchases = new TreeMap<>();

        for (Purchase p : purchases.values() ) {
            datedPurchases.compute(p.purchaseDate(),
                    (pdate, plist) -> {
                        List<Purchase> list =
                                (plist == null) ? new ArrayList<>() : plist;
                        list.add(p);
                        return list;
                    });
        }

        datedPurchases.forEach((key, value) -> System.out.println(key + ": " + value));

        int currentYear = LocalDate.now().getYear();

        LocalDate firstDay = LocalDate.ofYearDay(currentYear, 1);
        LocalDate week1 = firstDay.plusDays(7);
        Map<LocalDate, List<Purchase>> week1Purchases = datedPurchases.headMap(week1);
        Map<LocalDate, List<Purchase>> week2Purchases = datedPurchases.tailMap(week1);

        //System.out.println("-----------------------");
        //week1Purchases.forEach((key, value) -> System.out.println(key + ": " + value));
        //System.out.println("-----------------------");
        //week2Purchases.forEach((key, value) -> System.out.println(key + ": " + value));

        displayStats(1, week1Purchases);
        displayStats(2, week2Purchases);

//Part-4
/*

*/
//End-Part-4

        System.out.println("-----------------------");

        LocalDate lastDate = datedPurchases.lastKey();
        var previousEntry = datedPurchases.lastEntry();

        while (previousEntry != null) {
            List<Purchase> lastDaysData = previousEntry.getValue();
            System.out.println(lastDate + " purchases : " + lastDaysData.size());

            LocalDate prevDate = datedPurchases.lowerKey(lastDate);
            previousEntry = datedPurchases.lowerEntry(lastDate);
            lastDate = prevDate;
        }

//Part-5
/*
        I'll put a separator line here. I'll use var as the type for the reversed local variable, and assign that dated
    purchases dot descending map. This code is going to look very similar to the code I just showed you, where I looped
    through my entries, using lower key, and lower entry, to process the data. I'll set up two local variables, as I did
    before. The first one, a Local date, first date, gets assigned what I get back, from reversed dot first key. I want
    to use the reversed map I just created, to get the first key, and not the datedPurchases map. Because this map is in
    reverse order, I can produce the same output, but use different methods. The next variable, is nextEntry, and that's
    what I get back, from calling the first Entry method on the reversed map. I'll set up a while loop, checking to see
    if next Entry is not null for this loop. I'll set up a local variable for my list of daily purchases. I'll print the
    date and number of purchases like before. I'll set up another local variable in my loop, and this time I'm going to
    call it nextDate, and instead of calling lowerKey, I'll use the higher key method here. Because my map's in reversed
    order, higher is going to return the next most current date and purchases. nextEntry gets assigned what I get back,
    from the higher entry method, using whatever's in firstDate. I'll reassign first date to the next date. If I run that
    code, I get the same results, as the previous while loop, this time by using a reversed map, and the first and higher
    methods. For good measure, I'll also print out my original map after this loop.
*/
//End-Part-5

        System.out.println("-----------------------");
        var reversed = datedPurchases.descendingMap();

        LocalDate firstDate = reversed.firstKey();
//        var nextEntry = reversed.firstEntry();
        var nextEntry = reversed.pollFirstEntry();

        while (nextEntry != null) {
            List<Purchase> lastDaysData = nextEntry.getValue();
            System.out.println(firstDate + " purchases : " + lastDaysData.size());

            LocalDate nextDate = reversed.higherKey(firstDate);
//            nextEntry = reversed.higherEntry(firstDate);
            nextEntry = reversed.pollFirstEntry();
            firstDate = nextDate;
        }

//Part-6
/*
        I'll add a separator line, and use the for each method on my datedPurchases map, to print that data again. Running
    that, I can confirm that my datedPurchases map hasn't changed at all. Now, I'll make two minor changes to this last bit of
    code. First I'll copy the statement, pasting a copy just below it, and I'll comment out.

                                    //var nextEntry = reversed.firstEntry();
                                    var nextEntry = reversed.pollFirstEntry();

    I'll do the same thing for the next statement, pasting a copy below, and commenting the first statement out.

                                    //nextEntry = reversed.higherEntry(firstDate);
                                    nextEntry = reversed.pollFirstEntry();

    In both these cases, where I'm assigning next entry, I want to use a different method, replacing both with the same
    expression. In the first case, I'm changing first entry, to poll first entry method. In the second, I'm replacing
    the call to higher entry, also replacing it with pollFirstEntry. Remember that pollFirstEntry doesn't take any arguments.
    How does this work? Well, let me run it, and see what I get.

                    -----------------------
                    2023-01-13 purchases : 2
                    2023-01-12 purchases : 1
                    2023-01-10 purchases : 1
                    2023-01-08 purchases : 1
                    2023-01-06 purchases : 1
                    2023-01-05 purchases : 2
                    2023-01-03 purchases : 1
                    2023-01-02 purchases : 1
                    -----------------------

    You can see, my output looks the same for the loop results. Using this method gave me the same result. But there's a
    really important difference. Can you see it? Don't forget, I've got code after this loop, to print the information
    in my dated purchases map. So why didn't that data get printed out? There are two important things you need to remember,
    which is why I'm showing you this particular example. First, you need to understand, that the poll methods, pollFirstEntry
    and pollLastEntry, remove data from the map, on each subsequent call. These aren't just an alternate way get the first
    and last entry, because they're also mutating the map. And secondly, the reversed map is a view. The true source is
    my dated purchases map. When I execute the poll methods on the reversed map, those operations are actually occurring,
    on the dated purchases map. If you're not really careful, when you use these views, you could really mess things up,
    for code using the dated purchases map later on. Views are very helpful, but they can also get you in trouble, if you
    don't remember that's what they are. I'll put up a table, and list the Map's view collections. They're very similar
    to TreeSet's, but there are separate views for the keys and the entries.

            View collection methods                         Notes
       __________________________________________________________________________________________________________________
        entrySet(),keySet(),values()               Provides views of mappings, keys and values. These are views available
                                                   to any map, and not just the TreeMap. I include them here to remind you,
                                                   These are views.
       __________________________________________________________________________________________________________________
        descendingKeySet()                         Provides reversed order key set or map, reversed by the key values.
        descendingKeyMap()
       __________________________________________________________________________________________________________________
        headMap(K key)                             Provides views of either the first or last parts of the map, divided
        headMap(K key, boolean inclusive)          by the key passed.
        tailMap(K key)                             The head map is by default EXCLUSIVE of all elements higher or equal
        tailMap(K key, boolean inclusive)          to the key.
                                                   The tail map is by default INCLUSIVE of all elements higher or equal
                                                   to the key.
       __________________________________________________________________________________________________________________
        subMap(K fromKey, K toKey)                 Provides a view of contiguous section of the map, higher or equal to
        subMap(K, fromKey, boolean inclusive,      the fromKey and lower than the toKey, so the toKey is EXCLUSIVE.
            K toKey, boolean inclusive)            The overloaded version allows you to determine the inclusivity you
                                                   want for both keys.

    The entrySet, keySet, and values methods return views of the mappings, keys and values respectively. These are views
    available to any map, and not just the TreeMap. I include them here, to remind you, these are still views. Manipulating
    the collections returned from these methods, will be manipulating the source map. The descendingKeySet and descendingKeyMap
    methods, provide reversed order views, reversed by the key values. The headMap and tailMap methods provide views of
    either the first or last parts of the map, spliced or divided by, the key argument that's passed. The head map, is
    by default, EXCLUSIVE of all elements higher or equal to the key. The tail map is, by default, INCLUSIVE of all
    elements higher or equal to the key. The subMap method provides a view of a contiguous sub section of the map, higher
    or equal to the from key, and lower than the two Key, so the two Key is EXCLUSIVE. The overloaded version allows you
    to determine the inclusivity you want for both keys. In the next lecture, I want to introduce you to two more collection
    classes, the Enum Set and the Enum Map.
*/
//End-Part-6

        System.out.println("-----------------------");
        datedPurchases.forEach((key, value) -> System.out.println(key + ": " + value));

    }

    private static void addPurchase(String name, Course course, double price) {

        Student existingStudent = students.get(name);
        if (existingStudent == null) {
            existingStudent = new Student(name, course);
            students.put(name, existingStudent);
        } else {
            existingStudent.addCourse(course);
        }

        int day = new Random().nextInt(1, 15);
        String key = course.courseId() + "_" + existingStudent.getId();
        int year = LocalDate.now().getYear();
        Purchase purchase = new Purchase(course.courseId(),
                existingStudent.getId(), price, year, day);
        purchases.put(key, purchase);
    }

    private static void displayStats(int period, Map<LocalDate, List<Purchase>> periodData) {

        System.out.println("-----------------------");
        Map<String, Integer> weeklyCounts = new TreeMap<>();
        periodData.forEach((key, value) -> {
            System.out.println(key + ": " + value);
            for (Purchase p : value) {
                weeklyCounts.merge(p.courseId(), 1, (prev, current) -> {
                    return prev + current;
                });
            }
        });
        System.out.printf("Week %d Purchases = %s%n", period, weeklyCounts);
    }
}
