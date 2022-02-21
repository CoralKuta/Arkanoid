// ID: 208649186

package gamelevels;

import collidables.Block;
import game.GameEnvironment;
import game.GameLevel;
import shapes.Point;
import shapes.Circle;
import shapes.Rectangle;
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
 * The third level of the game.
 * Has a green background and colorful blocks.
 */
public class Green3 implements LevelInformation {
    private static final int BUILDING_WIDTH = 120;
    private static final int NUM_BALLS = 2;
    private static final int PADDLE_SPEED = 4;
    private static final int PADDLE_WIDTH = 100;
    private static final int BLOCKS_NUM = 50;
    private static final int OUTER_RADIUS = 15;
    private static final int MID_RADIUS = OUTER_RADIUS - 5;
    private static final int INNER_RADIUS = MID_RADIUS - 5;


    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> ballVelocities = new ArrayList<>();

        //Creating to balls in opposite directions.
        Velocity velocity1 = Velocity.fromAngleAndSpeed(405, 5);
        Velocity velocity2 = Velocity.fromAngleAndSpeed(315, 5);
        ballVelocities.add(velocity1);
        ballVelocities.add(velocity2);
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        //Creating the green background.
        Rectangle background = new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT,
                new Color(42, 129, 21));
        ArrayList<Shape> list = new ArrayList<>();

        int buildingPartW = BUILDING_WIDTH / 6;
        int buildingHeight = GameLevel.HEIGHT / 3;
        int startBuildingX = GameEnvironment.BORDER_SIZE * 3;
        int darkHeight = 350;

        //Creating the darker part of the building.
        Color grayer = new Color(62, 58, 57);
        Point darkUpperLeft = new Point(startBuildingX + buildingPartW * 2, darkHeight);
        Rectangle dark = new Rectangle(darkUpperLeft, buildingPartW * 2, buildingHeight, grayer);
        list.add(dark);

        //Creating the chimney of the building.
        Color gray = new Color(77, 73, 72);
        Point brightUpperLeft = new Point(dark.getUpperLeft().getX() + 0.3 * dark.getWidth(), 150);
        Rectangle bright = new Rectangle(brightUpperLeft, buildingPartW - 5, buildingHeight, gray);
        list.add(bright);

        //Creating the building itself.
        Color grayest = new Color(46, 42, 41);
        Point buildingUpperLeft = new Point(startBuildingX, buildingHeight * 2);
        Rectangle building = new Rectangle(buildingUpperLeft, BUILDING_WIDTH, buildingHeight, grayest);
        list.add(building);

        //Creating all the white windows.
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                int windowW = 15;
                int windowH = 30;
                Point upperLeft = new Point(69 + j * (windowW + 7), 412 + i * (windowH + 5));
                Rectangle window = new Rectangle(upperLeft, windowW, windowH, Color.WHITE);
                list.add(window);
            }
        }

        //Creating the edge light of the chimney.
        Color outerC = new Color(255, 202, 122);
        Circle outer = new Circle(new Point(building.getUpperLeft().getX() + (double) BUILDING_WIDTH / 2,
                brightUpperLeft.getY()), OUTER_RADIUS, outerC, outerC);

        Color midC = new Color(244, 76, 54);
        Circle mid = new Circle(new Point(building.getUpperLeft().getX() + (double) BUILDING_WIDTH / 2,
                brightUpperLeft.getY()), MID_RADIUS, midC, midC);

        Color innerC = new Color(215, 192, 192);
        Circle inner = new Circle(new Point(building.getUpperLeft().getX() + (double) BUILDING_WIDTH / 2,
                brightUpperLeft.getY()), INNER_RADIUS, innerC, innerC);

        list.add(outer);
        list.add(mid);
        list.add(inner);

        return new Background(background, list);
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.LIGHT_GRAY};

        //Creating blocks like the previous assignment.
        for (int i = 0; i < 5; i++) {
            Color color = colors[i];
            int j = GameLevel.WIDTH - GameEnvironment.BORDER_SIZE - Block.BLOCK_WIDTH;
            int colWidth = i * Block.BLOCK_WIDTH + Block.BLOCK_WIDTH * 3;
            for (; j > colWidth; j -= Block.BLOCK_WIDTH) {
                Point upperLeft = new Point(j, Block.BLOCK_HEIGHT * (i + 7));
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
        //Creating 2 balls of this level.
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
