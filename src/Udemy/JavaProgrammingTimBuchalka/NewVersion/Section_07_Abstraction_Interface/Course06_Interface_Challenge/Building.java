package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_Abstraction_Interface.Course06_Interface_Challenge;

//Part-9
/*
        That's just a few of the usages a building might have. I want this enum, because each type of building may have
    a different color, or marker shape. I still need to make this class implement the Mappable interface. I'll add "implements
    Mappable" to Building.

        And of course, IntelliJ is telling us there's more work we need to do, because of this change. Let me implement
    those methods.
*/
//End-Part-9

enum UsageType {ENTERTAINMENT, GOVERNMENT, RESIDENTIAL, SPORTS}

public class Building implements Mappable {

    private String name;

    private UsageType usage;

//Part-7
/*
        Usage type is going to be an enum type, but I haven't yet created it. I'll do that in just a minute. First, I want
    to generate a constructor, with those 2 fields.
*/
//End-Part-7

    public Building(String name, UsageType usage) {
        this.name = name;
        this.usage = usage;
    }

//Part-8
/*
        I don't really need getters or setters. In real life, this class would be the class you really want mapped, that
    already exists in the application you were updating. I'll set up the UsageType enum in this file, right above the
    Building class definition.
*/
//End-Part-8

    @Override
    public String getLabel() {
        return name + " (" + usage + ")";
    }

//Part-10
/*
        And that gives me some default implementations, which I need to edit in all cases. Starting with the getLabel
    method.

                return name + " (" + usage + ")";

    This means the name of the building and what the building's used for, entertainment, government, and so on, and that's
    what would get printed as the map label.
*/
//End-Part-10

    @Override
    public Geometry getShape() {
        return Geometry.POINT;
    }

//Part-11
/*
        And next, I'll do the shape method. And for this, I want to return the enum constant for a POINT, so return GEOMETRY.POINT
    is all I need.
*/
//End-Part-11

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

//Part-12
/*
        And finally, for the getMarker Method, I want to set up icons, or map markers, determined by the building type,
    both for their color, and for their shape. Happily, we can do this pretty easily, with the enhanced switch expression,
    and using an enum variable.

        All of that simply returns a String, like RED STAR for a government building, or BLUE SQUARE for a residential
    building. So that's our building class done, and it implements a Mappable interface. Now, I'll go back to the main
    method, and set up a list of Mappable items.
*/
//End-Part-12

    @Override
    public String toJSON() {
        return Mappable.super.toJSON() + """
               , "name": "%s", "usage": "%s"  """.formatted(name, usage);
    }

//Part-16
/*
        And I want to edit "return Mappable.super.toJSON();" so that my result includes also name and usage. Running the
    code again,

    "properties": {"type": "POINT", "label": "Sydney Town Hall (GOVERNMENT)", "marker": "RED STAR", "name": "Sydney Town Hall", "usage": "GOVERNMENT"}
    "properties": {"type": "POINT", "label": "Sydney Opera House (ENTERTAINMENT)", "marker": "GREEN TRIANGLE", "name": "Sydney Opera House", "usage": "ENTERTAINMENT"}
    "properties": {"type": "POINT", "label": "Stadium Australia (SPORTS)", "marker": "ORANGE PUSH_PIN", "name": "Stadium Australia", "usage": "SPORTS"}

    you can see the output, which should be well-formed for the type of file we want to produce. Now let's create the
    UtilityLine class, so again new class, and I'll type in UtilityLine.
*/
//End-Part-16
}
