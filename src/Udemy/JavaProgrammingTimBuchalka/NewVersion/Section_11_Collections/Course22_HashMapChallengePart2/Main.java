package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course22_HashMapChallengePart2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


//Part-10
/*
        Ok, next, the bonus part, I want to let players customize the game's locations. Let's say I want my lake to have
    a different description, and I want to add some additional destinations from the Lake, in addition to the two that
    are already set up. Let me show this to you this. Lake is already in my map, but I want to customize it, first by
    describing it, naming it Lake Tim, and then adding two additional destinations from there, a cave to the north, and
    an ocean to the east. The cave and ocean will be additional board locations. Right now, from the lake, I can only go
    west to the forest, or south to the well house.

        To customize the game, I'll add a text block that will have my extra locations, in the same format as the default
    data. I'll include a lake, which will be a duplicate key, and this lake will be Lake Tim. It'll still have forest to
    the West, and well house to the south, but I'll include an ocean to the east, and cave to the north. And I'll set up
    the ocean location, and the cave location here too. I want to pass this text string, myLocations, to the Adventure
    Game constructor. To make it easier to get the locations, I'll start at the lake location Running that,

                *** You're standing at the edge of Lake Tim ***
                    From here, you can see:
                    • A cave to the North (N)
                    • A ocean to the East (E)
                    • A forest to the West (W)
                    • A well house to the South (S)
                Select Your Compass (Q to quit) >>

    you can see that when I go to the lake, it now says I'm standing at the edge of Lake Tim, and my destinations include
    an ocean to the east, and a cave to the north. Let's check out the cave, so I'll enter an n there.

                *** You're standing at the mouth of Tim's bat cave ***
                    From here, you can see:
                    • A forest to the West (W)
                    • A ocean to the East (E)
                    • A lake to the South (S)
                Select Your Compass (Q to quit) >>

    Now I'm standing at the mouth of Tim's bat cave. Ok, that's kind of fun. For good measure, I'll check out the ocean
    to the East,

                *** You're standing on Tim's beach before an angry sea ***
                    From here, you can see:
                    • A lake to the West (W)
                Select Your Compass (Q to quit) >>

    and now I'm on Tim's beach, before an angry sea. I'll press Q to quit, and that will end the adventure game challenge.
    In the next lecture, I'll be talking about two other classes that implement the Map interface, and these are the
    LinkedHashMap and the TreeMap.
*/
//End-Part-10

        String myLocations = """        
                lake,at the edge of Lake Tim,E:ocean,W:forest,S:well house,N:cave
                ocean,on Tim's beach before an angry sea,W:lake
                cave,at the mouth of Tim's bat cave,E:ocean,W:forest,S:lake
                """;

//Part-3
/*
        I'll create an AdventureGame called game. That's a new AdventureGame, with nothing passed to the constructor, so
    no customizations. That's all I have to do to test this part of the code, because remember I included a print statement
    after the locations were added, in the load locations method. Running that code,

                hill:Location[description=on top of hill with a view in all directions, nextPlaces={N=forest, E=road}]
                well house:Location[description=inside a well house for a small spring, nextPlaces={N=lake, W=road, S=stream}]
                forest:Location[description=at the edge of a thick dark forest, nextPlaces={E=lake, S=road}]
                road:Location[description=at the end of the road, nextPlaces={N=forest, E=well house, W=hill, S=valley}]
                stream:Location[description=near a stream with a rocky bed, nextPlaces={N=well house, W=valley}]
                valley:Location[description=in a forest valley beside a tumbling stream, nextPlaces={N=road, E=stream, W=hill}]
                lake:Location[description=by an alpine lake surrounded by wildflowers, nextPlaces={W=forest, S=well house}]

    you can see my board locations map. First, I've printed out the key, a one or two word description, then I've got the
    Location object, which has a description, and a nextPlaces nested Map. And you can see for hill, for example, a player
    can go east to the road, or north to the forest. That's a good start, but now it's time to implement the code, to
    actually play this game. To start, I want a method on the AdventureGame class,
*/
//End-Part-3

        //AdventureGame game = new AdventureGame();
        AdventureGame game = new AdventureGame(myLocations);

//Part-8
/*
        and fire this game up. I'll execute play, on my game to start, and pass it the road, the starting position. If I
    run that,

                *** You're standing at the end of the road ***
                    From here, you can see:
                    • A forest to the North (N)
                    • A well house to the East (E)
                    • A hill to the West (W)
                    • A valley to the South (S)
                Select Your Compass (Q to quit) >>

    you can see what gets printed. I get the full description of the road, from the data provided, And I get the directions
    I can choose from, and a short description of what lies in each of those directions. I can change my starting position,
    so I'll go up, and change road to lake in my main method.

                *** You're standing by an alpine lake surrounded by wildflowers ***
                    From here, you can see:
                    • A forest to the West (W)
                    • A well house to the South (S)
                Select Your Compass (Q to quit) >>

    And now I get a different output Ok, so that's the set up. I'm going to revert that last change, so I do start with
    the road. Next I have to get the response from my player.
*/
//End-Part-8

        //game.play("road");
        game.play("lake");

//Part-9
/*
        I'll set up a scanner, then go into a while loop. Inside this loop, I'm going to call nextLine on my scanner, and
    then chain some string methods to that. I'll trim the data I get from the user, I'll make it upper case, and I'll get
    the first character of whatever they entered. I'm assigning the result of all this to a variable named direction.
    Next, I want to check if the user pressed q, or any text that started with a q, and if they did, I'll break out of
    the loop, and end the game. Otherwise I want to call move on the game, passing it the direction picked by my player.
    Ok, let me run this.

                    *** You're standing at the end of the road ***
                        From here, you can see:
                        • A forest to the North (N)
                        • A well house to the East (E)
                        • A hill to the West (W)
                        • A valley to the South (S)
                    Select Your Compass (Q to quit) >>

    Now imagine that you haven't seen the challenge info with the map on it. You don't know what lies ahead. So which way
    would you go? I guess I'll go South first to the valley, and I'll just enter S at the prompt.

                    *** You're standing in a forest valley beside a tumbling stream ***
                        From here, you can see:
                        • A road to the North (N)
                        • A stream to the East (E)
                        • A hill to the West (W)
                    Select Your Compass (Q to quit) >>

    And now I'm standing in a forest valley beside a tumbling stream. Let's go check out the stream, so I'll go "east",
    I'll type east out in lowercase.

                    *** You're standing near a stream with a rocky bed ***
                        From here, you can see:
                        • A well house to the North (N)
                        • A valley to the West (W)
                    Select Your Compass (Q to quit)

    That still works, and I get you're standing near a stream with a rocky bed. Now let's go to the well house, north,
    so I'll enter just a lower case n here,

                    *** You're standing inside a well house for a small spring ***
                        From here, you can see:
                        • A lake to the North (N)
                        • A road to the West (W)
                        • A stream to the South (S)
                    Select Your Compass (Q to quit) >>

    and there, you can see I'm inside a well house for a small spring. And now I'll quit. There you go, you have the
    beginnings of a text based game. The real game lets you find things in each location, and unlock locations, and so on,
    so if this inspires you, you can continue to build on your game.
*/
//End-Part-9

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String direction = scanner.nextLine().trim().toUpperCase().substring(0, 1);
            if (direction.equals("Q")) break;
            game.move(direction);
        }
    }
}
