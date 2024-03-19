package CourseCodes.NewSections.Section_15_Collections.Course25_TargetedCollectionsForEnumTypes;

import java.util.*;

//Part-1
/*                                          EnumSet and EnumMap

        Before we move on, I want to talk about two more classes in the collections framework, specifically created to
    support enum types more efficiently. You can use any List, Set, or Map, with an enum constant. The EnumSet, and
    EnumMap, each has a special implementation that differs from the HashSet or HashMap. These implementations make these
    two types extremely compact and efficient. There's no special list implementation for enum types.

        The EnumSet is a specialized Set implementation for use with enum values. All of the elements in an EnumSet must
    come from a single enum type. The EnumSet is abstract, meaning we can't instantiate it directly. It comes with many
    factory methods to create instances. In general, this set has much better performance than using a HashSet, with an
    enum type. Bulk operations (such as containsAll and retainAll) should run very quickly, in constant time, O(1), if
    they're run on an enumSet, and their argument is an EnumSet.

        The EnumMap is a specialized Map implementation for use with enum type keys. The keys must all come from the same
    enum type, and they're ordered naturally by the ordinal value of the enum constants. This map has the same functionality
    as a HashMap, with O(1) for basic operations. The enum key type is specified during construction of the EnumMap,
    either explicitly by passing the key type's class, or implicitly by pass another EnumSet. In general, this map has
    better performance than using a HashMap, with an enum type. Let's review some of the things you can do when you combine
    enums with these collections. I've created a Main class and main method.
*/
//End-Part-1

public class Main {

//Part-2
/*
        I'm going to create an enum type in this class, called WeekDay. This enum will just have all the days in the week.
*/
//End-Part-2

    enum WeekDay {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}

