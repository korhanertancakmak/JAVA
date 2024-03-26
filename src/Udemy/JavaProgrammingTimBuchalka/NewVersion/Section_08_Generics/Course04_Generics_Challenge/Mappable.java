package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course04_Generics_Challenge;

import java.util.Arrays;

public interface Mappable {

    void render();
    static double[] stringToLatLon(String location) {

        var splits = location.split(",");
        double lat = Double.parseDouble(splits[0]);
        double lng = Double.parseDouble(splits[1]);
        return new double[]{lat, lng};
    }
}


abstract class Point implements Mappable {

    private double[] location = new double[2];

    private String location() {
        return Arrays.toString(location);
    }

    @Override
    public void render() {

        System.out.println("Render " + this + " as POINT (" + location() + ")");
    }

    public Point(String location) {
        this.location = Mappable.stringToLatLon(location);
    }
}

abstract class Line implements Mappable {

    private final double[][] locations;

    public Line(String... locations) {
        this.locations = new double[locations.length][];
        int index = 0;
        for (var l : locations) {
            this.locations[index++] = Mappable.stringToLatLon(l);
        }
    }

    private String locations() {
        return Arrays.deepToString(locations);
    }

    @Override
    public void render() {

        System.out.println("Render " + this + " as LINE (" + locations() + ")");
    }
}