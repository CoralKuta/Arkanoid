// ID: 208649186

package collisiondetection;

import shapes.Ball;
import collidables.Block;

/**
 * @author Coral Kuta.
 * An interface for representation of listeners from the Listener Pattern.
 */
public interface HitListener {

    /**
     *
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit - the block that is being hit.
     * @param hitter - the ball that hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
