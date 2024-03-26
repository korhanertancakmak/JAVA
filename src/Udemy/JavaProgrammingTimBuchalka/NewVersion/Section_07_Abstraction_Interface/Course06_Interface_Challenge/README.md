# Interface Challenge

<div align="justify">
On the Mappable interface, I have one constant, JSON_PROPERTY, 
and three abstract methods, getLabel, getMarker, and getShape. 
I'm also including a default method, toJSON, which is going to return a String. 
This will be a combination of the geometry type, 
the icon and the labels printed appropriately for the JSON format. 
This is the attribute _name_ followed by a ":" 
followed by the attribute _value_. 
This method will get called from the static mapIt() method, 
which takes a Mappable argument.

![image05](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_07_Abstraction_Interface/images/image05.png?raw=true)
      
Notice that the getShape method returns an enum type, Geometry, 
and the valid types on this enum are LINE, POINT, and POLYGON, 
although I'll only show classes with Line and a Point examples. 
I'm going to be using a lot of enums in this example, 
just to make the code a little more interesting, as well as simpler and easier to read. 
I'm also going to use enums for Color, and the PointMarker and LineMarker types(or icons).

I have 2 business classes to be mapped, Building and UtilityLine with a couple of fields, 
name and type or usage. 
For building, I'll use an enum to describe the building type or how the building's used, 
for example, if it's residential or business, and so on. 
For the UtilityLine, I'll use another enum to describe the type of utility it is, 
like electrical or water.

The first thing I'll do is, create the interface, and I'm calling that Mappable.
Notice in the project panel, how an interface has a different icon from a class, 
a green circle with an _I_, representing an interface type. 
So first, my constant JSON_PROPERTY.

```java  
public interface Mappable {

    String JSON_PROPERTY = """
            "properties": { %s } """;
}
```

