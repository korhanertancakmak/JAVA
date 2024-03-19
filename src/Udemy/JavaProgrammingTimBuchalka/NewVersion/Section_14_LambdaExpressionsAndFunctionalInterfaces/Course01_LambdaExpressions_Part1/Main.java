package CourseCodes.NewSections.Section_14_LambdaExpressionsAndFunctionalInterfaces.Course01_LambdaExpressions_Part1;

//Part-1
/*
                                                Lambda Expressions

        I wanted to introduce you to this section a little earlier in the course than here, because many methods supporting
    these expressions, have been introduced on Java's interfaces and classes, since JDK 8. Lambda expressions let you pass
    around snippets of custom code, giving you so much more functionality than you might otherwise be able to achieve,
    and with very little effort. It's sometimes confusing to understand these expressions, or how much power is in their
    punch, and sometimes with just a single statement. I hope that by the end of this section, you'll be kind of excited
    about what you can do with them, and be ready to use them a lot. A lambda expression can be thought of as implicit
    code for an anonymous class, using a special kind of interface, as the mechanics to do this. The method reference goes
    even further, and is a short cut for the lambda expression syntax, for existing methods. To conclude this section,
    I'll introduce you to convenient methods available on many interfaces. Don't miss out on this part of the section,
    as it will simplify some of the repetitive work you may encounter. Ok, so let's get started.

        In the lecture on anonymous classes, I replaced an anonymous class with a lambda expression in a method argument,
    using the IntelliJ code hint, and generation tools. I want to do that again here, at the start of this lecture, so
    let me that code up real quick. I'm going to create a record, and just for fun, I'll make that inner record on the
    Main class, since you already know how to do this, if you've been following along with the course in order. If you
    need a refresher on inner classes, look at the previous section.
*/
//End-Part-1


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

//Part-2
/*
        I'll call this person, and for the fields, I'll have 2 strings, first name and last name. I think I've said this
    before, but it bears repeating, that records are implicitly static, when used as an inner class, in the same way enums
    and interfaces are. What this means is, I can access the record directly using Main's class name, if I wanted to create
    instances of it, from outside this class. Next, I want to include a simpler String representation, so generate an
    overridden toString method. I'll change "return null;", to return the first and last name with a space between them.
    Now, I want to create a list of these person records in the main method.
*/
//End-Part-2

    record Person (String firstName, String lastName) {

        @Override
        public String toString() {
            //return null;
            return firstName + " " + lastName;
        }
    }

    public static void main(String[] args) {

//Part-3
/*
        I'll set up a list variable, named people, assigning that a new ArrayList. I'm going to pass it a list of person
    records, so I'll use the helper method on the Arrays class, as List to do it. And I'll create new records for some
    of the peanuts characters. I purposely used "Main.Person()" for the first person creation, just to demonstrate that
    I could use the class qualifier to create a new person, because person is really a static nested class of main. This
    isn't required inside this class, so for the rest of the records, I simply use the unqualified Person class. Now, I
    want to create a custom comparator.
*/
//End-Part-3

        List<Person> people = new ArrayList<>(Arrays.asList(
                new Main.Person("Lucy", "Van Pelt"),
                new Person("Sally", "Brown"),
                new Person("Linus", "Van Pelt"),
                new Person("Peppermint", "Patty"),
                new Person("Charlie", "Brown")));

//Part-4
/*
        I'll do this first with an anonymous class. I'm using var as the type for simplicity, and assigning it to a new
    Comparator with a type of Person. I'll use IntelliJ's tools to add the overridden method. And I'll replace "return 0;"
    with the code to compare people by their last names. CompareTo will work nicely.
*/
//End-Part-4

        // Using anonymous class
        var comparatorLastName = new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                //return 0;
                return o1.lastName().compareTo(o2.lastName());
            }
        };

//Part-5
/*
        Next, I'll execute sort on my people list, passing it this anonymous instance of the Comparator interface, by passing
    the comparatorLastName variable to the sort method. Then I'll print my list out. Running that:

            [Sally Brown, Charlie Brown, Peppermint Patty, Lucy Van Pelt, Linus Van Pelt]

    you can see my list of peanuts characters printed out there, sorted by their last names, where Sally Brown in listed
    first. Because I only used last name to sort, any person records that have the same last name will still be ordered
    by insertion order, so that's why Sally comes before Charlie Brown in this case.
*/
//End-Part-5

        //people.sort(comparatorLastName);
        /*people.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                //return 0;
                return o1.lastName().compareTo(o2.lastName());
            }
        });*/

