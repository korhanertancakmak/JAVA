package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course04_Generics_Challenge;

public class Main {

    public static void main(String[] args) {

        var nationalUSParks = new Park[] {
                new Park("Yellowstone", "44.4882, -110.5916"),
                new Park("Grand Canyon", "36.1085, -112.0965"),
                new Park("Yosemite", "37.8855, -119.5360")
        };

//Part-18
/*
        Those are the parks, I want to create myParkLayer next.
*/
//End-Part-18

        Layer<Park> parkLayer = new Layer<>(nationalUSParks);
        parkLayer.renderLayer();

//Part-19
/*
        The Park layer should only take Parks, so I am very specific about the type, and it creates a new layer, passing
    the array of Parks I just set up. Then it calls renderLayer on the parkLayer. Running this code,

        Render Yellowstone National Park as POINT ([44.4882, -110.5916])
        Render Grand Canyon National Park as POINT ([36.1085, -112.0965])
        Render Yosemite National Park as POINT ([37.8855, -119.536])

    you can see the output, the 3 national parks, rendered as points, with their latitude and longitude given. And now I'll
    create the River Layer. This time, I'll start 2 rivers.
*/
//End-Part-19

        var majorUSRivers = new River[] {
                new River("Mississippi",
                        "47.2160, -95.2348", "29.1566, -89.2495", "35.1556, -90.0659", "35.1556, -90.0659"),
                new River("Missouri",
                        "45.9239, -111.4983", "38.8146, -89.1218")
        };

//Part-20
/*
        That sets up the array of rivers, which I'm calling Major U.S. Rivers. I only set up 2 here, because I want to use
    the other method I created on Layer, called addElements, but before I do that, let me create a new river layer.
*/
//End-Part-20

        Layer<River> riverLayer = new Layer<>(majorUSRivers);

//Part-21
/*
        Once I have a riverLayer, I can add more elements, just one or multiples, using the addElements method on that
    Layer class:
*/
//End-Part-21

        riverLayer.addElements(
                new River("Colorado",
                        "40.4708, -105.8286", "31.7811, -114.7724"),
                new River("Delaware",
                        "42.2026, -75.00836", "39.4955, -75.5592"));

        riverLayer.renderLayer();

//Part-22
/*
        Finally, I'll call render on this layer. And running this code,

                    ... (same)
                    Render Mississippi River as LINE ([[47.216, -95.2348], [29.1566, -89.2495], [35.1556, -90.0659], [35.1556, -90.0659]])
                    Render Missouri River as LINE ([[45.9239, -111.4983], [38.8146, -89.1218]])
                    Render Colorado River as LINE ([[40.4708, -105.8286], [31.7811, -114.7724]])
                    Render Delaware River as LINE ([[42.2026, -75.00836], [39.4955, -75.5592]])

    we now can see the Layer, printing out information for each type in its List. So our Layer generic class, never had
    any knowledge about the Point or Line classes, or the Park and River classes, but was able to make use of the Mappable
    class as its type parameter to support a container for these classes. The generic class is another powerful tool in
    our arsenal, to write robust, reusable, and extensible code in Java. It does get more complicated, but for the most
    part, what we've talked about so far, covers what you'll probably be doing most with generic classes.
*/
//End-Part-22

    }
}
