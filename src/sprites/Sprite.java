// ID: 208649186

package sprites;

import game.GameLevel;
import biuoop.DrawSurface;


/**
 * @author Coral Kuta.
 * An interface for sprite objects.
 */
public interface Sprite {

    /**
     * Draw all sprites on the screen.
     *
      * @param d - draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add sprite to game.
     * @param game - the game.
     */
    void addToGame(GameLevel game);

    /**
     * Remove sprite from game.
     * @param game - the game.
     */
    void removeFromGame(GameLevel game);
}