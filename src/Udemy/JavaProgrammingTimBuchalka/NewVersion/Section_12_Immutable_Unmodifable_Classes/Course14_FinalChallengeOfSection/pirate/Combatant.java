package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.game.Player;

import java.util.*;

//Part-4
/*
        I'll make this abstract. Now, I'll go through and start removing members, that wouldn't apply to the other players.
    I'll remove the townsVisited field. If I look at the initializer, I think I'll just have the average combatant have
    two mapped fields, health and score. I'll remove level and town index, since those are specific to a pirate. I'll
    also remove the call to visitTown here. Scrolling down, I can keep all these methods, all the way down to the useWeapon
    method. I want to change that method,
*/
//End-Part-4

public sealed abstract class Combatant implements Player permits Islander, Pirate, Soldier {

    private final String name;
    private final Map<String,Integer> gameData;
    private Weapon currentWeapon;

    public Combatant(String name) {
        this.name = name;
    }

    public Combatant(String name, Map<String, Integer> gameData) {
        this.name = name;
        if (gameData != null) {
            this.gameData.putAll(gameData);
        }
    }

//Part-6
/*
        So I'll scroll up and generate one below the current one. I'll select the first two fields highlighted. I've got
    an error on this constructor, because I'm trying to assign a value to gameData, but it's final and initialized by the
    instance initializer. What I really want to do is, add keyed elements that can be mapped. I'll remove that assignment
    statement. Instead of assigning data here, I'll first check that the argument's not null. If it isn't, I'll add the
    map that's passed, to my current game data. This is mutating the gameData map, by adding new key values, and in this
    case, it's a desired effect. Finally, I need to change the modifiers on any method that sets or adjusts the game data,
    so I'll make all of those protected. This lets my subclasses use them. Ok, that's the Combatant class. This should
    simplify the Pirate class quite a bit, so I'll edit that next.
*/
//End-Part-6

    //------------------------------------------------
    {
        gameData = new HashMap<>(Map.of(
                "health", 100,
                "score", 0
        ));
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    int value(String name) {
        return gameData.get(name);
    }

    protected void setValue(String name, int value) {
        gameData.put(name, value);
    }

    protected void adjustValue(String name, int adj) {
        gameData.compute(name, (k, v) -> v += adj);
    }

    protected void adjustHealth(int adj) {

        int health = value("health");
        health += adj;
        health = (health < 0) ? 0 : ((health > 100) ? 100 : health);
        setValue("health", health);
    }

    boolean useWeapon(Combatant opponent) {

        System.out.print(name + " used the " + currentWeapon);
        if (new Random().nextBoolean()) {
            System.out.println(" and HIT *** " + opponent.name() + "! ***");
            opponent.adjustHealth(-currentWeapon.getHitPoints());
            System.out.printf("%s's health=%d, %s's health=%d%n",
                    name, value("health"),
                    opponent.name(), opponent.value("health"));
            adjustValue("score", 50);
        } else {
            System.out.println(" and missed!");
        }
        return (opponent.value("health") <= 0);
    }

//Part-5
/*
        First to take a Combatant type, an opponent. I'll remove the current statements that are there. Now I'll add code,
    that will let opponents use weapons against each other. I'll set up an if condition next. Here, I'll use another method
    on the Random class, that just gives me a random boolean value back. If that's true, I'll say my player was HIT, and
    print the opponent's name. Next, I'll adjust the opponent's health, I'm going to make that negative so it will subtract
    the weapon's hit points from the opponent's health. I'll print out the name and health of both opponents. I'll add 50
    points to the combatant's score. If false came back from the nextBoolean method, I'll print that the player missed.
    I'll return the opponent's health from this method. If the opponent is killed, this will return true. Right below this
    method, I'll remove the visitTown method. That's a method specific to a pirate, so it shouldn't be here. I'll also
    remove both the two string method, and the visitNextTown, the last two methods in this class. I want the combatant
    toString to be much simpler, printing just the name, so I'll generate that. I'll replace the statement that's generated,
    with just return name. In addition to the toString method, I want another, and I'd like it to print out more detailed
    information. I'll make it public, return a String and name it information, with no parameters. I want to make one more
    change to this class, and what I want is a second constructor,
*/
//End-Part-5

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String information() {
        return name + " " + gameData;
    }
}
