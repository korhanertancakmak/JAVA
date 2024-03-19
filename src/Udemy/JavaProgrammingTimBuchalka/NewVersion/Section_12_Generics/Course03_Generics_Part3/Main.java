package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Generics.Course03_Generics_Part3;

//Part-1
/*
        In the last course, we created a generic team class, and used it in our main method, declaring the type parameter
    there for our teams. We first typed Team with a Baseball Player in <> to create 2 teams of baseball players. And we
    also typed a Team with FootballPlayer in <>, which supported a team of football players. And because of this, we couldn't
    add a baseball player to a football team, so Java's compiler recognized this was a problem, and gave us an error.

        Next, I'll add another team, and team member.
*/
//End-Part-1


interface Player {

    String name();
}

//Part-7
/*
        First, I want to change my interface, to have one abstract method. And you'll remember, any method we add without
    a method body, is implicitly public and static on an interface. First of all, I know my records, BaseballPlayer and
    FootballPlayer, all have an implicit name accessor method already, so this doesn't force a change to those records.
    They'll already have this method implemented on them. Going back to Team,
*/
//End-Part-7

record BaseballPlayer(String name, String position) implements Player {}

record FootballPlayer(String name, String position) implements Player {}

record VolleyballPlayer(String name, String position) implements Player {}

