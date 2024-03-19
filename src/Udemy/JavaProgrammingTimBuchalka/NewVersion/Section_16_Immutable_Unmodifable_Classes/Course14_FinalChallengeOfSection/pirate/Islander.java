package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate;

public final class Islander extends Combatant {

    public Islander(String name, Weapon weapon) {
        super(name);
        setCurrentWeapon(weapon);
    }
}

//Part-12
/*
        I'll add extends Combatant to that declaration. I get an error on this because I need to have a constructor. I'll
    generate that, selecting Create constructor matching super, picking just name for the field. I'm going to add a Weapon
    parameter here, as the second parameter in this constructor. I'll call the setCurrentWeapon after the call to super.
    I still have an error because Combatant is sealed. I'll choose to add Islander to the permits list. Next, I have to
    decide if this is going to be sealed, non-sealed or final. I'll take IntelliJ's hint, and make it final. I don't plan
    to extend it any further. Next, I'm just going to copy this class and paste it, naming it Soldier.
*/
//End-Part-12