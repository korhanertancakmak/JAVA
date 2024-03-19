package CourseCodes.NewSections.Exercises0030To0049.Challenge0036_CylinderVolumeCalculator;

public class Cylinder extends Circle{

    private double height;


    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;

        if (height < 0) {
            this.height = 0;
        }
    }

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        return height * getArea();
    }
}
