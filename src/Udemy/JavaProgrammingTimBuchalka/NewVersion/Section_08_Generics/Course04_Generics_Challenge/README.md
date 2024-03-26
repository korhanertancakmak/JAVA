# Generics Challenge

<div align="justify">

![image05](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_08_Generics/images/image05.png?raw=true)

This diagram shows how I plan to build this.
You can see my Mappable interface has the method render() on it,
and by default that's both public and abstract.
I've also added a static method that will take a String,
and split it into a double array, which will have the latitude and longitude values in them.
I've made two classes, Point and Line, abstract,
because I don't really want anyone to instantiate these classes.
Point has a location field, which is a double array, and will just have two doubles,
the latitude and longitude.
And I have a method that will print that array to a string, called location.
And then the render method is implemented.
And the same with abstract Line, except a line will have multiple latitude,
longitude pairs, and these will be represented as a two-dimensional array.
And then I'm going to have a Park class extend Point, with just a name field.
River will extend Line, and that has just a name as well, for simplicity.
Lastly, there's the Layer class, this is the generic class,
and it has a list of layerElements and methods to add one or more of these.
It also has the method, renderLayer.

Let's build this by starting with Mappable Interface,

```java  
public interface Mappable {
    void render();
    
    static double[] stringToLatLon(String location) {

        var splits = location.split(",");
        double lat = Double.parseDouble(splits[0]);
        double lng = Double.parseDouble(splits[1]);
        return new double[]{lat, lng};
    }
}
```

This method takes a String, like one you'd copy from googleMaps, 
and it splits it by the comma in the string, returning an array of String. 
It gets latitude as the first element, making that a Double, 
using the valueOf() method on the Double wrapper, 
and it's using autoboxing automatically in this assignment. 
And the same with longitude, it's the second element in the split string. 
And then we just want to return those two points as a double array, 
which that last statement is doing.

Now, I want to create two abstract classes in this _Mappable.java_ source file.

```java  
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
```

Here, I'm creating an abstract class. 
The field I created, location, will hold two doubles, the latitude and longitude.
But did you notice something interesting here? 
Even though I'm implementing Mappable, I'm not getting any errors in this class. 
I didn't implement Mappable's render method, so why didn't I get an error? 
Because this class is abstract, I don't have to implement the Mappable's abstract method. 
Any class that extends this abstract class will be required to implement **render()** 
if I don't do it here.
But actually, I want to implement the render() method here, 
so I'll use IntelliJ's code generation tool to include an override for the render method on Mappable. 
Before I fill that out, I want to implement a private method called _location_, 
that just prints the location as a point.

This method just makes a call to _Arrays.toString_ and passes it our location, 
to print out the array, or latitude and longitude in this case. 
Now, I'll use that in the render method. 
I'll print out the current instance, and then I'll make a call to the location method.

This just prints out **render**, then the string representation of the class 
that's calling _this_ method, followed by the location string in parentheses. 
I also want to include a constructor.

This takes advantage of the static method on Mappable, 
that transforms a String representation of latitude and longitude, 
like we'd get from googleMaps, and returns a double array. 
And that's it for the Point class. 
The line class is going to be very similar. 
I'll just add this after Point.

```java  
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
```

For a line, I'll have an array of arrays, so I'll have an array of lat and lon doubles. 
This means I'm using a 2D array to represent all the points that make up a line on a map. 
Now I'll add a constructor, which will take a bunch of Strings as arguments, 
which are the lat and lon values, each represented as a String.

This method takes a variable argument of String (by "String... locations"), 
so we can have zero to many Strings, each representing the lat and lon of a point in the line. 
And we'll instantiate the **locations** field based on the number of Strings passed. 
And then I've got a local variable, index, which is just the position in the array. 
I'm looping through the Strings that get passed to the method. 
And I set locations using that index, to what we get back, from the Mappable method, _stringToLatLon_. 
So I pass a String to that, it gives me a double array, which is just two doubles, 
lat and lon, and I assign that to the next locations array element. 
Now I'll add the private method, locations, which is like the location method on point, 
except here, I'll use the deepToString method.

Finally, I want to add the render method. 
I'll select override. 
Then add my statement to print out the information.
Once again, I'll print out the current instance and then make a call to the **locations'** method.

Now that we have the interface and abstract classes. 
Next, I'll create the concrete classes, Park and River, 
and the generic class that will let us create layers of these. 
I'll create a new class, Park, in the same package by extending the Point class.

```java  
public class Park extends Point{

    private final String name;

    public Park(String name, String location) {
        super(location);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " National Park";
    }
}
```

With this toString, I'll now replace super.toString, because I just want to return name, 
and concatenate National Park to that. 
If I had other fields, I could include them here.

And that's our first class that will render itself as a point on a map. 
Now I'll copy and paste this class, making it River, to create the first Line class 
by extending it Line, not Point.

```java  
public class River extends Line{

    private final String name;

    public River(String name, String... locations) {
        super(locations);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " River";
    }
}
```

And in the constructor, instead of passing a single location string, 
I want to allow multiple location strings using a variable argument(...), 
and call that location.
And I'll change the call to the super constructor, passing locations, not just location. 
Finally, the toString method will return the name, and River concatenated to that, 
in the description. 
And now I've built everything except the Generic class and the Main class, so I'll do that now.

