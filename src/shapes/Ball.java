// ID: 208649186

package shapes;

import collidables.Block;
import collisiondetection.HitListener;
import collisiondetection.HitNotifier;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import collidables.Paddle;
import collisiondetection.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import sprites.Sprite;


/**
 * @author Coral Kuta.
 * A class for representation of a Ball.
 * Ball is a circle (extends Circle) that has velocity and can move.
 */
public class Ball extends Circle implements Sprite, HitNotifier {
    public static final int RADIUS = 5;
    public static final Point CENTER = new Point((double) GameLevel.WIDTH / 2,
            GameLevel.HEIGHT - GameEnvironment.BORDER_SIZE - Ball.RADIUS - Paddle.PADDLE_HEIGHT);

    // fields:
    private Point center;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    // The list of the listeners to the ball's hit.
    private final List<HitListener> hitListeners;


    /**
     * Constructor with a center point, default borders.
     *
     * @param center          - the center point of the ball.
     * @param radius          - the ball's radius (size).
     * @param color           - the ball's color.
     * @param gameEnvironment - the ball's game environment.
     */
    public Ball(Point center, int radius, java.awt.Color color, GameEnvironment gameEnvironment) {

        //Calling the circle constructor for the base part.
        super(center, radius, color, color);

        this.center = super.getCenter();
        //Setting the velocity of the ball to 0 - default static ball.
        this.velocity = new Velocity(0, 0);

        this.gameEnvironment = gameEnvironment;

        //Create a new list of listeners.
        this.hitListeners = new ArrayList<>();

    }


    /**
     * Default constructor for white ball.
     *
     * @param center - the center of the ball
     */
    public Ball(Point center) {

        //Calling the circle constructor for the base part.
        super(center, RADIUS, Color.BLACK, Color.WHITE);
        this.center = super.getCenter();
        // Setting the velocity of the ball to 0 - default static ball.
        this.velocity = new Velocity(0, 0);

        this.gameEnvironment = null;

        //Create a new list of listeners.
        this.hitListeners = new ArrayList<>();
    }


    /**
     * Constructor with x and y value for creating a center point to the ball, default borders.
     *
     * @param x               - the x value of the ball's center point.
     * @param y               - the y value of the ball's center point.
     * @param radius          - the ball's radius (size).
     * @param color           - the ball's color.
     * @param gameEnvironment - the ball's game environment.
     */
    public Ball(int x, int y, int radius, java.awt.Color color, GameEnvironment gameEnvironment) {

        //Calling the circle constructor for the base part.
        super(new Point(x, y), radius, color, color);

        this.center = super.getCenter();
        // Setting the velocity of the ball to 0 - default static ball.
        this.velocity = new Velocity(0, 0);

        this.gameEnvironment = gameEnvironment;

        //Create a new list of listeners.
        this.hitListeners = new ArrayList<>();

    }


    /**
     * Accessor to the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }



    /**
     * Setter.
     *
     * @param v - setting the new Velocity to the ball's velocity.
     */
    private void setVelocity(Velocity v) {
        this.velocity = v;
    }


    /**
     * Set the new velocity using dx dy.
     *
     * @param dx - the difference in the x axis.
     * @param dy - the difference in the y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }


    /**
     * Changes the direction of the ball when it hits a collidable object, and notifies hit if
     * a hit occurred.
     */
    public void moveOneStep() {
        // Saving the old dx and dy.
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();

        // Get the signs of them to know which direction change the ball to.
        int dxSign = this.getVelocity().getSignDx();
        int dySign = this.getVelocity().getSignDy();

        // Shorting names.
        Point centerP = this.center;
        Block down = this.gameEnvironment.getDown();
        Paddle paddle = (Paddle) gameEnvironment.getCollidables().get(0);

        // If we detected a collision with the paddle
        if (paddle.getCollisionRectangle().containsPoint(this.center)) {

            // First we make sure the ball is hit in the borders and not inside the paddle.
            if (this.isInBorders()) {
                getOutOfPaddle(paddle);
                return;
            }

            // If the ball is not in the bounds of the game we notify hit with the dead region to remove it.
            this.notifyHit(down, this);
        }

        // We make a trajectory and get the closest collision.
        Point end = new Point((centerP.getX() + dx + dxSign), (centerP.getY() + dy + dySign));
        Line trajectory = new Line(centerP, end);
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);