//Part-6
/*
        Next, instead of assigning an anonymous class to a variable, I'm to both create and use this anonymous class directly
    in the method argument. First I'll copy most of the anonymous class statement, from after the assignment operator,
    the equals sign there, and before the final semi-colon. I'm going to paste that, in place of the comparatorLastName
    variable,

                    people.sort(comparatorLastName);
                                    to
                    people.sort(new Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            //return 0;
                            return o1.lastName().compareTo(o2.lastName());
                        }
                    });

    in the call to the people.sort method. I can run it this way and it works just as before. But again, notice that IntelliJ
    has grayed out "new Comparator<Person>()", when I use it in the method argument. If I hover over that, I can see I can
    replace that with a lambda expression, so I'll do that.

                    people.sort(new Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            //return 0;
                            return o1.lastName().compareTo(o2.lastName());
                        }
                    });
                                            to
                    people.sort((o1, o2) -> o1.lastName().compareTo(o2.lastName()));

        You might notice that even after I changed the last bit to a lambda expression, IntelliJ has again grayed that out
    and has another hint for us. IntelliJ says "we can now replace our code with 'Comparator.comparing'". I want you to
    ignore this suggestion until the end of this section of the course for now. It helps to understand first lambda expressions,
    then method references, then Comparator's special convenience methods, which the comparing method is. Don't worry.
    I'm going to cover all of these topics in order. For now, I want to ignore this hint and leave this code as I currently
    have it with this lambda expression. Let's review this expression for a few minutes.
*/
//End-Part-6

        people.sort((o1, o2) -> o1.lastName().compareTo(o2.lastName()));
        System.out.println(people);

//Part-7
/*
        Let's review this expression for a few minutes. The syntax of this lambda expression is on the left below.

        Generated Lambda Expression                                         Comparator's Abstract Method
        ((o1, o2) -> o1.lastName().compareTo(o2.lastName())                 int compare(T o1, T o2)

    This was passed directly as a method argument, for a parameter type that was a Comparator. The Comparator's abstract
    method, compare, is shown here on the right side. The lambda expression parameters are determined by the associated
    interface's method, the functional method. In the case of a Comparator, and it's compare method, there are two arguments.
    This is why we get o1, and o2 in parentheses, in the generated lambda expression. These arguments can be used in the
    expression, which is on the right of the arrow token.

                                    The Syntax of a Lambda Expression

        A lambda expression consists of a formal parameter list, usually but not always declared in parentheses; the arrow
    token; and either an expression or a code block after the arrow token. Because lambda expressions are usually simple
    expressions, it's more common to see them written as shown below, which doesn't use a code block, or opening and closing
    curly braces in other words.

                            (parameter1, parameter2, ... ) -> expression;

    The expression should return a value, if the associated interface's method returns a value. In the case of our generated
    expression, it returns an int, which is the result of the compare method on Comparator.

                            (o1, o2) -> o1.lastName().compareTo(o2.lastName())

    Next I want to look at the anonymous class side by side with the lambda expression. I think it helps with understanding
    the mechanism of lambdas. Are you asking, where's the link between the compare method, and this lambda expression?
    It's obvious in the anonymous class, because we override the compare method, and return the result of that expression.

            Anonymous Class                                                     Lambda Expression
        new Comparator<Person>() {                                              (o1, o2) -> o1.lastName().compareTo(o2.lastName());
            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName().compareTo(o2.lastName());
            }
        }

    We can see the two parameters and their types, and what the return value should be, in the anonymous class. It's all
    spelled out for us there. But the lambda expression has no reference to an enclosing method, as far as we can see from
    this code. How can these mean the same thing? How does Java know what the parameters and return type are, or even the
    method for that method?

                                Where's the method in the lambda expression?

        For a lambda expression, "the method is inferred" by Java! How can Java infer the method to use? Java takes its
    clue from the reference type, in the context of the lambda expression usage. What do I mean by that? Here, I show a
    simplified view, ignoring the generic types, of the sort method on List.

                        void sort(Comparator c)

    And here is the call to that method passing the lambda expression.

                        people.sort((o1, o2) -> o1.lastName().compareTo(o2.lastName()));

    From this, Java can infer that this lambda expression, resolves to a Comparator type, because of the method declaration.
    This means the lambda expression passed, should represent code for a specific method on the Comparator interface.
    But which method? Well, there's only one the lambda expression cares about, and that's the "abstract method" on Comparator.
    Java requires types which support lambda expressions, to be something called a functional interface.

                                        What's a functional interface?

        "A functional interface" is an interface that has "one, and only one, abstract method". This is how Java can infer
    the method, to derive the parameters and return type, for the lambda expression. You may also see this referred to
    as SAM, which is short for "Single Abstract Method", which is called the functional method. A functional interface is
    the "target type for a lambda expression". Let's explore this further in a bit code.
*/
//End-Part-7

        interface EnhancedComparator<T> extends Comparator<T> {
            int secondLevel(T o1, T o2);
        }

