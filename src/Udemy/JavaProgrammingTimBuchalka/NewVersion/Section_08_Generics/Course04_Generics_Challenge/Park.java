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
}
