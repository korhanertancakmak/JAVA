package CourseCodes.OldSections.Section_07_InterfacesAndInnerAbstractClasses.Course05_AbstractClasses;

public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract void eat();
    public abstract void breathe();

    public String getName() {
        return name;
    }
}
