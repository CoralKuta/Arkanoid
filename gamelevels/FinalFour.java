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
 * The last level of the game.
 * Has a full rows of blocks to pop with a narrow paddle.
 */
public class FinalFour implements LevelInformation {
    private static final int NUM_BALLS = 3;
    private static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 100;
    private static final int BLOCKS_NUM = 105;
    private static final int BLOCKS_PER_ROW = 15;
    private static final int ROW_BLOCKS = 7;


    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //Creating 3 balls - one goes to the left, one to the right and one in the middle.
        ArrayList<Velocity> ballVelocities = new ArrayList<>();
        ballVelocities.add(Velocity.fromAngleAndSpeed(345, 4));
        ballVelocities.add(Velocity.fromAngleAndSpeed(360, 4));
        ballVelocities.add(Velocity.fromAngleAndSpeed(375, 4));
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        //Creating the blue sky color of the background.
        Color sky = new Color(23, 135, 206);
        Rectangle background = new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT, sky);
        ArrayList<Shape> list = new ArrayList<>();

        //Creating three colors of the clouds.
        Color light = new Color(202, 202, 202);
        Color mid = new Color(186, 186, 186);
        Color dark = new Color(169, 169, 169);

        //Creating the left cloud.
        Circle cloud1 = new Circle(new Point(90, 400), 30, light, light);
        Circle cloud2 = new Circle(new Point(120, 420), 35, light, light);
        Circle cloud3 = new Circle(new Point(140, 380), 35, mid, mid);
        Circle cloud4 = new Circle(new Point(170, 420), 30, dark, dark);
        Circle cloud5 = new Circle(new Point(190, 380), 35, dark, dark);
        //An imaginary line that goes behind the cloud.
        Line one = new Line(90, 400, 190, 400);

        //Creating the right cloud.
        Circle cloud6 = new Circle(new Point(600, 530), 30, light, light);
        Circle cloud7 = new Circle(new Point(620, 500), 35, light, light);
        Circle cloud8 = new Circle(new Point(649, 520), 35, mid, mid);
        Circle cloud9 = new Circle(new Point(670, 480), 30, dark, dark);
        Circle cloud10 = new Circle(new Point(690, 520), 35, dark, dark);
        //An imaginary line that goes behind the cloud.
        Line two = new Line(600, 500, 690, 500);

        //Creating the rain using the imaginary line.
        double len1 = one.length() / 10;
        for (int i = 0; i < 10; i++) {
            Line line = new Line(cloud1.getX() + (i * len1), one.end().getY(),
                    (i * len1), 800, light);
            list.add(line);
        }

        //Creating the rain using the imaginary line.
        double len2 = two.length() / 11;
        for (int i = 0; i < 15; i++) {
            Line line = new Line(cloud6.getX() + (i * len2), two.end().getY(),
                    450 + (i * len2), 800, light);
            list.add(line);
        }

        //Adding all.
        list.add(cloud1);
        list.add(cloud2);
        list.add(cloud3);
        list.add(cloud4);
        list.add(cloud5);

        list.add(cloud6);
        list.add(cloud7);
        list.add(cloud8);
        list.add(cloud9);
        list.add(cloud10);

        return new Background(background, list);
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();

        //Creating an array of ordered colors.
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.LIGHT_GRAY, Color.PINK, Color.CYAN};

        //Creating 15 blocks in 7 rows.
        for (int i = 0; i < ROW_BLOCKS; i++) {
            for (int j = 0; j < BLOCKS_PER_ROW; j++) {
                Color color = colors[i];
                int upperLeftX = GameLevel.WIDTH - GameEnvironment.BORDER_SIZE - Block.BLOCK_WIDTH;
                int upperLeftY = 100;
                Point upperLeft = new Point(upperLeftX - (j * Block.BLOCK_WIDTH),
                        upperLeftY + (i * Block.BLOCK_HEIGHT));
                Block block = new Block(upperLeft, Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT, color);
                blocks.add(block);
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
        //Creating all balls in the middle.
        ArrayList<Ball> levelBalls = new ArrayList<>();
        for (int i = 0; i < NUM_BALLS; i++) {
            Ball ball = new Ball(Ball.CENTER);
            levelBalls.add(ball);
        }
        return levelBalls;
    }

    @Override
    public Color textColor() {
        return Color.WHITE;
    }
}