First the new Layer class.
And the first thing I need to do is make this generic, so I'll add a type parameter, 
and you might remember from requirements, that this class should only take Mappable types. 
In <>, I'll add _T_ as the type, and extends Mappable after that.

```java  
public class Layer<T extends Mappable>{

    private final List<T> layerElements;

    public Layer(List<T> layerElements) {
        this.layerElements = layerElements;
    }

    public void addElements(T... elements) {
        layerElements.addAll(List.of(elements));
    }

    public void renderLayer() {

        for (T element :layerElements) {
            element.render();
        }
    }
}
```

This sets an upper bound, meaning anything that is Mappable, 
can use this Layer class.
It also means we can use methods on Mappable without casting. 
Now, I want to set up a List of Mappable elements. 
I want this to be private because I don't want any direct access to this list.

This is going to use the reference type List, typed by the parameter type of this generic class, T. 
Next, I'll generate a constructor, which includes layer Elements.
So that gives us a constructor that will take a list of elements, 
but I actually want that argument to be an array. 
I'll change that List<T> to T[]. 

```java  
public Layer(T[] layerElements) {
    this.layerElements = new ArrayList<T>(List.of(layerElements));
}
```

And I want to create the list as an ArrayList in the constructor, of type _T_.
This constructor lets the calling code pass an array, to create a new Layer. 
Now I want to include a method to add one or more Elements. 
If I use a variable argument, this can be a single method, which I'll call addElements. 
And the variable argument uses the _T_ parameter, and simply calls addAll, 
a method on the list interface.

This method takes a list of data, not a variable argument, so I can generate a List, 
with _List.Of_ method, with variable arguments. 
Next, I want a method called renderLayer.

The for loop uses the type parameter. 
Because I've defined my type as extending Mappable:

```java  
public class Layer<T extends MAppable> {}
```
            
I can call the render method on this type parameter, the loop element, 
as I'm showing there. 
And that's a generic layer class. 
For the main method, I'm going to set up three parks and four rivers, with their locations, 
from the tables in the description of the challenge.

```java  
public class Main {

    public static void main(String[] args) {

        var nationalUSParks = new Park[]{
                new Park("Yellowstone", "44.4882, -110.5916"),
                new Park("Grand Canyon", "36.1085, -112.0965"),
                new Park("Yosemite", "37.8855, -119.5360")
        };

        Layer<Park> parkLayer = new Layer<>(nationalUSParks);
        parkLayer.renderLayer();
    }
}
```

Those are the parks, I want to create myParkLayer next.
The Park layer should only take Parks, so I am very specific about the type, 
and it creates a new layer, passing the array of Parks I just set up. 
Then it calls renderLayer on the parkLayer. 
Running this code:

```java  
Render Yellowstone National Park as POINT ([44.4882, -110.5916])
Render Grand Canyon National Park as POINT ([36.1085, -112.0965])
Render Yosemite National Park as POINT ([37.8855, -119.536])
```

You can see the output, the three national parks, rendered as points, 
with their latitude and longitude given. 
And now I'll create the River Layer. 
This time, I'll start 2 rivers.

```java  
var majorUSRivers = new River[] {
        new River("Mississippi",
                "47.2160, -95.2348", "29.1566, -89.2495", "35.1556, -90.0659", "35.1556, -90.0659"),
        new River("Missouri",
                "45.9239, -111.4983", "38.8146, -89.1218")
};

Layer<River> riverLayer = new Layer<>(majorUSRivers);
```

That sets up the array of rivers, which I'm calling Major U.S. Rivers. 
I only set up 2 here because I want to use the other method I created on Layer, 
called addElements, but before I do that, let me create a new river layer.

Once I have a riverLayer, I can add more elements, just one or multiples, 
using the addElements method on that Layer class:

```java  
riverLayer.addElements(
                new River("Colorado",
                        "40.4708, -105.8286", "31.7811, -114.7724"),
                new River("Delaware",
                                  "42.2026, -75.00836", "39.4955, -75.5592"));

riverLayer.renderLayer();
```

Finally, I'll call render on this layer. And running this code,

```java  
... (same)
Render Mississippi River as LINE ([[47.216, -95.2348], [29.1566, -89.2495], [35.1556, -90.0659], [35.1556, -90.0659]])
Render Missouri River as LINE ([[45.9239, -111.4983], [38.8146, -89.1218]])
Render Colorado River as LINE ([[40.4708, -105.8286], [31.7811, -114.7724]])
Render Delaware River as LINE ([[42.2026, -75.00836], [39.4955, -75.5592]])
```
                    
We now can see the Layer, printing out information for each type in its List. 
So our Layer generic class never had any knowledge about the Point or Line classes, 
or the Park and River classes, but was able to make use of the Mappable class 
as its type parameter to support a container for these classes. 
The generic class is another powerful tool in our arsenal, to write robust, reusable, 
and extensible code in Java. 
It does get more complicated, but for the most part, what we've talked about so far covers 
what you'll probably be doing most with generic classes.
</div>