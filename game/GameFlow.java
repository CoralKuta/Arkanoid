// ID: 208649186

package game;

import biuoop.KeyboardSensor;
import gamelevels.LevelInformation;
import gamelogic.*;

import java.util.List;


/**
 * @author Coral Kuta.
 * A class for the game flow.
 * It runs levels and is in charge of winning and loosing.
 */
public class GameFlow {
    public static final int LIVES = 3;

    //Fields
    private final KeyboardSensor keyboard;
    private final AnimationRunner runner;
    private final Counter score;
    private final Counter lives;
    private final ScoreTrackingListener scoreTracking;


    /**
     * Constructor.
     *
     * @param runner - animation runner.
     * @param keyboard - keyboard.
     */
    public GameFlow(AnimationRunner runner, KeyboardSensor keyboard) {
        this.runner = runner;
        this.keyboard = keyboard;
        this.score = new Counter();
        this.lives = new Counter(LIVES);
        this.scoreTracking = new ScoreTrackingListener(this.score);
    }

    /**
     * Run the levels of the game.
     *
     * @param levels - list of all levels.
     */
    public void runLevels(List<LevelInformation> levels) throws ExitException {
        try {
            // Starting with main menu.
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, new Menu(this.score)));

            //Going through all the levels.
            for (LevelInformation levelInfo : levels) {
                //Creating a new game with the same components, initialize and run it.
                GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.score, this.lives);
                level.initialize();
                level.run();

                //If the player ran out of balls, while he still has life he can keep playing.
                while ((level.getBallRemover().getRemaining().getValue() == 0)
                        && (this.lives.getValue() > 0)) {
                    level.run();
                }

                //If there are no more balls and no more life - Game Over.
                if ((level.getBallRemover().getRemaining().getValue() == 0)
                        && (this.lives.getValue() == 0)) {
                    this.runner.run(new KeyPressStoppableAnimation(
                            this.keyboard, new GameOver(levelInfo, this.score)));
                    this.score.setValue(0);
                    this.lives.setValue(LIVES);
                    runLevels(levels);
                }

                //If we got to the last level, and we have no more blocks - WIN
                if ((levels.size() - 1 == levels.indexOf(levelInfo))
                        && (level.getBlockRemover().getRemaining().getValue() == 0)) {
                    this.runner.run(new KeyPressStoppableAnimation(
                            this.keyboard, new YouWin(levelInfo, this.score)));
                    this.score.setValue(0);
                    this.lives.setValue(LIVES);
                    runLevels(levels);
                }

            }
            //Close the gui.
            this.runner.getGui().close();
        } catch (ExitException ex) {
            this.runner.getGui().close();
        }
    }
}
