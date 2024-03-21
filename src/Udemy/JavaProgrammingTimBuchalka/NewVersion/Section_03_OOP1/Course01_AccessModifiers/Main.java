package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_03_OOP1.Course01_AccessModifiers;

public class Main {

    // 5 private fields for a car class(we didn't change the name of class)
    private String make;
    private String model;
    private String color;
    private int doors;
    private boolean convertible;
    // These are the fields, because they're defined in the class's code block, or the body of the class, and not in a method.
    // When we create an object from this class, then the values we assign to these fields represent the state of the object.
    // Unlike local variables, class variables should have some type of access modifier declared for it.
    // If you don't declare one, Java declares the default one (package private), implicitly.
    // So, here, we've set the access modifier to be private in all cases, which we've said help us encapsulate this class.
    // We'll want to control access to these fields, and this starts by making them private.
    // Later we'll add the method to access them.
    // The other thing you might have noticed is that we're not actually assigning any values yet.
    // This is because, at this point, we don't know what these values might be, and they'll likely be different for each
    // instances anyway. If we were creating a real application, we'd likely have a lot more fields, but we'll keep this
    // simple to start with.
    // Let's add a method now that will print out this information about the car object.
    // We'll call this method describeCar and make the method public. This method is not static, because we're accessing
    // instance fields on the class. Methdos, unlike fields, will often be public, because we want to give users a way
    // to interact with the object.

    public void describeCar() {

        System.out.println(doors + "-Door " +
                color + " " +
                make + " " +
                model + " " +
                (convertible ? "Convertible" : ""));
    }

    // So we've created our first real template class, called Car, and we've set up some attributes on car. This feels like
    // a good place to end this course.
}
