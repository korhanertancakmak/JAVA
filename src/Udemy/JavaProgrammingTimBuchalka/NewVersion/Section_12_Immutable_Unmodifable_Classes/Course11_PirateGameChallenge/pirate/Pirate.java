package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge.pirate;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge.game.Player;
import java.util.*;

//Part-2
/*
                                                Pirate Game

        This should extend the Game class we created previously. It should have a Pirate class, which implements the Player
    interface. Your game will have different levels, and each level will have a list of towns, which can be Strings for
    now. Use an enum for the Weapon options. Weapon should have two fields.

        * Hit points will get deducted from a player hit by this weapon.
        * Level will be the minimum level needed, to be able to use the weapon.

        Use an "enum constructor" to set this up. I also want you to think about how you could use instance or static
    initializers, as you build these types.

               _________________________________________________             _________________________________________________
               | Game<T extends Player>                        |             | Weapon(enum) <AXE,KNIFE,MACHETE,...>          |
               |_______________________________________________|             |_______________________________________________|
               | gameName: String                              |             | minLevel: int                                 |
               | players: List<Player>                         |             | hitPoints: int                                |
               |_______________________________________________|             |_______________________________________________|
                                       ↑
               ________________________↑_________________________            _____________________________________
               | Pirate Game                                    |            | Pirate implements Player          |
               |________________________________________________|            |___________________________________|
               | static levelMap: List<List<String>>            |<>----------| gameData: Map<String, Integer>    |
               | players: List<Player>                          |            | name: String                      |
               |________________________________________________|            | townsVisited: Set<Town>           |
                                                                             | currentWeapon: Weapon             |
                                                                             |___________________________________|

    This diagram shows a model of the main entities, for my own Pirate game. I'm going to create Weapon, as an enum, as
    suggested. The Pirate class will implement the Player interface, from the last lecture. Finally, I'll create a PirateGame
    class, that extends Game.

        I haven't included methods in the diagram, but this gives you a road map for what I'll be building. I'll be using
    the Game Console from the previous set up lecture. That code included a Player interface, an abstract generic Game
    class that takes a type that implements Player, as a type argument. It also includes a Game Console, that solicits
    input from the console, to determine what actions to take. Any custom game actions should be setup on the new game
    class, as methods that take an integer, and return a boolean. This means I can store its method reference in the game
    action's field, which is set up as a Predicate, with an Integer type. I'm going to put this new game, and related
    types, in the pirate package. I'll create the Weapon enum first.
*/
//End-Part-2

public class Pirate implements Player {

    private final String name;
    private final Map<String, Integer> gameData;
    private final List<String> townsVisited = new LinkedList<>();
    private Weapon currentWeapon;

    public Pirate(String name) {
        this.name = name;
    }

    //------------------------------------------------
    {
        gameData = new HashMap<>(Map.of(
                "health", 100,
                "score", 0,
                "level", 0,
                "townIndex", 0
        ));
        visitTown();
    }

//Part-8
/*
        I'll create that in my pirate package too. This will implement the Player interface. This code doesn't compile
    until I implement the abstract method, which for player is just a method, called name. I'll replace null with name,
    the field, in that return statement. Right now, I don't yet have a field for name. I'll add that next, and a few other
    fields as well. Name will be private, final, and a string. I want some gameData, like the score, the health, the current
    level, and the current town index. I could make all these integer fields, but I'll just create a map, with string as
    the key, and integer as the value. If I find I want to add more pirate data later, then it's pretty simple, to just
    add a new mapping. I'll keep a list of the towns visited while playing. This will include towns across all levels,
    and I'll make it a linked list, so it'll be in insertion order, meaning the order the pirate visits the towns. I'll
    also add a field, to keep track of the pirate's current weapon. I'll generate a constructor, with just the name. I
    want to initialize my game data, and I could do that here in this constructor, but I think this is a good opportunity
    to use an instance initializer. At this point, the initialization process will be the same for every player. Maybe
    some day I'd want to store and restore a session, but that's for a later date. If I use an instance initializer, this
    code will get executed, regardless of what constructor is used. I'll put this block of code, immediately after the
    constructor. First, I'll just add a comment, just some dashes, so it's a bit easier to see this code is separate from
    the constructor. I'll assign gameData to a new instance of a hash map, since order doesn't matter, for this information.
    We can create a map by specifying a series of key value pairs, using the Map.of method. That's what I'll do here.
    I'll start passing the list of key value pairs, so first health, with 100. Then score, with an initial value zero.
    Next, the playing level, and that starts at zero.
*/
//End-Part-8

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    int value(String name) {
        return gameData.get(name);
    }

