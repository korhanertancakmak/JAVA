package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_Abstraction_Interface.Course06_Interface_Challenge;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Mappable> mappable = new ArrayList<>();


        mappable.add(new Building("Sydney Town Hall", UsageType.GOVERNMENT));
        mappable.add(new Building("Sydney Opera House", UsageType.ENTERTAINMENT));
        mappable.add(new Building("Stadium Australia", UsageType.SPORTS));

        for (var m : mappable) {
            Mappable.mapIt(m);
        }


        mappable.add(new UtilityLine("College St", UtilityType.FIBER_OPTIC));
        mappable.add(new UtilityLine("Olympic Blvd", UtilityType.WATER));

        for (var m : mappable) {
            Mappable.mapIt(m);
        }
    }
}
