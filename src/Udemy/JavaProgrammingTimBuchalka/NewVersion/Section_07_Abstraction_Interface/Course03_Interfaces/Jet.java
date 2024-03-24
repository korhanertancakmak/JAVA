package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_Abstraction_Interface.Course03_Interfaces;
//Part-15
/*
        I'll have that class implement the same 2 interfaces. Now, I think you'd agree with me that a bird and jet, have
    very little in common besides flying, and maybe having wings. But this is the beauty of interfaces. We can treat a
    bird and a jet similarly, from the client code's perspective. What they actually do, is dependent on how they implement
    the FlightEnabled methods, but the client can treat them as if they were the same. Let's copy and paste the last 4
    methods we had on bird into jet. And going to the main method, I'll call the inFlight method, and pass it a new instance
    of Jet.
*/
//End-Part-15
public class Jet implements FlightEnabled, Trackable {

    @Override
    public void takeOff() {
        System.out.println(getClass().getSimpleName() + " is taking off");
    }

    @Override
    public void land() {
        System.out.println(getClass().getSimpleName() + " is landing");
    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName() + " is flying");
    }

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    }

}
