// ID: 208649186

package gamelogic;

import biuoop.DrawSurface;

/**
 * @author Coral Kuta.
 * An interface for the game animation.
 * Applies to the game animation itself, as well as the pause, game over and you win screens.
 */
public interface Animation {
    /**
     * What the animation should draw.
     *
     * @param d - the surface
     */
    void doOneFrame(DrawSurface d) throws ExitException;

    /**
     * indicates when the current animation should stop.
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}