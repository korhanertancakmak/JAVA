package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course12_FinalClasses.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//Part-7
/*
        I declared this class public and abstract, and gave it a public constructor. Let's see what happens if I make that
    constructor private.

                        public Game(String gameName) {this.gameName = gameName;}
                                                   to
                        private Game(String gameName) {this.gameName = gameName;}

    I don't get any errors on this class, but if I open up either the PirateGame or ShooterGame, you can see these both
    have errors, on the call to the super constructor. In this case, I've created an abstract class that nobody can extend,
    by making that constructor private. That's probably not what I really wanted to happen. You might have some private
    constructors on an abstract class, but you don't want them all to be private, which is the situation I've created here.
    Instead, I'll revert that last change on Game, making the constructor public again. I could make it protected, but
    it's not really necessary to make it more restrictive, since an abstract class won't ever be instantiated. Only a
    subclass will. Now, what happens if I add the final keyword to this class?

                        public abstract class Game<T extends Player> {
                                                to
                        public final abstract class Game<T extends Player> {

    IntelliJ doesn't like that. Illegal combination of modifiers, final and abstract. What this means is, the two modifiers
    mean the exact opposite thing. A final modifier means your class is complete, and it not only doesn't need to be extended,
    but you don't want it to be. An abstract modifier means your class isn't complete, it probably has methods that aren't
    complete, and they'll only be implemented by subclasses. I'll revert that last change, removing the final modifier.

    I'll show a quick summary on a table. Private constructors will prevent both a new instance and a new subclass from
    being created.

                                                    Final       Abstract        Private Constructors        Protected Constructors
        Operations                                  Class        Class                Only                          Only

        Instantiate a new instance                   yes          no                   no                    yes, but only subclasses, and
                                                                                                             classes in same package

        A subclass can be declared successfully      no           yes                  no                           yes

    Protected constructors will prevent an instance from being created outside of a sub class or the package. The final
    and abstract modifiers are incompatible and wouldn't be used in the same declaration. You can see that if you don't
    want your class to be instantiated, you can either make it abstract or use a more restrictive access modifier on the
    class. Now, let's consider the Game class for a moment. It's abstract, which means I intend it to be subclassed. But
    what if I only wanted this class extended by my own games, and wanted to disallow it for all others? How would I go
    about doing this? I can't do it with access modifiers. Later, I'll be talking about the modular JDK, and controlling
    visibility of code with module techniques. There's still another method, and that's with a feature that became available
    in JDK 17, called Sealed Classes. I'll be discussing that feature, in the next lecture.
*/
//End-Part-7

public abstract class Game<T extends Player> {

    private final String gameName;
    private final List<T> players = new ArrayList<>();
    private Map<Character, GameAction> standardActions = null;

    public Game(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public Map<Character, GameAction> getStandardActions() {

        if (standardActions == null) {
            standardActions = new LinkedHashMap<>(Map.of(
                    'I',
                    new GameAction('I', "Print Player Info",
                            i -> this.printPlayer(i)),
                    'Q',
                    new GameAction('Q', "Quit Game",
                            this::quitGame)
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
}