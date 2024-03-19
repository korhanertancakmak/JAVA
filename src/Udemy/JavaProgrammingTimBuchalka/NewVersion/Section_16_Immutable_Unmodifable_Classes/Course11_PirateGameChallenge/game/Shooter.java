package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course11_PirateGameChallenge.game;

public record Shooter(String name) implements Player {

    boolean findPrize() {

        System.out.println("Prize found, score should be adjusted.");
        return false;
    }

    boolean useWeapon(String weapon) {

        System.out.println("You Shot your " + weapon);
        return false;
    }
}
