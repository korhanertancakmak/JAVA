package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge.pirate;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

//Part-3
/*
        This will have two fields, a minimum level, and a hit points value. The minimum level determines when a player
    can use a particular weapon. Hit points would be used to adjust an opponent's health. Again, I'll set these up, with
    these values already declared, in parentheses next to each constant. I'll use a set of weapons, each with a unique
    first character, to make set up a little easier. I'm only going to set up data for two levels for this challenge.
    So I have Knife, and that will be available at the first level, so 0, and it will take off 10 points, if a player is
    injured by a knife. Next, will be axe, again level 0, and this time 30 hit points. Machete won't be available until
    the second level, and that will hurt, so 40 points. Next is a pistol, that will also come into play at level 2, and
    take away 50 health points. Next, I need my two fields, both will be private and final, since they won't change. The
    first is min level, an int. And the hit points, another int. I'll generate the constructor.
*/
//End-Part-3

public enum Weapon {

    KNIFE(0, 10),
    AXE(0, 30),
    MACHETE(1, 40),
    PISTOL(1, 50);

    private final int minLevel;
    private final int hitPoints;

//Part-4
/*
        This will have two fields, a minimum level, and a hit points value. The minimum level determines when a player
    can use a particular weapon. Hit points would be used to adjust an opponent's health. Again, I'll set these up, with
    these values already declared, in parentheses next to each constant. I'll use a set of weapons, each with a unique
    first character, to make set up a little easier. I'm only going to set up data for two levels for this challenge.
    So I have Knife, and that will be available at the first level, so 0, and it will take off 10 points, if a player is
    injured by a knife. Next, will be axe, again level 0, and this time 30 hit points. Machete won't be available until
    the second level, and that will hurt, so 40 points. Next is a pistol, that will also come into play at level 2, and
    take away 50 health points. Next, I need my two fields, both will be private and final, since they won't change. The
    first is min level, an int. And the hit points, another int.
*/
//End-Part-4

    Weapon(int minLevel, int hitPoints) {
        this.minLevel = minLevel;
        this.hitPoints = hitPoints;
    }

//Part-5
/*
        I'll generate the constructor. Then the getters for both. I'll add some helper methods. The first method is going
    to get a weapon from the enum values, using a single character. It'll be public and static, returning a Weapon. I'll
    call it get weapon by char, and it'll take a char, I'll call that first initial. I'll loop through each Enum constant,
    so through the values. I'll get the first character from the constant's name, and if it matches firstInitial, I'll
    return it from this method. If a match wasn't found, I'll return the first weapon in the enum.
*/
//End-Part-5

    public int getMinLevel() {
        return minLevel;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public static Weapon getWeaponByChar(char firstInitial) {

        for (Weapon w : values()) {
            if (w.name().charAt(0) == firstInitial) {
                return w;
            }
        }
        return values()[0];
    }

    public static List<Weapon> getWeaponsByLevel(int levelOfPlay) {

        List<Weapon> weapons = new ArrayList<>(EnumSet.allOf(Weapon.class));
        weapons.removeIf(w -> (w.minLevel > levelOfPlay));
        return weapons;
    }

//Part-6
/*
        The next method will get a list of weapons available, based on the level of play. Again, this will be public and
    static, and I'll call it get Weapons By Level, and it'll take an integer, the level of play. I'll first set up a new
    array list of all weapons, using enum set's allOf method to populate it. Next, I'll just call the remove if method
    on that list, and pass it a predicate lambda, a conditional statement in other words. I'll compare min level to the
    user's level of play. If the weapon's min level is greater than a player's level, it'll get removed from this list.
    I'll return the remaining weapons, meaning those that have a minimum level, less than or equal to the player's current
    level. Getting back to the Main class,
*/
//End-Part-6

}
