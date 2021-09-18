// ID: 208649186

package gamelevels;

import collidables.Block;
import shapes.Ball;
import shapes.Velocity;
import sprites.Sprite;
import java.awt.Color;
import java.util.List;


/**
 * @author Coral kuta.
 * An interface to implement the information of each level - such as the blocks and ball arrange, the background
 * and more.
 */
public interface LevelInformation {

    /**
     * Number of balls.
     *
     * @return number of balls.
     */
    int numberOfBalls();


    /**
     * The initial velocity of each ball.
     *
     * @return a list with ball velocities.
     */
    List<Velocity> initialBallVelocities();


    /**
     * The speed of the paddle.
     *
     * @return speed.
     */
    int paddleSpeed();


    /**
     * The width of the paddle.
     *
     * @return width.
     */
    int paddleWidth();


    /**
     * The level's name, that will be displayed on the screen.
     *
     * @return name.
     */
    String levelName();


    /**
     * The background of the level.
     * Contains drawings in colors.
     *
     * @return Background.
     */
    Sprite getBackground();


    /**
     * Creating the layout of the blocks for the level.
     *
     * @return a list of all blocks
     */
    List<Block> blocks();


    /**
     * The number of blocks to remove from the level in order to win.
     *
     * @return the number of blocks.
     */
    int numberOfBlocksToRemove();


    /**
     * Creating the balls for the level.
     *
     * @return a list of all balls.
     */
    List<Ball> levelBalls();

    /**
     * Each level has a different color as background.
     * We want to write text in a visible and readable color.
     *
     * @return text color.
     */
    Color textColor();
}