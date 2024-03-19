package CourseCodes.NewSections.Section_11_Abstraction_Interface.Course05_PublicStaticMethodsOnInterface;

public class Truck implements Trackable {

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    }
}
