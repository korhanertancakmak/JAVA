package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Abstraction_Interface.Course07_Interface_Challenge;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Mappable> mappables = new ArrayList<>();

//Part-13
/*
        Here you can see, I'm coding to the interface, by using interface types in 2 different places. First, List is an
    interface type, so my variable mappables has an interface reference type. And second, I'm using Mappable as the List's
    type parameter, and not Building, in the <>. That means this List can contain anything that's Mappable, and not just
    buildings. Now, I'll add a few buildings, that are in the city of Sydney.
*/
//End-Part-13

        mappables.add(new Building("Sydney Town Hall", UsageType.GOVERNMENT));
        mappables.add(new Building("Sydney Opera House", UsageType.ENTERTAINMENT));
        mappables.add(new Building("Stadium Australia", UsageType.SPORTS));

//Part-14
/*
        This code demonstrates that we can add Building instances to a List, that's declared with a type parameter of
    Mappable. If we tried to add any object that didn't implement the Mappable interface, we'd get a compiler error. Now,
    I'll add some code to map it. Well not really, but just imagine that it could map it.
*/
//End-Part-14

    for (var m : mappables) {
        Mappable.mapIt(m);
    }

//Part-15
/*
        In this case, I'm just looping through the list, and calling the static method we created on the Mappable interface,
    passing it the current element in the list. I'll run this, and see if it all works.

        "properties": {"type": "POINT", "label": "Sydney Town Hall (GOVERNMENT)", "marker": "RED STAR"}
        "properties": {"type": "POINT", "label": "Sydney Opera House (ENTERTAINMENT)", "marker": "GREEN TRIANGLE"}
        "properties": {"type": "POINT", "label": "Stadium Australia (SPORTS)", "marker": "ORANGE PUSH_PIN"}

    And you can see that, for every one of the mappable features we created, we now have printed out the mapping features
    from the abstract method, the type, the label, and marker, for each. But it's common practice, to dump all attributes
    about a feature, or as many as are public knowledge, so I want to include the name and the usage attributes, even
    though these are part of the label. It could be the people using our data to make a map, may want to form the label
    in some other way, so we'll provide that information as well. To do this, we want to go to the building class, and
    override the toJSON method.
*/
//End-Part-15

        mappables.add(new UtilityLine("College St", UtilityType.FIBER_OPTIC));
        mappables.add(new UtilityLine("Olympic Blvd", UtilityType.WATER));

        for (var m : mappables) {
            Mappable.mapIt(m);
        }

//Part-23
/*
        I'll create a Utility line object by passing college st and Fiber optic to the constructor, and then I'll add it
    to the mappables list. I'll do the same thing for the second object, but this time I'll pass olympic blvd and water
    as the arguments. And running that,

        "properties": {"type": "POINT", "label": "Sydney Town Hall (GOVERNMENT)", "marker": "RED STAR", "name": "Sydney Town Hall", "usage": "GOVERNMENT"}
        "properties": {"type": "POINT", "label": "Sydney Opera House (ENTERTAINMENT)", "marker": "GREEN TRIANGLE", "name": "Sydney Opera House", "usage": "ENTERTAINMENT"}
        "properties": {"type": "POINT", "label": "Stadium Australia (SPORTS)", "marker": "ORANGE PUSH_PIN", "name": "Stadium Australia", "usage": "SPORTS"}
        "properties": {"type": "LINE", "label": "College St (FIBER_OPTIC)", "marker": "GREEN DOTTED", "name": "College St", "utility": "FIBER_OPTIC"}
        "properties": {"type": "LINE", "label": "Olympic Blvd (WATER)", "marker": "BLUE SOLID", "name": "Olympic Blvd", "utility": "WATER"}

    you can see this uses the same mechanism, to map lines on a map. Now, this is just a subset of what a json file might
    look like, but it's a pretty good start. If we really had a mapping program, we could generate the file with our entire
    list of Mappable things or features, and it would have enough information, to render things we care about on a map.
    I did not include map coordinates here, but future challenges will include that.
*/
//End-Part-23
    }
}
