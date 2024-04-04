package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate;

//Part-3
/*
        I'll use IntelliJ's replace function, to change all references to the worth field, to healthPoints, turning off
    the case matching, and selecting replaceAll. I'll just change the H in get health points to uppercase. Next, I'll
    remove the constants from that. I'll add new constants for some of the different things a person might encounter or
    run into, on an island. Alligator, will be minus 45, Aloe, will be 5, jellyfish will be -10, and pineapple, will be
    plus 10. Snake will be minus 25, a spring with freshwater will restore 25 points to our pirate's health, and lastly,
    let's say sun poison, will be minus 15. That's really it for the Feature enum. Next, I want to create my Combatant
    class. I'm going to copy the Pirate class, and paste that, naming the new copy Combatant.

*/
//End-Part-3

public enum Feature {

    ALLIGATOR(-45),
    ALOE(5),
    JELLYFISH (-10),
    PINEAPPLE(10),
    SNAKE(-25),
    SPRING(25),
    SUN_POISON(-15);

    private final int healthPoints;

    Feature(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
