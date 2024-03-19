package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge.game;

import java.util.Scanner;

//Part-9
/*
        This class has one type argument, so T, and that extends Game. Since Game is also generic, I need to identify valid
    ranges of types for this Game. Here, I'll use a wildcard, to specify the type, since I'm using the Game, and not just
    declaring it. I'll use an upper bound wildcard, so extends Player there.
*/
//End-Part-9

public class GameConsole<T extends Game<? extends Player>> {

//Part-10
/*
        This class has one instance field, the game, type T, and that'll be private and final. I'll make the scanner private
    static and final here. I'll generate a constructor, and pass the game instance, so I'll pick game there.
*/
//End-Part-10

    private final T game;
    private static final Scanner scanner = new Scanner(System.in);

    public GameConsole(T game) {
        this.game = game;
    }

    public int addPlayer() {

        System.out.print("Enter your playing name: ");
        String name = scanner.nextLine();

        System.out.printf("Welcome to %s, %s!%n".formatted(game.getGameName(), name));
        return game.addPlayer(name);
    }

//Part-11
/*
        Next, I want to code the addPlayer method, which has to get the player's name from the console. This will return
    an int, again the player's index in the player list. First, this should prompt the user for their playing name. Then
    wait for the user to respond. Once I have the name, I'll print out a welcome message. Then I'll return what comes back
    from the game add player method.
*/
//End-Part-11

    public void playGame(int playerIndex) {

        boolean done = false;
        while (!done) {
            var gameActions = game.getGameActions(playerIndex);
            System.out.println("Select from one of the following Actions: ");
            for (Character c : gameActions.keySet()) {
                String prompt = gameActions.get(c).prompt();
                System.out.println("\t" + prompt + " (" + c + ")");
            }
            System.out.print("Enter Next Action: ");

            char nextMove = scanner.nextLine().toUpperCase().charAt(0);
            GameAction gameAction = gameActions.get(nextMove);

            if (gameAction != null) {
                System.out.println("-------------------------------------------");
                done = game.executeGameAction(playerIndex, gameAction);
                if (!done) {
                    System.out.println("-------------------------------------------");
                }
            }
        }
    }

//Part-12
/*
        After this, I want to implement playGame. This is public and void, and takes a player index. I want to set a local
    variable, done, to false, My while loop will keep going until this becomes true. I'll get the game Actions from the
    game. These may change for a player as play continues, so I'll call this inside the while loop. The first thing I need
    to do, in the loop, is show the available options. I'll print out a statement that these are the actions to choose
    from. I'll loop through the keys of my game actions map. I'll get the prompt. I'll print that prompt, and the key,
    which is the keystroke that will execute that action. After all the options are listed, I'll prompt the user to pick
    an action.

        Next, I'll get the letter from the user, you've seen me do this multiple times, I'll use nextLine on scanner,
    making it upper case, and getting the first character. The input is the key, to get the action from the map. If I find
    game action in the map for the key entered, I'll first print a separator line. Then I execute game action on that.
    If that returns a false, it means the game isn't yet over. I'll print another separator line so it separates' the
    action that just occurred, from the options to be picked from. Ok, that's my generic game console. I'll quickly test
    this, by creating a shooter game, that will have shooters as players.
*/
//End-Part-12
}
