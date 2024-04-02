# [HashMap Challenge]()
<div align="justify">

The first thing I want to do is create a class, called **AdventureGame**. 
I'll set up a text block that contains all my board location data. 
I'll make that private static final, and a String, with a name, 
**GAME_LOCATIONS** all in caps, because this is a constant. 
I'll create that constant first, with the triple quotes, 
leaving an empty line between.
I'll paste the text, I want to make sure the start of my strings lines 
up with the closing triple quotes, so there aren't any unnecessary 
leading tabs or indents.

```java  
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
    }

    public AdventureGame(String customLocations) {

    }
}
```

The next thing I want is an enum, that will have my compass points, 
so _E_, _N_, _S_, and **W**. 
I'm going to add a method in this enum, 
so I'll end my list of constants with a semicolon and a new line. 
I want to add a private static final array of Strings, 
and I'll call that _directions_, which will just contain _East_, 
_North_, _South_, _West_, in the same order of the corresponding constants. 
I don't want to override _toString_ here, 
because I want the compass points to be printed as single characters as well.
Now, I'll add a get _String_ method, that takes no parameters, 
and returns a String. 
I'll return the value I get from the _directions_ array, 
when I index it by the ordinal value of the current enum. 
The ordinal method on enum gives me its placement in the _constants_ list. 
If I have _S_, ordinal will be _2_, and using that 
to get the element from directions will give me the string _South_.
The next type I want is a _Location_ record, 
which I'll make as a nested type. 
This will have a description, and a map field called _nextPlaces_. 
This map will be keyed by the _Compass_ direction, 
and will hold the key to the next location, so a string. 
Ok, so that's my location, and now I can focus on the game itself.
I want two fields on my **Adventure Game**. 
First, I want to keep track of my last location, 
so I want a String called _lastPlace_. 
And then I need my hashmap for the board locations. 
My variable type is **Map**, and my type arguments are String for the key, 
and Location for the value. 
I'll name it adventure map and set that to a new hashmap. 
I want to generate two constructors, 
the first will be a no args constructor. 
Leaving that as it is, for a minute, 
I'll generate the second one, and I'll pick _lastPlace_ from the fields.
Now, this last constructor isn't really going to accept the _lastPlace_, 
because I want to change that to _customLocations_. 
I want players to be able to customize the map, 
so they can pass a well-formatted string to this constructor. 
I'll remove the current statement in this constructor.
Before I can complete these constructors, 
I need some code that can parse my board location data, 
or any custom location data in the same format. For that,

```java  
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
```

I'm going to create a private method, so private, 
void, _loadLocations_, and that takes a String I'll call data.
My data's set up as a text block, 
but this could also be data coming in from a file, or whatever. 
This data will have line breaks after each data entry, 
so I want to split this data by a line break. 
I want a for loop that will iterate over each line, 
so on the right of the colon in this loop statement, 
I'm going to split the string by two backslashes and an _R_. 
This is a way to split up my text block by each line. 
This escape sequence, **\\R**, was introduced in java 8, 
and matches any Unicode line-break on any operating system.
If you used a **\n**, that's good too, but this is just a bit more robust. 
After I get each line, I want to split it into three parts, 
the first and second comma-delimited parts, 
and then anything else after that as a single string. 
To do this, I'm going to use the overloaded version of the split method, 
passing the comma as the delimiter, 
and 3 which is the total number of parts 
I want this record to be split into. 
The last part, the third part, will contain all the data 
after the second comma, and it will include commas if any, 
which this will, since it has all the next places data. 
After this, I'll use a list, backed by an array, 
chaining the replace all method to that, 
calling the String's _trim_ method reference. 
This is a quick way to remove leading and 
trailing spaces from my array of strings. 
To create each Location record, I first need to create the _nextPlaces_ map.
That map will be keyed by one of the **Compass** enum constants, 
and the value will be a string. 
I'll assign that the result of a method, _loadDirections_, 
and pass that the last string in my string parts array, 
which has all the direction data in it. 
I haven't created this method yet, so that's not going to compile. 
I'll create this method shortly. 
I'll create a new instance of the Location record, 
passing it the second delimited string, 
which had the description, and the map, _nextPlaces_. 
And I'll put that new location into my _adventureMap_. 
I am purposely using _put_, and not _putIfAbsent_. 
This will make the customization piece easier.
Finally, at the end of this method, I'll print out the adventure map, 
the key and value for now, so I can test it. 
Now I need to code the _loadDirections_ method.

