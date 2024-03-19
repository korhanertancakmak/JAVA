package CourseCodes.NewSections.Section_11_Abstraction_Interface.Course04_default_MethodsOn_Interfaces;

public class Truck implements Trackable {

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    }
}
