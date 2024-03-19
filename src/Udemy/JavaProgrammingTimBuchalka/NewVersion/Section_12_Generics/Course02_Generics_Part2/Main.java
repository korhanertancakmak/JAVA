package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Generics.Course02_Generics_Part2;

//Part-1
/*
                                            Solution 1: Duplicate code

        In the last course, we built a Baseball Team class, and now we need to have a class that can handle football teams
    as well. One solution is to duplicate the code. We could copy and paste the BaseballTeam, and rename everything for
    FootballTeam, and create a FootballPlayer, as I'm showing below.

            _______________________________________
            | BaseballTeam =>                     |                  _____________________
            |-------------------------------------|                  | BaseballPlayer => |
            |   teamMembers: List<BaseballPlayer> |----------------<>|-------------------|
            |   ...                               |                  | name              |
            |-------------------------------------|                  |-------------------|
            |   addTeamMember(BaseballPlayer)     |                  |___________________|
            |_____________________________________|


            _______________________________________
            | FootballTeam =>                     |                  _____________________
            |-------------------------------------|                  | FootballPlayer => |
            |   teamMembers: List<FootballPlayer> |----------------<>|-------------------|
            |   ...                               |                  | name              |
            |-------------------------------------|                  |-------------------|
            |   addTeamMember(FootballPlayer)     |                  |___________________|
            |_____________________________________|


    This means you'd have to make sure any changes you made to one team or player, that made sense for the other team and
    player, had to be made in both sets of code. This is rarely a recommended approach, unless team operations are significantly
    different. The second solution is to use a Player interface.

                                 Solution 2: Use a Player Interface or Abstract Class

        We could change Baseball team to simply Team, and use an interface type (or abstract or base class) called Player.
    Below, I show a Team Class, and on this class, the members are a List of Players.

            _______________________________
            | Team =>                     |                  ___________________________
            |-----------------------------|                  | <<Interface>> Player => |
            |   teamMembers: List<Player> |----------------<>|-------------------------|
            |   ...                       |                  |-------------------------|
            |-----------------------------|                  |_________________________|
            |   addTeamMember(Player)     |                               ↑
            |_____________________________|                               ↑
                                                                          ↑ Implements
                                                       -------------------↑-------------------
                                                       ↑                                     ↑
                                            ___________↑__________                ___________↑__________
                                            | BaseballPlayer =>  |                | FootballPlayer =>  |
                                            |--------------------|                |--------------------|
                                            | name               |                | name               |
                                            | battingAverage     |                | shotsPerGame       |
                                            |____________________|                |____________________|

    I've made Player an interface, and have BaseballPlayer and FootballPlayer implementing that interface. This is a better
    design than the previous one, but it's still got problems.

        Let's explore it a little in code. Let's start by copying Baseball Team so that I create a new class named SportsTeam.
    We have a team, but our type of element in the team, is still BaseballPlayer. We could make an interface called Player
    and use that. I'll do that next, and I'll just add that interface in this Main.java source file above the BaseballPlayer
    record.
*/
//End-Part-1


interface Player {}

//Part-2
/*
        And I'm not even going to add any method in this interface. The reason I'm using an interface here, is simply
    because my BaseballPlayer is a record, and records can implement interfaces, but can't extend any classes. There are
    other reasons to use an interface, especially if you have methods you want implemented, such as rankBestPlayer or
    something like that.

        Next, I'll change all "BaseballPlayer" variable names with just "Player" in SportsTeam.java source file. And also
    the BaseballPlayer record below, I'll change it as "implements to Player". That's one advantage to using an interface
    over an abstract class, interfaces work with records. Now, we have 2 classes that can work with baseball players, the
    BaseballTeam class(I'll remove from this package for clarity), as well as this new SportsTeam class. Going back to
    main method, I'll copy those first 3 statements, and paste them right below.
*/
//End-Part-2

record BaseballPlayer(String name, String position) implements Player {}

record FootballPlayer(String name, String position) implements Player {}

