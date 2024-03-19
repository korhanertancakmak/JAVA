package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Abstraction_Interface.Course01_Abstract;
//Part-12
/*
        We now have a Fish class that extends Animal, and it's just copied over the move and makeNoise method, which I
    want to change. First the move method.
*/
//End-Part-12
public class Fish extends Animal {
    public Fish(String type, String size, double weight) {
        super(type, size, weight);
    }

//Part-13
/*
        So fish, if they're unafraid, have a nice slow lazy way of gliding through the water. But if they're threatened,
    they might dart behind something. And now I'll change the makeNoise method.
*/
//End-Part-13

    @Override
    public void move(String speed) {

        if (speed.equalsIgnoreCase("slow")) {
            //System.out.println(type + " lazily swimming");
            System.out.println(getExplicitType() + " lazily swimming");
        } else {
            //System.out.println(getExplicitType() + " darting frantically");
        }
    }

//Part-14
/*
        Although fish may not make noises in general, I'll just put something in here. So Goldfish will swish, and everything
    else will splash. Going back to the main method, what I want to show you next, what I want you to appreciate, when
    you code, is that writing code using the more generic or abstract type, save you a lot of effort. Let's create an
    ArrayList of Animal now.
*/
//End-Part-14

//Part-20
/*
        I'll change type, to a call to getExplicitType. Ok, so now I'm going to run this by going back to main method,
*/
//End-Part-20

    @Override
    public void makeNoise() {

        if (type == "Goldfish") {
            System.out.print("swish ");
        } else {
            System.out.print("splash ");
        }
    }


}
