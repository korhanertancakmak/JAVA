package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge.game;

//Part-13
/*
        I'll make the shooter a record, putting it in the game package. This record has one field, a name and implements
    Player. I'll include two additional methods, that would emulate some kind of shooter action on a game. I'll make these
    package private, so that only classes in this package can call these methods.
*/
//End-Part-13

public record Shooter(String name) implements Player {

    boolean findPrize() {

        System.out.println("Prize found, score should be adjusted.");
        return false;
    }

//Part-14
/*
        First, I'll code find prize, this will return a boolean. I'll just print that a prize was found, and score needs
    to be adjusted. I'll return false, because we don't want the game to end, if the player finds a prize. The next one
    will be useWeapon, and return a boolean, but it'll take a weapon. I'll just print out what weapon it is. An again
    return false. Ok, that's good enough for the shooter test. Finally, I'll add a new class, the shooter game.
*/
//End-Part-14

    boolean useWeapon(String weapon) {

        System.out.println("You Shot your " + weapon);
        return false;
    }
}
