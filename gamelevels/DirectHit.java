// ID: 208649186

package gamelevels;

import collidables.Block;
import game.GameLevel;
import shapes.Point;
import shapes.Circle;
import shapes.Rectangle;
import shapes.Line;
import shapes.Velocity;
import shapes.Shape;
import sprites.Background;
import shapes.Ball;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Coral Kuta.
 * The first level of the game - Direct hit to the block.
 */
public class DirectHit implements LevelInformation {
    private static final int EDGE = 36;
    private static final int NUM_BALLS = 1;
    private static final int PADDLE_SPEED = 3;
    private static final int PADDLE_WIDTH = 80;
    private static final int BLOCKS_NUM = 1;

    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> ballVelocities = new ArrayList<>();
        Velocity velocity = new Velocity(0, -6);
        ballVelocities.add(velocity);
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        int centerX = GameLevel.WIDTH / 2;
        int centerY = GameLevel.HEIGHT / 3 + 20;

        //The black background of the level.
        Rectangle background = new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT, Color.BLACK);

        //The shapes list that decorates the level.
        ArrayList<Shape> shapes = new ArrayList<>();

        Point center = new Point((centerX), centerY);

        //Creating the three circles around the target - all colored black with blue borders.
        Circle small = new Circle(center, 80, Color.BLUE, Color.BLACK);
        Circle medium = new Circle(center, 110, Color.BLUE, Color.BLACK);
        Circle large = new Circle(center, 140, Color.BLUE, Color.BLACK);

        //Add them to the list.
        shapes.add(large);
        shapes.add(medium);
        shapes.add(small);

        //Creating 4 lines of the target.
        Line up = new Line(centerX, centerY - EDGE, centerX, 60, Color.BLUE);
        double len = up.length();
        Line down = new Line(centerX, centerY + EDGE, centerX, centerY + EDGE + len, Color.BLUE);
        Line left = new Line(centerX - EDGE, centerY, centerX - EDGE - len, centerY, Color.BLUE);
        Line right = new Line(centerX + EDGE, centerY, centerX + EDGE + len, centerY, Color.BLUE);
        shapes.add(down);
        shapes.add(up);
        shapes.add(left);
        shapes.add(right);

        return new Background(background, shapes);
    }

    @Override
    public List<Block> blocks() {
        //Create one red square block to this level.
        ArrayList<Block> blocks = new ArrayList<>();
        Point upperLeft = new Point(((double) GameLevel.WIDTH / 2) - (double) (EDGE / 2),
                (double) GameLevel.HEIGHT / 3);
        Block block = new Block(upperLeft, EDGE, EDGE, Color.RED);

        //Add it to the list.
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKS_NUM;
    }

    @Override
    public List<Ball> levelBalls() {
        //Only one ball aimed directly to the target.
        ArrayList<Ball> levelBalls = new ArrayList<>();
        levelBalls.add(new Ball(Ball.CENTER));
        return levelBalls;
    }

    @Override
    public Color textColor() {
        return Color.WHITE;
    }
}
