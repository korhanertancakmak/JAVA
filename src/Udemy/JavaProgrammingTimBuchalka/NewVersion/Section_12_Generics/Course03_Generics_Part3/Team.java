package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Generics.Course03_Generics_Part3;

import java.util.ArrayList;
import java.util.List;

//Part-6
/*
        After the generic type T, I'm going to type extends Player, still in the <>. Before we talk about this, let's see
    what it did to our code, in the main method of the Main class.

        In the two cases, where we use Team for String and Integer, I'm getting compiler errors now. If I hover over String,
    we get the message that

    "Type parameter 'java.lang.String' is not within its bound; should implement 'Player'"

    What does not within its bounds mean?

                        Generic classes can be bounded, limiting the types that can use it

        Below, I'm showing the code from my class. So T, our type parameter, is followed by the word extends, and then the
    class Player. This extends keyword doesn't have the same meaning as extends, when it's used in a class declaration.

                    public class Team<T extends Player> {}

    This isn't saying our type T extends Player, although it could. This is saying the parameterized type T, has to be a
    Player, or a "subtype" of Player. Now Player in this case could have been either a class or an interface, the syntax
    would be the same. This declaration establishes what is called an "upper bound", on the types that are allowed to be
    used with this class. This means that only subtypes of Player or a Player itself(if it were a class and not an instance)
    can be used with this class. In this case, this code doesn't care if Player is an interface or a class. We use the
    extends keyword for either. There are good reasons for specifying an upper bound. We saw one already, that we can limit
    what types can be used by the generic class. But there's another advantage. Going back to the Main class,
*/
//End-Part-6

//Part-8
/*
        I want to revert that last change for a moment, so that this class has no bound. And then, I want to change the
    listTeamMembers code.
*/
//End-Part-8

record Affiliation(String name, String type, String countryCode) {

    @Override
    public String toString() {
        return name + " (" + type + " in " + countryCode + ")";
    }
}

//Part-13
/*
        And I'll override the toString method. And I'll replace "null" with code to print out the fields on this class.

                return name + " (" + type + " in " + countryCode + ")";

    Now, I could have made this a base or abstract class, but right now, for simplicity, I'm just going to use this record,
    whether a team is affiliated with a city or a town, or even a country. Getting back to the main method, you can see
    I have quite a few compiler errors, because now Team expects 2 type parameters to be declared.
*/
//End-Part-13

public class Team <T extends Player, S>{

    private String teamName;
    private List<T> teamMembers = new ArrayList<>();
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;

    private S affiliation;


//Part-11
/*
        You might remember, I said a second type parameter, by convention will be S, U, or V, so I'll use S, because I
    don't want to confuse it with the static methods type parameter. And this particular type, is going to represent the
    city the team is affiliated with. And I'll add an attribute, I'll call it affiliation, so that our team could be associated
    with different kinds of teams, like city teams, country teams or school teams. Next, I want to add a constructor to
    include this type. I'll add that after the one that's already here,
*/
//End-Part-11

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(String teamName, S affiliation) {
        this.teamName = teamName;
        this.affiliation = affiliation;
    }

    public void addTeamMember(T t) {

        if (!teamMembers.contains(t)) {
            teamMembers.add(t);
        }
    }

//Part-9
/*
        Instead of using the default print out, I want to print each member on its own line. I'll write the code with a
    for each loop. And our code should run like this. Going back to the main method, and running the code again,

                    Philadelphia Phillies Roster:
                    BaseballPlayer[name=B Harper, position=Right Fielder]
                    BaseballPlayer[name=B Marsh, position=Right Fielder]
                    BaseballPlayer[name=D Guthrie, position=Center Fielder]
                    Adelaide Crows Roster:
                    FootballPlayer[name=Tex Walker, position=Centre half forward]
                    FootballPlayer[name=Rory Laird, position=Midfield]
                    Adelaide Storm Roster:
                    N Roberts
                    Canberra Heat Roster:
                    B black
                    Canberra Heat (Ranked 3) lost to Adelaide Storm (Ranked 1)

    The only difference is the members are printed on separate lines. But I really only want the player's name to be printed.
    If I go back to the Generic Team Class, listTeamMembers method, I'll just use the name accessor method to do that, by
    rewriting println(t) as println(t.name()).

        After changing println(t) to println(t.name()), we've got a problem. This code doesn't like that method being
    called on the generic type, T. Remember, at this point, generic type T can be anything under the sun, and as we've
    shown, it could be a String, or an Integer, or anything. And these types may not have a name method on them, and in
    fact they don't, so we can't try to use a method in our class, that's not on java.lang.Object. When we don't specify
    an upper bound, the upper bound is implicitly java.lang.Object, meaning that's the only functionality we can use on
    our type parameter, without first casting. In this case, this is really too generic for our purposes. Leaving this
    method for a moment and going back to the class declaration, I want to get more specific, and again, add back the
    "extends Player". This lets us use the name method now, in listTeamMembers, and the Team class compiles. Going back
    to the main method,
*/
//End-Part-9

    public void listTeamMembers() {

        //System.out.println(teamName + " Roster:");                    // commented via Part-12
        System.out.print(teamName + " Roster:");
        System.out.println((affiliation == null ? "" : " AFFILIATION: " + affiliation));
        for (T t : teamMembers) {
            System.out.println(t.name());
        }

//Part-12
/*
        And let's actually just print out the affiliation in the listTeamMembers method. I'll change println to print, on
    the first statement, and then add a second statement, so these get printed on a single output line. Now, this could
    be "null", if we don't pass it on the constructor, so I want to use a ternary operator to print it, if it's not "null".

        So what is Affiliation? Well, it could be anything, a String, a StringBuilder, an interface, or a class or record.
    I'll create a record, called Affiliation in this Team.java source file.
*/
//End-Part-12
    }

    public int ranking() {
        return (totalLosses * 2) + totalTies + 1;
    }

    public String setScore(int ourScore, int theirScore) {

        String message = "lost to";
        if (ourScore > theirScore) {
            totalWins++;
            message = "beat";
        } else if (ourScore == theirScore) {
            totalTies++;
            message = "tied";
        } else {
            totalLosses++;
        }
        return message;
    }

    @Override
    public String toString() {
        return teamName + " (Ranked " + ranking() + ")";
    }

}
