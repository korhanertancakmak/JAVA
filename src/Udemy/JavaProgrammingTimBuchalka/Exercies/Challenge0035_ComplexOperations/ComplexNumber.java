package CourseCodes.NewSections.Exercises0030To0049.Challenge0035_ComplexOperations;

public class ComplexNumber {

    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void add(double r, double i) {
        this.real = getReal() + r;
        this.imaginary = getImaginary() + i;
    }

    public void add(ComplexNumber c) {
        this.real = c.real + getReal();
        this.imaginary = c.imaginary + getImaginary();
    }

    public void subtract(double r, double i) {
        this.real = getReal() - r;
        this.imaginary = getImaginary() - i;
    }

    public void subtract(ComplexNumber c) {
        this.real = getReal() - c.real;
        this.imaginary = getImaginary() - c.imaginary;
    }
}
