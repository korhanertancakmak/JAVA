package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate;

import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.game.Game;
import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.game.GameAction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PirateGame extends Game<Pirate> {

    private static final List<List<Town>> levelMap;

//Part-19
/*
        IntelliJ has a re-factor function that makes this a little easier to change. I'll go to my levelMap field on the
    PirateGame. I'll click on String here.

                            private static final List<List<String>> levelMap;
                                                to
                            private static final List<List<Town>> levelMap;

    This should be changed to Town, wherever this is used. I'll right click to bring up the menu items. I'll select Re-factor.
    We have a lot of options, but the one I want is Type Migration. Once I select that, it gives me a form field. I'm
    going to change java.lang.String to just Town, with a capital T. I'll then click the refactor button. I'll select
    Ignore on this next popup, and this will fix all but the two statements that were on that popup. The first thing I
    need to do after these changes, is fix the loadData method.
*/
//End-Part-19

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
        if (pirate.hasOpponents()) {
            for (Weapon weapon : weapons) {
                char init = weapon.name().charAt(0);
                map.put(init, new GameAction(init, "Use " + weapon,
                        this::useWeapon));
            }
        }

        map.put('F', new GameAction('F', "Find Loot", this::findLoot));
        if (pirate.hasExperiences()) {
            map.put('X', new GameAction('X', "Experience Town Feature",
                    this::experienceFeature));
        }
        map.putAll(getStandardActions());
        return map;
    }

//Part-29
/*
        To start with, I'll only print the weapons, if the pirate has opponents. I'll check the hasOpponents method on
    pirate. I'll need to copy and paste the Weapon for loop code. I'll do that in a bit. I'll include a finding loot action.
    A pirate can't move to the next town, until he finds all the loot for the level. The keystroke will be an F, and the
    game action will be the find Loot method, on this PirateGame class. Next, I'll allow a user to select an experiences,
    if he hasn't already run into them all. The keystroke will be an X, and the game action will be the experience feature
    method I just created. I'll move the Weapon for loop now. I'll comment out all the code, except the last three statements
    in my Main class's main method.
*/
//End-Part-29

    private static void loadData() {

        // Level 1 Towns
        levelMap.add(new ArrayList<Town>(List.of(
            new Town("Bridgetown", "Barbados", 0),
                new Town("Fitts Village", "Barbados", 0),
                new Town("Holetown", "Barbados", 0)
        )));
        // Level 2 Towns
        levelMap.add(new ArrayList<Town>(List.of(
                new Town("Fort-de-France", "Martinique", 1),
                new Town("Sainte-Anne", "Martinique", 1),
                new Town("Le Vauclin", "Martinique", 1)
        )));
    }

//Part-20
/*
        I'm going to remove the six Strings in that code. Now I'll replace those with new Town instances, using the same
    town names, and islands. I'll type in all 6 towns into the level 1 towns area, and then move them the 3 level 2 towns
    after. The first set of towns will have level 0. The second set of towns will have level 1. Before I can get this to
    compile, I need to fix the two problems on Pirate. Both of these are in the information method.
*/
//End-Part-20

    public static List<Town> getTowns(int level) {

        if (level <= (levelMap.size() - 1)) {
            return levelMap.get(level);
        }
        return null;
    }

    private boolean useWeapon(int playerIndex) {
        return getPlayer(playerIndex).useWeapon();
    }

    @Override
    public boolean executeGameAction(int player, GameAction action) {

        getPlayer(player).setCurrentWeapon(Weapon.getWeaponByChar(action.key()));
        return super.executeGameAction(player, action);
    }

    @Override
    public boolean printPlayer(int playerIndex) {

        System.out.println(getPlayer(playerIndex).information());
        return false;
    }

//Part-10
/*
        I want it to use the new pirate's information method instead. First, I'll just return false. Next I'll print the
    data I get from the information method. I'll get the player using the index, and chain a call to the information method.
    Now, I should be able to play my PirateGame. It won't be able to change towns, since I changed the useWeapon method,
    and removed visitNextTown from that. In this challenge, the pirate will visit a new town, after finding all the possible
    loot for that town, but I'll be implementing that a bit later. Before I run this code, I'll uncomment the last three
    lines in the main method.
*/
//End-Part-10

    private boolean findLoot(int playerIndex) {
        return getPlayer(playerIndex).findLoot();
    }

//Part-27
/*
        I'll add a wrapper method for the pirate's findLoot method. Hopefully you remember, that any game action methods
    have to return a boolean, and take an index. I'll call this findLoot. I'll get the player from the player list, using
    the index, and chain the findLoot method on player, which is pirate in this case, and return it's value. I'll do the
    same thing for the pirate's experienceFeature method.
*/
//End-Part-27

    private boolean experienceFeature(int playerIndex) {
        return getPlayer(playerIndex).experienceFeature();
    }


//Part-28
/*
        I'll use the experienceFeature method name. I'll delegate to the experienceFeature method on my pirate. Lastly,
    I want to adjust the game actions.
*/
//End-Part-28
}
