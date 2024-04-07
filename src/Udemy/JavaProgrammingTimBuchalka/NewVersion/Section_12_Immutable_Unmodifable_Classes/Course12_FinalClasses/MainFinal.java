package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course12_FinalClasses;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course12_FinalClasses.game.GameConsole;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course12_FinalClasses.pirate.PirateGame;


/*
class SpecialGameAction extends GameAction {
    public SpecialGameAction(char key, String prompt, Predicate<Integer> action) {
        super(key, prompt, action);
    }
}
*/

/*
class SpecialGameAction extends Weapon {}
*/

/*
class SpecialGameConsole<T extends Game<? extends Player>> extends GameConsole<Game<? extends Player>> {

    public SpecialGameConsole(Game<? extends Player> game) {
        super(game);
    }
}
*/

public class MainFinal {

    public static void main(String[] args) {

        //SpecialGameConsole<PirateGame> game = new SpecialGameConsole<>(new PirateGame("Pirate Game"));


        GameConsole<PirateGame> game = new GameConsole<>(new PirateGame("Pirate Game"));

    }
}
