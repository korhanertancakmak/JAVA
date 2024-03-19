package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection;

import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.game.GameConsole;
import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate.PirateGame;
import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate.Town;

public class Main {

    public static void main(String[] args) {
/*
        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);
*/

//Part-11
/*
        Running the Main class's main method,

                        Loading Data...
                        Finished Loading Data.
                        Enter your playing name: Tim

    I'll enter Tim for the player name.

                        Welcome to The Pirate Game, Tim!
                        Tim
                        Select from one of the following Actions:
                            Use KNIFE (K)
                            Use AXE (A)
                            Find Loot (F)
                            Experience Town Feature (X)
                            Print Player Info (I)
                            Quit Game (Q)
                        Enter Next Action: I

    I'll select I,

                        -------------------------------------------
                        ---> Bridgetown, Barbados
                        Tim {health=100, score=0, level=0, townIndex=0}
                            townsVisited=[Bridgetown]
                        -------------------------------------------
                        Tim
                        Select from one of the following Actions:
                            Use KNIFE (K)
                            Use AXE (A)
                            Find Loot (F)
                            Experience Town Feature (X)
                            Print Player Info (I)
                            Quit Game (Q)
                        Enter Next Action: q

    that gives me the current town, and Tim's game data, as it did before. The basics are still working, so that's good,
    and I'll select q

                        -------------------------------------------
                        Sorry to see you go, Tim

    to quit out of that. Next, I'll create two more classes that extend player. First, I want a class named Islander.
*/
//End-Part-11

/*
        Town bridgetown = new Town("BridgeTown","Barbados", 0);
        System.out.println(bridgetown);
        System.out.println(bridgetown.information());
*/

//Part-18
/*
        And comment the last three lines out momentarily. I'll set up a town, the variable will be Bridgetown, and the
    town name is Bridgetown on the island of Barbados, and the level is zero. I'll print out the town, bridgetown. I'll
    print the town's information next. Running that code,

                    BridgeTown, Barbados
                    Town: BridgeTown, Barbados
                        loot=[SILVER_COIN, PEARL_NECKLACE]
                        features=[SNAKE, SPRING, ALLIGATOR]
                        opponents=[Joe]

    you can see I've got a town with 2 pieces of loot, 3 features, some good, some bad, and a single opponent. If I run
    it again,

                    BridgeTown, Barbados
                    Town: BridgeTown, Barbados
                        loot=[PEARL_NECKLACE, SILVER_COIN]
                        features=[SNAKE, SPRING, JELLYFISH]
                        opponents=[Joe]

    I get a different list of loot and features. The opponent won't change, until I change levels. I'll remove the last
    three statements I added, but leave the last three statements commented out. Next, I have to change my pirate game,
    to use a nested list of town, not just Strings.
*/
//End-Part-18

        //PirateGame.getTowns(0).forEach(t -> System.out.println(t.information()));
        //System.out.println("------------------------------------------------------");
        //PirateGame.getTowns(1).forEach(t -> System.out.println(t.information()));

//Part-22
/*
        And in here, I'll change the method reference on line 26, to a lambda. This will take t as an argument, standing
    for the town, and this will print the town's information method instead. Running this,

                        Town: Bridgetown, Barbados
                            loot=[PEARL_NECKLACE, GOLD_BAR]
                            features=[SNAKE, SPRING, SUN_POISON]
                            opponents=[Joe]
                        Town: Fitts Village, Barbados
                            loot=[GOLD_BAR, GOLD_COIN]
                            features=[JELLYFISH, PINEAPPLE, SUN_POISON]
                            opponents=[Joe]
                        Town: Holetown, Barbados
                            loot=[GOLD_BAR, PEARL_NECKLACE]
                            features=[SNAKE, PINEAPPLE, SUN_POISON]
                            opponents=[Joe]
                        ------------------------------------------------------
                        Town: Fort-de-France, Martinique
                            loot=[PEARL_NECKLACE, SILVER_COIN, GOLD_COIN]
                            features=[JELLYFISH, ALOE, SUN_POISON, PINEAPPLE]
                            opponents=[Joe, John]
                        Town: Sainte-Anne, Martinique
                            loot=[GOLD_BAR, SILVER_COIN, GOLD_RING]
                            features=[JELLYFISH, ALLIGATOR, PINEAPPLE, ALOE]
                            opponents=[Joe, John]
                        Town: Le Vauclin, Martinique
                            loot=[GOLD_RING, SILVER_COIN, PEARL_NECKLACE]
                            features=[SNAKE, SPRING, SUN_POISON, ALOE]
                            opponents=[Joe, John]

    there are six towns listed, all with random loot and features. At level one, each town has only two items of loot, and
    three features, and one opponent. For the level two towns, all on Martinique, there are three treasures, four features,
    and two opponents. Getting back to the pirate class, I'll change the visitTown method.
*/
//End-Part-22

        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);

//Part-30
/*
        Running this,

                    Loading Data...
                    Finished Loading Data.
                    Enter your playing name: Tim

    I get prompted for my name, so I'll type in Tim.

                    Welcome to The Pirate Game, Tim!
                    Tim
                    Select from one of the following Actions:
                        Use KNIFE (K)
                        Use AXE (A)
                        Find Loot (F)
                        Experience Town Feature (X)
                        Quit Game (Q)
                        Print Player Info (I)
                    Enter Next Action: A

    You can see I can choose from the two weapons, and now I've got find loot, and Experience Town Feature. I want to use
    a weapon to see what happens, so I'll enter A.

                    -------------------------------------------
                    Tim used the AXE and missed!
                    Joe used the KNIFE and missed!
                    -------------------------------------------
                    Tim
                    Select from one of the following Actions:
                        Use KNIFE (K)
                        Use AXE (A)
                        Find Loot (F)
                        Experience Town Feature (X)
                        Quit Game (Q)
                        Print Player Info (I)
                    Enter Next Action: F

    I get the information that I used the axe. Since this is random, I may or may not have hit my opponent, and he may or
    may not have hit me. The health values are printed out, if either one of us hits the other. Next I'll select F, to
    find some loot.

                    -------------------------------------------
                    Found SILVER_COIN!
                    Tim's score is now 5
                    -------------------------------------------
                    Tim
                    Select from one of the following Actions:
                        Use KNIFE (K)
                        Use AXE (A)
                        Find Loot (F)
                        Experience Town Feature (X)
                        Quit Game (Q)
                        Print Player Info (I)
                    Enter Next Action: X

    There I found one of the loot items, and my score changed, so it's printed out. Next I'll try X. Since this too is
    random, this might be a good thing for my health, or it might not be.

                    -------------------------------------------
                    Ran into PINEAPPLE!
                    Tim's health is now 100
                    -------------------------------------------
                    Tim
                    Select from one of the following Actions:
                        Use KNIFE (K)
                        Use AXE (A)
                        Find Loot (F)
                        Experience Town Feature (X)
                        Quit Game (Q)
                        Print Player Info (I)
                    Enter Next Action: F

    This shows me what I ran into, and what my health is after that experience. I'll select F this time,

                    -------------------------------------------
                    Found GOLD_BAR!
                    Tim's score is now 505
                    Sailing to next town! Bonus: 50 points!
                    -------------------------------------------
                    Tim
                    Select from one of the following Actions:
                        Use KNIFE (K)
                        Use AXE (A)
                        Find Loot (F)
                        Experience Town Feature (X)
                        Quit Game (Q)
                        Print Player Info (I)
                    Enter Next Action: I

    and this time, I should see that I'm sailing to a new town. Level one towns only have two items to find. I'll press
    I to see what's going on.

                    -------------------------------------------
                    ---> Fitts Village, Barbados
                    Tim {score=555, health=100, level=0, townIndex=1}
                        townsVisited=[Bridgetown, Fitts Village]
                    -------------------------------------------
                    Tim
                    Select from one of the following Actions:
                        Use KNIFE (K)
                        Use AXE (A)
                        Find Loot (F)
                        Experience Town Feature (X)
                        Quit Game (Q)
                        Print Player Info (I)
                    Enter Next Action: X

    I get the town I'm in, and my data, as well as all the towns I visited. At this point I should have 3 more experiences
    for this town, so I'll select X three times, and hope it doesn't kill me first.

                    -------------------------------------------
                    Ran into SNAKE!
                    Tim's health is now 20
                    -------------------------------------------
                    Tim
                    Select from one of the following Actions:
                        Use KNIFE (K)
                        Use AXE (A)
                        Find Loot (F)
                        Quit Game (Q)
                        Print Player Info (I)
                    Enter Next Action:

    Once I use up the experiences for the town, the X option goes away, so I don't see that as an option now. I won't see
    it again until I sail to another town. I could keep playing this game, to try to kill off my opponents. If I play long
    enough, and use my axe, on the first level, I should in theory be able to kill Joe off. I'll keep using the axe and
    see if I can do that. Once I eliminate all my opponents, I don't have the option to use any of the weapons. I'll quit
    the game here. In the next section, I'm going to introduce you to Java streams, which will make collection processing
    a lot easier and fun.
*/
//End-Part-30
    }
}
//Part-1
/*                                              The Final Section Challenge
        In this challenge, you'll be adding functionality to the Pirate Game we've been working on for several lectures.
    You'll be adding hidden treasure, town features, and opponents. A town feature, will affect the health of your pirate.
    For example, if your pirate runs into an alligator, you may want to subtract health points. If he finds a fresh water
    spring, you'll probably increase his health. As your pirate finds loot, each item should increase his score. Each town
    will have different loot, and different features.

        In this challenge, I want you to abstract some of the functionality of your Pirate player to a Combatant class.
    This means you should make a copy of Pirate, calling it Combatant, and placing it in the pirate package. This class
    should be abstract, and contain most of Pirate's fields (like name, weapon, and game data), as well as methods related
    to these fields. The Pirate class should extend Combatant. You'll also create a couple of other Combatant classes,
    such as Islander and Soldier. You should seal the Combatant class. For the town loot and features, You'll create two
    enums, both with constructors. The first, Loot, defines pirate treasure such as gold coins or pearl necklaces, each
    with its own worth, that when found, will increase your pirate's score. The second enum, Feature, should describe
    some town features, that will either add to the pirate's health, or subtract from it. Features should have a health
    value (positive or negative), so that when a pirate runs into this feature, his health is adjusted accordingly. Some
    examples of features might be an Alligator with a large negative health value, or a Pineapple with a small positive
    adjustment. Use a record to create a Town, with the fields: name, island, level, loot, features, and opponents. The
    last three can be any Collection. Create a compact constructor, to initialize randomized lists of loot and features.
    These lists should contain only a portion of the ones available on each enum. You should also add an opponent or two,
    in this constructor. Next, you'll add a custom constructor that takes a name, and additional items to be tracked, in
    the game data map. At least one of the opponents should use their weapon, when your pirate uses his. Randomize part
    of the use weapon method, so that opponents only occasionally hit each other, and deduct points from each's health
    accordingly. Change PirateGame's loadData method, to load up a List of Town, instead of Strings. Change your game's
    menu items to include a Find Loot option, and an Experience Town Feature option. Change your code so that a pirate
    only visits a new town, after they've found all the loot in the current town.


        Ok, so I'm back in the GameConsole Project, and the first thing I'll do is, create the new enums. All my types
    will be created, in the pirate package. The first enum is Loot,
*/
//End-Part-1


