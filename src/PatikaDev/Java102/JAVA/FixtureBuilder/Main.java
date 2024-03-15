package JAVA.FixtureBuilder;

import java.util.*;

/**
A class that creates random football match schedules for teams entered should be written in Java.

Rules:

* Double Circuit League method will be applied. Each team will play two matches against other teams, home and away.
* The left side of the list shows the home team and the right side shows the away team.
* If an odd number of team lists is entered, another team named "Bye" must be added to complete the even number. Teams matched with Bye will not have a match that week.

Number of teams double scenario:                Number of teams single scenario:

Teams                                           Teams
- A                                             - A
- B                                             - B
- C                                             - C
- D                                             - D
- E                                             - E
- F                                             - F
                                                - G

Round 1 :                                       Round 1 :
A vs B                                          A vs Bye
C vs D                                          C vs G
E vs F                                          E vs B
                                                F vs D

Round 2 :                                       Round 2 :
B vs F                                          Bye vs D
D vs E                                          B vs F
A vs C                                          G vs E
                                                A vs C

Round 3 :                                       Round 3 :
C vs B                                          C vs Bye
E vs A                                          E vs A
F vs D                                          F vs G
                                                D vs B

Round 4 :                                       Round 4 :
B vs D                                          Bye vs B
A vs F                                          G vs D
C vs E                                          A vs F
                                                C vs E

Round 5 :                                       Round 5 :
E vs B                                          E vs Bye
F vs C                                          F vs C
D vs A                                          D vs A
                                                B vs G

Round 6 :                                       Round 6 :
B vs A                                          Bye vs G
D vs C                                          A vs B
F vs E                                          C vs D
                                                E vs F

Round 7 :                                       Round 7 :
F vs B                                          F vs Bye
E vs D                                          D vs E
C vs A                                          B vs C
                                                G vs A

Round 8 :                                       Round 8 :
B vs C                                          Bye vs A
A vs E                                          G vs C
D vs F                                          B vs E
                                                D vs F

Round 9 :                                       Round 9 :
D vs B                                          D vs Bye
F vs A                                          F vs B
E vs C                                          E vs G
                                                C vs A

Round 10 :                                      Round 10 :
B vs E                                          Bye vs C
C vs F                                          A vs E
A vs D                                          G vs F
                                                B vs D

                                                Round 11 :
                                                B vs Bye
                                                D vs G
                                                F vs A
                                                E vs C

                                                Round 12 :
                                                Bye vs E
                                                C vs F
                                                A vs D
                                                G vs B

                                                Round 13 :
                                                G vs Bye
                                                B vs A
                                                D vs C
                                                F vs E

                                                Round 14 :
                                                Bye vs F
                                                E vs D
                                                C vs B
                                                A vs G
**/

class Scheduler {

    private final List<String> teams;

    public Scheduler(List<String> teams) {
        this.teams = teams;
        if (this.teams.size() % 2 != 0) {
            this.teams.add("Bye");
        }
    }

    public void run() {
        generateSchedule(this.teams);
    }

    public static void generateSchedule(List<String> teams) {
        List<Matchup> previousMatchups = new ArrayList<>();

        int numMatches = teams.size() / 2;
        int numRounds = 2 * (teams.size() - 1);
        String[][] scheduleList = new String[numRounds][numMatches];
        for (int round = 1; round <= 2 * (teams.size() - 1); round++) {
            System.out.println("Round " + round);
            if (round <= numRounds / 2) {
                for (int match = 1; match <= numMatches; match++) {
                    String withdrawnTeam = teams.removeFirst();

                    // Calculate positions without worrying about out of bounds
                    int awayPosition = (round + teams.size() / 2 - 1) % teams.size();

                    // Avoid the same matchup in consecutive rounds
                    while (hasMatchupOccurredBefore(withdrawnTeam, teams.get(awayPosition), previousMatchups, round, numRounds)) {
                        awayPosition = (awayPosition + 1) % teams.size();
                    }

                    String awayTeam = teams.get(awayPosition);

                    // Record the matchup
                    recordMatchup(withdrawnTeam, awayTeam, previousMatchups);

                    // Print the matchup for demonstration purposes
                    scheduleList[round - 1][match - 1] = "Match " + match + ": " + withdrawnTeam + " vs " + awayTeam;
                    scheduleList[round + numRounds / 2 - 1][match - 1] = "Match " + match + ": " + awayTeam + " vs " + withdrawnTeam;
                    System.out.println(scheduleList[round - 1][match - 1]);

                    // Add the withdrawn team back to the list for the next round
                    teams.add(withdrawnTeam);
                }
            } else {
                for (int i = 0; i < numMatches; i++) {
                    System.out.println(scheduleList[round - 1][i]);
                }
            }
        }
    }

    // Check if a matchup has occurred before
    public static boolean hasMatchupOccurredBefore(String team1, String team2, List<Matchup> previousMatchups, int round, int numRounds) {
        return previousMatchups.stream()
                .anyMatch(matchup -> (matchup.getHomeTeam().equals(team1) && matchup.getAwayTeam().equals(team2) && round <= numRounds / 2) ||
                        (matchup.getHomeTeam().equals(team2) && matchup.getAwayTeam().equals(team1) && round > numRounds / 2));
    }

    // Record a matchup
    public static void recordMatchup(String homeTeam, String awayTeam, List<Matchup> previousMatchups) {
        previousMatchups.add(new Matchup(homeTeam, awayTeam));
    }
}

// Class representing a matchup
class Matchup {
    private final String homeTeam;
    private final String awayTeam;

    public Matchup(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> teams = new ArrayList<>();
        teams.add("team1");
        teams.add("team2");
        teams.add("team3");
        teams.add("team4");
        teams.add("team5");

        Scheduler schedule = new Scheduler(teams);
        schedule.run();
    }
}