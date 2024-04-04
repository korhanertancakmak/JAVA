package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//Part-4
/*
        I'll make this both abstract and generic. I'lI have to define the type, and I'll put that in angle brackets, using
    T as my type parameter, and that needs to extend Player. My game will be a container for players, if that helps you
    think about it that way. I'll add my three fields. I'll have a game name. I'll set up and array list for players,
    and initialize it here. I want a standard set of actions, that every game might have, like quit, or info. For now,
    I'll initialize this to null. The first two will be final, because I want to use the techniques we've been discussing,
    making this class as immutable as possible. The third, the key map, will vary by the type of game and user.
*/
//End-Part-4

public abstract class Game<T extends Player> {

    private final String gameName;
    private final List<T> players = new ArrayList<>();
    private Map<Character, GameAction> standardActions = null;

    public Game(String gameName) {
        this.gameName = gameName;
    }

//Part-5
/*
        Now, I'll generate a constructor, using just the gameName. I'll generate a getter for gameName and standardActions.
    I'll create my two abstract methods:

      - The first method, createNewPlayer has a return type of just T, and that takes a string name.
      - Next, the method returns a map, key is character, value is a game action. The name is get Game Actions, and takes
        a playerIndex. This means game actions could be unique for each player.

        Any subclasses extending this class, have to write their own custom implementations for these methods.
*/
//End-Part-5

    public String getGameName() {
        return gameName;
    }

    public Map<Character, GameAction> getStandardActions() {

//Part-6
/*
        Next, I'll focus on the getStandardActions method that I generated earlier. I'll insert an if statement before
    the return statement. This will check if the standard actions map is null first. If it is, it will populate it with
    two standard actions, I for information, and that will call a printPlayer method on the game, and Q to quit, which
    will call quitGame on the game. I'll make this a LinkedHashMap, so instructions get printed in insertion order. I'll
    use Map.of, which lets me pass a series of keys and values, in order. It takes first a key, then value, then the next
    key, and the next value, and so on. The first key is I. This will be a key to the map, as well as a field on the
    GameAction record. The prompt for this will be Print Player Info, and the method will be printPlayer, called on the
    current game instance. I'll use a regular lambda expression here, so you can see the parameter is an integer, and I'll
    call a method on this class, that takes an integer and returns a boolean, the printPlayer method.

        Next, I'll have Q to quit the game. The prompt is quit game, and the method will be quit game. The lambdas I'm
    using here won't compile, until I include those methods on this class. I'll do that in just a bit. First I'll add
    the addPlayer method,
*/
//End-Part-6

        if (standardActions == null) {
            standardActions = new LinkedHashMap<>(Map.of(
                    'I',
                    new GameAction('I', "Print Player Info", i -> this.printPlayer(i)),
                    'Q',
                    new GameAction('Q', "Quit Game", this::quitGame)
            ));
        }
        return standardActions;
    }

    public abstract T createNewPlayer(String name);
    public abstract Map<Character, GameAction> getGameActions(int playerIndex);

    final int addPlayer(String name) {

        T player = createNewPlayer(name);
        if (player != null) {
            players.add(player);
            return players.size() - 1;
        }
        return -1;
    }

//Part-7
/*
        That takes a name and returns an integer, the playerIndex. I don't want subclasses to have their own versions so
    I'll make it final. I want my game console to have access to it, so I'll make it package private, meaning no access
    modifier. I'll first call the abstract method, create new player, and assign that to the T type parameter. Each
    subclass will have to implement the createNewPlayer method, producing the right type of player for the game. If this
    isn't null, I'll add it to the players list. To return the player's index, I'll get the last index. If we were multi
    -threading, this wouldn't be a safe way to get a player's index. Otherwise I'll return -1.
*/
//End-Part-7

    protected final T getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    public boolean executeGameAction(int player, GameAction action) {
        return action.action().test(player);
    }

    public boolean printPlayer(int playerIndex) {

        Player player = players.get(playerIndex);
        System.out.println(player);
        return false;
    }

    public boolean quitGame(int playerIndex) {

        Player player = players.get(playerIndex);
        System.out.println("Sorry to see you go, " + player.name());
        return true;
    }

//Part-8
/*
        Next I want a method to get a player, by the playerIndex. This method will be protected, so subclasses can use
    it, but final, so they can't override it. I'll return the player, from the layers list, for that index.

        Continuing on, I'll add the execute Game Action method, making it public and not final, because subclasses may
    want additional functionality. This will be executing lambda expressions or method references, that return a boolean
    and take an integer. I'll have this method do the same, but I'll also include a game action parameter. I'll return
    the result of the predicate lambda in the action record. A predicate's method is test, and it takes the
    type we declared, so an integer, but I can pass an int, because of auto boxing there.

        Finally, I want to implement the two methods in my game action records. First, the print player method, which has
    to match the Boolean Predicate, returning a boolean and taking an integer. I'll get the player from the private players
    list, using player index. I'll print the player string, and just return false. Requesting info should probably never
    end the game.

        Next, I need the quit Game, with the same return type and integer parameter. Again, I'll get the player by index.
    I'll print sorry to see you go, using the player's name. I'll return true every time, because this should end the game.
    Ok, that's everything except the Game Console class itself. Ok, now it's time to build the Game Console. I'll put this
    class in the game package again.
*/
//End-Part-8
}