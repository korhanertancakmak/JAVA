package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge.pirate;


import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge.game.Game;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge.game.GameAction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//Part-15
/*
        This will extend the Game class, and I'll make the type argument for the Game, my Pirate player. I need to implement
    Game's abstract methods, and I'll use IntelliJ to do that. For the createNewPlayer method, I want to return a new
    Pirate instance, passing name to the Pirate constructor. I'll hold off on finishing the getGameActions method for now.
    I still have an error with this class, and if I hover over that, you can see there's no default constructor, and I
    can pick, Create constructor matching super. This constructor fixes the last of my compiler errors for this class,
    so next, I'm going to set up some static fields.
*/
//End-Part-15

public class PirateGame extends Game<Pirate> {

    private static final List<List<String>> levelMap;

//Part-16
/*
        I want the first to be private, static and final, a List. This will actually be a two dimensional list, the first
    level will represent the level of play. What I mean by this is, because it's a list, the data at element zero, is the
    first level's data, the data at element one, is the second level's data, and so on. This list will contain a nested
    list of towns, so it's type argument for the list, is another list, with type String. I could've created a Level class,
    with a list of towns on it. That's a really good way to go, because you can expand that class to include a lot more
    variety by each level. For now, I'm trying to keep this code relatively simple. This isn't going to compile yet, because
    I've made it final, without initializing it. Here, I'll use a static initializer to set this data.
*/
//End-Part-16

    //------------------------------------------------------------
    static {
        levelMap = new ArrayList<>();
        System.out.println("Loading Data...");
        loadData();

        if (levelMap.size() == 0) {
            throw new RuntimeException("Could not load data, try later");
        }
        System.out.println("Finished Loading Data.");
    }
    //------------------------------------------------------------

//Part-17
/*
        I'll add this comment line, so it's easier to recognize the static initializer code block. a static initializer
    starts with the word static before the opening curly brace. First, I'll initialize level map to a new array list.
    I'll print a message, that data is loading. I'll call a method called "loadData", which will populate my levelMap
    with data. If that map comes back and has no data in it, size is zero in other words, I'll throw a runtime exception.
    This means nobody can use this PirateGame class, if this process fails. Otherwise, I'll print that the data finished
    loading. I'll include another comment line. This won't compile until I implement the loadData method.
*/
//End-Part-17

    public PirateGame(String gameName) {
        super(gameName);
    }

    @Override
    public Pirate createNewPlayer(String name) {
        return new Pirate(name);
    }

    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {

        Pirate pirate = getPlayer(playerIndex);
        System.out.println(pirate);
        List<Weapon> weapons = Weapon.getWeaponsByLevel(pirate.value("level"));

        Map<Character, GameAction> map = new LinkedHashMap<>();
        for (Weapon weapon : weapons) {
            char init = weapon.name().charAt(0);
            map.put(init, new GameAction(init, "Use " + weapon, this::useWeapon));
        }
        map.putAll(getStandardActions());
        return map;
    }

//Part-22
/*
        Now I need to add some code in there. I'll replace the return null statement. I'll first get the pirate information
    and print it. I'll get the weapons for the current level. I'll create a local map, with a key of Character, and value
    of GameAction, which is the return type of this method. I'll make that a new linked hash map, because I want these
    to stay in the order I add them. This map will get returned from the method. But I also need to add a way for the
    user to select which weapon to use. I'll add code to loop through the weapons available at the player's level. I'll
    get the first initial of the weapon's name, which will be the menu key. I'll add a new entry to the map, using the
    key, and creating a new game action. The prompt I create will include the weapon name. The method reference for any
    weapon, will be this::useWeapon. Finally, I'll add all the standard actions from the Game's class. These actions were
    quit the game, or get player information. Before we can play, I need to fix the visitTown method on my Pirate class.
*/
//End-Part-22

    private static void loadData() {

        // Level 1 Towns
        levelMap.add(new ArrayList<>(List.of(
                "Bridgetown, Barbados",
                "Fitts Village, Barbados",
                "Holetown, Barbados"
        )));
        // Level 2 Towns
        levelMap.add(new ArrayList<>(List.of(
                "Fort-de-France, Martinique",
                "Sainte-Anne, Martinique",
                "Le Vauclin, Martinique"
        )));
    }

//Part-18
/*
        Because it's being called from a static initializer, this method needs to be static, and I'll make it private,
    and void, with no arguments. Each of my levels will represent a different Caribbean island, so I'll set up towns by
    what island they're on. I'm only going to set up two levels right now. I'll include a comment, that the first group
    is level 1 towns. I want to add an array list of town to my level map. The first town is Bridgetown, Barbados. Next,
    will be Fitts Village. And the last town on this level is holetown. I'll copy those 6 lines, and paste that copy right
    below. I'll change the comment to say Level 2 towns. I'll change Bridgetown Barbados, to Fort-de-france martinique.
    The second town will be Sainte-Anne Martinique. The last town on level 2, will be Le-Vauclin Martinique. Before I test
    this, I want to include another static method, to return towns for a specified level.
*/
//End-Part-18

    public static List<String> getTowns(int level) {

        if (level <= (levelMap.size() - 1)) {
            return levelMap.get(level);
        }
        return null;
    }

//Part-19
/*
        I'll make this public and static, and take the playing level, as a parameter. First, I'll check that the level
    has towns set up for it. If so, It'll return those towns for that level. If the player's level is greater than the
    game's level, it'll return null. I'll test this in the Main class's main method.
*/
//End-Part-19

    private boolean useWeapon(int playerIndex) {
        return getPlayer(playerIndex).useWeapon();
    }

    @Override
    public boolean executeGameAction(int player, GameAction action) {

        getPlayer(player).setCurrentWeapon(Weapon.getWeaponByChar(action.key()));
        return super.executeGameAction(player, action);
    }

//Part-21
/*
        I want a method here, that will delegate to the use weapon method I created on the Pirate class. On this class,
    the method needs to follow a pattern, because of the way I set up the Game Console. These methods need to return a
    boolean and take an integer, the player's index. This new method will be private, and named use Weapon. The player
    is retrieved from the player's list, and the player's use weapon is chained to that. This method returns a boolean,
    so I can just return that value here. To make this work, I'm going to first override the executeGameAction method,
    that's on the Game class. The only reason I want to override this method, is so I can set the weapon on my pirate
    player, before executing the use weapon method. I'll get player from the list, and then set the current weapon on
    that player. I can get the weapon by using the action's key, that's passed to this method, which is the key the user
    entered at the console. Finally, I have to set up the menu options, which will make everything work. This gets done
    in the getGameActions, which was the abstract method on Game. I've already included this on my PirateGame.
*/
//End-Part-21
}
