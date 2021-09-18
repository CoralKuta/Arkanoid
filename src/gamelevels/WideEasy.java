// ID: 208649186

package gamelevels;

import collidables.Block;
import game.GameEnvironment;
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
 * The second leve of the game.
 * It has a bright background and a wide paddle.
 */
public class WideEasy implements LevelInformation {
    private static final int CENTER_SUN_X = 150;
    private static final int CENTER_SUN_Y = 150;
    private static final int OUTER_RADIUS = 60;
    private static final int MID_RADIUS = OUTER_RADIUS - 10;
    private static final int INNER_RADIUS = MID_RADIUS - 10;
    private static final int NUM_BALLS = 10;
    private static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 600;
    private static final int BLOCKS_NUM = 15;
    private static final int SUNBEAM = 150;
    private static final int TOP_BLOCK = 250;


    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> ballVelocities = new ArrayList<>();

        //Create half balls with an angle to the left.
        for (int i = 0; i < NUM_BALLS / 2; i++) {
            int angle = 315 + (9  * i);
            ballVelocities.add(Velocity.fromAngleAndSpeed(angle, 5));
        }

        //Create half balls with an angle to the right.
        for (int i = 0; i < NUM_BALLS / 2; i++) {
            int angle = 369 + (9  * i);
            ballVelocities.add(Velocity.fromAngleAndSpeed(angle, 5));

        }
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
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        //Creating the white background of the level.
        Rectangle background = new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT, Color.WHITE);
        ArrayList<Shape> list = new ArrayList<>();

        //The center of the sun.
        Point center = new Point(CENTER_SUN_X, CENTER_SUN_Y);

        //The outer brighter sun.
        Color outer = new Color(237, 229, 175);
        Circle outerSun = new Circle(center, OUTER_RADIUS, outer, outer);

        //The mid sun.
        Color mid = new Color(234, 213, 72);
        Circle midSun = new Circle(center, MID_RADIUS, mid, mid);

        //The inner darker sun.
        Color inner = new Color(253, 223, 24);
        Circle innerSun = new Circle(center, INNER_RADIUS, inner, inner);

        //Creating the sunbeams.
        for (int i = 0; i < SUNBEAM; i++) {
            Line line = new Line(center.getX(), center.getY(), i * 5, TOP_BLOCK, outer);
            list.add(line);
        }

        //Add suns to the game.
        list.add(outerSun);
        list.add(midSun);
        list.add(innerSun);

        return new Background(background, list);
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        //Create an array of colors by order.
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN};

        int startX = GameEnvironment.BORDER_SIZE + 5;
        int width = (GameLevel.WIDTH - (2 * GameEnvironment.BORDER_SIZE)) / 15;
        int height = Block.BLOCK_HEIGHT;
        int colorIndex = 0;

        for (int i = 0; i < 15; i++) {
            int addition = width * i;
            Block block = new Block(new Point(startX + addition, TOP_BLOCK), width, height, colors[colorIndex]);
            blocks.add(block);

            //Increasing color index every 2 odd blocks at first.
            if (i < 7) {
                if (i % 2 != 0) {
                    colorIndex++;
                }
                //After the middle, increasing color index every 2 even blocks.
            } else {
                if (i % 2 == 0) {
                    colorIndex++;
                }
            }

        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKS_NUM;
    }

    @Override
    public List<Ball> levelBalls() {
        ArrayList<Ball> levelBalls = new ArrayList<>();

        //Create all balls at the middle.
        for (int i = 0; i < NUM_BALLS; i++) {
            Ball ball = new Ball(Ball.CENTER);
            levelBalls.add(ball);
        }

        return levelBalls;
    }

    @Override
    public Color textColor() {
        return Color.BLACK;
    }
}
