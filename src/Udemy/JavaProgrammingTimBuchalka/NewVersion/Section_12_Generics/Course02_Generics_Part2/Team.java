package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Generics.Course02_Generics_Part2;

import java.util.ArrayList;
import java.util.List;

//Part-6
/*
        That's our first generic class, Team with one type parameter. Now, you'll remember, this class was dealing with
    Player instances, predominantly, as the team members. So T in the cases we've been looking at, would really stand for
    player, either a football player or a baseball player. I want to replace any reference to the Player class, paying
    attention to exact match on case, and change them to the type T. This changed our List to have T in its <>. And the
    addTeamMember method was changed, the type of the parameter is T. The parameter is player can be also changed to t.
    And that's our first generic class. Let's switch back to the main method.
*/
//End-Part-6

public class Team <T>{

    private String teamName;
    private List<T> teamMembers = new ArrayList<>();
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public void addTeamMember(T t) {

        if (!teamMembers.contains(t)) {
            teamMembers.add(t);
        }
    }

    public void listTeamMembers() {

        System.out.println(teamName + " Roster:");
        System.out.println(teamMembers);
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