    private void setValue(String name, int value) {
        gameData.put(name, value);
    }

//Part-9
/*
        Next, I'll generate a getter and setter for the current weapon. I'll remove the modifier on setCurrentWeapon so
    only classes in this package can call it. For the user data, I want to create some setter and getter methods of my
    own. First, I'll create a value method. This should be package private, so the PirateGame can call it. It'll return
    an int, and take a string, which will be the key, to the pirate data element on gameData. This can just return the
    map value, based on the key passed. Next, there'll be a setValue method. This one will be private, only the Pirate
    class will be able to set this value. The return type is void, and it'll take a String for the key, and an integer
    for the new value. This method will just put the new value in the map, using the name passed.
*/
//End-Part-9

    private void adjustValue(String name, int adj) {
        gameData.compute(name, (k, v) -> v += adj);
    }

//Part-10
/*
        The next method will adjust a value in this data map. This will be private, again encapsulated, so that only this
    Pirate class can adjust its own data. It's return type is void, and it should take a field name, and an adjustment
    value, an integer. I'll use map's compute method here, using a lambda, that simply adds the adjusted amount to the
    existing value.
*/
//End-Part-10

    private void adjustHealth(int adj) {

        int health = value("health");
        health += adj;
        health = (health < 0) ? 0 : ((health > 100) ? 100 : health);
        setValue("health", health);
    }

//Part-11
/*
        I'll add a special method just for a player's health though, called adjustHealth, and that's because health shouldn't
    ever go below 0, or above 100. This method will be private and void, and take an adjustment argument, an integer.
    First, I want to get the current value of health. The adjusted value can simply be added, because the adjustment
    could be negative. Next, I'll use a nested ternary operator. if the adjustment took health's value below zero, it
    will return 0, or above 100, it returns 100. I'll use set value to put this back in the map. IntelliJ may give you a
    hint, to change this code, to a simpler form, using Math.min. You're welcome to take that suggestion, but since I
    haven't reviewed the Math class, I'm going to ignore this for now. I'll be covering library classes like Math, in
    some detail in a later section, but that shouldn't stop you from exploring it on your own if you're curious.
*/
//End-Part-11

    boolean useWeapon() {

        System.out.println("Used the " + currentWeapon);
        return visitNextTown();
    }

//Part-23
/*
        I'll replace the town assignment statement. First I want to get the towns for pirate's playing level, and assign
    that to a local variable, level towns. If there are no towns, meaning level towns is null, the game is over, so I'll
    return true here. I'll get the town using the player's town index, and leave the rest of the code the way I had it.
    And that's it, we should be able to play this pirate game, and use some weapons. I'll go back to the main class,
*/
//End-Part-23

