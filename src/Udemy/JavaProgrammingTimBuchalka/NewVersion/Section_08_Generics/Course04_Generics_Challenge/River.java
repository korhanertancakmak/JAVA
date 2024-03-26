package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course04_Generics_Challenge;

public class River extends Line{

    private final String name;

    public River(String name, String... locations) {
        super(locations);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " River";
    }
}
