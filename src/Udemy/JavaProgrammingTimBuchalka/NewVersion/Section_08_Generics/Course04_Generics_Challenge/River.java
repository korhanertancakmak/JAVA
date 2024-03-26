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

//Part-11
/*
        And in the constructor, instead of passing a single location string, I want to allow multiple location strings
    using a variable argument(...), and call that locations. And I'll change the call to the super constructor, passing
    locations, not just location. Finally, the toString method will return the name, and River concatenated to that, in
    the description. And now I've built everything except the Generic class and the Main class, so I'll do that now.

        First the new Layer class.
*/
//End-Part-11
}
