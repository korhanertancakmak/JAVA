package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate;

//Part-2
/*
        And I'll set up five constants for now, each with it's worth in parentheses. First, SILVER COIN, for 5 points.
    Next, a GOLD COIN for 10. GOLD RING for 125. now, a PEARL NECKLACE for 250. Lastly, a GOLD BAR for 500 points, and
    I'll end the list with a semi colon. I'll add a public final int field, called worth, after the constants declaration.
    I'll generate the constructor, with that field. next I'll generate a getter. That's all I need for Loot. I'll just
    copy this enum, and paste it in the pirate package, calling the new enum Feature.
*/
//End-Part-2

public enum Loot {

    SILVER_COIN(5),
    GOLD_COIN(10),
    GOLD_RING(125),
    PEARL_NECKLACE(250),
    GOLD_BAR(500);

    private final int worth;

    Loot(int worth) {
        this.worth = worth;
    }

    public int getWorth() {
        return worth;
    }
}