        // If there is a collision point we call hit() to set a new velocity.
        if ((info.collisionPoint() != null)) {
            Point hitPoint = info.collisionPoint();
            Velocity newVelocity = info.collisionObject().hit(this, hitPoint, this.velocity);
            this.setVelocity(newVelocity);

            // If the  ball hit the death region, we notify hit and remove it from the game.
            if (info.collisionPoint().isOnLine(down.getCollisionRectangle().upLine())) {
                notifyHit(down, this);
                return;
            }
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * If the ball is inside the paddle we want him to get out.
     *
     * @param paddle - the paddle.
     */
    private void getOutOfPaddle(Paddle paddle) {

        // Saving the old dx and dy, and the dx sign.
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        int dxSign = this.velocity.getSignDx();
        double newDx = this.velocity.getDx();

        // Shorting.
        Point upperLeft = paddle.getCollisionRectangle().getUpperLeft();
        Point upperRight = paddle.getCollisionRectangle().getUpperRight();

        if ((this.center.distance(upperLeft) <= this.center.distance(upperRight)) && (dxSign > 0)) {
            this.setVelocity(-dx, dy);
            dxSign = this.velocity.getSignDx();
            newDx = this.velocity.getDx();

            if (dx == 0) {
                this.setVelocity(-1, dy);
                dxSign = this.velocity.getSignDx();
                newDx = this.velocity.getDx();
            }
        }

        if ((this.center.distance(upperLeft) > this.center.distance(upperRight)) && (dxSign < 0)) {
            this.setVelocity(-dx, dy);
            dxSign = this.velocity.getSignDx();
            newDx = this.velocity.getDx();
        }

        this.velocity = new Velocity(dxSign * Paddle.DX + 1.5 * newDx, -2);
        this.center = this.getVelocity().applyToPoint(this.center);
        this.velocity = new Velocity(newDx, dy);
    }


    /**
     * Checks if the ball is in the borders of the game and didn't got out.
     *
     * @return true - if the ball is in the borders, false otherwise.
     */
    private boolean isInBorders() {
        double centerX = this.center.getX();
        double centerY = this.center.getY();
        int size = super.getSize();

        int maxLeft = GameEnvironment.BORDER_SIZE;
        int maxRight = GameLevel.WIDTH - GameEnvironment.BORDER_SIZE;
        int maxUp = GameEnvironment.BORDER_SIZE;

        return ((centerX - size >= maxLeft + Paddle.DX) && (centerX + size <= maxRight - Paddle.DX)
                && (centerY - size >= maxUp));
    }


    @Override
    public void drawOn(DrawSurface surface) {
        // Converting to int the ball's center values.
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();

        // Draw the ball
        surface.setColor(super.getMainColor());
        surface.fillCircle(x, y, super.getSize());

        // Draw a smaller dot inside the ball.
        surface.setColor(super.getBorderColor());
        surface.drawCircle(x, y, super.getSize());

    }


    @Override
    public void timePassed() {
        this.moveOneStep();
    }



    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }


    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies a hit with a block when happens.
     *
     * @param down - the death region.
     * @param hitter - the ball the hit the region.
     */
    private void notifyHit(Block down, Ball hitter) {
        /* Make a copy of the hitListeners before iterating over them, so when we change the list during the
         iteration the original list won't be hurt. */
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(down, hitter);
        }
    }

    /**
     * Setter.
     * @param environment - the game environment.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }
}
