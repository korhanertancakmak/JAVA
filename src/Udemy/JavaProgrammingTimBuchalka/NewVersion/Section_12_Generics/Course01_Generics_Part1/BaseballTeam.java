package CourseCodes.NewSections.Section_12_Generics.Course01_Generics_Part1;

import java.util.ArrayList;
import java.util.List;

public class BaseballTeam {

    private String teamName;
    private List<BaseballPlayer> teamMembers = new ArrayList<>();
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;

//Part-3
/*
        Okay, so nothing very exciting here. Notice we're actually using a generic interface, the List, as well as a generic
    class, the ArrayList. I've used "List" as the reference type, for teamMembers. Hopefully you'll remember that its best
    practice, to use the interface type for the reference variable. In other words, use List and not ArrayList, on the
    left side of the assignment operator. And I'm making the type parameter, on the left side of the assignment operator.
    And I'm making the type parameter, Baseball Player, our record type. I'll add a constructor after these fields, and
    pick just team name.
*/
//End-Part-3

    public BaseballTeam(String teamName) {
        this.teamName = teamName;
    }

//Part-4
/*
        Now, I want to start adding a few methods. First, addTeamMember. If team members does not contain player, add the
    player.
*/
//End-Part-4

    public void addTeamMember(BaseballPlayer player) {

        if (!teamMembers.contains(player)) {
            teamMembers.add(player);
        }
    }

//Part-5
/*
        Since baseball player is a record, and records come with an implicit equals method, this method can test the equality
    of all the record's attributes. The contains method will check if one player's name and position, are equal to another
    player's same fields, and if it is, the player won't get added. It's case-sensitive, and I'll leave it this way for
    simplicity. Now, I'll add listTeamMembers, and that one's easy enough. Teams often call their current members list,
    a roster, so I'll print that in the label along with the team name.
*/
//End-Part-5

    public void listTeamMembers() {

        System.out.println(teamName + " Roster:");
        System.out.println(teamMembers);
    }

//Part-6
/*
        Here, I'm just using ArrayList's built-in functionality, so I can pass that list directly to the println statement.
    Now, I also actually want to rank teams. This is how one team ranks against another.
*/
//End-Part-6

    public int ranking() {
        return (totalLosses * 2) + totalTies + 1;
    }

//Part-7
/*
        The best team should have the highest rank. This means the team with the most wins, is ranked number 1. I'll make
    losses count more, multiplying them by 2, then add ties, and if all of that is zero, I'll add 1, so the highest rank
    is never higher than 1. This method isn't the final ranking of a team in a league or group of teams, it's just used
    to determine how well one team is doing compared to another. You'd probably use a list to store the teams and then
    sort them, but we're just keeping it simple for now.

        Next, I want a set Score method, this will return a String, whether this team lost or beat the other team, and
    it takes 2 arguments, ourScore and their Score:
*/
//End-Part-7

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

//Part-8
/*
        This just checks if ourScore is greater than the other team's score, and if it is, it increments totalWins and
    returns the message "beat" from this method. Now we want to add the other two conditional expressions, for a loss and
    a tie. So I'll add an else if statement there, and check that ourScore is equal to theirScore. if that's true, increment
    totalTies, so ++ after that variable. And set message = tied. Now an else, this happens when ourScore is less than
    theirScore, so I'll increment totalLosses in this case. So this code just figures out which bucket to increase, the
    total Wins, total Ties, totalLosses bucket. Next, I want to override the toString method for baseball team. I'll
    generate that, and that gives us the generated code, that calls super's to String. I'll replace that with my own code.
*/
//End-Part-8

    @Override
    public String toString() {
        return teamName + " (Ranked " + ranking() + ")";
    }

//Part-9
/*
        Here, I just print out the team name with its rank. Going to the main class, main method, I'll create 2 baseball
    teams.
*/
//End-Part-9
}
