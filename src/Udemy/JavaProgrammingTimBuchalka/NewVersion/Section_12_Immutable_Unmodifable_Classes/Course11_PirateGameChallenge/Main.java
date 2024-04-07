package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge.pirate.*;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge.game.*;

public class Main {

    public static void main(String[] args) {

        Weapon weapon = Weapon.getWeaponByChar('P');
        System.out.println("weapon = "+ weapon + ", hitPoints=" + weapon.getHitPoints() + ", minLevel=" + weapon.getMinLevel());

        var list = Weapon.getWeaponsByLevel(1);
        list.forEach(System.out::println);


        Pirate tim = new Pirate("Tim");
        System.out.println(tim);


        PirateGame.getTowns(0).forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        PirateGame.getTowns(1).forEach(System.out::println);


        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);
    }
}

