package CourseCodes.NewSections.Exercises0030To0049.Challenge0036_CylinderVolumeCalculator;

public class Circle {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;

        if (radius < 0) {
            this.radius = 0;
        }
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return (radius < 0) ? 0 : radius * radius * Math.PI;
    }
}