```java  
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
```

That's private and returns a map, and has Compass as a key, 
and String as a value. 
It'll take one argument, a string, 
and I'm going to call that _nextPlaces_. 
I want to set up a local variable that has the same type 
as the return type of this method. 
I'll call that _directions_, and set it to a new **HashMap**. 
I'm going to split the method argument by commas again. 
This was everything after the second comma, on each line from my input data, 
so it's got a comma-delimited list of compass directions, 
and next places to go. 
I can pass that to the as List method on Arrays,
and assign it to a local variable, _nextSteps_. 
I'll again call the _replaceAll_ method on that, 
with string's _trim_ method as I did in the other method, 
to remove leading and trailing spaces. 
Now that I've cleaned up my data a bit,
I want to loop through each delimited string. 
Each String, from my data, has a compass direction, 
followed by a colon, followed by a string, 
which is the short descriptor of the next place. 
The first thing I'll do in this loop is again
split the current string, and this time by the colon. 
I'll trim the first part, and pass it to the value of method on **Compass**, 
which will return a matching compass constant. 
If you were building this in a real environment,
you'd want to make sure the data provided was spotless,
meaning the compass direction is actually in your enum,
but I'll keep this simple to save a bit of time. 
The destination will be after the colon, 
so that's the second element in the _splits_ array, 
nd I'll trim that. 
Finally, I'll put this data into the map, 
the compass as the key, and the destination as the value. 
These directions are all the possible ways a player can pick from, 
to move to another location.

```java  
public AdventureGame() {
    this(null);
}

public AdventureGame(String customLocations) {
    loadLocations(GAME_LOCATIONS);
    if (customLocations != null) {
        loadLocations(customLocations);
    }

}
```

Now, I'm going to call my _loadLocations_ method, 
passing the static string, _GAME_LOCATIONS_, to the _loadLocations_ method.
If I wasn't allowing customizations, I'd be done, 
but I do want to use custom locations, so I'll set up an if statement.
If there are custom locations passed to this constructor, 
then I'll call _loadLocations_ again, passing that data.
This is the customization piece. 
A user can pass in their own locations. 
These locations will get loaded after the default locations. 
This means the user's location will replace the default location 
if the key is already in the map.
If _customLocations_ isn't null, I'll load these _customLocations_.
Finally, I want to go back to the no args constructor,
and chain it to the other constructor, just passing _null_. 
Ok, so that's the code to load the game data. 
Let's go to the **Main** class's _main_ method, and load up this data.

```java  
public class Main {
    public static void main(String[] args) {
        
        AdventureGame game = new AdventureGame();
    }
}
```

I'll create an **AdventureGame** called game. 
That's a new **AdventureGame**, with nothing passed to the constructor, 
so no customizations. 
That's all I have to do to test this part of the code, 
because remember I included a print statement
after the locations were added, in the _loadLocations_ method. 
Running that code:

```html  
hill:Location[description=on top of hill with a view in all directions, nextPlaces={N=forest, E=road}]
well house:Location[description=inside a well house for a small spring, nextPlaces={N=lake, W=road, S=stream}]
forest:Location[description=at the edge of a thick dark forest, nextPlaces={E=lake, S=road}]
road:Location[description=at the end of the road, nextPlaces={N=forest, E=well house, W=hill, S=valley}]
stream:Location[description=near a stream with a rocky bed, nextPlaces={N=well house, W=valley}]
valley:Location[description=in a forest valley beside a tumbling stream, nextPlaces={N=road, E=stream, W=hill}]
lake:Location[description=by an alpine lake surrounded by wildflowers, nextPlaces={W=forest, S=well house}]
```
                
You can see my board locations map. 
First, I've printed out the key, a one or two-word description, 
then I've got the Location object, which has a description, 
and a _nextPlaces_ nested **Map**. 
And you can see for _hill_, for example, a player can go _east_ to the road, 
or _north_ to the forest. 
That's a good start, but now it's time to implement the code, 
to actually play this game. 
To start, I want a method in the AdventureGame class:

```java  
private void visit(Location location) {

    System.out.printf("*** You're standing %s *** %n", location.description);
    System.out.println("\tFrom here, you can see:");

    location.nextPlaces.forEach((k, v) -> {
        System.out.printf("\t• A %s to the %s (%S) %n", v, k.getString(), k);
    });
    System.out.print("Select Your Compass (Q to quit) >> ");
}
```

