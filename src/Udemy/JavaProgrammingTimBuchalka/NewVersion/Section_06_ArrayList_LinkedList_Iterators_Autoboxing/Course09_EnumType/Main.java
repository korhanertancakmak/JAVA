package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_06_ArrayList_LinkedList_Iterators_Autoboxing.Course09_EnumType;

/*
Part-1
                                                 Enumeration

        In this course, I want to look at another list type in Java, called enum. The enum type is Java's type to support
    something called an enumeration. Wikipedia defines enumeration as "A complete ordered listing of "all the items" in a
    collection." The distinction here is on the phrase: "all the items".

        Java describes the enum type as: A special data type that contains predefined constants. A constant is a variable
    whose value can't be changed, once it's value has been assigned. So an enum is a little like an array, except it's
    elements are known, not changeable, and each element can be referred to by a constant name, instead of an index position.

                        public enum DayOfTheWeek {
                            SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
                        }

        An enum, in its simplest form, is described like a class, but the keyword enum, replaces the keyword class. You
    can name the enum with any valid identifier, but like a class, Upper CamelCase is the preferred style. Within the enum
    body, you declare a list of constant identifiers, separated by commas. By convention, these are all uppercase labels.
    One example of an enum, is the days of the week, as shown here. An enum is ordered, by the way you declare the constants.
    This means that SUNDAY is considered the first day of the week, and SATURDAY is the last day of the week.

                                                The enum Type

        The enum type is used to declare a limited set of constants, and sometimes, there is a natural order to the listing,
    as in the case of days of the week. Some other examples of possible enum declarations might be:

       - The months in the year: JANUARY, FEBRUARY, MARCH, etc.
       - The directions in a compass: EAST, NORTH, WEST, SOUTH.
       - A set of sizes: EXTRA_SMALL, SMALL, MEDIUM, LARGE, EXTRA_LARGE.

    Underneath the covers, the enum type is a special type of class, which contains fields to support the constants, but
    we'll get into that, in a later discussion. You don't have to understand all the internals of an enum, to derive the
    benefits of using this type. Once you get used to how this type works, you may find many places to use an enum. They
    simplify your code, and make it more readable in many ways.

        Now, to start, creating an enum is like creating a new class, in a new source file.
End-Part-1
*/

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        DayOfTheWeek weekDay = DayOfTheWeek.TUES;
        System.out.println(weekDay);

/*
Part-4
        Here, I've created a variable called weekDay, and assigned it the enum value, that's the abbreviation for Tuesday.
    And I've passed that variable, directly to the println statement. Now, let's see what happens when I run this code.

                    TUES

    And you can see the output. It printed out the label for Tuesday. And we can use 2 methods, on this simple enum type,
    the first to print out the label, and the second method to give us the ordinal value (or order), of this constant, in
    the enum collection itself. I'll do that now, with this weekDay.
End-Part-4
*/

        System.out.printf("Name is %s, Ordinal Value = %d%n",
                weekDay.name(), weekDay.ordinal());

/*
Part-5
        And running that,

                    Name is TUES, Ordinal Value = 2

    we get the additional line of output, that Name, from the name method is TUES, and the ordinal value is 2. Now, like
    arrays and lists, enums start with an ordinal value of 0. The constant with the identifier SUN, would return a 0. This
    means, in Java, it's the first element in this list. We'll look at that shortly, but this statement means that Tuesday
    is not really the 2nd day of the week, it's the 3rd, because Java starts counting at 0. Now, I want to create a method,
    that returns a random day of the week.
End-Part-5
*/

        for (int i = 0; i < 10; i++) {
            weekDay = getRandomDay();

            System.out.printf("Name is %s, Ordinal Value = %d%n",
                    weekDay.name(), weekDay.ordinal());
        }

/*
Part-7
        In this code, we're going to loop 10 times, and each time we call the getRandomDay method. We don't know what day
    we'll get back, since it's random. And then we print the information about the day, it's name and ordinal value. And
    running that,

                    Name is WED, Ordinal Value = 3
                    Name is TUES, Ordinal Value = 2
                    Name is SAT, Ordinal Value = 6
                    Name is TUES, Ordinal Value = 2
                    Name is THURS, Ordinal Value = 4
                    Name is THURS, Ordinal Value = 4
                    Name is MON, Ordinal Value = 1
                    Name is TUES, Ordinal Value = 2
                    Name is MON, Ordinal Value = 1
                    Name is SUN, Ordinal Value = 0

    your output is going to be different of course, but you can see the week day is changing, and its ordinal value is shown.
    Another thing you can do, is to test if your variable is equal to one of the constants, using the "==" operator, I'll
    add that code next, in the for loop.
End-Part-7
*/

        for (int i = 0; i < 10; i++) {
            weekDay = getRandomDay();

            System.out.printf("Name is %s, Ordinal Value = %d%n",
                    weekDay.name(), weekDay.ordinal());

            if (weekDay == DayOfTheWeek.FRI) {
                System.out.println("Found a Friday!!!");
            }
        }


/*
Part-8
        In this code, we test if the randomly generated week day, is a Friday, matching it to the enum constant, FRI, and
    if it is, we print "found a Friday". This demonstrates the readability benefits, of using an enum. It's very easy to
    see, that this code is testing if the day is a Friday. If you run this a few times so you can see the randomly generated
    results.
End-Part-8
*/
    }

    public static DayOfTheWeek getRandomDay() {

        int randomInteger = new Random().nextInt(7);
        var allDays = DayOfTheWeek.values();

        return allDays[randomInteger];
    }

/*
Part-6
        The first thing to notice here is this creation of a new Random instance. And we've used this class in previous
    courses, but in this case we're not assigning the instance to a local variable. We're passing a 7 as the bounding integer
    to nextInt. This means we don't want anything greater than or equal to seven, the random number should be between 0
    and 6. And that's because the values of our weekdays are 0 through 6, with Sunday being 0, and 6 being Saturday. And
    we'll call the nextInt method from that new instance of Random, which will give us that random number. So enum types
    have a method, named values, which returns an array of all the enum constant values. And lastly, we return the result
    of that method, to a variable we'll call allDays, and we'll use the var type here. Now, what I want you to notice,
    is the IntelliJ hint, telling us that we got an array of DayOfTheWeek back from that method. So now, because we have
    an array, we know we can use the random number as the index, and return the value at the randomly selected position.
    There, now we have a method, to return a random day of the week. Let's test this out, say for 10 times,
End-Part-6
*/
}
