package Udemy.JavaProgrammingTimBuchalka.Exercies.Challenge0033_PointDistanceCalculator;

public class Point {

    private int x;
    private int y;

    public Point() {
        this(0,0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distance() {
        return Math.sqrt((x * x) +(y * y));
    }

    public double distance(int a, int b) {
        return Math.sqrt(((x - a) * (x - a)) + ((y - b) * (y - b)));
    }

    public double distance(Point point) {
        return Math.sqrt(((x - point.x) * (x - point.x)) + ((y - point.y) * (y - point.y)));
    }
}
