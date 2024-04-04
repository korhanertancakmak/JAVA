package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge.game.GameConsole;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge.game.ShooterGame;

//Part-1
/*
        In this video, I'll set up some code I plan to use in the remaining challenges and lectures. I'll again set this
    up as an independent challenge, if you want to try it on your own. This code will be using generic types and lambda
    expressions, and will be available as a resource in the upcoming lectures. The game console will be a container to
    execute some scanner code, to drive a text based game's play. It'll collect a user name, creating a player from that.
    It will start a while loop, displaying a menu of options for a user, then solicit a user's response. It'll execute
    a game or player method, based on a user's selected action, and end the game if the action indicates the game is over.

                                            GameConsole Class

        This GameConsole class is a container for a game, so it needs a type argument for a game field. It should also
    have static scanner field, which uses System.in to get keyboard input. You should implement two methods on this class.
    The addPlayer method will prompt a user for their name, read in the response from the scanner, and delegate to the
    Game's addPlayer method. The playGame method will display all available game options, soliciting user input in a
    while loop, then calling execute the action associated to the input. The constructor should take a new instance of
    a Game.

                                          The GameAction Record

        You'll also want to create a "GameAction" record with three fields. There should be a key, a char field, which
    is the key a user would press to select the action. Next, include a prompt, which is displayed to the user to describe
    the specific action. There should be an action field, for a lambda expression or method reference. I'll be using
    Predicate with an Integer type argument. The integer is the player's index in the player list. A predicate always
    returns a boolean result. This will be used to continue or end the play.

                                          The Player Interface

        Next, you'll want to create a Player interface. The Player interface will have a single abstract method, name,
    that returns a String. A game's player should implement this type. Use this type as a type parameter for Game.

                                            The Game Class

        The "Game" class should be "abstract" and "generic", the type parameter should be a type of Player. This class
    should have three fields, a gameName, a list of players, and a map of game actions. Your "Game" class should have "two
    abstract methods" you want any custom game to implement.

       - The method createNewPlayer will return a new instance of the type used for a player.
       - The method getGameActions will return a map that associates a character a user would enter, with a prompt and
         an action to be taken.

    For example, if a user selects 'Q', this should map to a GameAction record, that has "QuitProgram" as the prompt,
    and a lambda expression, calling the quit method on the game, with a method reference, this::quitGame.

        This class should have "concrete methods", some of which might be overridden by subclasses. The addPlayer method
    takes a string for name, creates a player instance, adding it to the Game's player list, and returns that index. The
    executeGameAction will call the Predicate's test method, on the lambda expression in the action field, returning the
    boolean result. The printPlayer and quitGame methods, are the methods referenced in the GameAction records. Include
    getter and helper methods as appropriate. Finally, create your own simple game, and player type, and test some of
    the methods on the GameConsole.

                                    _______________________________
                                    | <<Interface>>               |
                                    |    Player                   |
                                    |_____________________________|
                                    | bane(): String              |
                                    |_____________________________|
                                                ↑
                                                ↑
               _______________________________________________________________________
               | Game<T extends Player>                                              |
               |_____________________________________________________________________|
               | gameName: String                                                    |
               | players: List<Player>                                               |
               | standardActions:Map<Character,GameAction> standardActions           |
               |_____________________________________________________________________|          _______________________________
               | abstract CreateNewPlayers(String name): T                           |          | GameAction                  |
               | abstract getGameActions(int playerIndex): Map<Character,GameAction> |          |_____________________________|
               | ----------------------------------------------                      |<>--------| key: char                   |
               | addPlayer(String name): int                                         |          | prompt: String              |
               | executeGameAction(int player, GameAction action): boolean           |          | action: Predicate<Integer>  |
               | printPlayer(int player): boolean                                    |          |_____________________________|
               | quitGame(int player): boolean                                       |
               |_____________________________________________________________________|
                                                ↑
                                                ↑
                           _________________________________________________
                           | GameConsole<T extends Game<? extends Player>> |
                           |_______________________________________________|
                           | game: T                                       |
                           | static scanner: Scanner                       |
                           |_______________________________________________|
                           | addPlayer(): int                              |
                           | playGame(int playerIndex)                     |
                           |_______________________________________________|

        Here is a model of the types I just explained, which I'll be building now. I've created the usual Main class and
    main method. I'm going to start with the Player interface, in game package.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        var console = new GameConsole<>(new ShooterGame("The Shootout Game"));

        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);

//Part-18
/*
        and set up a new game console. I'll take advantage of type inference, and use var, call the variable console, and
    assign that a new Game Console, passing a new shooter game, with the game title. I'll call console add player, and
    pass the index back to a player index variable. Lastly, I'll call play game on console, passing it that player index.
    If I run this,

                    Enter your playing name:

    I can see the console prompting me for my player name. I'll enter Tim here,

                    Welcome to The Shootout Game, Tim!
                    Select from one of the following Actions:
                        Use your gun (S)
                        Find Prize (F)
                        Quit Game (Q)
                        Print Player Info (I)
                    Enter Next Action:

    and now I see the welcome message, and I can see all the options available, so that's kind of neat. I'll try finding
    a prize, so F.

                    -------------------------------------------
                    Prize found, score should be adjusted.
                    -------------------------------------------
                    Select from one of the following Actions:
                        Use your gun (S)
                        Find Prize (F)
                        Quit Game (Q)
                        Print Player Info (I)
                    Enter Next Action:

    Now I get the message, that a prize was found, and score should be adjusted, and my options are printing again. Let
    me try shooting, S.

                    -------------------------------------------
                    You Shot your pistol
                    -------------------------------------------
                    Select from one of the following Actions:
                        Use your gun (S)
                        Find Prize (F)
                        Quit Game (Q)
                        Print Player Info (I)
                    Enter Next Action:

    And now the message, you shot your pistol. Now, I'll select I, to get the shooter information.

                    -------------------------------------------
                    Shooter[name=Tim]
                    -------------------------------------------
                    Select from one of the following Actions:
                        Use your gun (S)
                        Find Prize (F)
                        Quit Game (Q)
                        Print Player Info (I)
                    Enter Next Action:

    That just print's my record's default string representation, so you can see the player's a shooter, with my name. And
    now I'll quit, q.

                    -------------------------------------------
                    Sorry to see you go, Tim

    That does quit, so that's good, and the game tells me it's sorry to see me go. That's our game console class. I'm
    going to use this in the next couple of lectures. The next lecture is a game challenge, and you'll use this console
    to run it. The game will test your knowledge of enum constructors, and both instance and static initializers.
*/
//End-Part-18

    }
}
