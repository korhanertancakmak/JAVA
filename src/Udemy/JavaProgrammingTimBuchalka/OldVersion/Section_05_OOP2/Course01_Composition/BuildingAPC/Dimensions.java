package Udemy.JavaProgrammingTimBuchalka.OldVersion.Section_05_OOP2.Course01_Composition.BuildingAPC;

public class Dimensions {
    private int width;
    private int height;
    private int depth;

    public Dimensions(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }
}
