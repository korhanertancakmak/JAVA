package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course22_HashMapChallengePart2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Part-1
/*
        In the last lecture, I started setting up my Adventure Game. I included some nested types, an enum for compass
    directions, and a location record, as well as a static text block with my board location data. In this lecture, I'll
    complete this challenge as well as the bonus part, which is to allow customization. To do this, I want to get back
    to my constructors in the AdventureGame class, first the one with a single argument.
*/
//End-Part-1

public class AdventureGame {

    private static final String GAME_LOCATIONS = """
            road,at the end of the road, W: hill, E:well house,S:valley,N:forest
            hill,on top of hill with a view in all directions,N:forest, E:road
            well house,inside a well house for a small spring,W:road,N:lake,S:stream
            valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:stream
            forest,at the edge of a thick dark forest,S:road,E:lake
            lake,by an alpine lake surrounded by wildflowers,W:forest,S:well house
            stream,near a stream with a rocky bed,W:valley, N:well house
            """;

    private enum Compass {
        E, N, S, W;

        private static final String[] directions = {"East", "North", "South", "West"};

        public String getString() {
            return directions[this.ordinal()];
        }
    }

    private record Location(String description, Map<Compass, String> nextPlaces) {}

    private String lastPlace;
    private Map<String, Location> adventureMap = new HashMap<>();

    public AdventureGame() {
        this(null);
    }

//Part-2
/*
        I'm going to call my load locations method, passing the static string, GAME_LOCATIONS, to the loadLocations method.
    If I wasn't allowing customizations, I'd be done, but I do want to use custom locations, so I'll set up an if statement.
    If there are custom locations passed to this constructor, then I'll call load locations again, passing that data.
    This is the customization piece. A user can pass in their own locations. These locations will get loaded after the
    default locations. This means the user's location will replace the default location, if the key is already in the map.
    If customLocations isn't null, I'll load these customLocations. Finally, I want to go back to the no args constructor,
    and chain it to the other constructor, just passing null. Ok, so that's the code to load the game data. Let's go to
    the Main class's main method, and load up this data.
*/
//End-Part-2
    public AdventureGame(String customLocations) {
        loadLocations(GAME_LOCATIONS);
        if (customLocations != null) {
            loadLocations(customLocations);
        }

    }

    private void loadLocations(String data) {

        for (String s : data.split("\\R")) {
            String[] parts = s.split(",", 3);
            Arrays.asList(parts).replaceAll(String::trim);
            Map<Compass, String> nextPlaces = loadDirections(parts[2]);
            Location location = new Location(parts[1], nextPlaces);
            adventureMap.put(parts[0], location);
        }
        //adventureMap.forEach((k, v) -> System.out.printf("%s:%s%n", k, v));
    }

//Part-7
/*
        Before I call this play method, I'm going to go up to my load Locations method, and I'll comment out that last
    statement, so I'm not printing my map at the start. And that's it, for my adventure game. Ok, so I'll go back to the
    Main method,
*/
//End-Part-7

    private Map<Compass, String> loadDirections(String nextPlaces) {

        Map<Compass, String> directions = new HashMap<>();
        List<String> nextSteps = Arrays.asList(nextPlaces.split(","));

        nextSteps.replaceAll(String::trim);
        for (String nextPlace : nextSteps) {
            String[] splits = nextPlace.split(":");
            Compass compass = Compass.valueOf(splits[0].trim());
            String destination = splits[1].trim();
            directions.put(compass, destination);
        }
        return directions;
    }

//Part-4
/*
        Visit method will print out information about where the user's at, and where he or she can go next. This will be
    private, void, named visit, and take a location. I'll print that the player is standing in that location, with a
    description of the place. I'll also print that they can select the compass position or q for quit. In between those
    two statements, I need to include what options the player can pick from. I'll add the text, from here you can see.
    I'll follow that with a forEach call on the locations next places,  to loop through the options, or directions a player
    has. The lambda I want to use here, has two parameters, representing the key and value of the map, so just k and v
    there. In the lambda (meaning for each option in the map), I'll print a tab, a bullet point, and a formatted string,
    with the destination, the full compass string, East, North, South, and the abbreviation. That's the visit method.
*/
//End-Part-4

    private void visit(Location location) {

        System.out.printf("*** You're standing %s *** %n", location.description);
        System.out.println("\tFrom here, you can see:");

        location.nextPlaces.forEach((k, v) -> {
                    System.out.printf("\t• A %s to the %s (%S) %n", v, k.getString(), k);
                });
        System.out.print("Select Your Compass (Q to quit) >> ");
    }

//Part-5
/*
        In addition to that, I want a method to let the player move, based on the direction the player picked. I want this
    to be public, I'm going to use it from my client code. It'll have void for the return type, I'll call it move, and it
    takes a string, a direction. This method needs to first get the nextPlaces map, from the last place the player was at.
    It has to check if it's one of the four valid directions (in the enum), otherwise it prints invalid direction. If they
    did enter a valid direction, I want to use that as the key to the next places map. If I get null back, then the user
    picked a direction that's not in the next places map. Remember that not all locations, have a place to go, for all 4
    possible compass directions. If I get something back, that's the key to the next location, and I'll execute the method
    play, passing it this next location.
*/
//End-Part-5

    public void move(String direction) {

        var nextPlaces = adventureMap.get(lastPlace).nextPlaces;
        String nextPlace = null;
        if ("ENSW".contains(direction)) {
            nextPlace = nextPlaces.get(Compass.valueOf(direction));
            if (nextPlace != null) {
                play(nextPlace);
            }
        } else {
            System.out.println("!! Invalid direction, try again!!");
        }
    }

//Part-6
/*
        Finally, I've got to code the play method, which is the heart of the game. It's public, the client will call this
    method to start the play. This method has a void return type, is named play, and takes a string, the string location
    of the location to visit, or the location key. I'll use containsKey on the adventure map, passing it the location,
    the string value, to make sure it's a valid location, and that it's a key in my map. If it's not, I'll print out that
    it's not a valid location. Otherwise, I want to retrieve the location record, from the adventureMap, using the location
    key. I'll assign that to a local variable, which I'll call next. After that, I'll set the lastPlace field, to the
    location key. And I'll call my visit method, passing it the next location record.
*/
//End-Part-6

    public void play(String location) {

        if (adventureMap.containsKey(location)) {
            Location next = adventureMap.get(location);
            lastPlace = location;
            visit(next);
        } else {
            System.out.println(location + " is an invalid location");
        }
    }

}
