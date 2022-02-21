// ID: 208649186

package collidables;

import biuoop.KeyboardSensor;
import game.GameEnvironment;
import shapes.Ball;
import shapes.Velocity;
import shapes.Rectangle;
import shapes.Point;
import shapes.Line;
import sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import game.GameLevel;


/**
 * @author Coral Kuta.
 * A class for representation of the paddle.
 * Paddle implements sprite (an object in the game) and a collidable (a object a ball can collide with).
 */
public class Paddle implements Sprite, Collidable {
    public static final int PADDLE_HEIGHT = 20;
    public static final double DX = 8;
    public static final int GAP = 2;
    public static final int REGIONS = 5;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FORTH = 3;
    public static final int FIFTH = 4;

    // Fields
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle body;
    private final Color color;
    private Velocity velocity;


    /**
     * Constructor of the paddle.
     *
     * @param keyboard - keyboard.
     * @param point - upper left point.
     * @param width - width.
     * @param height - height.
     */
    public Paddle(KeyboardSensor keyboard, Point point, int width, int height) {
        this.keyboard = keyboard;
        this.body = new Rectangle(point, width, height, Color.ORANGE);
        this.color = Color.ORANGE;
        this.velocity = new Velocity(0, 0);
    }


    /**
     * Move the paddle to the left when user presses "LEFT" on the keyboard.
     */
    public void moveLeft() {
        //Change the paddle velocity to go left.
        if (this.body.getUpperLeft().getX() > GameEnvironment.BORDER_SIZE + GAP) {
            Velocity newVelocity = new Velocity(-DX, 0);

            this.body = newVelocity.applyToRectangle(this.body);
            this.velocity = newVelocity;
        }
    }

    /**
     * Move the paddle to the right when user presses "RIGHT" on the keyboard.
     */
    public void moveRight() {
        //Change the paddle velocity to go right.
        if (this.body.getUpperRight().getX() < GameLevel.WIDTH - GameEnvironment.BORDER_SIZE - GAP) {
            Velocity newVelocity = new Velocity(DX, 0);

            //Apply new velocity to the rectangle shape of the paddle.
            this.body = newVelocity.applyToRectangle(this.body);
            this.velocity = newVelocity;
        }
    }


    @Override
    public void drawOn(DrawSurface d) {
        this.body.drawOn(d, this.color);
    }


    @Override
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        }

        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Accessor to the velocity of the paddle.
     *
     * @return the paddle's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return this.body;
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //Creating regions and current speed.
        Line[] regions = regionsLines();
        double speed = currentVelocity.getSpeed();

        //For each region of the paddle we change to another angle.
         if (collisionPoint.isOnLine(regions[FIRST])) {
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if (collisionPoint.isOnLine(regions[SECOND])) {
            return Velocity.fromAngleAndSpeed(330, speed);
        } else if (collisionPoint.isOnLine(regions[THIRD])) {
            return Velocity.fromAngleAndSpeed(0, speed);
        } else if (collisionPoint.isOnLine(regions[FORTH])) {
            return Velocity.fromAngleAndSpeed(30, speed);
        } else if (collisionPoint.isOnLine(regions[FIFTH])) {
            return Velocity.fromAngleAndSpeed(60, speed);
        }

         //Delegation for a block shape.
        Block block = new Block(this.body, this.color);
        return block.hit(hitter, collisionPoint, currentVelocity);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {

    }


    /**
     * Create semi lines for splitting the paddle to regions.
     *
     * @return an array of lines
     */
    private Line[] regionsLines() {
        double portion = this.body.getWidth() / REGIONS;
        Point start = this.body.getUpperLeft();

        Line[] array = new Line[REGIONS];

        //Starting from the upper left point, we create new fake lines.
        for (int i = 0; i < REGIONS; i++) {
            Point end = new Point(start.getX() + portion, start.getY());
            Line line = new Line(start, end);
            array[i] = line;

            start = end;
        }
        return array;
    }
}
