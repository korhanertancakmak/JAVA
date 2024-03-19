package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course13_SealedClasses;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course13_SealedClasses.pirate.Pirate;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course13_SealedClasses.pirate.PirateGame;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course13_SealedClasses.pirate.Weapon;

public class Main {

    public static void main(String[] args) {

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


    }
}
