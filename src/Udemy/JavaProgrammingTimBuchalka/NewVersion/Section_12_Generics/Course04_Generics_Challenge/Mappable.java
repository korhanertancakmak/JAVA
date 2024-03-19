package CourseCodes.NewSections.Section_12_Generics.Course04_Generics_Challenge;

import java.util.Arrays;

public interface Mappable {

//Part-1
/*
        This method takes a String, like one you'd copy from googleMaps, and it splits it by the comma in the string, returning
    an array of String. It gets latitude as the first element, making that a Double, using the valueOf() method on the Double
    wrapper, and it's using autoboxing automatically in this assignment. And the same with longitude, it's the second element
    in the split string. And then we just want to return those two points as a double array, which that last statement
    is doing.

        Now, I want to create 2 abstract classes in this Mappable.java source file.
*/
//End-Part-1

    void render();
    static double[] stringToLatLon(String location) {

        var splits = location.split(",");
        double lat = Double.valueOf(splits[0]);
        double lng = Double.valueOf(splits[1]);
        return new double[]{lat, lng};
    }
}


abstract class Point implements Mappable {

    private double[] location = new double[2];

//Part-2
/*
        Here, I'm creating an abstract class. The field I created, location, will hold 2 doubles, the latitude and longitude.
    But did you notice something interesting here? Even though I'm implementing Mappable, I'm not getting any errors on
    this class. I didn't implement Mappable's render method, so why didn't I get an error? Because this class is abstract,
    I don't actually have to implement the Mappable's abstract method. Any class that extends this abstract class, will
    be required to implement render(), if I don't do it here. But actually, I want to implement the render() method here,
    so I'll use IntelliJ's code generation tool to include an override for the render method on Mappable. Before I fill
    that out, I want to implement a private method called location, that just prints the location as a point.
*/
//End-Part-2

    private String location() {
        return Arrays.toString(location);
    }

//Part-3
/*
        This method just makes a call to Arrays.toString and passes it our location, to print out the array, or latitude
    and longitude in this case. Now, I'll use that in the render method. I'll print out the current instance, and then
    I'll make a call to the location method.
*/
//End-Part-3

    @Override
    public void render() {

        System.out.println("Render " + this + " as POINT (" + location() + ")");
    }

//Part-4
/*
        This just prints out "render", then the string representation of the class that's calling "this" method, followed
    by the location string in parentheses. I also want to include a constructor.
*/
//End-Part-4

    public Point(String location) {
        this.location = Mappable.stringToLatLon(location);
    }

//Part-5
/*
        This takes advantage of the static method on Mappable, that transforms a String representation of latitude and
    longitude, like we'd get from googleMaps, and returns a double array. And that's it for the Point class. The line class
    is going to be very similar. I'll just add this after Point.
*/
//End-Part-5
}

abstract class Line implements Mappable {

    private double[][] locations;

//Part-6
/*
        For a line, I'll have an array of arrays, so I'll have an array of lat and lon doubles. This means I'm using a
    2D array to represent all the points that make up a line on a map. Now I'll add a constructor, which will take a bunch
    of Strings as arguments, which are the lat and lon values, each represented as a String.
*/
//End-Part-6

    public Line(String... locations) {
        this.locations = new double[locations.length][];
        int index = 0;
        for (var l : locations) {
            this.locations[index++] = Mappable.stringToLatLon(l);
        }
    }

//Part-7
/*
        This method takes a variable argument of String(by "String... locations"), so we can have zero to many Strings,
    each representing the lat and lon of a point in the line. And we'll instantiate the "locations" field based on the
    number of Strings passed. And then I've got a local variable, index, which is just the position in the array. I'm
    looping through the Strings that get passed to the method. And I set locations using that index, to what we get back,
    from the Mappable method, stringToLatLon. So I pass a String to that, it gives me a double array, which is just 2 doubles,
    lat and lon, and I assign that to the next locations array element. Now I'll add the private method, locations, which
    is like the location method on point, except here, I'll use the deepToString method.
*/
//End-Part-7

    private String locations() {
        return Arrays.deepToString(locations);
    }

//Part-8
/*
        Finally, I want to add the render method. I'll select override. Then add my statement to print out the information.
    Once again, I'll print out the current instance and then make a call to the locations method.
*/
//End-Part-8

    @Override
    public void render() {

        System.out.println("Render " + this + " as LINE (" + locations() + ")");
    }

//Part-9
/*
        Now that we have the interface and abstract classes. Next, I'll create the concrete classes, Park and River, and
    the generic class that will let us create layers of these. I'll create a new class, Park, in the same package by extending
    the Point class.
*/
//End-Part-9
}