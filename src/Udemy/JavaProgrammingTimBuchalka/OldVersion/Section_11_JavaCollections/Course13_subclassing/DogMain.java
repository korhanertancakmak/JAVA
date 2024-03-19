package CourseCodes.OldSections.Section_11_JavaCollections.Course13_subclassing;

public class DogMain {
    public static void main(String[] args) {
        Labrador rover = new Labrador("Rover");
        Dog rover2 = new Dog("Rover");

        System.out.println(rover2.equals(rover));    // must give true
        System.out.println(rover.equals(rover2));    // must give false. To remove this problem, we have to comment equal method on Labrador
                                                     // Or we can make equal method in Dog class final.
    }
}
