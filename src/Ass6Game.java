// ID: 208649186

import gamelevels.DirectHit;
import gamelevels.WideEasy;
import gamelevels.Green3;
import gamelevels.FinalFour;
import gamelevels.LevelInformation;
import gamelogic.AnimationRunner;
import game.GameFlow;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Coral Kuta.
 * A class for running the game.
 */
public class Ass6Game {

    /**
     * Run the game.
     * @param args - arguments from the commend line.
     */
    public static void main(String[] args) {

        //Creating a new list of levels and animation runner.
        List<LevelInformation> levels = new ArrayList<>();
        AnimationRunner runner = new AnimationRunner();

        if (args.length > 0) {
            //Append the list accordingly.
            for (String arg : args) {
                switch (arg) {
                    case "1":
                        levels.add(new DirectHit());
                        break;
                    case "2":
                        levels.add(new WideEasy());
                        break;
                    case "3":
                        levels.add(new Green3());
                        break;
                    case "4":
                        levels.add(new FinalFour());
                        break;
                    default:
                        break;
                }
            }
        }

        //If no level enters to the list, the arguments were not valid - we run the whole game.
        if (levels.isEmpty()) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }

        //Creating and running a new game with all levels.
        GameFlow gameFlow = new GameFlow(runner, runner.getGui().getKeyboardSensor());
        gameFlow.runLevels(levels);
    }
}
