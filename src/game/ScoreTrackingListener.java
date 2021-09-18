// ID: 208649186

package game;

import collidables.Block;
import collisiondetection.HitListener;
import shapes.Ball;

/**
 * @author Coral Kuta.
 * A class for representation of the score listener.
 * It's a Counter who keeps track of the scores.
 */
public class ScoreTrackingListener implements HitListener {
    public static final int POINTS_PER_BLOCK = 5;

    //Field
    private final Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter - the counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(POINTS_PER_BLOCK);
    }
}