_Visit_ method will print out information about where the user's at, 
and where he or she can go next. 
This will be private, void, named _visit_, and take a location. 
I'll print that the player is standing in that location, 
with a description of the place. 
I'll also print that they can select the compass position or _q_ for quit.
In between those two statements, I need to include what options the player can pick from. 
I'll add the text, from here you can see. 
I'll follow that with a _forEach_ call on the locations next places, 
to loop through the options, or directions a player has. 
The lambda I want to use here, has two parameters, representing the key
and value of the map, so just _k_ and _v_ there. 
In the lambda (meaning for each option in the map), I'll print a tab,
a bullet point, and a formatted string, with the destination, 
the full compass string, _East_, _North_, _South_, and the abbreviation. 
That's the _visit_ method.

```java  
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
```

In addition to that, I want a method to let the player move, 
based on the direction the player picked. 
I want this to be public, I'm going to use it from my client code. 
It'll have void for the return type, I'll call it _move_, 
and it takes a string, a direction. 
This method needs to first get the _nextPlaces_ map, 
from the last place the player was at. 
It has to check if it's one of the four valid directions (in the enum), 
otherwise it prints an invalid direction. 
If they did enter a valid direction, I want to use 
that as the key to the next places map. 
If I get null back, then the user picked a direction
that's not in the next places map. 
Remember that not all locations have a place to go 
for all four possible compass directions. 
If I get something back, that's the key to the next location,
and I'll execute the method play, passing it this next location.

```java  
public void play(String location) {

    if (adventureMap.containsKey(location)) {
        Location next = adventureMap.get(location);
        lastPlace = location;
        visit(next);
    } else {
        System.out.println(location + " is an invalid location");
    }
}
```

Finally, I've got to code the _play_ method, 
which is the heart of the game. 
It's public, the client will call this method to start the play. 
This method has a void return type, is named _play_, 
and takes a string, the string location of the location to visit,
or the location key. 
I'll use _containsKey_ on the _adventureMap_, 
passing it the _location_, the string value, to make sure it's a valid location, 
and that it's a key in my map. 
If it's not, I'll print out that it's not a valid location. 
Otherwise, I want to retrieve the location record, 
from the _adventureMap_, using the location key. 
I'll assign that to a local variable, which I'll call next. 
After that, I'll set the _lastPlace_ field to the location key. 
And I'll call my visit method, passing it the next location record.
Before I call this _play_ method, I'm going to go up to my loadLocations method:

```java  
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
```

I'll comment out that last statement, 
so I'm not printing my map at the start. 
And that's it, for my adventure game. 
Ok, so I'll go back to the _main_ method,
and fire this game up. 

```java  
public class Main {
    public static void main(String[] args) {
        
        AdventureGame game = new AdventureGame();
        game.play("road");
    }
}
```

I'll execute play, on my game to start, and pass it the _road_, 
the starting position. 
If I run that:

```html  
*** You're standing at the end of the road ***
    From here, you can see:
    • A forest to the North (N)
    • A well house to the East (E)
    • A hill to the West (W)
    • A valley to the South (S)
Select Your Compass (Q to quit) >>
```

You can see what gets printed. 
I get the full description of the road from the data provided. 
And I get the directions I can choose from, and a short description of 
what lies in each of those directions. 
I can change my starting position, so I'll go up, 
and change _road_ to _lake_ in my main method.

```java  
public class Main {
    public static void main(String[] args) {
        
        AdventureGame game = new AdventureGame();
        //game.play("road");
        game.play("lake");
    }
}
```

And now I get a different output:

```html  
*** You're standing by an alpine lake surrounded by wildflowers ***
    From here, you can see:
    • A forest to the West (W)
    • A well house to the South (S)
Select Your Compass (Q to quit) >>
```

Ok, so that's the setup.
I'm going to revert to that last change, 
so I do start with the road. 
Next, I have to get the response from my player.

```java  
public class Main {
    public static void main(String[] args) {

        AdventureGame game = new AdventureGame();
        game.play("road");
        //game.play("lake");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String direction = scanner.nextLine().trim().toUpperCase().substring(0, 1);
            if (direction.equals("Q")) break;
            game.move(direction);
        }
    }
}
```

I'll set up a scanner, then go into a while loop. 
Inside this loop, I'm going to call nextLine on my scanner, 
and then chain some string methods to that. 
I'll trim the data I get from the user, 
I'll make it upper case, and I'll get the first character of whatever they entered. 
I'm assigning the result of all this to a variable named _direction_.
Next, I want to check if the user pressed _q_, 
or any text that started with a _q_, and if they did,
I'll break out of the loop, and end the game. 
Otherwise, I want to call move on the game, 
passing it the direction picked by my player.
Ok, let me run this.

