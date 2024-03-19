package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course06_CollectionsChallenge_CardGamePart1.games.poker;

//Part-3
/*
        I want to set up the Hands with the worst hand in the first ordinal position, and that will be NONE, for nothing
    of value in a hand. Remember it's customary to make the enum constants all uppercase, and words separated by underscores.
    I'll follow NONE, with ONE PAIR, TWO PAIR, THREE of a KIND, FULL HOUSE, and finally the best hand is FOUR_OF_A_KIND,
    so it'll have the highest ordinal value. I want to end this constants list with a semi-colon, because I'm going to
    override the toString method on this. I'll insert a new line, and use the override features to generate a toString
    method. I'll return the enum name value, which will be the constant name, but I want to replace any underscores with
    just spaces. That's all I need for my Ranking enum. Before I continue, I'm going to edit the Card record,
*/
//End-Part-3

public enum Ranking {

    NONE, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND;

    @Override
    public String toString() {
        return this.name().replace('_',' ');
    }
}
