package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_Abstraction_Interface.Course03_Interfaces;
//Part-17
/*
        I'll add implements Trackable. The class doesn't compile, because we have to implement the track method on it.
    I'll copy the track method from Bird and paste it here. Ok, so that's a truck, which isn't going to fly, but it might
    be tracked, if it's used for moving freight around. And we're not going to call inFlight for a truck in the main method,
    but we can call the track method.
*/
//End-Part-17
public class Truck implements Trackable{

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    }
}
