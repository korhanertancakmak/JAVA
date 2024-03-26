# Interface Challenge

On the Mappable interface, I have one constant, JSON_PROPERTY, 
and three abstract methods, getLabel, getMarker, and getShape. 
I'm also including a default method, toJSON, which is going to return a String. 
This will be a combination of the geometry type, 
the icon and the labels printed appropriately for the JSON format. 
This is the attribute _name_ followed by a ":" 
followed by the attribute _value_. 
This method will get called from the static mapIt() method, 
which takes a Mappable argument.

![image05]()
      
Notice that the getShape method returns an enum type, Geometry, and the valid types on this enum are LINE, POINT,
and POLYGON, although I'll only show classes with Line and a Point examples. I'm going to be using a lot of enums in
this example, just to make the code a little more interesting, as well as simpler and easier to read. I'm also going
to use enums for Color, and the PointMarker and LineMarker types(or icons).

      I have 2 business classes to be mapped, Building and UtilityLine with a couple of fields, name and type or usage.
For building, I'll use an enum to describe the building type or how the building's used, for example if it's residential
or business, and so on. For the UtilityLine, I'll use another enum to describe the type of utility it is, like electrical
or water.

      The first thing, I'll do is, create the interface, and of course, I'm calling that Mappable.