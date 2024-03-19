package Udemy.JavaProgrammingTimBuchalka.OldVersion.Section_11_JavaCollections.Course13_subclassing;

public class Labrador extends Dog {

    public Labrador(String name) {
        super(name);
    }

    /*@Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj instanceof Labrador) {
            String objName = ((Labrador) obj).getName();
            return this.getName().equals(objName);
        }

        return false;
    }*/
}
