package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Abstraction_Interface.Course01_Abstract;

public class Horse extends Mammal{
//Part-26
/*
        And I'll have that extend Mammal. And we see right away this doesn't compile. It's saying we have to implement move,
    on Animal, even though we're inheriting from Mammal. This is because Mammal didn't include an implementation, for the
    move method. Let's add that on the Mammal class, using implement methods.
*/
//End-Part-26

    public Horse(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void makeNoise() {

    }

    @Override
    public void shedHair() {

        System.out.println(getExplicitType() + " sheds in the spring");
    }

//Part-29
/*
        We still have an error on this class, but it's not because of overridden methods now. It's because of the missing
    constructor, so I'll pick create constructor matching super, in that popup this time. And now the Horse class is valid,
    but let's actually put something in there for shedHair. I'll print out the type with a message. And for good measure,
    I want to create a Horse in the main method.
*/
//End-Part-29
}
