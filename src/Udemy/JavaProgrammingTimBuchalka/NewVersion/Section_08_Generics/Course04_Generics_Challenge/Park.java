package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course04_Generics_Challenge;

public class Park extends Point{

    private final String name;

    public Park(String name, String location) {
        super(location);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " National Park";
    }

//Part-10
/*
        With this toString, I'll now replace super.toString, because I just want to return name, and concatenate National
    Park to that. If I had other fields, I could include them here.

        And that's our first class that will render itself, as a point on a map. Now I'll copy and paste this class, making
    it River, to create the first Line class by extending it Line, not Point.
*/
//End-Part-10
}
