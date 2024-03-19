package Udemy.JavaProgrammingTimBuchalka.Exercies.Challenge0032_WallArea;
public class Wall {

    private double width;
    private double height;

    public Wall() {
        this(0d, 0d);
    }

    public Wall(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        } else {
            this.width = 0d;
        }
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height > 0) {
            this.height = height;
        } else {
            this.height = 0d;
        }
    }

    public double getArea() {
        return width * height;
    }
}
