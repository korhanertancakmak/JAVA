package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge;

import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge.pirate.*;
import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge.game.*;

//Part-1
/*                                   The Initializer Challenge(Pirate Invasion Game)
        In this challenge, You'll want to create a game using the Game Console, from the previous video. This game will
    be a Pirate Game.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        Weapon weapon = Weapon.getWeaponByChar('P');
        System.out.println("weapon = "+ weapon + ", hitPoints=" + weapon.getHitPoints() + ", minLevel=" + weapon.getMinLevel());

        var list = Weapon.getWeaponsByLevel(1);
        list.forEach(System.out::println);

//Part-7
/*
        I'll remove the code from the previous lecture, for the Shooter game test. First, I want to test getting a weapon
    by a character. I'll create a local variable weapon, and assign that the result of calling my getWeaponByChar method,
    with the letter P. And I'll print the weapon out, with its hit points and min level. Running that,

                    weapon = PISTOL, hitPoints = 50, minLevel = 1

    you can see I get a Pistol, its hit points is 50, and the minimum level equals 1. I'll test the next method, getWeaponsByLevel.
    I'll just use var here as the type, variable name is list, and that gets the result of calling my getWeaponsByLevel
    method, passing that 1. I'll print the weapons I get back.

                    weapon = PISTOL, hitPoints = 50, minLevel = 1
                    KNIFE
                    AXE
                    MACHETE
                    PISTOL

    And you can see, I can use four weapons at level 2, the knife, axe, machete and pistol. Ok, so all good so far. I'll
    work on the Pirate Class next.
*/
//End-Part-7

        Pirate tim = new Pirate("Tim");
        System.out.println(tim);

//Part-14
/*
        I'll create a new pirate named Tim. And I'll just print Tim out. Running that code,

                        ---> Bridgetown, Barbados
                        Pirate Tim {health=100, score=0, level=0, townIndex=0}
                        	townsVisited=[Bridgetown]

    I get my pirate's information. You can see it first print's the current town's data, in this case My Town, somewhere.
    Next, I get the pirate's game data, and a list of the towns the pirates visited, which of course is just My Town.
    Now, I'll create the Pirate Game class, and use the Game Console to play it. I'll create this class in the pirate
    package.
*/
//End-Part-14

        PirateGame.getTowns(0).forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        PirateGame.getTowns(1).forEach(System.out::println);

//Part-20
/*
        I'll add a call to get Towns, on the PirateGame class, and first pass it 0, for the first level. I'll print a
    separator line, and print the towns for the second level. Running that,

                    Loading Data...
                    Finished Loading Data.
                    ---> Bridgetown, Barbados
                    Pirate Tim {health=100, score=0, level=0, townIndex=0}
                        townsVisited=[Bridgetown]
                    Bridgetown, Barbados
                    Fitts Village, Barbados
                    Holetown, Barbados
                    -----------------------------------------------------
                    Fort-de-France, Martinique
                    Sainte-Anne, Martinique
                    Le Vauclin, Martinique

    first you can see the loading data and finished load data messages, from the static initializer code. I only see those
    printed once, because that's only called once. A static initializer's code is usually called the first time a class
    member is referenced, as I'm doing in the main method. After that, I get three towns for the first level, Bridgetown,
    Fitts Village, and Holetown, all in Barbados. The second level are all towns, on the island of Martinique. This code
    was all run without having an instance of the PirateGame created. You can imagine loading this data from a database
    or a file, and if it failed, you'd want to prevent anything further from happening. Next, I'll get back to the PirateGame.
*/
//End-Part-20

        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);

//Part-24
/*
       And set up a new variable called console, using var as its type, and instantiate a new Game Console. I'll pass a
    new Pirate Game to the new console. I'll add a player, returning the index and assigning it to playerIndex. And I'll
    play the game, using this current player's index, meaning it's his turn to play. if I run that,

                Welcome to The Pirate Game, Tim!
                ---> Bridgetown, Barbados
                Pirate Tim {score=0, health=100, level=0, townIndex=0}
                    townsVisited=[Bridgetown]
                Select from one of the following Actions:
                    Use KNIFE (K)
                    Use AXE (A)
                    Print Player Info (I)
                    Quit Game (Q)
                Enter Next Action: K<<

    I'll get prompted to enter my user name in there, so I'll type in Tim. Then I get, Welcome to the Pirate Game Tim.
    I can see I'm in Bridgetown, Barbados to start. Information about the player is printed, and here, I can see the player
    data, all initialized, health = 100, score = 0, level = 0, and townIndex = 0 as well. I've also got my custom menu
    of actions printed here, Use Knife if I enter a K, or use axe, if I enter A, as you can see. First, I'll use the
    Knife, so I'll enter K at the prompt,

                -------------------------------------------
                ---> Fitts Village, Barbados
                Pirate Tim {score=50, health=100, level=0, townIndex=1}
                    townsVisited=[Bridgetown, Fitts Village]
                Select from one of the following Actions:
                    Use KNIFE (K)
                    Use AXE (A)
                    Print Player Info (I)
                    Quit Game (Q)
                Enter Next Action: q<<

    and there you can see it called the use weapon method, but it knows I selected Knife to use, so that's kind of nice.
    If I enter I next, I get information, first about the town I'm in, and then about my own status. Right now, I'm not
    doing anything with health or score, or changing levels. I'll press q to quit,

                -------------------------------------------
                Sorry to see you go, Tim

    and the game says, Sorry to see you go Tim. If I add one more method to the Pirate class, it'll make this game a bit
    more interesting.
*/
//End-Part-24
    }
}