//Part-8
/*
        And next, I want to create my own local interface that extends Comparator. As of JDK 16, this functionality is
    supported, meaning I can declare an interface right here, a local interface, in the method block, local only to this
    method. I'm going to call this EnhancedComparator, a generic interface, with type T. It's going to extend the Comparator
    interface. And this interface is going to have one abstract method, called secondLevel, that takes 2 arguments of type
    T, the generic type. I'll next use this interface to create a second anonymous class. My variable will be called
    comparatorMixed, and I'll assign it a new anonymous class expression, using this EnhancedComparator, and declaring the
    type argument to be person. IntelliJ is prompting me there's an error, and the solution is to implement the abstract
    method, so I'll do that, picking the highlighted ones by default. Notice here that IntelliJ has highlighted 2 methods.
    Why two, when my local interface only has one? What you shouldn't forget is, that interfaces inherit abstract methods.
    My new interface here, inherits the abstract method, compare, that's on the Comparator interface. I could've implemented
    it in EnhancedComparator, but I didn't. Remember, I don't have to implement abstract methods, when I extend interfaces.
    This means there are now 2 abstract methods, on the EnhancedComparator, that concrete classes have to implement, if
    they use this interface. I'll generate these 2 methods. And I'll replace "return 0;", in the compare method, with 2
    statements.
*/
//End-Part-8

        var comparatorMixed = new EnhancedComparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                //return 0;
                int result = o1.lastName().compareTo(o2.lastName());
                return (result == 0 ? secondLevel(o1, o2) : result);
            }

//Part-9
/*
        First I'll set up a local variable, result, assigning it the result of comparing the last names. And then in my
    return statement, I'm going to use a ternary operator. If the result of comparing the last names is zero, meaning they
    have the same last names, I want to execute the secondLevel method. Now I need to implement the second level method,
    which will compare first names. I'll replace "return 0;" with one that returns the comparison of the 2 persons first
    names. Ok, so that's my new anonymous comparator. I want to again sort my people list, using this variable, comparator
    Mixed, and print my list out to confirm it worked.
*/
//End-Part-9

            @Override
            public int secondLevel(Person o1, Person o2) {
                //return 0;
                return o1.firstName().compareTo(o2.firstName());
            }
        };

        people.sort(comparatorMixed);
        System.out.println(people);

//Part-10
/*
        Running that:

                    [Sally Brown, Charlie Brown, Peppermint Patty, Lucy Van Pelt, Linus Van Pelt]
                    [Charlie Brown, Sally Brown, Peppermint Patty, Linus Van Pelt, Lucy Van Pelt]

    you can see the people sorted by last name, then first name, on the second output there, so now Charlie Brown comes
    before Sally Brown, and Linus is before Lucy. Now, let's say I want to turn this anonymous class, into a lambda
    expression? Well, I can't. This is because it's not technically a functional interface. When we say a functional interface
    can only have 1 abstract method, this means only 1, and includes counting any inherited methods. Java can't determine
    which abstract method to use for this interface, so it can't be a target type for lambda expressions. Because this
    interface ultimately requires classes to implement 2 abstract methods, it's not a functional interface. I hope that
    make sense, and that now you have a pretty good handle on how a lambda expression works under the covers, and why the
    functional interface is the underlying framework for its use.
*/
//End-Part-10
    }
}
