// ID: 208649186

package gamelogic;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import game.GameLevel;

/**
 * @author Coral Kuta.
 * A class for representation of the animation runner.
 * It does all the work of running the game, the loop and the timing.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;


    /**
     * Constructor.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Runs the animation until it is told to stop.
     * @param animation - the animation that's running.
     */
    public void run(Animation animation) throws ExitException {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;

        while (!animation.shouldStop()) {
            //Timing.
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            //Do one frame.
            animation.doOneFrame(d);
            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Getter.
     * @return gui.
     */
    public GUI getGui() {
        return this.gui;
    }


}