// ID: 208649186

package game;

import collidables.Block;
import collisiondetection.HitListener;
import shapes.Ball;

/**
 * @author Coral Kuta.
 * A class for removing balls when they are out of the screen.
 * The class implements hit listener and that's how it knows when balls are out.
 */
public class BallRemover implements HitListener {
    //Fields
    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game           - the game.
     * @param remainingBalls - number of remaining balls.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }


    /**
     * Getter.
     *
     * @return balls left.
     */
    public Counter getRemaining() {
        return remainingBalls;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
