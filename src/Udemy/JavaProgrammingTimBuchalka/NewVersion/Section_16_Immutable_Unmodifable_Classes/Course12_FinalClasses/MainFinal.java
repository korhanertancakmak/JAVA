package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course12_FinalClasses;

import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course12_FinalClasses.game.GameConsole;
import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course12_FinalClasses.pirate.PirateGame;


/*
class SpecialGameAction extends GameAction {
    public SpecialGameAction(char key, String prompt, Predicate<Integer> action) {
        super(key, prompt, action);
    }
}
*/

//Part-2
/*
        I want that to have a public static void main method, so I'll use the psvm shortcut to add that. The first thing
    I'll test, is whether I can extend the GameAction record. I'll include this as an additional class in the java source
    file, and not a nested class. I'll call it SpecialGameAction, and that'll extend GameAction.

                class SpecialGameAction extends GameAction {}

    IntelliJ is flagging this as an error, and if I hover over that, it says there's no default constructor available.
    That's maybe not the message you might have expected, so now I'll actually try to run this code, which will attempt
    to compile it first.

                java: cannot inherit from final .game.GameAction

    Here, I get java's message, cannot inherit from final GameAction. GameAction's a record, so I can't use it in any
    extends clause for another class. If I hover over GameAction again, I'll now select Create constructor matching super.
    And that will generate a constructor for me.

                Cannot inherit from final .game.GameAction

    Now I get the error from IntelliJ, that makes more sense, cannot inherit from final .game.GameAction. I'll revert that
    last change, removing that constructor. I'll change GameAction to Weapon, my enum from the last Challenge,
*/
//End-Part-2

/*
class SpecialGameAction extends Weapon {}
*/

//Part-3
/*
        And again, I get the same message from IntelliJ, about no default constructor. If I attempt to run it,

                java: cannot inherit from final .pirate.Weapon

    I get the same compiler error as I did with the record, just a different type. Ok, so hopefully there's no surprises
    there. We can't extend records or enums. I'll remove that statement. Next, I'll create another class in this source
    file, SpecialGameConsole, that extends GameConsole.
*/
//End-Part-3

/*
class SpecialGameConsole<T extends Game<? extends Player>> extends GameConsole<Game<? extends Player>> {

    public SpecialGameConsole(Game<? extends Player> game) {
        super(game);
    }
}
*/

//Part-4
/*
        Since GameConsole is generic, I want this to also be generic, so I'll declare it in the same way as GameConsole,
    but when I extend it, I specify the type argument, a Game, with the same wildcard for player.

                    class SpecialGameConsole<T extends Game<? extends Player>>
                                extends GameConsole<Game<? extends Player>> {}

    Ok, here, I'm getting the same error as when I tried to use a record in the extends class, but this time, the message
    is accurate in this case, so I'll just click on Create constructor matching super. I'll set up a local variable to
    use this new class in my main method.
*/
//End-Part-4

public class MainFinal {

    public static void main(String[] args) {

        //SpecialGameConsole<PirateGame> game = new SpecialGameConsole<>(new PirateGame("Pirate Game"));

//Part-5
/*
        I'll set the type argument to my Pirate Game, the name of the variable is simply game. I'll make ita new instance
    of the SpecialGameConsole, passing it the new Pirate Game. I can run this code.

                    Loading Data...
                    Finished Loading Data.

    I'll see the Loading Data, and Finished loading data messages from the PirateGame. I'll make the constructor on
    SpecialGameConsole, to have a private access modifier.

                            public SpecialGameConsole(Game<? extends Player> game) {
                                                    to
                            private SpecialGameConsole(Game<? extends Player> game) {

    I'm sure I showed you this before, but changing the access modifier on a constructor, lets you control who can create
    instances of your class. If you make it private, like I did here, nobody can instantiate an instance of this class.
    You can see the error in the main method of Main Final, that this class has private access. Making a constructor private
    has the same effect, as far as initializing new instances, as making your class abstract. I'll revert that change to
    the constructor, and next add the abstract modifier to my class.

                            class SpecialGameConsole
                                        to
                            abstract class SpecialGameConsole

    You can see I still have an error when I try to instantiate SpecialGameConsole, but this time the error is the class
    is abstract, it can't be instantiated. Again, I'll revert that last change, removing the abstract modifier from the
    SpecialGameConsole. I've also shown you that making a constructor package private, has much the same effect, outside
    of the package.

                            public class GameConsole
                                        to
                            class GameConsole

    If I go to my GameConsole class, and I remove the public access modifier. Jumping back to the SpecialGameConsole class,
    I've got an error on the constructor in that class, and it's that the constructor is not public. Making all your
    constructors package private is effectively making your class final, because your subclasses can't chain any of super's
    constructors at that point. If I go back to GameConsole and make that protected, as recommended by that IntelliJ popup.

                            class GameConsole
                                    to
                            protected class GameConsole

    That cleans up the problem for my subclass, and I can again create my sub class without errors. This does create a
    problem in my Main class, which instantiates a GameConsole. I'll comment that code out right now. I'll go back to
    the Game Console class, and next I'll add the final key word to the class declaration.

                            public class GameConsole
                                        to
                            public final class GameConsole

    Popping back over to our custom class, there's an error on this class,

                            class SpecialGameConsole<T extends Game<? extends Player>>
                                    extends GameConsole<Game<? extends Player>> {}

    and because I've already got a constructor declared, I get the message which really is the crux of the problem. Cannot
    inherit from final GameConsole. If you're not purposely planning to allow your classes to be extended, its best practice
    to make them final. I'm going to comment out my SpecialGameConsole class I'll change my main method
*/
//End-Part-5

        GameConsole<PirateGame> game = new GameConsole<>(new PirateGame("Pirate Game"));

//Part-6
/*
        To use the GameConsole and not the SpecialGameConsole. Because I made the constructor protected, I can't create
    a new GameConsole from this class, as we saw earlier in the Main class. I'll switch back to GameConsole and make that
    public again. My code compiles and runs as before.

        Making the class final doesn't prevent client code from creating instances of the class, or using the class. It
    just means no other class can extend it. Next, I want to jump over to the Game class.
*/
//End-Part-6
    }
}
