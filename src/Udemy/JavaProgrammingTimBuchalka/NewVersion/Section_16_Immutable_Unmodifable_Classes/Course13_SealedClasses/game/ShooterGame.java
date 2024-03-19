package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course13_SealedClasses.game;

import java.util.LinkedHashMap;
import java.util.Map;

//Part-2
/*
        And revert it, so that it again extends the unsealed Game class. I'll leave the final modifier there. Next, I'll
    just delete the SealedGame class. I'll create a new class in sealed package, calling it Special Abstract Class.
*/
//End-Part-2

public final class ShooterGame extends Game<Shooter> {

    public ShooterGame(String gameName) {
        super(gameName);
    }

    @Override
    public Shooter createNewPlayer(String name) {
        return new Shooter(name);
    }

    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {

        var map = new LinkedHashMap<>(Map.of(
                'F',
                new GameAction('F', "Find Prize", this::findPrize),
                'S',
                new GameAction('S', "Use your gun", this::useWeapon)
        ));
        map.putAll(getStandardActions());
        return map;
    }

    public boolean findPrize(int playerIndex) {
        return getPlayer(playerIndex).findPrize();
    }

    public boolean useWeapon(int playerIndex) {
        return getPlayer(playerIndex).useWeapon("pistol");
    }
}
