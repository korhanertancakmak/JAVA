package CourseCodes.NewSections.Section_11_Abstraction_Interface.Course07_Interface_Challenge;

//Part-6
/*
        As I've said, the thing we're mapping cn be either a line, point, or a polygon. Next, I'm going to set up 3 other
    enums, to support rendering an icon or marker on a map. I'll have colors, point shapes, and line types.

        Up to now, we have our Mappable interface ready, and we've set up a bunch of enums, to set up different types of
    data, which we'll use to drive what kind of marker gets drawn on the map. Now, I'll create a new class in the same
    package, and I want that to be named as Building.
*/
//End-Part-6

enum Geometry {LINE, POINT, POLYGON}

enum Color {BLACK, BLUE, GREEN, ORANGE, RED}

enum PointMarker {CIRCLE, PUSH_PIN, STAR, SQUARE, TRIANGLE}

enum LineMarker {DASHED, DOTTED, SOLID}

//Part-1
/*
        Notice in the project panel, how an interface has a different icon from a class, a green circle with an "I", representing
    an interface type. So first, my constant JSON_PROPERTY.
*/
//End-Part-1
public interface Mappable {

    String JSON_PROPERTY = """
            "properties": {%s} """;

//Part-2
/*
        As I've mentioned previously, when you use a text block, meaning the triple """ feature, anything between 2 sets
    of """ gets printed, and that includes any double "" you specify. In """, I want my string that gets printed out to
    have properties in "", then a colon, then a set of curly brackets. Inside {}, I'm using "%" which is a specifier for
    formatted string. I'm going to replace that with the actual properties string later. Because this is an interface,
    underneath the covers, this field is public static and final. Because it's a string, this is really a constant, and
    I'm using the constant naming convention, all uppercase letters. This is a public constant, meaning anybody can get
    this value by calling Mappable.JSON_PROPERTY.

        Ok, we want 3 abstract methods on this interface, one each to return a map label, a geometry type, and a map icon.
*/
//End-Part-2

    String getLabel();
    Geometry getShape();
    String getMarker();

//Part-3
/*
        This means I have 3 abstract methods. On an interface, I don't have to use the abstract modifiers. Methods are
    automatically public and abstract, if there's no method body. The requirements said that we should force classes
    implementing this interface, to have these methods, so that's what an abstract method does. A default method doesn't
    do that, only a true abstract method requires any implementing classes, to declare that method on it, when they
    implement the interface. And the only other thing to note here is, that the shape method returns the enum type, Geometry.

        Next, I'll create the default method, called toJSON. I'm again going to use a text block, so that I don't have to
    escape embedded "".
*/
//End-Part-3

    default String toJSON() {

        return """
                "type": "%s", "label": "%s", "marker": "%s" """.formatted(getShape(), getLabel(), getMarker());
    }

//Part-4
/*
        This is the default properties String, printing out the type, label and marker. The property name is in "", so
    you can see type, label, marker are in "". And the string specifiers, the "%s" placeholders, are all also in "". What's
    notable here, is that we can call the abstract methods, from this concrete method. At run time, Java resolves the method
    that's actually invoked. Even though on this interface, the methods like getLabel, have no implementation, we can
    assume at runtime, that what's invoking this default method, also has these other methods.

        And finally, I'll add the static method mapIt(), that takes a Mappable type as a parameter.
*/
//End-Part-4

    static void mapIt(Mappable mappable) {
        System.out.println(JSON_PROPERTY.formatted(mappable.toJSON()));
    }

//Part-5
/*
        This static method, is going to first format that JSON_PROPERTY static String. And the string that will get replaced
    will be the String we get back from the toJSON method. If we don't override the toJSON method on our building and
    utility line classes, then we'll get the label, type, and marker printed there, because it will just call that default
    method we just created. And you can see we can call that default method, on the Mappable type, that's passed to this
    static helper method.

        Ok, the next thing I want to do is set up my enums to support rendering different kinds of elements on a map. First,
    I want the enum for the three different geometry types, even though I said we only need to do 2 for this challenge.
    I'm putting this enum before the interface, in this file.
*/
//End-Part-5
}