As I've mentioned previously, when you use a text block, 
meaning the triple """ feature, anything between 2 sets of """ gets printed, 
and that includes any double "" you specify. 
In """, I want my string that gets printed out to have properties in "", 
then a colon, then a set of curly brackets. 
Inside { }, I'm using _%_ which is a specifier for formatted string. 
I'm going to replace that with the actual properties string later. 
Because this is an interface underneath the covers, this field is public static and final. 
Because it's a string, this is really a constant, 
and I'm using the constant naming convention, all uppercase letters. 
This is a public constant, meaning anybody can get this value by calling Mappable.JSON_PROPERTY.

Ok, we want three abstract methods on this interface, 
one each to return a map label, a geometry type, and a map icon.

```java  
String getLabel();
Geometry getShape();
String getMarker();
```

This means I have three abstract methods. 
On an interface, I don't have to use the abstract modifiers. 
Methods are automatically public and abstract if there's no method body. 
The requirements said that we should force classes implementing this interface 
to have these methods, so that's what an abstract method does. 
A default method doesn't do that, only a true abstract method 
requires any implementing classes to declare that method on it 
when they implement the interface. 
And the only other thing to note here is 
that the shape method returns the enum type, Geometry.

Next, I'll create the default method, called toJSON. 
I'm again going to use a text block, so that I don't have to escape embedded "".

```java  
default String toJSON() {
    return """
            "type": "%s", "label": "%s", "marker": "%s" """.formatted(getShape(), getLabel(), getMarker());
}
```

This is the default properties String, printing out the type, label and marker. 
The property name is in "", so you can see type, label, marker is in "". 
And the string specifiers, the "%s" placeholders, are all also in "". 
What's notable here is that we can call the abstract methods, 
from this concrete method. 
At run time, Java resolves the method that's actually invoked. 
Even though on this interface, the methods like getLabel, 
have no implementation, we can assume at runtime, 
that what's invoking this default method, also has these other methods.

And finally, I'll add the static method mapIt() that takes a Mappable type as a parameter.

```java  
static void mapIt(Mappable mappable) {
    System.out.println(JSON_PROPERTY.formatted(mappable.toJSON()));
}
```

This static method is going to first format that JSON_PROPERTY static String. 
And the string that will get replaced will be the String we get back from the toJSON method. 
If we don't override the toJSON method on our building 
and utility line classes, then we'll get the label, type, and marker printed there, 
because it will just call that default method we just created. 
And you can see we can call that default method, on the Mappable type 
passed to this static helper method.

Ok, the next thing I want to do is set up my enums to support rendering 
different kinds of elements on a map. 
First, I want the enum for the three different geometry types, 
even though I said we only need to do 2 for this challenge. 
I'm putting this enum before the interface in this file.

```java  
enum Geometry {LINE, POINT, POLYGON}

enum Color {BLACK, BLUE, GREEN, ORANGE, RED}

enum PointMarker {CIRCLE, PUSH_PIN, STAR, SQUARE, TRIANGLE}

enum LineMarker {DASHED, DOTTED, SOLID}
```

As I've said, the thing we're mapping can be either a line, point, or a polygon. 
Next, I'm going to set up 3 other enums to support rendering an icon or marker on a map. 
I'll have colors, point shapes, and line types.

Up to now, we have our Mappable interface ready, and we've set up a bunch of enums 
to set up different types of data, which we'll use to drive 
what kind of marker gets drawn on the map. 
Now, I'll create a new class in the same package, and I want that to be named as Building.

```java  
public class Building implements Mappable {

    private String name;
    private UsageType usage;
}
```

Usage type is going to be an enum type, but I haven't yet created it. 
I'll do that in just a minute. 
First, I want to generate a constructor with those two fields.

```java  
public Building(String name, UsageType usage) {
    this.name = name;
    this.usage = usage;
}
```

I don't really need getters or setters. 
In real life, this class would be the class you really want mapped 
that already exists in the application you were updating. 
I'll set up the UsageType enum in this file, 
right above the Building class definition.

```java  
enum UsageType {ENTERTAINMENT, GOVERNMENT, RESIDENTIAL, SPORTS}
```

That's just a few of the usages a building might have. 
I want this enum because each type of building may have a different color, 
or marker shape. 
I still need to make this class implement the Mappable interface. 
I'll add _implements Mappable_ to Building.

And IntelliJ is telling us there's more work we need to do 
because of this change. 
Let me implement those methods.

```java  
@Override
public String getLabel() {
    return name + " (" + usage + ")";
}
```

And that gives me some default implementations, which I need to edit in all cases. 
Starting with the getLabel method.

```java  
return name + " (" + usage + ")";
```

This means the name of the building and what the building's used for, 
entertainment, government, and so on, and that's what would get printed as the map label.

```java  
@Override
public Geometry getShape() {
    return Geometry.POINT;
}
```

And next, I'll do the shape method. 
And for this, I want to return the enum constant for a POINT, 
so return GEOMETRY.POINT is all I need.

```java  
@Override
public String getMarker() {
    return switch (usage) {
        case ENTERTAINMENT -> Color.GREEN + " " + PointMarker.TRIANGLE;
        case GOVERNMENT -> Color.RED + " " + PointMarker.STAR;
        case RESIDENTIAL -> Color.BLUE + " " + PointMarker.SQUARE;
        case SPORTS -> Color.ORANGE + " " + PointMarker.PUSH_PIN;
        default -> Color.BLACK + " " + PointMarker.CIRCLE;
    };
}
```

And finally, for the getMarker Method, I want to set up icons, or map markers, 
determined by the building type, both for their color, and for their shape. 
Happily, we can do this pretty easily, with the enhanced switch expression,
and using an enum variable.

All of that simply returns a String, like RED STAR for a government building, 
or BLUE SQUARE for a residential building. 
So that's our building class done, and it implements a Mappable interface. 
Now, I'll go back to the main method, and set up a list of Mappable items.

```java  
List<Mappable> mappables = new ArrayList<>();
```

Here you can see, I'm coding to the interface,
by using interface types in two different places. 
First, List is an interface type, so my variable mappable 
has an interface reference type. 
And second, I'm using Mappable as the List's type parameter, 
and not Building, in the < >. 
That means this List can contain anything that's Mappable, 
and not just buildings. 
Now, I'll add a few buildings that are in the city of Sydney.

```java  
mappables.add(new Building("Sydney Town Hall", UsageType.GOVERNMENT));
mappables.add(new Building("Sydney Opera House", UsageType.ENTERTAINMENT));
mappables.add(new Building("Stadium Australia", UsageType.SPORTS));
```

This code demonstrates that we can add Building instances to a List 
that's declared with a type parameter of Mappable. 
If we tried to add any object that didn't implement the Mappable interface, 
we'd get a compiler error. 
Now, I'll add some code to map it. 
Well, not really, but just imagine that it could map it.

```java  
for (var m : mappables) {
    Mappable.mapIt(m);
}
```

In this case, I'm just looping through the list, 
and calling the static method we created on the Mappable interface,
passing it the current element in the list. 
I'll run this, and see if it all works.

```java  
"properties": {"type": "POINT", "label": "Sydney Town Hall (GOVERNMENT)", "marker": "RED STAR"}
"properties": {"type": "POINT", "label": "Sydney Opera House (ENTERTAINMENT)", "marker": "GREEN TRIANGLE"}
"properties": {"type": "POINT", "label": "Stadium Australia (SPORTS)", "marker": "ORANGE PUSH_PIN"}
```
        
And you can see that, for every one of the mappable features we created, 
we now have printed out the mapping features from the abstract method, 
the type, the label, and marker, for each. 
But it's common practice to dump all attributes about a feature, 
or as many as are public knowledge, 
so I want to include the name and the usage attributes, 
even though these are part of the label. 
It could be the people using our data to make a map,
may want to form the label in some other way, 
so we'll provide that information as well. 
To do this, we want to go to the building class and override the toJSON method.

```java  
@Override
public String toJSON() {
    return Mappable.super.toJSON() + """, "name": "%s", "usage": "%s"  """.formatted(name, usage);
}
```

And I want to edit _return Mappable.super.toJSON();_ so 
that my result includes also name and usage. 
Running the code again:

```java  
"properties": {"type": "POINT", "label": "Sydney Town Hall (GOVERNMENT)", "marker": "RED STAR", "name": "Sydney Town Hall", "usage": "GOVERNMENT"}
"properties": {"type": "POINT", "label": "Sydney Opera House (ENTERTAINMENT)", "marker": "GREEN TRIANGLE", "name": "Sydney Opera House", "usage": "ENTERTAINMENT"}
"properties": {"type": "POINT", "label": "Stadium Australia (SPORTS)", "marker": "ORANGE PUSH_PIN", "name": "Stadium Australia", "usage": "SPORTS"}
```
    
You can see the output, which should be well-formed for the type of file we want to produce. 
Now let's create the UtilityLine class, so again new class, and I'll type in UtilityLine.
I'll set up the UtilityType enum above the UtilityLine class definition.

```java  
enum UtilityType {ELECTRICAL, FIBER_OPTIC, GAS, WATER}
```

This enum represents different types of utility lines, or pipelines, 
you might want to see mapped out for a town or city. 
I'll add name and type fields to the UtilityLine class. 
And generate the constructor for those two fields.

```java  
public class UtilityLine implements Mappable {

    private String name;
    private UtilityType type;

    public UtilityLine(String name, UtilityType type) {
        this.name = name;
        this.type = type;
    }
}
```

And I'll make this class implement Mappable, 
so adding implements Mappable after the class name. 
And we know that it's not going to compile because of the three abstract methods of Mappable. 
And like I did for Building, I'll change the getLabel method on this class first. 
This is almost exactly what I did for the building.

```java  
@Override
public String getLabel() {
    return name + " (" + type + ")";
}
```

That returns the name of the utility and the type for the label on the map. 
And for the shape method:

```java  
@Override
public Geometry getShape() {
    return Geometry.LINE;
}
```

And for a utility, we want a return a line. 
And now I'll make the marker that's returned be different, 
for the different utility types.

```java  
@Override
public String getMarker() {
    return switch (type) {
        case ELECTRICAL -> Color.RED + " " + LineMarker.DASHED;
        case FIBER_OPTIC -> Color.GREEN + " " + LineMarker.DOTTED;
        case GAS -> Color.ORANGE + " " + LineMarker.SOLID;
        case WATER -> Color.BLUE + " " + LineMarker.SOLID;
        default -> Color.BLACK + " " + LineMarker.SOLID;
    };
}
```

And for now, I want to copy Building's toJSON method.

```java  
@Override
public String toJSON() {
    return Mappable.super.toJSON() + """, "name": "%s", "utility": "%s"  """.formatted(name, type);
}
```

Then I'll change usage in "" to utility in "", 
and instead of passing usage to the formatted method, 
I'll pass _type_ there. 
And that's all we need for the UtilityLine class. 
Let's go set a couple of these up in the main method.

```java  
mappables.add(new UtilityLine("College St", UtilityType.FIBER_OPTIC));
mappables.add(new UtilityLine("Olympic Blvd", UtilityType.WATER));

for (var m : mappables) {
    Mappable.mapIt(m);
}
```

I'll create a Utility line object by passing college st and Fiber optic to the constructor, 
and then I'll add it to the mappable list. 
I'll do the same thing for the second object, 
but this time I'll pass olympic blvd and water as the arguments. And running that,

```java  
"properties": {"type": "POINT", "label": "Sydney Town Hall (GOVERNMENT)", "marker": "RED STAR", "name": "Sydney Town Hall", "usage": "GOVERNMENT"}
"properties": {"type": "POINT", "label": "Sydney Opera House (ENTERTAINMENT)", "marker": "GREEN TRIANGLE", "name": "Sydney Opera House", "usage": "ENTERTAINMENT"}
"properties": {"type": "POINT", "label": "Stadium Australia (SPORTS)", "marker": "ORANGE PUSH_PIN", "name": "Stadium Australia", "usage": "SPORTS"}
"properties": {"type": "LINE", "label": "College St (FIBER_OPTIC)", "marker": "GREEN DOTTED", "name": "College St", "utility": "FIBER_OPTIC"}
"properties": {"type": "LINE", "label": "Olympic Blvd (WATER)", "marker": "BLUE SOLID", "name": "Olympic Blvd", "utility": "WATER"}
```
        
You can see this uses the same mechanism, to map lines on a map. 
Now, this is just a subset of what a json file might look like, 
but it's a pretty good start. 
If we really had a mapping program, we could generate 
the file with our entire list of Mappable things or features, 
and it would have enough information to render things we care about on a map. 
I did not include map coordinates here, but future challenges will include that.
</div>
