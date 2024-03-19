package CourseCodes.NewSections.Section_15_Collections.Course21_HashMapChallengePart1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Part-1
/*                                          The Adventure Game

        In this challenge, you'll be creating a text based game, using a HashMap. This game will be loosely based on the
    original "Colossal Cave Adventure", which was one of the first adventure games, that came out years and years ago. Feel
    free to google that, if you want to find out more information about this game.

        The game starts, with the user standing in a road, in the center of the map. North would take the user to the Forest
    and South, would take the user to the Valley, East would be the well house, and West would be the Hill. For simplicity,
    we'll just support North, West, South and East compass directions, so each location will only have up to at most, four
    directions to choose from. In other words, we won't support diagonal moves, such as moving Northeast, although you're
    welcome to code your game that way. You'll want to use two HashMaps in this challenge, one for the board locations,
    and one for the next places data.

            ______________________________________________________________________________________________
            | key(String)       | value(Location)                                                        |
            |___________________|________________________________________________________________________|
            | String            | Description: String                         | Next Places: Map         |
            |___________________|_____________________________________________|__________________________|
            |                   |                                             | key       | value        |
            |                   |                                             |___________|______________|
            | "stream"          | "near a stream with a rocky bed"            | W         | "valley"     |
            |                   |                                             |___________|______________|
            |                   |                                             | W         | "well house" |
            |___________________|_____________________________________________|___________|______________|
            |                   |                                             | key       | value        |
            |                   |                                             |___________|______________|
            | "well house"      | "inside a well house for a small spring"    | W         | "road"       |
            |                   |                                             |___________|______________|
            |                   |                                             | N         | "lake"       |
            |                   |                                             |___________|______________|
            |                   |                                             | S         | "stream"     |
            |___________________|_____________________________________________|___________|______________|

    This table shows one way to structure your data, but feel free to come up with your own configuration, if you'd prefer
    to. Use a HashMap for the game board locations, keyed on a short descriptor, road or stream, for example, or some
    other key of your choice. Your Location class should have a location description, and could also have a field, a
    HashMap, to store next places to go from there. The next places Map should be keyed by the compass direction, and
    its value should be the key to the next location. As an example, the stream location will have a next places map,
    which has two entries. The first entry will have a key of W or West, and the value will be valley (or whatever key
    you would use, to get the valley data from the board locations map).

                *** You're standing inside a well house for a small sprint ***
                    From here, you can see:
                    * A road to the West (W)
                    * A lake to the North (N)
                    * A stream to the South (S)
                Select Your Compass Direction (Q to quit) >>

        Use the console to describe to the player what their current location is, (starting at the road), and show them
    what options they have to go from here. Feel free to edit the descriptions, and make them as detailed as you want.
    Prompt the player to enter the direction they want to go next. You can accept the whole word, east, or just the letter
    e. You can let the user know what lies ahead, by giving them a hint about the next place, or not. For example, you
    could just tell them they could go either East or West, and offer no hints. Continue to play until the user quits, Q
    for quit for example. I've included some starting data as a csv file (or comma-separated file), if you want to use
    that, you can cut and paste it for now, and set it up in a text block for example. or you can set up your own set of
    data from scratch. I'm showing a sample of what the console display might look like above. This is a suggestion only
    to help you understand how the game might work.

                                            The Adventure Game Bonus

        A bonus part of the challenge is to allow customizations to the board locations, as well as the next place directions.
    For example, your player should be able to add custom board locations, to include more places, or change the descriptions
    or destinations available.
*/
//End-Part-1

public class AdventureGame {

//Part-2
/*
        The first thing I want to do is create a class, called AdventureGame. I'll set up a text block that contains all
    my board location data. I'll make that private static final, and a String, with a name, GAME_LOCATIONS all in caps,
    because this is a constant. I'll create that constant first, with the triple quotes, leaving an empty line between.
    I'll paste the text, I want to make sure the start of my strings line up with the closing triple quotes, so there
    aren't any unnecessary leading tabs or indents.
*/
//End-Part-2

    private static final String GAME_LOCATIONS = """
            road,at the end of the road, W: hill, E:well house,S:valley,N:forest
            hill,on top of hill with a view in all directions,N:forest, E:road
            well house,inside a well house for a small spring,W:road,N:lake,S:stream
            valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:stream
            forest,at the edge of a thick dark forest,S:road,E:lake
            lake,by an alpine lake surrounded by wildflowers,W:forest,S:well house
            stream,near a stream with a rocky bed,W:valley, N:well house
            """;

//Part-3
/*
        The next thing I want is an enum, that will have my compass points, so E, N, S, and W. I'm going to add a method
    in this enum, so I'll end my list of constants with a semi colon and a new line. I want to add a private static final
    array of Strings, and I'll call that directions, which will just contain East, North, South, West, in the same order
    of the corresponding constants. I don't want to override toString here, because I want the compass points, to be
    printed as single characters as well. Now, I'll add a get String method, that takes no parameters, and returns a String.
    I'll return the value I get from the directions array, when I index it by the ordinal value of the current enum. The
    ordinal method on enum gives me it's placement in the constants list. If I have S, ordinal will be 2, and using that
    to get the element from directions will give me the string South.
*/
//End-Part-3

