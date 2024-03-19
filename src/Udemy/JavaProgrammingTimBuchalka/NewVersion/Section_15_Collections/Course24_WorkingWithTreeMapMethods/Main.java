package CourseCodes.NewSections.Section_15_Collections.Course24_WorkingWithTreeMapMethods;

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

//Part-1
/*
        In the last lecture, I created two maps of data, one, a LinkedHashMap of purchases made, and the other, a TreeMap
    of my enrolled students. Because I used LinkedHashMap, purchases are stored in the order they were made, which is
    pretty good in general, but I also created a sorted tree map, keying on purchase date, and maintaining a list of
    purchases by each distinct date. In this lecture, I'm going to use that last map, to derive some information about
    my sales. Like TreeSet, which had headSet and tailSet, the TreeMap has headMap and tailMap methods, which I'll show
    you next. First, I'll get the current year, using the now method on LocalDate, and chaining get year to that. I'll
    create a date with the current year, and first day. The local date class has methods that let you add date units or
    subtract them. The one to add days is called plusDays, and I can pass the number of days to add, so 7. I'll set up a
    Map, with the same type arguments as my datedPurchases map, and that's local date, and a List of purchases. I'll assign
    that the result, from calling the head map method on purchased dates, with the week1 date. I'll do the same thing for
    the next map, week 2 purchases, but call tail map this time, again using the date that is the cut off date for week 1.
    In addition to plusDays, the local date class has such methods as plusWeeks, plusYears, as well as minusDays, minusWeeks
    and minusYears, which make manipulating dates very easy. Again, there's a lot to know about date time processing, but
    for the very simple examples here, I'll just use a few of these somewhat self explanatory methods. In this code I'm
    using headMap, and like headSet, this excludes the value you pass, unless you use the overloaded method, and specify
    the inclusive flag to be true. On the other hand, tailMap, includes the value you pass, if it's in the map. I want
    to print these out and see what I get. I'll print a separator line, followed by the keys and values of my head view
    of the map, which I've called week 1 purchases. And I want to repeat that for the week 2 purchases. Running that code,

            -----------------------
            2023-01-02: [Purchase[courseId=pyt101, studentId=4, price=119.99, yr=2023, dayOfYear=2]]
            2023-01-05: [Purchase[courseId=jmc101, studentId=1, price=129.99, yr=2023, dayOfYear=5]]
            2023-01-06: [Purchase[courseId=pyt101, studentId=1, price=149.99, yr=2023, dayOfYear=6], Purchase[courseId=jmc101, studentId=3, price=149.99, yr=2023, dayOfYear=6], Purchase[courseId=pyt101, studentId=5, price=119.99, yr=2023, dayOfYear=6]]
            2023-01-07: [Purchase[courseId=jmc101, studentId=2, price=139.99, yr=2023, dayOfYear=7]]
            -----------------------
            2023-01-10: [Purchase[courseId=jmc101, studentId=6, price=139.99, yr=2023, dayOfYear=10], Purchase[courseId=pyt101, studentId=7, price=139.99, yr=2023, dayOfYear=10]]
            2023-01-11: [Purchase[courseId=pyt101, studentId=9, price=129.99, yr=2023, dayOfYear=11]]
            2023-01-12: [Purchase[courseId=jmc101, studentId=8, price=139.99, yr=2023, dayOfYear=12]]

    I get my purchases divvied up by the first two weeks of this year. I'll run this a couple of times, until I can confirm
    that January 7 is in the first week's map, and January 8 is in the second week. Finally, I want to know how many of
    each course, I sold for each week. I'll do this in another method on the Main class.
*/
//End-Part-1

        int currentYear = LocalDate.now().getYear();

        LocalDate firstDay = LocalDate.ofYearDay(currentYear, 1);
        LocalDate week1 = firstDay.plusDays(7);
        Map<LocalDate, List<Purchase>> week1Purchases = datedPurchases.headMap(week1);
        Map<LocalDate, List<Purchase>> week2Purchases = datedPurchases.tailMap(week1);

        //System.out.println("-----------------------");
        //week1Purchases.forEach((key, value) -> System.out.println(key + ": " + value));
        //System.out.println("-----------------------");
        //week2Purchases.forEach((key, value) -> System.out.println(key + ": " + value));

//Part-3
/*
        I'll comment out the code where I was printing week1 and week2 purchases. I'll replace that with calls to my new
    method for each of these maps. I'll call the display stats method, passing it first 1, and week 1 purchases. Then
    I'll call it again, with 2 and week 2 purchases. Running that, I'll see if that gives me the information I want.

                -----------------------
                2023-01-02: [Purchase[courseId=jmc101, studentId=8, price=139.99, yr=2023, dayOfYear=2]]
                2023-01-03: [Purchase[courseId=pyt101, studentId=9, price=129.99, yr=2023, dayOfYear=3]]
                2023-01-04: [Purchase[courseId=pyt101, studentId=1, price=149.99, yr=2023, dayOfYear=4]]
                2023-01-05: [Purchase[courseId=jmc101, studentId=1, price=129.99, yr=2023, dayOfYear=5]]
                Week 1 Purchases = {jmc101=2, pyt101=2}
                -----------------------
                2023-01-08: [Purchase[courseId=jmc101, studentId=3, price=149.99, yr=2023, dayOfYear=8]]
                2023-01-09: [Purchase[courseId=jmc101, studentId=2, price=139.99, yr=2023, dayOfYear=9], Purchase[courseId=jmc101, studentId=6, price=139.99, yr=2023, dayOfYear=9], Purchase[courseId=pyt101, studentId=7, price=139.99, yr=2023, dayOfYear=9]]
                2023-01-10: [Purchase[courseId=pyt101, studentId=5, price=119.99, yr=2023, dayOfYear=10]]
                2023-01-14: [Purchase[courseId=pyt101, studentId=4, price=119.99, yr=2023, dayOfYear=14]]
                Week 2 Purchases = {jmc101=3, pyt101=3}

    Again, this prints out all my purchases by date for the first week, but then I have the weekly totals, Week 1 Purchases,
    and these should match what I see in the printed data above. And I get similar information for the second week. The
    total of all purchases should always be 10, if I tally up all purchases for the two weeks. Let's look at a couple of
    other methods on the TreeMap. These are also very similar to TreeSet. You'll remember TreeSet and last, first, poll
    last and poll first. TreeMap has last key and first key methods, and lastEntry and firstEntry, instead of just first
    and last methods. Tree Map has poll last key and poll first key, and poll last entry and poll first entry methods,
    instead of just poll first and poll last. It has similar methods, but you have to specify key or Entry.
*/
//End-Part-3

        displayStats(1, week1Purchases);
        displayStats(2, week2Purchases);

