// ID: 208649186

package gamelogic;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.Counter;
import game.GameLevel;
import gamelevels.LevelInformation;
import sprites.SpriteCollection;

/**
 * @author Coral Kuta.
 * A class of the countdown. Implements the animation interface.
 * Counts down "3, 2, 1" to the start of the level.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final int countFrom;
    private final SpriteCollection screen;
    private final Counter counter;
    private final LevelInformation info;

    /**
     * Constructor.
     * @param numOfSeconds - the number of seconds.
     * @param countFrom - from where to count.
     * @param gameScreen - the drawable screen.
     * @param info - the information of the level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, LevelInformation info) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.screen = gameScreen;
        this.counter = new Counter(countFrom);
        this.info = info;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //Creating a new Sleeper to be in charge of the timer.
        Sleeper sleeper = new Sleeper();

        //Drawing the background and then the sprites.
        this.info.getBackground().drawOn(d);
        this.screen.drawAllOn(d);

        //Drawing the counter.
        d.setColor(this.info.textColor());
        d.drawText(GameLevel.WIDTH / 2 - 50, GameLevel.HEIGHT / 2, Integer.toString(counter.getValue()), 150);

        //Multiplying by 1000 to convert milliseconds to seconds.
        if (counter.getValue()  < countFrom) {
            sleeper.sleepFor((long) (1000 * numOfSeconds / countFrom));
        }
        // 3->2->1
        counter.decrease(1);
    }

    @Override
    public boolean shouldStop() {
        return counter.getValue() < 0;
    }
}