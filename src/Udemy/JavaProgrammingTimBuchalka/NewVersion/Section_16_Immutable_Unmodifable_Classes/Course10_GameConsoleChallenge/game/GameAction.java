package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course10_GameConsoleChallenge.game;

import java.util.function.Predicate;

//Part-3
/*
        Again in the same game package. I only want three fields, key, prompt, and action. I've said that key should be
    a char, and prompt a String. Action is going to be a functional interface type. I could've chosen from several different
    ones, but I chose Predicate, because I want the game to continue until some condition is met. Any one of these game
    options could end the game. I'm using Integer as the type, because that let's me get a player from the players list,
    and execute methods on Player as part of a game's method. This will become clearer later, as I set up the data. Now,
    I want to build the Game class, in the same package.
*/
//End-Part-3

public record GameAction(char key, String prompt, Predicate<Integer> action) {
}