    boolean visitTown() {

        List<String> levelTowns = PirateGame.getTowns(value("level"));
        if (levelTowns == null) return true;
        String town = levelTowns.get(value("townIndex"));
        if (town != null) {
            townsVisited.add(town);
            return false;
        }
        return true;
    }

//Part-12
/*
        Now, I want some operational methods, meaning game related methods. These will be the methods, useWeapon and visitTown.
    I'll make useWeapon package private, so my Pirate Game can call it, and it'll return a boolean. For now, this will
    simply print the current weapon out. It will return false to start with. The visitTown method will be package private
    also, and return a boolean. I'll set the town to a dummy variable right now. Later I'll get the right town from data
    on the Pirate Game. If the town is not null, I'll add it to the townsVisited field. I'll return false, so if this is
    used by the game, the play will continue. If a town couldn't be retrieved, the game needs to end, so I'll return true.
    I'm going to make a call to this method in my initializer code.
*/
//End-Part-12

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {

        var current = ((LinkedList<String>) townsVisited).getLast();
        String[] simpleNames = new String[townsVisited.size()];
        Arrays.setAll(simpleNames, i -> townsVisited.get(i).split(",")[0]);
        return "---> " + current +
                "\nPirate "+ name + " " + gameData +
                "\n\ttownsVisited=" + Arrays.toString(simpleNames);
    }

//Part-13
/*
        Finally, I want to include a toString method, so I'll generate that with no data to start. I'll be replacing that
    return statement with code to print out my pirate's information. First, I'll get the list of towns visited, and cast
    that to a linked list. This lets me use linked list methods. Specifically, I want to use the get last method, to
    retrieve the current town. Next, I just want a list of simple town names, without island names, so I'll set up a new
    string array. I'll use arrays.setAll, to just get the simplified town names, without the Island included. I'll return
    the current town, plus the pirate name and data. I'll include the towns visited for good measure. I can test this by
    creating a Pirate in my main method, so I'll do that.
*/
//End-Part-13

    private boolean visitNextTown() {

        int townIndex = value("townIndex");
        var towns = PirateGame.getTowns(value("level"));
        if (towns == null) return true;
        if (townIndex >= (towns.size()-1)) {
            System.out.println("Leveling up! Bonus: 500 points!");
            adjustValue("score", 500);
            adjustValue("level", 1);
            setValue("townIndex", 0);
        } else {
            System.out.println("Sailing to next town! Bonus: 50 points!");
            adjustValue("townIndex", 1);
            adjustValue("score", 50);
        }
        return visitTown();
    }

//Part-24
/*
       I'll make this private, and call it visit next town. It will return a boolean. First I'll get the current town
    index. Next, I'll get the towns for the player's current level. If I don't get any towns back, I'll return true, so
    the game will end. I'll return the result of calling visitTown from this method. Remember this gets the town, and
    returns true, meaning the game is over if no more towns can be found. After I get the towns for the level, I'll
    check if my player's town index is greater than or equal to the number of towns on this level, minus one. If this is
    true, the pirate's visited every town on the level, so he'll level up. I'll print that he's leveled up, and getting b
    onus points. I'll use my adjustValue method, to add 500 points to the score. I'll bump up the level by one. I'll
    reset the town index to zero, so it start's at the next level's first town. If the pirate's not leveling up, I just
    want to go to the next town, so I'll add an else statement. I'll add a message that our pirate's sailing to the next
    town, with 50 bonus points. I'll increase the town index by 1. I'll add 50 to the score. For now, I'll simply call
    this method every time the pirate uses his weapon. Instead of returning false, I'll return the value from calling
    visitNextTown. Later, we can build different conditions, like finding treasure, or shooting a certain number of other
    players, or something like that. I'll rerun the code, entering Tim for the name. I'll enter K, and now you can see
    an extra message.

                    -------------------------------------------
                    Used the KNIFE
                    Sailing to next town! Bonus: 50 points!
                    -------------------------------------------

    It says Sailing to next town, and bonus is 50 points. I'll enter I, I'll see my score is now 50, my town index is 1,
    and I have two towns in the townsVisited list. Let's keep going. I'll select an A next. The message says used the axe,
    and again, sailing to the next town. I'll select K again. Now, I see that I've leveled up, with 500 bonus points, but
    I've also got more weapons now. I now can select machete or pistol. If I select I, you can see, sure enough, I'm now
    in a town on the island of Martinique, my score is 600, and my level is 1. If I continue to play, the game will end,
    after I run out of towns. That's it for this challenge. I hope you were able to build a game on your own, and use
    enum constructors, and initializers. In the next lecture, I want to talk more about final classes, and do a review
    what it means to use different access modifiers on your constructors.
*/
//End-Part-24
}