    public static void main(String[] args) {

//Part-3
/*
        Let's say I'm interested in assigning work days to different employees. I already have an employee named Ann, so
    I'll set up a list of the days she works. The list type will be the enum type, weekDay, I'll call the variable ann's
    work days, and that will be a new ArrayList, created using the list dot of method, with Monday, Tuesday, Thursday and
    Friday. I'm going to use Ann's work days, to figure out what kind of coverage I'll need for other employees I want
    to hire. First, let me use one of a factory method on the EnumSet, to create a set of Ann's work days. I'll set my type to var
    right now. I'll call this variable ann's Days Set. I'll assign that the result, of the enum dot copyOf method, with
    ann's work days. This method will take any collection type. Then, I want to printout the actual type of the instance
    I get back. Before I run it, notice that the inferred type is an EnumSet, with the type of WeekDay. The EnumSet is
    an abstract class, as I said before, so if I want to know what the set type really is, I'll need to run this
    code. Running this,

                    RegularEnumSet

    you can see my instance is a RegularEnumSet.

                                        2 Types of EnumSet Implementations

        Enum sets are represented internally as bit vectors, which is just a series of ones and zeros. A one indicates
    that the enum constant (with an ordinal value that is equal to the index of the bit) is in the set. A zero indicates
    the enum constant is not in the set. Using a bit vector allows all set operations to use bit math, which makes it
    very fast. A RegularEnumSet uses a single long as its bit vector, which means it can contain a maximum of 64 bits,
    representing 64 enum values. A JumboEnumSet gets returned if you have more than 64 enums.

                Ann's EnumSet
                    |   0   |   1   |   1   |   0   |   1   |   1   |   0   |   0   |
                       [0]     [1]     [2]     [3]     [4]     [5]     [6]     [7]          Vector indices
                                ↓        ↓                                        ↓
                   | SUNDAY | MONDAY | TUESDAY | WEDNESDAY | THURSDAY | FRIDAY | SATURDAY |

    This table is a visual representation of the EnumSet for Ann's work days. It's size is 7, for the 7 possible values
    (based on the number of constants in the WeekDay Enum). Any weekday that's part of her set, will be set to 1, at the
    index that corresponds to, the weekday ordinal value. MONDAY has an ordinal value of 1 in our WeekDays enum, and the
    value in the underlying bit vector, at position 1, is a 1. This means MONDAY is part of Ann's EnumSet. Ok, so let's
    get back to the code and explore this class a little more.
*/
//End-Part-3

        List<WeekDay> annsWorkDays = new ArrayList<>(List.of(WeekDay.MONDAY,
                WeekDay.TUESDAY, WeekDay.THURSDAY, WeekDay.FRIDAY));

        var annsDaysSet = EnumSet.copyOf(annsWorkDays);
        System.out.println(annsDaysSet.getClass().getSimpleName());
        annsDaysSet.forEach(System.out::println);

//Part-4
/*
        I'll print AnnsDaysSet out, each day on its own line. Running this,

                    RegularEnumSet
                    MONDAY
                    TUESDAY
                    THURSDAY
                    FRIDAY

    Ann's workdays are printed out, in the order as defined in the Enum. This is another advantage of using just a HashSet
    with an enum type, because the set is naturally ordered by the ordinal value. We can create a Set for all the enum
    values, using another factory method, allOf. I'll set up a local variable, again using var, named all days set, and
    assign that enum set.allOf, and pass that the WeekDay dot class. I'll print a separator line for clarity. I'll print
    each element. Running that,

                    ---------------------
                    SUNDAY
                    MONDAY
                    TUESDAY
                    WEDNESDAY
                    THURSDAY
                    FRIDAY
                    SATURDAY

    it just prints out all the days in our enum, in order. You might be wondering why that's useful, since that's data
    we can just get, using enum's values methods. Hold that thought for a minute.
*/
//End-Part-4

        var allDaysSet = EnumSet.allOf(WeekDay.class);
        System.out.println("---------------------");
        allDaysSet.forEach(System.out::println);

//Part-5
/*
        First I want to show you another method, called complementOf. I'll set up a Set of WeekDay this time, calling it
    new person days. I'll assign that the result of enum set's static method, complement of, and passing it annsDaysSet.
    Again, I'll output a separator line. And print what that gives me. Running this,

                ---------------------
                SUNDAY
                WEDNESDAY
                SATURDAY

    I get Sunday, Wednesday, and Saturday, so what did this really do? What set math operation is this? This is a difference,
    of the full set of all possible work days, minus ann's set, isn't it? I can get the same results with the removeAll
    bulk function, which I'll do next.
*/
//End-Part-5

        Set<WeekDay> newPersonDays = EnumSet.complementOf(annsDaysSet);
        System.out.println("---------------------");
        newPersonDays.forEach(System.out::println);

//Part-6
/*
        I'll make a copy of the full set, and call it anotherWay. I'll call the remove all method, on that, passing it
    anns Days Set. And I'll print this set out. Running that,

                ---------------------
                SUNDAY
                WEDNESDAY
                SATURDAY

    you can see I get the same result. It was a lot easier to use the complement of method. I didn't have to create a set
    of all elements, or call the remove all method. One other creation method is the range method.
*/
//End-Part-6

        Set<WeekDay> anotherWay = EnumSet.copyOf(allDaysSet);
        anotherWay.removeAll(annsDaysSet);
        System.out.println("---------------------");
        anotherWay.forEach(System.out::println);

//Part-7
/*
        I'll set up a new variable called business days. I'll set that to the result of enum set dot range, passing it
    Monday and Friday. I'll print those out.

                ---------------------
                MONDAY
                TUESDAY
                WEDNESDAY
                THURSDAY
                FRIDAY

    You can get all the days between Monday and Friday, including both Monday and Friday. All other operations on this
    set are the same as those I covered with the HashSet, so I won't go through them here.


    Let's now look at the EnumMap.
    This class is not abstract, so I can create an instance of it directly using new, but unlike other Map implementations, it doesn't have a no args
    constructor. I'll create a map, keyed by Week day, and each mapped element will be an array of string. That array
    will be the names of my employees who work those days. I'll assign that a new EnumMap instance, and pass it the
    WeekDay.class. Now, I have an empty enum map. I can add elements in the usual way, with put, put all, put if absent,
    compute if present, replace all, and the other many methods of a Map. I'll just use the put method. I'll add Friday's
    crew, Ann, Mary and Bob. I'll next add Monday's crew, Mary and Bob.  I'll print this data out. Notice here, that
    these are again ordered by the week day, without me having to use a SortedMap. I also didn't have to insert them in
    order, like I would have if I had used a LinkedHashMap. Again, I won't cover all the methods I've covered previously
    with the HashMap. This class has all those methods, and works very similarly to a HashMap with a key type that's an
    enum. But it's more efficient, and it's naturally ordered. This lecture ends the material for the Collections
    Framework section of the course. We covered a lot of ground. Before I start a new section, I do want to give you one
    last opportunity, a challenge, that will give you a little extra time with some of these concepts.
*/
//End-Part-7

        Set<WeekDay> businessDays = EnumSet.range(WeekDay.MONDAY, WeekDay.FRIDAY);
        System.out.println("---------------------");
        businessDays.forEach(System.out::println);

//Part-8
/*
        Let's now look at the EnumMap. This class is not abstract, so I can create an instance of it directly using new,
    but unlike other Map implementations, it doesn't have a no args constructor. I'll create a map, keyed by Week day,
    and each mapped element will be an array of string. That array will be the names of my employees who work those days.
    I'll assign that a new EnumMap instance, and pass it the WeekDay.class. Now, I have an empty enum map. I can add elements
    in the usual way, with put, put all, put if absent, compute if present, replace all, and the other many methods of
    a Map. I'll just use the put method. I'll add Friday's crew, Ann, Mary and Bob. I'll next add Monday's crew, Mary and
    Bob.  I'll print this data out.

                    ---------------------
                    MONDAY
                    TUESDAY
                    WEDNESDAY
                    THURSDAY
                    FRIDAY
                    MONDAY : [Mary, Bob]
                    FRIDAY : [Ann, Mary, Bob]

    Notice here, that these are again ordered by the week day, without me having to use a SortedMap. I also didn't have
    to insert them in order, like I would have if I had used a LinkedHashMap. Again, I won't cover all the methods I've
    covered previously with the HashMap. This class has all those methods, and works very similarly to a HashMap with a
    key type that's an enum. But it's more efficient, and it's naturally ordered. This lecture ends the material for the
    Collections Framework section of the course. We covered a lot of ground. Before I start a new section, I do want to
    give you one last opportunity, a challenge, that will give you a little extra time with some of these concepts.
*/
//End-Part-8

        Map<WeekDay, String[]> employeeMap = new EnumMap<>(WeekDay.class);

        employeeMap.put(WeekDay.FRIDAY, new String[]{"Ann", "Mary", "Bob"});
        employeeMap.put(WeekDay.MONDAY, new String[]{"Mary", "Bob"});
        employeeMap.forEach((k, v) -> System.out.println(k + " : " + Arrays.toString(v)));

    }
}
