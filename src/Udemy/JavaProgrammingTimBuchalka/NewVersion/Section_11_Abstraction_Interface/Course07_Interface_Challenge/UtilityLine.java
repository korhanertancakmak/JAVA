package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Abstraction_Interface.Course07_Interface_Challenge;

//Part-17
/*
        I'll set up the UtilityType enum above the UtilityLine class definition.
*/
//End-Part-17

enum UtilityType {ELECTRICAL, FIBER_OPTIC, GAS, WATER}

//Part-18
/*
        This enum represents different types of utility lines, or pipelines, you might want to see mapped out for a town
    or city. I'll add name and type fields to the UtilityLine class. And generate the constructor for those 2 fields.
*/
//End-Part-18

public class UtilityLine implements Mappable {

    private String name;
    private UtilityType type;

    public UtilityLine(String name, UtilityType type) {
        this.name = name;
        this.type = type;
    }

//Part-19
/*
        And I'll make this class implement Mappable, so adding implements Mappable after the class name. And we know that's
    not going to compile, because of the 3 abstract methods of Mappable. And like I did for Building, I'll change the
    getLabel method on this class first. This is almost exactly what I did for the building.
*/
//End-Part-19

    @Override
    public String getLabel() {
        return name + " (" + type + ")";
    }

//Part-20
/*
        That returns the name of the utility and the type, for the label on the map. And for the shape method:
*/
//End-Part-20

    @Override
    public Geometry getShape() {
        return Geometry.LINE;
    }

//Part-20
/*
        And for a utility, we want a return a line. And now I'll make the marker that's returned be different, for the
    different utility types.
*/
//End-Part-20

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

//Part-21
/*
        And for now, I want to copy Building's toJSON method.
*/
//End-Part-21

    @Override
    public String toJSON() {
        return Mappable.super.toJSON() + """
               , "name": "%s", "utility": "%s"  """.formatted(name, type);
    }

//Part-22
/*
        Then I'll change usage in "" to utility in "", and instead of passing usage to the formatted method, I'll pass
    type there. And that's all we need for the UtilityLine class. Let's go set a couple of these up in the main method.
*/
//End-Part-22
}
