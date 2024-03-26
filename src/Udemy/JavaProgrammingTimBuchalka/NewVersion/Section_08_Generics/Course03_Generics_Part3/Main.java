package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course03_Generics_Part3;

interface Player {

    String name();
}

record BaseballPlayer(String name, String position) implements Player {}

record FootballPlayer(String name, String position) implements Player {}

record VolleyballPlayer(String name, String position) implements Player {}

public class Main {
    public static void main(String[] args) {

        var philly = new Affiliation("city", "Philadelphia, PA", "US");


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

        Team<FootballPlayer, String> afc1 = new Team<>("Adelaide Crows", "City of Adelaide, South Australia, in AU");
        var tex1 = new FootballPlayer("Tex Walker", "Centre half forward");
        afc1.addTeamMember(tex1);

        var rory = new FootballPlayer("Rory Laird", "Midfield");
        afc1.addTeamMember(rory);
        afc1.listTeamMembers();

        /*
        Team<String> adelaide = new Team<>("Adelaide Storm");
        adelaide.addTeamMember("N Roberts");
        adelaide.listTeamMembers();

        var canberra = new Team<String>("Canberra Heat");
        canberra.addTeamMember("B black");
        canberra.listTeamMembers();
        scoreResult(canberra, 0, adelaide, 1);


        Team<int> melbourneVB = new Team<>("Melbourne Vipers");           // Commented due to error


        Team<Integer> melbourneVB = new Team<>("Melbourne Vipers");
        */

        Team<VolleyballPlayer, Affiliation> adelaide = new Team<>("Adelaide Storm");
        //adelaide.addTeamMember("N Roberts");
        adelaide.addTeamMember(new VolleyballPlayer("N Roberts", "Setter"));
        adelaide.listTeamMembers();

        var canberra = new Team<VolleyballPlayer, Affiliation>("Canberra Heat");
        canberra.addTeamMember(new VolleyballPlayer("B black", "Opposite"));
        canberra.listTeamMembers();
        scoreResult(canberra, 0, adelaide, 1);
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
