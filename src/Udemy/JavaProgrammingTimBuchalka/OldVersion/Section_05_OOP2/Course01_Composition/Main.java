package CourseCodes.OldSections.Section_05_OOP2.Course01_Composition;

import CourseCodes.OldSections.Section_05_OOP2.Course01_Composition.BuildingAHouse.*;
import CourseCodes.OldSections.Section_05_OOP2.Course01_Composition.BuildingAPC.*;

public class Main {

    public static void main(String[] args) {

        // Building a PC
        Dimensions dimensions = new Dimensions(20, 20, 5);
	    Case theCase = new Case("220B", "Dell", "240", dimensions);
        Monitor theMonitor = new Monitor("27inch Beast", "Acer", 27, new Resolution(2540, 1440));
        Motherboard theMotherboard = new Motherboard("BJ-200", "Asus", 4, 6, "v2.44");
        PC thePC = new PC(theCase, theMonitor, theMotherboard);

        // PowerUp Option-1
        //thePC.getMonitor().drawPixelAt(1500,1200,"red");
        //thePC.getMotherboard().loadProgram("Windows 1.0");
        //thePC.getTheCase().pressPowerButton();

        // PowerUp Option-2
        thePC.powerUp();

/*                                              The Composition Exercise

        Create a single room of a house using composition. Think about the things that should be included in the room.
    Maybe physical parts of the house but furniture as well. Add at least one method to access an object via a getter and
    then that objects public method as you saw in the previous lecture, then add at least one method to hide the object
    e.g. not using a getter but to access the object used in composition within the main class like you saw in this lecture.
*/
        // Building an house
        Wall wall1 = new Wall("West");
        Wall wall2 = new Wall("East");
        Wall wall3 = new Wall("South");
        Wall wall4 = new Wall("North");

        Ceiling ceiling = new Ceiling(12, 55);
        Bed bed = new Bed("Modern", 4, 3, 2, 1);
        Lamp lamp = new Lamp("Classic", false, 75);
        Bedroom bedRoom = new Bedroom("Tims", wall1, wall2, wall3, wall4, ceiling,bed, lamp);
        bedRoom.makeBed();
        bedRoom.getLamp().turnOn();

    }
}
