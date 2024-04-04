package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course13_SealedClasses.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//Part-1
/*
        JDK17 introduced a new modifier, "sealed", for our classes and interfaces. This modifier can be used for both outer
    types and nested types. When used, a "permits" clause is also required in most cases, which lists the allowed subclasses.
    Subclasses can be nested classes, classes declared in the same file, classes in the same package, or if using Java's
    modules, in the same module. I'll be covering Java's modules later in the course. What this means though, for this
    specific conversation, is that all our code so far, since JDK9, is part of what's called, the unnamed default module.
    Because of this, I can't use subclasses in the permits clause that are in other packages. A sealed class and its direct
    subclasses create a circular reference. I'll be explaining what that means in a bit. I want to go back to the Game
    class, in the Game Console project I've been using.

        I'll add the modifier sealed on the Game class. IntelliJ doesn't like that change. Hovering over that, I get the
    message, Sealed class permits clause must contain all subclasses. I have two subclasses already, ShooterGame and PirateGame,
    so I'll add a permits clause manually, and include both of those. The permits clause should be the last clause of the
    class declaration, listed after any extends or implements clauses. I've got errors though, one on PirateGame, and one
    on the ShooterGame in this clause. If I hover over PirateGame, I get the message, Class is not allowed to extend sealed
    class from another package. Our PirateGame class is in the pirate package, so this isn't permitted, but ShooterGame
    is so let's see what that error says. I'll hover over that. This message tells me, that all sealed class subclasses,
    must either be final, sealed or non-sealed. Actually, If I try to run my MainFile main method here, I get the compiler
    error that is a bit more enlightening, that the code is all in the unnamed module. If I'm going to use the sealed class
    functionality, I have to do it for a class in the same package as Game. This message confirms that our code is in an
    unnamed module, as I mentioned before. Now, I'm going to copy my Game class, and create a new class from that, also
    in the game package. I want to call the copy, SealedGame. I'll go back to the original Game class and revert the last
    changes, removing the sealed modifier and the permits clause. Now, going to my new SealedGame class,

        I'll first remove the PirateGame from the permits clause. Because PirateGame is in another package, I can't include
    it here. I can include the ShooterGame, but first I need to change the declaration to extend the SealedGame, and not
    Game. When I do that, I get the error, all sealed, non-sealed or final modifiers expected. IntelliJ is suggesting I
    make ShooterGame final, and I'll do that. This clears up my errors for the ShooterGame, and Sealed Game. You can't
    make a class sealed, without first adding a modifier to a subclass, if you haven't already included one of the three
    valid ones. Are you confused by these interdependencies?

        Using the sealed keyword, requires the parent class to "declare it's subclasses", using a "permits" clause. This
    means the parent class has to know about every direct subclass, and these have to exist, in the same package in this
    case. In addition, the sealed keyword puts a requirement on all the subclasses that were declared in the permits clause.
    It requires each subclass to declare one of the three valid modifiers for a class extending a sealed class. These are
    "final", "sealed" or "non-sealed".

                                       ____________________________________
                                       | sealed class X permits A, B, C   |
                                       |__________________________________|                                                             NOT
                                       |__________________________________|                                                          PERMITTED
                                                         ↑→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→XXXXXXXXXXXXX→→→→→↓
                    ↓←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←↑→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→↓                             _________________↓___________
         ___________↓___________        _________________↑__________________          _________________↓___________                 | class D extends X         |
         | final class A       |       | sealed class B permits E         |          | non-sealed class C        |                  |___________________________|
         |_____________________|       |__________________________________|          |___________________________|                  |___________________________|
         |_____________________|       |__________________________________|          |___________________________|
                    X                                   ↑                                           ↑
                    X                                   ↑                                   ↑→→→→→→→↑←←←←←←←←↑
                   None                        _________↑___________                        ↑                ↑
                                               | final class E     |                   _____↑____       _____↑____
                                               |___________________|                   |class F |       |class G |
                                               |___________________|                   |________|       |________|
                                                        X
                                                        X
                                                       None

        On this diagram, I have a parent class X, declared as a sealed class, and permitting only classes A, B and C to
    subclass it. For that reason class D, which may exist and extends X, but won't compile. You'll have to either add D
    to the permits clause on X, or remove D from the hierarchy in some way. As I've stated several times, all subclasses
    declared in the permits clause, must be declared as final, sealed or non-sealed. Declaring a class final, means no
    other subclasses can extend that class, as I show with class A, on this diagram. A subclass declared with a sealed
    modifier, shown here with class B, must in turn use a permits clause. Its subclasses in turn, have to use one of the
    three valid modifiers. Lastly, a subclass can use the non-sealed modifier, as shown with class C. This means it's
    basically unsealing itself for all it's subclasses. This one at first sounds like a bit of a mystery. Why would you
    allow subclasses to unseal, what you said should be sealed?. We'll look at each of these in turn. I think the scenario
    we've currently set up is the easiest to understand. Basically, we're allowing a few classes, in the same package,
    to extend our special SealedGame, and that's where the subclassing stops. I'm going to go to my ShooterGame,
*/
//End-Part-1

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