public class Main {
    public static void main(String[] args) {

        SportsTeam phillies = new SportsTeam("Philadelphia Phillies");
        SportsTeam astros = new SportsTeam("Houston Astros");
        scoreResult(phillies, 3, astros, 5);

        var harper = new BaseballPlayer("B Harper", "Right Fielder");
        var marsh = new BaseballPlayer("B Marsh", "Right Fielder");
        phillies.addTeamMember(harper);
        phillies.addTeamMember(marsh);
        phillies.listTeamMembers();

//Part-3
/*
        And now, I'll change BaseballTeam to SportsTeam, in those 2 statements. This means our phillies and astros teams
    are SportsTeam instances, and not just BaseballTeam instances. So now my code compiles and when I run it,

                Philadelphia Phillies (Ranked 3) lost to Houston Astros (Ranked 1)
                Philadelphia Phillies Roster:
                [BaseballPlayer[name=B Harper, position=Right Fielder], BaseballPlayer[name=B Marsh, position=Right Fielder]]

    And the results are the same, but now with this new class SportsTeam, we can create a FootballTeam with football players.
    Let me do that now. I'll create FootballPlayer in Main.java, by copying the BaseballPlayer record, and then changing
    that to FootballPlayer. And FootballPlayer will also implement Player. And then, I can create a new team, with FootballPlayer.
*/
//End-Part-3

        SportsTeam afc = new SportsTeam("Adelaide Crows");
        var tex = new FootballPlayer("Tex Walker", "Centre half forward");
        afc.addTeamMember(tex);
        //afc.listTeamMembers();

//Part-4
/*
        Running this code,

                .... (same)
                Adelaide Crows Roster:
                [FootballPlayer[name=Tex Walker, position=Centre half forward]]

    we get our Adelaide Crows football team members printed out, and there's just 1, but you get the idea. So this was
    one solution. We created a team that has a list of Players, and as long as we have our classes, or records, implement
    the Player interface, we can use this class. And that's all well and good. We've got a team that will support any kind
    of player. But this team has a problem.

        First, there's no type checking when it comes to team members. Let me show you an example of what I mean.
*/
//End-Part-4

        var guthrie = new BaseballPlayer("D Guthrie", "Center Fielder");
        afc.addTeamMember(guthrie);
        afc.listTeamMembers();

//Part-5
/*
        Here, I'm adding a baseball player, a Phillies center fielder, named D Gutrie, to my Adelaide Crows football team.
    And the compiler lets me do it. Running that,

                ... (same)
                Adelaide Crows Roster:
                [FootballPlayer[name=Tex Walker, position=Centre half forward], BaseballPlayer[name=D Guthrie, position=Center Fielder]]

    we see that on the last line, our Adelaide Crows team (supposedly a football team) has a football player and a baseball
    player. This is not exactly what we'd want. We could leave the rules up to whoever is using this code. Or we could build
    in some rules. Generics give us this solution, by creating a generic team, meaning a team class, which has a type parameter.

        Next, I want to copy the SportsTeam class, and really make it generic, by naming it just Team. And we have an exact
    duplicate of SportsTeam, except the name. The first thing we need to do to make a class generic, is to set up type
    parameters.

                                                Generic Type Parameters

        I've already shown you that one way to declare a generic class, is to include a type parameter which I show here,
    in the angle brackets.

            public class Team<T> {}

    Now, using T is just a convention, short for whatever type you want to use this Team class for. But you can put anything
    you want in there. Single letter types are the convention however, and they're a lot easier to spot in the class code,
    so let me encourage you to stick to this convention. You can have more than one type parameter, so we could do T1, T2,
    T3.

            public class Team<T1, T2, T3> {}

    But again convention says, that instead of using type parameters like this, it's easier to read the code with alternate
    letter selections. And these are usually S, U, and V, in that order. If we had three types, we'd probably want to
    declare this class as shown here, with T, S, and U.

            public class Team<T, S, U> {}

    A few letters are reserved for special use cases. The most commonly used type parameter identifiers are:

          - E for Element (used extensively by the Java Collections Framework).
          - K for Key (used for mapped types).
          - N for Number.
          - T for Type.
          - V for Value.
          - S, U, V etc. for 2nd, 3rd, 4th types.

        Going back to our Generic Team, I'll add the type parameter, angle brackets with a T inside <T>, after the class
    name.
*/
//End-Part-5

        Team<BaseballPlayer> phillies1 = new Team<>("Philadelphia Phillies");
        Team<BaseballPlayer> astros1 = new Team<>("Houston Astros");
        scoreResult(phillies1, 3, astros1, 5);

//Part-7
/*
        And similar to what I did before, I'm going to copy the 3 statements, starting SportsTeam phillies, and paste them
    right below them. I'll rename the first 3 statements as phillies to phillies1, astros to astros1, respectively. And
    then in the pasted statements, I want to change SportsTeam to just Team. And again, I'll make a copy of the scoreResult
    method, and change the types to just Team. This code compiles, but it's got warnings. It says "Raw use of parameterized
    class "Team"". I can run this code, after this change,

                ... (same)
                Philadelphia Phillies (Ranked 3) lost to Houston Astros (Ranked 1)
                Philadelphia Phillies Roster:
                [BaseballPlayer[name=B Harper, position=Right Fielder], BaseballPlayer[name=B Marsh, position=Right Fielder]]

    and we get the same result for all of these types of Team classes. Leaving it this way in the code means we're really
    implementing it with the raw use of the class.

                                            Raw Usage of Generic Classes

        When you use generic classes, either referencing them or instantiating them, it's definitely recommended that you
    include a type parameter. But you can still use them without specifying one. This is called the "Raw Use" of the reference
    type. The raw use of these classes is still available, for backwards compatibility, but it's discouraged for several
    reasons:

  - Generics allow the compiler to do compile-time type checking, when adding and processing elements in the list.
  - Generics simplify code, because we don't have to do our own type checking and casting, as we would, if the type of our
    elements was Object.

    You may forget to include the type parameter, but IntelliJ will try to identify this for you. As you can see, IntelliJ
    is warning us, with the yellow highlights, that there's something we might want to review in this code. Let's change
    the code in our main method, to include type parameters, for our 3 new statements.

        Team phillies = new Team("Philadelphia Phillies");           Team<BaseballPlayer> phillies = new Team<>("Philadelphia Phillies");
        Team astros = new Team("Houston Astros");             to     Team<BaseballPlayer> astros = new Team<>("Houston Astros");
        scoreResult(phillies, 3, astros, 5);                         scoreResult(phillies, 3, astros, 5);

*/
//End-Part-7

        Team<FootballPlayer> afc1 = new Team<>("Adelaide Crows");
        var tex1 = new FootballPlayer("Tex Walker", "Centre half forward");
        afc1.addTeamMember(tex1);

        //var guthrie = new BaseballPlayer("D Guthrie", "Center Fielder");
        //afc1.addTeamMember(guthrie1);
        var rory = new FootballPlayer("Rory Laird", "Midfield");
        afc1.addTeamMember(rory);
        afc1.listTeamMembers();

//Part-8
/*
        I also want to go up to the adelaide crows to copy and paste that statement here, and do something similar, renaming
    the SportsTeam afc to afc1. And then changing SportsTeam to just Team on the pasted code. With these changes, you'll
    notice that I've got a compile error, where I'm trying to add D Guthrie, a baseball player, to the Adelaide crows team.
    And this is actually a good thing, and what we really want, because we want our teams to have all the same kind of
    player. Let me comment guthrie, and I'll create another football player, Rory Laird. And this code compiles and runs:

                Adelaide Crows Roster:
                [FootballPlayer[name=Tex Walker, position=Centre half forward], FootballPlayer[name=Rory Laird, position=Midfield]]

    Now, maybe you've noticed, in the Main class, IntelliJ's been trying to get our attention on that last scoreResult
    method, and I've been ignoring it. And it's the "Raw Use of parametrized class 'Team'". I'm going to continue to ignore
    this warning, as I work through the rest of this example. Generics can be tricky when they're used in method parameters,
    and the rules are different for a static method vs. an instance method. I want to cover these rules thoroughly in a
    future course. For now, I'll leave my code with these warnings, as we learn the basics of creating a generic class.
*/
//End-Part-8
    }

    public static void scoreResult(SportsTeam team1, int t1_score,
                                   SportsTeam team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void scoreResult(Team team1, int t1_score, Team team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }
}
