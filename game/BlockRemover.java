// ID: 208649186

package game;

import collidables.Block;
import collisiondetection.HitListener;
import shapes.Ball;

/**
 * @author Coral Kuta.
 * A class for removing blocks when they are being hit by the ball.
 * The class implements hit listener and that's how it knows when balls hit blocks.
 */
public class BlockRemover implements HitListener {
    //Fields
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game            - the game.
     * @param remainingBlocks - number of remaining balls.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Getter.
     *
     * @return blocks left.
     */
    public Counter getRemaining() {
        return remainingBlocks;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        this.getRemaining().decrease(1);
    }
}
