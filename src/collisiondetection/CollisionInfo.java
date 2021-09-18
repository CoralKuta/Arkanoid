// ID: 208649186

package collisiondetection;


import shapes.Point;
import collidables.Collidable;



/**
 * @author Coral Kuta.
 * A class for holding information about coliision.
 */
public class CollisionInfo {
    // Fields
    private final Collidable collisionObject;
    private final Point collisionPoint;

    /**
     * A constructor for collision information.
     * @param collidable - the collidable object.
     * @param point - the collision point.
     */
    public CollisionInfo(Collidable collidable, Point point) {
        this.collisionObject = collidable;
        this.collisionPoint = point;
    }

    /**
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }


    /**
     *
     * @return - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}