package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course12_FinalClasses;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course12_FinalClasses.pirate.*;

//Part-1
/*                                              Final Classes

        In earlier lectures, I talked about final methods which can't be overridden, and final variables which need to be
    initialized but then can't be reassigned. Final Classes are a similar concept. Using the final keyword on a class means
    it can't be extended. You declare a class "final" if its definition is complete, and no subclasses are desired or required.
    Enums and Records are final classes, as I showed you, when I used the java class disassembler tool on those. I've also
    showed you examples of how subclasses can take advantage of mutable fields on parent classes, if the parent classes
    aren't implementing defensive code. One of the easiest ways to prevent this, is to make your class final. I'll be using
    the GameConsole project from the last challenges, in this lecture.

        In this project I already have a record, GameAction, and if you followed along in the last challenge, you know I
    have a Weapon enum in the pirate package. I'm going to create another class, and call this one MainFinal.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

//        var console = new GameConsole<>(new ShooterGame("The Shootout Game"));
//
//        int playerIndex = console.addPlayer();
//        console.playGame(playerIndex);

        Weapon weapon = Weapon.getWeaponByChar('P');
        System.out.println("weapon = "+ weapon + ", hitPoints=" +
                weapon.getHitPoints() + ", minLevel=" + weapon.getMinLevel());

        var list = Weapon.getWeaponsByLevel(1);
        list.forEach(System.out::println);

        Pirate tim = new Pirate("Tim");
        System.out.println(tim);

        PirateGame.getTowns(0).forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        PirateGame.getTowns(1).forEach(System.out::println);

//        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
//        int playerIndex = console.addPlayer();
//        console.playGame(playerIndex);

    }
}
