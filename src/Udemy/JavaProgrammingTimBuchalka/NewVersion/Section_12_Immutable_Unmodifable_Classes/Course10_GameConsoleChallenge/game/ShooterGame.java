package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge.game;

import java.util.LinkedHashMap;
import java.util.Map;

//Part-15
/*
        I'll have that extend the Game, with a type argument of Shooter. I have an error, and that's because I have to
    implement Game's abstract methods. If I hover over that, I can pick implement methods. I'll pick both methods, in the
    popup. I want to replace return null, in the createNewPlayer method. I'll return a new instance of the Shooter record,
    passing that name.
*/
//End-Part-15

public class ShooterGame extends Game<Shooter> {

    public ShooterGame(String gameName) {
        super(gameName);
    }

    @Override
    public Shooter createNewPlayer(String name) {
        return new Shooter(name);
    }

    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {

        var map = new LinkedHashMap<>(Map.of(
                'F',
                new GameAction('F', "Find Prize", this::findPrize),
                'S',
                new GameAction('S', "Use your gun", this::useWeapon)
        ));
        map.putAll(getStandardActions());
        return map;
    }

//Part-16
/*
        For the getGameActions method, I'll replace that return statement altogether. In this method, I'll add some
    GameAction records, as well as include the standard ones, set up on Game. I'll first create a new map variable,
    using var, rather than be specific about that somewhat complicated type, and call it map. I'll assign that a new
    LinkedHashMap instance, and again I want to use the Map.of method, passing it key value pairs, as separate elements.
    If a user presses f, they'll be looking for a prize, and that will call this game's find method, which I'll add shortly.
    If a user presses S, they'll be shooting or using their weapon, and I'll call the use weapon method, again I'll add
    that in a minute.

        Next, I want to add the standard actions. Remember, these are quit and information, so I still want this. I want
    them listed after my own options, which is why I'm using LinkedHashMap, otherwise, the order wouldn't be guaranteed.
    Finally I'll return this new map. I've still got an error there, that there's no default constructor, so I'll select
    create a constructor matching super. Finally, I'll implement the two methods I used in those game options, starting
    with find Prize first.
*/
//End-Part-16

    public boolean findPrize(int playerIndex) {
        return getPlayer(playerIndex).findPrize();
    }

    public boolean useWeapon(int playerIndex) {
        return getPlayer(playerIndex).useWeapon("pistol");
    }

//Part-17
/*
        All my methods on Game, that are actionable menu items, have to return a boolean, and take an integer, I'll get
    the player by index, and chain the shooter's find prize method to that. Lastly, I'll implement the use weapon method.
    much like the findPrize method. This time, I'll call useWeapon on the shooter, passing it pistol. The methods on any
    of the player implementations, don't have to have the same return type, or parameters, that's required of the game's
    actionable methods. Here, you can see that my shooter's use weapon method, takes a string representing a weapon. Ok,
    that was a bit of set up, but I'll be using this in the future. Let's test this out. I'll go to the Main class main
    method,
*/
//End-Part-17
}