public class Main {
    public static void main(String[] args) {

        var philly = new Affiliation("city", "Philadelphia, PA", "US");

//Part-15
/*
        Again, I'm using "var" instead of the Affiliation type, just to save some screen space. I'll include that in the
    constructor for my phillies team. And running this code,

            Philadelphia Phillies (Ranked 3) lost to Houston Astros (Ranked 1)
            Philadelphia Phillies Roster: AFFILIATION: city (Philadelphia, PA in US)                <<<<<<<<
            B Harper
            B Marsh
            D Guthrie
            Adelaide Crows Roster:
            Tex Walker
            Rory Laird
            Adelaide Storm Roster:
            N Roberts
            Canberra Heat Roster:
            B black
            Canberra Heat (Ranked 3) lost to Adelaide Storm (Ranked 1)

    You can see the affiliation. For the second type parameter, we didn't include an upper bound, so we could pass a simple
    string, or another instance of any class, to another team. Let's do that for adelaide crows.
*/
//End-Part-15

        //Team<BaseballPlayer, Affiliation> phillies = new Team<>("Philadelphia Phillies");
        Team<BaseballPlayer, Affiliation> phillies = new Team<>("Philadelphia Phillies", philly);
        Team<BaseballPlayer, Affiliation> astros = new Team<>("Houston Astros");
        scoreResult(phillies, 3, astros, 5);

        var harper = new BaseballPlayer("B Harper", "Right Fielder");
        var marsh = new BaseballPlayer("B Marsh", "Right Fielder");
        var guthrie = new BaseballPlayer("D Guthrie", "Center Fielder");
        phillies.addTeamMember(harper);
        phillies.addTeamMember(marsh);
        phillies.addTeamMember(guthrie);
        phillies.listTeamMembers();

//Part-16
/*
        Before the change it was,

            Team<FootballPlayer, Affiliation> afc1 = new Team<>("Adelaide Crows");
            var tex1 = new FootballPlayer("Tex Walker", "Centre half forward");
            afc1.addTeamMember(tex1);

    Running after the change,

            ... (same)
            Adelaide Crows Roster: AFFILIATION: City of Adelaide, South Australia, in AU            <<<<<<<<<
            Tex Walker
            Rory Laird
            Adelaide Storm Roster:
            N Roberts
            Canberra Heat Roster:
            B black
            Canberra Heat (Ranked 3) lost to Adelaide Storm (Ranked 1)

    you can see we have an affiliation for Adelaide crows now, even though we passed a String. There may be times when
    you want upper bounds, and other times when you don't as we've shown here. We haven't covered multiple types in the
    upper bounds declaration, using lower bounds, and working with overridden methods, with these more complex bounded
    types. For the most part, you're far more likely to be using classes, with type parameters, and venturing into the
    complexities I've just mentioned.
*/
//End-Part-16

        Team<FootballPlayer, String> afc1 = new Team<>("Adelaide Crows", "City of Adelaide, South Australia, in AU");
        var tex1 = new FootballPlayer("Tex Walker", "Centre half forward");
        afc1.addTeamMember(tex1);

        var rory = new FootballPlayer("Rory Laird", "Midfield");
        afc1.addTeamMember(rory);
        afc1.listTeamMembers();

        /*Team<String> adelaide = new Team<>("Adelaide Storm");
        adelaide.addTeamMember("N Roberts");
        adelaide.listTeamMembers();*/                                       // Commented via Part-10

//Part-2
/*
        This team will be Adelaide Storm, and I'll add the team member N Robers, and then I'll print it out. In this case,
    I'm saying the type for our team member is just String. I don't use a Player at all. And this code compiles and runs,

                    Adelaide Storm Roster:
                    [N Roberts]

    and we can see our player, which is just String value, N Roberts, listed under the Adelaide Storm Roster. The Team
    class, the way we have it set up, can really work with any type at all. And now I'll add another team, the first was
    really an Australian Volleyball team, so I'll create another volleyball team. I'll add a member, list the members,
    then score a game.
*/
//End-Part-2

        /*var canberra = new Team<String>("Canberra Heat");
        canberra.addTeamMember("B black");
        canberra.listTeamMembers();
        scoreResult(canberra, 0, adelaide, 1);*/                            // Commented via Part-10

//Part-3
/*
        If I run that,

                    Canberra Heat Roster:
                    [B black]
                    Canberra Heat (Ranked 3) lost to Adelaide Storm (Ranked 1)

    you can see our methods work, regardless of what type we use for the type parameter, with a couple of exceptions. We
    can't use a generic class, any generic class, with a primitive data type.
*/
//End-Part-3

        //Team<int> melbourneVB = new Team<>("Melbourne Vipers");           // Commented due to error

//Part-4
/*
        You can see that gives an error. Luckily, we have autoboxing, and this isn't a big problem. We can use the wrapper
    instead. And I'll change int to Integer, in the <> on the left.
*/
//End-Part-4

        //Team<Integer> melbourneVB = new Team<>("Melbourne Vipers");       // Commented via Part-10

//Part-5
/*
        Ok, now let's just say, we really don't want Team to be used for any class under the sun. Instead, we want it to
    only work for things that implement the Player interface. This requires one small change to the Team class.
*/
//End-Part-5

        Team<VolleyballPlayer, Affiliation> adelaide = new Team<>("Adelaide Storm");
        //adelaide.addTeamMember("N Roberts");
        adelaide.addTeamMember(new VolleyballPlayer("N Roberts", "Setter"));
        adelaide.listTeamMembers();

        var canberra = new Team<VolleyballPlayer, Affiliation>("Canberra Heat");
        canberra.addTeamMember(new VolleyballPlayer("B black", "Opposite"));
        canberra.listTeamMembers();
        scoreResult(canberra, 0, adelaide, 1);

//Part-10
/*
        We have several errors, and again, now we've said Team can only be used for a type of Player, not for any class,
    like String or Integer. First I'll comment out that last statements, that for Integer and Strings. And let me real
    quick add a Volleyball Player record in this Main.java source file.

        Now I want to use the VolleyballPlayer, at the first commented code, which was for the type String but will be
    now for the GenericTeam that I created.

                Team<String> adelaide = new Team<>("Adelaide Storm");
                adelaide.addTeamMember("N Roberts");
                adelaide.listTeamMembers();

    And I want to create actual volleyball players, I'll do that directly in the call to addTeamMember. First for the
    Adelaide team, and N Roberts, whose position will be Setter. Then for the Canberra team and B Black, an Opposite. Let's
    run this code,

                ... (same)
                Philadelphia Phillies Roster:
                B Harper
                B Marsh
                D Guthrie
                Adelaide Crows Roster:
                Tex Walker
                Rory Laird
                Adelaide Storm Roster:
                N Roberts
                Canberra Heat Roster:
                B black
                Canberra Heat (Ranked 3) lost to Adelaide Storm (Ranked 1)

    and we have it running and just printing out the player's names. We can use our generic team with any type of class,
    as long as it implements Player.

        Let's review why you'd want to use this extends keyword with a type parameter, remembering that it's declaring an
    upper bound. There are 2 reasons to specify an upper bound.

                                        Why specify an upper bound?

    1) An upper bound permits access to the bounded type's functionality.
    2) An upper bound limits the kind of type parameters you can use when using a generic class. The type used must be
    equal to, or a subtype of the bounded type.

        Before I bring this course to a close, I want to add another type to our Generic Class.
*/
//End-Part-10

//Part-14
/*
        First, I'll do a global replacement, replacing "Player>" with "Player, Affiliation>". I'll create an affiliation
    for the city of Philadelphia in the state of Pennsylvania in the US, at the very first line of the main method.
*/
//End-Part-14        
    }

    public static void scoreResult(BaseballTeam team1, int t1_score,
                                   BaseballTeam team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void scoreResult(SportsTeam team1, int t1_score,
                                   SportsTeam team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void scoreResult(Team team1, int t1_score,
                                   Team team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }
}
