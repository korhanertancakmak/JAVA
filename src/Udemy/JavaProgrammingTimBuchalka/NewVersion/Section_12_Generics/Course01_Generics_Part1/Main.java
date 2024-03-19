package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Generics.Course01_Generics_Part1;

//Part-1
/*
                                                Generics

        By now, if you've been doing this course in order, you're familiar with generalizing, conceptually, your own class
    design. You pull out what things your classes have in common, so that you can think about them generally. Generics
    allow us to create classes, to design them, in a general way, without really worrying about the specific details of
    the elements they might contain. Java's ArrayLists are an example of a generic class. We can use an ArrayList for any
    type of object, because many of the methods on that class can be applied to any type. Let's jump in, and see how to
    create generic types, and examine how to use them and when you might choose to use them.

        We actually used generic classes, like an ArrayList or a LinkedList, but we haven't looked at how this all works.
    Java supports generic types, such as classes, records and interfaces. It also supports generic methods. Sound confusing?
    It's easier to talk about a generic class, by looking at one in a bit of code. Below, I'm showing you a regular class
    declaration, next to a generic class.

            Regular Class                                   Generic Class

            class ITellYou {                                class YouTellMe<T> {
                private String field;                           private T field;
            }                                               }

    The thing to notice with the generic class, is that the class declaration has angle brackets with a <T> in them, directly
    after the class name. T is the placeholder for a type that will be specified later. This is called a type identifier,
    and it can be any letter or word, but T which is short for Type is commonly used. For the regular class above, I've
    declared a field with a type of String. But for the generic class, the field's type is that placeholder, just T, and
    this means it can be any type at all. The T in the angle brackets means it's the same type as the T, specified as the
    type of the field.

                        Reference Type      Type Parameter      Variable Name
                          ArrayList     <      String       >   listOfString;

        Above, I have a variable declaration of the generic ArrayList. In the declaration of a reference type that uses
    generics, the type parameter is declared in angle brackets. The reference type is ArrayList, the type parameter (or
    parameterized type) is String, which is declared in angle brackets, and listOfString is the variable name. Many of
    Java's libraries are written using generic classes and interfaces, so we'll be using them a lot by moving forward.
    But it's still a good idea to learn to write your own generic class, to help you understand the concept.

        Next, I want to walk through a non-generic class example, and then turn that class into a generic example. I'm
    going to create a BaseballPlayer record, in the Main.java source file. That record will only have 2 fields, name and
    position, which is the player's usual position.
*/
//End-Part-1

record BaseballPlayer(String name, String position) {}

//Part-2
/*
        And now, I'm going to create a new class in this package, and call it BaseballTeam. Next, I'll add the fields on
    Baseball Team, practicing good encapsulation techniques, making them all private.
*/
//End-Part-2

public class Main {
    public static void main(String[] args) {

        BaseballTeam phillies = new BaseballTeam("Philadelphia Phillies");
        BaseballTeam astros = new BaseballTeam("Houston Astros");

//Part-10
/*
        Now, to score the result, I'll create a method in the Main class to handle this:
*/
//End-Part-10

        scoreResult(phillies, 3, astros, 5);

//Part-12
/*
        If I run that code,

                Philadelphia Phillies (Ranked 3) lost to Houston Astros (Ranked 1)

    you can see that the Philadelphia Phillies are Ranked 3 , and they lost to the Houston Astros who are Ranked 1. Ok,
    that's good, Our scoring works. Now I want to actually add a couple of players to my phillies team.
*/
//End-Part-12

        var harper = new BaseballPlayer("B Harper", "Right Fielder");
        var marsh = new BaseballPlayer("B Marsh", "Right Fielder");
        phillies.addTeamMember(harper);
        phillies.addTeamMember(marsh);
        phillies.listTeamMembers();

//Part-13
/*
        I've got a couple of philly team members, and both are right fielders. I'm using var as the type, which infers the
    type as BaseballPlayer, just to make it easier to see this code on the screen. Our record could have had more fields,
    like batting average or years on team, but I'm keeping it simple. If I run this code,

                Philadelphia Phillies (Ranked 3) lost to Houston Astros (Ranked 1)
                Philadelphia Phillies Roster:
                [BaseballPlayer[name=B Harper, position=Right Fielder], BaseballPlayer[name=B Marsh, position=Right Fielder]]

    we'll get the phillies roster printed with our 2 players. Ok, so we have a baseball team application, and let's imagine
    it sold really well, so well that a football team is interested in using it. What do we do?
*/
//End-Part-13
    }

    public static void scoreResult(BaseballTeam team1, int t1_score, BaseballTeam team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);                         // will be ignored
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

//Part-11
/*
        We want to call scoreResult on the first team, and get the message from that. Then we set the score on the second
    team, but ignore that message, because we're referring to the first team's message in our final output. Last line prints
    out team1, and how they scored against team2. And let me add some code to score a result in the main method.
*/
//End-Part-11
}
