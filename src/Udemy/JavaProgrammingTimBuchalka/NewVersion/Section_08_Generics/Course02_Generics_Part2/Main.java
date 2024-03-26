package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course02_Generics_Part2;

interface Player {}

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


        SportsTeam afc = new SportsTeam("Adelaide Crows");
        var tex = new FootballPlayer("Tex Walker", "Centre half forward");
        afc.addTeamMember(tex);
        //afc.listTeamMembers();


        var guthrie = new BaseballPlayer("D Guthrie", "Center Fielder");
        afc.addTeamMember(guthrie);
        afc.listTeamMembers();


        Team<BaseballPlayer> phillies1 = new Team<>("Philadelphia Phillies");
        Team<BaseballPlayer> astros1 = new Team<>("Houston Astros");
        scoreResult(phillies1, 3, astros1, 5);


        Team<FootballPlayer> afc1 = new Team<>("Adelaide Crows");
        var tex1 = new FootballPlayer("Tex Walker", "Centre half forward");
        afc1.addTeamMember(tex1);

        //var guthrie = new BaseballPlayer("D Guthrie", "Center Fielder");
        //afc1.addTeamMember(guthrie1);
        var rory = new FootballPlayer("Rory Laird", "Midfield");
        afc1.addTeamMember(rory);
        afc1.listTeamMembers();
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
