# Generics Challenge

<div align="justify">

![image05]()

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

</div>