```html  
*** You're standing at the end of the road ***
    From here, you can see:
    • A forest to the North (N)
    • A well house to the East (E)
    • A hill to the West (W)
    • A valley to the South (S)
Select Your Compass (Q to quit) >>
```

Now imagine that you haven't seen the challenge info with the map on it. 
You don't know what lies ahead.
So which way would you go? 
I guess I'll go _South_ first to the _valley_, 
and I'll just enter _S_ at the prompt.

```html  
*** You're standing in a forest valley beside a tumbling stream ***
    From here, you can see:
    • A road to the North (N)
    • A stream to the East (E)
    • A hill to the West (W)
Select Your Compass (Q to quit) >>
```

And now I'm standing in _a forest valley beside a tumbling stream_. 
Let's go check out the stream, so I'll go _east_, 
I'll type east out in lowercase.

```html  
*** You're standing near a stream with a rocky bed ***
    From here, you can see:
    • A well house to the North (N)
    • A valley to the West (W)
Select Your Compass (Q to quit) >>
```

That still works, and I get you _standing near a stream with a rocky bed_. 
Now let's go to the _well house_, _north_, so I'll enter just a lower case n here:

```html  
*** You're standing inside a well house for a small spring ***
    From here, you can see:
    • A lake to the North (N)
    • A road to the West (W)
    • A stream to the South (S)
Select Your Compass (Q to quit) >>
```

And there, you can see I'm inside a _well house_ for a small spring. 
And now I'll quit. 
There you go, you have the beginnings of a text-based game. 
The real game lets you find things in each location, 
and unlock locations, and so on, so if this inspires you,
you can continue to build on your game.

```java  
public class Main {
    public static void main(String[] args) {

        String myLocations = """        
                lake,at the edge of Lake Tim,E:ocean,W:forest,S:well house,N:cave
                ocean,on Tim's beach before an angry sea,W:lake
                cave,at the mouth of Tim's bat cave,E:ocean,W:forest,S:lake
                """;
        
        
        //AdventureGame game = new AdventureGame();
        AdventureGame game = new AdventureGame(myLocations);
        
        game.play("road");
        //game.play("lake");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String direction = scanner.nextLine().trim().toUpperCase().substring(0, 1);
            if (direction.equals("Q")) break;
            game.move(direction);
        }
    }
}
```

Ok, next, the bonus part, I want to let players customize 
the game's locations. 
Let's say I want my lake to have a different description, 
and I want to add some additional destinations from the _Lake_, 
in addition to the two that are already set up. 
Let me show this to you this. 
_Lake_ is already in my map, but I want to customize it,
first by describing it, naming it _Lake Tim_, 
and then adding two additional destinations from there, 
a _cave_ to the _north_, and an _ocean_ to the _east_. 
The _cave_ and _ocean_ will be additional board locations. 
Right now, from the _lake_, I can only go _west_ to the _forest_, 
or _south_ to the _well house_.
I want to pass this text string, _myLocations_, 
to the _AdventureGame_ constructor. 
To make it easier to get the locations, 
I'll start at the lake location. 
Running that:

```html  
*** You're standing at the edge of Lake Tim ***
    From here, you can see:
    • A cave to the North (N)
    • A ocean to the East (E)
    • A forest to the West (W)
    • A well house to the South (S)
Select Your Compass (Q to quit) >>
```

You can see that when I go to the lake, 
it now says I'm _standing at the edge of Lake Tim_, 
and my destinations include an ocean to the east, and a cave to the north. 
Let's check out the cave, so I'll enter an _n_ there.

```html  
*** You're standing at the mouth of Tim's bat cave ***
    From here, you can see:
    • A forest to the West (W)
    • A ocean to the East (E)
    • A lake to the South (S)
Select Your Compass (Q to quit) >>
```

Now I'm _standing at the mouth of Tim's bat cave_. 
Ok, that's kind of fun. 
For good measure, I'll check out the ocean to the _East_:

```html  
*** You're standing on Tim's beach before an angry sea ***
    From here, you can see:
    • A lake to the West (W)
Select Your Compass (Q to quit) >>
```
And now I'm _on Tim's beach_, before an angry sea. 
I'll press _Q_ to quit, and that will end the adventure game challenge.
</div>