    private enum Compass {
        E, N, S, W;

        private static final String[] directions = {"East", "North", "South", "West"};

        public String getString() {
            return directions[this.ordinal()];
        }
    }

//Part-4
/*
        The next type I want is a Location record, which I'll make as a nested type. This will have a description, and a
    map field called nextPlaces. This map will be keyed by the Compass direction, and will hold the key to the next location,
    so a string. Ok, so that's my location, and now I can focus on the game itself.
*/
//End-Part-4

    private record Location(String description, Map<Compass, String> nextPlaces) {}

//Part-5
/*
        I want two fields on my Adventure Game. First, I want to keep track of my last location, so I want a String called
    lastPlace. And then I need my hashmap for the board locations. My variable type is Map, and my type arguments are
    String for the key, and Location for the value. I'll name it adventure map and set that to a new hashmap. I want to
    generate two constructors, the first will be a no args constructor. Leaving that as is, for a minute, I'll generate
    the second one, and I'll pick lastPlace from the fields. Now, this last constructor, isn't really going to accept
    the lastPlace, because I want to change that to customLocations. I want players to have the ability to customize the
    map, so they can pass a well formatted string to this constructor. I'll remove the current statement in this constructor.
    Before I can complete these constructors, I need some code that can parse my board location data, or any custom location
    data that's in the same format. For that,
*/
//End-Part-5

    private String lastPlace;
    private Map<String, Location> adventureMap = new HashMap<>();

    public AdventureGame() {
    }

    public AdventureGame(String customLocations) {

    }

//Part-6
/*
        I'm going to create a private method, so private, void, load locations, and that takes a String I'll call data.
    My data's set up as a text block, but this could also be data coming in from a file, or whatever. This data will have
    line breaks after each data entry, so I want to split this data by a line break. I want a for loop, that will iterate
    over each line, so on the right of the colon in this loop statement, I'm going to split the string by two backslashes
    and an R. This is a way to split up my text block by each line. This escape sequence, \\R, was introduced in java 8,
    and matches any Unicode line-break on any operating system. If you used a \n, that's good too, but this is just a bit
    more robust. After I get each line, I want to split it into 3 parts, the first and second comma delimited parts, and
    then anything else after that as a single string. To do this, I'm going to use the overloaded version of the split
    method, passing the comma as the delimiter, and 3 which is the total number of parts I want this record to be split
    into. The last part, the third part, will contain all the data after the second comma, and it will include commas if
    any, which this will, since it has all the next places data. After this, I'll use a list, backed by an array, chaining
    the replace all method to that, calling the String's trim method reference. This is a quick way to remove leading and
    trailing spaces from my array of strings. To create each Location record, I first need to create the nextPlaces map.
    That map will be keyed by one of the Compass enum constants, and the value will be a string. I'll assign that the
    result of a method, loadDirections, and pass that the last string in my string parts array, which has all the direction
    data in it. I haven't created this method yet, so that's not going to compile. I'll create this method shortly. I'll
    create a new instance of the Location record, passing it the second delimited string, which had the description, and
    the map, nextPlaces. And I'll put that new location into my adventureMap. I am purposely using put, and not put if
    ABSENT. This will make the customization piece easier. Finally, at the end of this method, I'll print out the adventure
    map, the key and value for now, so I can test it. Now I need to code the load Directions method.
*/
//End-Part-6

    private void loadLocations(String data) {

        for (String s : data.split("\\R")) {
            String[] parts = s.split(",", 3);
            Arrays.asList(parts).replaceAll(String::trim);
            Map<Compass, String> nextPlaces = loadDirections(parts[2]);
            Location location = new Location(parts[1], nextPlaces);
            adventureMap.put(parts[0], location);
        }
        adventureMap.forEach((k, v) -> System.out.printf("%s:%s%n", k, v));
    }

//Part-7
/*
        That's private and returns a map, and has Compass as a key, and String as a value. It'll take one argument, a
    string, and I'm going to call that next places. I want to set up a local variable, that has the same type, as the
    return type of this method. I'll call that directions, and set it to a new HashMap. I'm going to split the method
    argument by commas again. This was everything after the second comma, on each line from my input data, so it's got
    a comma delimited list of compass directions, and next places to go. I can pass that to the as List method on Arrays,
    and assign it to a local variable, next Steps. I'll again call the replace all method on that, with string's trim
    method as I did in the other method, to remove leading and trailing spaces. Now that I've cleaned up my data a bit,
    I want to loop through each delimited string. Each String, from my data, has a compass direction, followed by a colon,
    followed by a string, which is the short descriptor of the next place. The first thing I'll do in this loop is again
    split the current string, and this time by the colon. I'll trim the first part, and pass it to the value of method
    on Compass, which will return a matching compass constant. If you were building this in a real environment, you'd want
    to make sure the data provided was really clean, meaning the compass direction is actually in your enum, but I'll keep
    this simple to save a bit of time. The destination will be after the colon, so that's the second element in the splits
    array, and I'll trim that. Finally, I'll put this data into the map, the compass as the key, and the destination as
    the value. These directions are all the possible ways, a player can pick from, to move to another location.
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
}