//Part-4
/*
        I'll add a separator line. Now I'll set up a local variable, a local date, and assign it the result, of calling
    the last key method on my datedPurchases map. I'll use the var type, and assign that the lastEntry result. You can
    see from IntelliJ's hints, that previousEntry is an Entry type. To get the list of purchases, I need to call the get
    value method on previous entry, and assign that to a List of purchases, in a local variable I'll call last days data.
    Here, I'll print that last date, and the size of the list. Let me run that.

                2023-01-13 purchases : 2

    It will show me the last day I made any purchases, and how many. Now, let's look at the lower key, and lower entry
    methods. To do this, I'm going to wrap the last two statements, in a while loop. I'll add the while statement, and
    the condition previousEntry is not null. This means I want to keep looping until previous entry is null. I have to
    add the closing brace for my while loop, after those two statements. If I left the code this way, I'd be in an infinite
    loop, because previousEntry never gets changed, it will always be non null. I'm going to reassign previous entry,
    as well as last date, but first I'll set up a local variable, a date called prevDate. I'll assign that the result,
    of calling lowerKey, on the dated purchases map, using the lastDate. previous entry will then get the result, of
    calling lower entry, on my dated purchases map, again using last date, which you'll remember was the last key. After
    that, I reassign lastDate to the value of previous date. This loop will continue processing, until the lower entry
    method returns a null, which should happen, after processing the first element in the map. Let me run that.

                2023-01-14 purchases : 1
                2023-01-10 purchases : 1
                2023-01-09 purchases : 3
                2023-01-08 purchases : 1
                2023-01-05 purchases : 1
                2023-01-04 purchases : 1
                2023-01-03 purchases : 1
                2023-01-02 purchases : 1

    This gives me the number of purchases for each day, in reverse order. What would happen if I changed these two lower
    method calls, to the corresponding floor methods? Let me do that, just to show you what happens. I'll change lowerKey
    to floor key, and lower entry to floor entry.

                                     LocalDate prevDate = datedPurchases.lowerKey(lastDate);
                                     previousEntry = datedPurchases.lowerEntry(lastDate);
                                                                to
                                     LocalDate prevDate = datedPurchases.floorKey(lastDate);
                                     previousEntry = datedPurchases.floorEntry(lastDate);

    If I run this code, I'll be stuck in an infinite loop. Why's this? Remember, the lower method on Set always returned
    the element that was less than the method argument, but the floor method gets the value, that's less than, or equal
    to, the method argument. This is true for these methods as well. In this case, the floorEntry method just keeps
    returning the same element each time, as does the floor key, because it finds an element whose key is equal to that
    key. I'll revert those last changes, so that my code runs again. While I'm at it, I might as well give Map's last
    and higher methods a try. I'll also use this to show you another map view collection, the descendingMap. First, I'll
    use the descending Map method, to get my purchases, in the reverse order, reversed by date which is my key.
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

//Part-2
/*
        I'll make that private static and void. I'll call it displayStats, and it will take an int for the period. If I
    pass it weekly data, this will be an int from 1 to 52, but if I pass it monthly data, it would be 1 through 12, and
    for quarterly data, it could be 1 through 4, and so on. I'll also have a Map for the second parameter, again with
    LocalDate as the key, and a List of Purchase records as the value, and I'll call that periodData. I'll print out some
    dashes, to separate the data. I'll create a local variable, a map, that's going to keep track of course I.D.'s, and
    the number of purchases for each, so the types are String for the course id key, and Integer for the course counts.
    I'm calling this variable weeklyCounts, and since I want it sorted, I'll make it a new tree map. Next, I'll loop
    through each entry of my period data. I'll first print the key and value. Next, I'll add the code, that actually
    keeps track of my counts by course id for this period. I'll loop through the elements in the value variable, remember
    this is a list of purchases, so I'll loop through purchases for each date. I'm going to use the merge method on map
    here. My map is keyed by the course id, which I can get from the purchase record. The next argument for the merge
    method, is what the value will get set to, if it doesn't yet exist in the map. I'll set that to 1. The last argument
    is going to be a lambda expression. The parameters for this BiFunction Interface Lambda, are what's currently in the
    map for the key, and the current value, in this case, that's 1 here. I'll return these two values added together,
    which should keep a running tally of purchases by course. I'll next print my weekly counts out, after the loop. I'll
    use system.out.printf, with a formatted string, passing it the period, and the weeklyCounts map. IntelliJ is suggesting
    I change this code, where I return previous plus current to a method reference, but I think right now, it's a little
    easier to understand if I leave it this way. Getting back to the main method,
*/
//End-Part-2

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
