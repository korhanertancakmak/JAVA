package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge.game.GameConsole;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge.game.ShooterGame;

//Part-1
/*

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
