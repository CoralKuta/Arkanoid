// ID: 208649186

package game;

import collidables.Block;
import sprites.Sprite;
import shapes.Point;
import biuoop.DrawSurface;
import java.awt.Color;


/**
 * @author Coral Kuta.
 * A class for representation of a Score Indicator - a special block up the screen that shows the score.
 */
public class ScoreIndicator implements Sprite {
    public static final int SIZE = 15;

    //Fields
    private final Block scoreIndicator;
    private final Counter score;
    private final Counter lives;
    private final String levelName;

    /**
     * Constructor for score indicator.
     *
     * @param lives - live left
     * @param score - score won
     * @param name - the level's name
     */
    public ScoreIndicator(Counter lives, Counter score, String name) {
        this.scoreIndicator = new Block(new Point(0, 0), GameLevel.WIDTH,
                GameEnvironment.BORDER_SIZE, Color.LIGHT_GRAY);
        this.score = score;
        this.lives = lives;
        this.levelName = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.scoreIndicator.drawOn(d);

        //Writing lives.
        d.drawText(GameLevel.WIDTH / 10, GameEnvironment.BORDER_SIZE - 3, "Lives: " + this.lives.getValue(), SIZE);

        //Writing score.
        d.drawText(GameLevel.WIDTH / 2 - 20, GameEnvironment.BORDER_SIZE - 3, "Score: " + this.score.getValue(), SIZE);

        //Writing level name.
        d.drawText(GameLevel.WIDTH / 12 * 9, GameEnvironment.BORDER_SIZE - 3, "Level Name: " + this.levelName,  SIZE);

    }

    @Override
    public void timePassed() {
        this.scoreIndicator.timePassed();
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
