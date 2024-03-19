package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate;

public final class Soldier extends Combatant {

    public Soldier(String name, Weapon weapon) {
        super(name);
        setCurrentWeapon(weapon);
    }
}

//Part-13
/*
        If I hover over the error, on Combatant, I'll pick, Add Soldier to permits list. Ok, so now I have three subclasses
    for my sealed Combatant class. If I examine that class, you can see that IntelliJ did indeed add Islander and Soldier
    to the permits clause. Now, I'll create a Town record, and start making the pirate game more interesting. I'll create
    a new record called town in the pirate package.
*/
//End-Part-13
