// ID: 208649186

package collidables;

import collisiondetection.HitListener;
import shapes.Ball;
import shapes.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import shapes.Rectangle;
import shapes.Point;
import game.GameLevel;
import collisiondetection.HitNotifier;
import sprites.Sprite;


/**
 * @author Coral Kuta.
 * A class for representation of a Block.
 * A block implements collidable - we can hit him, and aslo implements sprite - it's an object in the game.
 * Block has a rectangle shape.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    public static final int BLOCK_WIDTH = 51;
    public static final int BLOCK_HEIGHT = 25;

    //Fields
    private final Rectangle body;
    private final Color color;

    // The list of the listeners that the block needs to notify to.
    private final List<HitListener> hitListeners;


    /**
     * Constructor for borders in gray.
     *
     * @param point  - upper left.
     * @param width  - width.
     * @param height - height.
     */
    public Block(Point point, int width, int height) {
        this.body = new Rectangle(point, width, height, Color.GRAY);
        this.color = Color.gray;

        //Create a new list of listeners.
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor for blocks in general.
     *
     * @param point  - upper left.
     * @param width  - width.
     * @param height - height.
     * @param color  - color.
     */
    public Block(Point point, int width, int height, Color color) {
        this.body = new Rectangle(point, width, height, color);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor for blocks given a rectangle.
     *
     * @param rectangle - the rectangle body of the block.
     * @param color     - color.
     */
    public Block(Rectangle rectangle, Color color) {
        this.body = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return this.body;
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //Use the rectangle hit.
        this.notifyHit(hitter);
        return this.body.hit(collisionPoint, currentVelocity);

    }

    /**
     * Notify the listeners when the ball hit that block.
     *
     * @param hitter - the ball that hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


    @Override
    public void drawOn(DrawSurface d) {
        this.body.drawOn(d, this.color);
    }


    @Override
    public void timePassed() {
    }


    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

}
