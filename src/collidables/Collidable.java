// ID: 208649186

package collidables;

import shapes.Ball;
import shapes.Rectangle;
import shapes.Point;
import shapes.Velocity;


/**
 * @author Coral Kuta.
 * An interface for representation of collidables (rectangle) objects in the game.
 * we let them know someone hit them and the have a shape of rectangle.
 */
public interface Collidable {

    /**
     * we return the rectangle shape of the collidable object.
     *
     * @return - the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();


    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter - the ball that hit the object.
     * @param collisionPoint - where we hit the object.
     * @param currentVelocity - the sprite velocity.
     * @return a new velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}