package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate;

import java.util.*;

//Part-7
/*
        First, I want this class to extend Combatant. I'll replace implements Player, with extends Combatant to do that.
    Again, I'm going to go through, and delete a bunch of stuff. First, the fields. I'll remove all but townsVisited.
    I'll add a couple of new fields after the townsVisited field, a list of Loot, a list of opponents, and a list of
    features. These will get updated, every time a pirate moves from town to town, so they'll be changing. I'll next
    delete the constructor, which has errors. I'll regenerate a constructor, selecting only name.
*/
//End-Part-7

public final class Pirate extends Combatant {

    private final List<Town> townsVisited = new LinkedList<Town>();
    private List<Loot> loot;
    private List<Combatant> opponents;
    private List<Feature> features;

    public Pirate(String name) {
        super(name, Map.of("level", 0, "townIndex", 0));
        visitTown();
    }

//Part-8
/*
        I'm going to change this constructor's call to super. I want to include a second argument, a map of additional
    fields a pirate will have. I'll pass a call to Map.of, and include level, with an initial value of zero, and a town
    index, also initially zero. I'll also call visitTown from that constructor. I'll remove the instance initializer
    altogether. I can remove all of the methods that are on pirate, so I'll remove the name method next. I'll remove the
    getter and setter for current weapon. I'll remove the methods that manipulate game data, so value, set value, adjust
    value and adjust health.The useWeapon method has a compiler error, because currentWeapon is private on the parent.
    I'll replace that with a call to super dot getCurrentWeapon. I'll leave visitTown alone for the moment. That brings
    me to the toString method.
*/
//End-Part-8

    boolean useWeapon() {

        int count = opponents.size();
        if (count> 0) {
            int opponentIndex = count - 1;
            if (count > 1) {
                opponentIndex = new Random().nextInt(count + 1);
            }
            Combatant combatant = opponents.get(opponentIndex);
            if (super.useWeapon(combatant)) {
                opponents.remove(opponentIndex);
            } else {
                return combatant.useWeapon(this);
            }
        }
        return false;
    }

//Part-26
/*
        I'll also change the code in the use Weapon method on this class, to make it more interesting. I'll remove the code
    figure out what opponent to fight. First, it'll get the pirate's copy of opponents and get the size. If this is greater
    than zero, then there are still living opponents. I'll set the default opponent index to the count - 1. If the count
    is greater than one, I'll use a random number to randomly pick the opponent. I'll get the combatant, from the opponents
    list using the random index. I'll call use Weapon on the super class, passing it this combatant. If this method returns
    true, it means the combatant died. If that's the case, I'll remove him from the list. Otherwise, it's the opponent's
    turn to use his weapon, and I'll return the result of that. If there aren't any components, this code will return false,
    meaning the game continues. Ok that was a lot of set up. It should now all pay off, with a couple of minor additions
    to the PirateGame class. Switching to that class,
*/
//End-Part-26

    boolean visitTown() {

        List<Town> levelTowns = PirateGame.getTowns(value("level"));
        if (levelTowns == null) return true;
        Town town = levelTowns.get(value("townIndex"));
        if (town != null) {
            townsVisited.add(town);
            loot = town.loot();
            opponents = town.opponents();
            features = town.features();
            return false;
        }
        return true;
    }

//Part-23
/*
        I'll set the pirate's loot, opponents, and features fields here, every time a new town is visited. Remember these
    are modifiable copies of the data on the town record. I'll add two helper methods. The first is called hasExperiences,
    and will return true, if there are additional features to experience. Checks if features field isn't null, and the size
    is greater than zero, returns true if so. The second one, is similar, and called has Opponents, also returning a boolean.
    If the opponents field isn't null, and the size is greater than zero, this will return true, otherwise false. Now, I
    need to implement the find loot method on Pirate,
*/
//End-Part-23

    boolean hasExperiences() {
        return (features != null && features.size() > 0);
    }

    boolean hasOpponents() {
        return (opponents != null && opponents.size() > 0);
    }

//Part-21
/*
        In the first case, I can just change the cast from a LinkedList of String to Town.

                        var current = ((LinkedList<String>) townsVisited).getLast();
                                                    to
                        var current = ((LinkedList<Town>) townsVisited).getLast();

    The setAll method can now be simplified. I'll remove the call to the split method, and so on, with just name. I'll pop
    back to the Main class's main method,
*/
//End-Part-21

    public String information() {

        var current = ((LinkedList<Town>) townsVisited).getLast();
        String[] simpleNames = new String[townsVisited.size()];
        Arrays.setAll(simpleNames, i -> townsVisited.get(i).name());
        return "---> " + current +
                "\n" + super.information() +
                "\n\ttownsVisited=" + Arrays.toString(simpleNames);
    }

//Part-9
/*
        I'm going remove the annotation, and rename this method to information. And there's errors on name and gameData,
    I'll replace this statement. These changes made the Pirate class a lot simpler. I'm going to make this class final,
    one of my three options if I'm extending a sealed class. Before I make any further changes to this class, I'll go
    seal the Combatant class. I'll add the sealed keyword before the abstract modifier. If I hover over the error I get,
    I can select, 'Add missing subclasses to the permits clause' That'll generate the clause for me. Right now I only
    have one class extending Combatant, and that's my Pirate. Before I add any others, I'll first go to my PirateGame
    class, and override the print player method, which just prints the toString method on a player.
*/
//End-Part-9

    boolean findLoot() {

        if (loot.size() > 0) {
            Loot item = loot.remove(0);
            System.out.println("Found " + item + "!");
            adjustValue("score", item.getWorth());
            System.out.println(name() + "'s score is now " + value("score"));
        }
        if (loot.size() == 0) {
            return visitNextTown();
        }
        return false;
    }

//Part-24
/*
        Which will return a boolean. I'll make this package private, it'll be called by the PirateGame. If the pirate's
    list of loot has items, this code will remove the first element, assigning the removed item to a local variable. I'll
    print a statement that an item was found. I'll adjust the score, adding the the item's worth. I'll print the score.
    Finally, if all the loot is found, meaning this list is zero, I'll execute the visitNextTown method, returning its
    boolean value. Otherwise, false is returned, meaning the game continues.
*/
//End-Part-24

    boolean experienceFeature() {

        if (features.size() > 0) {
            Feature item = features.remove(0);
            System.out.println("Ran into " + item + "!");
            adjustHealth(item.getHealthPoints());
            System.out.println(name() + "'s health is now " + value("health"));
        }
        return (value("health") <= 0);
    }

//Part-25
/*
        The next method is similar, again package private, returning a boolean. I'll call this one experienceFeature. If
    the pirate's copy of features has any elements left in it, this code will remove the first element, returning the
    reference to a local variable. It'll print that the pirate ran into the feature. The pirate's health will be adjusted
    by this feature's health points. And the health will be printed out.
*/
//End-Part-25

    private boolean visitNextTown() {

        int townIndex = value("townIndex");
        List<Town> towns = PirateGame.getTowns(value("level"));
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
}
