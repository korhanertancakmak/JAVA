package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_Abstraction_Interface.Course04_default_MethodsOn_Interfaces;

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


    //@Override                                                                 // overrode via part-7
    //public FlightStages transition(FlightStages stage) {
        //return FlightEnabled.super.transition(stage);                         // removed at the beginning of part-6
        //System.out.println(getClass().getSimpleName() + " transitioning");
        //return FlightStages.CRUISE;                                           // updated via part-10
    //}



    @Override
    public FlightStages transition(FlightStages stage) {
        System.out.println(getClass().getSimpleName() + " transitioning");
        return FlightEnabled.super.transition(stage);
    }

}
