package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course08_HashCodes_Sets_Maps;

public class PlayingCard {

    private String suit;
    private String face;
    private int internalHash;

    public PlayingCard(String suit, String face) {
        this.suit = suit;
        this.face = face;
        //this.internalHash = 1;
        this.internalHash = (suit.equals("Hearts")) ? 11 : 12;
    }

    @Override
    public String toString() {
        return face + " of " + suit;
    }

/*
    @Override
    public boolean equals(Object o) {
        //return super.equals(o);
        System.out.println("--> Checking Playing Card equality");
        return true;
    }
*/

/*
    @Override
    public int hashCode() {
        //return super.hashCode();
        return internalHash;
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayingCard that = (PlayingCard) o;

        if (!suit.equals(that.suit)) return false;
        return face.equals(that.face);
    }
    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + face.hashCode();
        return result;
    }
}
